# Additional Broadway Features

### ![](/academy/images/Solution.png) Solution

<ul>
<pre><code>
1a. Yes, the inherited Actor can be used in any other flow.
1b. Select <strong>Export Actor</strong> from the inherited Actor's context menu and update the definition of the arguments. 
For example, change the <strong>tz</strong> input argument from <strong>hidden</strong> to <strong>empty</strong>, click the <strong>override current</strong> checkbox and submit.
2a. It is not the only way, the Error Handler can be defined using any Actor. 
For example, the described validation can be performed using <strong>GreaterThanEquals</strong> Actor.
2b. When an error handler returns false, the flow stops. 
Thus when <strong>a</strong> is negative, the flow stops before the <strong>Now</strong> Actor is invoked.
3. When an error handler returns false, the flow stops and the transaction ends with a rollback. 
Thus when <strong>a</strong> is negative, the flow stops and the transaction is rolled back.
</code></pre>
</ul>

