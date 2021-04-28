# User IAM Auditing

Users’ login access to Fabric is recorded into Fabric Auditing mechanism with the information about the channel and the authenticator which has been used, as following:

- **Action** column value = "LOGIN"
- **Protocol** column value - according to the channel: 
  - For Web Access = "HTTP/1.1"
  - For Fabric console = "DRIVER" 
- **Query** column value according to the authenticator - "LDAP"/"SAML"/"FABRIC".



Note: in case of sequence of authenticators, Auditing logs the last one that activated. For example - if authenticator list = "LDAP,Fabric" and LDAP succeeded then Query will equal to "LDAP" with success as *Result* column value. in case LDAP failed and  next one - "Fabric" is used then the auditing entry shall contain "Fabric" as the *Query* column's value.





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/06_data_masking.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/26_fabric_security/05_fabric_webservices_security.md)