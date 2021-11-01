# Broadway High Level Components


In the Overview we saw the main Broadway concepts that make it a unique solution:
[Stages](19_broadway_flow_stages.md) as way to model work flow, [Actors](03_broadway_actor.md) to model data flow and [data inspection](27_broadway_data_inspection.md) to
discover and manipulate complex data types.

There are a few other core capabilities that are important for a high-level of understanding of the Broadway system.

## Actor Input/Output Arguments

Actors can get their input from three different sources:

* The output of a previous Actor - the connecting lines between Actors.
* A Constant value supplied by the user.
* An input argument to the flow (external).

When the Actor executes, it is completely unaware of the source of its data. Output arguments can also be exposed (external) as results of the flow's execution. This makes the data available to the module that executed the Broadway flow. 

In the image, the JavaScript Actor obtains the **script** input as a constant input, **a** is supplied by connecting to a previous Actor, **b** is supplied as an external input to the flow and the result is exposed as external flow result.

![image](images/input-output-args.PNG)

[Click for more information about Actor's Inputs and Outputs](03_broadway_actor_window.md#actors-inputs-and-outputs).

## Type System

Broadway Actors pass data between them as Java objects. Virtually any data type can be passed between Actors but in practice most Actors pass a subset of types that are supported by Broadway.  Supported Broadway types can be described by the Broadway Schema engine, can be displayed clearly by the Data Inspector and can be converted automatically to other supported types.
Broadway supports Arrays, Maps and Primitives such as String, Long, Real, Date, Boolean and Byte Array (binary data).
In addition Broadway has a robust type conversion system that automatically converts between types where possible, relieving the user of the burden of type conversion.

[Click for more information about Broadway data types](05_data_types.md#data-types-in-broadway).

## Data Schema

The Broadway UI uses JSON Schemas to describe the data and enable designing data flows that leverage the known data structure.
Broadway can learn the Schema by example. Just run the flow, and the metadata is automatically derived from the data. If you do have available JSON Schemas, they can be easily imported and edited in the Broadway interface.

<img src="images/overview_edit_schema.PNG" />

[Click for more information about the Broadway Data Editor and how you can edit the schema using it](27_broadway_data_inspection.md).

## Iterations

A common pattern of execution is to perform an iterative operation on a data set. For instance, performing some data transformation on a database result-set or traversing a JSON array obtained from a REST API.
The way Broadway deals with such cases is with the **Iterate** line (double dotted line below). This signals Broadway to perform the operation for every entry in the data set.

<img src="images/overview_iterate.png" >

[Click for more information about iterations in Broadway](21_iterations.md).

## Stage Conditions

When Stages are split, an Actor can be designated to decide if a specific fork in the flow will be performed. The Actor can be a simple logical operator or an entire flow. Based on the result, Broadway will decide if the flow should be executed.
The *else* fork will be executed if none of the other splits were executed.

<img src="images/overview_condition.png">

[Click for more information about Stage Conditions](19_broadway_flow_stages.md#what-is-a-stage-condition).

## Actor Inheritance

Actors have an inheritance hierarchy. This enables activities such as pinning a constant value and reusing it across multiple flows, reusing some Actor logic such as JavaScript or SQL and even overriding the Actor Java implementation and tailoring it to a specific use case.  

[Click for more information about Actor Inheritance](06_export_actor.md).

## Transactions

Broadway has a built-in transaction management mechanism. Stages can be marked as part of a transaction. Any transactional resource the Actors in these Stages use, such as database or queue, automatically becomes part of a transaction. Once the Transactional Stages are complete, the transaction is committed. In case of failure the transactions will be rolled back.
Transactions also take into account inner Broadway flows. If a Transactional Stage executes an inner Broadway flow, the flow automatically becomes part of the outer transaction.

[Click for more information about Transactions](23_transactions.md).

## Error Handling

Every Stage can be assigned an error handler. The error handler is an Actor that can hold the logic to perform if an error occurs as well as the decision whether the flow should continue or stop on that error. The error Actor can be a simple logical check or an entire flow.

<img src="images/overview_error.png" >

[Click for more information about Error Handling](24_error_handling.md).



[![Previous](/articles/images/Previous.png)](01_broadway_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02a_broadway_flow_overview.md)
