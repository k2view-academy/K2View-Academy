<studio>

# Catalog Setup Guide for Desktop Studio

## Introduction

This article describes the steps to be taken in order to setup the Fabric Catalog when using the Desktop Studio:

* Installation of neo4j - once per Studio environment. 
* Catalog configuration setup - once per project.


## Installation of neo4j

Install the neo4j GraphDB as described [here](99_neo4j_installation_guide.md).



## Catalog Configuration Setup

Download the [Catalog Add-On zip file](https://download.k2view.com/index.php/s/JNcyTc0Vdd82Cf9) and unzip it in your project. Note that this link is internal. If you don't have permissions to the folder, open a freshdesk ticket.

It includes the following:

* The ```web``` folder, which includes the ```apps.json``` file to enable access to the Catalog from the K2view Web Framework's menu.
  * This folder is copied to the ```\Implementation\LogicalUnits\k2_ws``` folder in your project tree.

* The  ```Discovery``` folder, which includes the ```plugins.discovery``` configuration file and the Catalog  MTables for classification and masking. 
  * The Discovery folder is copied under ```\Implementation\SharedObjects\Interfaces``` folder in your project tree. 


Note that the copied folders are hidden in the .NET Studio and thus cannot be seen in the project tree. Nevertheless, they will be loaded to the Fabric memory upon the project deployment.


## Solution Limitations

There is no integration between the .NET Studio and the Catalog. As a result, there are some limitations:

1. The Discovery job cannot be triggered from the .NET Studio. You should invoke the job either using the Fabric terminal via the command line or from the Catalog's Monitor tab in the Web Framework.
2. The Logical Unit Schema cannot be created based on the Catalog results, thus you cannot use the



</studio>
