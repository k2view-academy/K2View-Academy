#Define Purging Rules

To define the purging rules, two steps are needed:
1.	List the tables that are in the purge scope, meaning the tables that the data they contain may be purged if the purging conditions are met. This is done by including those tables in a translation table as will be detailed below.
2.	Define the purging rules by listing the queries that should be executed and define for each of them the expected result that indicates that the data is valid for purge. 