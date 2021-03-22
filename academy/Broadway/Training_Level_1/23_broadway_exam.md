# Broadway Certification Exam

<img src="images/Quiz.png" style="zoom:80%;" /> 

Excellent! You have completed the Broadway Training and the Summary Exercise. It's now time to take the following certification exam to see what you have learned. 

The exam consists of a number of multiple-choice questions, each providing a number of possible answers. Click the answer you think is correct. 



#### Question 1: Broadway Overview

Broadway is the Fabric module that will allow you to:


\- A:  Populate Logical Units from external sources.


\- B:  Read from and write data into DBs or files.

\- C:  Execute scheduled activities (user jobs).

\- D:  All the above.



#### Question 2: Actor Overview

Broadway Actors are:


\- A:  Built-in Java classes that should be used as-is in a flow and cannot be modified.


\- B:  Exposed REST APIs that can be invoked by any external system.

\- C:  Ready-to-use classes with predefined logic that can be extended if needed.

\- D:  None of the above.



#### Question 3: Actors Inputs and Outputs

Actors input parameters values are:


\- A:  Populated based on the selected Link, Const or External population type.


\- B:  Either hold Constant values or receive the data from the external source.

\- C:  Always defined as Constant.

\- D:  Populated based on the Actor's position in the flow.



#### Question 4: Actors Inputs and Outputs

The External population type is used:


\- A:  When the flow gets its input from the external process.


\- B:  When the flow requires using a read-only parameter.

\- C:  When using a FabricGet Actor.

\- D:  When you need to iterate over the data set.



#### Question 5: Data Types Conversion

If the Actor expects to receive a Boolean value but receives a number instead, Broadway will:


\- A:  Throw an error message due to an incompatible data type.


\- B:  Convert the number to a Boolean value: false if 0, otherwise true.

\- C:  Ignore the input argument's value.

\- D:  Open a pop-up window to insert the correct value.



#### Question 6: Built-in Actors

Does Broadway support asynchronous message handling? If yes - how?


\- A:  It is not supported by Broadway.


\- B:  It is supported via Web Services.

\- C:  Broadway can subscribe to and publish the messages to Kafka only.

\- D:  Broadway can subscribe to and publish the messages to Kafka and any JMS provider.



#### Question 7: Actor Inheritance

When creating an inherited Actor, the new Actor:


\- A:  Must keep their ancestor's input and output arguments as-is.


\- B:  Must set all their ancestor's input and output arguments as either hidden or final.

\- C:  Can set their ancestor's input and output arguments as either hidden or final.

\- D:  Will be created in a new category (tag) designated for inherited Actors only.



#### Question 8: Broadway Flow Stages

A Broadway flow is built of several Stages whereby:


\- A:  The Stages are executed from left to right, each Stage can include one or more Actors which are executed top-down within the Stage.


\- B:  Any Stage in a flow can be split to create parallel execution branches.

\- C:  Any two Stages in a flow can be merged into one Stage.

\- D:  All the above.



#### Question 9: Stage Conditions

A Stage condition:


\- A:  Can only be defined using a JavaScript Actor.


\- B:  Stops the execution of the branch if the Actor's first output returns false.

\- C:  Should be the only Actor in the Stage.

\- D:  Is needed to define the Else condition.



#### Question 10: Iterations

Iterations in Broadway:


\- A:  Are started by setting the link type of the Actor that begins the loop to Iterate.


\- B:  Can be stopped programmatically by accessing the context object.

\- C:  Support having more than one loop over the same data structure .

\- D:  All the above.



#### Question 11: Broadway Integration with Fabric Studio

In a Broadway flow you can:


\- A:  Invoke any Project function or a Graphit resource under the selected Logical Unit.


\- B:  Execute the SET and GET commands on the current Fabric session.

\- C:  Connect to any Fabric interface and validate an SQL query using the Query Builder window.

\- D:  All the above.



#### Question 12: Population based on a Broadway Flow

Population of an LU table using a Broadway flow:


\- A:  Can only be done by selecting data from a source DB.


\- B:  Can combine data from several sources such as a DB and an HTTP call.

\- C:  Can only populate one LU table per each flow.

\- D:  Is performed using Fabric commands and functions.



#### Question 13: Population based on a Broadway Flow

When using the SourceDbQuery Actor in a Broadway population flow:


\- A:  You need to create a Root function in order to query the source DB.


\- B:  You cannot edit the SQL statement in the Actor's sql input argument.

\- C:  You can edit the SQL statement in the Actor's sql input argument but cannot add the WHERE clause to it.

\- D:  The WHERE clause is generated in the background by connecting the parent_rows input argument to the PopulationArgs Actor's output.



#### Question 14: Transactions

When the flow includes the transaction:


\- A:  The transaction starts when the Actor in the first Stage marked as a transaction requests to start a connection.


\- B:  The transaction can span over several sequential Stages in the flow.

\- C:  The transaction ends after the last Stage marked as a transaction and is followed by a commit (or by a rollback if there are errors).

\- D:  All the above.



#### Question 15: Transactions

The transaction in a Broadway flow:


\- A:  Can only be defined on DB interfaces.


\- B:  Impacts the transactional Stages but does not impact the inner flows within the transaction.

\- C:  When defined on an iteration, can do a commit (or rollback) either on each iteration or at the end of the iteration data set.

\- D:  Cannot be defined on a split Stage.



#### Question 16: Error Handling

When an error occurs in a Broadway flow:


\- A:  An error handler must be defined in the last Stage of the flow.


\- B:  The error can be handled by an error handler in a flow but not in an inner flow.

\- C:  Only a JavaScript Actor can be used for catching the errors.

\- D:  The error handler catches it and returns either true to continue the flow or false to stop it.



#### Question 17: Error Handling

Error Handling in a flow:


\- A:  Always suppresses any type of error.


\- B:  Can programmatically catch some errors and continue or stop the flow.

\- C:  Will always cause a rollback in the transactional Stage.

\- D:  Must include the error validation check.



#### Question 18: Run and Debug the Flow

When running the flow with Debug ON:


\- A:  You can view the data object in the JSON-like representation via the Data Viewer.


\- B:  You can compare and update the schema based on the proposed Debug Schema.

\- C:  Only A is correct.

\- D:  A and B are correct.



#### Question 19: Data Inspector

Broadway Data Inspector (the yellow segment):


\- A:  Enables editing the metadata during the runtime.

\- B:  Can identify and display complex Object data types, their schemas and values.

\- C:  Allows to connect individual columns of complex Object data types to other Actors arguments.

\- D:  All of the above.


#### Question 20: Invoke a Flow for Multiple Instances

Can a Broadway flow that executes business logic on one instance (for example, customer ID) be run on several instances? If yes - how?


\- A:  It is not possible.


\- B:  Via the Fabric Studio by providing one customer ID for each run.

\- C:  Using a BATCH command for a given list of instances (list of customers ID).

\- D:  Using a BROADWAY command.



[![img](/articles/images/Previous.png)](22a_broadway_summary_exercise_solution.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](24_broadway_exam_with_solution.md)
