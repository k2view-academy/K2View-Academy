/////////////////////////////////////////////////////////////////////////
// LU Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.ORDERS.Root;

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


	@type(RootFunction)
	@out(name = "IID", type = String.class, desc = "")
	@out(name = "orderType", type = String.class, desc = "")
	public static void fnRootOrderId(String uid) throws Exception {
		// Parse the input IID to get the order type
		String orderType = uid.substring(uid.indexOf("_") + 1);
		
		yield(new Object[] {uid, orderType});
		
					
	}


	@type(RootFunction)
	@out(name = "IID", type = String.class, desc = "")
	public static void fnRootOrdersParams(String IID) throws Exception {
		// Return null. The actual population of the table will be done by the enrichment function
		if (1 == 2) yield(new Object[]{null});
	}



	
	
	
	
}
