# Logical Unit / Data Product Overview

### What is a Logical Unit?
A Logical Unit (LU or Logical Unit type - LUT), also known as a Data Product, is a blueprint data asset, engineered to deliver a trusted dataset for a specific business purpose. It holds a set of definitions and instructions used for integrating data from source systems, processes the data and stores it. The LU is the prototype from which LU Instances [(LUIs)](/articles/01_fabric_overview/02_fabric_glossary.md#lui) are created. 

An LU is defined and configured in the Fabric Studio as a core element of the [Fabric project](/articles/04_fabric_studio/08_fabric_project_tree.md). These definitions are comprised of 3 main types of objects:

1. [**LU Table**](/articles/06_LU_tables/01_LU_tables_overview.md): The definition of an LU table upon its columns, PKs, indexes and triggers.

2. [**LU Table Population**](/articles/07_table_population/01_table_population_overview.md): 
    * Feed data into LU tables from a variety of data sources and keep it up to date.
    * Ability to manipulate the fed data, that is, performance of enrichment, cleansing, masking, transforming, etc. 
3. [**LU Schema**](/articles/03_logical_units/03_LU_schema_window.md): The relationship between the LU tables (similar to foreign keys). An LU schema has one LU table defined as its Root Table. The Root Table holds the LU’s unique key.



In addition to these main objects, there are some others that are part of logical units, that used to define the logic. They can be found in the Project Tree, under each logical unit:

- Java - [Globals](typora://app/articles/08_globals/01_globals_overview.md) and [Functions](typora://app/articles/07_table_population/08_project_functions.md)
- [Broadway](typora://app/articles/19_Broadway/01_broadway_overview.md)
- Instance Groups
- Resources - files that can be saved as part of a project

<studio>    

- [Translations](typora://app/articles/09_translations/01_translations_overview_and_use_cases.md)
- Parsers
- Jobs
- IIDFinder 

</studio>



**Let’s use an example of a Customer 360 implementation for Company ABC:**

* LU / Data Product: Customer.
* Data sources: CRM, <studio>Ordering, Billing and Collection, </studio><web>Billing and Assets.</web>
* LU tables: The tables that will hold the data you wish to keep about a customer from the 4 data sources.
* LU Table Populations: The set of definitions that will be used for extracting, transforming, cleaning, aggregating, validating (etc.) the data from the 4 data sources into the LU tables.
* LU schema: The definition of the Root Table and the relationship between all LU tables.

<studio>

   

![image](images/1.1_LU_Overview.png)

</studio>

<web>

   

![image](images/web/1_web_lu_overview.PNG)

</web>

### What is a Logical Unit Instance (LUI)?
A Logical Unit Instance is one instance of a Logical Unit Type – it is a single physical database, which holds the data of one single Digital Entity in the LUT structure definition.
Using our above example (Customer 360), assume that Company ABC has 35 million customers:

* LU/LUT = Customer
* LUI = one single customer database

Fabric will hold 35 million instances (LUIs) of the Customer LUT. That is, one physical database for each customer.

### Things to Consider Before Designing an LU 

Every Fabric project starts by defining its LUs. Analyze the business requirements and understand how the consuming application will use the data. Use this information to define the different Digital Entities to implement and build an LU for each Digital Entity.


### General Recommendations for Designing an LU 
Digital Entity data is often split between different data sources. In some cases, it is preferable to create one LU that contains all data sources. In other cases, it is more advantageous to split the LUs and create a separate LU for each data source.

In general, an LU should be based on the smallest number of data sources, as long as it represents a full 360 view of a Digital Entity.

For example, if you have a Digital Entity called Customer, but different Customer Types (e.g. consumer and business) have different data sources, the recommended approach will be to create an LU for each Digital Entity subtype (in our example, different Customer Types).

Below is a **pros and cons** table of each alternative:

<table role="table" width="800">
<tbody>
<tr>
<td width="300">
<p><strong>Item</strong></p>
</td>
<td width="250">
<p><strong>LU per Digital Entity</strong></p>
</td>
<td width="250">
<p><strong>LU per Digital Entity and data source</strong></p>
</td>
<td width="250">
<p><strong>LU per Digital Entity subtype</strong></p>
</td>
</tr>
<tr>
<td width="300">
<p>Ease of writing APIs</p>
</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="10">&nbsp; <img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="10">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
</tr>
<tr>
<td width="300">
<p>Replacing a data source</p>
</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>   
</tr>
<tr>
<td width="300">
<p>Small amount of data in LU</p>
</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>   
</tr>
<tr>
<td width="250">
<p>Maintenance, handling a less complex schema and internal relationships</p>
</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
</tr>
<tr>
<td width="250">
<p>Implementing a real-time action based on an event like a Golden Gate update, when the action depends on multiple data systems</p>
</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
</tr>
<tr>
<td width="250">
<p>Performance of real-time updates</p>
</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
</tr>
<tr>
<td width="250">
<p>Tuning the migration process</p>
</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/X_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
<td align="center" width="60">&nbsp; <img src="/articles/images/V_icon.png" alt="" width="25" height="26"</td>
</tr>
</tbody>
</table>


**Note:**

The file name's ambiguity is not supported within the same Logical Unit. This is not restricted by the Fabric Studio on purpose, allowing the implementor to continue the work and to update the names later. For example, if 2 Java function files with identical names were exported from other projects or libraries, they can be saved in the project in the Fabric Studio. 

However, **at run-time there should be no ambiguity within the LU**, otherwise the server will run the first file that it finds (no commitment to what is considered the first one).





[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_create_a_logical_unit_flow.md) 	
