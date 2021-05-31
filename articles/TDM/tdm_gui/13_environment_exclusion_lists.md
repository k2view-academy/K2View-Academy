# Environment Exclusion Lists Tab

During functional tests there may be a need to lock copied entities to prevent them from being reloaded from a source environment by other users. Entities can be locked by an Admin user or the Environment Owner whereby the entities are added to an exclusion list in the environment.  An exclusion list holds the entities that cannot be deleted or loaded into the environment for a given BE. 

For example an exclusion list can be defined in ENV1 for a Customer BE and be populated by 1,2,3 whereby customers 1, 2, and 3 cannot be copied to ENV1 by the TDM. 

An exclusion list can be added, edited or deleted from the environment by an Admin user or the [Environment Owner](08_environment_window_general_information.md#environment-owners).  

An environment's exclusion lists are displayed in the **Exclusion Lists tab** in the Environment window:

- To add a new exclusion list to the environment, click **Add Exclusion List**, populate the exclusion list's settings and click **Add**.
- To open a selected exclusion list, click **Exclusion List** in the exclusion list and then click **Save Changes**. 
- To delete an exclusion list from the environment, click the [![be_Example](images/delete_icon.png)](/articles/TDM/tdm_gui/images/delete_icon.png) icon in the right corner of the Exclusion List window. 

## Environment Exclusion List Window 

The Exclusion List Window in the Environment Window holds the following settings:

- **Business Entity**,  the entity type of the excluded entities. Select a BE from the dropdown list of BEs. 
- **Requested by**, the User ID of the user requesting to lock the entities. This can be populated by Admin users, Environment Owners or the Testers of an environment. Note that only **one exclusion list per environment, BE and Requested by combination** can be created. If a tester already has an exclusion list and asks to add entities to it, update the existing list.
- **Exclusion list**, a list of excluded entities separated by a comma. 



 [![Previous](/articles/images/Previous.png)](12_environment_globals_tab.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](14_task_overview.md)

