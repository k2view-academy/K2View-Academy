# Templates Overview

Templates are ready made Fabric objects that can be used throughout a project rather than having to build them from scratch. 

* Having a defined structure already built saves time and offers increased efficiency when creating objects
* Using templates can reduce implementation mistakes as they adopt and embed the right methodologies and Fabric best practices.

Templates can be used for various purposes in Broadway flows and Actors, Java files, Graphit and the Data Catalog.

Fabric Templates support using placeholders for smoother and smarter reuse. When selecting a template, the user populates the template’s placeholder so that the new object is auto-generated based on the template.

The integrated Templates engine has powerful capabilities such as iteration over lists, conditions and lookup expressions. 



While templates can be useful for many scenarios, TDM is a good example where this capability is heavily been in use. At TDM Broadway flows are created for transferring data from LU tables into target tables. Instead of creating manually a flow for each of the LU tables, that get its data and load it to the target,  templates are used where only source table, target tables and interfaces shall be supplied.   





[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_create_and_edit_template.md)  

