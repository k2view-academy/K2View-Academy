## PostgreSQL hardening

* The Postgres server is pre-configured  to allow connecting  with SSL using a generic self signed certificate

* Forcing connection with SSL does not require chaning the connection port from the default 5432

To switch configuration to hardened configuration, follow the below instructions:

1. log in to the postgres user

    ~~~bash
    sudo su - postgres
    ~~~

2. Create new Self-Signed certificates:
    the script require 2 inputs, hostname/DNS name and IP address

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

	* To chnage the standard postgres connection port from 5432, you will need to manualy update the configuration file
	
	* stop the Postgres service
	
	* open the postgresql.conf file

	* look for the line 'port = 5432' and chnage the port number to the prefered one

	* restart the Postgres service

	~~~bash
	~/stop_pg13.sh
	nano /etc/postgresql/13/main/postgresql.conf
	~/start_pg13.sh
	~~~
	

* To check that the Postgres is upand running run the following:
	~~~bash
	psql
	\l
	~~~

	and you will see a list of existing DB's

* To verify the remote connection is established trough the SSL, run the followoing query:
	~~~
		SELECT datname,usename, ssl, client_addr 
		FROM pg_stat_ssl
		JOIN pg_stat_activity
		ON pg_stat_ssl.pid = pg_stat_activity.pid;
	~~~
