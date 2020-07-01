
![](/academy/Training_Level_1/05_LU_Enhancements/images/EnhancementPopMapState.PNG)                                                    

#   LU Populations Flow

 

### Different types of data manipulations

Let's look at what needs to be considered to ensure that the LU data you are sourcing from external systems is processed efficiently: 

- What changes would I want to apply to the data?

  - Cosmetic change to ensure format compliancy across the flow.
  - Mathematical formulas to compute new data. 
  - Conversion rules. 

- How to create new fields or tables by receiving transformed input from multiple fields within the schema and by applying transformation functions.

- How I ensure data is up-to-date by enforcing synchronization of specific LUIs.

  

Based on the above, the transformations will be defined on the Digital Entity you defined as **Customer** and its associated **CustomerLU** that you defined in the Fabric Basic LU section, using the CRM, Billing, Orders and Collection databases. 



You will apply data transformations using the following available objects:

- Functions
- Translations
- Globals
- Lookup tables

To learn more, read the following [article](/articles/07_table_population/06_table_population_transformation_rules.md).



Now you are acquainted with the different data transformation means, let's look at the Populations diagrams's outline in the [Populations Diagrams Outline](/articles/07_table_population/12_table_population_diagram_outline.md) article. You will see an example of how functions, lookup or translation tables are inserted between the external sources table and the target LU table to enable data transformation during its migration from its sources.  

To conclude this section, read about [Execution Orders](/articles/07_table_population/13_LU_table_population_execution_order.md) to understand how to set different execution orders for the population objects you defined above. 







[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/01_LU_Enhancement_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/03_LU_Enhancements_Functions_flow.md)

 

 

 

 

 

------

