# Cassandra

1. The **Truncate** operation In Cassandra tables is required if the **Consistency Level** is set to **ALL**. When using **truncate** in your code, make sure to use **delete** statements in the catch (in case one or more nodes are down).

2. Cassandra interface Setting: 

    a. Set the Cassandra interface with all IPs of the given cluster to achieve high availability and load balancing (applicable for Fabric Version 5.x and above - set it to localhost for lower versions of Fabric). 

     If a node goes down, Fabric will use one of the other IPs to connect to the cluster 

    b. The default CL of the Cassandra interface is **QUORUM**. Set it manually to your cluster’s CL. If needed, set the **readtimeoutms** & **writetimeoutms** parameters to increase\decrease the default duration defined in the Cassandra **yaml** file.

3. Reloading data: 

    a. When data is reloaded (upsert) to Cassandra periodically (on a weekly basis for example), compactions and size must be taken into consideration. 

    b. If possible, drop the data and reload (insert). This needs to be decided per project\required functionality.

     Example:

   ​	Requirement: Load 600M records to Cassandra table on a daily basis

   ​	Solution:

   ​			Create table <TABLE_NAME>_A

   ​			Load the data to table ‘_A’

   ​			On the next day, load the data into table ‘_B’ and drop table ‘A’

   ​			Use a system global to identify the current “active “table

4. Use Cassandra Batch Command for multi statements. 

    a. When sending several statements in separate transactions, if one statement is successful and the seconds fails, there is no rollback to the first one. 

    b. Use a Cassandra batch command IN to send it all in one transaction and achieve atomicity. In such cases if one fails, they will all fail. 

    c. Batch with conditions cannot be used across partitions or across multiple tables.

     **NOTE:** The disadvantage of using batch command is that this might impact the performance. Make sure to test this before deploying it in the field!

5. Use of Fabric Cassandra singleton

    a. In versions prior to Fabric 6, use this command: **CassandraClusterSingleton.getInstance().getDefaultSession();**

    b. In Fabric Version 6 and above, use this command:  **CassandraClusterSingleton.getInstance().getSession();**   Do not close this session 

6. Cassandra queries - Avoid using **allow filtering** . 

7. Parser\Job for Cassandra Table Load:
    a. Apply the generic functionality to split the data retrieval and load from the source system to Cassandra to multi-thread the process and improve the performance. This logic will split the data based on ranges to multiple parsers which will run in parallel.

    b. You can control the number of parsers running in parallel by using the **logical id** functionality (in the **node.id** file) or by limiting the number of jobs in the config.ini file  (**K2JOBS_POOL_SIZE**)

     Example: If the source table has 100M records, you can split it into ranges of 10M each and run 10 parallel parsers to load the data.

8. LU Tables populated using full refresh from Cassandra:

    a. Reload the data only if it is being used in the web service or by another table that was updated. 

    b. Set a thread global in the web service and check this global in the root population to control when this occurs.

9. Cassandra table structure - A large partition can cause performance and load issues on specific servers. Consider this when defining the table structure.

10. Avoid using manual compactions:-

    a. Compaction in Cassandra happens automatically, but the frequency of it depends on the selected compaction strategy. 

    b. When [forcing a major compaction](https://docs.datastax.com/en/dse/6.0/dse-admin/datastax_enterprise/tools/nodetool/toolsCompact.html) on a table, all the SSTables on the node get compacted together into a single large SSTable. Due to its size, the resulting SSTable will likely never get compacted out again
    
    
    [![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_LU_and_Tables.md) [<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_kafka.md)
