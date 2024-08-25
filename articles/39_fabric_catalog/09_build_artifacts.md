# Catalog Artifacts

### Overview

The Catalog provides an ability to build artifacts and save them into the Project tree. An artifact includes details of all the Catalog fields with their properties, such as Classification and PII, for a currently displayed Catalog version. 

Pre-requisite for building the Catalog artifact is running the Discovery Job for at least one Project interface.

### Build Artifacts

Building a Catalog artifact is done by clicking **Actions > Build Artifacts** in the Catalog application's [Menu bar](05_catalog_app.md#menu-bar). A Catalog artifact is a file called **catalog_field_info.csv**. It is created in a CSV format, saved into the ```Implementation/SharedObjects/Interfaces/Discovery/MTable``` folder in the Project tree and uploaded to the Fabric memory as an [MTable](/articles/09_translations/06_mtables_overview.md).

The below image is an example of a Catalog artifact:

<img src="images/catalog_info_mtable.png" style="zoom:75%;" />

The artifact is created for the Catalog version that is displayed in the application. The last column's heading holds the version number, such as **V4** in the case of the above image. This column remains always empty.

Note that a Catalog artifact can be created for any Catalog version. Each new artifact overrides the previous one in the Project tree.

### Split and Combine Artifacts

Starting from V8.1, the Catalog's artifacts can be split into separate files per each data platform and schema of the Catalog version. This is only enabled when the SPLIT_CATALOG_ARTIFACTS parameter in the config.ini is set to ON (by default, it’s set to OFF). 

The name of separate files is: ```catalog_field_info___<dataPlatform>_<schema>.csv``` (with 3 underscore).

Upon deploy, the files are combined into one single **catalog_field_info MTable** (though the files are kept separately in the Project tree).

 

[![Previous](/articles/images/Previous.png)](08_search_catalog.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](10_catalog_settings.md) 







