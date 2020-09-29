# Broadway Certification Exam

<img src="images/Quiz.png" style="zoom:80%;" /> 

Excellent! You have completed the Broadway Training and the Summary Exercise.

Let's take the following certification example to see what you have learnt. The exam consists of a number of multiple-choice questions, each providing a number of possible answers. 

Click the answer you think is correct. 



#### Question 1: Broadway Overview

Broadway is the Fabric module that will allow you to:


\- A:  Populate Logical Units from external sources.


\- B:  Subscribe to a message bus and consume messages.

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


\- B:  Constant for some Actors and Link for others.

\- C:  Always defined as Constant.

\- D:  Populated based on the Actor's position in the flow.


(**Solution 3. A: The population of the input parameter depends on the population type. The supported types are:  Link, Const or External**).



#### Question 4: Data Types Conversion

If the Actor expects to receive a Boolean value but receives a number instead, Broadway will:


\- A:  Throw an error message due to an incompatible data type.


\- B:  Convert the number to a Boolean value: false if 0, otherwise true.

\- C:  Ignore the input argument's value.

\- D:  Open a pop-up window to insert the correct value.


(**Solution 4. B: Supported Broadway types can be converted automatically to other supported types expected by the Actor**).



#### Question 5: Broadway Flow Stages

A Broadway flow is built of several Stages whereby:


\- A:  The Stages are executed from left to right in the flow, each Stage can include one or more Actors and the Actors are executed top-down within the Stage.


\- B:  Any Stage in a flow can be split to create parallel execution branches.

\- C:  Any two Stages in a flow can be merged into one Stage.

\- D:  All the above.


(**Solution 5. A: B is incorrect because a root Stage cannot be split. C is incorrect because only the Stages with the same parent Stage can be merged**).



#### Question 6: Stage Conditions

A Stage condition:


\- A:  Can only be defined using a JavaScript Actor.


\- B:  Stops the execution of the branch if false is returned by the Actor's first output.

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

\- C:  A SourceDbQuery Actor which inherits from the DbCommand that must include a WHERE clause that connects the table to its parent table.

\- D:  A SourceDbQuery Actor which inherits from the DbCommand whereby the WHERE clause is generated in the background by connecting the parent_rows input argument to the PopulationArgs Actor's output.


(**Solution 9. D: The data is retrieved by a SourceDbQuery Actor that inherits from the DbCommand Actor. The WHERE clause is generated automatically and is not visible in the Actor's UI.**).



#### Question 10: Broadway Integration with Fabric Studio

In a Broadway flow you can:


\- A:  Invoke any Project function or a Graphit resource under the selected Logical Unit.


\- B:  Execute the SET and GET commands on the current Fabric session.

\- C:  Connect to any Fabric interface and validate an SQL query using the Query Builder window.

\- D:  All the above.

(**Solution 10. D: All the above are supported**).



#### Question 11: Actor Inheritance

When creating an Inherited Actor, the new Actor:


\- A:  Must keep their Ancestor's input and output arguments as-is.


\- B:  Must set all their Ancestor's input and output arguments as either hidden or final.

\- C:  Can set their Ancestor's input and output arguments as either hidden or final.

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


\- B:  Impacts the transactional Stages but don't impact Inner flows which fall within the transaction.

\- C:  When defined on an iteration, can do commit (or rollback) either on each iteration or at the end of the iteration data set.

\- D:  Cannot be defined on a split Stage.

(**Solution 13. C: There are two approaches for handling transactions during an iteration: closing the transaction and performing a commit on each iteration or after the loop ends.**).



#### Question 14: Error Handling

When handling the errors and exceptions in a Broadway flow:


\- A:  An error handler must be defined at the last Stage of the flow.


\- B:  Several error handling Actors can be defined in a flow but not in an inner flow.

\- C:  Only a JavaScript Actor can be used for handling errors.

\- D:  The flow continues when an error handler returns true, otherwise the flow stops.

(**Solution 14. D: Any Actor can be used as an error handler and it can be defined in any Stage. The Actor's logic is validated and when an error handler returns true, the flow continues. Otherwise the flow stops. An inner flow can be used as an error handler.**).



#### Question 15: Invoke a Flow for Multiple Instances

Is it possible to run a Broadway flow for several instances? If yes - how?


\- A:  It is not possible.


\- B:  Via the Fabric Studio.

\- C:  Using a BATCH command for a given Instance Group.

\- D:  Using a BROADWAY command.

(**Solution 15. C: It is possible run a flow as a batch process providing the batch configuration parameters and the LU name and flow name execution parameters.**).



#### Question 16: xxx

xxx


\- A:  xx.


\- B:  xx.

\- C:  xx.

\- D:  xx.

(**Solution 16. X: XXX.**).



#### Question 17: xxx

xxx


\- A:  xx.


\- B:  xx.

\- C:  xx.

\- D:  xx.

(**Solution 17. X: XXX.**).



#### Question 18: xxx

xxx


\- A:  xx.


\- B:  xx.

\- C:  xx.

\- D:  xx.

(**Solution 18. X: XXX.**).



#### Question 19: xxx

xxx


\- A:  xx.


\- B:  xx.

\- C:  xx.

\- D:  xx.

(**Solution 19. X: XXX.**).

[![img](/articles/images/Previous.png)](22_broadway_summary_exercise.md)
