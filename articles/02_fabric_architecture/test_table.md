## 1.1  Main Directories
<!---tali - test comment->

### 1.1.1Temp Files Directory

This directory holds the cached database files. The default directory is /dev/shm/ fdb_cache/<LU NAME>/ (as defined in the K2View Fabric config file).

Example: 6.db

Note: 6 is incremental sequence

## 1.2  Fabric Login

In order to login into Fabric console, run Fabric script

This keyspace holds K2view Fabric batch processes, including migration, information.

- fffff
  - ffff
    - fffgg
      - fgbhgh

| Table Name            | Description                                                  |
| --------------------- | ------------------------------------------------------------ |
| batch_list            | List of the entire history of the batch process commands.    |
| batch_node_info       | Summary of handled entities per batch process per node.      |
| batch_entities_info   | Detailed execution  information for a given entity per batch  process  command. |
| batch_entities_errors | Detailed entities information for failed entities  (instances) per migration execution. This table is intended to improve the  search performance for failed instances in migration. |

### 1.1.1K2audit

| Table Name  | Description                                                            |
| ----------- | ---------------------------------------------------------------------- |
| K2_auditing | Every activity performed within K2View Fabric is logged into this table. |
