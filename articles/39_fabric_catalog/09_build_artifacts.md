# Catalog Artifacts

### Overview

The Catalog provides an ability to build artifacts and save them into the Project tree. An artifact includes details of all the Catalog fields with their properties, such as Classification and PII, for a currently displayed Catalog version. 

Pre-requisite for building the Catalog artifact is running the Discovery Job for at least one Project interface.

### Build Artifacts

Building a Catalog artifact is done by clicking **Actions > Build Artifacts** in the Catalog application's [Menu bar](05_catalog_app.md#menu-bar). A Catalog artifact is a file called **catalog_field_info.csv**. It is created in a CSV format, saved into the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder in the Project tree and uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md).

The below image is a Catalog artifact example:

<img src="images/catalog_info_mtable.png" style="zoom:75%;" />

The artifact is created for the Catalog version, which is displayed in the application. The last column's heading holds the version number, **V4** in the above example. This column remains always empty.

Note that a Catalog artifact can be created for any Catalog version. Each new artifact overrides the existing artifact in the Project tree.

### Splitting and Combining Artifacts

Starting from V8.1, the Catalog artifacts can be split into separate files per each data platform and schema of a given Catalog version. The files' content is then combined into one single **catalog_field_info MTable** in Fabric's memory although the files are saved separately in the Project tree. 

This ability allows to combine separate artifacts, created in different projects (or different spaces), into a single artifact. Hence, the artifact files can be copied from one project to another, and upon deployment, they will be combined into one MTable.

Note that if the catalog_field_info.csv file exists in the Project tree, it should be manually deleted.

The splitting and combining ability is enabled only when the SPLIT_CATALOG_ARTIFACTS parameter in the config.ini file is set to ON (OFF is this parameter's default setting).

The separate files' name is created based on the following template - ```catalog_field_info___<dataPlatform>_<schema>.csv```, (containing 3 underscores before the data platform name).



[![Previous](/articles/images/Previous.png)](08a_filter_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_catalog_settings.md) 







