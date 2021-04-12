# iidFinder

1. iidFinder\Staging.xml levels: 

    a. Try to reduce the number of levels in the **staging.xml\iidFinder.xml** . If applicable (the table contains the LUID field), set the table as a first level. 

3. **sourceAvailable** setting in **iidFinder\staging.xm**l : 

    a. The latest iidFinder implementation is tested with **sourceAvailable** set to **false**, so the recommendation is to use this setting. 

    b. If this property is not set in your project, the default is **true**. If you are using the previous implementation version, special scenarios will not be supported (LCK, LPK & Cross instances). 

4. **Setting store=false** in the **iidFinder\staging.xml** :

    a. Even if the table does not have subordinate tables, it is recommended to save the cache data for it. 

    b. Store can be set to **false** only in the case when this table has no subordinate tables and when the link field will always be presented in the Kafka message (Either it is a PK field or the GG extract is defined as **all_column_ind = true**)

5. Rules to delete data from the LU (Example: Purging of "old" records):  

    a. Apply a change to execute the purging only when an update is received for the specific table and not for every sync (Using the generic **trnExecUserActivity**)

    b. Records must be deleted from the cache tables as well. Otherwise, iidFinder will continue linking insert\update of the subordinate tables for deleted records. Use the **addDeleteFinde**r plus **runDeleteFinder** APIs to delete these records from the cache.

6. Execute Enrichment functionality only when required. 

    a. Make sure the enrichment is being executed only once a message was received for the relevant table

    b. Use **trnExecUserActivity** functionality to set the relevant thread global 

7. Do not extract from source in case of a new instance 

    a. Use **EXTRACT_FROM_SOURCE_IND = false** and add the iidFinder generic LU decision function**: fnIIDFCheckExtractFromSourceInd**

    b. Make sure that there are no scenarios that cannot be supported. For example, records which were orphans on the source before the new instance was created. 

8. IIDF Root setting

    The following is the correct setting:

   ​	 Truncate = **false**

   ​	 Unique index on the IID

   ​	 population mode = **upsert**

   If the truncate mode is set to “true”, the **deleteOrphans** functionality for LPK scenario on a table that is connected to the root might trigger the deletion of records which should not be deleted.  

9. **IIDF_EXTRACT_FROM_SOURCE & IIDF_SOURCE_DELAY** setting:

   **IIDF_SOURCE_DELAY** is used for rearranging transactions from different DCs or to re-execute a transaction after extract from a source that might receive a delayed update transaction. If the **EXTRACT_FROM_SOURCE** is set to “false” and there is no multi-DC cluster, there is no point in saving the transactions in the queue. It will just consume additional space.

10. Lookup tables in Cassandra: Apply Kafka transactions. 

    a. Update the lookup table in Cassandra using a job consumer and not an enrichment function. If enrichment is used, such as when the delta record was not synced yet, the lookup will not be updated and the web service will return **no data found**. Generic lookup consumer information can be found in the COE knowledge base.

    b. As the lookup is usually built from only a few fields of the original table, create a separate topic for it (if possible) and apply filtering so that only if one of the columns gets changed, the message will be published to this topic. This can be done on the GG replicat to Kafka and will reduce the load from the consumer and the cluster.

11. IIDF Kafka Details – Do not store the IIDF Kafka details in a translation table. Use **IifProperties.getInstance()** to get the IIDFinder configuration setting of the cluster.

![image](/articles/COE/Fabric_Implementation_Best_Practices/images/best_practice_iid_finder.png)

Note: Kafka Producer & Consumer examples can be found in the COE knowledge base project 

11. Kafka Delta **get** jobs – If you are using the Kafka delta topic for the **get** mechanism, it is very important to set all globals \ iifConfig parameters correctly. Otherwise, data might not be deleted from Delta or **‘sync’** might be skipped. 

    a. **DELTA_JOB_DELAY_TIME** should be set to a higher value than the **DELTA_RANGE_DELAY_MS** to ensure that the records will be deleted. 
     Delete Formula: 

    ​	**MAX_DC_UPDATE – DELTA_EXTRA_TIME_MS** < Delta records update_time  <  **CURRENT_TIME - DELTA_RANGE_DELAY_MS**

    b. **DELTA_JOB_PREV_MESSAGE_DIFF_EPSILON_MS** – Setting this parameter skips a transaction if this IID was already synced in the last *X* msec – Meaning if we set it to 2 seconds and instance 111 was synced on 10:00:01, a transaction on 10:00:02 for the same instance will be skipped. Therefore, if you must ensure that each get will be executed, set this parameter to **0**.

12. iidFinder Library – Make sure you are using the latest version from the GIT repository.  


[![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_kafka.md) [<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_security.md)
