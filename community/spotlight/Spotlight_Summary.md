<!--block-->

<img src="images/soft_deploy_1.PNG" style="zoom:80%;" />

### Soft Deploy

Soft Deploy is Fabric's ability to exclude automatic processes from the LU deployment. 

Soft Deploy is mostly useful for implementers working in a development environment and frequently changing their code, such as Broadway flows or Java functions. They can use the Soft Deploy option to deploy their changes without triggering automatic processes.

[Read more >](20210722_soft_deploy.md)

<!--block-->

<img src="images/image_manip_1.PNG" style="zoom:80%;" />

### Image Manipulation Actors

Fabric provides a set of built-in image manipulation Actors that allow to perform various activities, such as:

- Load an image into a flow.
- Write text on a given image.
- Clone an image in memory.

Let's check how it works by reviewing the example flow. 

[Read more >](20210701_image_manipulation_actors.md)

<!--block-->

<img src="images/breakpoints_1.PNG" style="zoom:80%;" />

### Fabric Studio Breakpoints

Fabric Studio provides an ability to view all active breakpoints. Check out a new panel that was added to the “Server / Activity Logs” area, which displays a list of the breakpoints in the open Fabric objects (currently only Broadway flows are supported), including the File name (flow name) and the ID (e.g. stage where the breakpoint is set).

[Read more >](20210630_fabric_studio_breakpoints.md)

<!--block-->

<img src="images/actor_editor_1.PNG" style="zoom:80%;" />

### Broadway Actor's Editor

Broadway offers a number of built-in Actors which address a wide range of predefined activities that can be added to Broadway flows. When your implementation requires business logic that is not supported by a built-in Actor and it is repeated across several flows, you can create your own Actor using an **Actor Editor**.

[Read more >](20210527_actor_editor.md)

<!--block-->
