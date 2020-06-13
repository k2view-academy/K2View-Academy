# Getting Started with Fabric Server

Now that our Fabric console is opened, let's get started by reviewing some of the Fabric Server key componenets:

[Fabric Directories](/articles/02_fabric_architecture/02_fabric_directories.md)

[Fabric Basic Utilities](/articles/02_fabric_architecture/03_fabric_basics_getting_started.md)

[Fabric Log Files]()<!--Add link to 21.1 Fabric troubleshoot log files-->

### ![/academy/Training_Level_1/03_fabric_basic_LU/images/example.png]()Example - Fabric Directories and Basic Utilities

###### Fabric server-Linux

1. Go to Fabric  directory -  **cd $K2_HOME/fabric**
2. Run **k2fabric -version** , what is the version being displayed?
3. Let's list the folders , how many folders are there? *9* folders
4. Let's run **k2fabric stop**, what is the message being prompted? **------------Fabric is STOPPED**
5. On another server session , go to **$K2_HOME/logs** and **tail k2fabric.log** , what is the last message being prompted? **Stopped** marked in yellow
6. Let's resart Fabric using **k2fabric start**, what is the message being prompted?**++++++++++Fabric is READY**
7. On another server session , go to **$K2_HOME/logs** and **tail k2fabric.log** , what being prompted? **Starting** marked in blue

###### Local Fabric- Window



 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/04_fabric_runtime/01_fabric_runtime_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/04_fabric_runtime/03_fabric_deployment.md)

