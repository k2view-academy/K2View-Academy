

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Invoking a Web Service

1. Right click the **Web Service**, select **Invoke Web Service** and then the **preferred environment** which can be either the Fabric server name or Local Fabric (debug).


   Swagger is prompted and the invoked Web Service is expanded. Note that since the defined **Verb** for the Web Service included all GET, POST, PUT and DELETE options, they are all available. The GET (default) option is expanded.
3. Click **Try it out**, set the **i_id** to **82** and execute. 
  
    What is the response? Failed to authorize. 

    Why? The Web Service requires a token for authentication, unless authenticate product Web Service was called on the same session. 
  
4. For the request to succeed, create the permissions and a token:
   ```
    create user 'test_user'; 
    create role 'admin'; 
    grant ALL_WS on * to 'admin'; 
    assign role 'admin' to user 'test_user';`
    create token test; 
    assign role 'admin' to token test; 
   ```

5. Go back and try to execute the Web Service again. Click the **Lock**, set a valid **Token** and **close**.
7. Set the **i_id** to **82** and execute. 
  
   What is the Customer's first name? Anthony

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png) Exercise – Invoking a Web Service

Using the training materials and examples covered so far:

Create a new version for **wsGetCustomer** to enhance the current functionality:

- [ ]  Return the Customer's details including their contracts and associated subscribers. 

- [ ] The returned reponse should be based on SUBSCRIBER.VIP_STATUS. 

- [ ] Only the Customer's full name should appear as part of the response. 

- [ ] Make sure the WS path is the same for both Web Services.



1. `Question: How many subscribers has Customer 1 with VIP_STATUS "Platinum"?`

2. `Question: What is the CONTRACT_DESCRIPTION of the Subscriber with VIP_STATUS "Silver" ?`

3. `Question: Which version of your Web Service will be executed if the version is not set?` 



------
