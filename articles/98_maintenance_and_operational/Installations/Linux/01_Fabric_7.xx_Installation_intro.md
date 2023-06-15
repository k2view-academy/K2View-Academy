# Fabric 7.xx Pre-Installation Steps

## Introduction 

This article describes mandatory steps to be taken for a system setup prior to Fabric/TDM installation; such steps are:

* Hardware setup
* Users creation
* Environment variables setup
* Required packages for Linux RHEL/CentOs 8
* Required firewall ports

Follow the below instructions to prepare Linux environment for Fabric/TDM installation.

## Hardware Requisites 

for detailed hardware requirement, please see below:
<ul>      
<li>
<a href="/articles/98_maintenance_and_operational/Hardware/1_POV_Environments/00_hardware_requirements_for_POV.md">Fabric and TDM 7.x Hardware Requirements for POV Environments</a></li>
<li>
<a href="/articles/98_maintenance_and_operational/Hardware/2_All_Environments/01_hardware_requirements_introduction.md">Fabric and TDM 7.x Hardware Requirements for ALL Environments</a></li>
	  
</ul>

## OS Preparation

Create mandatory system users via the following commands:

On __all__ servers

~~~bash
mkdir -p /opt/apps
chmod 755 /opt/apps
~~~

On each server, depending on the service you wish to install, run the appropriate useradd command.
(on a single host setup, run all commands on the same server)

~~~bash
useradd -m -d /opt/apps/fabric  -s /bin/bash fabric
useradd -m -d /opt/apps/cassandra  -s /bin/bash cassandra
useradd -m -d /opt/apps/kafka  -s /bin/bash kafka
~~~



Update the OS limits as follows:

~~~bash
echo "root soft    nproc     unlimited" >> /etc/security/limits.conf
echo "cassandra - nofile 100000" >> /etc/security/limits.conf
echo "cassandra - nproc 50000" >> /etc/security/limits.conf
echo "fabric - nofile 100000" >> /etc/security/limits.conf
echo "fabric - nproc 50000" >> /etc/security/limits.conf
echo "kafka hard nofile 100000" >> /etc/security/limits.conf
echo "kafka soft nofile 100000" >> /etc/security/limits.conf
echo "kafka - nproc 50000" >> /etc/security/limits.conf
echo "kafka soft nofile 100000" >> /etc/security/limits.conf
echo "kafka - nproc 50000" >> /etc/security/limits.conf
~~~



Update /etc/sysctl.conf:

~~~bash
echo "## Added by K2view" >> /etc/sysctl.conf
echo "vm.max_map_count = 1048575" >> /etc/sysctl.conf
echo "fs.file-max = 1000000" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_time = 60" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_probes = 3" >> /etc/sysctl.conf
echo "net.ipv4.tcp_keepalive_intvl = 10" >> /etc/sysctl.conf
sysctl -p
~~~


If the system is planned to work over SSL/TLS and need to be hardend, OPENSSL is also required to be installed

On RHEL/CentOs 8 server **only** - add the following packages:

~~~bash
dnf install -y compat-openssl10 readline* python2 glibc-locale-source glibc-langpack-en
ln -s /usr/lib64/libreadline.so /usr/lib64/libreadline.so.6
~~~

> **Note:** Running one of the following commands would indicate your Linux flavour:
~~~bash
rpm -E %{rhel}
hostnamectl
cat /etc/os-release
~~~



