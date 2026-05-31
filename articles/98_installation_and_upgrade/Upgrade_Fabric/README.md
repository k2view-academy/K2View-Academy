# Upgrade Fabric

This section provides guidance for upgrading K2view Fabric installations.

Beginning with Fabric 8.x, Fabric upgrades have been significantly simplified. In most cases, upgrading Fabric no longer requires dedicated upgrade scripts or manual database migration procedures. Instead, Fabric automatically performs any required internal upgrade activities during startup.

As a result, most upgrades follow a common process that includes preparation, backup, software replacement, validation, and, if necessary, rollback. The generic upgrade methodology and procedures are documented in the topics below.

Before upgrading, always review the release notes for the target version. While the generic procedure applies to most Fabric 8.x upgrades, individual releases may introduce release-specific requirements, such as configuration changes, new modules, deprecated features, Java runtime updates, or additional migration steps.

The Upgrade Overview topic provides an overview of the Fabric upgrade methodology, upgrade principles, prerequisites, validation activities, and rollback considerations.

The Fabric Upgrade Procedure (Linux Installations) topic provides the standard step-by-step procedure for upgrading Fabric installations deployed directly on Linux servers. Version-Specific upgrade procedures links follow. 

<ul>    
<li><a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/upgrade_overview.md"> Upgrade  Overview</a></li>
<li><a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/upgrade_procedure_linux.md"> Fabric Upgrade Procedure (Linux Installations)</a></li>
<li><a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/Fabric_Upgrade_Procedure_To_V8.3.pdf"> Upgrade  to V8.3</a></li>
<li><a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/Fabric_Upgrade_Procedure_To_V8.2.pdf"> Upgrade  to V8.2</a></li>
<li><a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/Fabric_Upgrade_Procedure_To_V8.1.pdf"> Upgrade  to V8.1</a></li>
<li><a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/Fabric_Upgrade_Procedure_To_V8.0.pdf"> Upgrade  to V8.0</a></li>
<li><a href="/articles/98_installation_and_upgrade/Upgrade_Fabric/Fabric_Upgrade_Procedure_To_V7.2.pdf"> Upgrade  to V7.2</a></li>
</ul>
