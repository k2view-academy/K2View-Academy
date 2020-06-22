# Web Services Input Parameters

In order to call Fabric web services the following URL structure should be used:

http://IP address:PORT/api/[VERSION_NO]/[web-service name]?token=ABC&format=json

There are two ways to pass input parameters to Fabric web services:

### 1. Input parameters set as a part of the URL path

In order to set web service input parameters to be part of the web service URL, input parameters should be added to the URL path covered by {$}.

For example:

<img src="/articles/15_web_services/images/Web-Service-KI-8-1.png" alt="drawing"/> 

In the print screen  above two input parameters were set as a part of the URL part with a relation to wsMigrateStatsParams web services: i_migrateId and i_runMode.

This web service should be called in the following way:  

http://localhost:3213/api/v1/wsMigrateStatsParams/dca63eda-1553-40b7-b2e8-ae098b3c2c04/S?token=ABC&format=json

while dca63eda-1553-40b7-b2e8-ae098b3c2c04 is the migrate id and S is the run mode.

If input parameter is set as a part of the URL path it must be defined as required.

### 2. Input parameters are not part of the URL path

When input parameters are not set as a part of the URL part it should be passed to the web service in the following way:

http://localhost:3213/api/v1/wsMigrateStatsParams?i_migrateId=dca63eda-1553-40b7-b2e8-ae098b3c2c04&i_runMode=S&token=ABC&format=json

If one of the input parameters is set as required but not passed as a part of the URL web service will response with a relevant error code.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/07_deploy_web_services_from_fabric_studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/09_swagger.md)


