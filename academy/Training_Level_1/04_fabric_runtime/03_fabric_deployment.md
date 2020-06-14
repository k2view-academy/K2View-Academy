# Fabric Deployment

![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_07.png)

So far, you have implemented and validated your Project in a Test mode using the Fabric Studio, mainly to make sure that all the data retrieval business requirements are met. However, to actually envision how the Project will react and interact in a live system, you need to deploy the Logical Unit and other components to the Fabric Server where the data is actually stored, searched and available for inquiries.

 

### Deployment to Fabric Server

See how your design and implementation can be executed in scale andon demand:

[Deploy Fabric Project](/articles/16_deploy_fabric/01_deploy_Fabric_project.md)

[Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md)



### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Fabric Deployment

Let's deploy  from the Fabric studio the Logical Unit  we have just created:

1. Right click on CustomerLU , select the Deploy To Server:

   1.  If you are using Fabric linux server, select the server you have defined under the User Pefernces.
   2. On local Fabric, select the **debug** **server** 

2. Once deploy has completed, go back to the Fabric console and log into Fabric, using **fabric** command. Note that on local fabric, you are laready logged in.

3. Run **fabric>set;** to reveiw the deployed project properties , what is the project name? what is the active environment?

4. Let's validate that are Logical Unit has been deployed, How should we verify it?  Run **fabric>list LUT;**  

    We will learn more options for this fabric command up next ...



 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/04_fabric_runtime/02_getting_started_with_fabric.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/04_fabric_runtime/04_fabric_basic_commands.md)

 

