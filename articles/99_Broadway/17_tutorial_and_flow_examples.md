# Broadway Tutorial and Flow Examples

In order to understand the Broadway main principles and capabilities, K2View provides the **a-broadway-tutorial.flow** built-in tutorial. This is an example of a [Broadway flow](/articles/99_Broadway/16_broadway_flow_overview.md) which guides a user step-by-step through a sample Business Process and explains how to implement it using the Broadway. 

In addition to the main tutorial, there are many other built-in example flows, each explaining a specific subject within Broadway in more details, for example DB commands, conditions, parsers, dates and strings handling. 

To open the tutorial or any additional example flow, go to **Actions > Examples** in the [main menu of the Broadway flow window](/articles/99_Broadway/18_broadway_flow_window.md#main-menu) and select an example flow from the pop-up window.

![image](/articles/99_Broadway/images/99_17_01_tutorial.PNG)

The tutorial's explanations are provided using a **Remark** - a green asterisk (*) which appears in the right corner of a [Stage](/articles/99_Broadway/19_broadway_flow_stages.md) or an [Actor](/articles/99_Broadway/04_built_in_actor_types.md). A user can either:

- Press * in the main menu of the flow in order to see all the remarks at once.
- Or, press * in each Stage or Actor to see its remark.

The tutorial shows that the flow can run by either:

- Using **Run Flow** icon in the main menu. Then the flow will run till the end and show the results.
- Using **Debug Play** icon in the main menu. The the flow will stop at the first break point and can be continued using the **Debug Step** icon. [Click for more information about the Debug flow](<!--Link to 26-Flow window- run + debug flow-->).

The tutorial explains the usage of several most common [built-in Actor Types](/articles/99_Broadway/04_built_in_actor_types.md), for example:

- **Const** is a basic Actor which copies an input argument to the output value argument. 
- **StringBuild**, **Replace** and **Concat** are only some of the Actors which deal with the strings in a Broadway flow. Checkout **strings.flow** about working with strings in a Broadway flow.
- Broadway supports several types of parser Actors, for example **JsonParser** or **XmlParser**. Parsers take a string or blob and parse them into a structure. Checkout [**json.flow**](/articles/99_Broadway/17_tutorial_and_flow_examples.md#example---jsonflow), **csv.flow** and **lines.flow** for more parsing examples.

[Dynamic Logic Actors](<!--Link to 6-Edit Actors - Dynamic actors-->) are Actors which include the dynamic logic as one of their inputs. You can add input ports to these Actors and refer to them in the Actor's logic. For example, **JavaScript** Actor executes the Javascript provided in the 'script' parameter and returns the value of the last expression. **javascript.flow** provides an additional explanation of how this Actor works. 

The **conditions** are implemented in Broadway using [**Split** option](/articles/99_Broadway/19_broadway_flow_stages.md#how-do-i-split-or-merge-the-stages) in the [Stage context menu](/articles/99_Broadway/18_broadway_flow_window.md#stage-context-menu). You can split the flow and assign a condition to one or more Stages created as a result of the split. An **else** Stage will run if all other Stage conditions returned false. Checkout **conditions.flow** for more conditions examples.

The [flow loops](<!--Link to 22-Flow Loops-->) are implemented in Broadway via **Iterate** connection type. You can change the line type by selecting the line and changing its Link Type value to Iterate in the Properties Pane. To close the scope of the iteration, select **Iterate Close** from the Stage context menu. Checkout **iterate-for-each.flow** for more loop's examples.

Any Actor's input or output argument can be set as [External](<!--Link to 5-Actors-Input params-->). When marking an output parameter as External, it becomes an output for the entire flow. In addition, a flow can be invoked using the **InnerFlow** Actor. You can also [package an entire flow as an Actor](<!-- Add link to 23-Inner flow-->). To do so, go to **Actions > Save As Actor** in the main menu. Checkout **javascript-advanced.flow**, **inner-flow-power.flow** and **inner-flow.flow** for more explanation about input/output arguments and examples of the inner flow.  

### Example - json.flow 

**json.flow** example shows how to parse a collection of byte arrays (stream). In the example the source is a constant. Based on the requirements, the source can be set to a **FileReader**, **Http** or any Actor that will produce a stream/string.

A **JsonParser** Actor can either parse a single Json object or multiple json objects arriving on the same stream. Note that an array is a single json object. In this example you can see both: the first handles a stream and initiates an Iteration loop and the other handles a single entry.

![image](/articles/99_Broadway/images/99_17_02_tutorial.PNG)

