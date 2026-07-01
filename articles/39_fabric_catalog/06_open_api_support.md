# OpenAPI Support

## Overview

Starting from V8.5, the [File Cataloging](05_cataloging_of_files.md) framework supports discovery of **OpenAPI** interfaces (versions 3.0 and 3.1). This allows Fabric to automatically discover and catalog REST API endpoints — their structure, parameters, and data schemas — making them part of the Catalog alongside other data assets.

The solution follows the same Crawler-based mechanism used for other file cataloging sources: a **Get Metadata** Broadway flow is invoked to build the Catalog metadata using the dedicated **OpenApiToMetadata** actor. Once the metadata is ready, the standard Discovery pipeline — including classification, PII detection, and metrics plugins — executes in the same way as for any other data source.

## OpenApiToMetadata Actor

The OpenApiToMetadata actor reads an OpenAPI specification file and outputs an array of maps in the Catalog's expected metadata format, which is then consumed by the Crawler to build the Catalog structure.

The actor supports OpenAPI Specification versions 3.0 and 3.1 only. If the input file does not conform to one of these versions, the actor throws an exception.

When an API exposes several versions (e.g., v1, v2), the actor uses the latest version for discovery. A property is created on the data platform node with the version number, so the version context is preserved in the Catalog.

## Catalog Hierarchy Mapping

OpenAPI concepts map to the Catalog hierarchy as follows:

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 150px;"><strong>Catalog Node</strong></td>
<td style="width: 150px;"><strong>OpenAPI Concept</strong></td>
<td style="width: 600px;"><strong>Detailed Logic</strong></td>
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
<td>Derived from <code>paths</code>, when <code>application/json</code> schema in the Endpoint response is not empty</td>
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

The `tags` defined in OpenAPI specification determine the list of Catalog schemas. Each dataset (Endpoint) is placed under the schema that matches its tag. In case of multiple `tags` values, the first value is used.

* If `tags` is not defined in the Endpoint specification, the `schema` actor input is used.
* If there is neither `tags` nor the `schema` input, the actor throws an exception. 

Each entry under `paths` in the OpenAPI specification becomes a Catalog dataset, named after the endpoint path.

* When a path includes parameters, the parameters are kept as part of the dataset name. For example, the path `/customers/{customerId}/orders` will be transformed to `customersCustomerIdOrders` dataset.
* When the path includes brackets, they will be dropped. For example, the path `/A_EmailAddress(AddressID='{AddressID}',Person='{Person}')` will be transformed to `A_EmailAddress` dataset.

## Metadata Extraction Rules

The following rules govern how the actor interprets an OpenAPI specification when building the Catalog metadata.

**Dataset represents an entity, not an HTTP operation.** A dataset corresponds to an API resource (entity), not to individual HTTP methods. GET, POST, and DELETE defined on the same path all refer to the same dataset.

**`components/schemas` is the primary data source.** The `components/schemas` section contains the complete, canonical data model definitions and is treated as the authoritative source for field structures.

**`paths` is supplemental.** The `paths` section identifies which entities are exposed by the API and provides names for inline schemas. It does not replace `components/schemas` as the source of field definitions.

**Schema composition is preserved.** When a schema uses composition keywords — `allOf`, `anyOf`, or `oneOf` — the member schemas are kept as separate `definedBy` relationships in the Catalog rather than being flattened into a single list of fields. This preserves the original structure and inheritance relationships of the data model.

**HTTP transport elements are excluded.** Transport-level constructs — such as path and query parameters, request headers, security schemes, and primitive (non-object) responses — are not data model elements and are therefore excluded from the Catalog.

**Fields are merged across HTTP methods.** When multiple HTTP methods (e.g., GET, POST, PATCH) are defined on the same path, their fields are collected from all methods and merged to produce the complete field set for the dataset.

## Implementation

The setup follows the same pattern as other file cataloging sources:

1. Create a **Get Metadata** Broadway flow (under Shared Objects) that uses the **OpenApiToMetadata** actor, providing the OpenAPI spec file as input. Note that **Get Files List** and **Get File Data** flow are not required for the OpenAPI spec file discovery.
2. Attach the flow to the relevant interface using the **Discovery** parameters group. 
3. Deploy the Web Services LU and run Discovery on the interface.

Refer to [File Cataloging](05_cataloging_of_files.md) for the general implementation guidelines on creating transformation flows and attaching them to interfaces.

[![Previous](/articles/images/Previous.png)](05_cataloging_of_files.md)
