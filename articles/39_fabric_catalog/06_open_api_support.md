# OpenAPI Support

## Overview

Starting from V8.5, the [File Cataloging](05_cataloging_of_files.md) framework supports discovery of **OpenAPI** interfaces (versions 3.0 and 3.1). This allows Fabric to automatically discover and catalog REST API endpoints — their structure, parameters, and data schemas — making them part of the Catalog alongside other data assets.

The solution follows the same Crawler-based mechanism used for other file cataloging sources: a **Get Metadata** Broadway flow is invoked to build the Catalog metadata using the dedicated **OpenApiToMetadata** actor. Once the metadata is ready, the standard Discovery pipeline — including classification, PII detection, and metrics plugins — executes in the same way as for any other data source.

## OpenApiToMetadata Actor

The OpenApiToMetadata actor reads an OpenAPI specification file and outputs an array of maps in the Catalog's expected metadata format, which is then consumed by the Crawler to build the Catalog structure.

The actor supports OpenAPI Specification versions 3.0 and 3.1 only. If the input file does not conform to one of these versions, the actor throws an exception.

The actor's `resolver` input (which represents an inner actor or inner flow) fetches the root OpenAPI document and its external $ref references. It defaults to FileRead.actor.

When an API exposes several versions (e.g., v1, v2), the actor uses the latest version for discovery. A property is created on the dataset node with the version number, so the version context is preserved in the Catalog.

## Catalog Hierarchy Mapping

OpenAPI concepts map to the Catalog hierarchy as follows:

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 150px;"><strong>Catalog Node</strong></td>
<td style="width: 150px;"><strong>OpenAPI Concept</strong></td>
<td style="width: 600px;"><strong>How It Is Derived</strong></td>
</tr>
<tr>
<td>dataPlatform</td>
<td>OpenAPI interface</td>
<td> </td>
</tr>
<tr>
<td>schema</td>
<td>Endpoint tag</td>
<td>Derived from the first value of <code>tags</code> array in the Endpoint definition</td>
</tr>
<tr>
<td>dataset</td>
<td>Endpoint name</td>
<td>Derived from <code>paths</code>, when <code>application/json/schema</code> in the Endpoint response is not empty</td>
</tr>
<tr>
<td>class</td>
<td>Schema component</td>
<td>Derived from <code>#/components/schemas/</code></td>
</tr>
<tr>
<td>field</td>
<td>Object component</td>
<td>Derived from <code>properties</code></td>
</tr>
</tbody>
</table>

## Metadata Mapping Rules

The following rules govern how the actor interprets an OpenAPI specification when building the Catalog metadata.

**Schema names are derived from endpoint tags.** The `tags` defined in the OpenAPI specification are used to define the Catalog schema name. Each dataset is placed under the schema that matches its tag. 

* When an endpoint has multiple `tags` values, the first value is used. 
* When `tags` is not defined for an endpoint, the `schema` actor input is used instead. 
* When neither `tags` nor the `schema` input is provided, the actor throws an exception.

**Dataset represents an entity, not an HTTP operation.** A dataset corresponds to an API resource (entity), not to individual HTTP methods. GET, POST, and DELETE defined on the same path all refer to the same dataset.

**Dataset names are derived from path entries.** Each entry under `paths` becomes a dataset named after the endpoint path. 

* When a path includes parameters, they are kept as part of the dataset name, for example: `/customers/{customerId}/orders` becomes `customersCustomerIdOrders`. 
* When the path includes brackets, the brackets and their content are dropped and the base name is used, for example: `/A_EmailAddress(AddressID='{AddressID}',Person='{Person}')` becomes `A_EmailAddress`.

**`components/schemas` is the primary data source; `paths` is supplemental.** The `components/schemas` section contains the complete, canonical data model definitions and is the authoritative source for field structures. The `paths` section identifies which entities are exposed by the API and provides names for inline schemas, but does not replace `components/schemas` as the source of field definitions.

**Classes referenced via `$ref` are shared across datasets.** When an endpoint's `application/json/schema` uses a `$ref` to a `#/components/schemas/` component, the actor creates that component as a class. To prevent metadata duplication, each class is created once per Catalog schema and shared across all datasets that reference it. The response schema type determines whether a class is created:

* When the response schema is `"type": "object"` and includes a `$ref` to `#/components/schemas/`, a class is created from the referenced component and the dataset links to it via a `definedBy` relationship.
* When the response schema is `"type": "array"` and includes a `$ref` to `#/components/schemas/`, no class is created — the component's fields are resolved directly at the dataset level.

**Fields are merged across HTTP methods.** When multiple HTTP methods (GET, POST, etc.) are defined on the same path, their fields are collected from all methods and merged to produce the complete field set for the dataset.

**OpenAPI data model composition is preserved.** When a data model uses composition keywords — `allOf`, `anyOf`, or `oneOf` — the member models are kept as separate `definedBy` relationships in the Catalog rather than being flattened into a single list of fields. This preserves the original structure and inheritance relationships of the data model.

**HTTP transport elements are excluded.** Transport-level constructs — such as path and query parameters, request headers, security schemes, and primitive (non-object) responses — are not data model elements and are therefore excluded from the Catalog.

[![Previous](/articles/images/Previous.png)](05_cataloging_of_files.md)
