# Roles management

## Roles management Overview

The DPM is a role-based application. Each user is associated with one or more roles. The role determines the Activities the user can perform in the system.
The roles are structured in two layers: 

- DPM Application Roles – each application role includes a set of DPM functionality that a user can perform.
- Corporate Roles – Configurable roles defined by the corporate to represent the corporate organizational structure for the DPM users. Each user that should take part in one of the DPM Activities, should be first assigned to a Corporate role. 

For a user to be able to perform a specific Activity in the DPM, the user should be assigned to a Corporate Role that has enabled the Application Role where this Activity is included. 

 ![image](/articles/DPM/images/Figure_22_User_Roles.png)

Upon login, the system identifies the user’s corporate role and the DPM Application Role associated with it. The user will get access only to the functionality allowed for his role.
The Admin module allows the DPM Administrator to define the relation between the users, corporate defined roles and the functions they assume in the DPM system. 



[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/09_Activities.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/11_DPM_Roles.md)
