# Fabric Environments Security

Fabric requires the following environments for each phase of the project lifecycle, from inception to delivery and maintenance. 

## Development Environment
This environment should contain:
- Fabric Server for each developer.
- Windows for installing and running the Fabric Studio.
- SVN / GIT for version control.

Access to this server and SVN / GIT should be limited to the relevant developers.

<img src="/articles/99_fabric_infras/devops/images/01_devop-devEnv.png">

## QA Environment

This environment should be isolated from the Development environment and should contain the following components:

- Fabric servers cluster, **minimum 3 for Testing**
- Windows Terminal Server, for installing and running Fabric Studio.
- Access to Fabric WS (API) via the load balancer (LB).
- Access to SVN/GIT for version control, limited to relevant users. 
- Source databases, including data that should have the same structure as in the Production environment.

Note that all connection methods and protocols must be of the same type as those defined in the Production environment. 
All access to the above-described servers should be limited to the QA team members only.

<img src="/articles/99_fabric_infras/devops/images/02_devop-QAEnv.png">

## Pre-Production Environment
This environment should be isolated from the Development and QA environments and should be identical to the Production environment:

- Fabric servers cluster, identical as possible to the Production environment ***(minimum 3 on each DC)***
- Windows terminal server for installing and running the Fabric Studio. Access to this server should be limited to the relevant Admin team only.
- Access to the Fabric WS (API) via the load balancer (LB).
- Access to SVN/GIT for version control, limited to relevant users.
- Source databases, including data that should have the same structure as in the Production environment.

Note that:
- All connection methods and protocols must be identical to those defined in the Production environment. 
- All access to the servers described above should be limited to the QA team members only.
- The connection method and protocol must match those used in the Production environment. 
- This environment should be sufficiently strong to be able to run load testing. 

<img src="/articles/99_fabric_infras/devops/images/03_devop-preProdEnv.png">

## Production Environment
This environment should be isolated from the Development and QA environments and should be identical to the Pre-Production environment:

- Fabric server cluster, according to the production sizing definitions.
- Windows Terminal Server, for installing and running Fabric Studio. Access to this server should be limited to the relevant Admin team only.
- Access to the Fabric WS (API) via the load balancer (LB).
- Access to SVN/GIT for version control should be limited to the relevant users.

<img src="/articles/99_fabric_infras/devops/images/04_devop-prodEnv.png">


[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/09_fabric_replace_admin_password.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/03_fabric_api_and_ui_hardening.md)
