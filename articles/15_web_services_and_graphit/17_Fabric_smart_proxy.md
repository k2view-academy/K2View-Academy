# Fabric Smart Proxy

Fabric Web Services are Java functions that allow, among other purposes, accessing the Logical Unit's data externally. The Fabric Web Services are exposed through the Fabric Web Service layer.

Since Fabric receives multiple requests concurrently, the Web Service response time is highly important. To optimize the LUI retrieval process, Fabric uses a cache mechanism which enables loading an instance into the memory faster.

[Click for more information about LU Storage and Cache Management.](/articles/32_LU_storage/02_storage_management.md)

Since the Fabric MDB cache files are stored on server, it is more difficult to utilize the cache in a multi-node environment. This is due to the fact that each Web Service call might be redirected to a different node and as a result, the cache will be stored in different servers. So, when the same Web Service is invoked for the same instance, the cache most likely is not going to be reused. 

Fabric Smart Proxy mechanism allows reusing the Fabric cache by the Web Services in a more efficient way. The main principle of the Smart Proxy mechanism is to redirect the Web Service call to the same node when the same input is received. The logic of how to establish which node should be invoked is based on a built-in hash function which is applied on the input argument value. It is achieved by setting the web server filters via the config.ini file and setting the fabric_tokens to the name of the input argument used by the Web Services.

## How Do I Setup Web Server Filters?

The Web Server filters are empty in the config.ini. To setup the filter, uncomment the default setting:

~~~
#WEBSERVER_FILTERS=
~~~

and populate it as follows:

~~~
WEBSERVER_FILTERS=[{"class":"com.k2view.cdbms.ws.ProxyAPI", "params":{"fabric_redirect":"SERVER", "fabric_retry":"1", "fabric_tokens":"token1, token2", "fabric_affinity":"", "read_timeout_sec":"60", "connect_timeout_sec":"60"}}]
~~~

Replace token1, token2 with the names of the Web Service input arguments.

For example, when a Web Service has input parameter called **ID**, the filter should be set to:

~~~
WEBSERVER_FILTERS=[{"class":"com.k2view.cdbms.ws.ProxyAPI", "params":{"fabric_redirect":"SERVER", "fabric_retry":"1", "fabric_tokens":"ID", "fabric_affinity":"", "read_timeout_sec":"60", "connect_timeout_sec":"60"}}]
~~~

<img src="images/web-service-proxy.png" style="zoom:80%;" />





[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/16_rest_api_additions.md)
