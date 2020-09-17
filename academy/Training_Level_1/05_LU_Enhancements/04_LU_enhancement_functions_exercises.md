
![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png)



#### **Exercise 1 - Enrichment functions**

As you saw in the [Course User Story](/academy/Training_Level_1/01_Fabric_Introduction/1_3_course_user_story.md) at the beginning of this training, and as part of the company's marketing initiative, you need to ensure that all 5G/LTE contract lines are in international format. This enables new data roaming offers to be sent as text to the owners while they are abroad. 

Let's focus for now on the Customer LU in the course's project. 

##### Question 1: Standardization of the phone number

To develop the functions pertaining to this course's section, use the Customer data viewer to retrieve the LUI with the following IDs: 1123, 1125 and 1472.   

    1. How many distinct lines are associated with the LUI in the Contract table?
    

    2. How many lines are associated to a 5G/LTE offer and how many match the international standard format for US numbers?
       +1-xxx-xxx-xxxx (disregard the parenthesis and minus signs).
         

    3. Let's write a Java function that will modify any LUI "Associated Line" fields to the international format using the 
       contract_ID 2787 and 2788 to validate the code. 
        Tips:
            - Use the LUDB class to fetch data from the LUDB.  
            - Use regular expressions to identify the fields that need to be modified.
            - Use the fabric() execute function to update data. 
            

    4. Apply the previous data transformation to telephone lines that belong to a 5G/LTE contract. 
       Please modify the code accordingly.




##### Question 2: Case notes clean-up

The Case_Notes table stores notes about a specific case that has been opened for a specific owner.  
Tamar (Instance ID = 1472) recieves old bills and apology letters to her mailing address about issues she 
is experiencing with the network. The case notes reflect issues that are still open.

    1. Let's first list the contracts owned by Tamar in the data viewer.
           - How many contracts does she own ?
           

    2.  Let's look at the different notes in the Case_Note table of Tamar's LUI.
           - What is the ID number of the note suggesting that Tamar has been alienated and the associated Case Type description 
             (in the Cases table).
           - How many cases are still open ?
           

    3. Write a Java function that operates the following data transformations:
       - All case notes belonging to cases of the "Billing issues" type should be changed to "insolvent customer due to 
         alien assimilation".
       - All case notes belonging to cases of the "Network issues" type should be changed to "Customer has been 
         assimilated to a phone and is no longer network compatible".
       - All open cases should be set to Status="closed".

 
##### Question 3: Attaching the enrichment function to the appropriate table
      1. What happens if you attach the enrichment function to the CASES table ? 
      
      2. To which table should the function be attached ? 




![](/academy/Training_Level_1/05_LU_Enhancements/images/Exercise.png)

#### **Exercise 2 - Decision functions**

To save network resources, ensure that data synchronization of LUIs only occurs if the number of entries in a given external source's table is above the CRMCases_threshold threshold.

##### Question 1: Build a decision function based on the following criteria:

    - If the number of entries in the CASES table in the CRM_DB database is higher than CRMCases_threshold=25000 
      then the sync function will return the *syncind* boolean variable set to TRUE.
    
    - Hardcode the value of the threshold. You will return to this section later to see how to set this threshold 
      as a global parameter.

##### Question 2: Validate the function
    1. Run Instance ID: 1472. How many entries do you see in the Cases table of the LUI ? 
    
    2. Change the value of the CRMCases_threshold to 30000. 
    
    3. Check whether the sync process has been implemented by looking at the Cases table of the 1472 instance. 
       How many cases entries can you see?

  


[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/05_LU_Enhancements/03_LU_Enhancements_Functions_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/05_LU_Enhancements/05_LU_enhancement_functions_solutions.md)

------
