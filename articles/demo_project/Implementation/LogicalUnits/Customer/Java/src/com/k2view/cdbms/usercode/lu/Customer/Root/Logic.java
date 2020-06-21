/////////////////////////////////////////////////////////////////////////
// LU Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.Customer.Root;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;

import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.shared.Globals;
import com.k2view.cdbms.shared.user.UserCode;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;
import com.k2view.cdbms.func.oracle.OracleToDate;
import com.k2view.cdbms.func.oracle.OracleRownum;
import com.k2view.cdbms.usercode.lu.Customer.*;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.lu.Customer.Globals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends UserCode {


	@type(RootFunction)
	@out(name = "merchant_id", type = Long.class, desc = "")
	@out(name = "merchant_type", type = String.class, desc = "")
	@out(name = "merchant_name", type = String.class, desc = "")
	@out(name = "tin", type = String.class, desc = "")
	@out(name = "effective_start_date", type = String.class, desc = "")
	@out(name = "effective_end_date", type = String.class, desc = "")
	public static void fnPop_merchant(String input) throws Exception {
		String sql = "SELECT merchant_id, merchant_type, merchant_name, tin, effective_start_date, effective_end_date FROM yp_cust.merchant";
		Db.Rows rows = db("MYSQL_SOURCE").fetch(sql);
		for (Db.Row row:rows){
			yield(row.cells());
		}
	}


	@type(RootFunction)
	@out(name = "CUSTOMER_ID", type = Double.class, desc = "")
	@out(name = "ACTIVITY_ID", type = Double.class, desc = "")
	@out(name = "ACTIVITY_DATE", type = String.class, desc = "")
	@out(name = "ACTIVITY_NOTE", type = String.class, desc = "")
	@out(name = "IID", type = String.class, desc = "")
	public static void fnPop_ACTIVITY(String input) throws Exception {
		String sql = "SELECT CUSTOMER_ID, ACTIVITY_ID, ACTIVITY_DATE, ACTIVITY_NOTE, fnCreateInstId(?) IID FROM Customer.ACTIVITY";
		Db.Rows rows = ludb().fetch(sql,input);
		for (Db.Row row:rows){
			yield(row.cells());
		}
	}


	@type(RootFunction)
	@out(name = "CUSTOMER_ID", type = String.class, desc = "")
	@out(name = "ACTIVITY_ID", type = String.class, desc = "")
	@out(name = "CASE_ID", type = String.class, desc = "")
	@out(name = "NOTE_ID", type = String.class, desc = "")
	@out(name = "NOTE_TEXT", type = String.class, desc = "")
	public static void fnPop_ACT_CASE_NOTE(String input) throws Exception {
		//-----------------------------------------------------//
		// Example of a Root function which has 3 DB queries:  //
		// one from LU and 2 from another DB source            //
		//-----------------------------------------------------//
		
		String sql1 = "Select * From ACTIVITY where CUSTOMER_ID = ?";
		String sql2 = "Select * from CASES where ACTIVITY_ID = ?";
		String sql3 = "Select * from CASE_NOTE where CASE_ID = ?";
		
		String customerID="";
		String activityID="";
		String caseID="";
		String noteID="999";
		String noteText="xxx";
		
		Db.Rows caseRows;
		Db.Rows caseNotesRows;
		Db.Rows rows = ludb().fetch(sql1,input);
		
		for (Db.Row row:rows) {
			if (row.cell(0) != null) 
				customerID = row.cell(0).toString();
			if (row.cell(1) != null) 
				activityID = row.cell(1).toString();
			
			caseRows = db("CRM_DB").fetch(sql2,activityID);
			for (Db.Row caseRow:caseRows) {
					if (caseRow.cell(1) != null) 
						caseID = caseRow.cell(1).toString();
		
					caseNotesRows = db("CRM_DB").fetch(sql3,caseID);	
					//----------Example for taking the data from additional LU--------------------//
					//In 6.1 this option doesn't work in the Data viewer but only in Fabric server//
					//caseNotesRows = ludb("CRM",input).fetch(sql3,caseID);
					//----------------------------------------------------------------------------//
					for (Db.Row caseNoteRow:caseNotesRows) {
						if (caseNoteRow.cell(1) != null) 
							noteID = caseNoteRow.cell(1).toString();
						if (caseNoteRow.cell(3) != null) 
							noteText = caseNoteRow.cell(3).toString();
					
						List<String> caseList = new ArrayList<String>();
						caseList.add(customerID);
						caseList.add(activityID);
						caseList.add(caseID);
						caseList.add(noteID);
						caseList.add(noteText);
						
						Object[] result = caseList.toArray();
						yield(result);
					}
			}
		 }
	}

	
	
	
	
}
