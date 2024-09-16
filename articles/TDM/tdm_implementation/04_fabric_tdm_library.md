# Fabric TDM Library

The TDM Library has all the utilities required for implementing a TDM project and for running TDM execution processes. It holds the following:

- [Shared Objects](#tdm-library---shared-objects)
- [TDM MTables](#tdm-library---MTables)
- [TDM LU](#tdm-lu)
- [TDM_LIBRARY LU](#tdm_library-lu)
- [TDM_Table level LU](09_tdm_reference_implementation.md) 

The TDM Library must be imported to the Fabric project created for TDM. 

## TDM Library - Shared Objects

### TDM Web Services

Import and deploy all TDM Web Services (APIs) to the Fabric project. These Web Services are invoked by the TDM Portal application, and they constitute of the back-end layer of the TDM Portal application.

As the TDM categories contain the product's Web Services, it is recommended to add the project's Web Services into separate categories, which would simplify the TDM version upgrading.

### Generic TDM Interfaces

Import and deploy the following [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md) into the project's **Shared Objects**:
- **DB_CASSANDRA** - this is the connection to the Cassandra DB. Edit the IP address according to the environment if you use the DB_CASSANDRA as the Fabric system DB.

- **POSTGRESQL_ADMIN** - this is the admin connection to the [TDM PosgreSQL DB](/articles/TDM/tdm_architecture/02_tdm_database.md). This interface is used by the **TDMDB flow** in the **TDM LU** to create the TDM DB in the PostgreSQL DB. 

- **TDM** - this is the connection to the [TDM PosgreSQL DB](/articles/TDM/tdm_architecture/02_tdm_database.md). Edit the IP address according to the environment. 

    Note that if you work on a PostgreSQL with an SSL connection, you must edit the custom connection string of the POSTGRESQL_ADMIN and the TDM interfaces as follows:

    - jdbc:postgresql://[ip address]:5438/TDMDB?stringtype=unspecified&ssl=true&sslmode=verify-ca&sslrootcert=[full path of the .crt file]
    - Example:
      - jdbc:postgresql://localhost:5438/TDMDB?stringtype=unspecified&ssl=true&sslmode=verify-ca&sslrootcert=C:\k2view\pgSSL\cert\k2v_CA.crt 

    

- [AI interfaces](17_tdm_ai_generation_implementation.md) - AI DB and Kubernetes interfaces. TDM 9.0 has added an integrated AI solution for a synthetic data generation. The AI-related interfaces must be disabled if the AI machine is not installed and the AI data generation is not in use.

### Shared Globals

Import the list of shared [global variables](/articles/08_globals/01_globals_overview.md) required for executing TDM in your project. TDM 9.0 locates the TDM library shared Globals under Implementation/SharedObjects/Java/src/com/k2view/cdbms/usercode/common/TDM/SharedGlobals.java. The project's shared Globals should be populated in a separate SharedGlobals file (Implementation/SharedObjects/Java/src/com/k2view/cdbms/usercode/common/SharedGlobals.java) in order to simplify the TDM version upgrading and to prevent overriding the project's globals by the TDM version upgrade process.

#### SEQ_CACHE_INTERFACE Global
A new Global has been added in TDM 8.1 - SEQ_CACHE_INTERFACE. This Global is populated with the DB interface of the k2masking DB (PostgreSQL or Cassandra), and it must be aligned with Fabric’s system DB. TDM 9 sets the POSTGRESQL_ADMIN as a default value in this Global:
- If you use Cassandra as Fabric’s system DB, you must edit the SEQ_CACHE_INTERFACE Global and update its value to DB_CASSANDRA.
- If you wish to use the PostgreSQL DB as Fabric's system DB, do the following:
    - Open the Fabric’s config.ini file and edit the [system_db] section’s attributes including the SYSTEM_DB_DATABASE attribute to be aligned with the POSTGRESQL_ADMIN DB interface. 


### Shared Functions

The TDM shared functions are saved in the **TDM** [Logic file](/articles/04_fabric_studio/09_logic_files_and_categories.md). 

Import the TDM shared functions to your project. Note that as the TDM category contains the product's functions, it is recommended to add the project's shared functions to a separate category (Logic file) in order to simplify the TDM version upgrading.

## TDM Library - MTables

TDM 8.1 replaces the previous TDM translation with [MTables](/articles/09_translations/06_mtables_overview.md) to support the development of the TDM on both Fabric Studios: Desktop-Studio and Web-Studio.

The following MTables have been added to the **References** in the TDM library. Note that you **must deploy the Reference to Fabric** after updating the MTables:

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="200pxl">
<p><strong>Item&nbsp;</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Description&nbsp;</strong></p>
</td>
<td valign="top" width="400pxl">
<p><strong>Instructions&nbsp;</strong></p>
</td>
</tr>
<td valign="top" width="200pxl">
<p><h5>MigrateList</p>
</td>
<td valign="top" width="300pxl">
<p>Defines the query and interface names, or the Broadway flow to generate the entity list when running a task with a predefined entity list selection method for the entities' subset; one record per LU.</p>
</td>
<td valign="top" width="400pxl">
<p>Populate this table for each Logical Unit. A separate record must be created for each Logical Unit in the Fabric project apart from TDM, TDM_LIBRARY and the dummy LU of the post-execution processes. &nbsp;</p>
<p>If there is a need to define a query per source environment, populate the source environment name and create a separate record for each Logical Unit and source_env_name combination. Otherwise, leave the source environment empty.</p>
        <p>Click <a href="14_tdm_implementation_supporting_non_jdbc_data_source.md">here</a> for more information on how to implement a Broadway flow to get the entities (populated in external_table_flow field of MigrateList table).</p>   
  <p><strong>Example 1:</strong></p>
  <ul><li>lu_name= ORDER</li>
    <li>source_env_name = ENV1</li>
    <li>interface_name = TDM</li>
<li>ig_sql = Select lu_type2_eid from tdm_lu_type_relation_eid where lu_type_2 = &lsquo;ORDER&rsquo; and source_env = 'ENV1';</li></ul>
  <p><strong>Example 2:</strong></p>
  <ul><li>lu_name= CUSTOMER</li>
    <li>source_env_name is empty</li>
    <li>interface_name = CRM_DB</li>
    <li>ig_sql = Select customer_id from customer limit 1000;</li></ul>
    <p><strong>Example 3:</strong></p>
  	<ul><li>lu_name= CUSTOMER</li>
    <li>source_env_name is empty</li>
    <li>external_table_flow = getEntityListFlow</li>   
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p><h5>MigrateListQueryFormats</p>
</td>
<td valign="top" width="300pxl">
<p>Supports special syntax for <strong>extract tasks </strong>when creating the LU instance query based on the MigrateList table. Each LUI consists of a concatenation of the source environment, IID, version name and version datetime.</p>
<p>Click to read more about <a href="01_tdm_set_instance_per_env_and_version.md">LUI structure for TDM implementation</a>.</p>
<p>This table is required for databases that do not support the standard &lsquo;||&rsquo; syntax for concatenated strings, e.g., sqlServer.</p>
</td>
<td valign="top" width="400pxl">
<p>Populate 2 records for each database, one record with version_ind&nbsp;&lsquo;true&rsquo; and another with&nbsp;version_ind&nbsp;&lsquo;false&rsquo;.&nbsp;</p>
  <p><strong>Example 1:</strong> </p>
<ul> <li>interface_type = sqlserver </li>
<li>version_ind = true </li>
<li>query_format = CONCAT(&lt;source_env_name&gt;,'_',&lt;entity_id&gt;,'_',&lt;task_name&gt;,'_',&lt;timestamp&gt;)</li>
</ul>
<p><strong> Example 2:</strong></p>
<ul><li>interface_type = sqlserver </li>
<li>version_ind = false </li>
<li>query_format = CONCAT(&lt;source_env_name&gt;,'_',&lt;entity_id&gt;)</li>
  </ul>
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p><h5>RefList</p>
</td>
<td valign="top" width="300pxl">
<p>Defines the list of available tables related to a Business entity, a list that can be included on a <strong>TDM task</strong> for <strong>Entities and referential data</strong>. In addition it can be used in order to set different interface/schema/table name between the source and target environments for table level </p>
<p>Click to read more about <a href="09_tdm_reference_implementation.md">Tables implementation</a>.</p> 
</td>
<td valign="top" width="400pxl">
<p>A separate record must be created for each table. Set the LU name on each record.</p>
</td>
</tr>
<tr>
<td><h5>TableLevelInterfaces and TableLevelDefinitions</h5></td>
<td>These MTables defines special handling for task with tables.</td>
<td>A separate record must be set for each DB or table. 
<p>Click to read more about <a href="09_tdm_reference_implementation.md">Tables implementation</a>.</p>  
</td>    
</tr>    
<tr>
<td valign="top" width="200pxl">
<p><h5>PostAndPreExecutionProcess </p>
</td>
<td valign="top" width="300pxl">
<p>Defines the list of pre-execution and post-execution flows to run before or at the end of a task's execution. For example, a process that sends a mail to notify the user when the task's execution ends, or a process that populates a mapping table before the LU execution starts.</p>
<p>Each process is implemented as a Broadway flow.</p>
</td>
<td valign="top" width="400pxl">
<p>Populate the list of Broadway flows, the LU of the Broadway flow and the process type (pre/post). Each flow can have external parameters that can be overridden by the task's creator. The LU can be empty if the processes are defined under Shared Objects, whereby the TDM task execution process sets the LU Name to TDM when running Batch commands to carry out pre/post execution processes. Redeploy the LUs populated in this table, the TDM LU, and the Web-Services.  </p>
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p><h5>ChildLink</p>
</td>
<td valign="top" width="300pxl">
<p>Mapping parent and child IDs.&nbsp;</p>
<p>Click for more information about <a href="/articles/TDM/tdm_overview/03_business_entity_overview.md">TDM business entities</a> and how to <a href="/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md">support a hierarchy</a> when implementing the LUs.</p>
</td>
<td valign="top" width="400pxl">
<p>A record must be added to this table for each parent-child relationship. The parent_lu field must be populated with the name of the parent LU and the child_lu field must be populated with the name of the child LU.</p>
<p>Both SQLs populated in child_lu_eid_sql and child_lu_tar_eid_Sql fields must run on the parent LU and get the source and target child IDs for each parent ID.</p>
<p><strong>Example:</strong><u><br /></u>Customer LU is the parent of the Orders LU. <br />ChildLink of the Customer LU must be populated as follows:</p>
<ul>
<li><strong>parent_lu = </strong>CRM</li>    
<li><strong>child_lu = </strong>Orders</li>
<li><strong>child_lu_eid_sql = </strong>select order_id from subscriber</li>
<li><strong>child_lu_tar_eid_sql = </strong>select order_id from tar_subscriber</li>    
</ul>
<p>The parameters - tables, subscriber and tar_subscriber - must all be defined in the CRM LU schema.</p>  
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p><h5>LuParams</p>
</td>
<td valign="top" width="300pxl">
<p>This table is used for the population business parameters.&nbsp;</p>
</td>
<td valign="top" width="400pxl">
<p>The COLUMN_NAME is populated by the name of the parameter and the SQL is populated by the SQL query that gets the values for the defined parameter.</p>
<p>Click for more information about <a href="/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md">handling parameters</a>. </p>
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p><h5>LuParamsMapping</p>
</td>
<td valign="top" width="300pxl">
<p>This table is used for the population business parameters when the <stong>parameters coupling mode</stong> is set.&nbsp;</p>
</td>
<td valign="top" width="400pxl">
<p>Map each parameter to an LU table's field.</p>
<p>Click for more information about <a href="/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md">handling parameters</a>. </p>
</td>
</tr>     
<tr>
    <td><h5>AI configuration tables</h5></td>
    <td>Configration settings for AI-based data generation.</td>
    <td>Click for more information about <a href>AI-based data generation implementation</a></td>
    </tr>    
</tbody>
</table>






### Broadway Generic Flows and Templates

The Fabric TDM library includes a set of built-in generic Broadway flows, defined for an easy adaptation of a generic TDM implementation for a specific data model. 

Click for more information about [Generic TDM Broadway Flows](10_tdm_generic_broadway_flows.md).

## TDM LU

The TDM Logical Unit must be deployed to the Fabric project. It has the following tasks:

- Saving information about executed TDM tasks. The TDM Portal provides execution statistics and reports based on the data in the TDM LU. The LUI of the TDM LU is a unique task_Execution_id generated by the TDM Portal for each executed task. 
- [Task execution jobs](/articles/TDM/tdm_architecture/03_task_execution_processes.md) are included in the TDM LU.
- The TDM cleanup job that cleans the TDM DB is defined under the TDM LU. 
- **From TDM 7.6 onwards, the TDM Portal code is included in the TDM LU**: The TDM Portal code is saved under the web sub-folder in the TDM LU. Note that the web directory can only be viewed using the Fabric web studio. If you use the desktop, you can right-click the TDM LU > Open Folder and view the web folder in the windows File Explorer.
- TDM 7.6 and onwards includes the TDM DB upgrade scripts and a flow.

### Set TTL (Time To Live) on the TDM LUIs

TDM enables setting TTL (Time To Live) on the TDM LUIs. The default TTL period is 10 days. The TDM LUI's TTL depends on the following **shared Globals** (imported from the TDM Library):

- **TDM_LU_RETENTION_PERIOD_TYPE** - by default, it is populated by 'Days'. This Global can have one of the following values: Minutes, Hours, Days, Weeks or Years.
- **TDM_LU_RETENTION_PERIOD_VALUE** - by default, it is populated with the value 10. **Populate this Global with either a zero or an empty value to avoid setting TTL on the TDM LUIs**.

### TDM Cleanup Process

<table width="900pxl">
<tbody>
<tr>
<td valign="top" width="200pxl">
<p><strong>Item&nbsp;</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Description&nbsp;</strong></p>
</td>
<td valign="top" width="400pxl">
<p><strong>Instructions&nbsp;</strong></p>
</td>
</tr>
    <tr>
<td valign="top" width="200pxl">
    <p><h5>TDMCleanUp</p>
        </td>
<td valign="top" width="300pxl">
    Defines the list of the TDM DB tables to be cleaned up by the TDM cleaning-up process. This table defines the Delete statement on each table and a cleanup indicator indicates whether the table should be cleaned up by the TDM cleaning-up process.
        </td>
  <td valign="top" width="400pxl">
      Set the cleanup_ind field to FALSE to remove a table from the cleaning-up process. TDM tables can be added, and Delete statements can be edited.
        </td>
    </tr>
    </tbody>
</table>

​    

### TDM Deploy Flow

The **deploy.flow** process runs the following activities upon the TDM LU deployment:

- Deployment of the project's environments to Fabric.
- Creating the k2masking keyspace in Cassandra, if it does not already exist.
- TDM 7.6 has added a creation of the TDM PostgreSQL DB: The TDM deploy flow creates the TDM DB tables, sequences, views and functions.
- Notes:
  - You must **set the BUILD_TDMDB Global to true (default is false) and the POSTGRESQL_ADMIN interface to be active** in order to create the TDM DB by the TDM deploy flow.
  - TDM 8.0 has added the environment's deployment if the **TDM_DEPLOY_ENVIRONMENTS** Global is set to **true**. This Global is set to **false**, by **default**. The environment's file is taken from the project directory. If you wish to deploy the environments to Fabric, set the TDM_DEPLOY_ENVIRONMENTS to true. 


### TDM LU Deployment

Deploy the TDM LU to Fabric. **From TDM 7.6 onwards, the deployment of the TDM LU deploys the TDM Portal as well into the TDM web applications**. 

Notes:

- Deploy the TDM LU to the local debug Fabric server using the [soft deploy](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#soft-deploy) option, or stop the TDM jobs on the local Fabric before deploying the TDM LU to a remote Fabric server, thus avoiding a parallel execution of TDM jobs on the same TDM DB. 
- The apps.json file in the TDM LU overrides the list of web applications in Fabric. Open the file and edit it before the TDM LU deployment, if needed.


## TDM_LIBRARY LU

The TDM_LIBRARY LU holds utilities that must be copied to the project's LUs. These utilities are described below.

It is recommended to duplicate the TDM_Library LU and use it as a template when creating a new LU in a TDM project.

### Globals

#### LU level [Globals](/articles/08_globals/01_globals_overview.md) 

- Populate the **ROOT_TABLE_NAME** Global using the main source table/s. You can populate several tables, separated by a comma. 

  Examples: 

  - CUSTOMER 
  - CUSTOMER, ACCOUNT 

- Populate the **ROOT_COLUMN_NAME** Global using the entity ID's column. These Globals are needed for setting the IS_INSTANCE_ID column correctly in [TDM_SEQ_MAPPING](/articles/19_Broadway/actors/08_sequence_implementation_guide.md#sequence-mapping) TDM DB table. Note that the number and order of root column names must be aligned with the number and order of the tables that are populated in **ROOT_TABLE_NAME**. 

  Examples:

  <table width="900pxl">
  <tr>
  <td><strong>ROOT_TABLE_NAME</strong></td>
  <td><strong>ROOT_COLUMN_NAME</strong></td>
  </tr>
  <tr>
  <td>CUSTOMER</td>
  <td>CUSTOMER_ID</td>
  </tr>
  <tr>
  <td>CUSTOMER, ACCOUNT</td>
  <td>CUSTOMER_ID, CUSTOMER_ID</td>
  </tr>
  <tr>
  <td>CUSTOMER, ACCOUNT_DATA</td>
  <td>CUSTOMER_ID, ACC_CUST_ID</td>
  </tr>  
  </table>

### LU Tables

- **FABRIC_TDM_ROOT** - the Root table of each LU. This table contains the following columns:

  - K2_TDM_EID - populated by the LU instance ID. 
  - IID - populated by the entity ID without the concatenation of the source environment, version name and version datetime.
  - SOURCE_ENV - populated by the source environment name of the TDM task.
  - TASK_NAME - version name, populated with a task name for a [Data Versioning](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-versioning) task.
  - TIMESTAMP - version datetime, populated by a [Data Versioning](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-versioning) task. 

  **Example:** 

  <table width="900pxl">
  <tbody>
  <tr>
  <td valign="top" width="200pxl">
  <p><strong>K2_TDM_EID</strong></p>
  </td>
  <td valign="top" width="100pxl">
  <p><strong>IID</strong></p>
  </td>
  <td valign="top" width="200pxl">
  <p><strong>SOURCE_ENV</strong></p>
  </td>
  <td valign="top" width="200pxl">
  <p><strong>TASK_NAME</strong></p>
  </td>
  <td width="200pxl" valign="top">
  <p><strong>TIMESTAMP</strong></p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="200pxl">
  <p>PROD_1</p>
  </td>
  <td valign="top" width="100pxl">
  <p>1</p>
  </td>
  <td valign="top" width="200pxl">
  <p>PROD</p>
  </td>
  <td valign="top" width="200pxl">
  <p>&nbsp;</p>
  </td>
  <td width="200pxl" valign="top">
  <p>&nbsp;</p>
  </td>
  </tr>
  <tr>
  <td valign="top" width="200pxl">
  <p>PROD_1_copyCust_20201105090000</p>
  </td>
  <td valign="top" width="100pxl">
  <p>1</p>
  </td>
  <td valign="top" width="200pxl">
  <p>PROD</p>
  </td>
  <td valign="top" width="200pxl">
  <p>copyCust</p>
  </td>
  <td width="200pxl" valign="top">
  <p>20201105090000</p>
  </td>
  </tr>
  </table>

- **LU_PARAMS** - parameters table. It must be added to each LU schema even when it is not required for defining parameters in the LU. The LU_PARAM table holds only the ENTITY_ID and SOURCE_ENVIRONMENT fields.

  Click for more information about [TDM parameters handling](/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md).

- **TDM_LU_TYPE_RELATION_EID** and **TDM_LU_TYPE_REL_TAR_EID** - TDM relationship tables that map the parent to child IDs. Note that these tables are also created in the TDM DB.

  Click for more information about [TDM Hierarchy implementation](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md).



## TDM_TableLevel LU

TDM 9.0 and onwards stores the extracted tables in a new LU - TDM_TableLevel. Each table is stored as a separate LUI. For more information, read [Tables Implementation](09_tdm_reference_implementation.md).


[![Previous](/articles/images/Previous.png)](03_tdm_fabric_implementation_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_tdm_lu_implementation_general.md)
