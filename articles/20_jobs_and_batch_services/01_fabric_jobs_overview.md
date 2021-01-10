# Fabric Jobs Overview 

The Fabric Jobs mechanism is rich, resilient and scalable and can be used to run any script or executable. For example, recurring or one-time only scheduled asynchronous actions that run Fabric functions according to a predefined schedule.


Once set up, Fabric creates asynchronous tasks (running threads) that execute specific commands, Broadway flows or Java code at specific dates and times. Jobs can also be used to collect data from structured DB, HTTP streams, files (local and remote) or message queues.

Fabric Jobs can be one of the following categories:

- User Jobs, that run light-weight user functions.

- Process Jobs, that run a script or other executables.

- Migrate process, that sync multiple instances of a specific LU.

- Parser executions, that get data from tables and unstructured files which can be pushed to a table in Cassandra and then used to build an LUT Schema.

- Broadway flows, that can be scheduled and benefit from Jobs execution parameters.


 # What is a Fabric Job? 
A Fabric Job process can be exposed across Fabric nodes and be run to execute scripts, flows or functions according to a specific schedule or once only.

A Job must be deployed to Fabric so that it can be invoked by one of the Fabric Cluster's nodes following an allocation process that we will describe in more details in this section.

Job functions can be defined in the Fabric Studio, saved to the project file and be deployed to the Fabric server.




[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/02_jobs_flow_and_status.md) 
