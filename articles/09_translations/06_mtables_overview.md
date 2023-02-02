# MTable Overview

### What Is an MTable?

MTable is a Fabric Studio object created by uploading a CSV file into the project tree. The purpose of an MTable object is to keep reference data as part of the Fabric project and enable data lookup or data transformation rules at run time. 

<studio>

MTable objects are only available in the Fabric Web Studio.

</studio>

<web>

### How Can I Create an MTable?

An MTable object can be created in the Fabric Studio using one of the following ways:

* Uploading a CSV file into the MTable folder in the project tree under LU, Common or Web Services. 
* Manually creating a CSV file under the MTable folder and populating its data.

Upon the deploy, the MTable metadata is created / updated based on the CSV file structure. Any other file type except CSV is ignored. The MTable is created as a schema in Fabric memory. The data from CSV file is then loaded to this schema. 

Another way of creating an MTable is at run time using a dedicated Actor. In this case, the MTable is created in memory and not under a specific LU.

The data lookup can be performed by one or several keys of MTable. The index is created on-the-fly, based on the search keys. 

### How Can I Use an MTable?

An MTable can be used when a flow or a project function needs to lookup the data by given key(s). 

* In a flow:
  * Invoke an [MTable Actor](/articles/19_Broadway/actors/09_MTable_actors.md) providing it the MTable name and a map of keys and their values. Each key can receive one value. 
  * The Actor then will return an array of results.
* In Java code, 2 APIs are exposed: 
  * To return the whole MTable by its name.
  * To return the array of results by the map of keys & values.

Each MTable can be accessible by any LU, regardless of their location in the Project.

[![Previous](/articles/images/Previous.png)](01_translations_overview_and_use_cases.md)

</web>
