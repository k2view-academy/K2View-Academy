# Advanced Broadway Features

### What Will You Experience In This Learning Item?

By the end of this learning item you will:

- Be familiar with the Actor Inheritance mechanism.
- Learn about inner flows, how to handle errors and manage transactions.
- Create a Broadway flow with an inner flow and an error handler.
 

### Overview

After learning about Broadway flows, Stages and Actors it is time to become familiar with additional Broadway features that are essential for creating complex business flows. These features are:

* **Actor Inheritance Mechanism**, that enables extending an Actor with additional logic and arguments when specific business logic is required in several flows. For example, to set the same date format of all the dates in all Broadway flows.

* **Inner Flows**, that encapsulate a flow to create an Actor that can be used in other flows. For example, to implement the same validation in several Broadway flows.

* **Error Handling**, a built-in mechanism that handles exceptions in a flow using an Error Handler Actor. The Actor's logic is validated and if it returns true, the flow continues. Otherwise the flow stops.

* **Transactions Management**, that enables starting a transaction at any Stage of the flow and performs a commit or a rollback at the end of the transaction in the flow.

  

For more details about the above functionalities, please refer to:

* [Actor Inheritance Mechanism](/articles/19_Broadway/06_export_actor.md).
* [Inner Flows](/articles/19_Broadway/22_broadway_flow_inner_flows.md).
* [Error Handling](/articles/19_Broadway/24_error_handling.md).
* [Transactions](/articles/19_Broadway/23_transactions.md).



Now let's see some examples that demonstrate the above Broadway features.



[![Previous](/articles/images/Previous.png)](13_interface_listener_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](15_broadway_advanced_features_example1.md)
