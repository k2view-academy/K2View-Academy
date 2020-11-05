# Environments Overview

Environment is a list of project's active [DB and non-DB Interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md) with specific connection details (server, port, etc.) defined per specific needs. For example, ENV1 can be defined for UAT purpose and ENV2 - for PROD.

Using the environments, Fabric enables the implementer to define several source environments for the same project and to switch between them in the same Fabric cluster. 

Explicit definition of the environments in a project is optional. When no environment is defined and a [Logical Unit](/articles/03_logical_units/01_LU_overview.md) is deployed to a Fabric server, all the project's interfaces are deployed with their connection details to a default **_dev** environment. The default environment is not shown in the Fabric Studio neither in the Environments XML configuration file.

Different users can work in different environments at the same time, but the performed changes might affect the entire data in Fabric.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_create_new_environment.md)



