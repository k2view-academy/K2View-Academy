# Creating a New Job

Fabric Studio supports defining a Job function, saving it to the project file and deploying it to the Fabric Server. A Job can be a Java function or a simple command .

### How Do I Create a New User Job?

The following steps discuss how a new User Job is created:

1. In the **Project Tree**, go to your LUT > **Java** > **Utilities** and right-click on **New Function** to display the **Function Editor** window.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-1.png" alt="drawing"/>     

2. Write the User Job Function (or use IntelliJ to write it) and once done, set the Function Type to the **User Job** value in the right-hand panel.

3. Name and save your function.

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-2.png"/>  

4. Then in the **Project Tree**, go to your LUT > **Jobs** > **User Jobs**

   <img src="/articles/15_web_services_and_graphit/images/Web-Service-KI-3-3.png" alt="drawing"/>  

5. In the **table** start filling in the details pertaining to the Job.
Method: The name of the User Job Function defined in Step 3
Unique Job Name: The name for the Job service
Execution Mode: Automatically or Manually depending on whether you intend to start the job service from the Command Line.
Active: Unclick to disable the Job - It will not be deployed or run if left unchecked.
Mode: 


[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/02_web_services_properties.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/04_web_services_function_basic_structure.md)


