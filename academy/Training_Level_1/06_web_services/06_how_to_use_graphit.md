# Working With Graphit

Graphit is a utility that allows you to design the document response format of a Web Service.

### Creating and Editing a Graphit File

The following are the basic documents for creating a Graphit file and understanding its structure and properties:

-  [Graphit Overview](/articles/15_web_services_and_graphit/17_Graphit/01_graphit_overview.md).
-  [Creating and Editing a Graphit File](/articles/15_web_services_and_graphit/17_Graphit/02_create_and_edit_a_graphit_file.md).

### Graphit Nodes 
The following articles describe the different types of Graphit Nodes and their properties:

-  [Graphit Nodes Type](/articles/15_web_services_and_graphit/17_Graphit/03_graphit_node_types.md).
-  [Graphit Nodes Properties](/articles/15_web_services_and_graphit/17_Graphit/04_graphit_node_properties.md).

### Running or Invoking Graphit
You can run Graphit either:
- In Debug mode from the Graphit Editor. Please refer to [Graphit Debugging](/articles/15_web_services_and_graphit/17_Graphit/05_graphit_debugging.md).
- Embed it as follows:
  - [Using Graphit with Parameters](/articles/15_web_services_and_graphit/17_Graphit/06_using_graphit_files_with_parameters.md)
  - [Invoking Graphit Files](/articles/15_web_services_and_graphit/17_Graphit/07_invoking_graphit_files.md)
  - [Using Java Code from Graphit](/articles/15_web_services_and_graphit/17_Graphit/08_invoke_javacode_from_graphit.md)
  - [Calling Graphit from a Web Form](/articles/15_web_services_and_graphit/17_Graphit/09_invoke_graphit_from_outside_studio.md)

### Graphit Code Examples 
Please refer to [Code Examples](/articles/15_web_services_and_graphit/17_Graphit/10_graphit_examples.md).


### Exercise 1 - Create a Graphit Web Service 

In this exercise we refer to the exercise in [LU Enhancements - Translations](/academy/Training_Level_1/05_LU_Enhancements/04_LU_Enhancements_lookup-translations_flow.md) where we associated a unique geo-location code to the Address table to build a heat map of the customer's location.
 
    Step 1: 
    Open the Graphit Editor and create a JSON form with the following fields:
    - Customer Full Name.
    - Customer City.
    - City International ID.
    - LAT/LONG (previously populated using the translation table and concatenation function).

    Step 2:
    Using JavaScript, open a function node that splits the LAT/LONG field (e.g. 38.3484::-81.6323) and parse it to a map link
    in the following format: https://www.google.com/maps/search/?api=1&query=38.3484::-81.6323.

    Step 3:
    Add a field type node named Map Link to the Graphit file.
    Run the Graphit file in Debug mode for customer ID = 1000.

    Step 4:
    Copy / paste the link in your Browser.
    In which avenue does customer ID 1000 live? What are the two closest businesses? 


### Solution Exercise 1 - Create a Graphit Web Service

Step 1: 

![](/academy/Training_Level_1/06_web_services/images/graphit_exercise1Step1.PNG)

Step 2:
```javascript
"https://www.google.com/maps/search/?api=1&query="+LATLONG.split('::')[0]+","+LATLONG.split('::')[1]'''
```
Step 3:
![](/academy/Training_Level_1/06_web_services/images/graphit_exercise1Step3.PNG)

Step 4:
Massachusetts Ave.

Businesses: Yogulatte, Mass Avenue Pub.

![](/academy/Training_Level_1/06_web_services/images/graphit_exercise1Step4.PNG)
            

[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/06_web_services/05_quiz.md)
[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/06_web_services/07_graphit_quiz.md)
