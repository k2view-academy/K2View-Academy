# Invoke Remote Servers Calls

### Overview

There are cases where it is required to combine data from different sources (such as a source DB, HTTP APIs or files) and load it into the target tables. Broadway already supports such capability as built-in [Streams actors (http & files)](/articles/19_Broadway/04_built_in_actor_types.md#streams) (an example of using it can be found [here](/academy/Broadway/Training_Level_1/12b_broadway_as_a_population_http.md#exercise-2---populate-an-lu-table-using-a-db-query-and-an-http-call)), along with some response [parsers actors](/articles/19_Broadway/04_built_in_actor_types.md#parsers) (JSON, CSV, XML) and associated to special interfaces ([HTTP Interface](/articles/24_non_DB_interfaces/05_HTTP_interface.md#http-interface) and [SFTP Interface](/articles/24_non_DB_interfaces/02_SFTP_interface.md#sftp-interface)). However, this capability is required also at Fabric functions. Moreover, in some cases other external source types or formats might be needed, for example SOAP response format.

Accessing to a remote server shall be sued using libraries which already familiar with those sources protocols and formats and encapsulate it to java methods and objects. See [here](/articles/31_external_resources/01_external_jars.md) for more information about using external JAR libraries.

Next you can see examples for invoking HTTP REST API with a JSON response and HTTP SOAP call.

* HTTP REST API Call with JSON response
* HTTP SOAP Call
* (SFTP)