# Broadway Overview


Broadway is Fabric's module for designing data movement, transformation and orchestration business flows. Broadway provides a powerful user interface to create and debug business and data flows, coupled with a high performance execution engine that can be activated by Fabric.

Broadway is used throughout Fabric, wherever data movement and orchestration is needed. Some examples:
* Logical Unit data population from external databases or REST APIs.
* Move data from Fabric to external systems based on CDC or batch processes.
* Subscribe to a message bus and consume messages.
* Orchestration of scheduled activities through Fabric's job system.
* Data transformation for web services.

Broadway is a flexible engine, seamlessly integrated into the fabric command system. You can leverage its power anywhere in Fabric's architecture layers for limitless use cases.


## Orchestration and Business Process

A Broadway flow is built out of Stages. The Stages are executed from left to right. The flow can be 'split' into different execution paths based on conditions. More than one stage can be executed in each fork in the path.

![image](/articles/99_Broadway/images/Broadway_flow.png)

In this example above, the "Fetch" stage is executed first. The system will then execute either the "Transform Consumer" or (else) the "Transform Business" stage. Finally it will execute the "Load" stage.


## Logic and Data Transformation

Each of the Stages can contain one or more Actors. Actors are reusable pieces of logic with input and output arguments that can be assembled together to create complex logic. The Actors are executed by the Stages.

![image](/articles/99_Broadway/images/Broadway_actors.png)

In this example, "Fetch" queries some data and passes it as inputs to the Actors on the next Stages. Based on the data, either the "Transform Consumer" stage is executed or the "Transform Business".
In turn, these stages execute the Actors that build data for the DbLoad actor in the last stage.

An important aspect of Actors is that an entire Broadway flow can be exported and encapsulated into an Actor and be reused between flows. This is powerful tool for reusing logic and dealing with high complexity flows.


## Data Inspection

As Broadway passes data between Actors, the data is visible in the Broadway studio. Complex data types (objects, arrays) are automatically detected and analyzed, and both metadata and data are visually rendered for easy debugging and extraction.

![image](/articles/99_Broadway/images/Broadway_data_inspection.png)

Above we can see the system automatically identifying the data structure of the "Fetch Customer" Actor. This enables picking specific fields from the data and passing them to the appropriate actors.


## Learning Broadway

The powerful combination of execution by Stages and logic encapsulated in Actors are where Broadway gets its name. Coupled with the data and metadata inspection engine, they provide the main pillars of Broadway.
Broadway has additional capabilities that together provide a great way to model data movement and orchestration which we will explore in the next article. The rest of the articles in the knowledge base will go in depth into each feature.

Another great way to learn how to use Broadway is from the built-in tutorial. From any Broadway flow you create, you can go to *Actions > Examples* and see documented sample flows. "a-broadway-tutorial.flow" takes you step by step through the major Broadway features and capabilities and can act as a good starting point. The other example flows focus on a single feature or capability, demonstrating and explaining them in depth.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_Broadway/02_broadway_high_level_components.md)
