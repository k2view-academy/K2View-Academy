# Parameters Definition

Parameters are used throughout the BI application to store values. Although parameters can be created and given a default value in the **Admin** module, parameters are designed to be set at runtime through the REST APIs. 

For example, when creating a report based on an LUI, a filter must be set on the LU's Root table using the Instance ID. Then the filter value is set during the report execution.

**How Do I Create a Parameter?**

See below the detailed explanation how to define parameters using the **BI Admin** module.

![image](images/bi_admin.PNG)

To create a parameter:

1. Right click on **Parameters > Add** and define the following:
   - **Name** - a unique parameter name.
   - **Type** - data type such as string or integer.
   - **Value** - default parameter value.
   - **Hidden** - must be set to **False** to enable the parameters.
2. Click **Apply** to save the changes.

**How Do I Use a Parameter?**

To use a parameter, create a report and add a filter.

[Click to get more information how to use a parameter in a report](05_report_creation_guidelines.md).



[![Previous](/articles/images/Previous.png)](03_Metadata_Setup.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_report_creation_guidelines.md)

