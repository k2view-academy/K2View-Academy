![image](https://github.com/k2view-academy/K2View-Academy/assets/104299047/4bae6090-46ad-4c4a-a73f-1169d22d7ac8)

# Fabric Space Upgrade and Rollback Procedure
This document details the upgrade and rollback procedure for upgrading the Fabric application that runs on a Kubernetes cluster.
Here's the updated upgrade and rollback procedure for the Fabric application running on Kubernetes, with Step 1 modified to include a command that lists the `fabric-deployment`, container versions, and their state.

---

### Upgrade Procedure for Fabric on Kubernetes

**Note**: This procedure is valid for either only minor upgrades or all versions above 8.0.
Major upgrades may require additional steps, such as schema migrations, configuration changes, or downtime planning. Always consult the specific upgrade documentation for major version changes.


**Prerequisites:**
- Ensure you have kubectl access to the Kubernetes cluster.
- Backup existing configuration and data as required.
- Check that the code is correctly committed and pushed to the Git repository.
- Verify the current state of the deployment and pods.

---

#### Step 1: Check the Current Status of the Fabric Deployment

1. Run the following command to list the `fabric-deployment`, including container versions and their state in the `space-k2view` namespace:
   ```bash
   kubectl get deployment fabric-deployment -n space-k2view -o=custom-columns='NAME:.metadata.name,READY:.status.readyReplicas,AVAILABLE:.status.availableReplicas,IMAGE:.spec.template.spec.containers[0].image'
   ```
   
   This command outputs the name of the deployment, the number of ready and available replicas, and the current image version of the container.

2. Identify the current running deployment, pods, and replica sets based on the output.

#### Step 2: Check the Current Version of the Deployment

1. To check which version of the Fabric deployment is currently running, use the following command with a filter to display the image version:
   ```bash
   kubectl get deployment fabric-deployment -n space-k2view -o=jsonpath='{.spec.template.spec.containers[0].image}'
   ```
   
   This command will output the current image version used by the deployment.

#### Step 3: Update the Deployment Image

1. Use the `kubectl set image` command to update the deployment with the new image version:
   ```bash
   kubectl set image deployment/fabric-deployment fabric-container=<new-image>:<tag> -n space-k2view
   ```
   
   Replace `<new-image>:<tag>` with the specific image name and tag for the new version.

2. Verify the image update by checking the rollout status:
   ```bash
   kubectl rollout status deployment/fabric-deployment -n space-k2view
   ```

#### Step 4: Verify the Upgrade

1. Check the status of the new pods to ensure they are running correctly:
   ```bash
   kubectl get pods -n space-k2view
   ```

#### Step 5: Verify Web  Functionality

1. Verify that the Fabric is functioning as expected:
   - Access the Web  and generate a piece of test code to ensure that it operates correctly.
   
2. Check that the generated code is committed and pushed to the Git repository:
   - Navigate to the relevant Git repository and verify the presence of the latest commits.
   - Confirm that the commits correspond to the generated code from Web .

---

### Rollback Procedure for Fabric on Kubernetes

If the upgrade fails or any issues are encountered, follow the steps below to roll back to the previous version.

#### Step 1: Rollback to the Previous Revision

1. Use the following command to initiate a rollback to the previous deployment version:
   ```bash
   kubectl rollout undo deployment/fabric-deployment -n space-k2view
   ```

2. Verify the rollback status:
   ```bash
   kubectl rollout status deployment/fabric-deployment -n space-k2view
   ```

#### Step 2: Verify the Rollback

1. Check the status of the pods to ensure they are running the previous stable version:
   ```bash
   kubectl get pods -n space-k2view
   ```

2. Confirm that the Fabric is functioning as expected post-rollback:
   - Generate/Run a code in Web  and verify its functionality.

3. Confirm that the application is functioning as expected post-rollback.

---

**Note:** After performing either an upgrade or a rollback, document the actions taken and validate the application's functionality thoroughly before proceeding with any further steps.

---
### General Troubleshooting

If you encounter issues during the upgrade or rollback process, consider the following general troubleshooting steps:

1. **Check Logs:** Review the logs of the Fabric deployment for any error messages or warnings:
   ```bash
   kubectl logs deployment/fabric-deployment -n space-k2view
   ```

2. **Inspect Events:** Use Kubernetes events to identify potential issues with pods, deployments, or other resources:
   ```bash
   kubectl get events -n space-k2view --sort-by=.metadata.creationTimestamp
   ```

3. **Resource Status:** Verify the status of related resources such as services, config maps, and persistent volume claims to ensure they are functioning correctly:
   ```bash
   kubectl get svc,configmap,pvc -n space-k2view
   ```

4. **Network Connectivity:** Ensure that there are no network issues or blocked connections affecting Fabric's ability to operate correctly. This can include checking network policies, service endpoints, and DNS resolutions within the cluster.

5. **Check Pod Health:** Use the `describe` command to get detailed information about pod failures or restarts:
   ```bash
   kubectl describe pod <pod-name> -n space-k2view
   ```

6. **Resource Limits and Requests:** Review the resource limits and requests for Fabric pods to ensure they are not being throttled or evicted due to insufficient resources.

7. **Cluster Health:** Ensure the overall health of the Kubernetes cluster is good, and there are no node issues, resource shortages, or other factors that could affect the deployment.

8. **Contact Support:** If issues persist, consider reaching out to support with relevant logs, steps taken, and any specific errors encountered.

---

### Appendix: ClusterRole and ClusterRoleBinding Configuration

To ensure that the user running the `kubectl` commands has the necessary permissions, the following `ClusterRole` and `ClusterRoleBinding` configurations should be applied.

#### ClusterRole Configuration

Create or update a `ClusterRole` named `fabric--manager` with the required permissions by saving the following configuration in a file named `fabric--clusterrole.yaml`:

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRole
metadata:
  name: fabric--manager
rules:
  - apiGroups: ["", "apps"]
    resources:
      - pods
      - pods/exec
      - deployments
      - replicasets
      - services
      - configmaps
      - persistentvolumeclaims
    verbs:
      - get
      - list
      - watch
      - create
      - delete
      - deletecollection
      - patch
      - update
      - edit
  - apiGroups: ["apps"]
    resources:
      - deployments/rollback
      - deployments/scale
    verbs:
      - update
  - apiGroups: ["extensions"]
    resources:
      - replicasets
    verbs:
      - delete
```

Apply the `ClusterRole` configuration:

```bash
kubectl apply -f fabric--clusterrole.yaml
```

#### ClusterRoleBinding Configuration

Bind the `ClusterRole` to the user executing the `kubectl` commands. Create or update the `ClusterRoleBinding` by saving the following configuration in a file named `fabric--clusterrolebinding.yaml`:

```yaml
apiVersion: rbac.authorization.k8s.io/v1
kind: ClusterRoleBinding
metadata:
  name: fabric--manager-binding
subjects:
  - kind: User
    name: <username>  # Replace with the actual username
    apiGroup: rbac.authorization.k8s.io
roleRef:
  kind: ClusterRole
  name: fabric--manager
  apiGroup: rbac.authorization.k8s.io
```

Apply the `ClusterRoleBinding` configuration:

```bash
kubectl apply -f fabric--clusterrolebinding.yaml
```

---
