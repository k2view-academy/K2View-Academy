# Transactions

Broadway has a built-in Transactions Management mechanism. 


In a Broadway flow, a **Transaction** is set between two blue diagonal lines which can span across several [Stages](19_broadway_flow_stages.md).

 ![image](images/99_23_01.PNG)

Each Stage of a flow can be marked as a transaction. 

- The transaction starts when the Actor marked Transaction in the first Stage requests to start a connection. 
- Several sequential Stages marked as Transactions are part of the same transaction.
- The transaction ends in the last Stage marked Transaction and is followed by a Commit.   The transaction is rolled back if there are errors. 

A transaction can be defined on DB-related activities as well as on different types of entities. For example writing into a file. When a Broadway flow writes into a file, the end of Transaction closes the file. 

Transactions can include [inner flows](22_broadway_flow_inner_flows.md). If a transactional Stage executes an inner Broadway flow, the flow automatically becomes a part of the outer transaction.

### How Do I Mark or Unmark a Stage as a Transaction?

To mark a Stage as a transaction, click [![image](https://github.com/k2view-academy/K2View-Academy/raw/KB_DROP2_99_BROADWAY/articles/19_Broadway/images/99_19_dots.PNG)](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP2_99_BROADWAY/articles/19_Broadway/images/99_19_dots.PNG) to open the [Stage context menu](/18_broadway_flow_window.md#stage-context-menu) > **Transaction**.

![image](images/99_23_02.PNG)

To unmark a Stage, uncheck **Transaction** in the [Stage context menu](/18_broadway_flow_window.md#stage-context-menu).

![image](images/99_23_03.PNG)

[![Previous](/articles/images/Previous.png)](22_broadway_flow_inner_flows.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](24_error_handling.md)
