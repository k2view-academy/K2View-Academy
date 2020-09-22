# Examples of Additional Broadway Features

### ![info](/academy/images/example.png)Example 3 - Transactions

To see this example, open the flow you created in the [Using Actors in a Flow](10_using_various_actors_exercise.md) exercise. The flow already has a Transaction that has not yet been examined.

![image](images/10_flow.PNG)



1. Run the flow and verify that a new entry has been added to the ACT_SUM Fabric LU table.
2. Click ![dots](images/three_dots_icon.png)> **Transaction** in the Stage 6 context menu to uncheck the Transaction.
3. Run the flow again and verify that a new entry has not been added to the table.
4. Check the  message in the log file to verify that a rollback has been performed.
5. Add Stage 7 and then add a **CheckNegative_Actor** Actor to it. Mark this Stage as a Transaction also.
6. Run the flow again with a positive or a negative input for a **CheckNegative_Actor** Actor and check the flow's behavior in each run. 
   * When the input is a positive number, the flow finishes successfully with a commit. 
   * When the input is a negative number, the **CheckNegative_Actor** throws an error and the flow ends with a rollback.



[![Previous](/articles/images/Previous.png)](16_broadway_addl_features_ex2.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](18_broadway_addl_features_exercise.md)

