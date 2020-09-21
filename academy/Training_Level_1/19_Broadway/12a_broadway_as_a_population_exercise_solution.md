# Broadway Flow as a Population

### ![](/academy/images/Solution.png) Solution - Creating a Broadway Flow Based Population 

<ul>
<pre><code>
1. The <strong>iid</strong> holds the Instance ID of the execution, in the above example - customer ID. 
The <strong>parent_rows</strong> is the iterator of the parent rows, in the example - the list of activity IDs under the given customer.
2. The SQL statement is: SELECT * FROM CASES WHERE ACTIVITY_ID IN (...) AND STATUS = ...
3. Add an Actor to <strong>Stage 1</strong> in the template flow to perform the required data manipulations.
</code></pre>
</ul>

[![Previous](/articles/images/Previous.png)](12_broadway_as_a_population_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](13_interface_listener_exercise.md)

