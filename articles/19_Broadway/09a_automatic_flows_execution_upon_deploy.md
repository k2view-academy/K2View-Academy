# Automatic Flows Execution Upon Deploy

A Broadway flow can run automatically as a result of a Logical Unit Deploy activity. Once the **deploy.flow** Broadway flow is defined under a selected LU, each time a deploy is initiated for the LU, it will trigger the execution of the **deploy.flow**.

* If the **deploy.flow** is defined on the Shared level only, it will be inherited by all the LUs.

* If a Soft Deploy is defined to the deployed environment, a deploy will not trigger the **deploy.flow** execution.

When a new Logical Unit is created, a **deploy.flow** will be generated automatically with the following constants:

* **lu_name** - contains the name of deployed LU.

* **nosync**

  * NOSYNC TRUE: Only schema changes trigger a sync after a deploy.
  * NOSYNC FALSE: Any deploy (even without any changes) triggers a sync the first time an instance is accessed.

* **is_first_deploy** - A Boolean indicating if this is the first deploy.

* **is_studio** - This value is true if this is the Studio debug environment Fabric instance.

<studio>

[![Previous](/articles/images/Previous.png)](09_broadway_integration_with_Fabric.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](17_tutorial_and_flow_examples.md)

</studio>

<web>

[![Previous](/articles/images/Previous.png)](08_show_only_connected_actors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](17_tutorial_and_flow_examples.md)

</web>