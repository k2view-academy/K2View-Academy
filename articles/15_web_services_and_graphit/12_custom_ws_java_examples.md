# Java Function Web Services - Code Examples

While it is recommended to build web services with Graphit, you might use Java function for this purpose.   

### Example List

* [Simple response - single entry from the LU root table](#simple-response-single-entry-from-the-LU-root-table)
* [Complex JSON input using standard Java objects](#example-of-a-complex-java-input-structure)
* [Complex JSON input using customized Java objects](#example-of-a-complex-customized-input-structure)
* [complex TDM](#example-of-a-complex-tdm-web-service)
* [Customized Payload -  JSON](#example-of-a-custom-payload---json)
* [Customized Payload -  XML](#example-of-a-custom-payload---xml)



### Simple response - single entry from  the LU root table  

The following Web Service gets an input LUI for the CUSTOMER LU and returns data from the CUSTOMER table in the CUSTOMER LU. Output data is returned in DB.Rows structure. It can also be returned as an object, which is then converted by Fabric into DB.Rows structure.
```java
String sql = "SELECT CUSTOMER_ID, SSN, FIRST_NAME, LAST_NAME FROM CUSTOMER";
Db.Rows rows = ludb("Customer", i_id).fetch(sql);
log.info("WS executed Successfully for Customer ID :" + i_id);
return rows;
```

Output 

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



### Example of a complex Java input structure
A complex JSON format can also be sent as input to a Fabric Web Service using the POST verb. Data is automatically serialized according to the input structure defined as a part of the Web Service's markup.

For example:

Requested body 
```
{
  "ID":[{"id":"78999", "company":"Telco1"},{"id":"z34","company":"Telco2"}],
  "parent_customer_id":"456",
  "company":"Telco International"
}
```
Web Service markup
```java
public static String wsExample(List<Map<String,String>> ID, String parent_customer_id, String company){
}
```
Web Service inside logic 
```java
     Map<String,String> m = ID.get(0);
     String id = m.get("id"); // will return 78999
     String company = m.get("company"); // will return Telco1
```



### Example of a complex customized input structure

This example is similar to the above example, however, this time it is with customized Java classes.

For example:

Requested body 

```
    {
      "person": {
        "address": [
         { 
            "number": 10,
            "city": "Net York",
            "street": "5th Ave."
          }
        ],
        "name": "Lion",
        "id": "1234",
        "age": 45
      }
    }
```

Web Service markup

```java
static class Person {
   String name;
   String id;
   int age;
   List<Address> address;
}

static class Address {
   String city;
   String street;
   int number;
}

	@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = 					false, produce = {Produce.XML, Produce.JSON})
	public static Address CustomClassExample(Person person) throws Exception {
```

Web Service inside logic 

```java
     return person.address.get(0);
```

Web Service response

```
{
  "city": "Net York",
  "street": "5th Ave.",
  "number": 10
}
```




### Example of a complex TDM Web Service 

The wsGetTaskExeStatsForEntity Web Service used by TDM GUI brings a map of all entity lists related to a given LUI that are related to the same business entity. That is, all instances related to all LUT under the same task execution that are defined as a parent or child of the given input LUI, call recursive functions to get a full hierarchy path. 

```java
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
	//Sending the children hierarchy in order to add it to the ancestors as child hierarchy
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



### Example of a Custom Payload - JSON

The following Web Service is similar to the [Simple response - single entry from the LU root table](#simple-example-of-a-wscustomerinfo-web-service-that-brings-a-line-of-data-for-a-given-instance), but it manages the input request payload by itself. 

Do not forget to set the *Custom Payload* flag to *True* in the **Web Service Properties** panel, located in the top right corner of the **Web Service** window.

It starts by reading the request body payload and acquiring it using `request().getInputStream()`:

```java
StringBuffer sbf = new StringBuffer();
try {
   InputStream inputStream = WebServiceUserCode.request().getInputStream();
   BufferedReader in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
   char[] bufferChar = new char[1024];
   int index = 0;
   while((index=in.read(bufferChar))!=-1){
      sbf.append(bufferChar,0,index);
   }
   inputStream.close();
} catch (IOException e) {
   log.error(e.getMessage(), e);
}
```

After reading the payload, it transforms it into the JSON format used in this example. It then fetches the data from the LU: 

```java
if (sbf!=null) {
   jsonRequest = new JSONObject(sbf.toString());
   log.info("json - " + jsonRequest.toString());
   ID = jsonRequest.getString("ID");
   if (ID != null) {
      rows = ludb("Customer", ID).fetch(sql);
   }
}
```

The wsCustomerInfoCustomPayload function is available in the Demo project. 

### Example of a Custom Payload - XML 

The following Web Service inserts rows into the CASES table in the CUSTOMER LU, where the input is retrieved in an XML format.

Do not forget to set the *Custom Payload* flag to *True* in the **Web Service Properties** panel, located in the top right corner of the **Web Service** window.

The wsInsertCasesCustomPayloadXML function is available in the Demo project.

Request body 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<cases iid="1">
    <case id="20007">
        <activity_id>1</activity_id>
        <case_date>2020-03-17 16:16:47</case_date>
        <case_type>Network Issue</case_type>
		<status>Open</status>
	</case>	
	<case id="20008">
        <activity_id>1</activity_id>
        <case_date>2020-03-18 16:16:47</case_date>
        <case_type>Device Issue</case_type>
		<status>Closed</status>
	</case>	
</cases>
```

In order to parse the XML, several import statements are added to the file:

```java
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
```

The following XML DOM reading code is added to the function:

```java
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
factory.setNamespaceAware(true);
DocumentBuilder documentBuilder = factory.newDocumentBuilder();
Document doc = documentBuilder.parse(WebServiceUserCode.request().getInputStream());
doc.getDocumentElement().normalize();
```

Note that the `WebServiceUserCode.request().getInputStream()` statement is used to manually handle the request body.

From here DOM functions are used to acquire the input attributes and elements.

For example, the iid is located in this example as an attribute on the XML's root element. 

```java
Element iidEelem = (Element) doc.getDocumentElement();
String iid = iidEelem.getAttribute("iid");
```

To read the list of cases, a loop iteration was performed on the case list: 

```
NodeList nList = doc.getElementsByTagName("case");
```

Each element and attribute of the case can then be read; for example:

```java
Node node1 = elem.getElementsByTagName("activity_id").item(0);
String activityId = node1.getTextContent();
```

The code for inserting this into the DB is similar to the code in the wsInsertCases function, which is available in the Demo project.





[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/11_swagger.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/13_custom_ws_java_annotations.md)
