# Frequently Used Actors

Now that you know how to work with Actors, let's explore the different types of built-in Broadway Actors and learn about the most frequently used ones.

For an overview on built-in Broadway Actors, please read [Built-in Actor Types](/articles/19_Broadway/04_built_in_actor_types.md). 

Let's take a closer look at some of the most useful Actors.

* Dynamic Logic Actors are Actors that include dynamic logic as one of their input parameters. The most frequently used are:
  * **JavaScript** Actors, that simplify flows by writing JavaScript business logic or validation code in the **script** input parameter.
  * **DbCommand** Actors, that perform DB commands and actions like creating a new table, loading data, etc.

* Other Actors for handling useful activities are:
  * **Stream** Actors, that handle streams like reading from and writing to a file or compressing and decompressing data.
  * **Parser** Actors, that parse input streams into different formats like JSON, CSV and XML.
  * **Queue** Actors, that manage Pub / Sub asynchronous message handling like Apache Kafka or JMS.

To learn more about the above Actors, their specifications and examples, read [Actor Specifications and Examples](/articles/19_Broadway/actors/README.md). 



Now let's see the example of using Actors to read a file and parse it.

[![Previous](/articles/images/Previous.png)](08a_using_actors_in_boadway_flows_example.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](09a_frequently_used_actor_types_example.md)