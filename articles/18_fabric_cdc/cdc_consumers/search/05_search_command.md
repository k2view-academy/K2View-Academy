# Search Command

The **SEARCH** command runs a search on Elasticsearch for specific LU tables and columns and returns a result set with the search results.

#### Usage: 

```
SEARCH lutype=<LUT_Name> TABLES=<tables names> AS_JSON=<false/true> '<Search Query>';   
```

- The **TABLES** parameter can be populated by one or several LU tables, separated by a comma. Several LU tables can be included in a search only if they have the same list of Search fields (search indexes).       
- The **Search Query** parameter is populated by a JSON with Elasticsearch query.
- The **AS_JSON** parameter (when set to true) returns a table with only one row and one column named "json" that contains the original elastic search API JSON payload response. This option can be used when looking for extra information returned by an elastic search API that is not retrieved using the Fabric search command parsing mechanism. Default value: false. 
- The **SEARCH** command returns the records that match the search query. The following information is displayed for each record:
  - LUI of each record.
  - List of Search fields, defined in the specified LU table.
  - Score returned by Elasticsearch.

## Search Command - Examples

The following contains examples of SEARCH commands for common use cases. 

Note that the SEARCH command can run any search query, supposed by Elasticsearch.

- To see the full list of search queries supported by the Elasticsearch in the Elasticsearch website: 

https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl.html 

### 1. Pattern Matching

- #### Example

  - Tal* = Tali = Talilla

- #### Search Index Type

  - keyword

- #### Elasticsearch Method

  - query_string

- #### Search Example

  - Search lutype=CUSTOMER tables=CUSTOMER '{ "query": { "query_string": { "fields": ["FIRST_NAME"], "query": "Tal*" } } }';

### 2. Identical Values, Case Sensitive Check

- #### Example

  - Tali = Tali
  - Tali != tali

- #### Search Index Type

  - keyword

- #### Elasticsearch Method

  - match/match_phrase

- #### Search Example

  - search lutype=CUSTOMER tables=CUSTOMER '{"query":  {"match_phrase" : { "FULL_NAME.keyword": {"query" :  "Waneta Hensley"}}}}';    
  - search lutype=CUSTOMER tables=CUSTOMER '{"query":  {"match" : { "FIRST_NAME.keyword": {"query" :  "Tali"}}}}';

### 3. Identical Values,  Support Special Characters,  Case Insensitive Match

- #### Examples
  - Tali = tali = TALI 
  - Golf&Co = golf&co
  - tali.einhorn@k2view.com = Tali.Einhorn@k2view.com
  - tali.einhorn@k2view.com != tali.einhorn@gmail.com	

- #### Search Index Type
  - case-insensitive-match	

- #### Fabric Implementation Guidelines

  - Add a [case-insensitive-match](02_search_implementation.md#search-index-types-templates) template to the Search options in the .k2proj file and select it as the type for the Search field. This setting is needed to enable case insensitive searches on the full email address. By default (text) - the @ and . chars split the tokens. Therefore, when using the default setting, the search command matches tali@gmail.com to tali@k2view.com.

- #### Elasticsearch Method
  - match

- #### Search Example

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"match" : { "MAIL": {"query" : "tali.einhorn@k2view.com"}}}}';

### 4. Fuzzy Search, Ignore Typos

- #### Examples
  - Ludwigshafern = Ludwigshafen
  - tali = taly
  - 12345 = 12346 = 1234
  - Only one digits is different in the searched field:
    - 12345 = 12346 = 15345
    - 12345 != 12366

- #### Search Index Type	
  - keyword

- #### Elasticsearch Method
  - query + match + fuzziness

- #### Search Examples

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"match" : {  "FIRST_NAME": {"query" : "Christina",  "fuzziness": "1" }}}}';
  -  search lutype=CUSTOMER tables ADDRESS  ''{"query": {"match" : {"CITY": {"query" : "Ludwigshafern",  "fuzziness":  "2" } } }}';
  - search lutype=CUSTOMER tables ADDRESS  ''{"query": {"match" : {"CITY": {"query" : "Ludwigshafern",  "fuzziness":  "2", "transpositions": false } } }}';
  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"fuzzy" : {"ZIP": {"value": "12345", "fuzziness": 1, "transpositions": false }}}}';
  - search lutype=CUSTOMER tables=CUSTOMER '{"query": { "fuzzy" : { "NAME": { "value": "Tali", "fuzziness": 1, "transpositions": false } } }}';

- #### Notes

  - The **fuzziness** parameter defines the number of mistakes (typos) allowed. This parameter can support up to two mistakes.

  - By default, a swap of two adjacent characters (Orange vs. Ornage) is considered as an error by the **fuzziness** parameter. However, if you add the **transpositions** parameter to the search query and set it to **false**, the swap of two characters is considered as two mistakes.

    Note that when the transposition parameter is set to false, two adjacent characters are considered as two mistakes. By default, this parameter is set to true.

### 5. Fuzzy Search on Special Characters, Case Insensitive Match

- #### Examples

  - tali.einhorn@k2view.com = TALI.EINHON@k2view.com
  - tali.einhorn@k2view.com != tali.einhorn@yahoo.com
  - David&Co = david&co = davi@CO

- #### Search Index Type

  - case-insensitive-match

- #### Fabric Implementation Guidelines

  - Add a [case-insensitive-match template](02_search_implementation.md#search-index-types-templates) to the Search options in the .k2proj file and select it as the type for the Search field. This setting is needed to enable a case insensitive search on the full email address. By default (text), the @ and . chars split the tokens. Therefore, when using  the default setting, the search command matches tali@gmail.com to tali@k2view.com.

- #### Elasticsearch Method

  -  match + fuzziness

- #### Search Examples

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"match" : { "MAIL": {"query" : "tali.einhon@k2view.com", "fuzziness": "1"}}}}';
  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"match" : { "FIRST_NAME": {"query" : "David&Co", "fuzziness": "1"}}}}';

### 6. Partial Match, Ignore Additional Information

- #### Examples

  - Washington Rd = Washington xx Rd

- #### Search Index Type

  - keyword

- #### Fabric Implementation Guidelines

  - This matching rule only works for Text columns. If the LU table's column is set to another data type, the matching rule will not work.

- #### Elasticsearch Method Options

  - **match_phrase + slop**. The value of the **slop** parameter defines the number of words that can be located between the words of the search. The default of the slop is zero.
  - Set the **match** method on each one of the required words. 
  - Note that unlike the **match** method, the **match_phrase** does not support the use of fuzziness. To use fuzziness, use the **match** option.

- #### Search Examples

  - match_phrase query
    - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"match_phrase" : { "ADDRESS": {"query" : "Washington Rd", "slop": "2"}}}}';
      search lutype=CUSTOMER tables=CUSTOMER '{"query": {"bool": {"must": [{"match" : { "ADDRESS": {"query" : "Washington", "fuzziness": "1"}}},{"match" : { "ADDRESS": {"query" : "Rd", "fuzziness": "1"}}}]}}}';
  - match query
    - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"bool": {"must": [{"match" : { "ADDRESS": {"query" : "Washington", "fuzziness": "1"}}},{"match" : { "ADDRESS": {"query" : "Rd", "fuzziness": "1"}}}]}}}';

### 7. Ignoring Leading or Trailing Blank Characters

- #### Examples

  - '1234' = ' 1234' = '1234 ' 

- #### Search Index Type

  - keyword

- #### Fabric Implementation Guidelines

  - This matching rule only works for Text columns. If the LU table's column is set to another data type, the matching rule will not work.

- #### Elasticsearch Method

  - match

- #### Search Examples

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"match" : { "FIRST_NAME": {"query" : "Waneta"}}}}';
  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"match" : { "ID_NUMBER": {"query" : "1234"}}}}';

### 8. Transposed Digits - Partial Match

- #### Description

  - At least x characters must exist in the searched field and at least y% of them must be in the right order.

- #### Examples

  - At least 4 digits must exist in the searched field and at least 50% of them must be in the right order.
    - 1234 = 1243 = 56347712
    - 1234 != 7777712
    - 1234 != 8751883724
    - 123456 = 1246 = 9230056 = 1243
  - At least 6 digits must exist  in the searched field and at least 3 of them must be in the right order.
    - 123456 = 1238888654 = 9994560321
    - 123456 != 1238888
    - 123456 != 654321

- #### Search Index Type

  - precision-match-20

- #### Fabric Implementation Guidelines

  - Add a [precision-match-20](02_search_implementation.md#search-index-types-templates) template to the Search options in the .k2proj file and select it as the type for the search field.

- #### Elasticsearch Method

  - bool + must + N-gram tokenizers

- #### Search Examples

  - At least 4 digits must exist in the searched field and at least 50% of them must be in the right order:
    - search lutype=ACCOUNT tables=ACCOUNT '{"query":{"bool":{ "must":[{"match":{"ACCOUNT_NUMBER.1gram":{ "query":"123456", "minimum_should_match":4}}},{"match":{"ACCOUNT_NUMBER.2gram":{"query":"123456","minimum_should_match":1 }}} ]}}}';
  - At least 6 digits must exist  in the searched field and at least 3 of them must be in the right order:
    - search lutype=ACCOUNT tables=ACCOUNT '{"query":{"bool":{"must":[ {"match":{"ACCOUNT_NUMBER.1gram":{"query":"86422233", "minimum_should_match":6 }}}, {"match":{"ACCOUNT_NUMBER.3gram":{"query":"86422233", "minimum_should_match":1 }}} ] }}}';

### 9. Partial Match, Ignore a Limited Number of Additional Digits or Letters

- #### Example

  - At least 4 digits exist in the searched field, but the searched field must not have more than two additional digits or letters.
    - 123456 = 1234 = 1234 AB = 3456 = 345699
    - 123456 != 1234567
    - 123456 != 3456890
    - 123456 != 34599

- #### Search Index Type

  - precision-match-20

- #### Fabric Implementation Guidelines

  - Add a [precision-match-20](02_search_implementation.md#search-index-types-templates) template to the Search options in the .k2proj file and select it as the type for the Search field.

- #### Elasticsearch Method

  - bool + must + fuzziness +  N-gram tokenizers
  - Note that a fuzzy search supports up to two mistakes.

- #### Search Example

  - search lutype=ACCOUNT tables=ACCOUNT '{ "query":{ "bool":{"must":[{ "match":{"ACCOUNT_NUMBER.1gram":{"query":"123456", "minimum_should_match":4}}}, { "fuzzy":{"ACCOUNT_NUMBER":{"value":"123456", "fuzziness":"2", transpositions":false }}} ] }}}';

### 10. Partial Match, Ignore a Limited Number of Missing Digits or Letters

- #### Example

  - At least 6 digits exist in the searched field, but the searched field must not have more than two missing digits or letters.
    - 12345678 = 123456 = 3456789
    - 123456890 = 12345688
    - 12345678 != 12330000
    - 876543212 != 876543

- #### Search Index Type

  - precision-match-20

- #### Fabric Implementation Guidelines

  - Add a [precision-match-20](02_search_implementation.md#search-index-types-templates) template to the Search options in the .k2proj file and select it as the type for the Search field.

- #### Elasticsearch Method

  - bool + should + fuzziness +  N-gram tokenizers
  - Note that a fuzzy search supports up to two mistakes.

- #### Search Example

  - search lutype=ACCOUNT tables=ACCOUNT '{ "query":{"bool":{"should":[ { "match":{ "ACCOUNT_NUMBER.1gram":{"query":"12345678", "minimum_should_match":6 }}} {"match":{"ACCOUNT_NUMBER.1gram":{"query":"12345678", "minimum_should_match":7 }}} {"match":{"ACCOUNT_NUMBER.1gram":{"query":"12345678", "minimum_should_match":8}}}{"fuzzy":{"ACCOUNT_NUMBER":{"value":"12345678", "fuzziness":"2", "transpositions":false }}} ] }}}';

### 11. Match by Several Fields 

- #### Examples

  - NAME = 'EINHORN' and GIVEN_NAME = 'TALI' and BIRTH_DATE is 01-JAN-18 null and POSTAL_CODE = '63462' 
  - Check Register_type + register_number+ register_city + location 

- #### Search Index Type

  - keyword/date

- #### Elasticsearch Method

  - bool + must + match
  - bool + must + query_string

- #### Search Example

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"bool": {"must": [{"match" : { "FIRST_NAME": {"query" : "Marget", "fuzziness": "1"}}},{"match" : {"LAST_NAME":  {"query" : "Lane", "fuzziness": "1"}}}, {"match": {"CITY": {"query" : "ROSCO", "fuzziness": "1"}}}, {"match": {"DATE1.date": {"query" : "2018-01-01"}}}]}}}';

### 12. Match Cross Values of Multiple Fields 

- #### Examples

  - NAME = Tali, FAMILY_NAME = Einhorn
  - NAME = Einhorn, FAMILY_NAME = Tali

- #### Search Index Type

  - keyword

- #### Elasticsearch Method

  - bool + must + multi_match.
  - Note that when using the **must** method, all conditions must match. However, when using the **should**  method you can set a **minimum_should_match** parameter to define how many conditions need to match the search query.

- #### Search Example

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"bool": {"must": [{ "multi_match": { "query": "Tali", "fields": ["NAME", "FAMILY_NAME"] } }, { "multi_match": { "query": "Einhorn", "fields": ["NAME", "FAMILY_NAME"] } } ] }}}';
  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"bool": {"must": [{"multi_match": {"query": "Cris", "fields": ["FIRST_NAME", "LAST_NAME"]}}, {"multi_match": {"query": "Michael", "fields": ["FIRST_NAME", "LAST_NAME"]}}]}}}';

### 13. Support Scoring for Matching

- #### Examples
  - Full match - Ludwigshafern = Ludwigshafern - gets a scoring of 1.
  - Partial match - 1234 AB  = 1234 = 123456  - gets a scoring of 0.9. 
  - Ignoring spelling errors or additional information -  Ludwigshafern = Ludwigshafen - gets  a scoring of 0.85.

- #### Search Examples

  - search lutype=CUSTOMER tables=CUSTOMER '{"query":{"dis_max":{"queries":[ {"function_score":{"query":{"match":{"MAIL":"SyhKHckZ@gmail.com"}},"script_score":{"script":{"source":"0.9"}}, "score_mode":"max","boost_mode":"replace"}},{"function_score":{ "query":{ "match":{"MAIL":"SyhKHckZ@gmail.com"}}, "script_score":{ "script":{ "source":"1"}}, "score_mode":"max", "boost_mode":"replace"}}, {"function_score":{"query":{"match":{"MAIL":{"query": "SyhKHckZ@gmail.com", "fuzziness": 2}}}, "script_score":{"script":{"source":"0.85" }}, "score_mode":"max", "boost_mode":"replace" }} ] }}}';

### 14. Support Predictive Search

- #### Examples

  - When the user types 'Ber',  complete the value to 'Berlin' or 'Berry'.

- #### Search Index Type

  - predictive-search

- #### Fabric Implementation Guidelines

  - Add a [predictive-search](02_search_implementation.md#search-index-types-templates) template to the Search options in the .k2proj file and select it as the type for the search field.

- #### Elasticsearch Method

  - bool + query_string + fuzziness
  - Note that a fuzzy search supports up to two mistakes.

- #### Search Examples

  - Date fields:
    - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"bool": {"must": [{"query_string": { "fields": ["DATE1"], "query" : "2018", "fuzziness": "1"}},{"query_string": { "fields": ["DATE1"], "query" : "09", "fuzziness": "1"}}]}}}';
  - Text fields:
    - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"bool": {"must": [{"query_string": { "fields": ["ADDRESS"], "query" : "Washington", "fuzziness": "1"}},{"query_string": { "fields": ["ADDRESS"], "query" : "R*", "fuzziness": "1"}}]}}}';

### 15. Match a Different Order of Words in a Field

- #### Examples

  - Chris Michael = Michael Chris

- #### Search Index Type

  - keyword

- #### Elasticsearch Method

  - match_phrase + slop

- #### Search Example

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"match_phrase" : {"FULL_NAME": {"query": "Chris Michael", "slop": 2 }}}}';

### 16. Check Date Range

- #### Examples

  - Search date fields when the date is between 1-Jan-20 to 1-Sep-20.

- #### Search Index Type

  - keyword

- #### Elasticsearch Method

  - range

- #### Search Example

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"range" : {"DATE1.date" : { "gt" : "2020-01-01", "lte" : "2020-09-01"}}}}';

### 17. Check Date Value

- #### Examples

  - 2003-03-04 00:05:30 = 2003/03/04 00:05:30 = 2003-MAR-04 00:05:30
  - 2019-01-20 = 20-JAN-2019 = 2019/01/20

- #### Search Index Type

  - date

- #### Elasticsearch Method

  - query_string

- #### Search Example

  - search lutype=CUSTOMER tables=CUSTOMER '{"query": {"query_string": { "fields": ["DATE1.date"], "query": "2018-08-02"}}}';

### 18. Check Empty String or Null Values

- #### Examples

  - Tali = ""
  - ID number = 156646767 and (birth date = '2000-01-01' or birth date is null)

- #### Search Index Type

  - keyword/date
  - Note that empty String is kept as a null value in the Elasticsearch.

- #### Elasticsearch Method

  - bool + should + must_not + exist

- #### Search Examples

  - Date field:
    - search lutype=CUSTOMER_ES tables=VISITS '{"query": {"bool": {"should": [{"bool": {"must_not": {"exists": {"field": "VISIT_DATE"}}}}, {"match": {"VISIT_DATE.date": {"query": "2008-02-29"}}}]}}}';
  - Keyword field:
    - search lutype=CUSTOMER_ES tables=CUSTOMER '{"query": {"bool": {"should": [{"bool": {"must_not": {"exists": {"field": "CITY"}}}}, {"match": {"CITY": {"query": "JERUSALEM", "fuzziness": "2"}}}]}}}';





[![Previous](/articles/images/Previous.png)](04_search_templates.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_search_solution_limitations.md)




