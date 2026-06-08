# Deploy a Project

Deploying a project uploads compiled LU artifacts to a Fabric server, making them active and ready for use.

Deployment not performed from [Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md) can be done in two ways:

- **Build and deploy in one step** using `buildAndDeployArtifacts.sh` — builds artifacts and deploys them in a single command.
- **Deploy pre-built artifacts** using `deploy-artifacts.sh` — deploys artifacts that were previously built and stored, for example from a separate CI build stage.

You can also deploy a single LU directly on the Fabric server using the `DEPLOY` command, or remotely using the Fabric REST API.

All script-based methods support **local** and **remote** deployment:

- **Local deploy** — connects to a running Fabric instance on the same machine via the Fabric CLI. Requires a local Fabric installation.
- **Remote deploy** — uses the Fabric HTTP API (`-r` flag). Does not require a local Fabric installation.

---

## Build and Deploy in One Step

<studio>

`buildAndDeployArtifacts.sh` orchestrates the full pipeline: build → optionally deploy environment → deploy LU artifacts. It calls `buildArtifacts.sh`, `deploy-environment.sh`, and `deploy-artifacts.sh` internally, stopping if any step fails.

The script is located under `$K2_HOME/fabric/scripts`.

**Usage:**

~~~
./buildAndDeployArtifacts.sh -pd <PATH_TO_PROJECT> [options]
~~~

**Options:**

<table>
<thead>
<tr>
<th><p><strong>Option</strong></p></th>
<th><p><strong>Description</strong></p></th>
<th><p><strong>Mandatory</strong></p></th>
<th><p><strong>Default</strong></p></th>
</tr>
</thead>
<tbody>
<tr>
<td><p>-pd / --project-dir</p></td>
<td><p>Path to the project directory. Required unless <code>-d</code> (deploy-only) is set.</p></td>
<td><p>Conditional</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-l / --lu-type-name</p></td>
<td><p>LU name(s) to build and deploy. Accepts a comma-separated list.</p></td>
<td><p>N</p></td>
<td><p>All LUs</p></td>
</tr>
<tr>
<td><p>-u / --username</p></td>
<td><p>Fabric username.</p></td>
<td><p>N</p></td>
<td><p>admin</p></td>
</tr>
<tr>
<td><p>-p / --password</p></td>
<td><p>Fabric password.</p></td>
<td><p>N</p></td>
<td><p>admin</p></td>
</tr>
<tr>
<td><p>-t / --token</p></td>
<td><p>API token for authentication. Takes precedence over username/password.</p></td>
<td><p>N</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-host / --host</p></td>
<td><p>Fabric host (IP address or URL).</p></td>
<td><p>N</p></td>
<td><p>localhost</p></td>
</tr>
<tr>
<td><p>-port / --port</p></td>
<td><p>Fabric port.</p></td>
<td><p>N</p></td>
<td><p>5124 (when host is localhost)</p></td>
</tr>
<tr>
<td><p>-r / --remote-deploy</p></td>
<td><p>Enable remote deployment via HTTP API.</p></td>
<td><p>N</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-d / --deploy-only</p></td>
<td><p>Skip the build step; deploy pre-built artifacts only.</p></td>
<td><p>N</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-sd / --soft-deploy</p></td>
<td><p>Soft deploy: skip automatic processes (jobs, parsers, interface listeners, deploy.flow).</p></td>
<td><p>N</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-s / --nosync</p></td>
<td><p>NoSync mode.</p></td>
<td><p>N</p></td>
<td><p>true</p></td>
</tr>
<tr>
<td><p>-ad / --artifact-dir</p></td>
<td><p>Path to the artifacts directory.</p></td>
<td><p>N</p></td>
<td><p>&lt;project&gt;/Implementation/LogicalUnits</p></td>
</tr>
<tr>
<td><p>-e / --environment</p></td>
<td><p>Environment name to activate after deployment.</p></td>
<td><p>N</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-ef / --environment-file</p></td>
<td><p>Path to the environment XML file.</p></td>
<td><p>N</p></td>
<td><p>&lt;project&gt;/Implementation/SharedObjects/Environments/Environments.k2fabEnv.xml</p></td>
</tr>
<tr>
<td><p>-de / --deploy-environment</p></td>
<td><p>Deploy the environment file without setting it as active.</p></td>
<td><p>N</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-v / --jdk-version</p></td>
<td><p>JDK version for compilation.</p></td>
<td><p>N</p></td>
<td><p>21</p></td>
</tr>
<tr>
<td><p>-a / --args</p></td>
<td><p>Additional arguments passed to broadway.flow.</p></td>
<td><p>N</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-h / --help</p></td>
<td><p>Displays usage information.</p></td>
<td><p>N</p></td>
<td><p></p></td>
</tr>
</tbody>
</table>

**Example — build and deploy to a remote server:**

~~~bash
./buildAndDeployArtifacts.sh \
  -pd /opt/apps/MyProject \
  -host 10.0.0.5 \
  -u admin -p mypassword \
  -r
~~~

**Example — deploy-only (pre-built artifacts) with environment activation:**

~~~bash
./buildAndDeployArtifacts.sh \
  -pd /opt/apps/MyProject \
  -host 10.0.0.5 -r \
  -d \
  -de -e Production
~~~

</studio>

---

## Deploy Pre-Built Artifacts

`deploy-artifacts.sh` deploys LU artifacts that have already been built, for example using `buildArtifacts.sh` in a separate CI stage. The script is located under `$K2_HOME/fabric/scripts`.

**Usage:**

~~~
./deploy-artifacts.sh [options]
~~~

**Options:**

<table>
<thead>
<tr>
<th><p><strong>Option</strong></p></th>
<th><p><strong>Description</strong></p></th>
<th><p><strong>Mandatory</strong></p></th>
<th><p><strong>Default</strong></p></th>
</tr>
</thead>
<tbody>
<tr>
<td><p>-host / --host</p></td>
<td><p>Fabric host (IP address or URL).</p></td>
<td><p>N</p></td>
<td><p>localhost</p></td>
</tr>
<tr>
<td><p>-port / --port</p></td>
<td><p>Fabric port.</p></td>
<td><p>N</p></td>
<td><p>5124 (when host is localhost)</p></td>
</tr>
<tr>
<td><p>-u / --username</p></td>
<td><p>Fabric username.</p></td>
<td><p>N</p></td>
<td><p>admin</p></td>
</tr>
<tr>
<td><p>-p / --password</p></td>
<td><p>Fabric password.</p></td>
<td><p>N</p></td>
<td><p>admin</p></td>
</tr>
<tr>
<td><p>-t / --token</p></td>
<td><p>API token for authentication. Takes precedence over username/password.</p></td>
<td><p>N</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-pd / --project-dir</p></td>
<td><p>Path to the project directory.</p></td>
<td><p>N</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-ad / --artifact-dir</p></td>
<td><p>Path to the artifacts directory.</p></td>
<td><p>N</p></td>
<td><p>&lt;project&gt;/Implementation/LogicalUnits</p></td>
</tr>
<tr>
<td><p>-r / --remote-deploy</p></td>
<td><p>Enable remote deployment via HTTP API.</p></td>
<td><p>N</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-sd / --soft-deploy</p></td>
<td><p>Soft deploy: skip automatic processes (jobs, parsers, interface listeners, deploy.flow).</p></td>
<td><p>N</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-l / --lu-list</p></td>
<td><p>LU name(s) to deploy. Accepts a comma-separated list.</p></td>
<td><p>N</p></td>
<td><p>All LUs in the artifact directory</p></td>
</tr>
<tr>
<td><p>-h / --help</p></td>
<td><p>Displays usage information.</p></td>
<td><p>N</p></td>
<td><p></p></td>
</tr>
</tbody>
</table>

The script enforces a fixed deployment order: `k2_ref` is deployed first, all other LUs follow, and `k2_ws` (Web Services) is deployed last.

**Example — deploy from a CI artifact store to a remote server:**

~~~bash
./deploy-artifacts.sh \
  -ad /artifacts/MyProject \
  -host 10.0.0.5 \
  -t $API_TOKEN \
  -r
~~~

---

## Deploy using the Fabric DEPLOY Command

<studio>

On the Fabric server, deploy a single LU directly using the `DEPLOY` command. Copy the `ludb.jar` and `ludbXMLs.zip` files to the target server, then run:

~~~
DEPLOY <LUT> WITH JAR <'jar_path'> ZIP_FILE <'zip_path'> [WS_METHODS <'string'>] NOSYNC <Boolean>;
~~~

**Options:**

<table>
<thead>
<tr>
<th><p><strong>Option</strong></p></th>
<th><p><strong>Description</strong></p></th>
</tr>
</thead>
<tbody>
<tr>
<td><p>LUT</p></td>
<td><p>Logical Unit Type name.</p></td>
</tr>
<tr>
<td><p>JAR</p></td>
<td><p><strong>Mandatory.</strong> Path to the JAR file, relative to USER_DIR.</p></td>
</tr>
<tr>
<td><p>ZIP_FILE</p></td>
<td><p>Path to the ZIP file, relative to USER_DIR.</p></td>
</tr>
<tr>
<td><p>NOSYNC</p></td>
<td><p>TRUE — only schema changes trigger a sync after deploy. FALSE — any deploy triggers a sync on the first instance access. Note that NOSYNC FALSE is equivalent to checking the <a href="/articles/14_sync_LU_instance/02_sync_modes.md#fabric-studio-server-configuration---force-upgrade-post-deploy-checkbox">Force Upgrade Post Deploy</a> checkbox in the Server Configuration window.</p></td>
</tr>
<tr>
<td><p>SOFT_DEPLOY</p></td>
<td><p>Default: FALSE. When TRUE, skips automatic processes: jobs, parsers, interface listeners, and deploy.flow.</p></td>
</tr>
<tr>
<td><p>WS_METHODS</p></td>
<td><p>When LUT = k2_ws (Web Services), specify which methods to deploy, separated by ",". Empty or omitted = all methods.</p></td>
</tr>
</tbody>
</table>

**Example:**

~~~
DEPLOY k2_ws WITH JAR '/home/k2view/project/k2_ws/ludb.jar' ZIP_FILE '/home/k2view/project/k2_ws/ludbXMLs.zip' WS_METHODS 'dbQueryOnAnyDB' NOSYNC true;
~~~

</studio>

---

## Deploy using API Calls

Use the Fabric REST API to deploy remotely without a local Fabric installation.

### Request URL Format

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white"> POST</span>   `https://<FABRIC-IP>:<FABRIC-PORT>/deploy?luName=<LUT-NAME>[&noSync=true|false][&softDeploy=true|false][&methodList=<LIST-OF-METHODS>]&[token=<APIKEY>][user=<USER-NAME>&password=<PASSWORD>]`

#### Parameters

<table>
	<thead>
		<tr>
            <th><p><strong>Parameter</strong></p></th>
            <th><p><strong>Description</strong></p></th>
            <th><p><strong>Mandatory</strong></p></th>
            <th><p><strong>Default</strong></p></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><p>luName</p></td>
		    <td><p>The name of the LUT to deploy. Each call deploys a single LUT.</p></td>
			<td><p>Y</p></td>
			<td><p></p></td>            
		</tr>
        <tr>
			<td><p>noSync</p></td>
		    <td><p>When true, only schema changes trigger a sync after deploy.</p></td>
			<td><p>N</p></td>
			<td><p>false</p></td>            
		</tr>
		<tr>
            <td><p>softDeploy</p></td>
            <td><p>When true, skips automatic processes after deploy.</p></td>
            <td><p>N</p></td>
   			<td><p>false</p></td>
		</tr>
   		<tr>
            <td><p>methodList</p></td>
            <td><p>When luName=k2_ws (Web Services), specifies which methods to deploy. Comma-separated. Empty or omitted = all methods.</p></td>
            <td><p>N</p></td>
   			<td><p></p></td>
		</tr>
    </tbody>
</table>

#### Authentication & Authorization

* Use either `user` + `password` parameters, or a `token` (API key).
* The caller must hold deploy permissions. See [Fabric Credentials](/articles/17_fabric_credentials/01_fabric_credentials_overview.md#list-of-permissions).

### Request Body

Send with `Content-Type: multipart/form-data`.

<table>
	<thead>
		<tr>
            <td><p><strong>Parameter</strong></p></td>
            <td><p><strong>Description</strong></p></td>
            <td><p><strong>Mandatory</strong></p></td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><p>jar</p></td>
		    <td><p>Path to the JAR file.</p></td>
			<td><p>Y</p></td>
		</tr>
		<tr>
            <td><p>projectXmlData</p></td>
            <td><p>Path to the ludbXMLs ZIP file. When not specified, only Java files are deployed.</p></td>
            <td><p>N</p></td>    
		</tr>
    </tbody>
</table>



[![Previous](/articles/images/Previous.png)](/articles/16_deploy_fabric/03_build_artifacts.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/16_deploy_fabric/05_project_versioning.md)
