# BW_Actors

## Slide 1: Course 3BW Actors

Alpinist

## Slide 2: Agenda

- WELCOME
- DB Actors
- Fabric Actors
- JavaScript
- Stage Condition
- Strings
- Dates
- Collections
- Parse & Stringify
- Inner Flows
- PubSub
- Files
- System
- HTTP

## Slide 3: BW – DB Actors

- Broadway has a category of DB Actors that are useful for performing DB commands and actions like creating a new table, loading data or fetching it and executing other DB commands
- DbLoad - Loads data into a database using an INSERT, UPDATE or UPSERT command.Batch Mode: When the batch input argument is set to true, the Actor accumulates statements and executes them as a batch to improve performance. It must run within a transaction, and any errors are reported upon committing the batch. The default batch size is 1,000 statements.

## Slide 4: BW – DB Actors

DbCommand - Performs database commands on a DB interface. It has two extensions:
  - DbFetchField - Returns the first field of the first row
  - DbFetchFirstRow - Returns the first row
  - Parameters and Non-Prepared Statements Support:
  - ?/${0} - for ordered parameters.
  - ${param_value} - for named parameters.
  - ${@param} - for non-prepared statement parameters.
For example: Select * From ${@table} where ${@column} = ${case_sts}

## Slide 5: BW – DB Actors

DbCreateTable - Creates a new database table.
DbDelete - Deletes data from a database using DELETE based on keys
Utilize the DBLoad Actor, as it has a built-in functionality of running INSERT, UPDATE or UPSERT commands (e.g. instead of DBCommand ).
  - Batch capabilities
  - IngloreNull
When selecting one value, take the following into consideration:
  - When selecting one row, use the DbFetchFirstRow Actor instead of the DbCommand Actor.
  - When selecting one value, use the DbFetchField Actor instead of the DbCommand Actor.
Db Actors Best practice:

## Slide 6: BW – Fabric Actors

- Broadway has a category of fabric actors that are useful to execute the Fabric commands.
- FabricGet - Performs the GET command on the current Fabric session.The FabricGet Actor supports fetching multiple instances from different LUs and enables setting parallel and stop_on_error arguments.
- FabricDelete – Delete an instance from Fabric
- FabricSet - Sets a global value on the Fabric session.
- FabricSetRead – Read a global value from the session.

## Slide 7: JavaScript

- FlowArgs
- The FlowArgs can be accessed by any JavaScript Actor, using FlowArgs.ExternalName
- Self
- The 'self' keyword provide access to the Actor state. It is kept between executions of the same actor

## Slide 8: JavaScript

- Actor’s output
- Actor’s output can be referenced by JavaScript code

## Slide 9: Control Flows: Stage Conditions

The Stage Condition splits the flow execution based on a condition
If a Stage condition is true, the actors in the stage will be executed
If a Stage condition is false, the ELSE stage actors will be executed
Stages without Stage Conditions are always executed and don't affect Stages with Conditions.
Fabric does not support two ‘ELSE’ stages on the same stage (do not split 1 stage to multiple if-else conditions)
  - Example 1: If is true
  - Example 1: If is false

**Notes:**

Orchestrate execution logic using powerful, visual actors

## Slide 10: Control Flows: Conditions Actors

Any actor can be a Stage Condition actor – the first output parameter is automatically checked for True/False.The output parameter doesn’t have to be a Boolean. The Broadway will automatically cast its type to Boolean.
Note:
The first Stage in the flow (root) cannot be split.
  - Boolean condition
  - 0=false, != 0: True
  - Empty string =false.Non-empty string = true
  - Inner flow: depends on the first output parameter

## Slide 11: Control Flows: ElseIf Stage Condition (v8.0+)

- ElseIf condition works when multiple Stages  exists at the same level.
- ElseIf logic is defined by adding Stage Condition and marking the same stage as else.

**Notes:**

Orchestrate execution logic using powerful, visual actors

## Slide 12: Control Flows: ElseIf Stage Condition (v8.0+)

The condition evaluation order (top to bottom):
  - Stage with a condition only (no else)
  - Stage with else and condition
  - Else stage ( default fallback)

**Notes:**

Orchestrate execution logic using powerful, visual actors

## Slide 13: Control Flows: Merged Stage

A merged Stage acts like a joining point after a Stage Condition splits a flow
A merged Stage runs only after all its parent Stages are executed.
If none of the parent branches executed (e.g., all Stage conditions are false), the merged Stage will not execute.
  - Condition met – continue with the flow
  - No condition met – break from the flow

**Notes:**

Orchestrate execution logic using powerful, visual actors

## Slide 14: Control Flows: Merged Stage - "Break" Behavior

- If none of the parent branches executed (e.g., all Stage conditions are false), the merged Stage is not executed and the flow will stop.
- If the Merged Stage is inside a loop –  only the loop is skipped

**Notes:**

Orchestrate execution logic using powerful, visual actors

## Slide 15: Control Flows: Condition Branch

  - Split stages appear after the IF stage acts as a "branch" of the IF statement. Only the branch with the met condition will be executed.

**Notes:**

Orchestrate execution logic using powerful, visual actors

## Slide 16: Control Flows: Loops and Iterations

Dynamic – Logic adapts in real-time to input data and conditions
Scalable – Efficiently process large sets, even with nested flows
Visual – intuitive design and debugging.
Iterations (ForEach Style)
  - Repeats logic over a real data set
  - Similar to for-each loops
  - Stops when no more records remain
ForLoop Actor
  - Loops N times over a virtual data set
  - Based on a range of integers
While Actor
  - Executes as long as a condition is true
  - Doesn’t require a data set.
ForEach Actor
  - Traverses each value in a collection or stream
  - Invokes JavaScript and/or Broadway flow for each item
  - Acts as a stream termination point
Flow Types
Key Benefits

## Slide 17: Iterations

What are Iterations for?
  - Automate repetitive logic over data sets using iteration actors
  - Runs logic until all data has been processed
  - Example: Iterate over Database result set, API/HTTP response, Kafka message stream

## Slide 18: ForLoop

What is a ForLoop?
  - Repeat logic a fixed number of times over a synthetic range,  without relying on a data set, running logic once per generated number.
  - Each run receives its iteration number as input.
  - Ideal for fixed-count logic and retries, but can be used with dynamic start/end numbers.

## Slide 19: While

What is a While loop?
  - Repeat actions based on dynamic conditions—powered by JavaScript.
  - Executes as long as the JavaScript script returns a truthy value. Ends once the script returns a false value
  - Ideal for creating logical loops based on conditions: poll until a status changes, retry until success, etc.

## Slide 20: ForEach

What is a ForEach?
  - Iterate over values in a collection and apply custom logic per entry.
  - Each value is passed to logic as the 'value' parameter.
  - Parallel execution is available for faster processing of a large dataset (multithreading doesn’t preserve the collection order during execution).
  - Ideal for triggering downstream logic for each data item.

## Slide 21: Controlling Loop Behaviour

LoopIndex Actor
  - Returns the current iteration index.
  - The index starts at 0.
  - Useful for logic based on the position within the loop.
LoopStop Actor
  - Stops the current loop immediately.
  - Continues flow execution after the loop section/iterate close.
  - Useful for early exits based on a condition.
LoopSkip Actor
  - Skips the current iteration
  - Moves directly to the next iteration in the loop
  - Helpful in ignoring certain steps of the loop conditionally

## Slide 22: String Operations

Broadway provides a wide set of string manipulations utilities.
Key functions include:
  - Transformation (Case changes, Trimming, Padding)
  - Aggregation & Formatting
  - Pattern Matching & Substituion
  - Conversion & Randomization

## Slide 23: Concatenation and Length

Concat
  - Joins an array of strings using a specified delimiter.
  - Example: [‘A’,’B’,’C’] with delimiter , -> A,B,C.
Length
  - Returns the character count of the input string.
  - Example: “Hello” -> 5.

## Slide 24: Case Conversion & Trimming

LowerCase
  - Converts all characters to lowercase.
UpperCase
  - Converts all characters to uppercase.
Trim
  - Removed whitespace from both ends of the string.

## Slide 25: Substring Operations

Substring
  - Retrieves a segment of the input string based on a given start index and an optional end index.
  - If the end index is 0 or beyond the length of the string, it is automatically disregarded
StringPos
  - Returns the 0-based index of a substring within the input string. The search is case-sensitive.
  - If the substring is found, the index is returned and the found flag is set to true; otherwise, -1 is returned and found is false.
  - Supports a fromPosition parameter to define the starting point of the search; negative values initiate a reverse search from the end of the string.
  - The substring can be interpreted as a regular expression by enabling the regex input flag.

## Slide 26: Padding and Replacement

String Padding
  - Extends the input string to a specified length using a provided padding character or string
  - Input: The original string to be padded.
  - Length: The desired total length of the final string
  - Padding: The character or string used to fill in the extra space
  - rightPadding: If set to true, padding is applied to the right; otherwise, it is added to the left.
Replace
  - Substitutes every instance of a specified substring within the input string with a replacement value (the search is case-sensitive).
  - String: The original string to modify.
  - Search : The substring or pattern to look for.
  - Replace: The value that will replace each occurrence of the pattern
  - Regex: Indicates whether the search pattern should be treated as a regular expression

## Slide 27: Split and StringBuild

Split
  - Breaks the input string into an array of substrings using the specified delimiter.
  - String: The string to be split.
  - Delimiter: The character or pattern used to divide the string
  - Regex flag: Specifies whether the delimiter should be interpreted as a regular expression.
  - Limit: The maximum number of elements in the resulting array.  The final element will include any remaining content if the limit is reached. Use 0 for no limit
StringBuild
  - Aggregates strings across multiple loop iterations, joining them with a specified delimiter.
  - Input: The string(s) to append to the cumulative result during each execution.
  - Delimiter: The separator inserted between each string segment.
Limit=2

## Slide 28: StringFormat and ToString

StringFormat
  - Enables dynamic string formatting using the ${} placeholder syntax.
  - Indexed placeholders: Use ${0}, ${1}, etc., to reference values by position
  - Named placeholders: Use ${name} to reference values by argument name
ToString
  - Transforms any input value into its string representation according to Broadway’s conversion rules.

## Slide 29: RegEx and NewLine

RegEx
  - Searches for substrings within the input using regular expression patterns..
  - Supports capturing groups for more precise matching.
NewLine
  - Returns the appropriate newline character(s) based on the selected mode:
    - LF -> n (Unix\Linux)
    - CR LF -> \r\n (Windows)
    - CR -> \r
    - LF CR -> \n\r
    - System Default - > Automatically uses the newline style of the operating system Broadway is running on

## Slide 30: Date Utilities

Broadway flexible handling of date and time in your flows.
  - Perform date arithmetic (add/subtract)
  - Break down or build date objects
  - Calculate durations between dates
  - Access the current timestamp with ease
Use these utilities to manipulate, format, and analyze time-based data efficiently across your implementation

## Slide 31: Date Construction & Decomposition

DateBuild
  - Assemble a date from components.
  - Inputs:
    - Year, Month, Day
    - Hour, Minute, Second, Millisecond
    - Time zone (default: UTC or ‘Local’)
  - Output: A complete Date object representing the specified time
Date Break
  - Decompose a date into its individual components.
  - Input: Date and Time zone (default: UTC or ‘Local’)
  - Outputs:
    - Year, Month, Day
    - Hour, Minute, Second, Millisecond.

## Slide 32: Current Date, Date Arithmetic & Duration

Now
  - Retrieve the current date and time.
  - Output:
    - A date object representing the current moment
    - Timestamp in milliseconds since 1970-01-01 00:00:00 UT
DateAdd
  - Perform arithmetic operations on a base date:
  - Add/Subtract Years, Months, Days, Hours, Minutes, Seconds, Milliseconds
  - Output: Adjusted date
Date Duration
  - Calculate the duration between two dates:
  - Inputs: From Date, To Date
  - Output:
    - Milliseconds (can be negative if reversed)
    - Real number of Days (includes fraction)

## Slide 33: Date Formatting & Parsing

DateFromat
  - Transform a date into a string using a specified format
  - Inputs
    - Date, Format Pattern, Timezone (default: UTC), Locale (e.g., en, fr)
  - Examples:
    - yyyy = Year, MM = Month, dd = Day
    - HH:mm:ss = Time, Z = Timezone Offset
  - Output:
    - A formatted date string.
DateParse
  - Convert a formatted date string back into a date object
  - Inputs
    - Date, Format Pattern, Timezone (default: UTC), Locale (e.g., en, fr)
  - Examples:
    - yyyy = Year, MM = Month, dd = Day
    - HH:mm:ss = Time, Z = Timezone Offset
  - Output:
    - A Date object representing the parsed time.

## Slide 34: Collections – Maps & Arrays

## Slide 35: Maps

- What is a Map?
- Map is a data structure that stores key-value pairs. It allows you to associate a specific key with a value and retrieve the value later using that key.
- Map Types supported by the Map Actors
- LinkedHashMap uses the hash value of the keys to index the map. The fields are sorted by the order of insertion.
- HashMap uses the hash value of the keys to index the map. The fields are sorted by the key's hash value.
- TreeMap uses a tree structure to index the map. The fields are sorted by the key's natural order.
- TreeMapIgnoreCase uses a tree structure to index the map. Strings that are equal under the same case are treated as the same key. Fields are sorted by the key's natural order, ignoring its case.

## Slide 36: Maps

MapCreate ActorReturn a map containing all input arguments. The keys are the names of the input arguments.

## Slide 37: Maps

MapMerge Actor
Merge a list of maps into a single map
duplicate keys : determines how duplicate keys are treated.
  - last - the last value supplied to the key is kept.
  - first - the first value value supplied for each is kept.
  - all - All values for each key are kept. In this case each key will point at a list of values, even if only one value is supplied.
  - allUnique - All unique values for each key are kept. In this case each key will point at a set of values, even if only one value is supplied.
duplicate keys = First
duplicate keys = All

## Slide 38: Maps

- MapDiff Actor
- Given 2 maps - a & b, the actor returns a new map with items in b that are not the same as a (b-a)
- Map a
- Map b
- Diff result ( b – a )

## Slide 39: Arrays

- ArrayBuild Actor
- Joins all inputs into an array.
- The array is aggregated in memory across multiple iterations.
- Result

## Slide 40: Arrays

- ConstStrings Actor
- Create an Array of Strings
- Result

## Slide 41: Arrays

- ArrayConcat Actor
- Flatten all input arrays into a single array.
- The ArrayConcat Actor is useful for combining multiple arrays or streams into one, without the need to copy the data into memory, making it efficient for handling large datasets or real-time streams.
- Result

## Slide 42: Arrays

- ArrayGrouper Actor
- Gets an array and splits it into an array of arrays based on the size parameter.
- This done by traversing the original array without copying its content into memory
- Output
- Input

## Slide 43: Arrays

- ArrayToMap Actor
- Create a map from values arrays and key list
- Output
- Input

## Slide 44: Collection Data Manipulation

- Mapper
- Transform values in a collection using JavaScript and/or Broadway flow logic.
- The Actor maps the data on the fly and does not load the entire data set to memory.
- threadCount parameter: the number of threads to execute the logic. If multiple threads are used, the order of the collection is not maintained.
- Example 1: Using JavaScript, return single value

## Slide 45: Collection Data Manipulation

- Mapper
- Example 2: Using JavaScript, return multiple values, using “value” as the last line in the script.

## Slide 46: Collection Data Manipulation

- Mapper
- Example 3: Using InnerFlow. The external name for both the input and output of the Inner Flow must be “value”.
- bwMapperInnerFlow:Inner flow return map of values.External parameter named “value”

## Slide 47: Collection Data Manipulation

- Filter
- Filter a collection of values based on JavaScript or Flow and return the filtered list..
- The Actor maps the data on the fly and does not load the entire data set to memory.
- threadCount parameter: the number of threads to execute the logic. If multiple threads are used, the order of the collection is not maintained.
- Example 1: Using JavaScript

## Slide 48: Collection Data Manipulation

- Filter
- Example 2: Using InnerFlow. The external name for both the input and output of the Inner Flow must be “value”.
- bwFilterInnerFlow:Inner flow return map of values if condition met.External parameter named “value”

## Slide 49: Collection Data Manipulation

- Peek
- Same functionality as Mapper, without the need to return the “value” from the js/innerFlow. Useful if you want to act on the value or make changes on complex objects without the need to return the value for the next stage.

## Slide 50: Collection Data Manipulation

Sort
Sort an input collection. Unless a specific sort has been requested, the actor will try to sort according to the type.
JavaScript parameter:  compares o1 to o2. The return value should be an integer:
  - If the return value (last line) of the snippet is a positive integer, it means o1 is bigger.
  - A negative integer value means o2 is bigger.
  - 0 means o1 is equal to o2.

## Slide 51: Parser & Stringify

## Slide 52: Json Parsing & Stringifying

JsonParser
Analyzing an input stream and outputting the JSON objects found in the stream.
Inputs:
  - [stream], an input stream represented by an iterable collection of blobs or strings. The parser will keep running until the end of the stream is detected
  - Single: if only a single object is expected in the input stream. In this case, the result will be a single object as opposed to an array.
Output:
  - object: a collection of parsed objects or a single object in case single is set to true

## Slide 53: Json Parsing & Stringifying

JsonStringify
  - Transform Json object into a string format
  - Inputs
    - object: Json object
    - nice: Boolean, If true, will add new lines and indentations to make the JSON output more readable
  - Output: string: The stringified JSON
YamlStringify
  - Transform Yaml object into a string format
  - Inputs
    - object: Yaml object
    - nice: Boolean, If true, will add new lines and indentations to make the Yaml output more readable
  - Output: string: The stringified YAML

## Slide 54: Yaml Parsing & Stringifying

YamlParser
Transform a string of Yaml format into unfoldable Yaml object.
Same input and output as JsonParser
XmlParser
Analyzing an input stream and outputting the objects found in the stream.
Inputs:
  - [stream]: An input stream represented by an iterable collection of blobs or strings.The parser will keep running until the end of the stream is detected
  - single (boolean): Expect only a single XML object in the input stream.In this case, the result will be a single object as opposed to an array.
  - skipRoot (boolean): In case true the actor will skip root element and will return stream of nested elements.
  - valueField: The key given to the XML element value field, used for mapping in output object.If empty, the valueField will not be created and the tag value/text will be created directly within the parent.Some XML structures cannot be successfully parsed without this level (when the XML has both attributes + a value in the same element)
  - attributesField: The key give to the XML element attributes field, used for mapping in output object.If empty, the attributesField will not be created and the attributes will be created directly within the parent.
  - namespaces (boolean): Determines if namespace information is added to the object
Output:
  - object: Return a collection of parsed objects or a single object in case single is set to true
  - info: An object containing additional information. XML document header and/or skipped root element information where applicable

## Slide 55: XML Parsing & Stringifying

XmlStringify
Converts given map to XML document, it supports nested maps and arrays.
Inputs:
  - object: Any map contains any values, nested maps, arrays.XML element attribute should be in the format: {'TAG_NAME#ATTRIBUTE_NAME' : 'VALUE’}.The given map should contain only one root entity.
  - nice (bool): If true, will add new lines and indentations to make the XML output more readable
  - xmlDeclaration (bool): If true, will add XML declaration to the output string
  - valueField: The name of the key that contains the element value.
  - attributesField: The name of the key that contains the attributes values.
Output:
  - xmlString: The stringified XML.

## Slide 56: CSV Parsing & Builder

CsvParser
A CSV parser, analyzing a CSV input stream and outputting an object for each row.If a header line is available, the labels in the header are used to mark the row object.If not, the labels will the order of the column starting at ‘0’
Inputs:
  - [stream]: An input stream represented by an iterable collection of blobs or strings.The parser will keep running until the end of the stream is detected
  - header (boolean): Select if a header row is to be expected.The header will be used to name the fields in the output object
  - delimiter: The column delimiter
  - quote: The quote character used to surround column values that contain a delimiter
  - escape: The escape character, used to escape quote characters in the column value
  - keepSpaces (boolean): If set to true, the parser will not trim the fields and keep their surrounding spaces
  - nullValue: A value that will be treated as null
Output: object: Return a collection of parsed objects.

## Slide 57: CSV Parsing & Builder

CsvBuilder
Build a csv string from map or sequence of maps
Inputs:
  - maps: The input maps or a single map for a single row
  - stream(bool): Determine if the return value is a stream of strings or a single string.Non-streaming mode returns a String with the entire CSV and is best used within a loop so as not to consume too much memory.Streaming mode returns a stream of strings that is backed by the input stream of maps.For backward compatibility reasons the default is false.
  - header (bool): Determinate if the headers row should be added
  - columns: Columns names
  - escape_char: Escaping char that will be used to escape special characters (only if quote_char was not defined)
  - quote_char: Quote char that will be used to enclose fields that contains special characters
  - delimiter: The column delimiter
  - recordDelimiter: The record delimiter. The default is newline (\n).
Example:
  - maps: [{"customerId":"CUST123456","firstName":"John","lastName":"Doe","email":"john.doe@example.com","phone":"+1-555-123-4567","street":"123 Maple Street","city":"Springfield","state":"IL","postalCode":"62704","country":"USA","dateOfBirth":"1985-07-15","registeredAt":"2023-03-22T10:45:00Z","isActive":"true","loyaltyPoints":"240","newsletterSubscribed":"true","preferredLanguage":"en","contactMethod":"email"}]
  - stream: false, header: true, columns: , quote_char, delimiter: “,” , record_delimiter:
Output:
  - csv: the csv result.

## Slide 58: Inner Flows

- Broadway provides the ability to call one flow from another flow, enabling reuse of logic across multiple flows or stages. This is achieved with the following built-in Inner Flow Actors:
- InnerFlowExecutes a specific Broadway flow. Input and output parameters map directly to the inner flow.
- InnerFlowDynamicA flexible version of InnerFlow, where the flow name and input arguments are provided dynamically at runtime. Returns a map of the inner flow’s outputs.
- InnerFlowAsyncRuns a flow asynchronously in a thread pool. The main flow continues immediately and the results from the inner flow will be available only when its completed
- InnerFlowSession Runs a flow asynchronously, either locally (default) or on a random node.

**Notes:**

Need to add slide for the outputs of InnerFlowAsync

## Slide 59: InnerFlowAsync

Executes a sub-flow asynchronously in a separate thread using a thread pool.
Threads:
  - The number of threads is set according to the available CPUs and can be overridden using the threadCount parameter.
  - If no threads are available, the flow will wait on this Actor for the thread to become available.
Async:
  - When called, the actor will return immediately once a working thread becomes available.
  - You can use the InnerFlowJoin component, to wait for all threads to complete.
  - When the flow completes, it will wait for all threads to complete.
Input/output parameters:
  - Actor's Input arguments reflects the External input parameters defined in the inner flow
  - All inner flows' output (non-empty one) is aggregated into [result] array. The [result] will be populated when all executions are finalized
Errors encountered will be counted and written to the log file.

**Notes:**

Need to add slide for the outputs of InnerFlowAsync

## Slide 60: InnerFlowAsync - output

- Call the Inner Flow
- The [result] output of an InnerFlowAsync Actor remains empty until its threads finish execution
- Note: The main flow does not complete until all asynchronous threads have finished.

**Notes:**

Need to add slide for the outputs of InnerFlowAsync

## Slide 61: InnerFlowJoin

- Used to synchronize after asynchronous execution
- Waits for all threads (flows) triggered by InnerFlowAsync to complete.
- To use this actor, hook the 'remaining' output argument to the 'remaining' input argument. Execution proceeds when the count of pending flows reaches zero.
- The [result] output of InnerFlowAsync will contain all inner flows' output (non-empty one) past InnerFlowJoin

## Slide 62: InnerFlowSession

- Executes a sub-flow asynchronously in a separate thread using a thread pool.
- Creates a new session context for each flow execution
- Uses affinity to determine which Fabric node runs the flow
- Example: Both the main flow and all InnerFlowSession instances are writing to and reading from the same thread-global variable. Each session maintains its own value.

**Notes:**

Need to add slide for the outputs of InnerFlowAsync

## Slide 63: Pubsub

## Slide 64: PubSub Configuration

Purpose: Defines the Fabric connection to message providers (e.g., Apache Kafka, JMS) using the PubSub abstraction layer.
Configuration Location: All connection settings are defined in the [default_pubsub] section of config.ini—not within the interface. This allows settings to be centrally managed and applied across various Fabric processes.
Interface Definition: The interface only includes the Config Section parameter, which references the name of the config.ini section containing connection settings. By default, it is set to default_pubsub.
Shared Usage:The [default_pubsub] section is also used by CDC and Common DB processes to connect to Kafka.
Multiple Connection Settings:
  - When different connection settings are required for various processes, you can create additional section(s) in config.ini and add their names to the interface’s Config Section parameter, separated by commas.
  - The first section name from this list that is found in the config.ini will be used.
  - If the section name defined in the interface does not exist in the config.ini, the settings will default to the [default_pubsub] section.
  - The new section does not have to include all the parameters, just those that override the default section's settings.

## Slide 65: PubSub Configuration

[default_pubsub]
TYPE=KAFKA - pubsub type can be one of [MEMORY, KAFKA, NO_OP, ERROR]
  - MEMORY - execute the message handling via an internal queue that runs on localhost. This type can only be used for debug purpose.
  - NO_OP - do not send or receive the messages. This type can only be used for debug purpose.
  - ERROR - simulate throwing an Unsupported Operation exception. This type can only be used for debug purpose.
POLL_TIMEOUT=-1 - The timeout to wait for a new message. If the timeout elapses the collection will come to an end. If set to -1 the wait will be forever.
MAX_POLL_RECORDS – Max number of records that the consumer reads from Kafka in each poll (default: 500). For performance optimization.

## Slide 66: PubSub Configuration

TRANSACTION_MODE - Determines how the publisher handles transactions.Publish Actor
The Publish Actor sends messages to a message bus or topic. It supports different transaction modes that define how and when the message is sent relative to the transaction context:
Async Mode
  - Description:
    - Messages are aggregated in an internal queue and sent asynchronously to Kafka upon commit (or discarded on rollback).
  - Key Points:
    - No Guaranteed Delivery: If any message fails, the remaining messages are still sent.
    - The commit command raises an exception for failed messages. Check the logs for details.
    - Faster than Broker or Ignore modes.
    - Recommended for scenarios where transactions are not critical.
  - Queue Size:
    - Controlled by PUB_QUEUE_LIMIT (a hidden parameter in config.ini).

## Slide 67: PubSub Configuration

  - Broker Mode
  - Description:
    - Fully supports transactions, sending messages directly to Kafka without using an internal queue.
  - Key Points:
    - On commit, the transaction is committed to Kafka.
    - On rollback, the transaction is rolled back in Kafka as well.
    - Transaction size is limited only by Kafka, with no additional restrictions from Fabric.
    - Suitable for scenarios requiring transaction guarantees.
  - Ignore Mode
  - Description:
    - Messages are sent synchronously, one by one, and committed immediately.
  - Key Points:
    - No need to execute commit in Fabric.
    - Transactions are not supported, and each message is handled individually.
    - Recommended for scenarios where transaction handling is unnecessary.

## Slide 68: PubSub Actors

- Broadway Pub/Sub Actors
- Broadway includes built-in queue category Actors for async message handling:
- Publish: Sends messages via a provider.
- Subscribe: Receives messages one by one.
- SubscribeBatch: Receives messages in batches
- SubscribeWithMetadata: Like SubscribeBatch, but includes metadata per message.
- Note: Both Subscribe and SubscribeBatch can read messages in batches—the difference lies in the output:
- Subscribe - Iterates over messages and returns them one by one.
- SubscribeBatch - Returns the entire batch at once, which can then be iterated inside the flow.
- Why it matters:
- Use SubscribeBatch when transactions are needed and a commit should happen at the end of the batch commit not to Kafka, but for another source).
- With Subscribe, since only one message is returned at a time, it is not possible to know where one batch ends and the next begins.

## Slide 69: PubSub Actors

- Supported Providers:
- Apache Kafka
- JMS (e.g., RabbitMQ, ActiveMQ)
- Fabric in-memory queue (Broker memory) – used manly for Dev environments

## Slide 70: Stream

## Slide 71: Streams

FileRead Read data from a file given an interface and path.
  - The file is opened lazily when an actor reads the output stream.
  - Once the file is read completely, it is closed. If the file is not read completely, it is closed at the end of the flow.
Inputs:
  - interface: The interface to use.
  - path: The path to the file, relative to the interface.
Output:
  - stream: The result stream, represented by a collection of blobs.
A stream output can connect to only one input—linking it to multiple targets causes failure, since a stream is readable only once.
Note:

## Slide 72: Streams

- FileWrite
- Write data to a file given an interface and a path.
- The file is opened when the actor is first called with new data
- If the file open happens within a transaction, the file is closed when the transaction is complete.If there is no transaction, the file is closed at the end of the flow.
- When using a transaction in rewrite mode (append=false), if a rollback occurs, the file is deleted.
- Use ioBufferSize to improve the performance; It holds a size of the memory buffer before flushing to the underlying file system. If left empty, uses the system default (8K).
- When using FileWriter, always add ResetActors at the end of the flow to reset the FileWriter(s)- to ensure flush of the file buffer into file.
- Best practice:

## Slide 73: Http

Http Actor Send a request to a web serverSupports streaming payload and result and sending and receiving header  parameters
Inputs:
interface – The HTTP interface to use.
path – Path relative to the interface (e.g. /api/v1/employees)
params – A map of query string parameters (e.g. {“employee_id”:”519”} )
  - Values are encoded automatically; ? and & are added as needed.
  - If a value is an array, the key/value is repeated for each entry.
  - If not a map, the value is appended directly to the URL without escaping.
method – HTTP method; defaults to POST (if a body is provided), otherwise GET.
stream – Request body. If iterable, the entire stream is sent.
headers – Optional HTTP headers. Provided headers are not overridden.
format – Sets Content-Type and Accept headers automatically.
  - If these headers are provided explicitly, they are not overridden.
  - Does not affect serialization/deserialization; use HttpJson Actor for JSON conversion.
exceptionOnError – Defines behavior for error responses:
  - true – Throws an exception for bad status codes.
  - false – Continues execution, returning the status code and error content.
  - Networking errors always throw an exception.
timeout – Request timeout (ms). Throws an exception if exceeded.
noCertificateCheck – Disables certificate verification for HTTPS.
  - Allows connections to servers with self-signed or unknown certificates.
  - ⚠️ Connection is encrypted but not secure (susceptible to MITM attacks). Use only if necessary.
debug – Enables debug logging of URI, method, headers, and parameters.
  - Outputs:
  - stream: The result stream, represented by a collection of blobs.
  - status: The status code returned by the http server.
  - header: A map of header parameters returned in the HTTP response.

## Slide 74: Http

- HttpJson Actor
- Send a request to a web server stringifying the request into JSON and parsing the JSON result. In case of a JSON parsing error, the actor will throw an exception
- HttpForm Actor
- Send a request to a web server emulating form POST submission.
- Where applicable, use the HttpJson Actor instead of the Http Actor.
- The HttpJson Actor sends a request to a web server, stringifying the request into JSON and parsing the JSON result.
- If there is a JSON parsing error, the Actor will throw an exception.
- Best practice:

## Slide 75: Streams

ConstBuffer Converts the input value to a byte array blob.The constant supplied is a string where every two bytes are a hexadecimal value of a single byte.For instance FF10 represents the byte array [255,16].Other characters or characters that are nor paired are ignored so 'FF 10' is the same as 'FF,10!’..
  - Inputs:
    - value: A string representation of a binary value.
  - Example:
    - value :48656C6C6F20576F726C64.
  - Output:
    - value: The input converted to a byte array blob.
StringToBytes
BytesToString

## Slide 76: Streams

Unzip Actor
Unzip data from a zip file.
Inputs:
  - stream: The input stream, represented by a collection of blobs.
  - filter: An optional regex to filter the file names.The regex needs to be present at least once in the full path name of the file.
Example:
  - stream: output of FileRead of a zipped file
  - filer: *.
Output:
  - files: A map containing the file description fields and a 'stream' field containing the file payload.

## Slide 77: Streams

Compress Actor Read data from a stream, compress it and return a stream with the compressed  data.The stream is read lazily when an actor consumes the output stream.
Inputs:
  - format: The compression format to use. Supported formats are GZIP, LZ4, DEFLATE and NONE. If no value is passed, the stream will be repeated with no compression (NONE).
  - stream: The input stream, represented by a collection of blobs. String collections and single strings are automatically converted.
Example:
  - format: GZIP.
  - stream: text in const actor.
Output:
  - stream: The result stream, represented by a collection of blobs.
Uncompress Actor Read compressed data from a stream, uncompress it and return a stream with the uncompressed data.The stream is read lazily when an actor consumes the output stream.
Inputs:
  - format: The compression format to use. Supported formats are GZIP, LZ4, DEFLATE and NONE. If no value is passed, the stream will be repeated with no compression (NONE).
  - stream: The input stream, represented by a collection of blobs. String collections and single strings are automatically converted.
Example:
  - format: GZIP.
  - stream: compressed file content as output from FileRead.
Output:
  - stream: The result stream, represented by a collection of blobs.

## Slide 78: System

## Slide 79: System

Ls Actor List files in a given directory
Inputs:
  - interface: The interface to use.This can be the base URL or a reference to an interface defined in the container.
  - path: The path to use relative to the interface
  - pattern: The file pattern depends on the interface type(SFTP/FTP/S3/Azure/GCS) – see input description for more info.
  - directories (boolean): Show directories in the result
Output:
  - result: The list of files, with their name and size

## Slide 80: System

cp ActorCopy a file.
Inputs:
  - interface: The interface to use.This can be the base URL or a reference to an interface defined in the container.
  - from: Path of the source file
  - to: Path of the destination file
Output:
  - affected: The number of files affected by this action

## Slide 81: System

md ActorCreate a directory.
Inputs:
  - interface: The interface to use.This can be the base URL or a reference to an interface defined in the container.
  - path: The path of the directory to create
Output:
  - affected: Will return 1 if the directory was created, 0 if it already existed.

## Slide 82: System

mv ActorMove a file.
Inputs:
  - interface: The interface to use.This can be the base URL or a reference to an interface defined in the container.
  - from: Path of the source file
  - to: Path of the destination file
Output:
  - affected: The number of files affected by this action

## Slide 83: System

rm ActorRemove a file.
Inputs:
  - interface: The interface to use.This can be the base URL or a reference to an interface defined in the container.
  - path: the path of file or directory to removein case of path of directory, directory should be empty before deleting.
Output:
  - affected: The number of files affected by this action

## Slide 84: System

- Sleep ActorSuspend the execution thread for a given duration
- Input:millis: Duration in milliseconds to sleep

## Slide 85: System

- SysEnv ActorRead an environment variable, either from the java properties or from the system environment.If no variable name is supplied, the actor returns a map of all java and system properties.
- Input:name: The environment variable name to read or empty for all
- Output:value: The variables value or a map of all values
- Example 1: read FABRIC_HOME
- Example 2: read all

## Slide 86: System

Exec ActorExecute a system process and wait for it to complete
Inputs:
  - command: The command to perform
  - params: Array of parameters to pass to the command
  - standard: Selects which standard streams to read.
  - in: The data to send to the standard input of the process before reading the output
Example:
  - command: chmod
  - params: [766, /opt/apps/fabric/workspace/project/newDirectory/]
  - standard: both
  - in:
Outputs:
  - exitValue: The exit value of the process
  - out: The standard output of the process
  - err: The standard error of the process
ExecAsyncExecute a system process and do not wait for it to complete. This mode of execution enables reading the output as a stream.
Example 1: get system variable
Example 2: execute chmod

## Slide 87: System

JMXWrite ActorWrite a custom JMX value into the current process JMX statistics module.
Inputs:
  - key: Select which key to report on.
  - value: The value to report.
JMXRead ActorRead a JMX value from the current process JMX statistics module.
Inputs:
  - stats: Select which statistic metric to read. JMXWrite reports as customStats.
  - key: Select which key within the statistics to read.Leaving the key empty will read an aggregate of all statistics for the stats entry.
Outputs:
  - count : The number of times the given key has been reported.
  - total: The aggregate total of the values passed to the given statistics key.
  - last: The last value reported for this statistic.
  - average: The average value of this statistic, rounded down to the closest integer.
  - timestamp: The timestamp of the last report for this statistic
  - since: The number of milliseconds elapsed since the last report for this statistic
  - totalLast: For an aggregate statistic, when a key is not supplied, this is the total of the 'last' reports of all keys.
The stats in statistic page
Write & read JMX stats
