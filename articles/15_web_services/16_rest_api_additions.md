# Rest API Additions

## Supported K2view Web Services URL Structure
http://IP address:PORT/api/[VERSION_NO]/[web-service name]?token=ABC&format=json

URL parameters, including the Token and Format, are supported on both the URL and header request.

## URL Redirect

Browser-submitted URLs can be manipulated and translated to deliver content to the browser. This process takes place entirely on the server-side and without the browser's knowledge. The resulting content appears to be originated from the submitted URL.

For example:

A user may ask for http://www.somesite.com/widgets/blue/, but will receive http://www.somesite.com/widgets.php?colour=blue from the server and will not be aware of the difference.

### Configuration

Copy rewrite.config file (sample file attached) to $FABRIC_HOME\webserver\WEB-INF

To use this functionality users must have at least basic knowledge of rewrite rules / conditions and their different parameters. 

Useful tutorials: 

- https://tomcat.apache.org/tomcat-8.0-doc/rewrite.html

- https://www.sitepoint.com/apache-mod_rewrite-examples-2/

- http://helpful.knobs-dials.com/index.php/Apache_config_and_.htaccess_-_URL_rewriting

## Authentication

A RESTful API should be stateless whereby the request authentication does not depend on cookies or sessions and each request arrives with authentication credentials.

By always using SSL, authentication credentials can be simplified to a randomly-generated access token that is delivered in the **Username** field of the HTTP Basic Auth. The great thing about this is that it's completely browser explorable - the browser will just pop up a prompt asking for credentials, if it receives a 401 Unauthorized status code from the server.

Token can be provided as a part of the URL, as a parameter, or on the header request.

To invoke a Web Service call, do the following:

1. Create a token, this token should be assigned to user if product built-in web-services are required (create token 'ABC' user 'admin').
2. Create a role (CREATE ROLE <'role_name'> description <"role description">).
3. Assign the role to a token (ASSIGN ROLE <'ROLE'> to token <'TOKEN'>).
4. Grant privilege's to the role (GRANT <'Operation'> ON <'RESOURCE'> TO <'ROLE'>).

[Click for more information about Web-Services authorization.](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#web-services-authorization)

## Response Formats 

Support of JSON (default), XML and CSV formats for data returned in the body of the response. This applies to all HTTP methods that return a response body. The requester is able to specify the response format in several ways: 

   *     Making a request without specifying the response format will result in a default JSON format. 

   *     Using the reserved “format” query string parameter in the URI when making a request. You can set the format to XML by adding “format=xml” to the query string portion of the request (the key-value pair data after the “?”). This is in addition to any other query string parameters also in the URI.

   *    Using format parameter on the HTTP request header.

## URL Encoding

In general, URIs allow only ASCII values. However, there are specific cases like internationalized domain names (IDN), where non-ASCII characters may be used in the domain name. For the purposes of communicating data using query string parameters, you cannot directly send non-ASCII (unsafe) characters. Also, some characters like spaces, “=”, and “&” have a specific meaning when sent in the query string section of the URI and are reserved. In order to handle unsafe characters and to distinguish between data and reserved characters that have special meaning in a URI, the URI must be “URL Encoded”. This encoding replaces non-ACII and reserved characters parameter data with ASCII equivalents. This is also known as “Percent Encoding” since each unsafe character is replaced with a value starting with percent sign (“%”). All parameter values should be URL encoded to ensure correct transmission. For example, the query string: “name=Mañana” is URL encoded as “name= %20Ma%C3%B1ana”. A URI cannot have a space so that is encoded to the value “%20”. The Spanish letter “ñ” is not a valid ASCII value and is encoded as “%C3%B1”. Once the data reaches the server, it is decoded back to the original characters. The key portion of each parameter is determined by the application, and therefore, will never contain unsafe characters. It is possible to repeat the same parameter within the query string. However, we will only observe the final occurrence of the parameter in order to obtain a value. For example, given the query string “?code=A&code=B”, the interpreted value of the “code” parameter will be “B”. The “A” value is discarded. There is no use case for transmitting repeated parameters as the desired result is achieved through other module-specific query string mechanisms.

## Request Data Encoding

By default, UTF-8 is used to decode the request body, as this handles the majority of characters for the supported languages. However, for situations where customers choose to use a different encoding, it can be specified in the Content-Type header’s optional “charset” parameter: Content-Type: application/json; charset=latin-1 will use the provided charset to decode the request body data. It is up to the user to ensure that their data is properly encoded using the desired charset before transmission to k2view API. Failure to do so may result in incorrect characters or an inability to process the request. It is also important to note that this only applies to the encoding of the request body and does not apply to the encoding used in any response body data.

## Response Data Encoding 

When a response body is returned, the raw JSON or XML data is always encoded using UTF-8. There is no way to configure or specify the response body’s encoding. This is done to ensure that the response content can always be correctly rendered. A request body using a different encoding is allowed, because the requester is able to control the contents being sent. However, the output data may contain characters outside of the encoding used for the request, if for example, a consistent character set has not been used throughout the application. UTF-8 covers the full change of characters and is therefore the default, and generally preferred, encoding.

## Pagination

Not supported.

## Order of Data

The order of data cannot be consistence since it is performance consumed, unless it is specified on the URL QUERY parameter. The parameter ORDER_BY must be added.

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/15_Supported_Verbs_Delete.md)


