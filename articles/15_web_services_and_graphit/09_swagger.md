# Swagger

Swagger is a specification and complete framework implementation for describing, producing, consuming, and visualizing RESTful Web Services.

K2View Fabric Studio supports invocation and testing of Web Services using Swagger that is aligned with the OpenAPI Specification (OAS) version 3. 

OAS defines a standard, language-agnostic interface to RESTful APIs which allows both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection. When properly defined, a consumer can understand and interact with the remote service with a minimal amount of implementation logic. An OpenAPI definition can then be used by documentation generation tools to display the API, code generation tools to generate servers and clients in various programming languages, testing tools, and many other use cases.

Web Services can be deployed to the K2view Fabric server either using the Fabric Studio or the Fabric Console (offline deployment).

### Invoking and Testing Web Services Using Fabric Studio

1. Go to: **http://[Fabric server]:3213/api** and then define the **IP Address** of the Fabric server. 
   
   Or, 
   
2. Go to **User Preferences** > **Server Configuration** and do the following:
   
    a. Set the **Web Service Invoke Path Template** to **static/swaggerUI/dist/index.html#/<CATEGORY>/<WS_VERB>_<WS_PATH>**.  

    b. Go the the **Project Tree**, right click the **Web Service** and then click **Invoke Web Service**.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-Swagger-1.png" alt="drawing"/>

  Once the link to Swagger is invoked, you can focus on a given version of the invoked URL path. For example, by adding /v1 to the URL as shown below.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-Swagger-1-1.png" alt="drawing"/>

The relevant invoked Web Service is expanded and points to the correct verb. 
*	If only the GET verb is defined as eligible for the invoked Web Service, it will only display the GET verb  as an option.

*	If all the four verbs are defined as eligible, it will display GET, POST, PUT and DELETE, but zoom into GET by default.

3. Click a **Web Service** in the Swagger user interface to view more details including its parameters, as shown below. If a description of the method or comments have been added to the Input parameters in the K2View Studio, they are displayed in the Swagger user interface, as shown in the following examples (customer_id description):

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-Swagger-2.png" alt="drawing"/>

   If JSON / XML or CSV examples have been defined in the Web Service properties in the Studio, they are displayed in Swagger.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-Swagger-4.png" alt="drawing"/>

4. Click the **Lock**, set a valid **Token** and then click [**Authorize**](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#web-services-authorization).

   Use the **authenticate** Product Web Service and enter the API Key or Username / Password as input in the body request to skip the Authorize as Client step in the remaining Web Services calls. 

5. Click **Try it Out**!. Complete the parameters and then click **Execute** to display a response.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-Swagger-3.png" alt="drawing"/>

6. The default **Media Type** is set to **application/json** and can be modified to another supported eligible format (XML or CSV) and respond accordingly.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-Swagger-5.png" alt="drawing"/>

### Swagger - Supported Web Services Categories
Fabric Web Services are displayed in Swagger in categories and in alphabetical order.


   * Common - all built-in product Web Services that bring reference table metadata and data.
   
   * Fabric - pre-built API to run Fabric commands, Data Catalog requests, Authentication and Heart bits/Statuses queries   
   
   * Graphit - all Graphit files defined as a part of the project implementations.
   
   * LU - all  built-in LU Web Services that bring LU metadata, data and data modifications, such as Delete instance.
   
   * Project defined categories for Web Services.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/08_web_services_input_parameters.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/10_annotations.md)


