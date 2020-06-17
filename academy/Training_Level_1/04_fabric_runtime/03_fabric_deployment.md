# Fabric Deployment

![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_07.png)

So far, you have implemented and validated your project in a Test mode using the Fabric Studio, mainly to make sure that all data retrieval business requirements are met. However, to actually envision how the project will react and interact in a live system, you need to deploy the Logical Unit and other components to the Fabric server where the data is actually stored, searched and available for inquiries.

 

### Deployment to the Fabric Server

See how your design and implementation can be executed in large-scale and on demand:

[Deploy Fabric Project](/articles/16_deploy_fabric/01_deploy_Fabric_project.md)

[Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md)


### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Fabric Deployment

Let's deploy the Logical Unit we have just created in the Fabric Studio:

1. Right click  **CustomerLU** and select **Deploy To Server**:

   1. If you are using a **Fabric Linux server**, select the server defined under **User Peferences**.
   2. In **local Fabric**, select **Debug Server**. 

2. Once the deployment has been completed, return to the Fabric console and log into Fabric using the **fabric** command. Note that you are already logged into local Fabric.

3. To review the deployed project's properties, run **fabric>set;**. What is the project name? Which environment is active?

4. Let's check which Logical Unit has been deployed. How is it verified?  Run **fabric>list LUT;**  

    You will learn more about the options for this Fabric command in our next learning item.



 [![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/04_fabric_runtime/02_getting_started_with_fabric_server.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/04_fabric_runtime/04_fabric_basic_commands.md)

 

