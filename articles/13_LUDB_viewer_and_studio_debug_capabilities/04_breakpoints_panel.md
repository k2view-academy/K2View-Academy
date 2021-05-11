# Breakpoints Panel

Fabric Studio provides an ability to view all active breakpoints. A new panel was added to the **Server / Activity Logs area**, displaying a list of the breakpoints in the Fabric objects (currently only [Broadway flows](/articles/19_Broadway/18_broadway_flow_window.md) are supported). The panel displays the File name (for example, a Broadway flow name) and the ID where the breakpoint was set (for example, the Stage name).

[Click for more information about Broadway](/articles/19_Broadway/01_broadway_overview.md).

The panel is refreshed every 3 sec and only when it is open (visible to the user). The yellow arrow sign on the panel indicates the breakpoint at which the flow has stopped.

Double-click on the breakpoint in the panel brings the related file to the front.

Known limitation: if the breakpoints panel displays several flows with the same name that belong to different [Logical Units](/articles/03_logical_units/01_LU_overview/md), the double-click will open the first flow with such name that it finds (now necessarily the correct one).



[![Previous](/articles/images/Previous.png)](03_debug_table_population.md)