# Metadata Logical Reference

### Overview

The purpose of a **Metadata Logical Reference** plugin is to identify possible foreign key references between datasets and to create *refers to* relations. This plugin is useful in a case where a source doesn't have predefined foreign key constraints. Note that this plugin is inactive by default and if needed, it should be set to active via the plugins.discovery file. 

The matching algorithm works by comparing the field names of 2 different datasets at a time. Prior to the matching, the field names are normalized using the following formatting rules: underscore ‘_’ removal, conversion to lowercase letters and addition of a table name in case the field name is an ID.

For example, the following field names will be matched: customer.ID, CUSTOMER_ID and CustomerID.

This plugin includes a exclusion list of field names (e.g., 'username' or 'age') and an exclusion list of field types (e.g., date, time, blob) to be excluded from the matching algorithm. These exclusion lists are defined in the plugins.discovery configuration file as plugin input parameters, and they can be updated on a project level.

If a match is found, the plugin evaluates both the relation direction and the foreign key fields by using the matching rule. The *refers to* relation direction is Many-to-One. The relation is created with a score - a probability of the match's correctness. 

### Matching Rules

The following matching rules are defined in the plugins.discovery file and can be applied by the plugin. Note that the rule is actually applied only if its score is **above** the plugin's threshold. Otherwise the rule is skipped.

- **field_name_is_id_and_pk** - dataset1 has a PK field **id** and dataset2 has a field **dataset1id** (normalized).

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.ID (PK) and* *activity.customer_id*

- **field_name_is_id_and_not_pk** - dataset1 has a non-PK field **id** and dataset2 has a field **dataset1id** (normalized).

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.ID (non-PK) and* *activity.customer_id*

- **single_field_pk_and_not_pk** - dataset1 has a PK field and dataset2 has a field with the same name (normalized), non-PK.

  - The relation *dataset2 refers to dataset1* is created.
  - Example: *customer.customer_id (PK) and* *activity.customer_id* 

- **common_fields_in_both_pk** - common fields that are part of the PK in both datasets, but where dataset1 has less PKs than dataset2.

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

- **same_field_names_pk** - common fields that are part of the PK in both datasets, and where both datasets have an identical PKs number.

  - The relation is created and its direction is random. 

- **same_field_names_not_pk** - both datasets have fields with the same names (normalized, not in *field_name_blk*), both are non-PK.

  - The relation is created and its direction is random. 

### Field Exclusion List

Fields can be excluded from the **Metadata Logical Reference** plugin's matching algorithm by either their name or type. The exclusion list can be defined using the **field_name_exclude_list** and **field_type_exclude_list** arrays in the plugin's input parameters definition of the plugins.discovery configuration file. This can be useful when, for example, the same field name exists in many datasets of the same schema and this field should not be part of the *refers to* relation, e.g., lastModifiedDate.