# MTable Overview

### What Is an MTable?

MTable is a Fabric Studio object created by uploading a CSV file into the project tree. 

<studio>

MTable objects are only available in the Fabric Web Studio, replacing the [Translation](01_translations_overview_and_use_cases.md) objects of the Fabric .NET Studio.

</studio>

<web>

The purpose of an MTable object is to keep reference data as part of the Fabric project. The MTable metadata is created as a schema in Fabric memory upon deploy, based on the CSV file structure. The data from CSV file is then loaded to this schema. 

The data lookup can be performed by one or several keys of MTable. The index is created on-the-fly, based on the search keys. 

There are two ways for creating an MTable object in the Fabric Studio:

* Uploading a CSV file into the MTable folder in the project tree. Note that any other file type except CSV will be ignored. 
* Manually creating a CSV file under the MTable folder and populating its data.

### How Can I Use an MTable?

An MTable can be used when a flow or a project function needs to lookup the data by given key(s). 

* In a flow:
  * Invoke an MTable Actor providing it the MTable name and a map of keys and their values. Each key can receive one value. 
  * The Actor then will return an array of results.
* In Java code, 2 APIs are exposed: 
  * To return the whole MTable by its name.
  * To return the array of results by the map of keys & values.

Each MTable can be accessible by any LU, regardless of their location.

[![Previous](/articles/images/Previous.png)](05_translations_code_examples.md)

</web>