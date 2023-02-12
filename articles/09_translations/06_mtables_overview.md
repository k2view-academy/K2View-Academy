# MTable Overview

### What Is an MTable?

An MTable is a Fabric Studio object, created by uploading a CSV file into the Project Tree. The purpose of an MTable object is to keep reference data as part of the Fabric project and enable data lookup or data transformation rules at run time. 

<studio>

MTable objects are available only in the Fabric Web Studio.

</studio>

<web>

### How Can I Create an MTable?

An MTable is an object created in the Fabric memory from a CSV file. The file can be created in Fabric Studio by using one of the following techniques:

* Uploading a CSV file into the MTable folder in the Project Tree, under either LU, Common or Web Services. 
* Manually creating a CSV file under the MTable folder and populating its data.

Upon deployment, the MTable metadata is created based on the CSV file structure, and the MTable object is uploaded to the Fabric memory. Other file types, apart from the CSV type, are ignored.

In addition, an MTable can be created or updated in Fabric memory by using a MTableLoad Actor at run time.

The data lookup can be performed by one or several MTable keys. The index is created on-the-fly during the first select, based on the search keys. 

### How Can I Use an MTable?

An MTable can be used when a flow or a Web Service needs to look up data either by the given key(s) or without them, randomly. 

* To use an MTable in a flow:
  * Invoke an MTable Actor, providing it with the MTable name and a map of keys and their values. Each key can receive one value. 
  * The Actor, then, will return an array of results that match the input parameters.
* In a Java code, 2 APIs are exposed: 
  * API that returns the whole MTable by its name.
  * API that returns the array of results by the map of keys & values.

Each MTable can be accessible from any LU, regardless of the MTable location in the Project.

[Click here for more information about MTable Actors.](/articles/19_Broadway/actors/09_MTable_actors.md)

[![Previous](/articles/images/Previous.png)](01_translations_overview_and_use_cases.md)

</web>
