# Filtering Strategy

The list of activities reported by the Auditing mechanism can be controlled. By default, once the AUDIT parameter is set to ON, all Fabric activities are logged by the Auditing mechanism. To allow / avoid auditing specific activities, the AUDIT_FILTER_STRATEGY parameter in the config.ini file must be populated with the full path of the class defining the filter strategy. 

 

### Example of Reporting Web Services Only

1. Create a new class under the com.k2view.external.fabric.audit.filters folder. 

2. To define the Auditing filter, implement the **com.k2view.fabric.auditing.filters.AuditingFilter;** interface. 

3. Save the artifacts into the following path: AuditCustomStrategies/out/artifacts/<module>/<module>.jar under the $K2_HOME/ExternalJars directory.

5. Update the config.ini file and populate the full path of the filtering class in the AUDIT_FILTER_STRATEG field. 
    AUDIT_FILTER_STRATEGY=com.k2view.external.fabric.audit.filters.SampleFilter.

6. Restart the Fabric node.

The following example displays the com.k2view.external.fabric.audit.filters.SampleFilter filter class that accepts auditing for Web Service calls only.

~~~java
   package com.k2view.external.fabric.audit.filters;
   import com.k2view.fabric.common.Log;
   import com.k2view.fabric.auditing.AuditBean;
   import com.k2view.fabric.auditing.filters.AuditingFilter;

   public class SampleFilter implements AuditingFilter {
     @Override
     public boolean filter(AuditBean auditBean) {
       /*
       \* Here You should add your code
       \* when return false - the bean will not be audited
       \* when return true - the bean will be audited
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

In order to build the artifacts following steps should be implemented:

1) Copy the project located under $K2_HOME/fabric/samples/AuditCustomStrategies to your selected location.

2) Create a directory called k2view-libs under AuditCustomStrategies directory.

3) Copy the files $K2_HOME/fabric/lib/fabric-common.jar and fabric-session.jar to k2view-libs folder. 

4) Open IntelliJ IDE.

5) Import project AuditCustomStrategies.

6) Type CTRL+ALT+SHIFT+S and choose Artifacts.

7) Use the + sign

8) Use Jar -> From modules with dependency

9) Use Build->build Artifacts

[![Previous](/articles/images/Previous.png)](01_auditing_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_persistence_strategy.md) 