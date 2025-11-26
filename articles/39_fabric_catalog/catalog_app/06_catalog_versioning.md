# Catalog Versioning

The Catalog supports **versioning**, which is the ability to create a new Catalog version in the Neo4j Graph DB every time the Discovery process runs and finds differences when comparing with the previous version.

A user can view each version separately or check the differences between two selected versions using the Catalog application, as described below.

## Version Creation

Every time the Discovery job is executed, it performs a comparison between the current results and the previous Catalog version of the same data platform. The changes can be either due to the data source changes (such as a new table or a field) or due to the configuration changes (such as a new profiling rule or an update of an existing one). 

If the Discovery process does not identify any changes in either the data source or the configuration rules, it does not create a new Catalog version.

If changes are found, a new version is created and the can be analyzed by version comparison, as explained further in this article. 

An additional trigger for a version creation is a manual edit of the Catalog. 

[Click here for more information about manual overrides](07_manual_overrides.md).

## Version View

By default, the Catalog application displays the latest available version. To view any version, open the version's drop-down list and click a version number: 

<img src="../images/versions.png" style="zoom:75%;" />

The Catalog tree is then displayed using the standard coloring scheme, with nodes shown in blue and relations in orange. 

## Version Comparison

To compare two versions, click the comparison <img src="../images/compare.png" style="zoom:75%;" /> icon in the version's drop-down list. The Catalog tree is then displayed using the comparison coloring scheme, indicating the differences between the two versions, as follows:

* New elements are shown in green, the removed elements in red, and updated elements in purple.
* When a property is updated, it is displayed twice — the new value is highlighted in green, while the removed value is shown in red.
* All unchanged entities and relations are shown in grey.

To return to the regular view mode, open the version's drop-down list again and click a version number.

<img src="../images/compare_versions.png" style="zoom:67%;" />

### Show Differences Only

When a schema contains many datasets, it might be difficult to spot which ones were updated. To view only the changes, start by expanding the schema and then click the 'Show differences only' icon in the Catalog legend. Note that this icon is visible only in version comparison mode.

<img src="../images/show_diff_only.png"  />

### Version Differences Report

The **Version Differences** screen, available in Fabric V8.3.1, displays all changes between the two selected versions, under the selected data platform and schema. The purpose of this screen is to visualize the changes in a table view, making it easier to identify the differences.

The screen can be accessed by clicking the *Version differences report* icon, which is available on the schema node and appears only in version comparison mode.

<img src="../images/version_differences_icon.png" style="zoom:75%;" />

Each line in the **Version Differences** screen includes the node name, its path and the type of change. The latter indicates whether the node was added, deleted or updated. For property changes, the *Old Value* and *New Value* columns display the previous and updated property values.

The differences list can be exported to a CSV file by clicking the *Export* icon (top-right corner).

<img src="../images/version_differences_report.png" style="zoom:67%;" />







[![Previous](/articles/images/Previous.png)](05_catalog_app.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07_manual_overrides.md) 

