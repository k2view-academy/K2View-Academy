# Swagger

Swagger is a specification and complete framework implementation for describing, producing, consuming, and visualizing RESTful web services.

K2View Fabric Studio supports invocation and testing of web services using Swagger that is aligned with OpenAPI  Specification version 3. 

The OpenAPI Specification (OAS) defines a standard, language-agnostic interface to RESTful APIs which allows both humans and computers to discover and understand the capabilities of the service without access to source code, documentation, or through network traffic inspection. When properly defined, a consumer can understand and interact with the remote service with a minimal amount of implementation logic.

An OpenAPI definition can then be used by documentation generation tools to display the API, code generation tools to generate servers and clients in various programming languages, testing tools, and many other use cases.

Web services can be deployed to the K2view Fabric server either using the Fabric Studio or using the Fabric Console (offline deployment).

### Invoking and Testing Web Services using Fabric Studio

Following steps should be followed 

1.  Navigate to the following URL and specify the IP address of your Fabric server: http://<Fabric server>:3213/api or set Web service invoke path template in your **User preferences** - Server configuration to static/swaggerUI/dist/index.html#/<CATEGORY>/<WS_VERB>_<WS_PATH> and then invoke web service (right click the **Web Service** name in the Project Tree pane and click the **Invoke Web Service** option), the follow screen will show up

   <img src="/articles/15_web_services/images/Web-Service-Swagger-1.png" alt="drawing"/>

   Once the link to the Swagger will be invoked, it is possible to focus on a given version of the invoked URL path, by adding for exmaple /v1 to the URL as shown below

   <img src="/articles/15_web_services/images/Web-Service-Swagger-1-1.png" alt="drawing"/>

   The relevant invoked Web-Service will be expended pointing to the correct verb, if only GET verb was defined as illegible for the invoked Web-Service, it will show only verb GET as an option, if all the four verbs were defined as illegible, it will show GET, POST, PUT and DELETE, but it will zoom into GET as a default.

   

2. Click a web service in the Swagger user interface to view more details about it, including its parameters, as shown below. If a description of the method or comments were added in K2View Studio, they display in the Swagger user interface, as shown in the examples below

   <img src="/articles/15_web_services/images/Web-Service-Swagger-2.png" alt="drawing"/>

   If JSON/XML or CSV examples were defined in the Web-Service properties in Studio, it will be shown accordingly in the swagger

   <img src="/articles/15_web_services/images/Web-Service-Swagger-4.png" alt="drawing"/>

3. Click on the lock and set a valid Token and click on Authorize button afterwards.

4. Click the **Try it out**! button. Fill in the parameters and then click the Execute button to display a response.

   <img src="/articles/15_web_services/images/Web-Service-Swagger-3.png" alt="drawing"/>

5. The default Media Type is set to application/json, however it can be changed to other illegible supported format (xml or csv) and response accordingly

   <img src="/articles/15_web_services/images/Web-Service-Swagger-5.png" alt="drawing"/>

### Swagger - supported Web-Services categories

Fabric Web-Services are shown in swagger in alphabetic order divided by categories.

   * Common - All the product built-in Web-Services to bring reference tables meta data and data.
   
   * Graphit - All the graphit files defined as a part of the project implementations.
   
   * Lu - All the LU built-in Web-Services to bring LU meta data and data and data modification, such as Delete instance.
   
   * Project defined categories for Web-Services.
   
   [![Previous](/articles/images/Previous.png)](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">
 

