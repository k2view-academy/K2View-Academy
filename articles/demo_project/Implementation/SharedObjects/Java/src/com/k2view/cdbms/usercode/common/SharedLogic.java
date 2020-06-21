/////////////////////////////////////////////////////////////////////////
// Project Shared Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.common;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;

import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.shared.user.UserCode;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;
import com.k2view.cdbms.func.oracle.OracleToDate;
import com.k2view.cdbms.func.oracle.OracleRownum;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;

import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.shared.user.UserCode.*;
import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.usercode.common.SharedGlobals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class SharedLogic {


	@category("Masking")
	public static void fnMasking() throws Exception {
			//Get the translation data
			Map<String,Map<String,String>> data = getTranslationsData(TRN_MASKING);
			Map<String, String> tableIndexMap = new HashMap<String, String>();
			Map<Integer, String> indexTableMap = new TreeMap<Integer, String>();
			String tableName = null;
			// Read the encryption information for iTableName from the trnMasking translation.
			for(String index: data.keySet()){
				Map<String, String> valMap = data.get(index);
				tableName = valMap.get(TRN_TABLE_NAME_COLUMN_HEADER);
				if(tableIndexMap.get(tableName) == null){
					tableIndexMap.put(tableName, index);
					indexTableMap.put(new Integer(index), tableName);
				}
			}
	}


	@category("Enrichment")
	@out(name = "result", type = Map.class, desc = "")
	public static Map<String,Object> fnErrorCheck(String fabricErrorID) throws Exception {
		//--------------------------------------------------------------------------------------
		//Example of how to use getTranslationValues in the code
		//The function retrieves ErrorID & ErrorMsg using the input param fabricErrorID
		//--------------------------------------------------------------------------------------
		
		Map<String,Object> result = new HashMap<>();
		String errID; 
		
		if (fabricErrorID != null) {
			Map <String,String> rs = getTranslationValues("trnApplicationErrorList",new Object[]{fabricErrorID});
			if (rs != null) {
				result.put("p_errno", rs.get("ErrorID"));
				result.put("p_message", rs.get("ErrorMsg"));
				//-----------------------------------------------------------------//
				//example of how to override the global variable on a session level//
				//-----------------------------------------------------------------//
				fabric().execute("set RECEIVED_ERROR="+fabricErrorID);
				//-----------------------------------------------------------------//
			} else {
				result.put("p_errno", "999");
				result.put("p_message", "Fatal Error");	
			}
		}
		
		return result;
	}


	@category("DECISION")
	@type(DecisionFunction)
	@out(name = "decision", type = Boolean.class, desc = "")
	public static Boolean test() throws Exception {
		return true;
	}

	
	
	

}
