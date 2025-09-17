# How to Stop/Start/Restart Fabric Pods Including Rolling Restarts (Technical Note)

## Abstract

The *How to Stop/Start/Restart Fabric Pods Including Rolling Restarts* technical note provides guidance and best practices for managing Fabric services in Kubernetes environments, including how to stop, start, and restart pods with minimal disruptions. It describes three main approaches:

* **Using the K2cloud Orchestrator** to pause and resume environments—this is the recommended method for cloud-managed setups, offering graceful shutdowns and restarts while preserving sequencing and availability.
* **Using Kubernetes commands directly** for environments where more control is needed: full restarts (scale down/up), rolling restarts to update pods incrementally, or restarting individual pods.
* **Restarting from within a pod** when debugging or applying service-level restarts without impacting the whole environment.

It emphasizes maintaining high availability, configuration consistency, and minimal downtime, and gives command examples (e.g., `kubectl scale`, `kubectl rollout restart`, `kubectl delete pod`, and `k2fabric restart`) tailored to different scenarios.

## Link

<ul>
		<li><a href="/articles/44_administration_and_operations/Kubernetes_Operations_and_Scaling/Technical_Note_How_to_Stop_Start_Restart_Fabric_Pods.pdf">How to: Stop/Start/Restart Fabric Pods</a>
    </li>	
</ul>

## Keywords

Stop Fabric Pods, Start Fabric Pods, Restart Fabric Pods, Rolling Restart, Kubernetes, StatefulSet, kubectl commands, K2cloud Orchestrator, Pause/Resume, Full Restart, Pod-level Restart, Debugging, High Availability, Configuration Consistency, Minimal Downtime, Environment Management, Kubernetes Namespace, Service Restart, Orchestrator Managed Environments

