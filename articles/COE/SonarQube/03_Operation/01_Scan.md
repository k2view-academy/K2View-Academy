# Operation



## <u>3.1 Scan the project</u>

1. Create a new file and name it **sonar-project.properties** under your project (e.g. *C:/Users/\<userName>/Documents/K2View Fabric Studio/Projects/\<projectName>*) with the following parameters 
   - sonar.projectKey=\<yourProjectKey>
   - sonar.projectName=\<yourProjectName>
   - sonar.java.binaries=*C:/Users/\<userName>/Documents/K2View Fabric Studio/Projects/\<projectName>/Implementation*
      (or location of Implementation folder wherever your project is located)
   - sonar.java.source=1.8

**Note: Make sure you are using regular slashes instead of back slashes.**

2. Set the user/password you use to login SonarQube UI in sonar scanner properties file.
   ([See Installation/2.3.3](https://github.com/k2view-academy/K2View-Academy/blob/Academy_6.2/articles/COE/SonarQube/02_Installation/01_Installation.md))

3. Scan your project by following below steps:

   	- open your project root folder in File Explorer, same place you created sonar-project.properties in step 1. 
	   - open cmd (windows) pointing to that path by typing cmd in the Address Bar in File explorer
	   - type sonar-scanner and hit enter
	   - Wait for scan to complete
      
3. Open your browser and connect to the sonarQube GUI using the URL which you have configured in sonar-scanner.properties file

         e.g. https://localhost:9000 (default)
      
4. Your project results should appear under your project name.


[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/COE/SonarQube/03_Operation/02_Review.md)

