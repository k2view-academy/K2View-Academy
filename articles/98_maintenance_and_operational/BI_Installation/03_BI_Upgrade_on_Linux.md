# Fabric BI Upgrade on Linux

ExagoBI periodically released new versions. The following article describes the process of the ExagoBI upgrade for the existing BI implementation.

## Prerequisites
Working installation of ExagoBI previous version.

## Upgrading Linux

1. Download the ExagoBI installer: ExagoInstaller_vXXX.X.X.XXX.tgz from [here](https://download.k2view.com/index.php/s/uqSlM6wJjLUeKlC) into a temporary directory.

2. Download the the K2View additional files and upgrade script from [here](https://download.k2view.com/index.php/s/vESr1KlaEAewNuu) into a temporary directory.

3. Open a CLI, move to the temporary directory and extract the downloaded file by running the following command as a user with root permissions:

   ~~~bash
   tar -zxvf exago_upgrader.tar.gz
   ~~~

4. Run the installation script with 2 variables: the installation folder **/opt/apps/exago** and the ExagoBI installation file name. 
   In the example, the installation filename is: **ExagoInstaller_v2021.1.15.239**.

   ~~~bash
   ./k2view_exago_upgrade.sh /opt/apps/exago ExagoInstaller_v2021.1.15.239
   ~~~

   **Note**: the Exago installation folder name **/opt/apps/exago** is case-sensitive.

5. Restart the Apache web server.

