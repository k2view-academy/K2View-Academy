# Relations Creation

### Overview

The following article describes plugins that create relations in the Catalog schema, based on the data source analysis.

* [Metadata Logical Reference](05_relations_creation.md#metadata-logical-reference) - identify possible foreign key references between datasets by matching field names and create the *refersTo* relations.

### Metadata Logical Reference

The purpose of a **Metadata Logical Reference** plugin is to identify possible foreign key references between datasets and to create the *refersTo* relations. This plugin is useful in a case where a source doesn't have predefined foreign key constraints. Note that this plugin is inactive by default and if needed, it should be set to active. 

The matching algorithm works by comparing the field names of 2 datasets at a time. Prior to the matching, the field names are normalized using the following formatting rules: underscore ‘_’ removal, conversion to lowercase letters and addition of a table name in case the field name is 'ID'. For example, the following field names - customer.ID, CUSTOMER_ID and CustomerID - will be normalized to the same value - customerid.

This plugin allows defining a exclusion list of field names (e.g., 'username' or 'age') and an exclusion list of field types (e.g., date, time, blob). The field names or type defined there are excluded from the matching algorithm. 

When the plugin finds a match by the field name, it evaluates the foreign key fields and direction by using the matching rules described below. The *refersTo* relation direction is Many-to-One. The relation is created with a score - a probability of the match's correctness. 

#### Matching Rules

The following matching rules are applied by the plugin. Note that the rule is applied only if its score is **above** the plugin's threshold. Otherwise the rule is skipped.

- **fieldNameIsIdAndPk** - dataset1 has a PK field **id** and dataset2 has a field **dataset1id** (normalized).

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.ID (PK) and* *activity.customer_id*

- **fieldNameIsIdAndNotPk** - dataset1 has a field called **id** and dataset2 has a field **dataset1id** (normalized), both are non-PK.

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.ID (non-PK) and* *activity.customer_id*

- **singleFieldPkAndNotPk** - dataset1 has a single PK field and dataset2 has a non-PK field with the same name (normalized).

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.customer_id (PK) and* *activity.customer_id* 

- **commonFieldsInBothPk** - common fields that are part of the PK in both datasets, but dataset1 has less PKs than dataset2.

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

- **sameFieldNamesPk** - common fields that are part of the PK in both datasets, and both datasets have an identical number of PKs.

  - The relation is created and its direction is random. 

- **sameFieldNamesNotPk** - both datasets have fields with the same names (normalized), both are non-PK.

  - The relation is created and its direction is random. 

#### Field Exclusion List

Fields can be excluded from the **Metadata Logical Reference** plugin's matching algorithm by either their name or type. The exclusion list can be defined using the **fieldNameExcludeList** and **fieldTypeExcludeList** arrays in the plugin's input parameters definition. 

This can be useful when, for example, the same field name exists in many datasets of the same schema and this field should not be part of the *refersTo* relation, e.g., lastModifiedDate.