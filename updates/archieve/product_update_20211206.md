### TDM 7.3 is Certified with Fabric 6.5.3

TDM 7.3 has been certified with Fabric 6.5.3. In addition, the TDM library has been fixed. Fix the overridden parameters validations in the start task API:

* It is possible to assign an individual TDM user to a specific TDM environment role, regardless of the group (Fabric role) to which he belongs. For example, the user is assigned to a TDM environment role which limits the user to select fewer entities in the task than the TDM environment role that corresponds to his group.  
* Previously, the check for the limited role did not work well. For example, before this fix, an individual user whose group TDM environment role allowed him to run a task on 10 entities, but was assigned a specific TDM environment role of only 5 entities, was still allowed run a task with 10 entities. This has been fixed, and now the user in this example is only allowed to select 5 entities when running a task.

<img src="images/img11.png" alt="image" style="zoom: 60%;" />