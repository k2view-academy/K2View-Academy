# Web-Services - Code Examples
### Simple Example of Web-Service bringing one line of data for a given instance - wsCustomerInfo

The following Web-Service get as an input LUI for CUSTOMER LU and return data from CUSTOMER table on CUSTOMER LU.

It returns DB.Rows output structure (can be set as Object as well, Fabric knows how to convert it to DB.Rows structure)

```
String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME, LAST_NAME FROM CUSTOMER";


Db.Rows rows = ludb("Customer", i_id).fetch(sql);

reportUserMessage("WS executed Succesfully for Customer ID :" + i_id);

return rows;
```

Output:

```
[
  {
    "CUSTOMER_ID": "1",
    "SSN": "5153527856",
    "FIRST_NAME": "Tali",
    "LAST_NAME": "Griffin"
  }
]
```

### Example of Web-Service bringing Db.Rows structure as an output for a given instance - wsCustomerInfo2

The following Web-Service get as an input LUI for CUSTOMER LU and return several rows of data by running a join query on several tables on CUSTOMER LU

It returns DB.Rows output structure (can be set as Object as well, Fabric knows how to convert it to DB.Rows structure)

```
String sql = "select cust.CUSTOMER_ID,cust.SSN,cust.FIRST_NAME||' '||cust.LAST_NAME CUSTOMER_NAME, cont.CONTRACT_ID,cont.CONTRACT_DESCRIPTION,sub.SUBSCRIBER_ID,sub.MSISDN,sub.IMSI,sub.SIM,sub.SUBSCRIBER_TYPE " +
		"from CUSTOMER cust, CONTRACT cont, SUBSCRIBER sub where cont.CONTRACT_ID=sub.SUBSCRIBER_ID";

Db.Rows rows = ludb("Customer", i_id).fetch(sql);

reportUserMessage("WS executed Succesfully for Customer ID :" + i_id);

return rows;
```

Output:

```
[
  {
    "CUSTOMER_ID": "1",
    "SSN": "5153527856",
    "CUSTOMER_NAME": "Tali Griffin",
    "CONTRACT_ID": "1.0",
    "CONTRACT_DESCRIPTION": "5G tether",
    "SUBSCRIBER_ID": "1",
    "MSISDN": "9614867860",
    "IMSI": "531015353732639",
    "SIM": "2988735759833578",
    "SUBSCRIBER_TYPE": "1"
  },
  {
    "CUSTOMER_ID": "1",
    "SSN": "5153527856",
    "CUSTOMER_NAME": "Tali Griffin",
    "CONTRACT_ID": "2.0",
    "CONTRACT_DESCRIPTION": "10G 3G",
    "SUBSCRIBER_ID": "2",
    "MSISDN": "7997099409",
    "IMSI": "457470703125000",
    "SIM": "3751389217697811",
    "SUBSCRIBER_TYPE": "3"
  },
  {
    "CUSTOMER_ID": "1",
    "SSN": "5153527856",
    "CUSTOMER_NAME": "Tali Griffin",
    "CONTRACT_ID": "3.0",
    "CONTRACT_DESCRIPTION": "Roaming special",
    "SUBSCRIBER_ID": "3",
    "MSISDN": "3924663547",
    "IMSI": "759668646918403",
    "SIM": "2395410334354832",
    "SUBSCRIBER_TYPE": "1"
  },
  {
    "CUSTOMER_ID": "1",
    "SSN": "5153527856",
    "CUSTOMER_NAME": "Tali Griffin",
    "CONTRACT_ID": "4.0",
    "CONTRACT_DESCRIPTION": "450 min",
    "SUBSCRIBER_ID": "4",
    "MSISDN": "7042855196",
    "IMSI": "345797729492187",
    "SIM": "9009227816906040",
    "SUBSCRIBER_TYPE": "4"
  },
  {
    "CUSTOMER_ID": "1",
    "SSN": "5153527856",
    "CUSTOMER_NAME": "Tali Griffin",
    "CONTRACT_ID": "5.0",
    "CONTRACT_DESCRIPTION": "Unlimited call",
    "SUBSCRIBER_ID": "5",
    "MSISDN": "7183304985",
    "IMSI": "734794108072916",
    "SIM": "5671433642523324",
    "SUBSCRIBER_TYPE": "4"
  }
]
```

### Versioning example

Both wsCustomerInfo and wsCustomerInfo2 Web-Services from the examples above are sharing the same URL path, called test/getCustomerInfo, wsCustomerInfo is defined with Version 1 property and wsCustomerInfo is defined with Version 2 property.

In order to invoke a call to wsCustomerInfo the following URL should be called: http://localhost:3213/api/v1/test/getCustomerInfo?i_id=1&token=ABC&format=json

In order to invoke a call to wsCustomerInfo2 the following URL should be called: http://localhost:3213/api/v2/test/getCustomerInfo?i_id=1&token=ABC&format=json

### Complex Example of TDM Web-Service 

A Web-Service used by TDMGUI, called wsGetTaskExeStatsForEntity, that brings a map of all entities list related to a given LUI with a relation to the same Business Entity.

Meaning all the instances related to all LU types under the same task execution that are defined as parent or child of the given input LUI (call recursive functions in order to get a full hierarchy path).

```
String sqlGetEntityData = "select lu_name luName, target_entity_id targetId, entity_id sourceId, " +
	"execution_status luStatus from TDM.task_Execution_link_entities  " +
	"where lu_name <> ? and target_entity_id = ? and entity_id = ?";

String sqlGetParent = "select parent_lu_name, target_parent_id from TDM.task_Execution_link_entities " +
	"where lu_name= ? and target_entity_id = ? and parent_lu_name <> ''";

Map <String, Object> mainOutput = new HashMap<>();
Map <String, Object> childHierarchyDetails = new HashMap<>();
Map <String, Object> parentHierarchyDetails = new HashMap<>();
	
Db.Row entityDetails = null;
Boolean countChildren = false;
		
fabric().execute( "get TDM." + taskExecutionId);

//Get the Hierarchy starting from the given entity and below
childHierarchyDetails = fnGetChildHierarchy(luName, targetId);

String parentLuName = "";
String parentTargetId = "";
		
// Get the parent of the given LU, to see if there is a reason to get the ancestors or not
Db.Row parentRec = fabric().fetch(sqlGetParent, luName, targetId).firstRow();

if (!parentRec.isEmpty()) {
	//log.info("There is a parent: " + parentRec.cell(0));
	parentLuName = "" + parentRec.cell(0);
	parentTargetId = "" + parentRec.cell(1);
}
//If the the input entity has parents get the hierarchy above it
if (parentLuName != null && !"".equals(parentLuName)) {
	//Starting for the parent as the details of the input entity is already included in the children part
	//Sending the chilren hierarchy in order to add it to the ancestors as child hierarchy
	parentHierarchyDetails = fnGetParentHierarchy(parentLuName, parentTargetId, childHierarchyDetails);
} else {// Given inputs are of a root entity
	parentHierarchyDetails =  childHierarchyDetails;
}

String rootLUName = "" + parentHierarchyDetails.get("luName");
String rootTargetID = "" + parentHierarchyDetails.get("targetId");
String rootSourceID = "" + parentHierarchyDetails.get("sourceId");

mainOutput.put(rootLUName, parentHierarchyDetails);
//If there are other root entities with the same root entity ID get them, 
//they will be added to output as standalone (even if they have their own hierarchy)
Db.Rows otherRootRecs = fabric().fetch(sqlGetEntityData, rootLUName, rootTargetID, rootSourceID);
for (Db.Row rootRec : otherRootRecs) {
	Map <String, Object> rootDetails = new HashMap<>();
	String currRootLuName = "" + rootRec.cell(0);
	rootDetails.put("luName", currRootLuName);
	rootDetails.put("targetId", "" + rootRec.cell(1));
	
//Get instance ID from entity id
Object[] splitId = fnSplitUID("" + rootRec.cell(2));
String instanceId = "" + splitId[0];
rootDetails.put("sourceId", "" + instanceId);

rootDetails.put("entityStatus", "" + rootRec.cell(3));

mainOutput.put(currRootLuName, rootDetails);

}
if (otherRootRecs != null) {
	otherRootRecs.close();
}

return mainOutput;
```

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/05_edit_web_service_code.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/07_deploy_web_services_from_fabric_studio.md)
