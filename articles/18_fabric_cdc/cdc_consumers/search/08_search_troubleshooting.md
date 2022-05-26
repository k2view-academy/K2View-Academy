# Search - Troubleshooting



## Too Many Scroll Context Error

**Issue:**

Getting the following error when running migration that involves search queries on the Elasticsearch:  "Trying to create too many scroll contexts. Must be less than or equal to: [500]. This limit can be set by changing the [search.max_open_scroll_context] setting."

As a result the LUI sync fails on many of the LU instances . How can the  **max_open_scroll_context** size be extended? 

**Resolution:**

First you need to verify that your implementation code closes the search's result set. 

If true, and you certainly execute at up to 500 (the default max scroll contexts) concurrent search queries from all fabric nodes, then you can increase the scroll max context of the Elasticsearch using the following command:

```
curl -X PUT <Elasticsearch host>:9200/_cluster/settings -H 'Content-Type: application/json' -d'{
    "persistent" : {
        "search.max_open_scroll_context": 1024
    },
    "transient": {
        "search.max_open_scroll_context": 1024
    }
}'
```



## Unable to Parse Response Body Error

**Issue:**

The Elasticsearch throws the following exception:

```
org.elasticsearch.ElasticsearchStatusException: Unable to parse response body
```



**Resolution:**

This is a known issue in the elastic driver. Fabric 6.5.6 and onwards uses an advanced elastic driver version: 7.13.4, which doesn't have this issue.



[![Previous](/articles/images/Previous.png)](07_search_configuration.md)

