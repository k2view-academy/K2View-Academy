<web>

# Catalog Integration with Fabric

### Run Discovery

To initiate the Discovery process:

* Start by defining an interface for your data source in the Fabric Studio. 

* Next, trigger the Discovery job (Crawler) on this interface. This is done by opening the [DB Interface explorer](/articles/04_fabric_studio/25_web_data_explorer.md) in the Web Studio, right-clicking on the interface and selecting **Run Discovery Job**.

* Alternatively, run the following command from the Fabric terminal:

  ~~~bash
  startjob DISCOVERY_CRAWLER name='<interface name>' uid='<interface name>';
  ~~~

When the Discovery job is invoked, it automatically triggers the DATA_DISCOVERY_JOB listener job. The DATA_DISCOVERY_JOB is subscribed to the Pubsub topic dedicated to the Discovery process. Its purpose is to create a new Catalog version in the *neo4j* GraphDB.

### Open In Catalog

Once the Discovery job execution on a data source is completed, the [DB Interface Explorer](/articles/04_fabric_studio/25_web_data_explorer.md) tab in the Web Studio retrieves the discovery schema from the *neo4j* rather than from the data source.

To view a data source element in the Catalog application, do the following:

* Open the DB Interface explorer tab in the Web Studio and select the required element. It can be either an interface, a schema, a table or a field.

* Right-click the element and click **Open in Catalog**. 

* The Catalog application opens and the selected element is displayed in the Catalog tree.

* Note that the **Open in Catalog** command can be executed on all levels: interface, schema, table or field.

[Click here for more information about the Catalog Application.](05_catalog_app.md)

<img src="images/show_catalog_commands.png" style="zoom:75%;" />

Note that **Run Discovery Job** and **Open in Catalog** commands may be hidden in the Web Studio. 

[Click here for more information about how to update this setting](11_advanced_settings.md#web-studio).





[![Previous](/articles/images/Previous.png)](04_plugin_framework.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_catalog_app.md) 

</web>
