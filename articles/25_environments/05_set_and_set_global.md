# SET and SET_GLOBAL Commands for Environments

- **SET** command displays the list of active environments, deployed to Fabric server.

  - Use the following syntax:

    ~~~
    SET;
    ~~~

- **SET_GLOBAL ENVIRONMENT** command is used to set the environment **for the whole cluster**. This command has a global impact and replaces the active environment for all open Fabric sessions and for new Fabric sessions.

  - Use the following syntax:

    ~~~
    SET_GLOBAL ENVIRONMENT='<environment name>';
    ~~~

- **SET ENVIRONMENT** command is used to set the environment **for the current session**.

  - Use the following syntax:

    ~~~
    SET ENVIRONMENT='<environment name>';
    ~~~

  - To reset the session environment, use the following syntax:

    ~~~
    SET ENVIRONMENT='';
    ~~~

[![Previous](/articles/images/Previous.png)](04_manual_deployment_from_XML_file.md)