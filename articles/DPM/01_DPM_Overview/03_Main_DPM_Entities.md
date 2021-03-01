# Main DPM Entities

The DPM configuration is based on the following entities.

- <i>Regulation</i> – DPM flexible configuration allows compliance with any current or future regulation, such as GDPR, CCPA, or LGPD. There is no limit on the number of supported regulations.
- <i>Activities</i> – A set of Activities defines each regulation. Each Activity represents an action related to Data Privacy legislation that can be requested by a customer. For example, an Activity can be a request to provide the customer with data, or a request to delete the customer data. There is no limit on the nature or number of configurable Activities.
- <i>Flow</i> – A Flow is a series of stages executed in their particular order to fulfill an Activity. Each Activity is linked to a Flow that executes the process of performing the  specific customer request. A Flow can be used in multiple Activities. For example, if the Activity “Request My Data” is supported under both CCPA and GDPR Regulation, but can be executed similarly—regardless of which privacy legislation applies—then the same flow will be linked to this Activitiy under both regulations.
- <i>Stage</i> – A Stage is the building block for a Flow. Each Flow has at least one Stage. Stages are executed in sequence to fulfill customer data requests. 
- <i>Task</i> – Each Stage is composed of one or more Tasks. A Task is the lowest level component defined in the Admin by the Administrator. It typically performs one specific Activity, such as: check request validity, send an e-mail to the customer, or gather customer data. Tasks are definable to be executed in parallel or in sequence. Once all the Tasks of a specific Stage are completed, then the next Stage begins.

An example of the relation between the above entities is depicted in the following diagram.

​                

![Flowchart](/articles/DPM/images/entities1.png)





[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/01_DPM_Overview/02_DPM_Overview.md)
