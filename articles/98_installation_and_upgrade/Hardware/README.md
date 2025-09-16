# System Requirements, Installation, and Upgrade Overview

The Installation & Upgrade section provides the foundation for installing and upgrading Fabric, Fabric Web Studio, and Test Data Management (TDM). Each deployment option—on-premises, cloud virtual machines, or Kubernetes—has distinct requirements to ensure stability, performance, and scalability.

This section connects system requirements directly to the installation and upgrade guides, helping you plan, size, and maintain your environments consistently across all deployment models.

---

**System Requirements**

Baseline specifications (CPU, memory, disk, network) for on-premises servers and equivalent cloud instance types. Separate recommendations are provided for <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/04_hardware_req_for_studio.md">Fabric Web Studio</a>, <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/00_hardware_requirements_for_POV.md">Proof-of-Value (POV)</a>, <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/02_hardware_req_for_dev_qa.md">Development/QA</a>, and <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/03_hardware_req_for_prod.md">Production environments</a>.

---

**Installation Guidance**

Step-by-step procedures for deploying Fabric, <a href="/articles/98_installation_and_upgrade/Install_TDM/README.md">TDM</a>, <a href="/articles/AI_fusion/01_overview.md">GenAI Data Fusion</a>, and <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/README.md">Fabric Web Studio</a> within <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/README.md">Docker/Podman containers (Fabric Web Studio)</a>, on <a href="/articles/98_installation_and_upgrade/Install_on_Linux/README.md">Linux servers (Fabric, TDM, or GenAI Data Fusion)</a>, and on <a href="/articles/98_installation_and_upgrade/Install_on_Kubernetes/README.md">Kubernetes clusters (Fabric, TDM, or GenAI Data Fusion)</a>.

---

**Upgrade Procedures**

Recommended approaches for upgrading Fabric and TDM, including database backup, storage considerations, binary replacement, and validation steps. Kubernetes-specific upgrade strategies are also provided for self-managed clusters orchestrated by K2Cloud.


---

**Installation Scenarios**

Different components and deployment options require different system setups. Use the table below to navigate to the correct requirements:

<table>
  <thead>
    <tr>
      <th>Deployment Type</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Fabric Web Studio (On-Premises)</td>
      <td>
        Install Fabric Web Studio on dedicated Linux servers.
        Refer to
        <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/04_hardware_req_for_studio.md">
          Linux System Requirements
        </a> and 
        <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/README.md">
          Fabric Web Studio Installation.
        </a> 
      </td>
    </tr>
    <tr>
      <td>Fabric and TDM (On-Premises or Cloud VMs)</td>
      <td>
        Deploy Fabric and TDM directly on Linux servers,
        either on-premises or on cloud provider instances sized to match the same hardware requirements.
        See
        <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/README.md">
          Linux System Requirements,
        </a> 
        <a href="/articles/98_installation_and_upgrade/Install_on_Linux/README.md">
          Fabric Installation on Linux,
        </a> and
        <a href="/articles/98_installation_and_upgrade/Install_TDM/README.md">
          TDM Installation.
        </a>
      </td>
    </tr>
    <tr>
      <td>Fabric, TDM, and Fabric Web Studio (Kubernetes)</td>
      <td>
        Run Fabric, TDM, and Fabric Web Studio as self-managed Kubernetes deployments,
        orchestrated via K2Cloud Orchestrator. Refer to
        <a href="/articles/98_installation_and_upgrade/Hardware_K8s/README.md">
          Kubernetes Requirements
        </a> and 
        <a href="/articles/98_installation_and_upgrade/Install_on_Kubernetes/README.md">
          Fabric Installation on Kubernetes.
        </a>       
      </td>
    </tr>
    <tr>
      <td>Fabric and GenAI Data Fusion</td>
      <td>
        Run Fabric and GenAI Data Fusion on a Linux VM or Kubernetes. GenAI Data Fusion extends Fabric’s data exposure capabilities to support retrieval-augmented workflows for large language models. The Fabric platform is responsible for retrieving structured and unstructured data, applying masking and enrichment, and preparing the context that is ultimately provided to an LLM. 
        <br><br>Please refer to the
        <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/00_hardware_requirements_for_POV.md">
          Proof of Value Environment System Requirements,
        </a> and either the
        <a href="/articles/98_installation_and_upgrade/Hardware_Linux_Docker/04_hardware_req_for_studio.md">
          Linux System Requirements
        </a> or <a href="/articles/98_installation_and_upgrade/Hardware_K8s/README.md">
          Kubernetes Requirements.
        </a> 
        <br><br> Please follow the respective installation instructions for the installation on a 
        <a href="/articles/98_installation_and_upgrade/Install_on_Linux/README.md">
          Linux VM
        </a> or <a href="/articles/98_installation_and_upgrade/Install_on_Kubernetes/README.md">
          on Kubernetes.
        </a> 
      </td>
    </tr>
  </tbody>
</table>

---

**How to Use This Section**

1. *Identify your deployment scenario* (on-premises server, cloud VM, or Kubernetes cluster).

2. *Review the corresponding hardware requirements* in this section to ensure adequate resources.

3. *Follow the installation guides* for your chosen deployment type.

4. *Consult the upgrade procedures* to keep your environment current, stable, and secure.
