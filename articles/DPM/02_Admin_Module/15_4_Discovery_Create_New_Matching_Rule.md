## Create a New Matching Rule

The matching rules define what logic is used to detect PII data when scanning your source systems databases. You can add as many new rules as required. 

Click the ![image](/articles/DPM/images/Figure_82_Discovery_NewMatchingRule.png) button at the top-right of the screen in order to configure a new Matching Rule.

![image](/articles/DPM/images/Figure_83_Discovery_MatchingRulesTab_Callout.png)

The following dialog box displays.

![image](/articles/DPM/images/Figure_78_Discovery_NewMatchingRule.png)

<table>
<tbody>
<tr>
<td width="85">
<p><strong>Property</strong></p>
</td>
<td width="785">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td width="85">
<p> Matching  Category</p>
</td>
<td width="785">
    <p>Select a category that is defined within this  option. Select <b>+New Category</b> from the drop-down to create a new category. After selecting <b>+New Category</b> you can type any text in the Category field.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Field Type</p>
</td>
<td width="785">
<p>Field types are used to define the nature of the field you are searching for. For example - First Name, or Bank Account Number. Multiple rules can search for the same field type. Each Rule searches for this field type using a different logic. Select a filed type that is already listed within this option in order to add new rules for existing field types. Select <b>+New Field Type</b> to add rules for a new field type.</p>
</td>
</tr>
<tr>
<td width="85">
<p>Active</p>
</td>
<td width="785">
<p>The “Active” slider is turned to “On” by default. Turn the slider to “Off” if you do not want the  new Matching Rule to be active. You can switch between on and off at any time later on.</p>
</td>
</tr>
<tr>
<td width="85">
<p> Probability (1 – 100%).</p>
</td>
<td width="785">
<p>Set the probability  percentage of the new Matching Rule. This describe the probability of precise discovery that you attribute to the rule you are defining. The higher the probability, the more accurate you consider this rule. For example, when you validate a social  security number, the 9-digit number could be an account number of the same  format. Therefore, the Probability would be lower (ex: 70%).</p>
</td>
</tr>
<tr>
<td width="85">
<p>Matching Type</p>
</td>
<td width="785">
<p>Select the Matching Type.  Choose between Column, Data, Data Function, and Data Sample. The differences between those options are described in the previous page</p>
</td>
</tr>
</tbody>
</table>


### Edit or Delete a Matching Rule

Click the ![image](/articles/DPM/images/Figure_4_3_Delete.png) button to delete a Matching Rule. 

Note: If you click the ![image](/articles/DPM/images/Figure_4_3_Delete.png) button, the system displays a dialog box, prompting “Are you sure you want to delete matching type?” Click the ![image](/articles/DPM/images/ICON_Delete_02.png) button to remove the selected Matching Type from the system.

Click the ![image](/articles/DPM/images/Figure_4_2_Edit.png) button to edit the Matching Category, Field Type, Probability, and Matching Type.


[![Previous](/articles/DPM/images/Previous.png)]( /articles/DPM/02_Admin_Module/15_3_Discovery_Matching_Rules_Overview.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/15_5_Discovery_Interfaces_Overview.md)
