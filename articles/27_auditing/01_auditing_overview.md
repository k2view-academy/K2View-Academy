# Auditing Overview

Fabric has a robust Auditing mechanism that logs activities like logins, Web Service calls and Fabric commands running in Fabric.

Two major functionalities can be controlled:

-  Auditing filtering strategy, offering full flexibility over the type of activities that are introduced to the Auditing mechanism, like reporting only Web Service calls. This flexibility does not impact the performance of other activities and saves a lot for disk space.
-  Auditing persistence strategy, defining the channel for reporting the activities logged by the Auditing mechanism, like Cassandra (default), Kafka, files, etc.

### Auditing Reporting Structure

When an activity is logged by the Fabric Auditing mechanism it is logged in the following structure:

| Name       | Description                                                  | Example                                                  |
| ---------- | ------------------------------------------------------------ | -------------------------------------------------------- |
| action     | Type  of activity performed in Fabric                    | LOGIN, GetCommand, called Web-Service  name, etc.        |
| date       | Activity date                                                | 2020-11-05                                               |
| user       | Fabric User ID                                               | admin, etc...                                            |
| written_at | Activity date and timestamp                                 | 2020-11-05 11:49:14.452000+0000                          |
| address    | IP address of the node where the activity was performed. In http/https protocol this appears as a concatenation of the IP address:port | 10.21.1.1 or 10.21.1.1:3213                              |
| params     | Activity parameters                                           | For a GetCommand [DC_NAME=null\|LU_NAME=CRM\|IID=1] |
| protocol   | Contains the protocol used for the activity, valid values, HTTP/1.1, HTTPS/1.3 or DRIVER or JDBC driver | DRIVER                                                   |
| query      | Activity details like a CQL query for a CQLCommand or DESCRIBE SCHEMA CRM for a DescribeCommand | SELECT * FROM CRM.SUBSCRIBER                             |
| result     | Number of affected rows                                      | Rows Affected: 3                                         |
| session_id | Session ID                                                   | 73ae6592                                                 |

### Turning Auditing On/Off

By default Auditing is set to OFF. To enable Auditing in Fabric, set AUDIT=ON in the config.ini file and then restart Fabric.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_filtering_strategy.md) 

