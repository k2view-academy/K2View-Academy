# SET and SET_GLOBAL Global Commands for Global Variables 

There are two Fabric SET commands that can override a Global value:
 
* **set_global global**, overrides the value at a **cluster level** whereby both the original and new values are saved in Cassandra. Note that when a Global is overridden it maintains its new value after re-deployment and after Fabric is restarted.  
* **set**, overrides the value at a **session level**.
 
Both commands can override a Global value only if the Global is not defined as Final.

### How do I Use Set_Global Global Command?

This command overrides a Global value on the **entire cluster**, impacting all Fabric sessions, including open ones.\
 set_global global '<LUT NAME>.<PARAM_NAME>[=<PARAM_VALUE>]';

The **set_global global** command sets the value of the Global indicated by <PARAM_NAME> to the value provided by the <PARAM_VALUE>. The PARAM_NAME must be defined as a Global Variable in Fabric.

### How do I Use the Set Variable Command?

The **set** command can be used to set a variable **per session** so that the variable is available for all Fabric objects in the same session. This command can be used either to set a new ad-hoc variable, or to override an existing Global for the Fabric session if the Global is not final.

**Click for more information about Globals – Code Examples.**



