# Built-In Actor Types

Broadway has a large list of built-in [Actors](03_broadway_actor.md#actor-overview) that can be added to a flow to create various types of activities. Broadway's built-in Actors are split into categories, where each category includes several Actor types.

The following table presents a list of Actors categories with examples of each category. This is not an exhaustive list of Actors.

[Click for more information about the most useful Actors and code examples](actors/README.md).

<table>
<tbody>
<tr style="height: 54px;">
<td style="height: 54px; width: 146.925px;">
<h3><strong>Category</strong></h3>
</td>
<td style="height: 54px; width: 292.913px;">
<h3><a id="user-content-category-description" class="anchor" href="04_built_in_actor_types.md#category-description" aria-hidden="true"></a><strong>Category Description</strong></h3>
</td>
<td style="height: 54px; width: 439.763px;">
<h3><a id="user-content-example-of-actors-per-category" class="anchor" href="04_built_in_actor_types.md#example-of-actors-per-category" aria-hidden="true"></a><strong>Examples per Category</strong></h3>
</td>
</tr>
<tr style="height: 242px;">
<td style="vertical-align: top; height: 242px; width: 146.925px;">
<h4><a id="user-content-favorites" class="anchor" href="04_built_in_actor_types.md#favorites" aria-hidden="true"></a><strong>Favorites</strong></h4>
</td>
<td style="vertical-align: top; height: 242px; width: 292.913px;">
<p>Most commonly used Actors.</p>
<p>Favorites is not a separate category. Each Actor in Favorites belongs to a category.</p>
<p>Click <img src="images/99_favorites.PNG" alt="lev" /> in an Actor's description in the Add Actors window to add the Actor to Favorites.</p>
<p>Actors in Favorites have a <img src="images/99_favorites1.PNG" alt="lev" />.</p>
</td>
<td style="vertical-align: top; height: 242px; width: 439.763px;">
<p><strong>Const</strong> Actor, copies the input value argument to the output value argument. A Const Actor can:</p>
<ul>
<li>Pass a constant value to the next Actor.</li>
<li>Receive its input from the output of a previous Actor and transfer it to the next Actor.</li>
<li>Receive an external flow argument and transfer it to the next Actor.</li>
</ul>
<strong>Concat</strong> Actor, concatenates an array of strings and joins them with the given delimiter. <strong><br /></strong><strong>JavaScript</strong> Actor, executes the JavaScript provided in the script parameter. The script returns the value of the last line.</td>
</tr>
<tr style="height: 247px;">
<td style="vertical-align: top; height: 247px; width: 146.925px;">
<h4><a id="user-content-basic" class="anchor" href="04_built_in_actor_types.md#basic" aria-hidden="true"></a><strong>basic</strong></h4>
</td>
<td style="vertical-align: top; height: 247px; width: 292.913px;">
<p>Actors serve as the basic building blocks for creating a Broadway flow.</p>
</td>
<td style="vertical-align: top; height: 247px; width: 439.763px;">
<p><strong>ForLoop</strong> Actor, iterates over a range of numbers.</p>
<p><strong>Logger</strong> Actor, writes a message to the log file referencing entries from params and Actor inputs.</p>
<p><strong>InnerFlow</strong> Actor, executes a Broadway flow.</p>
<p><strong>LuFunction</strong> Actor, executes Studio function logic. Parameters for the function's execution are taken from input arguments or from the params input argument.</p>
<p><strong>Email</strong> Actor, sends an email using SMTP interface. Supports email body in HTML format and can send attachments.</p>
</td>
</tr>
<tr style="height: 82px;">
<td style="vertical-align: top; height: 82px; width: 146.925px;">
<h4><strong>data</strong></h4>
</td>
<td style="vertical-align: top; height: 82px; width: 292.913px;">
<p>Various data manipulations, such as accumulate values into a map, build an array or sort the input collection.</p>
</td>
<td style="vertical-align: top; height: 82px; width: 439.763px;">
<p><strong>MapBuild</strong> Actor, accumulates key/value pairs into a map. Duplicate keys are handled according to the duplicateKeys mode field.</p>
</td>
</tr>
<tr style="height: 270px;">
<td style="vertical-align: top; height: 270px; width: 146.925px;">
<h4><a id="user-content-datetime" class="anchor" href="04_built_in_actor_types.md#datetime" aria-hidden="true"></a><strong>date/time</strong></h4>
</td>
<td style="vertical-align: top; height: 270px; width: 292.913px;">
<p>Various Date and Time manipulation functions, such as DateAdd, DateFormat or Now.</p>
</td>
<td style="height: 270px; width: 439.763px;">
<p><strong>DateFormat</strong> Actor, formats a date into a string.</p>
<ul>
<li>Input values: Date, Output Format (following a predefined pattern) and Time Zone.</li>
<li>Initial format: yyyy-MM-dd HH:mm:ss.SSS.</li>
<li>Initial value of the time zone: UTC.</li>
<li>Output: a string.</li>
</ul>
<p>For example:</p>
<ul>
<li>To display a day of a year in the output, add <strong>D</strong> to the format.</li>
<li>To display a day of the week in the output, add<strong> E</strong> to the format.</li>
</ul>
</td>
</tr>
<tr style="height: 411px;">
<td style="vertical-align: top; height: 411px; width: 146.925px;">
<h4><a id="user-content-db" class="anchor" href="04_built_in_actor_types.md#db" aria-hidden="true"></a><strong>db</strong></h4>
</td>
<td style="vertical-align: top; height: 411px; width: 292.913px;">Actions to be performed on a DB interface, such as creating a new table, loading data or executing a DB command.</td>
<td style="width: 439.763px; vertical-align: top; height: 411px;">
<p><strong>DbCommand</strong> Actor, performs database commands on a DB command interface. The interface used as input can be:</p>
<ul>
<li>A JDBC URL.</li>
<li>Reference to a predefined interface.</li>
<li>Others, like the schema, table name, fields definition, SQL dialect to use and append text appended to the CREATE command.</li>
</ul>
<p>The DbCommand can be used to create the WITH section where required.</p>
<p><strong>DbLoad</strong> Actor, loads data into a database using INSERT, UPDATE or UPSERT (if supported). The interface used as input can be the same as for <strong>DbCommand</strong> Actor.</p>
<ul>
<li>The Actor supports named parameters with the parameter name inside ${}. Values are taken from the Actor's input parameters.</li>
<li>In an INSERT command, the Actor also supports ordered parameters using the <strong>?</strong> as a replacement for the parameter.</li>
</ul>
</td>
</tr>
<tr style="height: 165px;">
<td style="vertical-align: top; height: 165px; width: 146.925px;">
<h4><strong>fabric</strong></h4>
</td>
<td style="vertical-align: top; height: 165px; width: 292.913px;">Actors which execute the Fabric commands.</td>
<td style="width: 439.763px; vertical-align: top; height: 165px;">
<p><strong><span class="md-pair-s md-expand"><span class="md-plain">FabricGet</span></span></strong><span class="md-plain md-expand"> Actor performs the GET command on the current Fabric session.</span></p>
<p><span class="md-plain md-expand"><strong>FabricSet</strong> Actor sets a value on the Fabric session.</span></p>
<p><span class="md-plain md-expand"><strong>BatchWait</strong> Actor waits for a batch process to complete or throw an error if the batch does not complete successfully or does not complete within the predefined time.</span></p>
</td>
</tr>
<tr style="height: 133px;">
<td style="vertical-align: top; height: 133px; width: 146.925px;">
<h4><a id="user-content-images" class="anchor" href="04_built_in_actor_types.md#images" aria-hidden="true"></a><strong>images</strong></h4>
</td>
<td style="vertical-align: top; height: 133px; width: 292.913px;">Image manipulation Actors that allow to perform various activities, such as:
<ul>
<li>Load an image into a flow.</li>
<li>Write text on a given image.</li>
<li>Clone an image in memory.</li>
</ul>
</td>
<td style="width: 439.763px; vertical-align: top; height: 133px;">
<p><strong>ImageLoad</strong> Actor, receives a buffer or buffer stream, and loads the image into memory.</p>
<p><strong>ImageSave</strong> Actor, encodes the image to a byte buffer with the given format. This can then be transported or saved using DbLoad, FileWrite, Http or other Actors.</p>
</td>
</tr>
<tr style="height: 55px;">
<td style="vertical-align: top; height: 55px; width: 146.925px;">
<h4><strong>jobs</strong></h4>
</td>
<td style="vertical-align: top; height: 55px; width: 292.913px;">Actors that creates Fabric jobs. These Actors are introduced in V6.5.3.</td>
<td style="width: 439.763px; vertical-align: top; height: 55px;">
<p><strong>BroadwayJob</strong>, Actor that creates a Broadway job</p>
<p><strong>InterfaceListener</strong>, Actor that creates an Interface Listener job.</p>
</td>
</tr>
<tr style="height: 205px;">
<td style="vertical-align: top; height: 205px; width: 146.925px;">
<h4><a id="user-content-logic" class="anchor" href="04_built_in_actor_types.md#logic" aria-hidden="true"></a><strong>logic</strong></h4>
</td>
<td style="vertical-align: top; height: 205px; width: 292.913px;">Logical operation on Actors<strong> A</strong> and <strong>B</strong> which returns a <strong>True</strong> or <strong>False</strong> boolean result.
<p>Broadway converts the following types of input parameters to booleans:</p>
<ul>
<li>Null/no input, False.</li>
<li>Integer/double, True if not 0.</li>
<li>String, True if not empty/0/False.</li>
<li>Array/Map, True if not empty.</li>
</ul>
</td>
<td style="width: 439.763px; vertical-align: top; height: 205px;">
<p><strong>And</strong> Actor, returns&nbsp;True&nbsp;if and only&nbsp;if both A and B&nbsp;are&nbsp;True. Both A and B must be boolean values or a value that can be converted to a boolean.&nbsp;&nbsp;</p>
<p><strong>Elvis</strong> Actor, returns&nbsp;A&nbsp;if converted to boolean is&nbsp;True. Otherwise returns B.</p>
<p><strong>IfElse</strong> Actor, includes the&nbsp;test&nbsp;input to be validated as either True or False.&nbsp;If test is True, return&nbsp;A, else return&nbsp;B.&nbsp;</p>
</td>
</tr>
<tr style="height: 165px;">
<td style="vertical-align: top; height: 165px; width: 146.925px;">
<h4><strong>masking</strong></h4>
</td>
<td style="vertical-align: top; height: 165px; width: 292.913px;">A group of Actors to mask sensitive information, such as SSN, credit card number, email. It also includes the Actors that can define a sequence.</td>
<td style="width: 439.763px; vertical-align: top; height: 165px;">
<p><strong>MaskingSSN</strong> Actor,&nbsp;masks the original SSN number with a valid fake SSN.</p>
<p><strong>MaskingCreditCard</strong> Actor,&nbsp;generates a fake but valid credit card number similar to the original card's type.</p>
<p><strong>MaskingSequence</strong> Actor, &nbsp;implements a unique sequence number.</p>
</td>
</tr>
<tr style="height: 169px;">
<td style="width: 146.925px; vertical-align: top; height: 169px;">
<h4><a id="user-content-math" class="anchor" href="04_built_in_actor_types.md#math" aria-hidden="true"></a><strong>math</strong></h4>
</td>
<td style="width: 292.913px; vertical-align: top; height: 169px;">Various mathematical functions, such as MathMax, MathMin, Aggregate.</td>
<td style="height: 169px; width: 439.763px;">
<p><strong>Aggregate</strong> Actor, aggregates values.&nbsp;It receives a number or collection of numbers and calculates the Sum, Count, Average, Min and Max values of this collection. This Actor maintains its state across multiple loop iterations.&nbsp;&nbsp;</p>
<p><strong>MathDivMod</strong> Actor, returns the divisor and modulo factor of&nbsp;A&nbsp;and&nbsp;B.&nbsp;For example, if A=10 and B=3 then div=3 and mod=1.&nbsp;</p>
</td>
</tr>
<tr style="height: 100px;">
<td style="height: 100px; width: 146.925px;">
<h4><a id="user-content-parsers" class="anchor" href="04_built_in_actor_types.md#parsers" aria-hidden="true"></a><strong>parsers</strong></h4>
</td>
<td style="height: 100px; width: 292.913px;">Various parsers which can be received as input stream in different types of formats, for example, CSV, JSON or XML.</td>
<td style="height: 100px; width: 439.763px;">
<p><strong>XmlParser</strong> Actor, receives an input stream represented via an iterable collection of blobs or strings. The parser runs until the end of the stream is detected. It returns a collection of parsed objects or a single object if Single is set to True.</p>
</td>
</tr>
<tr style="height: 133px;">
<td style="height: 133px; width: 146.925px;">
<h4><a id="user-content-queue" class="anchor" href="04_built_in_actor_types.md#queue" aria-hidden="true"></a><strong>queue</strong></h4>
</td>
<td style="height: 133px; width: 292.913px;">Publish / subscribe messages to the queue.</td>
<td style="width: 439.763px; vertical-align: top; height: 133px;">
<p><strong>Publish</strong> Actor, publishes messages using a message broker.&nbsp;The inputs are:</p>
<ul>
<li>Broker interface to use.</li>
<li>Topic to publish to.</li>
<li>Message.&nbsp;</li>
</ul>
</td>
</tr>
<tr style="height: 284px;">
<td style="height: 284px; width: 146.925px;">
<h4><strong>stats</strong></h4>
</td>
<td style="height: 284px; width: 292.913px;">Actors that gather the flow statistics.</td>
<td style="width: 439.763px; vertical-align: top; height: 284px;">
<p><strong>StatsWriter</strong> Actor, sets or increments statistics that can then be read by the StatsReader.</p>
<p><strong>StatsReader</strong> Actor, reads statistics written by other Actors such as StatsWriter, DbCommand or ErrorHandler.</p>
<p>The Actor has two outputs:</p>
<ul>
<li>Flow, returns the flow level statistics map.</li>
<li>Global, returns the global/session level statistics map.</li>
</ul>
<p>For example, if <strong>StatsReader</strong> is added to the flow after the <strong>DbCommand</strong> Actor that executes an INSERT query, the <strong>StatsReader</strong> displays the number of executed and the number of affected records.</p>
</td>
</tr>
<tr style="height: 292px;">
<td style="height: 292px; width: 146.925px;">
<h4><a id="user-content-streams" class="anchor" href="04_built_in_actor_types.md#streams" aria-hidden="true"></a><strong>streams</strong></h4>
</td>
<td style="height: 292px; width: 292.913px;">Various stream manipulation functions, such as Compress, FileRead or Http.</td>
<td style="height: 292px; width: 439.763px;">
<p><strong>FileRead</strong> Actor, reads data from a file given in an interface and path. The file is opened lazily when an Actor reads the output stream. Once the file has been fully read, it is closed. If the file is not fully read, it is closed at the end of the flow.</p>
<p><strong>Http/HttpJson</strong> Actors, send a request to a Web Server. Support streaming payload and results, sending and receiving header parameters. Using this Actor you can call a Web Service and parse its results.</p>
<p><strong>StringsToBytes</strong> Actor, reads an iterable of strings (or string representation) and converts them to byte arrays using the given character set (for example, UTF-8). The <strong>byteOrderMark</strong> input argument should be set to true if you need the BOM to be written at the beginning of the buffer, according to the given character set.</p>
</td>
</tr>
<tr style="height: 379px;">
<td style="vertical-align: top; height: 379px; width: 146.925px;">
<h4><a id="user-content-strings" class="anchor" href="04_built_in_actor_types.md#strings" aria-hidden="true"></a><strong>strings</strong></h4>
</td>
<td style="vertical-align: top; height: 379px; width: 292.913px;">
<p>Various string manipulation functions, such as Concat, Split or Trim.</p>
<p>Graphit and JsonStringify Actors are also included in this category.</p>
</td>
<td style="height: 379px; width: 439.763px;">
<p><strong>Regex</strong> Actor, finds sub-strings in an input string using a regular expression. The Actor tries to find all matches of the pattern within the input string and return them. When using matching groups, the result is the content of the matching group instead of the full match. For example, in the ABCDEF string, the C.E pattern returns [CDE], whereas C(.)E returns [D].</p>
<p><strong>Graphit</strong> Actor, executes Graphit logic for data serialization. Parameters for the Graphit execution are taken from input arguments or from the params input argument. The inputs are:</p>
<ul>
<li>LU containing the Graphit file (initial value = k2_ws).</li>
<li>Graphit filename.</li>
<li>Required output format (initial value = JSON).</li>
<li>Parameters for Graphit execution.</li>
</ul>
<p>The Actor first looks at the input parameters (first level) and, if it is not found there, looks in the params input argument.</p>
</td>
</tr>
<tr style="height: 215px;">
<td style="vertical-align: top; height: 215px; width: 146.925px;">
<h4><a id="user-content-system" class="anchor" href="04_built_in_actor_types.md#system" aria-hidden="true"></a><strong>system</strong></h4>
</td>
<td style="vertical-align: top; height: 215px; width: 292.913px;">System processes and commands to be performed in the file system.</td>
<td style="height: 215px; width: 439.763px;">
<p><strong>cp</strong> Actor, copies a file. The interface used as input can be:</p>
<ul>
<li>JDBC URL.</li>
<li>Reference to a predefined interface.</li>
<li>Path of the sourcefile (from).</li>
<li>Destination (to).</li>
</ul>
<p>The output is a number of affected files.</p>
<p><strong>Exec</strong> Actor, executes a system process and waits for it to be completed.</p>
</td>
</tr>
<tr style="height: 229px;">
<td style="vertical-align: top; height: 229px; width: 146.925px;">
<h4><strong>testing</strong></h4>
</td>
<td style="vertical-align: top; height: 229px; width: 292.913px;">Actors that check a condition and throw an assersion exception if the condition is not met.</td>
<td style="height: 229px; width: 439.763px;">
<p><strong>assertNotNull</strong> Actor, throws an assertion error if supplied object is null.</p>
<p><strong>assertContains</strong> Actor, throws an assertion error if substring cannot be found in string.</p>
<p><strong>assertEquals, </strong>throws an assertion exceptions if actual does not equal expected.</p>
</td>
</tr>
</tbody>
</table>
<p>&nbsp;</p>












[![Previous](/articles/images/Previous.png)](03_broadway_actor_window.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_data_types.md)
