# Fabric scale within Kubernetes Deployment
Fabric cluster is deployed in Kubernetes where each pod represents a Fabric node.

Kubernetes is designed to automatically scale the number of pods in a deployment or replica set based on observed metrics like CPU utilization, using its Horizontal Pod Autoscaling (HPA) mechanism.

The HorizontalPodAutoscaler (HPA) controller calculates desired replicas based on the ratio of the current metric value to the desired value: 
desiredReplicas = [currentReplicas * ( currentMetricValue / desiredMetricValue )].

This article deals with K8S auto-scaling concepts with regards to Fabric aspects and does not refer to a specific or custom K8S autoscaler.

## Prerequisites
1. Kubernetes Version: Ensure deployment on Kubernetes version 1.18 or later. Verify version compatibility from official Kubernetes documentation.
2. Metrics Server: Deploy the Metrics Server in the cluster, HPA utilizes it in order to gather resource metrics from pods and nodes.

## Scaling Strategies for Fabric Pods
To effectively harness the scalability of Fabric pods, the approach varies based on the use case:
* For loads stemming from a high number of Web Services (WS's) calls, it's advisable to employ a Load Balancer (LB) that directs traffic to all Fabric pods.
* When the load is due to the quantity of Fabric jobs, jobs are redistributed as new nodes are integrated into the cluster.

### Example

```yaml
apiVersion: autoscaling/v1
kind: HorizontalPodAutoscaler
metadata:
    labels:
        tenant: tenant
        space: space
        app: fabric
    name: fabric-hpa
spec:
    scaleTargetRef:
        apiVersion: apps/v1
        kind: deployment
        name: fabric
    minReplicas: 1
    maxReplicas: 3
    targetCPUUtilizationPercentage: 80
```



For more information about an advanced setup, read below:

<ul>
    <li><a href="https://kubernetes.io/docs/tasks/run-application/horizontal-pod-autoscale/">horizontal pod autoscale (HPA)</a></li>
    <li><a href="https://github.com/kubernetes-sigs/metrics-server#deployment">Metrics server</a></li>
</ul>
