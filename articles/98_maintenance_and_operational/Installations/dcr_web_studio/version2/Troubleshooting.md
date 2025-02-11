# Troubleshooting - Docker Compose Runtime for K2view Fabric Web Studio, Version 2.0

<ul>      
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Troubleshooting.html#command-failures">Command Failures</a></li>
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Troubleshooting.html#slow-performance-using-wsl">Slow Performance Using WSL</a></li>
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Troubleshooting.html#system-cannot-find-the-file-specified">System cannot find the file specified</a></li>
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Troubleshooting.html#connect-permission-denied">Connect Permission Denied</a></li>
   <li><a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Troubleshooting.html#404-and-502-errors">404 and 502 Errors</a></li>
</ul>

This topic summarizes common errors that can be encountered. 


## Command Failures

Fabric Web Studio and the `k2space.sh` command each require that Docker and Docker Compose are running. If it is not running, commands like `docker login` or the `k2space.sh` will fail. If you install Docker Desktop to run your installation, it must be running.

Starting Docker and its Docker Compose plugin depends on the operating system you use. Please refer to the <a href="/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/6-Docker-Compose.html#install-docker-and-docker-compose-on-linux-macos-or-microsoft-windows">Docker and Docker Compose Installation</a> topic.


## Slow Performance Using WSL

When using Microsoft Windows, you must run Docker inside the WSL file system in conjunction with a Linux distribution. Otherwise, the installation will not perform adequately and will exhibit slow performance. 

When installing Fabric Web Studio, you must not use the Windows file system, such as installing it within the `/mnt/c` directory mounted by WSL. Rather, you should use the WSL file system (e.g., `/home/username/K2view/Studio`) for your installation. Please refer to the [installation instructions](https://support.k2view.com/Academy/articles/98_maintenance_and_operational/Installations/dcr_web_studio/version2/Installation.html#Installation) for this.


## System cannot find the file specified

You may encounter "The system cannot find the file specified errors." when Running Docker Desktop on Microsoft Windows. 

On Microsoft Windows, you need to enable WSL Integration for the Linux distribution you are using. To do so, on the Docker Desktop app, select Settings, then Resources, and then WSL Integration. In addition to enabling integration with my default WSL distro, you should be able to enable the distribution you selected. 

Using `wsl --install -d <distribution_name>` will achieve this also if the "Enable integration with my default WSL distro" is enabled. 

If you do not enable WSL integration, you may get errors like this when running k2space.sh. 

```bash
unable to get image '...': error during connect: Get "...": open //./pipe/dockerDesktopLinuxEngine: The system cannot find the file specified.
```

## Connect Permission Denied

"connect: permission denied" Errors Running Docker on Linux

When running Docker commands - including `docker login` - or starting the Docker service on Linux, you might need to use `sudo` with your commands. For example,


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

## 404 and 502 Errors

After creating your first Space, you will need to wait for Fabric to come up. Unless it up you may get a 404 error if Traefik hasn't yet processed its new ingress rules which may take a few seconds. Otherwise, you might get a 502 error if Traefik is ready but Fabric is not yet ready. Give it some time. 

To restart Traefik (e.g., after configuring your TSL certificates), run the command below:

```bash
docker compose -f k2vingress-compose.yaml restart
```



