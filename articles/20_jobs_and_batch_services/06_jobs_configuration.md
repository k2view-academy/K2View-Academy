# Jobs Configuration

When running jobs a few parameters must be configured at both node and cluster levels.


## Nodes Assignment
Priority for a given job on a given logical and physical node can be defined in the node configuration file.
For example: it is possible to define that node X will handle maximum 10 threads in parallel by setting the #K2JOBS_POOL_SIZE variable to 10 in the config.ini file located in
In node.id file in Fabric/config it is possible to define the following
node_b:1
node_c:3
node_d:6
It means 3 logical names were given for node 1.
Let’s assume 3 jobs are running, it is possible to define:
First job AFFINITY=node_b, Second job AFFINITY=node_c, Third job=node_d
It means the third job will get the highest priority on node 1

***Config.ini***
In this file can be set jobs-related configuration variable. It is located in the  
K2JOBS_POOL_SIZE=10: This value will define the size of the thread pool for fabric jobs processing. 
K2JOB_ARCHIVING_TIME_HOUR=720: This value will define the time to delete the job row from k2_jobs table. Default is 720 hours(30 days).


***Node.ini***
# This file lists the node identifiers.
# Node identifiers can be used in the job affinity mechanism
# There can be more than one since a node can have more than one logical role.

# If UUID is not defined fabric will generate one while starting.
uuid:7da16985-a8ac-4ea1-8e93-3118a225edd7

# logical_id helps us to define affinity between node and job
# In order to limit the number of fabric jobs with the same affinity that are running on node,
# use logical_id:5 for example.

#cluster identifier - should contain only letters and numbers
#cluster_id:


## Cluster Configuration

***Heartbit***
## This value will define the delay of fabric heartbeat frequency. Default is 10 sec.
FABRIC_HEARTBEAT_INTERVAL_MS=5000

***KeepAlive***
## Defined the number of the heartbeat that fabric node can miss before it will be considered as not alive.
FABRIC_HEARTBEAT_MISS=12



