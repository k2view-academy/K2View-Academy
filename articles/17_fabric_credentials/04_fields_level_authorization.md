# Declarative Fields Level Authorization

### Overview

Starting from Fabric V6.5.8, Fabric introduces the **Declarative Field Level Authorization** mechanism. The purpose of this mechanism is to enable access restriction to sensitive data, using a Role Based Access approach. Fabric can expose the data requesting user to either original or manipulated data, based on his predefined role in Fabric. This mechanism is applicable to all channels that query the Fabric data: Web Services, Graphit, Broadway actors and Fabric BI.

For example, the customer details WS retrieves an SSN, which is considered as sensitive data. Most user roles are not allowed to see/view a customer's SSN, whereas some roles (such as Admin) are. The Declarative Field Level Authorization mechanism allows defining a [security profile](05_security_profiles.md) that allows attaching an LU tables and [LU views](/articles/06_LU_tables/06_LU_views.md) in order to redirect the WS to retrieving the masked SSN instead of providing the original SSN. The steps of how to do it are described further in this article.

### E2E Field Level Authorization Definition

The following steps should be performed in order to enable the Field Level Authorization:

1. Apply data manipulation on a table with sensitive data. For example, you can add a new field -MASKED_SSN - to the CUSTOMER LU table and populate it with a masked value of the original SSN field in the CUSTOMER population flow:

   ![](images/masking_example_1.PNG)

2. Create an LU view that retrieves the manipulated value instead of the original value. For example, create an LU view, using the following query, that exposes/displays the MASKED_SSN field instead of the original SSN field. Note that data manipulation can also be done using an [LUDB function](/articles/07_table_population/11_3_creating_an_LUDB_function.md).

   <img src="../06_LU_tables/images/lu_views_1.PNG" style="zoom:80%;" />

   [Click to get more information about LU views creation](/articles/06_LU_tables/06_LU_views.md).

3. Create a security profile and connect between an LU table and an LU view:

   <img src="images/security_profile_1.PNG" style="zoom:80%;" />

   [Click to get more information about the security profiles](05_security_profiles.md). 

4. Deploy the LU.

5. Open the [Web Admin](/articles/30_web_framework/03_web_admin_application.md) and assign the created security profile to the user role.

   ![](images/assign_security_profile_1.PNG)

Note that you can also use a Fabric command to [assign a security profile to a role](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-security_profile-security_profile-to-role-role).



