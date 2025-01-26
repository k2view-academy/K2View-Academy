# Troubleshooting - Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0

This topic summarizes common errors that can be encountered. 


## Failures Running Docker Command or `k2space.sh` Commands

Fabric Web Studio and the `k2space.sh` command each require that Docker and Docker Compose are running. If it is not running, commands like `docker login` or the `k2space.sh` will fail. If you install Docker Desktop to run your installation, it must be running.

Starting Docker and its Docker Compose plugin depends on the operating system you use. Please refer to the <a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/6-Docker-Compose.html#install-docker-and-docker-compose-on-linux-macos-or-microsoft-windows">Docker and Docker Compose Installation</a> topic.


## Slow Performance Using the Windows Subsystem for Linux (WSL)

When using Microsoft Windows, you must run Docker inside the WSL file system in conjunction with a Linux distribution. Otherwise, the installation will not perform adequately and will exhibit slow performance. 

When installing Fabric Web Studio, you must not use the Windows file system, such as installing it within the `/mnt/c` directory mounted by WSL. Rather, you should use the WSL file system (e.g., `/home/username/K2view/Studio`) for your installation. Please refer to the [installation instructions](https://support.k2view.com/Academy/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Installation.html#Installation) for this.


## "The system cannot find the file specified errors." When Running Docker Desktop on Microsoft Windows

On Microsoft Windows, you need to enable WSL Integration for the Linux distribution you are using. To do so, on the Docker Desktop app, select Settings, then Resources, and then WSL Integration. In addition to enabling integration with my default WSL distro, you should be able to enable the distribution you selected. 

Using `wsl --install -d <distribution_name>` will achieve this also if the "Enable integration with my default WSL distro" is enabled. 

If you do not enable WSL integration, you may get errors like this when running k2space.sh. 

```bash
unable to get image '...': error during connect: Get "...": open //./pipe/dockerDesktopLinuxEngine: The system cannot find the file specified.
```

## "connect: permission denied" Errors Running Docker on Linux

When running Docker commands - including `docker login` - or starting the Docker service on Linux, you must use `sudo`. For example,


```bash
sudo docker login -u [YourAccount] https://docker.share.cloud.k2view.com
```

and 

```bash
sudo ./k2space.sh create [--profile=profile-name] spacename
```


If you do not, you will get errors like:

```bash
unable to get image '...': error during connect: Get "...":  ... connect: permission denied
```





