# Creation of 'Refers To' Relation

The article describes plugins that create *refersTo* relations in the Catalog schema based on various types of analysis performed on the data source.

**The plugins are:**

* [Reference by Name Comparison](05_relations_creation.md#reference-by-name-comparison) - identifies possible foreign key references between datasets by matching field names, and then creates corresponding *refersTo* relations.
* [Reference by Query Analysis](05_relations_creation.md#reference-by-query-analysis) - identifies possible foreign key references between datasets by analyzing JOIN statements in the provided SQL file, and then creates corresponding *refersTo* relations. This plugin is available starting from Fabric V8.3.
* [Reference by Data Comparison](05_relations_creation.md#reference-by-data-comparison) - identifies possible foreign key references between datasets by analyzing data in fields' columns, and then creates the *refersTo* relations. This plugin is available starting from Fabric V8.3.
* [Trim Extra Relations](05_relations_creation.md#trim-extra-relations) - identifies and removes redundant *refersTo* relationships between schema datasets to maintain essential connections. This plugin is available starting from Fabric V8.4.1.

## Reference by Name Comparison

The purpose of the **Reference by Name Comparison** plugin (formerly known as *Metadata Logical Reference*) is to identify possible foreign key references between datasets by matching field names and to create *refersTo* relations. This plugin is useful in cases where a source does not have predefined foreign key constraints. Note that this plugin is inactive by default and must be activated via Discovery Pipeline if needed.

The matching algorithm operates by comparing the field names of two datasets at a time. Prior to the matching, the field names are normalized using the following formatting rules: underscore ‘_’ removal, conversion to lowercase letters and the addition of the table name in case the field name is 'ID'. For example, the field names customer.ID, CUSTOMER_ID and CustomerID will be normalized to the same value — customerid.

This plugin allows defining an exclusion list of field names (e.g., 'username' or 'age') and an exclusion list of field types (e.g., date, time, blob). The defined field names and types are excluded from the matching algorithm. 

When the plugin finds a match based on field names, it evaluates the foreign key fields and the direction of the relation using the matching rules described below. The *refersTo* relation direction is *childDataset refersTo parentDataset*. The relation is created with a score of the matching rule. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that each of these rules is applied only if its score **exceeds** the plugin's threshold; otherwise, the rule is skipped.

- ```fieldNameIsIdAndPk``` - Parent Dataset ('DS1') has a PK field 'ID'; Child Dataset has a field 'DS1ID' (normalized).

  - Example: *customer.ID (PK) and activity.customer_id*
  - Relation: *ACTIVITY refers to CUSTOMER (ID)*

- ```fieldNameIsIdAndNotPk``` - Parent Dataset ('DS1') has a PK field 'ID'; Child Dataset has a field 'DS1ID' (normalized); both are non-PK.

  - Example: *customer.ID (non-PK) and* *activity.customer_id*
  - Relation: *ACTIVITY refers to CUSTOMER (ID)*

- ```singleFieldPkAndNotPk``` - Single PK field in a Parent Dataset and non-PK field in Child Dataset.

  - Example: *customer.customer_id (PK) and* *activity.customer_id* 
  - Relation: *ACTIVITY refers to CUSTOMER (customer_id)*

- ```commonFieldsInBothPk``` - Common fields in PK of both datasets, but Child Dataset has additional PKs.

  - The relation *Child refers to Parent* is created.

  - Some examples of the matching rules are:

    <table style="width: 900px;">
    <tbody>
    <tr>
    <td style="width: 300px;" colspan="2"><strong>Input: Two Datasets</strong></td>
    <td style="width: 600px;">
    <p><strong>Output: Relation created by plugin</strong></p>
    </td>
    </tr>
    <tr>
    <td style="width: 150px;">
    <p><strong>Parent_DS</strong></p>
    </td>
    <td style="width: 150px;">
    <p><strong>Child_DS</strong></p>
    </td>
    <td style="width: 600px;" colspan="2">
    <p><strong>Relation direction and FK</strong></p>
    </td>
    </tr>
    <tr>
    <td>
    <p>field_1 PK</p>
    </td>
    <td>
    <p>field_1 PK</p>
    <p>field_2PK</p>
    </td>
    <td>
    <p><em>Child_DS refers to Parent_DS</em></p>
    <p>FK: Child_DS (field_1)</p>
    </td>
    </tr>
    <tr>
    <td>
    <p>field_1 PK</p>
    <p>field_2PK</p>
    </td>
    <td>
    <p>field_1 PK</p>
    <p>field_2PK</p>
    <p>field_3PK</p>
    </td>
    <td>
    <p><em>Child_DS refers to Parent_DS</em></p>
    <p>FK: Child_DS (field_1, field_2)</p>
    </td>
    </tr>
    </tbody>
    </table>

- ```sameFieldsInBothPk``` - Part of PK in both datasets, and both datasets have an identical number of PKs.

  - The relation is created and its direction is random. 


Note that the ```sameFieldNamesNotPk``` rule to create relations between non-FK fields has been removed as of Fabric V8.3

#### Field Type Include List

The ```fieldTypeIncludeList``` plugins input parameter controls which field data types are considered when creating relations. 

By default, this parameter is set to the STRING, INTEGER or REAL data type for this plugin. The valid values are STRING, INTEGER, REAL, DATETIME, DATE and BOOLEAN.

## Reference by Query Analysis

The purpose of the **Reference by Query Analysis** plugin (introduced in Fabric V8.3) is to identify possible foreign key references between datasets by analyzing JOIN operations in the queries of the input SQL file. Whenever a JOIN is found, its datasets are considered candidates for creating the *refersTo* relations. 

The plugin then evaluates the candidate datasets using the matching rules described below. When one of the rules matches, a *refersTo* relation is created, with the direction being *childDataset refersTo parentDataset*. The relation is created with a score of that matching rule. 

Some database management systems (such as Oracle) support automatic generation of **audit files**. These files record activities within the database by tracking executed SQL queries, user logins, schema changes, privilege escalations, and other events. An audit file can be used for creating an input SQL file for the plugin analysis. However, the audit file must first be transformed to remove all information except the SQL queries. This transformation can be done by creating a Broadway flow in your project, which converts the file to the required format. 

This plugin is useful when a source does not have predefined foreign key constraints. Note that this plugin is inactive by default and must be activated via Discovery Pipeline if needed. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that the rule is applied only if its score **exceeds** the plugin's threshold; otherwise, the rule is skipped.

* ```singleFieldPkAndNotPk``` - Single PK field in Parent Dataset and non-PK field in Child Dataset.
  * The relation *Child refers to Parent* is created.
* `joinOnlyNoPkCheck` - PKs are not checked. FK is based on JOIN only: left side of condition is the Parent Dataset, right side is the Child Dataset.:
  * The old syntax is also supported, having WHERE and '=' instead of the JOIN.
  * in case of the LEFT JOIN or LEFT OUTER JOIN the relation direction is **opposite**: the left side should be considered a Child Dataset, the right side → a Parent Dataset.
  * the `joinOnlyNoPkCheck` matching rule is only available in the ANTLR mode.
* ```commonFieldsInBothPk``` - Common fields in PK of both datasets, but Child Dataset has additional PKs.
  * The relation *Child refers to Parent* is created.
* ```sameFieldsInBothPk``` - Part of PK in both datasets, and both datasets have an identical number of PKs.
  - The relation is created and its direction is random. 

#### Field Type Include List

The ```fieldTypeIncludeList``` plugin input parameter controls which field data types are considered when creating relations. 

By default, this parameter is set to the STRING, INTEGER or REAL data type for this plugin. The valid values are STRING, INTEGER, REAL, DATETIME, DATE and BOOLEAN.

#### Queries Path

The ```queriesPath``` plugin input parameter must be set to the path to a folder containing the files with a list of queries to be analyzed. 

The file name format is: ```<Data Platform name>.sql```.

If the ```queriesPath``` is not set or contains an invalid path, the plugin throws an exception.  

#### Analysis Mode

This plugin input parameter controls how queries are analyzed: using Fabric's internal parser only, by an LLM only, or first by Fabric with fallback to the LLM if needed (when results are insufficient). By default, ```analysisMode``` is set to ANTLR (Fabric's internal parser).

Note that the `joinOnlyNoPkCheck` matching rule is only available in the ANTLR mode.

#### LLM Interface

The ```llmInterface``` parameter is optional. It allows overriding the project's default LLM interface to be used by the LLM plugin. This parameter should include the interface name. 

- If the ```llmInterface``` parameter is not set in the plugin definition, the plugin searches for an LLM AI interface tagged as 'discovery'. If none of the LLM AI interfaces are tagged as 'discovery', it will use an interface tagged as 'default'.
- Setting the ```llmInterface``` parameter is only applicable when the plugin invokes an LLM which occurs if the ```analysisMode``` parameter is set to either LLM or ANTLR & LLM.

## Reference by Data Comparison

**Reference by Data Comparison** is a new plugin (introduced in Fabric V8.3) that examines data within data source fields to identify correlations using the probabilistic Bloom filter algorithm. Based on the analysis results, this plugin can establish FK relationships between datasets. 

The data comparison is performed by comparing field values of two datasets at a time - dataset1 and dataset2. All fields in dataset2 are considered for analysis, while only the PK fields in dataset1 are used for this comparison. The data comparison results with a calculated score that represents the probability of a match between each pair of columns from two datasets.   

Note that this plugin is inactive by default and must be activated via Discovery Pipeline if needed. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that the rule is applied only if its score **exceeds** the plugin's threshold; otherwise, the rule is skipped.

- ```singleFieldPkAndNotPk``` - Single PK field in Parent Dataset and non-PK field in Child Dataset.
  - The relation *Child refers to Parent* is created.
- ```commonFieldsInBothPk``` - Common fields in PK of both datasets, but Child Dataset has additional PKs.
  - The relation *Child refers to Parent* is created.
- ```sameFieldsInBothPk``` - Part of PK in both datasets, and both datasets have an identical number of PKs.
  - The relation is created and its direction is random. 

#### Field Type Include List

The ```fieldTypeIncludeList``` plugin input parameter controls which field data types are considered when creating relations. 

By default, this parameter is set to the STRING, INTEGER or REAL data type for this plugin. The valid values are STRING, INTEGER, REAL, DATETIME, DATE and BOOLEAN.

#### Factor

The factor refers to score multiplication, applied only when comparing columns defined by non-GUID data types. By default, the factor is set to 0.85 as it aims to reduce the score of potential matches between non-GUID columns.

## Trim Extra Relations

The **Trim Extra Relations** plugin identifies and removes redundant *refersTo* relationships between the schema datasets to maintain only the essential connections. The plugin exclusively inspects *refersTo* relations created by Catalog plugins, while keeping the Crawler and Manual relations untouched. This optimization simplifies the data model and improves overall clarity.

**The Problem: Redundant Relationships**

Consider a scenario with three tables - **Customer**, **Order**, and **Product** - and the following links:

~~~
Customer ──────────────────────────────────> Product
    │                                            ↑
    │                                            │
    └──────> Order ──────────────────────────────┘
~~~

- **Customer** → **Order** (Customer places orders)
- **Order** → **Product** (Order contains products)
- **Customer** → **Product** (Direct link - **REDUNDANT**)

The **Customer → Product** relationship is unnecessary because the path to **Product** is already established through **Orders**: 

* **Customer → Order → Product**. 

This redundant direct connection clutters the data model without adding analytical value.

**The Solution: Streamlined Modeling**

The plugin **removes these unnecessary shortcuts** while preserving the meaningful underlying relationships:

~~~
Customer ────────> Order ────────> Product
~~~

The resulting model is cleaner and accurately reflects business logic:

- Customer place Order.
- Order contain Product.

#### How Trim Plugin Works

The plugin maps all existing relationships between datasets, similar to a transit map showing all possible routes. For every relationship, the plugin evaluates a core question: *"Is there an alternative route from Dataset1 to Dataset2?"*

If an alternative path exists, the direct relationship is deemed redundant and is removed, keeping the data model focused.

The plugin skips the following relationships from deletion to prevent the loss of critical metadata:

- **Physical Foreign Keys:** Relationships marked with `Origin = Crawler` are actual database constraints and are never removed.
- **Manual Relationships:** Relationships marked with `Origin = Manual` are explicitly defined by users and are preserved regardless of alternative paths.

The Trim Extra Relations plugin is most effective when running discovery on data sources that lack physical foreign keys. In these cases, logical foreign keys are generated by Discovery plugins; the Trim plugin then simplifies the resulting model by removing redundant connections.

