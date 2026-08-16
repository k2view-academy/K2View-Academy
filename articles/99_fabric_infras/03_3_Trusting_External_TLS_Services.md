# Trusting External TLS Services

When Fabric connects to an external service using TLS, the certificate presented by that service must be trusted by the Fabric Java runtime.

If the certificate or its issuing Certificate Authority (CA) is not already trusted, add the required certificate to the Fabric **truststore**.

This article explains how the Fabric truststore is used and describes the Fabric utilities available for adding trusted certificates:

- `certificates.sh addtrust` - import an existing certificate into the truststore.
- `get-certificate.sh` - retrieve and trust the certificate presented by a single TLS endpoint.
- `get_interface_certs.sh` - retrieve and trust certificates presented by multiple TLS endpoints.

For an overview of inbound and outbound TLS certificate management, see [Certificate Management](/articles/99_fabric_infras/03_1_Certificate_Management.md).

## Table of Contents

- [When External Certificate Trust Is Required](#when-external-certificate-trust-is-required)
- [Fabric Truststore](#fabric-truststore)
- [Choosing a Certificate Import Method](#choosing-a-certificate-import-method)
- [Import an Existing Certificate Using certificates.sh](#import-an-existing-certificate-using-certificatessh)
  - [Replacing an Existing Certificate](#replacing-an-existing-certificate)
- [Retrieve a Certificate Using get-certificate.sh](#retrieve-a-certificate-using-get-certificatesh)
  - [Saving the Retrieved Certificate](#saving-the-retrieved-certificate)
- [Retrieve Certificates from Multiple Endpoints Using get_interface_certs.sh](#retrieve-certificates-from-multiple-endpoints-using-get_interface_certssh)
  - [Using a Comma-Separated List](#using-a-comma-separated-list)
  - [Using an Endpoint File](#using-an-endpoint-file)
  - [Truststore Selection](#truststore-selection)
- [Verify the Truststore](#verify-the-truststore)
- [Restart Requirements](#restart-requirements)
- [Containerized Deployments](#containerized-deployments)
- [Certificate Renewal and Replacement](#certificate-renewal-and-replacement)
- [Troubleshooting TLS Trust Errors](#troubleshooting-tls-trust-errors)
- [Related Topics](#related-topics)



## When External Certificate Trust Is Required

Fabric acts as a TLS client when it establishes a secure connection to an external service.

Examples include:

- external Key Management Systems (KMS);
- databases;
- APIs and web services;
- identity and authentication services;
- messaging services; and
- other TLS-enabled external endpoints.

During the TLS handshake, the external service presents its certificate. Fabric's Java runtime validates the certificate against its configured truststore.

If the certificate chain can be validated using certificates already trusted by the Java runtime, no additional certificate configuration is required.

If the certificate cannot be validated, the connection can fail with an SSL handshake error such as:

```text
javax.net.ssl.SSLHandshakeException:
PKIX path building failed:
sun.security.provider.certpath.SunCertPathBuilderException:
unable to find valid certification path to requested target
```

This commonly occurs when the external service uses:

- a certificate issued by an organization's private CA;
- a self-signed certificate; or
- another certificate whose trust chain is not available in the Fabric truststore.

In these cases, add the required certificate to the Fabric truststore.

> **Note:** The truststore contains certificates that Fabric trusts. It is different from the Fabric **keystore**, which contains Fabric's own TLS identity and private key. See [Fabric TLS Identity](/articles/99_fabric_infras/03_2_Fabric_TLS_Identity.md) for inbound TLS configuration.

## Fabric Truststore

The Fabric truststore contains certificates used by the Java runtime to validate external TLS services.

The `certificates.sh` utility determines the truststore location using the following order:

1. `FABRIC_TRUSTSTORE_PATH`, when the environment variable is defined.
2. `${JAVA_HOME}/lib/security/cacerts`, when `FABRIC_TRUSTSTORE_PATH` is not defined.

The default is therefore:

```text
${JAVA_HOME}/lib/security/cacerts
```

A custom truststore can be specified using:

```bash
export FABRIC_TRUSTSTORE_PATH=<truststore-path>
```

When `certificates.sh addtrust` is used with a custom `FABRIC_TRUSTSTORE_PATH` and the specified truststore does not yet exist, the script copies the Java default truststore from:

```text
${JAVA_HOME}/lib/security/cacerts
```

to the configured location before importing the certificate.

If a password is not specified, the Fabric certificate utilities use:

```text
changeit
```

as the default truststore password.

## Choosing a Certificate Import Method

Fabric provides three ways to add trust for external TLS services.

<table>
    <thead>
        <tr>
            <th>Requirement</th>
            <th>Utility</th>
            <th>When to Use</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Import a certificate that you already have</td>
            <td><code>certificates.sh addtrust</code></td>
            <td>Use when the required certificate has already been obtained from the service owner, security team, CA, or another trusted source.</td>
        </tr>
        <tr>
            <td>Retrieve and trust a certificate from one TLS endpoint</td>
            <td><code>get-certificate.sh</code></td>
            <td>Use when Fabric can connect directly to the endpoint and retrieve the certificate presented by the service.</td>
        </tr>
        <tr>
            <td>Retrieve and trust certificates from multiple TLS endpoints</td>
            <td><code>get_interface_certs.sh</code></td>
            <td>Use when certificates must be retrieved from multiple external endpoints.</td>
        </tr>
    </tbody>
</table>

## Import an Existing Certificate Using certificates.sh

If you already have the certificate that Fabric must trust, use:

```text
${FABRIC_HOME}/fabric/scripts/certificates.sh addtrust <ALIAS> <PATH> [PASSWORD]
```

Where:

<table>
    <thead>
        <tr>
            <th>Parameter</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>ALIAS</code></td>
            <td>Alias used to identify the certificate in the Fabric truststore.</td>
        </tr>
        <tr>
            <td><code>PATH</code></td>
            <td>Path to the certificate file to import.</td>
        </tr>
        <tr>
            <td><code>PASSWORD</code></td>
            <td>Truststore password. If omitted, the script uses <code>changeit</code>.</td>
        </tr>
    </tbody>
</table>

For example:

```bash
${FABRIC_HOME}/fabric/scripts/certificates.sh addtrust external_service /path/to/service.crt changeit
```

The script imports the certificate into the configured Fabric truststore.

### Replacing an Existing Certificate

If an entry with the specified alias already exists in the truststore, `certificates.sh` removes the existing entry before importing the new certificate.

This allows the same alias to be reused when replacing or renewing a trusted certificate.

## Retrieve a Certificate Using get-certificate.sh

When Fabric can connect directly to an external TLS endpoint, `get-certificate.sh` can retrieve the certificate presented by that endpoint and add it to the Fabric truststore.

The script is located at:

```text
${FABRIC_HOME}/fabric/scripts/get-certificate.sh
```

The syntax is:

```text
get-certificate.sh <IP:PORT> <ALIAS> [PASSWORD] [CERT_PATH]
```

Where:

<table>
    <thead>
        <tr>
            <th>Parameter</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>IP:PORT</code></td>
            <td>Host or IP address and TLS port of the external service.</td>
        </tr>
        <tr>
            <td><code>ALIAS</code></td>
            <td>Alias used to identify the retrieved certificate in the Fabric truststore.</td>
        </tr>
        <tr>
            <td><code>PASSWORD</code></td>
            <td>Truststore password. If omitted, <code>certificates.sh</code> uses <code>changeit</code>.</td>
        </tr>
        <tr>
            <td><code>CERT_PATH</code></td>
            <td>Optional path where the retrieved certificate is saved. If omitted, the script uses a temporary certificate file and removes it after the import.</td>
        </tr>
    </tbody>
</table>

For example, to retrieve the certificate presented by an external service on port `443`:

```bash
${FABRIC_HOME}/fabric/scripts/get-certificate.sh external.example.com:443 external_service changeit
```

The script performs the following operations:

1. connects to the endpoint using `openssl s_client`;
2. retrieves the certificate presented by the endpoint;
3. writes the certificate to the specified certificate file or a temporary file;
4. invokes `certificates.sh addtrust` using the specified alias and password; and
5. removes the temporary certificate file when no `CERT_PATH` was specified.

The connection attempt has a 20-second timeout.

If a certificate cannot be retrieved, the script reports:

```text
Cert not found.
```

and does not perform the truststore import.

### Saving the Retrieved Certificate

By default, the certificate retrieved by `get-certificate.sh` is used only for the import and the temporary file is removed afterward.

To retain the retrieved certificate, specify the fourth argument:

```bash
${FABRIC_HOME}/fabric/scripts/get-certificate.sh external.example.com:443 external_service changeit /path/to/external-service.crt
```

The certificate remains at the specified location after the truststore import.

## Retrieve Certificates from Multiple Endpoints Using get_interface_certs.sh

`get_interface_certs.sh` automates certificate retrieval and trust configuration for multiple external endpoints.

The script is located at:

```text
${FABRIC_HOME}/scripts/get_interface_certs.sh
```

The syntax is:

```text
get_interface_certs.sh <interfaces>
```

The `interfaces` argument can be either:

- a comma-separated list of endpoints; or
- the path to a file containing one endpoint per line.

Each endpoint must identify a TLS endpoint in `host:port` or `IP:port` format.

### Using a Comma-Separated List

For example:

```bash
${FABRIC_HOME}/scripts/get_interface_certs.sh "service1.example.com:443,service2.example.com:443"
```

The script processes each endpoint individually and invokes `get-certificate.sh` for it.

Aliases are generated sequentially:

```text
interface_1
interface_2
interface_3
...
```

### Using an Endpoint File

Create a file containing one endpoint per line:

```text
service1.example.com:443
service2.example.com:443
service3.example.com:8443
```

Then run:

```bash
${FABRIC_HOME}/scripts/get_interface_certs.sh /path/to/endpoints.txt
```

The script reads the file and processes each endpoint in sequence.


### Truststore Selection

`get_interface_certs.sh` uses the truststore defined by `FABRIC_TRUSTSTORE_PATH` when the environment variable is set.

If `FABRIC_TRUSTSTORE_PATH` is not defined, the script attempts to determine the Fabric truststore from the following Java option in `${FABRIC_HOME}/config/jvm.options`:

```text
-Djavax.net.ssl.trustStore=<truststore-path>
```

To explicitly specify the truststore, set `FABRIC_TRUSTSTORE_PATH` before running the script:

```bash
export FABRIC_TRUSTSTORE_PATH=/path/to/truststore
${FABRIC_HOME}/scripts/get_interface_certs.sh /path/to/endpoints.txt
```

If no Fabric truststore is configured, the underlying certificate-management utility uses the default Java truststore:

```text
${JAVA_HOME}/lib/security/cacerts
```

## Verify the Truststore

After importing a certificate, the truststore can be inspected using the Java `keytool` utility.

For example:

```bash
keytool -list \
  -alias <ALIAS> \
  -keystore <truststore-path> \
  -storepass <password> \
  -storetype PKCS12
```

Use the truststore path and password configured for the Fabric environment.

The alias should correspond to the alias used when importing the certificate.

## Restart Requirements

A running Fabric Java process might already have loaded its trust configuration. After changing the truststore, restart the applicable Fabric instance so that the updated trust configuration is used.

In a multi-node deployment, ensure that the required truststore configuration is available to every Fabric node that can establish the external connection.

## Containerized Deployments

In containerized environments, including Kubernetes deployments, changes made only to the filesystem of a running container might not survive container or pod replacement.

Running `get-certificate.sh`, `get_interface_certs.sh`, or `certificates.sh addtrust` interactively inside a container can modify the truststore used by that running container, but the modification is persistent only if the truststore itself is stored using the deployment's persistent configuration.

Ensure that the required truststore and certificate configuration is available whenever Fabric containers are created or replaced and consistently available to all Fabric replicas that require the external connection.

## Certificate Renewal and Replacement

Certificates used by external services can expire or be replaced.

If Fabric trusts a specific service certificate rather than a CA certificate, the Fabric truststore might need to be updated when the service certificate changes.

When the same alias is used with `certificates.sh addtrust`, the existing alias is removed before the new certificate is imported.

After replacing a trusted certificate:

1. verify that the expected certificate is present in the truststore;
2. ensure that all applicable Fabric nodes or containers have the updated truststore;
3. restart Fabric when required; and
4. verify the TLS connection to the external service.

## Troubleshooting TLS Trust Errors

An error such as:

```text
javax.net.ssl.SSLHandshakeException:
PKIX path building failed
```

indicates that the Java runtime could not establish a trusted certification path for the certificate presented by the external service.

When troubleshooting:

1. Verify that Fabric can reach the external endpoint and port.
2. Verify that the external service is presenting the expected certificate.
3. Verify that Fabric is using the expected truststore.
4. Verify that the required certificate is present in that truststore.
5. Verify that the truststore password is correct.
6. If multiple Fabric nodes or replicas are used, verify that the trust configuration is consistent across them.
7. Restart Fabric after changing the truststore when required.

If the certificate is already available, use `certificates.sh addtrust`.

If the certificate must be retrieved from the external endpoint, use `get-certificate.sh`.

If certificates must be retrieved from multiple endpoints, use `get_interface_certs.sh`.

## Related Topics

- [Certificate Management](/articles/99_fabric_infras/03_1_Certificate_Management.md) - Overview of Fabric inbound and outbound TLS certificate management.
- [Fabric TLS Identity](/articles/99_fabric_infras/03_2_Fabric_TLS_Identity.md) - Configure the certificate and private key Fabric presents to connecting clients.
- [Fabric API, UI, and JDBC Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md) - Configure TLS for Fabric services.
- [Generating a Self-Signed Certificate in Fabric](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md) - Generate a self-signed Fabric TLS identity.

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md)

