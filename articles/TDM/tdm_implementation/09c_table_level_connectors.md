# Supporting Table-Level Tasks Using Connectors

## Update TableLevelDefinitions MTable 

The installment of a K2exchange connector adds a dedicated TableLevelDefinitions file for the connector. 

**Example — TableLevelDefinitions___mongodb**:

![mongo example](C:\Users\TaliEinhorn\OneDrive - K2View\Documents\K2View-Academy\articles\TDM\tdm_implementation\images\mongo_tableleveldefinitions.png) 



Note that you must set the task's retention period to *Do not retain*. This ensures that the tables are loaded directly to the target environment, without being saved to Fabric, when the data source is based on a connector. 