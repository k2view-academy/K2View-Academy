/////////////////////////////////////////////////////////////////////////
// LU Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.CRM.DECISION;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
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


	@type(DecisionFunction)
	@out(name = "decision", type = Boolean.class, desc = "")
	public static Boolean fnCheckSourceEnv() throws Exception {
		// Init the Boolean by true
		
		Boolean syncInd = true;
		
		// Check the source environment
		String env = getActiveEnvironmentName();
		
		// If the active environment is not production, this sync is not the first sync of the instance, and the schema is not changed => do not sync the instance
		if(!env.toLowerCase().equals("prod") && !isFirstSync() && !isStructureChanged())
			syncInd = false;
		
		// DEBUG- note- the output of getTableName() was added to the log, since when setting a decision function on the LU schema- it runs on each population of every LU table of the schema
		log.info("fnCheckSourceEnv- table name: " + getTableName() + ", active env: " + env.toLowerCase() + ", isFirstSync: " + isFirstSync() + ", isStructureChanged: " +  isStructureChanged() + ", Syc indicator: " + syncInd.toString());
		return syncInd;
	}

	@type(DecisionFunction)
	@out(name = "decision", type = Boolean.class, desc = "")
	public static Boolean fnCheckSourceVersion() throws Exception {

		// Init the Boolean by true

		Boolean syncInd = true;

		// Check the product version (application version) global. Populate ACTIVITY table only of the source version is 'dev'
		String prodVer = SOURCE_PRODUCT_VERSION;

		if(!prodVer.toLowerCase().equals("dev") )
			syncInd = false;

		// DEBUG
		log.info("fnCheckSourceVersion- product version: " + prodVer + ", isFirstSync: " + isFirstSync() + ", isStructureChanged: " +  isStructureChanged() + ", Syc indicator: " + syncInd.toString());
		return syncInd;
	}


	@type(DecisionFunction)
	@out(name = "decision", type = Boolean.class, desc = "")
	public static Boolean fnCheckSkipSync() throws Exception {
		// Init the Boolean by true
		
		Boolean syncInd = true;
		
		// Check the source environment
		String env = getActiveEnvironmentName();
		
		// DEBUG- note- the output of getTableName() was added to the log, since when setting a decision function on the LU schema- it runs on each population of every LU table of the schema
		log.info("fnCheckSkipSync- table name: " + getTableName() + ", active env: " + env.toLowerCase() + ", isFirstSync: " + isFirstSync() + ", isStructureChanged: " +  isStructureChanged() + ", Syc indicator: " + syncInd.toString());
		
		
		if(!env.toLowerCase().equals("prod") && !isFirstSync())
		{
			syncInd = false;
			skipSync();
		}
		
		return syncInd;
	}

	@type(DecisionFunction)
	@out(name = "decision", type = Boolean.class, desc = "")
	public static Boolean fnCheckOffPeak() throws Exception {
		// Init the Boolean by false
		
		Boolean syncInd = false;
		
		
		LocalTime start = LocalTime.parse( "09:00:00" );
		LocalTime end = LocalTime.parse( "17:00:00" );
		LocalTime now = LocalTime.now();
		
		Boolean offPeak=true;
		
		if(now.isAfter(start) && now.isBefore(end))
			offPeak=false;
		
		if(isFirstSync() || offPeak)
			syncInd = true;
		
		// DEBUG
		reportUserMessage("now: " + now.toString()+ ", is offPeak: " + offPeak + ", now: " + now.toString() + " , isFirstSync(): " + isFirstSync() + " sync ind: " + syncInd);
		log.info("now: " + now.toString()+ ", is offPeak: " + offPeak + ", now: " + now.toString() + " , isFirstSync(): " + isFirstSync() + " sync ind: " + syncInd);
		
		if(!syncInd)
			skipSync();
		
		return syncInd;
	}
	
	
	
}
