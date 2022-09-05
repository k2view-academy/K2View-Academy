# Editing Interface Settings

Throughout a project’s lifecycle there are a number of reasons for modifying, disabling or deleting interface settings. For example, to update an IP address or the credentials of a DB you wish to access as a data source.

### How Do I Change an Interface's Connection Settings?
     
1.	Go to **Project Tree** > **Shared Objects** > **Interfaces** to open the **Interfaces list**. 
<studio> 
      
2.	Double click the **interface** to open it.
</studio>
<web>
      
2.	Click on the **interface** to open it.
</web>

3.	Edit the [Connection Settings](/articles/05_DB_interfaces/03_DB_interfaces_overview.md#database-connection-settings).
<studio>
      
4.	Click the **Test Connection String** link to verify that the connection settings are correct:
</studio>
<web>
      
4.	Click the **Test Connection** button to verify that the connection settings are correct:
</web>

5.	
     * If the connection is OK, the **Connection is OK** notification will be displayed on the bottom of the window.
     * If the connection fails, a description of the problem is displayed on the bottom of the window.  
<studio>
      
6.	**Save** the interface.
</studio>
<web>
      
6.	**Save** the interface by selecting **File->Save** or doing **CTRL-S** .
</web>

Note that for the changes to become effective for the Fabric object (for example for Web Services or an LU), deploy each object to the server separately.

[Click for more information about Deployment from the Fabric Studio.](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md#deploy-from-fabric-studio)

[![Previous](/articles/images/Previous.png)](/articles/05_DB_interfaces/04_creating_a_new_database_interface.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/05_DB_interfaces/07_deleting_disabling_an_interface.md)
