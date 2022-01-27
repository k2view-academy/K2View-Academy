# Fabric BI Docker Upgrade

ExagoBI periodically released new versions. The following article describes the process of the ExagoBI upgrade for the existing BI implementation.

## Prerequisites
Working installation of ExagoBI previous version.

## Upgrading Docker on Linux / Windows / MAC

1. Backup the configuration XML from within the existing Docker:

~~~bash
docker cp exago:/opt/apps/exago/Config/WebReports.xml /file/location/
~~~

​	where **exago** is the docker name (you can define a different name if needed).

2. Stop & delete the existing container.

3. Download the updated Docker image (D_k2view_BI_v2021.1.15.tar.gz) from [here](https://download.k2view.com/index.php/s/vZ2heS6xpqtMX0w).

4. Import the image using the following command:

   ~~~bash
   docker load -i D_k2view_BI_v2021.1.15.tar.gz
   ~~~

5. Start the Docker image as follows:
   ~~~bash
   ddocker run -dt --name=exago -p 80:80 k2view/exagobi:2021.1.15
   ~~~

6. Restore the backed up configuration XML file.

~~~bash
docker cp /file/location/WebReports.xml exago:/opt/apps/exago/Config/
~~~

​	where **exago** is the docker name (you can define a different name if needed). 
