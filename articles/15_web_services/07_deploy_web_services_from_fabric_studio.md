# Deploy Web Services from Fabric Studio

Web services can be deployed to the K2view Fabric server either using the Fabric Studio or using the Fabric Console(offline deployment).

### How Do I Deploy a Web Service Using Fabric Studio?

In the **Project Tree** pane, right click either **Web Services**, **Selected Web Services** or **Categories of the Web Services** to open the **Context** menu.

1. Click Deploy to Server to display the K2View Fabric Servers list.
2. Define the K2View Fabric Server to Deploy to open the following deployment options: 
   1. Deploy all web services.
   2. Deploy select list of categories - All the web services related to the selected categories are deployed.
   3. Deploy selected list of web services.
   4. Deploy stand alone web service.

[Click here for more information on Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md)

### How Do I Deploy Web Service using  Fabric Console?

Run the deploy command using the following syntax:

DEPLOY ‘k2_ws’ WITH JAR <'jar_path'> ZIP_FILE <'zip path'> WS_METHODS ‘ws1’, ‘ws2’;  

Notes: 

- LUT parameter must be k2_ws, which is the name of the key space created for the web services. 
- The WS_METHODS field can be populated using the list of deployed web services. If this field is empty, the Deploy command deploys all web services into Fabric. 

[Click here for more information on offline Deploy](/articles/16_deploy_fabric/03_offline_deploy.md)

### Deplyed Web Service and The Fabric K2SYSTEM Keyspace  

- Each deployment of a web services creates a new record in k2_lut_info (k2system keyspace). 

- Each record maps the deployed web services and has its own version.

- Fabric gets the latest version of each web service.

- The deployment of all web services creates one k2_ws instance of all methods and deletes previous k2_ws instances in the in k2_lut_info table.


**Note**: if a deployed web service is deleted from the implementation, redeploy all web services to delete the previous deployment of the deleted webservice.

'list ws' command return the list of deployed web services and the deployed version + datetime.

## Example 

### Deploy all web services: 

select lut_name, lut_version, properties from k2system_kb_fabric_project.k2_lut_info where lut_name = 'k2_ws’; 

lut_name | lut_version  | properties

----------+---------------+-------------------------------------

k2_ws | 1591772670495 | {'methods': ''}

### Deploy 'wsCustomerInfo’ and ‘wsCustomerInfo2’ web services:

|lut_name|lut_version  |properties               |
+--------+-------------+-------------------------+
|k2_ws   |1591772947986|{methods=wsCustomerInfo2}|
|k2_ws   |1591772937531|{methods=wsCustomerInfo}  |
|k2_ws   |1591772670495|{methods=}                                |

### Deploy again all web services:

|lut_name|lut_version  |properties|
+--------+-------------+----------+
|k2_ws   |1591773207364|{methods=}|

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/06_web_services_code_examples.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/08_web_services_input_parameters.md)
