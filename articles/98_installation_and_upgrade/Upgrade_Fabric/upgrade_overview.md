# Upgrade Overview

## Purpose

This document describes the upgrade methodology used by K2view Fabric and provides guidance for planning, validating, and rolling back Fabric upgrades.

Detailed upgrade procedures are documented separately.

## Upgrade Philosophy

Beginning with Fabric 8.x, Fabric upgrades no longer require dedicated database migration scripts.

Instead, Fabric is designed to perform any required internal upgrade activities automatically during startup. This significantly simplifies the upgrade process and reduces the operational effort required to move between supported releases.

In most cases, upgrading Fabric consists of:

1. Reviewing release-specific requirements.
2. Backing up the existing environment.
3. Replacing the Fabric software package.
4. Starting the upgraded Fabric version.
5. Validating system operation.

## Upgrade Scope

The Fabric upgrade process is designed to preserve:

* Project source code
* Runtime configuration
* System databases
* Project artifacts
* External integrations

During the upgrade, only the Fabric runtime binaries are replaced.

## Release Notes

Although the upgrade process is largely standardized, individual releases may introduce specific requirements.

Examples include:

* New configuration parameters
* Removed configuration parameters
* New Fabric modules
* Deprecated capabilities
* Java runtime changes
* Product-specific migration activities

For this reason, the release notes for the target version must always be reviewed before upgrading.

## Recommended Upgrade Process

K2view recommends the following upgrade workflow:

1. Review release notes.
2. Validate prerequisites.
3. Perform backups.
4. Upgrade a non-production environment.
5. Execute validation testing.
6. Upgrade production.
7. Perform post-upgrade validation.

## Validation

Following the upgrade, validate:

* Fabric startup
* Cluster health
* Interfaces and APIs
* Scheduled jobs
* Monitoring and alerting
* Business flows and representative transactions

Each organization should follow its own operational validation procedures in addition to these recommendations.

## Rollback Considerations

A rollback plan should be prepared before any production upgrade.

Rollback procedures typically involve:

* Restoring the previous Fabric installation package
* Restoring modified configuration files
* Reverting release-specific changes if applicable

Release-specific upgrade documentation should be reviewed for any special rollback considerations.

## Related Documentation

* <a href="https://support.k2view.com/knowledge-base.html">Fabric Release Notes</a>
* <a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/upgrade_procedure_linux.md">Fabric Upgrade Procedure (Linux Installations)</a>
* <a href="/articles/98_installation_and_upgrade/Install_on_Linux/README.md">Fabric Installation on Linux</a>

