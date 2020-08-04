# JavaScript Actor

### JavaScript Actor Overview

**JavaScript** Actor is an important and useful [built-in Actor type](04_built_in_actor_types.md) since it can help to simplify the flow by writing some of its business logic or validations using the JavaScript code in the **script** input parameter.

For the good usability of the Actor and the whole flow, it is recommended to write short pieces of JavaScript code though the Actor doesn't have a limitation and can include any number of the rows.

### Special Keywords and Conventions

- The return value of a **JavaScript** Actor is the last expression in the **script** input parameter. There is no need to write **return** keyword within the script. Use the **result** output parameter to read the Actor's result. For example, when the following code is written in the **JavaScript** Actor, the value of **rowSum** is returned. 

  ```
  var rowSum = 0;
  for each (var col in row) { 
   rowSum += col;
  }
  rowSum;
  ```

- Use the [**self** keyword](08_javascript_actor.md#example-of-how-to-sum-the-values-of-an-array) to maintain state across script executions, such as loop aggregation. For example: 

  ``` self.agg += value ```

- To [read data from completed Actors](08_javascript_actor.md#example-of-how-to-access-data-of-completed-actors-in-the-flow), you can access their id as a local variable, and read the output ports. For example, you can use **Const1.value** to access the value of Const1.

- To access iteration data, use the **contextLoop** object. You have access to **contextLoop.index()**, **contextLoop.stop()**, **contextLoop.skip()**.

- To read and write data to the flow arguments, you can use the [**flowArgs** keyword](08_javascript_actor.md#example-of-how-to-read-and-write-data-to-the-flow-arguments). You can also use **flowArgs** to write data to the flow context and refer to it in other actors.

- To iterate over a collection, use the standard syntax: 

  ``` rows.forEach(row => {...}) ```

- A special [**for...each** syntax](08_javascript_actor.md#example-of-how-to-use-foreach-syntax) is also supported: 

  ``` for each (var row in rows) { ... } ```

- To write to the log at INFO level, use **print()**. For example:

  ``` print(Const1.value) ```

- An error should be communicated by throwing an exception. For example: 

  ``` throw "Invalid Data Received" ```

### Code Examples

#### **Example of How to Sum the Values of an Array**

To summarize the values of an array, use the **self** keyword which gives an access to the Actor's state. The state is kept between the executions of the same Actor in the flow.

```  
self.sum = self.sum || 0;
for (var i =0;i<input.length;++i) {
    self.sum += input[i];
}
self.sum
```

#### **Example of How to Use For...Each Syntax**

**For...each** is a syntax extension allowing JavaScript to iterate over an Iterable instance.

```
var sum = 0;
for each (var i in input) {
    sum += Math.abs(i);
}
sum;
```

#### **Example of How to Access Data of Completed Actors in the Flow**

The **JavaScript** Actor can access the data of the previous Actors in the flow. For example:

 ```SumArray.result == ForEach.result && ForEach.result == 21 ```

where **SumArray** and **ForEach** are the names of the Actors in the flow that precede the current Actor.

#### **Example of How to Read And Write Data to the Flow Arguments**

The **JavaScript** Actor enables you to read the values of the flow arguments using the **flowArgs** scope and override or remove them.

```
    for (var i in flowArgs) {
        print(flowArgs[i]); //read the value
        flowArgs[i] = null; //remove the value
    }
    print(flowArgs.v3); //read the value of argument v3
```
