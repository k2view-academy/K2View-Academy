# K2View Customized Rules

## 4.6	Kafka


**1. Kafka Consumer!**  
   -*Avoid creating your own threads for Kafka consumers within the code. It is recommended to use the built-in job mechanism and Kafka interface. You can start a job instance for
each partition. Examples can be found in the COE KB.*

  -*Perform “commit” every ‘X’ number of transactions. ‘X’ parameter should be
tuned.*



[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_K2View_Customized_Rules/05_LU_and_Tables.md)