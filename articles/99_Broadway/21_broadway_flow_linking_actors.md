# Broadway Flow- Linking Actors

**A Broadway Flow** is a main Broadway object which represents a business process. A flow is built out of several [Stages](https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP2_99_BROADWAY_Nataly/articles/99_Broadway/!--Link to 18-Flow Stages--) and each Stage includes one or more [Actors](/articles/99_Broadway/03_broadway_actor.md). The Stages are executed one by one from left to right, while the Actors within each Stage of the flow are executed top down.

Each actor has [Data input and output parameters](/articles/99_Broadway/03_broadway_actor.md#actor-window). An input parameter can be populated either by:

- **Link**- get the input value as an input parameter from another actor.
- **Const**- constant. You need to set the value of the parameter.
- **External**- get the input value as a parameter from the external process which executes the Broadway flow. 

You can link the output of source actor to an input of the target actor which runs after the source actor.

Note that you can only link an actor to Link input parameters.

**Example1: Valid Link**

Two **Const** actors are linked to a **DbCommand** actor and send inputs for the DB query:

![link-example1](/articles/99_Broadway/images/valid_link_example.png)

This is a valid link, since the source  Const actors run before the target DbCommand actor.

**Example 2: Invalid Link**

A **Const** actor named FileName sends the file name as a parameter to the **FileRead** actor, but the source  Const actor runs **after** the target FileRead actor:

![link-example2](/articles/99_Broadway/images/invalid_link_example.png)

### Link Object Properties

A link holds the following settings:

![link properties](/articles/99_Broadway/images/link_attributes.png)

- **From (source) parameters**: From Actor, From Parameter, and From Parameter Type. These parameters are read-only parameters and cannot be edited.

- **To (target) parameters**: To Actor, To Parameter, and To Parameter Type. These parameters are read-only parameters and cannot be edited. Note that Broadway makes the best effort to handle differences between the source and target types. For example: if the source type is Integer and the target type is String, then Broadway casts the source integer to a String.

- **Link Type**,  can be edited to set one of the following link types:

  - **Value**- this is the default option. Send the value of the parameter.

  - **Iterate**-open a loop on the transferred parameter. If this option is set, the link line is displayed as a double dashed line. Note that if you link an array or types to an output with a single element of this type- for example: link an array of String to a String output- the link is created automatically with **Iterate** link type. 

    Click for more information about loop handling.

  - **First**- send the first value of the parameter. For example- send the first record of the result set. 

- **Varargs** (variable arguments). Setting the **Varargs** to ON enables the target actor to accept arbitrary number of values by updating the target parameter to be an array and link each source parameter to a different element of the array. This can be useful when the source parameter has unknown type (type is "any"). 

  **Example:**

  - Const1.value is linked to DbCommand1.params. The Varargs setting is OFF:

    ![varagrs-off](/articles/99_Broadway/images/link_varargs_off.png)

  - Updating the Varargs setting to ON updates the params input variable and changes it to an array, holding two elements- the first element is linked to Const1.value and the second element is available for an additional link:

  ![varargs-on1](/articles/99_Broadway/images/link_varargs_on_1.png)

  - Linking Const1.value to the second element of params array adds again an element to the params array to enable linking additional inputs to the params array:

    ![varagrs-on2](/articles/99_Broadway/images/link_varargs_on_2.png)

    

  **Notes:**

  - All additional links to the output array are created automatically with Varagrs, set to ON.
  - If you set  the Varargs of one of these links to OFF, it changes back the target array, created by the Varargs, to its original type, and removes the other links to this target parameter.

  

### How Do I Add Links to the Flow?

A **Link** can be created by either:

- Click the **output parameter** of the source actor and drag the **connection line** to the **input parameter** of the target actor.

- Click the **input parameter** of the target actor and drag the **connection line** to the **output parameter** of the source actor.

- Click the three dots icon (...) in the source actor > Link:

  - First, populate the **Target Actor**.

  - Then populate the **Target Parameter** and the **Selection Parameter** (source parameter).

  - Click the **V** icon to save the changes.

    ![Adding link](/articles/99_Broadway/images/add_link_1.png)

#### Linking a Schema Object 

A Schema object contains different elements. 

**Example:** 

The source parameter holds the following schema:

`{
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
}` 

Click the plus inside the parameter to open the **Data Inspection** object and view the parameters list inside the object:

![data inspection](/articles/99_Broadway/images/data_inspection_example.png)

 You can link a schema to a target actor by either:

- Click the  parameter name in the **Data Inspection** object and drag the **connection line** to the **input parameter** of the target actor.

- Click the three dots icon (...) in the source actor > Link:

  - First, populate the **Target Actor**.

  - Then populate the **Target Parameter** and the **Selection Parameter** (source schema parameter) and **Selection Schema** (parameter name in the source schema).

  - Click the **V** icon to save the changes.

    ![Adding link](/articles/99_Broadway/images/add_link_2.png)



### How Do I Remove Links from the Flow?

Click the connection line of the link and press the Delete key.

### How Do I Edit Links in the Flow?

Click the connection line of the link to open the Link object window on the right side of the Flow window.

Edit the **Link Type** or **Varargs** settings. 

### Show Only Connected Objects

Click the three dots icon (...) in the actor > Show only connected to show only actors connected (linked) to this actor.

Click again the three dots icon (...) in the actor > Show only connected to remove this filter and show all the actors in the flow.

This option is useful when tracking complex flows.

**Example:**

A complex String handling flow:

![String flow](/articles/99_Broadway/images/string_flow_example.png)

To view only the connected actors to Regex1 actor, click the three dots on this actor > Show only connected.

The following actors are displayed:

![show connected-example](/articles/99_Broadway/images/show_connected_examples.png)

