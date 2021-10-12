# ExagoBI Installation

## Introduction

This article describes the installation of ExagoBI. In order to start using the BI application, complete the configuration and implementation by following all the steps described in [BI User Guide](/articles/38_bi_integration/00_BI_user_guide_overview.md).

## Prerequisites

- Apache web server is installed.
- SELinux is disabled.
- Redhat/CentOS latest Operating System and above, with latest patches.
- Hardware - TDB

[Click for more details about Exago Technical Specifications](https://support.exagoinc.com/hc/en-us/articles/216396637-Technical-Specifications).

## Installation on Linux

1. Download the ExagoBI Installer ExagoInstaller_vXXX.X.X.XXX.tgz from [here](https://download.k2view.com/index.php/s/nlbvsAKqG9sbi0D) into a temporary directory.

2. Download the the K2View Additional files and installation script from [here](https://download.k2view.com/index.php/s/nlbvsAKqG9sbi0D) into a temporary directory.

3. Open a CLI, move to the temporary directory and extract the downloaded file by running the following command as a user with root permissions:

   ~~~bash
   tar -zxvf exago_installer.tar.gz
   ~~~

4. Run the installation script with 2 variables, installation folder and ExagoBI installation file name. 
   In the example, the installation folder will be **/opt/apps/exago** and installation filename is: **ExagoInstaller_v2021.1.11.220.tgz**.

   ~~~bash
   ./k2view_exago_install.sh /opt/apps/exago ExagoInstaller_v2021.1.11.220.tgz
   ~~~

   **Note**: the Exago installation folder name **/opt/apps/exago** is case-sensitive.
   
5. Restart the Apache web server.

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

1. Download the Docker image (D_K2V_Exago_v2021.1.11.tar.gz) from [here.](https://download.k2view.com/index.php/s/nlbvsAKqG9sbi0D)

2. Import the image using the following command:

   ~~~bash
   docker load -i D_K2V_Exago_v2021.1.11.tar.gz
   ~~~

3. Start the docker image as follows:
  ~~~bash
  docker run -d --privileged=true -p 80:80 --name=exago k2viewexago:v2021.1.11.220
  ~~~

 **Note**: if it is needed to connect to an existing network, use the "--network='network name' " option. 
Consult the Docker documentation for more information.



## Access ExagoBI

Once the installation and configuration files update is completed, ExagoBI can be accessed via the **K2View Web Framework** > **BI**. 

In demo or development environments it is possible to configure ExagoBI to be accessed directly using the following URLs:

* Administration Console: http://[host]/Exago/Admin.aspx
* Designer: http://[host]/Exago/ExagoHome.aspx

Click here to learn how to allow the [direct access to ExagoBI](/articles/38_bi_integration/99_bi_admin_config.md#allow-direct-access).

