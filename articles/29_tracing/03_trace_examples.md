# Trace Examples

### Trace Sync from Source

Trace a database category to analyze the following:

-  Select statements run on source interfaces.
-  Timing as a result of a sync command. 

The trace can be run on a global or session level.

![images](images/trace_example1.png)

A trace file named test1.fabrictrace has been created under $FABRIC_HOME/traces.

File content 

![images](images/trace_example1_view.png)

### Trace Web Service

Trace overall timing of a Web Service and drilldown into the sync process, check how long it takes to sync each table and population.

![images](images/trace_example2.png)

The getCustomerInfo Web Service was called after activating the Trace mechanism.

A trace file has been created under $FABRIC_HOME/traces called test2.fabrictrace.

File Content 

![images](images/trace_example2_view.png)

[![Previous](/articles/images/Previous.png)](02_trace_command.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_trace_custom.md) 
 






