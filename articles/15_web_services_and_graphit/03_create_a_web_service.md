# Creating a Web Service

An exposed Fabric Web Service enables you to access specific data attributes stored in Fabric using input parameter/s and the relevant structured response like JSON, XML or CSV. 

Fabric Studio supports defining a Web Service function, saving it to the project file and deploying it to the Fabric Server. A Web Service is a Java function.

### How Do I Create a New Web Service?

The following steps discuss how a new Web Service is created and include options for generating parts of code automatically:

<studio>

1. Go to **Project Tree**, right click **Web Services** > **New Web Service** to display the **Function Editor** window.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-1.png" alt="drawing"/>
   
   
   
2. Click either the **Objects** or **Database** tab (left pane) and then either > **DB Interface**, **Newest Reference File**, or **Newest** **LU File**.

3. Select the preferred **DB Connection**. To access Fabric, select **Newest**  [**LU Name**](/articles/03_logical_units/01_LU_overview.md). Note that for the newest file of an LU to be part of the DB Connections list, at least one LUI must be executed using the [**Data Viewer**](/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md) .

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-2.png"/>  

4. Click the **DB File** icon to display the **Logical Unit Tables** list.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-3.png" alt="drawing"/>  

5. Click the **table** that retrieves the data, check the **Columns list** and right click the **column** that is the **Input** parameter for data retrieval. 

6. Click **Add Selected to Input Parameters**.

    <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-4.png" alt="drawing"/> 

   Note that the Column Name, Data Type, Mandatory and Comments are displayed in the Input Parameters list in the lower right pane.
   


7. Select the **Column/s** to be retrieved or press the **CTRL key** to select multiple columns. Right click and select **Generate Code** to automatically create the **Select Statement** and **Basic Code Structure**. 
   

    The generated code is displayed in the Java Editor window and includes the basic structure of the call to the interface or LUDB and the Fetch method. Note that you can also write your own code. 

    <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-5.png" alt="drawing"/>  

8. Once the code is generated, edit the function based on the logic to be applied. For example:

    <p>Db.Rows rows = ludb("Customer", &lt;instanceID&gt;).fetch(sql, &lt;val1&gt;, &lt;val2&gt;, ...);</p>
    <p>Replace the <strong>&lt;instanceID&gt; placeholder</strong> with the input parameter <strong>CUSTOMER_ID</strong>.</p>
    <p>Db.Rows rows = ludb("Customer", CUSTOMER_ID).fetch(sql);</p>

9. Set the [**Web Service Properties**](/articles/15_web_services_and_graphit/02_web_services_properties.md) in the upper right corner pane.

</studio>


<web>

1. Go to **Project Tree** > **Web Services** and right-click **Java** to select **New Java File**.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-1WEB.PNG" alt="drawing"/>  
   
   
2. Fill in the name for the web service in the pop-up input box at the top of the screen
   
   <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-2WEB.PNG" alt="drawing"/> 
   
3. Start writing your code in the Java editor, using VScode built-in code snipplets by pressing CTRL -> SPACE and selecting the requested snipplet
   
   
   ```
   @desc("")
   @webService(path = "", verb = {MethodType.GET, MethodType.POST}, version = "1", isRaw = false, isCustomPayload = false, produce = {Produce.XML, Produce.JSON},                elevatedPermission = false)
   @resultMetaData(mediaType = Produce.JSON, example = "")
   public static void webServiceName(@param(description="") String param1) throws Exception {
   }
   ```
   

</web>




[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/01_web_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/02_web_services_properties.md)
