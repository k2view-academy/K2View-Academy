# Table Population Overview

### What is a Table Population in Fabric? 
A **Table Population** is a component that defines and executes the mapping and data transformation rules from a data source, like a DB table or Input file, into a target Logical Unit (LU) Table . The population acts as a map that renders a graphical display of the transformation’s business logic from the source object to the target LU Table. Source data can be mapped directly to LU Table columns and Fabric transformation objects like Translations , Functions  or Globals , and can be added to define the mapping logic into the LU Table. 

Click for more information about Population Transformation Rules. 

Each table can have one or several Table Populations that can be executed simultaneously or according to a predefined execution order .
Each Table Population extracts data from a data source, transforms it when needed and then populates the data into an LU Table.
There are two types of source objects for a Table Population object:
*	DB query , (default) that executes an SQL Select query on a predefined DB interface. 
*	Root function , that can run various SQL Select queries and execute complex logic using Java code, including data manipulations, Fabric APIs, Fabric commands and calculations. All records yielded from the function are inserted into the table. 
Note that tables can also be populated or updated by enrichment function s which, unlike root functions, are executed after all LU Tables are populated.
TABLE POPULATIONS IN AN LU SCHEMA 
An LU Schema structure  displays a hierarchical representation of the data related to the root table. Parent-child links in LU Tables are created via their Table Population objects:
*	Each LU Table  can have one or several Table Population objects. 
*	Each Table Population object, apart from the Table Population of the root LU Table, must be linked to a parent table via its Input columns.
*	Each Table Population object can be linked to a different parent LU Table.
Note that an LU Table can be added to an LU Schema  without a Table Population object. This table is not populated by the sync  of the LU Instance  but can be populated by a separate transaction .

Click for more information about Building an LU Hierarchy and Linking Table Populations.

### Table Population Window
The Table Population window is used to define and display the transformation rules that are applied to data when it is loaded into a Fabric database. 

Click for more information about How to Create a New Population .

The following is an example of the Table Population window. 

