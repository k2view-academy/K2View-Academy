/////////////////////////////////////////////////////////////////////////
// Shared Globals
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.common;

import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;

public class SharedGlobals {

	@desc("The translation data masking")
	@category("Masking")
	public static final String TRN_MASKING = "trnMasking";
	@desc("Translation data TABLE_NAME header")
	@category("Masking")
	public static String TRN_TABLE_NAME_COLUMN_HEADER = "TABLE_NAME";

	@category("ERROR_TYPE")
	public static String MISSING_INPUT = "20";
	@category("ERROR_TYPE")
	public static String TOO_MANY_INPUTS = "10";
	@category("ERROR_TYPE")
	public static String MISSING_OUTPUT = "30";

	@category("ERROR_TYPE")
	public static String RECEIVED_ERROR = "0";

	


}
