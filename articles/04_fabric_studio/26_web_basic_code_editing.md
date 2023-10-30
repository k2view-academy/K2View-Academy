<web>

# Basic Code Editing 

Fabric Studio is a designer tool that manages the construction of [Data Products](/articles/01_fabric_overview/02_fabric_glossary.md#logical-unit--data-product), and includes the features you need for highly productive low-code and source code crafting and editing. 

Web Studio is a full, professional and comprehensive IDE, Visual Studio Code like editor. If you are not familiar with source code editors you can look and learn from VS Code's user guides, but keep in mind that Web studio is not fully the the same (as it is based on Code OSS, the open source project behind VS Code).

Rather than providing such guides, This article takes you through the following core capabilities provided by the Web studio source code editor, helping you get moving with your code:

* Powerful Editing
* Easy and streamlined Debugging, as explained [here]().
* In-Product Source Control, as explained [here]()



## Powerful Editing

Offering a professional code editor, Web studio provides wide variety capabilities, among them:

- As-you-type reporting of parsing and compilation errors. 
- Syntax highlighting and bracket matching
- Smart Code Completion, Code Snippet support and correction suggestions.
- Code navigation, like Go to Definition and Find All References
- Code Refactoring



### Parsing and Compilation Errors Indicators

The as-you-type reporting is shown inline the editor with red line under the error and also in the right mini-map code outline.

Along with that, there are other indications on errors and warnings in a file:

* On the project tree - where whole files hierarchy is red colored.
* At the Warning and Error panels
* At the left side of the bottom panel 
* Top editor tab



In addition, when applicable, the editor, using the language engine, hint you suggests a correction for the problem.

The below example illustrate these indicators and hints

![](images/web/26_parse_indicators.gif)



### Syntax Highlighting and Bracket Matching

Syntax highlighting determines the color and style of the displayed source code. It is responsible for colorizing keywords differently than strings and comments and variable names.

Matching brackets will be highlighted as soon as the cursor is near one of them. You can easily jump between the brackets and also collapse regions which are surrounded by these brackets as explained [here](/articles/04_fabric_studio/27_web_productivity_tips.md#editing-and-debugging). This capability enables you to cope with long source code and focus only on what relevant to you now.

### Smart Code Completion

Code smarter with code completions, hinting and info for variables, methods, member lists and imported modules. This is also called in VS Code, which Web studio based on -  *IntelliSense*. It is called like that as it provides intelligent code completions based on language semantics and an analysis of your source code. 

When possible completions are found, the IntelliSense suggestions will pop up as you type. If you continue typing characters, the list of members (like variables and methods) is filtered to only include members containing your typed characters. Pressing Tab or Enter will insert the selected member. 

You can trigger IntelliSense in the the editor by typing Ctrl+Space or by typing a trigger character - the dot character (.) in Java or JavaScript. 



More detailed explanations and examples can be found [here](https://code.visualstudio.com/docs/editor/intellisense). 

The comprehensive Java language support is done by using Red Hat extension (published both at [Visual Studio Marketplace](https://marketplace.visualstudio.com/items?itemName=redhat.java) and at [Open VSX](https://open-vsx.org/extension/redhat/java))



>  Note: Similar code completion and behavior, though less contextual, is available also at Query Builder SQL editor, using the [Query Builder Assistance](). 

### Code Snippet Support

Code snippets are templates that make it easier to enter repeating code patterns, such as loops or conditional-statements.

Snippets appear in IntelliSense (Ctrl+Space) mixed with other suggestions, and similarly, by typing a snippet prefix (trigger text), where you can see also snippet suggestions. You can then either select the snippet or scroll down with arrows and then press Tab to insert the snippet.

Web Studio has built-in snippets for a number of languages such as: java, JavaScript, TypeScript, Markdown, and also offers various Fabric snippets. 

Type `fabric` for Fabric code's snippet prefix, and then a list of Fabric related snippets will be revealed. You can continue and type also other parts of the snippet prefix, to limit the suggestions. For example: `fabric-func` will bring only Fabric' functions snippets and not others.

Following are Fabric prefixes and snippets:   

* Functions
  * `fabric-function` - Creates a regular [Fabric project function](/articles/07_table_population/08_project_functions.md) template.
  * `fabric-function-enrichment` - Creates an [Enrichment function](/articles/10_enrichment_function/01_enrichment_function_overview.md) template.
  * `fabric-function-decision` - Creates a [Decision function](/articles/14_sync_LU_instance/05_sync_decision_functions.md) template.
  * `fabric-function-trigger` - Creates a [Trigger function](/articles/07_table_population/11_4_creating_a_trigger_function.md) template.
  * `fabric-function-event` - Creates an [Event function](/articles/07_table_population/11_5_creating_an_event_function.md) template.
  * `fabric-function-ludb` - Create a [LUDB function](/articles/07_table_population/11_3_creating_an_LUDB_function.md) template.
  * `fabric-function-ws` - Create a [Web-Service function](/articles/15_web_services_and_graphit/07_custom_ws_create_java_ws.md) template.
    * `fabric-methods` - reveal fabric methods list to be used in WS permissions definition.
  * `fabric-function-ws-raw` - Creates a Web-Service snippet function template, with all available parameters & settings.
* Other Fabric code's snippets:
  * `fabric-global` - Add a [Globals](/articles/08_globals/01_globals_overview.md) variable template. 
  * `fabric-code-db-fetch` - Adds a code template for fetching data from a DB interface (including loop). 
  * `fabric-code-db-execute` - Adds a code template for executing SQL at DB interface.
  * `fabric-code-fabric-fetch` - Adds a code template for fetching data from Fabric DB (including loop).
  * `fabric-code-fabric-execute` - Adds a code template for executing SQL at Fabric DB.





WEB APP VITE

Extensions?





> **NOTE**: Web studio comes with built-in support for  various programming language like Java, JavaScript, TypeScript, CSS, and HTML.
>
> More rich language extensions can be found in [Open VSX Registry](https://open-vsx.org/), which is similar to the VS Code Marketplace.







[![Previous](/articles/images/Previous.png)](/articles/04_fabric_studio/25_web_data_explorer.md)
[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/04_fabric_studio/24_web_debug.md)



</web>
