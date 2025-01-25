
# Docker and Docker Compose Installation - Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0

If Docker has not already been installed, follow the Docker installation guide from Docker's official documentation. You also need to install the Docker Compose Plugin. Both can be installed from https://docs.docker.com/compose/.

The easiest and recommended way to get Docker Compose is to install Docker Desktop. Docker Desktop includes Docker Compose, Docker Engine, and Docker CLI which are Compose prerequisites. See https://docs.docker.com/compose/install/ for more information.

## Install Docker and Docker Compose on Linux, MacOS or Microsoft Windows

1.	You need to install and run Docker, which you can download from https://docs.docker.com/engine/install/.
2.	You need to install the Docker Compose Plugin. See https://docs.docker.com/compose/install/.

If you install Docker Desktop, Docker Compose is bundled with Docker Engine.

## Using the Windows Subsystem for Linux (WSL)
When using Microsoft Windows, you must run Docker inside the WSL file system in conjunction with a Linux distribution. Otherwise, the installation will not perform adequately and will exhibit slow performance. 

### Installing WSL
1.	Open PowerShell as Administrator.

2.	Install WSL and a Linux distribution (e.g., Ubuntu):
 > wsl –install
3.	List the installed WSL distribution:
 > wsl -l
4.	You cannot use Microsoft's default “docker-desktop” distribution. You need to install a Linux distribution. 

 > wsl --install -d <distribution_name>

e.g., wsl --install -d Ubuntu

5.	You can set the default to use your selected Linux distribution. It might be listed as item 2, for example. To set the default, use this command:

 > wsl --set-default-version 2

6.	You can now launch WSL using the desired distribution using the WSL command:

 > wsl

### Using the WSL File System
When installing Fabric Web Studio you must not use the Windows file system, such as installing it within the /mnt/c directory mounted by WSL. 

Rather you should use the WSL file system (e.g., /home/username/K2view/Studio) for your installation. Please refer to the installation instructions for this.



