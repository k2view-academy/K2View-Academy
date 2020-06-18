## Knowledge Item Title: the basic structure of a Web service function

Fabric Studio enables you to automatically generate Java code that holds the basic components of a Web Service function. 

Click for more information about How[[MAC1\]](#_msocom_1) to Create a Web Service[[MAC2\]](#_msocom_2) .[[MAC3\]](#_msocom_3) 

## Web service function: basic structural components 

The following table describes the basic structural components of a Web Service function:

| Component          | Description                                                  |
| ------------------ | ------------------------------------------------------------ |
| String Declaration | String declaration of the SQL  statement’s structure which includes the Column Name, Table Name, Join with  Other Tables and other SQL syntax elements.   For example, to generate an SQL  statement to retrieve the CUSTOMER ID, SSN and FIRST_NAME from the CUSTOMER  table:  String sql = "SELECT CUSTOMER_ID, SSN,  FIRST_NAME FROM CUSTOMER"; |
| Fetch Statement    | Fabric    Get a specific instance and fetch the  data from Fabric using the declared SQL statement.   For example, to get the data for a customer  instance from Fabric (LUDB):  Db.Rows rows = ludb("Customer",  ID).fetch(sql);      “Customer” – Fabric LUT name   ID  - Instance id value, received as a Web Service input parameter  DB Interfaces   Get the required data from another DB  interface. The SQL statement should be structured with binding parameter/s that  are represented by a question mark. The parameter/s should be added to the Fetch method  in the same order they are defined in the SQL statement.   For example, to fetch the CUSTOMER  data from the CRM_DB based on the CUSTOMER_ID as an Input parameter.  String sql = "SELECT CUSTOMER_ID,  CUSTOMER_TYPE, CREATION_DATE FROM CUSTOMER WHERE CUSTOMER_ID = ? “;  Db.Rows rows =  db("CRM_DB").fetch(sql, custId);  Note that during runtime, the question  mark is replaced by the Web Service’s Input parameter value as the **custID**. |
| Return Statement   | Terminates the execution of the Web  Service  function.     return rs; |

------



 [[MAC1\]](#_msoanchor_1)



Add link to KI 13.3 [[MAC2\]](#_msoanchor_2)



 [[MAC3\]](#_msoanchor_3)

[![Previous](/articles/images/Previous.png)](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">


