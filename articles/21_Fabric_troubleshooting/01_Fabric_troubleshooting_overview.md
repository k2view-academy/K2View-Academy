# Fabric Troubleshooting Overview

Fabric provides the following tools and methods that you can use when troubleshooting executed processes:

- **Log files**. All activities performed in Fabric are recorded in [log files](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md) in the server. In addition, the activities run on the Fabric debug server that were started by the Studio are recorded in the [Log screen](/articles/13_LUDB_viewer_and_studio_debug_capabilities/02_fabric_studio_log_files.md) in the Fabric Studio. The log messages display the failed [LU](/articles/03_logical_units/01_LU_overview.md) and [Table Population](/articles/07_table_population/01_table_population_overview.md) names in case of failure. 

- [**PS**](/articles/02_fabric_architecture/04_fabric_commands.md#ps-and-kill-commands) command – this is a Fabric command similar to [User Jobs](/articles/20_jobs_and_batch_services/01_fabric_jobs_overview.md), [Web Service](/articles/15_web_services_and_graphit/01_web_services_overview.md), [Graphit](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md) or  [Sync process](/articles/14_sync_LU_instance/01_sync_LUI_overview.md). The PS command displays tasks running on the Fabric cluster, and can be used to identify stuck processes and their running duration. 

- [**kill**](/articles/02_fabric_architecture/04_fabric_commands.md#ps-and-kill-commands) command - can be used to kill stuck processes.

- [**jjstack.sh** script](/articles/21_Fabric_troubleshooting/01_Fabric_troubleshooting_overview.md#how-do-i-run-jjstack) – this is a Fabric script that collects Java stack traces for a given process, stores the stacks and analyzes the results. The script can be applied to the Fabric server or the IID Finder. 

- A [**Heap Dump**](/articles/21_Fabric_troubleshooting/01_Fabric_troubleshooting_overview.md#how-is-a-heap-dump-file-created) file - this file can be used to monitor memory leaks or intensive memory consumption. A Heap Dump file file is created either automatically during a Fabric crash (when memory usage exceeds its defined maximum value), or manually on demand. Heap Dump files can be investigated to analyze the source of a memory leak.

- The [**k2profiler**](/articles/21_Fabric_troubleshooting/01_Fabric_troubleshooting_overview.md#how-does-k2profiler-work) - a Fabric command to create a CPU/Memory snapshot.

### How Do I Run jjstack?

The use cases for running **jjstack.sh** are as follows:

- An explicit request from K2View R&D to enable the investigation of a problem in Fabric.
- When you suspect that a job or a **Migrate** command is stuck.
- To investigate a performance issue in the implementation layer.

When running jjstack, first run the script and then analyze the output either on your own or with the help of K2View R&D staff.

The following table describes the syntax and parameters for calling the **jjstack.sh** script. The script is located under **$K2_HOME/fabric/scripts** in the Fabric server.


<table>
<tbody>
<tr>
<td width="170px">
<p><strong>jjstack.sh</strong></p>
</td>
<td width="730px">
<p><strong>Description</strong>: The script collects Java stack traces of a given process, stores stacks into the store directory and analyzes the results.</p>
<p><strong>Usage</strong>: ./jjstack.sh [pid] [sample count] [store directory] [cutoff limit]</p>
<p><strong>Options</strong>:</p>
<ul>
<li>[pid] &ndash; optional parameter. Java process ID to sample. By default, the script scans the Fabric server - see Example 1. When this parameter should be the default value and other parameters must be provided, use &ldquo;&rdquo; - see Example 2.</li>
<li>[sample count] - optional parameter. Number of samples and output files created. Default = 100</li>
<li>[store directory] - optional parameter. Location for storing output files that can be later analyzed. By default, the output directory is <strong>/tmp</strong>.</li>
<li>[cutoff limit] - optional parameter. Minimum number of appearances of the Java methods to show in the output file. No default value. </li>
</ul>
<p>Note that it is recommended to set both the sample count and the cutoff limit to proportional values. The recommended values are:</p>
<ul>
<li>Sample count = 100.</li>
<li>Cutoff limit = 30.</li>
</ul>
<p><strong>Examples:</strong></p>
<ul>
<li>Sample the Fabric server 100 times, create the files in /tmp. No cutoff limit:
<ul>
<li><strong>&nbsp;</strong>./jjstack.sh &nbsp;</li>
</ul>
</li>
<li>Sample&nbsp;the Fabric server 50 times, create the files in the <strong>js_iid1</strong> output directory. No cutoff limit:
<ul>
<li><strong>&nbsp;</strong>./jjstack.sh &ldquo;&rdquo; 50 js_iid1</li>
</ul>
</li>
<li>Sample the process 14323 100 times, create the files in the&nbsp;<strong>js_iid1</strong>&nbsp;output directory and output only entries with at least 30 appearances:
<ul>
<li>./jjstack.sh 14323 100 js_iid1 30</li>
</ul>
</li>
</ul>
</td>
</tr>
</tbody>
</table>


[Click to view the Example of the **jjstack** Output File.](/articles/21_Fabric_troubleshooting/images/jjstack.md)



### How Is a Heap Dump File Created?

A Heap Dump file is automatically created when Fabric crashes due to memory usage exceeding the definition. When required, a Heap Dump file can also be created manually using the **jmap** command. 

Default memory usage is defined in the **$K2_HOME/config/ jvm.options** configuration file and is equal to 2GBytes.  The location of a Heap Dump file is defined in the **jvm.options** configuration file. If the location of the Heap Dump is not defined explicitly in this file, its location is created in the folder where Fabric started, which is usually **$K2_HOME** or **$K2_HOME/fabric/scripts**. 

Note that Heap Dump files can take up a lot of disk space, therefore it is recommended to delete them after an investigation has been completed. Head Dump files should be uploaded to [K2view sftp dedicated servers](https://k2view.sharepoint.com/sites/Wiki/IT%20%20Technology/Heap%20dump%20upload%20point.aspx) in order to allow their analysis by the K2View R&D team.


The following table describes the syntax and parameters for creating the Heap Dump file using the **jmap** command. 

<table>
<tbody>
<tr>
<td width="170px">
<p><strong>jmap -dump:&lt;dump-options&gt;</strong></p>
</td>
<td width="730px">
<p><strong>Description</strong>: To connect to the running process and to dump Java heap in hprof binary format</p>
<p><strong>Usage</strong>: jmap -dump:[dump-options] [pid]</p>
<p><strong>Options</strong>:</p>
<ul>
<li>Dump-options are:
<ul>
<li>live - dump only live objects. If not specified, all objects in the heap are dumped.</li>
<li>format=b - binary format.</li>
<li>file=&lt;file&gt; &nbsp;- dump heap to file in the local directory (scan be absolute path).</li>
</ul>
</li>
<li>[pid] - Fabric process ID.</li>
</ul>
<p><strong>Example</strong>:</p>
<p>jmap -dump:live,format=b,file=heap.bin 20568;</p>
  
  Dumps only live objects in binary form to the file **heap.bin** relating to Fabric process number 20568
</td>
</tr>
</tbody>
</table>

### How Does k2profiler Work?

In Fabric 6.5.2 the **k2profiler** was introduced, which creates a CPU/Memory snapshot. 

The k2profiler works using two commands: first, the **k2profiler start** command (which starts the profiler), and then the **k2profiler snapshot** command, which takes a CPU or memory snapshot. Both of these commands must be used in the order indicated (first **k2profiler start**, then **k2profiler snapshot**). 

* **k2profiler start** [type=sampling|tracing|call_counting] [duration]

     This starts the profiler.
     
     **type** is one of the following:

  * **sampling** (default) - Estimates how long a method lasts by periodically probing stacks of running threads. Overhead is usually low and depends on the sampling period and the number of threads. In this mode, the method invocation counts are not available. Time accuracy is high for long methods and low for short periods.
  * **tracing** – In tracing mode, the profiler uses the bytecode of its instruments to measure time and the invocation count of each class method. Trivial methods like getters and setters are skipped to achieve better performance. Overhead is usually high and depends on the settings.
  * **call_counting** - Call counting is designed to have a minimal overhead. Call counting provides a plain list of methods with invocation counts. Unlike other modes, neither call stacks nor times are gathered. Trivial methods like getters and setters are skipped to achieve better performance.

 
   **duration** is the time in seconds for the profiler to gather information. Default duration is 60 seconds.

* **k2profiler snapshot** [stop=true|false] [type=cpu|memory]

     This command creates a CPU or memory snapshot. 
     Memory snapshot includes various checks on the CPU as well. 

     * **stop** - Instructs the profiler to stop once a snapshot is taken (this is the "true" value). Default is "true". If you wish continue taking snapshots, use the "stop=false" argument.
     * **type** - Determines if the snapshot is of CPU type or memory type. Default type is **cpu**.   

**Note**   
The profiler file will be created in the location defined in **jvm.options** file. 
In case of Fabric upgrade to a release greater or equal to 6.5.2, add the following section manually:

```bash
################
# Profiler     #
################
#
# Use option =help to introdce the available options
#-agentpath:$FABRIC_HOME/fabric/bin/profiler/yourkit/bin/linux-x86- 64/libyjpagent.so=delay=60000,dir=${FABRIC_HOME%/}/storage/snapshots,logdir=$FABRIC_HOME/logs,snapshot_name_format=profiler--{sessionname}--{datetime}
```

**To enable the k2profiler capability, you must uncomment this section and restart Fabric.**

If you are running the k2profiler on a Windows machine, add the following line to fabric-server-start.bat under JVM section:

    SET JVM_OPTIONS_DEBUG=!JVM_OPTIONS_DEBUG! -agentpath:!FABRIC_DIR!\bin\profiler\yourkit\bin\win64\yjpagent.dll=delay=60000,dir=!FABRIC_HOME!\storage\snapshots,logdir=!FABRIC_HOME!\logs,snapshot_name_format=profiler--{sessionname}--{datetime}

[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/21_Fabric_troubleshooting/02_Fabric_troubleshooting_log_files.md) 
