# Auditing Mechanism — Persistence

The persistence of the data reported by the Auditing mechanism can be controlled. Fabric offers three persistence options that are written to:

* System DB — **com.k2view.fabric.auditing.persistence.SystemDbBeanPersistence** (default) — audit data is written to **k2_auditing** table in the metadata keyspace (k2audit by default). 
* Kafka — **com.k2view.fabric.auditing.persistence.KafkaBeanPersistence** — audit data is written to the default **k2audit** topic.
* Fabric log — **com.k2view.fabric.auditing.persistence.AuditLog** — audit data is written to the [k2Fabric log files](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md).



Persistence strategy is defined in the **config.ini** using the `AUDIT_PERSISTENCE_STRATEGY` parameter, which defaults to **com.k2view.fabric.auditing.persistence.SystemDbBeanPersistence**.

In addition to the product strategies described above, a new strategy can be defined by creating your own class. The required steps are explained further on in this article.

### How Can I Set The Persistence Strategy to Kafka?

In order to switch the persistence strategy to Kafka, perform the following actions:

1. Update the **AUDIT_PERSISTENCY_STRATEGY** parameter in the **config.ini** file to: 

   ~~~
   AUDIT_PERSISTENCE_STRATEGY = com.k2view.fabric.auditing.persistence.KafkaBeanPersistence
   ~~~

2. Verify that AUDIT is set to ON in the **config.ini** file.

   ~~~
   AUDIT=ON
   ~~~

3. Configure the Kafka producer using the relevant parameters in the **[audit_kafka_producer]** section of the **config.ini** file.

4. Restart the Fabric node.

#### Example — Logging Audit to PostgreSQL via Kafka

When there is a requirement to make the audit records available to another channel, the persistence strategy should be changed from System DB to Kafka. 

For example, when you need to log the Audit records into some relational DB (e.g., PostgreSQL), you can publish them to Kafka. To do this, update the **AUDIT_PERSISTENCE_STRATEGY** parameter in the **config.ini** to **com.k2view.fabric.auditing.persistence.SystemDbBeanPersistence** and restart the Fabric node as explained above.

Then, create a Broadway flow that will consume the Kafka messages and load them into your required target DB. 

The below Broadway flow consumes the Audit messages from Kafka topic and loads them into the target PostgreSQL DB.

![](images/03_kafka_persistance.png)

The Kafka message appears as follows:

~~~json
{
	"action": "GetCommand",
	"username": "admin",
	"result": "LU type 'OracleLu' not found. Please deploy it using K2view Fabric Studio",
	"sessionId": "264adf99",
	"query": "get OracleLu.5 ",
	"params": {
		"DC_NAME": "null",
		"LU_NAME": "OracleLu",
		"IID": "5"
	},
	"protocol": "DRIVER",
	"address": "127.0.0.1"
}
~~~



### How Can I Define A New Persistence Strategy?

In order to define a new persistency strategy, create your own persistency strategy class. Alternatively, you can start with the sample class **com.k2view.external.fabric.audit.persistencies.SamplePersist** provided as part of the Fabric installation and modify it as needed. 

In case of your own class, it must be created under the **com.k2view.external.fabric.audit.persistencies** folder and should implement the **com.k2view.external.fabric.audit.filters.AuditBeanPersistence** interface. 

Build the artifacts using the same steps as described [in the Filtering Strategy article](02_filtering_strategy.md). Then, perform the following actions:

1. Update the **config.ini** file with the full path of the class in the **AUDIT_PERSISTENCE_STRATEGY** parameter. For example:

   ~~~
   AUDIT_PERSISTENCE_STRATEGY = com.k2view.external.fabric.audit.persistencies.SamplePersist
   ~~~

2. Verify that AUDIT is set to ON in the **config.ini** file.

   ~~~
   AUDIT=ON
   ~~~

3. Restart the Fabric node.

#### Example - Setting New Persistence Strategy

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

