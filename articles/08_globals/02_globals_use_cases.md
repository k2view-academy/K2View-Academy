# Using Globals in Fabric

Globals are predefined global variables that can be accessed by different objects within a project and are used when the same information is required repeatedly by various Fabric objects. 

A Global can be used either in a map ([Table Population](/articles/07_table_population/01_table_population_overview.md) of an [LU table](/articles/06_LU_tables/01_LU_tables_overview.md) or a Reference table<studio> or Parser</studio>) or in a Java code (a [Project function](/articles/07_table_population/08_project_functions.md) or a [Web Services](/articles/15_web_services_and_graphit/01_web_services_overview.md)).

<studio>

### How Do I Add a Global to a Table Population?

1.	Go to **Project Tree** > **Logical Units** > [**LU Name**]. 
2.	Click **Tables** > [**Table Name**] > [**Population Name**] to open the existing population.
3.	Click the **Objects tab** in the right panel of the Table Population working area.
4.	Click **Globals** and then click the **global** in the **Globals list**.
5.	Drag the **global** into the working area.
6.	Connect the **global** to the **table column**.

[Click for more information about Globals – Code Examples](/articles/08_globals/04_globals_code_examples.md).

### How Do I Add a Global to a Parser Map?
1.	Go to **Project Tree** > **Logical Units** > [**LU Name**].
2.	Click **Parsers** > [**Parser Map**] to open the existing parser map.
3.	Click the **Objects tab** in the right panel of the Parser Map working area.
4.	Click **Globals** and select the **global** in the **Globals list**.
5.	Drag the **global** into the working area.
6.	Connect the **global** to the **table column**.

</studio>

### How Do I Use a Global in a Java File in Fabric?
To enable using the Globals in Java code, the following import statements are generated automatically by the Fabric infrastructure for every new Java file created in Fabric like a function or Web Service: 

<pre><code>
// import of shared Globals
import com.k2view.cdbms.shared.Globals; 
// Import of Globals under the LU
import static com.k2view.cdbms.usercode.lu.<LU name>.Globals.*; 
</code></pre>

When opening a function or a Web Service in Fabric, a list of all Globals is displayed in the **Objects tab** in the left panel of the window. 
* Globals that are defined in an LU or in Shared Objects are available for use in the LU functions of the same LU.
* Globals that are defined in Shared Objects are available for use in Web Services and in the LU functions of all LUs.

<studio>

Click the grey arrow on the left of the window to display the panel and the list of Globals.

![image](/images/08_02_01_list_of_Globals.png)

</studio>

[Click for more information about Globals – Code Examples](/articles/08_globals/04_globals_code_examples.md)

[Click for more information about How to Create Project Functions](/articles/07_table_population/10_creating_a_project_function.md)

### How Do I Override a Global Value?
Globals can be overridden via the Fabric Server using Fabric commands **without re-deploying an LU**.
* Only Globals that are not defined as Final can be overridden.  
* Globals defined as Final on a Shared Objects level but are not defined as Final under the same name on an LU level, can be overridden in the context of this LU only.
* Globals can be overridden per environment. 

For more information about Globals settings per environment, click [here](/articles/25_environments/02_create_new_environment.md).

For more information about overriding Globals using SET and SET_GLOBAL commands, click [here](/articles/08_globals/03_set_globals.md). 

[![Previous](/articles/images/Previous.png)](/articles/08_globals/01_globals_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/08_globals/03_set_globals.md)
