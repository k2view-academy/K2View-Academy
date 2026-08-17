# Batch

# Batch

# What is a Fabric Batch?

Fabric Batch is a built-in utility that executes a fabric command on a list of threads, leveraging multiple Fabric nodes for parallel execution.

Key Features

Distributed Execution
Configure which nodes will participate in running the batch across instances.

Dynamic Load Balancing
Control the number of threads per node for optimal resource usage during execution.

Real-Time Monitoring
Track batch progress and performance by cluster, data center, node, or IID
(via CLI or the Batch Monitor Dashboard).

Failure Recovery
Automatically handles unresponsive nodes to ensure smooth execution.

Pause & Resume
Supports stopping and resuming of long-running migration processes.

Detailed Tracking
Monitor execution time, duration, responsible node, and failure diagnostics at the entity level.

Supported Use Cases:

Instance Sync (Migration Process):
Runs sync operations across selected IIDs in a Logical Unit using the migrate command:

BATCH LU ('LUI','LUI2','LUI3','LUI4') FABRIC_COMMAND="sync_instance LU.?" with ASYNC='true';

Broadway Flows:
Executes a Broadway flow for each instance in a batch:

BATCH LU FABRIC_COMMAND="broadway LU.SampleFlow SampleIID=?" with async=true;

For example: 
Consider a Broadway flow that generates report data for a single instance and stores the result in a database table.

The flow performs the following steps:

Defines an external parameter named IID, which receives its value from the batch command

Executes a GET operation on the given IID

Calculates the report and loads the results into the database

You can use the batch command to run this flow across a list of IIDs:
batch Customer5 from CRM_DB using ('select customer_id from public.customer') fabric_command='broadway Customer5.calculateIIDReport IID=?' with async=true;

Once the batch completes, the aggregated report data will be available in the database.

# Batch command

The Fabric Batch command allows you to execute a fabric_command across a set of instances, with full control over execution behavior and distribution.

Each Batch Command Defines:

List of Instance IDs (IIDs) – Which instances to process

Fabric Command – The operation to run on each instance

Execution Nodes – Which nodes in the cluster will participate

Thread Configuration – Number of threads per node (each handling one IID)

## Syntax

BATCH IID_LIST FABRIC_COMMAND=FABRIC_COMMAND

[WITH

[AFFINITY='<affinity>']

[JOB_AFFINITY='<job_affinity>']

[ASYNC=true|false]

[GENERATE_ENTITIES_FIRST=true|false]

[ALLOW_MULTIPLE=true|false]

[MAX_WORKERS_PER_NODE=<number>]

[ESTIMATED_ENTITIES_COUNT=<number>]];

## IID_LIST Options

1. Full Fabric Population

All instances from source:
batch Customer fabric_command='<fabric command>'
* query is built automatically using the SourceDbQuery of the root table

Based on existing entity table records:
batch Customer from fabric fabric_command='<fabric command>'

Use parent IIDs to sync all child IIDs:
BATCH PARENT from fabric fabric_command='sync_instance CHILD.?';

2. Subset of Instances

Explicit list:
batch Customer.('1','2','3') fabric_command='<fabric command>'

Instance Group:
batch Customer.ig10CustomersList fabric_command='<fabric command>'

Source query:
batch Customer from CRM_DB using ('select customer_id from CUSTOMER where customer_id <= 1000') fabric_command='<fabric command>'

Starting Fabric 8.3 - 
added binding for the select query – if we have many small migrations, better to use binding.
Batch CustomerLu using ("select customer_id from customer where cycle_no=${cycle_no}") fabric_command="sync_instance CustomerLu=?";

Broadway result set (must return IID list):
batch Customer from fabric using ('<BROADWAY>') fabric_command='<fabric command>'
Note: The BROADWAY command, executed within the ‘USING’ clause, will run as if the RESULT_STRUCTURE=CURSOR

3. Messaging Queue

Dynamic instance set from a Kafka topic:
batch Customer from KafkaInterface1 using ('topic1') fabric_command='<fabric command>'
Note: Use batch_cancel to stop the process when using a dynamic feed like Kafka.

Example:
batch test1 from fabric USING('BROADWAY test1.testSub ,\"topic\"=${topic} \"interface\"=\"${interfaceName}\"') fabric_command='sync_instance test1.?' WITH ASYNC=true;


The flow returns iterate of IIDs, until it reaches a message with the word ”DONE”

Version 8.3

### BATCH Command Enhancements: Binding Parameters

The BATCH command has been upgraded to support binding parameters. This allows you to dynamically inject values into the SELECT query that runs on the source interface.

You can now specify one of the following new input parameters:

- BIND_PARAMS — A list of binding parameters used in the SELECT query on the source interface.

- BIND_PARAMS_SEPARATOR — Defines the separator between parameters in BIND_PARAMS (optional).
 Default separator: ,

- BIND_PARAMS_JSON — A JSON-formatted array of binding parameters used in the SELECT query on the source interface.

### Example

Select all customers with customer_id <= 1000 and customer_id > 10 from the CRM_DB interface and migrate them into the CUSTOMER LU:

Using BIND_PARAMS:

BATCH CUSTOMER FROM CRM_DB USING ('select customer_id from CUSTOMER where customer_id <= ? and customer_id > ?')
FABRIC_COMMAND="sync_instance CUSTOMER.?" with async='true'
BIND_PARAMS='1000|10' BIND_PARAMS_SEPARATOR='|';

Using BIND_PARAMS_JSON:

BATCH CUSTOMER FROM CRM_DB USING ('select customer_id from CUSTOMER where customer_id <= ? and customer_id > ?')
FABRIC_COMMAND="sync_instance CUSTOMER.?" with async='true'
BIND_PARAMS_JSON='["1000","10"]';

## FABRIC_COMMAND Options

This is the operation to be executed per IID. It must contain ? as a placeholder for the instance ID.

Examples:

Sync (legacy migrate command):
"sync_instance Customer.?"

Run a Broadway flow:
"broadway Customer5.SampleFlow SampleIID=?"

Republish via CDC:
"cdc_republish_instance Customer.?"

## Command Parameters (WITH Clause):

| Parameter | Description |
| --- | --- |
| AFFINITY | List of nodes or data centers to execute the Fabric Command on |
| JOB_AFFINITY | Affinity for the batch job itself |
| ASYNC | If true, runs without job mechanism (default: false) |
| GENERATE_ENTITIES_FIRST | If true, pre-generates all entities before execution. Mostly used for checking the performance of the functionality fetching the instance group. |
| ALLOW_MULTIPLE | Allows concurrent execution of the same batch command (default: false). When ALLOW_MULTIPLE is set to true, a unique UID is generated for a batch process, allowing running the same command again - before the first command is completed. The reason for that can be for example, when the subset of instances is created based on a random entity selection. |
| MAX_WORKERS_PER_NODE | Limits the number of worker threads per node (cannot exceed the config-defined max) |
| ESTIMATED_ENTITIES_COUNT | Estimated number of entities to process (for monitoring/statistics only). Used until generate_iid_list ends or exceed the provided estimation |
| MAX_NODES | The maximum number of Fabric nodes that can participate in the batch process (random nodes). If Affinity is used – select random from the suitable nodes. Can be used, for example, when the number of connections is limited on the source side |

## Command Output

Batch_id – unique identifier of the batch process

Execution_id – Used internally by Fabric

Looking at the logs:

INFO  2025-04-29 16:27:41,616 [LIDde030000000020cc] [TerminalWebSocket] c.k.f.s.c.BatchProcessCommand - Starting sync batch id '211c5aa6-de41-4dde-9a08-ad1c97de4303' on lu 'Customer5'

INFO  2025-04-29 16:27:41,619 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessStatisticsCollector - Batch process id '211c5aa6-de41-4dde-9a08-ad1c97de4303' was changed to 'NEW'

INFO  2025-04-29 16:27:41,622 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessStatisticsCollector - Batch process id '211c5aa6-de41-4dde-9a08-ad1c97de4303' was changed to 'GENERATE_IID_LIST'

INFO  2025-04-29 16:27:41,622 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessSteps - Start operation 'Fetching entities list for batch 211c5aa6-de41-4dde-9a08-ad1c97de4303'

INFO  2025-04-29 16:27:41,623 [LIDde030000000020cd] [batchProcessWorker_211c5aa6-de41-4dde-9a08-ad1c97de4303_node_dev-fabric-deployment-848b7d9f89-ddm97] c.k.c.b.BatchProcessNodeCommunicator - Creating new connection to node 'dev-fabric-deployment-848b7d9f89-ddm97 (ip address: 10.176.6.3, Port: 5124)' for batch process '211c5aa6-de41-4dde-9a08-ad1c97de4303'

INFO  2025-04-29 16:27:41,623 [LIDde030000000020cd] [batchProcessWorker_211c5aa6-de41-4dde-9a08-ad1c97de4303_node_dev-fabric-deployment-848b7d9f89-ddm97] c.k.c.b.BatchProcessNodeCommunicator - Sending set command: "set sync='ON',environment='_dev',is_resume='false',SKIP_VERIFY_REF_SYNC='true',from='{"scope":{"EXECUTION_ID":"1d09df75-de22-494e-830b-bc80f6dda287","LATEST_BATCH":"211c5aa6-de41-4dde-9a08-ad1c97de4303","IS_IN_BATCH_PROCESS_PROCESS":"true","LOG_ID":"de0300000000004f"},"user":"{\"type\":\"AuthenticatedUserByCredentials\",\"username\":\"shani.alpinist@k2view.com\",\"authenticator\":\"SAML\",\"authnType\":\"SAML\",\"roles\":[\"t-b78d1b60-aa9d-47a9-bf84-703d7a1ce600_k2v_admin\",\"t-b78d1b60-aa9d-47a9-bf84-703d7a1ce600_k2v_user\",\"Everybody\",\"t-b78d1b60-aa9d-47a9-bf84-703d7a1ce600_k2v_cloud_user\"],\"authenticationTime\":1745937478096}"}'" to node 'dev-fabric-deployment-848b7d9f89-ddm97 (ip address: 10.176.6.3, Port: 5124)'

INFO  2025-04-29 16:27:41,625 [LIDde0300000000004f] [batchProcessWorker_211c5aa6-de41-4dde-9a08-ad1c97de4303_node_dev-fabric-deployment-848b7d9f89-ddm97] c.k.c.b.BatchProcessNodeCommunicator - Sending command 'Starting batch process 211c5aa6-de41-4dde-9a08-ad1c97de4303' to node 'dev-fabric-deployment-848b7d9f89-ddm97 (ip address: 10.176.6.3, Port: 5124)'

INFO  2025-04-29 16:27:41,625 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessSteps - End operation 'Fetching entities list for batch 211c5aa6-de41-4dde-9a08-ad1c97de4303' successfully.  [3ms]

INFO  2025-04-29 16:27:41,629 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessStatisticsCollector - Batch process will be completed after handling all 32 instances

INFO  2025-04-29 16:27:41,630 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessStatisticsCollector - Batch process id '211c5aa6-de41-4dde-9a08-ad1c97de4303' was changed to 'IN_PROGRESS'

INFO  2025-04-29 16:27:41,633 [LIDde0300000000004f] [SyncInstance:Customer5.215_tid:4520] c.k.f.s.FabricSession - START - ATTACH Customer5.215

INFO  2025-04-29 16:27:41,634 [LIDde0300000000004f] [SyncInstance:Customer5.217_tid:4522] c.k.f.s.FabricSession - START - ATTACH Customer5.217

INFO  2025-04-29 16:27:41,634 [LIDde0300000000004f] [SyncInstance:Customer5.216_tid:4521] c.k.f.s.FabricSession - START - ATTACH Customer5.216

INFO  2025-04-29 16:27:41,634 [LIDde0300000000004f] [SyncInstance:Customer5.219_tid:4524] c.k.f.s.FabricSession - START - ATTACH Customer5.219

INFO  2025-04-29 16:27:41,634 [LIDde0300000000004f] [SyncInstance:Customer5.218_tid:4523] c.k.f.s.FabricSession - START - ATTACH Customer5.218

INFO  2025-04-29 16:27:41,635 [LIDde0300000000004f] [SyncInstance:Customer5.215_tid:4520] c.k.f.s.FabricSession - Access to [Customer5.215] by user shani.alpinist@k2view.com is authorized.

INFO  2025-04-29 16:27:41,635 [LIDde0300000000004f] [SyncInstance:Customer5.215_tid:4520] c.k.f.s.FabricSession - local get request

INFO  2025-04-29 16:27:41,635 [LIDde0300000000004f] [SyncInstance:Customer5.216_tid:4521] c.k.f.s.FabricSession - Access to [Customer5.216] by user shani.alpinist@k2view.com is authorized.

…

INFO  2025-04-29 16:27:42,138 [LIDde030000000020d6] [batchProcessStats_211c5aa6-de41-4dde-9a08-ad1c97de4303] c.k.c.b.BatchProcessStatisticsWorker - Statistics for batch process 211c5aa6-de41-4dde-9a08-ad1c97de4303 - succeeded: 32, failed: 0. Total duration: 0sec

INFO  2025-04-29 16:27:42,139 [LIDde030000000020d6] [batchProcessStats_211c5aa6-de41-4dde-9a08-ad1c97de4303] c.k.c.b.BatchProcessStatisticsWorker - StatisticsWorker for batch process 211c5aa6-de41-4dde-9a08-ad1c97de4303 ended

INFO  2025-04-29 16:27:42,139 [LIDde0300000000004f] [batchProcessWorker_211c5aa6-de41-4dde-9a08-ad1c97de4303_node_dev-fabric-deployment-848b7d9f89-ddm97] c.k.c.b.BatchProcessWorker - BatchProcessWorker for batch process '211c5aa6-de41-4dde-9a08-ad1c97de4303' for node dev-fabric-deployment-848b7d9f89-ddm97 (ip address: 10.176.6.3, Port: 5124) ended

INFO  2025-04-29 16:27:42,139 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessSteps - Batch process was too fast. Waiting 491ms before updating to DONE...

INFO  2025-04-29 16:27:42,634 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessStatisticsCollector - Batch process id '211c5aa6-de41-4dde-9a08-ad1c97de4303' was changed to 'DONE'

INFO  2025-04-29 16:27:42,634 [LIDde030000000020cc] [TerminalWebSocket] c.k.c.b.BatchProcessAPI - Batch process 211c5aa6-de41-4dde-9a08-ad1c97de4303 was completed

# Managing Batch Processes

## Canceling a Running Batch

Use the batch_cancel command to stop a running batch process.

- batch_cancel '<batch_id>';
Cancels the specified batch process, regardless of the coordinating node.

- batch_cancel;
Cancels the most recent async batch process started in the current session..

## Pausing a Running Batch

Use the batch_pause command to pause an active batch. The batch status will be set to PAUSED, and it can later be resumed using batch_retry command.

- batch_pause '<batch_id>';
Pauses the specified batch process.

- batch_pause;
Pauses the most recent async batch process started in the current session.

- Applicable only for async batch process.

## Resuming a Paused or Cancelled Batch

Use the batch_retry command to resume a paused batch, or optionally retry a cancelled one.

- batch_retry '<batch_id>' [allow_cancelled=true|false];

Note:

- Applicable only for async batch process.

If the batch completed before the pause, only failed instances will be retried.

If the batch was paused mid-execution, all remaining unprocessed instances will be executed.

The allow_cancelled flag (default: false) determines whether cancelled batches can be retried. Set to true to allow retrying a cancelled batch.

Use batch_pause when you plan to stop the batch temporarily:

A fix is needed (e.g., in the DB or implementation).

After the fix, you can use batch_retry to continue processing both failed and unprocessed entities.

Use batch_cancel when you do not intend to resume the batch:

The process is being terminated permanently.

No further execution or retry is expected.

## Editing Batch Parameters at Runtime

Use the batch_edit command to update certain parameters of a running batch.

- BATCH_EDIT ['<batch_id>'] param1=<value1> param2=<value2>

Supported Parameters:

MAX_WORKERS_PER_NODE – Set the number of workers per node (must not exceed the limit in config.ini).

Note: All batch processes share the total worker pool per node.

# Legacy Support: Migrate Command

The migrate command is a legacy, simplified form of the batch command, specifically designed for migrating instances into the Fabric database.

MIGRATE <LU>[@<DC>] WITH ASYNC='true';

How It Works:

Behind the scenes, Fabric translates the migrate command into a batch command. This means all Batch-related options and parameters can be used with migrate—except you do not need to explicitly define the FABRIC_COMMAND.

Example Equivalence:

The following two commands are functionally identical:

- MIGRATE Customer@DC1;

- BATCH Customer@DC1 FABRIC_COMMAND='sync_instance Customer.?';

Use migrate for convenience when running standard sync operations. For advanced control, use batch directly.

# Batch Monitoring Commands

Fabric provides a set of commands to monitor, inspect, and analyze the execution of batch processes.

## List batch processes

batch_list [STATUS='<status>'] [FROM_DATE='<from date>'] [TO_DATE='<to date>'] [FILTER='<filter criteria>'];

Description:

Lists batch processes based on status, time range, or specific filters.

If no arguments are provided, only active batch processes are listed.

Options:

STATUS: Possible values: NEW, GENERATE_IID_LIST, IN_PROGRESS, FAILED, CANCELLED, DONE, ALL

FROM_DATE, TO_DATE: Use DATE_FORMAT or DATETIME_FORMAT as formatted in config.ini

FILTER: Filters batch processes based on a substring or regex match within the batch command, fabric command, or execution ID.

Examples:

batch_list STATUS='ALL';

batch_list STATUS='ALL' FILTER='sync_instance';

The second command returns the same results as migrate_list STATUS='ALL';

## View Batch Command Details

batch_info '<batch_id>';

Returns:

Full batch command

LU Name

Command Type (e.g., Sync, Broadway)

Fabric Command (e.g., "sync_instance Customer5.?", “broadway Customer5.calculateIIDReport IID=?”)

Max workers per node

Entity inclusion logic (e.g., "select customer_id from public.customer limit 30")

Source interface (e.g., "CRM_DB")

Flags and parameters:

Generate Entities First

Affinity

Job Affinity

Async

Max Nodes

Allow Multiple

Execution ID

Environment

## View Batch Execution Summary

batch_summary '<batch_id>';

Provides:

Stats Levels:

Per Node

Per Data Center (DC)

Per Cluster

Timing:

Start and end time

Total duration

Progress Estimation:

Remaining time and instances (available after IID generation)

Statistics:

Number of instances: synced, failed, added, updated, unchanged (according to the ADD/UPDATE/SKIP result of the GET)

Pace Metrics:

Sync rate per the last BATCH_PACE_CALC_TIME_WINDOW_MS time window (default 10 seconds)

## View Currently Running Instances

batch_in_process FILTER='<filter regex>';

Displays active instance processing across all running batch processes.

- Filter: regex applied on the Command only (Fabric is concatenating “.*” at the beginning of the value).

Notes:

Use the process ID to identify the batch.

Note: The number of records shown are limited by MAX_WORKERS_PER_NODE shared across all active batches.

## View Instance-Level Sync Details

batch_details '<batch_id>'

[STATUS='<status>']

[ENTITIES='<entity1,entity2,...>']

[AFFINITY='<node_or_dc>']

[LIMIT=<limit>]

[SORT_BY_PROCESS_TIME=true|false];

Options:

STATUS: WAITING, COMPLETED, FAILED

ENTITIES: Comma-separated list of specific entity IDs

AFFINITY: Filter by data centers or nodes

LIMIT: Restrict result count (default limit is 10,000)

SORT_BY_PROCESS_TIME: If set to true, displays only the SLOWEST_PROCESSED_STATS_COUNT entities (config.ini, default 19) with the longest process times (overrides all other filters)

# Batch behind the scenes

## Batch flow

- Batch Command Execution

- A new record is created in the k2batchprocess.batch_list table.

- Key fields include:

- bid: Unique batch ID (UUID)

- FABRIC_COMMAND, JOB_UID, and session_scope (containing the globals set on the session)

- Batch command metadata (source DB, LU, interface, affinity, etc.)

- extra_stats: Maintains stats for slowest IIDs (updated every few seconds)

- status: NEW

- Async vs. Sync

- Async Mode (async = true)

- A job record is created in k2_system.k2_jobs

- Type = BATCH_JOB

- UID = The batch command itself

- The job is picked up by one of the nodes, which becomes the Coordinator Node

- The batch is executed under the job framework (supports retry, recovery, etc.)

- Sync Mode (async = false)

- The batch runs directly on the current node, which acts as the Coordinator Node

- No job record is created

- Not backed by the job mechanism (no automatic retry or error recovery)

- Coordinator Flow

- Generate IID list

- The batch status in batch_list is updated to GENERATE_IID_LIST.

- IID Retrieval Begins, based on the IID_LIST command

- If this is a batch_retry:

- Retry only failed IIDs → Exclude COMPLETED IIDs

- Retry all unprocessed IIDs → Exclude both COMPLETED and FAILED IIDs

- Note: Filtered IIDs are not re-sent to nodes, but their previous results are reused in the final batch statistics.

- IIDs are pushed into an in-memory queue (one queue per batch command):

- The queue has a size limit to prevent memory overuse (BATCH_MAX_ITEMS_IN_MEMORY config.ini).

- If the queue fills up, remaining IIDs are written to a file and then reloaded into the queue as space frees up.

- For each IID, a record is created in batch_entity_info with a null status (initial state before processing).

- Coordinator-to-Node Distribution

- The Coordinator Node launches one thread per participating Fabric node.

- Each thread:

- Opens a persistent JDBC connection to its target node (The JDBC connection remains open until the node responds back with results).

- Sends up to MAX_BULK_SIZE messages.

- IID Tracking for Reliability

- The Coordinator keeps track of which IIDs are sent to each node.

- This ensures recovery if a node goes down — unsynced IIDs, in the node that went down, are returned to the queue and reassigned to other nodes.

- Important Note:

- If a node that previously went offline comes back up, it might still try to process IIDs it had already received.

- This can lead to duplicate processing (e.g., an IID might appear as ADDED on one node and later as UPDATED on another).

- However, for batch statistics, only the first successful sync is counted — duplicates are ignored.

- Node-Side Processing

- Each node receives IIDs from the coordinator and places them into a local queue.

- The local queue is shared across all active batch commands running on the cluster.

- Each queue entry contains more than just the IID — it includes metadata like:

- Logical Unit (LU)

- Fabric command

- Execution context

- Because the queue is shared, threads on the node may serve multiple batch processes concurrently.

- Thread Management

- Each node runs MAX_WORKERS_PER_NODE threads.

- Each thread:

- Pulls a message from the queue (default: up to MAX_BATCH_SIZE_PROCESSING IIDs).

- Processes the IIDs sequentially.

- Removes the entry from the queue after picking it up.

- Once a thread completes its set of IIDs:

- It collects execution stats for each IID (start time, end time, status, errors, etc.).

- It updates the batchprocess_entities_info table, marking each IID as COMPLETED.

- Returning Results to the Coordinator

- Once any thread on a node finishes processing its assigned IIDs, the main thread on that node sends a response back to the Coordinator Node over the open JDBC connection.

- The response includes:

- Available queue slots on the node

- Aggregated execution results from all threads:

- Batch ID (bid)

- IID

- LU

- Start time, end time

- Execution result (status, error, etc.)

- Coordinator Result Handling

- Upon receiving results, the Coordinator:

- Maintains a Top-10 Slowest IIDs List

- Compares the new results to the current slowest IIDs across all nodes and threads.

- Updates Batch Metrics

- Calculates overall statistics

- Updates the batchprocess_list and batchprocess_node_info tables with the latest execution data.

- Once all IIDs processed, the batchprocess_list status is updated to DONE.

## MAX_WORKERS_PER_NODE

When MAX_WORKERS_PER_NODE is specified in the batch command, no queue is created on the Fabric nodes and the batch will work slower:

The Coordinator sends up to a fixed-size batch of MAX_BATCH_SIZE IIDs.

The node must complete all IIDs in that batch before sending results back.

Only after sending the results back will the next batch of IIDs be sent

### OLD

- Batch command is executed

- A record is added to k2batchprocess.batch_list table:

- Bid = unique batch id (uuid)

- Arguments: {
{"FABRIC_COMMAND": "sync_instance Customer5.?",
"JOB_UID": "BATCH Customer5 FROM CRM_DB USING ('select customer_id from public.customer limit 5') FABRIC_COMMAND=\"sync_instance Customer5.?\" with async=true;",
"session_scope": "{\"scope\":{\"EXECUTION_ID\":\"1d09df75-de22-494e-830b-bc80f6dda287\",\"LATEST_BATCH\":\"4fa4764f-5239-4084-8ca5-8dcaff6f72f2\",\"IS_IN_BATCH_PROCESS_PROCESS\":\"true\",\"LOG_ID\":\"de0300000000004f\"},\"user\":\"{\\\"type\\\":\\\"AuthenticatedUserByCredentials\\\",\\\"username\\\":\\\"shani.alpinist@k2view.com\\\",\\\"authenticator\\\":\\\"SAML\\\",\\\"authnType\\\":\\\"SAML\\\",\\\"roles\\\":[\\\"t-b78d1b60-aa9d-47a9-bf84-703d7a1ce600_k2v_admin\\\",\\\"t-b78d1b60-aa9d-47a9-bf84-703d7a1ce600_k2v_user\\\",\\\"Everybody\\\",\\\"t-b78d1b60-aa9d-47a9-bf84-703d7a1ce600_k2v_cloud_user\\\"],\\\"authenticationTime\\\":1745937478096}\"}",
"SRC_DB_INTERFACE_NAME": "CRM_DB",
"ESTIMATED_ENTITIES_COUNT": "0",
"sync_mode": "ON",

- "EXECUTION_ID": "1d09df75-de22-494e-830b-bc80f6dda287",

- "JOB_AFFINITY": "DC1",

- "INSTANCES_LIST": "",

- "IS_ASYNC": "true",

- "lu_name": "Customer5",

- "ALLOW_MULTIPLE": "false",

- "COMMAND": "batch Customer5 from CRM_DB using ('select customer_id from public.customer limit 5') fabric_command='sync_instance Customer5.?' with async=true",

- "bid": "4fa4764f-5239-4084-8ca5-8dcaff6f72f2",

- "SRC_DB_QUERY": "select customer_id from public.customer limit 5",

- "environment_name": "_dev"
}
The arguments include the session_scope

- Extra_stats – keep stats of the SLOWEST_PROCESSED_STATS_COUNT IIDs (default – 10).
The field is updated every BATCH_PACE_CALC_TIME_WINDOW_MS ms.
{"slowestProcessed":[{"entityId":"216","processTimeMS":22,"status":"FAILED","nodeId":"dev-fabric-deployment-848b7d9f89-ddm97"},{"entityId":"219","processTimeMS":34,"status":"FAILED","nodeId":"dev-fabric-deployment-848b7d9f89-ddm97"},{"entityId":"218","processTimeMS":34,"status":"FAILED","nodeId":"dev-fabric-deployment-848b7d9f89-ddm97"},{"entityId":"215","processTimeMS":35,"status":"FAILED","nodeId":"dev-fabric-deployment-848b7d9f89-ddm97"},{"entityId":"217","processTimeMS":39,"status":"FAILED","nodeId":"dev-fabric-deployment-848b7d9f89-ddm97"}]}

- Error – error in the batch process itself, not in the IIDs sync (for example – in generate_iid_list stage)

- Status

- WAITING_FOR_JOB

- NEW (after job started and before the batch started)

- GENERATE_IID_LIST

- IN_PROCESS

- DONE

- FAILED

- CANCELLED

- PASUED

- RESUME_FAILURES

- total_entities – updated once generate_iid_list is completed

- Sync parameter:

- If async = false – running on the current Fabric, without the Job mechanism (like retry), the coordinator will be the node on which the command was executed

- If async = true:

- a job record is created in k2_system.k2_jobs table

- Type = BATCH_JOB

- UID = The batch command itself

- * session_scope arguments are kept only in the batchprocess_list table

- The job starts on one of the nodes. This node will be the coordinator

- The batch process is starting on the coordinator node.

- Generate_iid_list stage:

- The record in batch_list is updated with generate_iid_list status

- Start pulling the iid list and :

- In case of batch_retry – filter out IIDs:

- Resume only for failed IIDs – filter out COMPLETED IIDs

- Resume not only for failed IIDs – filter out COMPLETED or FAILED IIDs

- The filtered IIDs will not be sent to the nodes, but their results (from prev run) will be added to the currect batch statistics.

- push to a in-memory queue (one queue per batch command). The queue has size limit in order not to exhaust the memory (hidden parameter?).
If the queue limit reach – start writing to a file and push from the file to the queue.

- For each entity - Add record to batch_entity_info in status

- The Coordinator starts thread per Fabric node that is taking part in the batch process. Each thread opens jdbc connection to the node and sends  MAX_BULK_SIZE messages to the node.

- The list of IIDs sent to each node is maintained in the coordinator to support a scenario when the node is going down for any reason.
If the node will get up at some point and try to run the same IIDs that were already sent to another node, it may happen that the same IID will be synced twice (so in the logs, for example, we can see a new IID first with ADDED status and second time with UPDATED status) but for the stats purpose, the IID will be taken only once (the first sync). The second sync will be skiped and not added to the stats.

- The jdbc connection stays open until it gets response from the node

- Each node receives the IIDs from the coordinator and add it to a local queue.

- The queue is used for all the batch commands running on the cluster.
Therefore it holds more info except the IID, like LUT, fabric command, etc.
(Therefore – the thread on the nodes are shared between for all the batch processes running in the cluster)

- MAX_WORKERS_PER_NODE threads running on each node

- Each thread is taking a message from the queue (up to MAX_BATCH_SIZE_PROCESSING IIDs (default 5) and run them sequentially. Once taken – an entry is cleared on the queue.

- Once the thread completes its IIDs:

- It keeps stats about each IID – start + end time, status, error etc, to be sent to the coordinator

- Update batchprocess_entites_info table to COMPLETED

- The main thread returns results to the coordinator node, using the open jdbc connection, once one of the threads completed its current IIDs. It returns a message, on the JDBC connection, with the below info:

- How many available slots it got in the local queue.

- The aggregated results from all threads (bid, IID, LUT, start, end, result…)

- The coordinator gets the results and:

- Maintain internal map to hold the 10 IIDs that took the longest (across all nodes, all threads) – compare new IIDs’ stats to existing 10

- Calculate stats and update batchprocess_list and batchprocess_node_inf0 tables

## Batch statuses

- WAITING_FOR_JOB - The batch command was executed, but the job has not started yet.

- NEW - The job has started, but the batch process has not begun execution.

- GENERATE_IID_LIST - The batch is generating the list of instance IDs (IIDs) to process.

- IN_PROCESS - All IIDs have been fetched, and sync operations are ongoing.

- DONE - The batch has completed successfully.

- FAILED - The batch command failed (not individual IIDs). See batchprocess_list.error for the failure message.

- CANCELLED - The batch was cancelled using the batch_cancel command.

- PAUSED - The batch was paused using the batch_pause command.

- RESUME_FAILURES - A batch_retry was issued after a previous execution completed. Only previously failed instances will be reprocessed.

## Handling Dynamic Cluster Nodes During Batch Execution

### Node Failure (Node Going Down)

If a node goes down during a batch:

The Coordinator tracks which IIDs were sent and not yet acknowledged.

These unprocessed IIDs are returned to the coordinator’s internal queue and redistributed to other active nodes.

If the failed node was the Coordinator:

The batch job will be restarted on another node (the will become the new cooredinator).

Node Recovery

If a failed node comes back online, it may still attempt to process previously assigned IIDs.

Duplicate processing may occur, but only the first successful sync is counted in the statistics.

### Node Join (New Node Added)

During the migration, the Coordinator checks for newly added nodes (every CHECK_FOR_ADDED_NODES_INTERVAL_MS ms,  default: 10 seconds).

When a new node joins:

The Coordinator starts a new thread for it.

The node is then assigned IIDs and begins participating in the batch execution.

## Batch cancel

When a batch_cancel command is issued:

Fabric sends a JDBC-based notification to all participating nodes indicating that the batch is being cancelled.

Nodes will:

Not process any IIDs that are still in the queue (related the cancelled batch id).

Allow currently running IIDs to complete gracefully.

The batch job updates the batchprocess_list table with status = CANCELLED.

## Batch pause

When a batch_pause command is issued:

Fabric sends a JDBC-based notification to all participating nodes indicating that the batch is being cancelled.

Nodes will:

Not process any IIDs that are still in the queue (related the paused batch id).

Allow currently running IIDs to complete gracefully.

The batch job updates the batchprocess_list table with status = PAUSED.

## Batch retry (resume)

When the batch_retry command is executed, Fabric determines how to resume the previous batch based on its last known status:

If the previous status was DONE, the new status is set to RESUME_FAILURES.

For all other statuses, it is set to GENERATE_IID_LIST (not NEW).

Job Startup Logic:

Upon job start, Fabric checks the current state to determine how to load the IID list:

If status = DONE:
→ IID list is loaded from system_db.

Otherwise:

If the IID generation step was not completed, the system re-fetches the IID list from the source and inserts it into Cassandra.

If IID generation was completed but the batch did not finish, the system reuses the IID list stored in system_db from the previous run.

Distributing IIDs to Nodes:

If the previous batch completed (status = DONE),still all IIDs are fetched from the system_db.

The Coordinator:

Sends only IIDs that need to be synchronized.

Skips IIDs that were already successfully processed, but uses their previous results to update batch statistics.

Note:

When the IID_LIST is based on pub/sub, the Broadway flow that streams IIDs must clearly indicate when the IID list is complete (e.g., via not nextPage or an end-of-stream signal).

## Sync mode

When a batch is executed without async=true, it runs in synchronous mode directly on the current node, without using the job mechanism. As a result:

The prompt remains blocked until the batch completes.

Pause and resume options are not supported.

There is no automatic recovery if the Coordinator node fails—Fabric will not resume the process, unlike in async mode with job support.

## Batch_in_process

When the batch_in_process command is executed, the Coordinator sends a request to all participating nodes to report the status of currently running IIDs.

This allows real-time visibility into in-progress instances across the cluster.

## Session scope

Just like in the job mechanism, session scope variables are shared across all threads involved in the batch execution, and executed before running the bulk of IIDs (MAX_BATCH_SIZE_PROCESSING ) on the node side.

The session scope is stored in the arguments column of the batchprocess_list table.

These variables ensure consistent context and global values across the entire batch process.

## Common tables dependencies

If the LU Schema relies on a Common Table, Fabric performs a dependency check before the batch starts:

- The JDBC connection first requests the remote node to verify that the required common table has been executed.

- If a node does not have the required common table loaded, it will not synchronize any IIDs.

- The JDBC connection will withhold results until the common table has completed its sync.

- This prevents worker threads from getting stuck on IIDs that depend on missing common data.

# Batch system_db tables

All batch-related tables are stored under the k2batchprocess schema.

These tables have a Time-To-Live (TTL) of 7 days.

TTL Handling:

If system_db is Cassandra: TTL is enforced at the table level using Cassandra's native TTL configuration.

For other database types: A dedicated cleanup job runs periodically to delete records older than 7 days.

Note: The 7-day retention period is hard-coded and cannot be modified by Fabric.

## batchprocess_list table

Stores metadata for each executed batch command.

Key Columns:

Bid - Unique batch identifier (UUID)

arguments - JSON containing:

Batch command parameters (e.g., FABRIC_COMMAND, SRC_DB_QUERY, lu_name, IS_ASYNC, etc.)

session_scope object with global variables and authenticated user details

command - The full original batch command string

create_time, start_time, end_time - Timestamps for when the batch was created, started, and completed

error - Error details if the batch process itself failed (not per-entity errors)

extra_stats
Tracks the 10 slowest processed IIDs with:

entityId

processTimeMS

status

nodeId

Example:

- {"slowestProcessed":[{"entityId":"246","processTimeMS":10372,"status":"COMPLETED","result":"{}","nodeId":"shani-mountain8-solutions"},{"entityId":"236","processTimeMS":10373,"status":"COMPLETED","result":"{}","nodeId":"shani-mountain8-solutions"},{"entityId":"241","processTimeMS":10373,"status":"COMPLETED","result":"{}","nodeId":"shani-mountain8-solutions"},{"entityId":"231","processTimeMS":10374,"status":"COMPLETED","result":"{}","nodeId":"shani-mountain8-solutions"},{"entityId":"226","processTimeMS":10374,"status":"COMPLETED","result":"{}","nodeId":"shani-mountain8-solutions"},{"entityId":"216","processTimeMS":10375,"status":"COMPLETED","result":"{}","nodeId":"shani-mountain8-solutions"},{"entityId":"221","processTimeMS":10375,"status":"COMPLETED","result":"{}","nodeId":"shani-mountain8-solutions"},{"entityId":"215","processTimeMS":10376,"status":"COMPLETED","result":"{}","nodeId":"shani-mountain8-solutions"}]}

lut_name - Logical Unit name of the instances being processed

owner - User who executed the batch (e.g., shani@k2view.com.k2v)

status - Current state of the batch (e.g., NEW, IN_PROCESS, DONE, etc.)

total_entities - Total number of IIDs processed (populated after generate_iid_list)

## batchprocess_node_info table

Stores a per-node summary of entities handled during a batch process.

Key Columns:

- aggregated_results
Total number of entities processed on the node, categorized by:

- added

- updated

- unchanged

- pace - Processing speed — number of entities handled in the last BATCH_PACE_CALC_TIME_WINDOW_MS ms (default 10 sec).

- failed_entities_count - Total number of entities that failed during processing on the node.

- succeeded_entities_count - Total number of successfully processed entities on the node.

## batchprocess_entities_info table

Stores detailed execution data for each individual entity (IID) processed within a batch command.

Purpose:

Tracks the outcome and status of every entity processed in a batch, including:

Execution time

Status (e.g., COMPLETED, FAILED, null)

Errors (if any)

Results – for Broadway-based batches, this field captures the external output values returned by the flow execution.

## batchprocess_entities_errors tables

Contains detailed error information for entities that failed during batch execution.

Purpose:

For each failed entity (IID), this table records:

The node ID that attempted to execute the sync

The error message returned by the sync process

This data is useful for troubleshooting failed LUI syncs in distributed batch executions.

# Batch Actors

## BatchWait

The BatchWait Actor waits for a batch process to complete.

Behavior:

Completes when the batch finishes successfully.

Throws an error if the batch fails or exceeds the Wait For Seconds timeout.

If Wait For Seconds set to zero or a negative value, waits indefinitely.

If the batch is paused, the actor continues waiting.

- If the batch is cancelled, the actor will exit gracefully without error.

Interface it is set to fabric as default and can be changed to remote Fabric Interface.

## DbCommand

Use the DbCommand actor to execute batch command.

# Batch Config

config.ini – batch_process Section

Primary Configuration Parameters:

MAX_WORKERS_PER_NODE (default: 8)
Number of threads allocated per node for running all batch processes.
*Replaces the deprecated JOB_SERVERS_WORKERS_COUNT parameter.

MAX_BATCH_SIZE_PROCESSING (default: 5)

Defines the maximum number of entities grouped together in a single task of the remote threads

- A worker processes the group sequentially (not in parallel).

- Value means “up to X” entities; if fewer are available, a smaller group is sent.

- Best Practice for Long-Running Processes

- For Broadway or other long-running processes, set MAX_BATCH_SIZE_PROCESSING = 1.

- This maximizes parallelism since each entity is assigned to a separate worker.

System DB Loader Configuration

Used when writing batch metadata to system_db (IIDs to bachprocess_enteties_info). Fabric automatically detects the database type and applies the appropriate loader:

Defaults by DB Type:

For JDBC-based DBs: jdbc_default_loader

For Cassandra: default_loader

You can override the settings by adding batch_process_loader Section.

It is recommended to create the batch_process_loader, to not override other loaders functionality, with the below parameters:

- MODE = TOKEN_AWARE_BATCH

- BATCH_SIZE = 100 (should be tuned based on the system_db type – may lead to a huge performance improvement)

[jdbc_default_loader]

#MODE = BATCH          ; Execution mode: SINGLE or BATCH

#QUEUE_SIZE = 10000

#BATCH_SIZE = 1000     ; Applies only to BATCH mode

[default_loader]

#MODE = SINGLE         ; Options: SINGLE / BATCH / TOKEN_AWARE_BATCH

#QUEUE_SIZE = 10000

#NUMBER_OF_THREADS = 1

#SESSION_NAME = loader

#MAX_IN_FLIGHT = 1024

#CONSISTENCY_LEVEL = LOCAL_QUORUM

#IS_NOP = false

Hidden / Advanced Parameters

MAX_BULK_SIZE (default: 10)
Maximum bulk size of entities sent per JDBC iteration.
-1 uses the value of MAX_WORKERS_PER_NODE on the Coordinator node.

BATCH_DETAIL_MAX_ROWS_SIZE (default: 10,000)
Maximum rows fetched when executing batch_details.

BATCH_MAX_ITEMS_IN_MEMORY (default: 100,000)
Max queue size held in memory. Overflow is stored in file on the disk.
Should be tuned if Instance IDs are large.

BATCH_MIN_TIME_MS (default: 1,000)
Minimum allowed execution time for a batch. Required due to Cassandra consistency limitations.

BATCH_PACE_CALC_TIME_WINDOW_MS (default: 10,000)
Interval for Pace value calculation and system_db updates.

BATCH_UNITS_CACHE_EXPIRATION_IN_MIN (default: 1,440 minutes / 24 hours)
Time to retain nodes IIDs results in case Coordinator is down.

CHECK_FOR_ADDED_NODES_INTERVAL_MS (default: 10,000)
Frequency to check for new nodes joining the cluster.

- COMMAND_JOB_UID_MAX_LENGTH (default: 1024)
Max length allowed for the batch command string used as the job UID (limitations in keyspace for 1024 bytes)

MAX_UNIQUE_AGGREGATION_KEYS (default: 20)
Maximum number of unique result columns (aggregation keys) that the Coordinator will track per IID from the Broadway flow results returned by the nodes.

SLOWEST_PROCESSED_STATS_COUNT (default: 10)
Number of slowest processed IIDs to track in batch statistics.

WORKERS_QUEUE_SIZE (default: -1)
Queue size for worker threads:

-1: Queue size equals max workers

0: No queue

# JMX Stats

Under the Transactions section:

systemJobs – Displays the number of currently running jobs (total 0 indicates the job is not running).

systemJobsExecution – Shows the total number of times the job has been executed.
