# Data Purging #

Purging data from a database is an important task, which must be undertaken from time to time for a variety of reasons: confidentiality, security, contractual obligations, saving of space, etc.

Fabric provides a solution for implementing a purge process that is secure and can be implemented in an on-going manner. The purge process can also function as an archiving process, allowing Fabric or an external data base to function as the archival repository. Since Fabric works with Logical Units, complex purging rules can be created and implemented without impacting performance. 

The purge process is designed using the Fabric Studio user interface, which eliminates the need to perform coding tasks, as the purge can be done by implementation only.

This purge solution also allows simple testing and validations, as the process populates detailed reporting tables of the purged entities and the logic that led to purging or not purging a specific database entry.

Specially designed purging tables are imported to Fabric, which define what actions to take for the purge. The user can copy these tables to the Logical Unit or Units on which he wants to carry out a purge process. The purge tables reside in the Logical Unit, and will only carry out the purge action if the appropriate switch is enabled.  

This Logical Unit-based purging process provides significant advantages, such as:

- Assuring that the purge will never affect the data integrity in your database, as for each such unit the deletion of data will only be done if it was successful.

- Allowing complex logic to be used to qualify data for purging. This logic is applied in each transaction on the data of the specific LU only, which means that only a small data volume is queried.

- Breaking the purging process down into small chunks and running it on a regular basis. 

If archiving of the data should be performed as well, it can be performed immediately before the purge of a specific unit, and the purge will only be done if the archive was completed successfully for this unit. The archive repository can be either Fabric itself or an alternative data repository.

<img src="images/Purge_Architecture.png" style="zoom: 67%;" />

Implementing a Purging project can be divided to the following macro steps: 

- **Define the purging rules** that should be applied in order to decide what data qualifies for purging
  
- **Importing the purge tables** that hold the purge actions

- **Implement the purging logic** on the specific LUs that have data to be purged. 

Once those steps are completed, the process is ready for deployment and execution.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/37_libraries/purging/02_purge_process_design.md)

