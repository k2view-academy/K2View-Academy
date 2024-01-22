# Fabric Smart Proxy

Fabric Web Services are REST APIs that, among other purposes, allow external access to Logical Unit's data. The Fabric Web Services are exposed through the Fabric Web Service layer. Fabric receives multiple requests concurrently, thus the Web Service response time is highly important. 

Fabric Smart Proxy mechanism allows to redirect the Web Service calls between multiple nodes based on the configuration of the web server filters. The logic behind establishing what node should be invoked is based on a built-in hash function, which is applied on the values of the Web Service input argument as well as the parameters of the web server filters. 

One of the most common reasons for using this mechanism is the need to optimize the LUI retrieval process, in order to reduce the Web Service response time. 
When LUI data is retrieved by the Web Service, Fabric uses the cache mechanism to load an instance into the memory. As the Fabric MDB cache files are stored on a server, it is more difficult to utilize the cache in a multi-node environment. This is due to the fact that each Web Service call might be redirected to a different node and consequently the cache will be stored on different servers. As a result, when the same Web Service is invoked again for the same instance ID, the cache is most likely not going to be reused. The Smart Proxy mechanism can solve this issue by redirecting the call to the same node when the same input is received and thus reduce the Web Service response time.

The Smart Proxy mechanism is off by default. It can be applied by the setting the Web Service annotations  as explained further in this article.

## How Do I Set Up the Smart Proxy?

Start from adding the ```smartProxy=true``` parameter on the WS input:

~~~java
@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, isCustomPayload = false, produce = {Produce.XML, Produce.JSON}, elevatedPermission = false)
public static Boolean ws1(@param(smartProxy=true) String in) throws Exception {
	UserCode.log.info("here" +in);
	fabric().execute("broadway k2_ws.pub value="+in);
	return true;
}
~~~

Then, the WS will be redirected to the same node as the first WS call.

When it is required to redirect the every call of this Web Service to the same node or the subset of nodes, add the ```affinity``` annotation as follows:

~~~java
@smartProxy(serverRedirect=true,affinity = "my_node")
@webService(path = "", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, isCustomPayload = false, produce = {Produce.XML, Produce.JSON}, elevatedPermission = false)
public static Boolean ws1(@param(smartProxy=true) String in) throws Exception {
	UserCode.log.info("here" +in);
	fabric().execute("broadway k2_ws.pub value="+in);
	return true;
}
~~~

The WS call will be redirected by the server: ```serverRedirect=true```.

Alternatively, the WS call can be redirected by the browser: ```serverRedirect=false```.

~~~java
@smartProxy(serverRedirect=false,affinity = "my_node")
~~~



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/14_rest_api_additions.md)
