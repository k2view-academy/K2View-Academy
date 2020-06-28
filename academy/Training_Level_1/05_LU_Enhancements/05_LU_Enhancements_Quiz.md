![](/academy/Training_Level_1/05_LU_Enhancements/images/EnhancementQuizState.PNG) 

# LU Enhancements Quiz

![](/academy/Training_Level_1/03_fabric_basic_LU/images/Quiz.png) 



Excellent! 
You have completed the Fabric Enhancements learning items.


Before moving to the next item, let's take the following quiz to see what you have learnt. The Quiz consists of a number of multiple-choice questions, each providing a number of possible answers. Click the answer you think is correct. 



#### Question 1: Fabric Functions

To perform standard transformation of dates, strings, or basic mathematical operations you will use:

A: A project function

B: A root function 

C: Built-in functions or any other java standard utilities


(***Solution 1: C ***).



#### Question 2: LUDB functions

LUDB functions are

A: An LUDB function is a function invoked from an SQL query to perform more complex operations on LU data than those performed using standard SQL statements.

B: a function used to create LU schemas automatically

C: a function used to query external sources

(***Solution 2: A ***).



#### Question 3: Root functions

Root functions are:

A: a function that is only applicable to data manipulations on root tables. 

B: a specific type of Fabric function, used as a Source Object to trigger the table population mechanism, run various SQL SELECT queries and execute complex logic.

C: a function that can be applied to any table of the LU schema, making it a root table

D: a function that operates data manipulations only if the systems root user is logged on

(***Solution 3: B ***).



#### Question 4: Enrichment functions

Which of the following statements is certainly true ?



A: Enrichment functions are functions without Input/Output parameters that are used to insert, update, or delete LU tables' data .

B: Multiple enrichment functions can be attached to any of the LU's tables

C:  Enrichment functions operate only after all LU tables have already been populated from a source object.

D: The Enrichment function runs only once per LU table and LUI. 

E: All the above

(***Solution 4: E ***).



#### Question 5: Decision functions

A Decision function is:

A: a complex SQL query with multiple where statements to decide which records to create, update or delete

B: a Java function with multiple input parameters and one Boolean output parameter.

C: a Java project function that assesses whether a sync is performed on an LUI

D: a function that runs an LU table population based upon special sets of conditions defined on other tables of the same LU

(***Solution 5: C ***).



#### Question 6: Lookup tables

It is recommended to create look-up tables based on: 

A: an external DB table to ensure the data is fresher than the data sitting in the LU.

B: an LU table to improve performance since it is kept in Fabric memory and since it is much smaller than the DB source.

C: It does not matter, each solution has its pro's and con's.

(***Solution 6: B ***).



#### Question 7: Translation tables

Translations tables are used to:

A: operate complex language translations between different DBs storing data of different languages 

B: map records using one optional input and one mandatory output value.

C: transform data from one set of valid values to another in order to enable the execution of various transformation rules.

(***Solution 7: B ***).



#### Question 8: Globals

Which of the following statements is true?

A: Globals are predefined variables that can be accessed by different objects within a project

B: Globals are used when the same information is required repeatedly by various Fabric objects.

C: Globals are saved in a Java file as static variables and can be used by all Fabric object types

D: Globals can be defined as Final or not final, depending on whether they need to be overridden

E: All of the above

(***Solution 8: E ***).





#### Question 9: Execution order

Which of the following statements is true?

A: Execution order define how population objects are executed within an LU schema.

B: Execution orders have a default value based on the LU's schema's hierarchy.

C:  Execution orders can be defined by the LU implementer after a very careful thinking and strategizing process.

D: The higher the number of the execution order, the lower will be the priority of the population mapping executions

E: All of the above

(***Solution 9: E ***).



#### Question 10: Enrichment order

Enrichment order only applies:

A: to decision functions.

B: to all types of Fabric functions.

C: to population executions.

D: to all enrichment functions and is determined at the LU schema level in the Enrichment Order tab. 

(***Solution 10: D ***).

####    

[![img](https://github.com/k2view-academy/K2View-Academy/raw/master/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/04_LU_Enhancements_lookup-translations_flow.md)
