# Deploy Web Services

Web services can be deployed to the K2view Fabric server either using the Fabric Studio or the Fabric Console (offline deployment).

### How Do I Deploy a Web Service Using the Fabric Studio?

<studio>

1. In the **Project Tree** pane, right click either **Web Services**, **Selected Web Services** or **Categories of the Web Services** to open the **Context** menu.
2. Click **Deploy to Server** to display the K2View Fabric Servers list.
3. Define that the deployed **Fabric server** opens the following **deployment** options: 
   
    a. Deploy all Web Services.
    
    b. Deploy select list of categories.
   
    c. Deploy selected list of Web Services, whereby all Web Services related to the selected categories are deployed.
    
    d. Deploy standalone Web Service.
    

[Click for more information about Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md).

</studio>


<web>

1. In the **Project Tree** pane, right click either **Web Services** to open the **Context** menu.
2. Click **Deploy to Debug** to display the K2View Fabric Servers list.
3. Define that the deployed **Fabric server**
   
[Click for more information about Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md).

</web>

### How Do I Deploy a Web Service Using the Fabric Console?

Run the DEPLOY command using the following syntax:

<p style="padding-left: 30px;">DEPLOY ‘k2_ws’ WITH JAR <'jar_path'> ZIP_FILE <'zip path'> WS_METHODS ‘ws1’, ‘ws2’;  </p>

Notes: 

- The LUT parameter must be k2_ws, which is the name of the keyspace created for the Web Services. 
- The WS_METHODS field can be populated using the list of deployed Web Services. If this field is empty, the DEPLOY command deploys all Web Services into Fabric. 

[Click for more information about Offline Deploy](/articles/16_deploy_fabric/03_offline_deploy.md).

### Deployed Web Service and the Fabric K2SYSTEM Keyspace  

- Each deployment of a Web Service creates a new record in [k2_lut_info](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) (k2system keyspace). 

- Each record maps the deployed Web Service and has its own version.

- Fabric gets the latest version of each Web Service.

- The deployment of all Web Services creates one k2_ws instance of all methods and deletes previous k2_ws instances in the k2_lut_info table.


**Notes**: 
- If a deployed Web Service is deleted from the implementation, redeploy all Web Services to delete the previous deployment of the deleted Web Service.
- The 'list ws' command returns the list of deployed Web Services and the deployed version and date and time.

## Examples 

Run the following query on k2system.k2_lut_info table:

`select lut_name, lut_version, properties from k2system.k2_lut_info where lut_name = 'k2_ws’;` 

See below the results for different scenarios:

### Deploy all Web Services  

<table>
<thead>
<tr>
<th>lut_name</th>
<th>lut_version</th>
<th>properties</th>
</tr>
</thead>
<tbody>
<tr>
<td>k2_ws</td>
<td>1591772670495</td>
<td>{'methods': ''}</td>
</tr>
</tbody>
</table>

### Deploy 'wsCustomerInfo’ and ‘wsCustomerInfo2’ Web Services 

<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th>lut_name</th>
<th>lut_version</th>
<th>properties</th>
</tr>
</thead>
<tbody>
<tr>
<td>k2_ws</td>
<td>1591772947986</td>
<td>{methods=wsCustomerInfo2}</td>
</tr>
<tr>
<td>k2_ws</td>
<td>1591772937531</td>
<td>{methods=wsCustomerInfo}</td>
</tr>
<tr>
<td>k2_ws</td>
<td>1591772670495</td>
<td>{'methods': ''}</td>
</tr>
</tbody>
</table>



### Deploy All Web Services Again 

<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th>lut_name</th>
<th>lut_version</th>
<th>properties</th>
</tr>
</thead>
<tbody>
<tr>
<td>k2_ws</td>
<td>1591773207364</td>
<td>{'methods': ''}</td>
</tr>
</tbody>
</table>



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/06_web_services_code_examples.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/08_web_services_input_parameters.md)
