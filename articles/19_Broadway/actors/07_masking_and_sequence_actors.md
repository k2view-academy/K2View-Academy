# Masking and Sequence Actors

Data management systems such as **TDM** often deal with sensitive data. To be compliant with Data Privacy laws Fabric provides a **masking** category of Actors that enable masking  sensitive fields, such as SSN, credit card number or email, prior to loading them into the target DB.

For example: 

* The **MaskingSSN** Actor masks the original SSN number with a valid fake SSN.
* The **MaskingCreditCard** Actor generates a fake but valid credit card number similar to the original one.

Note that if it is needed to keep the data masked in Fabric, use the **masking** Actors in the Broadway population flows.

Another important functionality for the systems that need to load data frequently to target DBs is the ability to generate and populate a unique sequence ID.

The **MaskingSequence** Actor which also belongs to the **masking** category generates a unique sequence ID based on the provided input arguments.

<!--[Click for more information about TDM7 implementation]().  TBD !!!-->

### How Do I Set the Masking Input Arguments?

The input arguments of the **masking** Actors are:

* The **maskingId** is a unique masking identifier used to generate a target value. Since the same **masking** Actor can be used in several flows of the same project, use this parameter to refer to the same cache.
* The execution ID is generated in Fabric every flow run. Keep the **useExecutionID** = **true** in order to generate a new masked value in every flow run. When set to **false**, the same masked value is used across different executions.
* The **hashedInputValue** indicates whether to store the original or the hashed value. By default the hashed value is stored. To disable it and store the original value, set the **hashedInputValue** to **false**.
* Set the **interface** input argument to the interface to be used for caching of masked values. Cassandra or in-memory interfaces should be used for this purpose. 
  * When the Cassandra interface is set, the Cassandra **masking_cache** and **uniqueness** tables under the [k2masking keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) are used to cache the masked values and to enable their uniqueness when required. The data kept in the tables reflects the setting of the Actor's input arguments.
* The **verifyUnique** input argument determines whether different input values can be masked to the same masked value. The uniqueness is checked per masking ID and execution ID. Set this parameter to **true** if the masked value should be unique. For example, when masking an SSN, the masked value must be unique.

The following input arguments specific for the **MaskingSequence** Actor are:

* The **sequenceInterface** is where the sequence is defined. The **maskingId** of the **MaskingSequence** Actor holds the sequence name and it should be defined in the **sequenceInterface**.
* The **initialValue** and **increment** define the initial value of the sequence and the value of increment. 

### How Do I Mask Data using  the Masking Actors?

The following example shows how to mask the Address description and the ZIP code using two **masking** Actors in the population flow. 

The same masking can be done in several flows of the same project. For example, if the ZIP code is populated in several LU tables in Fabric, use the same Actor in those flows and specify the same masking ID.

![image](../images/99_actors_07_1.PNG)

### How Do I Use the MaskingSequence Actor?

The purpose of the **MaskingSequence** Actor is to enable the implementation of the sequences solution when creating Broadway flows that load data into a target DB.

The following example shows how to use a **MaskingSequence** Actor to generate a new sequence for customer ID instead of the original one.

![image](../images/99_actors_07_2.PNG)



[![Previous](/articles/images/Previous.png)](06_error_handling_actors.md)
