## Custom Lists In Consent Configuration

Another use of the Custom List is for the creation of filters in the consent configuration. 

Note: this section describes only the use of Custom Lists in the context of concent management. For consent configuration overview, access the [Consent Management](/articles/DPM/08_Consent_Management/01_Consent_Management_Overview.md) section of this user guide.

Each consent can be associated with a set of filters so that this consent is presented only for customers that meet the filter criteria. 

As an example, if there is a distinction between the consents that should be presented for a business account and those that should be presented for a consumer account, a custom list with those two values can be created, and associated with the relevant consents. 

The association between consent and the custom list is made in the New/Edit Consent configuration page: 

<img src="../images/Figure_100_Custom_Lists_consent_filter.png" width="80%" height="80%">

When a data subject, such as a customer, opens the Consent list page, the list of consents that is presented only includes those for which this customer meets their filter criteria. 

Since DPM can integrate with any corporate source system, the determination whether or not a customer meets the filter criteria is made at the level of the project implementation and can be based on any aspect of the customer data.

[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/16_1_Custom_Lists_use_in_flow.md)[<p align="right"> Return to main menu</p>](/articles/DPM/README.md)
