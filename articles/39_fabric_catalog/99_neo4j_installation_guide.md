<studio>

# Neo4j Installation Guide

This document provides a step-by-step guide on how to install **neo4j** on:

* [Linux](99_neo4j_installation_guide.md#linux)
* [macOS](99_neo4j_installation_guide.md#macos)
* [Windows](99_neo4j_installation_guide.md#windows)

The neo4j is required for using the Fabric Discovery and Catalog solution, when working in a .NET Studio. 

## Prerequisites
JDK version >= 17



## Linux

#### Create neo4j user and directory:

```bash
sudo useradd -m -d /opt/apps/neo4j neo4j
```

#### Switch to neo4j user:
```bash
sudo su - neo4j
```

#### Download K2view's Neo4j package:

Download the link from here: [neo4j_download_link.docx](https://k2view.sharepoint.com/:w:/r/sites/KS/Releases/K2V Product Documents/Fabric/neo4j/neo4j_download_link.docx?d=w7b19f65f451a4a2ba20894fd0547581e&csf=1&web=1&e=SAQTzf).

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



## macOS

#### Create neo4j folder and move into it:
```bash
mkdir -p /opt/apps/neo4j
cd /opt/apps/neo4j
```

#### Download K2view's Neo4j package:

Download the link from [here]([neo4j_download_link.docx](https://k2view.sharepoint.com/:w:/r/sites/KS/Releases/K2V Product Documents/Fabric/neo4j/neo4j_download_link.docx?d=w7b19f65f451a4a2ba20894fd0547581e&csf=1&web=1&e=SAQTzf)).

Note that this link is internal. If you don't have permissions to the folder, open a freshdesk ticket.

#### Untar the package:
```bash
tar -zxf k2view-neo4j-enterprise-5.16.0-unix.tar.gz
```

#### Remove tar.gz file:
```bash
rm -f k2view-neo4j-enterprise-5.16.0-unix.tar.gz
```

#### Update .zprofile witn neo4j environment:
```bash
echo '# Set NEO4J_HOME environment variable' >> ~/.zprofile
echo 'export NEO4J_HOME="/opt/apps/neo4j"' >> ~/.zprofile
echo '# Add NEO4J_HOME/bin to PATH' >> ~/.zprofile
echo 'export PATH="$NEO4J_HOME/bin:$PATH"' >> ~/.zprofile
```

#### Source zcs profiles:
```bash
source ~/.zprofile  # or use zsh -l
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

1. Create a neo4j.plist configuration file:

```bash
sudo bash -c 'cat > /Library/LaunchDaemons/com.neo4j.server.plist <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
<dict>
    <key>Label</key>
    <string>com.neo4j.server</string>
    <key>ProgramArguments</key>
    <array>
        <string>/opt/apps/neo4j/bin/neo4j</string>
        <string>console</string>
    </array>
    <key>RunAtLoad</key>
    <true/>
    <key>KeepAlive</key>
    <true/>
    <key>WorkingDirectory</key>
    <string>/opt/apps/neo4j</string>
    <key>StandardOutPath</key>
    <string>/opt/apps/neo4j/logs/neo4j.out</string>
    <key>StandardErrorPath</key>
    <string>/opt/apps/neo4j/logs/neo4j.err</string>
</dict>
</plist>
EOF'
```

2. Load the .plist file launchctl to start managing neo4j as a service:

```bash
launchctl load /Library/LaunchDaemons/com.neo4j.server.plist
```

3. Start the neo4j service:

```bash
launchctl start com.neo4j.server
```

### Additional Information

For more details on installing Neo4j on macOS, please visit the following link:

[Installing Neo4j on macOS](https://neo4j.com/docs/operations-manual/current/installation/osx/)



## Windows

Note that the installation on Windows should be done using the **Powershell** commands.

#### Create neo4j directory:
```powershell
New-Item -Path '\path\to\neo4j-folder' -ItemType Director
```

#### Move to neo4j-folder:
```powershell
cd '\path\to\neo4j-folder'
```

#### Download K2view's Neo4j package:

Download the link from [here]([neo4j_download_link.docx](https://k2view.sharepoint.com/:w:/r/sites/KS/Releases/K2V Product Documents/Fabric/neo4j/neo4j_download_link.docx?d=w7b19f65f451a4a2ba20894fd0547581e&csf=1&web=1&e=SAQTzf)).

Note that this link is internal. If you don't have permissions to the folder, open a freshdesk ticket.

#### Unzip the package:
```powershell
Expand-Archive -Path .\k2view-neo4j-enterprise-5.16.0-windows.zip .
```

#### Remove zip file:
```powershell
Remove-Item -Path .\k2view-neo4j-enterprise-5.16.0-windows.zip
```

#### Environment Variables:

Add NEO4J_HOME System Environment pointing to neo4j folder.

Add neo4j bin folder to system PATH.

#### Update neo4j GDS key path:
```powershell
# Get the current directory with double backslashes 
$currentDir = (Get-Location).Path -replace '\\', '\\'

# Read the neo4j.conf file and update the path for gds.enterprise.license_file using double backslashes in path
(Get-Content .\conf\neo4j.conf -Raw) -split '\r?\n' | ForEach-Object {
    if ($_ -match "gds.enterprise.license_file") {
        # Append the current directory with double backslashes, followed by \\licenses\\gds.key
        "gds.enterprise.license_file=$currentDir\\licenses\\gds.key"
    } else {
        $_
    }
} | Set-Content .\conf\neo4j.conf
```

#### Change initial password (optional):
The default password is **changeit**. 

To set a new password, run the following:

```bash
neo4j-admin set-initial-password <new-password>
```
Then, update the neo4j new password in the [data_discovery] section of Fabric config.ini file.

#### Install neo4j Windows service:
```powershell
neo4j windows-service install
```

### Start the neo4j service:
```powershell
neo4j start
```

### Additional Information

For more details on installing Neo4j on Windows, please visit the following link:

[Installing Neo4j on Windows](https://neo4j.com/docs/operations-manual/current/installation/windows/)





</studio>
