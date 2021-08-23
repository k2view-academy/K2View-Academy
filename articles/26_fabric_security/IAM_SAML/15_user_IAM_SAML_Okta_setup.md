# Okta SAML Setup Guide

This article describes the setup and integration steps to connect your Single-Sign-On account at Okta to Fabric and get the benefits of easy sign-in, along with authentication and authorization control via your admin tools.

## Prerequisite Requirements

- Admin access to Okta.
- Access to Fabric configuration settings. For more information about SAML configuration at Fabric please see [here](/articles/26_fabric_security/13_user_IAM_configiration.md#saml-configuration).

## Configuration instructions: At Okta

#### Select Fabric from Okta App Gallery

1. Log into the Okta Admin console as an Admin. 

2. In the Admin Console, go to **Applications** > **Applications** via the left navigation bar.

3. At the *Applications* page, click **‘Browse App Catalog’** .

   <table>
   <tbody>
   <tr>
   	<td >
       <img src="images/15_okta1_browse_apps.png">
       </td>
   </tr>
   </tbody>
   </table>
   
   
   
4. At the *Browse App Integration Catalog* page, search for "Fabric by K2View" via the search box and select it.

   <table>
   <tbody>
   <tr>
   	<td width="700pxl">
       <img src="images/15_okta2_search.png">
       </td>
   </tr>
   </tbody>
   </table>

5. On the *Fabric by K2View* app page, click **'Add'**.

   <table>
   <tbody>
   <tr>
   	<td>
       <img src="images/15_okta3_add_k2view.png">
       </td>
   </tr>
   </tbody>
   </table>
   
   

6. On next page, click **'Done'**. The app is created and is ready to configure the specific deployment setup.

   <table><tbody><tr>    <td>    <img src="images/15_okta4_done.png">    </td></tr></tbody></table>



#### Setup Configuration Settings

7. Assign users or groups to the application, via the *Assignments* tab, by using the **'Assign'** button.  Assigning groups will assign the users who are associated to that groups.

   <table><tbody><tr>    <td>    <img src="images/15_okta5_assignments.png">    </td></tr></tbody></table>

   

8. Complete the SAML SSO configuration via the *Sign On* tab, by clicking on **'Edit'** and fill the relevant fields. Once done - click **'Save'**. 

   - **groups**, At the select list choose "*Matches regex*" and fill ".*" in the form field. 

   - **Encryption Certificate**, Browse to the public key certificate used to encrypt the SAML assertion, as exported from Fabric. Then click on '**Upload**'. See more [here](/articles/26_fabric_security/13_user_IAM_configiration.md#saml-configuration) about the Fabric certificate file.

   - **ACS URL**, as provided by Fabric team. it shall be similar to this pattern: "https://\<fabric-external-domain-name>:\<port>/api/authenticate".

   - **Audience URI (SP Entity ID)**, The intended audience of the SAML assertion. This is the Fabric **Entity ID**. it shall be similar to this pattern: "https://\<fabric-external-domain-name>".

   - **Application username format**, At the select list choose "*Email".*

     <table><tbody><tr>    <td>    <img src="images/15_okta6_setup_config.png">    </td></tr></tbody></table>

     

#### Collect the IDP properties to be set at Fabric configuration

9. At the "**Sign On**" tab, click on "View Setup Instructions".

   <table><tbody><tr>    <td width="700pxl">    <img src="images/15_okta8.png">    </td></tr></tbody></table>

10. A new browser tab will be opened with the information that must be copied to Fabric configuration (IDP URL and IDP issuer/entity ID) and the certificate key to be uploaded into Fabric, for signing the authentication requests.

    <table><tbody><tr>    <td width="700pxl">    <img src="images/15_okta9.jpg">    </td></tr></tbody></table>

    

[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/IAM_SAML/14_user_IAM_SAML_Azure_AD_setup.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/IAM_SAML/16_user_IAM_auditing.md)

