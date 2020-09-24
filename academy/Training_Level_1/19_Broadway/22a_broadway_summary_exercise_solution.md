# Broadway Summary Exercise - Solution

**Step 1 - Create a Common Table and Populate it Using a Broadway Flow**

**Question 1:**

a. Modify the input arguments of the **FileRead** Actor to make them external.

b. Create an Interface Listener on the **Local File System** Interface for the LU **SummaryExercise** and attach it to the **EmployeeLookupPopulation** flow.

![image](images/exam_0.PNG)

**Step 2 - Create an LU and Lookup Data and Add them to a Population**

**Question 2:**

a. Modify the SQL statement in the **Lookup** Actor to:

~~~sql
select COUNT(*) AS IS_EMP, EMP_ID
~~~

b. Modify the **JavaScript** Actor to return **res.EMP_ID** if IS_EMP > 0, otherwise **0**.

**Question 3:**

a. Add an Error Handler **JavaScript** Actor to Stage 2 with the following script:

~~~javascript
res != null 
~~~

b. Add another **JavaScript** Actor with the following error message:

~~~javascript
throw "Not in EMPLOYEE table!!!"
~~~

c. Modify the script in the **JavaScript** Actor that connects between the **Lookup** Actor and the CUSTOMER **DbLoad** Actor as follows:

~~~javascript
res.EMP_ID
~~~

![image](images/exam_1.PNG)

**Step 3 - Read the Data from the File**

**Question 4:**

The **JavaScript** Actor exits the loop once the required value is found due to the following script line:

~~~javascript
contextLoop.stop();
~~~

**Step 4 - Add an Inner Flow to the Population**

![image](images/exam_2.PNG)

**Step 5 - Use Actor Inheritance in the Flow**

**Question 5:**

Yes, if the inherited Actor needs to be modified, use the **Export Actor** again with the **Override current** option. The change impacts all existing instances of the inherited Actor.

![image](images/exam_3.PNG)

**Step 6 - Split the Stages and Write into the File**

**Question 6**:

The purpose is to split the data based on the condition. **Roaming special** contracts types are written into the file and all other contracts are populated in the LU.

![image](images/exam_4.PNG)



[![Previous](/articles/images/Previous.png)](22_broadway_summary_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](23_broadway_exam.md)
