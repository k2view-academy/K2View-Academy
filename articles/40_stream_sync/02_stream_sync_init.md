# Stream Sync Initialization

### Logical Unit Setup for Stream Sync

Below are the steps to initialize the Stream Sync module upon an LU Schema creation:

1. Create a Logical Unit (Data Product) and open its Schema (which is currently empty). 
2. Open the LU Schema's Properties and set the **Enabled** checkbox in the **IID Finder tab** to **true**.
3. Once the tables are added to the Schema, and the Schema is saved, the table populations are created for each LU table. The input parameter **stream_sync_enabled** of the **SourceDBQuery Actor** is automatically set to **true** for all populations where the interface is **not fabric**.

For an existing LU Schema, set the **stream_sync_enabled** parameter manually for each relevant population.

<web>

Note that *Show IID Finder properties* is a Web Studio setting that either shows or hides the **IID Finder tab** in the LU Schema's Properties. 

* When a new project is created, *Show IID Finder properties* is unchecked, thus the **IID Finder tab** in the LU Schema's Properties is hidden. 
* In order to initialize the Stream Sync module, unhide the tab by updating this setting via the [Web Studio's user preferences](/articles/04_fabric_studio/04_user_preferences.md). 

</web>

### IID Finder XML Generation

When the LU Schema is ready, right-click on the Logical Unit name in the Project tree and click the **Generate** **IID Finder XML** menu item. The following will be performed:

* The **IID Finder** folder becomes visible under the selected LU.
* The **IID Finder XML** is generated in the above folder, based on the LU Schema.
* A product built-in **streamSync.flow** is copied into the **Broadway** folder of this LU.

The **streamSync.flow** includes remarks with explanations about what can be modified. The implementor should update this flow by setting the PubSub interface. Additional changes such as adding custom actors can be done if needed. Moreover, the **deploy.flow** of the selected LU should be updated - by modifying the **BroadwayJob** Actor with the **streamSync.flow** flow name.



[![Previous](/articles/images/Previous.png)](01_stream_sync_overview.md)
