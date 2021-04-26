### The Fabric 6.5 Official Release

We would like to share that we have just released Fabric 6.5. Some of the major enhancements of this release are listed below. The full list can be found in the Release Notes:

- **SAML/Active Directory/LDAP** - Authentication support
- **"No Get"** - New feature was added to be able to query data from the Fabric’s Logical Unit without performing an explicit GET command when:
  - AUTO_MDB_SCOPE = true and the query includes a WHERE clause with the filter by IID.

* New Broadway Actors were added, such as **Unzip**, image manipulation actors and more. In addition, an existing **Email** Actor now supports HTML format and can send attachments.
* Performance improvement when loading Big LUIs - by **reading chunks in parallel**. 

<img src="images/use_cases.png" alt="image" style="zoom: 67%;" />