# **Fabric Jobs Overview** 

Fabric supplies a rich, resilient, and scalable built-in functionality that allows the system to run any script or other executable using the Fabric Jobs mechanism.

Fabric Jobs fall into one of the following categories:

- User Jobs
The ability to run light weight user functions.

- Process Jobs
The ability to run a script or any other executable using the jobs mechanism.

- Migrate process
The ability to sync multiple instances of a particular Logical Unit.

- Parsers executions
Parsers allow you to get data from both tables and unstructured files. Using Fabric parser capacities, data can be pushed to a table in Cassandra, and then use this table when building a LUT schema.

- Broadway Flows
Broadway flows can be scheduled for execution and therefore defined as jobs.

 # **What is a Fabric Job** 
A Fabric Job is a function that can be exposed across Fabric nodes to be run according to a specific schedule, or once only.

Jobs should be deployed to Fabric separately and can then be invoked either by the node onto which it as been deployed or by any other Fabric nodes that would have received the job allocation from Cassandra distribution .... In the Fabric Studio, Jobs functions can be defined and saved to the project file and be deployed to the Fabric server.

The following articles will address:
- the different types of jobs
- their mechanism and lifecycle
- how to define them from Fabric Studio
- how to invoke and manage them from Fabric Runtime console.

