# K2View Customized Rules



## 4.1	General Rules

**1. Avoid execute sync command when it's not needed**  
  -*This rule detects unneeded usage of sync on/off*

**2. Check if LU sync timeout is not set**  
   -*This rule checks if LU sync timeout is not set (recommended timeout is 60 sec)*

**3. Check the LU recommended sync timeout (60 sec) is being used**  
   -*This rule checks if LU sync timeout is set for more than the recommended time (60 sec)*

**4. It’s highly recommended to use Graphit in the WS**  
  -*This rule detects WS that are not using graphit.*

**5. Avoid accessing the same table multiple times**  
  -*Avoid accessing the same table multiple timesif all queries can be combined into one query.*

**6. Generate CSV file with Fabric data**  
  -*Generate CSV file with Fabric data – When possible use the fabric ‘SET OUTPUT FILE’ command followed by query execution instead of writing a code.*

**7. Complex queries**  
  -*simplify the queries whenever it's possible to avoid performance issues*
  
**8. LUDB Functions**  
  -*This rule checks if LUDB functions parameters are being used inside each function logic.*

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/04_K2View_Customized_Rules/02_Java_Coding.md)

