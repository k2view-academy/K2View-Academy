# OrientDB Setup

Data Catalog is using OrientDB distributed graph database. OrientDB is an Open Source NoSQL DBMS that combines the power of graphs and the flexibility of documents into one scalable, high-performance operational database.

To setup the OrientDB, do the following:

1. Go to the OrientDB website: https://www.orientdb.org/download and download the **OrientDB with Gremlin Server - zip**.

2. Extract the zip file:

   * For Windows installation: extract the file under **C:\K2View**

3. Edit **orientdb-server-config.xm**l by adding the parameter value 'GREMLIN' as follows:

   ~~~
   <handler class="com.orientechnologies.orient.server.handler.OServerSideScriptInterpreter">
   <parameters>
   <parameter value="true" name="enabled"/>
   <parameter value="SQL,GREMLIN" name="allowedLanguages"/>
   <parameter value="" name="allowedPackages"/>
   </parameters>
   </handler>
   ~~~

4. On the first time you run OrientDB, you will be asked you to create username & password.

5. To run OrientDB:

   * Go to **C:\K2View\orientdb-tp3-3.1.2\orientdb-tp3-3.1.2\bin**.
   * Open CMD and run **server.bat**.

6. Once OrientDB is up and running, you will see the following lines:

   ~~~
   INFO  {db=demodb} Gremlin started correctly [OGremlinServerPlugin]
   INFO  {db=demodb} OrientDB Studio available at http://<yourIP>:2480/studio/index.html [OServer]
   INFO  {db=demodb} OrientDB Server is active v3.1.2 - Veloce (build 8b6ef394ce7b45017e84a65efb9ce7534c56aa8e, branch 3.1.x)
   ~~~

7. Copy the link to the browser to open the OrientDB web application to create a new database.

   * Note that you can also create a new OrientDB database during the definition of your Data Catalog Interface Type by populating the Database setting.



[![Previous](/articles/images/Previous.png)](06_override_data_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_catalog_commands.md) 