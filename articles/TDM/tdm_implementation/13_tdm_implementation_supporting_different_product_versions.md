# TDM Implementation - Supporting Different Product Versions

During the project's lifecycle, there will be few versions of the data source structure and relevant software. The TDM may need to support different source and target systems having different versions of the data sources structures. For example, a development environment may have new tables and fields comparing the production environment. These changes require also updates of the TDM implementation.

This article describes the working procedure for the update of the TDM implementation to support changes in the source and target systems.



## TDM Globals of Product Versions

The  [TDM product](/articles/TDM/tdm_gui/05_tdm_gui_product_window.md) represents a system or application installed in a source or target environment. The list of possible product versions must be set on each TDM product. When adding a TDM product to a TDM environment in the TDM GUI, the [product version must be set on the environment](/articles/TDM/tdm_gui/11_environment_products_tab.md#environment-product-window).

The [TDM Execution Process](/articles/TDM/tdm_architecture/03_task_execution_processes.md) sets the following Globals imported from the [TDM Library](04_fabric_tdm_library.md) for each LU. The product versions are based on the product's version on the task's environments: 

- **TDM_SOURCE_PRODUCT_VERSION**, populated by the product's version of the task's source environment. 

- **TDM_TARGET_PRODUCT_VERSION**, populated by the product's version of the task's target environment.

The TDM implementation can get  these Globals values to check the source and target product version of each execution.

## Update LU Schema

 The data source of the LU schema may be updated and requires editing of the LU schema:

### Adding or Removing LU Tables

Adding or removing LU tables to the LU Schema must be implemented manually.

### Adding New Columns to an LU Table 

The new columns can be added manually or  added by clicking [Update Tables from Database](/articles/03_logical_units/18_LU_schema_refresh_LU_options.md) icon in the LU Schema window. 

### Removing columns from an LU table

Removing columns from an LU table must be implemented manually.

### LU Schema - Support Multiple Product Versions

New columns or new tables can be added to a table by advanced product version. 

Examples:

- Adding the PAYMENT_METHOD column to PAYMENT table in the Development environment. This column does not exist in the PAYMENT table of the Production environment.

- Adding a new table, PAYMENT_DETAILS, to the  Development environment. This table does not exist in Production environment.

  #### LU Schema - Adding New LU Table

  - Add [a decision function](/articles/14_sync_LU_instance/05_sync_decision_functions.md) to check the TDM_SOURCE_PRODUCT_VERSION Global. The decision function return **true** in the table exists in the source environment. The source environment version is taken from  **TDM_SOURCE_PRODUCT_VERSION** Global.

  - Example:

    ```java
    String luName = getLuType().luName;
    String tdmSourceProdVersion = "" + ludb().fetch("SET " + luName + ".TDM_SOURCE_PRODUCT_VERSION").firstValue();
    
    Boolean decision = false; 
    if(tdmSourceProdVersion.equals("1.5") || tdmSourceProdVersion.equals("2")
    {
    	decision = true;
    }
    return decision;
    ```

    

  #### LU Schema - Add New Columns to an LU Table 
  - The new columns must be selected by the query only when exist in the source environment.

  - Define multiple populations on the LU table. Each population must run on its source environment. The source environment version is taken from  **TDM_SOURCE_PRODUCT_VERSION** Global.

  - Example:

    - Adding the PAYMENT_METHOD column to PAYMENT table in the Development environment. This table does not exist in Production environment.

    - Creating two table populations:

      1. Production table population, runs when  TDM_SOURCE_PRODUCT_VERSION Global is PROD. This population does not select the PAYMENT_METHOD from the source and leaves the PAYMENT_METHOD empty:

         ![prod population](images/multi_versions_lu_population_1.png)

         Adding to the Production population a new **decision function**: fnDecisionProductionProductVersion:

         ```java
         String luName = getLuType().luName;
         String tdmSourceProdVersion = "" + ludb().fetch("SET " + luName + ".TDM_SOURCE_PRODUCT_VERSION").firstValue();
         
         Boolean decision = false;
         
         if(tdmSourceProdVersion.equals(PRODUCTION_PRODUCT_VERSION))
         {
         	decision = true;
         }
         return decision;
         ```

         

      2. Development  table population, runs when TDM_SOURCE_PRODUCT_VERSION Global is DEV. This population selects the PAYMENT_METHOD from the source and populate the the PAYMENT_METHOD column of the LU table:

         ![dev population](images/multi_versions_lu_population_2.png)

         Adding  to the Development population a new **decision function**: fnDecisionDevProductVersion:

         ```java
         String luName = getLuType().luName;
         
         String tdmSourceProdVersion = "" + ludb().fetch("SET " + luName + ".TDM_SOURCE_PRODUCT_VERSION").firstValue();
         
         Boolean decision = false; 
         if(tdmSourceProdVersion.equals(DEVELOPMENT_PRODUCT_VERSION))
         {
         	decision = true;
         }
         return decision;
         ```

  - Note that it is recommended to create the project's functions in a separate [Logic File](/articles/04_fabric_studio/09_logic_files_and_categories.md) and avoid adding them to the TDM Logic file, since the TDM logic file contains product functions.

## Update Broadway Load Flows

- New table or new table's columns must be loaded to the target environment only if exists in the target environment.

- The target environment version is taken from  **TDM_TARGET_PRODUCT_VERSION** Global.

- Example:

  - Populate the PAYMENT_METHOD column of PAYMENT table only when loading the data to a Development target environment. Set a default value in the PAYMENT_METHOD if the source environment does not have this column:

    ![Broadway example](images/multi_versions_broadway_flow_example.png) 



[![Previous](/articles/images/Previous.png)](12_tdm_error_handling_and_statistics.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](tdm_fabric_implementation_environments_setup.md)