# Broadway 

1. Use meaningful names and naming conventions. Some examples are shown below: 

    * Stages should start with an uppercase letter and can have spaces between the words for easier readability.  For example:  **Read payment file**, **Extract from CRM tables**, etc. 

    * Start each stage with a number identifying the stage order in the flow and its split information. For example, 
      **080.2 ABCD…** means it is the 8th stage in the flow and the 2nd split of that stage.

    * Names of Actors always start with an uppercase letter. If the name contains multiple words, each word should start with an uppercase letter such as **DbLoad**, **KafkaProducer**, etc.

    * For variables, use the Camel case format, starting with a lowercase letter, such as **firstName**, **lastName**, etc.  

2. Use built-in Broadway Actors when possible in order to reduce the complexity and to ensure the quality of the code.
  
3. Try to limit the number of stages and the number of Actors within each stage. Use inner flows where applicable, especially when creating a split across several consecutive stages or when using nested iterations.
  
4. There are no limitations on the iteration nesting level. However, to make a flow more readable, consider limiting a flow to 3-4 nesting levels.
  
5. Utilize the **DBLoad** Actor, as it has a built-in functionality of running INSERT, UPDATE or UPSERT commands (e.g. instead of DBCommand ).
  
6. An Actor can be exported to create another Actor that inherits the current Actor’s logic. If the same business functionality is expected to be used for multiple stages or flows, exporting the Actor will save time, reduce errors, and increase efficiency by reusing it for all relevant locations.
  
7. Use the  **ErrorHandler** Actor to leverage built-in reactions to different exception types. Use JavaScript as **ErrorHandler** when a customization is needed.
  
8. Use the **contextLoop.index()** function to get the current loop index instead of using an additional **Counter** Actor.
  
9. Remove unused Actors or utilize the **‘Disable’** functionality if you expect that some Actors will not be used in the future.

10. Where applicable, use the **HttpJson** Actor instead of the **Http** Actor.

    * The **HttpJson** Actor sends a request to a web server, stringifying the request into JSON and parsing the JSON result.

    * If there is a JSON parsing error, the Actor will throw an exception. 
    
11. Set the number of iterations to the number of requested attempts, and use the **ErrorHandler** Actor to catch an error. 

    * If no error appears until the last step (inside an iteration), stop the context by using **contextLoop.stop()**;

    * If the last iteration fails (you can check this by using **contextLoop.index(**)), throw an exception.
    
12. Broadway has a built-in Transactions Management mechanism. When using this mechanism for iterations, there are 3 options:

    * Commit after each iteration 
    * Commit at the end
    * Commit in batch

    Data volumes, performance, and business requirements should be taken into consideration when deciding which of the above 3 methods to use. For example:

	 - If the data set is big (for example: 1M records), consider **commit in batch** to improve the performance.  

	 - If a rollback of the entire data set is needed upon a failure, use **commit at the end** method.

13. For long running flows (for example, hours), consider using the **Recovery point** feature. In case of a flow failure, a re-run will utilize the serialized data and start from the last saved recovery point rather than from the beginning.
  
14. When dragging multiple links (of **Iterate** link type) from outside the loop to Actors inside it, consider using a **Const** Actor inside the loop, dragging one **iterate** line to it and many **value** lines from it.

15. When selecting one value, take the following into consideration: 

    * When selecting one row, use the **DbFetchFirstRow** Actor instead of the **DbCommand** Actor. 

    * When selecting one value, use the **DbFetchField** Actor instead of the **DbCommand** Actor.
    
16. Document and record all your project level Actors (whether built-in or new) with their inputs & outputs variables in order to enable better visibility and support of your flows.

17. When using **InnerFlowAsync**, especially in iterations, use **InnerFlowJoin** to verify that all the asynchronous inner-flow instances have completed.

18. An Actor output of type 'stream' can be linked to only one input parameter of one Actor. Linking 'stream' output to more than one target results in a functional failure, as the stream can only be read once.

19. Use Error Handle for each Stage containing **HTTP** Actor or **DB** Actor.

[![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_security.md) 
