 # **What is a Fabric Batch Process ?** 
Migrate:

BATCH OracleLU.('1','2','3','4') FABRIC_COMMAND="sync_instance OracleLU.?" with ASYNC='true';


Broadway:

BATCH OracleLU fabric_command="broadway OracleLU.SampleFlow SampleIID=?" with async=true;


Cdc republish:

BATCH OracleLU from fabric fabric_command="cdc_republish_instance OracleLU.?" with async=true;
