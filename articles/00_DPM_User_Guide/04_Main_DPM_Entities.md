```
## Main DPM Entities
```

The DPM configuration is based on the following main entities: 

·     **Regulation** – DPM flexible configuration allows compliance with any current or future regulation, such as GDPR, CCPA, LGPD, or any other. There is no limit on the number of regulations supported. 

·     **Activities** – Under each regulation a set of Activities is defined. Each Activity represents an action that the customers can request as part of the Data Privacy legislation. For example, an Activity may be a request to get the customer data or a request to delete the customer data. There is no limit on the nature or number of Activities that can be configured 

·     **Flow** – a Flow is a set of stages that should be executed in order to fulfill an Activity. Each Activity entity is linked to a Flow that is responsible to execute the process to perform the relevant customer request. A Flow can be used in multiple Activities. For example, if the Activity is “Request My Data,” and it can be executed in the same way ,regardless of which privacy legislation the customer is under then the same flow will be linked to all Activities (whether the legislation is GDPR, CCPA, or any other).

·     **Stage** – A Stage is the building block of a Flow. Each Flow has at least one Stage. The Stages are executed in sequence one after the other.

·     **Task** – Task is the lowest level of action that is defined in the Admin. It typically performs one specific Activity, such as: check request validity, send an e-mail to the customer, or gather customer data. Each Stage includes one or more Tasks. Tasks can be defined to be executed in parallel or in sequence. Once all the Tasks of a specific Stage are completed, then the next Stage will start.

An example of the relation between the above entities is depicted in the following diagram: 

​                           

![](images/Main_DPM_Entities.png)

Figure 1. Main DPM Entities



 [![Previous](/articles/images/Previous.png)](/articles/03_DPM_Overview/03_DPM%20Overview.md)