# Purge Flow

The flow executed by the purging application is as follows:
For each instance of the Logical Unit (e.g. customer):
a.	Load the Logical Unit by retrieving the data from the source
b.	In case of fields that needs to be masked, run the masking enrichment function
c.	Go over the purge checks that are described in this section, according to the order of group id and then of check order. For each of those checks, register the key of this entry and check and if the result was positive in a result table. 
d.	Once all the checks were completed, select according to the result tables what information can be purged. An entry in the logical unit can be purged only if it got positive result in all of the queries define on it. For example - a specific line in the Invoice table of a specific customer is qualified to be purged only if the results for all of the queries that were defined for it were positive.
e.	According to the keys of each entry, purge it in the source database. Only if the purge of all the qualified entries of this logical unit instance (i.e. customer) were purged successfully, commit the deletion transaction.
f.	Move to the next logical Unit Instance and repeat the cycle. You will have several of such cycles running in parallel, according to the number of nodes in your Fabric cluster and the number of workers that are defined in the config.ini





[![Previous](/articles/images/Previous.png)](/articles/37_libraries/purging/01_purge_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/37_libraries/purging/03_purge_project_creation.md)
