# System Requirements, Installation, and Upgrade Overview

The Installation & Upgrade section provides the foundation for installing and upgrading Fabric, Fabric Web Studio, and Test Data Management (TDM). Each deployment option—on-premises, cloud virtual machines, or Kubernetes—has distinct requirements to ensure stability, performance, and scalability.

This section connects system requirements directly to the installation and upgrade guides, helping you plan, size, and maintain your environments consistently across all deployment models.

**System Requirements**: Baseline specifications (CPU, memory, disk, network) for on-premises servers and equivalent cloud instance types. Separate recommendations are provided for <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/04_hardware_req_for_studio.md">Fabric Web Studio</a>, <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/02_hardware_req_for_dev_qa.md">Development/QA</a>, <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/00_hardware_requirements_for_POV.md">Proof-of-Value (POV)</a>, and <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/03_hardware_req_for_prod.md">Production environments</a>.

**Installation Guidance**: Step-by-step procedures for deploying Fabric, TDM, GenAI Data Fusion, and Fabric Web Studio on <a href="/articles/98_installation_and_upgrade/Install_on_Linux/README.md">Fabric, TDM, or GenAI Data Fusion on Linux servers</a>, <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/README.md">Fabric Web Studio within Docker/Podman containers</a>, or <a href="/articles/98_installation_and_upgrade/Install_on_Kubernetes/README.md">Fabric, TDM, or GenAI Data Fusion on Kubernetes clusters</a>.

**Upgrade Procedures**: Recommended approaches for upgrading Fabric and TDM, including database backup, storage considerations, binary replacement, and validation steps. Kubernetes-specific upgrade strategies are also provided for self-managed clusters orchestrated by K2Cloud.

**Installation Scenarios**

Different components and deployment options require different system setups. Use the table below to navigate to the correct requirements:

**Installation Scenarios**

<table>
  <thead>
    <tr>
      <th>Deployment Type</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>1. Fabric Web Studio (On-Premises)</td>
      <td>
        Install Fabric Web Studio on dedicated Linux servers.
        Refer to
        <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/04_hardware_req_for_studio.md">
          Linux/Docker Hardware Requirements
        </a> and 
        <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/README.md">
          Fabric Web Studio Installation
        </a> 
      </td>
    </tr>
    <tr>
      <td>2. Fabric and TDM (On-Premises or Cloud VMs)</td>
      <td>
        Deploy Fabric and TDM directly on Linux servers,
        either on-premises or on cloud provider instances sized to match the same hardware requirements.
        See
        <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/README.md">
          Linux/Docker Hardware Requirements
        </a>, 
        <a href="/articles/98_installation_and_upgrade/Install_on_Linux/README.md">
          Fabric Installation on Linux
        </a>, and
        <a href="/articles/98_installation_and_upgrade/Install_TDM/README.md">
          TDM Installation
        </a>.
      </td>
    </tr>
    <tr>
      <td>3. Fabric, TDM, and Fabric Web Studio (Kubernetes)</td>
      <td>
        Run Fabric, TDM, and Fabric Web Studio as self-managed Kubernetes deployments,
        orchestrated via K2Cloud Orchestrator. Refer to
        <a href="/articles/98_installation_and_upgrade/Hardware_K8s/README.md">
          Kubernetes Requirements
        </a>, and 
        <a href="/articles/98_installation_and_upgrade/Install_on_Kubernetes/README.md">
          Fabric Installation on Kubernetes
        </a>.        
      </td>
    </tr>
  </tbody>
</table>


**How to Use This Section**

1. Identify your deployment scenario (on-premises server, cloud VM, or Kubernetes cluster).
2. Review the corresponding hardware requirements in this section to ensure adequate resources.
3. Follow the installation guides for your chosen deployment type.
4. Consult the upgrade procedures to keep your environment current, stable, and secure.
