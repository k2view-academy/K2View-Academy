# Fabric Smart Proxy

Fabric Web Services are REST APIs that, among other purposes, allow external access to Logical Unit's data. The Fabric Web Services are exposed through the Fabric Web Service layer. Fabric receives multiple requests concurrently, thus the Web Service response time is highly important. 

Fabric Smart Proxy mechanism allows to redirect the Web Service calls between multiple nodes based on the configuration of the web server filters. The logic behind establishing what node should be invoked is based on a built-in hash function, which is applied on the values of the Web Service input argument as well as the parameters of the web server filters. 

One of the most common reasons for using this mechanism is the need to optimize the LUI retrieval process, in order to reduce the Web Service response time. 
When LUI data is retrieved by the Web Service, Fabric uses the cache mechanism to load an instance into the memory. As the Fabric MDB cache files are stored on a server, it is more difficult to utilize the cache in a multi-node environment. This is due to the fact that each Web Service call might be redirected to a different node and consequently the cache will be stored on different servers. As a result, when the same Web Service is invoked again for the same instance ID, the cache is most likely not going to be reused. The Smart Proxy mechanism can solve this issue by redirecting the call to the same node when the same input is received and thus reduce the Web Service response time.

The Smart Proxy mechanism is off by default. It can be applied by setting the web server filters via the config.ini file as explained further in this article.

## How Do I Set Up Web Server Filters?

The Web Server filters in the config.ini are empty. To set up the filter, uncomment the default setting:

~~~
#WEBSERVER_FILTERS=
~~~

and populate it as follows:

~~~
WEBSERVER_FILTERS=[{"class":"com.k2view.cdbms.ws.ProxyAPI", "params":{"fabric_redirect":"SERVER", "fabric_retry":"1", "fabric_tokens":"token1, token2", "fabric_affinity":"", "read_timeout_sec":"60", "connect_timeout_sec":"60"}}]
~~~

The web server filter parameters are defined as follows:

* fabric_redirect is the process of forwarding a client from the requested URL to another URL. 
  * A CLIENT side redirect is a direct forward to a destination URL. The browser itself is doing the redirect. 
  * A SERVER side redirect means the server calls the WS and returns the response (back to back).
* fabric_retry allows to define a number redirect retries in case of a failure.
* fabric_tokens is a list of argument names to be used for redirect.
* fabric_affinity allows to define a subset of the nodes in a cluster, to choose a node for redirect.
* read_timeout_sec is a time that define the read timeout on the HTTP URL connection.
* connect_timeout_sec is the connection timeout to be set on the http URL connection.

All the parameters can keep their default value except for the fabric_tokens parameter which value must be replaced  from token1, token2 to the name(s) of the input argument(s) used by the Web Services. 

For example, when a Web Service has input parameter called **ID**, the filter should be set to:

~~~
WEBSERVER_FILTERS=[{"class":"com.k2view.cdbms.ws.ProxyAPI", "params":{"fabric_redirect":"SERVER", "fabric_retry":"1", "fabric_tokens":"ID", "fabric_affinity":"", "read_timeout_sec":"60", "connect_timeout_sec":"60"}}]
~~~

<img src="images/web-service-proxy.png" style="zoom:80%;" />

When a Web Service has several input arguments, all of them need to be listed as the fabric_tokens value.



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/16_rest_api_additions.md)
