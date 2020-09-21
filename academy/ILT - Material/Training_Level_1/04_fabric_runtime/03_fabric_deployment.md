# Fabric Deployment

![](/academy/Training_Level_1/03_fabric_basic_LU/images/fabric_main_flow_07.png)

NTT -> Deploy the Logical Unit and other components to the Fabric server where the data is actually stored, searched and available for inquiries.

 

### Deployment to the Fabric Server

NTT -> Explain how  design and implementation can be executed in large-scale and on demand:

[Deploy Fabric Project](/articles/16_deploy_fabric/01_deploy_Fabric_project.md)

[Deploy from Fabric Studio](/articles/16_deploy_fabric/02_deploy_from_Fabric_Studio.md)


### ![](/academy/Training_Level_1/03_fabric_basic_LU/images/example.png)Example - Fabric Deployment

Let's deploy the Logical Unit we have just created in the Fabric Studio:

1. Right click  **Customer** LU and select **Deploy To Server**:

   1. If you are using a **Fabric Linux server**, select the server defined under **User Peferences**.
   2. In **local Fabric**, select **Debug Server**. 

2. Once the deployment has been completed, return to the Fabric console and log into Fabric using the **fabric** command. Note that you are already logged into local Fabric.

3. To review the deployed project's properties, run **fabric>set;**. What is the project name? Which environment is active?

4. Let's check which Logical Unit has been deployed. How is it verified?  Run **fabric>list LUT;**  

    You will learn more about the options for this Fabric command in our next learning item.


------
 

