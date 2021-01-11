# TDM GUI Overview

The TDM GUI is a web-based application that helps users easily control and administrate a testing data.

The K2view TDM GUI application consists of two main functions:

- TDM Administrative Activities, TDM definitions, setting TDM Business Entities, environments, roles and permissions.

- TDM Copy Activities, creation and execution of TDM tasks that provide a selected subset of entities or Reference tables to the selected environment.

The TDM settings and tasks are kept in the TDM PostgreSQL DB.  Every TDM activity, created by the user via the TDM GUI, updates the TDM DB. 

The TDM GUI connects the [TDM DB](/articles/TDM/tdm_architecture/02_tdm_database.md) using APIs to retrieve and update the TDM settings and tasks.

## TDM - Working Modes

The TDM can work in two modes:

- DataFlux mode: enables the [Data Flux](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux) features:
  - Creating extract tasks
  - Adding a mode of versioning to load tasks 

- Regular mode: enables creating regular load tasks without versioning (marking extracted entities with task name and extract timestamp).

The following parameter of [config.js] file of the TDM GUI server (/usr/local/k2view/TDM/k2vtdmbe location) controls the TDM working mode:

- **fluxMode** – valid values are **true** or **false**.  **Default** value is **true**. 

## TDM GUI - Log In

Connect to TDM GUI by the following URL: `http://<TDM server>:4001/#/login.`

Populate the username and password. 

Note: all users must be defined in the LDAP system. The TDM authenticates the user and password with the LDAP.

## TDM GUI Navigation- General

### TDM Project Pane Tree

The TDM navigation pane displays different the TDM areas on the left side of the screen:

![tdm navigation](images/tdm_gui_navigation_pane.png)

​                                

You can move from one area to another by clicking on the associated option in the pane tree.

Click on the Tooltip icon ![tooltip](images/tdm_gui_tooltip_icon.png) in the upper left side of TDM window to display or hide the labels next to the icons of the TDM pane tree. 

## TDM Breadcrumbs 

The TDM GUI uses breadcrumbs as a graphical control element to aid navigation across the user interfaces. The breadcrumbs allow users to keep track of their location within the TDM screens. For example, view the details of a given task execution: 

![breadcrumbs](images/breadcrumbs_example.png)

You can either click the **Task Execution Summary - load2Roots** to view all task executions of **load2Roots** task, click **Tasks** to view all tasks, or click **Home** to got back to the TDM home page.

### TDM Soft Delete

In most cases, a delete activity does not delete the selected object from TDM DB but changes the status of the selected item to **Inactive**. The object is still kept in the TDM DB and can be viewed in the TDM GUI to enable the view of the history.

Inactive object cannot be repaired.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_gui_user_types.md)