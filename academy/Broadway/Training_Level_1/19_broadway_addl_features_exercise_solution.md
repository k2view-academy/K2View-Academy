# Additional Broadway Features

### ![](/academy/images/Solution.png) Solution

1. Actor Inheritance:
  a. Yes, the inherited Actor can be used in any other flow.
  b. Select <strong>Export Actor</strong> from the inherited Actor's context menu and update the definition of the arguments. 
  For example, change the <strong>tz</strong> input argument from <strong>hidden</strong> to <strong>empty</strong>, click the <strong>override current</strong> checkbox and submit.

2. Inner Flow and Error Handling:
  a. It is not the only way, the Error Handler can be defined using any Actor. 
  For example, the described validation can be performed using <strong>GreaterThanEquals</strong> Actor.
  b. When an error handler returns false, the flow stops. Thus when <strong>a</strong> is negative, the flow stops before the <strong>Now</strong> Actor is invoked.

  c. Modify the flow as follows:

  * Remove the existing error handler and add a new one selecting a **ErrorHandler** Actor. 

  * Modify the ErrorMsg **JavaScript** Actor to include the following script:

    ~~~javascript
    if (a < 0) {
        throw "A Can't be negative!";
    }
    ~~~

  * Run the flow once suppressing the exception via the **ErrorHandler** Actor and another time not suppressing.

  ![image](images/19_errHndl_1.png)

  * When the error is suppressed, the flow continues till the end.

  ![image](images/19_errHndl_2.png)

  * When the error is not suppressed, the flow fails.

3. Transactions:
  When an error handler returns false, the flow stops and the transaction ends with a rollback. 
  Thus when <strong>a</strong> is negative, the flow stops and the transaction is rolled back.


![image](images/10_flow_1.PNG)

[![Previous](/articles/images/Previous.png)](18_broadway_addl_features_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](20_broadway_and_fabric.md)
