# ExagoBI Upgrade

ExagoBI periodically released new versions. The following article describes the process of the ExagoBI upgrade for the existing BI implementation.

## Prerequisites
Working installation of ExagoBI previous version.

## Upgrading on Linux

1. Download the ExagoBI installer: ExagoInstaller_vXXX.X.X.XXX.tgz from [here](https://download.k2view.com/index.php/s/IUh31pFMPA1yJY8) into a temporary directory.

2. Download the the K2View additional files and upgrade script from [here](https://download.k2view.com/index.php/s/vESr1KlaEAewNuu) into a temporary directory.

3. Open a CLI, move to the temporary directory and extract the downloaded file by running the following command as a user with root permissions:

   ~~~bash
   tar -zxvf exago_upgrader.tar.gz
   ~~~

4. Run the installation script with 2 variables: the installation folder **/opt/apps/exago** and the ExagoBI installation file name. 
   In the example, the installation filename is: **ExagoInstaller_v2021.1.14.226.tgz**.

   ~~~bash
   ./k2view_exago_upgrade.sh /opt/apps/exago ExagoInstaller_v2021.1.14.226.tgz
   ~~~

   **Note**: the Exago installation folder name **/opt/apps/exago** is case-sensitive.

5. Restart the Apache web server.

## Docker Installation on Linux / Windows / MAC

1. backup the configuration XML from within the existing Docker

~~~bash
docker cp exago:/opt/apps/exago/Config/WebReports.xml /file/location/
~~~

where **exago** is the docker name (you can define a different name if needed).

2. Stop & delete the existing container.

3. Download the Updated Docker image (TBD) from [here](https://TBD).

4. Import the image using the following command:

   ~~~bash
   docker load -i D_K2V_EXAGOBI_SSL_vTBD.tar.gz
   ~~~

5. Start the Docker image as follows:
   ~~~bash
   docker run -d -ti --privileged=true --name=exago -v /sys/fs/cgroup:/sys/fs/cgroup:ro -p 80:80 k2view/nossl-exagobi:2021.1.14
   ~~~

6. Restore the backed up configuration XML file.

~~~bash
docker cp /file/location/WebReports.xml exago:/opt/apps/exago/Config/
~~~

where **exago** is the docker name (you can define a different name if needed). 
