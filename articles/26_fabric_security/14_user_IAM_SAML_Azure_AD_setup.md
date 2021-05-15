# Azure AD SAML Setup Guide

This article describes the setup and integration steps to connect your Single-Sign-On account at Azure AD (Microsoft Azure Active Directory) to Fabric and get the benefits of easy sign-in, along with authentication and authorization control via your admin tools.

### Prerequisite Requirements

- Admin access to Azure AD.
- Access to Fabric configuration settings. For more information about SAML configuration at Fabric please see [here](/articles/26_fabric_security/13_user_IAM_configiration.md#saml-configuration).

## Configuration instructions: At Azure AD

Note: some of the following will be simplified once Fabric will be part of the Azure [Active Directory Marketplace](https://azuremarketplace.microsoft.com/marketplace/apps/category/azure-active-directory-apps)

1. In the [Azure portal](https://portal.azure.com/), on the left navigation panel, select **Azure Active Directory**.

2. In the **Azure Active Directory** pane, select **Enterprise applications**. The **All applications** pane opens.

3. Select **New application**.	

   <table>
   <tbody>
   <tr>
   	<td >
       <img src="images/14_azure1.jpg">
       </td>
   </tr>
   </tbody>
   </table>

4. configure general settings like app name.

5. In the **Manage** section, select **Single sign-on** to open the **Single sign-on** pane for editing.

   <table>
   <tbody>
   <tr>
   	<td >
       <img src="images/14_azure2.jpg">
       </td>
   </tr>
   </tbody>
   </table>

6. Select **SAML** to open the SSO configuration page.  

7. Click to edit the various sections, and as explained below, similar to the below (the app name in this example is "*FabricSSO K2v*"):

   <table>
   <tbody>
   <tr>
   	<td >
       <img src="images/14_azure3.jpg">
       </td>
   </tr>
   </tbody>
   </table>

8. Edit the **Basic SAML Configuration** section (1) - information shall be taken and match to the Fabric Configuration, as explained [here](/articles/26_fabric_security/13_user_IAM_configiration.md#saml-configuration).

   - **Entity ID**
   - **Reply URL** **(Assertion Consumer Service URL)**, The location where the SAML assertion is sent with a POST operation back to Fabric. Its format: `https://<HOSTNAME>:<PORT>/api/authenticate` (host name shall be the Fabric LB).

   <table>
   <tbody>
   <tr>
   	<td >
       <img src="images/14_azure4.png">
       </td>
   </tr>
   </tbody>
   </table>

9. Edit the **User Attributes & Claims** section (2), verify that the groups are sent in a claim named "groups".

10. From the **SAML Signing Certificate** section (3) click **Download** to get the Azure IDP certificate key to be uploaded then into Fabric, for signing the authentication requests.

    <table>
    <tbody>
    <tr>
    	<td >
        <img src="images/14_azure5.png">
        </td>
    </tr>
    </tbody>
    </table>

11. Upload the public key certificate used to encrypt the SAML assertion, as exported from Fabric. See more  [here](/articles/26_fabric_security/13_user_IAM_configiration.md#saml-configuration) at SAML Configuration > Preparations > Provide to the IDP. 

12. From the 4th section - **Set up \<app-name>** (in our example "*FabricSSO K2v*") - Copy the IDP parameters - **Login URL** and **Azure AD Identifier**, to be populated at the Fabric SAML configuration for **IDP_ENTITYID** and **IDP_SINGLE_SIGN_ON_SERVICE_URL** parameters.

    <table>
    <tbody>
    <tr>
    	<td >
        <img src="images/14_azure6.png">
        </td>
    </tr>
    </tbody>
    </table>







[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/13_user_IAM_configiration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/15_user_IAM_SAML_Okta_setup.md)