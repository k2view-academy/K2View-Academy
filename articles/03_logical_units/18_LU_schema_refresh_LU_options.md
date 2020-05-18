# LU Schema - Refresh LU Options

The **Logical Unit toolbar** has two Refresh icons:

![image](https://k2vacademy.s3.eu-west-2.amazonaws.com/Fabric/1_LU_Schema_and_Overview/1.18_lu_schema_refresh_options/1.18_LU_Schema_Refresh_Icons.png)

* Refresh Items (blue), that refreshes project objects into the LU Schema. 
* Update Tables from Database (green), that rebuilds LU Tables based on the source DB and is used to implement source DB updates into an LU Schema. When needed, Update Tables from Database adds new columns to the LU Table. 

Note that Update Tables from Database does not delete or add tables to the LU Schema and does not delete columns from LU Tables. These updates must be implemented  manually. 
