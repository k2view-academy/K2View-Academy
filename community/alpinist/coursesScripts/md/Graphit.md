# Graphit

## Slide 1: Course 12 - Graphit

Alpinist

## Slide 2: Agenda

WELCOME
  - What is Graphit
  - Node types
  - Node properties
  - Parameters
  - Variables
  - Error handling
  - Input parameters
  - Permissions
  - Versioning
  - Verb
  - How to invoke Graphit

## Slide 3: What is Graphit?

Graphit is a Fabric UI utility for designing web services with minimal coding.
Graphit enables the visual design of JSON, XML, and CSV documents.
If the Graphit is defined under LUT (not Web Services) it can be invoked from other Fabric components. For example:
  - Store API response in the LUI in advance, to have faster Web Service response)
  - Send CDC messages to downstream apps

## Slide 4: What is Graphit?

- Graphit Terminology
- Node: Represents an output element or a functional element within the system.
- Node Type: Specifies the method for generating and structuring the content of a node.
- Node Property: Provides additional instructions to a node, such as formatting a number, specifying a database to query, or indicating whether the node is active or disabled.
- Nodes
- Node Type
- Node Properties

## Slide 5: Graphit Node Types and Properties

- Graphit Node Types are organized into four logical categories
- Field Nodes - Represent individual data points. Node Types: Field, Function, String, Raw
- Loop Nodes - Handle arrays or repeating sections. Node Types: SQL, SQL non-prepared, Function
- Structure Nodes - Manage output structure. Node Types: Condition, Group, Collect.
- Execute Nodes – Execute actions like GET requests or Broadway flows. Node Types:  GET, Broadway.
- Graphit Node Properties define additional behavior for nodes, such as:
- Formatting numbers and dates.
- Specifying the database for query execution.
- Modifying node output.
- Each node must be assigned a specific node type, and additional properties can be used to customize its functionality. Nodes that generate output must also have a designated name.

## Slide 6: Graphit Node Types

- Field - Basic node type. Defines the node as a tag in XML/JSON format.
- Output:
- Tag name
- Tag value

## Slide 7: Graphit Node Types

SQL - Specifies an SQL statement / Fabric commands. Support prepared statements and binding.
  - The SQL output automatically added as nodes to the Graphit.
  - The SQL Type loops through the returned records, executing nested code for each row in the result set.
Function - JavaScript code that returns the value of an XML/JSON tag.
SQL output iterations

## Slide 8: Graphit Node Types

SQL non-preparedTo be used if parts of the query are dynamic (such as table name).

## Slide 9: Graphit Node Types

- GET - Executes a GET command on a specified LUT and IID.
- String - Provides value to the tag, supporting variables like input parameters or values from previous field nodes.
- Broadway - Defines a Broadway flow to be executed, The flow's output parameters automatically added as nodes to the Graphit.
- BW flow output parameters
- BW flow input parameters

## Slide 10: Graphit Node Types

Condition - Implements IF-ELSE functionality, where the node's content is a simple code that returns a Boolean result.
  - The first node after the condition represents the "true" branch.
  - The next sibling node represents the "false" branch.
  - Any child nodes under the true or false branches are included based on the evaluation of the condition.
False element including child nodes
True element including child nodes
Condition element

## Slide 11: Graphit Node Types

- Group - Groups several elements under a single entry tag. Used mainly with Condition nodes.
- Result without Group

## Slide 12: Graphit Node Types

Row - Presents data as output without any manipulation. For example, a header for an XML format.

## Slide 13: Graphit Node Types

- Collect -  Combines multiple datasets into a single unified array.
- Result without collect

## Slide 14: Graphit Node Properties

- Session Provider – Used with SQL or Non-Prepared SQL node types to specify the interface to be used for executing a query, if not defaulting to Fabric.
- Nice - Configures the layout of the output format. When set to True, each tag is printed on a new line and properly indented.

## Slide 15: Graphit Node Properties

- One – Determines whether the node is treated as a single value or an array. When set to True, the result is always a single entry, even if the underlying query returns multiple rows.
- Result without ‘one’

## Slide 16: Graphit Node Properties

- Entry Tag - Specifies the tag used to enclose XML array entries. If not specified or set to None, the default value <entry> is applied.
- Result without ‘entryTag’

## Slide 17: Graphit Node Properties

Keys - Advanced mechanism that replaces nested queries by joining the data on the root query and grouping it with a key.Example without keys – join query return entry per record:

## Slide 18: Graphit Node Properties

- The Key should contain the “group by” element
- Example after adding the Keys property:

## Slide 19: Graphit Node Properties

Attribute – to change a value from an element to an attribute

## Slide 20: Graphit Node Properties

- Include – add another Graphit file to the existing Graphit
- includeGraphit.graphit
- You can address any field/variables in the included graphit, as if it’s written in the outer graphit

## Slide 21: Graphit Node Properties

Additional properties:
Show Empty - Defines whether empty nodes are displayed in the output. Default = True.Note that this property affects the node and its child nodes.
Show null - Defines whether null entries are displayed in the output. Default = True.Note that this property affects the node and its child nodes.
Output formats:
  - DatetimeFormat - Controls how date/time are formatted in the output
  - NumberFormat - Controls how numbers are formatted in the output
CSV - The following node properties control CSV format:
  - csvHeader - disables a header row
  - csvRow - defines the delimiter between rows in CSV format. The default value is set to the CR sign (\n).
  - csvCol - defines the delimiter between columns in CSV format. The default value is set to the comma character.
  - csvEnclose - defines the character used to enclose a value in CSV format. This is used only if the value holds a special character (csvEnclose, csvRow, csvCol).
Format – When defined, the node is evaluated and added when the output format matches the format's JSON, XML or CSV value. Note that this property only affects the node where it is defined.

## Slide 22: Graphit Variables

- Previous Field Nodes: Variables derived from earlier field nodes.
- SQL Query/Broadway Result: Data obtained from SQL queries or Broadway execution results.
- Input Parameters: User-defined inputs.
- Custom Variables in Function Node Type: Variables can be defined within a Function node (using JavaScript).  Unlike Field nodes, these variables can be accessed outside the scope of the field node.
- Use query result as variable
- Define variable
- Use the variable from outside the loop

## Slide 23: Error Handling

Error.Graphit
Executes automatically on unhandled exceptions from Fabric Web Service calls.
Not triggered if the exception is caught in the implementation.
Ensures a standardized error response in JSON or XML, matching the original request format.
The implementer has full flexibility to determine the cause of the underlying failure that triggered the process and adjust the Web Service response accordingly, including:
  - Status – e.g., 404 for “Page Not Found” (use setStatus to override).
  - Message – custom error message text.
  - Path – the endpoint or resource path involved.
  - Method – the HTTP method used (GET, POST, etc.).
  - Header – response headers (use getHeader / setHeader to read or modify

## Slide 24: Graphit Input parameters

- Use       button to define parameters.Use ${param_name} syntax
- Binding
- Binding

## Slide 25: Graphit Permissions

Require Authentication
  - Indicates whether a web service requires authentication or not. Default is set to True.
  - When set to False, it will allow calling the web service by skipping the Authentication step. This mode should be carefully used; use it only when a Web Service should be accessible for everyone, without enforcing an API key, a user/password, etc.
Additional Permissions
  - When a web service caller lacks the necessary role-based permissions to execute certain Fabric operations used within a Graphit, the developer can enable temporary elevated access specifically for the scope of that web service.
  - To do so, select the required permissions in the Graphit’s properties—these will override the caller's default permissions during execution.
  - For the full list of available permissions, refer to:https://support.k2view.com/Academy/articles/17_fabric_credentials/01_fabric_credentials_overview.html

## Slide 26: Graphit Versioning

Web Service Versioning
Versioning allows multiple versions of a web service to coexist, enabling support for different client needs. The version number is typically included in the service URL, but it is optional—if omitted, the default version is 1.
Versioning Logic
  - No version in URL → API returns the latest version.
  - Version in URL exists → API returns the specified version.
  - Version in URL higher than latest → API returns the latest version.
  - Version in URL lower than earliest → API returns an appropriate error response code.
  - Version in URL between two versions → API returns the lower of the two.
Implementation
  - Graphit: Add the version as part of the file name, e.g., a.v1.graphit.

## Slide 27: Graphit Verb

Methods supported by the web service, as follows:
  - GET - get data.
  - POST - create new data based on the data provided.
  - PUT - update data.
  - DELETE - delete data.
In Graphit, the verb setting is done by incorporating it as a part of the file name. For example: "a.POST.graphit".
Note:
  - Only a single verb can be set.
  - When a verb is omitted, the web service is exposed with GET and POST verbs.

## Slide 28: Invoke Graphit

- Graphit files can be invoked either externally as Web Services or internally from other Fabric implementation components (if defined under LUT).
- Web Service:
- Check “Expose as Endpoint” field
- Use the URLs provided in the Graphit Settings:Note: you can override the URL path using the Path parameter

## Slide 29: Invoke Graphit

- Map<String, Object> graphitParams = new HashMap<>(); graphitParams.put(“customer_id",14); return graphit("CustomerDetails.graphit", graphitParams);
- From Fabric Implementation Components
- Invoking From a Java Function
- Invoking Broadway flow using Graphit Actor:

## Slide 30: Graphit Assignment

Create the below Graphit:
  - Execute GET in Sync OFF for a Customer LUI
    - The IID should be parameter to the Graphit
  - Show the Customer’s data from Customer table
    - SSN should present only the last 4 digist. The others should be replaced with “*”
    - As there is only 1 record in the Customer table, make sure there is no entry tag to this record
  - Show the subscriber balance data from subscriber_balance table (created on course 7 – Project Functions)
    - For each record, add “payment_status” field and calculate the below using Broadway flow:
      - If balance = 0  Show PAID status
      - Else - if due_date passed  show “Late”
      - Otherwise  show “On Time”
    - Each record should have <balance_record> elements wrapper
    - Format the due_date to show only date
    - Format the balance #,###.00
  - While iterating on the customer_balance records, sum the total paid amount
    - Show the total paid amount at the end of the Graphit
  - Create error handler graphit
