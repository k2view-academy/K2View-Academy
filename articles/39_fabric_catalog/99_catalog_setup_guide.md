<studio>

# Catalog Setup Guide

## Introduction

This article describes the steps to be taken in order to setup the Fabric Catalog when working in the Fabric .NET Studio:

* Installation of neo4j
* Catalog configuration setup

This is a one-time setup that should be performed once per project.



## Installation of neo4j

Download the neo4j installation package from the download page... TBD



## Catalog Configuration Setup

Download the Catalog Add-Ons zip file from the download page and unzip it in your project. It includes the following:

* The ```apps.json``` file, which is copied to the ```\Implementation\LogicalUnits\k2_ws\web``` folder in your project tree and it enables access to the Catalog from the Web Framework menu.
* The  ```Discovery``` folder, which includes the Catalog's Settings MTables and the ```plugins.discovery``` configuration file. The Discovery folder is copied under ```\Implementation\SharedObjects\Interfaces``` folder in your project tree. 



## Solution Limitations

There is no integration between the .NET Studio and the Catalog. As a result, there are some limitations:

1. The Discovery job cannot be triggered from the Studio menu. You can invoke the job either using the Fabric terminal via the command line or from the Catalog's monitor tab.
2. The Logical Unit Schema cannot be created based on the Catalog results.  



</studio>
