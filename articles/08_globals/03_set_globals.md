# SET and SET_GLOBAL GLOBAL Commands for Global Variables 

There are two Fabric commands that can override a global:

* **set_global global**, overrides the value at a **cluster level** whereby both the original and new values are saved in Cassandra. Note that when a global is overridden it maintains its new value after re-deployment and after Fabric is restarted.  
* **set**, overrides or shows the value at a **session level**.

Both commands can override a global value only if the global is not defined as Final.

### How Do I Use *Set_Global Global* Command?

This command overrides a global value on the **entire cluster**, impacting all Fabric sessions, including open ones.\
**set_global global '<LUT_NAME>.<PARAM_NAME>[=<PARAM_VALUE>]';**

The **set_global global** command sets the value of the global indicated by <PARAM_NAME> to the value provided by the <PARAM_VALUE>. The PARAM_NAME must be defined as a Global Variable in Fabric.

<table>
<tbody style="vertical-align:top">
<tr>
<td width="300pxl">
<p>set_global global</p>
<p>'&lt;LUT NAME&gt;.&lt;PARAM_NAME&gt; [=&lt;PARAM_VALUE&gt;]';</p>
</td>
<td width="600pxl">
<p>Set the value of the Global indicated by &lt;PARAM_NAME&gt; to the value provided by the &lt;PARAM_VALUE&gt;.</p>
<p>
<UL>
<LI>Specifying &lt;LUT NAME&gt; is supported even when the Global is defined under Shared Objects. In such case the Global value is overridden only for the given &lt;LUT NAME&gt; scope. <br/>NOTE: Override takes effect only if settings of the specific LU is done <b>after</b> the all-LUs settings (using the '*' - see next command) ran.</LI>
<LI>In case variable was not defined at implmentation - neither at Shared Objects nor at LU, action will not take effect. An error message appears then, indicating that it does not exist in that LU.</LI>
</UL>
</p>
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
<td width="300">
<p>set_global global</p>
<p>'&lt;LUT NAME&gt;.&lt;PARAM_NAME&gt; &lsquo;;</p>
</td>
<td width="600">
<p>Reset the Global to its original value when the &lt;PARAM_VALUE&gt; is not provided. The original value is its value prior to the override.</p>
<p>Example:</p>
<p>set_global global &lsquo;CRM.SOURCE_PRODUCT_VERSION&rsquo;;</p>
</td>
</tr>
</tbody>
</table>



### How Do I Use the *Set* Command?

The **set** command can be used to set a variable **per session** so that the variable is available for all Fabric objects in the same session. This command can be used either to set a new ad-hoc variable, or to override an existing global for the Fabric session if the global is not Final.\

<table>
<tbody style="vertical-align:top">
<tr>
<td width="300pxl">
<p>set [&lt;LUT_NAME&gt;.]&lt;PARAM_NAME&gt;=&lt;PARAM_VALUE&gt;;
    <br/>or<br/>
    set [&lt;LUT_NAME&gt;.]&lt;PARAM_NAME&gt;&nbsp; &lt;PARAM_VALUE&gt;;</p>
</td>
<td width="600pxl" >
<p>Set the value of the variable indicated by &lt;PARAM_NAME&gt; to the value provided by the &lt;PARAM_VALUE&gt;.  A value of a Global parameter within a specific LUT can be set by specifting its name, as prefix</p>
<p>Example:</p>
<p>set SOURCE_PRODUCT_VERSION=100;<br/>or<br/>set SOURCE_PRODUCT_VERSION 100;</p>
    <p>
        Note: when specifying a Global variable value of LU on a session level, it is applied no matter when its SET command ran. Even if a general SET command will run <b>after</b> that, with same variable name, the LU scope value will still valid and will not be overriden.
    </p>
</td>
</tr>
<tr>
<td width="234">
<p>set &lt;PARAM_NAME&gt; =&rsquo;&rsquo;;</p>
</td>
<td width="600">
<p>Reset the variable value to the global one, if defined before at the session.</p>
<p>Example:<br>
    Assume that the original value of the SOURCE_PRODUCT_VERSION is &rsquo;prod&rsquo;
</p>
<p>    
    set SOURCE_PRODUCT_VERSION=&rsquo;uat&rsquo;;  // now the value at session is &rsquo;uat&rsquo; <br>
    set SOURCE_PRODUCT_VERSION=&rsquo;&rsquo;;     // now the value at session gets back to &rsquo;prod&rsquo; 
</td>
</tr>
<tr>
<td width="234">
<p>set [&lt;LUT_NAME&gt;.]&lt;PARAM_NAME&gt;;</p>
</td>
<td width="600">
<p>Shows the value of &lt;PARAM_NAME&gt;.  A value of a Global parameter within a specific LUT can be retrieved by specifting its name, as prefix.</p>
<p>Examples:</p>
<p>set SOURCE_PRODUCT_VERSION;<br> 
    set CRM.SOURCE_PRODUCT_VERSION;</p>
    <p>
        NOTES:
        <UL>
            <LI>In case a variable value was set different for a LU and for all others, and the command does not specify the 'LUT_NAME' (e.g. "set SOURCE_PRODUCT_VERSION;"), the result of the such set command will be a message that there is ambiguty.</LI>
            <LI>In case &lt;LUT_NAME&gt; is specified in the command but the variable was not defined at implmentation - neither at Shared Objects nor at LU, action will not take effect. An error message appears then, indicating that it does not exist in that LU.</LI>
        </UL>
    </p>
</td>
</tr>
<tr>
<td width="234">
<p>set;</p>
</td>
<td width="600">
    <p>Shows the session variables values.</p>
    <p>
       <UL>
           <LI>Globals are shown with "Global" prefix, as "set" command shows not only Globals but other session variables too.</LI>
           <LI>The Globals are shown in a "full" exapnded mode, include also values which are permeated from the Shared Objects toward  other levels. see <a href="/articles/08_globals/03_set_globals.md#understand-the-globals-appearance-as-result-of-set-command">below</a> for more information.</LI>
        </UL> 
    </p>     
</td>
</tr>    
</tbody>
</table>



Click [here](/articles/03_logical_units/05_globals_overrides_priorities.md) for more information and more examples of variable scopes, level and priorities, as reflected also by the SET command.  




### Understand the Globals Appearance as result of *Set* Command

When running the `set;` commend (with no parameters) at Fabric console, it shows, as outcome, the values of variables exist at current session.

* This outcome shows the Globals values as was set among the various ***scopes*** - Shared Objects, LUs, and References. Moreover - The Shared Objects Globals are permeated toward other objects  - References, WS and LUs, so that these values also shown at that command outcome.  

* The Globals naming convention at the command outcome is: **Global.\<unit type>.\<variable-name>**. Unit-type refers to LU-name for LUs, "k2_ref" for References, "k2_ws" for Web-Services. (The "Global" first prefix appears because set command shows also non Globals variables).  

* The Shared Globals are shown only on that lower-levels-units objects form and not as standalone values, unless it is set at the session.
* Specifying a session variable does NOT create it as Shared Object. Thus, although it is considered as available Global variable, it will not appear appear under each unit-type (if not dedicated created at this type scope) . It will appear then as standalone variable, without the Global or unit-type prefixes.
* The command outcome reflects the actual values, as a result of priority ***levels*** calculation. Click [here](/articles/03_logical_units/05_globals_overrides_priorities.md) for more information and more examples of variable scopes and priorities

* The command outcome shows both final and non-final Globals.

##### Example

The Globals in this example: 

* Shared Objects level contains these Globals: OLDINVOICES (final), INTERCODE_UK, CASES_THRESHOLD. 
* Customer and CRM LUs own their specific Globals as well. 
* Refences has a single Global

For simplicity, In this example we are not demonstrating Global overrides.

The `set;` command outcome, in our example, will be similar to the below:

![image](images/08_03_globals_console.png)

In the illustrated command outcome is separated by unit types and also colored, in order to emphasize and demonstrate the example: the *non* white colored lines (green, yellow, dark-red: <span style="color:yellow; background-color: black;">OLDINVOICES</span>, <span style="color:lightgreen; background-color: black;">CASES_THERSHOLD</span>, <span style="color:darkorange; background-color: black;">INTERCODE_UK</span>) are those three which inherit from Shared Objects. As can be seen those three Shared Objects Globals appear 4 times: for CRM and Customer LUs, as well as for the References ("k2_ref") and the Web-Services ("k2_ws").



[![Previous](/articles/images/Previous.png)](/articles/08_globals/02_globals_use_cases.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/08_globals/04_globals_code_examples.md)


