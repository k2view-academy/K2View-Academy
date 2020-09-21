# Examples of Additional Broadway Features

### ![info](/academy/images/example.png)Example 2 - Inner Flow and Error Handling

**Part 1 - Error Handling**

1. Create a new **CheckNegative** Broadway flow.

2. Click ![dots](images/three_dots_icon.png)> **Error Handler** in the Stage context menu of Stage 1 to select a **JavaScript** Actor and write the following validation in the **script** input argument:

   ~~~javascript
   a >= 0;
   ~~~

3. Add a new **a** input argument and set it as **External**.

4. Add another **JavaScript** Actor to the same Stage and write the following script to it:

   ~~~javascript
   throw "Can't be negative!"
   ~~~

5. The flow is ready and can be run with different positive and negative input arguments to check the results. 

   ![image](images/16_ex1.PNG)

6. When the input is negative, the following error message is thrown:

   ![image](images/16_ex2.PNG)

**Part 2 - Save a Flow as an Actor**

1. Click **Save as Actor** in the Main menu toolbar to save the current flow as an Actor and use its logic as an inner flow in another Broadway flow.

2. Populate the tag (category) name, for example **validatationChecks** and click **SUBMIT**. The new **validatationChecks** category is added and holds a **CheckNegative_Actor** Actor.

**Part 3 - Use an Inner Flow in a Flow**

1. Create an additional Broadway flow.

2. Add a **Const** Actor to Stage 1 and set the **value** = **1**. 

3. Add a **CheckNegative_Actor** Actor to Stage 2 and connect it to the **Const** Actor.

4. Add a **Logger** Actor to Stage 2 and connect it to the **Const** Actor also. Add the following message to the **Logger** Actor:

   ~~~javascript
   Hello ***${0}*** Hello!
   ~~~

5. The flow is ready and can be run and validated by the message in the log file.

   ![image](images/16_ex3.PNG)

6. Now change the value in the **Const** Actor to a negative value and run the flow again. Check that the error is thrown and the message of the **Logger** Actor is not printed into the log.

[![Previous](/articles/images/Previous.png)](15_broadway_addl_features_ex1.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](17_broadway_addl_features_ex3.md)

