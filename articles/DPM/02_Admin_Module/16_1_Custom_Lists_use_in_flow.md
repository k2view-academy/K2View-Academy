## Custom Lists In Flow Configuration

Use the custom list when configuring Data Subject Access Requests (DSAR) Flow parameters that should be presented to the user as a list of options to chose from.

This configuration can be done in any of the tabs which permit definition of input parameters in the Tasks configuration: 

- **Operations** - when a dropdown is required when collecting customer input for automatic tasks 
- **Additional Info** - when a dropdown is required when collecting customer input for manual tasks 
- **Steward Inputs** - when a dropdown is required when a task is being handled by a data steward

To configure the custom list, In any of the abovementioned Task tabs, choose "Custom List" as the Input Type, as in the following example:

<img src="../images/Figure_100_Custom_Lists_configuration.png" width="100%" height="100%">

As a result, you can select what is the custom list that should be used and define what is the label that should be presented to the user.

In the example below we have configured a Task to use a Custom List in two places -  Additional Info and Steward Inputs:

- **Additional Info:** To allow the customer to define a preferred contact method as part of the inputs in the Additional Info tab,  choose "Input" as the Field Type, then "Custom List" from the Input Type dropdown menu:

  <img src="../images/Figure_100_Custom_Lists_additional_info.png" width="100%" height="100%"> 

- **Steward Inputs:** To allow the data steward to specify the Request rejection reason when handling a request:<img src="../images/Figure_100_Custom_Lists_in_steward_input.png" width="100%" height="100%">

The results of the two configurations above is that when a customer submits a Request that uses this Flow, the Contact method will appear as a dropdown in the Request submission screen:

<img src="../images/Figure_100_Custom_Lists_customer_request.png" width="100%" height="100%">

In addition, when the data steward handles the Task as part of the Request fulfillment, the information that was selected by the customer appears in the Additional Info section, and the option to select a rejection reason appears at the Steward Inputs section. Note that Rejection Reasons can be configured when the custom list itself is configured:

 <img src="../images/Figure_100_Custom_Lists_steward_screen.png" width="100%" height="100%">



[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/16_Custom_Lists.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/16_1_Custom_Lists_use_in_consent.md)
