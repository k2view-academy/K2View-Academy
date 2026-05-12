# Fabric Credentials Backup

When testing the system, [user credentials setup](/articles/17_fabric_credentials/01_fabric_credentials_overview.md#setting-credentials) may need to be repeated for several reasons, such as environmental cleanup or when creating additional environments. 

To prevent data loss and speed up the setup process, it is recommended to create a Crontab expression that periodically backs up the credentials. The backup script can export the data from the System DB into a text file. If needed, this data can be imported from the text file into the System DB.  

### Example of Exporting Data

~~~bash
echo "COPY k2auth.roles TO '$FABRIC_HOME/k2auth.roles.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
echo "COPY k2auth.credentials TO '$FABRIC_HOME/k2auth.credentials.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
echo "COPY k2auth.permissions TO '$FABRIC_HOME/k2auth.permissions.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
echo "COPY k2auth.user_credentials TO '$FABRIC_HOME/k2auth.user_credentials.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
echo "COPY system_auth.roles TO '$FABRIC_HOME/system_auth.roles.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password] 
~~~



### Example of Importing Data

~~~bash
echo "COPY k2auth.roles FROM '$FABRIC_HOME/k2auth.roles.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
echo "COPY k2auth.credentials FROM '$FABRIC_HOME/k2auth.credentials.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
echo "COPY k2auth.permissions FROM '$FABRIC_HOME/k2auth.permissions.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
echo "COPY k2auth.user_credentials FROM '$FABRIC_HOME/k2auth.user_credentials.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
echo "COPY system_auth.roles FROM '$FABRIC_HOME/system_auth.roles.csv' WITH HEADER = TRUE ;"|cqlsh -u[user] -p[password]
~~~



[![Previous](/articles/images/Previous.png)](02a_fabric_credentials_list_commands.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_fields_level_authorization.md)

