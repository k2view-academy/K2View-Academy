# Built-In Actor Types

An [Actor](/articles/99_Broadway/03_broadway_actor.md) represents the activity (action) that must be executed on each [Stage]() of the **Broadway flow** to get input parameters and return output parameters. For example, reading a file, creating a table, parsing an object or concatenating a string.  

Broadway has a large built-in list of Actors that can create various types of activities and can be added to each flow. 

To add a built-in Actor to the flow, click anywhere in the **Stage** area to open the **Add Actors to [Stage Name]** window.

![image](/articles/99_Broadway/images/99_04_01_add_actor.PNG)

Broadway's built-in  Actors are split into the following categories, where each category includes several Actor types.

Note that when clicking [Export Actor](<!--add link-->) in the [Actor's context menu](/articles/99_Broadway/18_broadway_flow_window.md#actors-context-menu), a new category (tag) is created if the value that populates the **Tags** field doesn't exist in the Actor's categories.

<table class="unchanged rich-diff-level-one" width="900pxl">
<tbody>
<tr>
<td width="80pxl">
<h3 class="unchanged"><strong>Category</strong></h3>
</td>
<td width="100pxl">
<h3 class="unchanged"><strong>Category Description</strong></h3>
</td>
<td width="720pxl">
<h3 class="unchanged"><strong>Example of Actors per Category</strong></h3>
</td>
</tr>
<tr>
<td style="width: 210px; vertical-align: top;" width="210">
<h4 class="unchanged"><strong>Favorites</strong></h4>
</td>
<td style="width: 433px; vertical-align: top;" width="433">
<p class="unchanged">Most commonly used Actors. Each Actor in the Favorites category also belongs to another category.</p>
</td>
<td width="600">
<ul>
<li><strong>Const</strong> Actor, copies the input value argument to the output value argument. An Actor can do either:
<ul>
<li class="unchanged">Pass a constant value to the next Actor.</li>
<li class="unchanged">Receive its input from the output of a previous Actor and transfer it to the next Actor.</li>
<li class="unchanged">Receive an external flow argument and transfer it to the next Actor.</li>
</ul>
</li>
<li><strong>Concat</strong> Actor, concatenates an array of strings and joins them with the given delimiter.</li>
<li class="unchanged"><strong>JavaScript</strong> Actor, executes the Javascript provided in the 'script' parameter. The script returns the value of the last line.</li>
</ul>
</td>
</tr>
<tr>
<td style="vertical-align: top;" width="210">
<h4 class="unchanged"><strong>basic</strong></h4>
</td>
<td style="vertical-align: top;" width="433">
<p class="unchanged">Actors serving as the basic building blocks for creating the Broadway flow.</p>
</td>
<td width="600">
<ul>
<li><strong>ForLoop</strong> Actor, iterates over a range of numbers.</li>
<li class="unchanged">
<p><strong>Logger</strong> Actor, writes a message to the log file, referencing entries from params and Actor inputs.&nbsp;</p>
</li>
<li class="unchanged">
<p><strong>InnerFlow</strong> Actor, executes a Broadway flow.</p>
</li>
<li class="unchanged"><strong>LuFunction</strong> Actor, executes Studio function logic. Parameters for the function's execution are taken from input arguments or, if not there, from the params input argument.</li>
<li class="unchanged"><strong>Email</strong> Actor, sends an email using a given SMTP interface.</li>
</ul>
</td>
</tr>
<tr>
<td style="vertical-align: top;" width="210">
<h4 class="unchanged"><strong>date/time</strong></h4>
</td>
<td style="vertical-align: top;" width="433">
<p class="unchanged">Various date and time manipulation functions, such as DateAdd, DateFormat or Now.</p>
</td>
<td width="600">
<p class="unchanged"><strong>DateFormat</strong> Actor, formats a date into a string.</p>
<ul>
<li class="unchanged">Input values: Date, Format the output string following a predefined pattern and Time Zone.</li>
<li class="unchanged">Initial format:<strong>yyyy-MM-dd HH:mm:ss.SSS</strong>.</li>
<li class="unchanged">Initial value of the time zone:<strong>UTC</strong>.</li>
<li class="unchanged">Output: a string.</li>
</ul>
<p style="padding-left: 30px;">For example:</p>
<ul>
<li class="unchanged">To receive a day of the year in the output, add&nbsp;<strong>D</strong>&nbsp;to the format.</li>
<li class="unchanged">To receive a day of the week, add&nbsp;<strong>E</strong>&nbsp;to the format.&nbsp;</li>
</ul>
</td>
</tr>
<tr>
<td style="vertical-align: top;" width="210">
<h4 class="unchanged"><strong>db</strong></h4>
</td>
<td style="vertical-align: top;" width="433">Actions to be performed on a DB interface, such as creating a new table, loading data or executing a DB command.&nbsp;&nbsp;</td>
<td style="width: 600px; vertical-align: top;" width="600">
<p class="unchanged"><strong>DbCommand</strong> Actor, performs database commands on a DB command interface.&nbsp;</p>
<p class="unchanged">The interface used as input can be:</p>
<ul>
<li class="unchanged">A JDBC URL.</li>
<li class="unchanged">Reference to a predefined interface.</li>
<li class="unchanged">Others, like the Schema, table name, fields definition, SQL dialect to use and append text appended to the CREATE command.</li>
</ul>
<p class="unchanged">The DbCommand can be used to create the WITH section where required.&nbsp;</p>
</td>
</tr>
<tr>
<td style="width: 210px; vertical-align: top;" width="210">
<h4 class="unchanged"><strong>logic</strong></h4>
</td>
<td style="width: 433px; vertical-align: top;" width="433">Logical operation on Actors&nbsp;<strong>A</strong>&nbsp;and&nbsp;<strong>B</strong>&nbsp;which returns a&nbsp;<strong>True or False</strong>&nbsp;boolean result.
<p class="unchanged">Broadway converts the following types of input parameters to booleans:</p>
<ul class="unchanged">
<li class="unchanged">Null/no input, False.</li>
<li class="unchanged">Integer/double, True if not 0.</li>
<li class="unchanged">String, True if not empty/0/False.</li>
<li class="unchanged">Array/Map, True if not empty.</li>
</ul>
</td>
<td style="width: 600px; vertical-align: top;" width="600">
<p class="unchanged"><strong>And</strong> Actor, returns&nbsp;<strong>True</strong>&nbsp;if and only&nbsp;<strong>if both A and B&nbsp;</strong>are&nbsp;<strong>True</strong>. Both A and B must be boolean values or a value that can be converted to a boolean.&nbsp;&nbsp;</p>
<ul>
<li class="unchanged"><strong>Elvis</strong> Actor, returns&nbsp;<strong>A</strong>&nbsp;if converted to boolean is&nbsp;<strong>True</strong>. Otherwise it returns&nbsp;<strong>B</strong>.
<p>&nbsp;</p>
</li>
<li class="unchanged"><strong>IfElse</strong> Actor, includes the&nbsp;<strong>test</strong>&nbsp;input to be validated as either True or False.&nbsp;<strong>If test is True</strong>, return&nbsp;<strong>A</strong>, else return&nbsp;<strong>B</strong>.&nbsp;</li>
</ul>
</td>
</tr>
<tr>
<td style="vertical-align: top;" width="210">
<h4 class="unchanged"><strong>math</strong></h4>
</td>
<td style="vertical-align: top;" width="433">Various mathematical functions, such as MathMax, MathMin, Aggregate.</td>
<td width="600">
<ul>
<li><strong>Aggregate</strong> Actor, aggregates values.&nbsp;It receives a number or collection of numbers and calculates the sum, count, average, min and max values of this collection. This actor maintains its state across multiple loop iterations.</li>
<li class="unchanged"><strong>MathDivMod</strong> Actor, returns the divisor and modulo factor of&nbsp;<strong>A</strong>&nbsp;and&nbsp;<strong>B</strong>. <br />For example, if A=10 and B=3 then div=3 and mod=1.</li>
</ul>
</td>
</tr>
<tr>
<td style="width: 210px; vertical-align: top;" width="210">
<h4 class="unchanged"><strong>parsers</strong></h4>
</td>
<td width="433">Various parsers which can be received as input stream in different kinds of formats, for example, CSV, JSON or XML.</td>
<td width="600">
<p><strong>XmlParser</strong> Actor, receives an input stream represented via an iterable collection of blobs or strings.&nbsp;</p>
<p>The parser runs until the end of the stream is detected. It returns a collection of parsed objects or a single object if Single is set to True.&nbsp;</p>
</td>
</tr>
<tr>
<td style="vertical-align: top;" width="210">
<h4 class="unchanged"><strong>queue</strong></h4>
</td>
<td style="vertical-align: top;" width="433">Publish / subscribe messages to the queue.</td>
<td width="600">
<p class="unchanged"><strong>Publish</strong> Actor, publlishes messages using a message broker.&nbsp;</p>
<p class="unchanged">The inputs are:</p>
<ul>
<li class="unchanged">Broker interface to use.</li>
<li class="unchanged">Topic to publish to.</li>
<li class="unchanged">Message.</li>
</ul>
</td>
</tr>
<tr>
<td style="vertical-align: top;" width="210">
<h4 class="unchanged"><strong>streams</strong></h4>
</td>
<td style="vertical-align: top;" width="433">Various stream manipulation functions, such as Compress, FileRead or Http.</td>
<td width="600">
<ul>
<li><strong>FileRead</strong>&nbsp;Actor, reads data from a file given an interface and path. The file is opened lazily when an Actor reads the output stream. Once the file has been fully read, it is closed. If the file is not fully read, it is closed at the end of the flow.</li>
<li class="unchanged">
<p><strong>Http</strong>&nbsp;Actor, sends a request to a web server.&nbsp;Supports streaming payload and results and sending and receiving header parameters.&nbsp;</p>
</li>
</ul>
</td>
</tr>
<tr>
<td style="vertical-align: top;" width="210">
<h4 class="unchanged"><strong>strings</strong></h4>
</td>
<td style="vertical-align: top;" width="433">
<p class="unchanged">Various string manipulation functions, such as Concat, Split or Trim.</p>
<p class="unchanged">Graphit and JsonStringify Actors are also included in this category.</p>
</td>
<td width="600">
<ul>
<li><strong>Regex</strong>&nbsp;Actor, finds sub-strings in an input string using a regular expression. The actor tries to find all matches of the pattern within the input string and return them.&nbsp;When using matching groups, the result is the content of the matching group instead of the full match. For example, in the 'ABCDEF'string, the 'C.E' pattern returns ['CDE'], whereas 'C(.)E' returns ['D'].&nbsp;</li>
</ul>
<ul>
<li class="unchanged"><strong>Graphit</strong> Actor, executes Graphit logic for data serialization. Parameters for the Graphit execution are taken from input arguments or, if not there, from the params input argument. The Actor first looks at the input parameters (first level) and, if not found there, looks at the params input argument.
<p class="unchanged">The inputs are:</p>
<ul>
<li class="unchanged">LU containing the Graphit file (the initial value is k2_ws).</li>
<li class="unchanged">Graphit filename.</li>
<li class="unchanged">Required output format (inital value = JSON).</li>
<li class="unchanged">Parameters for Graphit execution.</li>
</ul>
</li>
</ul>
</td>
</tr>
<tr>
<td style="vertical-align: top;" width="210">
<h4 class="unchanged" style="padding-left: 30px;"><strong>system</strong></h4>
</td>
<td style="vertical-align: top;" width="433">System processes and commands to be performed in the file system. For example: Sych as copy, List or Remove.&nbsp;</td>
<td width="600">
<p class="unchanged"><strong>cp</strong> Actor, copies a file. The interface used as input&nbsp;can be:</p>
<ul>
<li class="unchanged">JDBC URL.</li>
<li class="unchanged">Reference to a predefined interface.</li>
<li class="unchanged">Path of the&nbsp;<strong>source</strong>&nbsp;file (from).</li>
<li class="unchanged"><strong>Destination</strong>&nbsp;(to).</li>
</ul>
<p class="unchanged">The output is a number of affected files.&nbsp;</p>
</td>
</tr>
</tbody>
</table>





