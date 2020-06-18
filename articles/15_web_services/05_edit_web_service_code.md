# Editing web service code

The code in an automatically generated Web Service function provides basic Select and Fetch templates for data retrieval. However, there is frequently a need to enhance a function’s code in order to perform a specific functionality. 

Click for more information on How to Create a Web Service.

### How do I edit the code of a function in a Web Service?

It is recommended to edit the code in a Web Service function using the IntelliJ Java[[MAC1\]](#_msocom_1) Editor which offers a number of advantages like:

·     Smart code completion.

·     Inspection and quick fixes.

·     Functions.

·     Navigation and Search options.

Note that IntelliJ is not part of the Fabric Studio Installation Package and **must be** installed. Once installed, it is integrated into the Fabric Studio and can be invoked by right clicking the Web Service working area and selecting it or by pressing **CTTL+I** on your keyboard.

A Web Service can also be edited directly in its main working area.

### What should be edited?

The following items should be edited after they are automatically generated:

| Item                              | Description                                                  |
| --------------------------------- | ------------------------------------------------------------ |
| Fetch Data Statement              | LUDB Fetch Statement  Replace the <instanceID> with the  Web Service Input parameter declared as the Fabric Logical Unit instance identifier.   For example, in the wsCustomerInfo Web Service the input  parameter = “ID”:   Db.Rows rows =  ludb("Customer", <instanceID>).fetch(sql, <val1>,  <val2>, ...);  Db.Rows rows =  ludb("Customer", ID).fetch(sql, <val1>, <val2>, ...);   LUDB or DB Interface Fetch  Statements  Either populate SQL query parameters  in their place holders (<val1>, <val2>,…) or delete them if they  are not required. These steps are mandatory for a clean Web Service code  compilation.   For example:  Db.Rows rows = ludb("CUSTOMER", ID).fetch(sql, “XX”, 123);  Db.Rows rows = ludb("CUSTOMER", ID).fetch(sql); |
| SQL Statement Enhancement Options | ·  Add a WHERE clause.  ·  JOIN additional tables to the query.   ·  Add advanced features to the SELECT statement.  For example, assigning identifiers to tables and specifying which table  columns to retrieve. |
| Java Code Enhancement             | Web Service code can also apply  transformation rules via the functions[[MAC2\]](#_msocom_2) , translations[[MAC3\]](#_msocom_3) or Globals[[MAC4\]](#_msocom_4) defined in the project.    Note that any Fabric Server runtime command[[MAC5\]](#_msocom_5) can be used within the Web  Service using the Execute function.   For example, the Get command on the  Customer LUT for a specific instance:  Fabric().execute(“ get Customer.?”,ID); |

 
[![Previous](/articles/images/Previous.png)](/articles/15_web_services/04_web_services_function_basic_structure.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/06_web_services_code_examples.md)


