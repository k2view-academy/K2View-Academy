# Jobs Configuration

Jobs Execution Environments can be configured at both node and cluster levels.


## Nodes Configuration
Priority for a given job on any given logical and physical node can be defined within the node configuration file.
For example: it is possible to define that node X will handle a maximum of 10 threads in parallel by setting the #K2JOBS_POOL_SIZE variable to 10 in the 

***Config.ini***
In this file can be set jobs-related configuration variable. It is located in the in the k2view/config/config.ini file under the section *JOBS*.
K2JOBS_POOL_SIZE=10: This value will define the size of the thread pool for fabric jobs processing. 
K2JOB_ARCHIVING_TIME_HOUR=720: This value will define the time to delete the job row from k2_jobs table. Default is 720 hours(30 days).


***Node.ini***
In this file can be set node and cluster-related configuration variables. It is located in the in the k2view/config/node.ini file.
In node.id file in k2view/config it is possible to define a set a number of logical names or Node Identifiers for the node in question; such node identifiers can be used for the job affinity mechanism. Note that there can be more than one logical names since a node can have more than one logical role.

The node UUID is unique and if left undefined, fabric will generate a random one while starting, the first time.
Example: ```uuid:7da16985-a8ac-4ea1-8e93-3118a225edd7```

The logical_id name helps define affinity between a node and candidate jobs. Therefore, in order to limit the number of fabric jobs running on a node (i.e. with the same affinity), each logical_id can be associated with the maximum number of jobs.

Example:
3 logical names were given for NODE 1.
node_b:1
node_c:3
node_d:6
Assuming 3 jobs are running, with the following afinities:
- Job 1 with AFFINITY=node_b
- Job 2 with AFFINITY=node_c 
- Job 3 with AFFINITY=node_d
It means the third job will get the highest priority on node 1




#cluster identifier - should contain only letters and numbers
#cluster_id: FabCluster1





## Cluster Configuration

***Heartbit***
## This value will define the delay of fabric heartbeat frequency. Default is 10 sec.
FABRIC_HEARTBEAT_INTERVAL_MS=5000

***KeepAlive***
## Defined the number of the heartbeat that fabric node can miss before it will be considered as not alive.
FABRIC_HEARTBEAT_MISS=12


[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/04_jobs_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/06_jobs_configuration.md)
