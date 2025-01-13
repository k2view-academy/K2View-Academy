# Operating - Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0

## Operating and Managing Docker Compose Runtime for Fabric Web Studio

### Fabric Spaces
This k2space.sh shell script makes it easy to create and delete Fabric. You can also use it to list and get information about existing Fabric Spaces using: 

```bash
Usage: `./k2space.sh COMMAND [OPTIONS] SPACE_NAME`
```

**Listing your Spaces**
List all Fabric Spaces, and some information like profile, state (running / stopped), Web Studio port, and the URL that can be used to access Web Studio if Traefik is running.

Use: 
```bash
./k2space.sh list
```

**Creating Additional Spaces**
Launch a Fabric Space "spacename" (optionally, with the selected Space Profile).

Use: 
```bash
./k2space.sh create [--profile=profile-name] spacename
```

**Destroying a Space**
Delete the Fabric Space "spacename". 

It will **not delete the persistent files** created by Web Studio and your database(s). You must manually delete them. These are located in the `persistent-data/spacename` directory.

Use: 
```bash
./k2space.sh destroy spacename
```

### Traefik
#### Starting Traefik
Traefik starts automatically after you create your first Fabric Space. It will also check whenever a new Fabric Space is created. If it is not running it will be started automatically.

> __Note:__ Traefik relies on the Docker network created during the creation of a Fabric Space. Therefore, It must be started __after__ the Fabric Space.

#### Restarting Traefik
To restart Traefik (e.g., after configuring your TSL certificates)  run the command below:

```bash
docker compose -f k2vingress-compose.yaml restart
```

### Adding Users

You are ready to add users. You can experiment with the built-in System DB (e.g., Postgres or Cassandra data stores). We recommend using alternate authentication providers rather than using built-in providers. 

To use the built-in authentication provider, navigate to the [Web Admin App](https://support.k2view.com/Academy/articles/30_web_framework/03_web_admin_application.html). Select the Security tab. Select the Users tab and add a user. Select the Roles tab, create a new role (e.g., User), and then assign Fabric permissions to the newly created role. 

The Docker Compose Runtime for K2view Fabric Web Studio employs underlying Fabric security capabilities and configurations. Fabric works with several authentication providers. Each authenticator is responsible for user authentication and managing user IDs and roles.

Following are the supported authentication providers as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/07_user_IAM_overview.html). 

- **Fabric**: Fabric stores users' credentials in a System DB table using Postgres. Passwords are stored securely in this table using a salted password hashing technique. By default, Fabric is configured to use a 32-byte salt length. When Cassandra is used, the provider is named Cassandra.
- **LDAP**: Fabric authentication is performed via LDAP integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/11_user_IAM_LDAP.html).
- **ADLDAP** (Microsoft Active Directory): Fabric authentication is performed via Active Directory integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/11_user_IAM_LDAP.html).
- **SAML**:  Fabric authentication is performed via SAML IDP integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/09_user_IAM_SAML_fundamentals_and_terms.html). SAML provides the means of offering an SSO experience to users using, for example, Microsoft Entra ID and Okta. See the [Microsoft Entra ID](https://support.k2view.com/Academy/articles/26_fabric_security/14_user_IAM_SAML_Azure_AD_setup.html) and [Okta](https://support.k2view.com/Academy/articles/26_fabric_security/15_user_IAM_SAML_Okta_setup.html) integration descriptions to learn more about Fabric SSO support.

## Reference Information

### k2space.sh OPTIONS Reference

Here are the command options for k2space.sh:

| Option            | Description                                                  |
| ----------------- | ------------------------------------------------------------ |
| --profile=        | Allows you to select the desired Fabric Space Profile        |
| --heap=           | Allows you to override the default 2GB allocated heap size   |
| --fabric-version= | Allows you to override the Fabric version specified in the .env file |
| --compose=        | Allows user to use a custom Docker compose.yaml file         |

The Fabric version is specified using major.minor Fabric version identifiers. E.g., 8.1.6_5. 

### .config File Format
These configuration files contain required or custom settings used by Fabric. Configure  parameters as if you were editing any "ini" file to update config.ini

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
