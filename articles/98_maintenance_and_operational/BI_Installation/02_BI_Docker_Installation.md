# Fabric BI Docker Installation

## Introduction

The Fabric BI application is based on *ExagoBI*. So, installing Fabric BI is essentially installing ExagoBI. Once the application is installed, complete the configuration and implementation by following all the steps described in the [Fabric BI User Guide](/articles/38_bi_integration/00_BI_user_guide_overview.md).

## Docker Installation on Linux / Windows / MAC

1. Download the Docker image (D_K2V_EXAGOBI_NOSSL_v2021.1.14.tar.gz) from [here](https://download.k2view.com/index.php/s/XwlG4hQKGTdLMBI)
   and additinal script from [here](https://download.k2view.com/index.php/s/STdJj1yL9C7rBWX).

2. Import the image using the following command:

   ~~~bash
   docker load -i D_K2V_EXAGOBI_NOSSL_v2021.1.14.tar.gz
   ~~~

3. Start the Docker image as follows:
   ~~~bash
   docker run -d -ti --privileged=true --name=exago -v /sys/fs/cgroup:/sys/fs/cgroup:ro -p 80:80 k2view/nossl-exagobi:2021.1.14
   ~~~

   where **exago** is the docker name (you can define a different name if needed).

4. Continue to other installation and configuration steps described in the [User Guide Installation article](/articles/38_bi_integration/01_Installation.md).

**Note**: if it is needed to connect to an existing network, use the "--network='network name' " option. 
