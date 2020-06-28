# Invoking a Web Service

Now that our first Web Service got created and deployed into Fabric,  we can view the data simply by invoking it from the Fabric Studio or by using any API client. Since  Fabric's Web Services comply with the REST API  guidelines, it is fully integrated with the latest Open API platform - **Swagger**.

### Invoking and Testing Web Services

Let's understand how to invoke and test a Web Service from Fabric Studio using Swagger:

[Swagger](/articles/15_web_services/09_swagger.md)

In order to make that the data being exposed by the Web Services is secured, authenticated and executed by permission, here is how you should set the Web Service Credentails	

[Fabric Credentials Commands](/articles/17_fabric_credentials/02_fabric_credentials_commands.md)

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png) Example- Invoking a Web Service

1. Write click on the Web Service we have just created and select **Invoke Web Service** from context menu
2. Choose  the prefered environent from context menu : The Fabric server name or Local Fabric (debug).
3. Swagger is prompted and the invoked Web Service is expanded. Note that since the defined **Verb** for the Web Service, included all options: GET, POST, PUT and DELETE, All of these options are available, however the GET option is expanded.
4. Click on **Try it out**, set the **i_id** to 82 and execute. What was the response? `"Failed to authorize"` Why? `The web service is requiring a token for authentication` 
5. In order for the request to succeed, permissions and a token should be created:
   1. `create user 'test_user';`
   2. `create role 'admin';`
   3. `grant ALL_WS on * to 'admin';`
   4. `assign role 'admin' to user 'test_user';`
   5. `Create token test;`
   6. `assign role 'admin' to token test;`
6. Let's go back and try to execute the Web Service again, Click the **Lock**, set a valid **Token** and close.
7. set the **i_id** to 82 and execute. What is the Customer's first name? `Anthony`

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Exercise – Invoking a Web Service

Using the training materials and examples covered so far:

1. `Create a new version for **wsGetCustomer** which will enhance the current functionality:`

   1. `Return the Customer details icluding it's contracts and associated subscribers`

   2. `The reponse should returned based on SUBSCRIBER.VIP_STATUS`

   3. `Only the Customer full name should appear as part of the response` 

      **Note**, make sure the WS path is the same for both Web Services

2. `Question: What is the SSN of Customer 1?`

3. `Question: How many subscribers has Customer 1 with VIP_STATUS "Platinum"?`

4. `Question: What is the CONTRACT_DESCRIPTION of the Subscriber with VIP_STATUS "Silver" ?`

5. `Question: Which version of you Web Service will be executed if the version is not set?`

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) Solution - Invoking a Web Service

1. ```java
   wsGetCustomer2 ; path :test/getCustomerDetails 
   
   String sql = "select cust.FIRST_NAME||' '||cust.LAST_NAME CUSTOMER_NAME, cont.CONTRACT_ID,cont.CONTRACT_DESCRIPTION," +
           "sub.SUBSCRIBER_ID,sub.MSISDN,sub.IMSI,sub.SIM,sub.SUBSCRIBER_TYPE,sub.VIP_STATUS " +
   		"from CUSTOMER cust, CONTRACT cont, SUBSCRIBER sub where cont.CONTRACT_ID=sub.SUBSCRIBER_ID and sub.VIP_STATUS=?";
   
   Db.Rows rows = ludb("CustomerLU", i_id).fetch(sql, i_vipStatus);
   
   return rows;
   ```

   

2. `Answer: 5153527856`

3. `Answer: 3`

4. `Answer: 5G tether`

5. `Answer: The Web Version that was the last to be deployed` 





 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/06_web_services/02_create_and_deploy_a_web_service.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/06_web_services/04_response_codes_and_supported_verbs.md)

