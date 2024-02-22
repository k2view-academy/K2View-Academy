# Complex Field Parsing

Starting V8.0, the Catalog supports parsing of complex fields using a **Complex Field Parser** plugin. Complex field is a text field of a dataset that includes a complex structure, such as JSON or an XML.

The plugins uses the data snapshot taken from the source to parse the dataset's fields. When the plugin identifies a complex structure embedded into a field, a node type **class** is created for each complex field level, recursively for all levels of the embedded complex structure. If the same complex field includes different structures within the data snapshot, they will be combined under the same class.

A **definedBy** relation type is created to connect between a complex field and its respective class. 

![](../images/complex_field.png)

Once parsed, the complex field undergoes the auto-profiling using the same profiling rules as all other Catalog fields. 

The profiling is performed by the **Data Regex Classifier** and **Metadata Regex Classifier** plugins, and then the **Classification PII Marker** plugin creates the PII property where applicable. 

When at least one of the fields of the complex field is marked as PII, the complex field itself is marked as PII.

[<img align="right" width="60" height="54" src="/articles/images/Next.png">]() 
