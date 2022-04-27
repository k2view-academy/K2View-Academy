# Persistence Strategy

The persistence of the data published by the Auditing mechanism can be controlled. By default, the persistence is defined in the Cassandra cluster used by Fabric (k2audit.k2_auditing table), however this can be customized and published to a Kafka queue, a file system or another platform or technology.

Persistence strategy is defined in the **config.ini** using the AUDIT_PERSISTENCE_STRATEGY parameter which by default is set to **com.k2view.fabric.auditing.persistence.CassandraBeanPersistence**.

### How Can I Define Persistence Strategy?

To define the persistency strategy, set the **AUDIT_PERSISTENCE_STRATEGY** parameter to either **com.k2view.fabric.auditing.persistence.KafkaBeanPersistence** class or to your own persistency strategy class. 

In case of your own class, it must be created under the **com.k2view.external.fabric.audit.persistencies** folder and it should implement the **com.k2view.external.fabric.audit.filters.AuditBeanPersistence** interface. 

Alternatively you can start from the sample class **com.k2view.external.fabric.audit.persistencies.SamplePersist** provided as part of the Fabric installation and modify it according to your needs. 

To build artifacts, do the same steps as described [in the Filtering Strategy article](02_filtering_strategy.md).

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

### Example of Setting Persistence Strategy to Kafka Topic

When you have a requirement to make the audit records available to another channel, it can be implemented by changing the persistence from Cassandra to Kafka. For example, when you need to log the Audit records to some relational DB (e.g. PostgreSQL), you can publish them to Kafka and then create a Broadway flow that will consume the Kafka messages and load them into your required target DB.

The following example displays how to do it using the existing persistency class **com.k2view.fabric.auditing.persistence.KafkaBeanPersistence**.

Do the following:

1. Update the **config.ini** file with the full path of the class in the  **AUDIT_PERSISTENCY_STRATEGY** parameter. 

   ~~~
   AUDIT_PERSISTENCY_STRATEGY = com.k2view.fabric.auditing.persistence.KafkaBeanPersistence
   ~~~

2. Verify that AUDIT is set to ON in the **config.ini** file.

   ~~~
   AUDIT=ON
   ~~~

3. Configure the Kafka producer using the relevant parameters in the **[audit_kafka_producer]** section of the **config.ini** file.

4. Restart the Fabric node.

5. In addition, create a Broadway flow that will consume the Audit messages from Kafka topic and load them into your required target DB.

### Example of Setting Persistence Strategy to Write Into File

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

