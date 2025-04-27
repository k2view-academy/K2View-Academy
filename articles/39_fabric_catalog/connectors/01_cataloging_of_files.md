# Cataloging of Files

## Overview

Fabric Catalog is a tool designed to organize all data assets within a company's data landscape. It facilitates metadata discovery, classification, the indication of PII, and the calculation of various data quality metrics for each source.

Sometimes the company's data assets are stored in files rather than in a data base, due to some business needs. For example, files containing sensitive data arrive periodically to a predefined filesystem interface. Before these files are utilized for various business purposes, it is essential to identify and mask any sensitive data they contain.

Starting V8.3 Fabric enables building a Catalog based on files. Discovery can be performed using:

* Schema definition, such as JSON schema or AVRO schema files.
* Sample files containing data.

The Crawler framework used for file cataloging employs a generic mechanism that is independent of specific file types. It expects input in a predefined format, achieved through the invocation of three Broadway flows. These flows serve to provide mapping and transformation rules, converting the specific file format (their schema definition and/or the sample files) to the Catalog’s standard architecture: data platform, schema(s), dataset(s), etc. 

Further  in this article, you can learn in more details about: 

* [Transformation rules definition](01_cataloging_of_files.md#transformation-rules-definition)
* [Rules integration with interface](01_cataloging_of_files.md#rules-integration-with-interface)

To illustrate the E2E process of file cataloging, the *Discovery of Files - Demo* extension is available on [K2exchange](/articles/04_fabric_studio/28_web_k2exchange.md). It can be installed in your project and offers a simple comprehensive example of file discovery. The explanation about how to use the extension can be found in the extension's README file.

## Transformation Rules Definition

Transformation rules are required in order to perform file's cataloging. The transformation rules should be created using Broadway flows under the Shared Objects and deployed. 

The following flows are expected:

1. The first and mandatory flow builds the Catalog's expected metadata, returning an array of maps. 

   * The metadata can be based on the schema definition file(s), if they are provided. In this case, each map is expected to represent a Catalog's field with its respective structure: data platform, schema, dataset, class, field name and all of its properties (defined in the schema definition file).


   * When there is no schema definition file and the metadata is expected to be discovered based on data sample, each map should represent a Catalog's dataset with its respective structure: data platform, schema, dataset, class. Then the field and properties will be completed from the example data.
   * The combined approach is possible. Meaning, some datasets in the same interface can be defined by schema files and some can be defined by sample data.

2. The second flow returns a mapping between the dataset and the respective list of files. This flow is optional and only required when sample files are provided.

   * Per each dataset, the flow should return a list of relevant data sample files. Several samples files can be provided for the same dataset. However, one sample file cannot define more than one dataset. 

3. The third flow returns a line of file's data. This flow is optional and only required when sample files are provided.

   * Per each file, the flow should return a result set which represents one row. 

## Rules Integration with Interface

Starting V8.3 filesystem interfaces include a set of input parameters called Discovery, which enables settings the names of Broadway flows to each of the rules:

<img src="../images/filesystem_discovery.png"  />