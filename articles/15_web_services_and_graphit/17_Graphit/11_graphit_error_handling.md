# Graphit - Error Handling

When error.graphit file is created on the project implementation, it is triggered each time an exception is returned as a result of 
Fabric Web-Service call. It allows the implementors to set a standard payload reponse structure no matter what was the trigger for the exception.

It can be either page not found since an external client calls a Web-Service that was not deployed in Fabric or invalid APIKEY, etc..

The implementator has a full flexability to analyze the reason for the failure and set the Web-Service response body, header and status accordingly.

### Example of error.graphit file

The following error.graphit file check the original Web-Service response status.
If it is 404 (page not found) 
	it returns
		original called Web-Sevice response status
		original called Web-Service exception error message	
		original called Web-Service called method
		original called Web-Service path
	and override
		response status with 200 (ok).
		header_key on the original Web-Service response header with the value 'Web Service Not Found'.
Else
	it returns	
		original called Web-Sevice response status
		original called Web-Service exception error message
		original called Web-Service request header User-Agent

<img src="images/66_graphit_error_handling.PNG"></img>


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/09_invoke_graphit_from_outside_studio.md)
