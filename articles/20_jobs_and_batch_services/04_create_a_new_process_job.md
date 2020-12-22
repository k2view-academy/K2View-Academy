# How Do I Create a New Process Job?
Process Jobs are batch files or scripts stored in the Fabric server and triggered manually from the Command Line for scheduled execution. 

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

```bash 
startjob process NAME='/home/k2view/echoArg.sh' UID='processJobtest' ARGS='{"0":"ARG 1 value","1":"ARG 2 value"}' EXEC_INTERVAL='00:00:03';
```

   Where:
  - Process, defines the type of Job; in this case a process job.
  - ID, defines the unique name of the processed job - **processJobtest**
  - ARGS, defines a list of expected parameters to be parsed to the script when executed - {"0":"ARG 1 value","1":"ARG 2 value"}
  - INTERVAL, refers to the frequency of the job's occurence; in this particular case every 3 seconds.



[![Previous](/articles/images/Previous.png)](/articles/20_jobs_and_batch_services/03_create_a_new_user_job.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/20_jobs_and_batch_services/05_create_a_new_broadway_job.md)
