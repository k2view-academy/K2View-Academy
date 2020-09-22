# Broadway and Fabric - Examples

### ![info](/academy/images/example.png)Example 1 - Run a Flow via the Broadway Command

In this example you can use the **CheckNegative** Broadway flow which you created in [Example 2 - Inner Flow and Error Handling](16_broadway_addl_features_ex2.md) or create a new flow.

1. Deploy the LU which includes the flow definition. 
   * If the flow is created under the Shared Objects, deploy any LU, for example CRM LU.
2. Open the Fabric Console and run the **Broadway** command twice, providing different input.

~~~
fabric>broadway CRM.CheckNegative a = "-10";
Flow: CheckNegative Level: 1 Stage: Stage 1 Actor: ErrorMsg  class jdk.nashorn.internal.runtime.ECMAException Cause: A Can't be negative!

fabric>broadway CRM.CheckNegative a = 10;
|column|value                  |
+------+-----------------------+
|date  |2020-09-22 12:08:12.620|
~~~

3. Check that the flow results are as expected.



[![Previous](/articles/images/Previous.png)](20_broadway_and_fabric.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xx.md)