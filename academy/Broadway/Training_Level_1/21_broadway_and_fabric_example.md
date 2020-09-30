# Broadway and Fabric - Example

### ![info](/academy/images/example.png)Running a Flow using a Broadway Command

In this example you can either use the **CheckNegative** Broadway flow you created in [Example 2 - Inner Flow and Error Handling](16_broadway_addl_features_ex2.md) or create a new flow.

The purpose of **CheckNegative** flow is to validate if the input is a negative number or not. In case of a negative number return the current date, otherwise throw an error message.

1. Deploy an LU that includes the flow definitions. 
   * If the flow is created under Shared Objects, deploy any LU, for example CRM LU.
2. Open the Fabric Console and run the **Broadway** command twice using different input.

~~~
fabric>broadway CRM.CheckNegative a = "-10";
Flow: CheckNegative Level: 1 Stage: Stage 1 Actor: ErrorMsg  class jdk.nashorn.internal.runtime.ECMAException Cause: A Can't be negative!

fabric>broadway CRM.CheckNegative a = 10;
|column|value                  |
+------+-----------------------+
|date  |2020-09-22 12:08:12.620|
~~~

3. Check that the flow's results are as expected.

Note that in order to provide a negative number as a parameter value, you need to enclose it with double quotes as show in the example above ("-10").

[![Previous](/articles/images/Previous.png)](20_broadway_and_fabric.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](22_broadway_summary_exercise.md)
