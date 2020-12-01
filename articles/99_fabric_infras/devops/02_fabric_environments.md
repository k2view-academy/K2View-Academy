# Fabric Environments

Fabric requires the following environments for each phase in the lifecycle of the project from its inception to its delivery and maintenance. 

## Development Environment
This environment should contain:
- Fabric server for each developer.
- Windows for installing and running the Fabric Studio.
- SVN / GIT for version control.

Note that to avoid uncontrolled access to external sources or to confidential information in another project, access to this server and to SVN / GIT should be limited to the relevant developers.

<img src="/articles/99_fabric_infras/devops/images/01_devop-devEnv.png">

## QA Environment

This environment should be isolated from the Development environment and contain the following components:

- Fabric servers cluster, **minimum 3 for check ......**
- Windows terminal server, for installing and running the Fabric Studio.
- Access to Fabric WS (API) via the load balancer (LB).
- Access to SVN / GIT for version control, limited to the relevant users. 
- Source databases, including data that should have the same structure as in the Production environment.

Note that all connection methods and protocols must be the same type as those defined in the Production environment. 
All access to servers described above should be limited to the QA team only.

<img src="/articles/99_fabric_infras/devops/images/02_devop-QAEnv.png">

## Pre-Production Environment
This environment should be isolated from the Development and QA environments and be identical to the Production environment:

- Fabric servers cluster, identical as possible to the Production environment ***(minimum 3 on each DC).....***
- Windows terminal server for installing and running the Fabric Studio. Access to this server should be limited to the relevant Admin team only.
- Access to the Fabric WS (API) via the load balancer (LB).
- Access to SVN / GIT for version control, limited to the relevant users.
- Source databases, including data that should have the same structure as in the Production environment.

Note that:
- All connection methods and protocols must be identical to those defined in the Production environment. 
- All access to servers described above should be limited to the QA team only.
- The connection method and protocol must be the same as those in Production. 
- This environment should be sufficiently strong to be able to run load testing. 

<img src="/articles/99_fabric_infras/devops/images/03_devop-preProdEnv.png">

## Production Environment
This environment should be isolated from the Development and QA environments and be identical to the Pre-Production environment:

- Fabric servers cluster, according to the production sizing definitions.
- Windows terminal server, for installing and running the Fabric Studio. Access to this server should be limited to the relevant Admin team only.
- Access to the Fabric WS (API) via the load balancer (LB).
- Access to SVNv/vGIT for version control, access should be limited to the relevant users.

<img src="/articles/99_fabric_infras/devops/images/04_devop-prodEnv.png">



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/01_fabric_security_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/03_fabric_and_cassandra_hardening.md)
