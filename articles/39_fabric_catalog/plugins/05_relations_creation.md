# Creation of Reference Relation

The article describes plugins that create *refersTo* relations in the Catalog schema based on various types of analysis performed on the data source.

**The plugins are:**

* [Reference by Name Comparison](05_relations_creation.md#reference-by-name-comparison) — identifies possible foreign key references between datasets by matching field names, and then creates corresponding *refersTo* relations.
* [Reference by Query Analysis](05_relations_creation.md#reference-by-query-analysis) — identifies possible foreign key references between datasets by analyzing JOIN statements in the provided SQL file, and then creates corresponding *refersTo* relations. This plugin is available starting from Fabric V8.3.
* [Reference by Data Comparison](05_relations_creation.md#reference-by-data-comparison) — identifies possible foreign key references between datasets by analyzing data in fields' columns, and then creates the *refersTo* relations. This plugin is available starting from Fabric V8.3.

## Reference by Name Comparison

The purpose of the **Reference by Name Comparison** plugin (formerly known as *Metadata Logical Reference*) is to identify possible foreign key references between datasets by matching field names and to create *refersTo* relations. This plugin is useful in cases where a source does not have predefined foreign key constraints. Note that this plugin is inactive by default and must be manually activated if needed. 

The matching algorithm operates by comparing the field names of two datasets at a time. Prior to the matching, the field names are normalized using the following formatting rules: underscore ‘_’ removal, conversion to lowercase letters and the addition of the table name in case the field name is 'ID'. For example, the field names customer.ID, CUSTOMER_ID and CustomerID will be normalized to the same value — customerid.

This plugin allows defining an exclusion list of field names (e.g., 'username' or 'age') and an exclusion list of field types (e.g., date, time, blob). The defined field names and types are excluded from the matching algorithm. 

When the plugin finds a match based on field names, it evaluates the foreign key fields and the direction of the relation using the matching rules described below. The *refersTo* relation direction is *childDataset refersTo parentDataset*. The relation is created with a score of the matching rule. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that each of these rules is applied only if its score **exceeds** the plugin's threshold; otherwise, the rule is skipped.

- ```fieldNameIsIdAndPk``` — dataset1 has a PK field **id** and dataset2 has a field **dataset1id** (normalized).

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.ID (PK) and* *activity.customer_id*

- ```fieldNameIsIdAndNotPk``` — dataset1 has a field called **id** and dataset2 has a field **dataset1id** (normalized), both are non-PK.

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.ID (non-PK) and* *activity.customer_id*

- ```singleFieldPkAndNotPk``` — dataset1 has a single PK field and dataset2 has a non-PK field with the same name (normalized).

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.customer_id (PK) and* *activity.customer_id* 

- ```*commonFieldsInBothPk``` — common fields that are part of the PK in both datasets, where dataset2 has more PKs than dataset1.

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

- ```sameFieldNamesPk``` — common fields that are part of the PK in both datasets, and both datasets have the same number of PKs.

  - The relation is created and its direction is random. 


Note that the ```sameFieldNamesNotPk``` rule to create relations between non-FK fields has been removed as of Fabric V8.3

#### Field Type Include List

The ```fieldTypeIncludeList``` plugins input parameter controls which field data types are considered when creating relations. 

By default, this parameter is set to the STRING, INTEGER or REAL data type for this plugin. The valid values are STRING, INTEGER, REAL, DATETIME, DATE and BOOLEAN.

## Reference by Query Analysis

The purpose of the **Reference by Query Analysis** plugin (introduced in Fabric V8.3) is to identify possible foreign key references between datasets by analyzing JOIN operations in the queries of the input SQL file. Whenever a JOIN is found, its datasets are considered candidates for creating the *refersTo* relations. 

The plugin then evaluates the candidate datasets using the matching rules described below. When one of the rules matches, a *refersTo* relation is created, with the direction being *childDataset refersTo parentDataset*. The relation is created with a score of that matching rule. 

Some database management systems (such as Oracle) support automatic generation of **audit files**. These files record activities within the database by tracking executed SQL queries, user logins, schema changes, privilege escalations, and other events. An audit file can be used for creating an input SQL file for the plugin analysis. However, the audit file must first be transformed to remove all information except the SQL queries. This transformation can be done by creating a Broadway flow in your project, which converts the file to the required format. 

This plugin is useful when a source does not have predefined foreign key constraints. Note that this plugin is inactive by default and must be manually activated if needed. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that the rule is applied only if its score **exceeds** the plugin's threshold; otherwise, the rule is skipped.

* ```singleFieldPkAndNotPk``` — dataset1 has a single PK field and dataset2 has a non-PK field, while both of these fields are part of the same JOIN statement.
  * The relation *dataset2 refers to dataset1* is created.
* ```commonFieldsInBothPk``` — common fields that are part of the PK in both datasets, where dataset2 has more PKs than dataset1.
  - The relation *dataset2 refers to dataset1* is created.
* ```sameFieldNamesPk``` — common fields that are part of the PK in both datasets, and both datasets have the same number of PKs.
  - The relation is created and its direction is random. 

#### Field Type Include List

The ```fieldTypeIncludeList``` plugin input parameter controls which field data types are considered when creating relations. 

By default, this parameter is set to the STRING, INTEGER or REAL data type for this plugin. The valid values are STRING, INTEGER, REAL, DATETIME, DATE and BOOLEAN.

#### Queries Path

The ```queriesPath``` plugin input parameter must be set to the path of the file containing the list of queries to be analyzed. 

The file name format is: ```<Data Platform name>.sql```.

If the ```queriesPath``` is not set or contains an invalid path, the plugin throws an exception.  

#### Analysis Mode

This plugin input parameter controls how queries are analyzed: using Fabric's internal parser only, by an LLM only, or first by Fabric with fallback to the LLM if needed (when results are insufficient). By default, ```analysisMode``` is set to ANTLR (Fabric's internal parser).

#### LLM Interface

The ```llmInterface``` parameter is optional. It allows overriding the project's default LLM interface to be used by the LLM plugin. This parameter should include the interface name. 

- If the ```llmInterface``` parameter is not set in the plugin definition, the plugin searches for an LLM AI interface tagged as 'discovery'. If none of the LLM AI interfaces are tagged as 'discovery', it will use an interface tagged as 'default'.
- Setting the ```llmInterface``` parameter is only applicable when the plugin invokes an LLM which occurs if the ```analysisMode``` parameter is set to either LLM or ANTLR & LLM.

## Reference by Data Comparison

**Reference by Data Comparison** is a new plugin (introduced in Fabric V8.3) that examines data within data source fields to identify correlations using the probabilistic Bloom filter algorithm. Based on the analysis results, this plugin can establish FK relationships between datasets. 

The data comparison is performed by comparing the values of the fields of two datasets at a time — dataset1 and dataset2. All fields in dataset2 are considered for analysis, while in dataset1 only the PK fields are used for this comparison.  

Note that this plugin is inactive by default and must be manually activated if needed. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that the rule is applied only if its score **exceeds** the plugin's threshold; otherwise, the rule is skipped.

- ```singleFieldPkAndNotPk``` — dataset1 has a single PK field and dataset2 has a non-PK field, while both of these fields are part of the same JOIN statement.
  - The relation *dataset2 refers to dataset1* is created.
- ```commonFieldsInBothPk``` — common fields that are part of the PK in both datasets, where dataset2 has more PKs than dataset1.
  - The relation *dataset2 refers to dataset1* is created.
- ```sameFieldNamesPk``` — common fields that are part of the PK in both datasets, and both datasets have the same number of PKs.
  - The relation is created and its direction is random. 

#### Field Type Include List

The ```fieldTypeIncludeList``` plugin input parameter controls which field data types are considered when creating relations. 

By default, this parameter is set to the STRING, INTEGER or REAL data type for this plugin. The valid values are STRING, INTEGER, REAL, DATETIME, DATE and BOOLEAN.

#### Factor

The factor refers to the score multiplication factor, applied only for calculating the score when comparing data types other than GUID. By default, the factor is set to 0.85 as it aims to reduce the probability of marking non-GUID columns as possible references as a potential match.
