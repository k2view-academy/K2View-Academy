#   LU Enhancement Flow

 ![](/academy/05_LU_Enhancements/images/fabric_main_flow_05.png)                                                    

 

### Different types of data manipulations

Let's look at the ways you would consider in order to ensure that the LU data you are sourcing from external systems is processed efficiently. 

- What kind of changes would I want to apply to the data?

  - cosmetic change to ensure format compliancy across your flow
  - mathematical formulas to compute new data. 
  - conversion rules 

- How to create new fields or tables by receiving transformed input from multiple fields within your schema and by applying transformation functions above-mentioned

- How do I ensure data is up-to-date by enforcing synchronization schemes for specific LUIs

  

Based on the above, we will apply such transformations on the Digital Entity we defined as **Customer** and its associated Logical Unit: **CustomerLU** that we defined in the Fabric Basic LU section, using the CRM, Billing, Orders and Collection databases. 



We will apply data transformations by using the following available objects :

- Functions
- Translations
- Globals
- Lookup tables

Lets read through the following [article](articles/07_table_population/06_table_population_transformation_rules.md).



Now you are acquainted with the different data transformation means, let's look at Populations diagrams outline to visualize all objects belonging to a particular population map. In the next article you will see an example of how functions, lookup or translation tables are inserted between the external sources table and any of the target LU table, to enable data transformation during it migration from the sources.  

[Populations Diagrams Outline](/articles/07_table_population/12_table_population_diagram_outline.md)



Let's now conclude this part by reading on how we can set different execution orders for the population objects we defined above: [Execution Orders](\articles\07_table_population\13_LU_table_population_execution_order.md) 







[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/01_LU_Enhancement_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/03_LU_Enhancement_Functions_flow.md)

 

 

 

 

 

------

