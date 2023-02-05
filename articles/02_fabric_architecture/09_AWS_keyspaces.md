# AWS Keyspaces - Limitations and Troubleshooting 

[AWS Keyspaces](https://docs.aws.amazon.com/keyspaces/latest/devguide/what-is-keyspaces.html) is one of the supported database types to be used as Fabric Operational DB. 

Below are various AWS Keyspaces limitations, that can lead to re-implementation in a  Fabric project, along with some suitable troubleshooting tips:

* The ```select count(*)``` and ```truncate table``` statements are not supported. The implementation that currently uses them (when working with Cassandra) would have to be modified.

* When using the AWS Keyspaces, the user management is handled by AWS. Thus, Fabric's capability to create and drop users (as it exists in Cassandra) is not supported. These activities would have to be done via the *adminInitialCredentials* file (which creates an admin user for the initial access only). Subsequently, the user management will be handled via the authentication server.

* TTL (time-to-live) mechanism is not automatically supported in AWS keyspaces. Thus, following the table creation, it should be altered in order to activate the TTL support. 

* To work with AWS Keyspaces as a Fabric interface, you should define it using the Cassandra interface type and set the connection settings to AWS server. Note that ```QUORUM``` is not supported and as a result, when defining an interface, QUORUM should be replaced with ```LOCAL_QUORUM``` via the server connection string.

* When working with Cassandra, the upper limit on a field size is 2GB, while in AWS Keyspaces it is 1 MB. Hence, when using the AWS Keyspaces, you would need to set the INSTANCE_CHUNK_SIZE in the config.ini to 990000 (or less).

  ~~~
  INSTANCE_CHUNK_SIZE=990000
  ~~~

* When a new table is created in AWS Keyspaces, it is created, by default, with an on-demand capacity mode. This means pay-per-request pricing for read and write requests, allowing you to pay only for what you've used. If, however, you would like to specify the numbers of reads and writes per second that are required for your application, you can do so by setting the AWS_TABLE_PROVISIONED parameter in the [default_session] section of the config.ini. This parameter can include a semicolon separated list of initial read and write provision values per table, using the following format:

  ~~~
  AWS_TABLE_PROVISIONED = keyspace1.table1=40,35,onDemand;keyspace2.table2=10,12
  ~~~

  * The first number here defines read capacity units (RCUs) and the second number defines write capacity units (WCUs).
  * Setting the read and write units per table means that the table's throughput capacity mode is updated in AWS to *PROVISIONED*. If you wish to keep the table on the *PAY_PER_REQUEST* throughout capacity mode, add onDemand after the RCU and WCU definition, as described above.
  * [Click here for more information about read and write capacity mode in AWS Keyspaces.](https://docs.aws.amazon.com/keyspaces/latest/devguide/ReadWriteCapacityMode.html)





[![Previous](/articles/images/Previous.png)](08_kafka_basic_commands.md)
