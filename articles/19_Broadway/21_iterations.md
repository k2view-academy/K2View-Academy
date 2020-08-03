# Iterations

Iterations are a way to perform a flow section repeatedly for a given data set.
They are similar to a for...each loop in the sense that a given logic is run repeatedly until there is no more data to act upon.
The most common use cases are iterating over a database result set or an array of data returned by an API.


## Iterable Line Type

To start the iterable logic, select the line where you want the loop to start and change the [Link Type](20_broadway_flow_linking_actors.md#link-object-properties) to Iterable. This changes the line to a double dotted line and marks the loop scope with a grey background and a starting thick line.

<div align="center"><img src="images/iterate_simple.png" height="130px"/></div>

In the image above, Stage 2 will run for each data entry returned by the the first actor.

## Scope of Iteration

The scope of the logic starts right after the Actor where the Iterate line type is originating from. It continues till the end of the flow or till a stage that is marked as *Iterate Close*. To mark a Stage iteration as closed , click ![image](images/99_19_dots.PNG) to open the [Stage context menu](18_broadway_flow_window.md#stage-context-menu) >  **Iterate Close**. 

<div align="center"><img src="images/iterate_scope.png" height="160px"/></div>

Above we can see the iteration loop starting in *Stage 2* and running until *Stage 3* for each entry. After the data is traversed the loop is complete and *Stage 4* is executed.

## Complex Objects and Paths

Path connections work well in combination with an iteration loop.

<div align="center"><img src="images/iterate_path.png" height="160px"/></div>

Here we can see an iterate connection picking a field in a database result set and iterating over it.

## Nested Arrays

Given a data set that has nested arrays, picking an item in inner array to loop on, will iterate over the entire data set.

<div align="center"><img src="images/iterate_nested_array.png" height="160px"/></div>

For instance, given a data set of names maps, containing an array of maps with the field name, Broadway will traverse all the *name* values.


## Nested Iterations

You can also nest iterations. For instance you can use one of the values in an iteration as an input to a further iteration. The depth of the iteration is marked with a darkening shade of grey. If you want to limit the loop scope using *Iterate Close* you need a closing stage for each level of the loop.
There is no limit to iteration nesting level. However, consider using inner flows if you have more than 3-4 nesting level to make your flow more readable.

<div align="center"><img src="images/iterate_nested_iterations.png" height="160px"/></div>

In the above flow, we use the first name as an input to a query to get the list of relevant phone numbers. *Stage 2* is run for every entry in  *Stage 1* and *Stage 3* for every entry in *Stage 2*.

## Split Iterations

You could have more than one collection at a certain stage. A common case is a JSON data structure that contains more than one array.
Using the stage split functionality you can split the flow and manage several loops over the same data structure.

<div align="center"><img src="images/iterate_split.png" height="180px"/></div>

## ForLoop Actor

The ForLoop actor can be used to create a virtual data set of integers in a given range. This enables the creation of a loop running N times over this synthetic data set. It is a useful way to iterate a number of times when there is no concrete data set to traverse.


## Programmatic Control

The Broadway Context object enables an Actor to programmatically access and control the loop it resides in, through the Loop interface.
You can access the Loop Context object via the *Context.loop()* method in Java or using the *contextLoop* instance in Javascript.

It supports the following methods:
* *Loop.stop()* Stops the current loop and continues execution after the loop. All actors on the same stage as the calling actor are still called.

* *Loop.skip()* Skips the current loop iteration and continues to the next data entry. All actors on the current stage are still called.

* *Loop.index()* Returns the current loop index. 0 is the index of the first iteration.

In a nested loop, you only have access to the inner most (deepest) loop that is running the current stage.
You can see how this works from the Javascript actor in the iterate-for-each.flow Broadway example.

[![Previous](/articles/images/Previous.png)](20_broadway_flow_linking_actors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">]()
