# Digital Signature Verification for K2view Fabric & Studio Artifacts

## Table of Contents

  * [Purpose](#purpose)
  * [What Is Signed](#what-is-signed)
    * [Container Images](#container-images)
    * [Fabric Packages](#fabric-packages)
  * [Key Design Principles](#key-design-principles)
    * [Digest-Based Signing](#digest-based-signing)
    * [One Signature per Artifact](#one-signature-per-artifact)
    * [Industry-Standard Tooling](#industry-standard-tooling)
  * [How Customers Verify Artifacts](#how-customers-verify-artifacts)
  * [Where Signature Metadata Comes From](#where-signature-metadata-comes-from)
    * [Container Images](#container-images-1)
    * [Fabric Packages](#fabric-packages-1)
  * [Verification Outcomes](#verification-outcomes)
  * [Key Takeaways](#key-takeaways)

## Purpose

K2view provides cryptographic signatures for all supported **Fabric and Studio container images** and **Fabric packages** to allow customers to independently verify:

* **Authenticity** – the artifact was produced and signed by K2view
* **Integrity** – the artifact has not been altered after release

This capability supports enterprise security controls, supply-chain security requirements, and regulated or air-gapped environments.

The detailed verification procedures are documented separately on the K2view Support Portal: https://support.k2view.com/signatures

This document explains what is signed, how the system works conceptually, and how customers should use the verification materials.

---

## What Is Signed

K2view signs the following deliverables:

### Container Images

* Fabric runtime images
* Fabric Studio images
* Other K2view-distributed container images

Each image is signed using its immutable cryptographic digest (SHA-256).

### Fabric Packages

* Offline packages (e.g., TAR or ZIP artifacts)
* Non-containerized Fabric deliverables

Each package is signed using the digest of the final packaged file, after it is fully assembled.

---

## Key Design Principles

### Digest-Based Signing

K2view signs the digest, not the filename or tag.

* Any change to the artifact (even a single byte) changes the digest
* If the digest changes, signature verification fails
* This provides strong tamper detection

---

### One Signature per Artifact

* Every image and every package has its own unique signature
* Signatures are not shared across versions or builds
* A signature is valid only for the exact artifact it was generated for

---

### Industry-Standard Tooling

K2view uses Cosign, an industry-standard signing tool widely adopted in the Kubernetes and cloud-native ecosystem.

* A K2view-managed private key is used to sign artifacts
* A corresponding public key is published for customer verification
* Customers do not need proprietary tooling to verify signatures

---

## How Customers Verify Artifacts

The verification process differs slightly depending on artifact type, but follows the same logical flow:

1. Obtain the artifact (image or package)
2. Calculate its digest locally
3. Retrieve the corresponding signature and public key
4. Verify the signature against the digest

The exact commands and tooling are documented on the Support Portal:
 https://support.k2view.com/signatures

This separation ensures:

* The verification steps remain current
* This document remains stable and explanatory

---

## Where Signature Metadata Comes From

### Container Images

For container images, signature metadata is embedded directly in the image as labels.  
These labels indicate:

* Which public key to use
* Where to retrieve the signature bundle

This allows customers to verify an image using only the image itself and the referenced metadata.

---

### Fabric Packages

Because packages do not support embedded metadata, K2view provides:
* A signature bundle for the package
* A small metadata file indicating the public key used for signing

These files are distributed alongside the package and referenced in the Support Portal documentation.

---

## Verification Outcomes

* **Successful verification** confirms the artifact is authentic and unmodified
* **Any verification failure** indicates the artifact should not be trusted or deployed

In the event of a failure, customers should stop using the image or package and contact K2view Support to inform us.

---

## Key Takeaways

* K2view signs what you deploy, not just what you download
* Verification is deterministic, repeatable, and customer-controlled
* The process aligns with modern software supply-chain security practices
* Step-by-step verification instructions are maintained on the Support Portal at https://support.k2view.com/signatures
