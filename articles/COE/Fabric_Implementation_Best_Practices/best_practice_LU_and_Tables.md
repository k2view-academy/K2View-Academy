# LU & Tables

1. Table Population - 

   o  Always consider the order in which the table is populated

   o  Set up **‘Truncate mode’** in case the population extracts all data for a given instance 

   ​	**NOTE:** The **‘Truncate mode’** setting on the population level truncates the entire table (For example, even if it was set on the 3rd population)

   o  Set up **‘No Truncate mode’** in case the population extracts a delta from the source

   o  It is recommended to set up **‘Insert’** mode on the 1st population

   o  Make sure indexes are defined on the source database for the **‘where’** statement columns (LUDB Schema link fields)

   o  Limit the number of link fields to the minimum required to fetch the relevant instance data

2. LUDB \ Reference Queries 

   o  Mark the Key fields of each table and set them as a unique index 

   o  Create the relevant indexes based on your statement.

   ​	**NOTE:** Creating the correct indexes improves the performance of select, update, and delete statements but will slow down the performance of insert statements. 

   If not created correctly, indexes may slow the performance of select, update, and delete statements. Therefore, it is recommended to execute **explain query plan** to validate that the correct indexes are being utilized 

   o  If you apply additional manipulation/transformation in the query on the index fields, it will not be used

   ​	§ For example: Concatenation of two fields on the **'where'** statement. In such case even if there is an index on those fields, it will not be used.

   o  If needed, enforce the index utilization in the query using **INDEXED BY**. If it is failing on parsing, use **/\* sqlite \*/ or /\* k2_no_parse \*/** before the select (depending on the Fabric version)

   o  Simplify queries used to achieve better performance and readability

   o  Avoid using the **JOIN** operator too many times in the same query. Consider splitting the query to several simple queries.

   o  Avoid using the **UNION** operator too many times. Additionally, use UNION ALL in the case that data being retrieved by the sub queries is unique. This will improve the performance as UNION adds another step of ‘distinct’ between the queries results.   

3. To validate that a record exists, select the first row with the required **‘where’** condition (using **limit 1 or rownum < 2** – depending on the DB). 

   o  Do not use **count(\*)** in the query as this is time consuming.

4. Remove unused fields from the LU

   o  Remove fields that are not used in the project (with the customer’s approval). 

   o  If iidFinder is being utilized, many **‘update’** messages might become redundant (update of fields which do not exist in the LU). Therefore, filtering should be applied (either on the extract or in the GG replicat code which writes to Kafka)

5. Root functions not using the input field as part of the query or in the function logic

   o  If these populations are connected to the logical parent and the input is not being used in the query when there is more than one distinct value for the input, the function will be executed multiple times. 

   o  The following should be done:

   ​	§ Connect the population to the root table

   ​	§ If this cannot be done due to other logical constraints (for example a ‘**delete orphans’** functionality), a thread global should be used to make sure this population is being executed only once

6. Table Population on top of the LUDB - Do not set it to automatically run every second. This population should be executed only when the source tables are being updated and therefore in most cases should be based on **decision** function.

7. Table population **‘Update/Delete’** mode – When using **‘Update’** mode, make sure the tables’ key fields are being marked. Otherwise, all records will be updated on each iteration. As a result, all records will be updated with the last iteration data.

   Example:

![A screenshot of a cell phone  Description automatically generated](file:///C:/Users/SHMUEL~1/AppData/Local/Temp/msohtmlclip1/01/clip_image004.png)