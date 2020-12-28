# Masking and Sequence Actors

Data management systems such as **TDM** are often dealing with sensitive data. To be compliant with Data Privacy laws Fabric provides a **masking** category of Actors that enable masking  sensitive fields, such as SSN, credit card number or email. 

For example: 

* The **MaskingSSN** Actor masks the original SSN number with a valid fake SSN.
* The **MaskingCreditCard** Actor generates a fake but valid credit card number similar to the original one.

One of the Actors in this category,  the **MaskingSequence** Actor, enables setting a unique sequence ID when loading the data into the target DB table.

<!--[Click for more information about TDM7 implementation]().  TBD !!!-->

### How Do I Mask Data using  the Masking Actors?

The purpose of the **masking** Actors is to enable the masking of the sensitive data when loading it into a target DB.

The following example shows how to use a **MaskingSSN** Actor in a Broadway flow that selects data from Fabric and loads it into the target DB, for example as part of TDM or a data migration flow. 

* Keep **useExecutionID** input argument as **true** in order to generate a new sequence value in every flow run. When set to **false**, the same sequence value is used for all executions.
* Set the **interface** input argument to the interface that should be used for caching. 

![image](../images/99_actors_07_1.PNG)

### How Do I Use the MaskingSequence Actor?

The purpose of the **MaskingSequence** Actor is to enable the implementation of the sequences solution when creating Broadway flows that load data into a target DB.























[![Previous](/articles/images/Previous.png)](06_error_handling_actors.md)
