# Web Services Input Parameters

The following URL structure is used to call Fabric Web Services:

http://IP address:PORT/api/[VERSION_NO]/[web-service name]?token=ABC&format=json

Input parameters can be transfered to Fabric Web Services either as:
* Part of the URL path.
* Not part of the URL path.  

### Transfering Input Parameters as Part of the URL Path

To set Web Service Input parameters to be part of the Web Service URL, add them to the URL path covered by {$}.

For example: 

<img src="/articles/15_web_services/images/Web-Service-KI-8-1.png" alt="drawing"/> 

In the above screenshot the **i_migrateId** and **i_runMode** Input parameters have been set as a part of the URL related to the wsMigrateStatsParams Web Service which is called as follows:  

http://localhost:3213/api/v1/wsMigrateStatsParams/dca63eda-1553-40b7-b2e8-ae098b3c2c04/S?token=ABC&format=json
Where:
* dca63eda-1553-40b7-b2e8-ae098b3c2c04 is the Migrate ID.
* S is the run mode. 

 

### 2. Transfering Input Parameters as Not Part of the URL Path

When Input parameters are not set as a part of the URL path, transfer them to the Web Service as follows:

http://localhost:3213/api/v1/wsMigrateStatsParams?i_migrateId=dca63eda-1553-40b7-b2e8-ae098b3c2c04&i_runMode=S&token=ABC&format=json

If an input parameters is correctly defined but is not transferred as a part of the URL, the Web Service displays the relevant error code.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/07_deploy_web_services_from_fabric_studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/09_swagger.md)


