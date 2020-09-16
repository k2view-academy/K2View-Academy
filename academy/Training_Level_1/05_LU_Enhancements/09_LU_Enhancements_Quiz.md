![](/academy/Training_Level_1/05_LU_Enhancements/images/EnhancementQuizState.PNG) 

# LU Enhancements Quiz

![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png) 



Excellent! 
You have completed the Fabric Enhancements learning items.


Before moving to the next item, take the following quiz to see what you have learnt. The Quiz consists of a number of multiple-choice questions, each providing a number of possible answers. Click the answer you think is correct. 



#### Question 1: Fabric functions

To perform standard transformation of dates, strings or basic mathematical operations you will use:

A: A project function.

B: A root function. 

C: Built-in functions or any other standard Java utilities.

(***Solution 1: C ***).



#### Question 2: LUDB functions

An LUDB function is a:

A: Function invoked from an SQL query to perform more complex operations on LU data than those performed using standard SQL statements.

B: Function used to create LU schemas automatically.

C: Function used to query external sources.

(***Solution 2: A ***).



#### Question 3: Root functions

A Root functions is a:

A: Function that is only applicable to data manipulations on root tables. 

B: Specific type of Fabric function used as a Source Object to trigger the table population mechanism, run various SQL SELECT 
   queries and execute complex logic in Java.

C: Function that can be applied to any table in the LU schema, making it a root table.

D: Function that operates data manipulations only if the system's root user is logged in.

(***Solution 3: B ***).



#### Question 4: Enrichment functions

Which of the following statements is definitely true?

A: Enrichment functions are functions without Input / Output parameters that are used to insert, update or delete an LU table's data.

B: Multiple enrichment functions can be attached to any of the LU's tables.

C: Enrichment functions operate only after all LU table populations are executed.

D: All the above.

(***Solution 4: D ***).



#### Question 5: Decision functions

A Decision function is a:

A: Complex SQL query with multiple WHERE statements that decide which records to create, update or delete.

B: Java function with multiple input parameters and one Boolean output parameter.

C: Java project function that assesses whether a sync should be performed on an LUI.

D: Function that runs an LU table population based on specific sets of conditions defined on other tables in the same LU.

(***Solution 5: C ***).



#### Question 6: Lookup tables

It is recommended to create look-up tables based on: 

A: An external DB table to ensure the data is fresher than the data in the LU.

B: An LU table or any internal Fabric object like a reference or translation that improves performance since it is 
   saved in the Fabric memmory and is much smaller than the DB source.

C: It does not matter, each solution has its pros and cons.

(***Solution 6: B ***).



#### Question 7: Translation tables

Translations tables are used to:

A: Operate complex language translations between different DBs storing data in different languages.

B: Map records using no more than one optional input and one mandatory output value.

C: Transform data from one set of valid values to another to enable the execution of various transformation rules.

(***Solution 7: C ***).



#### Question 8: Globals

Which of the following statements is true?

A: Globals are predefined variables that can be accessed by different objects within a project.

B: Globals are used when the same information is required repeatedly by various Fabric objects.

C: Globals are saved in a Java file and can be defined on different project tree levels (shared, LU, etc...).

D: Globals can be defined as Final or not Final, depending on whether they need to be overridden.

E: All of the above.

(***Solution 8: E ***).



#### Question 9: Execution order

Which of the following statements is true?

A: Execution order defines the order of the execution of population objects in an LU schema.

B: Execution orders have a default value based on the LU schema's hierarchy.

C: Execution orders can be defined by the LU implementer after a very careful decision-making and strategizing process.

D: The higher the number in the execution order, the lower the priority in the population mapping execution.

E: All of the above.

(***Solution 9: E ***).



#### Question 10: Enrichment order

An Enrichment order only applies to:

A: Decision functions.

B: All types of Fabric functions.

C: Population executions.

D: All enrichment functions and is determined on the LU schema level in the Enrichment Order tab. 

(***Solution 10: D ***).


####    

[![img](https://github.com/k2view-academy/K2View-Academy/raw/master/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/04_LU_Enhancements_lookup-translations_flow.md)
