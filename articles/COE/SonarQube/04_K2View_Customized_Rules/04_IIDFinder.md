# K2View Customized Rules

## 4.4	IIDFinder

**1.** Check Root table configuration

   -*Below are the correct settings:*
	
		• Truncate = false  
		• Unique index on the IID  
		• population mode = ‘upsert’  
		
  -*If the truncate mode is set to “true”, the deleteOrphans functionality for LPK scenario on a table that is connected to the root might trigger the deletion of records which should not be deleted*
	  
  
**2.** Check stored attribute in staging xml  
	-*This rule checks the "stored" attribute defined staging xml per table:*

		• if table is not a leaf stored should be true
		• if the table is leaf and parent key field is part of the PK then stored should be false
		• if the table is leaf and parent key is not part of the PK then headsup rule as below:
 			 "validate if we get all columns in the message then store=false "

**3.** Check IIDF Kafka Details  

   -*This rule checks below recommendations:* 

		• Do not store the IIDF Kafka details in translation table.
		• Use IifProperties.getInstance() to get the IIDFinder configuration setting of the cluster.

**4.** Check Source Available attribute defined in staging xml file  
	-*This rule checks the "sourceAvailable" attribute defined staging xml per lu:*

	Issues are created depending on the return value of the XPath expression. If the XPath expression returns:
			•The latest iidFinder implementation is tested with sourceAvailable set to "false",
			therefor the recommendation is to use this setting
			•If this property is not set in your project, the default is true. If you are using version
			older than 2019, special scenarios will not be supported
			LPK & Cross instances)

**5.** Check decision function level for enabled IIDFinder LUs  

   -*This rule checks if LU has IIDFinder enabled, then decision function fnIIDFCheckExtractFromSourceInd should be set on Schema level*



[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_K2View_Customized_Rules/03_Cassandra.md)

