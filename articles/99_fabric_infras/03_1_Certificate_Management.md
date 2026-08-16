# Certificate Management

Fabric uses TLS certificates both to **identify itself to clients** and to **establish trust when connecting to external services**. Understanding the connection direction matters because Fabric's certificate requirements and the certificate store it uses differ in each case.

This article introduces Fabric certificate management, explains the difference between the **keystore** and **truststore**, and directs you to the appropriate procedure for each certificate use case.

## Table of Contents

- [Certificate Usage in Fabric](#certificate-usage-in-fabric)
- [Inbound TLS - Fabric Identity](#inbound-tls---fabric-identity)
- [Outbound TLS - Trusting External Services](#outbound-tls---trusting-external-services)
- [Keystore and Truststore](#keystore-and-truststore)
- [Fabric Certificate Management Scripts](#fabric-certificate-management-scripts)
  - [certificates.sh](#certificatessh)
  - [get-certificate.sh](#get-certificatesh)
  - [get_interface_certs.sh](#get_interface_certssh)
- [Choosing the Appropriate Procedure](#choosing-the-appropriate-procedure)
- [Deployment Considerations](#deployment-considerations)

## Certificate Usage in Fabric

TLS certificates are used differently depending on whether Fabric receives a secure connection or initiates one.

<table>
    <thead>
        <tr>
            <th>Scenario</th>
            <th>Fabric Role</th>
            <th>Purpose</th>
            <th>Certificate Store</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Client connects securely to Fabric</td>
            <td>TLS server</td>
            <td>Fabric presents its identity to the client</td>
            <td>Keystore</td>
        </tr>
        <tr>
            <td>Fabric connects securely to an external service</td>
            <td>TLS client</td>
            <td>Fabric validates the identity of the external service</td>
            <td>Truststore</td>
        </tr>
    </tbody>
</table>

A useful way to distinguish the two is:

- **Keystore - "Who am I?"** - Contains Fabric's certificate and private key used to establish Fabric's identity.
- **Truststore - "Who do I trust?"** - Contains certificates or Certificate Authority (CA) certificates that Fabric trusts when establishing TLS connections to external systems.

## Inbound TLS - Fabric Identity

When a browser, application, JDBC client, or other client establishes a secure connection **to Fabric**, Fabric acts as the TLS server.

Fabric presents a TLS certificate to the connecting client. Fabric maintains the corresponding certificate and private key in the Fabric **keystore**.

A Fabric TLS identity can be configured using:

- an existing certificate and private key issued by your organization's Certificate Authority (CA) or another trusted CA; or
- a self-signed certificate, typically for development, testing, or isolated environments.

Fabric provides the `certificates.sh` utility for common keystore operations, including importing certificate material and generating a self-signed keypair.

For instructions on configuring Fabric's TLS identity, see **[Fabric TLS Identity](/articles/99_fabric_infras/03_2_Fabric_TLS_Identity.md)**.

For development, testing, or isolated environments where a self-signed certificate is appropriate, see **[Generating a Self-Signed Certificate in Fabric](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md)**.

After you configure Fabric's TLS identity, you can configure services such as the Fabric API and Web UI to use it. See **[Fabric API, UI, and JDBC Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md)**.

## Outbound TLS - Trusting External Services

Fabric can also act as a TLS client when connecting **to an external service**.

Examples include connections to:

- external Key Management Systems (KMS);
- databases;
- APIs and web services;
- identity and authentication services;
- messaging services; and
- other TLS-enabled external endpoints.

During the TLS handshake, the external service presents its certificate. Fabric's Java runtime must be able to establish a trusted certification path for that certificate.

Certificates issued by a CA already trusted by the Java runtime normally require no additional configuration.

If the external service uses a private CA, a self-signed certificate, or another certificate that is not already trusted, you must add the required certificate or CA certificate to the Fabric **truststore**.

A missing trust relationship can result in TLS handshake errors such as:

```text
javax.net.ssl.SSLHandshakeException:
PKIX path building failed
```

Fabric provides several utilities for establishing trust with external services:

- `certificates.sh addtrust` - imports an existing certificate into the Fabric truststore.
- `get-certificate.sh` - connects to a TLS endpoint, retrieves its certificate, and adds it to the Fabric truststore.
- `get_interface_certs.sh` - performs certificate retrieval and trust configuration for multiple external endpoints.

For instructions, see **[Trusting External TLS Services](/articles/99_fabric_infras/03_3_Trusting_External_TLS_Services.md)**.

## Keystore and Truststore

Although both stores contain certificate-related information, they serve different purposes.

<table>
    <thead>
        <tr>
            <th></th>
            <th>Keystore</th>
            <th>Truststore</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><strong>Primary purpose</strong></td>
            <td>Establish Fabric's identity</td>
            <td>Establish which external certificates Fabric trusts</td>
        </tr>
        <tr>
            <td><strong>Typical contents</strong></td>
            <td>Certificate and associated private key</td>
            <td>Trusted certificates and CA certificates</td>
        </tr>
        <tr>
            <td><strong>Typical TLS direction</strong></td>
            <td>Client to Fabric</td>
            <td>Fabric to external service</td>
        </tr>
        <tr>
            <td><strong>Fabric environment variable</strong></td>
            <td><code>FABRIC_KEYSTORE_PATH</code></td>
            <td><code>FABRIC_TRUSTSTORE_PATH</code></td>
        </tr>
        <tr>
            <td><strong>Default used by certificates.sh</strong></td>
            <td><code>~/.keystore</code></td>
            <td><code>${JAVA_HOME}/lib/security/cacerts</code></td>
        </tr>
        <tr>
            <td><strong>Relevant certificates.sh operations</strong></td>
            <td><code>addkey</code>, <code>genkey</code></td>
            <td><code>addtrust</code></td>
        </tr>
    </tbody>
</table>

Importing a certificate into the Fabric keystore does not make that certificate trusted for outbound TLS connections. Likewise, adding an external certificate to the truststore does not configure Fabric to use that certificate as its own TLS identity.

## Fabric Certificate Management Scripts

Fabric includes three scripts for common certificate-management operations.

### certificates.sh

Location:

```bash
${FABRIC_HOME}/fabric/scripts/certificates.sh
```

`certificates.sh` is the core Fabric certificate-management utility. It supports operations against both the Fabric keystore and truststore.

Supported commands are:

```text
certificates.sh addkey <ALIAS> <PATH> [PASSWORD]
certificates.sh addtrust <ALIAS> <PATH> [PASSWORD]
certificates.sh genkey <ALIAS> [CNAME] [PASSWORD]
```

<table>
    <thead>
        <tr>
            <th>Command</th>
            <th>Purpose</th>
            <th>Store</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>addkey</code></td>
            <td>Imports certificate material for use by Fabric</td>
            <td>Keystore</td>
        </tr>
        <tr>
            <td><code>addtrust</code></td>
            <td>Imports a certificate that Fabric must trust</td>
            <td>Truststore</td>
        </tr>
        <tr>
            <td><code>genkey</code></td>
            <td>Generates a keypair and self-signed certificate for Fabric</td>
            <td>Keystore</td>
        </tr>
    </tbody>
</table>

Fabric TLS Identity, Trusting External TLS Services, and Generating a Self-Signed Certificate in Fabric cover the detailed use of these commands.

### get-certificate.sh

Location:

```bash
${FABRIC_HOME}/fabric/scripts/get-certificate.sh
```

Use get-certificate.sh when Fabric must trust a certificate presented by an external TLS endpoint.

The script:

1. connects to the specified endpoint;
2. retrieves the certificate presented by the endpoint;
3. saves the retrieved certificate temporarily, unless a certificate output path is specified;
4. invokes `certificates.sh addtrust` to add the certificate to the Fabric truststore; and
5. removes the temporary certificate after the import completes.

The syntax is:

```text
get-certificate.sh <IP:PORT> <ALIAS> [PASSWORD] [CERT_PATH]
```

This script is useful when the certificate is available directly from the target TLS service and needs to be added to Fabric's truststore.

See **[Trusting External TLS Services](/articles/99_fabric_infras/03_3_Trusting_External_TLS_Services.md)** for the complete procedure.

### get_interface_certs.sh

Location:

```bash
${FABRIC_HOME}/scripts/get_interface_certs.sh
```

`get_interface_certs.sh` is a convenience utility for retrieving and trusting certificates from multiple external endpoints.

The script accepts either:

- a file containing endpoints, one endpoint per line; or
- a comma-separated list of endpoints.

It invokes `get-certificate.sh` for each endpoint and imports the retrieved certificates into the Fabric truststore.

The syntax is:

```text
get_interface_certs.sh <interfaces> [truststore_path]
```

This utility is useful when Fabric connects to multiple TLS-enabled external interfaces whose certificates must be trusted.

See **[Trusting External TLS Services](/articles/99_fabric_infras/03_3_Trusting_External_TLS_Services.md)** for detailed usage.

## Choosing the Appropriate Procedure

Use the TLS connection's direction and purpose to determine which certificate procedure applies.

<table>
    <thead>
        <tr>
            <th>If you need to...</th>
            <th>Procedure</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Configure Fabric to present an existing TLS identity</td>
            <td><strong>Fabric TLS Identity</strong></td>
        </tr>
        <tr>
            <td>Generate a self-signed Fabric certificate</td>
            <td><strong>Generating a Self-Signed Certificate in Fabric</strong></td>
        </tr>
        <tr>
            <td>Enable HTTPS for the Fabric API or Web UI</td>
            <td><strong>Fabric API, UI, and JDBC Hardening</strong></td>
        </tr>
        <tr>
            <td>Import an existing certificate or CA certificate that Fabric must trust</td>
            <td><strong>Trusting External TLS Services</strong> using <code>certificates.sh addtrust</code></td>
        </tr>
        <tr>
            <td>Retrieve and trust the certificate presented by one external TLS endpoint</td>
            <td><strong>Trusting External TLS Services</strong> using <code>get-certificate.sh</code></td>
        </tr>
        <tr>
            <td>Retrieve and trust certificates from multiple external TLS endpoints</td>
            <td><strong>Trusting External TLS Services</strong> using <code>get_interface_certs.sh</code></td>
        </tr>
    </tbody>
</table>

## Deployment Considerations

Apply certificate and truststore configuration consistently to the Fabric instances that require it.

For traditional Linux installations, the keystore or truststore is maintained on the filesystem used by the Fabric installation. In multi-node deployments, ensure that the required certificate configuration is available to all applicable Fabric nodes.

For containerized deployments, including Kubernetes deployments, certificate changes made interactively inside a running container might not persist when the container or pod is replaced or recreated. Therefore, incorporate certificate, keystore, and truststore configuration into the deployment's persistent configuration when you need persistence across container recreation.

For the configuration appropriate to each certificate use case, see:

- **[Fabric TLS Identity](/articles/99_fabric_infras/03_2_Fabric_TLS_Identity.md)**
- **[Trusting External TLS Services](/articles/99_fabric_infras/03_3_Trusting_External_TLS_Services.md)**
- **[Generating a Self-Signed Certificate in Fabric](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md)**
- **[Fabric API, UI, and JDBC Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md)**

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/02_fabric_environments.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/03_2_Fabric_TLS_Identity.md)