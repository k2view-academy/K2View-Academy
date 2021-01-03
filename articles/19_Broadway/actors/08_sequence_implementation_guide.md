# Sequence Implementation Guide

Broadway provides a solution for generating and setting new sequences before loading data into Fabric or target database. Various sequence patterns can be implemented  via the **MaskingSequence** Actor and other Broadway features.

This article describes the most useful use cases of sequence implementation by Broadway.

### Sequence Caching

The frequent sequence implementation scenario is when the same sequence needs to be used per entity and execution across several flows. The following use cases of sequence caching are supported:

* Using a sequence across several tables of the same LU. For example, Customer ID is a sequential field in the CUSTOMER LU and it is populated in several LU tables such as CUSTOMER and SUBSCRIBER. 
* Using a sequence across different LUIs within the same LU. For example, the same ADDRESS ID can be used for different customers during the same execution.
* Using a sequence across different LU types. For example, the same CUSTOMER ID can be used in CUSTOMER LU and Billing LU during the same execution.
* Using a sequence across different DCs.

To implement the above use cases, set the unique **maskingId** and populate it on the **MaskingSequence** Actor in every place where the same sequence should be used. Keep the **useExecutionID** as **true** on each Actor's settings.

### Sequence Next Value

The sequence next value method depends on where the sequence is managed. It is determined by the **sequenceInterface** setting. The following use cases are supported:

* In-memory or Redis, useful to testing purpose only since it can be used only by a single node configuration.
* DB sequence. To implement it, set the **maskingId** to the sequence name that is defined in the **sequenceInterface** DB.  

### Sequence Initiation Method

Sequence initiation can be done using the **initialValue** and the **increment** settings of the Actor though it is relevant for in-memory or Redis interfaces only. In case of DB sequence, these attributes are managed by the DB. Note that the initial value is cached on the Actor's first execution. The following use cases are supported:

* Initialize the sequence using the constant initial value, for example 1000000.
* Initialize the sequence using another Broadway flow by setting the flow name in the **initialValue** argument. The Actor invokes the flow to calculate the sequence initial value. Note that the flow must return an external variable named **initialValue**. 

![image](../images/99_actors_08_ex_1.png)

### Sequence Mapping

Broadway supports various ways how the sequence can be mapped. The following use cases are supported:

* Simple mapping from the old to the new value.

  ![image](../images/99_actors_08_ex_4.png)

* Set the sequence as part of the attribute list. To do so, generate the sequence and then create the concatenated attributes list using the **JavaScript** Actor.

  ![image](../images/99_actors_08_ex_2.png) 

* Set sequence in a specific format, e.g. SQ|date(Ym)|BR[value]|[new_seq]. To do so, generate the sequence and then use the **MaskingLuFunction** Actor to mask the input value with the result of LU function execution.

  ![image](../images/99_actors_08_ex_3.png)

  

[![Previous](/articles/images/Previous.png)](07_masking_and_sequence_actors.md)