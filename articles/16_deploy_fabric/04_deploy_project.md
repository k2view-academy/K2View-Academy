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

The script is located under `$FABRIC_HOME/fabric/scripts`.

**Usage:**

~~~
./buildAndDeployArtifacts.sh -pd <PATH_TO_PROJECT> [options]
~~~

**Options:**

The options are passed as command-line arguments. They can also be set as environment variables before running the script, although passing them as arguments is the recommended approach.

<table>
<thead>
<tr>
<th><p><strong>Option</strong></p></th>
<th><p><strong>Environment Variable</strong></p></th>
<th><p><strong>Description</strong></p></th>
<th><p><strong>Default</strong></p></th>
</tr>
</thead>
<tbody>
<tr>
<td><p>-pd / --project-dir</p></td>
<td><p>PROJ_DIR</p></td>
<td><p>Path to the project directory. Required unless <code>-d</code> (deploy-only) is set.</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-l / --lu-type-name</p></td>
<td><p>LUTNAME</p></td>
<td><p>LU name(s) to build and deploy. Accepts a comma-separated list.</p></td>
<td><p>All LUs</p></td>
</tr>
<tr>
<td><p>-u / --username</p></td>
<td><p>USERNAME</p></td>
<td><p>Fabric username.</p></td>
<td><p>admin</p></td>
</tr>
<tr>
<td><p>-p / --password</p></td>
<td><p>PASSWORD</p></td>
<td><p>Fabric password.</p></td>
<td><p>admin</p></td>
</tr>
<tr>
<td><p>-t / --token</p></td>
<td><p>TOKEN</p></td>
<td><p>Fabric API token, sent as a <code>token</code> URL parameter.</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-b / --bearer</p></td>
<td><p>BEARER_TOKEN</p></td>
<td><p>Fabric API token, sent as an <code>Authorization: Bearer</code> header instead of a URL parameter. Available from Fabric 8.5.1.</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-host / --host</p></td>
<td><p>HOST</p></td>
<td><p>Fabric host (IP address or URL).</p></td>
<td><p>localhost</p></td>
</tr>
<tr>
<td><p>-port / --port</p></td>
<td><p>PORT</p></td>
<td><p>Fabric port. See the note below the table.</p></td>
<td><p>5124</p></td>
</tr>
<tr>
<td><p>-r / --remote-deploy</p></td>
<td><p></p></td>
<td><p>Enable remote deployment via HTTP API.</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-d / --deploy-only</p></td>
<td><p>DEPLOYONLY</p></td>
<td><p>Skip the build step; deploy pre-built artifacts only.</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-sd / --soft-deploy</p></td>
<td><p>SOFTDEPLOY</p></td>
<td><p>Soft deploy: skip automatic processes (jobs, parsers, interface listeners, deploy.flow).</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-s / --nosync</p></td>
<td><p>NOSYNC</p></td>
<td><p>NoSync mode.</p></td>
<td><p>true</p></td>
</tr>
<tr>
<td><p>-ad / --artifact-dir</p></td>
<td><p>ARTIFACT_DIR</p></td>
<td><p>Path to the artifacts directory.</p></td>
<td><p>&lt;project&gt;/Implementation/LogicalUnits</p></td>
</tr>
<tr>
<td><p>-e / --environment</p></td>
<td><p>ENVIRONMENT</p></td>
<td><p>Environment name to activate after deployment.</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-ef / --environment-file</p></td>
<td><p>ENVIRONMENT_FILE</p></td>
<td><p>Path to the environment XML file.</p></td>
<td><p>&lt;project&gt;/Implementation/SharedObjects/Environments/Environments.k2fabEnv.xml</p></td>
</tr>
<tr>
<td><p>-de / --deploy-environment</p></td>
<td><p>DEPLOY_ENVIRONMENT</p></td>
<td><p>Deploy the environment file without setting it as active.</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-v / --jdk-version</p></td>
<td><p>JDK_VERSION</p></td>
<td><p>JDK version for compilation.</p></td>
<td><p>21</p></td>
</tr>
<tr>
<td><p>-a / --args</p></td>
<td><p>ARGS</p></td>
<td><p>Additional arguments passed to broadway.flow.</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-h / --help</p></td>
<td><p></p></td>
<td><p>Displays usage information.</p></td>
<td><p></p></td>
</tr>
</tbody>
</table>

> The port depends on the deploy mode. A local deploy connects to the Fabric JDBC endpoint, using the default port 5124. A remote deploy connects to the Fabric REST API endpoint, whose default port is 3213 — set it explicitly unless it is already part of the host URL.

**Authentication:** the script accepts `-b` / `--bearer`, `-t` / `--token`, or `-u` / `--username` with `-p` / `--password`. If more than one is set, `--bearer` is used first, then `--token`, then `--username`/`--password`.

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

`deploy-artifacts.sh` deploys LU artifacts that have already been built, for example using `buildArtifacts.sh` in a separate CI stage. The script is located under `$FABRIC_HOME/fabric/scripts`.

**Usage:**

~~~
./deploy-artifacts.sh [options]
~~~

**Options:**

The options are passed as command-line arguments. They can also be set as environment variables before running the script, although passing them as arguments is the recommended approach.

<table>
<thead>
<tr>
<th><p><strong>Option</strong></p></th>
<th><p><strong>Environment Variable</strong></p></th>
<th><p><strong>Description</strong></p></th>
<th><p><strong>Default</strong></p></th>
</tr>
</thead>
<tbody>
<tr>
<td><p>-host / --host</p></td>
<td><p>HOST</p></td>
<td><p>Fabric host (IP address or URL).</p></td>
<td><p>localhost</p></td>
</tr>
<tr>
<td><p>-port / --port</p></td>
<td><p>PORT</p></td>
<td><p>Fabric port. See the note below the table.</p></td>
<td><p>5124</p></td>
</tr>
<tr>
<td><p>-u / --username</p></td>
<td><p>USERNAME</p></td>
<td><p>Fabric username.</p></td>
<td><p>admin</p></td>
</tr>
<tr>
<td><p>-p / --password</p></td>
<td><p>PASSWORD</p></td>
<td><p>Fabric password.</p></td>
<td><p>admin</p></td>
</tr>
<tr>
<td><p>-t / --token</p></td>
<td><p>TOKEN</p></td>
<td><p>Fabric API token, sent as a <code>token</code> URL parameter.</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-b / --bearer</p></td>
<td><p>BEARER_TOKEN</p></td>
<td><p>Fabric API token, sent as an <code>Authorization: Bearer</code> header instead of a URL parameter. Available from Fabric 8.5.1.</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-pd / --project-dir</p></td>
<td><p>PROJECT_DIR</p></td>
<td><p>Path to the project directory.</p></td>
<td><p></p></td>
</tr>
<tr>
<td><p>-ad / --artifact-dir</p></td>
<td><p>ARTIFACT_DIR</p></td>
<td><p>Path to the artifacts directory.</p></td>
<td><p>&lt;project&gt;/Implementation/LogicalUnits</p></td>
</tr>
<tr>
<td><p>-r / --remote-deploy</p></td>
<td><p>REMOTE_DEPLOY</p></td>
<td><p>Enable remote deployment via HTTP API.</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-sd / --soft-deploy</p></td>
<td><p>SOFTDEPLOY</p></td>
<td><p>Soft deploy: skip automatic processes (jobs, parsers, interface listeners, deploy.flow).</p></td>
<td><p>false</p></td>
</tr>
<tr>
<td><p>-l / --lu-list</p></td>
<td><p>LU_LIST</p></td>
<td><p>LU name(s) to deploy. Accepts a comma-separated list.</p></td>
<td><p>All LUs in the artifact directory</p></td>
</tr>
<tr>
<td><p>-h / --help</p></td>
<td><p></p></td>
<td><p>Displays usage information.</p></td>
<td><p></p></td>
</tr>
</tbody>
</table>

> The port depends on the deploy mode. A local deploy connects to the Fabric JDBC endpoint, using the default port 5124. A remote deploy connects to the Fabric REST API endpoint, whose default port is 3213 — set it explicitly unless it is already part of the host URL.

**Authentication:** the script accepts `-b` / `--bearer`, `-t` / `--token`, or `-u` / `--username` with `-p` / `--password`. If more than one is set, `--bearer` is used first, then `--token`, then `--username`/`--password`.

The script enforces a fixed deployment order: `k2_ref` is deployed first, all other LUs follow, and `k2_ws` (Web Services) is deployed last.

**Example — deploy from a CI artifact store to a remote server:**

~~~bash
./deploy-artifacts.sh \
  -ad $FABRIC_HOME/artifacts/MyProject \
  --remote-deploy \
  --host 10.0.0.5 \
  --port 3124 \
  -b $API_TOKEN
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

Authenticate with any one of the following.

**Request header:**

* `Authorization: Bearer <APIKEY>` — available from Fabric 8.5.1.
* `Authorization: Basic <base64(user:password)>`

**URL parameters:**

* `token=<APIKEY>`
* `user=<USER-NAME>&password=<PASSWORD>`

The caller must hold deploy permissions. See [Fabric Credentials](/articles/17_fabric_credentials/01_fabric_credentials_overview.md#list-of-permissions).

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
