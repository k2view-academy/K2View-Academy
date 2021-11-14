# Fabric BI Docker Upgrade

ExagoBI periodically released new versions. The following article describes the process of the ExagoBI upgrade for the existing BI implementation.

## Prerequisites
Working installation of ExagoBI previous version.

## Upgrading Docker on Linux / Windows / MAC

1. Backup the configuration XML from within the existing Docker:

~~~bash
docker cp exago:/opt/apps/exago/Config/WebReports.xml /file/location/
~~~

where **exago** is the docker name (you can define a different name if needed).

2. Stop & delete the existing container.

3. Download the updated Docker image (TBD) from [here](https://TBD).

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
