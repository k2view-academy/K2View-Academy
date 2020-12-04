# Invoke Remote API Calls

Data can be retrieved from remote services, for example; during an LUI sync to combine the data from an API with the data in the source DB or, to call and update a remote API. 

Note that we recommend using a CDC to call and update a remote API. For information, see [CDC](/articles/18_fabric_cdc/).

Data can also be retrieved via the following built-in Broadway actors and interfaces:
-  [Streams actors](/articles/19_Broadway/04_built_in_actor_types.md#streams), http and files.
-  [Parsers actors](/articles/19_Broadway/04_built_in_actor_types.md#parsers) for JSON, CSV and XML files.
-  [HTTP Interface](/articles/24_non_DB_interfaces/05_HTTP_interface.md#http-interface) and [SFTP Interface](/articles/24_non_DB_interfaces/02_SFTP_interface.md#sftp-interface). 

Remote API calls can be invoked in Fabric functions and other external source types or formats like a SOAP response.

An understanding of remote API protocols and their formats is required to interact with remote servers and parse their response. To do so, Fabric uses Java JAR libraries or generated code that  encapsulates these interactions into Java methods and objects.

For more information about external JAR libraries, click [here](/articles/31_external_resources/01_external_jars.md).

For examples refer to:

* [HTTP REST API Call with JSON response](/articles/31_external_resources/03_invoke_http_rest_call_example.md) 
* [HTTP SOAP Call](/articles/31_external_resources/04_invoke_soap_call_example.md) 



[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/31_external_resources/03_invoke_http_rest_call_example.md)
