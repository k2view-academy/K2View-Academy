# Invoke Remote Servers Calls

There are cases where there us a need to retrieve information from remote services, for example when it is required to combine data from different sources (such as a source DB, HTTP APIs or files) and load it into the target tables. On other cases we might required to call remote server to update it with some information.

Broadway already supports such capability with its built-in [Streams actors (http & files)](/articles/19_Broadway/04_built_in_actor_types.md#streams), along with some response [parsers actors](/articles/19_Broadway/04_built_in_actor_types.md#parsers) (for JSON, CSV, XML) . In addition Broadway supported special related interfaces ([HTTP Interface](/articles/24_non_DB_interfaces/05_HTTP_interface.md#http-interface) and [SFTP Interface](/articles/24_non_DB_interfaces/02_SFTP_interface.md#sftp-interface)). 

However, this capability is required also at Fabric functions. Moreover, in some cases other external source types or formats might be needed, for example SOAP response format.

Remote servers interactions require to be familiar with their protocols and formats, in order to call them properly and to be enabled parse their response.  For this, we shall use Java JAR libraries (or generated code) which encapsulate these interactions into java methods and objects. See [here](/articles/31_external_resources/01_external_jars.md) for more information about using external JAR libraries.

Next you can see examples for invoking HTTP REST API with a JSON response and HTTP SOAP call.

* [HTTP REST API Call with JSON response](/articles/31_external_resources/03_invoke_http_rest_call_example.md) 
* [HTTP SOAP Call](/articles/31_external_resources/04_invoke_soap_call_example.md) 



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/31_external_resources/03_invoke_http_rest_call_example.md)