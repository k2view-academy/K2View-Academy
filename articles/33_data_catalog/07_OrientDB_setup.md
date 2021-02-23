# OrientDB Setup

The Data Catalog uses a distributed OrientDB graph database, an Open Source NoSQL DBMS that combines the power of graphs and the flexibility of documents into one scalable, high-performance operational database.

To setup the OrientDB in the **development** environment, do the following:

1. Go to the OrientDB website: https://www.orientdb.org/download and download the **OrientDB with Gremlin Server - zip**.

   <img src="images/33_07_download.PNG" alt="image" style="zoom:67%;" />

2. Extract the zip file:

   * For a Windows installation: extract the file under **C:\K2View**

3. Edit the **orientdb-server-config.xml** by adding the parameter value 'GREMLIN' as follows:

   ~~~
   <handler class="com.orientechnologies.orient.server.handler.OServerSideScriptInterpreter">
   <parameters>
   <parameter value="true" name="enabled"/>
   <parameter value="SQL,GREMLIN" name="allowedLanguages"/>
   <parameter value="" name="allowedPackages"/>
   </parameters>
   </handler>
   ~~~
4. Following the instructions in the link bellow add environment variable: "ORIENTDB_HOME" pointing to root of OrientDB folder AKA "C:\K2View\orientdb-3.1.8"
   https://explainjava.com/set-java-path-and-java-home-windows/
   
5. Restart command promt (CMD).

6.The first time you run the OrientDB, you will be asked to create a username and password.

7. To run the OrientDB:

   * Go to **C:\K2View\orientdb-tp3-3.1.2\orientdb-tp3-3.1.2\bin**.
   * Open the CMD and run the **server.bat**.

8. Once the OrientDB is up and running, the following lines are displayed:

   ~~~
   INFO  {db=demodb} Gremlin started correctly [OGremlinServerPlugin]
   INFO  {db=demodb} OrientDB Studio available at http://<yourIP>:2480/studio/index.html [OServer]
   INFO  {db=demodb} OrientDB Server is active v3.1.2 - Veloce (build 8b6ef394ce7b45017e84a65efb9ce7534c56aa8e, branch 3.1.x)
   ~~~

9. Copy the link to the browser to open the OrientDB web application to create a new database.

   * Note that you can also create a new OrientDB database during the definition of the Data Catalog Interface Type by populating the Database settings.



[![Previous](/articles/images/Previous.png)](06_override_data_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_catalog_commands.md) 
