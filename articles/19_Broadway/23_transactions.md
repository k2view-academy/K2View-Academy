# Transactions

### Transaction Definition

- Broadway has a built-in Transactions Management mechanism. The transaction starts when the [Actor](03_broadway_actor.md) in the first [Stage](19_broadway_flow_stages.md) marked as a transaction requests to start a connection. 
- Several sequential Stages marked as transactions are part of the same transaction.
- The transaction ends after the last Stage marked as a transaction or at the end of the flow and is followed by a commit (or by a rollback if there are errors). 

![image](images/99_23_general_ex.PNG)

### Inner Flows Behavior

Transactions can include [inner flows](22_broadway_flow_inner_flows.md). If a transactional Stage executes an inner flow, it automatically becomes a part of the outer transaction and can use its shared resource.

When the outer flow starts the transaction and then invokes an inner flow, the inner flow does not close the transaction. The transaction is closed by the outer flow.

### Iteration Behavior

A transaction's behavior within an iteration depends on how the Iterator step is defined in the flow. 

An Iterator is a Stage holding an Actor that starts the loop over the data set. The Iterator dictates the number of times the iteration occurs. 

If the Iterator Stage is transactional, the commit is performed at the end of the data set. However, if the Iterator Stage is not transactional, the transaction starts inside the loop and the commit is per each iteration.

**Examples**

* Commit the data after the loop over the data set is completed. Start the transaction on the **source data** Stage and close it at the end of the iteration.

  ![image](images/99_23_commit_at_end.PNG)

* Commit per iteration. Only the **insert data** Stage inside the loop is transactional.

  ![image](images/99_23_commit_each.PNG)

* A mixed approach for handling transactions during an iteration can be used when the data set is very big (for example, 1M records) and a commit is required every X records. The transaction then starts before the loop and commits each time the counter reaches 10,000. 

  The following example shows how to perform a commit every 3 records using the **JavaScript** Actor and the **contextLoop.skip()** method.

  ![image](images/99_23_batch.png)

* The following is an example of a transaction's behavior in the loop when not all Stages inside the loop are transactional. There are two transactions in each iteration: one at the **insert data** Stage which is followed by a commit and another at the **query params** Stage which is also followed by a commit.

  ![image](images/99_23_complex_ex.PNG)

### Impact of Error Handling on Transactions

When an [Error Handler](24_error_handling.md) is defined in the transactional Stage of the flow and it catches an error, the Error Handler can either true to continue the flow or false to stop the flow. If the Error Handler returns false, the transaction ends with a rollback and the flow execution stops. The error message displays the failure reason.

### Impact of Stage Conditions on Transactions

When the flow is split due to [Stage conditions](/articles/19_Broadway/19_broadway_flow_stages.md#what-is-a-stage-condition), the transaction can be defined for only some branches. For example, for an IF-ELSE condition, you can define that the transaction occurs only when the condition is true. 

In the example below, the transaction starts in Stage B1 if the condition is true and ends after the completion of Stage C1. If the condition is false, there is no transaction in this flow.

![image](images/99_23_split1.PNG)

In the second example, the transaction starts in Stage A. If the condition is true, the transaction ends after the completion of Stage C1. If the condition is false, the transaction ends at the end of Stage A.

![image](images/99_23_split2.PNG)

Note that if there are several conditions or too many parallel branches in the flow, it is not recommended to use the Transactions mechanism across the branches.

### Shared and Non-Shared Transactional Interfaces

Fabric [Interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md) used in a Broadway flow can be shared or non-shared during the transaction.

* Using a **shared** interface in a flow, the Fabric opens a connection only once within the same transaction, when the first Actor calls this interface. All other Actors will use the same connection. The shared interfaces used by Broadway are DB Interface, file system or SFTP.
* Using  a **non-shared** interface, the Fabric establishes a connection each time an Actor calls the interface in the flow. A non-shared interface used by Broadway is HTTP.  

### How Do I Mark or Unmark a Stage as a Transaction?

In a Broadway flow window, a **Transaction** is marked by blue diagonal lines in the Stage's background and can span across several [Stages](19_broadway_flow_stages.md).

![image](images/99_23_01.PNG)



<table>
<tbody>
<tr>
<td valign="center" ><strong>To mark</strong> a Stage, click <img src="images/99_19_dots.PNG" alt="..." /> > Transaction.</td>
<td valign="center" ><strong>To unmark</strong> a Stage, uncheck Transaction.</td>
</td>
</tr>
<tr>
<td valign="center" ><img src="images/99_23_02.PNG" alt="Mark" /></td>
<td valign="center" ><img src="images/99_23_03.PNG" alt="UnMark" /></td>
</td>
</tr>
</tbody>
</table>

[![Previous](/articles/images/Previous.png)](22_broadway_flow_inner_flows.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](24_error_handling.md)
