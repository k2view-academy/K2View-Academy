# How Fabric Leverages AWS IAM in a Kubernetes Environment

## Table of Contents

- [Overview](#overview)
- [Summary](#summary)
- [Mechanism: How AWS-based IAM Access Works](#mechanism-how-aws-based-iam-access-works)
  - [Core AWS Components](#core-aws-components)
  - [Runtime Flow (End-to-End)](#runtime-flow-end-to-end)
- [What the User Sees in Fabric UI (Important)](#what-the-user-sees-in-fabric-ui-important)
- [Configuring the IAM Role ARN (K2cloud Agent)](#configuring-the-iam-role-arn-k2cloud-agent)
- [How This Becomes a ServiceAccount Annotation](#how-this-becomes-a-serviceaccount-annotation)
- [Cross-Account Scenario (Common Enterprise Pattern)](#cross-account-scenario-common-enterprise-pattern)
- [One Role per Pod](#one-role-per-pod)

## Overview

Fabric supports AWS IAM–based authentication for accessing native AWS services (such as S3, Secrets Manager, KMS) without using static access keys or secrets.

This is achieved using:

- AWS IAM Roles
- AWS STS (Security Token Service)
- IRSA (IAM Roles for Service Accounts)
- Kubernetes ServiceAccounts
- Fabric Agent Helm configuration

The key concept is that Fabric pods inherit AWS permissions from a Kubernetes ServiceAccount, which is annotated with an IAM Role ARN.

The ARN is not configured in the Fabric UI, but via Helm values, and credentials are resolved automatically at runtime.

## Summary

The ARN is not entered in the UI.

It is injected into the Fabric pod via Helm, ServiceAccounts, and IRSA.

To use AWS IAM with Fabric:

1. Create an IAM Role with required permissions
2. Configure trust for EKS OIDC
3. Set the role ARN in Helm:

```
secrets:
  SPACE_SA_ARN: "<IAM Role ARN>"
```

4. Deploy Fabric
5. Leave AWS credentials blank in the Fabric UI

Fabric handles everything else automatically.

## Mechanism: How AWS-based IAM Access Works

### Core AWS Components

**IAM Role**

An IAM Role defines:

- _Who_ may assume the role (Trust Policy)
- _What_ permissions are granted (Permissions Policy)

**AWS STS**

AWS STS issues short-lived credentials when a trusted identity assumes a role.

These credentials rotate automatically and are never stored.

**IRSA (IAM Roles for Service Accounts)**

IRSA bridges Kubernetes and AWS by allowing a Kubernetes ServiceAccount to assume an IAM Role via an OIDC identity.

No access keys are stored in:

- Fabric UI
- Kubernetes Secrets
- Environment variables

### Runtime Flow (End-to-End)

1. Fabric runs inside a Kubernetes pod.
2. The pod uses a specific Kubernetes ServiceAccount.
3. That ServiceAccount is annotated with an IAM Role ARN.
4. AWS recognizes the pod’s identity via OIDC.
5. Fabric requests credentials from AWS STS.
6. STS returns temporary credentials.
7. Fabric uses those credentials to access AWS services (e.g., S3).

**Result:** Secure, short-lived IAM access with no static secrets.

## What the User Sees in Fabric UI (Important)

When configuring an **S3 interface in Fabric Studio**:

- **Access Key ID** → _leave empty_
- **Secret Access Key** → _leave empty_

This is expected and correct.

Fabric will automatically fallback to using IAM credentials provided by the pod environment.

## Configuring the IAM Role ARN (K2cloud Agent)

**Key Point:** Where the ARN Is Configured

The IAM Role ARN is configured at deployment time via Helm — not in the Fabric UI.

**Primary Setting (Fabric Spaces)**

To set the AWS IAM role ARN for Fabric spaces, which are used to access cloud resources, you use this secret.

```
secrets:
	SPACE_SA_ARN: "arn:aws:iam::<account-id>:role/<FabricRole>"
```

**Purpose**

- This ARN is passed by the Agent to the Fabric Helm chart
- It is applied when creating Fabric Spaces (namespaces)

**Effect**

Each Fabric space’s ServiceAccount is annotated with this IAM Role upon creation.

## How This Becomes a ServiceAccount Annotation

During space creation:

1. The Agent deploys Fabric into a Kubernetes namespace.
2. A ServiceAccount is created for the Fabric pod.
3. The ServiceAccount receives an annotation:

```
metadata:
	annotations:
		eks.amazonaws.com/role-arn: arn:aws:iam::<account-id>:role/<FabricRole>
```

4. The Fabric pod runs using this ServiceAccount.
5. AWS IRSA handles role assumption automatically.

## Cross-Account Scenario (Common Enterprise Pattern)

**Example**

- **Account A**: EKS cluster + Fabric
- **Account B**: S3 bucket

**How it works**

1. IAM Role is created in Account B
2. Trust policy allows OIDC identity from Account A
3. SPACE_SA_ARN references the role in Account B
4. Fabric pod assumes the role via STS
5. S3 access works cross-account

This is a standard AWS cross-account role assumption.

## One Role per Pod

- A pod can use only one ServiceAccount
- Therefore, Fabric uses one IAM Role per Fabric pod
- That role must include all required permissions:
    - S3
    - Secrets Manager
    - KMS
    - Databases (if applicable)
