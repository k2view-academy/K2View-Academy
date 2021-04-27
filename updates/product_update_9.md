### The Fabric 6.5 Official Release

We would like to share that we have just released Fabric 6.5. Some of the major enhancements of this release are listed below. The full list can be found in the Release Notes.

* **SAML/Active Directory/LDAP** - Authentication support.
* **Query LUI Without GET ("No Get")** - Ability to query data from the Fabric’s Logical Unit without performing an explicit GET command when AUTO_MDB_SCOPE = true and the query includes a WHERE clause with the filter by IID.
* In **iidFinder**, an ability to configure the Consumer Group ID via **iifConfig.ini** was added. In addition, some properties were removed.
* New Broadway Actors were added, such as **Unzip**, image manipulation and more. In addition, an existing **Email** Actor now supports HTML format and can send attachments.
* Performance improvement when saving Big LUIs to Cassandra. 

<img src="images/img4.png" alt="image"  />
