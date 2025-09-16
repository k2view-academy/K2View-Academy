# Customizing the jvm.options File (Technical Note)

## Abstract

The *Customizing the jvm.options File* technical note explains how users of K2view’s Fabric and associated services can modify Java Virtual Machine (JVM) runtime settings. It outlines which options are configurable via environment variables (e.g., minimum and maximum heap size; keystore/truststore paths and passwords) and which settings require direct editing of the `jvm.options` file. The note provides guidance on how to safely update `jvm.options` (and related files such as `logback.xml`), including appending vs overriding options, avoiding configuration conflicts (especially for heap settings), and best practices for consistent configuration across nodes. Examples using `sed`, `echo`, `tee`, and grep are included for making changes to pods or containers, emphasizing careful management to avoid duplication and maintain system stability.

## Link

<ul>
  <li><a href="/articles/44_administration_and_operations/Space_and_Configuration_Management/Technical_Note_Customize_the_jvm_options_File.pdf">Technical Note - Customize the jvm.options File</a>
  </li>
</ul>

## Keywords

jvm.options, JVM tuning, MIN\_HEAP, MAX\_HEAP, keystore, truststore, environment variables, logback.xml, Fabric runtime, configuration management, pod/container editing, sed commands, echo tee, grep, appending vs overriding, heap size conflict, consistent settings, node-level updates, best practices, runtime stability

