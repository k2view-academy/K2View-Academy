# Creation of Reference Relation

The article describes plugins that create *refersTo* relations in the Catalog schema based on various types of analysis performed on the data source.

**The plugins are:**

* [Reference by Names Comparison](05_relations_creation.md#reference-by-names-comparison) — identifies possible foreign key references between datasets by matching field names and create the *refersTo* relations.
* [Reference by Query Analysis](05_relations_creation.md#reference-by-query-analysis) — identifies possible foreign key references between datasets by analyzing the JOIN statements in the provided SQL file and create the *refersTo* relations. This plugin is available starting from Fabric V8.3.
* [Reference by Data Comparison](05_relations_creation.md#reference-by-data-comparison) — identifies possible foreign key references between datasets by analyzing data within the field's columns and create the *refersTo* relations. This plugin is available starting from Fabric V8.3.

## Reference by Names Comparison

The purpose of a **Reference by Names Comparison** plugin (formerly known as *Metadata Logical Reference*) is to identify possible foreign key references between datasets based on matching the field names and to create the *refersTo* relations. This plugin is useful in a case where a source doesn't have predefined foreign key constraints. Note that this plugin is inactive by default and if needed, it should be set to active. 

The matching algorithm works by comparing the field names of 2 datasets at a time. Prior to the matching, the field names are normalized using the following formatting rules: underscore ‘_’ removal, conversion to lowercase letters and addition of a table name in case the field name is 'ID'. For example, the following field names - customer.ID, CUSTOMER_ID and CustomerID - will be normalized to the same value - customerid.

This plugin allows defining a exclusion list of field names (e.g., 'username' or 'age') and an exclusion list of field types (e.g., date, time, blob). The field names or type defined there are excluded from the matching algorithm. 

When the plugin finds a match by the field name, it evaluates the foreign key fields and direction by using the matching rules described below. The *refersTo* relation direction is *childDataset refersTo parentDataset*. The relation is created with a score of the matching rule which defines a probability of the match's correctness. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that the rule is applied only if its score is **above** the plugin's threshold. Otherwise the rule is skipped.

- ```fieldNameIsIdAndPk``` — dataset1 has a PK field **id** and dataset2 has a field **dataset1id** (normalized).

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.ID (PK) and* *activity.customer_id*

- ```fieldNameIsIdAndNotPk``` — dataset1 has a field called **id** and dataset2 has a field **dataset1id** (normalized), both are non-PK.

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.ID (non-PK) and* *activity.customer_id*

- ```singleFieldPkAndNotPk``` — dataset1 has a single PK field and dataset2 has a non-PK field with the same name (normalized).

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.customer_id (PK) and* *activity.customer_id* 

- ```*commonFieldsInBothPk``` — common fields that are part of the PK in both datasets, but dataset1 has less PKs than dataset2.

  - The relation *dataset2 refers to dataset1* is created.

  - Some examples of the matching rules are:

    <table style="width: 900px;"><tbody>
    <tr>
    <td style="width: 300px;" colspan="2"><strong>Input: Two Datasets</strong></td>
    <td style="width: 600px;">
    <p><strong>Output: Relation created by plugin</strong></p>
    </td>
    </tr>
    <tr>
    <td style="width: 150px;">
    <p><strong>DS1</strong></p>
    </td>
    <td style="width: 150px;">
    <p><strong>DS2</strong></p>
    </td>
    <td style="width: 600px;" colspan="2">
    <p><strong>Relation direction and FK</strong></p>
    </td>
    </tr>
    <tr>
    <td >
    <p>field_1 PK</p>
    </td>
    <td >
    <p>field_1 PK</p>
    <p>field_2&nbsp; PK</p>
    </td>
    <td >
    <p><em>DS2 refers to DS1</em></p>
    <p>FK: DS2 (field_1)</p>
    </td>
    </tr>
    <tr>
    <td >
    <p>field_1 PK</p>
    <p>field_2&nbsp; (not PK)</p>
    </td>
    <td >
    <p>field_1 PK</p>
    <p>field_2&nbsp; PK</p>
    </td>
    <td >
    <p><em>DS2 refers to DS1</em></p>
    <p>FK: DS2 (field_1)</p>
    </td>
    </tr>
    </tbody>
    </table>

- ```sameFieldNamesPk``` — common fields that are part of the PK in both datasets, and both datasets have an identical number of PKs.

  - The relation is created and its direction is random. 


Note that ```sameFieldNamesNotPk``` rule to create relations between non-FK fields has been removed in V8.3

#### Field Type Include List

The purpose of the ```fieldTypeIncludeList``` plugin's input parameter is to allow controlling which field's data types should be considered for creating the relations. 

By default, it is set to STRING, INTEGER, REAL for this plugin. The valid values are: STRING, INTEGER, REAL, DATETIME, DATE, BOOLEAN.

## Reference by Query Analysis

The purpose of a **Reference by Query Analysis** plugin (introduced in V8.3) is to identify possible foreign key references between datasets by analyzing the JOIN operations in the queries of the input SQL file. Whenever a JOIN is found, the datasets of this JOIN become candidates for creating the *refersTo* relations. 

The plugin evaluates then the candidate datasets using the matching rules described below. When one of the rules match, the *refersTo* relation is created and the direction is *childDataset refersTo parentDataset*. The relation is created with a score of the matching rule. 

Some database management systems (such as Oracle) support automatic generation of **audit files**. Those are files that record activities within the database, by tracking executed SQL queries, user logins, schema changes, privilege escalations, and other events. Audit file can be used to create an input SQL file for the plugin analysis. File transformation is required, to remove all information other than the SQL queries. This transformation can be performed by creating a Broadway flow in your project that will transform file to the required format. 

This plugin is useful when a source doesn't have predefined foreign key constraints. Note that this plugin is inactive by default. If needed, the plugin should be set to active. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that the rule is applied only if its score is **above** the plugin's threshold. Otherwise the rule is skipped.

* ```singleFieldPkAndNotPk``` — dataset1 has a single PK field and dataset2 has a non-PK field, while both of these fields are part of the same JOIN statement.
  * The relation *dataset2 refers to dataset1* is created.
* ```commonFieldsInBothPk``` — common fields that are part of the PK in both datasets, but dataset1 has less PKs than dataset2.
  - The relation *dataset2 refers to dataset1* is created.
* ```sameFieldNamesPk``` — common fields that are part of the PK in both datasets, and both datasets have an identical number of PKs.
  - The relation is created and its direction is random. 

#### Field Type Include List

The purpose of the ```fieldTypeIncludeList``` plugin's input parameter is to allow controlling which field's data types should be considered for creating the relations. 

By default, it is set to STRING, INTEGER, REAL for this plugin. The valid values are: STRING, INTEGER, REAL, DATETIME, DATE, BOOLEAN.

#### Queries Path

The ```queriesPath``` plugin's input parameter must be set with the path to the file with a list of queries to be analyzed. 

The file name format is: ```<Data Platform name>.sql```.

When the ```queriesPath``` is not set or includes an invalid path, the plugin will thrown an exception.  

#### Analysis Mode

Defines whether the queries are analyzed by Fabric's internal parser only, by LLM only, or first attempt to analyze by Fabric and in case of low results, analyze them by LLM. By default, ```analysisMode``` is set to ANTLR (Fabric's internal parser).

#### LLM Interface

The ```llmInterface``` is an optional parameter. It allows overriding the default project's LLM AI interface, to be used by the LLM plugin. This parameter should include the interface's name. 

- When the ```llmInterface``` parameter is not set in the plugin definition, the plugin will search for an LLM AI interface tagged as 'discovery'. If non of the LLM AI interfaces are tagged as 'discovery', an interface with a 'default' tag will be used.
- Setting the ```llmInterface``` parameter is only applicable when the plugin invokes LLM (it happens if the ```analysisMode``` is set to either LLM or to ANTLR & LLM).

## Reference by Data Comparison

The purpose of a **Reference by Data Comparison** plugin (introduced in V8.3) is to identify possible foreign key references between datasets by comparison of the data within the field's columns and to create the *refersTo* relations. This plugin is useful when a source doesn't have predefined foreign key constraints. 

The data comparison is performed by comparing the values of the fields of two datasets at a time - dataset1 and dataset2. All fields of dataset2 are considered for analysis, while only the PK fields of dataset1are considered.  

Note that this plugin is inactive by default. If needed, the plugin should be set to active. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that the rule is applied only if its score is **above** the plugin's threshold. Otherwise the rule is skipped.

- ```singleFieldPkAndNotPk``` — dataset1 has a single PK field and dataset2 has a non-PK field, while both of these fields are part of the same JOIN statement.
  - The relation *dataset2 refers to dataset1* is created.
- ```commonFieldsInBothPk``` - common fields that are part of the PK in both datasets, but dataset1 has less PKs than dataset2.
  - The relation *dataset2 refers to dataset1* is created.
- ```sameFieldNamesPk``` — common fields that are part of the PK in both datasets, and both datasets have an identical number of PKs.
  - The relation is created and its direction is random. 

#### Field Type Include List

The purpose of the ```fieldTypeIncludeList``` plugin's input parameter is to allow controlling which field's data types should be considered for creating the relations. 

By default, it is set to STRING, INTEGER, REAL for this plugin. The valid values are: STRING, INTEGER, REAL, DATETIME, DATE, BOOLEAN.

#### Factor

Defines the score’s multiplication factor for the data types other than GUID. By default, the factor is set to 0.85 in order to reduce the probability of a possible reference for the matches of the column, which have data types other than GUID.
