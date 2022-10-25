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



## Set Parameters When Testing in Graphit
When creating a Graphit file, parameters can be defined using the **${}** symbols to refer to the value that is set in the Properties and Parameters Panel. To test it at Graphit, you shall populate the Debug value in the Properties and Parameters Panel.



EXAMPLE



## Transfer Parameters When Calling Graphit as Web Service


TBD: Refer to WS article



## Transfer Parameters When Invoking Graphit From a Function
A Graphit file can be invoked from Fabric internal implementation components. When calling Graphit from a java function, the input parameters shall be populated by a parameter name or by a map object.

**Example 1**: sending parameters as a map -

<pre><code>Map&lt;String, Object&gt; graphitParams = new HashMap&lt;&gt;();
graphitParams.put("input1",1000);
graphitParams.put("input2",2463);
return graphit("grSql2.graphit", graphitParams);</code></pre>

This code calls the following Graphit file which uses **${input1}** and **${input2}** as parameters.



**Example 2**: sending parameters as parameter list -        

TBD



>  Note: you can send "format" as one of the parameters when wishes to get the result document in a specific format, ither than the default which is JSON.



## Use The Input Parameters 

TBD + examples





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









