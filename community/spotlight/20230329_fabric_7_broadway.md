# Fabric 7.0 Broadway Enhancements

<img src="images/broadway_7_look.png" style="zoom:80%;" />

Broadway UI has undergone a major facelift, introducing a new up-to-date coloring scheme.

In addition, several new exciting features and actors have been added, some are described below.

#### Broadway command result structure

The Broadway command now enables defining the format of the flow output by setting the new command parameter RESULT_STRUCTURE to either ROW or COLUMN (default). 

Default is configurable via config.ini.

#### Custom editors

Broadway now allows creating custom editors (plug-ins) on a project level. The implementation of your custom editor should be placed in a new folder under the Server/fabric/staticWeb/editors folder, in the index.html file. 

Using the [Actor Editor](https://support.k2view.com/Academy/articles/19_Broadway/28_actor_editor.html), the editor can then be used by any actor, attaching it to the actor’s input argument.

#### Search enhancements

The Broadway Flow SEARCH was enhanced and now any object in a flow can be searched: stage, actor, parameter (input/output) or a schema element (in the yellow segment). 

The Search Results field indicates the number of occurrences found in a flow and allows to navigate between them using the Prev/Next buttons.

#### Copy schema element

Copy a schema element and paste it in another actor's input or output schema.

[Click to read more details in the Fabric_Release_Notes_V7.0.0](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/V7.0/Fabric_Release_Notes_V7.0.0.pdf.html).

