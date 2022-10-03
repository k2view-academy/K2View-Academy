# Web Services Overview 

Fabric data is stored internally using Logical Unit modelling. To enable the data to be accessed externally, Fabric exposes set of out-of-the-box web services to query Fabric Logical Unit instances data, as well as provides implementors an easy way to expose other required web services.

These two options are powerful as no product release is required when new web services are needed during the project lifetime.

## What Is a Fabric Web Service? 

A Fabric Web Service is either a Java function that can be exposed through the Fabric Web Service layer or a Graphit file. Graphit is a powerful low-code utility that provides a user-friendly visual interface to design a web-service layout. 

Fabric Web Services are part of the Fabric project files and once project is deployed, the web services are exposed and available to external calls. 

Fabric provides implementors methods to test and debug their web services, either by the built-in debugging capabilities at Graphit or by using Fabric Web Framework API web application (using Swagger Open API interface)

Fabric Web Services apply REST API guidelines. 



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/03_create_a_web_service.md)