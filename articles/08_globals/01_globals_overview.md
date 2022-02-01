# Globals Overview

### What Are Globals?
Globals are predefined variables that can be accessed by different objects within a project and are used when the same information is required repeatedly by various Fabric objects. For example, to define the source application version or a date format in order use the same value in several Fabric objects. 
* Globals are saved in a Java file as static variables and can be used by all Fabric object types like [Project functions](/articles/07_table_population/08_project_functions.md)<studio> or [Table Population](/articles/07_table_population/01_table_population_overview.md) objects</studio>.
* Globals can be defined as Final whereby they cannot be overridden in Java code or by a command that runs on the Fabric server.
* Globals that are not defined as Final can be overridden.

[Click for more information about Global's override using SET and SET_GLOBAL commands](/articles/08_globals/03_set_globals.md).

The scope of a Global depends on how it is defined, which can be either:
* [Shared Objects](/articles/04_fabric_studio/12_shared_objects.md), whereby the Global is available to all objects in a project under all Logical Units, Reference Tables and [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md).
* [Logical Unit](/articles/03_logical_units/01_LU_overview.md), whereby the Global is available within the specific Logical Unit where it is defined.

If the same Global is defined at both Shared Objects and Logical Unit levels, the Logical Unit definition is used within the scope of that Logical Unit. Other Logical Units use the Shared Objects definition.

<web>

The global variables are maintained in the **Globals.java** located under **Java/src** either in the Shared Objects or in the Logical Unit.

</web>

<studio>

### How Do I Create or Edit a Global?

1. Do either:
    * Go to **Project Tree** > **Logical Units > [LU name] > Java** and then click **Globals.java** to open the **Globals** window.
    * Go to **Project Tree** > **Shared Objects > Java** and then click **SharedGlobals.java** to open the **Globals** window. 
      ![image](images/08_01_01%20Globals%20window.png)
2. Populate the settings as follows:
  * Enter a **Global Name** in the **Name** column.
  * Enter a value in the **Value** column.
  * (Optional) Enter a **Category** in the **Category** column.
  * (Optional) Enter a **Comment** in the **Comment** column.
  * Check if the **Global** is **Final**.
3. Click **Save**.

**Notes**
* Each Global defined via the Globals window is created in either Globals.java under the LU or in SharedGlobals.java under the Shared Object. 
* A Global can be edited via the respective Globals window or by opening the source file in Fabric Studio. 

</studio>

[Click for more information about using Globals - Code Examples](/articles/08_globals/04_globals_code_examples.md).




[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/08_globals/02_globals_use_cases.md)
