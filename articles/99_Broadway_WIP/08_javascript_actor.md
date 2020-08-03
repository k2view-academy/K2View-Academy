# JavaScript Actor

### JavaScript Actor Overview

**JavaScript** Actor is an important and useful [built-in Actor type](04_built_in_actor_types.md) since it can help to simplify the flow by writing some of its business logic or validations using JavaScript.

The purpose of the Actor is to execute small pieces of JavaScript code provided in the **script** input parameter though the Actor doesn't have a limitation and can include any number of the rows.

The return value of **JavaScript** Actor is the last expression in the **script** input parameter. For example, when the following code is written in the **JavaScript** Actor, the return value is **rowSum**. There is no need to write **return** keyword within the script. Use the **result** output parameter to read the Actor's result.

```
var rowSum = 0;
for each (var col in row) { 
 rowSum += col;
}
rowSum;
```

### Special Keywords and Conventions

- Use the **self** keyword to maintain state across script executions, such as loop aggregation. For example: **self.agg+=value**.
- To write to the log at INFO level, use **print('...')**.

- An error should be communicated by throwing an exception, for example: **throw "Invalid Data Received"**.
- To access iteration data, use the **contextLoop** object. You have access to **contextLoop.index()**, **contextLoop.stop()**, **contextLoop.skip()**.
- To read data from completed Actors, you can access their id as a local variable, and read the output ports. For example, you can use **Const1.value** to access the value of Const1.
- To read and write data data to the flow arguments, you can use the **flowArgs** keyword (flowArgs.input1). You can also use **flowArgs** to write data to the flow context and refer to it in other actors.
- To iterate over a collection, use the standard syntax: **rows.forEach(row => {...});** .
- A special **for...each** syntax is also supported: **for each (var row in rows) { ... }** .