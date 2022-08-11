# GraphIt - Error Handling

Once an ```error.graphit``` file is created on the project implementation, its execution is triggered each time an exception is returned as a result of a
Fabric Web Service call. (Important to note that if you have prepared a "catch" for the exception, there will be no trigger to execute the file). 

The creation of an ```error.graphit``` file allows the implementors to set a standard payload response structure to any exception, regardless of the cause.
The payload structure can be in JSON or XML format, depending on the original Web Service call requested format. In case of CSV format ```error.graphit``` file will not be triggered.

For example, one trigger could be a ```page not found``` message caused by an external client calling a Web Service that was not deployed. A different example of a trigger can be the provisioning of an invalid APIKEY in the request, etc.

The implementor has full flexibility to analyze the reason for the underlying failure that caused the trigger, and as such, set the Web Service response body, header and status accordingly.

This feature was added as a part of Fabric release 6.5.3.

### Example 1 - simple error.graphit file

The following ```error.graphit``` file checks the original Web Service response status.

If the response is 404 (page not found) it returns these original Web Service details:
- response status
- exception error message	
- path

and overrides the response with the following:		
- response status: 500.

<img src="images/67_graphit_error_handling_2.png"></img>

### Example 2 - error.graphit file

The following ```error.graphit``` file checks the original Web Service response status.

If the response is 404 (page not found) it returns these original Web Service details:
- response status
- exception error message	
- method
- path

and overrides the response with the following:		
- response status: 200 (OK).
- header_key value: 'Web Service Not Found'


If the response is not 404, it returns these original Web Service details:	
- response status
- exception error message
- request header User-Agent

In the figure below, you can see both of these responses: *404 not found* is shown in the upper part of the figure, and *any other response* (that is, if the response is not 404), is shown in the lower part of the figure.  

<img src="images/66_graphit_error_handling.png"></img>


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)
