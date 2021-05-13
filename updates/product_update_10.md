### The Fabric 6.5 Official Release

We would like to share that we have just released Fabric 6.5. Some of the major enhancements of this release are listed below. The full list can be found in the Release Notes.

* **FIPS Compliancy** - Fabric encryption algorithm can now be set as FIPs certified, implemented by Bouncy Castle FIPS Java API 1.0.2.1, holding CMVP Certificate #3514.
* **User Identification and Access Management** - Fabric can now provide user identification and access management (IAM) for Web, Console and Web Services access, either using Fabric local repository or by using the organization’s identify provider (IDP) which Fabric is integrated with.
* **Query LUI Without GET ("No Get")** - Ability to query data from the Fabric’s Logical Unit without performing an explicit GET command when AUTO_MDB_SCOPE = true and the query includes a WHERE clause with the filter by IID.
* **Broadway** introduces a Profiler that analyzes the execution time per Stage / Actor / Iteration for an individual flow and as part of Statistics (JMX metrics). New Actors were added (e.g. Unzip).

And more...

<img src="images/img4.png" alt="image"  />