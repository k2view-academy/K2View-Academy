# Sequence Implementation Guide

Broadway provides a solution for generating and setting new sequences before loading data into the Fabric or target database. Various sequence patterns can be implemented  by building Broadway flows using the **MaskingSequence** Actor.

The **maskingId** input argument should be set to the name of the sequence and this sequence must be defined in the **sequenceInterface**. Note that you can should also set the **initialValue** and the **increment** of the sequence. 

For example, when you need to generate an INVOICE_ID using the INV_ID_SEQ_1 sequence defined in CRM_DB Oracle DB, set the **MaskingSequence** Actor's input argument as: **maskingId** to INV_ID_SEQ_1, **sequenceInterface** to CRM_DB, **initialValue** to 1000000 and **increment** to 1.























[![Previous](/articles/images/Previous.png)](07_masking_and_sequence_actors.md)