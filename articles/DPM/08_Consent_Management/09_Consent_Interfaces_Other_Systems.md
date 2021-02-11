

## Consent Data Ingestion to/from other systems

The configurable integration and ingestion capabilities of K2View’s platform provide a strong and flexible mechanism for the ingestion of consent data to other systems, be it within or outside the corporate systems. 

Furthermore, K2View’s DPM is built on the Fabric customer data hub platform. This means that the data ingested from any other system is not limited to consent related data but can be enriched with any information related to the customer or user coming from any system in the organization.  It also provides the ability to define any additional condition when deciding what population should be ingested to each system. 

K2View’s platform built-in capabilities powering the DPM functionality include:

- Configurable connectivity layer, enabling connectivity to the majority of systems without the need to code.
- Versatile integration methods, including files, streaming, publish/subscribe, messages queues, APIs, direct database injection or others. 
- Change Data Capture (CDC) that identifies changes made to a configurable list of fields and publishes the change information so that other applications that need to know about such changes are informed.
- Provides the ability to query consent data using JDBC with SQL queries.

Using these capabilities, full flexibility is provided to define how consent data are to be ingested to other systems, for example, to data lakes.  The ingestion can be executed in multiple ways in accordance with the methods required by each system. 

Replication of the consent preferences data to a separate repository for use by third parties can be configured.

The system provides full control on the content of the data that is provided to other systems: The information can be at the level of an individual subscriber, cover full populations, extract subsets of customers or consent categories, depending on configurable criteria. Both detailed data and summary information can be obtained this way.

Using the built-in implementation layer, each project can also include additional logic or filter criteria without the need to change the product itself. This can be used for example to include whitelists, blacklists or other rules that depend on customer profile. The access, extraction or ingestion of this information to other systems follows the same range of options as the overall consent preference data, including APIs, batch, streaming, CDC, files and more.  The format of the extract is configurable and can be adapted to the needs of each consuming/target application.

For example, information on subscribers that opted-out or opted-in can be delivered to the target system using streaming, invoking an API of the target system, exposing an API to be called by the interested system, providing extract files with the information, directly update of the target database and so on.
DPM provides versatile, configurable, two-way integration capabilities. Just as data can be provided to any other system, data can also be loaded into the DPM system from any external source. The data that is loaded into DPM is available in DPM Rest APIs in the same way as data that was generated within the system is presented. 

The integration in both directions is established based on the data format expected by the source or target systems, as the adaptable data model and the configurable layout formatting features provide full flexibility.

Acquiring data back to the corporate data lake is fully supported – the integration method can be based on any of the abovementioned options, and the data layout can be configured to any format the data lake is expecting to receive.

### Acquire and Publish in Real-Time

Using K2View DPM enables you to maintain the consents information obtained, updated and published in real time. Consent preferences can be updated via DPM user interface directly, by calling DPM REST APIs as well as by using methods such as acquiring data based on streaming. All these options allow DPM to be updated in real-time on any update.

In the same manner that customer preference changes are updated in DPM in real-time, also the propagating these changes to any other client application can be configured to be in real-time, regardless of the method used by the subscriber to update the data. 

### Consent Preferences Reminders

DPM can integrate with your corporate customer communication management system to trigger reminders to users to revisit their consent preferences. The triggering can be set to be timebound or in accordance with any other condition based on customer attributes, consent topic attribute or other criteria, depending on the project requirements. Two-way integration is provided, so DPM can acquire policy information that is defined in other systems as part of the communication triggering process. 

As DPM is based on K2View’s Fabric platform, obtaining relevant customer attributes from any corporate systems is a simple configuration. This allows the communication to include any required data for each customer, wherever it may reside. 

By integrating with the corporate communication management system, the reminders would follow the existing controls and policies as required. In the absence of such a customer communication system, DPM enables direct triggering of the reminders in the implementation layer, without changes to the product.
Information about failed and successful SMS delivery attempts can be logged as part of DPM integration with the customer communication system.



[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/08_Consent_Management/08_Consent_Repository_History_Audit.md)

