# BI Admin Configuration

**BI Admin** (Administration Console) enables the BI application configuration including the security, database, UI and other various settings. **WebReports.XML** is the main Exago BI configuration file which stores these settings. Any change applied on the BI Admin module is reflected in WebReports.XML.

Note that WebReports.XML does not keep the default flag values. Meaning that if by default a flag is True, it only appears in WebReports.XML when its value is changed to False. 

During the BI application installation, WebReports.XML is copied to your server and it is pre-configured for the Fabric-BI integration. 

This article describes the configurations which exist in WebReports.XML and are must for Fabric-BI integration as well as some other important settings.

### Allow Direct Access

The BI application can either be invoked directly via the URL or by the REST call from the hosting application. As part of Fabric-BI integration solution, the BI application must be invoked by the REST call only in order to secure the access to the BI data. Thus the following flag must be set to False in **BI Admin** > **General** > **Main Settings**:

![image](images/99_allow_dir_access.PNG)

This is how this flag is defined in WebReports.XML:

~~~xml
<allowhomedirect>False</allowhomedirect>
~~~

### REST Key Initialization

REST Key is used to authenticate REST requests from Fabric to Exago. 

Open **BI Admin** >  **General** > **Other Settings** and populate the REST Key:

![key](images/bi_rest_key.PNG)

Then copy the key to the **BI_REST_KEY** parameter of **config.ini**.

### Allow New Root Directories

By default, the **BI Designer** allows to create root folders for keeping the reports:

![image](images/99_add_folder.PNG)

In order to enforce the access permissions on the Project level, this ability is disabled by setting the following flag to False in **BI Admin** > **General** > **Feature/UI Settings**:

![image](images/99_allow_new_root.PNG)

Upon the project first deployment, the <project name> folder is created automatically as described [here](01_Installation.md#Project-Initialization-in-BI). The user can still create child folders under this folder, if he has either **Unrestricted** or **CreateContent** access level.

This is how this flag is defined in WebReports.XML:

~~~xml
<allowreporttreerootaccess>False</allowreporttreerootaccess>
~~~

### Exago User Roles 

Exago User Roles are created to specify how a user or group of users interfaces with Exago. Roles can restrict access to folders or data objects.

The main reason for using the Roles is the ability to restrict or allow the reports creation. Two roles are pre-configured in the BI Admin and must be present in WebReports.XML:

* **create_report** role, which allows to create all kinds of reports in BI Designer.
* **default** role, which doesn't allow the reports creation.

The Fabric user roles with **BI_ADMIN** permission are automatically assigned the **create_report** role, thus are allowed to create the reports.

[Click to get more information about the Exago Roles](https://support.exagoinc.com/hc/en-us/articles/214571808-Roles).

### Storage Management Initialization

When a PostgreSQL DB is installed, a Storage Management schema must be initialized. It includes a creation of specific metadata and data. 

As part of the Fabric-BI integration, the Storage Management initialization is performed automatically upon the project deploy. However when working on Exago BI application directly, the following steps must be performed:

1. Open **BI Admin** >  **Storage Management** and populate the **Database** connection parameters. Note that these are the same parameters as defined in config.ini.
2. If the same Storage Management DB must be reused for several BI environments, indicate a table prefix. It also should align with the table prefix defined in config.ini.
3. Click **Show Prepare Database SQL** to view the queries to be executed on the Storage Management DB.
4. Click **Prepare Database** to run the queries on the Storage Management DB.

![sm](images/bi_sm_details.PNG)

Note that it is not needed to perform the initialization process when using the default SQLite Storage Management DB.

[Click to get more information about the Storage Management DB initialization](https://support.exagoinc.com/hc/en-us/articles/360042229693).

