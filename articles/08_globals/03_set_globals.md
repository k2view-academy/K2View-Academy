# SET and SET_GLOBAL Global Commands for Global Variables 

There are two Fabric commands that can override a Global value:
 
* **set_global global**, overrides the value at a **cluster level** whereby both the original and new values are saved in Cassandra. Note that when a Global is overridden it maintains its new value after re-deployment and after Fabric is restarted.  
* **set**, overrides the value at a **session level**.
 
Both commands can override a Global value only if the Global is not defined as Final.

### How Do I Use Set_Global Global Command?

This command overrides a Global value on the **entire cluster**, impacting all Fabric sessions, including open ones.\
**set_global global '<LUT NAME>.<PARAM_NAME>[=<PARAM_VALUE>]';**

The **set_global global** command sets the value of the Global indicated by <PARAM_NAME> to the value provided by the <PARAM_VALUE>. The PARAM_NAME must be defined as a Global Variable in Fabric.

<table>
<tbody>
<tr>
<td width="300">
<p>set_global global</p>
<p>'&lt;LUT NAME&gt;.&lt;PARAM_NAME&gt; [=&lt;PARAM_VALUE&gt;]';</p>
</td>
<td width="400">
<p>Set the value of the Global indicated by &lt;PARAM_NAME&gt; to the value provided by the &lt;PARAM_VALUE&gt;. Specifying &lt;LUT NAME&gt; is supported in the command even when the Global is defined under Shared Objects. If the &lt;LUT NAME&gt; is provided, the Global value is overridden only for the given &lt;LUT NAME&gt;.</p>
<p>Example:</p>
<p>set_global global &lsquo;CRM.SOURCE_PRODUCT_VERSION=100&rsquo;;</p>
</td>
</tr>
<tr>
<td width="234">
<p>set_global global</p>
<p>'*.&lt;PARAM_NAME&gt;[=&lt;PARAM_VALUE&gt;] &lsquo;;</p>
</td>
<td width="319">
<p>Set the value of the Global indicated by &lt;PARAM_NAME&gt; to the value provided by the &lt;PARAM_VALUE&gt; - for all LUT.</p>
<p>Example:</p>
<p>set_global global &lsquo;*.SOURCE_PRODUCT_VERSION=100&rsquo;;</p>
</td>
</tr>
<tr>
<td width="234">
<p>set_global global</p>
<p>'&lt;LUT NAME&gt;.&lt;PARAM_NAME&gt;=&rsquo; ;</p>
</td>
<td width="319">
<p>Set the Global to a NULL value.</p>
<p>Example:</p>
<p>set_global global &lsquo;CRM.SOURCE_PRODUCT_VERSION=&rsquo;;</p>
</td>
</tr>
<tr>
<td width="234">
<p>set_global global</p>
<p>'&lt;LUT NAME&gt;.&lt;PARAM_NAME&gt; &lsquo;;</p>
</td>
<td width="319">
<p>Set the Global to its original value when the &lt;PARAM_VALUE&gt; is not provided. The original is the value of the Global prior to the override.</p>
<p>Example:</p>
<p>set_global global &lsquo;CRM.SOURCE_PRODUCT_VERSION&rsquo;;</p>
</td>
</tr>
</tbody>
</table>

### How Do I Use the Set Command?

The **set** command can be used to set a variable **per session** so that the variable is available for all Fabric objects in the same session. This command can be used either to set a new ad-hoc variable, or to override an existing Global for the Fabric session if the Global is not final.

<table>
<tbody>
<tr>
<td width="300">
<p>set &lt;PARAM_NAME&gt; =&lt;PARAM_VALUE&gt;; or</p>
<p>set &lt;PARAM_NAME&gt;&nbsp; &lt;PARAM_VALUE&gt;;</p>
</td>
<td width="400">
<p>Set the value of the variable indicated by &lt;PARAM_NAME&gt; to the value provided by the &lt;PARAM_VALUE&gt;.</p>
<p>Example:</p>
<p>set SOURCE_PRODUCT_VERSION=100; or</p>
<p>set SOURCE_PRODUCT_VERSION 100;</p>
</td>
</tr>
<tr>
<td width="234">
<p>set &lt;PARAM_NAME&gt; =&rsquo;&rsquo;;</p>
</td>
<td width="319">
<p>Remove the variable from the session.</p>
<p>Example:</p>
<p>set SOURCE_PRODUCT_VERSION=&rsquo;&rsquo;;</p>
</td>
</tr>
<tr>
<td width="234">
<p>set &lt;PARAM_NAME&gt;;</p>
</td>
<td width="319">
<p>Get the value of &lt;PARAM_NAME&gt; from the current session. If &lt;PARAM_NAME&gt; is not overridden, the command returns null.</p>
<p>Example:</p>
<p>set SOURCE_PRODUCT_VERSION;</p>
</td>
</tr>
</tbody>
</table>

[**Click for more information about Globals – Code Examples.**](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/04_globals_code_examples.md)

[![Previous](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Previous.png)](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/02_globals_use_cases.md)[<img align="right" width="60" height="54" src="https://github.com/k2view-academy/K2View-Academy/blob/master/articles/images/Next.png">](https://github.com/k2view-academy/K2View-Academy/blob/master/articles/08_globals/04_globals_code_examples.md)


