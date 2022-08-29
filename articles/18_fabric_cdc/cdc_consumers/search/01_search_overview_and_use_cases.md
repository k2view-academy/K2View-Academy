# Search Overview and Use Cases

Fabric CDC has built-in integration with the Elasticsearch search provider that enables cross-instance searches. For example, to search for all customers named “John Doe” and live in “New-York”.

Note that an installation of the Search provider is required.

Starting from Fabric V6.5.9, AWS OpenSearch is also supported as the Search provider. The setting which search provider to use is defined in config.ini. The default Search provider is Elasticsearch.

A search is implemented on LUIs only when the LU has [Search fields](02_search_implementation.md#creating-search-fields).

Deployment of the LU creates indexes in the Search Engine, where every data change in the LUI updates the data in the Search Engine.

LUIs must be loaded to Fabric to enable a cross-instance search. For example, a search for all customers named “John Doe” and live in “New-York” will only return customers that exist in Fabric.

A search can be executed either by running a Search request directly in the Search provider, or by using [Fabric Search Command](05_search_command.md). 

### Search Use Cases

1. Matching, search all LUIs by a searched criteria. 

   - Examples:
     - Search all customers whose first name is "Meryl" and last name is  "Streep". Run a case insensitive search and allow up to two errors. The search returns customers named "Meryl Streep", "meryl streep", or "Meril Streep" .
     - Search all customers whose full name is "Meryl Streep". Enable a different order of words within a field. The search returns customer named "Meryl Streep" or "Streep Meryl".
     - Search all customers whose ID number is '1234567'. Return all customers whose ID number contains at least 4 digits of the searched value and at least 2 of the digits have the correct order. The search returns '1243',  '56347712', and '654331234'.
     - Support a partial match.  At least 8 digits exist, but there must not be more than 2 additional digits/letters.

2. Predictive search. Complete the value typed by the user. 

   - Example: when the user types "Ber",  completes the value to "Berlin" or "Berry".

   [Click here for more information about Search examples](05_search_command.md#search-command---examples).

   



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_search_implementation.md)

