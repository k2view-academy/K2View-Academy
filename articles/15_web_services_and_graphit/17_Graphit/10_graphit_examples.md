# Graphit - Code Examples
### Simple Example of a Customer Info Web Service that brings data for an LUI

The following Graphit file gets an input LUI which extracts customer data from the CUSTOMER LU, calculates its balance and sets its status accordingly. 

Output data is returned in JSON structure and adds information on whether the customer is either a:
-  VIP member, with a total balance of over USD 10,000.
-  Gold member, with a total balance of over USD 1,000. 

<img src="images/58_graphit_examples.PNG"></img>

After deploying and invoking the Graphit file directly as a Web Service:

<img src="images/59_graphit_examples.PNG"></img>



###  Example of a Web Service that invokes the relevant Graphit file depending on a specific condition    
The following wsGraphitExample2 Web Service gets an input LUI for the CUSTOMER LU and returns a response stating whether a customer has a Bronze, Gold or Platinum status in their subscription lines.

Code:

```java
String val_brz="Bronze";
String val_gld="Gold";
String val_plt="Platinum";

String CUST_STATUS = "SELECT count(*) FROM SUBSCRIBER where SUBSCRIBER.VIP_STATUS=?";
String cnt_brz = ludb("Customer", i_id).fetch(CUST_STATUS,val_brz).firstValue().toString();
String cnt_gld = ludb("Customer", i_id).fetch(CUST_STATUS,val_gld).firstValue().toString();
String cnt_plt = ludb("Customer", i_id).fetch(CUST_STATUS,val_plt).firstValue().toString();


if ((Integer.parseInt(cnt_brz)==0)&&((Integer.parseInt(cnt_gld) !=0)||(Integer.parseInt(cnt_plt) !=0))){
	return graphit("grSqlGldPlus.graphit",i_id);}
else{
	return graphit("grSqlBrz.graphit",i_id);}
```

The first Graphit file displays the customer's basic information and their subscriber lines with a Gold or Platinum VIP status while the second Graphit file displays the customer's basic information and corresponding subscriber lines in Bronze VIP status.

Graphit file 1: **grSQLGldPlus.graphit**:

<img src="images/60_graphit_examples.PNG"></img>


Graphit file 2: **grSQLBrz.graphit**:

<img src="images/62_graphit_examples.PNG"></img>


Output from the Swagger GUI for grSQLGldPlus.graphit with Customer Instance ID = 1234:

<img src="images/59_graphit_examples.PNG"></img>

Output from the Swagger GUI for grSQLBrz.graphit with Customer Instance ID = 1000:

<img src="images/59a_graphit_examples.PNG"></img>

### Simple Example of a CSV Output
This example displays how to retrieve data from multiple tables in the BILLING_DB database and use Graphit to prepare a CSV-formatted response:

#### grCSV.graphit

<img src="images/63_graphit_examples.PNG"></img>

Run the Graphit file in Debug mode with 2 and 3 as consecutive values for the SubscriberID:

<img src="images/65_graphit_examples.PNG"></img>



Notes:

- The csvRow has been set to the SUBSCRIBER_ID node. Therefore a  new line has been created for each new subscriber_id entry.

-  The csvHeader has been set to False in the SUBSCRIBER_INFO node. Therefore the header has been removed from the CSV output.



###  Graphit Node Types Examples
#### grFormat.graphit
In this example, all children nodes of the CRM_DB and the BILLING_DB nodes are defined as **field**. The response populates the document with the names and values of each specific field.<br></br>
<img src="images/09_graphit_examples_tags.PNG"></img>
Output:

<img src="images/grFormatResp.PNG"></img>

#### grFunction.graphit
This example illustrates a simple JavaScript routine that returns the highest number of the **x** random number and the **y** random number.<br></br>
<img src="images/10_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grFormatResp.PNG"></img>

#### grSQL.graphit
This example illustrates a parent node that is defined as SQL non-prepared whereas its children nodes are defined as SQL.<br></br>
<img src="images/11_graphit_examples_tags.PNG"></img>
Output:
<img src="images/grSQL2Resp.PNG"></img>

#### grString.graphit
This example illustrates how two values retrieved from a previously-defined SQL query are concatenated.<br></br>
<img src="images/12_graphit_examples_tags.PNG"></img>
Output:
<img src="images/grStringResp.PNG"></img>

#### grCondition.graphit
The condition defined in this file triggers either the TRUE or FALSE node depending on the randomly generated values of **x** and **y**.<br></br>
<img src="images/13_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grConditionResp.PNG"></img>

#### grGroup.graphit
The ${x} string has been added to both TRUE and FALSE groups while the ${y} value is not declared in the groups. The display ${x} also lists the group of origin.<br></br>
<img src="images/14_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grGroupResp.PNG"></img>

#### grCollect.graphit
This example shows how both Subscriber and Billing datasets are collected into one single array.<br></br>
<img src="images/15_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grCollectResp.PNG"></img>

#### grRaw.graphit
This example illustrates XML output in raw format. Observe the header value displayed in the response: ```(?xml version="1.0" encoding="UTF-8"...)```<br></br>
<img src="images/16_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grRawResp.PNG"></img>


###  Graphit Node Properties Examples

#### grShowFormat.graphit
The **sessionProvider** flag is set to CRM_DB to enable direct references to CRM_DB tables and fields.<br></br>
<img src="images/17_graphit_examples_tags.PNG"></img>

Output:

![ShowFormatResp](images/grShowFormatResp.png)

#### grShowEnabled.graphit

The response returns empty since the entire CRM_DB node and its children nodes are affected by the **enabled** flag.

In addition, the **nice** flag is set to TRUE on the root node level. As a result, each tag of the response is indented according to the position of the tag in the document's hierarchy.



<br></br>
<img src="images/18_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grShowEnabledResp.png"></img>



#### grOne.graphit

The **one** flag is set to TRUE and has been applied to the Billing_DB2 node. The response only brings the first value for {"BILLING_DB2":{"SUBSCRIBER_ID":2}}, instead of the 10 values expected for this tag had the **one** flag not been activated.<br></br>
<img src="images/20_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grShowOneResp.png"></img>

#### grEntry.graphit
The **entry** flag has been set to the Subscribers node and therefore the XML response displays tags around each subscriber_id value.<br></br>
<img src="images/21_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grEntryResp.png"></img>


#### grAttribute.graphit
The **attribute** flag has been activated on all children nodes of the CRM_DB node.<br></br>
<img src="images/22_graphit_examples_tags.PNG"></img>
Output:
<img src="images/grAttributeResp.png"></img>

#### grFormat.graphit
The **format** flag has been set to XML in the CRM_DB node. If the format is specified, the node will only be evaluated and added if the output format matches the format value.

The example below sets a JSON in the output format. There CRM_DB node is not displayed in the response since its format is in XML.

![grFormat](images/23_graphit_examples_tags.PNG)


Output:

<img src="images/grFormat2Resp.png"></img>

#### grShowEmpty.graphit
The **showempty** flag has been set to False and applied to the CRM_DB node. Empty nodes are not shown in the response.<br></br>
<img src="images/24_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grShowEmptyResp.png"></img>

#### grShowNull.graphit
The **showNull** flag has been set to False and applied to the CRM_DB node. The response does not display the LAST_NAM field in the CRM_DB node since it has Null values which are ignored and not shown in the section of the response referring to the CRM_DB. The flag has not been applied to the BILLING_DB node, and therefore Null values are displayed.<br></br>
<img src="images/25_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grShowNullResp.png"></img>

#### grNumberFormat.graphit
The **numberFormat** flag has been set to 000.00 and applied to the NumberFormat node. All responses display **numberFormat** with 3 digits before the floating point and another 2 after.<br></br>
<img src="images/26_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grNumberFormatResp.png"></img>

#### grKeys.graphit
The response has been reorganized using the subscriber_id as a key.<br></br>
<img src="images/27_graphit_examples_tags.PNG"></img>

Output:

<img src="images/grKeysResp.png"></img>


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/09_invoke_graphit_from_outside_studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/11_graphit_error_handling.md)
