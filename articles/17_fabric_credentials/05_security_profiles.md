# Security Profiles

### Overview

Starting from Fabric V6.5.8, Fabric introduces the **Security Profiles** which are part of the Fabric [Declarative field level authorization](04_fields_level_authorization.md) mechanism. The purpose of security profiles is to enable restricting Fabric from exposing sensitive information to some users based on their roles. To enable this restriction, a security profile must:

1. Include a definition of at least one pair of an LU table and a respective LU view.
2. Be attached to a Fabric user role to enable the restriction mechanism.

### How Do I Create a Security Profile?

1. Go to **Project Tree** > **Logical Units** > [**LU Name**] and click on **Security Profiles** to display the **Security Profiles** window.
 
   <img src="images/security_profile_empty.PNG" style="zoom:80%;" />
3. Populate the Security Profile name and description in the upper screen part. 
   * Multiple security profiles can be created at a time, though the name must be unique.
   * If some security profiles were already created under a different Logical Unit, their names will appear in the drop-down list.
4. Then, for each security profile populate the pairs of LU table and LU view.
   * Note that each LU table can be only defined once under each security profile.
5. Save the security profile.

   <img src="images/security_profile_1.PNG" style="zoom:80%;" />
6. Deploy the Logical Unit.



