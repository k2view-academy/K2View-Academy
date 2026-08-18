# Microsoft Entra ID SAML Setup Guide

This article describes how to connect your Microsoft Entra ID Single Sign-On (SSO) to Fabric. This integration enables streamlined sign-in as well as admin-level control over authentication and authorization.

You can read more about the guidelines at this link: [Azure AD](https://docs.microsoft.com/en-us/azure/active-directory/saas-apps/fabric-tutorial), as Fabric is a part of the Microsoft's Entra ID app marketplace. 

## Table of Contents

- [Prerequisite Requirements](#prerequisite-requirements)
- [Configuration instructions: For Microsoft Entra ID](#configuration-instructions-for-microsoft-entra-id)
  - [Step 1–3: Add Fabric Application](#step-1-3-add-fabric-application)
  - [Step 4–5: Select Fabric from Gallery](#step-4-5-select-fabric-from-gallery)
  - [Step 6–8: Configure Single Sign-On](#step-6-8-configure-single-sign-on)
  - [Step 9: Basic SAML Configuration](#step-9-basic-saml-configuration)
  - [Step 10: User Attributes & Claims](#step-10-user-attributes--claims)
  - [Step 11: SAML Signing Certificate](#step-11-saml-signing-certificate)
  - [Step 12: Upload Public Key Certificate](#step-12-upload-public-key-certificate)
  - [Step 13: Copy IDP Parameters](#step-13-copy-idp-parameters)
- [Configuration instructions: For Fabric](#configuration-instructions-for-fabric)


### Prerequisite Requirements

- Admin access to Microsoft Entra ID.
- Access to Fabric configuration settings. For more information about SAML configuration at Fabric, please read [here](/articles/26_fabric_security_iam/13_user_IAM_configuration.md#saml-configuration).

## Configuration instructions: For Microsoft Entra ID

1. In the [Microsoft Entra admin center](https://portal.azure.com/), on the left navigation panel, select Identity → Overview (or simply Microsoft Entra ID).

2. In the Microsoft Entra ID pane, select Enterprise applications. The All applications page opens.

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

4. In the **Add from the gallery** section, type **Fabric** in the search box.

5. Select **Fabric** from the results panel and then add the app. Wait a few seconds while the app is added to your tenant.

6. On the **Fabric** application integration page, find the **Manage** section and select **single sign-on** to open the **Single sign-on** pane for editing.

7. Select **SAML** to open the SSO configuration page.  

8. Click to edit the various sections, similar to what is shown below (the app name in this example is "*FabricSSO K2v*"):

   <table>
   <tbody>
   <tr>
   	<td >
   	<img src="images/14_azure3.jpg">
   	</td>
   </tr>
   </tbody>
   </table>

9. In the **Basic SAML Configuration** section (1), use values matching your Fabric configuration (see the [Fabric SAML Configuration article](/articles/26_fabric_security_iam/13_user_IAM_configuration.md#saml-configuration)).

   - **Identifier (Entity ID)** 
   - **Reply URL (Assertion Consumer Service / ACS URL)** – where Microsoft Entra will send the SAML assertion; typically `https://<HOSTNAME>:<PORT>/api/authenticate` where `<HOSTNAME>` is your Fabric load balancer.
   - **Sign-on URL** (if required) – often the same or similar to the Reply URL, depending on your setup.


   <table>
   <tbody>
   <tr>
   	<td >
   	<img src="images/14_azure4.png">
   	</td>
   </tr>
   </tbody>
   </table>

10. Configure the group claim.
    1. In Attributes & Claims, select Add a group claim.
    2. Select the groups that should be included in the SAML assertion.
    3. Expand Advanced options and select Customize the name of the group claim.
    4. Enter groups as the claim name.
    5. Click Save.

Verify that the Attributes & Claims list contains a claim named groups and that the groups required for Fabric authorization are included in the SAML assertion.

11. From the **SAML Signing Certificate** section (3), click **Download** to obtain the Microsoft Entra ID certificate key, which will be uploaded into Fabric for signing authentication requests.

  <table>
  <tbody>
  <tr>
  	<td >
  	<img src="images/14_azure5.png">
  	</td>
  </tr>
  </tbody>
  </table>

12. Upload the public key certificate used to encrypt the SAML assertion, as exported from Fabric. Read more [here](/articles/26_fabric_security_iam/13_user_IAM_configuration.md#saml-configuration) in SAML Configuration > Preparations > Provide to the IDP. 

    <table>
    <tbody>
    <tr>
    	<td >
    	<img src="images/14_azure7.png">
    	</td>
    </tr>
    </tbody>
    </table>

13. From the 4th section - **Set up \<app-name>** (in our example "*FabricSSO K2v*") - copy the IDP parameters - **Login URL** and **Microsoft Entra ID Identifier**, to be populated at the Fabric SAML configuration for **IDP_ENTITYID** and **IDP_SINGLE_SIGN_ON_SERVICE_URL** parameters.

    <table>
    <tbody>
    <tr>
    	<td >
    	<img src="images/14_azure6.png">
    	</td>
    </tr>
    </tbody>
    </table>



## Configuration instructions: For Fabric

In addition to the instructions detailed [here](/articles/26_fabric_security_iam/13_user_IAM_configuration.md#saml-configuration), setting up SAML with Microsoft Entra ID requires adding a configuration parameter to the config.ini file: `SECURITY_WANT_NAMEID_ENCRYPTED=false`


[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security_iam/13_user_IAM_configuration.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security_iam/15_user_IAM_SAML_Okta_setup.md)

