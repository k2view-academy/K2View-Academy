# Web Services Input Parameters

The following URL structure is used to call Fabric Web Services:

<pre><code> http://[IP address]:[PORT]/api/[VERSION_NO]/[Web-Service name]?token=[TOKEN NAME]&format=json </pre></code>

Input parameters can be transfered to Fabric Web Services either as:
* Part of the URL path.
* Not part of the URL path.  

### Transfering Input Parameters As Part of the URL Path

To set Web Service input parameters to be part of the Web Service URL, add them to the URL path covered by {$}.

For example: 

<img src="/articles/15_web_services/images/Web-Service-KI-8-1.png" alt="drawing"/> 

In the above screenshot the **i_migrateId** and **i_runMode** input parameters have been set as part of the URL related to the **wsMigrateStatsParams** Web Service which is called as follows:  

http://localhost:3213/api/v1/wsMigrateStatsParams/dca63eda-1553-40b7-b2e8-ae098b3c2c04/S?token=ABC&format=json
Where:
* dca63eda-1553-40b7-b2e8-ae098b3c2c04 is the Migrate ID.
* S is the run mode. 

 

### Transfering Input Parameters That Are Not Part of the URL Path

When input parameters are not set as part of the URL path, transfer them as follows:

<pre><code> http://[IP address]:[PORT]/api/[VERSION_NO]/[Web-Service name]?[param1 name]=[param1 value]&[param2 name]=[param2 value]...&token=[TOKEN NAME]&format=json </code></pre>

For example:

http://localhost:3213/api/v1/wsMigrateStatsParams?i_migrateId=dca63eda-1553-40b7-b2e8-ae098b3c2c04&i_runMode=S&token=ABC&format=json

If an input parameters is correctly set but is not transferred as a part of the URL, the Web Service displays the relevant error code.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/07_deploy_web_services.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/09_swagger.md)


