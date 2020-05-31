# Deploying A Fabric Project

### What Is Fabric Deployment?

The implementation of a Fabric project can be divided into two steps:

- **Design, configuration and coding**, which includes the creation of a Fabric project and the definition of the relevant objects in the Fabric Studio in order to implement the business requirements.
- **Deployment** of the project into the Fabric Server so that the first deployment of the new project or all recent changes since its last deployment are compiled and become effective on the server side. 

A deployment includes the definition of Fabric objects like the LU Schema[[NL1\]](#_msocom_1) , LU Tables[[NL2\]](#_msocom_2) , Interfaces[[NL3\]](#_msocom_3) , Globals[[NL4\]](#_msocom_4) , Project functions[[NL5\]](#_msocom_5) , Web Services[[NL6\]](#_msocom_6) , Broadway flows[[NL7\]](#_msocom_7) , Graphit [[NL8\]](#_msocom_8) and Translations[[NL9\]](#_msocom_9) and is required whenever these objects are created or modified. 

The deployment is needed for any change operated in any of these objects, excluding the migration of source data into the Fabric Server. 

The deployment of a Fabric project is performed on the following levels:

- **Deployment of Logical Units**, each LU should be deployed separately. When needed, a deployment can be performed on a specific LU if only that LU has been modified.
- **Deployment of Broadway flows** as part of the LU to which they belong.
- **Deployment of References objects**, when they exist in a Project. All Reference (common) tables are deployed together.
- **Deployment of Web Services**, when they exist in the Project. The following deployment options are available: 
  - Deployment of all Web Services. 
  - Deployment of selected Web Services category files.  
  - Deployment of selected Web Services.
- **Deployment of Graphit**, each object can be deployed separately or all Graphit objects can be deployed as part of the Web Services. 
- **Deployment of Environments**.  

Deployment can be performed either:

- From the Fabric Studio[[NL12\]](#_msocom_12) .

- By creating the artifacts in the Fabric Studio via **Build Deploy Artifacts** and running the deployment on the server side (Offline Deploy[[NL13\]](#_msocom_13) ). 

When a Fabric object is deployed to the server, the deployment artifacts are created in the **/storage/lu** directory of the Fabric Server – one **ludb.jar** for each deployment. 

A folder is created under **/storage/lu** for each object’s first deployment. For example, when the CRM LU is deployed for the first time, the folder CRM is created under **/storage/lu**. The **/storage/lu/CRM** will then include the folder named as the deployment time and will include the **ludb.jar**.

Note that Shared Objects are not independent objects in a Project and therefore cannot be deployed as stand-alone items. You must re-deploy the object using the updated Shared Object to make the change in a shared object available in the Fabric Server. For example, when an Interface is updated, all Logical Units using this Interface should be re-deployed to make this change effective.

### How Do I check Which Project Is Deployed to Fabric? 

Only **one Project** can be deployed to **each Fabric cluster**[[NL14\]](#_msocom_14) . If a Project has been deployed and an attempt to deploy a different Project to the same cluster is made, an error message is displayed. 

To check the Project’s deployment in the Fabric Server, use the **SET** command from the Fabric console. 

Syntax:
<pre><code>
    set;
</code></pre>

This command displays the currently **deployed Project name**, as well as the values of various Project parameters. 

Click for more information about Fabric Basic Commands[[NL15\]](#_msocom_15) . 

### How Are Deployed Objects Reflected in the Fabric Server?

You can check which objects are deployed in the Fabric Server using the Fabric **list** command.

- **list LU_TYPES/LUT** provides a list of Logical Unit [[NL16\]](#_msocom_16) types deployed to the Fabric Server.
- **list WS** provides a list of all Web Service [[NL17\]](#_msocom_17) methods deployed to the Fabric Server. 
- **list ENVIRONMENTS/ENVS** provides a list of environments[[NL18\]](#_msocom_18) deployed to the Fabric Server.
- **list BF/BROADWAY_FLOWS** - list all Broadway flows [[NL19\]](#_msocom_19) of all LU Types deployed to the Server.
- **list INSTANCE_GROUPS/IGS** - lists all instance groups of all LU Types deployed to the Server.

###### Syntax:
<pre><code> 
    list lut; 
    list lu_types;
    list ws;
    list ENVIRONMENTS; 
    list ENVS;
    list BF;
    list BROADWAY_FLOWS;
    list IGS;
    list INSTANCE_GROUPS;
</code></pre>

### How Are Deployed Objects Reflected in Cassandra?

Project deployment is reflected in **Cassandra**[[NL20\]](#_msocom_20) as follows:

- The deployment of a Logical Unit[[NL21\]](#_msocom_21) , Reference[[NL22\]](#_msocom_22) s and Web Services [[NL23\]](#_msocom_23) creates new records in the **k2_lut_info** table[[TE24\]](#_msocom_24) under the **k2system** keyspace. A separate record is created for each LU and Reference Table and for each deployed Web Service.
- Each deployed LU creates a new Cassandra **keyspace** named **k2view_<LU Name>**.
- The first deployed WS creates a new Cassandra **keyspace** named **k2view_k2_ws**.
 

### How Do I Get the Deployed Implementation?

After the Project is deployed to the server, there might be a need to clarify which code has been deployed in a specific environment. For example, if there are many code changes in the Project and you need to verify whether a specific change has already been deployed to the server. Fabric supports the creation of a zip file for a selected LU name, so that the implementor can download the code deployed in the environment and check it.

###### Syntax:

<pre><code>
    http://<host>:3213/lut?lutName=<luname>&token=<token>
	http://<host>:3213/lut?lutName=k2_ws&token=<token>
	http://<host>:3213/lut?lutName=k2_ref&token=<token>
</code></pre>

The outcome of this command is that **ludbXMLs.zip** is downloaded to your local machine and can be opened in the Studio.

------



 [[NL1\]](#_msoanchor_1)  [[NL1\]](#_msoanchor_1)Add link to 1.3 Logical Unit Schema Window
 [[NL2\]](#_msoanchor_2)Add link to 2.1 LU Table overview
 [[NL3\]](#_msoanchor_3)Add link to 16.1 Interface Overview
Add link to  [[NL4\]](#_msoanchor_4)17_1 Globals overview
 [[NL5\]](#_msoanchor_5)Add link to 3.7 Project functions
 [[NL6\]](#_msoanchor_6)Add link to 13.1 WS overview
 [[NL7\]](#_msoanchor_7)Add link to Broadway – DROP 3!!!
 [[NL8\]](#_msoanchor_8)Add link to GraphIt – DROP 2!!!
 [[NL9\]](#_msoanchor_9)Add link to 18.1 Translation overview
 [[NL12\]](#_msoanchor_12)Add link to 15.2 Deploy from Fabric Studio
 [[NL13\]](#_msoanchor_13) [[NL13\]](#_msoanchor_13)Add link to Offline Deploy 
 [[NL14\]](#_msoanchor_14)Add link to 19.1 Fabric Architecture
 [[NL15\]](#_msoanchor_15)Add link to 19.5 Fabric basic commands
 [[NL16\]](#_msoanchor_16)Add link to 1.1 LU Overview
 [[NL17\]](#_msoanchor_17)Add link to 13.1 WS overview
 [[NL18\]](#_msoanchor_18) [[NL18\]](#_msoanchor_18)Add link to “Environments Overview and capabilities” DROP 2!!!
 [[NL19\]](#_msoanchor_19)Add link to Broadway – DROP 3!!!
 [[NL20\]](#_msoanchor_20)Add link to 19.1 Fabric Architecture
 [[NL21\]](#_msoanchor_21)Add link to 1.1 LU Overview
 [[NL22\]](#_msoanchor_22)Add link to Reference/Common – DROP 2!!
 [[NL23\]](#_msoanchor_23)Add link to 13.1 WS overview
 [[TE24\]](#_msoanchor_24)Add a link to 19.7 Cassandra keyspaces KI (under Fabric architecture)

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md)
