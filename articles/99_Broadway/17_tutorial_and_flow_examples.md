# Broadway Tutorial and Flow Examples

In order to understand the Broadway main principles and capabilities, K2View provides the **a-broadway-tutorial.flow** built-in tutorial. This is an example [Broadway flow](<!--link to 21-Broadway flow -->) which guides a user step-by-step through a sample Business Process. 

![image](/articles/99_Broadway/images/99_17_01_tutorial.png)

In addition to the main tutorial, there are many other built-in example flows, each explaining a specific subject within Broadway in more details, for example DB commands, conditions, parsers, dates and strings handling. To open the tutorial or any additional example flow, go to **Actions > Examples** in the main menu and select the example from the pop-up window.

The tutorial is using a **Remark** - a green asterisk (*) which appears in the right corner of a [Stage](<!--Link to 18-Flow Stages-->) or an [Actor](/articles/99_Broadway/04_built_in_actor_types.md) in order to provide the explanations. A user can either:

- Press * in the main menu of the flow in order to see all the remarks at once.
- Or, press * in each Stage or Actor to see its remark.

The flow can run by either:

- Using **Run Flow** icon in the main menu. Then the flow will run till the end and show the results.
- Using **Debug Play** icon in the main menu. The the flow will stop at the first break point and can be continued using the **Debug Step** icon. [Click for more information about the Debug flow](<!--Link to 26-Flow window- run + debug flow-->).

The tutorial shows several most common [built-in Actor Types](/articles/99_Broadway/04_built_in_actor_types.md), for example:

- **Const** is a basic Actor which copies an input argument to the output value argument. 
- **StringBuild**, **Replace** and **Concat** are only some of the Actors which deal with the strings in a Broadway flow. Checkout **strings.flow** about working with strings in a Broadway flow.
- Broadway supports several types of parser Actors, for example **JsonParser** or **XmlParser**. Parsers take a string or blob and parse them into a structure. Checkout **json.flow**, **csv.flow** and **lines.flow** for more parsing examples.

[Dynamic Logic Actors](<!--Link to 6-Edit Actors - Dynamic actors-->) are Actors which have dynamic logic as one of their inputs. For these actors you can add inputs ports and refer to them in the Actor logic. For example, **JavaScript** Actor executes the Javascript provided in the 'script' parameter and returns the value of the last line. **javascript.flow** provides additional explanation of how this Actor works. 

The **conditions** are implemented in Broadway via [**Split** option](<!--Link to 18-Flow Stages - Split-->) in the context menu. You can split the flow and assign a condition to each sub stage. An **else** Stage will run if all other stage conditions returned false. The Stages in the same row are dependent on the Stages before them. Checkout **conditions.flow** for more conditions examples.

The [flow loops](<!--Link to 22-Flow Loops-->) are implemented in Broadway via **Iterate** connection type. You can change the line type by selecting the line and changing its Link Type value to Iterate in the Properties Pane. To close the scope of the iteration, select **Iterate Close** from the Stage context menu. Checkout **iterate-for-each.flow** for more loop's examples.

Any Actor's input or output argument can be set as [External](<!--Link to 5-Actors-Input params-->). When marking an output parameter as External, it becomes an output for the entire flow. In addition, a flow can be invoked using the **InnerFlow** Actor. You can also [package an entire flow as an Actor](<!-- Add link to 23-Inner flow-->). To do so, go to **Actions > Save As Actor** in the main menu. Checkout **javascript-advanced.flow**, **inner-flow-power.flow** and **inner-flow.flow** for more explanation about input/output arguments and examples of the inner flow.  
