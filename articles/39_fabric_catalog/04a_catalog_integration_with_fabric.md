<web>

# Catalog Integration with Fabric

### Run Discovery

To initiate the Discovery process:

* Start from defining an interface for your data source in the Fabric Studio. 

* Then, trigger the Discovery job (Crawler) on this interface. To do so, open the [DB Interface explorer](/articles/04_fabric_studio/25_web_data_explorer.md) in the Web Studio, right click on the interface and click **Run Discovery Job** to trigger the job.

* Alternatively, run the following command from the Fabric terminal:

  ~~~bash
  startjob DISCOVERY_CRAWLER name='<interface name>';
  ~~~

Note that when the Crawler job is invoked, it automatically triggers the DATA_DISCOVERY_JOB listener job which is subscribed to the Pubsub topic dedicated to the Discovery process.

### Open In Catalog

To view a data source element in the Catalog application, do the following:

* Open the [DB Interface explorer](/articles/04_fabric_studio/25_web_data_explorer.md) in the Web Studio and select the required element. It can be an interface, schema, table or field.

* Right click the element and click **Open in Catalog**. 

* The Catalog application opens and the selected element is displayed in the Catalog tree.

* Note that the **Open in Catalog** command can be executed on all levels: interface, schema, table or field.

[Click for more information about the Catalog Application.](05_catalog_app.md)

<img src="images/show_catalog_commands.png" style="zoom:75%;" />

Note that **Run Discovery Job** and **Open in Catalog** commands might be hidden in the Web Studio. 

[Click for more information about how to update this setting](11_advanced_settings.md#web-studio).





[![Previous](/articles/images/Previous.png)](04_plugin_framework.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_catalog_app.md) 

</web>
