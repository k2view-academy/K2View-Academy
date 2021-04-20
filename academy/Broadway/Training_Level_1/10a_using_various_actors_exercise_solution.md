# Using Actors in a Flow

### ![](/academy/images/Solution.png) Solution - Create a Flow Using Built-In Actors

<ul>
<pre><code>
1. When running the flow for the first time, the Debug / Run Arguments window pops-up to set the value of the cust_id 
external input argument. The next time this window does not pop-up, and it uses the same value until it is reset by the user.
2. When the <strong>single</strong> input argument of the <strong>JsonParser</strong> Actor is set to <strong>true</strong>, the Actor expects only a single object 
in the input stream.
3. If only the number of calls needs to be calculated, they can be replaced by the <strong>Count</strong> Actor. 
However since this exercise needs to stop iterating the data on a specifc condition, 
the <strong>JavaScript</strong> Actor cannot be replaced by another Actor. 
4. The date format changes indicating a different weekday, AM/PM from the default date format.
5. The return value of a <strong>JavaScript</strong> Actor is the last expression in the <strong>script</strong> input parameter.
6. You can refer to a flow argument using the <strong>flowArgs</strong> keyword in a <strong>JavaScript</strong> Actor, for example: 
	flowArgs["cust_id"]; 
</code></pre>
</ul>




[![Previous](/articles/images/Previous.png)](10_using_various_actors_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](11_integration_with_fabric_studio.md)
