<studio>

# Auto Discovery Wizard

## Auto Discovery Wizard Overview 
The Fabric Studio Auto Discovery Wizard enables you to automatically generate or edit an LU schema based on predefined database constraints like a Primary Key or a Foreign Key. 

The Auto Discovery Wizard can run in two modes:
* Fast, the Auto Discovery Wizard generates a new Logical Unit schema based on Foreign Keys only.
* Medium, the Auto Discovery Wizard generates a new Logical Unit schema based on Primary Keys, Foreign Keys and Virtual Primary Keys. This mode allows you to be more involved in certain aspects of the process. 

## How Do I Use the Auto Discovery Wizard?

### STEP 1: Define the DB Interface
The first screen displays a list of all [DB interfaces](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) defined in a Fabric project (go to Shared Objects -> Interfaces). Select the DB interface for the Auto Discovery process.

![image](/articles/03_logical_units/images/03_06_wizard_01.png)

1. Click the specific **DB interface** upon which you want to base the structure of the LU. The right side of the window will fill up with various parameters, but what we need now is its *Connection String*, located under *Connection Details*.  
2. Validate the connection string by clicking **Test Connection String**. The response will appear in the field at the bottom of the window. 
3. If the connection string has been successfully validated, click **Next**.
4. If the connection string has not been validated, then you must first get a valid route for the connection string before continuing. 

### STEP 2: Define the Root Table and Field
In this step you will select a DB Table and column as the Root Table and [Instance ID](/articles/03_logical_units/08_define_root_table_and_instance_ID_LU_schema.md)  of the Logical Unit.

![image](/articles/03_logical_units/images/03_06_wizard_02.png)


1. Go to the **Tables** list and click the **Root Table** and then click the **Instance ID.**
1. Optional: In the **File Name Prefix** field, enter a **string prefix** for the **LU table names**. By default, the LU table name receives the name of the original source DB table.
1. Optional: Uncheck **Add schema name to the generated files**. When checked, the **Schema Name** is concatenated to the **LU Table Name**.  
1. Optional: Check **Table population based Broadway flow**. When checked, all the tables populations are created based on Broadway flows.
1. Click **Next**.

### STEP 3: Define the Auto Discovery Mode and Settings
This screen allows you to select the Auto Discovery mode (auto discovery type) and add additional settings to it.

![image](/articles/03_logical_units/images/03_06_wizard_03.png)

## Auto Discovery Mode (Type)   
Auto Discovery supports the following modes:
* **Fast mode** - the default mode. Follow the connections of the physical foreign keys as defined in the selected database to identify the relationships between the database tables. 
* **Medium mode** – allows you to be more involved in certain aspects of the process and checks the primary key fields in the tables to search for a link between the tables. See the list of parameters for Medium Mode in the table below. 


### Auto Discovery Settings

<table>
<tbody>
<tr>
<td width="160">
<p><b>Tables Regular Expression Checkbox<b></p>
</td>
<td colspan="2" width="800">
<p>Optional. To limit the number of tables included in the Auto Discovery Wizard, define a subset of the source DB tables to be connected.</p>
<p>A subset is defined using a regular expression that matches the names of the tables to be included. By default, it is populated by the schema name of the selected DB.</p>
<p>Example 1: if the schema name is CRM_DB, then this field is populated by default by ^CRM_DB\.</p>
<p>Example 2: if the regular expression ^CUSTOMER is defined, the Auto Discovery Wizard searches for relationships only within tables whose name begins with CUSTOMER.</p>
</td>
</tr>
<tr>
<td width="160">
<p><b>Enable Backwards Discovery Checkbox<b></p>
<p>&nbsp;</p>
</td>
<td colspan="2" width="444">
<p>By default, this checkbox is unchecked. When checked, tables identified by Auto Discovery as the parent tables of the selected Root Table are added to the LU schema as child tables and are not filtered out of the schema.</p>
<p>See the example in Step 4 below.</p>
</td>
</tr>
<tr>
<td rowspan="4" width="160">
<p><b>Medium Mode Parameters<b></p>
</td>
<td colspan="2" width="444">
<p>These optional settings are only displayed when Medium Mode is checked in the left pane.</p>
</td>
</tr>
<tr>
<td width="123">
<p>Column Aliases Collections</p>
</td>
<td width="321">
<p>Defines the relationship between fields within different tables. &nbsp;You can drag columns from the tables of the selected DB or edit this field manually.</p>
<p>For example:&nbsp; to populate the alias using CUSTOMER_ID, CUST_ID, CUST_NO, Auto Discovery links between these fields even though they have different names.</p>
</td>
</tr>
<tr>
<td width="123">
<p>Virtual Primary Keys</p>
</td>
<td width="321">
<p>Fields that are not defined as the&nbsp; primary key that are recognized and treated as a primary key by the Auto Discovery Wizard, in addition to the original Primary Key.</p>
</td>
</tr>
<tr>
<td width="123">
<p>Primary Keys that are Skipped</p>
</td>
<td width="321">
<p>Primary keys that need to be ignored or skipped by the Auto Discovery Wizard.</p>
</td>
</tr>
</tbody>
</table>


### STEP 4: Check the ERD Diagram
Display the suggested **ERD diagram** and the selected **Root Table** (pink) and related tables. 

Connections are colored:
* Foreign key connection = blue.
* Primary key connection = green.
* Virtual key connection = olive.

If the selected Root Table is not defined as the root of the identified ERD, consider changing the selected Root Table or check the Enable Backwards Discovery checkbox. If not, the created LU includes only the selected Root Table and its subordinate table. 

**For Example** 
![image](/articles/03_logical_units/images/03_06_wizard_04.png)

The selected Root Table is CRM_DB.ACTIVITY. However, the Auto Discovery Wizard identifies the CRM_DB.CUSTOMER table as the Root Table of the CRM_DB.  


When saving the CRM_DB.ACTIVITY table as a Root Table:
1. **If Enable Backwards Discovery** is unchecked (default), then Auto Discovery creates the following LU and filters out the remaining tables of the ERD diagram.

![image](/articles/03_logical_units/images/03_06_wizard_05.png)

2. When **Enable Backwards Discovery** is checked, Auto Discovery creates the following LU and links the CUSTOMER table and its subordinate table to the ACTIVITY table.

![image](/articles/03_logical_units/images/03_06_wizard_06.png)

### Create the Logical Unit Diagram 
Display the suggested logical unit diagram and click **Finish** to create the LU. 

![image](/articles/03_logical_units/images/03_06_wizard_07.png)

Optional: you can check tables out of the LU schema to create them as LU tables and add them manually to your LU if needed. 



[![Previous](/articles/images/Previous.png)](/articles/03_logical_units/05_create_a_new_LU_object.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/03_logical_units/07_build__or_update_an_LU_schema.md) 

</studio>
