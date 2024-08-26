# Complex Field Parsing

Fabric's Catalog supports parsing of text fields that include complex structures (JSON or XML). This is done by a **Complex Field Parser** plugin, which uses the data snapshot taken from the source. Starting from V8.1, parsing of BLOB, CLOB and VarBinary fields is also supported.

When the plugin identifies a complex structure embedded into a field, it applies the following parsing logic:

* If the same field includes different complex structures within the data snapshot, these structures are combined as the fields of the same class.
* If the same field includes complex structures (e.g., JSON) as well as regular strings, the parsing is not performed.

Once the field parsing is completed, a **Class** node is created for each complex field's level, recursively, for all levels of this embedded complex structure. 

A **definedBy** relation type is created to connect between a complex field and its respective Class node. This relation cannot be manually added or deleted in an Edit mode. 

![](../images/complex_field.png)

Once the complex field is parsed and the **Class** nodes are created, it undergoes the auto-profiling process using the same profiling rules as all other Catalog fields. 

The profiling is performed by the [Metadata Regex Classifier plugin](04a_builtin_plugins.md#metadata-regex-classifie) that create the Classification property for the fields of newly created classes. The [Classification PII Marker](04a_builtin_plugins.md#classification-pii-marker) then creates the PII property where applicable. When at least one of the fields of the complex field is marked as PII, the complex field itself is also marked as PII.
