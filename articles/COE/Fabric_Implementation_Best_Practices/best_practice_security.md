# Security

1. dbCassandra Interface - Do not use Cassandra\Cassandra user\password in the studio interface as this is used only internally 

2. Storing Passwords in the Studio – It is not recommended to store connection details or any password in translation tables or globals. Use custom Interfaces and environments to store these details and get access to it using **getCustomProperties("InterfaceName).get("Interface property")**

   ​	Admin user for production - For security reasons, generate a new admin user\password for production instead of admin\admin which is used for lower environments 