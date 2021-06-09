# Configure New Consent Topic

Click the <img src="../images/Figure_64a_New_Topic_Icon.png" width="10%" height="10%"> button at the top-right of the screen in order to configure a new consent topic.

 ![image](/articles/DPM/images/Figure_73_Consent_Management_Configuration_New_Topic.png)

The following dialog box displays.

 ![image](/articles/DPM/images/Figure_64_New_Consent_Screen.png)

<table>
<tbody>
<tr>
<td width="103">
<p><strong>Field</strong></p>
</td>
<td width="840">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="103">
<p>Category</p>
</td>
<td width="840">
<p>Categories are used for grouping consent topics. This field lets the user classify the new consent under an existing Category or select <strong>New Category</strong> to manually enter a new one. &nbsp;</p>
</td>
</tr>
<tr>
<td width="103">
 <p>Filter List</p>
</td>
<td width="840">
<p>When a consent topic is relevant only to specific groups of users, a filter can be added so that this consent would be presented only for the relevant groups.</p>
<p>The filter name and the values it holds are defined as a Customer List. To learn about how this configuration is done, visit the Custom List section in this user guide.</p>
</td>
</tr>
 <tr>
<td width="103">
 <p>Filter Items</p>
</td>
<td width="840">
<p>In this field, select the values from the Filter List (defined above) which represent the user groups that this consent topic is relevant for. Multiple values can be selected.</p>
<p> This configuration should be accompanied by the implementation of the logic that allows DPM to identify if a specific user meets the filter criteria. Adding this logic is done via implementation of a filter function at the project level</p>
</td>
</tr>
 <tr>
<td width="103">
<p>Regulations</p>
</td>
<td width="840">
<p>Define the list of Regulations that this consent is linked to by selecting the relevant regulations from the dropdown list. The list of regulations that displays in this dropdown is defined in the Regulation List option on the Administrator menu.</p>
</td>
</tr>
 <tr>
<td width="103">
<p>Duration</p>
</td>
<td width="840">
<p>Define the duration that the consent is considered valid at the customer level. For every customer, the consent takes effect the moment of opting-in, and then for the duration that is specified in this field.</p>
</td>
</tr>
 <tr>
<td width="103">
<p>Mandatory</p>
</td>
<td width="840">
<p>If a consent topic is defined as mandatory, it means that the consent would be presented to the customer as <strong>on</strong> (opt-in) and the customer cannot opt-out from this consent.</p>
</td>
</tr>
<tr>
<td width="103">
<p>Topic</p>
</td>
<td width="840">
<p>The consent topic name.</p>
</td>
</tr>
<tr>
<td width="103">
<p>Description</p>
</td>
<td width="840">
<p>The consent topic description.</p>
</td>
</tr>
<tr>
<td width="103">
<p>Opt In Text</p>
</td>
<td width="840">
<p>An additional description that explains the benefits of opting-in to each consent to the customer.</p>
</td>
</tr>
<tr>
<td width="103">
<p>Opt Out Text</p>
</td>
<td width="840">
<p>An additional description that explains the impacts of opting-out from each consent to the customer.</p>
</td>
</tr>
</tbody>
</table>

Click the <b>Save</b> button to save the new consent. 

When a new consent is configured, it appears to a customer or representative in the Consent List. The customer or representative can then choose to opt-in to the new consent topic. 

[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/08_Consent_Management/03_View_Consent_List.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/08_Consent_Management/05_Obtain_Customer_Consent.md)
