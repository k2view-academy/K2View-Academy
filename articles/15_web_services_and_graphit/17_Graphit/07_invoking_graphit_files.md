# Invoking Graphit Files
Graphit files can be invoked either externally as Web Services or internally from other Fabric implementation components. 

In either options you can transfer Graphit input parameters, while invoking it. To learn how to define parameters in Graphit read [here]().

## How Do I Invoke Direct Calls?
1. Go the the **Project Tree** and click **Resources** under **Web Services**. 

2. Right click the **Graphit file** > **Invoke Graphit Web Service** > **Fabric server**.

Note that the Fabric server must be predefined in the [Server Configuration tab](/articles/04_fabric_studio/04_user_preferences.md#what-is-the-purpose-of-the-server-configuration-tab) in the User Configuration tab in the Admin panel.

For more information, refer to the example in [Graphit Parameters](/articles/15_web_services_and_graphit/17_Graphit/06_using_graphit_files_with_parameters.md#parameters-setup-when-calling-graphit-directly-from-swagger) to see how to deploy and invoke a Graphit file as a Web Service.

![](images/47_invoking_graphit_files.png)
Note that the Graphit file can be invoked in both GET or POST modes. To use the GET method, set the parameters in the **Graphit parameter window**. When the Graphit file is invoked as a Web Service, Swagger will prompt you to enter the values.

For more information about Get and Post verbose, click [GET](/articles/15_web_services_and_graphit/12_Supported_Verbs_Get.md) or [POST](/articles/15_web_services_and_graphit/13_Supported_Verbs_Post.md).

## How Do I Invoke a Call From WS Code?
Graphit files are mainly used in a Web Service to structure the Web Service's response. To use the Graphit file, include the following code in the Web Service implementation:

<p><code>Object response = graphit(&lt;file name&gt;, &lt;Input parameters&gt;).</code></p>

The function's parameters are:
  - File_name, the name you assign the Graphit file that should generate the response document. If the Web Service's name is the same as the Graphit file's name, set this parameter to Null.
  - Input parameters, which can be populated by a parameter name or by a map object.

The Response variable gets the CSV, JSON or XML response string which can then be returned as the Web Service output.

Example:
Using the grSQL Graphit file and Customer_Id as input parameters:
 <p><code>Object response = graphit("grSql.graphit",Customer_Id);</code></p> 

![](/articles/15_web_services_and_graphit/17_Graphit/images/48_invoking_graphit_files.PNG)

After deploying and invoking the Web Service,![](/articles/15_web_services_and_graphit/17_Graphit/images/45_graphit_with_parameters.png) open Swagger and check that the Customer_id has been successfully parsed.
![](/articles/15_web_services_and_graphit/17_Graphit/images/46_graphit_with_parameters.PNG)


### How Can I Invoke a Call With a URL Link?
Graphit can also be invoked as a parameter from the IP address link of the corresponding Web Service.
Enter the following parameters in the link of the browser's Address field:

     http://localhost:3213/api/GraphitWS1?Customer_Id=1472&Case_Id=3707&token=test&graphitProfiler=true&format=json

The response is displayed in the Browser tab:

![](/articles/15_web_services_and_graphit/17_Graphit/images/49_invoking_graphit_files.PNG)

#### Profiler Flag
A profiling flag can be parsed in the URL using the graphitProfiler=true setting as in the following URL:

``` http://localhost:3213/api/GraphitWS1?Customer_Id=1472&Case_Id=3707&token=test&*graphitProfiler=true*&format=json``` 

To see the profiling information, open the [Fabric Logs](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md#log-files) of the Fabric session that processed the Web Service in the $K2_HOME/logs/k2fabric.log. Search for the lines containing "Starting webservice":

``` 
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,286 c.k.c.w.WebServiceMB - [LID10000000023a3] Starting webservice GraphitWS1
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,289 c.k.f.s.FabricSession - [LID10000000023a3] START - ATTACH Customer.1472
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,289 c.k.f.s.FabricSession - [LID10000000023a3] Access to [Customer.1472] by user admin is authorized.
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,292 c.k.f.s.FabricSession - [LID10000000023a3] START - sync Customer.1472
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,292 c.k.f.s.FabricSession - [LID10000000023a3] local get request
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,292 c.k.f.s.s.l.SyncExecution - [LID10000000023a3] Start operation 'Sync Customer.1472'
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,396 c.k.f.s.s.l.SyncExecution - [LID10000000023a3] End operation 'Sync Customer.1472' successfully.  [104ms]
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,396 c.k.c.e.LogService - [LID10000000023a3] LogId K2VIEW1000 message General Fabric info - <Invoice Cleaning fonction is running> with details: null
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,397 c.k.c.e.LogService - [LID10000000023a3] LogId K2VIEW1000 message General Fabric info - <PhoneFormat fonction is running> with details: null
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,397 c.k.c.e.LogService - [LID10000000023a3] LogId K2VIEW1000 message General Fabric info - <PhoneFormat fonction is running> with details: null
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,397 c.k.f.s.s.l.SyncExecution - [LID10000000023a3] Customer:1472 was synced from source
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,399 c.k.f.s.FabricSession - [LID10000000023a3] FINISHED - sync Customer.1472 (UPDATE)
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,400 c.k.f.s.FabricSession - [LID10000000023a3] FINISHED - ATTACH Customer.1472 (UPDATE)
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,401 c.k.c.e.WebExecutionService - [LID10000000023a3] Root 112.969598ms (112.355082ms), 180 bytes, 10 nodes
  CUSTOMER_DATA 0.183801ms (0.145869ms), 103 bytes, 5 nodes
    CUSTOMER_ID 0.31753ms, 40 bytes
    SSN 0.2792ms, 19 bytes
    FIRST_NAME 0.1801ms, 21 bytes
    LAST_NAME 0.1586ms, 21 bytes
  CASE_DATA 0.189604ms, 15 bytes
  Balance 0.241111ms (0.93389ms), 60 bytes, 3 nodes
    BalValue 0.10539ms, 28 bytes
    BalanceGBPEUR 0.137183ms, 30 bytes
INFO  [http-nio-3213-exec-1] 2020-07-15 06:46:21,401 c.k.c.w.WebServiceMB - [LID10000000023a3] End webservice GraphitWS1
```
The Profiler option can also be selected from the Graphit Editor by choosing the **Output** > **Profiler** option on the right side of the **Run** button, as shown in the following screenshot.

![](/articles/15_web_services_and_graphit/17_Graphit/images/49a_invoking_graphit_files.PNG)



Note that multiple parameters can be parsed to Graphit by:
- Passing a map as a parameter in which the parameters and their values have been stored as key / value pairs.
- Passing a list of arguments and then looping over the list.

In addition, when designing a Web Service you can use all [REST APIs and requests formats](/articles/15_web_services_and_graphit/12_Supported_Verbs_Get.md) generally supported by Web Services. Schemas with complex requests can be designed whereby different Graphit files are invoked depending on the data retrieved from its sources or LUI. 


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/06_using_graphit_files_with_parameters.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/08_invoke_javacode_from_graphit.md)
