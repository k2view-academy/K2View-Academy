
# Docker and Docker Compose Installation - K2view Fabric Web Studio, Version 2.0

If Docker has not already been installed, follow the Docker installation guide from Docker's documentation. You also need to install the Docker Compose Plugin.

The easiest and recommended way to get Docker Compose is to install Docker Desktop. Docker Desktop includes Docker Compose, Docker Engine, and Docker CLI, which are prerequisites for Compose. See https://docs.docker.com/desktop/ for information on how to install Docker Desktop for your Operating System.

## Install Docker and Docker Compose on Linux, MacOS, or Microsoft Windows

1.	You need to install and run Docker, which you can download from https://docs.docker.com/engine/install/.
2.	You need to install the Docker Compose Plugin. See https://docs.docker.com/compose/install/.
3.	If you install Docker Desktop, Docker Compose is bundled with Docker Engine. See https://docs.docker.com/desktop/ to install Docker Desktop.

## Using the Windows Subsystem for Linux (WSL)

When using Microsoft Windows, you must run Docker inside the WSL file system in conjunction with a Linux distribution. Otherwise, the installation will not perform adequately and will exhibit slow performance. 

### Installing WSL

1.	Open PowerShell as Administrator.

2.	Install WSL and a Linux distribution (e.g., Ubuntu):

```bash
wsl –install
```

3.	List the installed WSL distribution:

```bash
wsl –l
```

4.	You cannot use Microsoft's default “docker-desktop” distribution. You need to install a Linux distribution. E.g., `wsl --install -d Ubuntu`

```bash
wsl --install -d <distribution_name>
```

5.	You should set the default to use your selected Linux distribution. It might be listed as item 2, for example. To set the default, use this command:

```bash
wsl --set-default <distribution_name>
```

6.	You can now launch WSL using the desired distribution using the WSL command:

```bash
wsl 
```

### Using the WSL File System

When installing Fabric Web Studio, you must not use the Windows file system, such as installing it within the /mnt/c directory mounted by WSL. Rather, you should use the WSL file system (e.g., `/home/username/K2view/Studio`) for your installation. Please refer to the [installation instructions](https://support.k2view.com/Academy/articles/98_maintenance_and_upgrade/Install_Fabric_Web_Studio_v2-0/Installation.html#Installation) for this.

## Docker Commands

Not familiar with Docker commands? Please refer to Docker's [CLI Cheat Sheet](https://docs.docker.com/get-started/docker_cheatsheet.pdf). 

## Starting Docker

Fabric Web Studio and the `k2space.sh` command each require that Docker and Docker Compose are running.

Starting Docker depends on the operating system you use. Please refer to Docker documentation for instructions on how to start Docker on your operating system. 

### Running Docker Desktop

Running Docker Desktop will enable you to run Fabric Web Studio and the `k2space.sh` command. 

On Microsoft Windows, you need to enable WSL Integration for the Linux distribution you are using. To do so, on the Docker Desktop app, select Settings, then Resources, and then WSL Integration. In addition to enabling integration with my default WSL distro, you should be able to enable the distribution you selected. 

Using `wsl --install -d <distribution_name>` will achieve this also if the "Enable integration with my default WSL distro" is enabled. 

If you do not enable WSL integration, you may get errors like this when running k2space.sh. 

```bash
unable to get image '...': error during connect: Get "...": open //./pipe/dockerDesktopLinuxEngine: The system cannot find the file specified.
```

### Running Docker on Linux

When running Docker commands or starting the Docker service on Linux, depending on your system access you will need to use `sudo` with your commands. For example,

If you do not, you will get errors like:

```bash
unable to get image '...': error during connect: Get "...":  ... connect: permission denied
```

Examples of commands (see Docker's [CLI Cheat Sheet](https://docs.docker.com/get-started/docker_cheatsheet.pdf) ). 

Starting the Docker service: 

```bash
 service docker start
```

Logging in to the K2view Nexus Container Registry: 

```bash
 docker login -u [user] https://docker.share.cloud.k2view.com
```

Creating a Fabric Web Studio Space: 

```bash
 ./k2space.sh create [spacename]
```

Determining if the Fabric Web Studio Space and the Traefik Reverse Proxy are Running: 

```bash
 docker ps
```

If they are running you should see entries for Rraefik and each of the spaces you have created (e.g. myspace-fabric in this example)

```bash
CONTAINER ID   IMAGE      ...               NAMES
55879667423d   traefik:latest   ....        traefik
da112037b4d3   ... /fabric-studio:...  ...  myspace-fabric
```
