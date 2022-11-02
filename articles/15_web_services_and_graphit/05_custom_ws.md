# Project Custom APIs

While the built-in out-of-the-box web services are very useful and comprehensive, Fabric provides a very powerful capability of creating custom web services. This enables building new web services, according to the project needs, deploying these web services and using them immediately, without the need to wait for a new product release. This expedites the project's go-to-market strategy and ongoing updates.

In order to build and expose web services, use Graphit, a visual development Editor, which is aimed for building structured documents - with minimal coding needs, like JSON and XML - to be published as web services.

Note that Fabric also provides methods and helpers to build web services as Java functions, but it is recommended to use it only in specific cases, where Graphit does not fit the needs.

Similar to the built-in web services, the custom web services outcome are JSON, XML or CSV formats. 

Fabric also provides utilities and helpers to ease the building of the web services logic and the output. 

In addition to helping implementors to easily craft web services logic and output, Fabric lets you via the Studio UI to:

* Have web services versioning.
* Control and strict the outcome format.
* Control and set web services verb. 
* Control and strict web services permissions.
* Control the web services path.
* Set and use the web services input parameters.
* Test the web services. 



The custom web services are also exposed in a similar pattern to the built-in web services: `/api/[VERSION_NO]/<web-service name>[?<parameters>&format=<FORMAT>]`.





[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/04_built_in_fabric_ws.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/06_custom_ws_create_graphit_ws.md)



