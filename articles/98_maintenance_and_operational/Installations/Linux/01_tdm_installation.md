# TDM Installation and Initial Configuration

This document describes the installation guidelines and the initial configuration activities required for a new TDM installation or an upgrade of a TDM 7.x version to the current version.

## TDM Installation  -  Prerequisites

### Linux Installation 

The following components must be installed as a prerequisite:

- **Fabric Server** - TDM 8.1 works with Fabric 7.2 and above. see [here](/articles/98_maintenance_and_operational/Installations/Linux/02_Fabric_7.x.x_Setup.md).
- **PostgreSQL DB** - The TDM DB tables are created on a PostgreSQL DB. PostgreSQL v13 or v15 versions are certified. For more details, see [here](/articles/98_maintenance_and_operational/Installations/Linux/PGSQL_setup.md).

### Docker Installation 

Click here to open the [TDM 8.1 Docker Installation document](/articles/98_maintenance_and_operational/Installations/Docker/TDM/TDM_Docker_Installation_V8.1.md).

## Import the TDM Library
Both TDM layers - backend and frontend - are included in the [TDM library](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) from v7.6 onwards.
Download the TDM Library from the [download links](https://k2view.sharepoint.com/:w:/r/sites/KS/_layouts/15/Doc.aspx?sourcedoc=%7BAD4D11C5-FC8E-4794-AD25-B31ECE391ED4%7D&file=TDM%208.1.0_download_links.docx&action=default&mobileredirect=true), import and deploy it.

Click [here](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) for instructions on how to import and deploy the TDM Library.

## TDM Web Application

Import the updated TDM LU to Fabric project and deploy it to Fabric. Use the *Import All* option to import the TDM Portal (self-service) code (stored in the web subfolder) into the TDM LU. The deployment of the TDM LU also deploys the TDM Portal to Fabric and adds it to the web applications in your Fabric web framework.

Click for more information about [Fabric Web Framework](/articles/30_web_framework/01_web_framework_overview.md).


## Create the TDM PostgreSQL DB (in case of new installation)

- From TDM 7.6 onwards TDM creates the TDM DB objects by the TDM LU's deploy flow:

   - Creates the TDM DB tables, sequences, views, and functions.

  Notes: 
  - You must set the **BUILD_TDMDB Global to true in order to create the TDM DB** by the TDM deploy flow.
  - You must set the **POSTGRESQL_ADMIN interface** to be **active** in order to **create the TDMDB database and 'tdm' user (role) in the postgreSQL DB**.
  
## Upgrade the TDM PostgreSQL DB (if not a new installation)

- Soft deploy the TDM LU to Fabric debug server. Before the deploy, verify that the TDM interface is updated with the TDM DB connection details.
- Run the **RunTDMDBUpgradeScripts** flow. Populate the current version and the target version input parameters. Set the target version parameter to 8.1. For
example:
  - CURRENT_TDM_VERSION = 8.0.
  - TARGET_TDM_VERSION = 8.1.

- Note that the **version’s list** is set in **TDMDBUpgradeScripts** actor (imported from the TDM Library).

- For more details see [TDM Upgrade Document](Release_Notes_And_Upgrade/TDM-V8.1/TDM_Upgrade_Procedure_to_V8.1.pdf).


## Create K2masking Keyspace

The **k2masking** keyspace is needed for a TDM implementation in order to support masking or sequence handling. The **k2masking** keyspace is **automatically created** by the the **TDM LU's deploy.flow** upon the deployment of the TDM LU to Fabric. Alternatively, you can run the **masking-create-cache-table.flow** from the library of Broadway examples or run the **create_masking_cache_table.sql** of the [TDM Library](/articles/TDM/tdm_implementation/04_fabric_tdm_library.md) to create the **K2masking** keyspace if needed.

Click for more information about [sequence handling](/articles/TDM/tdm_implementation/11_tdm_implementation_using_generic_flows.md#step-2---create-sequences).

## Add Permission Groups Mapping to the TDM

The TDM Portal application is pre-integrated with [Fabric Web Framework](/articles/30_web_framework/02_preintegrated_apps_overview.md). The user logs in to the Fabric Web Framework and **Fabric authenticates the user**. The TDM Portal gets the **user id** and the user's **Fabric roles** from the user's session and **identifies the user type (Permission Group) by their Fabric roles**.

The mapping of each Fabric role to a TDM Permission Group is done by the [Permission Groups Mapping](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) TDM window and is kept in [permission_groups_mapping TDM DB table](/articles/TDM/tdm_architecture/02_tdm_database.md#permission_groups_mapping).

After installing the TDM, the admin user must [log in to the TDM Portal](/articles/TDM/tdm_gui/01_tdm_gui_overview.md#tdm-gui---login), open the [Permission Groups Mapping window](/articles/TDM/tdm_gui/02a_permission_group_mapping_window.md) and define the Permission Group mapping of each user's group (= Fabric role). This process is done in order to enable the TDM users to work properly on the TDM Portal, based on their Permission Groups.

## Update the TDM APIDOC (Optional)

The TDM APIDOC html file is located in the TDM folder (imported from the TDM library) in Fabric project:  

`\TDM\web\TDM\apidoc`

The TDM APIDOC describes all the Web Services in the Fabric project including the TDM Web Services imported from the TDM Library.

The below steps should be followed if a new APIDOC is generated to include project custom APIs:

1. Import the following objects from the updated TDM library:

   - TDM_APIDOC_JSON interface (local file interface)- this interface contains the location of the TDM APIDOC JSON file.
   - buildTdmApiJSON Broadway flow.

2. Edit the **TDM_APIDOC_JSON** interface or define the TDM_APIDOC directory under C:\k2view\ local directory.

3. Deploy Fabric project's Web Services to the local debug server.

4. Edit the buildTdmApiJSON flow: edit the port in the path input parameter of the HttpJson actor.

5. Run **buildTdmApiJSON** Broadway flow to create a JSON under the local directory of **TDM_APIDOC_JSON** interface. Populate the current TDM version in the **TDM Version** input parameter. For example, TDM 8.1. This version is added to the generated APIDOC.

6. Open the Swagger editor using the following URL: https://editor.swagger.io/.

7. In the Swagger Editor:

   - Click **File > Import File** and select the **TDM_APIDOC.json** generated by the Broadway flow.
   - Click **Generate Client > html** to download a zip file with the HTML file to your working station.

8. Open the TDM folder in the Fabric project: right-click the TDM LU > Open Folder. A File Explorer window opens.

9. Navigate the File Explorer window and move to `\TDM\web\TDM\apidoc` sub-directory.

10. Extract the zip file and copy the generated HTML file under the `\TDM\web\TDM\apidoc` sub-directory.

11. Open the TDM Portal and click the API Doc tab. Verify that the API doc is displayed properly.

