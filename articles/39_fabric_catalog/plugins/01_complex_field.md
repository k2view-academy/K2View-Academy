# Complex Field Parsing

## Overview

Starting V8.0, the Catalog supports parsing of complex fields using a **Complex Field Parser** plugin. Complex field is a text field of a dataset that includes a JSON or an XML structure. 

When the plugin identifies a complex structure embedded into a field, a node type **class** is created for each complex field level, recursively for all levels of the embedded complex structure. The parsing is performed based on the data snapshot taken from the source. When the same complex field includes different structures in a data snapshot, they will be combined under the same class.

A **definedBy** relation type is created to connect between a complex field and a respective class. 

## Classification

Once parsed, the complex field undergoes the auto-profiling using the same profiling rules as all other Catalog fields. 

The profiling is performed by the **Data Regex Classifier** and **Metadata Regex Classifier** plugins, and then the **Classification PII Marker** plugin creates the PII property where applicable. When at least one of the “inner fields” of the complex field is marked as PII, the complex field itself should be marked as PII.

[![Previous](/articles/images/Previous.png)](01_builtin_plugins.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">]() 
