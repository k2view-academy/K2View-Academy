# Setup PGSQL 13.3

TDM 7.xx is certified with pgsql 9.6 & 13. You can supply access to his PG if you have one.
TDM requires user & password with full create, delete and update privileges. 

You also have the option install it from the K2View predefined **tarball** file.

the tarball that is supplied by **K2view** is PGSQL 13.3 with TLS mode enabel, the the cert files can be found in:

- The certification files are located at /opt/apps/pgsql/bin/.crt 
- The configuration files are located at /opt/apps/pgsql/data 
- The user and password are postgres, port is the default (5432).  

## Prerequisite 

- RedHat/CentOs 8, AWS Linux 2
- 2 vCPU
- 8G RAM
- 100G free disk space, make sure it is assigned to `/opt/apps/pgsql` 

  Add the following users:

~~~bash
mkdir -p /opt/apps
chmod 755 /opt/apps
useradd -m -d /opt/apps/pgsql pgsql

#### add the following packges for RHEL/CentOs 8
dnf install -y compat-openssl10 readline* glibc-locale-source glibc-langpack-en
ln -s /usr/lib64/libreadline.so /usr/lib64/libreadline.so.6
~~~

## Setup  ##

- Connect as **pgsql** on the console.

- Download or copy the [pg13.3_tls_enabled.tar.gz](https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/TDM/PG%20image/pg13.3_tls_enabled/pg13.3_tls_enabled.tar.gz).

- Download the cert [pg13.3_tls_cert.zip](https://owncloud-bkp2.s3.us-east-1.amazonaws.com/adminoc/TDM/PG%20image/pg13.3_tls_enabled/pg13.3_tls_cert.zip)

  you need copy the files to the fabric that runs TDM, and locate them under $K2_HOME/.pg_cert/ (you need to create this directory)

  you will need it also for Pgadmin client 

- Untar the `pg13.3_tls_enabled.tar.gz` 

  ~~~bash
  tar -zxvf pg13.3_tls_enabled.tar.gz && bash -l
  ~~~

- You can now start the **pgsql** with: 

  ~~~bash
  cd bin/
  ./pg_ctl -D /opt/apps/pgsql/data -l logfile start
  ~~~


   stop:

  ~~~bash
  ./bin/pg_ctl -D /opt/apps/pgsql/data -l logfile stop
  ~~~

  - The configuration files are located at `/opt/apps/pgsql/data`
  - The user and password are **postgres**, port is the default (5432). 

- run the following command from the console to create the TDMDB and user tdm

  ~~~bash
  createuser tdm --login --superuser
  createdb -O tdm TDMDB
  echo "ALTER USER tdm WITH PASSWORD 'tdm';"| psql
  echo "ALTER USER postgres WITH PASSWORD 'postgres';"| psql
  echo "ALTER USER postgres WITH SUPERUSER;"| psql
  ~~~

- now you should connect via pgadmin and run the content of `TDMGUI/createTDMDB/k2vtdm2.sql`  and `TDMGUI/createTDMDB/k2vtdm3.sql` via the **pgadmin**, connect with **tdm** user

  or you can copy the files to the pgsql console and run them with the **psql** command.
   Example:
   the files are located at ~/ k2vtdm2.sql ~/ k2vtdm3.sql

  ~~~bash
  cd ~/
  psql -d TDMDB -f -a ~/k2vtdm2.sql
  psql -d TDMDB -f -a ~/k2vtdm3.sql
  ~~~

## How to connect from pgadmib4 in TLS mode

Open **pgAdmin** connection and setup as the example below:

<img src="images/pg13_tls_connet01.png" style="zoom:25%;" />        <img src="images/pg13_tls_connet02.png" style="zoom:25%;" />

### Checking that the connection is secure

- From the server side, run the following command from the console:

~~~sql
echo "SELECT datname,usename, ssl, client_addr FROM pg_stat_ssl JOIN pg_stat_activity ON pg_stat_ssl.pid = pg_stat_activity.pid;" | psql
~~~

​	The output should look like (the "t" = true):

~~~test
datname  | usename  | ssl |  client_addr
----------+----------+-----+---------------
 postgres | postgres | t   | 10.212.134.59
 postgres | postgres | t   | 10.212.134.59
 postgres | postgres | t   | 10.212.134.59
 postgres | postgres | t   | 10.212.134.59
 pgsql    | pgsql    | f   |
(5 rows)
~~~

- From **pgAdmin**, run the following script:

~~~sql
SELECT datname,usename, ssl, client_addr
  FROM pg_stat_ssl
  JOIN pg_stat_activity
   ON pg_stat_ssl.pid = pg_stat_activity.pid;
~~~

​	<img src="images/pg_check_if_con_is_ssl.png" style="zoom:35%;" />



### ***Fabric Implementation Guidelines***

**Edit the TDM Interface**

- Edit the TDM interface. Set the **Custom Connection String setting to true** and add **&ssl=true** to the connection string. Example:
  <img src="images/pg_fabric_interface01.png" style="zoom:45%;" />

   Verify that the TDM interface is defined using the Generic DB format (Database Type is genericdb).

- Redeploy the project to Fabric. 

**Edit the Environments**

- Open and edit all Environments: check the Custom Connection String checkbox of the TDM interface and add **&ssl=true** to the TDM’s connection string. 
- Redeploy the Environments to Fabric.

[![Previous](/articles/images/Previous.png)](01_Fabric_6.xx_Installation_intro.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_Fabric_6.xx_Setup_Single_DC_multi_nodes.md)  

