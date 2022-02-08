# K2View Customized Rules

## 4.5	LU and Tables

**1. To validate that a record exists - don't use count(*)**  
   -*To validate that a record exists, select the first row with the required ‘where’ condition (using limit 1 or rownum < 2 – depends on the DB). Do not use count(* * *)* *in the query as this is time 
consuming.*

**2. Table Population on top of the LUDB**  
   -*Do not set it to automatically run every second. This population should be executed only when the source tables are being updated and therefore in most cases should be based on decision function.*

**3. Root functions not using the input field as part of the query or in the function logic**  
   -*This rule checks if root functions parameters are being used inside each function logic.*

[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_K2View_Customized_Rules/04_IIDFinder.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/04_K2View_Customized_Rules/06_Kafka.md)
