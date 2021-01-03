# Masking and Sequence Actors

Data Management Systems like **TDM** often handle sensitive data. To be compliant with Data Privacy laws, Fabric provides a **masking** category of Actors that can mask sensitive fields like SSN, credit card numbers and email addresses before they are loaded into the target DB.

For example: 

* The **MaskingSSN** Actor masks the original SSN number with a valid fake SSN.
* The **MaskingCreditCard** Actor generates a fake but valid credit card number similar to the original one.

Note that if data needs to be masked before it is loaded to Fabric, **Masking** Actors can be used in Broadway population flows.

Another important functionality for systems that need to frequently load data to target DBs is the ability to generate and populate a unique sequence ID.

The **MaskingSequence** Actor, which also belongs to the **masking** category, generates a unique sequence ID based on the provided input arguments.

<!--[Click for more information about TDM7 implementation]().  TBD !!!-->

### How Do I Set Masking Input Arguments?

The common input arguments of the **masking** category of Actors are:

* **maskingId**, a unique masking identifier used to generate a target value. Since the same Actor can be used in several flows of the same project, use this parameter to refer to the same cache.
* **useExecutionID**, indication whether to use the Execution ID during the flow run whereby the Execution ID is a unique string generated each time the flow is running. Keep the setting as **true** to generate a new masked value in each execution. When set to **false**, the same masked value is used across different executions.
* **hashedInputValue**, indication whether to store the original or the hashed value. By default the hashed value is stored. Set to **false** when it is required to disable caching and store the original value.
* **interface**, set this input argument to the interface to be used to cache the masked values. Cassandra or in-memory interfaces should be used for this purpose. 
  * When the Cassandra interface is set, the Cassandra **masking_cache** and **uniqueness** tables under the [k2masking keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) are used to cache the masked values and to enable their uniqueness when required. The data kept in these tables reflects the setting in the Actor's input arguments.
* **verifyUnique**, determines whether different input values can be masked to the same masked value. The uniqueness is checked per **maskingId** and Execution ID. Set this parameter to **true** if the masked value should be unique. For example, when masking an SSN, the masked value must be unique.

The following input arguments are specific to the **MaskingSequence** Actor:

* **sequenceInterface**, the interface where the sequence is defined. The **MaskingSequence** Actor's **maskingId** setting holds the sequence name and must be defined in the **sequenceInterface** setting.
* **initialValue** and **increment**, define the initial value of the sequence and the value of the increment. 

### How Do I Mask Data using Masking Actors?

The following example shows how to mask an Address description and  ZIP Code using two Actors of the **masking** category in the population flow. 

The same masking can be implemented in several flows of the same project. For example, if the ZIP Code is populated in several LU tables in Fabric, use the same Actor in the flows and specify the same Masking ID.

![image](../images/99_actors_07_1.PNG)

### How Do I Use the MaskingSequence Actor?

The purpose of the **MaskingSequence** Actor is to enable the implementation of a sequence's solution when creating Broadway flows that load data into a target DB.

The following example shows how to use a **MaskingSequence** Actor to generate a new sequence for a Customer ID instead of the original one.

![image](../images/99_actors_07_2.PNG)



[![Previous](/articles/images/Previous.png)](06_error_handling_actors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_sequence_implementation_guide.md)s
