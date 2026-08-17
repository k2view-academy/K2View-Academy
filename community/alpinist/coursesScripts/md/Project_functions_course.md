# Project Functions

Fabric Project functions are user-defined Java functions that are added to the project implementation.

Functions can be created in Logical Units, References, Web Services, Shared Objects or from existing Table Populations. A function can be defined as a Shared Object and can be used in any object in a project. If a function is defined in an LU, Reference or Web Service, it is accessible only within that specific object.

## Types of Project Functions

Fabric supports six types of Java functions, each distinguished by its purpose, signature, usage, and context:

| Function type | Purpose | Context | Signature *In blue - mandatory |
| --- | --- | --- | --- |
| Decision Function | Determines whether a table population will be executed during the sync process | Sync process | @type(DecisionFunction) @out(name = "decision", type = Boolean.class, desc = "") public static Boolean descisionFunc() throws Exception { } |
| Trigger Function | Executed at the record level when a record undergoes a change (INSERT, UPDATE, or DELETE) | Sync process | @type(TriggerFunction) public static void triggerFunc(TableDataChange tableDataChange) throws Exception { } |
| Enrichment Function | Executed during the sync process, once all the LU populations have been executed | Sync process | public static void enrichmentFunc() throws Exception { } |
| Event Function | Executed as part of the GET process, after the sync process is completed. | GET process | @type(EventFunction) public static void eventFunc(EventDataContext eventDataContext) throws Exception { } |
| LUDB Function | Executed from within an SQL statement | SQL Statement | @type(LudbFunction) @out(name = "result", type = String.class, desc = "") public static String ludbFunc(@desc("") String param1) throws Exception { } |
| Java Function | Regular Java function | Any Fabric object | @out(name = "", type = String.class, desc = "") public static void regularFunc(@desc("") String param1) throws Exception { } |

### Studio integration with functions:

The @type annotation in the function declaration determines where the studio will display this function, such as under the Event Functions list, Trigger Functions list, or Enrichment Functions list.

The function declaration must comply with the function type rules (highlighted in blue above).

### BW integration with functions:

The LuFunction Actor can be utilized in BW flow to invoke a Java function. The functionName input parameter is used to determine which Java function to call. Once set, Fabric will automatically perform the following actions:

- Add the actor's input parameters corresponding to the names specified in the function's input, for example:

@out(name = "result", type = String.class, desc = "")

public static void functionName(@desc("") String param1) throws Exception {

}

- Add the actor's output parameter with the name defined in the @out annotation:
@out(name = "result", type = String.class, desc = "")
public static void functionName(@desc("") String param1) throws Exception {
}

* This @desc("") annotation was introduced for backward compatibility with .NET.
If  is specified, it is shown as a Web service description for Open API spec.

### Functions context

If a function is within the sync context, it can utilize sync process context functions, such as:

- getInstanceID()

- getLastSyncTime()

- getTableName

- and others.

If a function is not running within the sync context, it cannot use these functions. However, if it’s part of the GET process, the LUI is still attached to the session and therefore can be queried.

## How to create functions on the cloud:

- Main Menu → Fabric → New Java Function → Select the desired function type

- Choose LU type name or Shared Objects (if to be shared across all project LUTs)

- Choose Category

Each Category defines a new Package, containing Logic.java file.

- Each package creates a folder with Lofic.java file, under 
workspace/project/Implementation/LogicalUnits/Customer/Java/src/com/k2view/cdbms/usercode/lu/Customer

- Each package creates a folder in the project:

- The Logic.java file serves as the main container for all functions within the category.
It extends the UserCode.
Note: You can view a list of UserCode functions in the main menu under the “Documentation” section on the web page.

- Once the Logic.java file is created, you can add functions by typing the desired function type, and Fabric will automatically generate the function signature.

Alternatively, you can right click “src” and open the below menu:

Using the “New Java Logic file” option will create a new category, while the “New Java file” option can be used if you want to call Java functions from within your Logic files. However, functions written in "New Java file" won't be detected by Fabric Studio.

Note:

- When functions with the same name exist in multiple packages ("Category"), Fabric does not raise an error. Instead, the server will execute one of the functions at random.

- To use a function from a one package ("packageName1") within another package, you can:

- Add the following import statement: import static com.k2view.cdbms.usercode.lu.Customer.packageName1.Logic.*;

- Alternatively, use the full package name when calling the function.

# Decision Function

## What Is Decision Function?

Decision functions are used to control whether a table population should be executed during an LUI sync process. These Java-based functions return a Boolean value:

True: the population will be executed.

False: the population will NOT be executed.

Decision functions can be defined at various levels:

LU Schema: Will be used by all the table that are in ‘Inherit’ sync method.

LU Table: Will be used by all the table populations that are in ‘Inherit’ sync method

Table population:

.Net Studio: Will be used by the specific population

Cloud Studio: Not applicable

### Key characteristics of Decision function

- Decision function is running in the context of the sync process. Therefore, it can use the sync process context functions, such as: getInstanceID(),  getLastSyncTime(), getTableName, etc.

- Decision function take precedence over any Sync Mode (except for sync OFF): Sync ON, Sync FORCE, first sync – all those scenarios will activate the decision function, and accordingly the population will run or not.

- In the event of a schema upgrade, the decision function will take precedence over the schema change. This means that if the decision function returns false, the populations will not run, even though the schema upgrade has occurred. Consequently, the LUI will not go through this schema upgrade again.
To ensure proper handling, every decision function must account for schema changes by using the isStructureChanged() built-in function.

## When to use a Decision Function

Use decision function when syncing a table is bound to rules or pre-checks. The logic if to execute the population is implemented in the decision function’s code.

For example:
Run sync only during off-peak hours.
A Decision function can check the current date and time.

If the current date and time = off-peak, return True to Sync the LUI.

If the current date and time = peak, return False to skip the Sync.\ In this example, it is recommended to use the skipSync() method in the Decision function to perform a one-time execution of the Decision function per LUI (in case all tables inherit the same logic).

## Coding

Function signature:
    @desc("")

@type(DecisionFunction)

@out(name = "decision", type = Boolean.class, desc = "")

public static Boolean descisionFunc() throws Exception {

return true;

}

- Decision functions don’t get an input.

- Decision functions return only one boolean value.

- Must declare @type(DecisionFunction)

### Coding Best Practices:

- If the decision function returns the same result for each population, it’s advisable to set it on the Root Table’s population and invoke skipSync(). This approach allows Fabric to execute the Decision function once per LUI, rather than for each population individually.

- If LU Schema is changing, decision function will still be executed. Use isStructureChanged() in the implementation, to return true, to not block the populations from running
Soma goes to isFirstSync and Sync mode FORCE:
Boolean toRun = (isFirstSync() || isStructureChanged() || getSyncMode().equals(SyncMode.FORCE.name()));

- Since decision functions impact the overall sync process time, it’s important to avoid overloading them with complex processing logic.

- A failure in a decision function will cause the entire LUI sync to fail.

# Trigger Function

## What Is a Trigger Function?

A Trigger function is defined within the LU table and is activated whenever a record in the table is changed, added, or deleted.

The trigger function receives an input called TableDataChange, which provides details about the change, including:

- Table name

- Type of event (Insert/Update/Delete)

- Old values of the record (empty on insert)

- New values of the record (empty on delete)

## When to use a Trigger Function?

Trigger Functions are used to perform an action when a specific set of data or value is inserted, updated or deleted.

For example:

- Track order status and executing special logic when order status changes from Terminated to Activated.

### Key characteristics of Trigger function

- Trigger function is running in the context of the sync process. Therefore, it can use the sync process context functions, such as: getInstanceID(),  getLastSyncTime(), getTableName, etc.
Ticket: https://k2view.freshdesk.com/a/tickets/39207

- Triggers are defined at the table level; you cannot specify trigger for individual fields.

- Trigger functions are executed for every insert, update, or delete operation:

- The trigger will activate even for an update command that doesn’t change any value in the record.

- If a record is updated multiple times, the trigger function will execute each time.

- A Trigger function runs immediately after the transaction is applied, without waiting for the sync to finish successfully or for a commit. As a result, the functionality will execute regardless of the sync process outcome (success or failure).

- Trigger functions are added to the LUI during each GET request, right before the table population is executed, and then removed after the SQLite is committed (to reduce the size of the LUI and avoid complexity of managing removing/adding new triggers, etc). 
If trigger is defined on a table that its population is not being executed during the GET, the trigger will not be created on the LUI.
*Therefore, during sync OFF triggers are not being created

## Coding

@type(TriggerFunction)
public static void triggerFunc(TableDataChange tableDataChange) throws Exception {

}

The Trigger function receives an input called TableDataChange, which provides detailed information about the changes that occurred in the record:

getTable() - Returns the name of the table where the change occurred.

changedFields() - Returns a hashmap containing all fields whose values were changed.

Example: if (tableDataChange.changedFields().get("FIELD_NAME") == ...)

getType() - Returns the type of transaction (INSERT/UPDATE/DELETE).

Example: if (tableDataChange.getType().equals(DataChangeType.INSERT))

oldValues() - Returns a hashmap of fields with their old values.

newValues() - Returns a hashmap of fields with their new values.

Example: if (tableDataChange.newValues().get("ID").equals(tableDataChange.oldValues().get("ID")))

### Coding Best Practices:

- Trigger functions are created and dropped on each GET. Be mindful of this potential overhead when considering the addition of multiple trigger functions

- When a Trigger function is defined to a table, it is activated for any change in any field. If your logic depends on a specific field, make sure to check the old and new values of that field before taking any action.

- When comparing the type of data change, use the DataChangeType enum for accuracy.

- Instead of: tableDataChange.getType().equals("DELETE")

- Use: tableDataChange.getType().equals(DataChangeType.DELETE)

- If a primary key (PK) is not set on the trigger's table, Fabric will insert the same record multiple times instead of updating it.

- When a population is running, Fabric will, by default, delete all records in the table and re-insert them from the source. This triggers SQLite to activate triggers for both deletions and insertions for each record that existed in the LUI and is now being fetched from the source. To avoid this, set the delete mode to "Non-updated" and the Sync Method to "upsert."

- When using “Insert or replace” command, in case the record exists, SQLite deletes the record and inserts it back again. Using “Insert...on conflict update” command to avoid this behavior

# Enrichment Functions

## What Is Enrichment Function?

An Enrichment function is designed to execute specific functionality after all populations have been completed during the sync process.

Enrichment function is set on a table level, and the execution order of all Enrichment functions is set on the LU Schema properties.

### Key characteristics of the Enrichment function:

Enrichment function is linked to an LU table and will be executed only if at least one of the populations were executed during the Sync process.

The function is triggered only after ALL populations within the LU schema have been executed

The execution order of all enrichment functions is determined in the Schema properties.

The Enrichment function runs within the sync process context, allowing it to access and utilize sync process context functions.

## When to use Enrichment Function

As the name implies, an enrichment function enhances the functionality of the LU.

For example:

Populating an LU table with calculated data derived from other LU tables, such as calculating the total amount of a customer's payments and updating this value in the CUSTOMER LU table.

## Coding

Function signature:
@desc("")

public static void functionName() throws Exception {

}

- Enrichment functions don’t have an input or output

### Coding Best Practices:

- Enrichment function is running in the context of the sync process. Therefore, it can use the sync process context functions, such as: getInstanceID(),  getLastSyncTime(), getTableName, etc.

- While enrichment functions are very beneficial, it’s recommended to use population flows for better control and visibility.

- Enrichment functions contribute to the overall sync process time, so avoid overloading them with heavy processing logic.

- When defining multiple Enrichment functions they will run in sequence.

- Since enrichment functions don’t receive input, thread globals are typically used as flags or for sharing information.

- If the enrichment function is used for updating data in a table within the LUI, there’s no need to perform a commit; it will automatically be done by Fabric when the sync is completed.

- A failure in an enrichment function will cause the entire LUI sync to fail.

## Enrichment functions in Cloud studio

Enrichment functions are not supported in the Cloud studio by default.

To enable enrichment functions, do the following:

- Main menu à View à Command Pallet

- Type “setting” and select “Reference: Open Settings (UI)”

- Type “enrichment” in the tab that is opened and check “Show Enrichment List in Schema/table editor”

- ‘Enrichment Order List’ is added to the Schema properties

- ‘Enrichments’ is added to the table lproperties.

# Event Function

## What Is an Event Function?

An Event function is triggered at the final step of the GET process, after the sync process is completed. Users can configure three types of events:

On Sync Success (“SyncSucceeded”)

On Sync Failure (“SyncFailed”)

On Successful Instance Deletion (“DeleteInstanceSucceeded”)

### Key characteristics of Trigger function

Event functions operate outside the sync process, meaning they do not have access to the sync context.

For success or failure GET events, the LUI is attached to the session, allowing it to be queried.

When deleting the LUI, use the EventDataContext parameter to retrieve the necessary information.

## When to use Event Function

An Event function is the first step executed after the instance sync process is completed, allowing you to act based on the outcome - whether the sync was successful, failed, or the instance was deleted.

For example:

- The “SyncSucceeded” event type can be used to update stats table or send notifications to a third-party system.

- The “SyncFailed” event type can be used to handle specific exceptions, allowing you to take actions such as logging the issue as a statistic, sending a notification, or raising an alert.

- The “DeleteInstanceSucceeded” event type is useful when customer data (or any other LUI data) needs to be cleaned from Cassandra lookup tables once the LUI is deleted from Fabric.

## Coding

Function signature:

@desc("")

@type(EventFunction)

public static void functionName(EventDataContext eventDataContext) throws Exception {

}

- Event functions get EventDataContext as input

- Event functions doesn’t return value

- Must contain @type(DecisionFunction)

The EventDataContext data type exposes a set of methods which allow getting additional information about the change such as:

Instance ID getInstanceId()

LU Type name – getLuTypeName()

Exception – getLastException()

### Coding Best Practices:

- Event functions are executed as part of the GET process and run synchronously, which can extend the duration of the GET operation.

- If multiple event functions of the same type are defined, they will be executed sequentially, with each function waiting for the previous one to complete before starting. if you want to run some activities in parallel, do it under the same event function.

- If your event function involves heavy processing, consider implementing asynchronous code:

- Using Java

- By executing your code as a BW Job

- Note: When activating a BW flow from an event function, even if the BW runs its functionality using innerFlowAsync, the GET process will not complete until the innerFlowAsync operation is finished.

- If an exception occurs in the event function, the GET process will fail. Since the event function runs only after the sync process is completed, the sync itself may have finished successfully, with the final changes already committed to storage.

- If "skip sync" is used during the sync process, event functions will not be triggered.

- If "reject instance" is used during the sync process, only DeleteInstanceSucceeded will be triggered.

- When setting thread globals during the sync, ensure they are cleared afterward in both "Sync Succeeded" and "Sync Failed" functions.

- If an exception occurs in the event function, the GET process will fail. Since the event function runs only after the sync process is completed, it's possible that the sync itself finished successfully and the final changes were committed to storage.

- When setting thread globals during the sync, be sure to clear them afterward using two event functions—one set to "Sync Succeeded" and the other to "Sync Failed."

# LUDB Function

## What Is LUDB Function?

An LUDB (Logical Unit Data Base) function is a Project function invoked from an SQL query to perform more complex operations on an LU or reference data than those performed using standard SQL statements.

LUDB functions are invoked from an SQL statement.

LUDB functions must have at least one output value.

The LUDB function is created on the SQLite DB during the GET process, and can then be used by SQL statement.

For example:
String sql = "select  case_date, case_type, fnludbFunc(cases.status) as new_status from case”

try (Db.Rows rows = ludb().fetch(sql,input)😉 {

for (Db.Row row:rows){

yield(row.cells());

}

}

## When to use LUDB Function

Use an LUDB function when the desired logic cannot be achieved through a SQL statement, either due to its complexity or the need for Java’s access to additional resources and libraries.

## Coding

- Decision functions should get input, and return output

- Must contain @type(LudbFunction)

Function signature:

@desc("")

@type(LudbFunction)

@out(name = "result", type = String.class, desc = "")

public static String ludbFunc(@desc("") String param1) throws Exception {

return "ludb-function" + param1;

}

### Coding Best Practices:

LUDB functions should accept input and return output. An LUDB function that does not have or use input parameters will be called multiple times and will return the same result for all rows.

# Regular Java Function

To execute Java function from BW Flow, use LuFunction Actor.

# Reading the GET’s logs

c.k.f.s.FabricSession - START – ATTACH Customer.215

Validate LU type.

Check LUI authentication permissions for the Fabric User.

c.k.f.s.FabricSession - Access to [Customer.215] by user shani.alpinist@k2view,com is authorized.

Verify if there is any MDB already attached to the session that cannot be released due to an ongoing transaction (from the same LU type). If so, an error will occur: "Attached LU can't be detached while in transaction."

c.k.f.s.FabricSession - local get request

Extract the LUI from storage.

Decompress the LUI.

Decrypt the LUI if needed.

Check for schema upgrades by comparing to the LU type definition.

c.k.f.s.FabricSession - START - sync Customer.215

Perform ElevatedPermissions Check

﻿Attach file- MDB file locking.

c.k.f.s.FabricSession - FINISHED - ATTACH Customer.215 (UPDATE)

c.k.f.s.s.l.SyncExecution - Start operation 'Sync Customer.215’

Execute Decision functions, create Triggers and run all populations

Execute Trigger functions

Update k2_objects_info after each population

c.k.f.s.s.l.SyncExecution - End operation 'Sync Customer.215' successfully. [60078ms]

Execute all enrichment functions

c.k.f.s.s.l.SyncExecution - IIDF_LU:5455651083 was synced from source

Perform SQLite commit

Execute Event functions

Remove triggers

Compress the LUI.

Encrypt the LUI if needed.

Save the LUI back to storage.

Clean up resources.

c.k.f.s.FabricSession - FINISHED - sync Customer.215 (UPDATE)

c.k.f.s.FabricSession - FINISHED - Customer.215 (UPDATE)
