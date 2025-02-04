# Operating - Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0

## Content
<ul>      
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Operating.html#fabric-web-studio-spaces">Fabric Web Studio Spaces</a></li>
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Operating.html#restarting-traefik">Restarting Traefik</a></li>
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Operating.html#adding-users">Adding Users</a></li>
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Operating.html#reference-information">Reference Information</a></li>
</ul>

## Fabric Web Studio Spaces

This k2space.sh shell script simplifies the creation and deletion of Fabric. You can also use it to list and get information about existing Fabric spaces using: 

```bash
Usage: `./k2space.sh COMMAND [OPTIONS] SPACE_NAME`
```

> **Running k2space.sh on Microsoft Windows**
>
> The `k2space.sh` file is a `bash` script. A Windows PowerShell-compatible script is not yet available. To run the `k2space.sh` script, start the `Git Bash` applications offered by Git. Using `Git Bash` you can run the script after changing the directory to its location. 
> 
> If you have Git integration enabled within Windows Explorer, you can also start `Git Bash` from Windows Explorer by navigating to the script's directory, right-clicking within the Explorer's window, and selecting 'Show more options'. This will display an 'Open Git Bash here' menu item that can be used to start `Git Bash` to run `k2start.sh`. 


**Listing your Spaces**

List all Fabric spaces, along with some information such as profile, status (running / stopped), Web Studio port, and the URL that can be used for accessing Web Studio if Traefik is running.

Use: 
```bash
./k2space.sh list
```

**Creating Additional Spaces**

Launch a Fabric space - *spacename* (optionally, with the selected space profile).

Use: 
```bash
./k2space.sh create [--profile=profile-name] spacename
```

After creating your first Space, you will need to restart Traefik after creating an additional space. To restart Traefik (e.g., after configuring your TSL certificates), run the command below:

```bash
docker compose -f k2vingress-compose.yaml restart
```

**Starting a Space**

To start a Fabric space use: 

```bash
./k2space.sh start spacename
```

After starting a Space, you will need to restart Traefik. To restart Traefik (e.g., after configuring your TSL certificates), run the command below:

```bash
docker compose -f k2vingress-compose.yaml restart
```

**Stopping a Space**

To stop a Fabric space use: 

```bash
./k2space.sh stop spacename
```


**Destroying a Space**

Delete the Fabric space - *spacename*. 

This act will **not delete the persistent files** created by Web Studio and your database(s). You must manually delete them; they are located in the `persistent-data/spacename` directory.

Use: 
```bash
./k2space.sh destroy spacename
```

## Restarting Traefik
#### Starting Traefik
Traefik starts automatically after you create your first Fabric space. It will also check whenever a new Fabric space is created. If Traefik is not running, it will be started automatically.

> __Note:__ Traefik relies on the Docker network created during the creation of a Fabric space. Therefore, it must be started __after__ the Fabric space.

#### Restarting Traefik
To restart Traefik (e.g., after configuring your TSL certificates), run the command below:

```bash
docker compose -f k2vingress-compose.yaml restart
```

## Adding Users

You are ready to add users. You can experiment with the built-in System DB (e.g., Postgres or Cassandra data stores). We recommend using alternate authentication providers rather than using built-in providers. 

To use the built-in authentication provider, navigate to the [Web Admin App](https://support.k2view.com/Academy/articles/30_web_framework/03_web_admin_application.html). Select the Security tab. Select the Users tab and add a user. Select the Roles tab, create a new role (e.g., User), and then assign Fabric permissions to the newly created role. 

The Docker Compose Runtime for K2view Fabric Web Studio employs underlying Fabric security capabilities and configurations. Fabric works with several authentication providers. Each authenticator is responsible for handling user authentication and managing user IDs and roles.

Following are the supported authentication providers as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/07_user_IAM_overview.html). 

- **Fabric**: Fabric stores users' credentials in a System DB table using Postgres. Passwords are stored securely in this table using a salted password hashing technique. By default, Fabric is configured to use a 32-byte salt length. When Cassandra is used, the provider is named Cassandra.
- **LDAP**: Fabric authentication is performed via LDAP integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/11_user_IAM_LDAP.html).
- **ADLDAP** (Microsoft Active Directory): Fabric authentication is performed via Active Directory integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/11_user_IAM_LDAP.html).
- **SAML**: Fabric authentication is performed via SAML IDP integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/09_user_IAM_SAML_fundamentals_and_terms.html). SAML provides the means of offering an SSO experience to users by using, for example, Microsoft Entra ID and Okta. Read the [Microsoft Entra ID](https://support.k2view.com/Academy/articles/26_fabric_security/14_user_IAM_SAML_Azure_AD_setup.html) and [Okta](https://support.k2view.com/Academy/articles/26_fabric_security/15_user_IAM_SAML_Okta_setup.html) integration descriptions to learn more about Fabric SSO support.

## Reference Information

### k2space.sh OPTIONS Reference

Here are the command options for k2space.sh:

```bash
  --profile=         Allows you to select the desired Fabric Space Profile
 
  --heap=            Allows you to override the default 2GB allocated heap size
 
  --fabric-version=  Allows you to override the Fabric version specified in the .env file
 
  --compose=         Allows you to use a custom Docker compose.yaml file
```

The Fabric version is specified using major.minor Fabric version identifiers, e.g., 8.1.7_22. 

### .config File Format
These configuration files contain required or custom settings used by Fabric. Configure parameters as if you were editing any *ini* file to update the config.ini file.

```ini
[section1]
key1=value1
key2=value2

[section2]
key1=value1
key2=value2
```

### About the fabric-init Container

This temporary container sets the proper ownership of the persistent data's _Space_ folder. After its execution, it should exit automatically.
