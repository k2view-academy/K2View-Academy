### Best Practice for Preventing SQL Injection

**Question:**

Does the product have any built-in input filter functions for detecting potential SQL injection from user Web Service inputs?

**Answer:**

Our recommendation is to use prepared statements and only use parameters as arguments to pre-built prepared statements.

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/608)



### Creating a Flow With 2 Nested GET & SELECT Statements

**Question:**

How can I create a flow with 2 nested GET with SELECT statements per each?

**Answer:**

When a GET & SELECT from LU1 is followed by loop on GET & SELECT from LU2, the exception is thrown:
*[SQLITE_ERROR] SQL error or missing database (database LU2 is locked)*
The exception is thrown when trying to RELEASE an instance of LU2 and it happens due to open results set of the SELECT on LU1. The exception is thrown only when running with Debug Mode = OFF.

This problem can be resolved by modifying the flow as follows:

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/615)



### Multiple GETs and Release Statement

**Question:**

Why is there a note in the help section of the Release Statement stating the following:

Important: When there is a use case of multiple Get in a loop on the same Fabric session, it is recommended to use the Release command (without LU name) after each iteration.

**Answer:**

It is recommended to use the Release command without the LU name,only in case you need to release the LUIs of all LUs in the session. In case you need to release an LUI of a specific LU, you need to set the LU name in the release command.

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/270)



### Encryption error in environment <env_name>

**Question:**

When trying to save the Environments file from Studio, we sometimes get an error stating "Encryption error in environment <env_name>".

Why do we get this error? What is the logical reason behind this error message?

**Answer:**

We assume that in such cases you use "Fabric URL" option at the environment definitions and you switched from one to another, or you just start using "Fabric URL". In such cases re-key is required. [Click to read about Fabric security interfaces](https://support.k2view.com/Academy_6.5/articles/26_fabric_security/04_fabric_interfaces_security.html).

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/264)



### Fabric versions supporting TDM7

**Question:**

In the past, we had a strong dependency on a specific Fabric version - e.g. Fabric version 5.3 for TDM version 5.1. Is it still the case with TDM7?
TDM7 release notes mention fabric 6.4.1, but now 6.4.2 is available. We are wondering whether  it is OK to go ahead with the latest Fabric versions TDM projects.

**Answer:**

You can run TDM 7.0.1 on either Fabric 6.4.1 or Fabric 6.4.2. TDM 7.1 is based on Fabric 6.5.

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/248)

