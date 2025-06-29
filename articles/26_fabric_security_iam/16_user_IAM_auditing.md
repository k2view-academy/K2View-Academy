# User IAM Auditing

Users’ login access to Fabric is recorded in the Fabric Auditing mechanism with the information about which channel and the authenticator have been used, as follows:

- **Action** column value = "LOGIN"
- **Protocol** column value - according to the channel: 
  - For Web Access = "HTTP/1.1"
  - For Fabric console = "DRIVER" 
- **Query** column value. This is according to the authenticator name, as it appears in the configuration. 
  - If a sequence of authenticators is successfully configured, the auditing mechanism logs the authenticator that successfully authenticated the user. For example, if the authenticator list = "LDAP,Fabric" and LDAP was successful, the Query will be equal to "LDAP" with success showing in the *Result* column value. If LDAP fails, and the next authenticator is "Fabric", then the auditing entry shall contain "Fabric" as the value of the *Query* column.
  - If there is a complete authenticator failure, no authenticator is written in this column, as all authenticators failed to authenticate the user. 

For more information about the Auditing mechanism, see [here](/articles/27_auditing/01_auditing_overview.md).


[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security_iam/15_user_IAM_SAML_Okta_setup.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security_iam/17_user_IAM_custom_authenticator.md)
