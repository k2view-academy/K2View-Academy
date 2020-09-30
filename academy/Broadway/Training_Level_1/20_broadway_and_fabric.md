# Broadway and Fabric

### What Will You Experience In This Learning Item?

By the end of this learning item you will:

- Be able to invoke a Broadway flow from the Fabric Console.



### Overview

You have already learned about Broadway integration with the Fabric Studio, for example:
* Creating and running a flow to serve as an LU table population.
* Setting up the Interface Listener to invoke a Broadway flow as a job.
* Opening a Query Builder from DB Commands Actors to perform SQL query validations.

Now let's take a look at how Broadway can be invoked by the Fabric using one of the following ways:

* Broadway command, to run a flow once to provide the LU name and flow name execution parameters after the flow has been deployed. If the flow receives external input arguments, they can be sent using param=value syntax. 
* [Broadway Job](/articles/20_jobs_and_batch_services/05_create_a_new_broadway_job.md), to run a flow as a Job using the Fabric Jobs mechanism to provide the LU name and the flow name execution parameters.
* [Batch commands](/articles/20_jobs_and_batch_services/15_batch_broadway_commands.md), to run a flow as a batch process to provide both the batch configuration parameters and the LU name and flow name execution parameters.
* Consuming CDC messages by Broadway. Since Fabric publishes the CDC messages to Kafka, a dedicated CDC consumer can be defined for Broadway in order to subscribe to the CDC messages.



Now let's see some examples that demonstrate the above Broadway features.



[![Previous](/articles/images/Previous.png)](19_broadway_addl_features_exercise_solution.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](21_broadway_and_fabric_example.md)
