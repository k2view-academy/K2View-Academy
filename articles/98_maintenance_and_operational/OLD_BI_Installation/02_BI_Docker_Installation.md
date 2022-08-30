# Fabric BI Docker Installation

## Introduction

The Fabric BI application is based on *ExagoBI*. So, the installation of Fabric BI starts from ExagoBI application installation. Once the application is installed, complete the configuration and implementation by following all the steps described in the [Fabric BI User Guide](/articles/38_bi_integration/00_BI_user_guide_overview.md).

## Prerequisites

When you have Fabric and/or PostgreSQL server running on a docker on your local machine (Windows or MAC),  use **docker compose** to join all containers in the same network based on Docker Installation instructions below or do one of the following:

* Use the Docker GW:
  * On Linux - trough the Docker0 interface.
  * On Windows - trough the WSL network interface.
  * Not available for MAC.
* Install the PostgreSQL locally to use it for Storage Management DB.
* Keep the default SQLite as Storage Management DB. Note that the default SQLite DB can be used for demo or training purposes only.

## Docker Installation on Linux / Windows / OSX

1. Download the Docker image (D_k2view_BI_v2021.1.15.tar.gz) from [here](https://download.k2view.com/index.php/s/vZ2heS6xpqtMX0w).

2. Import the image using the following command:

   ~~~bash
   docker load -i D_k2view_BI_v2021.1.15.tar.gz
   ~~~
   
3. Start the Docker image as follows:

   ~~~bash
   docker run -dt --name=exago -p 80:80 k2view/exagobi:2021.1.15
   ~~~

   where **exago** is the docker name (you can define a different name if needed).
   
   If you need to use a different port (for example, when the port 80 is already in use) - see the notes below.
   
4. Update the **WebReportsApi.xml** file with the Container host IP:

   * Copy the WebReportsApi.xml from the container:

   ~~~bash
   docker cp exago:/opt/apps/exago/WebServiceApi/Config/WebReportsApi.xml .
   ~~~

   * Edit the file with a text editor, update the URL with the host IP or DNS name:
   
   ~~~xml
   <webreportsbaseurl>http://127.0.0.1/Exago</webreportsbaseurl>
   ~~~
   
   Replace {127.0.0.1} with the actual IP or DNS name.
   
   * Copy the file back to the container.

   ~~~bash
   docker cp WebReportsApi.xml exago:/opt/apps/exago/WebServiceApi/Config/WebReportsApi.xml
   ~~~
   
   In Linux environment, you can use the following script to automate the process.
   Download the Docker Configuration Script (for Linux) from [here](https://download.k2view.com/index.php/s/yBnXEWhq9SrTDX6).

   ~~~bash
   ./docker_config.sh {Container Name} {IP Address}|{Host Name}| {leave blank}
   ~~~

     * docker name: name of the running Exago container.

     * IP Address: container assigned IP or host local IP.

     * Host Name: container assigned Host Name.

     * To set IP automatically, leave the second variable **blank**.

Continue to other installation and configuration steps described in the [User Guide Installation article](/articles/38_bi_integration/01_Installation.md).

**Notes**

1. If it is needed to connect to an existing network, use the "--network='network name' " option.

2. To use a different port rather than 80, do the following:

   * Start the docker as follows:

     ~~~bash
     docker run -dt --name=exago -p <your port>:80 k2view/exagobi:2021.1.15
     ~~~

   * Update the **WebReportsApi.xml** file with the same port number.

   * In Linux environment, add the port number to the command:

     ~~~
     ./docker_config.sh {Container Name} {IP Address : Port}|{Host Name}| {leave blank}
     ~~~

3. To manage the Apache and fastCGI services within the docker:

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
<td style="width: 50%; height: 18px;">Personalization Configuration</td><td style="width: 50%; height: 18px;">/opt/apps/exago/Config</td>
</tr>
<tr>
<td style="width: 50%; height: 18px;">Exago Report Scheduler</td>
<td style="width: 50%; height: 18px;">/opt/apps/exago/Scheduler</td>
</tr>
</tbody>
</table>
