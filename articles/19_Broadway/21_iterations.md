# Iterations
### Overview

Iterations are used to repeatedly perform a section of a Broadway flow in a data set. Iterations are similar to a **for...each loop** in the sense that a logic is repeatedly run until no data remains to act upon.

Broadway Iterations enable to:

* Loop over:
  * One or several fields of a result set.
  * Data elements on different levels in the object's hierarchy, for example a field and a nested array.
  * More than one nested array of the same result set in the same iteration. 
  * Several result sets returned by different Actors in the same iteration.

* Combine different [Link Types](07_broadway_flow_linking_actors.md#link-object-properties) in the same iteration. 

* Execute a nested iteration within an iteration.

The common iteration use cases are iterating over a database result set, over a data returned by an HTTP call or an API or over Kafka messages. For example, [Broadway population flow](/articles/07_table_population/14_table_population_based_Broadway.md) iterates over the source DB data set to load it into the target DB. 


### How Do I Define an Iteration in a Flow?

To start the iteration, draw the line from the originating Actor's output and set the line's [Link Type](07_broadway_flow_linking_actors.md#link-object-properties) to **Iterate**. The connection line becomes double-dotted, the background of the loop's scope is highlighted grey and a thick divider line is displayed at the starting point.

The scope of the iteration's logic starts immediately after the iteration line and continues until the end of the flow or until the **Iterate Close** Stage. To mark an iteration Stage as closed, click ![image](images/99_19_dots.PNG) in the [Stage context menu](18_broadway_flow_window.md#stage-context-menu) >  **Iterate Close**.

In the below example, the **StringsArray** Actor is the iteration's originating Actor that returns an array of string values and the **Iterate on array** Stage runs on each array element. Then the iteration is closed and the flow continues.

![image](images/iterate_simple1.PNG)

### Single Connection Line

A simple iteration is when there is a single connection line from the originating Actor's output.

The originating Actor's output can be a complex object that includes an array of fields and nested arrays, such as an SQL query result set or a JSON object. 

The **Iterate** connection can be drawn from the output object itself or from any of its elements. 

##### Iterate Over a Row in a Result Array

The **result** output returns an array of maps. In each iteration, take the values of the entire map.

![image](images/iterate_path0.PNG)

##### Iterate Over a Field in a Result Array

Iterate on all the values of the **CUSTOMER_ID** field in the **result** output array.

![image](images/iterate_path1.PNG)

##### Iterate Over a Field in a Nested Array in a JSON Object

Iterate on all the values of the **externalCorrelationID** field in the **[header]** internal array in the output JSON object.

![image](images/iterate_path2.PNG)

### Multiple Connection Lines from a Result Array

In a flow there may be a need to iterate over several elements of the result set whereby it is possible to combine different link types. Note that the flow behavior changes based on the connection types.

##### Combination of Two or More **Iterate** Link Types

- Takes the values of the connected elements from the same iteration.

- In the below example: iterate over the **result** output array and take the values of CUSTOMER_ID, CONTRACT_REF_ID and CONTRACT_DESCRIPTION of the **current** iteration.

  ![image](images/iterate_mult_01.PNG)

##### Combination of **Iterate** and **Value** (or **First**) Link Types

- The elements connected using an **Iterate** link type, will have the value of the **current** iteration.
- The elements connected using a **Value** (or **First**) link type, will have the value of the **first** iteration during all iterations.

### Multiple Connection Lines from a JSON Object

When the originating Actor's output is a JSON object, the iteration's complexity increases because a JSON object usually includes an internal hierarchy of fields and nested arrays. Thus the iteration logic over a JSON object with multiple links is impacted by the answers to the following questions:

* Are the connected elements on the same level of hierarchy of the JSON object or different levels?
* Are the link types the same (all **Iterate**) or different (**Iterate** combined with other)?
* Do the connected elements belong to the same nested array or different nested arrays?

If the connected elements of the JSON are on the same level of hierarchy (such as two fields of the same array), the iteration behavior is the same as the iterations over [multiple connection lines in a result array]().

##### Iterate Over Both a Field and a Nested Array

* The connected elements are on different levels of hierarchy: **entityType** field in [resources] array and **objectType** field in [hardware] nested array.
* Both are connected using the **Iterate** link type. 
* The iteration runs over all elements of the [resources] array, and for each element - for all elements of the [hardware] nested array.

<img src="images/iterate_mult_02.PNG" alt="image" style="zoom:80%;" />

##### Iterate Over a Field and Take a Nested Array by Value

- The connected elements are on different levels of hierarchy: **entityType** field in [resources] array and the **[hardware]** nested array.
- The **entityType** is connected using the **Iterate** link type, while the **[hardware]** nested array is connected using the **Value** link type.
- The iteration runs over all elements of the [resources] array, and for each **entityType** takes all its hardware (the nested array data).

<img src="images/iterate_mult_03.PNG" alt="image" style="zoom:80%;" />

##### Iterate Over a Field and Take a First Value of a Nested Array

* This is a private case of the previous scenario with the same connected elements, where the **entityType** is connected using the **Iterate** link type, while the **[hardware]** nested array is connected using the **First** link type.
* The iteration runs over all elements of the [resources] array, and for each **entityType** takes the first element of its hardware nested array.

<img src="images/iterate_mult_05.PNG" alt="image" style="zoom:80%;" />

##### Take an Array by Value and Iterate Over a Field in the Same Array

* The connected elements are on different levels of hierarchy: **entityType** field in [resources] array and the whole **[resources]** array.
* The **entityType** is connected using the **Iterate** link type, while the **[resources]** array is connected using the **Value** link type.
* The iteration runs over all **entityTypes** of the [resources] array while during each iteration - the whole [resources] array is passed by value (the same data in all iterations).

<img src="images/iterate_mult_04.PNG" alt="image" style="zoom:80%;" />



## Nested Iterations

Iterations can also be nested. For example, a value in an iteration can be used as an input for another iteration. The depth of the iteration is highlighted in shades of grey. To limit the loop's scope using **Iterate Close**, add a closing Stage to each level of the loop.
There are no limitations on the iteration nesting level. However, to make a flow more readable, consider limiting a flow to 3-4 nesting levels and using [Inner Flows](22_broadway_flow_inner_flows.md) when more are needed.

In the following image, the first name is an input to a query that gets a list of relevant phone numbers. Stage 2 is run on every entry in Stage 1 and Stage 3 on every entry in Stage 2.

![image](images/iterate_nested_iterations.png)

## Split Iterations

A Stage can have more than one collection. A common case is a JSON data structure that contains more than one array.
Using the Stage Split functionality you can split the flow and manage several loops over the same data structure.

<img src="images/iterate_split.png" alt="image" style="zoom:50%;" />

## ForLoop Actor

The **ForLoop** Actor can be used to create a virtual data set of integers in a given range. This enables creating a loop that runs N times over a synthetic data set and is useful for repeat iterations when there is no data set to traverse.


## Programmatic Control

The Broadway Context object enables an Actor to programmatically access and control the loop using the Loop interface.
A Loop Context object can be accessed via the **Context.loop()** method in Java or using the **contextLoop** instance in JavaScript.

The following methods are supported:
* **Loop.stop()**, stops the current loop and continues execution after the loop. All Actors in the same Stage as the calling Actor are still invoked.

* **Loop.skip()**, skips the current loop iteration and continues to the next data entry. All Actors in the current Stage are still invoked.

* **Loop.index()**, returns the current loop index. The index of the first iteration = 0.

In a nested loop, you can only access the inner-most (deepest) loop that is running in the current Stage.

For more information, refer to the iterate-for-each.flow Broadway example.

[![Previous](/articles/images/Previous.png)](19_broadway_flow_stages.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](22_broadway_flow_inner_flows.md)

