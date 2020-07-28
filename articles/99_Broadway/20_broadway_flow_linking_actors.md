# Broadway Flow - Linking Actors

**A Broadway Flow** is a main Broadway object that represents a business process. A flow has several [Stages](/articles/99_Broadway/16_broadway_flow_overview.md) where each Stage includes one or more [Actors](/articles/99_Broadway/03_broadway_actor.md). Stages are executed consecutively from left to right whereas the Actors in each Stage of the flow are executed top-down.

Each actor has [data input and output parameters](/articles/99_Broadway/03_broadway_actor.md#actor-window). Input parameters can be populated  by either a:

- **Link**, which gets an input value as an input parameter from another Actor.
- **Const**, a constant value that is set for the parameter.
- **External**, which gets an input value as a parameter from an external process that executes the Broadway flow.

The output of a source Actor can be linked to to the input of a target Actor that runs after the source Actor.

Note that an Actor can only be linked to link input parameters.

**Example 1: Valid Link**

Two **Const** Actors are linked to a **DbCommand** Actor and send input for a DB query:

![link-example1](/articles/99_Broadway/images/valid_link_example.png)

This link is valid since the source Const Actors run before the target DbCommand Actor.

**Example 2: Invalid Link**

A FileName **Const** Actor sends the filename as a parameter to the **FileRead** Actor and the source **Const** Actor runs **after** the target FileRead Actor:

![link-example2](/articles/99_Broadway/images/invalid_link_example.png)

## Link Object Properties

A link holds the following settings:

![link properties](/articles/99_Broadway/images/link_attributes.png)

- **From (source) parameters**, From Actor, From Parameter and From Parameter Type. These parameters are read-only parameters and cannot be edited.

- **To (target) parameters**, To Actor, To Parameter and To Parameter Type. These parameters are read-only and cannot be edited. Note that Broadway attempts to handle the differences between the source and target types. For example: if the source type is Integer and the target type is String, then Broadway casts the source integer to a String.

- **Link Type**, can be edited to set one of the following link types:

  - **Value**, (default option). Sends the value of the parameter.

  - **Iterate**, opens a loop on the transferred parameter. When set, the link line is displayed as a double-dashed line. Note that if an Array or Type is linked to an output with a single element of this type - for example, linking an array of string to a string output - the link is created automatically with an **Iterate** link type. 

    Click for more information about handling loops.

  - **First**, sends the first value of the parameter. For example, sends the first record of the result set. 

- **Varargs** (variable arguments). When set to ON, the target Actor accepts an arbitrary number of values by updating the target parameter to an Array and linking each source parameter to a different element in the Array. This can be useful when the source parameter has an **any** (unknown) Type. 

  **Example:**

  - Const1.value is linked to DbCommand1.params. The Varargs setting is OFF:

    ![varagrs-off](/articles/99_Broadway/images/link_varargs_off.png)
  
  
  
- Updating the Varargs setting to ON updates the params input variable and changes it to an array that holds two elements - the first is linked to the Const1.value and the second is available for an additional link:
  
  ![varargs-on1](/articles/99_Broadway/images/link_varargs_on_1.png)

  

  
- Linking the Const1.value to the second element in the params array again adds an element to the params array and enables linking additional inputs to the params array:
  
  ![varagrs-on2](/articles/99_Broadway/images/link_varargs_on_2.png)
  
  
  
  **Notes:**

  - All additional links to the output array are created automatically when **Varagrs** is set to ON.
  - When a Varargs link is set to OFF, the target array returns to its original type as created by Varargs and removes other links to this target parameter.
  
  

## How Do I Add Links to the Flow?

To create a **Link** do either:

- Click the **output parameter** of the source Actor and drag the **connection line** to the **input parameter** of the target Actor.

- Click the **input parameter** of the target Actor and drag the **connection line** to the **output parameter** of the source Actor.

- Click **...** in the source **Actor** > **Link**. Populate the **Target Actor**, **Target Parameter** and the **Selection Parameter** (source parameter) and then click **V** to save the changes.

    ![Adding link](/articles/99_Broadway/images/add_link_1.png)
    

### Linking a Schema Object 

A Schema contains different elements. 

**Example:** 

The source parameter holds the following Schema:

```
{
    "type": "object",
    "properties": {
        "name": {
            "type": "string"
        },
        "family_name": {
            "type": "string"
        },
        "age": {
            "type": "integer"
        },
        "car": {
            "type": "string"
        }
    }
} 
```

To connect a specific element in the Schema, click **+** in the parameter to open the **Data Inspection** and view the parameters list in the object:

![data inspection](/articles/99_Broadway/images/data_inspection_example.png)

 To link a Schema a target Actor do either:

- Click the parameter name in the **Data Inspection** and drag the **connection line** to the **input parameter** of the target Actor.

- Click the **...** in the **source actor** > **link**. Populate the **Target Actor**, **Target Parameter**, **Selection Parameter** (source Schema parameter) and **Selection Schema** (parameter name in the source Schema). Click **V** to save the changes.

    ![Adding link](/articles/99_Broadway/images/add_link_2.png)

A Schema can be connected to another Actor. For example, connecting the output Schema to the **params** input parameter of the **DbLoad** Actor. Note that if a specific element of the Data Inspection object is connected to another input parameter of the **DbLoad** Actor, the specific link overrides the link of the Schema to the **params** input parameter.  

## How Do I Remove Links from the Flow?

Click the link's connection line and press **Delete** on  your keyboard.

## How Do I Edit Links in the Flow?

Click the link's connection line to open the **Link Object window** on the right of the Flow window. 
Edit the **Link Type** or **Varargs** settings. 

## Show Only Connected Objects

Click the **...** in the **Actor** > **Show only connected** to display only Actors linked to this Actor.

Click **...** again in the **Actor** > **Show only connected** to remove this filter and display all Actors in the flow.

This option is useful when tracking complex flows.

**Example:**

A complex String handling flow:

![String flow](/articles/99_Broadway/images/string_flow_example.png)

To view only the  Actors connected to Regex1 actor, click **...** in the **Actor** > **Show only connected**. The following actors are displayed:

![show connected-example](/articles/99_Broadway/images/show_connected_examples.png)

