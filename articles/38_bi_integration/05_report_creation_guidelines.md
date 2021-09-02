# Report Creation Guidelines

### Overview

**BI Designer** is the Web Framework application powered by [Exago BI](https://support.exagoinc.com/hc/en-us) which enables creating various types of reports and dashboards. This article provides several important guidelines to enable the user to utilize the application effectively.

### Creating Reports Based on Fabric LU Tables

One of the basic report type is a report based on Fabric LU instance data. Follow these steps to create such report:

1. Open **BI Admin** to:

   * Create the Fabric data source and define its objects and joins, as explained step-by-step in the [Metadata Setup](03_Metadata_Setup.md) article. 
   * Create the parameter(s) to be used by the filter, as explained in the [Parameters](04_parameters.md) article.

2. Open **BI Designer** to click the ![image](images/create_icon.PNG) icon to trigger the new report creation and select the report type, for example **Advanced Report**. 

3. Start by selecting the data objects for the report. 

   * The list of data objects presents a list of all existing data objects under all defined data sources. 
   * Once the first data object is selected, most of the objects are disabled except for those that have a join with the selected object.


   * Remember that you must start the selection from the LU Root table and down to the required tables, based on the LU Schema relations. 

4. Once the data objects are selected, click the  ![image](images/filter_icon.PNG) icon to set the filter by IID as follows:

![image](images/filter_definition.PNG)

[Click for more information about the Report Types and the detail documentation.](https://support.exagoinc.com/hc/en-us/articles/215451718-Report-Types)

### Creating Reports Based on Fabric Complex SQL

Creating a report based on a Fabric complex query is similar to creating it based on Fabric LU instance data. The only instance is that instead of selecting the data object, click the ![image](images/add_sql_icon.PNG)icon to write the query using the Custom SQL Object screen:

![image](images/custom_sql_obj.PNG)

Using this screen you can create a custom SQL to retrieve the report data. Remember that this SQL must include filter by LU Instance ID and can include parameters defined in BI Admin using the following syntax:

~~~sql
CUSTOMER.CUSTOMER_ID = @customer_id@
~~~

[Click for more information about Custom SQL Objects.](https://support.exagoinc.com/hc/en-us/articles/215330898-Data-Objects)

