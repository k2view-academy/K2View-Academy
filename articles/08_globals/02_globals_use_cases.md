# Using Globals in Fabric

Globals are predefined global variables that can be accessed by different objects within a Project and are used when the same information is required repeatedly by various Fabric objects. 

A Global can be used either in a map (Table Population of an [LU Table](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/06_LU_tables/01_LU_tables_overview.md) or a Reference Table or Parser) or in a Java code (a Project function or a Web Service).
 
The following scenarios describe how Globals can be used in Fabric objects.

### How do I Add a Global to a Table Population?
1.	Go to the **Project Tree**, click **Logical Units** and then click **LU name**. 
2.	Click Tables, <Table name> and then click **Table Population**.
3.	Click the Objects tab in the right panel of the Table Population working area.
4.	Click Globals and then click the Global in the Globals list.
5.	Drag the Global into the working area.
6.	Connect the Global to the table column.

**Click for more information about [Globals – Code Examples](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/04_globals_code_examples.md).**

### How do I Add a Global to a Parser Map?
1.	Go to the Project Tree, click Logical Units and then click **LU name**.
2.	Click Parsers and then click Parser Maps.
3.	Click the Objects tab in the right panel of the Parser Map working area.
4.	Click Globals and select the Global in the Globals list.
5.	Drag the Global into the working area.
6.	Connect the Global to the table column.



### How do I Use a Global in a Java File in Fabric?
To enable using the Globals in Java code, the following import statements are generated automatically by the Fabric infrastructure for every new Java file created in Fabric like a function or Web Service: 

<pre><code>
// import of shared Globals
import com.k2view.cdbms.shared.Globals; 
// Import of Globals under the LU
import static com.k2view.cdbms.usercode.lu.<LU name>.Globals.*; 
</code></pre>

When opening an LU function or a Web Service in Fabric, a list of all Globals is displayed in the **Objects** tab in the left panel of the window. 
* Globals defined in LU Globals or in Shared Globals are available for use in LU Functions.
* Globals that are only defined in Shared Globals are available for use in Web Services.

Click the grey arrow on the left of the window to display the panel and the list of Globals.

![image](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/images/08_02_01%20list%20of%20Globals.png)

[**Click for more information about Globals – Code Examples.**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/04_globals_code_examples.md)\
**Click for more information about How to Create Project Functions.**

### How do I Override a Global Value?
Globals can be overridden via the Fabric Server using Fabric commands **without re-deploying an LU implementation**.
* Only Globals that are not defined as Final can be overridden. 
* Globals defined as Final on a Shared Objects level but are not defined as Final under the same name on an LU level, can be overridden in the context of this LU only.

**Click for more information about [Globals Override Commands](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/03_set_globals.md).** 








 
