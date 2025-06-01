# Operating - Fabric Web Studio

## Table of Contents

1. [Fabric Web Studio Spaces](#fabric-web-studio-spaces)
    - [Listing your Spaces](#listing-your-spaces)
    - [Creating Additional Spaces](#creating-additional-spaces)
    - [Starting a Space](#starting-a-space)
    - [Stopping a Space](#stopping-a-space)
    - [Destroying a Space](#destroying-a-space)
2. [Restarting Traefik](#restarting-traefik)
    - [Starting Traefik](#starting-traefik)
    - [Restarting Traefik](#restarting-traefik)
3. [Adding Users](#adding-users)
4. [Reference Information](#reference-information)
    - [k2space.sh OPTIONS Reference](#k2spacesh-options-reference)
    - [.config File Format](#config-file-format)
    - [About the fabric-init Container](#about-the-fabric-init-container)
5. [Customizing Runtime Files Per Space](#customizing-runtime-files-per-space)
    - [File Customization Behavior](#file-customization-behavior)
        - [.env Customization](#env-customization)
        - [compose.yaml Customization](#composeyaml-customization)
        - [.config Customization](#config-customization)
    - [Manual Overrides](#manual-overrides)
    - [Example Use Case](#example-use-case)


## Fabric Web Studio Spaces

This k2space.sh shell script simplifies the creation and deletion of Fabric. You can also use it to list and get information about existing Fabric spaces using: 

```bash
Usage: `./k2space.sh COMMAND [OPTIONS] SPACE_NAME`
```

> **Docker Compose: Running k2space.sh on Microsoft Windows**
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

> After creating your first Space, you will need to wait for Fabric to come up. Unless it up you may get a 404 error if Traefik hasn't yet processed its new ingress rules which may take a few seconds. Otherwise, you might get a 502 error if Traefik is ready but Fabric is not yet ready. Give it some time. 


**Starting a Space**

To start a Fabric space use: 

```bash
./k2space.sh start spacename
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
To restart Traefik (e.g., after configuring your TLS certificates), run the command below:

Using Docker Compose: 

```bash
docker compose -f k2vingress-compose.yaml restart
```

Using Podman:

```bash
podman compose -f k2vingress-compose.yaml restart
```

## Adding Users

You are ready to add users. You can experiment with the built-in System DB (e.g., Postgres or Cassandra data stores). We recommend using alternate authentication providers rather than using built-in providers. 

To use the built-in authentication provider, navigate to the [Web Admin App](https://support.k2view.com/Academy/articles/30_web_framework/03_web_admin_application.html). Select the Security tab. Select the Users tab and add a user. Select the Roles tab, create a new role (e.g., User), and then assign Fabric permissions to the newly created role. 

The K2view Fabric Web Studio employs underlying Fabric security capabilities and configurations. Fabric works with several authentication providers. Each authenticator is responsible for handling user authentication and managing user IDs and roles.

Following are the supported authentication providers as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/07_user_IAM_overview.html). 

- **Fabric**: Fabric stores users' credentials in a System DB table using Postgres. Passwords are stored securely in this table using a salted password hashing technique. By default, Fabric is configured to use a 32-byte salt length. When Cassandra is used, the provider is named Cassandra.
- **LDAP**: Fabric authentication is performed via LDAP integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/11_user_IAM_LDAP.html).
- **ADLDAP** (Microsoft Active Directory): Fabric authentication is performed via Active Directory integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/11_user_IAM_LDAP.html).
- **SAML**: Fabric authentication is performed via SAML IDP integration as described [here](https://support.k2view.com/Academy/articles/26_fabric_security/09_user_IAM_SAML_fundamentals_and_terms.html). SAML provides the means of offering an SSO experience to users by using, for example, Microsoft Entra ID and Okta. Read the [Microsoft Entra ID](https://support.k2view.com/Academy/articles/26_fabric_security/14_user_IAM_SAML_Azure_AD_setup.html) and [Okta](https://support.k2view.com/Academy/articles/26_fabric_security/15_user_IAM_SAML_Okta_setup.html) integration descriptions to learn more about Fabric SSO support.

## Reference Information

### k2space.sh OPTIONS Reference

Here are the command options for k2space.sh:

<table>
  <thead>
    <tr>
      <th>Option</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>--profile=</code></td>
      <td>Allows you to select the desired Fabric Space Profile</td>
    </tr>
    <tr>
      <td><code>--heap=</code></td>
      <td>Allows you to override the default 4GB allocated heap size</td>
    </tr>
    <tr>
      <td><code>--fabric-version=</code></td>
      <td>Allows you to override the Fabric version specified in the .env file</td>
    </tr>
    <tr>
      <td><code>--compose=</code></td>
      <td>Allows you to use a custom Docker compose.yaml file</td>
    </tr>
    <tr>
      <td><code>--env=</code></td>
      <td>Allows you to use a custom Docker environment file</td>
    </tr>
    <tr>
      <td><code>--project=</code></td>
      <td>Allows you to specify the Project's name</td>
    </tr>
  </tbody>
</table>


The Fabric version is specified using the desired image tag. E.g., 8.2.1_46 

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


## Customizing Runtime Files Per Space

K2view Fabric Web Studio's Docker Compose Runtime supports **per-Space configuration overrides** using custom `.env`, `compose.yaml`, and `.config` files. When a new Fabric Space is created using the `k2space.sh` script, the runtime checks for specially named files corresponding to the Space name and applies them accordingly.

### File Customization Behavior

When creating a Space named, for example, `dcr210`, the following behavior applies:

- **`.env` and `.config` files** are **appended to** the base configuration.
- **`compose.yaml` files** are **overridden completely** if a matching file is found.

#### `.env` Customization

- The base `.env` file is always loaded first.
- If a file named `.env-dcr210` exists (where `dcr210` is the Space name), its values will be **appended to or override** those defined in the base `.env`.
- You can use this to set environment-specific parameters such as `MAX_HEAP`, `GIT_REPO`, or other custom variables.

#### `compose.yaml` Customization

- The default `compose.yaml` is loaded unless a file named `compose-dcr210.yaml` exists.
- If `compose-dcr210.yaml` exists  (where `dcr210` is the Space name), it will **completely replace** the default `compose.yaml` for that Space.

#### `.config` Customization

- The base `common.config` file is always loaded.
- If a file named `dcr210.config` exists (where `dcr210` is the Space name), its values will be **appended to or override** settings from `common.config`.
- This allows per-Space configuration of Fabric runtime behavior, such as a custom system DB path or SSO settings.

### Manual Overrides

You can also explicitly specify files when using the `k2space.sh` script:

```
./k2space.sh create --env=custom.env --compose=custom-compose.yaml --config=custom.config dcr210
```

When using the `--env` or `--compose` options, **automatic detection of `.env-[spacename]` and `compose-[spacename].yaml` is skipped**.

### Example Use Case

For a Space named `dcr210`, you might create:

- `.env-dcr210` to override heap size:

  ```
  env
  
  MAX_HEAP=8G
  ```

- `compose-dcr210.yaml` to expose an additional port (e.g., JDBC port 5124):

  ```
  yaml
  
  ports:
    - "5124:5124"
  ```

- `dcr210.config` to point to a different System DB path or define SSO behavior:

  ```
  ini
  
  [System]
  db.path=/custom/path/system.db
  ```

This flexible mechanism supports both shared and isolated runtime customization for Fabric Web Studio Spaces, making it ideal for multi-tenant development, QA, or demo environments.
