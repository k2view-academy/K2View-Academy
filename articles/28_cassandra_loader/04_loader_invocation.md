# Loader Invocation and JMX

### Loader JDBC Connection

The JDBC connection of the loader is defined using a **DbCassandraLoader** interface type and its only property is the loader's section name. It represents a section in *config.ini*. When the provided section name doesn't exist in the configuration, a new section needs to be added to config.ini with the relevant configuration parameters.

The section name property of the **DbCassandraLoad** interface can be overridden per each [environment](/articles/25_environments/01_environments_overview.md).

### Loader Invocation Code Example

The loader is invoked from the user code in the Fabric's projects implementation via the **DbCassandraLoad** interface type.

Below is an example of the loader invocation from the Web Service. Note that the loader is invoked via the **loaderDefaultInterface** interface defined as **DbCassandraLoad** interface type.

~~~java
Db ci = db("loaderDefaultInterface");

for (int i = 0; i < 10; i++){
    ci.execute("INSERT INTO k2view_LoaderLu.tbl(PATIENT_ID,test) VALUES ('2','McLoaderPopulation"+i+"');");    
}
ci.commit();
~~~

### JMX Metrics

When running the Cassandra loader, you can monitor it via K2View Web Admin Statistics.

![image](images/28_loader_stat.png)



[![Previous](/articles/images/Previous.png)](03_loader_configuration.md)
