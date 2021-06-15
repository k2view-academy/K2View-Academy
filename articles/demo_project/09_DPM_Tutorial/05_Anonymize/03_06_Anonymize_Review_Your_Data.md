## Review Anonymized Data in the Source Systems

When the status bar reaches the end and turns fully green, the status changes to **Complete**. A status marked **Complete** means that data relating to the Customer is now anonymized in the source systems that are integrated with the DPM. For this tutorial, access the two source systems we set up for simulation purposes. 

[Access Source Systems](../00_Setup/00_Access_Source_Systems.md) provides details on the access to the tutorial "source systems".

For the purpose of the demonstration in this tutorial, we only anonymized the customer name, to allow you still search for it in the source systems using the original e-mail. In the real world implementation, the fields that are anonymized would cover all of the PII data, and are configured to perform the anonymization in accordance with the policies of each company.   

Note that the DPM preserves the referential integrity between the two source systems - the same anonymized name is used for both systems. 

[![Previous](../images/Previous.png)]( 03_05_Anonymize_Ensure_Request_Marked_Complete.md)[<p align="right"> Return to Tutorial topics</p>](../DPM_Application_Tutorial.md#data-subject-requests)
