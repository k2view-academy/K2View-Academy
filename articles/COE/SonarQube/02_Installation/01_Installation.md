# Installation



## <u>2.1 SonarLint Installation</u>

**To install this plugin in your IntelliJ IDE:**

1. Go to File > Settings > Plugins.

2. Search for SonarLint

3. Launch the installation

   

## <u>2.2	SonarQube Installation</u>

**1. Go to [download](https://www.sonarsource.com/products/sonarqube/downloads/historical-downloads/) page and install SonarQube-10.6 Community edition.**
	
 	e.g., install under C:\sonarQube\sonarqube-<version>
	Sonarqube 10.6 requires Java 17.

**2. Download (right panel) java and xml plugins and copy to** *~\sonarqube-10.6\extensions\plugins.*

**3. Start SonarQube server:** 
	
	- open C:\sonarQube\sonarqube-<version>\bin\windows-x86-64\ in File Explorer
	- open cmd (windows) pointing to that path by typing cmd in the Address Bar in File explorer
	- execute StartSonar.bat in cmd
	- wait for server to be up and running, you supposed to see below message:
		jvm 1    | 2024.03.15 12:41:35 INFO  app[][o.s.a.SchedulerImpl] SonarQube is up

**4. Open your browser and type http://localhost:9000/ (9000 is default)**  

	log-in using default System Administrator credentials (login=admin, password=admin).

**6. Create Quality Profiles.**  
   Quality Profiles are sets of rules to be applied on project when scanned.
   Create QP for each plugin, need to run the steps for each plugin (XML/Java)
   
   	- Open SonarQube UI, Go to Home Page --> Quality Profiles --> Create
   	- Choose "Extend an existing quality profile"
   	- "Language" choose XML or Java
	- "Parent" choose "Sonar way (Built-in)", or any default profile used for the project.
	- "Name" give a uniqe QP name. (later on you will use this name when applying this QP to your project)  
	- Open your new QP, under "Rule breakdown" click on "Activate More"
 	- On the left panel, open tags, search for k2view, mark "k2view" and "k2view-iidf", click on "Bulk Change" and choose "Activate In <QP name>" and Ok.
  	- Apply this QP on your project under: <Project> -> Project Settings -> Quality Profiles and change Current Quality Profile to your newly created QP.
  
**Note:**
	*For any configuration changes go to conf folder and sonar.properties file.
	Here you can configure database, LDAP, webserver, SSO authentication, logging, etc.*
	
For port changes, under web-server section, you can add sonar.web.port=9001.

**3. Generate Token to be used in sonar scanner**

	- In Sonarqube server UI, go to Administration->Security->Users
	- Generate token for the user you need by clicking on three dots under Token and generate, keep the token so you can use in sonar scanner configuration file.

## <u>2.3	 SonarScanner Installation</u>

**1. [Download](https://docs.sonarsource.com/sonarqube-server/10.6/analyzing-source-code/scanners/sonarscanner/) and Install sonarScanner** 

  	e.g., install under c:\sonarQube\sonar-scanner-<version>-windows
   
**2. In the window, register sonar scanner executable under the "Path" environment variable.**

	- Type env in Windows Start Search and hit enter
	- Click on Environment Variables button
	- Look for "Path" environment variable and double click on it
	- Click New and set the path of the bin folder of downloaded scanner
		e.g. C:\SonarQube\sonar-scanner-4.5.0.2216-windows\bin
  ![image](/articles/COE/SonarQube/images/02_installation.png)

**3. Set token**

   Set the token you generated before in sonar scanner properties file.
   
   look for sonar-scanner.properties under conf folder
   	e.g. C:\SonarQube\sonar-scanner-4.5.0.2216-windows\conf\sonar-scanner.properties

   open this file with any text editor and add the below properties:
   sonar.token=<token>
		
  *Note: If while installing SonarQube server you decided to use different port than the default, you will need
   to set the port in scanner properties file.*
   
	Modify prop sonar.host.url within sonar-scanner.properties to point to your sonarQube server:  

   	•	Local sonarQube server: -  
   	 sonar.host.url= http://localhost:9000 (default)  

   	•	Remote sonarQube server: -  
  	 sonar.host.url= http(s)://IP:Port



[![Previous](/articles/COE/SonarQube/images/Previous.png)](articles/COE/SonarQube/01_Overview/README.md)[<img align="right" width="60" height="54" src="/articles/COE/SonarQube/images/Next.png">](/articles/COE/SonarQube/03_Operation/README.md)
