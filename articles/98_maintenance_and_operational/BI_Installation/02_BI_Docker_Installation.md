# Fabric BI Docker Installation

## Introduction

The Fabric BI application is based on *ExagoBI*. So, the installation of Fabric BI starts from ExagoBI application installation. Once the application is installed, complete the configuration and implementation by following all the steps described in the [Fabric BI User Guide](/articles/38_bi_integration/00_BI_user_guide_overview.md).

## Docker Installation on Linux / Windows / MAC

1. Download the Docker image (D_k2vBI_ubuntu_2021.1.15.tar.gz) from [here](https://download.k2view.com/index.php/s/hDtKbxgv1gMylYs).

2. Import the image using the following command:

   ~~~bash
   docker load -i D_k2vBI_ubuntu_2021.1.15.tar.gz
   ~~~

3. Start the Docker image as follows:
   ~~~bash
   docker run -d --name=exago -p 80:80 exago_ubuntu:2021.15.1
   ~~~

   where **exago** is the docker name (you can define a different name if needed).
   
4. Continue to other installation and configuration steps described in the [User Guide Installation article](/articles/38_bi_integration/01_Installation.md).

**Notes**
 *. if it is needed to connect to an existing network, use the "--network='network name' " option. 

*. To Manage the Apache and fastCGI services within the docker:

  ~~~bash
  docker exec exago service apache2 start|restart|stop|status
  ~~~
  
  ~~~bash
  docker exec exago bash -c "/opt/apps/exago/fastcgi.sh start|restart|stop|status
  ~~~

**Directory Locations**

<table style="border-collapse: collapse; width: 100%;">
<tbody>
<tr>
<td style="width: 50%; height: 18px;">Application Binaries</td>
<td style="width: 50%; height: 18px;">/opt/apps/exago/bin</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">API for Exago Web Service</td>

<td style="width: 50%; height: 18px;">/opt/apps/exago/WebServiceApi</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">3rd Party’s Utilities</td>

<td style="width: 50%; height: 18px;">/opt/apps/exago/Utilities</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">3rd Party’s Drivers</td>

<td style="width: 50%; height: 18px;">/opt/apps/exago/Drivers</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">Personalization Configuration</td>

<td style="width: 50%; height: 18px;">/opt/apps/exago/Config</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">Exago Report Scheduler</td>
<td style="width: 50%; height: 18px;">/opt/apps/exago/Scheduler</td>
</tr>
</tbody>
</table>

