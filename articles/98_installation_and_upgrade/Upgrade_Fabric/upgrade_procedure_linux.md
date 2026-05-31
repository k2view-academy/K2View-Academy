# Fabric Upgrade Procedure (Linux Installations)

## Table of Contents

- [Foreword](#foreword)
- [Purpose](#purpose)
- [Prerequisites](#prerequisites)
- [Backup](#backup)
- [Stop Fabric](#stop-fabric)
- [Install the New Fabric Version](#install-the-new-fabric-version)
- [Review Release-Specific Requirements](#review-release-specific-requirements)
- [Start Fabric](#start-fabric)
- [Deploy the Project](#deploy-the-project)
- [Validate the Upgrade](#validate-the-upgrade)
- [Rollback](#rollback)
- [Related Documentation](#related-documentation)

## Purpose

This document provides the standard procedure for upgrading K2view Fabric installations deployed directly on Linux servers.

This procedure applies to Fabric 8.x releases unless otherwise noted in the release-specific upgrade documentation.

## Prerequisites

Before upgrading:

* Review the release notes for the target version.
* Verify Java runtime requirements.
* Verify operating system compatibility.
* Verify project compatibility requirements.
* Prepare a rollback plan.
* Test the upgrade in a non-production environment.

## Backup

Before upgrading, create backups of:

* Fabric configuration files
* Project repositories
* System databases
* External databases as required by organizational procedures
* Custom JAR files and extensions

Example:

~~~bash

cp -r config config_backup_$(date +%Y%m%d)

~~~

## Stop Fabric

Stop Fabric on all nodes.

Example:

~~~bash

k2fabric stop 

~~~

If applicable:

* Stop iidFinder processes.
* Allow in-flight processing to complete.
* Verify no operational activities are still running.

Follow project-specific operational procedures where required.

## Install the New Fabric Version

Backup the existing Fabric installation:

~~~bash

mv fabric fabric_backup 

~~~

Extract the Fabric directory from the new package:

~~~bash

tar -zxvf k2fabric-server-fabric-<version>.tar.gz fabric 

~~~

Do not overwrite the configuration directory unless specifically instructed by the release notes.

## Review Release-Specific Requirements

Review the release notes and any version-specific upgrade documentation.

Examples of additional requirements may include:

* Configuration changes
* New modules
* Deprecated parameters
* Java upgrades
* Product-specific migration activities

## Start Fabric

Start the upgraded Fabric installation.

Example:

~~~bash

k2fabric start

~~~

For multi-node deployments:

1. Start the first node.
2. Verify successful startup.
3. Start the remaining nodes.

Follow project-specific runbooks where applicable.

## Deploy the Project

Deploy the project if required by your organization's deployment procedures.

## Validate the Upgrade

Verify:

* Fabric starts successfully.
* All nodes join the cluster.
* Interfaces are operational.
* Scheduled jobs execute successfully.
* Monitoring systems report a healthy status.

Recommended validation activities include:

* Execute representative GET commands.
* Execute representative business flows.
* Validate external integrations.
* Review startup logs.

## Rollback

If issues are encountered:

1. Stop Fabric.
2. Remove the upgraded Fabric installation.
3. Restore the previous Fabric installation.
4. Restore any modified configuration files.
5. Start Fabric using the previous version.

Example:

~~~bash

k2fabric stop
rm -rf fabric
mv fabric_backup fabric
k2fabric start

~~~

If release-specific upgrade activities were performed, consult the corresponding upgrade documentation before rolling back.


## Related Documentation

* <a href="/articles/98_installation_and_upgrade/Install_on_Linux/README.md">Fabric Installation on Linux</a>
* <a href="https://support.k2view.com/knowledge-base.html">Fabric Release Notes</a>
* <a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/upgrade_overview.md">Upgrade Overview</a>
* <a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/upgrade_procedure_linux.md">Fabric Upgrade Procedure (Linux Installations)</a>

