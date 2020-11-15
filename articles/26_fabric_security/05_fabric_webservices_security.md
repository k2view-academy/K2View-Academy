The user can define different roles and assign each role with token. The token is used for executing K2View Fabric web services. Fabric 5.2 encrypts the tokens before they are saved into the Cassandra.
Since Fabric 6.2 a new Authentication mechanism was added to support Web Service calls using 
a username and password or a secured token based on JSON Web Tokens (JWT) solution. A JWT 
is an open industry standard RFC 7519 method that securely represents claims between two 
parties. Tokens can be used in two modes:
o	Secured mode, using a digital signature on the client side (e.g. CREATE TOKEN ABC SECURED), secret key will be shared as an output only once to be used by the client to generate the digital signature
o	Unsecured mode signed by Fabric. 
Fabric continues to support backward capability via Token Authentication and now offers an enhanced Create Token command for secured tokens. 

