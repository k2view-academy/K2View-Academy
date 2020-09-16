# ![](/academy/images/Exercise.png) Exercise - Interface Listener and Stream Handling

In this exercise you will do the following:

* Create a flow that reads a CSV file, parses it, searches for a specific value in the file and prints this value into the log.
* Define an Interface Listener for the above flow.

For this exercise you will need a CSV file with the columns CNTRY_CD, NAME and AREA_CD. The file should be populated with the relevant codes, for example:

<table>
<tbody>
<tr>
<td>&nbsp;CNTRY_CD</td>
<td>&nbsp;NAME</td>
<td>&nbsp;AREA_CD</td>
</tr>
<tr>
<td>&nbsp;FIN</td>
<td>&nbsp;Finland</td>
<td>&nbsp;358</td>
</tr>
<tr>
<td>&nbsp;GIB</td>
<td>&nbsp;Gibraltar</td>
<td>&nbsp;350</td>
</tr>
<tr>
<td>&nbsp;HKG</td>
<td>&nbsp;Hong Kong</td>
<td>&nbsp;852</td>
</tr>
<tr>
<td>&nbsp;IND</td>
<td>&nbsp;India</td>
<td>&nbsp;91</td>
</tr>
<tr>
<td>&nbsp;ISR</td>
<td>&nbsp;Israel</td>
<td>&nbsp;972</td>
</tr>
<tr>
<td>&nbsp;ITA</td>
<td>&nbsp;Italy</td>
<td>&nbsp;39</td>
</tr>
</tbody>
</table>

##### Exercise Steps:

1. Create a new Broadway flow.

2. Add a **FileRead** Actor to Stage 1 to read the input file.

   * Set the input arguments **interface** and **path** as External.

3. Add a **CsvParser** Actor to Stage 2 and connect it with the **FileRead** Actor.

4. Add a **JavaScript** Actor to Stage 3 and add an **input1** input argument to it.

   * Now connect **input1** with the output of the **CsvParser** Actor.
   * Set  the **Link Type** to **Iterate**.
   * Click ![image](images/three_dots_icon.png) in the Stage context menu > **Iterate Close**. 
   * Write the following JavaScript code in the **script** input argument of the Actor:

   ~~~javascript
   if (input1.CNTRY_CD == "ISL") {
       contextLoop.stop();
       input1.AREA_CD;
   }
   ~~~

5. Add a **Logger** Actor to Stage 4 

   * Set the **message** value of  the **Logger** Actor to: *The required area code is: ${0}*.
   * Connect the **result** output argument of the **JavaScript** Actor to the **[params]** input argument of the **Logger** Actor. 

6. Save the flow. The flow is ready and you can start the definition of the Interface Listener.

   ![flow](images/13_flow.PNG)

7. Start from defining the Interface. 

   * Go to the **Project Tree** > **Shared Objects** > **Interfaces** and click **New Interface**. 
   * Set **Interface Type** = **Local File System**.
   * Write **C:\k2view\listener** in the **Working Directory** and ***** in the **Files Filter**.
   * Set the Interface name and save it.

   ![flow](images/13_interface.PNG)

8. Now create an Interface Listener.

   * In the Interface window click **Add interface listener as Broadway job** and select CRM - the LU for which the job should be created.
   * The **Interface Listener** tab of the **Jobs (CRM)** window opens.
   * Select the above flow from the **Broadway Flow** drop-down and the above Interface from the **Interface Name** drop-down and save the Jobs. 
   * Keep the **Execution Mode** = **Automatically**. 

9. Deploy the CRM LU.

   To learn how to deploy the Logical Unit, refer to [Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md).

   

You are ready for the Interface Listener execution! 

Create a folder **C:\k2view\listener** in your local file system and copy there a CSV file with the format and data as described at the beginning of this exercise. 

The Interface Listener will pick up the file and run the flow.

![cases population](images/13_flow_final.PNG)





[![Previous](/articles/images/Previous.png)](12_broadway_as_a_population_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](xxx.md)

