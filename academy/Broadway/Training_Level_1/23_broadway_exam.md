# Broadway Certification Exam

<img src="images/Quiz.png" style="zoom:80%;" /> 

Excellent! You have completed the Broadway Training and the Summary Exercise.

It's now time to take the following certification exam to see what you have learnt. The exam consists of a number of multiple-choice questions, each providing a number of possible answers. 

Click the answer you think is correct. 



#### Question 1: Broadway Overview

Broadway is the Fabric module that will allow you to:


\- A:  Populate Logical Units from external sources.


\- B:  Read from and write data into DBs or files.

\- C:  Execute scheduled activities (user jobs).

\- D:  All the above.

(**Solution 1. D: All the above activities can be performed using Broadway**).



#### Question 2: Actor Overview

Broadway Actors are:


\- A:  Built-in Java classes that should be used as-is in a flow and cannot be modified.


\- B:  Exposed REST APIs that can be invoked by any external system.

\- C:  Ready-to-use classes with predefined logic that can be extended if needed.

\- D:  None of the above.


(**Solution 2. C: Broadway offers a number of built-in Actors which address a wide range of predefined activities and can be extended by creating inherited Actors**).



#### Question 3: Actors Inputs and Outputs

Actors input parameters values are:


\- A:  Populated based on the selected Link, Const or External population type.


\- B:  Either hold Constant values or receive the data from the external source.

\- C:  Always defined as Constant.

\- D:  Populated based on the Actor's position in the flow.


(**Solution 3. A: The population of the input parameter depends on the population type. The supported types are: Link, Const or External**).



#### Question 4: Data Types Conversion

If the Actor expects to receive a Boolean value but receives a number instead, Broadway will:


\- A:  Throw an error message due to an incompatible data type.


\- B:  Convert the number to a Boolean value: false if 0, otherwise true.

\- C:  Ignore the input argument's value.

\- D:  Open a pop-up window to insert the correct value.


(**Solution 4. B: Supported Broadway types can be converted automatically to other supported types expected by the Actor**).



#### Question 5: Broadway Flow Stages

A Broadway flow is built of several Stages whereby:


\- A:  The Stages are executed from left to right in the flow, each Stage can include one or more Actors which are executed top-down within the Stage.


\- B:  Any Stage in a flow can be split to create parallel execution branches.

\- C:  Any two Stages in a flow can be merged into one Stage.

\- D:  All the above.


(**Solution 5. A: B is incorrect because a root Stage cannot be split. C is incorrect because only the Stages with the same parent Stage can be merged**).



#### Question 6: Stage Conditions

A Stage condition:


\- A:  Can only be defined using a JavaScript Actor.


\- B:  Stops the execution of the branch if the Actor's first output returns false.

\- C:  Should be the only Actor in the Stage.

\- D:  Is needed to define the Else condition.


(**Solution 6. B: If a Stage condition is false, its branch's execution stops. The condition Actor can have any number of output parameters; however a true / false check is performed on the first**).



#### Question 7: Iterations

Iterations in Broadway:


\- A:  Are started by setting the link type of the Actor that begins the loop to Iterate.


\- B:  Can be stopped programmatically by accessing the context object.

\- C:  Support having more than one loop over the same data structure .

\- D:  All the above.

(**Solution 7. D: All the above are supported by Broadway Iterations**).



#### Question 8: Population based on a Broadway Flow

Population of an LU table using a Broadway flow is performed by:


\- A:  Calling a Broadway flow from the Fabric Population object.


\- B:  Creating a Broadway flow template that includes predefined Stages and designated Actors and connecting it to the parent table in the LU Schema.

\- C:  Writing an INSERT query using a DbCommand Actor.

\- D:  A combination of Broadway Actors and available Fabric commands and functions.


(**Solution 8. B: A Broadway population flow template includes predefined Stages and designated Actors and can be modified by adding more Actors when needed**).



#### Question 9: Population based on a Broadway Flow

The data for a table population using a Broadway flow is retrieved by:


\- A:  Either a DB query or a Root function.


\- B:  A DbCommand Actor which must include a WHERE clause that connects the table to its parent table.

\- C:  A SourceDbQuery Actor that must include a WHERE clause that connects the table to its parent table.

\- D:  A SourceDbQuery Actor whereby the WHERE clause is generated in the background by connecting the parent_rows input argument to the PopulationArgs Actor's output.


(**Solution 9. D: The data is retrieved by a SourceDbQuery Actor that inherits from the DbCommand Actor. The WHERE clause is generated automatically and is not visible in the Actor's UI**).



#### Question 10: Broadway Integration with Fabric Studio

In a Broadway flow you can:


\- A:  Invoke any Project function or a Graphit resource under the selected Logical Unit.


\- B:  Execute the SET and GET commands on the current Fabric session.

\- C:  Connect to any Fabric interface and validate an SQL query using the Query Builder window.

\- D:  All the above.

(**Solution 10. D: All the above are supported**).



#### Question 11: Actor Inheritance

When creating an inherited Actor, the new Actor:


\- A:  Must keep their ancestor's input and output arguments as-is.


\- B:  Must set all their ancestor's input and output arguments as either hidden or final.

\- C:  Can set their ancestor's input and output arguments as either hidden or final.

\- D:  Will be created in a new category (tag) designated for inherited Actors only.

(**Solution 11. C: When exporting the Actor each input and output argument can optionally be set as hidden or final**).



#### Question 12: Run and Debug the Flow

When running the flow with Debug ON:


\- A:  You can view the data object in the JSON-like representation via the Data Viewer.


\- B:  You can compare and update the schema based on the proposed Debug Schema.

\- C:  Only A is correct.

\- D:  A and B are correct.

(**Solution 12. D: Both A and B are correct**).



#### Question 13: Transactions

The transaction in a Broadway flow:


\- A:  Can only be defined on DB interfaces.


\- B:  Impacts the transactional Stages but does not impact the inner flows within the transaction.

\- C:  When defined on an iteration, can do a commit (or rollback) either on each iteration or at the end of the iteration data set.

\- D:  Cannot be defined on a split Stage.

(**Solution 13. C: There are two approaches for handling transactions during an iteration, closing the transaction and performing a commit on each iteration or after the loop ends**).



#### Question 14: Error Handling

When an error occurs in a Broadway flow:


\- A:  An error handler must be defined in the last Stage of the flow.


\- B:  Several error handling Actors can be defined in a flow but not in an inner flow.

\- C:  Only a JavaScript Actor can be used for handling errors.

\- D:  The flow continues if an error handler returns true, otherwise the flow stops.

(**Solution 14. D: Any Actor can be used as an error handler and can be defined in any Stage. The Actor's logic is validated and when an error handler returns true, the flow continues. Otherwise the flow stops. An inner flow can be used as an error handler**).



#### Question 15: Invoke a Flow for Multiple Instances

Is it possible to run a Broadway flow for several instances? If yes - how?


\- A:  It is not possible.


\- B:  Via the Fabric Studio by providing an instance ID for each run.

\- C:  Using a BATCH command for a given list of instances.

\- D:  Using a BROADWAY command.

(**Solution 15. C: A flow can be run as a batch process by providing the batch configuration parameters and the LU name and flow name execution parameters**).



#### Question 16: Broadway Actors

Does Broadway support asynchronous message handling? If yes - how?


\- A:  It is not supported by Broadway.


\- B:  It is supported via Web Services.

\- C:  Broadway can subscribe to and publish the messages to Kafka only.

\- D:  Broadway can subscribe to and publish the messages to Kafka and any JMS provider.

(**Solution 16. D: Message provider types supported in Broadway are, Apache Kafka, JMS Queue and Topic by any JMS provider**).



#### Question 17: Broadway Actors

Can you read and update flow arguments during the flow?


\- A:  Flow arguments are read-only and are only accessible when the flow is invoked.


\- B:  Flow arguments can be accessed by a JavaScript Actor using a flowArgs keyword which enables reading the data and updating the flow context.

\- C:  Flow arguments can be accessed by a FabricGet Actor and updated by a FabricSet Actor.

\- D:  Flow arguments are accessible via the sync process only.

(**Solution 17. B: To read data from the flow's arguments and write into the flow's context, use the flowArgs keyword**).



#### Question 18: Data Inspector

Broadway Data Inspector (the yellow segment):


\- A:  Enables viewing and editing data when running a flow with Debug ON.


\- B:  Can identify and display complex Object data type structures regardless of the DEBUG flag.

\- C:  Displays the columns of complex Object data types but does not enable connecting them individually to other Actors arguments.

\- D:  None of the above.

(**Solution 18. D: A is incorrect because the data cannot be edited. B is incorrect because the structure can be identified in Debug mode only. C is incorrect since the columns of the complex data type can be connected individually to other Actors**).



#### Question 19: Transactions

When the flow is split into several branches, the transaction:


\- A:  Can be defined for selective branches.


\- B:  Must be defined for all branches.

\- C:  Must start one Stage before the flow split.

\- D:  Cannot be defined on the split part of the flow.

(**Solution 19. A: The transaction can be defined for selective branches. For example, for an IF-ELSE condition, you can define that the transaction occurs only when the condition is true**).



#### Question 20: Error Handling

Error Handling in a flow:


\- A:  Always catches any type of error.


\- B:  Can programmatically catch some errors and continue or stop the flow.

\- C:  Will always cause a rollback in the transactional Stage.

\- D:  Must include the error validation check.

(**Solution 20. B: The Error Handler can catch an error and either continue the flow or stop it**).

[![img](/articles/images/Previous.png)](22a_broadway_summary_exercise_solution.md)
