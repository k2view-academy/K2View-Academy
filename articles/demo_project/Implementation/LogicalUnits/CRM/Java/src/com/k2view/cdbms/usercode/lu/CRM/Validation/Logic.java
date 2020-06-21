/////////////////////////////////////////////////////////////////////////
// LU Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.CRM.Validation;

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
import com.k2view.cdbms.usercode.lu.CRM.*;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.lu.CRM.Globals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends UserCode {


	@FunctionalInterface
                 interface CustomFunction {
                      String f() throws Exception;
                 }
	
enum Factory {
                fnHasRoaming(Logic::fnHasRoaming),
				fnHasOpenCases(Logic::fnHasOpenCases)
                ;

                private CustomFunction logic;

                Factory(CustomFunction logic) {
                                this.logic = logic;
                }

                  String invoke() throws Exception{
                                return  logic.f();
                }
}

	@type(RootFunction)
	@out(name = "o_CheckDesc", type = String.class, desc = "")
	@out(name = "o_Result", type = String.class, desc = "")
	public static void fnExecuteValidations(String i_customer_id) throws Exception {
				
		Map <String, Map <String,String>> ChecksTrn = getTranslationsData("trnValidationFuncList");
		
		String Func = null;
		
		for(java.util.Map.Entry<String, Map <String,String>> trnVals : ChecksTrn.entrySet()){
			
			Map <String,String> trnVal = (Map <String,String>) trnVals.getValue();
			// Checks that the record is active
			if(trnVal.get("ACTIVE") != null && trnVal.get("ACTIVE").equalsIgnoreCase("false"))continue;
			
			String CheckDesc = trnVal.get("CHECK_DESC");
			String FuncName = trnVal.get("FUNCTION_NAME");
			
			if(FuncName != null && !FuncName.isEmpty() ){
		
				String Result =  Factory.valueOf(FuncName).invoke();			
				yield(new Object[]{CheckDesc,Result});
		
			}
			
		}
	}


	@out(name = "result", type = String.class, desc = "")
	public static String fnHasOpenCases() throws Exception {
		//---------------------------------------------------------------------------------//
		// Example of a validation function that is includes in the VALIDATIONS list       //
		// The function is defined in the translation trnValidationFuncList and invoked    //
		// by the Root function fnExecuteValidations, the results are populated into       //
		// the table EXEC_VALIDATIONS, to be checked by the Enrichment function            //
		//---------------------------------------------------------------------------------//
		
		String caseStatus = "Open";
		
		String sql = "SELECT count(CASE_ID) FROM CASES WHERE STATUS=? ";
		Db.Rows rows = ludb().fetch(sql, caseStatus);
		Integer noOfOpenCases = Integer.parseInt(rows.firstValue().toString());
		
		sql = "UPDATE CUSTOMER SET HAS_OPEN_CASES = ?";
		ludb().execute(sql, noOfOpenCases);
		
		if (noOfOpenCases > 0)
			return "Customer has Open cases!";
		else
			return "OK";
	}


	@out(name = "result", type = String.class, desc = "")
	public static String fnHasRoaming() throws Exception {
		//---------------------------------------------------------------------------------//
		// Example of a validation function that is includes in the VALIDATIONS list       //
		// The function is defined in the translation trnValidationFuncList and invoked    //
		// by the Root function fnExecuteValidations, the results are populated into       //
		// the table EXEC_VALIDATIONS, to be checked by the Enrichment function            //
		//---------------------------------------------------------------------------------//
		
		String roamingService = "Roaming special";
		String sql = "SELECT count(*) FROM SUBSCRIBER WHERE CONTRACT_DESCRIPTION=? ";
		Db.Rows rows = ludb().fetch(sql, roamingService);
		Integer hasRoaming = Integer.parseInt(rows.firstValue().toString());
		if (hasRoaming > 0){
			return  "Customer has Roaming services!!!";
		}
		else
			return  "OK";
	}





	
	
	
	
}
