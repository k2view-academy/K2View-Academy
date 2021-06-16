# Define Purging Rules

To define the purging rules, two steps are needed:
1.	List the tables that are in the purge scope, meaning the tables that the data they contain may be purged if the purging conditions are met. This is done by including those tables in a translation table as will be detailed below.
2.	Define the purging rules by listing the queries that should be executed and define for each of them the expected result that indicates that the data is valid for purge. 









### Defining a Recursive Purge Check

Recursive checks are defined as a group of checks which run one after the other repeatedly over the same set of entries. The repetition will only stop if the results obtained in the last cycle are equal to the results that were obtained in the previous cycle.






[![Previous](/articles/images/Previous.png)](03_purge_flow.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_purge_execution.md)


