# Creating a Web Service

An exposed Fabric Web Service enables you to access specific data attributes stored in Fabric using Input parameter/s and the relevant structured response like JSON, XML or CSV. The Fabric Studio supports defining a Web Service function, saving it to the Project file and deploying it to the Fabric Server. A Web Service is a Java function.

### How do I Create a New Web Service?

The following steps discuss how a new Web Service is created and include options for generating code parts automatically:

1. Go to **Project Tree**, right click **Web Services** > **New Web Service** to display the **Function Editor** window.

<img src="/articles/15_web_services/images/Web-Service-KI-3-1.png" alt="drawing"/>     

2. Click either **Objects** or **Database** tab (left pane) and then either > **DB Interface**, **Newest Reference File**, or **Newest** **LU File**.

3. Select the preferred **DB Connection**. To access Fabric, select the Newest  [**LU Name**](/articles/03_logical_units/01_LU_overview.md)

Note that for the **Newest file** of a Logical Unit to be part of the DB Connections list, you must execute at least one instance of the Logical Unit using the [**Data Viewer**](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md) .

<img src="/articles/15_web_services/images/Web-Service-KI-3-2.png" alt="drawing"/>  

4. Click the **DB File** icon to display the **Logical Unit Tables** list.

 <img src="/articles/15_web_services/images/Web-Service-KI-3-3.png" alt="drawing"/>  

5. Click the **table** that retrieves the data, check the columns list and right click the **column** that is the **Input** parameter for data retrieval. 

6. Click **Add Selected to Input Parameters**.

 <img src="/articles/15_web_services/images/Web-Service-KI-3-4.png" alt="drawing"/>  

Note that the Column Name, Data Type, Mandatory and Comment are displayed in the **Input Parameters** list in lower right pane.

7. Select the **Column/s** to be retrieved or press the **CTRL** **key** to select multiple columns. Right click and select **Generate code** to automatically create the **Select Statement** and **Basic Code Structure**. You can also choose to write your own code.  

The generated code is displayed in the Java Editor window and includes the basic structure of the call to the interface or LUDB and the Fetch method.

 <img src="/articles/15_web_services/images/Web-Service-KI-3-5.png" alt="drawing"/>  

8. Once the code is generated, edit the function based on the logic to be applied. For example:

Db.Rows rows = ludb("Customer", <instanceID>).fetch(sql, <val1>, <val2>, ...);

Replace the **<instanceID> place holder** with the input parameter **CUSTOMER_ID**

​    Db.Rows rows = ludb("Customer", CUSTOMER_ID).fetch(sql);

9. Add the [**Web Service Properties**](/articles/15_web_services/02_web_services_proprties.md) . in the upper right corner pane:

a.    **Name**: The Web Service preferred name. Make sure to use the prefix ws% and assign the Web Service a meaningful functional name. For example, **wsGetCustomerInfo.**

b.   **Category**: select the Web Service **Category**.

c.    **Return Type**: select the Web Service **Return Type**.

d.   **Version**: Fabric’s Web Service **Support Versioning**. Default = 1.

e.    **Path:** set the Web Service’s **URL path**.

f.    **Verb**: set the **Verb** to be supported (supporting: GET, POST, PUT and DELETE).

g.    **Is Raw:** set the **Is Raw field** to **True** or **False**. Default = False. 

h.   **Produce:** set the Support Output **Format**, Json (default), XML and CSV.

i.     **Description:** set the Web Service **Description** to be displayed in Swagger.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/02_web_services_properties.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/04_web_services_function_basic_structure.md)


