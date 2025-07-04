
## System Requirements, Installation, and Upgrade Overview

The Installation & Upgrade section provides a comprehensive roadmap for planning, installing, and upgrading your Fabric, Fabric Web Studio, and Test Data Management (TDM) environments. Whether deploying in Kubernetes, on standalone Linux servers, or for proof-of-value scenarios, the subsections contained will help you make informed decisions and successfully complete your installation.

## What The Installation & Upgrade Section Covers

- **Installation Requirements**  
  Understand the key factors affecting sizing, including workloads, environments, and concurrency. This section will help you estimate your CPU, memory, and storage needs, depending on whether you’re deploying on Kubernetes or standalone Linux.
  - **Kubernetes Requirements** – Provides detailed specifications for clusters, nodes, Helm versions, ingress controllers, and Kubernetes-native prerequisites for a successful Fabric deployment.
  - **Linux / Docker Requirements** – Outlines hardware recommendations for development, QA, production, and POV/POC deployments, including CPU, memory, and disk sizing per component.

- **Installation Steps**  
  - **TDM Installation** – Guides you through deploying the TDM component, including optional AI capabilities for advanced synthetic data generation.
  - **Fabric Web Studio Installation** – Covers deploying the Fabric Web Studio, the visual environment for managing Fabric projects, and configuring its runtime dependencies.
  - **Fabric Installation** – Includes dedicated instructions for both Kubernetes-based and standalone Linux installations, helping you choose and follow the right path for your environment.

- **Upgrades**  
  - **Fabric and TDM Upgrade Procedures** – Step-by-step instructions for upgrading existing installations, including backing up databases, replacing binaries, performing migrations, and verifying updates.
  - **Fabric Kubernetes Space Upgrades** – Special guidance for updating individual Fabric spaces within Kubernetes deployments without requiring a complete reinstallation.

## How to Use This Section

1. **Select the appropriate Installation Requirements section** depending on whether you’re deploying on Kubernetes or Linux.
2. **Follow the relevant Installation instructions** to deploy Fabric, TDM, and Fabric Web Studio in your environment.
3. **Use the Upgrade sections** to plan or carry out updates on existing installations.

