

## Consent Data Ingestion to/from Other Systems

The configurable integration and ingestion capabilities of K2View’s platform provide a strong and flexible mechanism for consent data ingestion to other systems, both inside and outside the corporate systems. 

K2View’s DPM is built on the fabric customer data hub platform; therefore, data ingested from any other system is not limited to consent-related data. It can be enriched with any information related to the customer/user from any system within the organization.  The platform also provides the ability to define any additional condition when deciding what population should be ingested to each system. 

The K2View platform powering the DPM functionality includes the following built-in capabilities.

- A configurable connectivity layer that enables connectivity to the majority of systems without the need to code, and is easily adaptable for adding connectivity to any additional system.
- Versatile integration methods that include files, streaming, publish/subscribe, message queues, APIs, and direct database injection. 
- Change Data Capture (CDC) that identifies changes made to a configurable list of fields. It then publishes the change information to inform other applications about the changes as necessary.
- The ability to query consent data using JDBC with SQL queries.

Using these capabilities, full flexibility is provided to define how consent data are ingested into other systems (example: into data lakes.)  The ingestion can be executed in multiple ways in accordance with the methods required by each system. 

Replication of the consent preferences data to a separate repository for use by third parties can be configured.

The system provides full control of the data content provided to other systems. The information can be at the individual user level, cover full populations, and extract subsets of customers or consent categories depending on configurable criteria. Both detailed data and summary information can be obtained this way.

Using the built-in implementation layer, each project can include additional logic or filter criteria without changing the product itself. The information can be based on any rule, or it can depend on a customer profile. The access, extraction, or ingestion of this information to other systems follows the same range of options as the overall consent preference data, including APIs, batches, streaming, CDC, and files.  The extraction format is configurable and adaptable to the needs of each consuming/target application.

For example, information on subscribers that opted-out or opted-in can be delivered to the target system using streaming, invoking an API of the target system, exposing an API to be called by the interested system, providing extract files with the information, and directly updating the target database.

DPM provides versatile, configurable, two-way integration capabilities. Just as data can be provided to any other system, data can also be loaded into the DPM system from any external source. The data that is loaded into DPM is available in DPM Rest APIs in the same way as system-generated data is presented. 

The integration in both directions is based on the data format expected by the source or target systems, because the adaptable data model and the configurable layout formatting features provide full flexibility.

Acquiring data back to the corporate data lake is fully supported. The integration method can be based on any of the previously explained options, and the data layout can be configured to any format the data lake expects to receive.

### Acquire and Publish in Real-Time

Using K2View DPM allows you to maintain the consents information obtained, updated and published in real-time. Consent preferences can be updated directly through the DPM user interface, by calling DPM REST APIs, and through methods such as acquiring data based on streaming. These options allow DPM to be updated in real-time on any update.

In the same manner that customer preference changes are updated in DPM, changes to other client applications are configurable in real-time, regardless of the method used by the subscriber to update the data. 

### Consent Preferences Reminders

DPM can integrate with your corporate customer communication management system to trigger reminders to users to revisit their consent preferences. Reminder triggering can be timebound or in accordance with other conditions based on customer attributes, consent topic attributes, or other criteria, depending upon the project requirements. Two-way integration is provided, so DPM can acquire policy information defined in other systems as part of the communication triggering process. 

DPM is based on K2View’s Fabric platform, so obtaining relevant customer attributes from any corporate systems is a simple configuration. This lets the communication include any required data for each customer, wherever it resides. 

By integrating with the corporate communication management system, the reminders follow the existing controls and policies as required. In the absence of such a customer communication system, DPM enables direct reminder triggering in the implementation layer, without changes to the product.

Information about failed and successful SMS delivery attempts can be logged as part of DPM integration with the customer communication system.

[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/08_Consent_Management/08_Consent_Repository_History_Audit.md)

