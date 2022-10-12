<studio>

# Broadway Integration with Fabric Studio

The Fabric Studio includes several integration points that are used by Broadway Actors to simplify the creation of Broadway flows.

### Broadway as a Population

A Broadway flow can be used as a [Table population](/articles/07_table_population/01_table_population_overview.md) to replace  complex Java code in the population logic by Stages and Actors in the flow. 

To create the population based on the Broadway flow, right click the table name in the **Project Tree** and select **New Table Population Based Broadway Flow**. The population template is created and can be modified as needed.

![image](images/99_07_POPULATION.PNG)

[Click for more information about creating a table population based on a Broadway flow](/articles/07_table_population/14_table_population_based_Broadway.md).

### Interface Listener For Broadway Flows

The **InterfaceListener** Actor allows a Broadway flow to be triggered upon detection of an event on the Interface. 

In addition, it is also supported via a flow as below and an Interface Listener job that can be defined via Fabric Studio.

The Interface Listener functionality, an enhancement of the Fabric Jobs functionality, can be used to read and parse files using a Broadway flow. An Interface Listener is triggered each time a new file arrives to the directory defined in the interface, which can be either an SFTP or a local file system. Each file is only picked up once by the Listener and the file name must not be repeated otherwise it will be disregarded.  

![images](images/file_read_listener.png)

The Listener invokes the attached Broadway flow that needs the **FileRead** Actor to read the files. The **interface** and the **path** input arguments of the **FileRead** Actor must be defined as an [External link type](/articles/19_Broadway/03_broadway_actor_window.md#actors-inputs-and-outputs). Their values are passed from the defined interface by the Listener.

![image](images/99_07_JOBS.PNG)

[Click for more information about creating an SFTP interface for an Interface Listener](/articles/24_non_DB_interfaces/02_SFTP_interface.md#example-of-using-an-sftp-interface).

### Fabric Commands Actors

The **Fabric** category of [built-in Actors](04_built_in_actor_types.md) executes Fabric commands. For example:

* **FabricGet** Actor, executes the GET command on the current Fabric session.
* **FabricSet** Actor, sets a value on the Fabric session.
* **FabricSetRead** Actor, reads a key from the Fabric session.

The **FabricGet** Actor supports fetching multiple instances from different LUs and enables setting **parallel** and **stop_on_error** arguments. To sync multiple LUIs, do either:

* Connect a list of LU types to the **luType** input argument and a list of Instance IDs to the **iid** input argument.


<img src="images/99_07_FABRIC_0.PNG" alt="image"  />

* Or, add a new input to the Actor, change the input name to the **luType** name and set the value as **iid**. In this case, the **luType** input argument of the Actor should be set to the empty value.


<img src="images/99_07_FABRIC_1.PNG" alt="image"  />


### Use of LU Functions and Graphit in Broadway

**LuFunction** and **Graphit** Actors use Fabric integration with Broadway to enable the reuse of Fabric logic in Broadway flows. 

To do so, set the [Logical Unit](/articles/03_logical_units/01_LU_overview.md) in the **luType** input argument and then select a [Project function](/articles/07_table_population/08_project_functions.md) or a [Graphit](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md) resource. 

Note that the **luType** input argument includes the list of all Logical Units in the Project including the [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md) and the References. It is recommended to use the same LU used for the creation of the Broadway flow.

#### LuFunction Actor

The **LuFunction** Actor is used when a Fabric [Project function](/articles/07_table_population/08_project_functions.md) must be invoked from a Broadway flow and is also a way to write business logic in Java rather than in JavaScript in Broadway. 

After the **luType** input argument is set, the list of values in the **functionName** dropdown list are filtered by the LU name and display the functions of the selected LU and Shared Object. The Actor's input and output arguments are updated with the inputs and output of the selected function.

#### Graphit Actor

The **Graphit** Actor executes Graphit logic for data serialization. Parameters for the Graphit execution are picked up from input arguments or from the **params** input argument.

After the **luType** input argument is set, the list of values in the **graphit** dropdown list is filtered by the LU name. The Actor's input and output arguments are updated with the inputs and output of the selected Graphit resource.

### Table Selection and Query Builder

The input arguments of [DB Commands Actors](actors/05_db_actors.md) include integration to Fabric Windows which simplifies the creation and validation of queries. 

To populate the SQL statement of a **DbCommand** Actor, do the following:

1. Set the **interface** input argument and then click **QB** in the **sql** input argument field. The [Query Builder window](/articles/11_query_builder/02_query_builder_window.md) opens and is filtered by the selected DB connection.
2. Click the required table and fields or write the query manually. 
3. Click **Execute Query** to validate it and then click **OK**. The SQL statement is populated in the **sql** input argument of the **DbCommand** Actor.

To set the table and the fields of a **DbLoad** Actor, do the following:

1. Set the **interface** input argument and click **DB** in the **table** input argument field. The Table Selection popup window opens and is filtered by the selected DB connection.
2. Click the required **table** and then **OK** to populate the table name and the columns in the **table** and the **fields** input arguments of the **DbLoad** Actor.

<table>
<tbody>
<tr>
<td valign="center" ><img src="images/99_07_SQL.PNG" alt="SQL" /></td>
<td valign="center" ><img src="images/99_07_DB.PNG" alt="DB" /></td>
</td>
</tr>
</tbody>
</table>

### Interfaces List

Several Broadway Actors include an **interface** as an input argument. When setting the Actor's interface from the dropdown list, the list of values is retrieved from the Project Interfaces list. Only Active interfaces are displayed. The values are filtered by the Interface Type where only interfaces relevant to the Actor type are shown.



[![Previous](/articles/images/Previous.png)](08_show_only_connected_actors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09a_automatic_flows_execution_upon_deploy.md)

</studio>
