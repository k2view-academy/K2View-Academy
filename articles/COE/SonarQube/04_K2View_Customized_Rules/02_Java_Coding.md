# K2View Customized Rules

## 4.2	Java Coding

-	resource must be closed

-	catch block should not be Empty!

Checks if catch block was defined to be empty, which is not recommended as it will make it very hard to locate failures in code.

Caught exception should be handled or printed out.

-	use binding to get instance

This rule detects an execution of get command without binding

use: 		db("Fabric").execute("get LUTYPE.?",  IID );
instead of: 	db("Fabric").execute("get LUTYPE.IID", null );



-	fetch single value/row using firstValue()/firstRow() functions

This rule detects a use of multi rows function instead of using the above mentioned functions for single value\row



-	use Prepare and send the values as parameters to the SQL

use Prepare and send the values as parameters to the SQL

use:		db(InterfaceName).fetch(select * from table where id =?,ID);
instead of:	db(InterfaceName).fetch(select * from table where id = "+ ID +",null);

-	ReportUserMessage/log.info should only be used for debugging

This rule detects usage of log.info/ReportUserMessage

log.info("message");
ReportUserMessage("message");
	
reduce the usage of the log.info/ReportUserMessage and use only if mandatoy or for debugging purpose.

- deprecated functions should not be used
  This rule detects usage of deprecated functions
  Please check User Guide for more details on deprecated functions.

  

[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_Customized_Rules/01_Customized_Rules.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/04_Customerized_Rules/03_Cassandra.md)

