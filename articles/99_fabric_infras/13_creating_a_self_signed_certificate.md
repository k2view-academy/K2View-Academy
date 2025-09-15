
# Generating a Self-Signed Certificate in Fabric

Fabric supports the use of TLS certificates to secure communication between its components and client applications. While production deployments typically use certificates issued by a trusted Certificate Authority (CA), it is sometimes necessary to generate a **self-signed certificate** for development, testing, or air-gapped environments.

This article explains how to create and use a self-signed certificate in Fabric using the `certificates.sh` helper script, and alternatively, how to use the `keytool` utility directly. It also covers advanced options for IP-based access and browser trust configuration.

## Table of Contents  

- [1. Using the `certificates.sh` Helper Script](#1-using-the-certificatessh-helper-script)  
- [2. Using `keytool` Directly (Alternative Approach)](#2-using-keytool-directly-alternative-approach)  
- [3. Generating a Certificate for IP-Based Access](#3-generating-a-certificate-for-ip-based-access)  
- [4. Exporting the Certificate for Browser Trust](#4-exporting-the-certificate-for-browser-trust)  
- [5. Key Points and Best Practices](#5-key-points-and-best-practices)  
- [Summary](#summary)  



## 1. Using the `certificates.sh` Helper Script

Fabric provides a script, `${FABRIC_HOME}/fabric/scripts/certificates.sh`, which wraps Java’s `keytool` utility to simplify common certificate operations. Among its supported commands is `genkey`, which generates a self-signed RSA keypair and stores it in a keystore.

Run the following command on one of your Fabric nodes:

```bash
${FABRIC_HOME}/fabric/scripts/certificates.sh genkey webserver ~/.keystore changeit
```

* `genkey` — command to generate a new keypair.
* `webserver` — the alias under which the key will be stored.
  * `fabric_cert` - use `fabric_cert` as an alias when creating a self-serve certificate that you will set as the value of the SP_CERT_ALIAS of the [saml] section of your config.ini
* `~/.keystore` — path to the keystore file (defaults to `~/.keystore` if not specified).
* `changeit` — keystore password (default is `"changeit"` if omitted).

By default, this generates a **4096-bit RSA key** signed with **SHA-256**, valid for **760 days**, and stored in **PKCS#12** format. The script also sets restrictive permissions (`chmod 600`) on the keystore for security.


## 2. Using `keytool` Directly (Alternative Approach)

Instead of relying on `certificates.sh`, administrators can invoke `keytool` directly. This provides greater control over certificate attributes.

Example command:

```bash
keytool -genkeypair \
  -alias webserver \
  -keyalg RSA -keysize 4096 -sigalg SHA256WithRSA \
  -keystore ~/.keystore -storetype PKCS12 \
  -storepass changeit -keypass changeit \
  -dname "CN=fabric.local, OU=K2View, O=K2View, L=City, ST=State, C=US" \
  -validity 760
```

Key points:

* `-genkeypair` generates both a private key and self-signed certificate.
* `-alias` specifies the logical name for this keypair inside the keystore.
  * `fabric_cert` - use `fabric_cert` as an alias when creating a self-serve certificate that you will set as the value of the SP_CERT_ALIAS of the [saml] section of your config.ini
  * otherwise `webserver` or other alias names can be used. 
* `-dname` sets the Distinguished Name fields; update `CN`, `O`, `L`, `ST`, and `C` as needed.
* `-validity` defines the certificate lifetime in days (here, \~2 years).
* Use `-storetype PKCS12` for compatibility with Fabric.

This approach is helpful if you need finer control (e.g., adding SAN entries, non-default lifetimes, or different signing algorithms).


## 3. Generating a Certificate for IP-Based Access

When Fabric endpoints are accessed by **IP address** rather than DNS name, most clients (including browsers) require the certificate to contain a **Subject Alternative Name (SAN)** entry. The `certificates.sh` script does not add SAN extensions automatically, so you must call `keytool` directly.

Example:

```bash
keytool -genkey -keyalg RSA -keysize 4096 -sigalg SHA256WithRSA \
  -alias webserver \
  -keystore ~/.keystore -storetype PKCS12 \
  -storepass changeit -noprompt \
  -dname "CN=YOUR_IP, OU=K2View, O=K2View, L=City, ST=State, C=US" \
  -ext "SAN=ip:YOUR_IP" \
  -validity 760
```

Replace `YOUR_IP` with the Fabric node’s IP address. This ensures the certificate is accepted when connecting to Fabric services via IP.


## 4. Exporting the Certificate for Browser Trust

Self-signed certificates are **not trusted by default** in browsers or external clients. To avoid security warnings, you can export the certificate from the keystore and import it into your local truststore (e.g., browser, OS certificate manager).

Export the certificate to PEM format:

```bash
keytool -export -alias webserver \
  -keystore ~/.keystore -storepass changeit \
  -rfc -file webserver.pem
```

Then import `webserver.pem` into your browser or system truststore following the vendor’s instructions.


## 5. Key Points and Best Practices

* Use self-signed certificates **only for non-production** or isolated environments.
* For production deployments, request certificates from a trusted CA or use an enterprise PKI.
* When replacing or rotating certificates, the script automatically deletes any existing entry under the same alias before creating a new one.


**Summary**:
With `certificates.sh`, generating a self-signed certificate for Fabric is straightforward. Alternatively, you can use `keytool` directly for more control. For IP-based access, add SAN extensions with `keytool`. If external clients or browsers must connect, export the certificate and add it to their truststores.


[![Previous](/articles/images/Previous.png)](/articles/99_fabric_infras/03_fabric_api_and_ui_hardening.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/99_fabric_infras/04_cassandra_hardening.md)
