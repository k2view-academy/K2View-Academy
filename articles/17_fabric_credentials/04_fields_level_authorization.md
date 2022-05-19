# Declarative Field Level Authorization

### Overview

Starting from Fabric V6.5.8, Fabric introduces the **Declarative Field Level Authorization** mechanism. The purpose of this mechanism is to enable access restriction to sensitive data, using a Role Based Access approach. Fabric can expose either original or manipulated data to the Fabric user based on his predefined role. This mechanism is applicable to all channels that query the Fabric data: Web Services, GraphIt, Broadway actors and Fabric BI.

For example, the customer details WS retrieves an SSN, which is considered as sensitive data. Most user roles are not allowed to view a customer's SSN, whereas some roles (such as admin) are. The Declarative Field Level Authorization mechanism allows defining a [security profile](05_security_profiles.md) that can redirect the WS to retrieving the masked SSN as opposed of the original one. The following section describes how to field setup the **Declarative Field Level Authorization** mechanism.

### E2E Field Level Authorization Definition

1. Apply data manipulation on a table with sensitive data. For example, you can add a new field called MASKED_SSN to the CUSTOMER LU table and populate it with a masked value of the original SSN field using the CUSTOMER population flow:

   ![](images/masking_example_1.PNG)

2. Create an LU view that retrieves the manipulated value instead of the original value. For example, create an LU view, using the following query, that exposes the value of the MASKED_SSN field instead of the original SSN value. Note that data manipulation can also be done using an [LUDB function](/articles/07_table_population/11_3_creating_an_LUDB_function.md).

   <img src="../06_LU_tables/images/lu_views_1.PNG" style="zoom:80%;" />

   [Click to get more information about LU views creation](/articles/06_LU_tables/06_LU_views.md).

3. Create a security profile. Then select an LU table and assign it with a corresponding LU view.

   * Note that each LU table can only be defined once under each security profile.

   <img src="images/security_profile_1.PNG" style="zoom:80%;" />

   [Click to get more information about the security profiles](05_security_profiles.md). 

4. Deploy the LU.

5. Open the [Web Admin](/articles/30_web_framework/03_web_admin_application.md) and assign the created security profile to the user role.

   ![](images/assign_security_profile_1.PNG)

Note: You can also use a Fabric command to [assign a security profile to a role](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-security_profile-security_profile-to-role-role).



[![Previous](/articles/images/Previous.png)](03_fabric_credentials_backup.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_security_profiles.md)

