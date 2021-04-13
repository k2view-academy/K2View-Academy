# Java Coding

1. Remove unused variables, redundant code, and commented out code.

2. Create a function if the same code is used in more than one place, thus avoiding duplicate code. 

3. Working with **IntelliJ** is highly recommended, as it provides features like code completion, code refactoring, and code debugging. For example,  points 1 & 2 mentioned above will appear as warnings, allowing you to fix them and improve your code quality.

4. Close all your resources: If your code implements auto closable, you can wrap it with **try()**. If this does not work, you should make sure you close it in the ‘**finally’** block. Examples:

   * **ResultSetWrapper** --> close all result sets in the **'finally'** block.  

   * **ludb().fetch**  --> Either wrap this command with the resource **try ()**,  or use **ludb().fetch().each** .

   * **ludb().fetch("select val from root", null).firstValue()** --> No need to close this, as it’s closed internally.  

   * **Db.Row rs = ludb().fetch("select \* from root", null).firstRow()** --> No need to close this, as it’s closed internally.  

      **NOTE:** It is recommended to change all **DBQuery** instances to **ludb().fetch** or **db().fetch**

5. Validate null values before executing a function on the given parameter. Example: 

    ~~~java
    if (x != null) 
    	x.substring(1);
    ~~~

6. Compare the constant to the parameter to avoid a **NullPointerException**. Example: 

    ~~~
    "CUSTOMER".equals (i_TableName)
    ~~~

7. String concatenation:

    * If you use a **toString()** function, first make sure that the value is not null. Another way to avoid a **NullPointerException** is by casting using **‘+ "" ‘**.
    * Use **StringBuilder** instead of **+** to concatenate strings. 
    * **String** is an immutable object and cannot be changed,  which means that if you use the **“+”** on a string object, a new object will be created. 

8. Avoid applying redundant casting. Examples:

    * Casting an object to **long** type and then using  **“+”** to concatenate it to the SQL query is redundant. 

    * Fabric alters all database sessions to use a uniform date format, and the default date field format is: yyyy-MM-dd HH:mm:ss (this default can be changed in the **config.ini** file).  If it is not needed, do not use casting when extracting a date field from the source.
    * If date casting is mandatory, make sure the dates being compared use the same format.

9. Prepare & Binding:

    * Use *Non-Bind* variables in an SQL statement only when the values are constant values.  

    * In any other case, use **Prepare** and send the values as parameters to the SQL statement. 

      **NOTE:** In Fabric 6.0 and above, there is no need to create a new **Object[]** to pass parameters to start binding. 

10. Use **binding** for a **get** command as well. 

    * In Fabric 6.2 and above, **binding** is supported for all Fabric commands.

11. Parameter definition:

     * Avoid setting unused parameters. 

     * Always define the parameters outside of a loop.

12. When selecting one value:

    * Use **ludb.fetch (<select statement>).**  **firstValue()”** instead of **Db.Rows rows = ludb.fetch(<select statement>)** with a loop.

    * In Fabric  5.4 and below, use **DBSelectValue** instead of **DBSelectQuery** and a loop.

13. Add **catch** to a **try** statement - Your process will not fail unless you throw the exception inside the catch block. 

    * There is no need to add a **catch** statement if there are no additional steps to be performed in case of a failure.

14. **log.info** should only be used for debugging. Remove these functions from your code before an actual production deployment.

15. It is recommended to combine two lists when possible. To do so, use the Java function: **myListObject.addAll** Do not use it for a loop. 

16. Always perform Unit Testing for your code! 

 

[![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_general.md) [<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_LU_and_Tables.md)

 
