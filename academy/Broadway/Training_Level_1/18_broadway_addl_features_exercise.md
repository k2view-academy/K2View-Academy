# Additional Broadway Features

### ![](/academy/images/Exercise.png) Exercise

1. In the Additional Broadway Features [Example 1 - Actor Inheritance](15_broadway_addl_features_ex1.md#example-1---actor-inheritance):

   a. Can you use the **myDate** Actor in another flow?

   b. How do you update the inherited Actor's arguments?

2. In Additional Broadway Features [Example 2 - Inner Flow and Error Handling](16_broadway_addl_features_ex2.md#example-2---inner-flow-and-error-handling):

   a. Is the only way to manage the described Error Handler by using a **JavaScript** Actor? If not - how?

   b. Add Stage 2 to the flow and add a **Now** Actor. When the **a** input argument is negative, is the **Now** Actor invoked? Is it invoked when **a** is positive?

   c. Modify the flow by using an **ErrorHandler** Actor as the Stage's error handler instead of the **JavaScript** Actor. Run the flow first suppressing the exception and then not suppressing it. What is the result?

3. In Additional Broadway Features [Example 3 - Transactions](17_broadway_addl_features_ex3.md#example-3---transactions):

   * Add Stage 7 at the end of the flow and add a **CheckNegative_Actor** Actor to it. 
   * Mark both Stage 6 and 7 as Transaction.
   * Run the flow when the **a** input argument of the **CheckNegative_Actor** Actor is negative.
   * Is the transaction committed or rolled back? Explain you answer.



[![Previous](/articles/images/Previous.png)](17_broadway_addl_features_ex3.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](19_broadway_addl_features_exercise_solution.md)
