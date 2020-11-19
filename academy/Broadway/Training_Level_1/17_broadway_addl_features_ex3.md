# Examples of Additional Broadway Features

### ![info](/academy/images/example.png)Example 3 - Transactions

Open the flow you created in the [Using Actors in a Flow](10_using_various_actors_exercise.md) exercise. The flow already has a Transaction that has not yet been examined.

![image](images/10_flow.PNG)



1. Run the flow and verify that a new entry has been added to the ACT_SUM Fabric LU table by the **ACT_SUM** Actor.
2. Click ![dots](images/three_dots_icon.png)> **Transaction** in the Stage 6 context menu to uncheck the Transaction.
3. Run the flow again and verify that a new entry has not been added to the table. 
4. Check the  message in the log file to verify that a rollback has been performed.
   * Note that data is automatically committed when writing into a DB that supports the Auto Commit process. However, when writing into Fabric, the Transaction must be explicitly added  otherwise it will be not committed.

[![Previous](/articles/images/Previous.png)](16_broadway_addl_features_ex2.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](18_broadway_addl_features_exercise.md)

