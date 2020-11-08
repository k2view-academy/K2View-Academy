# Environments Overview

Environments is a Fabric feature that enables a user to run the same project implementation on various data sources by switching between them per specific needs. For example, you can define environments for development, UAT testing and production. Or in case of K2View TDM, you can switch between several different testing environments. 

Environment is defined by a partial or a full list of project's [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md) with their connection details (server, port, etc.) and must be deployed to the server. Only one environment can become active on a certain time selected from a pre-defined list of environments and switched as needed. 

The environment's deployment is performed either from the [Fabric Studio](/articles/25_environments/03_deploy_env_from_Fabric_Studio.md) or from the Fabric server (also known as an [Offline Environment Deployment](/articles/25_environments/04_offline_deployment.md)). Offline environment deployment can be done by CI/CD process that will copy the environments configuration XML file to the predefined server location and deploy it using a Fabric command. 

Explicit definition of the environments in a project is optional. When no environment is defined and a [Logical Unit](/articles/03_logical_units/01_LU_overview.md) is deployed to a Fabric server, all the project's interfaces are deployed with their connection details to a default **_dev** environment. The default environment is not shown in the Fabric Studio neither in the environments configuration XML file.

Different users can work in different environments at the same time, but the performed changes might affect the entire data in Fabric.



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_create_new_environment.md)



