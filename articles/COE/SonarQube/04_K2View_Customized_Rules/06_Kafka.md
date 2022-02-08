# K2View Customized Rules

## 4.6	Kafka


**1. Kafka Consumer Usage**  
   -*Avoid creating your own threads for Kafka consumers within the code. It is recommended to use the built-in job mechanism and Kafka interface. You can start a job instance for
each partition. Examples can be found in the COE KB.*

  -*Perform “commit” every ‘X’ number of transactions. ‘X’ parameter should be
tuned.*

**2. Kafka Producer Usage**  
   -*Do not create a new KAFKA producer for each message. Create a producer once for a
session and use it multiple times.*

   -*Make sure to close the producer (inside the 'finally' block) before exiting the
session or in case of a failure.*

   -*Close the Kafka producer – producer.close();*

[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_K2View_Customized_Rules/05_LU_and_Tables.md)
