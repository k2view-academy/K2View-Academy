# **Fabric Jobs Overview** 

The Fabric Jobs mechanism is rich, resilient and scalable and can be used to run any script or executable. For example, asynchronous recurring one-time only or scheduled actions that run Fabric functions according to a predefined schedule.


Once set up, Fabric creates asynchronous tasks (running threads) that execute specific commands, Broadway flows or Java code at specific dates and times. Jobs can also be used to collect data from structured DB or HTTP files, streams or message queues.

Fabric Jobs can be one of the following categories:

- User Jobs, that run light-weight user functions.

- Process Jobs, that run a script or other executables.

- Migrate process, that sync multiple instances of a specific Logical Unit.

- Parser executions, that get data from tables and unstructured files which can be pushed to a table in Cassandra and then used to build an LUT Schema.

- Broadway flows, that can be scheduled for execution and are therefore defined as Jobs.

 # **What is a Fabric Job ?** 
A Fabric Job process can be exposed across Fabric nodes and be run to execute scripts, flows or functions according to a specific schedule or once only.

A Job should be deployed to Fabric separately and then be invoked either by the node onto which it as been deployed or by other Fabric nodes that would have been allocated this Job by Cassandra distribution. 

In the Fabric Studio, Job functions can be defined and saved to the project file and be deployed to the Fabric server.

For more information, click:
- Different types of jobs, their mechanism and lifecycle.
- Defining Jobs from the Fabric Studio.
- Invoking and managing Jobs from the Fabric Runtime console.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md) 
