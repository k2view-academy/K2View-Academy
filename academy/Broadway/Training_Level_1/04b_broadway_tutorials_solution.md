# Broadway Tutorial Flow


### ![](/academy/images/Solution.png) Solution - Run and Debug the Broadway Tutorial Flow Exercise 

 <ul>
 <pre><code> 
<strong>Step 2.1</strong>
A. The flow's result is "Hello Broadway"</code></pre>
 </ul>
<ul>
<pre><code>
<strong>Step 2.3</strong>
A and B, the flow executes two iteration runs on the <strong>StringBuild</strong> Actor. 
The first iteration runs on <strong>Hello</strong> and the second iteration runs on <strong>Broadway</strong>.
C. The <strong>StringBuild</strong> Actor returns the following output: <strong>Hello Broadway</strong>.
D. The <strong>StringBuild</strong> Actor returns one output. It gets its inputs by a loop and builds the string. 
The <strong>for each</strong> Stage is marked as <strong>Iterate Close</strong>. As a result, the StringBuilder in this Stage closes the loop
and only then returns its output.
E. The next Stage executed after the <strong>Splitting the flow</strong> Stage is the <strong>Dynamic Logic Actors</strong> Stage, 
since this Stage is on the same level as the <strong>else</strong> of the condition which returns <strong>false</strong>. 
See <a href="/articles/19_Broadway/02a_broadway_flow_overview.md#flow-with-condition">Flow with Conditions</a>.
</code></pre>
</ul>
<ul> 
<pre><code> 
<strong>Step 2.4</strong>
A. Once the debug option is set to <strong>Live Debug</strong>, every change in the flow's data (such as the change of a <strong>Const</strong> Actor's value)
causes the flow to be run automatically.
</code></pre> 
</ul>

<ul>
<pre><code>
<strong>Step 3.2</strong>
A. The <strong>Now</strong> Actor added to <strong>Stage 3</strong> has not been executed since this Stage runs only if the <strong>Paradox</strong> condition is fulfilled.</code></pre>
</ul>
<ul>
 <pre><code>
<strong>Step 3.6</strong> 
A. The flow's result is <strong>Broadway Training</strong>.</code></pre>
</ul> 
<ul><pre><code>
<strong>Step 4.1</strong>
A. Yes. A local copy of the Tutorial flow has been saved in the Fabric project during its execution.</code></pre></ul>
<ul><pre><code>
<strong>Step 4.2</strong>
A. The value of the input parameter is <strong>Broadway Training</strong>. You can edit the flow and save the changes or execute the flow. 
A local copy of the flow is saved in the Fabric project. 
</code></pre></ul> 



[![Previous](/articles/images/Previous.png)](04a_broadway_tutorials_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_create_broadway_flow.md)

