# Generating a Self-Signed Certificate in Fabric

Fabric supports self-signed TLS certificates for development, testing, air-gapped, or other environments where a certificate issued by a trusted Certificate Authority (CA) is not required or available.

A self-signed certificate used as a Fabric TLS identity consists of a certificate and private key stored in the Fabric **keystore**.

For production deployments, use a certificate issued by your organization's Certificate Authority (CA) or another trusted CA whenever possible.

For an overview of certificate management and the distinction between the Fabric keystore and truststore, see [Certificate Management](/articles/99_fabric_infras/03_1_Certificate_Management.md).

For information about configuring Fabric's TLS identity and keystore, see [Fabric TLS Identity](/articles/99_fabric_infras/03_2_Fabric_TLS_Identity.md).

## Table of Contents

- [Generate a Self-Signed Certificate Using certificates.sh](#generate-a-self-signed-certificate-using-certificatessh)
- [Generate a Self-Signed Certificate Using keytool](#generate-a-self-signed-certificate-using-keytool)
- [Generate a Certificate with Subject Alternative Names](#generate-a-certificate-with-subject-alternative-names)
- [Export the Certificate for Client Trust](#export-the-certificate-for-client-trust)
- [Using a Self-Signed Certificate for SAML](#using-a-self-signed-certificate-for-saml)
- [Key Points and Best Practices](#key-points-and-best-practices)

## Generate a Self-Signed Certificate Using certificates.sh

Fabric provides the following certificate-management utility:

```text id="67g8ue"
${FABRIC_HOME}/fabric/scripts/certificates.sh
```

The `genkey` command generates a self-signed RSA keypair and stores it in the Fabric keystore.

The syntax is:

```text id="qpnm7s"
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
            <td>Alias under which the generated keypair is stored in the Fabric keystore.</td>
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

For example, to generate a certificate for `fabric.example.com` using the alias `webserver`:

```bash id="y5h5oy"
${FABRIC_HOME}/fabric/scripts/certificates.sh genkey webserver fabric.example.com changeit
```

The destination keystore is **not specified as an argument to `genkey`**. The script uses:

- the path defined by `FABRIC_KEYSTORE_PATH`, when configured; or
- `~/.keystore` when `FABRIC_KEYSTORE_PATH` is not defined.

By default, the script generates:

- a 4096-bit RSA key;
- a certificate signed using SHA256 with RSA;
- a certificate valid for 760 days;
- a PKCS#12 keystore; and
- a Distinguished Name based on the specified Common Name.

The generated Distinguished Name has the following form:

```text id="o25jcc"
CN=<CNAME>,OU=K2View,O=K2View,C=US
```

The script also restricts access to the keystore using:

```text id="rhg3b2"
chmod 600
```

If an entry with the specified alias already exists, the script removes that entry before generating the new keypair.

## Generate a Self-Signed Certificate Using keytool

Administrators can alternatively use Java's `keytool` utility directly. This provides greater control over certificate attributes such as the Distinguished Name, validity period, and Subject Alternative Names (SANs).

For example:

```bash id="vzrtn5"
keytool -genkeypair \
  -alias webserver \
  -keyalg RSA -keysize 4096 -sigalg SHA256WithRSA \
  -keystore ~/.keystore -storetype PKCS12 \
  -storepass changeit -keypass changeit \
  -dname "CN=fabric.example.com, OU=K2View, O=K2View, L=City, ST=State, C=US" \
  -validity 760
```

In this example:

- `-genkeypair` generates a private key and self-signed certificate.
- `-alias webserver` stores the keypair under the `webserver` alias.
- `-keystore ~/.keystore` identifies the destination keystore.
- `-storetype PKCS12` creates or uses a PKCS#12 keystore.
- `-dname` defines the certificate's Distinguished Name.
- `-validity 760` defines the certificate lifetime in days.

If Fabric uses a keystore path other than `~/.keystore`, specify the configured Fabric keystore path instead.

## Generate a Certificate with Subject Alternative Names

Modern TLS clients validate the hostname or IP address used to access a service against the certificate's **Subject Alternative Name (SAN)**.

The `certificates.sh genkey` command does not provide an option for adding SAN entries. Use `keytool` directly when SANs are required.

### DNS Name

For access using a DNS name:

```bash id="pzh2fq"
keytool -genkeypair \
  -alias webserver \
  -keyalg RSA -keysize 4096 -sigalg SHA256WithRSA \
  -keystore ~/.keystore -storetype PKCS12 \
  -storepass changeit -keypass changeit \
  -dname "CN=fabric.example.com, OU=K2View, O=K2View, C=US" \
  -ext "SAN=dns:fabric.example.com" \
  -validity 760
```

### IP Address

For access using an IP address:

```bash id="vjj2bx"
keytool -genkeypair \
  -alias webserver \
  -keyalg RSA -keysize 4096 -sigalg SHA256WithRSA \
  -keystore ~/.keystore -storetype PKCS12 \
  -storepass changeit -keypass changeit \
  -dname "CN=10.0.0.10, OU=K2View, O=K2View, C=US" \
  -ext "SAN=ip:10.0.0.10" \
  -validity 760
```

Replace the example DNS name or IP address with the name or address clients use to connect to Fabric.

When clients can access Fabric using multiple DNS names or addresses, include the required identities in the certificate SAN configuration.

## Export the Certificate for Client Trust

Because a self-signed certificate is not signed by a trusted CA, browsers and other clients do not normally trust it automatically.

The public certificate can be exported from the Fabric keystore and added to the trust configuration of clients that need to connect to Fabric.

For example:

```bash id="p3b6wm"
keytool -exportcert \
  -alias webserver \
  -keystore ~/.keystore \
  -storepass changeit \
  -rfc \
  -file webserver.pem
```

If Fabric uses a different keystore path, replace `~/.keystore` with the configured path.

The resulting `webserver.pem` contains the public certificate. It does **not** contain the private key.

Configure the applicable browser, operating system, application, or client truststore to trust this certificate according to the client vendor's instructions.

## Using a Self-Signed Certificate for SAML

Fabric can also use a generated keypair when a Fabric certificate is required for SAML configuration.

When generating a certificate specifically for use as the SAML Service Provider certificate, use an appropriate alias such as:

```text id="pr9g0s"
fabric_cert
```

For example:

```bash id="f7tft8"
${FABRIC_HOME}/fabric/scripts/certificates.sh genkey fabric_cert fabric.example.com changeit
```

The alias used to generate the keypair must correspond to the value configured for `SP_CERT_ALIAS` in the `[saml]` section of `config.ini`.

For example:

```ini id="tjqk3c"
[saml]
SP_CERT_ALIAS=fabric_cert
```

If the SAML configuration requires the Fabric public certificate to be provided to the Identity Provider (IdP), export the certificate from the Fabric keystore and provide the exported public certificate according to the IdP configuration requirements.

## Key Points and Best Practices

- Use certificates issued by a trusted CA for production environments whenever possible.
- Use self-signed certificates primarily for development, testing, air-gapped, or isolated environments where they are appropriate.
- The `certificates.sh genkey` syntax is `genkey <ALIAS> [CNAME] [PASSWORD]`. The keystore path is **not** a `genkey` argument.
- Use `FABRIC_KEYSTORE_PATH` when Fabric is configured to use a keystore other than the default `~/.keystore`.
- Ensure that the certificate identity matches the DNS name or IP address clients use to connect to Fabric.
- Use `keytool` when Subject Alternative Names or other certificate attributes not supported by `certificates.sh genkey` are required.
- Protect the Fabric keystore because it contains private key material.
- Ensure that the required keystore and alias are available to every Fabric instance that uses the TLS identity.
- In containerized environments, ensure that the keystore persists across container or pod replacement.
- When using a self-signed certificate, configure connecting clients to trust the exported public certificate.

After creating the TLS identity, configure the applicable Fabric service to use the corresponding certificate alias. For API, Web UI, and JDBC configuration, see [Fabric API, UI, and JDBC Hardening](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md).

[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/03_2_Fabric_TLS_Identity.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/03_3_Trusting_External_TLS_Services.md)
