# Stream Actors

Broadway provides a group of [built-in Actors](../04_built_in_actor_types.md) to handle the streams, such as reading from a file or writing into a file, compressing or uncompressing the data. These Actors belong to a **streams** category.

Use the **FileRead** Actor to read the data from a file in the provided location and return the collection of blobs. Note that the **path** input argument expects to receive the relative path and the file name. 

When Broadway is reading from a stream or a file, it is done in blocks with constant size of 8K, in order to prevent loading too much data into the memory. Due to a constant size of the data blocks, the data can be cut in the middle of the object (e.g. in the middle of a line in the file), thus the stream handling must be followed by a [parser Actor](03_parsers_actors.md). 

The following example shows a flow where the **FileRead** Actor is reading the data from a CSV file and is followed by a **CsvParser** Actor to parse the data into separate lines.

![image](../images/99_actors_02_1.PNG)



Since **FileRead** Actor can work on one file at a time, in the flow it can be preceded by the file system handling Actors. For example, use the **ls** Actor to list the files in a given directory and then iterate on the files using the **FileRead** Actor.

The **file.flow** example shows how to handle the flow that includes various steps of stream handling such as compressing the data, creating a directory and the files, listing the file and moving them between the directories.

Click **Actions** > **Examples** in the [Main menu](../18_broadway_flow_window.md#main-menu) to open the **file.flow** example. 

![image](../images/99_actors_02_2.PNG)



[![Previous](/articles/images/Previous.png)](01_javascript_actor.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_parsers_actors.md)

