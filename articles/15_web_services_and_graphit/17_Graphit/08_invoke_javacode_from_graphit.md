# Invoking Java Code From Graphit


## JavaScript Functions
JavaScript functions can be added to the Graphit file in the Fabric Studio using the Graphit Editor. To do so, add a new node to an already-defined root and configure it as a function that runs a code to determine the value of the node. Values extracted from datasets in parent nodes can be parsed down to the JavaScript function.

Note: You can refer to parameters directly, using their defined name in the Parameters Window.

### Example
In the following example, a Graphit Web Service retrieves the sum of all payments made by a customer from the Received Invoices table.
A decision can be made whether to convert this amount into GBP or EUR currency, depending on the value of the convGBP parameter.

-  True - displays the amount in GBP.
-  False - displays the amount in EUR.

The following screenshot displays how dataset nodes and JavaScript nodes are defined as children of the main Balance node:

![](/articles/15_web_services_and_graphit/17_Graphit/images/50_invoke_javacode_from_graphit.PNG)


The following screenshot displays how Debug mode is run with the customer_id set to 1000 and the conversion currency set to False (i.e. GBP currency):

![](/articles/15_web_services_and_graphit/17_Graphit/images/51_invoke_javacode_from_graphit.PNG)


The following screenshot displays how the Web Service is deployed and launched in Swagger:

![](/articles/15_web_services_and_graphit/17_Graphit/images/52_invoke_javacode_from_graphit.PNG)

Note that an http link has been generated to invoke the Web Service for customer_id = 1000, and the convGBP flag is set to False, meaning that the conversion is set to EUR currency: ``` "http://10.21.1.76:3213/api/grSql?customer_id=1000&convGBP=false" ```


## Lambda Functions
Java functions can be bound to Graphit using a Web Service. To do so, create a Lambda expression based on the Scripter.F functional interface in a key/value map entry, where the key contains the name of the function that is invoked in Graphit and the value contains its logic.

### Example

Web Service file: LambdaGraphit1.ws

```java
Map<String, Object> scope = new HashMap<>();
// a map object named scope in which each entry will contain the function's name and its logic
scope.put("times3", (Scripter.F) p->(double)p[0] *3);
// call graphit with the scope
return graphit("LambdaGraphit1.graphit", scope);
```

Graphit file LambdaGraphit1.graphit: 
Note that the function times defined above are called from the node **field 1** (defined as a function) on a variable called **dbl**, declared in the parent node 
![](/articles/15_web_services_and_graphit/17_Graphit/images/52a_invoke_javacode_from_graphit.PNG)


Output using the Swagger GUI:
![](/articles/15_web_services_and_graphit/17_Graphit/images/52b_invoke_javacode_from_graphit.PNG)


Limitations:
This type of function cannot be debugged in the Graphit Editor as the functional parameters cannot be created in the Studio. 
However, the code can be tested by deploying it to Fabric or by debugging the Web Service code using the the IntelliJ editor. 

 

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/07_invoking_graphit_files.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/09_invoke_graphit_from_outside_studio.md)

