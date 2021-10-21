# ExagoBI Installation

## Introduction

The Fabric BI application is based on *ExagoBI*. So, installing Fabric BI is essentially installing ExagoBI. Once the application is installed, complete the configuration and implementation by following all the steps described in the [Fabric BI User Guide](/articles/38_bi_integration/00_BI_user_guide_overview.md).

## Prerequisites

- Install Apache web server with mod_ssl.
- Disable SELinux.
- Make sure you have the latest Redhat/CentOS operating system, including all patches.
- Hardware - TDB.

[Click for more details about ExagoBI Technical Specifications](https://exagobi.com/support/administrators/installation-and-configuration/technical-specifications/).

## Installation on Linux

1. Download the ExagoBI installer: ExagoInstaller_vXXX.X.X.XXX.tgz from [here](https://download.k2view.com/index.php/s/rZLcJXsxVtIyOt5) into a temporary directory.

2. Download the the K2View additional files and installation script from [here](https://download.k2view.com/index.php/s/9F4zFbC3OYkNOHX) into a temporary directory.

3. Open a CLI, move to the temporary directory and extract the downloaded file by running the following command as a user with root permissions:

   ~~~bash
   tar -zxvf exago_installer.tar.gz
   ~~~

4. Run the installation script with 2 variables: the installation folder **/opt/apps/exago** and the ExagoBI installation file name. 
   In the example, the installation filename is: **ExagoInstaller_v2021.1.11.220.tgz**.

   ~~~bash
   ./k2view_exago_install.sh /opt/apps/exago ExagoInstaller_v2021.1.11.220.tgz
   ~~~

   **Note**: the Exago installation folder name **/opt/apps/exago** is case-sensitive.

5. Restart the Apache web server.

6. Continue to other installation and configuration steps described in the [User Guide Installation article](/articles/38_bi_integration/01_Installation.md).

   ​

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

## Docker Installation on Linux / Windows / MAC

1. Download the Docker image (D_K2V_EXAGOBI_SSL_v2021.1.11.220.tar.gz) from [here](https://download.k2view.com/index.php/s/kPcU0qApu95l9BG).

2. Import the image using the following command:

   ~~~bash
   docker load -i D_K2V_EXAGOBI_SSL_v2021.1.11.220.tar.gz
   ~~~

3. Start the Docker image as follows:
   ~~~bash
   docker run -d --privileged=true -p 80:80 -p 443:443 --name=exago k2view/exago_ssl:v2021.1.11.220
   ~~~
  
   where **exago** is the docker name (you can define a different name if needed).

4. Continue to other installation and configuration steps described in the [User Guide Installation article](/articles/38_bi_integration/01_Installation.md).

**Note**: if it is needed to connect to an existing network, use the "--network='network name' " option. 
Consult the Docker documentation for more information.



