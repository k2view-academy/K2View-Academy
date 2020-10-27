# Local Installations of Zookeeper, Kafka, and Elasticsearch

Note: All installations are subject to your legal use of the software installed, according to each software’s license terms.

K2View will not be liable for any unauthorized, unlicensed or unallowed use (“Prohibited Use”) of the software by you or for any damages caused to you or any third party as a result of the Prohibited Use.

### **Zookeeper Installation and Configuration** 

1.  Download  the  latest Zookeeper version from https://zookeeper.apache.org/releases.html. For example [**apache-zookeeper-3.6.1-bin.tar.gz**](https://eur03.safelinks.protection.outlook.com/?url=http%3A%2F%2Farchive.apache.org%2Fdist%2Fzookeeper%2Fzookeeper-3.6.1%2Fapache-zookeeper-3.6.1-bin.tar.gz&data=02|01|tali.einhorn%40k2view.com|e467cadc9c524812a6df08d86a02b5ce|994f176e677549549f9e0c719b5e9ca0|1|0|637375907175544494&sdata=AgNFm8tEyEyNBi6ROZs67s%2BvqcgF8pgQ4zUtiS6crHk%3D&reserved=0).

2.  Copy the Zookeeper zip file to your local directory. For example **C:\k2view**, and extract the zip file.

3.  Go to the **conf** sub-directory under the Zookeeper directory. For example **C:\k2view\apache-zookeeper-3.6.1-bin\conf** directory. 

4.  Rename the **zoo_sample.cfg** file to **zoo.cfg**.
5.  Open the **zoo.cfg** file for editing in any text editor like Notepad++. Find **dataDir=/tmp/zookeeper** and replace the **/tmp/zookeeper** with the Zookeeper data directory. For example **dataDir=C:\k2view\apache-zookeeper-3.6.1-bin\data**. 

6. (Optional) Modify the default Zookeeper port (2181) in the **zoo.cfg** file.

7.  Open the **System Properties** of your Windows environment and edit the **Environment Variables**:

8. Add the **ZOOKEEPER_HOME** environment variable and populate it using the Zookeeper directory. For example: **ZOOKEEPER_HOME = C:\k2view\apache-zookeeper-3.6.1-bin\**.

9. Edit the **Path** environmentvariable and add **;%ZOOKEEPER_HOME%\bin;** 

10. To run ZooKeeper, open a new cmd window from the Zookeeper directory and type **zkserver**.

  

### **Kafka Installation and Configuration**  

1.  Download the latest Kafka version from http://kafka.apache.org/downloads.html. For example [**kafka_2.12-2.5.0.tgz**](https://eur03.safelinks.protection.outlook.com/?url=http%3A%2F%2Fapache.spd.co.il%2Fkafka%2F2.5.0%2Fkafka_2.12-2.5.0.tgz&data=02|01|tali.einhorn%40k2view.com|e467cadc9c524812a6df08d86a02b5ce|994f176e677549549f9e0c719b5e9ca0|1|0|637375907175564482&sdata=ERKF0Gv2B3pEClzy0rHUb7pETIYlfsFzyNU5Q8arRtk%3D&reserved=0).

2.  Copy the Kafka zip file to  to your local directory, for example: **C:\k2view**, and extract the zip file. 
3.  Go to the **config** sub-directory under the Kafka directory, for example: **C:\K2View\kafka_2.12-2.5.0\config**. 
4.  Open the **server.properties** file for editing. Find and edit the **log.dirs=/tmp/kafka-logs** line and replace the **/tmp/kafka-logs** with **[Kafka local directory]\data**. For example **log.dir= C:\K2View\kafka_2.12-2.5.0\data**.
4.  Start Kafka, open a new cmd window from the Kafka directory and run the following command in the cmd window: **.\bin\windows\kafka-server-start.bat .\config\server.properties**

  

#### **Creating a Producer and Consumer to Test the Server** 

1.  Open a cmd window from the [Kafka directory]\bin\windows directory. For example **C:\K2View\kafka_2.12-2.5.0\bin\windows**.
2.  Run the following command in the cmd window to start a producer type:
    **kafka-console-producer.bat --broker-list localhost:9092 --topic test**.

3.  Open a new cmd window in the same location again and run the following command in the cmd window to start a consumer:
   **kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test**

 **Notes:**

- The local Kafka runs on default port 9092 and connects to the ZooKeeper’s 2181 port (default).
- Update the **config.ini** and **iifConfig.ini** Fabric files and update the Kafka port from 9093 to 9092.

### Installing the Elasticsearch 

Please follow the instructions in the following link:  https://www.elastic.co/guide/en/elasticsearch/reference/current/zip-windows.html.
