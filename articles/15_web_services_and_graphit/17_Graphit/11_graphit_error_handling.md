# Graphit - Error Handling

Once an ```error.graphit``` file is created on the project implementation, its execution is triggered each time an exception is returned as a result of 
Fabric Web-Service call. 

It allows the implementors to set a standard payload reponse structure no matter what triggered the exception.

For example it can be a ```page not found``` message caused by an external client calling a Web-Service that was not deployed or by the provisioning of an invalid APIKEY in the request, etc..

The implementator has full flexibility to analyze the reason for the failure and set the Web-Service response body, header and status accordingly.

### Example of error.graphit file

The following ```error.graphit``` file checks the original Web-Service response status.

If the response is 404 (page not found) it returns the original Web-Service details:
- response status
- exception error message	
- method
- path

and overrides the response with the following:		
- response status: 200 (OK).
- header_key value: 'Web Service Not Found'


If the response is not 404, it returns the original Web-Service details:	
- response status
- exception error message
- request header User-Agent

<img src="images/66_graphit_error_handling.png"></img>


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/09_invoke_graphit_from_outside_studio.md)
