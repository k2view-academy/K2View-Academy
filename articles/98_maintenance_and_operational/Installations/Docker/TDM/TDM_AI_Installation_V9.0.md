# TDM with AI Installation Document

This document describes infrastructure and application setup for TDM with AI integration.

## Table of contents
1. [Pre-requisites](#pre-requisites)
2. [GPU-based GKE creation](#gpu-based-gke-creation)
    - [VPC](#vpc)
    - [GKE](#gke)
    - [Admin Token](#admin-token)
3. [Create a space with TDM space profile](#create-a-space-with-tdm-space-profile)
4. [Configure Fabric Interfaces](#configure-fabric-interfaces)
5. [Configure Environment](#configure-environment)
6. [Project Configuration](#project-configuration)
7. [Additional explanation about the LU schemas (export / import)](#additional-explanation-about-the-lu-schemas-export--import)
8. [Training task process](#training-task-process)
9. [Generation task process](#generation-task-process)
10. [Possible override generated fields](#possible-override-generated-fields)
11. [Manual cleanup flow ](#manual-cleanup-flow)

## Pre-requisites
- K2view cloud site
- GCP account
- GCP project with necessary permissions to create:
    - VPC
    - GKE
    - AlloyDB (optional)
    - Training task image in Artifact Registry
    - Generation task image in Artifact Registry
- GCP quota for
    - At least 1 NVIDIA A100 40GB GPU
    - At least 12 A2 CPUs
- gcloud installed
- kubectl installed

## GPU-based GKE creation
### VPC
1. Open GCP console
2. Go to VPC network section and click on "Create VPC network"
3. Populate the following:
    - VPC Name
    - Subnet creation mode: Custom
    - Subnet Name
    - Region
    - IPv4 range

Keep the default values for the rest of the parameters.

### GKE
1. Open GCP console
2. Go to Kubernetes Engine -> Clusters
3. Click "Create"
4. Click "Configure" in "Standard: You manage your cluster" option
5. Populate the following:
- Cluster basics
    - Name
    - Location type: Zonal
    - Zone (choose the zone from the region that you created in the VPC section of this document)
- Default Pool
    - Name
    - Number of nodes: 1
    - Nodes
        - Machine configuration: GPUs
        - GPU type: NVIDIA A100 40GB
        - Number of GPUs: 1
        - GPU Driver installation: Google-managed
        - Select a driver version: Latest
        - Machine type: a2-highgpu-1g (12 vCPU, 6 core, 85 GB memory)
        - Boot disk size: 500
- Networking
    - Network: choose the network that you created in the VPC section of this document
    - Node subnet: choose the subnet that you created in the VPC section of this document
6. After all the parameters are populated, click "Create"
7. After the cluster is created, connect it to your kubectl by clicking on the name of your cluster -> Connect -> Command-line access. Copy the content, paste and run in your terminal.
8. Verify the installation
```bash
kubectl config current-context # Check the kubectl is connected to your cluster
kubectl get ns # Check that you can see the namespaces of the cluster
```

### Admin Token
Admin token enables fabric to interact with K8S API server using HTTP/HTTPS interface

1. Create "admin-user.yaml" file
2. Paste the following content in it
```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: full-admin-user
  namespace: kube-system
---
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: full-admin-user
roleRef:
  apiGroup: rbac.authorization.k8s.io
  kind: ClusterRole
  name: cluster-admin
subjects:
  - kind: ServiceAccount
    name: full-admin-user
    namespace: kube-system
---
apiVersion: v1
kind: Secret
type: kubernetes.io/service-account-token
metadata:
  name: full-admin-user-secret
  namespace: kube-system
  annotations:
    kubernetes.io/service-account.name: full-admin-user
```
3. Apply the file
```bash
kubectl apply -f admin-user.yaml
```
4. Get the token
```bash
token=$(kubectl -n kube-system describe secret $(kubectl -n kube-system get secret | grep "full-admin-user-secret" | awk '{print $1}')| grep token: |awk '{print $2}')
echo $token

# Alternative way to see the token
kubectl describe secrets/full-admin-user-secret -n kube-system
```
5. Copy the token, you will need in later steps

## Create a space with TDM space profile
TBD (Sivan)
## Configure Fabric Interfaces
- AI_DB
- AI_Execution (add k8s admin token)
## Configure Environment
- Globals
    - public static final String AI_ENVIRONMENT = "AI";
    - public static final String AI_ENTITIES_INTERFACE = "AI_DB";
    - public static final String AI_K2SYSTEM_INTERFACE = "AI_DB";
    - public static final String CREATE_AI_K2SYSTEM_DB = "true";
- mTables
    - TrainingMappingTables.csv
    - TrainingSpecialFields.csv
## Project Configuration
- Creation of the K2system schema/tables – explain that it will be done by the TDM deploy flow if the AI global is set to true
- Additional explanation about the k2system AI tables
## Additional explanation about the LU schemas (export / import)
- adding creditionals in TrainingDataSubset.flow stage 5 actor kubeBuildMedoidInfraInit1
## Training task process
- Extract of subset – a subset can be taken directly from Fabric or be extracted from the source.
- Export to the AI DB – create the LU schema if not exists and load the LU schema table + k2system tables
- Training process
    - When the training completes => update the status of the task in the k2system tables + TDM tables
## Generation task process
- Populate a request on the k2system tables
- Start a generation process
    - When the generation process ends – it populates the entity list + the LU schema tables with the generated entities
- Import the generated entities into Fabric and update the TDM DB parameters with the generated entities
## Possible override generated fields
TBD (Sivan)
## Manual cleanup flow 
TBD (Sivan)