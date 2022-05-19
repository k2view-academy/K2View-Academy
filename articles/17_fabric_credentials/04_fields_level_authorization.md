# Declarative Fields Level Authorization

### Overview

Starting from Fabric V6.5.8, Fabric introduces the **Declarative field level authorization** mechanism. The purpose of this mechanism is to enable restricting the access to sensitive data using a Role Based Access approach. Fabric can expose either full or manipulated data based on the role of the user which requests it. This mechanism is applicable to all channels that can query the Fabric data: Web Services, GraphIt, Broadway actors and Fabric BI.

For example, the customer details WS retrieves an SSN which is considered a sensitive data. Most of the user roles are not allowed to see the customer's SSN while some roles (such as admin) can see it. The Declarative field level authorization mechanism allows defining a [security profile](05_security_profiles.md) which allows attaching an LU tables and [LU views](/articles/06_LU_tables/06_LU_views.md) in order to redirect the WS to retrieving the masked SSN instead of providing the original SSN. The steps how to do it are described further in this article.

### E2E Field Level Authorization Definition

The following steps must be performed in order to enable the field level authorization:

1. Apply data manipulation on a table with sensitive data. For example, you can add a new field MASKED_SSN to the CUSTOMER LU table and populate it with a masked value of SSN field in the CUSTOMER population flow:

   ![](images/masking_example_1.PNG)

2. Create an LU view that retrieves the manipulated instead of the original value. For example, create an LU view using the following query that exposes the MASKED_SSN field instead of the SSN field. Note that data manipulation can also be done using an [LUDB function](/articles/07_table_population/11_3_creating_an_LUDB_function.md).

   <img src="../06_LU_tables/images/lu_views_1.PNG" style="zoom:80%;" />

   [Click to get more information about LU views creation](/articles/06_LU_tables/06_LU_views.md).

3. Create a security profile and connect between an LU table and an LU view:

   <img src="images/security_profile_1.PNG" style="zoom:80%;" />

   [Click to get more information about the security profiles](05_security_profiles.md). 

4. Deploy the LU.

5. Open the [Web Admin](/articles/30_web_framework/03_web_admin_application.md) and assign the created security profile to the user role.

   ![](images/assign_security_profile_1.PNG)

Note that you can also use a Fabric command to [assign a security profile to the role](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#assign-security_profile-security_profile-to-role-role).



