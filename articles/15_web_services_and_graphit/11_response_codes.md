# Response Codes

K2view APIs use many of the available HTTP response status codes to convey success or failure of the request back to the user. All response status codes fall into the following categories:
*	[**1xx Informational**](/articles/15_web_services_and_graphit/11_response_codes.md#1xx-informational), the request has been received and is being processed.
*	[**2xx Successful**](/articles/15_web_services_and_graphit/11_response_codes.md#200), the request has been successfully received, understood and accepted.
*	[**3xx Redirection**](/articles/15_web_services_and_graphit/11_response_codes.md#3xx-redirection), further action needs to be taken to complete the request.
*	[**4xx Client Error**](/articles/15_web_services_and_graphit/11_response_codes.md#4xx-client-error), the request contains bad syntax or cannot be fulfilled.
*	[**5xx Server Error**](/articles/15_web_services_and_graphit/11_response_codes.md#5xx-server-error), the server failed to fulfill an apparently valid request.

#### 1xx: Informational

The request has been received and is being processed**. 

The 1xx Informational status code indicates an interim response for communicating a connection status or requesting progress prior to completing the requested action and sending a final response: 
*	1xx responses are terminated by the first empty line after the status line (the empty line signaling the end of the header section).
*	Since HTTP/1.0 has not defined any 1xx status codes, a server MUST NOT send a 1xx response to an HTTP/1.0 client.
*	A client MUST be able to parse one or more 1xx responses received prior to a final response, even if the client does not expect one. 
*	A user agent MAY ignore unexpected 1xx responses. 
*	A proxy MUST forward 1xx responses unless the proxy itself requested the generation of the 1xx response. For example, if a proxy adds an **Expect: 100-continue** field when it forwards a request, it need not forward the corresponding **100 Continue** response(s). – **NOT IN USE IN FABRIC.**

#### 3xx: Redirection 

Further action needs to be taken to complete the request. 

The 3xx Redirection status code indicates that further action needs to be taken by the user agent to fulfill the request. 
*	If a Location header field is provided, the user agent MAY automatically redirect its request to the URI referenced by the Location field value, even if the specific status code is not understood. 
*	Automatic redirection must be implemented carefully for methods known to be unsafe, since the user might not wish to redirect an unsafe request. 

#### 4xx: Client Error 

The request contains bad syntax or cannot be fulfilled. 

The 4xx Client Error status code indicates that the client seems to have erred. Apart for when responding to a HEAD request, the server SHOULD send a presentation containing an explanation of the error and whether it is a temporary or permanent condition. 
*	These status codes apply to any request method. 
*	User agents SHOULD display any included representation to the user.

#### 5xx: Server Error 

The server failed to fulfill an apparently valid request. 

The 5xx Server Error status code indicates that the server is aware that it has erred or is incapable of performing the requested method. 
*	Except for when responding to a HEAD request, the server SHOULD send a representation with an explanation about the error and whether it is a temporary or permanent condition. 
*	A user agent SHOULD display any included notifications to the user. 
*	These response codes apply to any request method.

<table width="900pxl">
<tbody>
<tr>
<td width="100pxl" valign="top" >
<p><Strong>Response Code</Strong></p> 
</td>
<td width="100pxl" valign="top" >
<p><Strong>Message</Strong></p>
</td>
<td width="200pxl" valign="top" >
<p><Strong>HTTP verb</Strong></p>
</td>
<td width="500pxl" valign="top" >
  <p><Strong>Description</Strong></p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
  <p><h5>200</h5></p>
</td>
<td width="100pxl" valign="top" >
<p>OK</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 200 (OK) status code indicates that the request has succeeded.&nbsp;The payload is sent in a 200 response depending on the request method. For the methods defined by this specification, the intended meaning of the payload can be summarized as:</p>
<p>GET a representation of the target resource;</p>
<p>POST a representation of the status of, or results obtained from, the action;</p>
<p>PUT, DELETE a representation of the status of the action;</p>
<p>Aside from responses to CONNECT, a 200 response always has a payload, although an origin server MAY generate a payload body of zero length.</p>
<p>If no payload is required, an origin server should send 204 (No Content) instead. For CONNECT, no payload is allowed because the successful result is a tunnel, which begins immediately after the 200 response header section.</p>
<p>A 200 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>201</p>
</td>
<td width="100pxl" valign="top" >
<p>Created</p>
</td>
<td width="200pxl" valign="top" >
<p>POST/PUT</p>
</td>
<td width="500pxl" valign="top" >
<p>The 201 (Created) status code indicates that the request has been fulfilled and has resulted in one or more new resources being created.</p>
<p>The primary resource created by the request is identified by either a Location header field in the response or, if no Location field is received, by the effective request URI.</p>
<p>The 201 response payload typically describes and links to the resource(s) created. See&nbsp;<a href="https://tools.ietf.org/html/rfc7231#section-7.2">Section 7.2</a>&nbsp; of <strong>Hypertext Transfer Protocol (HTTP/1.1): Semantics and Content</strong> document for a discussion of the meaning and purpose of validator header fields, such as ETag and Last-Modified, in a 201 response.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>301</p>
</td>
<td width="100pxl" valign="top" >
<p>Moved Permanently</p>
</td>
<td width="200pxl" valign="top" >
<p>&nbsp;</p>
</td>
<td width="500pxl" valign="top" >
<p>The 301 (Moved Permanently) status code indicates that the target resource has been assigned a new permanent URI and any future references to this resource should use one of the enclosed URIs.</p>
<p>Clients with link-editing capabilities should automatically re-link references to the effective request URI to one or more of the new references sent by the server, where possible.</p>
<p>The server SHOULD generate a Location header field in the response containing a preferred URI reference for the new permanent URI.&nbsp;The user agent MAY use the Location field value for automatic redirection.&nbsp;The server's response payload usually contains a short hypertext note with a hyperlink to the new URI(s).</p>
<p>Note that for historical reasons, a user agent MAY change the request method from POST to GET for the subsequent request. If this behavior is undesired, the 307 (Temporary Redirect) status code can be used instead.</p>
<p>A 301 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>400</p>
</td>
<td width="100pxl" valign="top" >
<p>Bad Request</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 400 (Bad Request) status code indicates that the server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>401</p>
</td>
<td width="100pxl" valign="top" >
<p>Unauthorized</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 401 (Unauthorized) status code is returned from the application server when application security is enabled, and authorization information is missing from the request. For example, TOKEN is invalid.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>403</p>
</td>
<td width="100pxl" valign="top" >
<p>Forbidden</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 403 (Forbidden) status code indicates that the server understood the request but refuses to authorize it.</p>
<p>A server that wishes to make public why the request has been forbidden can describe that reason in the response payload (if any).</p>
<p>If authentication credentials were provided in the request, the server considers them insufficient to grant access.&nbsp;The client SHOULD NOT automatically repeat the request with the same credentials.</p>
<p>The client MAY repeat the request with new or different credentials. However, a request might be forbidden for reasons unrelated to the credentials.</p>
<p>An origin server that wishes to hide the current existence of a forbidden target resource MAY instead respond with a status code of 404 (Not Found). User lacks permission.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>404</p>
</td>
<td width="100pxl" valign="top" >
<p>Not Found</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 404 (Not Found) status code indicates that the origin server did not find a current representation for the target resource or is not willing to disclose that one exists.</p>
<p>A 404 status code does not indicate whether this lack of representation is temporary or permanent; the 410 (Gone) status code is preferred over 404 if the origin server knows, presumably through some configurable means, that the condition is likely to be permanent.</p>
<p>A 404 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>405</p>
</td>
<td width="100pxl" valign="top" >
<p>Method Not Allowed</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 405 (Method Not Allowed) status code indicates that the method received in the request-line is known by the origin server but not supported by the target resource.</p>
<p>The origin server MUST generate an Allow header field in a 405 response containing a list of the target resource's currently supported methods. Returned when the targeted resource does not support the requested HTTP method; for example, the functions resource only allows GET operations.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>406</p>
</td>
<td width="100pxl" valign="top" >
<p>Not Acceptable</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 406 (Not Acceptable) status code indicates that the target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request and the server is unwilling to supply a default representation.</p>
<p>The server SHOULD generate a payload containing a list of available representation characteristics and corresponding resource identifiers from which the user or user agent can choose the most appropriate.</p>
<p>A user agent MAY automatically select the most appropriate choice from that list.</p>
<p>The data format requested in the Accept header or accept parameter is not supported by the targeted resource. That is, the client has requested the data to be returned in a particular format, but the server is unable to return data in that format.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>408</p>
</td>
<td width="100pxl" valign="top" >
<p>Request timeout</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 408 (Request Timeout) status code indicates that the server did not receive a complete request message within the time that it was prepared to wait.</p>
<p>A server SHOULD send the "close" connection option, since 408 implies that the server has decided to close the connection rather than continue waiting.</p>
<p>If the client has an outstanding request in transit, the client MAY repeat that request on a new connection.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>413</p>
</td>
<td width="100pxl" valign="top" >
<p>Payload too large</p>
</td>
<td width="200pxl" valign="top" >
<p>&nbsp;</p>
</td>
<td width="500pxl" valign="top" >
<p>The 413 (Payload Too Large) status code indicates that the server is refusing to process a request because the requested payload is larger than the server is willing or able to process.</p>
<p>The server MAY close the connection to prevent the client from continuing the request.</p>
<p>If the condition is temporary, the server SHOULD generate a Retry-After header field to indicate that it is temporary and after what time the client MAY try again.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>414</p>
</td>
<td width="100pxl" valign="top" >
<p>URI too long</p>
</td>
<td width="200pxl" valign="top" >
<p>GET</p>
</td>
<td width="500pxl" valign="top" >
<p>The 414 (URI Too Long) status code indicates that the server refuses to service the request because the request-target is longer than the server is willing to interpret.</p>
<p>This rare condition is only likely to occur when a client has incorrectly converted a POST request to a GET request When the client has descended into a "black hole" of redirection (e.g., a redirected URI prefix that points to a suffix of itself) or when the server is under attack by a client attempting to exploit potential security holes.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>426</p>
</td>
<td width="100pxl" valign="top" >
<p>Upgrade required</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 426 (Upgrade Required) status code indicates that the server refuses to perform the request using the current protocol but might be willing to do so after the client upgrades to a different protocol.</p>
<p>The server MUST send an Upgrade header field in a 426 response to indicate the required protocol(s).</p>
<p>Example:</p>
<p>HTTP/1.1 426 Upgrade Required Upgrade:&nbsp;HTTP/3.0 Connection:&nbsp;Upgrade Content-Length: 53 Content-Type: text/plain</p>
<p>This service requires use of the HTTP/3.0 protocol.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>500</p>
</td>
<td width="100pxl" valign="top" >
<p>Internal server error</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 500 (Internal Server Error) status code indicates that the server encountered an unexpected condition that prevented it from fulfilling the request.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>503</p>
</td>
<td width="100pxl" valign="top" >
<p>Service Unavailable</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 503 (Service Unavailable) status code indicates that the server is currently unable to handle the request due to a temporary overload or scheduled maintenance, which will likely be alleviated after some delay.</p>
<p>The server MAY send a Retry-After header field to suggest an appropriate amount of time for the client to wait before retrying the request.</p>
<p>Note that the existence of the 503 status code does not imply that a server has to use it when becoming overloaded. Some servers might simply refuse the connection.</p>
</td>
</tr>
<tr>
<td width="100pxl" valign="top" >
<p><h5>505</p>
</td>
<td width="100pxl" valign="top" >
<p>HTTP Version Not Support</p>
</td>
<td width="200pxl" valign="top" >
<p>GET/POST/PUT/DELETE</p>
</td>
<td width="500pxl" valign="top" >
<p>The 505 (HTTP Version Not Supported) status code indicates that the server does not support, or refuses to support, the major version of HTTP that was used in the request message.</p>
<p>The server indicates that it is unable or unwilling to complete the request using the same major version as the client other than with this error message.</p>
<p>The server SHOULD generate a representation for the 505 response that describes why that version is not supported and what other protocols are supported by that server.</p>
</td>
</tr>
</tbody>
</table>


## Override Response Code

To override the response code, use the following code in the Web Service:

  ```
  - HttpServletResponse response = response();
  - response.setStatus(201);
  ```

  

[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/10_annotations.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/12_Supported_Verbs_Get.md)


