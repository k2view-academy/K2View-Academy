
# Docker and Docker Compose Installation - Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0

If Docker is not already installed, follow the Docker installation guide from Docker's official documentation. Install  https://docs.docker.com/compose/

The easiest and recommended way to get Docker Compose is to install Docker Desktop. Docker Desktop includes Docker Compose, Docker Engine, and Docker CLI which are Compose prerequisites. See https://docs.docker.com/compose/install/  for more information.

## Install Docker and Docker Compose on Linux

1.	You need to install and run Docker, which you can download from https://docs.docker.com/engine/install/.
2.	The Docker Compose Runtime for Fabric Services requires Linux.  
3.	You need to install the Docker Compose Plugin. Please note that if you install Docker Desktop, Docker Compose is bundled. See https://docs.docker.com/compose/install/. Please use the native Docker Compose plugin and not the Python-based docker-compose utility. 

## Install Docker and Docker Compose on Microsoft Windows
1.	You need to install and run Docker that you can download from https://docs.docker.com/engine/install/.
2.	The Docker Compose Runtime for Fabric Services requires Linux or Microsoft Windows if you use the Windows Subsystem for Linux (WSL) in conjunction with a Linux distribution. 
3.	You need to install the Docker Compose Plugin. Please note that if you install Docker Desktop, Docker Compose is bundled. See https://docs.docker.com/compose/install/. Please use the native Docker Compose plugin and not the Python-based docker-compose utility. 

### Using the Windows Subsystem for Linux (WSL)
When using Microsoft Windows, you must run Docker inside the WSL file system in conjunction with a Linux distribution. Otherwise, the installation will not perform adequately and exhibit slow performance. 

### Installing WSL
1.	Open PowerShell as Administrator.

2.	Install WSL and a Linux distribution (e.g., Ubuntu):
 > wsl –install
3.	List the installed WSL distribution:
 > wsl -l
4.	You cannot use the default “docker-desktop” distribution provided by Microsoft. You need to install a Linux distribution. 

 > wsl --install -d <distribution_name>

e.g., wsl --install -d Ubuntu

5.	You need to set the default to use your selected Linux distribution. It might be listed as item 2, for example. To set the default, use this command:

 > wsl --set-default-version 2

6.	You can now launch WSL using the desired distribution using the WSL command:

 > wsl

### Using the WSL File System
In step 2 and when starting Fabric Docker Compose Runtime, you must not use the Windows file system for the installation, such as from within the /mnt/c directory. 

Avoid running commands within the Windows file system (e.g., /mnt/c). This will cause Fabric to fail to perform adequately. Rather, you should use the WSL file system (e.g., /home/username) for your installation. 



