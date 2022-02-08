# K2View Customized Rules

## 4.2	Java Coding

**1. Resource must be closed**


**2. Catch block should not be Empty!**  
   -*Checks if catch block was defined to be empty, which is not recommended as it will make it very hard to locate failures in code.*

   -*Caught exception should be handled or printed out.*


**3. Use binding to get instance**  
   -*This rule detects an execution of get command without binding*

	use: 		db("Fabric").execute("get LUTYPE.?",  IID );
	instead of: 	db("Fabric").execute("get LUTYPE.IID", null );



**4. Fetch single value/row using firstValue()/firstRow() functions**  
   -*This rule detects a use of multi rows function instead of using the above mentioned functions for single value\row*


**5. Use Prepare and send the values as parameters to the SQL**  

	use:		db(InterfaceName).fetch(select * from table where id =?,ID);
	instead of:	db(InterfaceName).fetch(select * from table where id = "+ ID +",null);

**6. ReportUserMessage/log.info should only be used for debugging**  
   -*This rule detects usage of log.info/ReportUserMessage*

		log.info("message");
		ReportUserMessage("message");

  -*Reduce the usage of the log.info/ReportUserMessage and use only if mandatoy or for debugging purpose.*

**7. Deprecated functions should not be used**  
   -*This rule detects usage of deprecated functions*
  	
   *Please check the [User Guide](https://docs.sonarqube.org/latest/instance-administration/quality-profiles/) for more details on deprecated functions.*

**8. Use fabric() instead of db(<fabricInterface>) in the WS**  
   -*db() function should be used to extract data from fabric via the WS*
	
**9. Combine two lists**  
   -*In order to combine two lists use Java function: ‘myListObject.addAll’. Do not add one by one by using loops.*
	

[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_K2View_Customized_Rules/01_General_Rules.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/04_K2View_Customized_Rules/03_Cassandra.md)

