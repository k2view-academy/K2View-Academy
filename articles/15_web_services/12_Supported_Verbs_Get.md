# Get Verb



Use GET requests **to retrieve resource representation/information only**, and not to modify it in any way. As GET requests do not change the state of the resource, these are said to be **safe methods**. Additionally, GET APIs should be **idempotent**, which means that making multiple identical requests must produce the same result every time until another API (POST or PUT) has changed the state of the resource on the server.

If the Request-URI refers to a data-producing process, it is the produced data which shall be returned as the entity in the response and not the source text of the process, unless that text happens to be the output of the process.

For any given HTTP GET API, if the resource is found on the server, it must return HTTP response code 200 (OK), along with response body, which is either XML or JSON content (due to their platform independent nature).

In case resource is NOT found on server, it must return HTTP response code 404 (NOT FOUND). Similarly, if it is determined that GET request itself is not correctly formed, server will return HTTP response code 400 (BAD REQUEST).

New REST API is fully integrated with open API (Swagger). We can know the body response structure before really calling to the API.

 

# 1.         Get LU data

http://IP address:PORT/api/[VERSION_NO]/lu/LU Name/iid/[[TABLE_NAME]?fields=VALUE1,VALUE2&where=WHERE STATEMENT]] query=QUERY STATMENT]&token=token name&[format=json/xml]&SET={mode,value}

 

| **Component** | **Description**                                 | **Mandatory** | **Example**                                                  | **Default**                                    |
| ------------- | ----------------------------------------------- | ------------- | ------------------------------------------------------------ | ---------------------------------------------- |
| Domain name   | Domain name                                     | Y             | localhost                                                    |                                                |
| PORT          | PORT                                            | Y             | 3213                                                         |                                                |
| api           | API                                             | Y             | api                                                          |                                                |
| VERSION_NO    | Version number                                  | N             | V1.4                                                         | Latest version                                 |
| lu            | Lu                                              | Y             | lu                                                           |                                                |
| LU Name       | Logical unit name                               | Y             | CUSTOMER                                                     |                                                |
| Iid           | Instance Id                                     | Y             | 1                                                            |                                                |
| TABLE_NAME    | Table Name                                      | N             | PAYMENT                                                      | All tables                                     |
| Fields        | Field name                                      | N             | fields=CUSTOMER_ID, INVOICE_ID                               | Multiple fields supported                      |
| where         | Where statement for the selected table          | N             | where=NAME=’MOSHE’ OR ADDRESS=’TEL AVIV’                     | Can be populated in case FIELDS were populated |
| QUERY         | Where statement for cross tables on the same LU | N             | QUERY=SELECT NAME from ADDRESS_DATA A,ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID  =B.ADDRESS_ID |                                                |
| token         | Token Name                                      | Y             |                                                              |                                                |
| format        | Response format                                 | Y             | JSON/XML/YAML                                                | JSON                                           |
| Set           | Sync mode                                       | N             | SET=sync,off  SET=sync,on  SET=sync,force                    | Sync policy on the session                     |
|               | Environment                                     | N             | SET=ENVIRONMENT,’ENV1’                                       | _dev                                           |
|               | Sync_timeout                                    | N             | SET=SYNC_TIMEOUT,10                                          | Set on config.ini                              |
|               | instance_ttl                                    | N             | SET=instance_ttl,10                                          |                                                |
|               | Environment variable                            | N             | SET=A,4                                                      |                                                |

**Examples:**

·      http://localhost:3213/api/v1.0/lu/CUSTOMER/1?token=ABC

Bring all data for CUSTOMER LU instance id 1

Response Body: response body supports streaming solution

·      http://localhost:3213/api/v1.0/lu/CUSOMTER/1/ALLERGIES?token=ABC

Bring data for CUSTOMER LU instance id 1, table ALLERGIES

·      http://localhost:3213/api/v1.0/lu/CUSTOMER/1/PAYMENT?fields=PAYMENT_ID,PAYMENT_DATE&where=PAYMENT_STATUS!=’CLOSED’&token=ABC

Bring data for CUSTOMER LU instance id 1, table PAYMENT, fields PAYMENT_ID, PAYMENT_DATE where payments are not closed.

·      http://localhost:3213/api/v1.0/lu/CUSTOMER/1?query=SELECT FIRST_NAME FROM ADDRESS_DATA A,ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID =B.ADDRESS_ID AND B.ADDRESS_TYPE=’P’&token=ABC

Bring data for CUSTOMER LU instance id 1, table ADDRESS_DATA field FIRST_NAME where name type is private.

·      http://localhost:3213/api/v1.0/lu/CUSTOMER/1?query=SELECT FIRST_NAME FROM ADDRESS_DATA A,ADDRESS_NAME_LINK B WHERE A.ADDRESS_ID =B.ADDRESS_ID AND B.ADDRESS_TYPE=’P’&token=ABC&set=sync,force&SET=ENVIRONMENT,ENV1&set=GLOBAL_LION,10

Bring data for CUSTOMER LU instance id 1, table ADDRESS_DATA field FIRST_NAME where name type is private. Make sure sync mode if force and it run on ENV1and set GLOBAL_LION to 10

# 2.         Get LU schema (meta data)

http://IP address:PORT/api/[VERSION_NO]/lu/LU Name?token=token name&[format=json/xml/yaml]

| Component   | Description       | Mandatory | Example       | Default        |
| ----------- | ----------------- | --------- | ------------- | -------------- |
| Domain name | Domain name       | Y         | localhost     |                |
| PORT        | PORT              | Y         | 3213          |                |
| Api         | API               | Y         | api           |                |
| VERSION_NO  | Version number    | N         | V1.4          | Latest version |
| Lu          | lu                | Y         | lu            |                |
| LU Name     | Logical unit name | Y         | CUSTOMER      |                |
| Token       | Token Name        | Y         |               |                |
| Format      | Response format   | Y         | JSON/XML/YAML | JSON           |

**Examples:**

·      http://localhost:3213/api/v1.0/lu/CUSTOMER?token=ABC

Bring meta data for CUSTOMER LU

   

# 3.         Get common schema (meta data)

http://IP address:PORT/api/[VERSION_NO]/COMMON/[table name]?token=token name&[format=json/xml/yaml]

| Component   | Description     | Mandatory | Example       | Default        |
| ----------- | --------------- | --------- | ------------- | -------------- |
| Domain name | Domain name     | Y         | localhost     |                |
| PORT        | PORT            | Y         | 3213          |                |
| Api         | API             | Y         | api           |                |
| VERSION_NO  | Version number  | N         | V1.4          | Latest version |
| COMMON      | Common tables   | Y         | COMMON        |                |
| Token       | Token Name      | Y         |               |                |
| Format      | Response format | Y         | JSON/XML/YAML | JSON           |

**Examples:**

·      http://localhost:3213/api/v1.0/COMMON?token=ABC

Bring meta data for all COMMON tables

# 4.         Get common table

http://localhost:PORT/api/[VERSION_NO]/common/[COMMON TABLE NAME?fields=list of fields separated by comma&where=WHERE STATEMENT&token=token name&[format=json/xml]

| Component         | Description                             | Mandatory | Example                 | Default                                                      |
| ----------------- | --------------------------------------- | --------- | ----------------------- | ------------------------------------------------------------ |
| Domain name       | Domain name                             | Y         | localhost               |                                                              |
| PORT              | PORT                                    | Y         | 3213                    |                                                              |
| Api               | API                                     | Y         | api                     |                                                              |
| COMMON            | Specify that scope is common            | Y         | COMMON                  |                                                              |
| COMMON TABLE NAME | Common table name                       | N         | ADDRESSES               |                                                              |
| where             | Where statement for the common table    | N         | WHERE={CITY=’TEL AVIV’} | Can be populated in case of COMMON TABLE  NAME was populated |
| fields            | List of fields to be returned as output | N         | fields=CITY,ADDRESS     |                                                              |
| Token             | Token Name                              | Y         |                         |                                                              |
| Format            | Response format                         | Y         | JSON/XML/YAML           | JSON                                                         |

 **Examples:**

·      http://localhost:3213/api/v1.0/COMMON/CITIES?token=ABC

Bring all data from CITIES common table

·      [http://localhost:3213/api/v1.0/COMMON/ADDRESSES?fields=CTIY_NAME&where=CITY=’TEL](http://localhost:3213/api/v1.0/COMMON/ADDRESSES?fields=CTIY_NAME&where=CITY='TEL) AVIV’&token=ABC

Bring city_name from ADDRESSES common table where city is ’TEL AVIV’

# 5.         Get based on graphit file

http://Domain name:PORT/api/[VERSION_NO]/graphit file name/ PARAM1VALUE1/PARAM2 VALUE2?token=ABC[&format=json/xml/yaml]

| Component         | Description                                              | Mandatory | Example                                                      | Default                                                      |
| ----------------- | -------------------------------------------------------- | --------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| Domain name       | Domain name                                              | Y         | localhost                                                    |                                                              |
| PORT              | PORT                                                     | Y         | 3213                                                         |                                                              |
| Api               | API                                                      | Y         | api                                                          |                                                              |
| VERSION_NO        | Version number                                           | N         | V1.4                                                         | 1.0                                                          |
| Token             | Token Name                                               | Y         |                                                              |                                                              |
| Format            | Response format                                          | Y         | JSON/XML/YAML                                                | JSON                                                         |
| Graphit file name | Name of graphit file                                     | Y         | file name should include the version  number customer_query.1.4.graphit |                                                              |
| PARAM1…N          | Input parameter 1..N name and value to  the graphit file | N         | iid=1&name=moshe                                             | Supported only on post verb, should be  part of request body {parameter name:parameter value} |

**Examples:**

[http://localhost:3213/api/v1.3/graphit/customer_query/1?token=ABC

Run Web-Service according to customer_query.1.3 graphit file, send as input 2 parameters (id =1 and name = moshe) and response body should input json structure defined on the graphit file

Should call graphit: customer_query.1.3.graphit

# 6.         Get custom Web-Service

http://Domain name:PORT/api/[VERSION_NO]/{customized Web-Service name}/PARAM1 VALUE/PARAM2 VALUE?token=token name&[format=json/xml]

| Component                   | Description                            | Mandatory | Example                                                      | Default        |
| --------------------------- | -------------------------------------- | --------- | ------------------------------------------------------------ | -------------- |
| Domain name                 | Domain name                            | Y         | localhost                                                    |                |
| PORT                        | PORT                                   | Y         | 3213                                                         |                |
| Api                         | API                                    | Y         | api                                                          |                |
| VERSION_NO                  | Version number                         | N         | V1.4                                                         | Latest version |
| Customized Web-Service name | Name of the Web-Service to be executed | Y         | Orders                                                       |                |
| PARAM1…N                    | Web-Service input parameters           | N         | /1/3  Assuming two parameters as input  i_order_id and order_status it will pass 1 to i_order_id and 3 to  i_order_status |                |
| Format                      | Response format                        | Y         | JSON/XML/YAML                                                | JSON           |

**Examples:**

·      http://localhost:3213/api/v1.0/Orders/1/Open?token=ABC&format=json

Call Web-Service Orders and bring output structure in json format according to input parameters i_order_id = 1 and i_order_status=Open

# 7.         Request Header

| Parameter                 | Mandatory | Value                                                       |
| ------------------------- | --------- | ----------------------------------------------------------- |
| Token                     | Y         | Token name                                                  |
| Accept                    | Y         | Json/XML/RAW/YAML/CSV                                       |
| Any additional parameters | N         | Parameter=value&     Can be provided on both URL and header |

 

# 8.         Response Body in failure

If case of failure, the error description structure is returned according to RFC 7807 guidelines with the required details.

[![Previous](/articles/images/Previous.png)](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">


