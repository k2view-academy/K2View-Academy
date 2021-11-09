# BI Admin Configuration

The **Admin** module of the BI application enables you to configure the BI application including the security, database, UI and other various settings. **WebReports.XML** is the main BI configuration file which stores these settings. Any change applied on the **Admin** module is reflected in the WebReports.XML file.

Note that WebReports.XML does not keep the default flag values. That is, if the default value of a flag is True, the flag only appears in WebReports.XML when its value is changed to False. 

During the installation of the BI application, the pre-configured WebReports.XML is copied to your server and it serves the BI solution. 

This article describes the configurations which exist in WebReports.XML and are required for the BI solution as well as other important settings.

### Allow Direct Access

The BI application can either be invoked directly via a URL or by the REST call from the hosting application. 

As part of the BI solution, the BI application should be invoked by the REST call only in order to secure the access to the business intelligence data. Thus the following flag must be set to **False** in the **Admin** module by selecting **General** > **Main Settings**:

![image](images/99_allow_dir_access.PNG)

This is how this flag is defined in WebReports.XML:

~~~xml
<allowhomedirect>False</allowhomedirect>
~~~

In demo or development environments, you can change this setting to **True** if it is needed to temporarily access the BI application directly via the URL. Note that this is not acceptable for production environments. 

### REST Key

The REST Key is used to authenticate REST requests from the Fabric server to BI. The REST Key is defined in the **Admin** module and it is set to the default value during the BI installation.

To update the REST Key do the following:

1. Open the **Admin** module (click the Admin tab on the upper left of the user interface), then select  **General** > **Other Settings** and either populate the REST Key manually or click **Generate Key**:

   ![key](images/bi_rest_key.PNG)

2. Click **Apply** to save the updated REST Key.

3. Copy the value of the REST Key to the **BI_REST_KEY** parameter of **config.ini** on your Fabric server.

### Allow New Root Directories

By default, the **Designer** module allows you to create several root folders using the **Add Folder** link:

![image](images/99_add_folder.PNG)

In order to enforce the access rules on the project level, this ability is disabled by setting the following flag to False in the **Admin** module by selecting **General** > **Feature/UI Settings**:

![image](images/99_allow_new_root.PNG)

This is how this flag is defined in WebReports.XML:

~~~xml
<allowreporttreerootaccess>False</allowreporttreerootaccess>
~~~

Upon the project first deployment, the <project name> folder is created automatically as described [here](01_Installation.md#Project-Initialization-in-BI). The user can still create child folders under this folder, if he has either **Unrestricted** or **CreateContent** access levels.

### BI User Roles 

BI user roles are created to specify how a user or group of users interfaces with BI. Roles can restrict access to folders or data objects.

The main reason for using roles is the ability to restrict or allow the creation of reports. Two roles are pre-configured in the **Admin** module and must be present in WebReports.XML:

* **create_report** role, which allows the creation of several kinds of reports in the **Designer** module.
* **default** role, which does not allow creation of reports.

The Fabric user roles with that have the **BI_ADMIN** permission level are automatically assigned the **create_report** role, thus these users are allowed to create reports.

### Storage Management Initialization

When a PostgreSQL DB is installed, a Storage Management schema must be initialized. It includes creation of specific metadata and data. 

As part of the BI solution, Storage Management initialization is performed automatically upon the deployment of the project. However when working on the BI application directly, the following steps must be performed:

1. Open the **Admin** module, then select **Storage Management** and populate the **Database** connection parameters. Note that these are the same parameters as defined in config.ini.
2. If the same Storage Management DB must be reused for several business intelligence environments, indicate a table prefix. It also should align with the table prefix defined in config.ini.
3. Click **Show Prepare Database SQL** to view the queries to be executed on the Storage Management DB.
4. Click **Prepare Database** to run the queries on the Storage Management DB.

![sm](images/bi_sm_details.PNG)

Note that it is not needed to perform the initialization process when using the default SQLite Storage Management DB.



[![Previous](/articles/images/Previous.png)](08_moving_from_dev_to_prod.md)
