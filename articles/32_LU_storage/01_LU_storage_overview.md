# Logical Unit Storage Overview

A [Logical Unit (LU)](/articles/03_logical_units/01_LU_overview.md) is a blueprint that holds definitions and instructions for creating and maintaining the data of a business entity, such as a customer.

Fabric uses the [System DB](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md) as the default Logical Unit [storage layer](/articles/02_fabric_architecture/01_fabric_architecture_overview.md#21-fabric-storage), where each business entity instance is saved as a [MicroDB](/articles/01_fabric_overview/02_fabric_glossary.md#mdb--microdb) in an **entity** table (and in an **entity_chunks** table for [big LUs](03_big_lu_storage.md)).

The sections below describe the additional LU storage types supported by Fabric and the configuration settings associated with each storage type.

## Storage Types

The location of a Logical Unit's permanent storage depends on the LU Schema's Storage setting. The following storage types are supported:

* **Default** — inherits the default MicroDB storage setting defined by `MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE` in the `[fabricdb]` section of the `config.ini` file.
* **None** — does not persist the LU instance after a GET retrieves the instance data from the source database.
* **S3** — stores LU instances in AWS S3 after execution of the GET command.
* **Azure Blob Store** — stores LU instances in Azure Blob Storage after execution of the GET command.
* **Google Cloud Storage (GCS)** — stores LU instances in Google Cloud Storage after execution of the GET command.
* **NFS** — stores LU instances in shared NFS storage.
* **Cassandra** — stores LU instances in Cassandra after execution of the GET command.

To display an LU's storage type, use the Fabric LIST command.

[Click for more information about the LIST command](/articles/16_deploy_fabric/01_deploy_Fabric_project.md#how-are-deployed-objects-reflected-in-the-fabric-server).

Optimistic locking is supported for all storage types except GCS. If needed, it can be enabled by setting:

```ini
VALIDATE_REMOTE_VERSION=true
```

Changing the LU storage from the default System DB to another storage type does not eliminate Fabric's requirement for a persistent System DB.

[Click for more information about Fabric System Database](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md).

Starting with Fabric 8.0, it is possible to store business entities on PostgreSQL when the use case is primarily querying data across entities. For further reading, click [here](/articles/32_LU_storage/04_business_entity_on_pg.md).

---

## LU Storage Type Reference

The default storage type for MicroDB instances is configured using `MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE` in the `[fabricdb]` section of `config.ini`.

The default configuration is:

```ini
[fabricdb]

## Defines the default storage for Micro Databases (can be changed for individual schemas from Studio). Storage can be:
## SYSTEM_DB/S3/AZURE_BLOB_STORE/GCS/NFS/NONE (requires restart)
#MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=SYSTEM_DB
```

When the property is not explicitly set, `SYSTEM_DB` is used.

The default storage type can be overridden for an individual LU Schema by changing its **Storage** property in Studio.

### SYSTEM_DB

`SYSTEM_DB` is the default MicroDB storage type.

```ini
[fabricdb]

MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=SYSTEM_DB
```

MicroDB instances are persisted in the Fabric System DB.

No additional LU storage configuration is required when `SYSTEM_DB` is used.

### S3

To use AWS S3 as the default MicroDB storage:

```ini
[fabricdb]

MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=S3
```

The S3 storage connection is configured in the `[s3_storage]` section of `config.ini`.

For example:

```ini
[s3_storage]

...
```

The S3 storage configuration defines the storage location and connection properties used by Fabric to persist MicroDB instances.

### AZURE_BLOB_STORE

To use Azure Blob Storage as the default MicroDB storage:

```ini
[fabricdb]

MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=AZURE_BLOB_STORE
```

The Azure Blob Storage connection is configured in the `[azure_blob_storage]` section of `config.ini`.

For example:

```ini
[azure_blob_storage]

...
```

The Azure Blob Storage configuration defines the storage location and connection properties used by Fabric to persist MicroDB instances.

### GCS

To use Google Cloud Storage as the default MicroDB storage:

```ini
[fabricdb]

MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=GCS
```

The Google Cloud Storage connection is configured in the `[gcs_storage]` section of `config.ini`.

For example:

```ini
[gcs_storage]

...
```

The GCS configuration defines the storage location and connection properties used by Fabric to persist MicroDB instances.

### NFS

NFS can be used as shared persistent storage for MicroDB instances.

To use NFS as the default MicroDB storage, set `MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE` to `NFS`:

```ini
[fabricdb]

MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=NFS
```

Unlike the other storage types, the NFS storage path is defined using the `MDB_DEFAULT_STORAGE_PATH` property.

The default is set to

```ini
${FABRIC_HOME}/storage
```

The NFS filesystem must be mounted and accessible to the Fabric nodes that use the NFS-backed LU storage.

### NONE

`NONE` disables persistent MicroDB storage.

```ini
[fabricdb]

MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE=NONE
```

When `NONE` is configured, an LU instance retrieved from its source systems is not persisted in the configured LU storage after the GET operation.

---

## Storage Configuration Summary

<table>
  <thead>
    <tr>
      <th>Storage Type</th>
      <th><code>MDB_DEFAULT_SCHEMA_CACHE_STORAGE_TYPE</code></th>
      <th>Additional Configuration</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>System DB</td>
      <td><code>SYSTEM_DB</code></td>
      <td>None</td>
    </tr>
    <tr>
      <td>AWS S3</td>
      <td><code>S3</code></td>
      <td><code>[s3_storage]</code></td>
    </tr>
    <tr>
      <td>Azure Blob Storage</td>
      <td><code>AZURE_BLOB_STORE</code></td>
      <td><code>[azure_blob_storage]</code></td>
    </tr>
    <tr>
      <td>Google Cloud Storage</td>
      <td><code>GCS</code></td>
      <td><code>[gcs_storage]</code></td>
    </tr>
    <tr>
      <td>NFS</td>
      <td><code>NFS</code></td>
      <td><code>MDB_DEFAULT_STORAGE_PATH</code> must be added to <code>[fabricdb]</code></td>
    </tr>
    <tr>
      <td>None</td>
      <td><code>NONE</code></td>
      <td>None</td>
    </tr>
  </tbody>
</table>
[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_storage_management.md)
