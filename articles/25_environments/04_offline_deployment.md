# Environment's Offline Deployment

An environment's offline deployment is used for deploying environments to a server not from Studio. 

<studio>

## Offline Deployment using scripts

Do the following:

1. Connect to a server using the Fabric Console.

2. Copy the **Environments.k2fabEnv.XML** file from the following Windows location: **[Fabric Project's Directory]\\[Project Name]\Implementation\SharedObjects\Environments** or edit the existing XML file manually.

3. Deploy it using the following command:

   ~~~
   Deploy environments from file ‘{filename}’
   ~~~

Where {filename} includes the file's path and name on the server.

Fabric encrypts the passwords in the file (if they are not already encrypted) and saves the XML file with the encrypted passwords.

Note that deploying an XML file overrides all existing environments except for *_dev*, which is the default environment. If the environment exists in Fabric, but not in the deployed XML file, it is removed from Fabric.

</studio>



## Offline Deployment using API calls

### Request URL Format

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white"> POST</span>   `https://<FABRIC-IP>:<FABRIC-PORT>/deployEnvironment?[token=<APIKEY>][user=<USER-NAME>&password=<PASSWORD>][&environment=<ENVIRONMENT-TO-SET>]`



#### Parameters

<table>
	<thead>
		<tr>
            <th width="120px" ><p><strong>Parameter</strong></p></th>
            <th ><p><strong>Description</strong></p></th>
            <th width="100px"><p><strong>Mandatory</strong></p></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><p>environment</p></td>
		    <td><p>When sent, the target fabric server set this environment to be active, similar to running <a href="/articles/25_environments/05_set_and_list_commands.md">"SET_GLOBAL ENVIRONMENT"</a> command.</p></td>
			<td><p>N</p></td>
		</tr>
    </tbody>
</table>

#### Authentication & Authorization

* Authentication is done by either user and password (*user* & *password* parameters) or by API Key (*token* parameter), that shall be sent as parameters. 
* The request caller shall be authorized with right permissions to perform the deploy (Granted with "DEPLOY_ENVIRONMENTS" permission). See [here](/articles/17_fabric_credentials/01_fabric_credentials_overview.md#list-of-permissions) for more information.

 

### Request Body

body parameters, along with the deployment files, to be sent with ContentType header = multipart/form-data

<table>
	<thead>
		<tr>
            <th width="120px"><p><strong>Parameter</strong></p></td>
            <th ><p><strong>Description</strong></p></td>
            <th width="100px"><p><strong>Mandatory</strong></p></td>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td><p>file</p></td>
		    <td><p>Path to the Environments XML file</p></td>
			<td><p>Y</p></td>
		</tr>
    </tbody>
</table>





[![Previous](/articles/images/Previous.png)](03_deploy_env_from_Fabric_Studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_set_and_list_commands.md)



</studio>



