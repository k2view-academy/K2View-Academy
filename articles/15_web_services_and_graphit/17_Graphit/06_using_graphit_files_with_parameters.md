# Defining and Using Graphit Parameters

Graphit allows you to define input parameters whereby the generated documents are executed using various settings like LUIs, LU table columns and other specific parameters that require processing.

Parameters can be set when:
- Testing a Graphit file in the Graphit Editor.
- Invoking Graphit as web service via HTTP request.
- Calling Graphit from another implementation component such as Java Function or Broadway. 



## Defining Graphit Parameters

1. Click on the <img src="D:\OneDrive - K2View\K2View-Academy-7.0\articles\15_web_services_and_graphit\17_Graphit\images\url-icon.png"></img> icon at the top Graphit Editor toolbar.
2. The **URL Parameters & Properties** right side panel is opened where at top you can find the **Input Parameter & Path** section. At the beginning no parameters are defined.
3. To add a new parameter click on the plus  (+) sign at the top of the Parameters sub section.
   <img src="D:\OneDrive - K2View\K2View-Academy-7.0\articles\15_web_services_and_graphit\17_Graphit\images\ws_graphit_props_1.png"></img>

4. New Parameter pane is opened where you can define for the new parameter: name, type, description and if it is mandatory.
   <img src="D:\OneDrive - K2View\K2View-Academy-7.0\articles\15_web_services_and_graphit\17_Graphit\images\ws_graphit_props_2.png"></img>

5. You can then add more parameters as needed. you can expand and collapse each of parameters pane, where in collapsed mode only the name appears as well as the debug value for testing. 
   <img src="D:\OneDrive - K2View\K2View-Academy-7.0\articles\15_web_services_and_graphit\17_Graphit\images\ws_graphit_props_4.png"></img>

> Note: the debug value is not saved with the Graphit file and it is used only in the Graphit Editor, for testing the web-service.



## How Do I Configure Parameters When Running Graphit From Fabric Studio?
When creating a Graphit file, parameters can be defined using the **${}** symbols to refer to the value that is set in the Parameters window. In this specific case, you must also define a **Debug** value in the Parameter window. If not, the response is empty.


**Example**: 

 The **grSql.graphit** file generates a JSON file that returns the values of the following fields:
- Customer_ID, SSN, first_name and last_name of a customer whose Instance ID = 547.  
- Date and status of Case ID = 1394.
- Generic SELECT statement that retrieves Instance 547: get Customer.${customer_id}.

The queries for the SELECT statements are:
- Select customer_id,ssn,first_name,last_name From Customer.CUSTOMER where CUSTOMER_ID = ${customer_id}.
- Select * from CASE_NOTE where case_id = ${case_id}

The following screenshot displays the Graphit file:

![](/articles/15_web_services_and_graphit/17_Graphit/images/35_graphit_with_parameters.PNG)

Before running the file, a **Debug** value is assigned to the **${customer_id}** and **${case_id}** input parameters.

![](/articles/15_web_services_and_graphit/17_Graphit/images/38_graphit_with_parameters.PNG)  

Click **Run** to see the results on the right side of the output window.

![](/articles/15_web_services_and_graphit/17_Graphit/images/39_graphit_with_parameters.PNG)

## How Do I Configure Parameters When Calling Graphit Directly From Swagger?
Add the parameters to the Parameters window to create a GET request for the Graphit file and populate the parameters in Swagger. 

Note that to execute the Graphit file, first populate the parameter's value in Swagger. The Debug values are only taken as input in Debug mode.

Example:

Using the same **grSql.graphit** Graphit file as in the previous example, in the following screenshot the **customer_id Debug** value has been left empty intentionally.

![](/articles/15_web_services_and_graphit/17_Graphit/images/40_graphit_with_parameters.PNG)

As indicated below, the two Parameters fields are marked as required.

![](/articles/15_web_services_and_graphit/17_Graphit/images/42_graphit_with_parameters.PNG)

The **customer_id=547** and **case_id=1394** values are filled in the Parameters fields. 

![](/articles/15_web_services_and_graphit/17_Graphit/images/43_graphit_with_parameters.PNG)

Note that when deleting all parameters in the Parameters dialog box together, the values in the GET section in the Swagger GUI cannot be specified but can be injected in the POST section, with a successful response upon execution.

![](/articles/15_web_services_and_graphit/17_Graphit/images/44_graphit_with_parameters.PNG)

## How Do I Configure Parameters When Invoking Graphit From a Web Service?
A Graphit file can be invoked directly or be wrapped by a WS. When wrapped by a WS, the WS sends the parameters to Graphit which then parses them accordingly when generating the XML, JSON or CSV documents. The input parameters for the Graphit file can be populated by a parameter name or by a map object.

Example:

<pre><code>Map&lt;String, Object&gt; temp = new HashMap&lt;&gt;();
temp.put("input1",1000);
temp.put("input2",2463);
return graphit("grSql2.graphit", temp);</code></pre>


This code calls the following Graphit file which uses **${input1}** and **${input2}** as parameters for the **customer_id** and **subscriber_id** to populate the JSON output with relevant invoices:
![](/articles/15_web_services_and_graphit/17_Graphit/images/46a_graphit_with_parameters.PNG)
        
        
## How Do I Parse Metadata Parameters Such as Table Names or Fields Names in Graphit SQL ?

Let's suppose you need to write a graphit file that retrieves data from a table and/or a field with dynamic names parsed from variables.
In that case you can invoke the field name as a parameters in a Graphit node defined as non-prepared SQL statement. 

For example, let's consider the following:

Parameters declaration window:
**customer_id** is defined as string and is given the value 39.

Node defined as a function:

``` var fieldName = 'customer_id' ```

Node defined as SQL non-prepared:

```select SSN from customer where ${fieldName} = ${customer_id}```


In this case, the ```${fieldName}``` variable will be given the (string) value ```'customer_id'``` while the variable ```${customer_id}``` will be given the value ```39```.
In that way, variables may be used in a graphit file (from a javascript function node) and hold the name of the field and the value to be processed to the WHERE statement.

See example in screenshot:
![](/articles/15_web_services_and_graphit/17_Graphit/images/46b_graphit_with_parameters.PNG)


In addition, rather than defining the variable ```fieldName``` within the javascript function node of your graphit file, you can define it in the java webservice that invokes the graphit file itself, and parse it as a map object as was shown in the [previous section](/articles/15_web_services_and_graphit/17_Graphit/06_using_graphit_files_with_parameters.md#how-do-i-configure-parameters-when-invoking-graphit-from-a-web-service) of this article.   


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/05_graphit_debugging.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/07_invoking_graphit_files.md)









