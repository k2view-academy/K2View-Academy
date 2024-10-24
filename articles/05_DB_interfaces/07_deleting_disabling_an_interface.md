# Deleting / Disabling an Interface


## Deleting a Database Interface
A database interface can be deleted from Fabric when it becomes irrelevant.

### How Do I Delete an Interface?

1.	Go to **Project Tree** > **Shared Objects** > **Interfaces** to open the **Interfaces list**.
<studio>
 
3.	Right click the **Interface** and click **Delete Selected Items**.
</studio>
<web>

3.	Right click the **Interface** and click **Delete**.
</web>
 

Note that for the changes to become effective for the Fabric object (for example for Web Services or an LU), deploy each object to the server separately.
 
[Click for more information about Deployment from the Fabric Studio.](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md#deploy-from-fabric-studio)

## Disabling a Database Interface

A database interface can be disabled temporarily. When needed, the interface can be enabled again. This can be useful for example, when working in a test environment or when specific parts of a project are irrelevant or deprecated. 

### How Do I Disable an Interface?
1.	Go to **Project Tree** > **Shared Objects** > **Interfaces** to open the **Interfaces list**. 
2.	<studio>Double click the **interface** and then change **Active** to **False**. An interface can be enabled or disabled when needed.</studio>
<web>Click on the **interface** and then change the Active toggle button from  **ON** to **OFF**.</web>
3.	**Save** the interface.

Note that for the changes to become effective for the Fabric object (for example for Web Services or an LU), deploy each object to the server separately.

[Click for more information about Deployment from the Fabric Studio.](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md#deploy-from-fabric-studio)

[![Previous](/articles/images/Previous.png)](/articles/05_DB_interfaces/06_editing_interface_settings.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/05_DB_interfaces/09_fabric_API_for_DB_interfaces.md)
