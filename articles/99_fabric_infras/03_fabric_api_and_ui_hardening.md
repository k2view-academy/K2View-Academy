# Fabric API, UI, and JDBC Hardening

Fabric supports TLS for securing access to the Fabric API, Web UI, web services, and JDBC interface.

This article describes how to enable TLS for these Fabric services. Before enabling TLS, Fabric must have a TLS identity containing the certificate and private key that it presents to connecting clients.

For an introduction to certificate management, keystores, and truststores, see [Certificate Management](/articles/99_fabric_infras/03_1_Certificate_Management.md).

## Table of Contents

- [Fabric API, UI, and Web Services Hardening](#fabric-api-ui-and-web-services-hardening)
  - [Step 1 - Configure the Fabric TLS Identity](#step-1---configure-the-fabric-tls-identity)
  - [Step 2 - Configure Fabric](#step-2---configure-fabric)
  - [Step 3 - Restart Fabric](#step-3---restart-fabric)
  - [Step 4 - Verify HTTPS Access](#step-4---verify-https-access)
- [Fabric JDBC Driver Hardening](#fabric-jdbc-driver-hardening)

## Fabric API, UI, and Web Services Hardening

### Step 1 - Configure the Fabric TLS Identity

Fabric requires a TLS identity consisting of a certificate and its associated private key. The TLS identity is stored in the Fabric keystore and identified by an alias.

For production environments, use a certificate issued by your organization's Certificate Authority (CA) or another CA trusted by the clients connecting to Fabric.

For instructions on importing an existing TLS identity and configuring the Fabric keystore, see [Fabric TLS Identity](/articles/99_fabric_infras/03_2_Fabric_TLS_Identity.md).

For development, testing, or isolated environments where a self-signed certificate is appropriate, see [Generating a Self-Signed Certificate in Fabric](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md).

In a multi-node deployment, ensure that the required TLS identity and corresponding alias are available to every Fabric node providing the TLS-enabled service.

In containerized environments, ensure that the TLS identity and keystore are available to all applicable Fabric containers and persist across container or pod replacement.

### Step 2 - Configure Fabric

In the `fabric` section of the `config.ini` file, configure the secure web-service port and the alias of the TLS identity stored in the Fabric keystore:

~~~ini
WEB_SERVICE_SECURE_PORT=8443
WEB_SERVICE_KEY_ALIAS=webserver
~~~

`WEB_SERVICE_SECURE_PORT` defines the HTTPS port used by the Fabric API, Web UI, and web services.

`WEB_SERVICE_KEY_ALIAS` identifies the key entry in the Fabric keystore that Fabric uses as its TLS identity.

The value of `WEB_SERVICE_KEY_ALIAS` must match the alias configured in the Fabric keystore. For example, if the TLS identity was imported or generated using the alias `webserver`, configure:

~~~ini
WEB_SERVICE_KEY_ALIAS=webserver
~~~

### Step 3 - Restart Fabric

Restart each applicable Fabric node after configuring the TLS identity and secure web-service settings.

For containerized deployments, restart or recreate the applicable Fabric workload using the deployment mechanism used to manage the environment.

### Step 4 - Verify HTTPS Access

After Fabric has restarted, verify that the Fabric Web UI is accessible using HTTPS.

For example:

~~~
https://<fabric-host>:8443/app/admin
~~~

Verify that:

- the HTTPS connection succeeds;
- Fabric presents the expected certificate; and
- the connecting browser or client trusts the certificate.

If a self-signed certificate or certificate issued by a private CA is used, the connecting client might also need to be configured to trust the corresponding certificate or CA.

## Fabric JDBC Driver Hardening

To securely access Fabric data using the Fabric JDBC driver, TLS is enabled by default in the `[jdbc-server]` section of the `config.ini` file:

~~~ini
## Turn on TLS for the Fabric driver protocol
#SECURE=true
~~~

To disable TLS for the Fabric JDBC protocol, set:

~~~ini
SECURE=false
~~~

For secure deployments, keep TLS enabled unless there is a specific requirement to disable it.


[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/03_3_Trusting_External_TLS_Services.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/04_cassandra_hardening.md)
