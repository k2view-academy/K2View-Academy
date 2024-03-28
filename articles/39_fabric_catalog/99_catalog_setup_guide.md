<studio>

# Catalog Setup Guide for Desktop Studio

## Introduction

This article describes the steps to be taken in order to set up the Fabric Catalog when using the Desktop Studio V7.2 and onwards:

* Installation of neo4j - performed once per Studio environment. 
* Catalog configuration setup - performed once per project.


## Installation of neo4j

Install the neo4j GraphDB as described [here](99_neo4j_windows_installation_guide.md).



## Catalog Configuration Setup

Download the [Implementation zip file](https://download.k2view.com/index.php/s/HAgVCsNHqsD2hvq) and unzip it into your project.

The following will be updated in your project:

* The ```web``` folder, which includes the ```apps.json``` file to enable access to the Catalog from the K2view Web Framework's menu.
  * This folder is copied to the ```\Implementation\LogicalUnits\k2_ws``` folder in your Project tree.

* The  ```Discovery``` folder, which includes the ```plugins.discovery``` configuration file and the Catalog MTables for classification and masking. 
  * The Discovery folder is copied under ```\Implementation\SharedObjects\Interfaces``` folder in your Project tree. 

Deploy the project upon the completion.


Note that the copied folders are hidden in the Desktop Studio and thus cannot be seen in the Project tree. 


## Solution Limitations

There is no integration between the Desktop Studio and the Catalog. As a result, there are some limitations:

1. The Discovery job cannot be triggered from the Desktop Studio. You should invoke the job either using the Fabric terminal via the command line or from the Catalog's Monitor tab in the Web Framework.
2. The Logical Unit Schema cannot be created based on the Catalog results. The Schema should be created manually.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](99_neo4j_windows_installation_guide.md) 



</studio>
