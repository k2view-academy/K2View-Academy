# Editing an LU Schema - Defining the Root Table and Instances ID Column

### How do I Define the Root Table and Instances ID Column in an LU Schema?

Each LU Schema must have a Root Table and Instance ID  (Instance PK Column). The instance ID is the unique identifier of each LU instance.
When creating a Logical Unit using the [Auto Discovery Wizard](https://github.com/k2view-academy/K2View-Academy/wiki/Auto-Discovery-Wizard), the Root Table and the [Instance ID](https://github.com/k2view-academy/K2View-Academy/wiki/Fabric-Glossary#instance-id) column are defined according to the selected Root Table and column in the Wizard. 

If you are not using the Auto Discovery Wizard, do the following:

1. Go to the **Project Tree**, select the **Logical Unit** and double click the **Schema** to open the **Logical Unit** screen.
2. Create the **LU table** and add it to the [**LU schema**](https://github.com/k2view-academy/K2View-Academy/wiki/Adding-a-Table-to-the-LU-Schema).
3. Go to the **LU Table**, right click the **table header** and then click **Set as: Root:**

![image](https://k2vacademy.s3.amazonaws.com/Fabric/1_LU_Schema_and_Overview/1.8_Set_Root_Table_and_Instance_ID/LU_Schema_Set_Root_Table.png)

4. Double click the **LU table** to open it.
5. In the **Table Properties**, click the **Instance PK Column** and then select the **table column** defining the **Main Identifier** of the table.

![image](https://k2vacademy.s3.amazonaws.com/Fabric/1_LU_Schema_and_Overview/1.8_Set_Root_Table_and_Instance_ID/LU_Table_Instance_PK_Column.png)

Note that only one column can be defined as **True** in the [**Input Arguments**](https://github.com/k2view-academy/K2View-Academy/wiki/Building-an-LU-Hierarchy---Linking-Table-Populations#how-do-i-link-a-table-population-to-the-lu-schema)  of the Root Table. This field is populated by the **Instance ID**. 

