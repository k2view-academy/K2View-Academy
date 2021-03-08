# Customer Consent Management

Each customer can set consent preferences by accessing the Consent Management menu option from the Customer menu.

 ![image](/articles/DPM/images/Figure_65_Consent_Management_At_Customer_Level.png)

When accessing this menu option, the customer can review the list of consents, define whether to opt-in or opt-out to each one, or view historical information about opting-in or opting-out of previously performed activities.

 ![image](/articles/DPM/images/Figure_66_Customer_Consent_Management_Screen.png)

In this screen:

<table style="width: 696px;">
<tbody>
<tr>
<td style="width: 106px;" width="103">
<p><strong>Field</strong></p>
</td>
<td style="width: 590px;" width="600">
<p><strong>Description</strong></p>
</td>
</tr>
<tr>
<td style="width: 106px;" width="103">
<p>TOPIC</p>
</td>
<td style="width: 590px;" width="600">
<p>The consent topic name.</p>
</td>
</tr>
<tr>
<td style="width: 106px;" width="103">
<p>DESCRIPTION</p>
</td>
<td style="width: 590px;" width="600">
<p>The consent topic description.</p>
</td>
</tr>
<tr>
<td style="width: 106px;" width="103">
<p>DURATION</p>
</td>
<td style="width: 590px;" width="600">
<p>The duration that the consent is considered valid. For every customer, the consent will be considered effective from the moment of opting-in, for the duration that is specified in this field.</p>
</td>
</tr>
<tr>
<td style="width: 106px;" width="103">
<p>EFFECTIVE DATE</p>
</td>
<td style="width: 590px;" width="425">
<p>The date the customer opted-in for this consent topic.</p>
</td>
</tr>
<tr>
<td style="width: 106px;" width="103">
<p>EXPIRATION DATE</p>
</td>
<td style="width: 590px;" width="600">
<p>The date the consent expires. This date is automatically calculated by adding the <strong>EFFECTIVE DATE</strong> to the number of months in the <strong>DURATION</strong> field.</p>
</td>
</tr>
<tr>
<td style="width: 106px;" width="103">
<p>ACTION</p>
</td>
<td style="width: 590px;" width="600">
<p>The <strong>On</strong>/<strong>Off</strong> slider allows the customer to opt-in or opt-out from the consent.</p>
<p><strong>Note</strong>: The default value for this field is <strong>Off</strong> (opt-out.)</p>
</td>
</tr>
</tbody>
</table>

When a user changes consent topic preferences (example: from opt-in to opt-out), a dialog box displays to confirm the selection. This dialog box includes the opt-in or opt-out text defined in the consent configuration. 

## Consent History

Any change that the customer performed in the consent preferences (example: opt-in or opt-out) is registered in the system for audit purposes. The change can be viewed in the <b>Consents History</b> tab.

 ![image](/articles/DPM/images/Figure_67_Consent_History.png)



[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/08_Consent_Management/05_Obtain_Customer_Consent.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/08_Consent_Management/07_CSR_Consent_User_Interface.md)

