# Invoke Remote Servers Calls

### Overview

There are cases where it is required to combine data from different sources (such as a source DB, HTTP APIs or files) and load it into the target tables. Broadway already supports such capability as built-in [Streams actors (http & files)](/articles/19_Broadway/04_built_in_actor_types.md#streams) (an example of using it can be found [here](/academy/Broadway/Training_Level_1/12b_broadway_as_a_population_http.md#exercise-2---populate-an-lu-table-using-a-db-query-and-an-http-call)), along with some response [parsers actors](/articles/19_Broadway/04_built_in_actor_types.md#parsers) (JSON, CSV, XML) and associated to special interfaces ([HTTP Interface](/articles/24_non_DB_interfaces/05_HTTP_interface.md#http-interface) and [SFTP Interface](/articles/24_non_DB_interfaces/02_SFTP_interface.md#sftp-interface)). However, this capability is required also at Fabric functions. Moreover, in some cases other external source types or formats might be needed, for example SOAP response format.

Accessing to a remote server shall be sued using libraries which already familiar with those sources protocols and formats and encapsulate it to java methods and objects. See [here](/articles/31_external%20resources/01_external_jars.md) for more information about using external JAR libraries.













### How Do I use a new library

#### Development and Debug Stage

* place the jar at the project's local lib directory **[Fabric Projects Directory]\[Project Name]\lib** (place it at the *lib* root directory and not at subdirectories).
* Restart you Fabric Studio, if it is already opened.
* Add the relevant JAR's classes ("import") at the *Logic* java file (make it on the relevant file in the tree, such as under "Enrichment" directory).
* Use the JAR classes and methods inside the Fabric function.

#### Deploying at Server

While JARs at the development stage within the Studio are associated to a specific project, at server the JARs are located regardless to projects. The following shall be Done at the server: 

* Copy the new JAR to the Fabric server into the following folder:

  `/home/k2view/ExternalJars/`. For more information refers to [Fabric Server - Main Directories](/articles/02_fabric_architecture/02_fabric_directories.md). 

  Note: JAR shall be copied to **all** Fabric nodes

* Restart the Fabric server (**all** nodes) in order your changes will take effect and can be used. 

### Example

This example demonstrates how a Telco carrier can align all its subscribers' phone number to follow the [E.164](https://en.wikipedia.org/wiki/E.164) standard format. The phone numbers, which are populated by CRM representatives or by integrated DBs, might vary. For example, at the Training Demo project, the LU **CONTRACT** table contains the following associated phone numbers for customer ID "55": "+1 (343) 842-1521", "680 463 5415", "5846694228", "(820) 633-6790". 

In order to achieve the goal of enabling the carrier to use a single format, we will use Google's [libphonenumber](https://github.com/google/libphonenumber) library, applying it at the Demo project, with the following steps:

1. Add an additional column to that table, named ***E164_LINE_FMT*** which we will populate at an [enrichment function](/articles/10_enrichment_function/01_enrichment_function_overview.md). Telco carrier can then use it when acquiring data from the customer LU.

2. Place the  [libphonenumber](https://github.com/google/libphonenumber) JAR (e.g. "libphonenumber-8.12.13.jar") at the project's local lib directory [Fabric Project's Directory]\demo\lib. Should looks like similar to the following:

   ![image](images/external_lib.png)

3. Create a new enrichment function at the **CUSTOMER** LU, named "E164PhoneFormat", using the external JAR: 

   - Add the relevant JAR's classes at the *Logic* java file:

     ~~~java
     import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
     import com.google.i18n.phonenumbers.PhoneNumberUtil;
     ~~~

   - Use the following function code: 

     ~~~java
     log.info("E164PhoneFormat function is running");
     
     PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance(); 
     PhoneNumber parsedNumber = null; 
     
     String SQLNumber="SELECT ASSOCIATED_LINE FROM CONTRACT";
     String SQLFormattedNumberE164="UPDATE CONTRACT SET E164_LINE_FMT  = ? where  ASSOCIATED_LINE = ?";
     String SQLFormattedNumber="";
     String formattedNumber="";
     String cellValue="";
     
     Db.Rows rows = fabric().fetch(SQLNumber);
     for (Db.Row row:rows){ 
     	cellValue=""+row.get("ASSOCIATED_LINE");
     	parsedNumber = phoneUtil.parse(cellValue, COUNTRY_CODE); 
     	formattedNumber = phoneUtil.format(parsedNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
         fabric().execute(SQLFormattedNumberE164,formattedNumber,cellValue);
     }
     ~~~

     \* Note: for simplicity we skipped null value validations and catching exceptions. 


After associating the enrichment function to the **CONTRACT** table and deploying the **CUSTOMER** LU, look for customer "55" via the the data viewer:



![image](images/external_e164.png)

As can be seen, the new table's column is populated with the required single format, for all associated lines.