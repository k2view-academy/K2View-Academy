# Fabric CDC and Search Certification Exam

<img src="/academy/images/Quiz.png" style="zoom:80%;" /> 

Excellent! You have completed the CDC Training and exercises. It's now time to take the following Certification Exam to see what you have learnt. 

The exam consists of a number of multiple-choice questions, each providing a number of possible answers. Click the answer you think is correct. 



#### Question 1: CDC Overview

How is CDC used in Fabric?


\- A:  To publish Fabric changes externally.


\- B:  To get data changes from source systems and to sync Fabric data accordingly.

\- C:  To feed data changes to the Search Loader.

\- D:  A and C are correct.

(**Solution 1. D**).



#### Question 2: CDC Messages

CDC messages:


\- A:  Update CDC consumers in synchronous mode.


\- B:  Are published to Kafka and update CDC consumers in asynchronous mode.

\- C:  Are only published when a new LUI is created in Fabric.

\- D:  None of the above.


(**Solution 2. B: CDC Messages are published to Kafka. Each CDC consumer has their own topic. CDC consumers can consume CDC messages from Kafka**).



#### Question 3: CDC Messages

Which events trigger the publication of a CDC Schema message?  

\- A:  Running a CDC_REPUBLISH_SCHEMA Fabric command on a selected LU.


\- B:  First deployment of an LU with CDC fields.

\- C:  First get of an LUI into Fabric.

\- D:   A and B are correct.


(**Solution 3. D**).



#### Question 4: CDC Messages

To avoid publishing CDC messages on a session level you can either:


\- A:  Remove the CDC fields from the LU and redeploy the LU.

\- B:  Update the config.ini file and set the CDC_PUBLISH_MODE parameter to OFF.

\- C:  Run the SET CDC_PUBLISH= false command.

\- D:  All of the above.


(**Solution 4. C: Option A removes all CDC fields of a given LU and option B blocks the publication of CDC messages on all Fabric sessions**).



#### Question 5: CDC Consumers

Which CDC consumers can get CDC messages from Fabric? 


\- A:  Only Elasticsearch can get CDC messages from Fabric.


\- B:  Only Broadway can get CDC messages from Fabric.

\- C:  Each CDC consumer defined in the k2Proj file of the Fabric project that has CDC fields can get CDC messages.

\- D:  None of the above.


(**Solution 5. C**).



#### Question 6: CDC Consumers

Assuming there are three CDC Consumers in your project, how many Kafka topics are created for CDC messages? 


\- A:  Three topics, a separate Kafka topic for each CDC consumer.


\- B:  One topic for all CDC consumers.

\- C:  A separate topic for each LU with CDC fields. 

\- D:  None of the above.


(**Solution 6. A**).



#### Question 7: Implementation Changes

What happens when you define LU table columns as CDC fields and redeploy the updated LU to Fabric? 

\- A:  Fabric publishes a CDC Update Schema message to CDC consumers, but you must re-migrate all LUIs to publish the data of the new CDC fields to the CDC consumers.

\- B:  Nothing happens. You must drop and redeploy the LU to publish the updated CDC fields to the CDC consumers.

\- C:  Fabric publishes a CDC Update Schema message to the CDC consumers and initiates a batch process running the CDC_REPUBLISH_INSTANCE command on each Fabric LUI to publish the CDC data of the new CDC fields to the CDC consumers.

\- D:  None of the above.

(**Solution 7. A**).



#### Question 8: Implementation Changes

You have added a new LU table with CDC fields to the LU. How do you update the data of the new LU table in CDC consumers?


\- A:  The deployment of the updated LU publishes an update message to the CDC consumers. No additional activity is needed.


\- B:  You must drop and redeploy the LU.

\- C:  You need to deploy the updated LU and then re-migrate all LUIs.

\- D:  You need to manually run a batch process to run CDC_REPUBLISH_INSTANCE on all the LUIs.


(**Solution 8. C: Re-migrate all LUIs to bring the data from the new LU table into Fabric. The migration updates the MicroDB and publishes a CDC Table Change Info message on each LUI to the CDC consumers**).



#### Question 9: Drop LU

What happens when you drop an LU with CDC fields? 

\- A:  The LU is dropped from Fabric. However, no notification is sent to the CDC consumers. The CDC consumers must be cleaned manually.

\- B:  Fabric publishes a CDC Schema Update message to the CDC consumers when the LU is dropped.  The "updateType" property is set to "REMOVED".

\- C:  You cannot drop an LU with CDC fields. You must remove the CDC fields, redeploy the LU, and only then drop it.

\- D:  None of the above.

(**Solution 9. B**).



#### Question 10: CDC Republish Instance

Which CDC messages does the CDC_REPUBLISH_INSTANCE command publish? 

\- A:  The CDC_REPUBLISH_INSTANCE publishes both CDC Delete Tables and CDC Table Change Info messages to recreate the LUI data in the CDC consumer.

\- B:   The CDC_REPUBLISH_INSTANCE publishes a Change Info message to update the LUI data in the CDC consumer.

\- C:  By default, the CDC_REPUBLISH_INSTANCE publishes both CDC Delete Tables and CDC Table Change Info messages to recreate the LUI data in the CDC consumer. However, if the TRUNCATE parameter of this command is set to False, the CDC Delete Tables message is not published.

\- D:  None of the above.

(**Solution 10. C**).



#### Question 11: Rollbacked Transaction

A transaction which updates several LU tables on a given LUI, fails and is rollbacked. Is a CDC message published for this transaction?

\- A:  The CDC message is not published for a rollbacked transaction.

\- B:  Any update in the MicroDB triggers a CDC message even if the transaction is rollbacked. The CDC messages must be cleaned from Kafka manually.

\- C:  The publication of CDC messages on rollbacked transactions depends on the Fabric configuration (config.ini file).

\- D:  None of the above.

(**Solution 11. A**).



#### Question 12: Broadway as a CDC Consumer

How does Broadway consume CDC messages?  

\- A: Broadway does not consume CDC messages.

\- B: Broadway only consumes a Search topic. 

\- C:  Add a CDC consumer to the k2proj file, define CDC fields for this consumer, and create a Broadway flow to subscribe the topic of this CDC consumer.

\- D:  None of the above.

(**Solution 12. C**).



#### Question 13: Search

How do you add Search fields to an LU?  

\- A: Add a CDC  consumer to the k2proj file and define CDC fields for this consumer.

\- B: Create a Search interface and add Search fields on the LU tables. 

\- C: Add a CDC  consumer to the k2proj file, define CDC fields for this consumer, and manually create the indexes in Elasticsearch.

\- D:  None of the above.

(**Solution 13. B**).



#### Question 14: Search

You need to search instances using their Full Name field. Which Search field type do you create on the Full Name field?

\- A: keyword.

\- B: data. 

\- C: date.

\- D:  None of the above.

(**Solution 14. A**).



#### Question 15: Search

How do you run a cross-instance search in Elasticsearch? 

\- A: Cross-instance searches are not supported. You can only search records of a given instance (LUI).

\- B: Run a query on Elasticsearch using an API invoker like Postman.

\- C: Run the Fabric Search command. 

\- D:  B and C are correct.

(**Solution 15. D**).



#### Question 16: Search

The search does not return a customer that exists in the source system. Why?

\- A: The customer does not exist in Fabric.

\- B: The Search fields of the Customer have not been updated in Elasticsearch.

\- C: Missing Search fields in LU table.

\- D:  A and B are correct.

(**Solution 16. D: Option C cannot be correct since in missing implementations, the search does not return all customers**).



#### Question 17: Search

The search does not return a customer using the ID number. Why?

\- A: The customers stored in Fabric do match the searched value.

\- B: There are no customers in Fabric. 

\- C: The ID number is defined as a data Search field.

\- D:  All of the above.

(**Solution 17. D: a data Search field is returned by the Search command and you cannot run a search using a data field**).



#### Question 18: Search

The customer's name and ID number are stored in the Customer source table and the customer's address is stored in the Address source table. How do you create the Customer LU to enable a search using a combination of the Name, ID number and Address fields? 

\- A: Follow the source structure and run the Search command on both the Customer and Address LU tables.

\- B: Create one unified LU table with all the Search fields. The Search command needs to run on this LU table.

\- C: Searching using a combination of fields from different tables is not supported. You need to run different searches on each table and search for the intersections of customers using your implementation code.

\- D:  Create separate LUs for the Customer and the Address data and run a Search on both LUs.

(**Solution 18. B**).



#### Question 19: Search

I searched for customers whose birth date is in Apr and the search failed. Why? 

\- A: The Search field type of the Birth Date field is not set to date.

\- B: The format of the birth date as populated in Fabric is not supported by Elasticsearch.

\- C: The query set in the Search command is incorrect.

\- D:  All of the above.

(**Solution 19. D**).



#### Question 20: Search

How many LUs can be included in one search?

\- A: The number of LUs that can be included in one search is unlimited.

\- B: The number of LUs that can be included in one search is unlimited if all the LUs have the LU table name, included in the search. For example, all LUs have an Address LU table and the search is performed on Address.

\- C: The Search command supports only one LU.

\- D:  None of the above.

(**Solution 20. C**).



[![img](/articles/images/Previous.png)](22a_broadway_summary_exercise_solution.md) 
