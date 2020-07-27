# Fabric Glossary

<table>
<tbody>
<tr>
<td width="200">
<p><h3><strong>Item<strong></p>
</td>
<td width="482">
<p><h3><strong>Descriptions<strong></p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>Digital Entity<strong>
</td>
<td width="482">
<p>A digital version of a person, place or a thing which is usually correlated to a business entity.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>LU / LUT<strong>
</td>
<td width="482">
<p>A <a href="/articles/03_logical_units/01_LU_overview.md">Logical Unit (LU)</a> or Logical Unit Type (LUT) is a blueprint holding a set of definitions / instructions used to create and maintain the data of a Digital Entity.</p>

</td>
</tr>
<tr>
<td width="123">
<h4><strong>LUI<strong>
</td>
<td width="482">
<p>A Logical Unit Instance (LUI) is a specific instance of a Logical Unit. For example, the data for a specific Customer ID.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>Instance ID<strong>
</td>
<td width="482">
<p>The Instance ID is the unique Digital Entity identifier of an LUI. For example, Customer ID 12345 represents a specific customer of a CUSTOMER Logical Unit Type. This ID is set as the Logical Unit's Root table's PK column.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>Instance Group<strong>
</td>
<td width="482">
<p>A list of Instance IDs (LUIs) used as part of a batch data extraction and transformation into Fabric. Usually constructed by an SQL query.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/06_LU_tables/01_LU_tables_overview.md"><strong>LU Table<strong></a>
</td>
<td width="482">
<p>The basic building block of a Logical Unit which defines a Digital Entity's data, including columns, PKs, indexes and triggers. A Logical Unit may have one or many LU tables.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/03_logical_units/08_define_root_table_and_instance_ID_LU_schema.md"><strong>Root Table<strong></a>
</td>
<td width="482">
<p>The main table in the Logical Unit. Each LU schema must have one Root Table. The Root Table holds the Instance ID (Instance PK Column) and is the root of the LU hierarchy.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/07_table_population/01_table_population_overview.md"><strong>Table Population<strong></a>
</td>
<td width="482">
<p>Defines the data transformation and mapping rules from a source into a targeted LU table. An LU table can contain several table populations.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/07_table_population/02_source_object_types.md"><strong>Source Object<strong></a>
</td>
<td width="482">
<p>A population rule, either a query or function, used as a source input to the LU table.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/02_fabric_architecture/01_fabric_architecture_overview.md#micro-databases-"><strong>MDB / MicroDB<strong>
</td>
<td width="482">
<p>Micro-database, a small SQL database used for the storage of a Digital Entity Instance (LUI) data. An MDB is stored as an SQLite file and also as a Blob field in the Cassandra Entity table, depending on the stored property definition on the LU schema.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/14_sync_LU_instance/01_sync_LUI_overview.md"><strong>Sync<strong></a>
</td>
<td width="482">
<p>A process that updates / modifies the source data connected within Fabric. The Sync process executes and extracts the transformation logic of a given instance on the LU tables and also populates its data into Fabric.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/14_sync_LU_instance/02_sync_modes.md"><strong>Sync Mode<strong></a>
</td>
<td width="482">
<p>The synchronization mode of an instance from source systems.</p>
<p><b>ON</b>: Run the sync based on the sync method. This is the default mode.</p>
<p><b>OFF</b>: Don't sync.</p>
<p><b>FORCE</b>: Always sync.</p>
<p>The Fabric Set Sync command is used to set the Sync Mode.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/14_sync_LU_instance/04_sync_methods.md"><strong>Sync Method<strong></a>
</td>
<td width="482">
<p>Defines how the sync will be performed (sync policy):</p>
<p><b>NONE</b>: Do not sync.</p>
<p><b>Time Interval</b>: Sync is performed automatically at the predefined time. Format = D.HH:DD:MM. Default = 1 Day.</p>
<p><b>Inherited</b>: Each LU level inherits the sync rule of its direct parent branch. For example, the LU Table Population inherits from the LU table and the LU table inherits from the LUT schema.</p>
<p><b>Decision function</b>: Sync is performed according to a Decision function which returns a True / False Boolean parameter.</p>
<p>The sync method can be set on LU Schema, LU Table, or Table Population levels.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/15_web_services/01_web_services_overview.md"><strong>WS<strong>
</td>
<td width="482">
<p>Web Service, a Java function that can be exposed through Fabric&rsquo;s Web Service layer.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/15_web_services/17_Graphit/01_graphit_overview.md"><strong>Graphit<strong></a>
</td>
<td width="482">
<p> A Fabric tool that can be used to create dynamic CSV, XML and JSON documents. Graphit is useful for generating Fabric Web Service responses. The content of a response is defined during its execution, either according to specific parameters relevant to the specific Web Service call and the employed LUI, or by retrieving dynamic information from other databases or interfaces. Graphit can be either invoked by a Web Service or directly. </p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>Common (Reference)<strong>
</td>
<td width="482">
<p>A Fabric allocated area for tables defined as Reference (metadata). These tables can be used by all LUI or within a specific LU's instances. For example; the postal code of a customer&rsquo;s address.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>ETL<strong>
</td>
<td width="482">
<p>Extract Transform Load.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/13_LUDB_viewer_and_studio_debug_capabilities/01_data_viewer.md"><strong>Data Viewer<strong></a>
</td>
<td width="482">
<p>A Fabric Studio capability, which allows you to view the Logical Unit Instance stored in memory as a database file.</p>
<p>The Data Viewer is also used to debug LUs to improve testing and defect resolution phases.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>CQL<strong>
</td>
<td width="482">
<p>Cassandra Query Language. CSQL is used by Fabric's Cassandra DB to query internal key spaces and tables.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>SOR<strong>
</td>
<td width="482">
<p>System of Record, refers to the source system / application. This functionality enables running a single transaction on a specific LU table of the Instance ID, or on the (common) Reference table. This functionality enables Fabric to become the master of the data rather than syncing data from external systems.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>Distributed DB<strong>
</td>
<td width="482">
<p>A distributed database is a database that consists of two or more files located in different sites either on the same network or on entirely different networks. Sections of the database are stored in multiple physical locations whereby processing is distributed among multiple database nodes. This make them easy to expand and resilient. Fabric greatly reduces the processing complexity associated with their architecture.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/03_logical_units/14_edit%20enrichment%20order.md"><strong>Enrichment Order<strong></a>
</td>
<td width="482">
<p>Defines the order of the Enrichment functions execution. The order is set in the Logical Unit schema&rsquo;s properties.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/07_table_population/11_lookup_tables.md"><strong>Lookup<strong></a>
</td>
<td width="482">
<p>A Lookup is an object that is added to a Table Population to get information from a source DB, LU table or reference table.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/09_translations/01_translations_overview_and_use_cases.md"><strong>Translation<strong></a>
</td>
<td width="482">
<p>A Fabric object that transforms data from one set of valid values to another and enables the execution of various transformation rules&nbsp;.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/05_DB_interfaces/01_interfaces_overview.md"><strong>Interfaces<strong></a>
</td>
<td width="482">
<p>An Interface is a data communication channel between Fabric and any external systems.</p>
<p>When multiple data sources are used by a Logical Unit, additional interfaces can be defined.</p> 
<p>The interface types are:</p>
<p><b>DB Interface</b>, enables the connections of the Fabric Server to databases like SQL Server, PostgreSQL or Oracle (data and metadata).</p>
<p><b>Non-DB Interface</b>, used to define a connection with data provisioning systems that are not databases. For example, search engines, FTP servers or a message queue.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>Database Types<strong>
</td>
<td width="482">
<p>New database types can be defined without additional enhancement to the product, depending on their specifications and whether they have a &nbsp;JDBC driver. Database types that are part of the Product package can be edited or overridden, but may require customization.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/08_globals/01_globals_overview.md"><strong>Globals<strong></a>
</td>
<td width="482">
<p>Globals are used when a variable with the same value is required repeatedly by various Fabric objects. A Global can be defined within a shared scope or locally in the LUT.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>User Jobs<strong>
</td>
<td width="482">
<p>A Fabric mechanism that executes a light-weight function either manually or automatically using a predefined Scheduler. For example, time interval.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>Parsers<strong>
</td>
<td width="482">
<p>Graphical map that defines data transformation and mapping rules from a source. For example, a DB table or Input file into&nbsp; Fabric distributed storage (Cassandra). Parsers use the same mechanism as jobs for execution which can be manual or automatic.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><a href="/articles/07_table_population/13_LU_table_population_execution_order.md"><strong>Execution Order<strong></a>
</td>
<td width="482">
<p>The order that LU tables are populated in an LU schema is defined by Fabric when the LU schema is created. The LU schema's Root Table is always populated first and by default, its population order is set to 1 since it is the first one to be executed.</p>
</td>
</tr>
<tr>
<td width="123">
  <h4><strong><a href="/articles/11_query_builder/01_query_builder_overview.md">Query Builder</a><strong>
</td>
<td width="482">
<p>The Query Builder is an embedded visual query building component in Fabric Studio that allows you to build complex SQL queries using a predefined Fabric interface. A basic knowledge of SQL and its concepts is required to use the Query Builder.</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong>Swagger<strong>
</td>
<td width="482">
<p>Fabric Studio supports invocation and testing of Web Services using Swagger (Open Source under Apache License 2.0), a specification and complete framework implementation for describing, producing, consuming, and visualizing RESTful Web Services.</p>
<p>To connect to Swagger:</p>
<p>Go to the following URL and specify the IP address of your Fabric Server: http://&lt;Fabric Server&gt;:3213/api</p>
<p>     Or,</p>
<p>Set the Web Service invoke path template in your User preferences - Server configuration to: static/swaggerUI/dist/index.html#/&lt;CATEGORY&gt;/&lt;WS_VERB&gt;_&lt;WS_PATH&gt;</p>
</td>
</tr>
<tr>
<td width="123">
<h4><strong><a href="/articles/16_deploy_fabric/01_deploy_Fabric_project.md">Deployment</a><strong>
</td>
<td width="482">
<p>The action of applying the Fabric Studio component into the Fabric Server (DB). A newly created Fabric component or an updated one is not reflected in the Fabric DB until it is deployed to the Fabric Server.</p>
<p>Deployment is needed for each implementation change. For example, LU schema definition changes, transformation rule changes in Functions, Globals and Translations.</p>
</td>
</tr>
</tbody>
</table>
  
  [![Previous](/articles/images/Previous.png)](/articles/01_fabric_overview/01_what%20is%20fabric.md)

