# Create and Deploy a Web Service

The Fabric stored data can be viewed, updated or even deleted using the Web Service Layer with sets of requests and responses:

[Web Services Overview](/articles/15_web_services/01_web_services_overview.md)

### Create a Web Service

Following are the basic steps to create a Web Service and understand its structure and properties:

[Create a Web Service ](/articles/15_web_services/03_create_a_web_service.md)

[Web Service Properties ](/articles/15_web_services/02_web_services_properties.md)

[Web Service Function Basic Structure ](/articles/15_web_services/04_web_services_function_basic_structure.md)

[Edit Web Service Code](/articles/15_web_services/05_edit_web_service_code.md)

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png) Example- Create a Web Service

Let's create our first simple Web Service which will retrieve basic infomration on a specific instance of a customer:

1. Go to **Project Tree**, right click **Web Services** > **New Web Service** to display the **Function Editor** window.
2. Select **Newest CustomerLU** as the Database and **CUSTOMER** as the table to generate the code from .Note that you'll need to select the required columns. Please select CUSTOMER_ID, SSN, FIRST_NAME and LAST_NAME.
3. Set the input parameter as **i_id** and data type as **String**
4. Edit the Web Service function  :
   1. Replace the [Instance Id] with the input parameter-**i_id**
   2. Remove any access values from the fetch statement, only the **sql** paramter should be left
5. Name the Web Service  , for example **wsGetCustomer**. Make sure that you prefix is 'ws%'.
6. Set the Web Service properties:
   1.  the prefered Category, for example **Training**
   2. Select **Object** from the **Return Type**  drop down list.
   3. Set the path to : **train/getCustomer**
   4. Add a description to your Web Service
7. Add comments or log message in your Web Service
8. Follow any addtional steps as mentioned in [Create a Web Service ](/articles/15_web_services/03_create_a_web_service.md)
9. Save the Web Service with no errors

### Deploy a Web Service

Before testing our Web Service, we must deply it into Fabric, let's understand how:

[Deploy Web Service from Fabric Studio](/articles/15_web_services/07_deploy_web_services_from_fabric_studio.md)

Please deploy the Web Service we have just created into the Fabric server you have set in your project 

Now we can validate if the Web Service has been deployed :

1. Please log into Fabric 
2. Execute : `cql select lut_name,lut_version,properties from k2system_kb_fabric_project.k2_lut_info where lut_name='k2_ws';`
3. You can now verify that your Web Service has been deployed :

`fabric>cql select lut_name,lut_version,properties from k2system_kb_fabric_project.k2_lut_info where lut_name='k2_ws';`



`|lut_name|lut_version  |properties             |`
`+--------+-------------+-----------------------+`
`|k2_ws   |1592814011085|{methods=wsGetCustomer}|`
`|k2_ws   |1591773207364|{methods=}             |`





 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/06_web_services/01_web_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/06_web_services/03_invoking_a_web_service.md)

