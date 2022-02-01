<studio>

# Creating a New Translation in Fabric



A Translation is a Fabric Studio object that transforms data from one set of valid values to another in order to enable the execution of various transformation rules. Translation objects can be used as decision tables in Fabric and can be defined either on a [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md) level or on a [Logical Unit](/articles/03_logical_units/01_LU_overview.md) level. 
* Translations defined on a Shared Objects level can be used in all objects included in a project. 
* When a Translation is used for a [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) it must be defined on a Shared Objects level.

## How Can I Create a New Translation in Fabric?
1.	Go to **Project Tree** > **Logical Units** > **[LU Name]**, right click **Translations** > **New Translation** to display the [Translation Schema tab](/articles/09_translations/01_translations_overview_and_use_cases.md#translation-schema).
  
2.	Define the **Translation Schema**: 
       * Complete the **Name**, **Direction** (Input / Output), **type**, etc. of each field.
       * Select what happens if a Translation match is not found. By default, the action is **Use Default**.

3.	Go to the **Translation Data** tab.

4.	Optional: If the population data exists in a file or is retrieved from the DB, follow the instructions in How can I Import Translation Data.

5.	To populate the data manually: 
       * Enter the **Input value** combinations to generate the **Primary Key**. A Primary Key defines data transformation rules and is a combination of all Input fields. Therefore, each combination must be unique. 
       * Enter the **Output value** combinations of each data Translation rule. The Output value does not need to be unique and can be repeated. 
       * Optional: if a **Field Type = SQL**, validate the **query** using the [**Query Builder**](/articles/11_query_builder/01_query_builder_overview.md). To do so, click the **SQL** icon in the corner of the field and then click **Execute Query** in the **Query Builder** screen.
       
6.	Click **Save** to display the **New Item** dialog box.  

7.	Complete the **Name** field and then click **OK**. Note the following:
       * A name must not include special characters like # or $.
       * The recommended naming convention to use is the **trn** prefix. For example, **trnMarket**.

## Examples

### Example of a Simple Translation Table

The following Translation includes a list of Input and Output combinations where Input has unique values and Output values are repeated.  For example, the Billing and Collection values of a LEGACY_ORD_TYPE Input field return the same Output value of BLG.

![image](images/09_02_01%20Simple%20Translation%20table.png)

### Example of a Translation where Data Type = SQL

In the following Translation the **Field Type = SQL** whereby the query must be validated via the [Query Builder](/articles/11_query_builder/01_query_builder_overview.md).

![image](/articles/09_translations/images/09_02_02%20Data%20Type%20%3D%20SQL.png)

To validate the query, click the **SQL** icon in the corner of the field and then click **Execute Query** in the **Query Builder** screen.

[![Previous](/articles/images/Previous.png)](/articles/09_translations/01_translations_overview_and_use_cases.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/09_translations/03_data_population_in_a_translation.md)

</studio>
