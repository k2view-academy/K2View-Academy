# Creating and Deploying a Web Service

The stored Fabric data can be viewed, updated and even deleted using the Web Service layer and its sets of requests and responses: 
[Web Services Overview](/articles/15_web_services/01_web_services_overview.md)

### Creating a Web Service

The following are the basic steps for creating a Web Service and understanding its structure and properties:

-  [Creating a Web Service ](/articles/15_web_services/03_create_a_web_service.md)

-  [Defining Web Service Properties ](/articles/15_web_services/02_web_services_properties.md)

-  [Understanding the Basic Structure of a Web Service Function](/articles/15_web_services/04_web_services_function_basic_structure.md)

-  [Editing Web Service Code](/articles/15_web_services/05_edit_web_service_code.md)

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png) Example

**Creating a Web Service**

Let's create our first simple Web Service which will retrieve basic information on a specific instance of a customer:

1. Go to **Project Tree**, right click **Web Services** > **New Web Service** to display the **Function Editor** window.
2. Select **Newest CustomerLU** as the Database and **CUSTOMER** as the table to generate the code from. 
3. Select the **CUSTOMER_ID**, **SSN**, **FIRST_NAME** and **LAST_NAME** columns.
4. Set the input parameter as **i_id** and data type as **String**
5. Edit the Web Service function  :
   1. Replace the [Instance Id] with the input parameter-**i_id**
   2. Remove any access values from the fetch statement, only the **sql** paramter should be left
6. Name the Web Service, for example **wsGetCustomer**. Make sure that you prefix is 'ws%'.
7. Set the Web Service properties:
   1. Set the prefered **Category**, for example **Training**
   2. Select **Object** from the **Return Type**  dropdown list.
   3. Set the path to: **train/getCustomer**
   4. Add a description of your Web Service.
8. Add comments or a log message in your Web Service.
9. Follow any addtional steps as mentioned in [Create a Web Service ](/articles/15_web_services/03_create_a_web_service.md)
10. Save the Web Service with no errors.

### Deploying a Web Service

Before testing you Web Service, it must be deployed into Fabric, let's understand how:

[Deploy Web Service from Fabric Studio](/articles/15_web_services/07_deploy_web_services_from_fabric_studio.md)

Please deploy the Web Service you have just created into the Fabric server you set for your project. 

Now we can validate that the Web Service has been deployed:

1. Log into Fabric. 
2. Execute: `cql select lut_name,lut_version,properties from k2system_kb_fabric_project.k2_lut_info where lut_name='k2_ws';`
3. You can now verify that your Web Service has been deployed:

`fabric>cql select lut_name,lut_version,properties from k2system_kb_fabric_project.k2_lut_info where lut_name='k2_ws';`



`|lut_name|lut_version  |properties             |`

`+--------+-------------+-----------------------+`

`|k2_ws   |1592814011085|{methods=wsGetCustomer}|`

`|k2_ws   |1591773207364|{methods=}             |`





 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/06_web_services/01_web_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/06_web_services/03_Invoking_a_web_service.md)

