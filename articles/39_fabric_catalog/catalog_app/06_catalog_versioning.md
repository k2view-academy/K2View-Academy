# Catalog Versioning

The Catalog supports **versioning**, which is the ability to create a new Catalog version in the Neo4j Graph DB every time the Discovery process runs and finds differences when comparing with the previous version.

A user can view each version separately or check the differences between 2 selected versions using the Catalog application, as described below.

## Version Creation

Every time the Discovery job is executed, it performs the comparison between the current results and the previous Catalog version of the same data platform. The changes can be either due to the data source changes (such as a new table or a field) or due to the configuration changes (such as a new profiling rule or an update of an existing one). 

If the Discovery process doesn't identify any changes either in the data source or in the configuration rules, it doesn't create a new Catalog version.

If the changes are found, a new version is created and the can be analyzed by version comparison, as explained further in this article. 

Additional trigger for a version creation is a manual edit of the Catalog. 

[Click here for more information about manual overrides](07_manual_overrides.md).

## Version View

By default, the Catalog application displays the latest available version. To view any version, open the version's drop-down list and click a version number: 

<img src="../images/versions.png" style="zoom:75%;" />

The Catalog tree is then displayed using the standard coloring scheme, where all the nodes are blue and the relations are orange. 

## Version Comparison

To compare 2 versions, click the comparison <img src="../images/compare.png" style="zoom:75%;" /> icon in the version's drop-down list. The Catalog tree is then displayed using the comparison coloring scheme, indicating the differences between the 2 versions, as follows:

* The new elements are green, the removed elements are red, and the updated elements are purple.
* When a property is updated, it is displayed twice – the new value is highlighted in green whereas the removed value is in red.
* All unchanged entities and relations are grey.

To return to a regular view mode, open the version's drop-down list again and click a version number.

<img src="../images/compare_versions.png" style="zoom:67%;" />

### Show Differences Only

When a schema contains too many datasets, it might be difficult to spot the updated ones. To view only the changes, start from expanding the schema and then click the "Show differences only" icon in the Catalog's legend. Note that this icon is only visible in the version comparison mode.

<img src="../images/show_diff_only.png"  />

### Version Differences Report

The **Version Differences** screen, available in Fabric V8.3.1, displays all changes between the 2 selected versions, under the selected data platform and schema. The purpose of this screen is to visualize the changes in a table view, so that it will be easier to identify the differences.

The screen can be accessed by clicking the Version Differences Report icon, available on the schema node in the version comparison mode only.

<img src="../images/version_differences_icon.png" style="zoom:75%;" />

Each line in the  **Version Differences** screen includes the node name, its path and the change type, which indicates whether the node was added, deleted or updated. For properties, the columns Old Value & New Value display the property value.

The differences list can be exported into a CSV file by clicking the Export icon.

<img src="../images/version_differences_report.png" style="zoom:67%;" />







[![Previous](/articles/images/Previous.png)](05_catalog_app.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_manual_overrides.md) 

