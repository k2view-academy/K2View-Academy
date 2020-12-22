# Operation



###### <u>3.1 Scan the project</u>

1. Create new file and name it sonar-project.properties under your project (e.g. C:/Users/<userName>/Documents/K2View Fabric Studio/Projects/<projectName>) with the following parameters 
   sonar.projectKey=<yourProjectKey>
   sonar.projectName=<yourProjectName>
   sonar.java.binaries=C:/Users/<userName>/Documents/K2View Fabric Studio/Projects/<projectName>/Implementation 
   (or location of Implementation folder wherever your project is located)
   sonar.java.source=1.8

2. Map scan results to the sonarQube server:
   In scanner conf file (see section  2.3), set sonar.host.url=https://<sonar server ip>:<sonar server port>

3. Open your browser and connect to the sonarQube GUI using the URL which you have configured in sonar-scanner.properties file

4. Your project results should appear under your project name.



[![Previous](/articles/images/Previous.png)](/articles/COE/SonarQube/02_Installation/01_Installation.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/03_Operation/02_Review.md)

