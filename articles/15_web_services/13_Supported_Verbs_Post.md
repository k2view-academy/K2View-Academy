# Post Verb

Use POST APIs **to create new subordinate resources**, e.g. a file is subordinate to a directory containing it, or a row is subordinate to a database table. Talking strictly in terms of REST, POST methods are used to create a new resource into the collection of resources.

Ideally, if a resource has been created on the origin server, the response SHOULD be HTTP response code `201 (Created)` and contain an entity which describes the status of the request and refers to the new resource, and a [Location](https://en.wikipedia.org/wiki/HTTP_location) header.

Many times, the action performed by the POST method might not result in a resource that can be identified by a URI. In this case, either HTTP response code **200 (OK)** or **204 (No Content)** is the appropriate response status.

Responses to this method are **not cacheable,** unless the response includes appropriate [Cache-Control](https://en.wikipedia.org/wiki/Web_cache#Cache_control) or [Expires](https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html) header fields.

Please note that POST is **neither safe nor idempotent** and invoking two identical POST requests will result in two different resources containing the same information (except resource ids).

## 1.         Post data into LU table

URL: http://Domain name:PORT/api/[VERSION_NO]/lu/LU Name/iid/token=token name&format=json/xml

| Component   | Description                                    | Mandatory | Example          | Default        |
| ----------- | ---------------------------------------------- | --------- | ---------------- | -------------- |
| Domain name | Domain name                                    | Y         | 10.21.1.69       |                |
| PORT        | PORT                                           | Y         | 3213             |                |
| api         | API                                            | Y         | api              |                |
| VERSION_NO  | Version number                                 | N         | V1.4             | Latest version |
| LU Name     | Logical unit name or COMMON for common  tables | Y         | CUSTOMER  COMMON |                |
| Iid         | Instance Id                                    | Y         | 1                |                |
| token       | Token Name                                     | Y         |                  |                |
| format      | Response format                                | Y         | JSON/XML/YAML    | JSON           |

 

**Examples:**

http://10.21.1.69:3213/api/v1.0/lu/CUSTOMER/1?token=ABC

Insert data into CUSTOMER LU instance id 1, LION table

Request Body

```                     
{"rows" : {"LION" : [{"ID":11, "NAME":"lion11"},{"ID":12, "NAME":"lion12"},{"ID":13, "NAME":"lion13"}]}}
```
 

# 2.      Post data into common table

http://Domain name:PORT/api/[VERSION_NO]/COMMON/[common table name]?token=token name&[format=json/xml]

| **Component**     | **Description**              | **Mandatory** | **Example**   | **Default** |
| ----------------- | ---------------------------- | ------------- | ------------- | ----------- |
| Domain name       | Domain name                  | Y             | 10.21.1.69    |             |
| PORT              | PORT                         | Y             | 3213          |             |
| api               | API                          | Y             | api           |             |
| COMMON            | Specify that scope is common | Y             | COMMON        |             |
| COMMON TABLE NAME | Common table name            | N             | ADDRESSES     |             |
| token             | Token Name                   | Y             |               |             |
| format            | Response format              | Y             | JSON/XML/YAML | JSON        |

**Examples:**

http://10.21.1.69:3213/api/v1.0/COMMON?REF_NAMES&token=ABC&format=json

Insert data into common table REF_NAMES

Request Body
```
{
  "rows": [
    {
      "FIRST_NAME": "XXX",
      "LAST_NAME": "YYY"
    }
  ]
}
```
# 3.   Post custom Web-Service 

http://Domain name:PORT/api/[VERSION_NO]/{customized Web-Service name?token=token name&[format=json/xml]

parameters should be populated on the body in the following structure:
```
{
“parameter name 1”:”value”,
“parameter name 2”:”value”
}
```
| **Component**               | **Description**                        | **Mandatory** | **Example**   | **Default**    |
| --------------------------- | -------------------------------------- | ------------- | ------------- | -------------- |
| Domain name                 | Domain name                            | Y             | 10.21.1.69    |                |
| PORT                        | PORT                                   | Y             | 3213          |                |
| Api                         | API                                    | Y             | api           |                |
| VERSION_NO                  | Version number                         | N             | V1.4          | Latest version |
| Customized Web-Service name | Name of the Web-Service to be executed | Y             | Orders        |                |
| Format                      | Response format                        | Y             | JSON/XML/YAML | JSON           |

# 4.    Request Header

| **Parameter**              | **Mandatory** | **Value**                                                    |
| -------------------------- | ------------- | ------------------------------------------------------------ |
| Token                      | Y             | Token name                                                   |
| Accept                     | Y             | Json/XML/RAW/YAML/CSV                                        |
| Any additional  parameters | N             | Parameter=value&     Can be provided on both  URL and header |

**Examples:**

http://10.21.1.69:3213/api/v1.0/Orders/1/Open?token=ABC&format=json

in the body request put:
```
{
 "i_order_id": "1",
 "i_order_status": "Open"
}
```
Call Web-Service Orders and bring output structure in json format according to input parameters i_order_id = 1 and i_order_status=Open

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/12_Supported_Verbs_Get.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/14_Supported_Verbs_Put.md)


