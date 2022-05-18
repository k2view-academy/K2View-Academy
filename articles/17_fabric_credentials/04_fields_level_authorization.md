# Declarative Fields Level Authorization

### Overview

Starting from Fabric V6.5.8, Fabric introduces the **Declarative field level authorization** mechanism. The purpose of this mechanism is to enable restricting the access to sensitive data using a Role Based Access approach. Fabric can expose either full or manipulated data based on the role of the user which requests it. This mechanism is applicable to all channels that can query the Fabric data: Web Services, GraphIt, Broadway actors and Fabric BI.

For example, the customer details WS retrieves an SSN which is considered a sensitive data. Most of the user roles are not allowed to see the customer's SSN while some roles (such as admin) can see it. The Declarative field level authorization mechanism allows defining a security profile and attaching it to LU tables and [LU views](/articles/06_LU_tables/06_LU_views.md) in order to redirect the WS to retrieving the masked SSN instead of providing the original SSN. The steps how to do it are described further in this article.

### E2E Field Level Authorization Definition

The following steps must be performed in order to enable the authorization mechanism:

1. Apply data manipulation on a table with sensitive data. For example, you can add a new field MASKED_SSN to LU table CUSTOMER and population it with the masked value of SSN field in the CUSTOMER population flow:

   ![](images/masking_example_1.PNG)

2. Create an LU view that retrieves the manipulated data instead of the original. For example, create an LU view using the following query that exposes MASKED_SSN field instead of the SSN field. Note that data manipulation can also be done using an LUDB function.

   <img src="../06_LU_tables/images/lu_views_1.PNG" style="zoom:80%;" />

   [Click to get more information about LU views creation](/articles/06_LU_tables/06_LU_views.md).

3. Create a security profile and connect between an LU table and an LU view:

   <img src="images/security_profile_1.PNG" style="zoom:80%;" />

   Click to get more information about the security profiles. 

4. Deploy the LU.

5. Open the [Web Admin](/articles/30_web_framework/03_web_admin_application.md) and assign the created security profile to the user role.

   ![](images/assign_security_profile_1.PNG)



