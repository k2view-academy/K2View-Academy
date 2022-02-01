# Web Services Input Parameters

The following URL structure is used to call Fabric Web Services:

<p><code>http://&lt;IP address&gt;:&lt;PORT&gt;/api/[VERSION_NO]/&lt;Web-Service name&gt;?token=&lt;TOKEN NAME&gt;&amp;[format=json]</p></code>

Input parameters can be transferred to Fabric Web Services either as:
- [Part of the URL path.](/articles/15_web_services_and_graphit/08_web_services_input_parameters.md#transfering-input-parameters-as-part-of-the-url-path)
- [Not part of the URL path.](/articles/15_web_services_and_graphit/08_web_services_input_parameters.md#transferring-input-parameters-not-part-of-the-url-path)  

<studio>
 
 
### Transferring Input Parameters As Part of the URL Path

To set Web Service input parameters to be part of the Web Service URL, add them to the URL path covered by {$}.

For example: 

<img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-8-1.png" alt="drawing"/> 

In the above screenshot the **i_migrateId** and **i_runMode** input parameters have been set as part of the URL related to the **wsMigrateStatsParams** Web Service which is called as follows:  

`http://localhost:3213/api/v1/wsMigrateStatsParams/dca63eda-1553-40b7-b2e8-ae098b3c2c04/S?token=ABC&format=json`
Where:

* 'dca63eda-1553-40b7-b2e8-ae098b3c2c04' is the Migrate ID.
* 'S' is the run mode. 

</studio>
 

### Transferring Input Parameters Not Part of the URL Path

When input parameters are not set as part of the URL path, transfer them as follows:

<p><code>&nbsp;http://&lt;IP address&gt;:&lt;PORT&gt;/api/[VERSION_NO]/&lt;Web-Service name&gt;?[param1 name]=[param1 value]&amp;[param2 name]=[param2 value]...&amp;token=&lt;TOKEN NAME&gt;&amp;[format=json]</code></p>

For example:

`http://localhost:3213/api/v1/wsMigrateStatsParams?i_migrateId=dca63eda-1553-40b7-b2e8-ae098b3c2c04&i_runMode=S&token=ABC&format=json`

If an input parameters is correctly set but is not transferred as a part of the URL, the Web Service displays the relevant error code.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/07_deploy_web_services.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/09_swagger.md)


