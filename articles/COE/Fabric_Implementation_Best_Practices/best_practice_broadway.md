# Broadway 

1. Name Convention - Use meaningful names and naming conventions 

   o  Stages - Should start with an uppercase letter and can have spaces between the words for easier readability. e.g: ‘Read payment file’, ‘Extract from CRM tables’ etc. (optional) For better clarity, start each stage with a number identifying the stage order in the flow and its split information, e.g. “080.2 ABCD…” means it’s the 8th stage in the flow and the second split of that stage.

   o  Actor - Starts with uppercase letter. If the name contains multiple words, each word should start with the uppercase letter such as DbLoad, KafkaProducer etc.

   o  Variable – Use Lower Camelcase format; starts with lowercase letter. If the name contains multiple words, start it with the lowercase letter followed by an uppercase letter such as firstName, lastName

2. Built-In Actor – Use built-in Broadway actors when possible to reduce the complexity and ensure the quality of the code
    

3. Create Readable Flows – Try to limit the number of stages and the number of actors within each stage. Use inner flows when applicable, especially when creating a split across several consecutive stages or when using Nested Iteration
    

4. Nested Iteration - There are no limitations on the iteration nesting level. However, to make a flow more readable, consider limiting a flow to 3-4 nesting levels.
    

5. Load Data – Utilize the DBLoad Actor as it has a built-in functionality for INSERT, UPDATE or UPSERT commands (e.g. instead of DBCommand ).
    

6. Create Actors – An Actor can be exported to create another Actor that inherits the current Actor’s logic. If the same business functionality is expected to be used for multiple stages or flows, exporting the actor will save time, reduce errors, and increase efficiency by reusing it for all relevant location.
   

7. **ErrorHandler** - Use **ErrorHandler** actor to leverage built-in reactions to different exception types. Use     JavaScript as **ErrorHandler** when customization is needed
        

8. **contextLoop.index()** – Use this function to get the current loop index instead of using additional Counter     actor
            

9. Redundant actors – Remove actors that are not being used or utilize the **‘Disable’** functionality in case     future use of it is expected.
       

10. Call to an API which returns Json – (when applicable) use HTTPJSON instead of HTTP

    o  This actor sends a request to a web server stringifying the request into JSON and parsing the JSON result.

    o  In case of a JSON parsing error, the actor will throw an exception. 
     

11. Retry mechanism – Set the number of iterations to the number of requested attempts and error handler actor to catch an error. 

    o  If no error appears untill the last step (inside iteration), stop context by using **contextLoop.stop()**;

    o  If the last iteration fails (validate that using **contextLoop.index(**)), throw an exception.
     

12. Transaction Management – Broadway has a built-in Transactions Management mechanism. When using transaction for iterations there are three options:

    o  Commit after each iteration 

    o  Commit at the end

    o  Commit in batch

    Data volumes, performance, and business requirements should be taken into consideration when deciding which method to use. For example:

    o  If the data set is big (For example: 1M records), consider **‘commit in batch’** to improve the performance  

    o  If rollback of the entire data set is needed upon failure, use **‘commit at the end’** method

13. Recovery Point – For long running flows (i.e Hours), consider using the **‘Recovery point’** feature. In case of a flow failure, a re-run will utilize the serialized data and start from the last saved recovery point rather than from the beginning.
    

14. Links into Loops – when dragging multiple links (of type iterate) from outside the loop to actors inside it, consider using a Const actor inside the loop, dragging one “iterate” line to it and many “value” lines from it



15. When selecting one value: 

    o  When selecting one row, use “**DbFetchFirstRow**” actor instead of “**DbCommand**” actor 

    o  When selecting one value, use “**DbFetchField**” actor instead of “**DbCommand**” actor
    

16. Document – Document and record all your project level actors (whether built-in or new) with their inputs & outputs variables to enable better visibility and support of your flows

[![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_security.md) 
