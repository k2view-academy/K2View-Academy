
/////////////////////////////////////////////////////////////////////////
// Project Web Services
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.k2_ws.Demo;

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

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.common.SharedGlobals.*;
import com.k2view.fabric.api.endpoint.Endpoint.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends WebServiceUserCode {


	@webService(path = "test/getCustomerInfo", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Object wsCustomerInfo(String ID) throws Exception {
		// wsCustomerInfo v1 - data retrieved from CUSTOMER table
		
		String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME, LAST_NAME FROM CUSTOMER";
		
		Db.Rows rows = ludb("Customer", ID).fetch(sql);
		
		reportUserMessage("WS executed Succesfully for Customer ID :" + ID);
		
		
		
		return rows;
	}


	@out(name = "RES", type = Object.class, desc = "")
	public static Object wsCustomee_1st2CharsCheck(String ID) throws Exception {
		//String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME, LAST_NAME FROM CUSTOMER";
		String sql = "SELECT CUSTOMER_ID, SSN,FIRST_NAME, substr(FIRST_NAME,1,2)as First_Name_Cut, LAST_NAME FROM CUSTOMER";
		Db.Rows rows = ludb("Customer", ID).fetch(sql);
		reportUserMessage("WS executed Succesfully");
		// Tali- 10-May-20- fix - Fabric 6.1
		WebServiceUserCode.response().setHeader("WS-Info","Executed by K2view");
		//addCustomResponseHeader("WS-Info","Executed by K2view on date "+k2_currentDate());
		
		return rows;
		
		
		//For the selec tive select statment
		//Select CUSTOMER.CUSTOMER_ID,
		//  CUSTOMER.SSN,
		//  CUSTOMER.FIRST_NAME,
		//  CUSTOMER.LAST_NAME
		//From CUSTOMER
		//Where SubStr(CUSTOMER.FIRST_NAME, 1, 2) = 'Le'
	}


	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Object Costomer_ino2701(String ID) throws Exception {
		String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME, LAST_NAME FROM CUSTOMER";
		Db.Rows rows = ludb("Customer", ID).fetch(sql);
		return rows;
	}

	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Db.Rows wsGetCaseList(String customerId, String caseStatus) throws Exception {
		Db.Rows rows=null; 
		
		if(caseStatus== null || caseStatus.toLowerCase().equals("all")) {
			String sql = "select c.case_id, c.case_date, c.case_type, c.status, n.note_text, n.note_date " +
					" from cases c, case_note n where c.case_id = n.case_id " +
					" order by case_date desc";
		
			// Send the LU type and the instanceID as parameters to ludb() method. This method runs get entity command based on the input parameters and then runs the required SQL statement on the instance
			rows = ludb("Customer", customerId).fetch(sql);
		}
		else {
			String status = "Open";
		
			if(caseStatus.toLowerCase().equals("c"))
			{
				status = "Closed";
			}
			// Add a binding parameter to the SQL query using ? sign
			String sql = "select c.case_id, c.case_date, c.case_type, c.status, n.note_text, n.note_date " +
					" from cases c, case_note n where c.case_id = n.case_id and c.status = ? " +
					" order by case_date desc";
			// Send the state as a parameter for the sql statement
			rows = ludb("Customer", customerId).fetch(sql, status);
		}
		return rows;
		
		
		
	}


	@webService(path = "test/getCustomerInfo", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "2", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Object wsCustomerInfo2(String ID) throws Exception {
		// wsCustomerInfo v1 - data retrieved from CUSTOMER table
		
		//String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME, LAST_NAME FROM CUSTOMER";
		
		// wsCustomerInfo v2- data retrieved from CUSTOMER,CONTRACT and SUBSCRIBER tables
		
		String sql = "select cust.CUSTOMER_ID,cust.SSN,cust.FIRST_NAME||' '||cust.LAST_NAME CUSTOMER_NAME, cont.CONTRACT_ID,cont.CONTRACT_DESCRIPTION,sub.SUBSCRIBER_ID,sub.MSISDN,sub.IMSI,sub.SIM,sub.SUBSCRIBER_TYPE " +
				"from CUSTOMER cust, CONTRACT cont, SUBSCRIBER sub where cont.CONTRACT_ID=sub.SUBSCRIBER_ID";
		
		Db.Rows rows = ludb("Customer", ID).fetch(sql);
		
		reportUserMessage("WS executed Succesfully for Customer ID :" + ID);
		
		
		
		return rows;
	}



	
	

	
}
