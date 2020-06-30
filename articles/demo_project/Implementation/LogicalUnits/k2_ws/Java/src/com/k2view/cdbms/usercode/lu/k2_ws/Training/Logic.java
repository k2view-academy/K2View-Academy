/////////////////////////////////////////////////////////////////////////
// Project Web Services
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.k2_ws.Training;

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
import com.k2view.fabric.api.endpoint.Endpoint.*;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.common.SharedGlobals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends WebServiceUserCode {


	@desc("Retrieve Customer info from CustomerLU")
	@webService(path = "test/getCustomerDetails", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Object wsGetCustomer(String i_id) throws Exception {
		String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME, LAST_NAME FROM CUSTOMER";
		Db.Rows rows = ludb("CustomerLU", i_id).fetch(sql);
		return rows;
	}


	@desc("Retrieve Customer info from CustomerLU")
	@webService(path = "test/getCustomerDetails", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "2", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Object wsGetCustomer2(String i_id, String i_vipStatus) throws Exception {
		String sql = "select cust.FIRST_NAME||' '||cust.LAST_NAME CUSTOMER_NAME, cont.CONTRACT_ID,cont.CONTRACT_DESCRIPTION," +
		        "sub.SUBSCRIBER_ID,sub.MSISDN,sub.IMSI,sub.SIM,sub.SUBSCRIBER_TYPE,sub.VIP_STATUS " +
				"from CUSTOMER cust, CONTRACT cont, SUBSCRIBER sub where cont.CONTRACT_ID=sub.SUBSCRIBER_ID and sub.VIP_STATUS=?";
		
		Db.Rows rows = ludb("CustomerLU", i_id).fetch(sql, i_vipStatus);
		
		return rows;
	}


	@desc("Delete from Subscriber table base on Subscriber ID and MSISDN")
	@webService(path = "", verb = {MethodType.POST, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	@resultMetaData(mediaType = Produce.JSON, example = "\"info\":[{\"subscriber_id\":\"\", \"msisdn\":\"\"},{\"subscriber_id2\":\"\",\"msisdn2\":\"\"}]")
	public static Object wsDeleteSub(String i_id, List<Map<String,String>> i_info) throws Exception {
		Map<String,String> result = new HashMap<>();
		String name ="";
		String message="";
		
		// Parse the input map to extract the values for the delete condition 
		
		//log.info(i_info.toString());
		if ( i_info != null && i_info.size() >0 ){
			
			for (int i = 0; i < i_info.size(); i++) {
				
				//Map<String,String> m = i_info.get.(i);
				 if (i_info.get(i) != null){
					 
					String subId =i_info.get(i).get("subscriber_id");
					String msisdn =i_info.get(i).get("msisdn");
					
				// Validate that input is not empty or wasn't exceed to number of object array
					
					    if (msisdn!=null && !msisdn.isEmpty()){
								
				// Delete from SUBSCRIBER table based on SUBSCRIBER_ID and MSISDN	
							fabric().execute("Begin");
							String sql = "DELETE FROM SUBSCRIBER WHERE SUBSCRIBER_ID=? AND MSISDN=? ";
							ludb("CustomerLU", i_id).execute(sql,subId,msisdn);
							fabric().execute("Commit");
						}
					else{
		//Missing values to process 
							i=i_info.size()+1;
						    name="Error";
						    message="MISSING VALUES"; 	
						    //result.put(name, message);
						   //return result;
						}
					
					}
			}
			name="Complete";
			message="DELETED ALL";
		} else {
		
		// Input can not be processed, not data to parse
			
		 name="Not Started";
		 message="No input received";
		//message=Integer.toString(i_info.size());
		}
		
		result.put(name, message);
		return result;
	}


	@desc("Insert inot CASES tables")
	@webService(path = "", verb = {MethodType.POST, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	@resultMetaData(mediaType = Produce.JSON, example = "\"info\":[{\"activity_id\":\"\", \"case_id\":\"\",\"case_date\":\"\",case_type\":\"\",\"status\":\"\"}")
	public static Object wsInsertCases(String i_id, List<Map<String,String>> i_info) throws Exception {
		Map<String,String> result = new HashMap<>();
		String name ="";
		String message="";
		
		// Parse the input map to extract the values for the delete condition 
		
		//log.info(i_info.toString());
		if ( i_info != null && i_info.size() >0 ){
			
			for (int i = 0; i < i_info.size(); i++) {
				
				//Map<String,String> m = i_info.get.(i);
				 if (i_info.get(i) != null){
					 
					String activity_id =i_info.get(i).get("activity_id");
					String case_id =i_info.get(i).get("case_id");
					String case_date =i_info.get(i).get("case_date");
					String case_type =i_info.get(i).get("case_type");
					String status =i_info.get(i).get("status");
					
				// Validate that input is not empty or wasn't exceed to number of object array
					
					    if (activity_id!=null && !activity_id.isEmpty()){
								
				// Insert into CASES table 	
							fabric().execute("Begin");
							String sql = "insert into cases (activity_id,case_id,case_date,case_type,status) values (?,?,?,?,?)";
							ludb("CustomerLU", i_id).execute(sql,activity_id,case_id,case_date,case_type,status);
							fabric().execute("Commit");
						}
					else{
		//Missing values to process 
							i=i_info.size()+1;
						    name="Error";
						    message="MISSING VALUES"; 	
						    //result.put(name, message);
						   //return result;
						}
					
					}
			}
			name="Complete";
			message="INSERTED ALL";
		} else {
		
		// Input can not be processed, not data to parse
			
		 name="Not Started";
		 message="NO INPUT RECIEVED";
		//message=Integer.toString(i_info.size());
		}
		
		result.put(name, message);
		return result;
	}



	
	

	
}
