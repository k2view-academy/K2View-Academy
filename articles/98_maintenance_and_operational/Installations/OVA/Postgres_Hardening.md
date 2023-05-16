## PostgreSQL Hardening

* The Postgres server is pre-configured in order to allow connection with an SSL, using a generic Self-Signed certificate.

* Forcing connection with SSL does not require changing the connection port from the default 5432.

To switch configuration to a hardened configuration, follow the below instructions:

1. Log in to the Postgres user

    ~~~bash
    sudo su - postgres
    ~~~

2. Create new Self-Signed certificates:
    the script requires 2 inputs, a hostname/DNS name and an IP address

    for example:

	 DNS name: appliance.k2view.local 

	 IP address: 192.168.1.1

    ~~~bash
    cd create_k2v_cer/
    ./create_cer.sh appliance.k2view.local 192.168.1.1
    ~~~
3. Apply configuration and restart the Postgres server:
   
   ~~~bash
   ./secure_pg13.sh
   ~~~
	
* *Optional*:

	* To change the standard Postgres connection port from 5432, you will need to manually update the configuration file
	
	* stop the Postgres service
	
	* open the postgresql.conf file

	* look for the line 'port = 5432' and change the port number to the preferred one

	* restart the Postgres service

	~~~bash
	~/stop_pg13.sh
	nano /etc/postgresql/13/main/postgresql.conf
	~/start_pg13.sh
	~~~
	

* To check that the Postgres is up and running, run the following:
	~~~bash
	psql
	\l
	~~~

	and you will be able see a list of existing DB's

* To verify that the remote connection is established through the SSL, run the followoing query:
	~~~
		SELECT datname,usename, ssl, client_addr 
		FROM pg_stat_ssl
		JOIN pg_stat_activity
		ON pg_stat_ssl.pid = pg_stat_activity.pid;
	~~~
