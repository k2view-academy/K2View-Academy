# Logical Unit (LU) Overview

## What is a Logical Unit (LU) or Logical Unit Type (LUT)?
A Logical Unit (LU or LUT) is a blueprint holding a set of definitions / instructions used to create and maintain the data of a [**Digital Entity**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#digital-entity)  (business entity). It is the prototype from which LU Instances [**(LUI)**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/01_fabric_overview/02_fabric_glossary.md#lui)  are created. 
An LU is defined and configured in Fabric Studio . These definitions are comprised of three main objects:
1. [**LU Table**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/06_LU_tables/01_LU_tables_overview.md) : The definition of tables that hold the data of a Digital Entity including its columns, PKs, indexes and triggers.

2. [**LU Table Population (ETL)**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/07_table_population/01_table_population_overview.md): 
    * Getting data into the LU tables from a variety of data sources and keeping it up to date.
    * Different types of data manipulation like enrichment, cleansing, masking, transforming, etc. 
3. [**LU Schema**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/03_LU_schema_window.md): The relationship between the LU Tables (similar to foreign keys). An LU Schema  has one LU Table defined as its Root Table that holds the Digital Entity’s unique key.

**Let’s use an example of a Customer 360 implementation for Company ABC:**
* **Digital Entity: Customer**.
* Data Sources (applications): CRM, Ordering, Billing and Collection.
* LU Tables: The tables that will hold the data you want to keep about a customer from the four data sources.
* LU Table Populations: The set of definitions to extract, transform, cleans, aggregate, validate   etc. data from the four data sources into the LU Tables.
* LU Schema: The definition of the Root Table and the relationship between all LU Tables.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/images/1.1_LU_Overview.png)

## What is a Logical Unit Instance (LUI)?
A Logical Unit Instance is one instance of a Logical Unit Type – it is a single physical database  holding the data of one single Digital Entity in the LUT structure definition.
Using our example from above (Customer 360):
* Assume that Company ABC has 35 million customers:
   * LU/LUT = Customer.
   * LUI = one single customer database.
Fabric will hold 35 million instances (LUI) of the Customer LUT. That is, one physical database for each customer.

## Things to Consider Before Designing an LU 
Every Fabric project starts by defining its LUs. Analyze the business requirements and understand how the consuming application will use the data. Use this information to define the different Digital Entities to implement and build an LU for each Digital Entity.


### General Recommendations for Designing an LU 
Digital Entity data is often split between different data sources. In some cases, it is preferable to create one LU that contains all data sources. In other cases, it is more advantageous to split the LUs and create a separate LU for each data sources.

Below is a table of **pros and cons** of each alternative:

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
</tr>
<tr>
<td width="300">
<p>Ease of writing APIs</p>
</td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/V_icon.png" alt="" </td>
<td align="center" width="10">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/X_icon.png" alt="" </td>
</tr>
<tr>
<td width="300">
<p>Replacing a data source</p>
</td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/X_icon.png" alt="" </td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/V_icon.png" alt="" </td>
</tr>
<tr>
<td width="300">
<p>Small amount of data in LU</p>
</td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/X_icon.png" alt="" </td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/V_icon.png" alt="" </td>
</tr>
<tr>
<td width="250">
<p>Maintenance, handling a less complex schema and internal relationships</p>
</td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/X_icon.png" alt="" </td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/V_icon.png" alt="" </td>
</tr>
<tr>
<td width="250">
<p>Implementing a real-time action based on an event like a Golden Gate update, when the action depends on multiple data systems</p>
</td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/V_icon.png" alt="" </td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/X_icon.png" alt="" </td>
</tr>
<tr>
<td width="250">
<p>Performance of real-time updates</p>
</td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/X_icon.png" alt="" </td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/V_icon.png" alt="" </td>
</tr>
<tr>
<td width="250">
<p>Tuning the migration process</p>
</td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/X_icon.png" alt="" </td>
<td align="center" width="60">&nbsp; <img src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/V_icon.png" alt="" </td>
</tr>
</tbody>
</table>


[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/03_logical_units/02_create_a_logical_unit_flow.md)
