# Persistence Strategy

The persistence of the data published by the Auditing mechanism can be controlled. By default, the persistence is defined in the Cassandra cluster used by Fabric (k2audit.k2_auditing table), however this can be customized and published to a Kafka queue, a file system or another platform or technology.

Persistence strategy is defined in the config.ini file in the AUDIT_PERSISTENCE_STRATEGY parameter which by default, is set to **com.k2view.fabric.auditing.persistence.CassandraBeanPersistence**.

### Example of Setting Persistence Strategy

1.  Create a new class under the com.k2view.external.fabric.audit.persistencies package.

2. Implement the following interface: com.k2view.fabric.auditing.persistence.AuditBeanPersistence;

   The following is an example of the com.k2view.external.fabric.audit.persistencies.SamplePersist persistency class which writes the Audit operations into a file.

  

~~~java
package com.k2view.external.fabric.audit.persistencies;

import com.k2view.fabric.common.Log;
import com.k2view.fabric.auditing.AuditBean;
import com.k2view.fabric.auditing.persistence.AuditBeanPersistence;

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

3. Update the config.ini file and populate the full path of the class in the  AUDIT_PERSISTENCY_STRATEGY parameter. For example: AUDIT_PERSISTENCY_STRATEGY = com.k2view.external.fabric.audit.persistencies.SamplePersist

4.  Restart the Fabric node.

[![Previous](/articles/images/Previous.png)](02_filtering_strategy.md)

