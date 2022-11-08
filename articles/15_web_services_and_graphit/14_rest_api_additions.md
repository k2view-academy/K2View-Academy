# Web Services Additions

## URL Redirect

Browser-submitted URLs can be manipulated and translated to deliver content to the browser. This process takes place entirely on the server-side, without the browser's knowledge whereby the resulting content appears to be originated from the submitted URL.

For example:

A user may ask for http://www.somesite.com/widgets/blue/, but receives http://www.somesite.com/widgets.php?colour=blue from the server and will not be aware of the difference.

### Configuration

Copy the **rewrite.config** file to **$FABRIC_HOME\webserver\WEB-INF**.

To use this functionality, users must have at least a basic knowledge of Rewrite rules/conditions and their different parameters. 

Useful tutorials: 

- https://tomcat.apache.org/tomcat-8.0-doc/rewrite.html.

- https://www.sitepoint.com/apache-mod_rewrite-examples-2/.

- http://helpful.knobs-dials.com/index.php/Apache_config_and_.htaccess_-_URL_rewriting.



## Response Formats 

JSON (default), XML and CSV formats are supported for data returned in the body of the response. This applies to all HTTP methods that return a response body. Yet CSV format is not supported for complex response formats. 

The response format can be defined in several ways: 

- Making a request without specifying the response format, resulting in the default JSON format. 
- Using the reserved **Format** query string parameter in the URI when making a request. Set the **Format** to **XML** by adding **“format=xml”** to the query string portion of the request (the key-value pair data after the “?”), in addition to any other query string parameters also in the URI.
- Using the **Format** parameter in the HTTP request header.



## URL Encoding

In general, URIs allow only ASCII values. However, there are specific cases like internationalized domain names (IDN), where non-ASCII characters may be used in the domain name. For the purposes of communicating data using query string parameters, you cannot directly send non-ASCII (unsafe) characters. Also, some characters like spaces, “=”, and “&” have a specific meaning when sent in the query string section of the URI and are reserved. 

To handle unsafe characters and to distinguish between data and reserved characters that have special meaning in a URI, the URI must be URL Encoded. This encoding replaces non-ACII and reserved characters parameter data with ASCII equivalents. This is also known as Percent Encoding, since each unsafe character is replaced with a value starting with percent sign (“%”).
All parameter values should be URL encoded to ensure correct transmission. For example, the query string: “name=Mañana” is URL encoded as “name= %20Ma%C3%B1ana”. 
A URI cannot have a space and is encoded to the value “%20”. 
The Spanish letter “ñ” is not a valid ASCII value and is encoded as “%C3%B1”. 
Once the data reaches the server, it is decoded back to the original characters. The key portion of each parameter is determined by the application, and therefore, will never contain unsafe characters. 
The same parameter can be repeated within the query string. However, only the final occurrence of the parameter is used to obtain a value. For example, given the query string “?code=A&code=B”, the interpreted value of the “code” parameter will be “B”. The “A” value is discarded. 

There is no use case for transmitting repeated parameters since the required result is achieved through other module-specific query string mechanisms.



## Request Data Encoding

By default, UTF-8 is used to decode the request body as this handles the majority of characters for the supported languages. However, in situations where customers choose to use a different encoding, it can be specified in the Content-Type header’s optional “charset” parameter: Content-Type: application/json; charset=latin-1 will use the provided charset to decode the request body data. 

Users are responsible for ensuring that their data is correctly encoded using the required charset before transmission to K2View API. Failure to do so may result in incorrect characters or an inability to process the request. It is also important to note that this only applies to the encoding of the request body and does not apply to the encoding used in any response body data.



## Response Data Encoding 

When a response body is returned, the raw JSON or XML data is always encoded using UTF-8. The response body’s encoding cannot be configured or specifed. This is done to ensure that the response content can always be correctly rendered. A request body using a different encoding is allowed, as the requester can control the sent contents. However, the output data may contain characters that are not  part of the encoding used for the request, if for example, a consistent character set has not been used throughout the application. UTF-8 covers the full change of characters and is therefore the default, and generally preferred, encoding.



## Pagination

Not supported.



## Order of Data

The order of data cannot be consistent since it is performance consumed, unless it is specified in the URL QUERY parameter. The parameter ORDER_BY must be added.



## Override Response

In order to override the RESTful API response that is automatically generated, use **HttpServletResponse** Class.

For Example:

```java
HttpServletResponse response = response();
response.setStatus(201);
```
Or
```java
HttpServletResponse response = response();
response.setHeader(String,String);
```



## Generate HTML Format or Legacy JSON/XML

To generate these formats, open the Java Logic file and add the following tag above the public class, which encapsulates the web services defined in the specific Logic category:

```java 
@legacy
public class Logic extends WebServiceUserCode {...}
```

Then add the UNKNOWN value to the Produce function call in the line where the web service is defined: 

```java 
@webService(path = "test/getCustomerInfo", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.UNKNOWN})
```

Modify the Properties panel of the web service with Produce = "UNKNOWN" and Return Type = "Object". 

After deploying the web service, call it from the browser using the appropriate token, parameter and format.

An example with an HTML format:

```html 
http://localhost:3213/api/test/getCustomerInfo?ID=1000&token=tgreg&format=html
```

The response is displayed in the body of the browser's web page:
<img src="images/Web-Service-KI-2-1_Produce2.PNG">





[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/13_custom_ws_java_annotations.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/15_Fabric_smart_proxy.md)

