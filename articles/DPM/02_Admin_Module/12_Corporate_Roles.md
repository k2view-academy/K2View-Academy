## Corporate Roles

### Overview

Corporate Roles represent the roles defined by the company. Those roles represent the different groups that take part in the DPM processes. Corporate roles are fully configurable, and each corporate defines them according to its own organizational structure. 
Corporate roles are configured by the DPM Administrator by accessing the Role Management menu option.

 ![image](images/Figure_23_Role_Management_in_menu.png)

The “Corporate Roles” screen is displayed. It presents the list of the corporate role in the screen center. 
When selecting one of the corporate roles, the right side of the screen displays the DPM Application Roles that are enabled for this Corporate Role. 
For example – take a user that belongs to the “Legal Fulfilment” group and is responsible to work on Tasks as part of the fulfilment process. Performing a Task is defined as part of the Steward Application Roles. So, for the user to be able to perform a Task, the DPM Administrator should:

1. Create a Corporate Role to represent the Legal team, called “Legal” in the below example
2. Mark that this Role can perform the actions that are grouped under the “Steward” DPM Application Role (on the right side of the screen).
3. Assign the user to the corporate Role “Legal” (user management is detailed in the next section).

 ![image](images/Figure_24_Corporate_Role_Management.png)

If a more detailed authorization definition is required, the Administrator can expand the options that appear under the selected Application Role, and specify with more details what are the actions that the users from this corporate role can perform:

 ![image](images/Figure_25_Detailed_Authorization_Configuration.png)

In the screen example above, the Corporate Role called “Marketing” was assigned the “Steward” Application Role but will not be allowed to add a note to a Task or Release Ownership of a Task.
Each Corporate Role can have multiple Application Roles enabled. This means that the users assigned to this Corporate Role will be able to perform actions or view information that were defined as part of all of the enabled Application Roles. 
The main menu on the left side of the screen is constructed based on the list of Application Roles that are enabled for the Corporate Roles the user belongs to. You can find more details about the menu options that are presented to each user in the DPM Main Menu chapter below. 

### Add a new Corporate Role

To create a new Corporate Role, use the   ![image](images/Figure_26_a_create_new_role_icon.png) option at the top of the Role Management screen.
The “New Corporate Role” screen is presented:

 ![image](images/Figure_26_New_Corporate_Role.png)

<table>
<tbody>
<tr>
<td width="85">
<p><strong>Property</strong></p>
</td>
<td width="30">
<p><strong>M/O</strong></p>
</td>
<td width="785">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="85">
<p>Role Name</p>
</td>
<td width="30">
<p>M</p>
</td>
<td width="785">
<p>The Corporate Role name.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Role Description</p>
</td>
<td width="30">
<p>O</p>
</td>
<td width="785">
<p>The Corporate Role description.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Email</p>
</td>
<td width="30">
<p>O</p>
</td>
<td width="785">
<p>The mail or distribution list for the corporate role. This email address is used when a Task reminder is being configured and the user selects the corporate role as the reminder target mail. &nbsp;</p>
</td>
</tr>
</tbody>
</table>

Once the user completed and saved the new Corporate Role, the new Role appears in the list of roles at the center of the screen. 

### Mapping Configuration Role to Application Role

The next step after creating a Corporate Role is to define the mapping to GDPR Application Roles assigned to it, and under each Application Role, what screens and actions this Corporate Role can view or perform. 
In order to perform this configuration:

1.	Select the Corporate Role line on the central part of the screen. As a result, the right side of the screen will present a tree of options that allows the mapping to the application roles this group should have
2.	Define the configuration by ticking or unticking the check-boxes options on this tree. 
3.	Save the configuration using the Save button at the bottom of the screen.

 ![image](images/Figure_27_Corporate_Role_Permissions_Configuration.png)

In the example above, the “Backend” Corporate Role is selected from the list of Corporate Roles. The right side of the screen presents the mapping of this “Backend” Corporate Role to the list of Application Roles it can use. 
At the highest level (first level of the tree), the checkboxes of “Representative” and “Steward” are ticked. This means that the “Backend” Corporate Role is configured to have the functionality of Representative and of Steward. A user that belongs to the “Backend” Corporate Role will have only those two options on the main menu on the left side of the screen (more about that in the Main Menu chapter). 
As you can see, the tree of permissions has multiple levels, which allows controlling the Activities a user can or cannot perform. For example, at the screen above, the option “Submit a new Request” under the “Representative” branch was unticked. This means that though the user will have the option to use Representative functionality such as View the request list, or view specific request, he will not be able to submit a new request.  

### Edit or Delete a Corporate Role

Use the  ![image](images/Figure_27_a_delete_icon.png)  button to delete a Corporate Role. The deletion of the Role will remove it from all the users that has it assigned to them. 

Use the  ![image](images/Figure_27_b_edit_icon.png) button to edit the Email address of a Corporate Role.  



[![Previous](/articles/images/Previous.png)](/articles/DPM/DPM_User_Guide/02_Admin_Module/11_DPM_Roles.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/DPM/DPM_User_Guide/02_Admin_Module/13_User_Management.md)