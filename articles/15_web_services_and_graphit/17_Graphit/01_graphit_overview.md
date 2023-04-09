# Graphit Overview

Graphit is a Fabric UI utility, which can be used to design [Project Custom-Built Web Services](/articles/15_web_services_and_graphit/05_custom_ws.md) responses. Graphit provides a very user-friendly interface and requires minimal coding. 

A Graphit file is structured and shown in the Graphit Editor as a hierarchical representation of nodes, where each node defines an entry element in the structure of the JSON, XML or CSV document. While usually writing web services using code editor and developer can only imagine if the required nodes and structure are built well, Graphit Editor lets you to see and design it visually, in a well clear view.	

Having the Graphit Editor as a powerful utility to build such documents, it can be used not only as a web service. For example, you can build Graphit files in other LUs as well, not only in Web Services LU, and invoke them internally from other project implementation components like Java functions or Broadway. To learn more about invoking Graphit  - read [here](05_invoking_graphit_files.md).



## Main Features
Using Graphit you can do the following:
- Create dynamic JSON, XML and CSV documents. 
- Accept external input as variables. 
- Use variables in queries.
- Execute queries on an LUDB, iterate over the results and use the returned values to create the response document.
- Execute queries on interfaces other than Fabric.
- Use prepared statements.
- Implement XML and JSON hierarchy, including queries in inner hierarchy levels. 
- Use outer level query results as arguments. 
- Recursively generate nested tags and structures.
- Customize the format of a value and define whether to generate a tag when the values are null or empty.





[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/17_Graphit/02_graphit_basic_editing.md)

