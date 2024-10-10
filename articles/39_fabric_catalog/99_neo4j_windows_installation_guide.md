<studio>

# Neo4j Installation for Windows

The neo4j is required for using the Fabric Discovery and Catalog solution, when working in a Desktop Studio. This document provides a step-by-step guide on how to **install neo4j on Windows**.

Note that the installation on Windows should be done using **Powershell** commands.

## Prerequisites
JDK version >= 17



## Steps

#### Create neo4j directory:
```powershell
New-Item -Path '\path\to\neo4j-folder' -ItemType Director
```

Note that *\path\to\neo4j-folder* is an example name. You are free to chose another path for this folder.

#### Move to neo4j-folder:

```powershell
cd '\path\to\neo4j-folder'
```

#### Download K2view's Neo4j package:

Download the link from [here](https://download.k2view.com/index.php/s/N32tuucAeE0Mq7W/download).

#### Unzip the package:
```powershell
Expand-Archive -Path .\k2view-neo4j-enterprise-5.23.0-windows.zip .
```

#### Remove zip file:
```powershell
Remove-Item -Path .\k2view-neo4j-enterprise-5.23.0-windows.zip
```

#### Environment Variables in Windows:

Add NEO4J_HOME System Environment pointing to the neo4j folder.

Add neo4j bin folder to System Environment path.

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

For more details about installing neo4j on Windows, please visit the following link:

[Installing Neo4j on Windows](https://neo4j.com/docs/operations-manual/current/installation/windows/)





[![Previous](/articles/images/Previous.png)](99_catalog_setup_guide.md)



</studio>
