/////////////////////////////////////////////////////////////////////////
// LU Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.ORDERS.Enrichment;

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
import com.k2view.cdbms.usercode.lu.ORDERS.*;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.lu.ORDERS.Globals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends UserCode {


	public static void fnEnrichContract() throws Exception {
		
		String sql = "UPDATE CONTRACT " +  
		" SET NO_OF_OPEN_ORDERS = (select count(*) no_of_open_orders from ORDERS " +  
		" where ORDERS.contract_id= CONTRACT.contract_id " + 
		" and ORDERS.order_status != 'Closed' ) ";
		
		ludb().execute(sql);
		
		// Note- if this update was made by a population- then the following logic would have been implemented-
		// Get the number of orders per each contract id and run an update on each contract instead of one update 
		
		/*
		// Get the number of open orders for each contract ID
			
			String contractId = input;
			// Get the total number of orders from Orders LU table by contractId and status. Use binding parameters.
			// Note: it is recommended to create index on Orders LU table for the combination of CONTRACT_ID and ORDER_STATUS fields to
			// make the performance of the query more efficient
		
			reportUserMessage("Get number of open orders for contract id: " + contractId);
			log.info("Get number of open orders for contract id: " + contractId);
		
			String sql = "SELECT COUNT(*) AS TOT_NUM_OF_ORDERS FROM ORDERS WHERE CONTRACT_ID = ? and ORDER_STATUS != ?";
			Db.Rows rows2 = ludb().fetch(sql, contractId, "Closed");
			Integer noOfOrders = Integer.parseInt(rows.firstValue().toString());
		
			reportUserMessage("Number of open orders for contract ID: " + contractId + " is: " + noOfOrders);
		    log.info("Number of open orders for contract ID: " + contractId + " is: " + noOfOrders);
		    reportUserMessage("Update table");
			// Update CONTRACT LU table for each contract. The update is done by the PK of the table
			String sql2= "UPDATE CONTRACT SET NO_OF_OPEN_ORDERS = ? WHERE CONTRACT_ID = ? ";
			ludb().execute(sql2, noOfOrders, contractId);
		
		}
		*/
	}


	public static void fnEnrichmentOrderParam() throws Exception {
		Map<String,Map<String,String>> data = getTranslationsData("trnOrdersParams");
		StringBuilder stringInsertFabricLuParam = new StringBuilder().append("insert or replace into orders_params (IID, ");
		String prefix = "";
		StringBuilder sqlInsertBind = new StringBuilder().append(" values ( ? ,");
		String IID = ludb().fetch("SELECT IID('ORDERS')").firstValue().toString();
		
		reportUserMessage("fnEnrichmentOrderParam- number of parameters in the trnOrdersParams: " + data.size());
		
		// Check if we have elements in the Translation object
		if(data.size() > 0){
		
		   //Parameters that will be used for Update. The size of this array needs to be the number of parameters + 1 for the IID field
		   Object[] params = new Object[data.size()+ 1];
		
		   // Populate the IID in the first position on params array
			params[0] = IID;
		
		   //Counter to insert the parameters in the correct position. Start the counter by 1, since the first position (0) is populated by the IID
		    int i=1;
		
		   //Getting the values from the LU
		   prefix = "";
		
		   for(String index: data.keySet()) {
		
			  //String which contains the values returned by the query
			  StringBuilder values = new StringBuilder();
		
			  Map<String, String> valMap = data.get(index);
			  String luParamColName = valMap.get("PARAM_NAME");
		
			  // Get the SQL query for each parameter
			  String sql = valMap.get("SQL");
		
			  //Check if SQL query contains distinct and add it if not
			  if (!sql.contains("distinct")) {
				  sql = sql.replace("select", "select distinct");
			  }
		
			  stringInsertFabricLuParam.append(prefix + luParamColName);
			  values.append("{"); 
		
			  //Run the SQL statement on the LU
			  Db.Rows rows = ludb().fetch(sql);
			  	
			  for (Db.Row row : rows) {
				  if (row.cell(0) != null)
					  values.append("\"" + row.cell(0) + "\",");
			  }
		
			  //Check if the last element is a comma and remove it
			  if (values.lastIndexOf(",") == values.length() - 1) {
				  values.deleteCharAt(values.lastIndexOf(","));
			  }
			  values.append("}");
			  //If no values, set NULL
			  if (values.toString().equals("{}")) {
				  params[i] = null;
			  } else {
				  params[i] = values.toString();
			  }
		
			  sqlInsertBind.append(prefix + " ? ");
			  i++;
		
			  prefix = ",";
		  } // close the loop of the translation
		
		 stringInsertFabricLuParam.append(") ");
		 sqlInsertBind.append(") ");
		
		//reportUserMessage("stringInsertFabricLuParam: " + stringInsertFabricLuParam.toString() + ",sqlInsertBind: " + sqlInsertBind.toString());	
		 // Insert a new record to ORDERS_PARAM
		
		 String insertSQL = stringInsertFabricLuParam.append(sqlInsertBind).toString();
			
		 reportUserMessage("Insert SQL: " + insertSQL + ", parameters:  " + Arrays.toString(params));
		
		  ludb().execute(insertSQL, params);
		} // end if the translation is populated
		else {//no parameters defined - inserting only entity_id and source_environment values
			ludb().execute("insert or replace into ORDERS_PARAMS (IID) values (?)", IID);
		}
	}



	
	
	
	
}
