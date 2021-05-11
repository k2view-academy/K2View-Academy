# Breakpoints Panel

Fabric Studio provides an ability to view all active breakpoints. A panel in the **Server / Activity Logs area** displays a list of breakpoints in the open Fabric files (currently only [Broadway flows](/articles/19_Broadway/18_broadway_flow_window.md) are supported). This panel displays the File name (for example, a Broadway flow name) and the ID where the breakpoint was set (for example, the Stage name).

The panel is refreshed every 3 sec, but only when it is open and visible to the user. The yellow arrow sign on the panel indicates the breakpoint at which the flow has stopped.

Double-click on the breakpoint in the panel to bring the related file to the front.

If the breakpoints panel displays several flows with the same name that belong to different [Logical Units](/articles/03_logical_units/01_LU_overview/md), the double-click will open the first of these flows with the same name. This might not be the correct one, so it is recommended to use differing names for flows. 


[![Previous](/articles/images/Previous.png)](03_debug_table_population.md)
