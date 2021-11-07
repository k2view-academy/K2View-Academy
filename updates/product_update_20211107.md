### The TDM 7.3 Official Release

We have just released TDM 7.3, which includes the following enhancements:

- **Enable  adding a User Group (Fabric Role) to a TDM Environment role :** this enables all  the users that belong to a given group to work on a TDM environment  without adding each one of them to the TDM environment.
- **TDM  Templates Enhancements:**
  - **Load Table Template**: automatically adding the sequence actors to the load flows. Automatically add the set target entity ID to the flow of the main target table.
  - **Update createFlowsFromTemplates flow:** adding an **incremental mode** to avoid overriding  existing flows.
  - **Update InitiateTDMLoad flow for load tasks:** run the sync of the LUI on the source environment (remote get LUI).
  - **Add deploy.flow to TDM LU** to validate the deployment of  the Web Services and the Environments to Fabric, verify that the Redis is up, and create the k2masking keyspace in Cassandra if not exists.

- **TDM APIs Enhancements:** additional filtering parameters and validations have been defined in the TAM task’s APIs.
- **Support getting the entity list using a Broadway flow** when running an extract task on all entities.
- **TDM Execution Reports:** adding the overridden execution parameters to the task execution reports.
- **Bug fixes, performance improvements and more**.

See the [Release Notes](https://support.k2view.com/Academy_6.5/Release_Notes_And_Upgrade/TDM-V7.3/TDM_Release_Notes_V7.3.pdf.html) for the full list.

<img src="images/img1.png" alt="image" style="zoom: 67%;" />
