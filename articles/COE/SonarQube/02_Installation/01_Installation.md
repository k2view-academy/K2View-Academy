# Installation



## <u>2.1 SonarLint Installation</u>

To install this plugin in your IntelliJ IDE:

1. Go to File > Settings > Plugins.

2. Search for SonarLint

3. Launch the installation

   

## <u>2.2	SonarQube Installation</u>

1. [Download](https://www.sonarqube.org/downloads/) and install SonarQube-8.5 or higher version.
	
	
 	e.g., install under *C:\sonarQube\sonarqube-8.5.1.38104*

2. Modify wrapper.conf  (located under *C:\sonarQube\sonarqube-8.5.1.38104\conf)* as below:

	wrapper.java.command=* *C:/Program Files/ojdkbuild/jdk-11.0.8/bin/java*
	Java path might be installed in different location, make sure to use the right path.

	*Note: You must download and config java 11 to execute SonarQube.*

   
3. Delete all plugins (jars) under *C:\sonarQube\sonarqube-8.5.1.38104\lib\extensions*
	**except** of *sonar-java-plugin-* *.jar.*


4. [Download](https://github.com/k2view-academy/K2View-Academy/tree/Academy_6.2/articles/COE/SonarQube/05_Reference_and_Document) and copy the plugin Snapshots 
	*java-custom-rules-1.0-SNAPSHOT* & *sonar-xml-plugin-2.1.0-SNAPSHOT* to 
	*C:\sonarQube\sonarqube-8.5.1.38104\extensions\plugins.*

5. Open command line (windows cmd) and execute: 
 	*C:\sonarQube\sonarqube-8.5.1.38104\sonarqube-8.5.1.38104\bin\windows-x86-64\StartSonar.bat.*

6. Open your browser and type http://localhost:9000/ (9000 is default) , log-in using default System Administrator	credentials (login=admin, password=admin).

7. Create Quality Profiles.
   Quality Profiles are sets of rules to be applied on project when scanned.
	
 	Follow below steps in order to import k2view customized QPs.
   	- [Download](https://github.com/k2view-academy/K2View-Academy/tree/Academy_6.2/articles/COE/SonarQube/05_Reference_and_Document) XML and java profiles
   	- Go to Home Page --> Quality Profiles --> Restore:
	
	 ![image](/articles/COE/SonarQube/images/09_restore.png)
	 
     You will be asked to choose a back up file, choose the files you downloaded for XML and Java.
   	
	- After restoring set each profile as Default.
	
	 ![image](/articles/COE/SonarQube/images/13_default.png)
 
	**Note:**
	*For any configuration changes go to conf folder and sonar.properties file.
	Here you can configure database, LDAP, webserver, SSO authentication, logging, etc.*
	
	For port changes, under web-server section, you can add sonar.web.port=9001.



## <u>2.3	 SonarScanner Installation</u>

1. [Download](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/) and Install sonarScanner 
  
   
2. In the window, register the sonar-scanner path in environment variable.

  ![image](/articles/COE/SonarQube/images/02_installation.png)

3. Go to

   *C:\ sonar-scanner-cli-4.4.0.2170-windows\sonar-scanner-4.4.0.2170-windows\conf.* 
   	
	Modify prop sonar.host.url within sonar-scanner.properties to point to your sonarQube server:

   	•	Local sonarQube server: -
   	 sonar.host.url= http://localhost:9000 (default)

   	•	Remote sonarQube server: -
  	 sonar.host.url= http(s)://IP:Port       sonar.login=223eadd44dd0cc401ed977c101347bde2269e206 (the shared token (each project will have his token)



[![Previous](/articles/COE/SonarQube/images/Previous.png)](articles/COE/SonarQube/01_Overview/README.md)[<img align="right" width="60" height="54" src="/articles/COE/SonarQube/images/Next.png">](/articles/COE/SonarQube/03_Operation/README.md)
