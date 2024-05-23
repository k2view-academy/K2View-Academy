# TDM with AI installation document

This document describes the infrastructure and application setup for TDM with AI integration.

## Table of Contents
1. [Pre-requisites](#pre-requisites)
2. [GPU-based GKE creation](#gpu-based-gke-creation)
    - [VPC](#vpc)
    - [GKE](#gke)
    - [Admin Token](#admin-token)
3. [TDM project configuration](#tdm-project-configuration)
4. [Manual cleanup flow](#manual-cleanup-flow)
5. [AI tests results](#ai-tests---performance-results)

## Pre-requisites
- K2view cloud site
- GCP account
- GCP project with the necessary permissions for creating:
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
5. Copy the token. The token is needed for later steps

## TDM project configuration
Click [here](/articles/TDM/tdm_implementation/17_tdm_ai_generation_implementation.md) for TDM-AI project implementation guidelines.

## Manual cleanup flow 
Cleanup process AICleanUp.flow located under TDM LU with inputs CleanALL Boolean if true the flow deletes all data in AI_execution and AI_DB else populate the TrainingExecutionIDs array that holds all training models that needs cleaning for example [1551,1660] the flow extracts all generations done by these training models and deletes all related data.

## AI Tests - Performance Results
Training Results :
![ai training](https://github.com/k2view-academy/K2View-Academy/blob/Academy_8.0_TDM_9.0/articles/TDM/tdm_implementation/images/training_resutls.png)
Generation Results : 
![ai generation](https://github.com/k2view-academy/K2View-Academy/blob/Academy_8.0_TDM_9.0/articles/TDM/tdm_implementation/images/generation_results.png)
