# SET and LIST Commands for Environments

- **SET** command displays the list of active environments deployed to the Fabric Server.

  - Use the following syntax:

    ~~~
    fabric>set;
    |key                                   |value                               |
    +--------------------------------------+------------------------------------+
    |...           
    |ENVIRONMENT                           |UAT1                                |
    |...
    ~~~

- **SET_GLOBAL ENVIRONMENT** command is used to set the environment **for the whole cluster**. This command has a global impact and replaces the active environment of all open Fabric sessions and of new Fabric sessions. The values are kept in the **global_settings** Cassandra table under [k2system keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md).

  - Use the following syntax:

    ~~~
    SET_GLOBAL ENVIRONMENT='<environment name>';
    
    fabric>set_global environment='UAT2';
    (1 row affected)
    ~~~
    
  - To reset the environment back to the default *_dev* on the cluster level, use the following syntax:

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

  - To reset the session environment to the default *_dev*, use the following syntax:

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

    



[![Previous](/articles/images/Previous.png)](04_offline_deployment.md)
