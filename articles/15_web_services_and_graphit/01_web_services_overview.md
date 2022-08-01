# Web Services Overview 

Fabric data is stored internally using Logical Unit modelling. To enable the data to be accessed externally, exposed Web Services are built to query all Fabric data and to present the relevant response to the outside world. 

Other common uses for Fabric Web Services include: 

- Deploying new applications. 
- Exposing data via API instead of by direct DB access. 

- Interacting with other systems based on Fabric data. 

## What Is a Fabric Web Service? 

A **Fabric** **Web** **Service** is a Java function that can be exposed through the Fabric Web Service layer. 

Web Services should be deployed to Fabric separately and can then be invoked either by a client or directly from any web browser. In the Fabric Studio, Web Service functions can be defined and saved to the project file and be deployed to the Fabric server. 

Fabric Web Services apply REST API guidelines which can be fully integrated into the latest Open API (Swagger) platform. 

## What Is a RESTful API? 

**Representational State Transfer** (**REST**) is a software architectural style that defines a set of constraints and standards used for creating Web Services. 

Web Services that conform to the REST architectural style known as RESTful Web Services (RWS), provide interoperability between computer systems on the internet. RESTful Web Services allow requesting systems to access and manipulate textual representations of web resources via a uniform and predefined set of stateless operations.  

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/03_create_a_web_service.md)