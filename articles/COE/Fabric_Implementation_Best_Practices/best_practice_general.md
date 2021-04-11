
# General

1. Avoid executing **“set sync on”** before a “**get**” command as this is already the default

2. Avoid executing **“set sync off”** after a “**get**” command and before executing the queries, as such queries will not trigger a sync (applicable for Version 5.x and above)

   - This is the default system behavior, but it can be modified via a change in the **config.ini** file. 

    - On versions prior to 5.x, **“set sync off”** should be used.

3. Always attempt to use in-memory data and avoid accessing external interfaces

4. Use of Graphit 

   - Try using Graphit whenever possible and minimal (or no) java code to allow easier maintenance and readability. If you are not sure how to implement a specific functionality in Graphit, please contact COE.

        - Note: a Graphit file can be a standalone web service

   - If you use “**get**” inside the Graphit file, make sure a redundant “**get**” is not executed in the WS.

5. Avoid accessing the same table multiple times if all queries can be combined into one query 

6. Stop an LUI sync when it takes too long - Use the **”sync timeout”** property on the LU level 

7. Select references from within an enrichment function - Use **ludb.fetch** or **fabric.fetch**, instead of **FabricDB.fetch** (fabric local interface defined in the implementation),  as using **FabricDB.fetch** can impact the performance drastically 

8. Thread Globals – Use the **clearThreadGlobals** function  to make sure thread globals are cleared at the end of the “**get**” process 

9. Generate CSV file with Fabric data – When possible, use the Fabric **”set output file”** command followed by a query execution instead of writing code

10. LUDB Functions – 

    - The purpose of LUDB functions is to expand the SQL library 

    - An LUDB function that does not have or does not use input parameters and is being used within the query will be called multiple times and will return the exact same result for all rows.

11. Graphit call from an enrichment or a job - 

    - Use the resources **‘try’** or **‘close’** to release the entry back into the GraphitPool. 

       + Without this step, fabric will generate/compile a new graphit every time, which will negatively affect performance.

    o  Code Example:

        GraphitPool.Entry entry = getLuType().graphitPool().get("Customer360.graphit"); 
        Graphit graphit = entry.get();**
        Object result = graphit.run();**
        entry.close()** 
        
[<img align="right" width="60" height="54" src="/articles/images/next.png">](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_java_coding.md)

------
