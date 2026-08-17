# GLobals_&_MTables

# Globals & MTables

# Globals

## What are Globals?

Globals refer to variables that are accessible from anywhere within a program. This means that they can be used in any class or method without needing to be explicitly passed as arguments.

## Global types

Fabric supports three types of Globals:

- Cluster Globals – defined in the Fabric Studio. Can be accessed by any function or component (unless defined for a specific LUT, and then can be accessed only by functions defined under this specific LUT).

- Session Globals – created on-the-fly at a session level and accessible only within that specific session.

- Thread Globals  - created on-the-fly at a GET level and are accessible only within that specific sync process

## Cluster Globals

Cluster Globals are defined in the Fabric Studio:

- If defined in the Shared Objects they can be accessed by any function or component in the project.

- If defined under a specific LUT, they can be used any function defined under this LUT.

### Define Cluster Globals

There are two locations within Fabric Studio to define Cluster Globals:

SharedGlobals.java: Found under Shared Objects/Java/src. 
Globals defined here are accessible to all project components, including BW flows, functions, web services, common tables, etc.

Globals.java: Located under each Logical Unit/Java/src (except for web services). Globals defined in this file are only accessible to components created within that specific Logical Unit.

All Globals defined in either the SharedGlobals.java or Globals.java are deployed with their default (initial) values as specified on their declaration.

Note:
If a Global is defined at both the Shared Objects level and the Logical Unit level, the definition in the Logical Unit takes precedence within its scope. Other Logical Units will use the Shared Objects definition.

When a global is defined in SharedGlobals.java, it is “inherited” by all Logical Unit types. However, when a global is defined in a specific Logical Unit (Globals.java), it is limited to that specific Logical Unit.

For example:

- Global defined under SharedObjects:

- Global defined under Customer LUT:

- After deploying the Globals, you can see them using Fabric SET command:

Note:

- The first "Global" prefix is displayed since the SET command also displays non Globals variables.

### Retrieve Cluster Global value

There are multiple ways to retrieve the value of a Cluster Global, depending on the method you're using:

- Using Java Code:
To get the Cluster Global value, use one of the following methods:

- getGlobal(String globalName, String lu) - Retrieves the Logical Unit's global value for this session.
Example:
UserCode.getGlobal("CLUSTER_GLOBAL_TEST", "Customer");

- getGlobal(String globalName) - Retrieves the global value for this session.
Example:
UserCode.getGlobal("CLUSTER_GLOBAL_TEST");
Note: If there is a conflict in global values between Logical Units, an exception will be thrown.

- Using the Global Name Directly in Code:
Every Logic.java file imports the Global declaration files, allowing you to directly use the Global name in your code. 

For example:

// Import shared Globals

import com.k2view.cdbms.shared.Globals;

// Import Globals from the Logical Unit

import static com.k2view.cdbms.usercode.lu.<LU name>.Globals.*;

if(CLUSTER_GLOBAL_TEST.equals("…")) {

// Perform logic based on the global value

}

- Using Fabric SET Command:
Use the following code to fetch the Cluster Global value:
fabric().fetch("set CLUSTER_GLOBAL_TEST").firstValue();
*See comment in “Override Cluster Global value”

- Using BW Actors:
Use FabricSetRead to retrieve the Cluster Global value.
*See comment in “Override Cluster Global value”

## Session Globals

Globals can also be declared and used at a session level. In this case, the Globals are defined on-the-fly within the session and are terminated once the session ends.

- To create and set (or modify) a session global value, use the following command:
fabric().execute("set GLOBAL_NAME=GLOBAL_VALUE");  // Setting a session global

- To retrieve the value of a session global, use:
fabric().fetch("set GLOBAL_NAME").firstValue();  // Retrieving a session global

For BW flows, use FabricSetRead and FabricSet BW Actors to read and set the session Globals.

Note: set; command:

- Specifying a session variable does NOT create it as Shared Object. Therefore, even though it is treated as a global variable, it will not appear under each LUT unless explicitly created for it. Instead, it is displayed as a standalone variable without any global or LUT prefixes.

## Thread Globals:

Thread Globals are designed for use exclusively within GET operations, enabling the sharing of values across populations, decision functions and enrichment functions. These Globals are defined dynamically, on-the-fly, within the thread and automatically terminated when the GET operation completes.

Methods to work with Thread Globals:

- setThreadlobals( key,  value) - create Thread Global

- getThreadGlobals(String Key) - get Thread Global value

- clearThreadGlobals() - Clear all Thread Globals, created on the thread level

Note:

- Thread Global cannot override Session Global.

- Unlike Cluster or Session Globals, Thread Globals also support data types beyond just strings.

## Override Cluster Global value

If the final keyword is added to a Cluster Global definition, the Global's value becomes immutable and can only be changed by redeploy.

public final static String  RECORDS_LIMIT = ...

When final is not declared, Cluster Global value can be overridden with or without re-deploying the project, at the following levels:

- Implementation level – by updating the SharedGlobals.java or Globals.java files and re-deploying

- Environment level – by modifying the Environment file in Fabric Studio and re-deploying

- Cluster level – using the set_global global command on run-time.

- Session level – using the SET command on run-time.

### Overriding Cluster Globals on Fabric Studio level

In case the value of the Cluster Global was not changed using set_global command, you can change the value of the global in the environment file, or, if not in use, on the SharedGlobals.java or Globals.java file.

### Overriding Cluster Globals on environment level

The default values for Cluster Globals, defined in the SharedGlobals.java and Globals.java files, are used for the _dev environment, which is the default environment for each cluster. However, these defaults can be overridden for other environments.

When creating a new environment, a Globals tab is available that allows you to modify the values of the Cluster Globals (as defined in SharedGlobals.java and Globals.java) specific to that environment.

Once an Environment is applied to a cluster, the Cluster Globals will use the default values defined for that environment.

To switch environments and assign the appropriate global values, use the SET ENVIRONMENT command:

- set environment='UAT' to apply the default values defined for the UAT environment.

- set environment='_dev' to revert to the default values defined in the SharedGlobals.java and Globals.java files for the development environment.

### Overriding Cluster Globals on a cluster level

Cluster Global value can be overridden for the entire cluster, for all running sessions.

- To change global value, use the set_global command:
set_global global '*.<PARAM_NAME>[=<PARAM_VALUE>]';

- Shared Global can be overridden for a specific LUT.

To change global value but only on a specific LUT, use:
set_global global 'LUT_NAME.<PARAM_NAME>[=<PARAM_VALUE>]'

Note:

- Set_global can be used only for Cluster Globals. You cannot define Cluster Global on-the-fly

- If the set_global global command is using a specific LUT, when fetching this value later, using SET command or getGlobal function, Fabric will throw exception in case value is different between the LUTs.

### Overriding Cluster Globals on a session level

Cluster Global value can be overridden on a specific session scope. The Global value will not be changed for any other running sessions.

#### Using Fabric SET command:

- To override Cluster Global on a session level:

- fabric().execute("set CLUSTER_GLOBAL_TEST='newValue1'");  à change the Value for all LUTs

- fabric().execute("set Customer.CLUSTER_GLOBAL_TEST='newValue1'");  à Changes the value only for Customer LUT

#### Using BW Actors:

- FabricSet – to change the global value

- FabricSetRead – to retrieve the global value.

Note:
When a Cluster Global is overridden at the session level, it will appear as a "new" global in the SET command, which is a presentation feature indicating that the value has changed.

### Overriding Cluster Globals – value priorities

| Level​ | Priority​ | Where to define​ | How to override​ | How to retrieve​ | How to reset​ |
| --- | --- | --- | --- | --- | --- |
| Global​ | 4​ | SharedGlobals.java Globals.java​ | Deploy​ | GetGlobal​ function SET​ command GLOBAL_NAME BW - fabricSetRead​ |  |
| Environment​ | 3​ | Environment​ | Deploy​ | GetGlobal​ function SET​ command GLOBAL_NAME BW - fabricSetRead​ |  |
| Runtime - cluster​ | 2​ | Runtime​ | set_global command​ | GetGlobal​ function SET​ command GLOBAL_NAME BW - fabricSetRead​ | set_global global ‘*.GLOBAL_NAME’​ |
| Runtime - session​ | 1​ | Runtime​ | SET command​ BW - fabricSet | GetGlobal​ function SET​ command BW - fabricSetRead​ | SET GLOBAL_NAME=’’​ |

Note:

- A Global value will always take the value set on the lowest level:

- First priority – Session level (SET command)

- Second priority – Cluster level (set_global command)

- Third priority – Environment file

- Last priority – SharedGlobals.java / Globals.java

- When a Cluster Global is overridden at a lower level, subsequent changes made at a higher level will not affect the lower levels. To allow changes at the upper level to propagate to the lower levels, the global must be reset at the lower level.
For example:

- If a Cluster Global is modified at the session level using the SET command, changing it at the cluster level using get_global will not update the session-level value.

- If a Cluster Global is modified at the cluster level using the set_global command, changing it in Studio and redeploying will not affect the value.

- A Cluster Global can be overridden for a specific LUT level. After being overridden, the global behaves independently:

- Changing the Cluster Global with set_global, without specifying a specific LUT, will not alter its value.

- To reconnect the global to its higher levels, it must first be reset using the specific LUT.

- Cluster Globals can be modified or overridden at the cluster or session level.

- getGlobal, the SET command, and BW Actors will return the session-level Global value, while using the global name directly will always return the cluster-level value.

## Reset Cluster Global value

### Resetting a Session Global to the Cluster Global Value:

Running set <PARAM_NAME> = '' will reset the session-level global value to its original value based on the following priority:

- The value set using the get_global command.

- The value defined in the Environment file.

- The value defined in SharedGlobals.java or Globals.java.

Note:

- If the Cluster Global was modified for a specific LUT, you must also reset it specifically.

- The SET command will always display the values for the specific session, which may differ from their current values in the Cluster.

### Resetting a Cluster Global to Its Default Value:

To reset a Cluster Global to its default value, use the set_global command without specifying =<PARAM_VALUE>.

Resetting will revert the value to its default as defined in the Environment file, or if no environment is in use, to its default in SharedGlobals.java or Globals.java.

Note: If the Global was modified for a specific LUT, you must reset that LUT’s Global explicitly. Resetting for *.Global will not reset the specific LUT’s Global.

## How it works?

Fabric uses the k2system.global_settings table to store Globals data and any overridden values, including those set by the set_global command and Environment settings. This ensures that the Globals data is retained when Fabric restarts.

When the set_global command is executed, Fabric updates the global_settings table and uses fabric-jdbc (TCP) to notify other nodes about the change.

## Summary

- Cluster Globals are defined and set on the SharedGlobals.java or Globals.java files.

- If Environment file is in use, their values will be taking precedence over their values in the SharedGlobals.java or Globals.java file

- Cluster Globals can be changed on the cluster level using set_global global command.

- Once Cluster Globals were changed using set_global command, changing their values in Environment file or, if not in use, in the SharedGlobals.java or Globals.java files and running deploy, will not change their values in the cluster.

- Changing values in Environment file or, if not in use, in the SharedGlobals.java or Globals.java files and deploying, will change the Globals values only if their values in the cluster were equal to their values in the files.

- To revert Cluster Globals to their original value, use get_global global command without ‘=<value>’ parameter. Their values will be reverted to the values defined in the Environment file or, if not in use, to values defined in the SharedGlobals.java or Globals.java files.

- FabricSet and FabricSetRead are executing SET command

## Using globals in select statements

A global can be used in an SQL statement in an LU function. The syntax is: '@[global_name]@'.

For example:

public static String SCHEMA_NAME = "public";

public static Integer RECORDS_LIMIT = 5;

Table population, sourceDbQuery Actor: 
select * From @SCHEMA_NAME@.activity limit @RECORDS_LIMIT@

Same structure for each BW DB Actors and code in Java

String sql = "SELECT * From ACTIVITY WHERE CUSTOMER_ID = ? AND ACTIVITY_ID = ? AND NEW_NOTE_IND = @NEW_IND@";

ludb().fetch(sql, input1, input2).each(row->{

yield(row.cells());

});

## Session Globals Integration in Job Execution

Jobs automatically receive the session_scope as part of their arguments, which includes Session Globals.

This means that when a Session Global is set and a job is run within the same session, the job will inherit these global values and operate accordingly.

For example:
{"session_scope":"{\"scope\":{\"EXECUTION_ID\":\"8f5dcec4-0125-4915-88c8-daf8003519eb\",\"GLOBAL_TEST\":\"10\",\"LOG_ID\":\"b4050000000000bd\"}}"}

The same behavior applies to migration commands.

{"FABRIC_COMMAND":"sync_instance TestLU.?","JOB_UID":"","session_scope":"{\"scope\":{\"EXECUTION_ID\":\"8f5dcec4-0125-4915-88c8-daf8003519eb\",\"GLOBAL_TEST\":\"10\",\"LATEST_BATCH\":\"45d294f5-bf9a-47af-8e9f-070826397321\",\"IS_IN_BATCH_PROCESS_PROCESS\":\"true\",\"LOG_ID\":\"b4050000000000bd\"},\"user\":\"{\\\"type\\\":\\\"AuthenticatedUserByCredentials\\\",\\\"username\\\":\\\"mariana.shinnara@k2view.com.k2v\\\",\\\"authenticator\\\":\\\"SAML\\\",\\\"authnType\\\":\\\"SAML\\\",\\\"roles\\\":[\\\"t-07472613-b67d-4e85-955d-2bf9638c5848_k2v_user\\\",\\\"Everybody\\\"],\\\"authenticationTime\\\":1728458788314}\"}","SRC_DB_INTERFACE_NAME":"Unknown","sync_mode":"ON","EXECUTION_ID":"8f5dcec4-0125-4915-88c8-daf8003519eb","JOB_AFFINITY":"","INSTANCES_LIST":"1,2,3,4,5","IS_ASYNC":"false","lu_name":"TestLU","ALLOW_MULTIPLE":"false","COMMAND":"batch TestLU.('1','2','3','4','5') fabric_command=\"sync_instance TestLU.?\"","bid":"45d294f5-bf9a-47af-8e9f-070826397321","environment_name":"_dev"}

Note: The BroadwayJob actor currently does not receive the session_scope. This will be addressed in upcoming releases.

## Coding best practice

- Using set_global affects the entire cluster. Instead, use session-specific globals within your schema functions (such as populations or other LU functions like decisions and events).
For example, if a root function doesn't use the linking field as input, use a session global to add logic that ensures it executes only once.

- Always clear session or Thread Globals at the end of your function or GET process. This ensures that if another GET runs later in the same session, the session values will be reset.

# MTables

## What Is an MTable?

An MTable is an object created in Fabric memory from a CSV file. It stores reference data as part of the Fabric project, allowing for fast in-memory lookups during runtime. 
MTables are best suited for small, static lists of reference data.

Note: MTable replaces the Translation tables in Cloud Studio.

## How to create MTable?

Using a CSV File in the MTable Folder:

When deployed, an MTable is created in Fabric memory based on the CSV file's structure and data and made available on all Fabric nodes (other files in the folder are ignored).

On a Fabric restart, the memory is released, and the MTable is reloaded.

Each MTable can be accessed from any LU, regardless of where its CSV file is located in the project.

At Runtime with MTableLoad Actor:

A new MTable created at runtime is available on a single node.

To distribute it across nodes, use SET CLUSTER_DISTRIBUTE_AFFINITY = ALL.

MTables created or updated at runtime are lost after a Fabric restart.

Note: SET CLUSTER_DISTRIBUTE_AFFINITY = is a new command, for distributing the subsequent command to the specified affinity. Use ALL to distribute the subsequent command to all live nodes.

Notes:

Reloading an MTable deletes all existing records.

If an MTable is created with an existing name, it replaces the previous one in memory when deployed.

Data lookup can be done using one or more keys. Search indices are created during the first lookup, based on the search keys.

## MTables Storage Settings

By default, MTables are stored in Fabric memory for fast data lookup. However, they can be stored in FabricDB under certain conditions:

When a joint query between MTable data and LU data is needed.

Due to the size of the MTable.

To change MTable storage to FabricDB, modify the config.ini.[fabricdb].FABRICDB_MTABLE_LIMIT parameter:

-1: Keep all tables in memory (default).

0: Store all tables in FabricDB.

>1: Any MTable exceeding the specified row limit is stored in FabricDB; smaller tables remain in memory.

MTables that are stored in FabricDB, are kept in mtables.db SQLite DB.

Indexes are created on-the-fly when querying the MTable using the lookup functionality (actor or mtable package functions).

The MTable can be also queried like regular SQLite table:

## How to work with MTable?

- Broadway Actors:

- MTableLookup - fetching data from an MTable by the given key(s). 
If no keys are provided, the entire MTable dataset is returned (array of objects). 
The search indices are created on-the-fly during the first search.

- MTableRandom - for fetching a random row from an MTable. 
The random selection can be limited by providing an input key(s). 
This Actor returns one object only. 
The search indices are created on-the-fly during the first search.

- MTableLoad - creating a new MTable dataset or replacing an existing one in the Fabric memory. 
The MTable is then created on one node and must be distributed to other nodes.

- Java code:
Use com.k2view.fabric.common.mtable package.
For example:

- Create MTable:
ArrayList<Object[]> rows = new ArrayList<>();
Object[] row1 = new Object[] { "value1","value2" };
Object[] row2 = new Object[] { "value3","value4" };
rows.add(row1);
rows.add(row2);
MTables.create("mtable_name",new String[]{"col1","col2"},rows);

- Read MTable based on keys:
MTable mtable = MTables.get("mtable_name");
Map<String, Object> keys = new HashMap();
keys.put("lu_name", getLuType().luName);
List<Map<String, Object>> mtResults  = mtable.mapsByKey(keys, MTable.Feature.caseInsensitive);

- Read all records from MTable:
AllRows<Map<String, Object>> mtableRows = MTables.get("mtable_name").allMaps();

- Graphit

- The below syntax returns the first matching MTable row:
mtable('<mtable_name>').mapByKey({'<key>':'<value>'})

- The below syntax returns the value of a specific MTable column
mtable('<mtable_name>').mapByKey({'<key>':'<value>'})[col_name]
