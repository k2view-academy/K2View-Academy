# DB Commands Actors 

Th **db** category of [built-in Actors](../04_built_in_actor_types.md) includes very useful Broadway Actors for performing the DB commands and actions, such as creating a new table, loading data or fetching it, or executing a DB command. These Actors are:

- **DbCommand** Actor, performs database commands against a DB interface. It has two extensions: 
  - **DbFetchField** Actor, returns the first field of the first row or null if not present.
  - **DbFetchFirstRow** Actor, returns the first row or an empty row if no result is present.
- **DbCreateTable** Actor, creates a new database table.
- **DbLoad** Actor, loads data into a database using INSERT, UPDATE or UPSERT where supported.

Each of the **db** category Actors includes the setting of the **interface** input argument that can be either a reference to the Fabric [DB Interface](/articles/05_DB_interfaces/03_DB_interfaces_overview.md) or a JDBC URL. 

When executing queries, the query can contain either ordered parameters using **?** or named parameters using **${}** notation. The values of named parameters are taken from the Actor's input parameters or from the **params** input argument and only in case it is a map. In case of ordered parameters, the **params** input argument should be an array or a single value (not a map).



### DB Commands Example

The **db-commands.flow** example shows how **DbCommand** Actor can be used to perform various DB actions:

* To create a table using the SQL provided in **sql** input argument.
* To insert the data into the table using the **sql** input argument with names parameters marked as **${text}**. The parameters values are populated from the **params** input argument.
* To select the data from the table using the **sql** input argument with order parameter marked as **?**. 

Click **Actions** > **Examples** in the [Main menu](../18_broadway_flow_window.md#main-menu) to open the **db-commands.flow** example. 

![image](../images/99_actors_05_1.PNG)



[![Previous](/articles/images/Previous.png)](04_queue_actors.md)