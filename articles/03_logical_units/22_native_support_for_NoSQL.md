# Native Support for NoSQL Document Storage

### Overview

Starting from V8.3, Fabric can provide native E2E support for NoSQL Document Storage (such as MongoDB or CouchBase), including the following:

* Discovery job can run on an interface of MongoDB or CouchBase instance, once the respective K2exchange connector has been installed in the project. Then the Catalog is created based on the discovered document hierarchy. (This feature is already supported prior to 8.3)

* The Web Studio’s Interface explorer can now present the Document’s complex structures such as nested hierarchy levels and arrays of primitives.

* The Logical Unit can now be created based on the Document’s metadata retrieved from the Catalog, after discovery has run on it.

  * The nested hierarchy levels are then created as LU tables with a referential link to their respective parent level.
  * When a complex structure on any level has a non-unique name, the names of the parent levels are concatenated to it, after 3 underscores. E.g. ```emailInfo___emergencyContacts```.

* The LU tables are created with the following system-generated fields:

  * ```_docId``` is a unique ID added to each LU table. Its purpose is to uniquely identify the row of an instance, when splitting the document and composing it back.
  * ```_parentDocId``` is added to all LU tables except for the root table, and it is used for creating the referential link from the nested structure to its parent structure.
  * ```_value``` is only added to LU tables that represent an array of primitives, to keep the element’s value.
  * ```_docHints``` is a field used internally by Fabric to manage the composition of the original Document.

* The root table population is the only population that reads data from the source (the Document).

* The populations created for such LU tables include a dedicated **DocumentQuery** actor that is responsible for generating unique IDs to maintain the relations between hierarchy levels of the Document. In addition, the actor takes the respective part of the original Document and breaks it into the fields, populated in the LU table.

  ​
