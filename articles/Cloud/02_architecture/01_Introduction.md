# Architecture Introduction

The K2cloud platform is comprised from two pivotal components that synergize to streamline the deployment, scaling, and management of various Fabric runtime environments:

* **Kubernetes**, Esteemed, production-grade container orchestration infrastructure system, renowned for its automation prowess. Kubernetes empowers K2cloud with a suite of features and capabilities:
  * Isolated Namespaces: Ensures the deployment of fabric containers within fully encapsulated runtime environments.
  * High Availability: Guarantees continuous operation and minimal downtime.
  * Auto-Scaling: Dynamically adjusts resources to meet workload demands.
  * Comprehensive Management: Adeptly handles secrets, storage, and configurations.
  * Universal Compatibility: Supported across all cloud providers and deployable in multi-availability zones.
  * Versatile Deployment: Operable in both cloud and on-premises environments.
* **Cloud Orchestrator**: The conductor complementing Kubernetes infra system is the Cloud Orchestrator, designed to facilitate users throughout the Fabric project lifecycle. Its main functional roles:
  * Space construction and lifecycle management: Directs Kubernetes in the creation and upkeep of spaces - the Fabric runtime environments.
  * Metadata management: Administers the metadata of projects, profiles, sites, and spaces.
  * Access governance: Integrates with CyberArk to establish robust permissions and access control mechanisms.

Together, these components form the K2cloud platform, with its very high flexibility of what and where deploying spaces. 
