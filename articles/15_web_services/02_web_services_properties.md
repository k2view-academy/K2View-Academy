# **Web Service Properties** 

Fabric Web Services properties include the definitions, methods, categories and essential metadata that contribute to the main functionalities and characteristics of the Web Service.  

## **How Do I Access Web Service Properties?** 

Go to the **Project Tree**, right click **Web** **Services** and select **New Web Service** to display the **Function Manager** window: 

<img src="/articles/15_web_services/images/Web-Service-KI-3-1.png" alt="drawing"/> 

## What Are Web Service Properties? 

**Web Service Properties** are located on the top right corner of the **Web Service** window. 

<img src="/articles/15_web_services/images/Web-Service-KI-2-1.png" alt="drawing"/> 
 
<div class="expandable unchanged js-expandable rich-diff-level-zero">
<p class="unchanged rich-diff-level-one">The following are the Web Services properties:</p>
</div>
<table class="changed rich-diff-level-zero">
<thead class="rich-diff-level-one">
<tr>
<th>Property &nbsp;</th>
<th style="text-align: left;">Description</th>
</tr>
</thead>
<tbody class="changed rich-diff-level-one">
<tr>
<td style="vertical-align: top;">Name</td>
<td>Name of the assigned Web Service function. The assigned name should be meaningful and should have a ws% prefix.</td>
</tr>
<tr>
<td>Category</td>
<td>Characteristics or class of the Web Service. Note that each category has a separate Java file.</td>
</tr>
<tr class="changed">
<td>Return Type</td>
<td class="changed">
<p>Type of Output value returned from the Web Service that is displayed in a dropdown list. For example, String, Long or Object. Output values in the list can be overridden with new values.&nbsp;</p>
</td>
</tr>
<tr>
<td>Version</td>
<td>
<p>Representation of the Web Service&rsquo;s versioning control status. A version is incorporated into the Web Service&rsquo;s URL and is used to enable several Web Service versions that are all related to the same service. By default, the version is populated by 1.</p>
<p><strong>Versioning Logic</strong>&nbsp;</p>
<ul>
<li>If the version is not part of the URL, the API will respond with the latest version.</li>
<li>If the version is part of the URL and the API version exists, the API will respond with the specified version.</li>
<li>If the version is part of the URL and the latest API version is lower, the API will respond with the latest version.</li>
<li>If the version is part of the URL and is lower than the lowest API version, the API will return the correct error response code.</li>
<li>If the version is part of the URL and is in between two API versions, the API will respond with the lower version.</li>
</ul>
</td>
</tr>
<tr>
<td>Path</td>
<td>
<p>The URL path of the Web Service. The actual name to be called by external applications when using the Web Service.</p>
<p>The URL path requires permissions and should be unique per Path / Version / Response Format / Request Format.</p>
<p>Web Service Permissions:&nbsp;<strong>set on the URL path.</strong>&nbsp;</p>
<p>For example, two Web Services sharing the same URL path:</p>
<ul>
<li>wsGetCustomerInfo version is set to 1.</li>
<li>wsGetCustomerInfoDev&nbsp;version is set to 2.</li>
</ul>
<p>The path is defined as follows:</p>
<p style="padding-left: 30px;">http://:3213/api/v1/getCustomerInfo?token=t1&amp;format=json&amp;customerId=543; When the above URL is run it invokes wsGetCustomerInfo. When the URL version is modified from v1 to v2, wsGetCustomerInfoDev is invoked.</p>
</td>
</tr>
<tr>
<td>Verb</td>
<td>
<p>Methods supported by the Web Service, as follows:&nbsp;</p>
<ul>
<li><strong>GET</strong>, get data.&nbsp;</li>
<li><strong>PUT</strong>, create new data based on the data provided.&nbsp;</li>
<li><strong>POST</strong>, update data.&nbsp;</li>
<li><strong>DELETE</strong>, delete data.</li>
</ul>
<p>To select a method, click the dropdown list and enable it. Note that at least one method should be selected.</p>
</td>
</tr>
<tr>
<td>Is Raw</td>
<td>
<p>Indicates whether the output structure should be manipulated automatically by Fabric.</p>
<p>Values are True or False.</p>
<p>Default = False. When True, Fabric brings the data response without parsing or formatting for the representation.</p>
</td>
</tr>
<tr>
<td>Produce</td>
<td>
<p>Web Service&rsquo;s output format.</p>
<p>Default format: JSON 
  
  Additional formats: XML and CSV.</p>
<p>To select the format/s, click the dropdown menu and enable them.</p>
</td>
</tr>
<tr>
<td>Description</td>
<td>Web Service&rsquo;s description on Swagger.</td>
</tr>
<tr>
<td>Result Metadata</td>
<td>
<p>Response example to be displayed in Swagger before the Web Service call.</p>
<p style="padding-left: 30px;">Set example-JSON, example-XML and example-CSV.</p>
</td>
</tr>
</tbody>
</table>
|

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/01_web_services_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/03_create_a_web_service.md)

