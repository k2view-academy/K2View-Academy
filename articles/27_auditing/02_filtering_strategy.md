# Filtering Strategy

The list of activities reported by the Auditing mechanism can be controlled. 

Once the AUDIT parameter is set to ON in **config.ini**, all Fabric activities are logged by the Fabric Auditing mechanism.

To limit the auditing to a specific predefined list of activities, the AUDIT_FILTER_STRATEGY parameter in the **config.ini** must be populated with the full path of the class defining the filter strategy. 

###  How Can I Define Auditing Filter?

To define the auditing filter, start from the sample provided as part of the Fabric installation and modify it according to your needs. Alternatively create a new class under the **com.k2view.external.fabric.audit.filters** folder.

Note that the auditing filter class must implement the **com.k2view.external.fabric.audit.filters.AuditingFilter** interface.

Do the following steps to build the artifacts:

1. Copy the project located under $K2_HOME/fabric/samples/AuditCustomStrategies locally.  

   * For example, copy from **C:\K2View\Fabric_6.5\Server\fabric\samples\AuditCustomStrategies** to your local **AuditCustomStrategies** directory.

2. Create a directory **k2view-libs** under **AuditCustomStrategies** directory.

3. Copy the files $K2_HOME/fabric/lib/fabric/fabric-common-[version-num].jar and auditing-[version-num].jar to your local **k2view-libs** folder. 

4. Open IntelliJ IDE.

5. Import project **AuditCustomStrategies** via IntelliJ menu **File > New > Project from Existing Source**.

6. Choose libraries either via IntelliJ menu **File > Project Structure** or by typing **CTRL+ALT+SHIFT+S**.

   * In the **Project Structure** screen, click **Libraries > the + sign > Java** and select the above two Jars.

     <img src="images/02_filter_select_lib.PNG" style="zoom:80%;" />

   * Click **OK**.

7. In the **Project Structure** screen, click **Artifacts > the + sign > JAR > From modules with dependencies**.

   * Choose the **Main class**, can be either All, filter strategies or persistency strategies. 

     <img src="images/02_choose_artifacts.PNG" style="zoom:80%;" />

   * Click **OK**.

8. Click **Build > Build Artifacts** in IntelliJ menu and select the **Build** action.

9. The artifacts are created under the AuditCustomStrategies/out/artifacts folder. 

10. Copy the created artifact JARs to the **$K2_HOME/ExternalJars** directory. 

11. Update the **config.ini** file with the full path of the filtering class in the **AUDIT_FILTER_STRATEGY** parameter. 

~~~
AUDIT_FILTER_STRATEGY=com.k2view.external.fabric.audit.filters.SampleFilter
~~~

12. Verify that AUDIT is set to ON.
13. Restart the Fabric node.

### Example of Reporting Web Services Only

The following example displays the **com.k2view.external.fabric.audit.filters.SampleFilter** filter class that performs auditing of the Web Service calls only.

~~~java
   package com.k2view.external.fabric.audit.filters;
   import com.k2view.fabric.common.Log;
   import com.k2view.fabric.auditing.AuditBean;
   import com.k2view.fabric.auditing.filters.AuditingFilter;

   public class SampleFilter implements AuditingFilter {
     @Override
     public boolean filter(AuditBean auditBean) {
       /*
       \* Here you should add your code
       \* When return false - the bean will not be audited
       \* When return true - the bean will be audited
       */
       if (auditBean.getAction().toLowerCase().contains("ws") || auditBean.getProtocol().toLowerCase().contains("http"))
        {
            Log.a(SampleFilter.class).info("Bean {} is passes the filter layer", auditBean.toString());
            return true;
        }
        else
        {
            Log.a(SampleFilter.class).info("Bean {} is filtered out by the filter layer", auditBean.toString());
            return false;
        }
     }
   }
~~~





[![Previous](/articles/images/Previous.png)](01_auditing_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_persistence_strategy.md) 