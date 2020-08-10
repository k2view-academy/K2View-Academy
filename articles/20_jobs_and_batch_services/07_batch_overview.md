...
Migrate operation was redesigned to cover following aspects:
Efficient distribution of work between nodes
Add the ability to dynamically change the configuration of work balance between nodes during execution – JMX reload
Better monitoring on migrate progress on the following levels: Cluster, DC, nodes
Better recovery in case of failure during the process (Nodes are getting down, etc..)
Add the ability to stop and resume the migrate
Better tracking on entity level
When it run?
For how long?
What is the Node id running this entity?
In case of failure – why it failed?
