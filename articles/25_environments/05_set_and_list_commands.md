# SET and LIST Commands for Environments

- **SET** command displays the list of active environments, deployed to Fabric server.

  - Use the following syntax:

    ~~~
    fabric>set;
    |key                                   |value                               |
    +--------------------------------------+------------------------------------+
    |ALWAYS_SYNC                           |false                               |
    |ASYNC_TRX                             |false                               |
    |ATTACH_POLICY                         |LATEST                              |
    |CDC_PUBLISH                           |true                                |
    |COMMON_LOCAL_TRX                      |false                               |
    |ENVIRONMENT                           |UAT1                                |
    ...
    ~~~

- **SET_GLOBAL ENVIRONMENT** command is used to set the environment **for the whole cluster**. This command has a global impact and replaces the active environment for all open Fabric sessions and for the new Fabric sessions. The values are kept in the **global_settings** Cassandra table under [k2system keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md).

  - Use the following syntax:

    ~~~
    SET_GLOBAL ENVIRONMENT='<environment name>';
    
    fabric>set_global environment='UAT2';
    (1 row affected)
    ~~~
    
  - To reset the environment back to the default **_dev** on the cluster level, use the following syntax:

    ~~~
    SET_GLOBAL ENVIRONMENT='';
    
    fabric>set_global environment='';
    (1 row affected)
    ~~~

- **SET ENVIRONMENT** command is used to set the environment **for the current session**.

  - Use the following syntax:

    ~~~
    SET ENVIRONMENT='<environment name>';
    ~~~

  - To reset the session environment back to the default **_dev**, use the following syntax:

    ~~~
    SET ENVIRONMENT='';
    ~~~

* **LIST ENVIRONMENTS** command is used to list all deployed environments.

  * Use the following syntax:

    ~~~
    LIST ENVIRONMENTS; or LIST ENVs;
    
    fabric>list envs;
    |Environments|
    +------------+
    |UAT1        |
    |UAT2        |
    |_dev        |
    
    (3 rows)
    fabric>
    ~~~

    



[![Previous](/articles/images/Previous.png)](04_manual_deployment_from_XML_file.md)