# Persistence Strategy

Users can control the persistence of data to be published by the auditing mechanism. By default persistence will be defined as the Cassandra cluster used by Fabric, however it can be customized to be published to Kafka queue or file system or any other platform/technology.

The persistence strategy is defined on config.ini by AUDIT_PERSISTENCE_STRATEGY parameter (default is set to com.k2view.fabric.session.auditing.persistence.CassandraBeanPersistence).

### Example of setting persistence as file

* Create a new class under the package "com.k2view.external.fabric.audit.persistencies”. Implement the following Interface: com.k2view.fabric.session.auditing.persistence.AuditBeanPersistence;

* Below is an example of a persistency class: com.k2view.external.fabric.audit.persistencies.SamplePersist. 

  This class writes the audit operations into a file.

  

~~~java
package com.k2view.external.fabric.audit.persistencies;

import com.k2view.fabric.common.Log;
import com.k2view.fabric.session.auditing.AuditBean;
import com.k2view.fabric.session.auditing.persistence.AuditBeanPersistence;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class SamplePersist implements AuditBeanPersistence {
    @Override
    public void persist(AuditBean auditBean) throws Exception {
        /*
         * Here you should add your way of how to persist the bean
         */
        Date d = new Date();
        
        String fileName="/home/k2view/AuditFiles/AuditFile.txt";
        
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        writer.write("Bean {} will not be persisted  "+ auditBean.toString());
        writer.flush();
        writer.close();
        Log.a(SamplePersist.class).info("Bean {} will not be persisted", auditBean.toString());
    }
}

~~~

* Update config.ini - populate the full path of your class into  AUDIT_PERSISTENCY_STRATEGY parameter. For example:

  AUDIT_PERSISTENCY_STRATEGY = com.k2view.external.fabric.audit.persistencies.SamplePersist

* Restart fabric node.

[![Previous](/articles/images/Previous.png)](02_filtering_strategy.md)

