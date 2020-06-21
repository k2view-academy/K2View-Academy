/////////////////////////////////////////////////////////////////////////
// LU Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.TEST_CUST.Root;

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
import com.k2view.cdbms.usercode.lu.TEST_CUST.*;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.lu.TEST_CUST.Globals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends UserCode {


	@type(RootFunction)
	@out(name = "SUBSCRIBER_ID", type = Long.class, desc = "")
	@out(name = "MSISDN", type = String.class, desc = "")
	@out(name = "IMSI", type = String.class, desc = "")
	@out(name = "SIM", type = String.class, desc = "")
	@out(name = "FIRST_NAME", type = String.class, desc = "")
	@out(name = "LAST_NAME", type = String.class, desc = "")
	@out(name = "SUBSCRIBER_TYPE", type = String.class, desc = "")
	@out(name = "VIP_STATUS", type = String.class, desc = "")
	public static void fnPop_SUBSCRIBER(String input, String input2) throws Exception {
		String sql = "SELECT SUBSCRIBER_ID, MSISDN, IMSI, SIM, FIRST_NAME, LAST_NAME, SUBSCRIBER_TYPE, VIP_STATUS FROM BILLING_DB.SUBSCRIBER";
		Db.Rows rows = db("BILLING_DB").fetch(sql);
		for (Db.Row row:rows){
			yield(row.cells());
		}
	}


	@type(RootFunction)
	@out(name = "CUSTOMER_ID", type = Double.class, desc = "")
	@out(name = "SSN", type = String.class, desc = "")
	@out(name = "FIRST_NAME", type = String.class, desc = "")
	@out(name = "LAST_NAME", type = String.class, desc = "")
	public static void fnPop_CUSTOMER(String input, String input2) throws Exception {
		String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME, LAST_NAME FROM CRM_DB.CUSTOMER";
		Db.Rows rows = db("CRM_DB").fetch(sql);
		for (Db.Row row:rows){
			yield(row.cells());
		}
	}

	
	
	
	
}
