# Templates Overview

Templates are ready made Fabric objects that can be used by implementers instead of building them from scratch. 

* Having a defined structure already built is extremely cost effective rather than having to rebuild objects over again.
* Using templates can reduce implementation mistakes as they adopt and embed the right methodologies and Fabric best practices.

Templates can be used for various purposes among the following objects which managed  by Fabric:  Broadway flows and actors, Java files,  Graphit and Data Catalog.

While templates can be useful for many scenarios, TDM is a good example where it heavily been in use. At TDM Broadway flows are created for transferring data from LU tables into target tables. Instead of creating manually such flows, which shall fulfill same or almost the same task, templates are used with just populating the specific target tables and interfaces.

Fabric Templates support **placeholders’** usage for smoother and smarter reuse. Using placeholders is a better way than using copy-and-paste because it automates all places needed to be changed, preventing by that mistakes.

Templates can be created per LU or can be part of the shared objects. Once a template is set, it is available at the required target location in the Fabric Studio Tree. On selecting the required template implementer shall populate the template’s placeholders, if exist, and then the new object is auto generated, based on the template.

   



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_.md)  

