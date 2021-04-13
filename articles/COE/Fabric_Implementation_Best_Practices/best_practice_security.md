# Security

1. Do not use *Cassandra* or *Cassandra user* or *password* in the studio interface as this is used only internally. 

2. Do not store connection details or any password in translation tables or globals. Use custom Interfaces and environments to store these details and get access to it using **getCustomProperties("InterfaceName).get("Interface property")**.

3. Generate a new admin user\password for production instead of admin\admin which is used for lower environments. 
  
   [![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_iid_finder.md) [<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_broadway.md)

