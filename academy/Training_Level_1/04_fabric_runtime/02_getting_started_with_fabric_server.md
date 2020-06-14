# Getting Started with Fabric Server

Now that our Fabric console is opened, let's get started by reviewing some of the Fabric Server key componenets:

[Fabric Directories](/articles/02_fabric_architecture/02_fabric_directories.md)

[Fabric Basic Utilities](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md)

[Fabric Log Files]()<!--Add link to 21.1 Fabric troubleshoot log files-->

### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Fabric Directories and Basic Utilities

###### Fabric server-Linux

1. Go to Fabric  directory -  **cd $K2_HOME/fabric**
2. Run **k2fabric -version** , what is the version being displayed?
3. Let's list the folders , how many folders are there? *9* folders
4. Let's run **k2fabric stop**, what is the message being prompted? **------------Fabric is STOPPED**
5. On another server session , go to **$K2_HOME/logs** and **tail k2fabric.log** , what is the last message being prompted? **Stopped** marked in yellow
6. Let's resart Fabric using **k2fabric start**, what is the message being prompted?**++++++++++Fabric is READY**
7. On another server session , go to **$K2_HOME/logs** and **tail k2fabric.log** , what being prompted? **Starting** marked in blue

###### Local Fabric- Window

1. To verify the Fabric version - run **fabric>version;** on the Fabric Console.

2. Go to the Fabric directory on the windows explorer - **C:\k2view\Fabric_6.1\Server\fabric**

3. Let's list the folders , how many folders are there? *8* folders

4. To review the k2fabric logs: 

   1. Check the **output** option under the Fabric Studio server\Activity Logs component
   2. Go to the Fabric log directory **C:\k2view\k2Projects\Fabric\\[Learning Project]\FabricHome\logs** check the **k2fabric** file. Note that addtional directories are under FabricHome such as : **config**

   

 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/04_fabric_runtime/01_fabric_runtime_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/04_fabric_runtime/03_fabric_deployment.md)

