<studio>

# SET and LIST Commands for Environments

- The **SET** command displays an active environment from the list of the environments deployed to the Fabric Server.

  - Use the following syntax:

    ~~~
    fabric>set;
    |key                                   |value                               |
    +--------------------------------------+------------------------------------+
    |...           
    |ENVIRONMENT                           |UAT1                                |
    |...
    ~~~

- The **SET_GLOBAL ENVIRONMENT** command is used to set the active environment **of the entire cluster**. This command has a global impact and replaces the active environment of all open Fabric sessions and of new Fabric sessions. The values are kept in the **global_settings** Cassandra table under the [k2system keyspace](/articles/02_fabric_architecture/06_cassandra_keyspaces_for_fabric.md).

  - Use the following syntax:

    ~~~
    SET_GLOBAL ENVIRONMENT='<environment name>';
    
    fabric>set_global environment='UAT2';
    (1 row affected)
    ~~~
    
  - To reset the environment to the default *_dev* on a cluster level, use the following syntax:

    ~~~
    SET_GLOBAL ENVIRONMENT='';
    
    fabric>set_global environment='';
    (1 row affected)
    ~~~

- The **SET ENVIRONMENT** command is used to set the environment **of the current session**.

  - Use the following syntax:

    ~~~
    SET ENVIRONMENT='<environment name>';
    ~~~

  - To reset the session's environment to the default *_dev*, use the following syntax:

    ~~~
    SET ENVIRONMENT='';
    ~~~

* The **LIST ENVIRONMENTS** command is used to list all deployed environments.

  * Use the following syntax:

    ~~~
    LIST ENVIRONMENTS; or LIST ENVS;
    
    fabric>list envs;
    |Environments|
    +------------+
    |UAT1        |
    |UAT2        |
    |_dev        |
    
    (3 rows)
    ~~~
    



[![Previous](/articles/images/Previous.png)](04_offline_deployment.md)

</studio>
