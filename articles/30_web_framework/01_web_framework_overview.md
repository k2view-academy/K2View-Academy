# Web Framework Overview

K2View Web Framework is a container for hosting multiple web applications and providing services out of the box. The purpose of the K2View Web Framework is to serve as a user's desktop that contains frequently-used web applications running on the same application server. Instead of manually typing the URL, the user logs into the K2View Web Framework and selects the required application from the context menu. 

The framework can be opened using the following URL: **http://[Fabric IP address]:[Fabric port]/app/login**, for example http://localhost:3213/app/login. The user should login using his user name and a password. 

K2View Web Framework provides several [pre-integrated applications](02_preintegrated_apps_overview.md) (such as Admin or Swagger). Additional applications can be easily integrated into the framework by adding the application to the **apps.json** and a folder with the application code into the Fabric's installation folder in Widows: **K2View\Fabric_[ver]\Server\fabric\staticWeb**. 

The framework's **k2api** object exposes various methods to be used by the application, such as navigation, Fabric commands and Web Services invocation, etc.  The application style can be set either using K2View Web Framework style sheets (**k2.css**) for having unified look & feel or by a different set of style sheets. The framework supports any application type (multi page or single page) and any routing method (History API, hash base or regular links).

The detailed API documentation can be found under K2View Web Framework > Documentation and it provides the description of the supported methods, code examples and the development guidelines. 





[<img align="right" width="60" height="54" src="/articles/images/Next.png">](02_preintegrated_apps_overview.md)

