# Cassandra Main Keyspaces & Commands 

### ![](/academy/Training_Level_1/04_fabric_runtime/images/fabric_execute_04.png)

Now that you can retrieve and view data in Fabric, let's learn how the data is stored in Cassandra, about the operational information that is displayed and the commands used to access it:

-  [Cassandra Keyspaces for Fabric](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md)

-  [Cassandra Basic Commands](/articles/02_fabric_architecture/07_cassandra_basic_commands.md)

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Keyspaces & Commands

 Let's look at our project and product keyspaces:

1. Run **describe keyspaces;**.

2. Change the keyspace to **CustomerLU**  by executing **use k2view_customerlu;**

3. Let's review the entities in **k2view_customerlu.entity** using the following statement: **select * from k2view_customerlu.entity**;  Note, you can also review the results from fabric executing the following:

   **cql select * from k2view_customerlu.entity;** The results varies a bit ,  instead of a  huge blob  you'll be able to review the microDB file sizing.

|id |batch_id|chunks_count|data       |key_desc_id|schema_hash|sync_version |

+---+--------+------------+-----------+-----------+-----------+-------------+

|82 |        |1           |**BYTES[4568]**|0          |1024909903 |1592227429701|

|215|        |1           |**BYTES[2634]**|0          |1024909903 |1592227437514|


### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Exercise.png)Exercise – Keyspaces & Commands

1. `Question: Which Instance IDs are stored in k2view_customerlu?` `How will you list them?`
2. `Question: How will you list the LUTs that have been deployed?`

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)Solution - Keyspaces & Commands

1. `Answer:215, 82;` 

   `cassandra@cqlsh> select id from k2view_customerlu.entity ;` 

   

2. `Answer: select lut_name, lut_version, lut_index_status from k2system.k2_lut_info; or by k2view_* keyspaces being created`



 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/04_fabric_runtime/04_fabric_basic_commands.md) 
