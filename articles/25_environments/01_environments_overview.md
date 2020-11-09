# Environments Overview

Environments, a Fabric feature, can be used to run the same project implementation on various data sources by switching between them according to specific needs. For example, defining environments for development, UAT testing and production. Or in the K2View TDM, switching between several different testing environments. 

An environment is defined by a partial or a full list of project's [interfaces](/articles/05_DB_interfaces/01_interfaces_overview.md) together with their connection details (server, port, etc.) and must be deployed to the server. Only one environment can become active at a specific time that is selected from a predefined list of environments and switched as needed. 

The environment's deployment is performed either from the [Fabric Studio](/articles/25_environments/03_deploy_env_from_Fabric_Studio.md) or from the Fabric server (also known as an [Offline Environment Deployment](/articles/25_environments/04_offline_deployment.md)). Offline environment deployment can be implemented by a CI/CD process that copies the environment's configuration XML file to the predefined server location and deploys it using a Fabric command. 

Explicit definition of the environments in a project is optional. When no environment is defined and a [Logical Unit](/articles/03_logical_units/01_LU_overview.md) is deployed to a Fabric server, all the project's interfaces are deployed with their connection details to a default *_dev* environment. The default environment is not displayed in the Fabric Studio or in the environment's configuration XML file.

An environment can be defined on a cluster level and on a session level. Therefore a user can override an active environment per session and work on it without interacting with other changes. Setting and resetting an active environment per cluster or per session is performed using [one of the SET environment commands](05_set_and_list_commands).



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_create_new_environment.md)



