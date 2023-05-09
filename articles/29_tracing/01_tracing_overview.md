# Tracing Overview

Fabric has a Tracing mechanism for writing internal operations performed by Fabric into trace files.

Tracing can be filtered by:
-  Operations, for example; sqlCommand, fabricCommand or sync.
-  Categories, for example; database, commands or jobs. 
-  Levels, for example; debug, verbose, info, warning or error.

Tracing can be set on both global and session levels. The result of the tracing process is logged into a file saved under the $FABRIC_HOME/trace folder which can be viewed by a web application to enable easier navigation and analysis of the results.

Several live tracing processes can run at the same time.

Common use cases of the Tracing mechanism:

* To understand why the sync of a given instance takes a long time. The user requests a list of all queries running on the source system as a part of the sync process together with details about the duration of each query.
* To know the number of connections opened for a given interface. The user requests a list of the activities that initiated the connections and when they ended.
* To analyze the duration of a Broadway flow execution, checking which Stage, Actor or loop has the longest duration.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_trace_command.md) 

