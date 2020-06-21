/////////////////////////////////////////////////////////////////////////
// LU Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.CRM.Enrichment;

import java.util.concurrent.atomic.AtomicReference;

import com.k2view.cdbms.shared.user.UserCode;
import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;
import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.shared.Globals;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;
import com.k2view.cdbms.func.oracle.OracleToDate;
import com.k2view.cdbms.func.oracle.OracleRownum;
import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends UserCode {








	public static void fnMonthsOpenCase() throws Exception {
		//-----------------------------------------------------//
		// Example of an Enrichment  function on table CASES   //
		// Checks the number of months the case is not Closed  //
		// And updates it in the table CASES for each case     //
		// For all Closed cases - set the number of month to 0 //
		//-----------------------------------------------------//
		
		String caseStatus = "Closed";
		
		String sqlNotClosed = "UPDATE CASES SET CASE_OPEN_MONTHS = "+
						"(SELECT round((julianday('now') - julianday(C2.CASE_DATE))/365*12) from CASES C2 "+
						" WHERE C2.CASE_ID = CASES.CASE_ID AND C2.STATUS != ?)";
		ludb().execute(sqlNotClosed,caseStatus);
		
		String sqlClosed = "UPDATE CASES SET CASE_OPEN_MONTHS = 0 WHERE STATUS = ?";
		ludb().execute(sqlClosed,caseStatus);
		
	}


	public static void fnCheckValidationsResults() throws Exception {
		//---------------------------------------------------------------//
		// Example of an Enrichment function on table EXEC_VALIDATIONS   //
		// Checks how many validation checks didn't pass                 //
		// And updates a ComputedField in the table CUSTOMER             //
		//---------------------------------------------------------------//
		
		String sqlSelect = "SELECT count(*) FROM EXEC_VALIDATIONS WHERE RESULT !='OK'";
		
		Db.Rows rows = ludb().fetch(sqlSelect);
		Integer validationsNotOK = Integer.parseInt(rows.firstValue().toString());
		
		if (validationsNotOK > 0){
			String sqlUpdate = "UPDATE CUSTOMER SET VALIDATIONS_NOT_PASSED = ?";
			ludb().execute(sqlUpdate, validationsNotOK);
		}
		
	}







	
	
	
	
}
