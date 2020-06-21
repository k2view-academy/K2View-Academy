/////////////////////////////////////////////////////////////////////////
// LU Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.CRM.Root;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;
import java.util.concurrent.TimeUnit;

import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.shared.Globals;
import com.k2view.cdbms.shared.user.UserCode;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;
import com.k2view.cdbms.func.oracle.OracleToDate;
import com.k2view.cdbms.func.oracle.OracleRownum;
import com.k2view.cdbms.usercode.lu.CRM.*;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.lu.CRM.Globals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends UserCode {


	@type(RootFunction)
	@out(name = "CASE_ID", type = Double.class, desc = "")
	@out(name = "NOTE_ID", type = Double.class, desc = "")
	@out(name = "NOTE_DATE", type = String.class, desc = "")
	@out(name = "NOTE_TEXT", type = String.class, desc = "")
	public static void fnPop_CASE_NOTE(String input, String gg) throws Exception {
		// One option for comment
		
		/*
		
			Option 2 for comment
		*/
		String sql = "SELECT CASE_ID, NOTE_ID, NOTE_DATE, NOTE_TEXT FROM CASE_NOTE";
		Db.Rows rows = db("CRM_DB").fetch(sql);
		for (Db.Row row:rows){
			yield(row.cells());
		}
	}


	@type(RootFunction)
	@out(name = "CUSTOMER_ID", type = String.class, desc = "")
	@out(name = "ACTIVITY_ID", type = String.class, desc = "")
	@out(name = "ACTIVITY_DATE", type = String.class, desc = "")
	@out(name = "ACTIVITY_NOTE", type = String.class, desc = "")
	public static void fnPop_ACTIVITY(String input) throws Exception {

		Db ci = ludb("Customer", input);
		/*Db ci = db("fabric");

		ci.execute("get Customer." + input);*/

		String sql = "SELECT CUSTOMER_ID, ACTIVITY_ID, ACTIVITY_DATE, ACTIVITY_NOTE FROM Customer.ACTIVITY";
		Db.Rows rows = ci.fetch(sql);
		for (Db.Row row:rows){
			yield(row.cells());
		}
	}


	@type(RootFunction)
	@out(name = "ACTIVITY_ID", type = Double.class, desc = "")
	@out(name = "CASE_ID", type = Double.class, desc = "")
	@out(name = "CASE_DATE", type = String.class, desc = "")
	@out(name = "CASE_TYPE", type = String.class, desc = "")
	@out(name = "STATUS", type = String.class, desc = "")
	public static void fnPop_CASES(String input) throws Exception {
		// TEST- add a seep
		//TimeUnit.SECONDS.sleep(1);
		
		String sql = "SELECT ACTIVITY_ID, CASE_ID, CASE_DATE, CASE_TYPE, STATUS FROM CRM_DB.CASES where activity_id = ?";
		Db.Rows rows = db("CRM_DB").fetch(sql, input);
		
		// Tali- Fabric 6.1- 10-May-20- fix the syntax to use for each
		db("CRM_DB").fetch(sql, input).each(row->{
			yield(row.cells());
		});
		
		
		// TEST- skip sync
		//if(Double.parseDouble(input) > 100)
		//{
			//log.info("activity id: " + input + ", skip sync");
			//skipSync();
		//}
		//for (Db.Row row:rows){
		//	yield(row.cells());
		//}
	}


	@type(RootFunction)
	@out(name = "SUBSCRIBER_ID", type = Long.class, desc = "")
	@out(name = "INVOICE_ID", type = Long.class, desc = "")
	@out(name = "ISSUED_DATE", type = String.class, desc = "")
	@out(name = "DUE_DATE", type = String.class, desc = "")
	@out(name = "STATUS", type = String.class, desc = "")
	@out(name = "BALANCE", type = Long.class, desc = "")
	@out(name = "INVOICE_IMAGE", type = Byte[].class, desc = "")
	public static void fnPop_invoice(String input) throws Exception {
		String sql = "Select *"
				+ " From BILLING_DB.INVOICE";
		Db.Rows rows = db("BILLING_DB").fetch(sql);
		for (Db.Row row:rows){
			yield(row.cells());
		}
	}


	@type(RootFunction)
	@out(name = "CUSTOMER_ID", type = Long.class, desc = "")
	@out(name = "ACTIVITY_ID", type = Long.class, desc = "")
	@out(name = "ACTIVITY_DATE", type = String.class, desc = "")
	@out(name = "ACTIVITY_NOTE", type = String.class, desc = "")
	public static void fnPop_popAct(String input) throws Exception {
		String sql = "SELECT * From ACTIVITY ";
		Db.Rows rows = db("CRM_DB").fetch(sql);
		for (Db.Row row:rows){
				yield(row.cells());	
		}
	}


	@type(RootFunction)
	@out(name = "SUBSCRIBER_ID", type = Long.class, desc = "")
	@out(name = "BALANCE_ID", type = Long.class, desc = "")
	@out(name = "BALANCE_REF_ID", type = Long.class, desc = "")
	@out(name = "AVAILABLE_AMOUNT", type = Long.class, desc = "")
	@out(name = "RESET_AMOUNT", type = Long.class, desc = "")
	@out(name = "RESET_DATE", type = String.class, desc = "")
	public static void fnPop_ttt_lll(String input) throws Exception {
		String sql = "Select *"
				+ " From BILLING_DB.BALANCE";
		Db.Rows rows = db("BILLING_DB").fetch(sql);
		for (Db.Row row:rows){
			yield(row.cells());
		}
	}


	@type(RootFunction)
	@out(name = "INVOICE_ID", type = Long.class, desc = "")
	@out(name = "PAYMENT_ID", type = Long.class, desc = "")
	@out(name = "ISSUED_DATE", type = String.class, desc = "")
	@out(name = "STATUS", type = String.class, desc = "")
	@out(name = "AMOUNT", type = String.class, desc = "")
	public static void fnPop_PAYMENT(String input) throws Exception {
		String sql = "SELECT INVOICE_ID, PAYMENT_ID, ISSUED_DATE, STATUS, AMOUNT FROM CRM_DB.PAYMENT";
		db("CRM_DB").fetch(sql).each(row->{
			yield(row.cells());
		});
	}


	@type(RootFunction)
	@out(name = "SUBSCRIBER_ID", type = Long.class, desc = "")
	@out(name = "MSISDN", type = String.class, desc = "")
	@out(name = "IMSI", type = String.class, desc = "")
	@out(name = "SIM", type = String.class, desc = "")
	@out(name = "FIRST_NAME", type = String.class, desc = "")
	@out(name = "LAST_NAME", type = String.class, desc = "")
	@out(name = "SUBSCRIBER_TYPE", type = String.class, desc = "")
	@out(name = "VIP_STATUS", type = String.class, desc = "")
	public static void fnPop_SUBSCRIBER(String input) throws Exception {
		String sql = "SELECT SUBSCRIBER_ID, MSISDN, IMSI, SIM, FIRST_NAME, LAST_NAME, SUBSCRIBER_TYPE, VIP_STATUS FROM CRM_DB.SUBSCRIBER";
		db("CRM_DB").fetch(sql).each(row->{
			yield(row.cells());
		});
	}


	@type(RootFunction)
	@out(name = "CUSTOMER_ID", type = Long.class, desc = "")
	@out(name = "ACTIVITY_ID", type = Long.class, desc = "")
	@out(name = "ACTIVITY_DATE", type = String.class, desc = "")
	@out(name = "ACTIVITY_NOTE", type = String.class, desc = "")
	public static void fnPop_NEW_ACTIVITY(String input1, String input2) throws Exception {
		//-----------------------------------------------------//
		// Example of a Root function which has an additional  //
		// business logic prior to target table population    //
		//-----------------------------------------------------//
		
		// Example of using a Global in SQL statement. The syntax is '@<global name>@'. For Integer value, the use of apostrophe is optional
		String sql = "SELECT * From ACTIVITY WHERE CUSTOMER_ID = ? AND ACTIVITY_ID = ? AND NEW_NOTE_IND = @NEW_IND@";
		
		// Example of using a Fabric command in Root function            
		// Set environment according the source environment Global variable
		// In 6.1 this option doesn't work in the Data viewer but only in Fabric server//
		//if(SOURCE_ENV_NAME != null && !SOURCE_ENV_NAME.isEmpty()){
		//	fabric().execute("set environment='" + SOURCE_ENV_NAME + "'");
		//}
		
		// Fetch the data  
		ludb().fetch(sql, input1, input2).each(row->{
				yield(row.cells());
		});
	}





	
	
	
	
}
