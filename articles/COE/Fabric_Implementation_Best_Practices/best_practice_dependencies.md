# Fabric and Project Dependencies

As each project has its class dependencies, we need to make sure that Fabric doesn’t use those dependencies. 

Therefore, starting V7.2.1 Fabric’s dependencies can be isolated from the project’s dependencies. By default, the current functionality is preserved, which means there is no isolation between Fabric and project dependencies.

In order to enable this feature:

- Uncomment PACKAGE_NAMES_CLASS_LOADING_FILTER in config.ini.

- Remove 'FABRIC_EXT_JARS' from scripts/fabric-server-start.sh from the following:
  - *CLASSPATH_STR="$FABRIC_CONF:${ROOTDIR}/lib/fabric/\*:${ROOTDIR}/lib/provided/\*:$FABRIC_EXT_JARS/\*:${ROOTDIR}/resources"*

- Restart Fabric.

[![Previous](/articles/images/Previous.png)](/articles/COE/Fabric_Implementation_Best_Practices/best_practice_broadway.md) 

