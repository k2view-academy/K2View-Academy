# K2View Customized Rules

## 4.4	IIDFinder

- Check Root table configuration
  o Following is the correct setting:

  ​	•	Truncate = false
  ​	•	Unique index on the IID
  ​	•	population mode = ‘upsert’
  o If the truncate mode is set to “true”, the deleteOrphans functionality for LPK scenario on a table that is connected to the root might trigger the deletion of records which should not be deleted

  

- Check stored attribute in staging xml
  This rule checks the "stored" attribute defined staging xml per table:

  ​	•	if table is not a leaf stored should be true
  ​	•	if the table is leaf and parent key field is part of the PK then stored should be false
  ​	•	if the table is leaf and parent key is not part of the PK then headsup rule as below:
  "validate if we get all columns in the message then store=false "

-	Check IIDF Kafka Details

This rule checks below recommendations: 

​		•	Do not store the IIDF Kafka details in translation table.
​		•	Use IifProperties.getInstance() to get the IIDFinder configuration setting of the cluster.

-	Check Source Available attribute defined in staging xml file
This rule checks the "sourceAvailable" attribute defined staging xml per lu:
Issues are created depending on the return value of the XPath expression. If the XPath expression returns:
	•	The latest iidFinder implementation is tested with sourceAvailable set to "false",
therefor the recommendation is to use this setting
	•	If this property is not set in your project, the default is true. If you are using version
older than 2019, special scenarios will not be supported
LPK & Cross instances)

-	Check decision function level for enabled IIDFinder LUs

This rule checks if LU has IIDFinder enabled, then decision function fnIIDFCheckExtractFromSourceInd should be set on Schema level



[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_Customized_Rules/03_Cassandra.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/05_Reference_and_Document/01_Reference.md)

