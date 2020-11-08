# Filtering Strategy

Users can control the list of activities to be reported by the auditing mechanism. By default once AUDIT parameter is set to ON all fabric activities are logged by the auditing mechanism.

AUDIT_FILTER_STRATEGY parameter in config.ini should be populated with a full path to the class defining the filter strategy, to allow/avoid audit of certain operations.

 

### Example of reporting only Web-Services

* Create a new class under the package "com.k2view.external.fabric.audit.filters”. 

* Implement the following Interface to define audit filter, com.k2view.fabric.session.auditing.filters.AuditingFilter;

* Save the artifacts into the following path: AuditCustomStrategies/out/artifacts/<module>/<module>.jar

* Place the artifacts under $K2_HOME/ExternalJars directory.

* Update config.ini - populate the full path of your filtering class in the AUDIT_FILTER_STRATEG parameter. 

  AUDIT_FILTER_STRATEGY=com.k2view.external.fabric.audit.filters.SampleFilter.

* Restart your Fabric node.

* Below is an example of a filter class - com.k2view.external.fabric.audit.filters.SampleFilter. 

  This class accepts auditing only for Web-services calls.

~~~java
   package com.k2view.external.fabric.audit.filters;
   import com.k2view.fabric.common.Log;
   import com.k2view.fabric.session.auditing.AuditBean;
   import com.k2view.fabric.session.auditing.filters.AuditingFilter;

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



[![Previous](/articles/images/Previous.png)](01_auditing_overview.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](03_persistence_strategy.md) 