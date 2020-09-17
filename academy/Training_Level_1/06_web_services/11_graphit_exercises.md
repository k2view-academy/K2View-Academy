
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
    
    


 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/06_web_services/10_how_to_use_graphit.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/06_web_services/12_graphit_solutions.md)

