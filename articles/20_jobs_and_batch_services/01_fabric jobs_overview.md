# **Fabric Jobs Overview** 

Fabric offers a rich, resilient, and scalable built-in functionality that allows the system to run any script or other executable using the Fabric Jobs mechanism. 
This is where all asynchronous recurring, one-time only or scheduled actions happen, enabling users to run Fabric functions according to a predefined schedule. Once set up by the user, Fabric creates asynchronous tasks (running threads) that execute specific commands, Broadway flows or Java code at specific dates and times. Jobs can be also used to collect data from structured DB or any files (HTTP), streams, or message queues.

Fabric Jobs fall into one of the following categories:

- User Jobs, that run light-weight user functions.

- Process Jobs, wthat run a script or other executables.

- Migrate process, that sync multiple instances of a specific Logical Unit.

- Parsers executions, that get data from both tables and unstructured files which can then be pushed to a table in Cassandra and then used to build a, LUT Schema.

- Broadway flows, that can be scheduled for execution and are therefore defined as Jobs.

 # **What is a Fabric Job ?** 
A Fabric Job is a process that can be exposed across Fabric nodes to be run to execute scripts, flows or functions according to a specific schedule, or once only.

Jobs should be deployed to Fabric separately and can then be invoked either by the node onto which it as been deployed or by any other Fabric nodes that would have received the job allocation from Cassandra distribution .... In the Fabric Studio, Jobs functions can be defined and saved to the project file and be deployed to the Fabric server.

The following articles will address:
- the different types of jobs
- their mechanism and lifecycle
- how to define them from Fabric Studio
- how to invoke and manage them from Fabric Runtime console.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md) 
