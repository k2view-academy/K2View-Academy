# Creating and Invoking a Graphit Functionality From a Web Form


Graphit files can be generated either:
- Using the Graphit Editor in the [Fabric Studio](/articles/15_web_services_and_graphit/17_Graphit/02_create_and_edit_a_graphit_file.md).
- Using a Web Form that can be accessed externally.

## How Do I Invoke Graphit From a Browser?

1.  Open your  **Internet Browser** and enter the following address:  **http://[Fabric IP address]:3213/static/graphit/graphit.html**.

    For example: http://localhost:3213/static/graphit/graphit.html
    
2.  To define the nodes, go to [Create and Edit a Graphit File](/articles/15_web_services_and_graphit/17_Graphit/02_create_and_edit_a_graphit_file.md).


3.  Define the parameters/values and token to debug the Graphit form.

    ![](/articles/15_web_services_and_graphit/17_Graphit/images/54_invoke_javacode_from_outside.PNG)


4.  Select the type of output format by clicking one of the following icons:
   [](/articles/15_web_services_and_graphit/17_Graphit/images/55_invoke_javacode_from_outside.PNG)
       - JSON, click {:}
       - XML, click </>
       - CSV, click ![](images/56_invoke_javacode_from_outside.PNG)
  
     The response is displayed in the white panel on the right side of the screen:
    
      ![](/articles/15_web_services_and_graphit/17_Graphit/images/57_invoke_javacode_from_outside.PNG)
   
5.  Test Graphit POST URL

Using *postman* (or any other similar tool), paste the POST link into the Query fieldand append the graphit parameter **&graphit=%7B%0%220%2...** (copied from from the GET URL) to the POST URL. 

Example:

```http://localhost:3213/graphit?nullformat=json&luName=k2_ws&graphit=%7B%0%220%2...``` 


6.  Test Graphit cURL

Using a the cURL command of your shell environment type the following command, replacing the ```@myfile.graphit``` with the actual name and local path of the graphit file you wish to invoke.

``` curl -H "Content-Type: application/json" -d @myfile.graphit "http://localhost:3213/graphit?nullformat=json&luName=k2_ws" ```


Example:

```curl -H "Content-Type: application/json" -d @C:\Users\DaniellaDavis\Downloads\graphit_file.graphit.graphit "http://localhost:3213/graphit?nullformat=json&luName=k2_ws"```


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/17_Graphit/08_invoke_javacode_from_graphit.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md)









