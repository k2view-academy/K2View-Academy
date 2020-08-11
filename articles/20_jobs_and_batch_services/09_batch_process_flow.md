...
#*Batch flow & statuses*

#*Job handling of batches*
When creating a new batch , a new job is created of TYPE BATCH PROCESS
then -> when the JOB goes to IN_PROCESS the BATCH moves to GENERATE_IID -> IN_PROGRESS
 
 
NEW -> GENERATE_IID_LIST -> IN_PROGRESS -> FAILED 
NEW -> GENERATE_IID_LIST -> IN_PROGRESS -> CANCELLED
NEW -> GENERATE_IID_LIST -> IN_PROGRESS -> DONE

