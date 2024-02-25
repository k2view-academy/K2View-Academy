# Stream Sync Initialization

<web>

*Show IID Finder Properties* is a Web Studio setting which either shows or hides the **IID Finder tab** in the LU Schema's Properties. 

* When the new project is created, *Show IID Finder Properties* is unchecked, thus the **IID Finder tab** in the LU Schema's Properties is hidden. 
* In order to initialize the Stream Sync, unhide the tab by updating this setting via the [Web Studio's user preferences](/articles/04_fabric_studio/04_user_preferences.md). 

</web>

Below are the steps to initialize the Stream Sync upon the LU Schema creation:

1. Create a Logical Unit (Data Product) and open its Schema (which is empty at this point.) 
2. Open the LU Schema's Properties and set the **Enabled** checkbox in the **IID Finder tab** to **true**.
3. Once the tables are added to the Schema and it is saved, the table populations are created for each LU table. The input parameter **stream_sync_enabled of SourceDBQuery Actor** is automatically set to **true** for all populations where interface is **not fabric**.

Note that for the existing LU's Schema populations, the **stream_sync_enabled** will have to be set manually on each relevant population.

When the LU Schema is ready, right click on the Logical Unit name in the project tree and click the **Generate** **IID Finder XML** menu item. The following will be performed:

* The **IID Finder** folder becomes visible under the selected LU.
* The **IID Finder XML** is generated in the above folder, based on the LU Schema.
* A product built-in **streamSync.flow** is copied into the **Broadway** folder of this LU.

The **streamSync.flow** includes the remarks with explanations about what can be modified. The implementor should update the flow (by setting the PubSub interface) and do additional changes if needed (e.g. add custom actors). In addition, the **deploy.flow** of the selected LU should be updated - by modifying the **BroadwayJob** Actor with the above flow name.

