# Working with Graphit

Graphit is a utility that allows you to design the documents response format of your web-services.

### Creating and Editing a graphit file

The following are the basic steps for creating a Graphit file and understanding its structure and properties:

-  [Graphit Overview](/articles/15_web_services/17_Graphit/01_graphit_overview.md)

-  [Create and Edit a Graphit File](/articles/15_web_services/17_Graphit/02_create_and_edit_a_graphit_file.md)


The following articles desscribe the different types of Graphit Nodes and their properties:

-  [Graphit Nodes Type](/articles/15_web_services/17_Graphit/03_graphit_node_types.md)

-  [Graphit Nodes Properties](/articles/15_web_services/17_Graphit/04_graphit_node_properties.md)


Running Graphit:
- In debug mode from the Graphit Editor: [Graphit Debugging](/articles/15_web_services/17_Graphit/05_graphit_debugging.md)
- [Using Graphit with Parameters](/articles/15_web_services/17_Graphit/06_using_graphit_files_with_parameters.md)

Invoking Graphit:
- [Invoking Graphit Files](/articles/15_web_services/17_Graphit/07_invoking_graphit_files.md)
- [Invoke Java Code from Graphit](/articles/15_web_services/17_Graphit/08_invoke_javacode_from_graphit.md)
- [Invoke Graphit from a Web Form](/articles/15_web_services/17_Graphit/09_invoke_graphit_from_outside_studio.md)


### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)
Graphit [Code Examples](/articles/15_web_services/17_Graphit/10_graphit_examples.md)



#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png) 

**Exercise 1 - Create a Graphit Web-Service**
  
For this exercise we will be referring to the exercise from previous section: [LU Enhancements - Translations](/master/academy/Training_Level_1/05_LU_Enhancements/04_LU_Enhancements_lookup-translations_flow.md).
There, we associated a unique geo-location code to the Address table to build a heat map of the customer's location.
 
Step 1: 
Open the graphit editor and create a JSON form that will feature the following fields:
- Customer Full Name
- Customer City
- City International ID
- LAT/LONG (previously populated using the translation table and concatenation function)

Step 2:
Using Java Script, open a function node that will split the LAT/LONG field (e.g. 38.3484::-81.6323) and parse it to a map link
in the following format: https://www.google.com/maps/search/?api=1&query=38.3484::-81.6323

Step 3:
Add a node of type field called Map Link to the graphit file
Run the graphit file in debug mode for customer ID = 1000.

Step 4:
Copy/Paste the link in your browser.
On which avenue does customer ID 1000 live ? Which are the 2 closest businesses ? 


#### ![](/academy/Training_Level_1/05_LU_Enhancements/images/Solution.png) 

**Solution Exercise 1 - Create a Graphit Web-Service**

Step 1:

![](/images/graphit_exercise1Step1.PNG)

Step 2:
```javascript
"https://www.google.com/maps/search/?api=1&query="+LATLONG.split('::')[0]+","+LATLONG.split('::')[1]'''
```
Step 3:
![](/images/graphit_exercise1Step1.PNG)

Step 4:
Massachusetts Ave.

Businesses: Yogulatte, Mass Avenue Pub

![](/images/graphit_exercise1Step4.PNG)
            
            



