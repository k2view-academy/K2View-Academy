# Installation - Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0

## Prerequisites

The Docker Compose Runtime for Fabric Services has specific prerequisites. 

### Host Machine

The supported processor architecture is AMD64.  Fabric does not support ARM-based processors.

The amount of RAM you need will depend on your use case. 32GB of memory should suffice to run your Docker Compose Runtime for Fabric Web Studio and the necessary integration. A 2GB Heap is allocated by default, which can be overridden. 

### 3rd Party Software

1. You need to install a Git client on the computer by downloading and installing it. You can download it from https://git-scm.com/downloads and follow the instructions provided at https://git-scm.com/book/en/v2/Getting-Started-Installing-Git

2. You need to install and run Docker, which you can download from https://docs.docker.com/engine/install/.
3. To install Docker and Docker Compose, which will host Docker Compose Runtime for Fabric Services, you need to have administrative rights on the machine:
   1. Linux: root or sudo access granting you administrative rights
   2. Windows: you need administrator rights on your machine

4. The Docker Compose Runtime for Fabric Services requires Linux. You can also use Microsoft Windows if you use the Windows Subsystem for Linux (WSL) in conjunction with a Linux distribution. Instructions are provided in this article’s `Docker and Docker Compose Installation` section. 
5. You need to install the Docker Compose Plugin. Please note that if you install Docker Desktop, Docker Compose is bundled. See https://docs.docker.com/compose/install/. Please use the native Docker Compose plugin and not the Python-based docker-compose utility. 

### K2view Software

1. The installation presumes you have Internet access, so you can obtain Fabric images from the K2view Nexus Container Registry and perform a Git clone on your machine. 
2. To obtain a Fabric Studio docker image, you need a K2view Nexus account. Your K2view representative can arrange this for you. 

### Internet Access is Required

Internet access is required to perform this installation. You will need access to:

1. Github.com to clone K2view’s blueprints at https://github.com/k2view/blueprints.git
2. K2view’s Nexus Docker Image repository at https://docker.share.cloud.k2view.com
3. If you plan to install TDM, you need access to K2view’s Exchange.


## What's in this Package

1. K2space.sh - A Bash shell script used to create, list, and destroy spaces defined by Web Studio profiles. This script is used to start Fabric and the embedded Traefik reverse proxy. It can allocate additional heap space if required and override the default Fabric version specified in the .env file.
2. .env file - define various Fabric and Git parameters
3. common.config file - define various Fabric and runtime configurations
4. Studio_*.config files - four Fabric Profiles to choose from
5. YAML files are used to configure the Fabric and Traefik services. You can use the tls-config.yaml file to configure the TLS certificate and private key. 


## Things to Configure
1. Git Configuration - This is described in Step 5 - Configuring Git and TLS
2. TLS Certificate and Private Key Configuration - Optional because Traefik uses its own self-signed TLS certificate for HTTPS connections by default.  To provide your own, please refer to Step 5. 


## Things to Know
1. Default administrator credentials are

   1. Username: admin
   2. Password: admin

2. Ports: Traefik employs the following ports:

   | Protocol | Port | Description       |
   | -------- | ---- | ----------------- |
   | HTTP     | 8080 | Traefik dashboard |
   | HTTP     | 80   | HTTP listener     |
   | HTTPS    | 443  | HTTPS listener    |


## Installation

There are five steps to carry out to get Fabric Web Studio up and running within the Fabric Docker Compose Runtime environment:

* **Step 1** - Install and Validate Docker and Docker Compose
* **Step 2** – Obtain the K2view Fabric Docker Compose Runtime Blueprint
* **Step 3** - Login to K2view's Nexus Container Registry
* **Step 4** – Configure Git and TLS
* **Step 5** - Select a Fabric Blueprint Profile to Use
* **Step 6** - Create and Launch a Fabric Space
* **Step 7** – Access Web Studio

## Before you proceed, confirm that you have a K2view Nexus Container Registry Account

You need to obtain credentials to access the K2view Nexus. Your K2view account representative can arrange this for you. If you do not have access, please contact your K2view representative, who can provide steps to help you through this process.

### **Step 1** - Install and Validate Your Docker Compose Runtime Environment

If Docker has not already been installed on your machine, follow the [Docker installation guide](https://docs.docker.com/engine/install/) from Docker's official documentation. Install https://docs.docker.com/compose/. 

The easiest and recommended way to get Docker Compose is to install Docker Desktop. Docker Desktop includes Docker Compose, Docker Engine, and Docker CLI, which are prerequisites for Compose. See https://docs.docker.com/compose/install/ for more information.

If you are using Windows, you must first set up and use WSL. Please consult the `Docker and Docker Compose Installation` section below for instructions. 

### **Step 2** – Obtain the K2view Fabric Docker Compose Runtime Blueprint

After installing a Git client on your machine, you must “clone” the K2view Blueprints. These blueprints incorporate the Fabric Docker Compose Runtime blueprint. They are hosted on GitHub.com, so Internet access is required. 

Select a directory to host the K2view Blueprints and within your shell's *change directory* command:

```bash
cd [selected directory]
```

Using a shell, change to your Git directory and run the following command:

```bash
git clone https://github.com/k2view/blueprints.git
```

### **Step 3** - Login to K2view's Nexus Container Registry

Using the K2view Nexus Container Registry account provided to you, run the following command from the same directory you performed the git clone command from: 

```bash
docker login -u [YourAccount] https://docker.share.cloud.k2view.com
```

You will be asked to enter your password.

**Note**: the Docker login command and the k2space.sh shell script requires Internet access to log in and pull K2view Fabric images from the K2view Nexus Container Registry at docker.share.cloud.k2view.com. 

### **Step 4** – Configure Git and TLS

#### Configuring Git

You should consider a few things, including configuring a Git repository for your project. Though not mandatory, it is a best practice to store your project files in Git (or a Git-compliant code repository). 

To do this, you must provide a token, a path to your Git repository, and the appropriate branch. You can create your initial space without this configuration. However, to configure it later, you must configure these values and recreate your space. 

To configure Git, open the .env file and specify the following in the Git Integration section:

* GIT_REPO  - The Github repository URI to clone and store your project data. 
  * **Important Note: Please do not prepend "HTTPS://" before the repository's URI**.

* GIT_BRANCH - The Git branch to use. The default is "master".
* GIT_TOKEN - Token used to authenticate to your GitHub repository.  

Fabric Web Studio will use these parameters to run an initial clone and Git operations. The initial clone performed will be: 

```bash
git clone --single-branch -b "${GIT_BRANCH}" "https://${GIT_TOKEN}@${GIT_REPO}"
```

#### Configuring TLS

Traefik will use its own self-signed TLS certificates for HTTPS connections by default. If you want to use your certificate, everything is pre-configured for you. You need to create an `ssl-certs` directory within the installation package's directory where you find k2vingress-compose.yaml file. and place your certificate and private key within this directory. These files must be named `cert.cer` and `cert.key` respectively.

The TLS certificate must be in PEM format and contain the server, root, and intermediate certificates, should they exist.

To enable the use of your certificates, uncomment the `certFile` and `keyFile` parameters in the file `tls-config.yaml` file. If you configure your certificates after you have created your Fabric Space, restart Traefik using the instructions below.

### **Step 4** - Select a Fabric Blueprint Profile to Use

There are four profiles that each embeds Fabric to choose from. The default is "studio.config".  

1. **studio.config**. The default Web Studio profile embeds SQLite for its System DB.
2. **studio_pg.config**. A generic Studio or TDM profile - Web Studio with PostgreSQL for use with its System DB and TDM.
3. **studio_cass.config**. A TDM profile - Web Studio with Cassandra used for the System DB and TDM.
4. **studio_pg_cass.config**. A TDM profile incorporating Apache Cassandra for its System DB and PostgreSQL for TDM tasks.

If you use the default "studio.config," you will not need to provide the profile on the k2space.sh command line. Otherwise, you will need to enter one of the other profiles. 

### **Step 6** - Create and Launch a Fabric Space

#### **Space Naming**

When creating a space, its name must consist only of lowercase alphanumeric characters, hyphens, and underscores and start with a letter or number.

#### Create Spaces on Your Server

You can create multiple Fabric Spaces on your server. To do so, use the k2space.sh script as shown here:

```bash
./k2space.sh create [--profile=profile-name] spacename
```

To use the default "studio.config", you can omit passing in a --profile parameter. 

```bash
./k2space.sh create spacename
```

Otherwise, please use the following --profile commands:

1. **studio_pg.config**. A generic Studio or TDM profile - Web Studio with PostgreSQL for use with its System DB and TDM.
```bash
./k2space.sh create --profile=studio_pg spacename
```

2. **studio_cass.config**. A TDM profile - Web Studio with Cassandra used for the System DB and TDM.
```bash
./k2space.sh create --profile=studio_cass spacename
```

3. **studio_pg_cass.config**. A TDM profile incorporating Apache Cassandra for its System 

```bash
./k2space.sh create --profile=studio_pg_cass spacename
```

### **Step 7** – Access Web Studio

You have completed the installation and are ready to access Fabric Web Studio over HTTP. 

Open a browser and connect to http:*//localhost/spacename*

You can also connect to Fabric remotely using *http://[hostname or ip address]/spacename*

When presented with the login screen, enter: 

* Username: admin
* Password: admin

If you access Fabric Web Studio, you have successfully installed it. 



## Docker and Docker Compose Installation
If Docker has not already been installed, follow the Docker installation guide from Docker's official documentation. Install docker from: [https://docs.docker.com/compose/](https://docs.docker.com/compose/).

The easiest and recommended way to get Docker Compose is to install Docker Desktop. Docker Desktop includes Docker Compose, Docker Engine, and Docker CLI, which are prerequisites for Compose. See [https://docs.docker.com/compose/install/](https://docs.docker.com/compose/install/). for more information.

### Install Docker and Docker Compose on Linux

1.	You need to install and run Docker that you can download from [https://docs.docker.com/compose/install/](https://docs.docker.com/compose/install/).
2.	The Docker Compose Runtime for Fabric Services requires Linux.  
3.	You need to install the Docker Compose Plugin. Just so you know, if you install Docker Desktop, Docker Compose is bundled. See https://docs.docker.com/compose/install/. Please use the native Docker Compose plugin and not the Python-based docker-compose utility. 

### Install Docker and Docker Compose on Microsoft Windows
1.	You need to install and run Docker that you can download from [https://docs.docker.com/compose/install/](https://docs.docker.com/compose/install/).
2.	The Docker Compose Runtime for Fabric Services requires Linux or Microsoft Windows if you use the Windows Subsystem for Linux (WSL) in conjunction with a Linux distribution. 
3.	You need to install the Docker Compose Plugin. Please note that if you install Docker Desktop, Docker Compose is bundled. See https://docs.docker.com/compose/install/. Please use the native Docker Compose plugin and not the Python-based docker-compose utility. 

####	Using the Windows Subsystem for Linux (WSL)
When using Microsoft Windows, you must run Docker inside the WSL file system in conjunction with a Linux distribution. Doing otherwise will not work.

####	Installing WSL
1.	Open PowerShell as Administrator.

2.	Install WSL and a Linux distribution (e.g., Ubuntu):
   `wsl –install`
3.	List the installed WSL distribution:
	`wsl -l`
4.	You cannot use the default “docker-desktop” distribution provided by Microsoft. You need to install a Linux distribution. 

   `wsl --install -d <distribution_name>`

e.g., wsl --install -d Ubuntu

5.	You need to set the default to use the Linux distribution you selected. It might be listed as item 2 for example. To set the default use this command

   `wsl --set-default-version 2`
6.	You can now launch WSL using the desired distribution using the WSL command:

   `wsl`

###	Using the WSL File System
In step 4 and when starting Fabric Docker Compose Runtime, it is essential that you do not use the Windows file system, for example, from within the /mnt/c directory. 
Avoid running commands within the Windows file system (e.g., /mnt/c) this will cause Fabric to fail to run correctly. Rather, navigate to the WSL file system (e.g., /home/username).



