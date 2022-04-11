<studio>

# Persistence Strategy

The persistence of the data published by the Auditing mechanism can be controlled. By default, the persistence is defined in the Cassandra cluster used by Fabric (k2audit.k2_auditing table), however this can be customized and published to a Kafka queue, a file system or another platform or technology.

Persistence strategy is defined in the **config.ini** using the AUDIT_PERSISTENCE_STRATEGY parameter which by default is set to **com.k2view.fabric.auditing.persistence.CassandraBeanPersistence**.

### How Can I Define Persistence Strategy?

To define the persistency strategy, start from the sample provided as part of the Fabric installation and modify it according to your needs. Alternatively create a new class under the **com.k2view.external.fabric.audit.persistencies** folder.

Note that the persistency strategy class must implement the **com.k2view.external.fabric.audit.filters.AuditBeanPersistence** interface.

To build artifacts, do the same steps as described [here](02_filtering_strategy.md).

Then, do the following:

1. Update the **config.ini** file with the full path of the class in the  **AUDIT_PERSISTENCY_STRATEGY** parameter. 

   ~~~
   AUDIT_PERSISTENCY_STRATEGY = com.k2view.external.fabric.audit.persistencies.SamplePersist
   ~~~

2. Verify that AUDIT is set to ON in the **config.ini** file.

   ~~~
   AUDIT=ON
   ~~~

3. Restart the Fabric node.

### Example of Setting Persistence Strategy

The following example displays the persistency class **com.k2view.external.fabric.audit.persistencies.SamplePersist** which writes the Audit operations into a file.

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



[![Previous](/articles/images/Previous.png)](02_filtering_strategy.md)

</studio>
