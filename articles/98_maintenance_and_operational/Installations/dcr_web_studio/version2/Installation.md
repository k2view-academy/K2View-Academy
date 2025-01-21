# Installation - Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0

## Prerequisites

The Docker Compose Runtime for Fabric Services has specific prerequisites. 

### Host Machine

The supported processor architecture is AMD64. Fabric does not support ARM-based processors.

The amount of RAM you need will depend on your use case. 32GB of memory should suffice to run your Docker Compose Runtime for Fabric Web Studio along with the necessary integration. A 2GB heap size is allocated by default, which can be overridden. 

### 3rd Party Software

1. You need to install a Git client on the computer by downloading and installing it. You can download it from https://git-scm.com/downloads and follow the instructions provided at https://git-scm.com/book/en/v2/Getting-Started-Installing-Git.

2. You need to install and run Docker. You also need to install the Docker Compose plugin. If you install Docker Desktop, then Docker Compose is bundled. Please use the native Docker Compose plugin and not the Python-based docker-compose utility. The installation links are:
   - Install Docker Desktop: https://docs.docker.com/compose/install/
   - Install Docker: https://docs.docker.com/engine/install/
     
3. To install Docker and Docker Compose, a platform that will host the Docker Compose Runtime for Fabric Services, you need to have administrative rights on the machine:
   - Linux: Root or sudo access grants you administrative rights
   - Windows: Administrator rights are required on your machine


### K2view Software

1. It is presumed that you have Internet access for the installation in order to obtain Fabric images from the K2view Nexus Container Registry and perform a Git clone on your machine. 
2. To obtain a Fabric Studio docker image, a K2view Nexus account is required. Your K2view representative can arrange this for you. 

### Internet Access is Required

Internet access is required to perform this installation. You will need access to:

1. Github.com to clone K2view’s blueprints at https://github.com/k2view/blueprints.git
2. K2view’s Nexus Docker Image repository at https://docker.share.cloud.k2view.com
3. If you plan to install TDM, you need access to K2view’s Exchange.


## What's in this Package

1. K2space.sh - a Bash shell script, which is used for creating, listing and destroying spaces defined by Web Studio profiles. This script is used for starting Fabric and the embedded Traefik reverse proxy. It can allocate additional heap space if required and override the default Fabric version specified in the .env file.
2. .env file - defines various Fabric and Git parameters
3. common.config file - defines various Fabric and runtime configurations
4. Studio_*.config files - four Fabric profiles to choose from
5. YAML files are used for configuring the Fabric and Traefik services. You can use the tls-config.yaml file for configuring the TLS certificate and private key. 


## Things to Configure
1. Git Configuration - this is described in 'Step 4 - Configure Git and TLS' of the Installation section below.
2. TLS Certificate and Private Key Configuration - optional, as Traefik uses its own self-signed TLS certificate for HTTPS connections by default. One is created for you by default for the machine. To provide your own, please refer to Step 4. 


## Things to Know
1. The default administrator credentials are:

   - Username: admin
   - Password: admin

2. Ports: Traefik employs the following ports:

   - HTTP: Port 8080 - Traefik dashboard
   - HTTP: Port 80 - HTTP listener
   - HTTPS: Port 443 - HTTPS listener


## Installation

Various steps should be taken to get Fabric Web Studio up and running within the Fabric Docker Compose Runtime environment:

  - **Step 1** – Install and Validate Docker and Docker Compose
  - **Step 2** – Obtain the K2view Fabric Docker Compose Runtime Blueprint
  - **Step 3** – Log in to K2view's Nexus Container Registry
  - **Step 4** – Configure Git and TLS
  - **Step 5** – Select a Fabric Blueprint Profile to Use
  - **Step 6** – Create and Launch a Fabric Space
  - **Step 7** – Access Web Studio

### Before you proceed, confirm that you have a K2view Nexus Container Registry Account

You need to obtain credentials to access the K2view Nexus. Your K2view account representative can arrange this for you. If you do not have access, please contact your K2view representative, who can provide steps to help you through this process.

### **Step 1** – Install and Validate Your Docker Compose Runtime Environment

If Docker has not already been installed on your machine, follow the [Docker installation guide](https://docs.docker.com/engine/install/) from Docker's official documentation. 

The easiest and recommended way to get Docker Compose is to install Docker Desktop. Docker Desktop includes Docker Compose, Docker Engine and Docker CLI, that are all prerequisites for Compose. See https://docs.docker.com/compose/install/ for more information.

### **Step 2** – Obtain the K2view Fabric Docker Compose Runtime Blueprint

After installing a Git client on your machine, you must “clone” the K2view Blueprints. These blueprints incorporate the Fabric Docker Compose Runtime blueprint. They are hosted on GitHub.com (Internet access is required). 

Select a directory to host the K2view Blueprints and within your shell's *change directory* command:

```bash
cd [selected directory]
```

Using a shell, change to your Git directory and run the following command:

```bash
git clone https://github.com/k2view/blueprints.git
```

### **Step 3** – Log in to K2view's Nexus Container Registry

Using the K2view Nexus Container Registry account provided to you, run the following command from the same directory that you have performed the git clone command: 

```bash
docker login -u [YourAccount] https://docker.share.cloud.k2view.com
```

You will be asked to enter your password.

**Note**: The Docker login command and the k2space.sh bash shell script require Internet access to log in and pull K2view Fabric images from the K2view Nexus Container Registry at docker.share.cloud.k2view.com. 

### **Step 4** – Configure Git and TLS

#### Configuring Git

You should consider a few things, including configuring a Git repository for your project. Though not mandatory, it is a best practice to store your project files in Git (or in a Git-compliant code repository). 

To do this, you must provide a token, a path to your Git repository, and the appropriate branch. You can create your initial space without this configuration. However, to configure it later, you must configure these values and recreate your space. 

To configure Git, open the .env file and specify the following in the Git Integration section:

  - GIT_REPO - the Github repository URI to clone and store your project data. 
    - **Important Note: Please do not prepend "HTTPS://" before the repository's URI**.

  - GIT_BRANCH - the Git branch to use; the default is 'master'.
  - GIT_TOKEN - the token used to authenticate to your GitHub repository.  

Fabric Web Studio will use these parameters to run an initial clone and Git operations. The initial clone performed will be: 

```bash
git clone --single-branch -b "${GIT_BRANCH}" "https://${GIT_TOKEN}@${GIT_REPO}"
```

#### Configuring TLS

By default, Traefik will use its own self-signed TLS certificates for HTTPS connections. One is created for you by default for the machine. If you want to use your certificate, everything is pre-configured for you. You need to open the `ssl-certs` directory within the installation package's directory, where you will find the k2vingress-compose.yaml file and replace the certificate and private key within this directory prepared for you with yours. 

These files must be named `cert.cer` and `cert.key`, respectively. The TLS certificate must be in PEM format and contain the server, root, and intermediate certificates, should they exist.

To enable the use of your certificates, uncomment the `certFile` and `keyFile` parameters in the `tls-config.yaml` file. If you configure your certificates after you have created your Fabric space, you can restart Traefik using the instructions below.

### **Step 5** – Select a Fabric Blueprint Profile to Use

There are four profiles that each embeds Fabric to choose from. The default is 'studio.config'.  

1. **studio.config**. The default Web Studio profile embeds SQLite for its System DB.
2. **studio_pg.config**. A generic Studio or TDM profile - Web Studio with PostgreSQL for use with its System DB and TDM.
3. **studio_cass.config**. A TDM profile - Web Studio with Cassandra used for the System DB and TDM.
4. **studio_pg_cass.config**. A TDM profile incorporating Apache Cassandra for its System DB and PostgreSQL for TDM tasks.

If you use the default profile, 'studio.config', you will not need to provide the profile on the k2space.sh command line. Otherwise, you will need to enter one of the other profiles. 

### **Step 6** – Create and Launch a Fabric Space

#### **Space Naming**

When creating a space, its name must consist of only lowercase alphanumeric characters, hyphens and underscores and start with either a letter or a number.

#### **Running k2space.sh on Microsoft Windows**

The `k2space.sh` file is a `bash` script. A Windows PowerShell-compatible script is not yet available. To run the `k2space.sh` script, start the `Git Bash` applications offered by Git. Using `Git Bash` you can run the script after you change the directory to its location. 

If you have Git integration enabled within Windows Explorer, you can also start `Git Bash` from Windows Explorer by navigating to the script's directory, right-clicking within the Explorer's window, and selecting 'Show more options'. This will display an 'Open Git Bash here' menu item that can be used to start `Git Bash` to run `k2start.sh`. 

#### Create Spaces on Your Server

You can create multiple Fabric spaces on your server. To do so, use the k2space.sh script as shown here:

```bash
./k2space.sh create [--profile=profile-name] spacename
```

To use the default 'studio.config', you can omit passing in a --profile parameter. 

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

You can also connect to Fabric remotely, using *http://[hostname or ip address]/spacename*

When presented with the login screen, enter: 

  - Username: admin
  - Password: admin

If you access Fabric Web Studio, you have successfully installed it. 





