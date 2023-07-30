# Fabric TDM Library

The TDM Library has all the utilities required for implementing a TDM project and for running TDM execution processes. It holds the following:

- [Shared Objects](#tdm-library---shared-objects)
- [TDM MTables](#tdm-library---MTables)
- [TDM LU](#tdm-lu)
- [TDM_LIBRARY LU](#tdm_library-lu)
- [TDM_Reference](09_tdm_reference_implementation.md) 

The TDM Library must be imported to the Fabric project created for TDM. 

## TDM Library - Shared Objects

### TDM Web Services

Import and deploy all TDM Web Services (APIs) to the Fabric project. These Web Services are invoked by the TDM Portal application and they comprise the back-end layer of the TDM Portal application.

As the TDM categories contain the product's Web Services, it is recommended to add the project's Web Services into separate categories in order to simplify the TDM version upgrading.

### Generic TDM Interfaces

Import and deploy the following [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md) into the project's **Shared Objects**:
- **DB_CASSANDRA** - this is the connection to the Cassandra DB.  This interface is used by TDM utilities. Edit the IP address according to the environment.

- **CASSANDRA_LD** - a Cassandra Loader interface. This interface is used by the Reference upgrade script (upgrade to TDM 7.5.1).

-  **POSTGRESQL_ADMIN** - this is the admin connection to the [TDM PosgreSQL DB](/articles/TDM/tdm_architecture/02_tdm_database.md). This interface is used by the **TDMDB flow** in the **TDM LU** to create the TDM DB in the PostgreSQL DB. 
   
-  **TDM** - this is the connection to the [TDM PosgreSQL DB](/articles/TDM/tdm_architecture/02_tdm_database.md). Edit the IP address according to the environment. 
   
    Note that if you work on a PostgreSQL with an SSL connection, you must edit the custom connection string of the POSTGRESQL_ADMIN and the TDM interfaces as follows:
    
    - jdbc:postgresql://[ip address]:5438/TDMDB?stringtype=unspecified&ssl=true&sslmode=verify-ca&sslrootcert=[full path of the .crt file]
    - Example:
      - jdbc:postgresql://localhost:5438/TDMDB?stringtype=unspecified&ssl=true&sslmode=verify-ca&sslrootcert=C:\k2view\pgSSL\cert\k2v_CA.crt 
    
- **FabricRedis** - this is the [Redis interface](/articles/24_non_DB_interfaces/09_redis_interface.md) that connects to the environment's **Redis** storage. The Redis interface can be used for the [sequence implementation](11_tdm_implementation_using_generic_flows.md#step-2---create-sequences). Edit the IP address and populate it with the IP address of the TDM server. Note that from Fabric 7.1 onwards, the sequence Actors can get the next sequence value from a newly created DB sequence in the sequence interface (the sequence interface can be the TDM DB). The sequence is created by the Actor, if it doesn't yet exist. It is therefore recommended to get the next value from a DB sequence instead of using the Redis.

    For more information, view the [Sequence Implementation Guide](/articles/19_Broadway/actors/08_sequence_implementation_guide.md).  

-  **TDM_APIDOC_JSON** - this is a local file system interface, used for generating the JSON file of the TDM APIDOC if the APIDOC needs to be updated to include project custom APIs.
    [Click here](/articles/TDM/tdm_configuration/01_tdm_installation.md#update-the-tdm-apidoc-optional) for more information about updating the TDM APIDOC.
    
    It is important to **set the TDM_APIDOC_JSON interface as *disabled* in the Environments** in order to prevent errors when running the test connection on the task's environment (the Fabric server has a different IP address than the local Windows machine and cannot connect to the local machine's directory).

### Shared Globals

Import the list of shared [global variables](/articles/08_globals/01_globals_overview.md) required for executing TDM in your project.

### Shared Functions

TDM shared functions are saved in the **TDM** [Logic file](/articles/04_fabric_studio/09_logic_files_and_categories.md). 

Import the TDM shared functions to your project. Note that since the TDM category contains the product's functions, it is recommended to add the project's shared functions to a separate category (Logic file) in order to simplify the TDM version upgrading.

## TDM Library - MTables

TDM 8.1 replaces the previous TDM translation with [MTables](/articles/09_translations/06_mtables_overview.md) to support a development of the TDM on both Fabric Studios: Desktop-Studio and Web-Studio.

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
<p>Define the query and interface name, or the Broadway flow to generate the entity list when running the <strong>extract task</strong> on all entities of each LU; one record per LU.</p>
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
<p>Define the list of available reference tables for <strong>TDM tasks</strong>.</p>
<p>Click to read more about <a href="09_tdm_reference_implementation.md">Reference implementation</a>.</p> 
</td>
<td valign="top" width="400pxl">
<p>Populate this table for each reference table. A separate record must be created for each reference table. Set the LU name on each record.</p>
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p><h5>PostProcessList</p>
</td>
<td valign="top" width="300pxl">
<p>Define the list of post-processes to run at the end of the task's execution. For example, a process that sends a mail to notify the user when the task's execution ends.</p>
<p>Each process is implemented as a Broadway flow.</p>
</td>
<td valign="top" width="400pxl">
<p>Populate the list of Broadway flows and the LU of the Broadway flow. The LU can be empty if the post processes are defined under Shared Objects, whereby the TDM task execution process sets the LU Name to TDM when running Batch commands to carry out post execution processes. Redeploy the LUs populated in this table, the TDM LU, and the Web-Services.  </p>
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
<p>A record must be added to this table for each parent-child relationship. The parent_lu field must be populated with the name of the parent LU and child_lu field must be populated with the name of the child LU.</p>
<p>Both SQLs populated in child_lu_eid_sql and child_lu_tar_eid_Sql fields must run on the parent LU and get the source and target child IDs for each parent ID.</p>
<p><strong>Example:</strong><u><br /></u>Customer LU is the parent of the Orders LU. <br />ChildLink of the Customer LU must be populated as follows:</p>
<ul>
<li><strong>parent_lu = </strong>CRM</li>    
<li><strong>child_lu = </strong>Orders</li>
<li><strong>child_lu_eid_sql = </strong>select order_id from subscriber</li>
<li><strong>child_lu_tar_eid_sql = </strong>select order_id from tar_subscriber</li>    
</ul>
<p>The parameters: tables, subscriber and tar_subscriber, must all be defined in the CRM LU schema.</p>  
</td>
</tr>
<tr>
<td valign="top" width="200pxl">
<p><h5>LuParams</p>
</td>
<td valign="top" width="300pxl">
<p>Translation for the population of the LU_PARAMS table.&nbsp;</p>
</td>
<td valign="top" width="400pxl">
<p>The COLUMN_NAME is populated by the name of the parameter and the SQL is populated by the SQL query that gets the values for the defined parameter.</p>
<p>Click for more information about <a href="/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md">handling parameters</a>. </p>
</td>
</tr>    
</tbody>
</table>




### Broadway Generic Flows and Templates

The Fabric TDM library includes a set of built-in generic Broadway flows, defined for easy adaptation of a generic TDM implementation for a specific data model. 

Click for more information about [Generic TDM Broadway Flows](10_tdm_generic_broadway_flows.md).

## TDM LU

The TDM Logical Unit must be deployed to the Fabric project. It has the following tasks:

- Saves information about executed TDM tasks. The TDM Portal provides execution statistics and reports based on the data in the TDM LU. The LUI of the TDM LU is a unique task_Execution_id generated by the TDM Portal for each executed task. 
- [Task execution jobs](/articles/TDM/tdm_architecture/03_task_execution_processes.md) are included in the TDM LU.
- The TDM cleanup job that cleans the TDM DB is defined under the TDM LU. 
- **From TDM 7.6 onwards, the TDM Portal code is included in the TDM LU**: the TDM Portal code is kept under the web sub-folder in the TDM LU. Note that the web directory can only be viewed using the Fabric web studio. If you use the desktop, you can right-click the TDM LU > Open Folder and view the web folder in the windows File Explorer.
- TDM 7.6 and onwards includes the TDM DB upgrade scripts and a flow.

### Set TTL (Time To Live) on the TDM LUIs

TDM enables setting TTL (Time To Live) on the TDM LUIs. The default TTL period is 10 days. The TDM LUI's TTL depends on the following **shared Globals** (imported from the TDM Library):

- **TDM_LU_RETENTION_PERIOD_TYPE** - by default, it is populated by 'Days'. This Global can have one of the following values: Minutes, Hours, Days, Weeks or Years.
- **TDM_LU_RETENTION_PERIOD_VALUE** - by default, it is populated by 10. **Populate this Global with either a zero or an empty value to avoid setting TTL on the TDM LUIs**.

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
    Define the list of the TDM DB tables to be cleaned by the TDM clean-up process. This table defines the Delete statement on each table and a clean-up indicator indicates whether the table should be cleaned by the TDM clean-up process.
        </td>
  <td valign="top" width="400pxl">
      Clear the cleanup_ind to remove a table from the clean-up process. TDM tables can be added and Delete statements can be edited.
        </td>
    </tr>
    </tbody>
</table>

​    

### TDM Deploy Flow

The **deploy.flow** process runs the following activities upon the TDM LU deployment:

- Deployment of the project's environments to Fabric.
- Creating the k2masking keyspace in Cassandra, if it does not already exist.
- TDM 7.6 added a creation of the TDM PostgreSQL DB: the TDM deploy flow Creates the TDM DB tables, sequences, views and functions.
- Notes:
  - You must **set the BUILD_TDMDB Global to true (default is false) and the POSTGRESQL_ADMIN interface to be active** in order to create the TDM DB by the TDM deploy flow.
  - TDM 8.0 added the environment's deployment if the **TDM_DEPLOY_ENVIRONMENTS** Global is **true**. By **default** this Global is **false**. The environment's file is taken from the project directory. If you wish to deploy the environments to Fabric, set the TDM_DEPLOY_ENVIRONMENTS to true. 


### TDM LU Deployment

Deploy the TDM LU to Fabric. **From TDM 7.6 onwards, the deployment of the TDM LU deploys the TDM Portal as well into the TDM web applications**. 

Notes:

- Deploy the TDM LU to the local debug Fabric server using the [soft deploy](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#soft-deploy) option, or stop the TDM jobs on the local Fabric before deploying the TDM LU to a remote Fabric, thus avoiding a parallel execution of TDM jobs on the same TDM DB. 
- The apps.json file in the TMD LU overrides the list of web applications in Fabric. Open the file and edit it before the TDM LU deployment, if needed.


## TDM_LIBRARY LU

The TDM_LIBRARY LU holds utilities that must be copied to the project's LUs. These utilities are described below.

It is recommended to duplicate the TDM_Library LU and use it as a template when creating a new LU in a TDM project.

### Globals

#### LU level [Globals](/articles/08_globals/01_globals_overview.md) 

- Populate the **ROOT_TABLE_NAME** Global using the main source table/s. You can populate several tables, separated by a comma. 

  Examples: 

  - CUSTOMER 
  - CUSTOMER, ACCOUNT 

- Populate the **ROOT_COLUMN_NAME** Global using the entity ID's column. These Globals are needed for setting the IS_INSTANCE_ID column correctly in [TDM_SEQ_MAPPING](/articles/19_Broadway/actors/08_sequence_implementation_guide.md#sequence-mapping) TDM DB table. Note that the number and order of root column names must be aligned with the number and order of the tables, populated in **ROOT_TABLE_NAME**. 

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

- **LU_PARAMS** - parameters table.  Must be added to each LU schema even when it is not required for defining parameters in the LU. The LU_PARAM table holds only the ENTITY_ID and SOURCE_ENVIRONMENT fields.

  Click for more information about [TDM parameters handling](/articles/TDM/tdm_implementation/07_tdm_implementation_parameters_handling.md).

- **TDM_LU_TYPE_RELATION_EID** and **TDM_LU_TYPE_REL_TAR_EID** - TDM relationship tables that map the parent to child IDs. Note that these tables are also created in the TDM DB.

  Click for more information about [TDM Hierarchy implementation](/articles/TDM/tdm_implementation/06_tdm_implementation_support_hierarchy.md).



## TDM_Reference LU

From TDM 7.6 onwards thwe stores the extracted Reference tables in a new LU - TDM_Reference - instead of storing them in Cassandra. Each Reference table is stored as a separate LUI. For more information see [Reference Implementation](09_tdm_reference_implementation.md).


[![Previous](/articles/images/Previous.png)](03_tdm_fabric_implementation_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_tdm_lu_implementation_general.md)
