# Masking and Sequence Actors

Data Management Systems, like **TDM**, often handle sensitive data. To be compliant with Data Protection and Privacy laws, Fabric provides a **masking** category of Actors that can mask sensitive fields like SSN, credit card numbers, and email addresses before they are loaded into a target DB.

The masking process contains the generation (manufacturing) of a random synthetic value that replaces the real value, and the caching of both the hashed original value and the masked value in order to keep the referential integrity of the data. Starting from V7.1, Fabric separates data generation (manufacturing) of synthetic data from the hashing and caching capabilities. Broadway provides the following Actors:

1. various data generation Actors, under the **generators** category, to generate a random synthetic value; e.g., RandomString, RandomNumber, and Sequence.
2. **Masking** - this Actor can wrap any data generation Actor and add the hashing and caching capabilities on top of the data generation Actor.
3. Broadway still keeps the existing masking Actors for backward compatibility reasons; e.g., **MaskingSSN** and **MaskingCreditCard**. 

Note that if there is a need to mask data before they are loaded to Fabric, masking Actors can be used in Broadway population flows.

Another important functionality for systems that need to frequently load data to target DBs is the ability to generate and populate a unique sequence ID: the **MaskingSequence** and the **Sequence** Actors generate a unique sequence ID based on the provided input arguments.

[Click for more information about the data generation Actors](07a_data_generators_actors.md).

[Click for more information about TDM](/articles/TDM/tdm_overview/01_tdm_overview.md). 

### How Do I Set Masking Input Arguments?

Common input arguments of masking Actors are:

* **maskingId** - a unique masking identifier, used for generating a target value; populated by a String. To use the same masking Actor in different flows of the same project, use this parameter to refer to the same masking cache. By default, the masking's specific ID is used across different DCs.
* **flowName** - the name of the flow or Actor to be executed in order to obtain a masked value. This parameter has been added to the **Masking** Actor - for enabling the execution of the data generation flow or Actor - which generates a fake value.
  
* **formatter** - this optional input has been added in Fabric 8.0 in order to support a [format preserving masking](/articles/26_fabric_security/06_data_masking.md#format-preserving-masking).  This parameter can be set with either a formatter flow or an Actor in order to **preserve the original format in the masked value** and to set the same masked values to all fields that have the same normalized (’naked‘) value, although they have a different format. Fabric offers a [SimpleMaskingFormat ] Actor, but the implementor can define a custom flow or an Actor to format the masked value based on the original format. 
  
* **category** - this parameter has been added by Fabric 6.5.3 and it indicates *when* the masking Actor needs to generate a new value, e.g., when masking sensitive data or replacing the ID (sequence). The following values can be set in the category:
  
  - **enable_sequences**, which generates a new ID value
  - **enable_masking**, which masks sensitive data
  - Any custom string value 
  
   By default, the category is set to **enable_masking** on all masking Actors except for the **MaskingSequence** Actor, in which case the default category is set to **enable_sequences**.
  
   The masking Actor inspects the **value of the session level key, set in the category**:
  
   - If the related session-level key **is not set**, or is set to **true** - it generates a new value.    
   - Else, if the related session-level key is set to **false** - it returns the original value.
  
   Note that TDM implementation sets the **enable_masking** and **enable_sequences** session-level keys to either **true** or **false**, based on the TDM task's attributes. For example, the **MaskingSequence** Actor generates a new ID value when the task replaces the sequences of the copied entities, or else, the original ID value is returned. 
  
* **useEnvironment** - indicates whether to separate the masked value per environment. When set to **true**, it generates a new masked value in each environment. When set to **false**, the same masked value is used across all environments. 
* **useExecutionId** - indicates whether to use the Execution ID during the flow run whereby the Execution ID is a unique string generated each time the flow is run. When set to **true**, it generates a new masked value in each execution. When set to **false**, the same masked value is used across different executions.
* **useInstanceId** - indicates whether to use the instance ID as part of the masking cache. If it is set to **true**, the instance ID is added to the masking cache. When set to **false**, the masked value is used across entities. Note that from Fabric 7.1 onwards, if this parameter is set to **true**, Fabric gets the instance ID value from the root_iid key, if it is set. If the root_iid key is not set, it gets the current LUI instance. The root_iid key enables the maintenance of the referential integrity on PII fields across different LUs that logically belong to each other. For example, CRM and Billing LUs keep the Customer's data. The customer name needs to be identical in both LUs for a given customer. Setting the root_iid with the customer ID enables keeping the referential integrity between the CRM and Billing LUs.
* **hashedInputValue** - indicates whether to store the original or the hashed input value. By default, the hashed value is stored. When set to **false**, it disables the caching and stores the original value.
* **interface** - the interface to be used to cache the masked values. This interface may be either any SQL DB interface defined in Fabric or the Fabric server memory. 
  * When the SQL DB interface is set, the **masking_cache** table under the [k2masking keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) is used to cache the masked values. The data kept in this table reflect the settings of the Actor's input arguments.
  * If the **k2masking** keyspace does not exist, create it by using either the **masking-create-cache-table.flow** example flow. 
  * IN-MEMORY interface is useful for testing as it can only be used in a single node configuration.
  * Set the interface to **NONE** in order to avoid caching the masked values.
* **verifyUnique** - determines whether different input values can be masked with the same masked value. The uniqueness is checked per **original value** (masked value) and **maskingId**. The uniqueness is also checked per environment where the useEnvironment is set to true, and per execution id where the useExecutionId is set to true. Set this parameter to **true** if the masked value should be unique, as in the case of masking an SSN.
  Notes:
    * Set the **useExecutionId** to **false**, the **useEnvironment** to **true**, and the **verifyUnique** to **true** in order to get unique masked values on a given field per environment for all executions.
    * If an interface is **IN-MEMORY**, uniqueness is checked only on a single node and not across a DC or a Cluster.


* **TTL** - Time to Live. This is the time in seconds to keep the masked values in the caching tables. The default value is 86400 seconds (24 hours). If this parameter is set to 0, the masked value will not be deleted from the cache table. Note that until Fabric 8, the TTL was supported only when creating the k2masking keyspace in Cassandra DB or when populating the interface parameter with IN-MEMORY value. Fabric 8 has added the support of a TTL also when the k2masking tables are created in Fabric system PostgreSQL DB, based on the new expiration date field added to the caching tables. 

* **onEmpty** - determines what to do with the input value when it is either an empty string or NULL:

  * **LEAVE_EMPTY** - return the input value as is.
  * **MASK_NO_CACHE** - mask an empty value but don't cache it.
  * **MASK_AND_CACHE** - mask an empty value and cache it.

 Note: The **MaskingSequence** Actor has specific arguments. Click [here](08_sequence_implementation_guide.md#sequence-next-value) for more information.

The below are specific input arguments for the **MaskingSequence** Actor:

* **sequenceInterface** and **sequenceId** - the **sequenceInterface** is populated with the interface name. The **sequenceId** is populated with the sequence name defined in the sequence interface. If the sequenceId is empty, the sequence name is taken from the **maskingId** input argument. The sequence next value implementation method depends on the sequence definition set by the **sequenceInterface** input argument. [Click for more information about Sequence Next Value](08_sequence_implementation_guide.md#sequence-next-value).
* **initialValue** and **increment** - define the initial value of the sequence and the value of the increment. 



### Formatter Actors and Flows

#### SimpleMaskingFormat

Fabric 8.0 includes this Actor to run a simple formatting logic. This Actor works in 2 modes:

- **Normalize** - normalizes the original value to get a normalized (’naked‘) value. It removes from the original value all the characters that are in the **formatDeny** (backlist) and/or that are not in the **formatAllow** (white list)  parameters.

- **Format** - adds to normalized ('naked') value the formatting characters for building an output value that has the same format as the input’s original value.

In order to build the formatted output, it adds to the normal value all the characters from the original value that are identified as format characters, i.e., that are included that are in the formatDeny and/or that are not in the formatAllow parameters.

 In case the formatAllow is empty, any character is allowed, except for the characters specified in the formatDeny.

 At least one the following inputs - formatAllow and formatDeny - must be populated in order to format or normalize the input value. If both inputs are empty, the formatter Actor/flow will do nothing. 

 If the normal value is longer than the original value, the remaining characters will be added to the output value.

 **Input Arguments:**

- **formatterMode**: indicates if the formatter needs to normalize or format the input’s original value. Valid values: *Normalize*, *Format*. Initial value: *Normalize*.
- **originalValue**: populated with the original (source or generated) value. The originalValue is needed for both modes.

- **normalValue**: populated with the masked normalized (‘naked‘) value. This input is needed if the formatterMode is *Format*.
- **formatAllow**: indicates which characters can be included in the normalized value.  The formatAllow can be populated with either Numeric, Alpha, Alphanumeric, White-Space, or Custom values. When a Custom value is set, the implementor can populate a String with a list of characters that can be included in the normalized value. 
    - Example: the formatAllow is Numeric. Therefore,  all non-numeric characters need to be removed from the originalValue in order to get the normalized value. If the OriginalValue = 12-542-99 and the formatAllow = Numeric, the normalized value 1254299.
- **formatDeny**: indicates which characters must be excluded from the originalValue in order to get the normalized value. The formatDeny can be populated with either Numeric, Alpha, Alphanumeric, White-Space, or Custom values. When a Custom value is set, the implementor can populate a String with a list of characters that must be excluded from the normalized value.
    - Example: if the formatDeny is '-', all the dashes need to be removed from the originalValue in order to get the normalized value. If the OriginalValue = 12A-542B-99 and the formatDeny = '-', the normalized value would be 12A542B99.

#### Custom Formatting Logic

You can define either a flow or an Actor to set a custom formatting logic. The formatter flow/Actor must contain the following parameters:

**Input parameters:**

- **formatterMode**: indicates if the formatter needs to normalize or format the input’s original value. Valid values: *Normalize*, *Format*. Initial value: *Normalize*.
- **originalValue**: populated with the original (source or generated) value. The originalValue is needed for both modes.

- **normalValue**: populated with the masked normalized (‘naked‘) value. This input is needed if the formatterMode is *Format*.

**Output parameters:**

- **value**: populated with the normalized or formatted value. Depends on the formatterMode. 

Notes:
- If you build a formatter Actor, it is recommended to inherit the Actor from the **AbstractMaskingFormat** Actor to get the required  arguments.
- The mandatory input and output parameters must be set as external parameters in the formatter flow.

### How Do I Mask Data Using Masking Actors?

The following example shows how to mask an Address description and a ZIP Code using 2 masking Actors in the population flow. 

The same masking rule can be implemented in several flows of the same project. For example, if the ZIP Code is populated in several LU tables in Fabric, use the same Actor in the flows and specify the same Masking ID.

![image](../images/99_actors_07_1.PNG)

### How Do I Use the MaskingSequence Actor?

The purpose of the **MaskingSequence** Actor is to enable the implementation of a sequence's solution when creating Broadway flows that load data into a target DB.

The following example shows how to use a **MaskingSequence** Actor to generate a new sequence for a Customer ID instead of the original value:

![image](../images/99_actors_07_2.PNG)

[Click here for more information about the sequence Actors](08_sequence_implementation_guide.md).


[![Previous](/articles/images/Previous.png)](06_error_handling_actors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](07a_data_generators_actors.md)
