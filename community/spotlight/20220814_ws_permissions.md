# WS Elevated and Additional Permissions

Fabric Web Services have two properties related to user role permissions:

* Elevated Permission - indicates if user permissions should be elevated to the Web Service.
* Additional Permissions - allows to the Web Service to activate certain methods which are prohibited to the user's role permissions. Applicable when Elevated Permission is set to False.

For example, when the user role has no READ and WRITE permissions, the user will not be able to read from Fabric and write into Fabric. However when Elevated  Permission is set to True on a Web Service, all restrictions are dismissed. 

When an Elevated Permission is set to False, the developer might still wish that the Web Service will be able to activate certain methods, an act which is prohibited according to his role permissions. 

For example, when the user role has no READ and WRITE permissions and an Elevated Permission is set to False, you can provide these permission to a Web Service using the Additional Permissions.

At the Web Service Properties pane, open the **Additional Permissions** and choose the methods that should be permitted, using the checkboxes.

<img src="images/swagger_0.PNG"  />

Then execute the Web Service. Note that the permissions should be granted carefully.