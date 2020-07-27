# Getting Started with Fabric Server

Now that the Fabric console is open, let's get started by reviewing the key components of the Fabric server:

-  [Fabric Directories](/articles/02_fabric_architecture/02_fabric_directories.md)

-  [Fabric Basic Utilities](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md)

-  [Fabric Troubleshooting](/articles/21_Fabric_troubleshooting/01_Fabric_troubleshooting_overview.md)

-  [Fabric Log Files](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md)


### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Fabric Directories and Basic Utilities

###### Fabric server-Linux

1. Go to the Fabric **cd $K2_HOME/fabric** directory.  
2. Run **k2fabric -version**. Which version is displayed?
3. Let's list the folders. How many folders are there? *9* folders
4. Let's run **k2fabric stop**. Which message is prompted? **------------Fabric is STOPPED**
5. In another server session, go to **$K2_HOME/logs** and **tail k2fabric.log**. What is the last prompted message? **Stopped** marked in yellow.
6. Let's restart Fabric using **k2fabric start**. Which message is prompted?**++++++++++Fabric is READY**
7. In another server session, go to **$K2_HOME/logs** and **tail k2fabric.log**. Which message is prompted? **Starting** marked in blue.

###### Local Fabric - Window

1. To verify the Fabric version, run **fabric>version;** in the Fabric Console.

2. Go to the Fabric **C:\k2view\Fabric_6.1\Server\fabric** directory in the Windows Explorer.

3. Let's list the folders. How many folders are there? *8* folders.

4. To review the k2fabric logs: 

   1. Check the **Output** option under the **Fabric Studio Server** \ **Activity Logs** component.
   2. Go to the Fabric **C:\k2view\k2Projects\Fabric\\[Learning Project]\FabricHome\logs** log directory and check the **k2fabric** file. Note that there are additional directories under **FabricHome**. For example: **config**. 

   

 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/04_fabric_runtime/01_fabric_runtime_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/04_fabric_runtime/03_fabric_deployment.md)

