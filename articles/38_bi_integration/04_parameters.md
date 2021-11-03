# Parameters Definition

Parameters are used throughout the BI application to store values. Although parameters can be created and given a default value in the **Admin** module, parameters are designed to be set at runtime through the APIs. 

For example, when creating a report based on an LUI, a filter must be set on the LU's Root table using the Instance ID. 

**How Do I Create a Parameter?**

To create a parameter:

1. Open the **Admin** module (click the Admin tab on the top left of the user interface).
2. Right click on **Parameters > Add** and define the following:
   - Name - a unique parameter name.
   - Type - data type such as string or integer.
   - Value - default parameter value.
3. Click **Apply** to save the changes.

**How Do I Use a Parameter?**

To use a parameter, create a report and add a filter.

[Click to get more information how to use a parameter in a report](05_report_creation_guidelines.md).



[![Previous](/articles/images/Previous.png)](03_Metadata_Setup.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_report_creation_guidelines.md)

