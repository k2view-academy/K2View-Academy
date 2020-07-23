# Broadway Overview


Broadway is a Fabric module that can be used to design data movement, its transformation and the orchestration of business flows. Featuring a powerful user interface for creating and debugging business and data flows, Broadway also provides a high-performance execution engine that can be activated by Fabric.

Broadway is used throughout Fabric, wherever data movement and orchestration is needed. For example:
* LU data population from external databases or REST APIs.
* Moving data from Fabric to external systems based on CDC or batch processes.
* Subscription to a message bus and consume messages.
* Orchestration of scheduled activities through Fabric's job system.
* Data transformation for Web Services.

Broadway is a flexible engine, seamlessly integrated into the Fabric command system. Its power can be leveraged anywhere in Fabric's architecture layers for limitless use cases.


## Orchestration and Business Process

A Broadway flow is built in Stages which are executed from left to right. A flow can be split into different execution paths based on conditions. More than one Stage can be executed in each fork in the path.

![image](/articles/99_Broadway/images/Broadway_flow.png)

In this example above, the Fetch Stage is executed first. The system then executes either the Transform Consumer or (else) the Transform Business Stage. Finally it executes the Load Stage.


## Logic and Data Transformation

Each Stage can contain one or more Actors. Actors are reusable pieces of logic with input and output arguments that can be assembled together to create complex logic. The Actors are executed by the Stages.

![image](/articles/99_Broadway/images/Broadway_actors.png)

In this example, Fetch queries data and passes it as input to the Actors in the next Stages. Based on the data, either the Transform Consumer Stage or the Transform Business Stage is executed.  In turn, these Stages execute the Actors that build data for the DbLoad Actor in the last Stage.

An important aspect of Actors is that an entire Broadway flow can be exported and encapsulated into an Actor and be reused between flows. This is powerful tool for reusing logic and dealing with high complexity flows.


## Data Inspection

As Broadway passes data between Actors, the data is visible in the Broadway Studio. Complex data types (objects, arrays) are automatically detected and analyzed, and both metadata and data are visually rendered for easy debugging and extraction.

![image](/articles/99_Broadway/images/Broadway_data_inspection.png)

Above we can see the system automatically identifying the data structure of the Fetch Customer Actor. This enables picking specific fields from the data and passing them to the appropriate Actors.


## Learning Broadway

The powerful combination of execution by Stages and logic encapsulated in Actors are where Broadway gets its name. Coupled with the data and metadata inspection engine, they provide the main pillars of Broadway.
Broadway has additional capabilities that together provide a great way to model data movement and orchestration which are explored in the next article. The other articles in the Knowledge Base provide in-depth information about each feature.

Another great way to learn how to use Broadway is from the built-in Tutorial. From any Broadway flow you create, you can go to *Actions > Examples* and see documented sample flows. "a-broadway-tutorial.flow" takes you step by step through the major Broadway features and capabilities and can act as a good starting point. The other example flows focus on a single feature or capability, demonstrating and explaining them in depth.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/02_broadway_high_level_components.md)
