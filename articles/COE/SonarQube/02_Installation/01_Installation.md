# Installation



###### <u>2.1 SonarLint Installation</u>

To install this plugin in your IntelliJ IDE:

1. Go to File > Settings > Plugins.

2. Search for SonarLint

3. Launch the installation

   

###### <u>2.2	SonarQube Installation</u>

1.  Download and install SonarQube-8.5 or higher version.
   https://www.sonarqube.org/downloads/

2. Go to 

   C:\sonarQube\sonarqube-8.5.1.38104\sonarqube-8.5.1.38104\conf.
   Modify the wrapper.java.command=C:/Program Files/ojdkbuild/jdk-11.0.8/bin/java.
   Note: You must download and config java 11 to execute sonarQube.

3. Go to

   C:\sonarQube\sonarqube-8.5.1.38104\sonarqube-8.5.1.38104\extensions\plugins.
   Copy the plugin Snapshots java-custom-rules-1.0-SNAPSHOT & sonar-xml-plugin-2.1.0-SNAPSHOT 

4. Open command line (windows cmd) and execute: 
   C:\sonarQube\sonarqube-8.5.1.38104\sonarqube-8.5.1.38104\bin\windows-x86-64\StartSonar.bat

5. Open your browser and type http://localhost:9000/ (9000 is default) , log-in using default System Administrator credentials (login=admin, password=admin).

**Note:**
For any configuration changes go to conf folder and sonar.properties file.
Here you can configure database, LDAP, webserver, SSO authentication, logging, etc.
e.g. For port changes, under web-server section, you can add sonar.web.port=9001.



###### <u>2.3	 SonarScanner Installation</u>

1. Download and Install sonarScanner 
   https://docs.sonarqube.org/latest/analysis/scan/sonarscanner/
2. In the window, register the sonar-scanner path in environment variable.

  ![image](images/02_installation.png)

3. Go to

   C:\ sonar-scanner-cli-4.4.0.2170-windows\sonar-scanner-4.4.0.2170-windows\conf. Modify prop sonar.host.url within sonar-scanner.properties to point to your sonarQube server:

   •	Local sonarQube server: -
    sonar.host.url= http://localhost:9000 (default)

   •	Remote sonarQube server: -
   sonar.host.url= http(s)://IP:Port       sonar.login=223eadd44dd0cc401ed977c101347bde2269e206 (the shared token (each project will have his token)



[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/01_Overview/03_Customized_Library.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/03_Operation/01_Scan.md)

