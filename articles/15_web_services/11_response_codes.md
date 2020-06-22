# Response Codes

K2view APIs use many of the available HTTP response status codes to convey success or failure of the request back to the user. All response status codes fall into the following categories:
*	1xx Informational, the request has been received and is being processed.
*	2xx Successful, the request has been successfully received, understood and accepted.
*	3xx Redirection, further action needs to be taken to complete the request.
*	4xx Client Error, the request contains bad syntax or cannot be fulfilled.
*	5xx Server Error, the server failed to fulfill an apparently valid request.

**1xx (Informational)**

The request has been received and is being processed**. 

The 1xx Informational status code indicates an interim response for communicating a connection status or requesting progress prior to completing the requested action and sending a final response: 
*	1xx responses are terminated by the first empty line after the status line (the empty line signaling the end of the header section).
*	Since HTTP/1.0 did not define any 1xx status codes, a server MUST NOT send a 1xx response to an HTTP/1.0 client.
*	A client MUST be able to parse one or more 1xx responses received prior to a final response, even if the client does not expect one. 
*	A user agent MAY ignore unexpected 1xx responses. 
*	A proxy MUST forward 1xx responses unless the proxy itself requested the generation of the 1xx response. For example, if a proxy adds an **Expect: 100-continue** field when it forwards a request, it need not forward the corresponding 100 (Continue) response(s). – **NOT IN USE IN FABRIC.**

**3xx (Redirection)**

Further action needs to be taken to complete the request. 

The 3xx (Redirection) status code indicates that further action needs to be taken by the user agent to fulfill the request. 
*	If a Location header field is provided, the user agent MAY automatically redirect its request to the URI referenced by the Location field value, even if the specific status code is not understood. 
*	Automatic redirection must be implemented carefully for methods known to be unsafe, since the user might not wish to redirect an unsafe request. 

**4xx (Client Error)**

The request contains bad syntax or cannot be fulfilled. 

The 4xx (Client Error) status code indicates that the client seems to have erred. Except for when responding to a HEAD request, the server SHOULD send a notification containing an explanation of the error and whether it is a temporary or permanent condition. 
*	These status codes apply to any request method. 
*	User agents SHOULD display any included notifications to the user.

**5xx (Server Error)**

The server failed to fulfill an apparently valid request. 

The 5xx (Server Error) status code indicates that the server is aware that it has erred or is incapable of performing the requested method. 
*	Except for when responding to a HEAD request, the server SHOULD send a notification with an explanation about the error and whether it is a temporary or permanent condition. 
*	A user agent SHOULD display any included notifications to the user. 
*	These response codes apply to any request method.

<p>&nbsp;</p>
<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th>Response code</th>
<th>Message</th>
<th>HTTP verb</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>200</td>
<td>OK</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 200 (OK) status code indicates that the request has succeeded.&nbsp;The payload sent in a 200 response depending on the request method.</p>
<p>For the methods defined by this specification, the intended meaning of the payload can be summarized as:</p>
<p style="padding-left: 30px;">GET a representation of the target resource;</p>
<p style="padding-left: 30px;">POST a representation of the status of, or results obtained from, the action;</p>
<p style="padding-left: 30px;">PUT, DELETE a representation of the status of the action;</p>
<p>Aside from responses to CONNECT, a 200 response always has a payload, although an origin server MAY generate a payload body of zero length.</p>
<p>If no payload is required, an origin server should send 204 (No Content) instead. For CONNECT, no payload is allowed because the successful result is a tunnel, which begins immediately after the 200 response header section.</p>
<p>A 200 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td>201</td>
<td>Created</td>
<td>POST/PUT</td>
<td>
<p>The 201 (Created) status code indicates that the request has been fulfilled and has resulted in one or more new resources being created.</p>
<p>The primary resource created by the request is identified by either a Location header field in the response or, if no Location field is received, by the effective request URI.</p>
<p>The 201 response payload typically describes and links to the resource(s) created. See&nbsp;<a href="https://tools.ietf.org/html/rfc7231#section-7.2" rel="nofollow">Section 7.2</a>&nbsp;for a discussion of the meaning and purpose of validator header fields, such as ETag and Last-Modified, in a 201 response.</p>
</td>
</tr>
<tr>
<td>301</td>
<td>Moved Permanently</td>
<td>&nbsp;</td>
<td>
<p>The 301 (Moved Permanently) status code indicates that the target resource has been assigned a new permanent URI and any future references to this resource should use one of the enclosed URIs.</p>
<p>Clients with link-editing capabilities should automatically re-link references to the effective request URI to one or more of the new references sent by the server, where possible.</p>
<p>The server SHOULD generate a Location header field in the response containing a preferred URI reference for the new permanent URI.&nbsp;The user agent MAY use the Location field value for automatic redirection.&nbsp;The server's response payload usually contains a short hypertext note with a hyperlink to the new URI(s).</p>
<p>Note that for historical reasons, a user agent MAY change the request method from POST to GET for the subsequent request. If this behavior is undesired, the 307 (Temporary Redirect) status code can be used instead.</p>
<p>A 301 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td>400</td>
<td>Bad Request</td>
<td>GET/POST/PUT/DELETE</td>
<td>The 400 (Bad Request) status code indicates that the server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).</td>
</tr>
<tr>
<td>401</td>
<td>Unauthorized</td>
<td>GET/POST/PUT/DELETE</td>
<td>The 401 (Unauthorized) status code is returned from the application server when application security is enabled, and authorization information was missing from the request. For example, TOKEN is invalid.</td>
</tr>
<tr>
<td>403</td>
<td>Forbidden</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 403 (Forbidden) status code indicates that the server understood the request but refuses to authorize it.</p>
<p>A server that wishes to make public why the request has been forbidden can describe that reason in the response payload (if any).</p>
<p>If authentication credentials were provided in the request, the server considers them insufficient to grant access.&nbsp;The client SHOULD NOT automatically repeat the request with the same credentials.</p>
<p>The client MAY repeat the request with new or different credentials. However, a request might be forbidden for reasons unrelated to the credentials.</p>
<p>An origin server that wishes to hide the current existence of a forbidden target resource MAY instead respond with a status code of 404 (Not Found). User lacks permission.</p>
</td>
</tr>
<tr>
<td>404</td>
<td>Not Found</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 404 (Not Found) status code indicates that the origin server did not find a current representation for the target resource or is not willing to disclose that one exists.</p>
<p>A 404 status code does not indicate whether this lack of representation is temporary or permanent; the 410 (Gone) status code is preferred over 404 if the origin server knows, presumably through some configurable means, that the condition is likely to be permanent.</p>
<p>A 404 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td>405</td>
<td>Method Not Allowed</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 405 (Method Not Allowed) status code indicates that the method received in the request-line is known by the origin server but not supported by the target resource.</p>
<p>The origin server MUST generate an Allow header field in a 405 response containing a list of the target resource's currently supported methods. Returned when the targeted resource does not support the requested HTTP method; for example, the functions resource only allows GET operations.</p>
</td>
</tr>
<tr>
<td>406</td>
<td>Not Acceptable</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 406 (Not Acceptable) status code indicates that the target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request and the server is unwilling to supply a default representation.</p>
<p>The server SHOULD generate a payload containing a list of available representation characteristics and corresponding resource identifiers from which the user or user agent can choose the most appropriate.</p>
<p>A user agent MAY automatically select the most appropriate choice from that list.</p>
<p>The data format requested in the Accept header or accept parameter is not supported by the targeted resource. That is, the client has requested the data to be returned in a particular format, but the server is unable to return data in that format.</p>
</td>
</tr>
<tr>
<td>408</td>
<td>Request timeout</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 408 (Request Timeout) status code indicates that the server did not receive a complete request message within the time that it was prepared to wait.</p>
<p>A server SHOULD send the "close" connection option, since 408 implies that the server has decided to close the connection rather than continue waiting.</p>
<p>If the client has an outstanding request in transit, the client MAY repeat that request on a new connection.</p>
</td>
</tr>
<tr>
<td>413</td>
<td>Payload too large</td>
<td>&nbsp;</td>
<td>
<p>The 413 (Payload Too Large) status code indicates that the server is refusing to process a request because the requested payload is larger than the server is willing or able to process.</p>
<p>The server MAY close the connection to prevent the client from continuing the request.</p>
<p>If the condition is temporary, the server SHOULD generate a Retry-After header field to indicate that it is temporary and after what time the client MAY try again.</p>
</td>
</tr>
<tr>
<td>414</td>
<td>URI too long</td>
<td>GET</td>
<td>
<p>The 414 (URI Too Long) status code indicates that the server refuses to service the request because the request-target is longer than the server is willing to interpret.</p>
<p>This rare condition is only likely to occur when a client has incorrectly converted a POST request to a GET request When the client has descended into a "black hole" of redirection (e.g., a redirected URI prefix that points to a suffix of itself) or when the server is under attack by a client attempting to exploit potential security holes.</p>
</td>
</tr>
<tr>
<td>426</td>
<td>Upgrade required</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 426 (Upgrade Required) status code indicates that the server refuses to perform the request using the current protocol but might be willing to do so after the client upgrades to a different protocol.</p>
<p>The server MUST send an Upgrade header field in a 426 response to indicate the required protocol(s).</p>
<p>Example:</p>
<p style="padding-left: 30px;">HTTP/1.1 426 Upgrade Required Upgrade:&nbsp;HTTP/3.0 Connection:&nbsp;Upgrade Content-Length: 53 Content-Type: text/plain</p>
<p>This service requires use of the HTTP/3.0 protocol.</p>
</td>
</tr>
<tr>
<td>500</td>
<td>Internal server error</td>
<td>GET/POST/PUT/DELETE</td>
<td>The 500 (Internal Server Error) status code indicates that the server encountered an unexpected condition that prevented it from fulfilling the request.</td>
</tr>
<tr>
<td>503</td>
<td>Service Unavailable</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 503 (Service Unavailable) status code indicates that the server is currently unable to handle the request due to a temporary overload or scheduled maintenance, which will likely be alleviated after some delay.</p>
<p>The server MAY send a Retry-After header field to suggest an appropriate amount of time for the client to wait before retrying the request.</p>
<p>Note that the existence of the 503 status code does not imply that a server has to use it when becoming overloaded. Some servers might simply refuse the connection.</p>
</td>
</tr>
<tr>
<td>505</td>
<td>HTTP Version Not Support</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 505 (HTTP Version Not Supported) status code indicates that the server does not support, or refuses to support, the major version of HTTP that was used in the request message.</p>
<p>The server indicates that it is unable or unwilling to complete the request using the same major version as the client other than with this error message.</p>
<p>The server SHOULD generate a representation for the 505 response that describes why that version is not supported and what other protocols are supported by that server.</p>
</td>
</tr>
</tbody>
</table>


fdsljsda;fkjsadlf
<p>&nbsp;</p>
<table class="unchanged rich-diff-level-one">
<thead>
<tr>
<th>Response code</th>
<th>Message</th>
<th>HTTP verb</th>
<th>Description</th>
</tr>
</thead>
<tbody>
<tr>
<td>200</td>
<td>OK</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 200 (OK) status code indicates that the request has succeeded.</p>
<p>The payload sent in a 200 response depending on the request method.</p>
<p>For the methods defined by this specification, the intended meaning of the payload can be summarized as:</p>
<p>GET a representation of the target resource;</p>
<p>POST a representation of the status of, or results obtained from, the action;</p>
<p>PUT, DELETE a representation of the status of the action;</p>
<p>Aside from responses to CONNECT, a 200 response always has a payload, though an origin server MAY generate a payload body of zero length.</p>
<p>If no payload is required, an origin server should send 204 (No Content) instead.</p>
<p>For CONNECT, no payload is allowed because the successful result is a tunnel, which begins immediately after the 200 response header section.</p>
<p>A 200 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td>201</td>
<td>Created</td>
<td>POST/PUT</td>
<td>
<p>The 201 (Created) status code indicates that the request has been fulfilled and has resulted in one or more new resources being created.</p>
<p>The primary resource created by the request is identified by either a Location header field in the response or, if no Location field is received, by the effective request URI.</p>
<p>The 201 response payload typically describes and links to the resource(s) created. See&nbsp;<a href="https://tools.ietf.org/html/rfc7231#section-7.2" rel="nofollow">Section 7.2</a>&nbsp;for a discussion of the meaning and purpose of validator header fields, such as ETag and Last-Modified, in a 201 response.</p>
</td>
</tr>
<tr>
<td>301</td>
<td>Moved Permanently</td>
<td>&nbsp;</td>
<td>
<p>The 301 (Moved Permanently) status code indicates that the target resource has been assigned a new permanent URI and any future references to this resource should use one of the enclosed URIs.</p>
<p>Clients with link-editing capabilities should automatically re-link references to the effective request URI to one or more of the new references sent by the server, where possible.</p>
<p>The server SHOULD generate a Location header field in the response containing a preferred URI reference for the new permanent URI.</p>
<p>The user agent MAY use the Location field value for automatic redirection.</p>
<p>The server's response payload usually contains a short hypertext note with a hyperlink to the new URI(s).</p>
<p>Note that for historical reasons, a user agent MAY change the request method from POST to GET for the subsequent request. If this behavior is undesired, the 307 (Temporary Redirect) status code can be used instead.</p>
<p>A 301 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td>400</td>
<td>Bad Request</td>
<td>GET/POST/PUT/DELETE</td>
<td>The 400 (Bad Request) status code indicates that the server cannot or will not process the request due to something that is perceived to be a client error (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).</td>
</tr>
<tr>
<td>401</td>
<td>Unauthorized</td>
<td>GET/POST/PUT/DELETE</td>
<td>The 401 (Unauthorized) status code is returned from the application server when application security is enabled, and authorization information was missing from the request. For example, TOKEN is invalid.</td>
</tr>
<tr>
<td>403</td>
<td>Forbidden</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 403 (Forbidden) status code indicates that the server understood the request but refuses to authorize it.</p>
<p>A server that wishes to make public why the request has been forbidden can describe that reason in the response payload (if any).</p>
<p>If authentication credentials were provided in the request, the server considers them insufficient to grant access.</p>
<p>The client SHOULD NOT automatically repeat the request with the same credentials.</p>
<p>The client MAY repeat the request with new or different credentials. However, a request might be forbidden for reasons unrelated to the credentials.</p>
<p>An origin server that wishes to hide the current existence of a forbidden target resource MAY instead respond with a status code of 404 (Not Found). User lacks permission.</p>
</td>
</tr>
<tr>
<td>404</td>
<td>Not Found</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 404 (Not Found) status code indicates that the origin server did not find a current representation for the target resource or is not willing to disclose that one exists.</p>
<p>A 404 status code does not indicate whether this lack of representation is temporary or permanent; the 410 (Gone) status code is preferred over 404 if the origin server knows, presumably through some configurable means, that the condition is likely to be permanent.</p>
<p>A 404 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls.</p>
</td>
</tr>
<tr>
<td>405</td>
<td>Method Not Allowed</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 405 (Method Not Allowed) status code indicates that the method received in the request-line is known by the origin server but not supported by the target resource.</p>
<p>The origin server MUST generate an Allow header field in a 405 response containing a list of the target resource's currently supported methods. Returned when the targeted resource does not support the requested HTTP method; for example, the functions resource only allows GET operations.</p>
</td>
</tr>
<tr>
<td>406</td>
<td>Not Acceptable</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 406 (Not Acceptable) status code indicates that the target resource does not have a current representation that would be acceptable to the user agent, according to the proactive negotiation header fields received in the request and the server is unwilling to supply a default representation.</p>
<p>The server SHOULD generate a payload containing a list of available representation characteristics and corresponding resource identifiers from which the user or user agent can choose the most appropriate.</p>
<p>A user agent MAY automatically select the most appropriate choice from that list.</p>
<p>The data format requested in the Accept header or accept parameter is not supported by the targeted resource. That is, the client has requested the data to be returned in a particular format, but the server is unable to return data in that format.</p>
</td>
</tr>
<tr>
<td>408</td>
<td>Request timeout</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 408 (Request Timeout) status code indicates that the server did not receive a complete request message within the time that it was prepared to wait.</p>
<p>A server SHOULD send the "close" connection option, since 408 implies that the server has decided to close the connection rather than continue waiting.</p>
<p>If the client has an outstanding request in transit, the client MAY repeat that request on a new connection.</p>
</td>
</tr>
<tr>
<td>413</td>
<td>Payload too large</td>
<td>&nbsp;</td>
<td>
<p>The 413 (Payload Too Large) status code indicates that the server is refusing to process a request because the requested payload is larger than the server is willing or able to process.</p>
<p>The server MAY close the connection to prevent the client from continuing the request.</p>
<p>If the condition is temporary, the server SHOULD generate a Retry-After header field to indicate that it is temporary and after what time the client MAY try again.</p>
</td>
</tr>
<tr>
<td>414</td>
<td>URI too long</td>
<td>GET</td>
<td>
<p>The 414 (URI Too Long) status code indicates that the server refuses to service the request because the request-target is longer than the server is willing to interpret.</p>
<p>This rare condition is only likely to occur when a client has incorrectly converted a POST request to a GET request When the client has descended into a "black hole" of redirection (e.g., a redirected URI prefix that points to a suffix of itself) or when the server is under attack by a client attempting to exploit potential security holes.</p>
</td>
</tr>
<tr>
<td>426</td>
<td>Upgrade required</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 426 (Upgrade Required) status code indicates that the server refuses to perform the request using the current protocol but might be willing to do so after the client upgrades to a different protocol.</p>
<p>The server MUST send an Upgrade header field in a 426 response to indicate the required protocol(s).</p>
<p>Example:</p>
<p>HTTP/1.1 426 Upgrade Required Upgrade:&nbsp;HTTP/3.0 Connection:&nbsp;Upgrade Content-Length: 53 Content-Type: text/plain</p>
<p>This service requires use of the HTTP/3.0 protocol.</p>
</td>
</tr>
<tr>
<td>500</td>
<td>Internal server error</td>
<td>GET/POST/PUT/DELETE</td>
<td>The 500 (Internal Server Error) status code indicates that the server encountered an unexpected condition that prevented it from fulfilling the request.</td>
</tr>
<tr>
<td>503</td>
<td>Service Unavailable</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 503 (Service Unavailable) status code indicates that the server is currently unable to handle the request due to a temporary overload or scheduled maintenance, which will likely be alleviated after some delay.</p>
<p>The server MAY send a Retry-After header field to suggest an appropriate amount of time for the client to wait before retrying the request.</p>
<p>Note that the existence of the 503 status code does not imply that a server has to use it when becoming overloaded. Some servers might simply refuse the connection.</p>
</td>
</tr>
<tr>
<td>505</td>
<td>HTTP Version Not Support</td>
<td>GET/POST/PUT/DELETE</td>
<td>
<p>The 505 (HTTP Version Not Supported) status code indicates that the server does not support, or refuses to support, the major version of HTTP that was used in the request message.</p>
<p>The server indicates that it is unable or unwilling to complete the request using the same major version as the client other than with this error message.</p>
<p>The server SHOULD generate a representation for the 505 response that describes why that version is not supported and what other protocols are supported by that server.</p>
</td>
</tr>
</tbody>
</table>



asfasdfadsf



| Response code | Message                  | HTTP verb           | Description                                                  |
| ------------- | ------------------------ | ------------------- | ------------------------------------------------------------ |
| 200           | OK                       | GET/POST/PUT/DELETE | The 200 (OK) status code indicates that  the request has succeeded. The payload  sent in a 200 response depends on the request method. For the methods defined by this  specification, the intended meaning of the payload can be summarized as:  GET   a representation of the target resource;  POST   a representation of the status of, or results obtained from, the  action;  PUT, DELETE a representation of the status of the  action;  Aside from responses to CONNECT, a 200  response always has a payload, though an origin server MAY generate a payload  body of zero length. If no payload is desired, an origin server ought to send  204 (No Content) instead.   For CONNECT, no payload is allowed  because the successful result is a tunnel, which begins immediately after the  200 response header section.  A 200 response is cacheable by default;  i.e., unless otherwise indicated by the method definition or explicit cache  controls. |
| 201           | Created                  | POST/PUT            | The 201 (Created) status code indicates  that the request has been fulfilled and has resulted in one or more new  resources being created. The primary  resource created by the request is identified by either a Location header  field in the response or, if no Location field is received, by the effective  request URI.  The 201 response payload typically  describes and links to the resource(s) created. See [Section 7.2](https://tools.ietf.org/html/rfc7231#section-7.2) for a discussion of the meaning and purpose of validator header  fields, such as ETag and Last-Modified, in a 201 response. |
| 301           | Moved Permanently        |                     | The 301 (Moved Permanently) status code  indicates that the target resource has been assigned a new permanent URI and  any future references to this resource ought to use one of the enclosed URIs.  Clients with link-editing capabilities ought to automatically re-link  references to the effective request URI to one or more of the new references  sent by the server, where possible.  The server SHOULD generate a Location  header field in the response containing a preferred URI reference for the new  permanent URI. The user agent MAY use  the Location field value for automatic redirection. The server's response payload usually contains  a short hypertext note with a hyperlink to the new URI(s).  Note: For historical reasons, a user  agent MAY change the request method from POST to GET for the subsequent  request. If this behavior is  undesired, the 307 (Temporary Redirect) status code can be used instead. A 301 response is cacheable by default;  i.e., unless otherwise indicated by the method definition or explicit cache  controls. |
| 400           | Bad Request              | GET/POST/PUT/DELETE | The 400 (Bad Request) status code  indicates that the server cannot or will not process the request due to  something that is perceived to be a client error (e.g., malformed request  syntax, invalid request message framing, or deceptive request routing). |
| 401           | Unauthorized             | GET/POST/PUT/DELETE | The 401 (Unauthorized) status code Is  returned from the application server when application security is enabled,  and authorization information was missing from the request. For example,  TOKEN is invalid. |
| 403           | Forbidden                | GET/POST/PUT/DELETE | The 403 (Forbidden) status code  indicates that the server understood the request but refuses to authorize  it. A server that wishes to make  public why the request has been forbidden can describe that reason in the  response payload (if any).  If authentication credentials were  provided in the request, the server considers them insufficient to grant  access. The client SHOULD NOT  automatically repeat the request with the same credentials. The client MAY repeat  the request with new or different credentials. However, a request might be  forbidden for reasons unrelated to the credentials.  An origin server that wishes to  "hide" the current existence of a forbidden target resource MAY  instead respond with a status code of 404 (Not Found).  User lacks permission. |
| 404           | Not Found                | GET/POST/PUT/DELETE | The 404 (Not Found) status code  indicates that the origin server did not find a current representation for  the target resource or is not willing to disclose that one exists.   A 404 status code does not indicate  whether this lack of representation is temporary or permanent; the 410 (Gone)  status code is preferred over 404 if the origin server knows, presumably  through some configurable means, that the condition is likely to be  permanent. A 404 response is cacheable by default; i.e., unless otherwise  indicated by the method definition or explicit cache controls. |
| 405           | Method Not Allowed       | GET/POST/PUT/DELETE | The 405 (Method Not Allowed) status  code indicates that the method received in the request-line is known by the  origin server but not supported by the target resource. The origin server MUST generate an Allow  header field in a 405 response containing a list of the target resource's  currently supported methods.  Returned when the targeted resource  does not support the requested HTTP method; for example, the functions  resource only allows GET operations. |
| 406           | Not Acceptable           | GET/POST/PUT/DELETE | The 406 (Not Acceptable) status code  indicates that the target resource does not have a current representation  that would be acceptable to the user agent, according to the proactive  negotiation header fields received in the request , and the server is  unwilling to supply a default representation.  The server SHOULD generate a payload  containing a list of available representation characteristics and  corresponding resource identifiers from which the user or user agent can  choose the most appropriate. A user  agent MAY automatically select the most appropriate choice from that list.  The data format requested in the  Accept header or accept parameter is not supported by the targeted resource.  That is, the client has requested the data to be returned in a particular  format, but the server is unable to return data in that format. |
| 408           | Request timeout          | GET/POST/PUT/DELETE | The 408 (Request Timeout) status code  indicates that the server did not receive a complete request message within  the time that it was prepared to wait.   A server SHOULD send the "close" connection option, since  408 implies that the server has decided to close the connection rather than  continue waiting. If the client has an  outstanding request in transit, the client MAY repeat that request on a new connection. |
| 413           | Payload too large        |                     | The 413 (Payload Too Large) status  code indicates that the server is refusing to process a request because the  request payload is larger than the server is willing or able to process. The server MAY close the connection to  prevent the client from continuing the request.  If the condition is temporary, the  server SHOULD generate a Retry-After header field to indicate that it is  temporary and after what time the client MAY try again. |
| 414           | URI too long             | GET                 | The 414 (URI Too Long) status code  indicates that the server refuses to service the request because the  request-target is longer than the server is willing to interpret. This rare  condition is only likely to occur when a client has improperly converted a POST  request to a GET request with long query information, when the client has  descended into a "black hole" of redirection (e.g., a redirected  URI prefix that points to a suffix of itself) or when the server is under  attack by a client attempting to exploit potential security holes. |
| 426           | Upgrade required         | GET/POST/PUT/DELETE | The 426 (Upgrade Required) status code  indicates that the server refuses to perform the request using the current  protocol but might be willing to do so after the client upgrades to a different  protocol. The server MUST send an  Upgrade header field in a 426 response to indicate the required protocol(s).  Example:  HTTP/1.1 426 Upgrade Required  Upgrade: HTTP/3.0  Connection: Upgrade  Content-Length: 53  Content-Type: text/plain  This service requires use of the HTTP/3.0  protocol. |
| 500           | Internal server error    | GET/POST/PUT/DELETE | The 500 (Internal Server Error)  status code indicates that the server encountered an unexpected condition  that prevented it from fulfilling the request. |
| 503           | Service Unavailable      | GET/POST/PUT/DELETE | The 503 (Service Unavailable) status code  indicates that the server is currently unable to handle the request due to a  temporary overload or scheduled maintenance, which will likely be alleviated  after some delay. The server MAY send  a Retry-After header field to suggest an appropriate amount of time for the  client to wait before retrying the request.  Note: The existence of the 503 status code  does not imply that a server has to use it when becoming overloaded. Some servers might simply refuse the  connection. |
| 505           | HTTP Version Not Support | GET/POST/PUT/DELETE | The 505 (HTTP Version Not Supported)  status code indicates that the server does not support, or refuses to  support, the major version of HTTP that was used in the request message. The server indicates that it is unable or  unwilling to complete the request using the same major version as the client other  than with this error message. The  server SHOULD generate a representation for the 505 response that describes  why that version is not supported and what other protocols are supported by  that server. |

## Override Response Code

To override the response code, use the following code in the Web Service:

  ```
  - HttpServletResponse response = response();
  - response.setStatus(201);
  ```

  

[![Previous](/articles/images/Previous.png)](/articles/15_web_services/10_legacy_annotation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services/12_Supported_Verbs_Get.md)


