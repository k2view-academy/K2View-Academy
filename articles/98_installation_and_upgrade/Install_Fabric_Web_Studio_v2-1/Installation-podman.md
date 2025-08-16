# Fabric Web Studio for Podman, version 2.1 – Podman Installation

This document describes how to use **Podman Compose** to host K2view Fabric Web Studio. It outlines the setup, components, installation options, and features relevant to Podman-based container environments.

**Podman Compose Setup**: Fabric Web Studio can be deployed using **Podman Compose**, which allows users to run Compose YAML files with Podman’s container engine. Podman Compose orchestrates the Web Studio environment—including a selected Fabric profile, an embedded Fabric engine, and a Traefik reverse proxy—enabling the creation of multiple, isolated Fabric Spaces. This setup offers a lightweight and daemonless alternative to Docker-based runtimes, fully aligned with Enterprise Linux practices.

Version 2.1 introduces minor enhancements to Fabric Web Studio, including an updated Fabric runtime, increased heap size defaults, an improved health check, simplified runtime overrides, and additional `k2space.sh` options.

## Content

1. [Prerequisites](#prerequisites)
2. [What's in this Package](#whats-in-this-package)
3. [Things to Configure](#things-to-configure)
4. [Things to Know](#things-to-know)
5. [Installation](#installation)


## Prerequisites
Please review the <a href="https://support.k2view.com/Academy/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/About.html#Prerequisites">Prerequisites</a> topic first. There are essential steps for installing and configuring Docker described in the prerequisites section. Please review these and the prerequisites.

The Podman Compose setup for Fabric Web Studio requires the following system prerequisites.

### **Enterprise Linux Required**

A supported **Enterprise Linux distribution** (e.g., RHEL, AlmaLinux, Rocky Linux, CentOS Stream) is required for running Podman in a production-grade configuration.

### **3rd Party Software**

1. **Git**:
    Install Git from https://git-scm.com/downloads and follow the platform-specific instructions provided at [Installing Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

2. **Podman**:
    Install Podman using your distribution's package manager. For example, on RHEL-based systems:

   ```bash
   sudo dnf install -y podman
   ```

   Refer to the official documentation at https://podman.io/getting-started/installation for complete installation steps.

3. **Podman Compose**:
    Install **Podman Compose** to support Compose YAML workflows. Use either:

   - DNF:

     ```bash
     sudo dnf install -y podman-compose
     ```

   - Or pip:

     ```bash
     pip3 install --user podman-compose
     ```

> **Note**:  Podman Compose provides native compatibility for Compose YAML files with Podman pods.

**K2view Software**

1. The installation assumes you have Internet access, allowing you to obtain Fabric images from the K2view Nexus Container Registry and perform a Git clone on your machine. 
2. To obtain a Fabric Studio docker image, you need a K2view Nexus account. Your K2view representative can arrange this for you. 

**Internet Access is Required**

Internet access is required to perform this installation. You will need access to:

1. K2view’s Nexus Docker Image repository at https://docker.share.cloud.k2view.com
2. (Optional) Github.com to clone K2view’s blueprints at https://github.com/k2view/blueprints.git
3. If you plan to install TDM, you need access to K2view’s Exchange.

## What's in this Package

1. README.md - This document
2. K2space.sh - A Bash shell script used to create, list, and destroy spaces defined by Web Studio profiles. This script is used to start Fabric and the embedded Traefik reverse proxy. It can allocate additional heap space if required and override the default Fabric version specified in the .env file.
3. .env file - define various Fabric and Git parameters
1. .env-tdmspace - a sample file with a set of parameters that will override the default configuration defined in the .env file that will apply to a Space named "tdmspace"
4. common.config file - define various Fabric and runtime configurations
5. Studio_*.config files - the Fabric Profiles to choose from
6. YAML files are used to configure the Fabric and Traefik services. You can use the tls-config.yaml file to configure the TLS certificate and private key. 


## Things to Configure
1. Git Configuration - this is described in <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation-podman.md#step-4-configure-git-and-tls">Step 4: Configure Git and TLS</a> of the Installation section below.
2. TLS Certificate and Private Key Configuration - optional, as Traefik uses its own self-signed TLS certificate for HTTPS connections by default. The Certificate is created for you by default for the machine. To provide your own, please refer to <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation-podman.md#step-4-configure-git-and-tls">Step 4: Configure Git and TLS</a>. 


## Things to Know
1. The default administrator credentials are:
   
   - Username: admin
   - Password: admin

2. Ports
   Traefik employs the following ports:

   - HTTP: Port 8080 - Traefik dashboard
   - HTTP: Port 80 - HTTP listener
   - HTTPS: Port 443 - HTTPS listener
  
3. Your Data Files
   Please note that persistent files created by Fabric Web Studio and the database instance you install will store their data in the "persistent-data" folder of your installation directory (e.g., K2view/Studio/persistent-data). Your Fabric Space's data is stored in the persistent-data/spacename directory. The respective space's directory will contain data if you create multiple spaces.
   - The location of the persistent data directory is configured in the `.env` file and set by default to be in the Fabric Web Studio installation directory.


## Installation

Various steps should be taken to get Fabric Web Studio up and running within the Fabric Docker Compose Runtime environment:

1. [Step 1: Install and Validate the Podman Runtime](#step-1-install-and-validate-the-podman-runtime)
2. [Step 2: Setup](#step-2-setup)
3. [Step 3: Download](#step-3-download)
4. [Step 4: Configure Git and TLS](#step-4-configure-git-and-tls)
5. [Step 5: Select a Fabric Blueprint Profile to Use](#step-5-select-a-fabric-blueprint-profile-to-use)
6. [Step 6: Log in to K2view's Nexus Container Registry](#step-6-log-in-to-k2views-nexus-container-registry)
7. [Step 7: Create and Launch a Fabric Space](#step-7-create-and-launch-a-fabric-space)
8. [Step 8: Access Web Studio](#step-8-access-web-studio)


**Before you proceed, confirm that you have a K2view Nexus Container Registry Account**

You need to obtain credentials to access the K2view Nexus. Your K2view account representative can arrange this for you. If you do not have access, please contact your K2view representative, who can provide steps to help you through this process.

### **Step 1**: Install and Validate the Podman Runtime

If Podman is not already installed on your machine, refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Podman.md">Podman and Podman Compose Installation</a> topic. 

The recommended approach is to install **Podman** using your Linux distribution's package manager. This will install the Podman engine and CLI tools. You must also install **Podman Compose**, which provides Compose YAML support for Podman-based environments.

Please follow the steps in the **Podman and Podman Compose Installation** guide to complete the installation and validate your setup. Please refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Podman.md">Podman and Podman Compose Installation</a> topic.


### **Step 2**: Setup

You can download the distribution (recommended) or use Git, “clone” the K2view Blueprints to "download" them. These blueprints incorporate the Fabric Docker Compose Runtime installation files. The K2view Blueprints are hosted on GitHub.com (Internet access is required). 

Please note that persistent files created by Fabric Web Studio and the database instance you install will store their data in the "persistent-data" folder of your installation directory (e.g., K2view/Studio/persistent-data). Your Fabric Space's data is stored in the persistent-data/spacename directory. The respective space's directory will contain data if you create multiple spaces. The location of the persistent data directory is configured in the `.env` file and set by default to be in the Fabric Web Studio installation directory. This is a per-space configuration. 

*Select a Base Directory for your Download and Installation Directory Locations*

First, please select a location to download the distribution or clone the K2view Blueprint content. This *base* directory can also hold the Fabric Web Studio installation directory from which it will run. 

Use the *change directory* command on your shell to switch to the designated base directory:

```bash
cd [base directory]
```

*Create your Download and Installation Directory Location*

Using a shell, create a "K2view" directory. You can also use the K2view directory to hold the K2view Fabric Web Studio Installation directory. We recommend using K2view for this directory.

```bash
mkdir K2view
```


### **Step 3**: Download

There are two options to obtain Fabric Web Studio. You can download a zip file or clone the content from K2view's Blueprints.

#### Option 1: Download The Latest Version of Fabric Web Studio for Podman

You can download the latest version of Fabric Web Studio for Podman from this location: 

```bash
https://nexus.share.cloud.k2view.com/repository/k2view-download/web-studio/Studio-Podman-latest.zip
```

Then, change the directory to the K2view directory. Copy `Studio-Podman-latest.zip` to this directory, and unzip `Studio-Podman-latest.zip` to this directory. Then, rename the `Studio-Podman` directory as `Studio`.

```bash
cd K2view
# copy Studio-Podman-latest.zip to this directory
# unzip Studio-Podman-latest.zip to this directory
# rename Studio-Podman as Studio
```


The Studio directory contains the configuration, YAML, and the `k2Space.sh` script files to configure and create your Fabric Web Studio spaces. Please refer to the What's in this Package topic above for details about these files. 

You can now skip to Step 4.

#### Option 2: Clone the K2view Blueprints 

Using a shell, change your directory to your K2view directory and run the following command to clone K2view Blueprints (this requires a prior installation of a Git client):

Using the prior example of the `K2view` directory:

```bash
cd K2view
git clone https://github.com/k2view/blueprints.git
```

This will create a `blueprints` directory with various subdirectories. The `Studio` subdirectory holds the Fabric Web Studio installation files. 

#### Create an Installation Directory and Copy the Fabric Web Studio Files

We recommend running Fabric Web Studio within the `Studio` directory of the `K2view` directory. From the K2view directory, copy the `blueprints/Studio` directory as `Studio`. 

From the K2view directory

```bash
cp -r blueprints/Studio/ Studio
```
The Studio directory contains the configuration, YAML, and the `k2Space.sh` script files to configure and create your Fabric Web Studio spaces. Please refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation-podman.md#whats-in-this-package">What's in this Package</a> topic above for details about these files. 

### **Step 4**: Configure Git and TLS

#### Configuring Git

You should consider a few things, including configuring a Git repository for your project. Though not mandatory, it is a best practice to store your project files in Git (or in a Git-compliant code repository). 

You can configure it before the creation of Fabric Space via the .env file. You can do so after starting Fabric Web Studio using its built-in Git client. Perform this step within Fabric Web Studio.

**Configuring Git before Creating your Fabric Space**

To do this, you must provide a token, a path to your Git repository, and the appropriate branch. You can create your initial space without this configuration. However, to configure it later, you must configure these values and recreate your space. 

To configure Git, open the .env file and specify the following in the Git Integration section:

  - **GIT_REPO** - the Git repository URI to clone and store your project data. 
    - **Important Note: Please do not prepend "HTTPS://" before the repository's URI**.

  - **GIT_BRANCH** - the Git branch to use; the default is 'master'.
  - **GIT_TOKEN** - the token used to authenticate to your Git repository.  
  - **GIT_USERNAME** - the user name used to authenticate to your Git repository.

Fabric Web Studio will use these parameters to run an initial clone and Git operations. The initial clone performed will be: 

```bash
git clone --single-branch -b "${GIT_BRANCH}" "https://${GIT_TOKEN}@${GIT_REPO}"
```

#### Configuring TLS

Traefik will use its own self-signed TLS certificates for HTTPS connections by default. The Certificate is created for you by default for the machine. If you want to use a certificate created by your organization, everything is pre-configured for you to do so. You need to open the `ssl-certs` directory within the installation package's directory (e.g., `K2view/Studio`), where you will find the `ssl-certs` directory and replace the certificate and private key files. 

These files must be named `cert.cer` and `cert.key`, respectively. The TLS certificate must be in PEM format and contain the server, root, and intermediate certificates, should they exist.

If you perform this step after the initial installation, you must restart Fabric for these to take effect.

### **Step 5**: Select a Fabric Blueprint Profile to Use

There are four profiles, each of which embeds Fabric. The default is 'studio'.  

1. **studio**. The default Web Studio profile embeds SQLite as its system database.
2. **studio_pg**. A generic Studio or TDM profile - Web Studio with PostgreSQL for use with its System DB and TDM.
3. **studio_cass**. A TDM profile - Web Studio with Cassandra used for the System DB and TDM.
4. **studio_pg_cass**. A TDM profile incorporating Apache Cassandra for its System DB and PostgreSQL for TDM tasks.

Using the default profile, 'studio', you will not need to provide the profile on the `k2space.sh` command line. Otherwise, you will need to enter one of the other profiles. 


### **Step 6**: Log in to K2view's Nexus Container Registry

*Prerequisite*

Podman must be installed and properly configured to access the container registry. If running in rootless mode, ensure the Podman socket is active.

Using the K2view Nexus Container Registry credentials provided to you, run the following command from the same directory where you performed the `git clone`:

```bash
podman login -u [YourAccount] https://docker.share.cloud.k2view.com
```

You will be prompted to enter your password.

Depending on your environment and user permissions, you may need to prepend `sudo` to the command on some Linux systems.

> Tip: You can verify a successful login by running `podman info` and confirming registry authentication.



To ensure the **Podman socket** is active in **rootless mode**, follow these steps:

#### Step-by-Step: Ensure Podman Socket is Active (Rootless Mode)

**Check the status of the socket**

```bash
systemctl --user status podman.socket
```

- If the service is **inactive** or **not found**, proceed to the next steps.

**Enable and start the socket**

```bash
systemctl --user enable --now podman.socket
```

This will:

- Enable the socket to start automatically on login
- Start the socket immediately



**Enable linger (so user services run after logout)**

```bash
loginctl enable-linger $USER
```

- This allows systemd user services (like the Podman socket) to run after the user logs out.
- This step is essential for non-interactive or headless environments.

**Verify the socket is now listening**

```bash
systemctl --user status podman.socket
```

You should see output like:

```bash
Active: active (listening)
```





### Podman Image Offline Package Download

The Podman login command and the `k2space.sh` bash shell script require Internet access to log in and pull K2view Fabric images from the K2view Nexus Container Registry at docker. share. cloud. k2view.com.

If your target machine **does not have Internet connectivity**, you can follow this **offline download procedure** to transfer the required image from another system. The Fabric image is approximately **1.9GB**, and its version must match the value specified in your local `.env` file.

By preloading the image locally, the `k2space.sh` script can create a Fabric Space without needing to download the image from the registry.

#### Offline Image Transfer Procedure

**Save and compress the desired Fabric image on an online machine:**

```bash
podman save docker.share.cloud.k2view.com/k2view/fabric-studio:X.Y.Z_0 | gzip > k2view_fabric-studio_X.Y.Z_0.tar.gz
```

**Transfer the image file to your target (offline) machine:**

Use `scp`, USB, or any secure method to copy `k2view_fabric-studio_X.Y.Z_0.tar.gz` to the target environment.

**Load the image into Podman on the offline machine:**

```bash
podman load -i k2view_fabric-studio_X.Y.Z_0.tar.gz
```

This will make the image available to the local Podman image store.

> **Important**: Ensure that the image version matches the value in your `.env` file before executing `k2space.sh`. This ensures proper image resolution and prevents unnecessary fetch attempts.

### **Step 7**: Create and Launch a Fabric Space

#### **Space Name**

When creating a space, its name must consist of only lowercase alphanumeric characters, hyphens, and underscores, and start with either a letter or a number. You cannot use uppercase characters. 

#### Create Spaces on Your Server

First, change the directory to your Installation directory, e.g., `Studio`

```bash
cd Studio
```

**Ensuring you have Read-Other Permission on all .config files on Linux**
You may need to have Read-Other permissions on the .config files on a Linux system. To do so use the `chmod 644 [file]` command using:

```bash
 chmod 644 *.config
```

**Ensuring you have Execute Permission on Linux**
You may need to make `k2space.sh` executable on a Linux system. To do so, use the `chmod` command using:

```bash
 chmod 700 k2space.sh
```

**Running the k2space.sh Script**
You can create multiple Fabric spaces on your server. To do so, use the `k2space.sh` script as shown here. 

 > On some Linux systems, you may need to prefix the command with `sudo`.

```bash
 ./k2space.sh create [--profile=profile-name] spacename
```

You can omit passing in a `-- profile` parameter to use the default profile, 'studio'. 

```bash
 ./k2space.sh create spacename
```

Otherwise, please use the following --profile commands:

1. **studio_pg**. A generic Studio or TDM profile - Web Studio with PostgreSQL for use with its System DB and TDM. 
   
```bash
 ./k2space.sh create --profile=studio_pg spacename
```

2. **studio_cass**. A TDM profile - Web Studio with Cassandra used for the System DB and TDM. 
   
```bash
 ./k2space.sh create --profile=studio_cass spacename
```

3. **studio_pg_cass**. A TDM profile incorporating Apache Cassandra for its System. 

```bash
 ./k2space.sh create --profile=studio_pg_cass spacename
```

#### The Initial Installation

You will download Fabric from the K2view Nexus Container Registry when creating your first Fabric Space. While this is happening, you should observe the following.

```bash
$ ./k2space.sh create myspace
[+] Running 0/3
 - fabric Pulling                                    177.1s
 - init-fabric [⡀] 318.8MB / 1.964GB Pulling         177.1s
   - e7a390e229e3 Downloading [========> ]  318.8MB/1.964GB   
```


### **Step 8**: Access Web Studio

You have completed the installation and are ready to access Fabric Web Studio over HTTP or HTTPS.

Open a browser and connect to `http://localhost/spacename`. 

You can also connect to Fabric remotely, using `https://[hostname or IP address]/spacename`.

> Traefik will default to using its own self-signed TLS certificates for HTTPS connections. The Certificate is created for you by default for the machine. If you want to use a certificate created by your organization, everything is pre-configured for you to do so. See the "Configuring TLS" topic above for instructions. 

When presented with the login screen, enter: 

  - Username: admin
  - Password: admin

If you access Fabric Web Studio, you have successfully installed it. 





