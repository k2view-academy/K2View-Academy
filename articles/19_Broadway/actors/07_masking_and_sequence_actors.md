# Masking and Sequence Actors

Broadway has a **masking** category of Actors that enable masking of sensitive information, such as SSN, credit card number or email, and also enable setting sequences.

For example: 

* The **MaskingSSN** Actor masks the original SSN number with a valid fake SSN.
* The **MaskingCreditCard** Actor generates a fake but valid credit card number similar to the original one.
* The **MaskingSequence** Actor implements a unique sequence number.

The **masking** category Actors are mainly used in TDM implementation. <!-- TBD: add link to TDM !!!-->

### How Do I Use the MaskingSequence Actor?

The purpose of the **MaskingSequence** Actor is to enable the implementation of the sequences solution when creating Broadway flows that load data into a target DB.























[![Previous](/articles/images/Previous.png)](06_error_handling_actors.md)