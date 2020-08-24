# How Do I Create a New Process Job?
Process Jobs are batch files or scripts stored in the Fabric server and triggered manually from the Command Line.

## Step 1. 
Create a bash script and save it in /home/k2view/ directory.

For example, save the following code into /home/k2view/echoArg.sh
```bash
#!/bin/bash
echo "Total Arguments : $#"
echo "1st Argument = $1"
echo "2nd Argument = $2"
```

## Step 2. 
Invoke the **startjob** command to trigger the job with the relevant parameters. 

-  Go to the Fabric runtime command line and execute the following command:
```startjob process NAME='/home/k2view/echoArg.sh' UID='processJobtest' ARGS='{"0":"ARG 1 value","1":"ARG 2 value"}' EXEC_INTERVAL='00:00:03';```

   Where:
  - Process, defines the type of Job; in this case a process job.
  - ID, defines the unique name of the processed job.
  - ARGS, defines a list of parameters to be parsed to the script when executed.
  - INTERVAL, refers to the frequency of the job's occurence; in this case every 3 seconds.


## How Do I Create a New Broadway Job?
The Fabric Jobs mechanism also enables running a [Broadway flow](/articles/19_Broadway/01_broadway_overview.md).
Set the **Job** type to **broadway_job** and the name of the flow with a list of its arguments.

Example: 
```startjob broadway_job name='<lu>.<flow>' [args='{"key":"value"}'];```
where args consists of a json-type format string containing the parameters to be parsed to broadway:                                                                              e.g. {"first_param":"first_value","second_param":"second_value"}
``` startjob broadway_job name='Customer.Flow1' args='A=10, B=20';```

## How Do I Create a New CDC Job?
Fabric can execute [CDC](/articles/18_cdc_and_search/02_cdc_messages.md) Jobs (Change Data Capture) to notify external systems about any data changes occuring in Fabric DB. 
Jobs can also execute cross-instance searches using Fabric's Search capability - (Elastic Search command syntax should be used).

Set the **Job** type to **cdc_republish_instance_job**

Example:
``` startjob cdc_republish_instance_job Customer.1000 tables='customer,address'```
