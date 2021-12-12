### K2View Fabric and CVE-2021-44228 (Log4Shell)

You've probably heard about the log4shell vulnerability in the news or from concerned customers.  Following is our current assessment. 

As you are probably aware a serious vulnerability in the Apache Log4j library has emerged that enables remote code execution by crafting a specialized log message. 
Here is our current analysis of the threat as it applies to the Fabric platform. This is an evolving situation. 
We will communicate updates as new information becomes available.

K2View Fabric - fabric and the iidfinder jvm processes use logback (http://logback.qos.ch/) for their core logging. 
The information from logback is that it does not share the vulnerable code with log4j and is not affected by it.
Starting at version 6.1, fabric includes the log4j-api jar file. This dependency comes from the Elastic Search driver. 
However, the vulnerability in question is in log4j-core. 
log4j-api does not include this vulnerability and at runtime binds with logback as its runtime logging library. 
log4j-api does not contain the JndiLookup.class.
Our assessment is that all fabric versions are not affected by this vulnerability.

Apache Cassandra - Fabric depends on Cassandra as its storage engine. Like Fabric, Cassandra uses logback for its logging framework and is similarly unaffected by this vulnerability.

Confluent Kafka - Kafka is often used in Fabric installations for data integration and fabric inter-node communication. 
The Kafka broker process does depend on log4j.
The versions fabric is bundled with (Kafka Confluent 5.5.1, 5.5.3) use older versions of log4j (1.12.7) that current reports indicate are not affected by this vulnerability. 
The vulnerability is also further mitigated by running Java versions later than 8u191 in a protected network. 
We currently do not see a clear vector of crafting log messages through fabric to affect Kafka log content.
We are waiting for official indication from Confluent for a formal risk assessment and further recommendations regarding Kafka.
