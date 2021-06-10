# DPM APPLICATION TUTORIAL 

## Introduction

We invite you to acquire a firsthand experience of our DPM application by following this step-by-step tutorial. 

This tutorial will take you through the main activities of implementing and using our DPM application, such as implementing a Data Subject Access Request (DSAR) Flow, defining Consents, executing PII Discovery, and other DPM features. 

## Tutorial Project Presentation

In order to demonstrate the implementation of our DPM application, we created an environment that simulates a real-world company with multiple source systems that should be integrated with the DPM platform. To represent those source systems, the sandbox we generated for you includes two separate applications, each holding different information about the company customers. 

The two applications that represent the source systems in this tutorial were selected for the purpose of demonstrating concepts. In a real-world implementation, you will of course connect to whichever and as many source applications your company uses. 


### Meet the Tutorial “Source Systems”

The two systems that we picked up to simulate source systems are:
<table>
<tbody>
<tr>
<td width="100"><img src="images/00_odoo.png" alt=""/></td>
<td width="800">
<p>Odoo is an open source ERP and CRM application. You can view more details about this application at their Website: https://www.odoo.com/.</p>
</td>
</tr>
<tr>
<td width="100"><img src="images/00_opensourcebilling_icon.png" width="120%" height="120%" alt=""/></td>
<td width="800"> 
<p>Open Source Billing (OSB), as its name indicates, is an open source billing system. Access its Website https://opensourcebilling.org/ for more information.</p>
</td>
</tr>
</tbody>
</table>

When we set up the tutorial environment, we automatically generate for you not only a running instance of each of those applications, but we also generate in each of those systems an imaginary customer using the name an e-mail address you provided when initiating the tutorial. 

This imaginary customer will be used throughout the tutorial to demonstrate DPM features and functionality, such as customer matching between applications, fulfilment of data access requests, keeping up with data change scenarios, and more.  

### Accessing and Using the "Source Systems"

As part of our tutorial, we will walk you through demonstration of how K2View's DPM automates Data Subject Requests (DSR) of different types, such as Data Subject Access Request, data rectification, data anonymization and purging. 

For that purpose, as part of the different demo scenarios you will be requested to access the "Source Systems" in order to confirm that the activities were indeed executed as expected. 

For example: When a DSAR is completed we will request you to verify that the data collected by the DPM are indeed "your data" as it is reflected in the source systems, or that when a request for data anonymization was completed, "your data" were anonymized at the "source systems".  

The following link includes the instructions to login and navigate in those two "source systems". We will direct you to this link in each point in the tutorial where you are asked to view or use the "source systems".

[Login and use of the "source environments"](00_Setup/00_Access_Source_Systems.md)

# Tutorial Scenarios

Follow the links below to access the step-by-step instruction for the generation of your dedicated sandbox environment

## Data Subject Requests

The first section of our tutorial guides you through the steps of implementing the automation and streamlining the fulfilment process of data access requests.

- [**Data Subject Access Request (DSAR)**]( 01_DSAR/01_00_DSAR.md) - By demonstrating how to create a DSAR Request, we will lead you through the implementation steps that allow you to define the Flow and automation of any type of Data Subject Request that Data Privacy regulations may require. 

- [**DSAR Fulfillment Process**]( 02_DSAR_Fulfillment/02_00_DSAR_Fulfillment_intro.md) - In this tutorial, you will simulate the scenario of a customer who submitted a DSAR, and the Flow this Request goes through from submission to fulfillment. 

- [**Auto Sync of Source System Changes**]( 03_Auto_Sync/01_Auto_Sync_Data_Main.md) - This tutorial demonstrates the DPM seamless synchronization with the source systems by simulating a data change at one of the source systems, and  then submitting a DSAR Request for the customer.
- **[Data Rectification]( 04_Rectify/01_Rectify_Data_Main.md)** - The steps in this section simulate the scenario of a customer submitting a Request to rectify its data. As a result, the DPM application automatically updates the data in all relevant source systems and confirm when the request fulfilment is completed.
- [**Anonymize Data**]( 05_Masking/01_Masking_Data_Main.md) - This tutorial will simulate a customer Request to anonymize their data in all source systems, and follow this Request to its completion.
- **[Purging Data]( 06_Purging/01_Purging_Data_Main.md)** -  In this tutorial, you will simulate a customer Request to delete their data in all source systems, and follow this Request to its completion.

## Personally Identifiable Information (PII) Discovery

The DPM PII discovery feature provides you with a powerful tool to identify the location where personal data is stored in your organization's systems, and then take the appropriate measures to protect it.

- [**PII Discovery**]( 07_Discovery/01_Discovery_Main.md) - In this guided tutorial, we take you through the steps of defining and executing the PII discovery process.

## Customer Consent Management

Managing your customer's preferences and consents is easy with DPM Consent Management capabilities.

- [**Consent Management**]( 08_Consent/01_Consent_Main.md) - This interactive tutorial demonstrates how to define any consent topic and the way the DPM system manages the consent preferences of each customer. 

