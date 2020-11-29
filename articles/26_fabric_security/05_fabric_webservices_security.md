# **Fabric Web Services Security** 

## Tokens

Fabric provides a secured mechanism for web-services to access to the data it exposes - such as LUI data or Reference Tables.
This is done by means of authentication mechanism that support Web Service calls using either of the following credentials:
- unsecured API key  
- secured API key based on JSON Web Tokens (JWT) solution. 

JWT is an open industry standard method (RFC 7519) that securely represents claims between two parties. 

API keys can be used in two modes:

- Unsecured mode signed by Fabric.
- Secured mode, using a digital signature on the client side. The secret key will be shared as an output only once to be used by the client to generate the digital signature.


Fabric supports backward capability via Token Authentication and an enhanced "Create Token" command for secured tokens. 


### JWT Tokens Generation 

In order to generate a JWT token using Fabric Authenticate API, follow the next steps:

- Open Postman
- Select the POST method and enter your Fabric Server Address: http://localhost:3213/api/authenticate in the URL window
- Under the *body* tab, enter both username and password as keys and enter corresponding values (e.g. admin/admin). Similarily, an API key can be defined.
- Click the SEND button
- Open the *Cookies* tab next to the response body.
- The API key is displayedin the value field - e.g: 

            ``` eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0b2tlbiIsImlzcyI6ImZiciIsImlhdCI6MTYwNjY2MDg4MiwiZXhwIjoxNjA2NjYxNzgyLCJ1bm0iOiJhZG1pbiJ9.sQpH343SbfLPHrR7lp5eG4qZKGXXhMrkggX9wqVzLBQ ```

<img src="/articles/26_fabric_security/images/05_devop-prodEnv_PostMAN.png">
    



## Tokens and the Admin Panel

In order to generate a new token for accessing Fabric API, open the Admin Panel web page:

Step 1: Select the *Admin* -> *Security* -> *API keys* tab.

Step 2: Select the *Add API Key +* button on the upper right of the window.

Step 3: Fill in the following details:
- Name (Mandatory)
- Secured (Optional)

Step 4: Click on the *Save* button


If the secured option was selected, the secret key will appear in a pop=up window, with the ability to select and copy it.
```f151c40f-fede-4fb3-8010-398ffbc02329```


<img src="/articles/26_fabric_security/images/07_fabric_webToken.PNG">


Note that in cases where the secured option was not selected the token value is the token name itself.


## Tokens from the Command Line

The creation of a token from the command line is described [here](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#create-token)

## Granting Web-services Permissions to Roles 

When assigning a role to a user, different types of methods can be attributed. 
The roles supported are described [here](/articles/17_fabric_credentials/02_fabric_credentials_commands.md#grant-ws_name-to-role-)


### Web Service authorization using the API Key:

#### Project Web Services

Permission is granted to the role in the Web Service or to all Web Services and the API Key is assigned to the role. This is achieved from the command line by using the following command:

```GRANT <ws_name> TO <ROLE>```


#### Product Web Services

The API key is assigned to a user. The permissions for Product Web Services are defined by combining the API Key assigned to the user and the permissions of the roles assigned to the user.

Example:

``` 
    create user 'greg';
    create role 'writeRole';
    grant WRITE on * to 'writeRole';
    assign role 'writeRole' to user 'greg';
    create token 'test_token' user 'greg';
```

This sniplet shows how the WRITE permission (granted to the writeRole) role was assigned to the user; and how a token (test_token) reflecting this role/permission was generated for the user.

When trying to invoke the web service with the DELETE verb, using the 'test_token' token, Fabric will throw the following error as the delete permission was not granted to the specific token: 

``` "Com.k2view.cdbms.exceptions.UnauthorizedException: test_read is not allowed to perform [DELETE_INSTANCE]" ```

## Steps to simulate a Secured API key

1.	Create a secured token and save secret key
2.	Create regular token
3.	Assign a secured token to a specific role
4.	Generate a JWT token on the regular token (http://localhost:3213/api/authenticate)
5.	Copy the jwt (from postman for example)
6.	Go to https://www.base64decode.org/ and paste the JWT token in first box
8.	Click Decode
9.	Go to the jwt.io website
10.	Enter part in first brackets in ‘header’ box
11.	Enter part in second bracket in ‘payload’ part and edit the name of the apk tag to name of secured token
for example apk: “lion2” -> apk: “lion1”
12.	Enter secret key in ‘verify signature’ part
 
13.	The jwt should appear in the left box.
14.	Override the jwt in the cookie

 
15.	Run a WS – should work fine





[![Previous](/articles/images/Previous.png)](/articles/26_fabric_security/04_fabric_interfaces_security.md)

