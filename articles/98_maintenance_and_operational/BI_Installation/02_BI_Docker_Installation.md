# Fabric BI Docker Installation

## Introduction

The Fabric BI application is based on *ExagoBI*. So, the installation of Fabric BI starts from ExagoBI application installation. Once the application is installed, complete the configuration and implementation by following all the steps described in the [Fabric BI User Guide](/articles/38_bi_integration/00_BI_user_guide_overview.md).

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
4. Update file WebServiceApi.xml with the Container host IP:

   A. copy the file WebReportsApi.xml from the container

   ~~~bash
   docker cp exago:/opt/apps/exago/WebServiceApi/Config/WebReportsApi.xml .
   ~~~

   B. Edit the file with a text editor, Update the url with the host IP or DNS name
   `<webreportsbaseurl>`http://127.0.0.1/Exago`</webreportsbaseurl>`
   Replace {127.0.0.1} with the actual IP or DNS name.
   C. Copy the file back to the container.

   ~~~bash
   docker cp WebReportsApi.xml exago:/opt/apps/exago/WebServiceApi/Config/WebReportsApi.xml
   ~~~

   In linux Enviroment, you can use the followoing script to automate the proccess
   Download the Docker Configuration Script (for Linux ) from [here](https://download.k2view.com/index.php/s/yBnXEWhq9SrTDX6) .

~~~bash
./docker_config.sh {Container Name} {IP Address}|{Host Name}| {leave blank}
~~~

    **docker name**: name of the running exago container.

    **IP Adress**:container assigend IP or host local IP.

    **Host Name**: container assigend Host Name.

    To set IP automaticly, leave the second variable**blank**.

5. Continue to other installation and configuration steps described in the [User Guide Installation article](/articles/38_bi_integration/01_Installation.md).

**Notes**

1. if it is needed to connect to an existing network, use the "--network='network name' " option.
2. To Manage the Apache and fastCGI services within the docker:

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

