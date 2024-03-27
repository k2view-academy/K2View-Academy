<studio>

# Neo4j Installation for Linux

The neo4j is required for using the Fabric Discovery and Catalog solution, when working in a Desktop Studio. This document provides a step-by-step guide on how to **install neo4j on Linux**.



## Prerequisites
JDK version >= 17



## Steps

#### Create neo4j user and directory:

```bash
sudo useradd -m -d /opt/apps/neo4j neo4j
```

#### Switch to neo4j user:
```bash
sudo su - neo4j
```

#### Download K2view's Neo4j package:

Download the link from [here](https://k2view.sharepoint.com/:w:/r/sites/KS/Releases/K2V%20Product%20Documents/Fabric/neo4j/neo4j_download_link.docx?d=w7b19f65f451a4a2ba20894fd0547581e&csf=1&web=1&e=5wjbBe).

Note that this link is internal. If you don't have permissions to the folder, open a freshdesk ticket.

#### Untar the package:
```bash
tar -zxvf k2view-neo4j-enterprise-5.16.0-unix.tar.gz
```

#### Remove tar.gz file:
```bash
rm -rf k2view-neo4j-enterprise-5.16.0-unix.tar.gz
```

#### Source bash profile:
```bash
source ~/.bash_profile  # or use bash -l
```

#### Change initial password (optional):
The default password is **changeit**. 

To set a new password, run the following:

```bash
/opt/apps/neo4j/bin/neo4j-admin set-initial-password <new-password>
```
Then, update the neo4j new password in the [data_discovery] section of Fabric config.ini file.

#### Start neo4j:
```bash
neo4j start
```

#### Start neo4j as a service (optional):

1. Connect as a root user.

2. Create a neo4j.service configuration file:

```bash
cat > /etc/systemd/system/neo4j.service <<EOF
[Unit]
Description=Neo4j Graph Database
After=network.target

[Service]
User=neo4j
Group=neo4j
ExecStart=/opt/apps/neo4j/bin/neo4j console
Restart=on-failure
LimitNOFILE=60000
TimeoutSec=120
Environment="NEO4J_CONF=/opt/apps/neo4j/conf"

[Install]
WantedBy=multi-user.target
EOF
```
3. Reload systemd to recognize the new service file:

```bash
systemctl daemon-reload
```
4. Enable the Neo4j service to start on boot:

```bash
systemctl enable neo4j
```
5. Start the neo4j service:

```bash
systemctl start neo4j
```

### Additional Information

For more details on installing Neo4j on Linux, please visit the following link:

[Installing Neo4j on Linux](https://neo4j.com/docs/operations-manual/current/installation/linux/debian/#debian-installation)

</studio>
