/////////////////////////////////////////////////////////////////////////
// Project Web Services
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.k2_ws.Translation;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;

import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.shared.user.WebServiceUserCode;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;
import com.k2view.cdbms.func.oracle.OracleToDate;
import com.k2view.cdbms.func.oracle.OracleRownum;
import com.k2view.cdbms.usercode.lu.k2_ws.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.common.SharedGlobals.*;
import com.k2view.fabric.api.endpoint.Endpoint.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends WebServiceUserCode {


	@out(name = "res", type = Object.class, desc = "")
	public static Object wsTRN(String In1, String In2) throws Exception {
		String[] trnValues = {In1, In2};
		
		Map trn = getTranslationValues("MyTrans", trnValues);
		
		Object  Output = trn.get("Out1");
		
		return Output;
	}


	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Object wsGetRefTablesListByLu(String luArray) throws Exception {
		//Map <String, String> refTablesList = new LinkedHashMap<String, String>();
		List<Object> refTablesList = new ArrayList<Object>();
				
		Map<String,Map<String, String>> trnRefListValues = getTranslationsData("trnRefList");
		
		List<String> luNamesList = new ArrayList<String>();
				
		// Parse the input array of LUs
		
		try {
		
			JSONArray jArray = new JSONArray(luArray);
			 for (int i=0;i< jArray.length(); i++){
					//DEBUG
				    reportUserMessage("Input LU Name: " + jArray.get(i).toString());
		            luNamesList.add(jArray.get(i).toString());
		        }
			 
		} catch (JSONException e) {
		reportUserMessage(e.getMessage());
		}
		
		// Scan the translation  by its keys. The key contains the LU name + ID
		Set<String> keys = trnRefListValues.keySet();
		String luName;
		String prevLuName="";
		for(String trnLuKey:keys){
			// Get the LU name from the key. Key example- PATIENT_LU@:@1
			luName = trnLuKey.substring(0, trnLuKey.indexOf("@") );
		    
			// DEBUG
			//reportUserMessage("Key: "+ trnLuKey + " lu name of the translation key: " + luName);
		
			if(luNamesList.contains(luName)) {
				//DEBUG 
		  	 // reportUserMessage("luNamesList contains " + luName );
		
				//StringBuffer refInfoStr = new StringBuffer();
				JSONObject refInfo=null;
				//refInfoStr.put("logical_unit_name: " + luName + "," );
				refInfo = new JSONObject();
				refInfo.put("logical_unit_name", luName);
		
				Set<String> internalKeys = trnRefListValues.get(trnLuKey).keySet();
				String paramValue;
		
				for(String paramKey:internalKeys) {
					paramValue = trnRefListValues.get(trnLuKey).get(paramKey);
					//reportUserMessage(paramKey + ": " + paramValue);
					refInfo.put(paramKey, paramValue);
					//refInfoStr.append(paramKey + ","  + paramValue);
		
				}
		
				//reportUserMessage( "refInfo JSON: " + refInfo.toString());
				//refTablesList.add(refInfoStr);
		        //refTablesList.add("logical_unit_name: " + luName);
		        //refTablesList.add(trnRefListValues.get(trnLuKey));
				 refTablesList.add(refInfo.toString());
		
			  }
			// DEBUG
			// reportUserMessage(trnLuKey+" -- "+trnRefListValues.get(trnLuKey));
					
		}
		
		return refTablesList;
	}


	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Object wsGetCustomerDetails(String CONTR_ID, String ADDRESS_ID) throws Exception {
		Map<String,Object> result = new HashMap<>();
		String contrID = k2_ifNull(CONTR_ID,"");
		String adrID = k2_ifNull(ADDRESS_ID,"");
		Db.Row row;
		
		String receivedErr = ""; 
		Object val;
		
		//--------------------------------------------------------------------------------------------------------------
		//Example how to perform validation and return the results based on Translation definition
		//Invoke the function fnErrorCheck that retrieves ErrorID & ErrorMsg using the input param fabricErrorID
		//--------------------------------------------------------------------------------------------------------------
		
		if (contrID == "" && adrID == "") {
			//-----------------------------------------------------------------//
			//example of how to override the global variable on a session level//
			//1.invoke fnErrorCheck() which sets the value of RECEIVED_ERROR   //
			//2. get the value of RECEIVED_ERROR as set by fnErrorCheck()      //
			//-----------------------------------------------------------------//
			result = fnErrorCheck(MISSING_INPUT); //Missing input
			val = db("fabric").fetch("set RECEIVED_ERROR").firstValue();
			receivedErr = k2_ifNull(val.toString(),"No error");
			result.put("p_error", receivedErr);	
			//-----------------------------------------------------------------//	
			return result;
			
		} else if (contrID != "" && adrID != "") {
			result = fnErrorCheck(TOO_MANY_INPUTS); //Too many inputs
			val = db("fabric").fetch("set RECEIVED_ERROR").firstValue();
			receivedErr = k2_ifNull(val.toString(),"No error");
			result.put("p_error", receivedErr);	
			return result;
			
		} else {
			if (contrID != "") {
			  String sql = "SELECT DISTINCT(CUST.CUSTOMER_ID) " +
			          "FROM CUSTOMER CUST, CONTRACT CONTR " +
			          "WHERE CUST.CUSTOMER_ID = CONTR.CUSTOMER_ID " +
			          "AND CONTR.CONTRACT_ID = ?";
			  row = db("CRM_DB").fetch(sql, contrID).firstRow();
		
		
			} else {
			  String sql = "SELECT DISTINCT(CUST.CUSTOMER_ID) " +
			          "FROM CUSTOMER CUST, ADDRESS ADR " +
			          "WHERE CUST.CUSTOMER_ID = ADR.CUSTOMER_ID " +
			          "AND ADR.ADDRESS_ID = ?";
			  row = db("CRM_DB").fetch(sql, adrID).firstRow();
		
			}
		
			if ( row.isEmpty() ) {
				result = fnErrorCheck(MISSING_OUTPUT); //Missing output
				val = db("fabric").fetch("set RECEIVED_ERROR").firstValue();
				receivedErr = k2_ifNull(val.toString(),"No error");
				result.put("p_error", receivedErr);	
				//-----------------------------------------------------------------//
				//example of how to override the global variable on a cluster level//
				//-----------------------------------------------------------------//
			    //fabric().execute("set_global global '*.RECEIVED_ERROR="+MISSING_OUTPUT+"'");
				//-----------------------------------------------------------------//	
				return result;
			}
		    reportUserMessage("WS wsGetCustomerDetails executed Succesfully");
			// Tali- 10-May-20- fix - Fabric 6.1
			WebServiceUserCode.response().setHeader("WS-Info","Executed by K2view");
			//addCustomResponseHeader("WS-Info","Executed by K2view");
			
			return row.cell(0);
		
		}
	}

	
	

	
}
