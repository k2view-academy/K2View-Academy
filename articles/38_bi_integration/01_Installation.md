# Installation and Setup

Following are the installation and setup recommendations:

* It is recommended to have 3 separate installations: for Development, QA and Production.
* ​

### Installation

Install the following components:

* **The latest available Exago version**, that includes the server and client Exago applications. 
  * Exago can be installed either on Linux or on Windows (as a docker).
* **Storage Management DB**, the data base that keeps full report definition such as report type (e.g. Express View), report metadata, currency, decimal setting, colors and more. [Click for more information about the Exago Storage Management](https://support.exagoinc.com/hc/en-us/articles/360042587313-Storage-Management-Introduction).
  * PostgreSQL is a recommended DB type and is a must for UAT / Production.
  * SQLite DB can be used for development or demo purposes. 
* **Storage Management Utility**, the UI tool that allows setting up the folders' access permissions. [Click for more information about the Exago Storage Management Utility](https://support.exagoinc.com/hc/en-us/articles/360053801773-Storage-Management-Utility-v2021-1-).

*Link to the K2View DevOps document explaining how to install the Exago components - TBD.*

*Q: Update Exago host in Config.ini - manually or part of installation?*



### Storage Management Setup

*The recommendation is to use one PostgreSQL DB for all environments, providing a unique Table Prefix per each environment => still checking with Exago if this is correct*

 

