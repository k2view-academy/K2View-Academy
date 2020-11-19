# Broadway Integration with Fabric Studio

### What Will You Experience In This Learning Item?

By the end of this learning item you will:

- Be familiar with the integration points between Broadway and the Fabric Studio.
- Create a Broadway flow for the table population in the Logical Unit.
- Define an Interface Listener for a Broadway flow that will serve as a daemon for handling the stream.

### Overview

The Fabric Studio includes several integration points that are used by Broadway Actors to enhance the Fabric and to simplify the creation of Broadway flows, such as:

* **Broadway as a Population**, to create a flow which can be used to populate one or more tables of the Logical Unit to streamline the logic and the related validations.
* **Interface Listener for Broadway flows**, to define a daemon on an SFTP or a local file system interface to invoke a Broadway flow that handles input files.
* **FabricGet** / **FabricSet** Actors to execute Fabric commands.
* **LuFunction** Actor to invoke any LU Project function from the flow.
* **Graphit** Actor to execute Graphit logic for data serialization.

To learn about the above functionalities, please refer to:

* [Broadway Integration with Fabric Studio](/articles/19_Broadway/09_broadway_integration_with_Fabric.md).
* [Table Populations Based on Broadway Flows](/articles/07_table_population/14_table_population_based_Broadway.md).



Now let's go to the exercises and practice creating a Broadway flow as a population and an Interface Listener.



[![Previous](/articles/images/Previous.png)](10_using_various_actors_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](12_broadway_as_a_population_exercise.md)
