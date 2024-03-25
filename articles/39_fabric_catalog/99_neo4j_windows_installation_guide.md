<studio>

# Neo4j Installation Guide

The neo4j is required for using the Fabric Discovery and Catalog solution, when working in a Desktop Studio. This document provides a step-by-step guide on how to install **neo4j** on Windows.

Note that the installation on Windows should be done using the **Powershell** commands.

## Prerequisites
JDK version >= 17



## Steps

#### Create neo4j directory:
```powershell
New-Item -Path '\path\to\neo4j-folder' -ItemType Director
```

#### Move to neo4j-folder:
```powershell
cd '\path\to\neo4j-folder'
```

#### Download K2view's Neo4j package:

Download the link from [here](https://k2view.sharepoint.com/:w:/r/sites/KS/Releases/K2V%20Product%20Documents/Fabric/neo4j/neo4j_download_link.docx?d=w7b19f65f451a4a2ba20894fd0547581e&csf=1&web=1&e=5wjbBe).

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

#### Start the neo4j service:
```powershell
neo4j start
```



## Additional Information

For more details on installing neo4j on Windows, please visit the following link:

[Installing Neo4j on Windows](https://neo4j.com/docs/operations-manual/current/installation/windows/)





[![Previous](/articles/images/Previous.png)](99_catalog_setup_guide.md)



</studio>
