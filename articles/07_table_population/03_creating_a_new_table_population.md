# Creating a New Table Population

A **Table Population** can be created by either:
*	Dragging the table from the LU Schema  DB Objects tab into the LU Schema working area to create the LU Table including its Table Population object.
*	Creating an LU Table  using the New Table from SQL Based DB Query or New Table from SQL Based Root Function options  in the LU Schema window to create the LU Table including its Table Population object.
*	Using the Auto Discovery Wizard to  automatically create the LU Table and a DB Query population for each LU Table.
*	Manually, by right clicking the table under the Project Tree and then clicking New Table Population. 

LU Tables can be populated by either a DB Query or a Root Function source object. To decide which source object to use, refer to Population Comparison Analysis . 
*	A source object can return one or many records whereby each Output record is mapped and populated into a target LU Table. 
*	Note that a Table Population can extract data from other LU Tables  in the same Logical Unit. It is recommended to always check the execution order  of a source table’s population objects to verify that the source LU Tables are populated before the target LU Table. For example, the SUBSCRIBER LU Table must be populated before the OFFER LU Table to enable populating the OFFER LU Table based on data from the SUBSCRIBER LU Table.
*	When an LU Table has two populations, a second Table Population can extract data from the same LU Table. For example, the ADDRESS LU Table has two Table Populations:
*	Population 1 extracts data from the CRM DB.
*	Population 2 extracts data from the ADDRESS LU Table that has been inserted by Population 1.

Click for more information about Creating Table Population Objects .

### How Do I Create a New Table Population from a DB Query? 
1.	Go to Project Tree > Logical Units and then click the <LU Name>.
2.	Click Tables, right click <Table Name> and then select New Table Population to open the working area for creating the mapping.
3.	Click the Objects tab in the right panel of the Table Population working area.
4.	Click Databases to display the Databases List holding a list of interfaces defined in the project. 
5.	Do either: 
    a.	Double click the Interface Name and drag a table into the working area.
    b.	Use the Newest LUDB File or Newest Reference File options to populate the data from the table of the current LU. Double click the <LU Name> and drag the LU Table into the working area. These options will be available after running the Data Viewer for the LU or for the Reference to create the LUDB file  or Reference file .
6.	Click Create as DB Query to create the DB Query source object in the working area. 
7.	Optional: use the Edit Query option to edit the input query via the Query Builder , for example, to choose specific columns or conditions in SQL statements. Do either:
    a.	Click the Source Object, then go to the Properties tab > Edit Query in the right panel of the working area.
    b.	Double click the Query Object.
8.	To connect the Source Object to the target LU Table, right click the DB Query object and either:
    a.	Click Auto Connect to Table.
    b.	Connect the fields manually.

### How Do I Create a New Table Population from a Root Function? 
1.	Go to Project Tree > Logical Units and then click the <LU Name>.
2.	Click Tables, right click <Table Name> and then click New Table Population to open the working area for creating the mapping.
3.	Click the Objects tab in the right panel of the Table Population working area.
4.	Click Root Functions  and do either:
    a.	Select an existing Root Function and drag it into the working area. 
    b.	Click Create New Root Function.
5.	Right click the Source Object and do either:
    a.	Check Auto Connect to Table.
    b.	 Connect the fields manually.
