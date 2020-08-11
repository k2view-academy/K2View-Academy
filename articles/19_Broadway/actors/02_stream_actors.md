# Stream Actors

Broadway provides a group of [built-in Actors](../04_built_in_actor_types.md) to handle the streams, such as reading from a file or writing into a file, compressing or uncompressing the data. These Actors belong to a **streams** category.

Use the **FileRead** Actor to read the data from a file in the provided location and return the collection of blobs. Note that the **path** input argument expects to receive the relative path and the file name. 

When Broadway is reading from a stream or a file, it is done in blocks with constant size of 8K, in order to prevent loading too much data into the memory. Due to a constant size of the data blocks, the data can be cut in the middle of the object (e.g. in the middle of a line in the file), thus the stream handling must be followed by a [parser Actor](03_parsers_actors.md). The following example shows a **FileRead** Actor which is reading from a CSV file and it followed by a **CsvParser** Actor.

![image](../images/99_actors_01_1.PNG)



The **file.flow** example shows how to handle the flow that includes the steps of compressing the data, creating a directory and the files, moving the files between the directories, etc.

Click **Actions** > **Examples** in the [Main menu](../18_broadway_flow_window.md#main-menu) to open the **file.flow** example. 

![image](../images/99_07_01.PNG)



[![Previous](/articles/images/Previous.png)](01_javascript_actor.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_parsers_actors.md)

