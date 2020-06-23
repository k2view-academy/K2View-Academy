# Fabric Log Files

All activities performed in Fabric are written into log files in the server and displayed in the [Log screen in the Fabric Studio](/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md) when Fabric local is started by the Studio. When executing a Fabric command, open the log file to view errors, warnings or messages.

### Log Configuration

Logs are configured in the **logback.xml** file which is located on the Fabric server in:

<pre><code>	$K2_HOME/config/logback.xml</code></pre>
 
Settings like the [log files location and rolling policy](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md#log-files-location--rolling-policy) and the [log level](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md#log-level) can be updated in the **logback.xml** file.  

For additional information, refer to http://logback.qos.ch/manual/configuration.html.

### Log Files 

Fabric log files are located in [$K2_HOME/logs](/articles/02_fabric_architecture/02_fabric_directories.md#k2_homelogs) directory.
The location of a log file can be configured in the [logback.xml](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md#log-files-location--rolling-policy) log configuration file. 

The latest log is always named **k2fabric.log**, and is [rolled to a new file](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md#log-files-location--rolling-policy) as soon as it reaches a specific size. 

The ***.err** and ***.** **out** log files are created during [Fabric restart]( /articles/02_fabric_architecture/03_fabric_basics_getting_started.md#k2fabric-restart) and are not rolled to a new file. Always Check these files when an error occurs. 

#### **IID Finder Log Files**

The IID Finder has a separate log and configuration files:

<pre><code>	$K2_HOME/config/logback-iid_finder.xml
	$K2_HOME/config/logback-init_finder.xml
	$K2_HOME/logs/iidfinder.log
</code></pre>
**logback-iid_finder.xml** is a configuration file for IID Finder log files, while **logback-init_finder.xml** is responsible for the creation of IID Finder tables in Cassandra.\
Click for more information about the IID Finder. <!--Drop 3- add a link to IID Finder -->

### **Log Files Location & Rolling Policy**

While log files convey useful information, they grow bigger over time, and eventually their size can become an issue. This problem is addressed in the **logback.xml** configuration file. To prevent unwanted downtime, **rolling file appenders** automatically archive the current log file and resume logging in a new file when specific predefined conditions occur. Log files can be rolled based on size, date/time and a combination of size and date/time. In addition, you can configure to automatically compress and later delete old log files.

**Example:**
~~~
<appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
 <file>${FABRIC_HOME}/logs/k2fabric.log</file>
 <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
 	<fileNamePattern>${FABRIC_HOME}/logs/k2fabric.log.%d{yyyy-MM-dd}.%i.gz 
 	</fileNamePattern>
 	<maxFileSize>20MB</maxFileSize>
 	<maxHistory>10</maxHistory>
	<totalSizeCap>3GB</totalSizeCap>
 </rollingPolicy>
</appender>
~~~

The above configuration defines the **k2fabric.log** log file located in the Fabric server under the **$K2_HOME/logs** directory. The rolling policy of the above appender is based on a combination of size and date/time. A new file is created every day. The maximum size of a log file is 20 MB and the total size of the files is 3 GB. History is saved for 10 days. 

Note that to define the maxHistory in hours rather than in days, edit the fileNamePattern tag to include the following pattern: {yyyy-MM-dd-hh}.
 
When required, a new log file can be created by configuring a new **appender** in the **logback.xml** file. To do so, go to the **logback.xml** file, add the new appender and define its filename, rolling policy and other settings.

**Example:**
~~~
<appender name="InfoHandler" class="ch.qos.logback.core.rolling.RollingFileAppender">
  <file>${FABRIC_HOME}/logs/monitoring.log</file>
  <rollingPolicy class="ch.qos.logback.core.rolling. TimeBasedRollingPolicy">
  	<fileNamePattern>${FABRIC_HOME}/logs/monitoring.log.%d{yyyy-MM-dd}.gz  
 	</fileNamePattern>
 	<maxHistory>7</maxHistory>
  </rollingPolicy>
</appender>
~~~

The above configuration defines the **monitoring.log** file located in the Fabric server under the **$K2_HOME/logs** directory. The rolling policy of the above appender is based on date/time. A new file is created every day. History is saved for 7 days. 

### **Log Level**
The log level parameter defines which messages are written into the log file. There are four log levels (from the least severe to the most severe): DEBUG, INFO, WARN and ERROR. 

To print all Fabric messages into the log, set this parameter to DEBUG - the lowest level. Another reason for setting the log level to DEBUG is to make code investigations easier. However, make sure to change this parameter back to the default level (INFO) as soon as the investigation is completed. Any changes to the logback.xml file will require a Fabric restart on all relevant Fabric nodes.

Writing errors/messages/warnings into the log has an impact on both performance and disk space. Therefore, they should be written only when requested by the customer or for debugging. For better performance, reduce the log level. Consider moving from INFO to ERROR after the application is stabilized. 

**Example:**

~~~
<root level="INFO">
  <appender-ref ref="STDOUT"/>
</root>
<logger name="org.apache.thrift.server.TNonblockingServer" level="ERROR" />
<logger name="com.k2view" level="INFO" />
<logger name="com.k2view.fabric.commonArea.CommonTablesManager" level="DEBUG" />
~~~

The Root level sets the default log level. Higher or lower levels can be set for specific packages or classes. With the above setting, DEBUG messages are written into the log only for CommonTablesManager class errors.

### Writing into Log Files
Use the Fabric built-in **reportUserMessage(Level level, String message)** function to add messages into the log file. The function receives two parameters - log level (DEBUG, INFO, WARN, or ERROR) and message.

The log level is optional, although it is recommended to set it. When the log level is set to DEBUG, messages can remain in the code  since they do not appear once the log level in **logback.xml** is set to INFO or higher level.

In addition, you can use the log.info, log.debug, log.warn and log.error Java methods to write into log files. 

####  Add Connection Leaks to the Log File
The SUSPECTED_CONNECTION_LEAK_SEC setting in the **config.ini** file enables getting information about connections which are suspected as leaked into the error file. By default, this setting = 0. When set to a value greater than 0, a connection that is idle for this many seconds is suspected as leaked. The system logs this into the **k2fabric.log** and shows a stack trace of the getConnection() method to indicate the code causing the leaky connection.


### Logs Management and Cleanup
Due to disk space limitations, logs are automatically deleted after several days, based on the configuration in the **logback.xml** file. 

K2View recommends the following for managing logs and cleanup:

- Check the default settings in the **logback.xml** file related to saving history and validate they fit your business needs:
rollingPolicy, maxFileSize, maxHistory and totalSizeCap. 
	
- Define the Crontab expression to delete ***.err** and ***.** **out** log files since they are not configured via the **logback.xml** file. 

- To keep the log files history for a longer period, for example due to regulations, integrate Fabric with a central syslog system like ELK Stack (Elasticsearch, Logstash and Kibana), Splunk, or other. 


[![Previous](/articles/images/Previous.png)](/articles/21_Fabric_troubleshooting/01_Fabric_troubleshooting_overview.md)
