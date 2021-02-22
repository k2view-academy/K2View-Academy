# TDM GUI Overview

The TDM GUI is a web-based application that helps users to easily control and manage test data. It has two main functions:

- TDM administrative activities, defining TDM Business Entities, environments, roles and permissions.
- TDM copy activities, creating and executing TDM tasks that provide a selected subset of entities or Reference tables to a selected environment.

TDM settings and tasks are saved in the [TDM PostgreSQL DB](/articles/TDM/tdm_architecture/02_tdm_database.md). Each TDM activity created by a user via the TDM GUI updates the TDM DB. 

The TDM GUI uses APIs to connect to the TDM DB to retrieve and update TDM settings and tasks.

## TDM - Operational Modes

TDM can operate in two modes:

- DataFlux mode, which enables the following [Data Flux](/articles/TDM/tdm_overview/02_tdm_glossary.md#data-flux) features:
  - Creating extract tasks.
  - Adding a versioning mode to load tasks. 

- Regular mode, which enables creating regular load tasks without versioning by marking extracted entities with a Task Name and Extraction Timestamp.

The TDM operational mode is set in the **fluxMode** field in the [config.js] file in the TDM GUI server (/usr/local/k2view/TDM/k2vtdmbe location):   

- **fluxMode** = **True** (default). 
- **fluxMode** = **False**.   

## TDM GUI - Login

Connect to the TDM GUI using the following URL: `http://<TDM server>:4001/#/login.`

Populate the username and password. 

Note that since the TDM authenticates users and passwords via LDAP, all users must be defined in the LDAP system. 

## TDM GUI Navigation - General

### TDM Project Navigation Tree

The TDM navigation tree displays different TDM sections on the left of the screen:

![tdm navigation](images/tdm_gui_navigation_pane.png)

                             

To move between sections, click the option in the TDM navigation tree.

Click the Tooltip icon ![tooltip](images/tdm_gui_tooltip_icon.png) in the upper left of the TDM window to display or hide the labels next to the icons in the TDM navigation tree. 

## TDM Breadcrumbs 

The TDM GUI uses breadcrumbs as a graphical control element to aid navigation across user interfaces whereby users can track their position in the TDM screens. For example, to display the details of a given task's execution: 

![breadcrumbs](images/breadcrumbs_example.png)

- Click **Task Execution Summary - load2Roots** to display an executed **load2Roots** task. 
- Click **Tasks** to display all executed tasks.
- Click **Home** to return to the TDM home page.

### TDM Soft Delete

A Delete activity generally changes the status of a selected object to **Inactive** and does not delete the object from the TDM DB. The object remains in the TDM DB and its history can be displayed in the TDM GUI.

Inactive objects cannot be repaired.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_tdm_gui_user_types.md)
