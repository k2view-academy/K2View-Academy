# Broadway 

1. Use meaningful names and naming conventions. 

    a. Stages should start with an uppercase letter and can have spaces between the words for easier readability.  For example:  ‘Read payment file’, ‘Extract from CRM tables’ etc. 

    b. For better clarity, start each stage with a number identifying the stage order in the flow and its split information, e.g. '080.2 ABCD…' means it’s the 8th stage in the flow and the second split of that stage.

    c. Actor - Starts with uppercase letter. If the name contains multiple words, each word should start with the uppercase letter such as DbLoad, KafkaProducer etc.

    d. Variable – Use Lower Camelcase format; starts with lowercase letter. If the name contains multiple words, start it with the lowercase letter followed by an uppercase letter such as firstName, lastName. 

2. Use built-in Broadway Actors when possible to reduce the complexity and ensure the quality of the code.
  
3. Try to limit the number of stages and the number of Actors within each stage. Use inner flows when applicable, especially when creating a split across several consecutive stages or when using Nested Iterations.
  
4. There are no limitations on the iteration nesting level. However, to make a flow more readable, consider limiting a flow to 3-4 nesting levels.
  
5. Utilize the **DBLoad** Actor as it has a built-in functionality for INSERT, UPDATE or UPSERT commands (e.g. instead of DBCommand ).
  
6. An Actor can be exported to create another Actor that inherits the current Actor’s logic. If the same business functionality is expected to be used for multiple stages or flows, exporting the Actor will save time, reduce errors, and increase efficiency by reusing it for all relevant locations.
  
7. Use the  **ErrorHandler** Actor to leverage built-in reactions to different exception types. Use JavaScript as **ErrorHandler** when customization is needed.
  
8. User the **contextLoop.index()** function to get the current loop index instead of using additional **Counter** Actor.
  
9. Remove Actors that are not being used or utilize the **‘Disable’** functionality if you expect that some Actors will not be used in the future.

10. When applicable, use **HttpJson Actor** instead of **Http Actor**

    a. This Actor sends a request to a web server stringifying the request into JSON and parsing the JSON result.

    b. If there is a JSON parsing error, the Actor will throw an exception. 
    
11. Set the number of iterations to the number of requested attempts and error handler Actor to catch an error. 

    a. If no error appears untill the last step (inside iteration), stop context by using **contextLoop.stop()**;

    b. If the last iteration fails (validate that using **contextLoop.index(**)), throw an exception.
    
12. Broadway has a built-in Transactions Management mechanism. When using transaction for iterations there are three options:

    a. Commit after each iteration 
    b. Commit at the end
    c. Commit in batch

     Data volumes, performance, and business requirements should be taken into consideration when deciding which method to use. For example:

	 - If the data set is big (For example: 1M records), consider **‘commit in batch’** to improve the performance.  

	 - If rollback of the entire data set is needed upon failure, use **‘commit at the end’** method.

13. Recovery Point – For long running flows (i.e Hours), consider using the **‘Recovery point’** feature. In case of a flow failure, a re-run will utilize the serialized data and start from the last saved recovery point rather than from the beginning.
  
14. Links into Loops – when dragging multiple links (of type **iterate**) from outside the loop to Actors inside it, consider using a **Const** Actor inside the loop, dragging one **iterate** line to it and many **value** lines from it

15. When selecting one value: 

    a. When selecting one row, use the **DbFetchFirstRow** Actor instead of the **DbCommand** Actor 

    b. When selecting one value, use the **DbFetchField** Actor instead of the **DbCommand** Actor
    
16. Document and record all your project level Actors (whether built-in or new) with their inputs & outputs variables to enable better visibility and support of your flows.

[![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_security.md) 
