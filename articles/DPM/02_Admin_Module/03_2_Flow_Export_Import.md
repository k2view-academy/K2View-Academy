### Export and Import Flow

Flows that are generated in one environment can be exported and then imported at another environment. 

This functionality allows you to create a Flow and test it in one environment and then transfer the Flow without any effort to another environment such as Acceptance Test environment and eventually Production environment. 

#### To export a Flow

From the Flow screen, click the  ![image](/articles/DPM/images/Figure_7_export_flow_icon.png)  icon, located at the upper-left corner of the Flow screen. This creates a json file containing the Flow configuration, which can be imported at the target environment.

#### To import a Flow

At the target environment, from the Flow List screen, use the option ![image](/articles/DPM/images/Figure_7_import_flow_icon.png), located at the upper-right corner of the screen. 
As a result, a pop-up window is presented, where the information for the import activity is defined. 

![image](/articles/DPM/images/Figure_7_import_Flow_screen.png)

Choose the file that contains the Flow configuration (that was generated as a result of the export at the previous step).
Once the file was selected, the DPM loads the configuration, identifies the Flow name and version of the imported Flow, and present the information to the user:

![image](/articles/DPM/images/Figure_7_imported_Flow_example.png)

Before confirming the import, you can change the Flow name and version. If the purpose is to update the definitions of a Flow and version that already exists in the target environment - Define the same name and version as the Flow you would like to update and turn on the override indicator:  ![image](/articles/DPM/images/Figure_7_override_flow_icon.png).

When needed, the Flow configuration can be edited manually before the import, by changing the content of the json file or editing the json when it is presented on the screen. This editing should be done with care in order not to create invalid scenario or invalid json structure.

[![Previous](/articles/DPM/images/Previous.png)](/articles/DPM/02_Admin_Module/03_1_Flow_Level_Actions.md)[<img align="right" width="60" height="54" src="/articles/DPM/images/Next.png">](/articles/DPM/02_Admin_Module/03_1_Flow_Level_Actions.md)