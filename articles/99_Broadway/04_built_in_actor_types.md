# Built-In Actor Types

An [Actor](/articles/99_Broadway/03_broadway_actor.md) represents the activity (action) that must be executed on each [Stage]() of the **Broadway flow** to get input parameters and return output parameters. For example, reading a file, creating a table, parsing an object, concatenating a string.  

Broadway has a large list of built-in Actors that can create various types of activities and can be added to each flow. To add a built-in Actor to the flow, click anywhere on the Stage area and a pop-up window will open.

![image](/articles/99_Broadway/images/99_04_01_add_actor.PNG)

The Broadway built-in Actors are split into the following categories (tags), while each category includes several Actor types.
Note that when doing [Export Actor](<!--add link-->) from the [Actor's context menu](/articles/99_Broadway/18_broadway_flow_window.md#actors-context-menu), a new category (tag) will be created - if the value which you populate in the Tag field doesn't exist among the Actor's categories.

<table width="900pxl">
<tbody>
<tr>
<td width="80pxl">
<h3><strong>Category</strong></h3>
</td>
<td width="100pxl">
<h3><strong>Category Description</strong></h3>
</td>
<td width="720pxl">
<h3><strong>Example of Actors per Category</strong></h3>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>Favorites</strong></h4>
</td>
<td width="433">
<p>Most commonly used Actors. Each Actor in the category Favorites also belongs to one of other categories.</p>
</td>
<td width="600">
<p><strong>Const</strong> Actor - the input value argument is copied to the output value argument.</p>
<ul>
<li>A constant value.</li>
<li>Receives its input from the output of a previous Actor and passes it to the next Actor.</li>
<li>Receives an external flow argument and passes it to the next Actor.</li>
</ul>
<p><strong>Concat</strong> Actor concatenates an array of strings and join them with the given delimiter.</p>
<p><strong>JavaScript</strong> Actor&nbsp;executes the Javascript provided in the 'script' parameter.&nbsp;The script returns the value of the last line.</p>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>basic</strong></h4>
</td>
<td width="433">
<p>The Actors which serve as the basic building blocks for Broadway flow creation.</p>
</td>
<td width="600">
<p><strong>ForLoop</strong> Actor iterates over a range of numbers.</p>
<p><strong>Logger</strong> Actor&nbsp;writes a message to the log file, referencing entries from the params and Actor inputs.</p>
<p><strong>InnerFlow</strong> Actor executes a Broadway flow.</p>
<p><strong>LuFunction</strong> Actor&nbsp;executes Studio function logic. Parameters to the function execution are picked up from input arguments or, if not there, from the params input argument.</p>
<p><strong>Email</strong> Actor&nbsp;sends an email using a given SMTP interface.</p>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>date/time</strong></h4>
</td>
<td width="433">
<p>Various date and time manipulation functions, such as DateAdd, DateFormat, Now.</p>
</td>
<td width="600">
<p><strong>DateFormat</strong> Actor&nbsp;formats a date into a string.&nbsp;</p>
<p>The Inputs are: the date, the format of the output string following pre-defined pattern and a time zone. The initial format is&nbsp;<strong>yyyy-MM-dd HH:mm:ss.SSS</strong> and the initial value of the time zone is <strong>UTC</strong>. The output is a string.</p>
<p>For example, to receive a day of the year in the output, add <strong>D</strong> to the format. To receive&nbsp;&nbsp;a day of the week, add <strong>E</strong>.</p>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>db</strong></h4>
</td>
<td width="433">Actions to be performed on a DB Interface, such as creation of a new table, load of data or execution of a DB command.&nbsp;</td>
<td width="600">
<p><strong>DbCommand</strong> Actor&nbsp;performs database commands against a DB command interface.</p>
<p>The interface to use as an Input&nbsp;can be a JDBC URL or a reference to a pre-defined interface. Other inputs include the schema, table name, fields definition,&nbsp;sql dialect to use and append -&nbsp;text to be appended to the create command. Can be used to create the WITH section where required.</p>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>logic</strong></h4>
</td>
<td width="433">Logical operation on two Actors&nbsp;<strong>a</strong> and <strong>b</strong> which returns a boolean result&nbsp;- <strong>true or false</strong>.&nbsp;
<p>Broadway will convert the following types of Input parameters to booleans:</p>
<ul>
<li>Null/no input - false</li>
<li>Integer/double - true if not 0</li>
<li>String - true if not empty/0/false</li>
<li>Array/Map - true if not empty</li>
</ul>
</td>
<td width="600">
<p><strong>And </strong>Actor returns <strong>true</strong> if and only <strong>if both a and b </strong>are <strong>true</strong>. Both a and b&nbsp;should be boolean values or a value that can be converted to boolean.&nbsp;</p>
<p><strong>Elvis </strong>Actor returns&nbsp;<strong>a</strong> if a converted to boolean is <strong>true</strong>. Otherwise it returns <strong>b</strong>.</p>
<p><strong>IfElse</strong> Actor includes the <strong>test</strong> input to be validates as either true or false. <strong>If test is true</strong>, return <strong>a</strong>, else return <strong>b</strong>.</p>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>math</strong></h4>
</td>
<td width="433">Various mathematical functions, such as MathMax, MathMin, Aggregate.</td>
<td width="600">
<p><strong>Aggregate</strong> Actor aggregates values.&nbsp;It receives a number or collection of numbers and calculates the sum, count, average, min and max values of this collection. This actor maintains its state across multiple loop iterations.&nbsp;</p>
<p><strong>MathDivMod</strong> Actor returns the divisor and modulo factor of <strong>a</strong> and <strong>b</strong>. For example, if a=10 and b=3 then div=3 and mod=1.</p>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>parsers</strong></h4>
</td>
<td width="433">Various parsers which can receive as Input stream in different kinds of formats, for example, CSV, Jason or XML.</td>
<td width="600"><strong>XmlParser</strong> Actor receives an input stream represented by an iterable collection of blobs or strings.&nbsp;The parser is running until the end of the stream is detected. It returns a collection of parsed objects or a single object in case single is set to true.&nbsp;</td>
</tr>
<tr>
<td width="210">
<h4><strong>queue</strong></h4>
</td>
<td width="433">Publish / Subscribe messages to the queue</td>
<td width="600">
<p><strong>Publish&nbsp;</strong>Actor publishes messages using a message broker.&nbsp;</p>
<p>The inputs are: the broker interface to use, the topic to publish to and the message.&nbsp;</p>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>streams</strong></h4>
</td>
<td width="433">Various streams manipulation functions, such as Compress, FileRead, Http.</td>
<td width="600">
<p><strong>FileRead</strong> Actor reads data from a file given an interface and the path. The file is opened lazily when an Actor reads the output stream. Once the file is read completely, it is closed. If the file is not read completely, it is closed at the end of the flow.</p>
<p><strong>Http</strong> Actor sends a request to a web server.&nbsp;Supports streaming payload and result and sending and receiving header parameters.</p>
</td>
</tr>
<tr>
<td width="210">
<h4><strong>strings</strong></h4>
</td>
<td width="433">
<p>Various strings manipulation functions, such as Concat, Split, Trim.</p>
<p>Graphit and JsonStringify Actors are also included in this category.</p>
</td>
<td width="600">
<p><strong>Regex</strong> Actor finds sub strings within an input string using a regular expression. The actor will try and find all matches of the pattern within the input string and return them.&nbsp;When using matching groups, the result will be the content of the matching group instead of the whole match.</p>
<p>For example, in the string 'ABCDEF' the pattern 'C.E' will return ['CDE'], where as 'C(.)E' will return ['D'].</p>
<p><strong>Graphit</strong> Actor executes Graphit logic for data serialization. Parameters to the graphit execution are picked up from input arguments or, if not there, from the params input argument.</p>
<p>The inputs are: the&nbsp;Logical Unit containing the Graphit file (the initial value is k2_ws),&nbsp;the Graphit file name,&nbsp; the required output format (the initla value is JSON) and the parameters for the Graphit execution. The Actor looks first at the input parameters (first level) and, if not found there, looks at the params input argument.</p>
</td>
 <tr>
<td width="210">
<h4><strong>system</strong></h4>
</td>
<td width="433">System processes and commands to be performed in the file system, sych as copy, list or remove.&nbsp;</td>
<td width="600">
<p><strong>cp</strong> Actor copies a file.</p>
<p>The interface to use as an Input&nbsp;can be a JDBC URL or a reference to a pre-defined interface. Other inputs include the path of the <strong>source</strong> file (from) and the <strong>destination</strong> (to). The output is a number of affected files.&nbsp;</p>
</td>
</tr>
</tr>
</tbody>
</table>
