# Fabric Web Studio for Docker Compose, version 2.1, – Docker Compose Installation

This document describes how to install and configure K2view Fabric Web Studio 2.1 using Docker Compose.

Version 2.1 introduces minor enhancements to Fabric Web Studio including updated Fabric runtime, increased heap size defaults, improved healthcheck, simplified runtime overrides, and additional `k2space.sh` options.

## Table of Contents

1. [Prerequisites](#prerequisites)
2. [What's in this Package](#whats-in-this-package)
3. [Things to Configure](#things-to-configure)
4. [Things to Know](#things-to-know)
5. [Installation](#installation)
6. [Docker Image Offline Package Download](#docker-image-offline-package-download)


## Prerequisites
Please review the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/About.md#prerequisites">Prerequisites</a> topic first. There are essential steps for installing and configuring Docker described in the prerequisites section. Please review these and the prerequisites.

A crucial step is to ensure that Docker and the Docker Compose plugin are properly installed. These are described in the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/About.md#required-3rd-party-software">3rd Party Software</a> topic, and the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md">Docker and Docker Compose Installation</a> topic. 

> Please use the native Docker Compose plugin and not the deprecated Python-based docker-compose utility.

If installing Docker on Microsoft Windows, it is essential to review the instructions for <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md#using-the-windows-subsystem-for-linux-wsl">installing and using WSL</a>. If running Docker Desktop on Microsoft Windows, you also need to  <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md#running-docker-desktop"> enable the WSL Linux distribution</a>.


> Docker Desktop includes the Docker Compose plugin by default. WSL2 is strongly recommended when using Docker Desktop on Windows.

## What's in this Package

1. `README.md` — installation reference.
2. `k2space.sh` — a Bash shell script that is used for creating, listing, and destroying spaces that are defined by Web Studio profiles. This script is used to start Fabric and the embedded Traefik reverse proxy. It can allocate additional heap space if required and override the default Fabric version specified in the .env file.
3. `.env` — defines various Fabric and Git parameters.
4. `.env-tdmspace` — sample environment overrides for specific Spaces.
5. `common.config` — common runtime configuration.
6. `studio_*.config` — Fabric profile configurations.
7. `compose.yaml` — base Docker Compose configuration.
8. `tls-config.yaml` — TLS configuration for Traefik.

## Things to Configure
1. Git Configuration - this is described in <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#step-4-configure-git-and-tls">Step 4: Configure Git and TLS</a> of the Installation section below.
2. TLS Certificate and Private Key Configuration - optional, as Traefik uses its own self-signed TLS certificate for HTTPS connections by default. The Certificate is created for you by default for the machine. To provide your own, please refer to <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#step-4-configure-git-and-tls">Step 4: Configure Git and TLS</a>. 


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
   - Please note that persistent files created by Fabric Web Studio and the database instance you install will store their data in your installation directory's "persistent-data" folder (e.g., K2view/Studio/persistent-data). Your Fabric Space's data is stored in the persistent-data/spacename directory. The respective space's directory will contain data if you create multiple spaces.
   - The location of the persistent data directory is configured in the `.env` file and set by default to be in the Fabric Web Studio installation directory.

4. Command Line Options
   Please refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Operating.md">k2space.sh command options</a> topic to learn about overrides and settings. 
   
5. Running on Microsoft Windows
   - You need to use a Windows Subsystem for Linux (WSL) and a Linux distribution-mounted file system for the installation to avoid slow performance issues. Please refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md">Docker and Docker Compose Installation</a> topic.

## Installation

Various steps should be taken to get Fabric Web Studio up and running within the Fabric Docker Compose Runtime environment:

1. [Step 1: Install and Validate Docker and Docker Compose Runtime](#step-1-install-and-validate-docker-and-docker-compose-runtime)
2. [Step 2: Setup](#step-2-setup)
3. [Step 3: Download](#step-3-download)
4. [Step 4: Configure Git and TLS](#step-4-configure-git-and-tls)
5. [Step 5: Select a Fabric Blueprint Profile to Use](#step-5-select-a-fabric-blueprint-profile-to-use)
6. [Step 6: Log in to K2view's Nexus Container Registry](#step-6-log-in-to-k2views-nexus-container-registry)
7. [Step 7: Create and Launch a Fabric Space](#step-7-create-and-launch-a-fabric-space)
8. [Step 8: Access Web Studio](#step-8-access-web-studio)


### Before you proceed, confirm that you have a K2view Nexus Container Registry Account

You need to obtain credentials to access the K2view Nexus. Your K2view account representative can arrange this for you. If you do not have access, please contact your K2view representative, who can provide steps to help you through this process.

### **Step 1**: Install and Validate Docker and Docker Compose Runtime

If Docker has not already been installed on your machine, please refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md">Docker and Docker Compose Installation</a> topic. 

The easiest and recommended way to get Docker Compose is to install Docker Desktop. Docker Desktop includes Docker Compose, Docker Engine, and Docker CLI, and all prerequisites for Compose. Please also refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md">Docker and Docker Compose Installation</a> topic.

> Docker Desktop includes Docker Compose plugin by default starting with version 2.x. Please ensure you're using the plugin-based Compose implementation and not the deprecated Python-based docker-compose utility.

### **Step 2**: Setup

You can download the distribution (recommended) or use Git, “clone” the K2view Blueprints to "download" them. These blueprints incorporate the Fabric Docker Compose Runtime installation files. The K2view Blueprints are hosted on GitHub.com (Internet access is required). 

Whether you download or clone the files depends on the operating system you use. There are different instructions depending on whether you are using Linux or MacOS, than those for Microsoft Windows.

Please note that persistent files created by Fabric Web Studio and the database instance you install will store their data in the "persistent-data" folder of your installation directory (e.g., K2view/Studio/persistent-data). Your Fabric Space's data is stored in the persistent-data/spacename directory. The respective space's directory will contain data if you create multiple spaces. The location of the persistent data directory is configured in the `.env` file and set by default to be in the Fabric Web Studio installation directory. This is a per-space configuration. 

#### Using Linux or MacOS

*Select a Base Directory for your Download and Installation Directory Locations*

First, please select a location to download the distribution or clone the K2view Blueprint content. This *base* directory can also hold the Fabric Web Studio installation directory from which it will run. 

Use the *change directory* command on your shell to switch to the designated base directory:

```bash
cd [base directory]
```

*Create your Download and Installation Directory Location*

The location of this installation directory depends on your needs. On a Linux system, consider using `/opt/apps/` as a location for your applications. If you are installing on a Mac or Windows computer, the `/home/username` or `\users\username` directory, respectively, can be considered. 

 > Keep in mind that persistent files created by Fabric Web Studio and the database instance you install will host their data in your installation directory's "persistent-data" folder (e.g., `K2view/Studio/persistent-data`). Your Fabric Space's data is stored in the persistent-data/spacename directory. The respective space's directory will contain data if you create multiple spaces.

Using a shell, create a `K2view` directory to download K2view's Blueprints. You can also use the K2view directory to hold the K2view Fabric Web Studio Installation directory. We would recommend that you use K2view for this directory.

```bash
mkdir K2view
```

#### Using Microsoft Windows

Using a Linux file system is highly recommended when installing on Microsoft Windows. It can be installed with the Windows Subsystem for Linux (WSL) and a Linux distribution such as Ubuntu. Doing so avoids performance problems using Docker on a native Windows file system. Please refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/6-Docker-Compose.md">Docker and Docker Compose Installation</a> topic for instructions on how to install WSL and a Linux distribution.
> 
> Not only should you avoid using the Windows file system, but you should also avoid using WSL's `/mnt/c` mounted Windows file system. Instead, use the file system of the Linux distribution you installed, e.g., `/home/username/K2view`, to download and install the configuration files and store the workspace data created by Fabric Web Studio. 

#### Create the Base Directory

*Identify the Name of your WSL Linux Distribution*

First, find the path to your Linux distribution's location (e.g., Ubuntu used in these examples) by running `wsl --list` to obtain its name and then changing to its directory. If you enabled Windows Explorer navigation during WSL installation, you can navigate directly to it under Explorer's "Linux" sidebar icon.

```bash
wsl --list
```
This will return the following. Ubuntu will be shown if you installed it as your Linux distribution. 

```bash
Windows Subsystem for Linux Distributions:
docker-desktop (Default)
Ubuntu
```

*Change Directory to your Linux WSL Distribution*

Then, change directory to `\\wsl$\[distributionName]` - in this example "Ubuntu"

```bash
cd \\wsl$\Ubuntu
```

*Select a Base Directory for your Download and Installation Directory Locations*

Please select a directory where you will download the K2view Blueprints and install Fabric Web Studio. You can use your home directory in `\\wsl$\ubuntu\home\[username]`. For example:

```bash
cd \\wsl$\Ubuntu\home\[username]
```

*Create your Download and Installation Directory Locations*

Using a shell, create a `K2view` directory to download K2view's Blueprints. You can also use the K2view directory to hold the K2view Fabric Web Studio Installation directory. We recommend using K2view for this directory.

```bash
mkdir K2view
```

### **Step 3**: Download

There are two options to obtain the Docker Compose Runtime for Fabric Web Studio. You can download a zip file (recommended) or clone the content from K2view's Blueprints.

#### Option (Recommended): Download The Latest Version of Fabric Web Studio for Docker Compose

The recommended approach is to download the latest version of Fabric Web Studio for Docker Compose from this location: 

```bash
https://nexus.share.cloud.k2view.com/repository/k2view-download/web-studio/Studio-Docker-latest.zip
```

Then, change the directory to the K2view directory. Copy `Studio-Docker-latest.zip` to this directory, and unzip `Studio-Docker-latest.zip` to this directory. Then, rename the `Studio-Docker` directory as `Studio`.

```bash
cd K2view
# copy Studio-Docker-latest.zip to this directory
# unzip Studio-Docker-latest.zip to this directory
# rename Studio-Docker as Studio
```

The Studio directory contains the configuration, YAML, and the `k2space.sh` script files to configure and create your Fabric Web Studio spaces. Please refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#whats-in-this-package">What's in this Package</a> topic above for details about these files. 

You can now skip to <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#step-4-configure-git-and-tls">Step 4: Configure Git and TLS</a>.

#### Option: Clone the K2view Blueprints 

> You may prefer to use Git to clone the installation files; in this case, you can clone the K2view Blueprints, which provide these files.

Using a shell, change your directory to your K2view directory and run the following command to clone K2view Blueprints (this requires a prior installation of a Git client):

Using the prior example of the `K2view` directory:

```bash
cd K2view
git clone https://github.com/k2view/blueprints.git
```

This will create a `blueprints` directory with various subdirectories. The `Studio` subdirectory holds the Fabric Web Studio installation files. 

> You will find two subdirectories: `Docker` and `Podman`. For the Docker installation, use the content of the `Docker` subdirectory. 

#### Create an Installation Directory and Copy the Fabric Web Studio Files

We recommend running Fabric Web Studio within the `Studio` directory of the `K2view` directory. From the K2view directory, copy the `blueprints/Studio` directory as `Studio`. 

*Using Linux or MacOS*

From the K2view directory, copy the content of the `blueprints/Studio/Docker` to the `Studio` directory.

```bash
cp -r blueprints/Studio/Docker Studio
```

*Using the Microsoft Windows PowerShell*

You must use the Linux file system to store the Studio directory if you are using Microsoft Windows. Please review <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#step-2-setup">Step 2's</a> "Using Microsoft Windows" section for details.  

```bash
cp -r blueprints\Studio\Docker Studio
```

The Studio directory contains the configuration, YAML, and the `k2space.sh` script files to configure and create your Fabric Web Studio spaces. Please refer to the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#whats-in-this-package">What's in this Package</a> topic above for details about these files. 

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

Docker and its Compose extension must be running on the server to perform this step. 

Using the K2view Nexus Container Registry account provided to you, run the following command from the same directory that you have performed the git clone command - please note that you need to use sudo on some Linux systems, depending on your permissions:

```bash
docker login -u [YourAccount] https://docker.share.cloud.k2view.com
```

You will be asked to enter your password.

**Note**: The Docker login command and the `k2space.sh` bash shell script require Internet access to log in and pull K2view Fabric images from the K2view Nexus Container Registry at docker.share.cloud.k2view.com. 

> Should you not have Internet connectivity, you can use the <a href="/articles/98_installation_and_upgrade/Install_Fabric_Web_Studio_v2-1/Installation.md#docker-image-offline-package-download">Docker Image Offline Package Download</a> procedure to download the file on a separate machine and copy it to the local installation directory. The file, a Docker Image, is approximately 1.9 GB in size. The version of the image depends on what is configured in the `.env` file. You will need to download the same version.
>
> By following this procedure, when the `k2space.sh` script runs, the expected file will already be loaded on the local machine and will not need to be downloaded from the Internet. 


### **Step 7**: Create and Launch a Fabric Space

#### Specifying an Alternate Fabric Version

To specify a different or later version of Fabric to use with Fabric Web Studio, edit the `.env` file's `FABRIC_VERSION` parameter. 

Please contact your K2view representative to determine which versions of Fabric you can use.


#### **Space Naming**

When creating a space, its name must consist of only lowercase alphanumeric characters, hyphens, and underscores, and start with either a letter or a number. You cannot use uppercase characters. 

#### **Running k2space.sh on Microsoft Windows**

The `k2space.sh` file is a `bash` script. A Windows PowerShell-compatible script is not yet available. To run the `k2space.sh` script, start the `Git Bash` application offered by Git. Using `Git Bash` you can run the script after you change the directory to its location. 

If you have Git integration enabled within Windows Explorer, you can start `Git Bash` from Windows Explorer by navigating to the script's directory, right-clicking within the Explorer window, and selecting 'Show more options'. This will display an 'Open Git Bash here' menu item that can be used to start `Git Bash` to run `k2start.sh`. 

#### Create Spaces on Your Server

First, change directory to your Installation directory, e.g., `Studio`

```bash
cd Studio
```

**Ensuring you have Read-Other Permission on all .config files on Linux**

You may need to have Read-Other permissions on the .config files on a Linux system. To do so, use the `chmod 644 [file]` command using:

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
 - fabric Pulling                                                                    177.1s
 - init-fabric [⡀] 318.8MB / 1.964GB Pulling                                         177.1s
   - e7a390e229e3 Downloading [========>                                          ]  318.8MB/1.964GB   
```


### **Step 8**: Access Web Studio

You have completed the installation and are ready to access Fabric Web Studio over HTTP or HTTPS.

Open a browser and connect to `http://localhost/spacename`. 

You can also connect to Fabric remotely, using `https://[hostname or ip address]/spacename`.

> Traefik will default to using its own self-signed TLS certificates for HTTPS connections. The Certificate is created for you by default for the machine. If you want to use a certificate created by your organization, everything is pre-configured for you to do so. See the "Configuring TLS" topic above for instructions. 

When presented with the login screen, enter: 

  - Username: admin
  - Password: admin

If you access Fabric Web Studio, you have successfully installed it. 


## Docker Image Offline Package Download

The Docker login command and the `k2space.sh` bash shell script require Internet access to log in and pull K2view Fabric images from the K2view Nexus Container Registry at docker.share.cloud.k2view.com. 

If you do not have Internet connectivity, you can use the following procedure to download the file offline on a separate machine and then copy it to the local installation directory. The file, a Docker Image, is approximately 1.9 GB in size. The version of the image depends on what is configured in the `.env` file. You will need to download the same version.

Following this procedure, when the `k2space.sh` script runs, the expected file will have already been loaded on the local machine and will not need to be downloaded from the Internet. 

Here is the flow:

1. Pull the desired Image tag:

`docker pull docker.share.cloud.k2view.com/k2view/fabric-studio:8.1.7_22`
 
2. Save / compress the desired Image tag:

`docker save docker.share.cloud.k2view.com/k2view/fabric-studio:8.1.7_22 | gzip > k2view_fabric-studio_8.1.7_22.tar.gz`

3. Copy the `k2view_fabric-studio_8.1.7_22.tar.gz` file to the target machine.

4. On the target machine, load the image locally:

`docker load -i k2view_fabric-studio_8.1.7_22.tar.gz`

Doing this before you run the first `k2space.sh` command ensures the file is present on your system to create your first space and avoids downloading it from the Internet.

5. Use similar procedures to obtain the following:

* Traefik Reverse Proxy:

   `docker pull traefik:latest`
  
* Socat Relay:

  `docker pull alpine/socat`
  
* Database containers as required: Postgres 15.8, Cassandra 4.1.5

  `docker pull postgres:15.8`
  
  `docker pull cassandra:4.1.5`


 



