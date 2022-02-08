# K2View Customized Rules

## 4.4	IIDFinder

**1. Check Root table configuration**  
   -*Below are the correct settings:*
	
		• Truncate = false  
		• Unique index on the IID  
		• population mode = ‘upsert’  

  -*If the truncate mode is set to “true”, the deleteOrphans functionality for LPK scenario on a table that is connected to the root might trigger the deletion of records which should not be deleted*
	  

**2. Check stored attribute in staging xml**  
	-*This rule checks the "stored" attribute defined staging xml per table:*

		• if table is not a leaf stored should be true
		• if the table is leaf and parent key field is part of the PK then stored should be false
		• if the table is leaf and parent key is not part of the PK then headsup rule as below:
			 "validate if we get all columns in the message then store=false "

**3. Check IIDF Kafka Details**  
   -*This rule checks below recommendations:* 

		• Do not store the IIDF Kafka details in translation table.
		• Use IifProperties.getInstance() to get the IIDFinder configuration setting of the cluster.

**4. Check Source Available attribute defined in staging xml file**  
 -*This rule checks the "sourceAvailable" attribute defined staging xml per lu:*  

 -*Issues are created depending on the return value of the XPath expression. If the XPath expression returns:*  
 				
		•The latest iidFinder implementation is tested with sourceAvailable set to "false",
		therefor the recommendation is to use this setting  
		•If this property is not set in your project, the default is true. If you are using version
		older than 2019, special scenarios will not be supported
		LPK & Cross instances)

**5. Check decision function level for enabled IIDFinder LUs**  
   -*This rule checks if LU has IIDFinder enabled, then decision function fnIIDFCheckExtractFromSourceInd should be set on Schema level*

**6. iidFinder\Staging.xml levels –**  
   -*Try to reduce the number of levels in the staging.xml\iidFinder.xml. If applicable (the table contains the LUID field), set the table 
as a first level*

**7. Table Population on top of the LUDB**  
   -*Table Population on top of the LUDB - Do not set it to automatically run every second. This population should be executed only when the source tables are being updated and therefore in most cases should be based on decision function*
   
**8. Not to extract from source in case of a new instance**  
   -*Use EXTRACT_FROM_SOURCE_IND = false and add the iidFinder generic LU
decision function: fnIIDFCheckExtractFromSourceInd*  
   -*Make sure that there are no scenarios that cannot be supported. For example,
records which were orphans on the source before the new instance was created.*

**9. Execute Enrichment functionality only when required**  
   -*This rule checks if enrichment functions inside IIDF category use getThreadGlobals with a valid global as parameter (global is definded in trnExecUserActivity).*  
   
		• Make sure the enrichment is being executed only once a message was received for the relevant table.
		• Use trnExecUserActivity functionality to set the relevant thread global.

[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_K2View_Customized_Rules/03_Cassandra.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/04_K2View_Customized_Rules/05_LU_and_Tables.md)

