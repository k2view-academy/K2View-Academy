# Stream Actors

Broadway has a group of [built-in](../04_built_in_actor_types.md) **Stream** Actors that handle streams like reading from and writing to a file or compressing and decompressing  data.  

The **FileRead** Actor can be used to read data from a file in a provided location and return a collection of blobs. Note that the **path** input argument expects to receive the relative path and the file name. 

To prevent overloading data into the memory, when Broadways reads from a stream or file, the data is split into blocks that have a constant size of 8K. As a result, data can be cut in the middle of an object, for example, in the middle of a line in the file, whereby the stream can then be handled by a [Parser Actor](03_parsers_actors.md). 
 
When Broadway is reading from a stream or a file, it is done in blocks with constant size of 8K, in order to prevent loading too much data into the memory. Due to a constant size of the data blocks, the data can be cut in the middle of the object (e.g. in the middle of a line in the file), thus the stream handling must be followed by a [parser Actor](03_parsers_actors.md). 

### FileRead Examples

The following example shows a flow where the **FileRead** Actor reads the data from a CSV file and is followed by a **CsvParser** Actor that parses the data into separate lines.

![image](../images/99_actors_02_1.PNG)



Since the **FileRead** Actor can work on one file in the flow at a time, it can be preceded by the file system handling Actors. For example, using the **ls** Actor to list the files in a given directory and iterating over the files using the **FileRead** Actor.

The **file.flow** example shows a flow with various steps that handle streams including compressing data, creating a directory and files, listing the files and moving them between directories.
The **file.flow** example shows how to handle the flow that includes various steps of stream handling such as compressing the data, creating a directory and the files, listing the file and moving them between the directories.

Click **Actions** > **Examples** in the [Main menu](../18_broadway_flow_window.md#main-menu) to open the **file.flow** example. 

![image](../images/99_actors_02_2.PNG)



[![Previous](/articles/images/Previous.png)](01_javascript_actor.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_parsers_actors.md)

