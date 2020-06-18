# **Web Services Properties** 

Fabric Web Services properties include the definitions, methods, categories, and essential metadata that contribute to the main functionalities and characteristics of the Web Service.  

## **How do** **I** **access** **the** **Web services properties?** 

Go to the **Project Tree**, right click **Web** **Services** and select **New Web Service** from the context menu to display the **Function Manager** window: 

<img src="/articles/15_web_services/images/Web-Service-KI-3-1.png" alt="drawing"/> 

## What are Web Services properties? 

**Web Service Properties** are located on the top right corner of the **Web Service** window. 

<img src="/articles/15_web_services/images/Web-Service-KI-2-1.png" alt="drawing"/> 

The following are the Web Services properties: 

| Property        | |
| --------------- | ------------------------------------------------------------ |
| Name            | The name of the assigned Web Service function. The assigned name should be meaningful and should have a ws% prefix. |
| Category        | The characteristics or class of the Web Service. Note that each category has a separate Java file. |
| Return Type     | The type of Output Return from the Web Service. For example, String, Long or Object. A drop down list is provided, however it is possible to override it with any desirable value that is not part of the drop down list. |
| Version         | The representation of the Web Service’s versioning control status. A version is incorporated into the Web Service’s URL and is used to enable several Web Service versions that are all related to the same service. By default, the version is populated by 1. <br>Versioning Logic:</br> <br>If the version is not part of the URL, the API will respond with the latest version. </br><br>If the version is part of the URL and the API version exists, the API will respond with the specified version. </br><br>If the version is part of the URL and the latest API version is lower, the API will respond with the latest version. </br><br>If the version is part of the URL and is lower than the lowest API version, the API will return the correct error response code. </br><br>If the version is part of the URL and is in-between two API versions, the API will respond with the lower version. </br> |
| Path            | The URL path of the Web Service. The actual name to be called by the external applications when using the web service. The URL path requires permissions and should be unique per Path / Version / Response Format / Request Format. Web Service Permissions: **set on the URL path.** For example, two Web Services sharing the same URL path: The version of wsGetCustomerInfo is set to 1. The version of wsGetCustomerInfoDev is set to 2.  The path is defined as follows: http://<Fabric IP address>:3213/api/v1/getCustomerInfo?token=t1&format=json&customerId=543; When the above URL is run it invokes wsGetCustomerInfo. When version of the URL is modified from v1 to v2, wsGetCustomerInfoDev is invoked. |
| Verb            | The methods supported by the Web Service, as follows: **GET**, get data. **PUT**, create new data based on the data provided. **POST**, update data. **DELETE**, delete data. To select a method, click the drop-down menu and enable the it. Note that at least one method should be selected. |
| Is Raw          | Indicates whether the output structure should be manipulated automatically by Fabric. Values are True or False. Default = False. When True, Fabric brings the data response without parsing or formatting for the representation. |
| Produce         | The Web Service’s output format. Default format: JSON Additional formats: XML and CSV. To select the format/s, click the drop-down menu and enable them. |
| Description     | The Web Service’s description on Swagger.                    |
| Result Metadata | Response example to be presented on Swagger before the Web Service call. Set example-JSON, example-XML and example-CSV. |

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/01_web_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/03_create_a_web_service.md)

