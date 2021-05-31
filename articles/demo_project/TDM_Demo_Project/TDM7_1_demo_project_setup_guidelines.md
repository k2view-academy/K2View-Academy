# TDM Demo Project - Setup and Implementation Guidelines

### Databases Setup

The TDM demo project is aligned with TDM V7.1 and is based on PostgreSQL databases:

<table>
<tr>
<td><strong>Database</strong></td>
<td><strong>Source DBs (populated)</strong></td>
<td><strong>Target DBs (empty)</strong></td>
</tr>
<tr>
<td>CRM</td>
<td>CRM_DB</td>
<td>TAR_CRM_DB</td>
</tr>
<tr>
<td>Billing</td>
<td>BILLING_DB</td>
<td>TAR_BILLING_DB</td>
</tr>
<tr>
<td>Ordering</td>
<td>ORDERING_DB</td>
<td>TAR_ORDERING</td>
</tr>
<tr>
<td>Collection</td>
<td>COLLECTION_DB</td>
<td>TAR_COLLECTION</td>
</tr>
</table>

The target DBs are empty and the source DBs are populated with customers.

#### Creating the Demo DBs in the Local PostgreSQL DB

1. Download the SQL script and the DB backup files from the **Demo DBs** directory.

2. Create the databases in PostgreSQL: 

   - Go to the script's directory and open a **powershell** window. 
   - Then run the following command to the the script and create the DB users and schemas on the PostgreSQL DB:

     & "[PostgreSQL DB exe file full path]" -Upostgres --host [PG ip address] --port [DB port] -f "create_resources.sql"

     **Example:** 

   ```
   & "C:\Program Files\PostgreSQL\9.6\bin\psql.exe" -Upostgres --host localhost --port 5432 -f "create_resources.sql"
   ```
   
3. Open **pgAdmin 4** and start restoring the databases from backup files.

### Fabric Implementation

1. Open the Fabric Studio and create a new project.
2. Download the TDM Demo project export file (format **.k2export**) and import it to your project. All of the characteristics of the TDM Demo project will appear. 
3. The interfaces and/or environments of the TDM Demo project might need some modification, edit them if needed (you might be working locally or remotely, for example). 
4. Open the DB_CASSANDRA interface, and check if the **k2masking** keyspace exits. If it does not exist, create it using the **masking-create-cache-table.flow** from the library of Broadway examples. After creating the **k2masking** keyspace, run the **update_tdm_sequence_mapping.sql** script on Cassandra DB to recreate **TDM_SEQ_MAPPING** table with the correct structure.
5. If you use a Fabric docker and a local PG DBs, populate the **Server** setting by **host.docker.internal**.
6. Deploy the LUs, Web-Services, and the Environments to Fabric. Note that you must deploy the environments *before* running Data Viewer, since the main Target LU table sets the target ("TAR") environment to be the active environment.

###  TDM GUI Definitions

1. Create a Customer Business Entity that includes an LU with the following structure:

 ![Customer BE](images/Customer_demo_BE.png)

2. Create the following products:  

   - CRM product. Attach the Customer LU to this product.
   - BILLING product. Attach the Billing LU to this product.  Add two product versions to the BILLING product: PROD and DEV.
   - ORDERS product. Attach the Orders LU to this product. 

3. Create the following environments:

- SRC:

     - Set  **Environment Type** setting to **Source**.
     - Set  **Fabric Environment Name** setting to **SRC**.
     - Add the products to this environment.
     - Set the version of the BILLING product to PROD.
   
- TAR:
  
     - Set  **Environment Type** setting to **Both** to enable running Data Flux tasks on this environment.
     - Set  **Fabric Environment Name** setting to **TAR**.
     - Add the products to this environment.
     - Set the version of the BILLING product to DEV.


​     

See instructions on how to work with the TDM GUI in the following [link](/articles/TDM/tdm_gui/README.md).



Now you are ready to create and execute TDM tasks based on the TDM Demo implementation.
