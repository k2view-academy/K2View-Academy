# AWS Keyspaces - Limitations and Troubleshooting 

AWS Keyspaces is one of the supported database types to be used as Fabric Operational DB. 

Below is the list of limitations of AWS Keyspaces, which can cause re-implementation in a  Fabric project, and some troubleshooting tips:

* The ```select count(*)``` and ```truncate table``` statements are not supported. The implementation that currently uses them (when working with Cassandra) would have to be modified.
* When using the AWS Keyspaces, the user management is handled by AWS. Thus, Fabric's capability to create and drop users (as it exists in Cassandra) is not supported. These activities would have to be done via the *adminInitialCredentials* file (which creates an admin user for the first time only). Then all the user management will be handled via the authentication server.
* TTL (time-to-live) mechanism is not automatically supported in AWS keyspaces. Thus, following the table creation, it should be altered in order to activate the TTL support. 
* In order to work with AWS Keyspaces as a Fabric interface, define an interface type Cassandra and set the connection settings to the AWS server. Note that ```QUORUM``` is not supported. Thus, when defining an interface, QUORUM should be replaced with ```LOCAL_QUORUM``` via the server connection string.
* When working with Cassandra, the upper limit on a field size is 2GB while in AWS Keyspaces it is 1 MB. Hence, when using the AWS Keyspaces, you would need to set the INSTANCE_CHUNK_SIZE in the config.ini to 990000 (or less).

[Click here for more information about working with AWS Keyspaces.](https://docs.aws.amazon.com/keyspaces/latest/APIReference/Welcome.html)



[![Previous](/articles/images/Previous.png)](08_kafka_basic_commands.md)
