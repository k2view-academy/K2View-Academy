# Delete Verb

Use a DELETE API to delete resources that are identified by the Request-URI.

A successful response to DELETE requests SHOULD be:

- **HTTP 200 OK**, if the response includes an entity describing the status.  
- **HTTP 202 Accepted**, if the action has been queued.  
- **HTTP 204 No Content**, if the action has been performed but the response does not include an entity. 

DELETE operations are **idempotent**. When a resouce is deleted, it is removed from the collection of resources and remains so, even when the DELETE API is called on the same resource repeatedly. The **404 NOT FOUND** response code is returned when the DELETE API is called on a deleted resource for the second time. Some may argue that this makes the DELETE method non-idempotent. It is a matter of discussion and personal opinion.

If the request passes through a cache and the Request-URI identifies one or more currently cached entities, these entries SHOULD be handled as stale. Responses to this method are **not cacheable**.

## DELETE LUI

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/&lt;LU Name&gt;/&lt;iid&gt;&amp;token=&lt;TOKEN NAME&gt;&amp;[format=json/xml]</p></code>

| **Component** | **Description**                               | **Mandatory** | **Example**     | **Default**    |
| ------------- | --------------------------------------------- | ------------- | --------------- | -------------- |
| Domain name   | Domain name                                   | Y             | localhost       |                |
| PORT          | PORT                                          | Y             | 3213            |                |
| Api           | API                                           | Y             | api             |                |
| VERSION_NO    | Version number                                | N             | V1.4            | Latest version |
| LU Name       | Logical unit name or COMMON for common tables | Y             | PATIENT  COMMON |                |
| Iid           | Instance Id                                   | Y             | 1               |                |
| Token         | Token Name                                    | Y             |                 |                |
| Format        | Response format                               | Y             | JSON/XML/YAML   | JSON           |

**Example:**

- `http://localhost:3213/api/v1.0/lu/PATIENT/1?token=ABC`

  Delete LUI 1 from PATIENT LU

  Request Body: null

  Response Body: null

  Response code: 200 if delete ended successfully



## DELETE Data From LU Table

<p><code>http://&lt;Domain name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/lu/&lt;LU Name&gt;/&lt;iid&gt;/&lt;TABLE_NAME&gt;&amp;token=&lt;TOKEN NAME&gt;&amp;[format=json/xml]</code></p>

| **Component** | **Description**                               | **Mandatory** | **Example**     | **Default**    |
| ------------- | --------------------------------------------- | ------------- | --------------- | -------------- |
| Domain name   | Domain name                                   | Y             | localhost       |                |
| PORT          | PORT                                          | Y             | 3213            |                |
| Api           | API                                           | Y             | api             |                |
| VERSION_NO    | Version number                                | N             | V1.4            | Latest version |
| LU name       | Logical unit name or COMMON for common tables | Y             | PATIENT  COMMON |                |
| Iid           | Instance Id                                   | Y             | 1               |                |
| TABLE_NAME    | Table name for data deletion                  | Y             | PAYMENT         |                |
| WHERE CLAUSE  | Where clause statement                        | Y             | INVOICE_ID=1    |                |
| token         | Token Name                                    | Y             |                 |                |
| format        | Response format                               | Y             | JSON/XML/YAML   | JSON           |

**Example:**

- `http://localhost:3213/api/v1.0/lu/PATIENT/1/INVOICE?WHERE=CUSTOMER=1 or NAME=’LION’&token=ABC`

  Delete data from PATIENT LU instance id 1, INVOICE table by where clause

  Request Body: null

  Response Body: null

  Response code: 200 if delete ended successfully

##  Delete Data From Common Table

<p><code>http://&lt;Domain Name&gt;:&lt;PORT&gt;/api/[VERSION_NO]/COMMON/&lt;COMMON TABLE NAME&gt;?&lt;WHERE CLAUSE&gt;&amp;token=&lt;TOKEN NAME&gt;&amp;[format=json/xml]</p></code>

| **Component**     | **Description**              | **Mandatory** | **Example**   | **Default** |
| ----------------- | ---------------------------- | ------------- | ------------- | ----------- |
| Domain name       | Domain name                  | Y             | localhost     |             |
| PORT              | PORT                         | Y             | 3213          |             |
| api               | API                          | Y             | api           |             |
| COMMON            | Specify that scope is common | Y             | COMMON        |             |
| COMMON TABLE NAME | Common table name            | N             | ADDRESSES     |             |
| WHERE CLAUSE      | Where clause statement       | Y             | CITY_ID=1     |             |
| token             | Token Name                   | Y             |               |             |
| format            | Response format              | Y             | JSON/XML/YAML | JSON        |

**Example:**

- `http://localhost:3213/api/v1.0/COMMON?CITIES&WHERE CITY_ID=1&token=ABC`

  Delete data from CITIES common table where city_id = 1

  Request Body: null

  Response Body: null

  Response code: 200 if delete ended successfully


## Delete Custom Web Service 

Delete works like GET. All parameters should be populated on the URL or header.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/14_Supported_Verbs_Put.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/16_rest_api_additions.md)


