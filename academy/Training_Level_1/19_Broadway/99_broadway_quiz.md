# Broadway Quiz

![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png) 



Excellent! You have completed the Broadway learning items.


Let's take the following quiz to see what you have learnt. The Quiz consists of a number of multiple-choice questions, each providing a number of possible answers. Click the answer you think is correct. 



#### Question 1: Broadway Overview

Broadway is the Fabric module that will allow you to:


\- A:  Populate the Logical Units from external sources.


\- B:  Subscribe to a message bus and consume messages.

\- C:  Execute scheduled activities (user jobs) .

\- D:  All the above.

(**Solution 1: D; All the above activities can be done using Broadway**).



#### Question 2: Actor Overview

The Broadway Actors are:


\- A:  Built-in Java classes that should be used as-is in a flow and cannot be modified.


\- B:  Exposed REST APIs that can be invoked by any external system.

\- C:  Ready-to-use classes with predefined business logic that can be extended with additional functionality if needed.

\- D:  None of the above.


(**Solution 2: C; Broadway offers a number of built-in Actors which address a wide range of predefined activities and can be extended by creating inherited Actors**).



#### Question 3: Actor's Inputs and Outputs

The values in the Actors input parameters are:


\- A:  Populated based on the selected population type: Link, Const or External.


\- B:  Constant for some Actors, Link for other.

\- C:  Always defined as Constant.

\- D:  Populated depending on the Actor's position in the flow.


(**Solution 3: A; The population of the input parameter depends on the population type. The supported types are:  Link, Const or External**).



#### Question 4: Data Types Conversion

If the Actor expects to receive a Boolean value but receives a number instead, Broadway will:


\- A:  Throw an error message due to incompatible data type.


\- B:  Convert the number to Boolean value: **false** if 0, otherwise **true**.

\- C:  Ignore the input argument's value.

\- D:  Open a pop-up window to insert the correct value.


(**Solution 4: B; Supported Broadway types can be converted automatically to other supported types expected by the Actor**).



#### Question 5: Broadway Flow Stages

A Broadway flow is built of several Stages whereby:


\- A:  The Stages are executed from left to right in the flow, each Stage can include one or more Actors and the Actors are executed top-down within the Stage.


\- B:  Any Stage in a flow can be split to create parallel execution branches.

\- C:  Any two Stages in a flow can be merged into one Stage.

\- D:  All the above.


(**Solution 5: A; B is incorrect because a root Stage cannot be split. C is incorrect because only the Stages with the same parent Stage can be merged.**).



#### Question 6: Stage Conditions

A Stage condition:


\- A:  Can only be defined using a **JavaScript** Actor.


\- B:  Stops the execution of the branch if **false** is returned by the Actor's first output.

\- C:  Should be the only Actor in the Stage.

\- D:  Is needed to define the **else** condition.


(**Solution 6: B; If a Stage condition is false, its branch's execution stops. The condition Actor can have any number of output parameters, however a true / false check is performed on the first on the first one.**).



#### Question 7: Iterations

Iterations in Broadway:


\- A:  Are started by setting the link type of the Actor that begins the loop to **Iterate**.


\- B:  Can be stopped programmatically by accessing the context object.

\- C:  Support having more than one loop over the same data structure .

\- D:  All the above.

(**Solution 7: D; All the above is supported by Broadway Iterations**).



#### Question 8: Broadway Flow Iterations

xxx:


\- A:  ???.


\- B:  ???.

\- C:  ???.

\- D:  ???.


(**Solution 8: X; XXX**).




[![img](/articles/images/Previous.png)](XXX.md)