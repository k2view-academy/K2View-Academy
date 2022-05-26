# User IAM Auditing

Users’ login access to Fabric is recorded into the Fabric Auditing mechanism with the information about which channel and the authenticator has been used, as follows:

- **Action** column value = "LOGIN"
- **Protocol** column value - according to the channel: 
  - For Web Access = "HTTP/1.1"
  - For Fabric console = "DRIVER" 
- **Query** column value. This is according to the authenticator name, as it appears in the configuration. 
  - If a sequence of authenticators are successfully configured, the auditing mechanism logs the authenticator that successfully authenticated the user. For example - if the authenticator list = "LDAP,Fabric" and LDAP was successfull, the Query will be equal to "LDAP" with success showing in the *Result* column value. If LDAP failed, and the next authenticator is "Fabric", then the auditing entry shall contain "Fabric" as the *Query* column's value.
  - If there is a complete authenticator failure, no authenticator is written in this column as all authenticators failed to authenticate the user. 



For more information about the Auditing mechanism see [here](/articles/27_auditing/01_auditing_overview.md).


