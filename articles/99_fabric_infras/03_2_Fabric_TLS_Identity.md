# Fabric TLS Identity

Fabric requires a TLS identity when clients connect securely to Fabric services. The TLS identity consists of a certificate and its associated private key, and Fabric maintains it in the Fabric **keystore**.

This article explains how Fabric uses its TLS identity, how it selects the Fabric keystore, and how to use the `certificates.sh` utility to configure the keystore.

For an overview of inbound and outbound TLS certificate management, see [Certificate Management](03_1_Certificate_Management.md).

## Table of Contents

- [When a Fabric TLS Identity Is Required](#when-a-fabric-tls-identity-is-required)
- [Fabric Keystore](#fabric-keystore)
- [certificates.sh](#certificatessh)
- [Import an Existing TLS Identity](#import-an-existing-tls-identity)
  - [Replacing an Existing Alias](#replacing-an-existing-alias)
- [Generate a Self-Signed TLS Identity](#generate-a-self-signed-tls-identity)
- [Keystore File Permissions](#keystore-file-permissions)
- [Using the TLS Identity](#using-the-tls-identity)
- [Multi-Node Deployments](#multi-node-deployments)
- [Containerized Deployments](#containerized-deployments)
- [Related Topics](#related-topics)

## When a Fabric TLS Identity Is Required

Use a Fabric TLS identity when Fabric acts as the **TLS server** and presents a certificate to a connecting client.

Examples include secure connections from:

- browsers accessing the Fabric Web UI;
- applications accessing Fabric APIs; and
- clients using TLS-enabled Fabric services.

The certificate identifies the Fabric service to the connecting client. The client then determines whether it trusts the certificate based on its own certificate trust configuration.

This differs from an outbound connection, where Fabric must trust the certificate presented by an external service. For outbound certificate trust, see **[Trusting External TLS Services](/articles/99_fabric_infras/03_3_Trusting_External_TLS_Services.md)**.

## Fabric Keystore

Fabric stores its TLS identity in a **keystore**.

The keystore contains the certificate and associated private key that Fabric uses to establish its identity during a TLS handshake.

The `certificates.sh` utility determines the keystore location using the following order:

1. `FABRIC_KEYSTORE_PATH`, when the environment variable is defined.
2. `~/.keystore`, when `FABRIC_KEYSTORE_PATH` is not defined.

The default is therefore:

```text
~/.keystore
```

To use a different keystore, set `FABRIC_KEYSTORE_PATH` to the required path before using the certificate-management utilities and ensure that Fabric is configured to use the same keystore.

> **Note:** The keystore is different from the Fabric truststore. The keystore contains Fabric's own TLS identity. The truststore contains certificates that Fabric trusts when connecting to external TLS services.

## certificates.sh

Fabric provides the `certificates.sh` utility for certificate-management operations.

The script is located at:

```bash
${FABRIC_HOME}/fabric/scripts/certificates.sh
```

The operations related to the Fabric TLS identity are:

```text
certificates.sh addkey <ALIAS> <PATH> [PASSWORD]
certificates.sh genkey <ALIAS> [CNAME] [PASSWORD]
```

<table>
    <thead>
        <tr>
            <th>Command</th>
            <th>Purpose</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>addkey</code></td>
            <td>Imports certificate material into the Fabric keystore.</td>
        </tr>
        <tr>
            <td><code>genkey</code></td>
            <td>Generates a keypair and self-signed certificate in the Fabric keystore.</td>
        </tr>
    </tbody>
</table>

The script uses `changeit` as the default password when you don't supply one.

## Import an Existing TLS Identity

For production environments, use a certificate issued by your organization's Certificate Authority (CA) or another CA trusted by the clients that connect to Fabric.

Use `certificates.sh addkey` to import the certificate material:

```text
${FABRIC_HOME}/fabric/scripts/certificates.sh addkey <ALIAS> <PATH> [PASSWORD]
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
            <td>Alias used to identify the certificate entry in the Fabric keystore.</td>
        </tr>
        <tr>
            <td><code>PATH</code></td>
            <td>Path to the certificate material being imported.</td>
        </tr>
        <tr>
            <td><code>PASSWORD</code></td>
            <td>Keystore password. If omitted, the script uses <code>changeit</code>.</td>
        </tr>
    </tbody>
</table>

For example:

```bash
${FABRIC_HOME}/fabric/scripts/certificates.sh addkey webserver <certificate-path> changeit
```

The alias is important because Fabric services that use the TLS identity reference the corresponding key by alias. For the Fabric API and Web UI, for example, the configured `WEB_SERVICE_KEY_ALIAS` must correspond to the alias containing the required TLS identity.

> **Important:** The `<PATH>` argument identifies the certificate material to import. It is **not** the path of the destination Fabric keystore. FABRIC_KEYSTORE_PATH determines the destination keystore; if that variable is not defined, it defaults to `~/.keystore`.

### Replacing an Existing Alias

If the specified alias already exists in the keystore, `certificates.sh` removes the existing entry before importing the new certificate material.

When replacing a certificate, use the same alias if the Fabric configuration already references that alias.

## Generate a Self-Signed TLS Identity

`certificates.sh` can also generate a keypair and self-signed certificate directly in the Fabric keystore.

The syntax is:

```text
${FABRIC_HOME}/fabric/scripts/certificates.sh genkey <ALIAS> [CNAME] [PASSWORD]
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
            <td>Alias used to identify the generated keypair in the Fabric keystore.</td>
        </tr>
        <tr>
            <td><code>CNAME</code></td>
            <td>Common Name (CN) used when generating the certificate. If omitted, the script uses <code>k2view.local</code>.</td>
        </tr>
        <tr>
            <td><code>PASSWORD</code></td>
            <td>Keystore password. If omitted, the script uses <code>changeit</code>.</td>
        </tr>
    </tbody>
</table>

For example:

```bash
${FABRIC_HOME}/fabric/scripts/certificates.sh genkey webserver fabric.example.com changeit
```

The script generates:

- a 4096-bit RSA key;
- a certificate signed using SHA256 with RSA;
- a self-signed certificate with the specified Common Name; and
- a certificate validity period of 760 days.

For the complete self-signed certificate procedure, including cases where Subject Alternative Names (SANs) or IP-based access are required, see [Generating a Self-Signed Certificate in Fabric](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md).

## Keystore File Permissions

When `certificates.sh` creates or modifies the Fabric keystore, the script sets the keystore file permissions to:

```text
600
```

This restricts access to the keystore owner.

The operating-system user running Fabric must be able to read the configured keystore.

## Using the TLS Identity

Adding a TLS identity to the Fabric keystore does not by itself enable TLS for a Fabric service.

The applicable Fabric service must also be configured to use the certificate alias.

For example, the Fabric API and Web UI use settings including:

```ini
WEB_SERVICE_SECURE_PORT=8443
WEB_SERVICE_KEY_ALIAS=webserver
```

In this example, `webserver` must correspond to the alias containing the Fabric TLS identity in the configured keystore.

For the complete API, Web UI, and JDBC TLS configuration, see [Fabric API, UI, and JDBC Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md).

## Multi-Node Deployments

All Fabric nodes providing the same TLS-enabled service must have access to the required TLS identity.

When you use the same certificate across multiple Fabric nodes, ensure the corresponding keystore and alias are consistently available to each applicable node.

The private key contained in the keystore is sensitive material. Protect the keystore and its password when you copy, store, or distribute it.

## Containerized Deployments

In containerized environments, including Kubernetes deployments, changes made only to a running container's filesystem might not survive container or pod replacement.

Therefore, supply the Fabric TLS identity and keystore configuration through the deployment's persistent configuration so the required identity is available whenever Fabric containers are created or replaced.

Avoid treating an interactive change made inside a running container as the persistent certificate-management procedure unless the underlying keystore is stored persistently.

## Related Topics

- [Certificate Management](/articles/99_fabric_infras/03_1_Certificate_Management.md) - Overview of Fabric inbound and outbound TLS certificate management.
- **[Trusting External TLS Services](/articles/99_fabric_infras/03_3_Trusting_External_TLS_Services.md)** - Configure certificates that Fabric must trust when connecting to external services.
- [Generating a Self-Signed Certificate in Fabric](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md) - Generate and configure a self-signed Fabric TLS identity.
- [Fabric API, UI, and JDBC Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md) - Configure Fabric services to use TLS.

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/03_1_Certificate_Management.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/13_creating_a_self_signed_certificate.md)
