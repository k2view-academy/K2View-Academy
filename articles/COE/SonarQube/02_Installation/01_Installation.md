# Installation



## <u>2.1 SonarLint Installation</u>

**To install this plugin in your IntelliJ IDE:**

1. Go to File > Settings > Plugins.

2. Search for SonarLint

3. Launch the installation

   

## <u>2.2	SonarQube Installation</u>

**1. [Download](https://www.sonarqube.org/downloads/) and install SonarQube-8.5 Community edition or higher version.**
	
 	e.g., install under C:\sonarQube\sonarqube-<version>

**2. Modify wrapper.conf  (located under *C:\sonarQube\sonarqube-\<version>\conf)* as below:**

	wrapper.java.command=C:/Program Files/ojdkbuild/jdk-11.0.8/bin/java
	Java path might be installed in different location, make sure to use the right path.

   *Note: You must download and config java 11 to execute SonarQube.*

   
**3. Delete all plugins (jars) under** *C:\sonarQube\sonarqube-\<version>\lib\extensions*   
	**except** of *sonar-java-plugin-\<version>.jar.*


**4. [Download](/articles/COE/SonarQube/02_Installation) and copy the plugin Snapshots**   
	*java-custom-rules-\<version>* & *sonar-xml-plugin-\<version>*  
	**to** 
	*C:\sonarQube\sonarqube-\<version>\extensions\plugins.*

**5. Start SonarQube server:** 
	
	- open C:\sonarQube\sonarqube-<version>\bin\windows-x86-64\ in File Explorer
	- open cmd (windows) pointing to that path by typing cmd in the Address Bar in File explorer
	- execute StartSonar.bat in cmd
	- wait for server to be up and running, you supposed to see below message:
		jvm 1    | 2020.12.28 12:41:35 INFO  app[][o.s.a.SchedulerImpl] SonarQube is up

**6. Open your browser and type http://localhost:9000/ (9000 is default)**  

	log-in using default System Administrator credentials (login=admin, password=admin).

**7. Create Quality Profiles.**  
   Quality Profiles are sets of rules to be applied on project when scanned. 
 	
	Follow below steps in order to import k2view customized QPs.  
   	
	- [Download](/articles/COE/SonarQube/02_Installation) k2view_profiles.zip 
	  and extract XML and Java profiles and save the in temporary place.
   	- Open SonarQube UI, Go to Home Page --> Quality Profiles --> Restore
	- You will be asked to choose a back up file, choose the files you extracted for XML and Java.  
   	- After restoring set each profile as Default.
	
![image](/articles/COE/SonarQube/images/13_default.png)
 
**Note:**
	*For any configuration changes go to conf folder and sonar.properties file.
	Here you can configure database, LDAP, webserver, SSO authentication, logging, etc.*
	
For port changes, under web-server section, you can add sonar.web.port=9001.



## <u>2.3	 SonarScanner Installation</u>

**1. [Download](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/) and Install sonarScanner** 

  	e.g., install under c:\sonarQube\sonar-scanner-<version>-windows
   
**2. In the window, register sonar scanner executable under the "Path" environment variable.**

	- Type env in Windows Start Search and hit enter
	- Click on Environment Variables button
	- Look for "Path" environment variable and double click on it
	- Click New and set the path of the bin folder of downloaded scanner
		e.g. C:\SonarQube\sonar-scanner-4.5.0.2216-windows\bin
  ![image](/articles/COE/SonarQube/images/02_installation.png)

**3. Set SonarQube user/password**

   Set the user/password you use to login SonarQube UI in sonar scanner properties file.
   
   look for sonar-scanner.properties under conf folder
   	e.g. C:\SonarQube\sonar-scanner-4.5.0.2216-windows\conf\sonar-scanner.properties

   open this file with any text editor and add the below properties:
   sonar.login=<your username>
   sonar.password=<you password>
	
	e.g.    
		sonar.login=admin
   		sonar.password=admin
		
  *Note: If while installing SonarQube server you decided to use different port than the default, you will need
   to set the port in scanner properties file.*
   
	Modify prop sonar.host.url within sonar-scanner.properties to point to your sonarQube server:  

   	•	Local sonarQube server: -  
   	 sonar.host.url= http://localhost:9000 (default)  

   	•	Remote sonarQube server: -  
  	 sonar.host.url= http(s)://IP:Port



[![Previous](/articles/COE/SonarQube/images/Previous.png)](articles/COE/SonarQube/01_Overview/README.md)[<img align="right" width="60" height="54" src="/articles/COE/SonarQube/images/Next.png">](/articles/COE/SonarQube/03_Operation/README.md)
