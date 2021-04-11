# Java Coding

1. Close resources - If it implements auto closable, you can wrap it with **try()**. If not, you should make sure you close it in the ‘**finally’** block. Examples:

   o  **ResultSetWrapper**- close all result sets in the finally 

   o  **ludb().fetch** --> Either wrap it with resource **try ()** or use **ludb().fetch().each**

   o  **ludb().fetch("select val from root", null).firstValue();** --> No need to close it as it’s closed internally  

   o  **Db.Row rs = ludb().fetch("select \* from root", null).firstRow();** --> No need to close it as it’s closed internally  

   ​	**NOTE:** It is recommended to change all **DBQuery** to **ludb().fetch** or **db().fetch**
   

2. Validate null values before executing a function on the given parameter.

   o  Example: if (x != null) x.substring(1);

   o  Compare the constant to the parameter to avoid a nullPointer exception

   ​	§ Example: "CUSTOMER".equals (i_TableName)

3. String casting – If you use a **toString()** function, first make sure that the value is not null. Another way to avoid a “Null Pointer Exception” is by casting using **‘+ "" ‘**

4. String concatenation

   o  Use **StringBuilder** instead of **“+”** to concatenate strings

   ​	§ String is an immutable object and cannot be changed,  which means that if you use the “+” on a string object, a new object will be created 

5. Avoid applying redundant casting

   o  For example, casting an object to “long” type and then using **“+”** to concatenate it to the SQL query is redundant. 

   o  Date Casting

   ​	§ Fabric alters all database sessions to use a uniform date format 

   ​	§ The default date field format is: yyyy-MM-dd HH:mm:ss. Do not use unnecessary casting when extracting a date field from the source.

   ​	§ This default format can be changed in the config.ini

   ​	§ If casting is mandatory, make sure the dates being compared are using the same format 
    
    

6. Prepare & Binding

   o  Use *Non-Bind* variables in the sql statement only when the values are constant values 

   o  In any other case, use **‘Prepare’** and send the values as parameters to the SQL 

   ​	**NOTE:** There is no need to create new **Object[]** to pass parameters for binding starting with version 6.0 and later

7. Use **binding** also for **‘get**’ command 

   **NOTE:** In Fabric 6.2 and later versions, **binding** is supported for all Fabric commands 



8. **Parameter definition**

   o  Avoid setting unused parameters 

   o  Always define the parameters outside of a loop

9. **When selecting one value**

   o  use **“DBSelectValue”** instead of **“DBSelectQuery”** and a loop (lower versions of Fabric only)

   o  use **“ludb.fetch (<select statement>).** **firstValue()”** instead of **“Db.Rows rows = ludb.fetch(<select statement>)”** with a loop

10. Add **‘catch’** to a **‘try’** statement - Your process will not fail unless you throw the exception inside the catch block. 

    o  There is no need to add a **‘catch’** statement if there are no additional steps to be performed in case of a failure.

11. **ReportUserMessage\log.info** should only be used for debugging – Remove these functions from your code before an actual production deployment.

12. Combine two lists – In order to combine two lists use Java function: **‘myListObject.addAll’.** Do not use it for a loop. 

13. Remove unused variables, redundant code, and commented out code

14. Duplicate code – Create a function in case the same code is being used in more than one place

15. Working with **intellij** is highly recommended - It provides features like code completion, code refactoring, and code debugging. 

    o  For example: points 13 & 14 mentioned above will appear as warnings, allowing you to fix them and improve your code quality.

16. Always perform Unit Testing for your code! 

 

[![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_general.md) [<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_LU_and_Tables.md)

 
