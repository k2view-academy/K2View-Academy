# FABRIC DEMO PROJECT 

The Fabric Knowledge Base project includes a set of Logical Units, code examples and objects that are used in all Knowledge Base articles and Learning Items. It uses four SQLite databases as the data source. 

Starting from Fabric 6.5.3, the demo project also includes a sample BI implementation. 

Follow the steps below to set up the demo project on your local Fabric.

## Setup Steps

To set up the project and connect the databases, do the following:

1. Open the Fabric Studio and create a new project.

2. Download the [KB_FABRIC_PROJECT.k2export](KB_FABRIC_PROJECT.k2export) file and import it to your project as follows:

    ![image](images/demo_proj_01.PNG)

3. Download the [BILLING_DB.db](Demo_sources/billing_db.db), [CRM_DB.db](Demo_sources/crm_db.db), [COLLECTION_DB.db](Demo_sources/collection_db.db) and [ORDERS_DB.db](Demo_sources/orders_db.db) files locally on your computer. Note the location where you placed these files. 

4. Edit the CRM_DB, BILLING_DB, COLLECTION_DB and ORDERS_DB Interfaces, as follows:
    - Double-click to open the Interfaces from the Project tree.

      ![image](images/demo_proj_02.PNG)


    - Edit the **Database** field, setting the location of the *.db file to the location where you placed these files.

      ![image](images/demo_proj_03.png)

    - Test the connection by clicking on Test Connection String link. 

    - Save the changes and close the DB interface.

    [Click for more information about DB Connection Settings](/articles/05_DB_interfaces/03_DB_interfaces_overview.md).



## BI Configuration and Setup (V6.5.3)

To set up the BI and generate the example reports and dashboards using the demo project, carry out the following steps.

Note that if you are already using the demo project from a previous version, you need to download the project export file again and import it, then also download the DB files and copy them to the predefined location.

1. Install the docker image as explained [here](/articles/98_maintenance_and_operational/BI_Installation/02_BI_Docker_Installation.md).

2. Download the [Demo_BI.zip](Demo_BI.zip) file locally on your computer and extract it. The file contains the following:

   * WebReports.xml
   * reports.json
   * Json folder which contains 46 JSON files

3. Update your docker image with the Demo Project BI example as follows:

   <!--TBD - run the script that will update your docker image with the Demo Project BI example.-->

   a) Copy the files into the docker using the following commands, where **exago** is a docker name. You can do it using the Windows PowerShell or another Command line program.

     ~~~
   > docker cp C:\<path to the file>\WebReports.xml exago:/opt/apps/exago/Config
   > docker cp C:\<path to the file>\Json\ exago:/opt/apps/exago/bin/
   > docker cp C:\<path to the file>\reports.json exago:/opt/apps/exago/bin/
     ~~~

   b) Open the docker CLI and run the following commands to import the Demo BI dashboards and reports definition into your docker image:

     ~~~
   > cd /opt/apps/exago/bin
   > mono ImportExportStorageMgmt.exe -f ./reports.json -I
     14:39:30 INFO  - ExportStorageMgmt -f ./reports.json -I
     14:39:30 INFO  - Starting Import
     14:39:31 INFO  - Completed Loading Content Records: Inserted 46 content records, and updated 0 content records
     14:39:31 INFO  - Completed Loading Access Records: Inserted 46 access records, and updated 0 access records
     14:39:31 INFO  - No Orphan Records Located
     ~~~

   c) Complete the docker image update by changing the owner to WebReports.xml to be apache and restarting the docker:

   ~~~bash
   > chown apache:apache WebReports.xml
   > systemctl restart httpd
   ~~~

4. Open the Demo Project in the Fabric Studio and open the project's config.ini as follows:

   a) Right click on the Project and click **Open Folder**:

     ![img](images/open_folder.PNG)

   b) Go to **FabricHome/config** and open the **config.ini**.

   c) In the project's **config.ini** set the **BI_HOST** parameter of the **[bi]** section to **localhost** and keep the Storage Management DB pointing to the default SQLite DB with empty **TABLE_PREFIX**.

5. Deploy the project including the reference sync. 

6. Click the ![img](images/web_fr.PNG) icon to log in to the **Web Framework > BI** and go to **Admin** tab.

   ![img](images/bi_admin.PNG)

7. Go to **Data > Sources > Fabric-Local**, click the ![img](images/eye.PNG) icon to see the Connection String and update the URLS to be the IP address of your computer. 

   ![img](images/fabric_local.PNG)

   * Click the ![img](images/test_con.PNG) icon to test the connection.
   * If the connection is successful, click **Apply** to save your changes.

## How to Work with BI Demo

1. Switch to the **Designer** tab of the **BI** and open the **Demo Proj** folder. The folder includes a dashboard called **Dashbrd-v0** and a list of reports which comprise this dashboard.

2. Click the ![img](images/play.PNG) icon to generate the dashboard with the data. 

   ![img](images/dashboard.PNG)

3. The generated dashboard is built using a data of a customer_id=118 from the Customer LU of the demo project. Customer ID is set using a session parameter **customer_id**.

4. To see the data of another customer, go to **Admin** tab and open **Data > Parameters > customer_id**, set another value and click **Apply** to save your changes. Then return to the **Designer** tab and run the dashboard.


   [For more information about the Fabric's BI solution click to open the complete BI user guide](/articles/38_bi_integration/README.md).

