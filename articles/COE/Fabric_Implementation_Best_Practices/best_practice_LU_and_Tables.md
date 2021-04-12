# LU & Tables

1. Table Population: 

    a. Always consider the order in which the table is populated.

    b. Set up **Truncate mode** if the population extracts all data for a given instance. 

     **NOTE:** The  **Truncate mode**  setting on the population level truncates the entire table (even if it was set on the 3rd population, for example).

    c. Set up **No Truncate mode**  if the population extracts a delta from the source.

    d. It is recommended to set up the **Insert**  mode on the 1st population. 

    e. Make sure indexes are defined on the source database for the **where**  statement columns (LUDB Schema link fields).

    f. Limit the number of link fields to the minimum required to fetch the relevant instance data.

2. LUDB \ Reference Queries:  

    a. Mark the Key fields of each table and set them as a unique index. 

    b. Create the relevant indexes based on your statement.

    **NOTE:** Creating the correct indexes improves the performance of SELECT, UPDATE, and DELETE statements, but will slow down the performance of INSERT statements. 

    c. If not created correctly, indexes may slow the performance of SELECT, UPDATE, and DELETE statements. Therefore, it is recommended to execute **explain query plan** to validate that the correct indexes are being utilized. 

    d. If you apply additional manipulation/transformation in the query on the index fields, it will not be used. For example: Concatenation of two fields in the WHERE statement. In this case, even if there is an index on those fields, it will not be used.

   e. If needed, enforce the index utilization in the query using **INDEXED BY**. If it is fails upon parsing, use **/\* sqlite \*/ or /\* k2_no_parse \*/** before the SELECT (depending on the Fabric version). 

   f. Simplify queries used to achieve better performance and readability.

   g. Avoid using the JOIN operator too many times in the same query. Consider splitting the query to several simple queries.

   h. Avoid using the UNION operator too many times. Additionally, use UNION ALL if the data being retrieved by the sub queries is unique. This will improve the performance as each UNION instance adds another action between the results of the queries, and this adds time.    

3. To validate that a record exists, select the first row with the required WHERE condition (using **limit 1 or rownum < 2** – depending on the DB). 

   a. Do not use **count(\*)** in the query as this is time consuming.

4. Remove unused fields from the LU.

   a. Remove fields that are not used in the project (with the customer’s approval). 

   b. If iidFinder is being utilized, many UPDATE messages might become redundant (update of fields which do not exist in the LU). Therefore, filtering should be applied (either on the extract or in the GG replicated code which writes to Kafka)

5. Root functions not using the INPUT field as part of the query or in the function logic:

   a. If these populations are connected to the logical parent and the input is not being used in the query when there is more than one distinct value for the input, the function will be executed multiple times. 

   b. Connect the population to the root table.

   If this cannot be done due to other logical constraints (for example a ‘**delete orphans**  functionality), a thread global should be used to make sure this population is executed only once

6. Table Population on top of the LUDB:

   a. Do not set it to automatically run every second. This population should be executed only when the source tables are being updated and therefore in most cases should be based on a DECISION function.

7. Table population UPDATE/DELETE  mode:

   a. When using UPDATE  mode, make sure the tables’ key fields are marked. Otherwise, all records will be updated on each iteration. As a result, all records will be updated with the last iteration data.

   Example: 

![image](/articles/COE/Fabric_Implementation_Best_Practices/images/best_practice_lu.png)


[![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_java_coding.md) [<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_cassandra.md)
