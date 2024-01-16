# Offline Deploy

New or updated Fabric project implementations must be deployed to the server. 

Deployment into non-development servers shall be carefully done, verifying that the right changes will be applied. Verification can be done by code-reviews (a human process), as well as by tools that perform 3rd-party dependency vulnerability checks and code analysis for quality & security.

Such deployment, which is not done from the [Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md), is called an *Offline Deploy*, and Fabric provides 2 main methods for performing it:

- In the Fabric server itself, using a Deploy script.
- Remotely, using API calls.

These methods should be used only after the prebuilt project artifacts - aimed for deployment - have passed verifications. Moreover, your CI/CD tools responsible for the verification pipeline, can performs the Offline Deploy actions.



## Offline Deployment using Build and Deploy scripts

There are 2 ways to perform this Offline Deployment method:
- Build and deploy in 2 steps: First, build the artifacts in either the Fabric Studio or a server, using the [buildArtifacts](03_offline_deploy.md#build-artifacts) script. Then, perform the deployment by <studio> running either the Fabric **Deploy** command on the target Fabric server or the *buildAndDeployArtifacts* script (using the DEPLOYONLY flag) or by </studio> using API calls.

  > Building artifacts in a server is aimed to be done by CI/CD tool, using a docker image that contains Fabric in it. This way, Fabric is not required to run for building the artifacts.

- Build and deploy in one step. <studio>Build and deploy in the target Fabric server using the  [buildAndDeployArtifacts](03_offline_deploy.md#build-and-deploy-artifacts) script.</studio><web>Use the k2cloud UI.</web>

### Build and Deploy in Two Steps

<studio>

To build the artifacts **in the Fabric Studio**:

1. Right-click the **object** (for example, **Web Services**) and click **Build Deploy Artifacts**. A notification displays following a successfully built of the artifacts.

<img src="images/16_03_offline_deploy1.png" alt="16_03_offline_deploy1" style="zoom: 80%;" />

2. Right-click the same **object** and select **Open Folder**. The Windows Explorer opens in the following location: [Your PC Folder]\K2View Fabric Studio\Projects\\[Project Name]\Implementation\LogicalUnits\\[LU Name].

3. Look for the **ludb.JAR** and **ludbXMLs.ZIP** files.

</studio>

To build the artifacts **in a server**, run the *[buildArtifacts.sh](/articles/16_deploy_fabric/03_offline_deploy.md#build-artifacts)* script.

<studio>

Copy the **ludb.JAR** and **ludbXMLs.ZIP** files of the relevant LUs into the target Fabric server, and run the Fabric **Deploy** command using the following syntax:

~~~
DEPLOY <LUT> WITH JAR <'jar_path'> ZIP_FILE <'zip path'> [WS_METHODS <'string'>] NOSYNC <Boolean>.
~~~




Note that if the LUT parameter is populated by a **k2_ws** (Web Service LU Type), you can populate the WS_METHODS using the list of Web Services to be deployed. If this parameter is not populated or is empty, all the WS are deployed into the Fabric server.

**Example**:

~~~
DEPLOY k2_ws WITH JAR '/home/k2view/AutoTests/Data/StudioProject/QA/Implementation/LogicalUnits/k2_ws/ludb.jar' ZIP_FILE '/home/k2view/AutoTests/Data/StudioProject/QA/Implementation/LogicalUnits/k2_ws/ludbXMLs.zip' WS_METHODS 'dbQueryOnAnyDB' NOSYNC true;
~~~




### Build and Deploy in One Step
To build the artifacts and the deployment together, in one step, from the server, run the *[buildAndDeployArtifacts](/articles/16_deploy_fabric/03_offline_deploy.md#deployment-scripts-syntax-and-options)* script.

</studio>

### Build and Deploy Scripts Syntax and Options
The following table describes the syntax and the mandatory/optional parameters for calling the deployment scripts. The scripts are located under **$K2_HOME/fabric/scripts** in the Fabric server.



#### Build Artifacts

<table>
<tbody>
   <tr>
<td>
   <p style="text-align: left;">
   <strong>buildArtifacts.sh</strong> for Linux or</p>
   <p><strong>buildArtifacts.bat</strong> for Windows</p>
   </td>
   <td>
   <p><strong>Description</strong>: Build the artifact files.</p>
   <p><strong>Usage</strong>: ./buildArtifacts.sh&nbsp;[-h --help] -pd [PATH_TO_PROJECT] -l [LUTNAME] -d [OUTPUT DIRECTION]</p>
   <p><strong>Options</strong>:</p>
   <ul>
   <li>-h/--help displays the usage of the script.</li>
   <li>-pd [PROJ_NAME] - mandatory parameter. Sets the path to the project.</li>
   <li>-l [LUTNAME] - optional parameter. If not set, the artifacts are created for the entire project.</li>
   <li>-d [OUTPUT DIRECTION] - optional parameter. If not set, the artifacts are created in the given/each LU folder.</li>
   </ul>
   </td>
   </tr>
</tbody>
</table>
<studio>

#### Build and Deploy Artifacts

<table>
<tbody>
   <tr>
   <td>
   <p><strong>buildAndDeployArtifacts.sh</strong> for Linux or <strong>buildAndDeployArtifacts.bat</strong> for Windows</p>
   </td>
   <td>
   <p><strong>Description</strong>: Build and deploy the artifact files.</p>
   <p><strong>Usage</strong>: ./buildAndDeployArtifacts.sh -pd [PATH_TO_PROJECT] -s [NOSYNC] -l [LUT_NAME] -u [USER] -p [PASSWORD] -d [DEPLOYONLY]</p>
   <p><strong>Options</strong>:</p>
   <ul>
   <li>-h/--help - displays the usage of the script.</li>
   <li>-pd [PROJ_NAME] - mandatory parameter. Sets the path to the project.</li>
   <li>-s [NOSYNC] - optional parameter. Default is <strong>True</strong>.</li>
   <li>-l [LUTNAME] - optional parameter. If not set, the deploy runs for the entire project.</li>
   <li>-u [USER] - optional parameter. Default is <strong>admin</strong>.</li>
   <li>-p [PASSWORD] - optional parameter. Default is <strong>admin</strong>.</li>
   <li>-d [DEPLOYONLY] - optional parameter. If <strong>True</strong>, the script only runs a <strong>deploy</strong> command without creating and deleting the artifacts.</li>
   </ul>
   </td>
   </tr>
   </tbody>
   </table>


### Deploy Command Syntax and Options
The following table describes the syntax and the mandatory/optional parameters when invoking the **deploy** command on the Fabric server. Note that for deployment, you can also run the *buildAndDeployArtifacts* script, using the DEPLOYONLY flag.

<table width="900px">
<tbody>
<tr>
<td width="200px">
<p><strong>DEPLOY</strong></p>
</td>
<td width="700px">
<p><strong>Usage</strong>: DEPLOY &lt;LUT&gt; WITH JAR &lt;'jar_path'&gt; ZIP_FILE &lt;'zip path'&gt; [WS_METHODS &lt;'string'&gt;] NOSYNC &lt;Boolean&gt;.</p>
<p><strong>Options</strong>:</p>
<ul>
<li>LUT - Logical Unit Type name.</li>
<li>JAR - mandatory parameter. Path to JAR file, relative to USER_DIR.</li>
<li>ZIP_FILE - optional parameter. Path to ZIP file, relative to USER_DIR.</li>
<li>NOSYNC - gets Boolean value:
<ul>
<li>NOSYNC TRUE: Only schema changes trigger sync after deploy.</li>
<li>NOSYNC FALSE: Any deploy (even without any changes) triggers sync on the first time the instance is accessed.</li>
</ul>
Note that NOSYNC FALSE is the same as checking <a href="/articles/14_sync_LU_instance/02_sync_modes.md#fabric-studio-server-configuration---force-upgrade-post-deploy-checkbox">Force Upgrade Post Deploy</a> in the Server Configuration window in the Fabric Studio.</li>
<li>SOFT_DEPLOY - optional. Default is <strong>False</strong>.
<ul>
<li>In case the value of this parameter is set to TRUE, the deploy is performed without automatic processes: user jobs, parsers and interface listener, deploy.flow.</li>
</ul>
</li>
<li>WS_METHODS - optional. When LU Type = Web Services (k2_ws), specify which methods are selected, separated by &ldquo;,&rdquo;. Empty for all.</li>
</ul>
</td>
</tr>
</tbody>
</table>
</studio>


## Offline Deployment using API calls

### Request URL Format

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white"> POST</span>   `https://<FABRIC-IP>:<FABRIC-PORT>/deploy?luName=<LUT-NAME>[&noSync=true|false][&softDeploy=true|false][&methodList=<LIST-OF-METHODS>]&[token=<APIKEY>][user=<USER-NAME>&password=<PASSWORD>]`

#### Parameters

<table>
	<thead>
		<tr>
            <th ><p><strong>Parameter</strong></p></th>
            <th ><p><strong>Description</strong></p></th>
            <th><p><strong>Mandatory</strong></p></th>
            <th><p><strong>Default</strong></p></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><p>luName</p></td>
		    <td><p>The name of the LUT that shall be deployed. Each deploy command manages only a single LUT.</p></td>
			<td><p>Y</p></td>
			<td><p></p></td>            
		</tr>
        <tr>
			<td><p>noSync</p></td>
		    <td><p>Indicates if deploy shall run in a no-sync mode, applying only schema changes, but without triggering a sync.</p></td>
			<td><p>N</p></td>
			<td><p>false</p></td>            
		</tr>
		<tr>
            <td><p>softDeploy</p></td>
            <td><p>Indicates if deploy shall run in a soft deploy mode - without triggering automatic processes.</p></td>
            <td><p>N</p></td>
   			<td><p>false</p></td>
	    </tr>
   		<tr>
            <td><p>methodList</p></td>
            <td><p>Specifies which methods to be deployed, when luName=k2_ws (Web Services). Method names shall be separated by &ldquo;,&rdquo;. When empty or not sent - whole methods are deployed.</p></td>
            <td><p>N</p></td>
   			<td><p></p></td>
	    </tr>
    </tbody>
</table>

#### Authentication & Authorization

* Authentication is done by either user and password (*user* & *password* parameters) or an API Key (*token* parameter), that shall be sent as parameters. 
* The request caller shall be authorized with the right permissions to perform the deploy on the required LUT / web-services. See [here](/articles/17_fabric_credentials/01_fabric_credentials_overview.md#list-of-permissions) for more information.

 

### Request Body

body parameters, along with the deployment files, to be sent with ContentType header = multipart/form-data

<table>
	<thead>
		<tr>
            <td ><p><strong>Parameter</strong></p></td>
            <td ><p><strong>Description</strong></p></td>
            <td><p><strong>Mandatory</strong></p></td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><p>jar</p></td>
		    <td><p>Path to the JAR file</p></td>
			<td><p>Y</p></td>
		</tr>
		<tr>
            <td><p>projectXmlData</p></td>
            <td><p>path to the ludbXMLs ZIP file. When not specified, only Java files will be deployed.</p></td>
            <td><p>N</p></td>    
	    </tr>
    </tbody>
</table>



[![Previous](/articles/images/Previous.png)](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/16_deploy_fabric/04_project_versioning.md)

