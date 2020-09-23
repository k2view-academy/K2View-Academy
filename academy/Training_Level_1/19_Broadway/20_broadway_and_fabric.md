# Broadway and Fabric

### What Will You Experience In This Learning Item?

By the end of this learning item you will:

- Be able to invoke a Broadway flow from the Fabric Console.



### Overview

You have already learned about the Broadway integration with the Fabric Studio, for example:
* Creating and running a flow to serve as LU table population.
* Setting up the Interface Listener to invoke a Broadway flow.
* Opening a Query Builder from DB Commands Actors to perform SQL query validations.

Now let's take a look at how Broadway can be invoked by the Fabric Console. Please refer to the following:

* A Broadway command - ability to run a flow once providing the execution parameters (LU name and the flow name) once the flow has been deployed. If the flow receives external input arguments, they can be sent using the param=value syntax. 
* [A Broadway Job](/articles/20_jobs_and_batch_services/05_create_a_new_broadway_job.md) -  ability to run a flow as a Job using the Fabric Jobs mechanism by providing the execution parameters (LU name and the flow name).
* [Batch commands](/articles/20_jobs_and_batch_services/15_batch_broadway_commands.md) - ability to run a flow as a batch process providing the batch configuration parameters as well as the execution parameters (LU name and the flow name).
* Consuming CDC messages by Broadway.



Now let's see few examples that will demonstrate the above Broadway features.



[![Previous](/articles/images/Previous.png)](19_broadway_addl_features_exercise_solution.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](21_broadway_and_fabric_example.md)
