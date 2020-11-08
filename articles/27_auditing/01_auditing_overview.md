# Auditing Overview

Fabric has a robust auditing mechanism, it can log all the activities that run on fabric such as: login, Web Services calls, running fabric commands, etc...

There are two major aspects that can be controlled:

* Auditing filtering strategy - a full flexibility on the type of the activities that will be introduced to the auditing mechanism, such as: reporting only Web-Services calls. While using this flexibility the performance of other activities will not be impacted and you can save a lot for disk space.
* Auditing persistence strategy - defining the channel for reporting the activities logged by the auditing mechanism, such as : Cassandra (default), Kafka, files, etc.

### Auditing reporting structure

When an activity is logged by fabric auditing mechanism is it logged in the following structure:

| Name       | Description                                                  | Example                                                  |
| ---------- | ------------------------------------------------------------ | -------------------------------------------------------- |
| action     | The type  of activity, performed on Fabric.                  | LOGIN, GetCommand, called Web-Service  name, etc.        |
| date       | Activity date                                                | 2020-11-05                                               |
| user       | Fabric user id                                               | admin, etc...                                            |
| written_at | Activity date with timestamp                                 | 2020-11-05 11:49:14.452000+0000                          |
| address    | The IP address of the node where the activity was performed, in case of http/https protocol it appears as a concatenation of IP address:port | 10.21.1.1 or 10.21.1.1:3213                              |
| params     | Activity parameters                                          | In case of GetCommand [DC_NAME=null\|LU_NAME=CRM\|IID=1] |
| protocol   | Contains the protocol use for the activity, valid values, HTTP/1.1, HTTPS/1.3 or DRIVER in case or JDBC driver | DRIVER                                                   |
| query      | The activity details such as CQL query for CQLCommand or DESCRIBE SCHEMA CRM in case of DescribeCommand | SELECT * FROM CRM.SUBSCRIBER                             |
| result     | Number of affected rows                                      | Rows Affected: 3                                         |
| session_id | Session Id                                                   | 73ae6592                                                 |

### Turning Auditing On/Off

Auditing is set by default to OFF.

In order to enable the auditing capability in Fabric, you need to set AUDIT=ON on config.ini file and restart Fabric afterwards.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_filtering_strategy.md) 

