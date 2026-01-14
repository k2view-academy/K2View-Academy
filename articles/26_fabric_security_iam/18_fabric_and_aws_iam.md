
## How Fabric Leverages AWS IAM in a Kubernetes Environment

This article explains how Fabric leverages AWS IAM to authenticate and authorize access to AWS services such as AWS S3 in a Kubernetes environment.

There are three AWS components working together to secure the Kubernetes-to-S3 connection. Since Fabric runs on Kubernetes, we are **not using static access keys**. Instead, authentication is performed using short-lived credentials via AWS STS and IRSA. 

Here are the key pieces to help understand how AWS IAM is employed by Fabric. 

**Core Components**

**IAM Role** – A set of permissions, not a user.
It has:

- A **Trust Policy** (who is allowed to assume the role)
- A **Permissions Policy** (what the role can access, e.g., the S3 bucket)

**AWS STS (Security Token Service)** – Issues **temporary credentials**.
When Fabric needs S3 access, it calls STS, which validates authorization and returns short-lived keys that expire automatically.

**IRSA (IAM Roles for Service Accounts)** – The Kubernetes ↔ AWS bridge.
It allows a Kubernetes **ServiceAccount** to be mapped to an **IAM Role**, so pods can obtain AWS credentials without any keys stored in config or UI.

**How This Works in Fabric**

When you configure the S3 interface in Fabric/Studio:

1. Fabric runs in a pod using a specific Kubernetes **ServiceAccount**.
2. That ServiceAccount is mapped to an **IAM Role ARN** (as Miron mentioned).
3. Fabric automatically calls **AWS STS** to obtain temporary credentials.

When leveraging IAM, because credentials are provided via the environment, when configuring, for example, an AWS S3 interface, the **Access Key and Secret Key fields can be left empty** in the interface's UI.

**Cross-Account Scenario (Cluster in Account A, S3 in Account B)**

There are two AWS accounts involved:

**Account A – Compute:** Hosts the Kubernetes cluster and Fabric.

**Account B – Storage:** Hosts the S3 bucket and the IAM Role.

To enable access:

- The **IAM Role is created in Account B** (same account as the S3 bucket).
- The Role’s **Trust Policy allows the Kubernetes OIDC identity from Account A** to assume it.
- In Fabric, we configure the **Role ARN from Account B**.
- At runtime, Fabric uses STS to **assume the role in Account B** and access the bucket.

This is a standard AWS cross-account role assumption using STS, with IRSA handling identity mapping from Kubernetes.
