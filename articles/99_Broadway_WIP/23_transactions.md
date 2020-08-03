# Transactions

Broadway has a built-in transaction management mechanism. **Transaction** in a Broadway flow is marked by blue diagonal lines and can span across several [Stages](19_broadway_flow_stages.md).

 ![image](images/99_23_01.PNG)

Each Stage in a flow can be marked as Transaction. 

- The transaction starts when an Actor on the first Stage marked as transaction is asking to start the connection. 
- Several sequential Stages marked as Transaction are part of the same transaction.
- The transaction ends in the last Stage marked as transaction. Then the commit occurs. In case of error, the rollback occurs. 

The transaction can be defined on DB related activities as well as on different kinds of entities, for example writing into a file. When a Broadway flow writes into a file, the end of transaction closes the file. 

The transactions can include [inner flows](22_broadway_flow_inner_flows.md). If a transactional Stage executes an inner Broadway flow, the flow automatically becomes a part of the outer transaction.

### How Do I Mark or Unmark a Stage as Transaction?

To mark a Stage as Transaction, click [![image](https://github.com/k2view-academy/K2View-Academy/raw/KB_DROP2_99_BROADWAY/articles/19_Broadway/images/99_19_dots.PNG)](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP2_99_BROADWAY/articles/19_Broadway/images/99_19_dots.PNG) to open the [Stage context menu](/18_broadway_flow_window.md#stage-context-menu) > **Transaction**.

![image](images/99_23_02.PNG)

To unmark a Stage, uncheck Transaction in the [Stage context menu](/18_broadway_flow_window.md#stage-context-menu).

![image](images/99_23_03.PNG)

[![Previous](/articles/images/Previous.png)](22_broadway_flow_inner_flows.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](24_error_handling.md)