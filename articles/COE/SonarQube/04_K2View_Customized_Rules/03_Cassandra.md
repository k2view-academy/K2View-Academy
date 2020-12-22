# K2View Customized Rules

## 4.3	Cassandra

**1.** Check defined Cassandra interface user/pass

   -*This rule checks if cassandra\cassandra user\password are being used in the studio*

   -*cassandra\cassandra must not be used as user\password in the studio interface as this is used only internally*

**2.** Check host defined in Cassandra interfaces
  
   -*This rule checks if hosts are well defined in Cassandra interface*
      - Do not use localhost as host
      - Make sure to set all Cassandra hosts in the cluster in the interface host in order to allow high availability 

**3.** Avoid allow filtering since it will trigger a full table scan

   -*This rule detects executing read transactions on Cassandra using allow filtering*



[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/04_K2View_Customized_Rules/02_Java_Coding.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/04_K2View_Customized_Rules/04_IIDFinder.md)

