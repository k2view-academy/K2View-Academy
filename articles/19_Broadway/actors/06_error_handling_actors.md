# Error Handling Actors

Broadway has a built-in error handling mechanism that handles exceptions in a flow using **Error Handlers**. An error handler is defined using a Broadway Actor. Any Actor or [Inner flow](/articles/19_Broadway/22_broadway_flow_inner_flows.md) can act as an error handler. 

This article describes the **ErrorHandler** and **ErrorFields** Actors which are dedicated for error handling.

For information about the error handling mechanism of Broadway, refer to the [Error Handling](/articles/19_Broadway/24_error_handling.md) article.

### How Do I Use the ErrorHandler Actor?

The **ErrorHandler** Actor can be used as an error handler to enable different handling of various exceptions. It can be configured to suppress one type of exception and not to suppress others, or to invoke an inner flow when an exception is thrown. 

The exceptions are classified into three types: SQL error, HTTP error and exception. The SQL errors are divided into Unique constraint or other. Note that the Unique constraint exception validation is only done for Oracle, DB2, SQLite and SQL servers. 

When the error is suppressed, the **ErrorHandler** returns **true** and the flow continues. When the inner flow is invoked, it receives the **error** object which includes all the exception details, such as the error code, the flow, etc. Note that when the inner flow is invoked and it returns either **true** or **false**, it will override the response of the **ErrorHandler** Actor. For example, if the **Suppress** indicator is checked, but the inner flow returns false - the exception is thrown and the flow fails.  

The following example shows how an **ErrorHandler** Actor is used as an error handler in the **LU Table** Stage. In the following configuration only **Unique Constraint** SQL errors are caught and suppressed. Any other error causes the flow to fail. 

![image](../images/99_actors_06_1.PNG)

The following example shows how different types of errors are handled whereby error handling is performed according to the order of the Actor's execution.

* On the **Unique Constraint** SQL error, suppress the exception.
* On any other exception, invoke the **errorHndlFlow**.

![image](../images/99_actors_06_3.PNG)

### How Do I Use the ErrorFields Actor?

The **ErrorFields** Actor can be used to get detailed information about an exception. The Actor can be used either as an error handler or as a regular Actor in an inner flow to access error information. The **ErrorFields** Actor always suppresses exceptions. 

![image](../images/99_actors_06_2.PNG)



When the **ErrorFields** Actor is used in an inner flow that is invoked from the **ErrorHandler** Actor, the **error** input argument must be defined as External. Then the error details are automatically passed from the calling flow.

<img src="../images/99_actors_06_4.PNG" alt="image" style="zoom:80%;" />

### Retry via the Error Handling Actors

Starting from V8.0, Broadway supports a retry mechanism: if any actor of the stage fails and the retry parameters are set, the actor is executed again. The retry can be configured using the **Retries** and **Interval** fields in the **ErrorHandler** Actor's editor (the default values are 0 and 500 msec). 

If the Inner Flow is defined in the ErrorHandler's editor, it overrides the Retry logic in the main flow. 

A retry can be defined for selected exception types only. When several different exception types are configured using the same ErrorHandler Actor, each with different retry parameters, each exception type triggers its own retry counter.

When the **Log** checkbox is checked, the number of attempts is written in the log, for example:

~~~
Stage 3 - starting to retry DbLoad1: 1
~~~



![image](../images/99_actors_06_5.png)

Note that the retry mechanism can be implemented by any actor defined as an error handler in a flow ('red actor'). In this case, the actor should return **result** = **retry** instead of *true* or *false* to activate the retry mechanism. While the inner flow returns **retry**, the main flow will continue the attempts.

![image](../images/99_actors_06_7.png)

When the retry is implemented by an **InnerFlow** Actor, the **ErrorFields** Actor may be used in this inner flow to check how many times the flow has already been executed - using the **attempt** output parameter. ![image](../images/99_actors_06_6.png)

[![Previous](/articles/images/Previous.png)](05_db_actors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_masking_and_sequence_actors.md)
