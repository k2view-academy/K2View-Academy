# Delete Verb

As the name applies, DELETE APIs are used **to delete resources** (identified by the Request-URI).

A successful response of DELETE requests SHOULD be HTTP response `code 200 (OK),` if the response includes an entity describing the status, `202 (Accepted),` if the action has been queued, or `204 (No Content)` if the action has been performed but the response does not include an entity.

DELETE operations are **idempotent**. If you DELETE a resource, it is removed from the collection of resources. Repeatedly calling DELETE API on that resource will not change the outcome, however, calling DELETE on a resource a second time will return a 404 (NOT FOUND) because it was already removed. Some may argue that it makes DELETE method non-idempotent. It is a matter of discussion and personal opinion.

If the request passes through a cache and the Request-URI identifies one or more currently cached entities, those entries SHOULD be treated as stale. Responses to this method are **not cacheable**.

# 1.         DELETE LUI

URL: http://IP address:PORT/api/[VERSION_NO]/LU Name/iid&token=token name&[format=json/xml]

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

**Examples:**

·      http://localhost:3213/api/v1.0/lu/PATIENT/1?token=ABC

Delete LUI 1 from PATIENT LU

Request Body: null

Response Body: null

Response code: 200 if delete ended successfully

# 2.         DELETE Data from LU Table

URL: http://IP address:PORT/api/[VERSION_NO]/lu/LU Name/iid/TABLE_NAME&token=token name&[format=json/xml]

| **Component** | **Description**                               | **Mandatory** | **Example**     | **Default**    |
| ------------- | --------------------------------------------- | ------------- | --------------- | -------------- |
| Domain name   | Domain name                                   | Y             | localhost       |                |
| PORT          | PORT                                          | Y             | 3213            |                |
| Api           | API                                           | Y             | api             |                |
| VERSION_NO    | Version number                                | N             | V1.4            | Latest version |
| LU Name       | Logical unit name or COMMON for common tables | Y             | PATIENT  COMMON |                |
| Iid           | Instance Id                                   | Y             | 1               |                |
| TABLE_NAME    | Table name for data deletion                  | Y             | PAYMENT         |                |
| WHERE CLAUSE  | Where clause statement                        | Y             | INVOICE_ID=1    |                |
| token         | Token Name                                    | Y             |                 |                |
| format        | Response format                               | Y             | JSON/XML/YAML   | JSON           |

**Examples:**

·      http://localhost:3213/api/v1.0/lu/PATIENT/1/INVOICE?WHERE=CUSTOMER=1 or NAME=’LION’&token=ABC

Delete data from PATIENT LU instance id 1, INVOICE table by where clause

Request Body: null

Response Body: null

Response code: 200 if delete ended successfully

# 3.         Delete data from common table

http://IP address:PORT/api/[VERSION_NO]/COMMON/COMMON TABLE NAME?where city_id=1&token=token name&[format=json/xml]

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

**Examples:**

·      http://localhost:3213/api/v1.0/COMMON?CITIES&WHERE CITY_ID=1&token=ABC

delete data from CITIES common table where city_id = 1

Request Body: null

Response Body: null

Response code: 200 if delete ended successfully

 

# 4.         Delete custom Web-Service 

Delete works like GET. All parameters should be populated on the URL or header.

[![Previous](/articles/images/Previous.png)](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">


