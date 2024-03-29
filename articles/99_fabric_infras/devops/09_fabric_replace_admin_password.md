# Replace Default Admin Password of Fabric



## On a Fresh New Cluster  

On the first Fabric node use the `adminInitialCredentials` file for setting the user and password for the Fabric "admin" user. The file will be read when Fabric starts for the first time, and then deleted.

As an example, to set the user and passoword to be k2consoleadmin and KW4RVG98RR9xcrTv, run the following commands:

~~~bash
cp config/adminInitialCredentials.template config/adminInitialCredentials
sed -i 's@user.*@k2consoleadmin/KW4RVG98RR9xcrTv@' config/adminInitialCredentials
~~~

You can now start Fabric, and use the above stated user and password.

## On Running Cluster 

### Via Console

```bash
# create second admin user
cqlsh -uadmin -padmin --ssl

create user 'admin2' with password 'Q7xp8GPNmjZp' SUPERUSER;
assign role admin to user admin2;

# Alter the admin with new password
cqlsh -uadmin2 -pQ7xp8GPNmjZp --ssl 
ALTER USER admin WITH PASSWORD 'Q7xp8GPNmjZp' SUPERUSER;

```

Check the connection: 

```bash
# connect with the following
cqlsh -uadmin2 -pQ7xp8GPNmjZp --ssl
```

Connect to the Web UI to verify the new login details (replace the IP with your server details):

* Fabric WS will be available at:
``` https://10.10.10.10:9443/deploy ```

* Admin:
``` https://10.10.10.10:9443/admin/gui/index.html ```



[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/devops/08_oracleGG_hardening.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/devops/10_fabric_definde_master_key.md)

