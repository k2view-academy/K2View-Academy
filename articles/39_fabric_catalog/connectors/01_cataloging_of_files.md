# Cataloging of Files

## Overview

Fabric Catalog is a tool designed to organize all data assets within a company's data landscape. It facilitates metadata discovery, classification, PII indication and calculation of various data quality metrics of all entities of a data source.

Sometimes the company's data assets are stored in files rather than in a data base, due to some business needs, and this data should be protected due to privacy laws. For example, files containing sensitive data arrive periodically to a predefined filesystem interface. Before they are utilized for the business purposes, it is essential to identify and mask any sensitive data these files contain.

Starting V8.3 Fabric enables building a Catalog based on files. Discovery can be performed using:

* Metadata definition, such as JSON schema or AVRO schema files.
* Sample files containing data.

The Crawler framework used for file cataloging employs a generic mechanism that is independent of specific file types. The Crawler expects to get an input in a predefined format. Since the files might have various structures (based on each project's business needs), the solution requires a creation of 3 Broadway flows that are invoked by the Crawler upon running the Discovery job.

These flows define mapping and transformation rules, converting the specific file format (their schema definition and/or the sample files) to the Catalog’s standard structure: data platform, schema(s), dataset(s), fields and their properties. 

Once the Catalog structure is built, the active plugins (such as parsing of complex structures, classification & PII indication) are executed like in case of discovery over any other data source.

Further  in this article, you can learn in more details about: 

* [Transformation rules definition](01_cataloging_of_files.md#transformation-rules-definition)
* [Rules attachment to interface](01_cataloging_of_files.md#rules-attachment-to-interface)

To illustrate the E2E process of file cataloging, the *Discovery of Files - Demo* extension is available on [K2exchange](/articles/04_fabric_studio/28_web_k2exchange.md). The extension can be installed in your project and it offers a comprehensive example of file discovery. The explanation about how to use the extension can be found in the extension's README file.

## Transformation Rules Definition

Due to multiple file formats, transformation rules are required in order to perform file's cataloging. The transformation rules are created using Broadway flows, that should be placed in a Project tree under the Shared Objects and deployed. 

Below is the description of each expected flow:

1. **Get Metadata** is the first flow that builds the Catalog's expected metadata, returning an array of maps. This flow is mandatory.

   * The metadata can be based on the schema definition file(s), if they are provided. In this case, each map is expected to represent a Catalog's field with its respective structure: data platform, schema, dataset, class, field name and all of its properties (defined in the schema definition file).
   * When there is no schema definition file and the metadata is expected to be discovered based on data sample, each map should represent a Catalog's dataset with its respective structure: data platform, schema, dataset, class. Then the fields and their properties will be completed from the example data.
   * The combined approach is also possible. Meaning, some datasets can be defined by schema definition files and some can be defined by sample data.


2. **Get Files List** is the second flow that returns a mapping between the dataset and the respective list of sample files. This flow is optional and only required when sample files are provided.

   * The flow should return a list of relevant data sample files per each dataset. Several sample files can be provided for the same dataset. However, one sample file cannot include data for more than one dataset. 

3. **Get Data Snapshot** is the third flow that returns a row of file's data. This flow is optional and only required when sample files are provided (when **Get Files List** is defined, it should be defined too).

   * Per each file, the flow should return a result set which represents one row. 

## Rules Attachment to Interface

Starting V8.3, filesystem interfaces include a group of input parameters called Discovery, which enable settings the names of Broadway flows to each of the rules:

<img src="../images/filesystem_discovery.png"  />