# Project Versioning

Fabric offers project version tagging so that on the deployed server you can see the deployed version tag. This capability provides you a better control on your project’s versions, as well as visibility of what project’s version is currently deployed at a Fabric server.



The project versioning solution is built from 3 steps along the project lifecycle, helping you to control, verify and have visibility of the project's version:

1. Tag a version, a new action available at the Studio.

2. Deploy Fabric objects, which were tagged with a version, into a remote server<studio>, using the Studio</studio>.

3. Check the project objects version/s in Fabric console / terminal to understand what is deployed.

   

## Tag a Version

Studio helps you to tag a version properly by:

1. Proposing you the next version, according to current version and based on semantic versioning format.
2. Alerting you when you have any local changes which are not pushed to GIT.
3. Alerting you when your changes are not aligned with other changes on your working GIT's branch.
4. Updating the project version both at project definitions and into GIT, as an annotated tag.  

### Tag Version Work Flow

Following is a development workflow:

<studio>

1. Developer pulls project from GIT.

2. Developer works and implement his code on the project.

3. Once a working phase is done, the developer sets a version for his work:

   - From the project tree root, right click and choose "Version Tag".

   - A popup appears with information of the remote GIT branch, the proposed tag version and an optional tag message. The proposed version is based on the previous but can be changed, as described below.

      ![version tag popup](images/04_01_versiontag.png)

   - Click OK to proceed.
   

</studio>

<web>

1. Developer create a space based on a project from GIT.

2. Developer works and implement his code on the project.

3. Once a working phase is done, the developer sets a version for his work:

   - From the top Fabric menu item choose "Version Tag".

   - A popup appears with information of the remote GIT branch, the proposed tag version and an optional tag message. The proposed version is based on the previous but can be changed, as described below.

      ![version tag popup](images/04_04_web_version_versiontag.png)
   
   - Press 'Enter' to proceed.

</web>

4. Studio makes validations about the action: 

   - checks that developer does not have any local changes (no matter if committed locally or not) which are not pushed to GIT. If there are changes - Studio warns the user about that. 
   
     If developer decide creating a version despite the warn, a version will be created, containing only the pushed to GIT files. 
   
   - Checks weather developer changes are aligned with other changes on this GIT's branch and warns the user if not. 
   
     Note that this case is valid: sometimes two or more developers are working on same branch in parallel, on different capabilities, aimed to different phases. Thus, a version tag might contain specific development made on that on that branch. 


5. In case developer click to continue, after examine warns, if appears, the version tag is created at both k2proj.xml file and also as a annotated tag in GIT, where tag message, if populated, is set at GIT. Both changes are committed and pushed into GIT.

   

> **Note**: Studio is not responsible about the permissions and capability of a developer to create a tag and from which branch. Project's owner shall defines it at GIT itself as well as define the procedures, for example from which branch (e.g., any or specific "main"), version tag shall be produced.



### Project Version Format

it is recommended to follow the **Semantic Versioning Structure**, where version number shall be built as MAJOR.MINOR.PATCH,  as defined [here](https://semver.org/). Accordingly, Studio enforces the format where it must be 



## Deploying Tagged Objects 

<studio>

Fabric Studio allows deploying project objects into remote servers. As part of project versioning capability, Studio offers you to decide, per remote server, weather activating the version tag validation, as a pre-deploy step, ensuring by doing it that you will have visibility on what is deployed at the target remote Fabric server. 

At the Studio User Preferences screen, you can choose, at the **Pre Deploy Tag Validation** field, either to skip on it, to warn when validation failed or block the deployment when validation failed.

![user preferences window with pre deploy tag validation](images/04_02_deploy_userpref.png)

On deploying to remote server, either the whole project or only specific objects, Studio performs the following according to the chosen preferences:

* When **NoValidation** is chosen (default), then Studio skips on all tag checks. This is less recommended because local changes, if were done, will not be reflected, via the version command, at the remote server. It is available for customers that do not use project versioning capability.

* When **WarnOnTagDiff** is chosen, then Studio checks that the candidates to be deployed are aligned with the version tag. It compares the files that are going to be deployed against the files at GIT, by the current project version tag. If they are not equal - Studio warns the user. User can choose to continue anyway.

  In such case, where user decides to continue with the deployment, despite the warns, a star will be added to the project's version at the built artifacts (e.g. "1.0.2*****"). This will be used later on the remote server to indicate, at the `version` command results, to hint the user about. By doing this, Studio empowering the version visibility at the remote target server, about what is deployed.

* When **ErrorOnTagDiff** is chosen, then Studio behaves similar to the **WarnOnTagDiff** option but in this case it blocks the action and deployment cannot be done.



When running **Build Deploy Artifacts** action, Studio behaves similarly, but without alerting the user, because no deployment is done but building artifacts. Yet, when not skipping on the validation checks, the built artifacts are sign with the star indication, when not aligned with tag. This means that if later on, if these artifacts will be taken and deployed at another server, using the [offline deploy](), same indication will appear on the `version` command results and hint users about.



> Note: User Preferences is saved at developer's Studio and thus it is his responsibility to set properly the pre deploy validation, according to the project's procedures. 



</studio>

<web>

To use a specific version tag in a space, you shall set in either in project level - at branch/tag field or on a specific project space profile level. 

</web>



## Fabric Version command

Fabric enable visibility on the version of the currently deployed objects at server. By knowing the project version, you can examine at GIT the exact content of that object and accordingly understand better his functionality and behavior, especially if something works not as expected.

* The ```version``` command shows, in addition to the product version, the project version for each LU, in a separated entry. Because each LU can be deployed separately, their versions might be different.  For Example:

  ```
  |Resource        |Result                                  |
  +----------------+----------------------------------------+
  |Version         |7.2.0                                   |
  |Full Version    |7.2.0_37                                |
  |Version's commit|46a96b7246e96cecd84dfc3522302b99c7c1b65c|
  |MDB Version     |3.39.2                                  |
  |Customer        |1.0.0                                   |
  |Orders          |1.0.0                                   |
  |Billing         |1.0.3                                   |
  ```

* Project version will not be shown as long as project versioning actions were not activated. In addition it will not be shown for those LUs which are not deployed. This can happen also during the phase of starting using this capability, where some LUs are being deployed after using it will appear with version and other will not. 

* ```list ws```  and ```list lut``` are also reflecting the project versioning, shown in the "Project Version" column in the commands result. 

  `list ws` command result example: 

  ```
  |Name |Category|Version|Deployed               |Project Version|
  +-----+--------+-------+-----------------------+---------------+
  |test1|test    |1.0    |2023-06-01 12:23:30.608|1.0.0          |
  |test2|test    |1.0    |2023-07-03 18:27:49.738|1.0.3          |
  ```

  `list lut` command result example: 

  ```
  |LU_NAME |Project Version|
  +--------+---------------+
  |Billing |1.0.3          |
  |Customer|1.0.0          |
  |Orders  |1.0.0          |
  ```

* When the remote deployment was done despite it was not aligned with the tagged version, the ```version``` command yields a star near the project version (e.g. "1.0.2*****"), indicating that the deployed object is not quals to the version, but contain changes.  



<web>

In Studio, You can see the current deployed version also at the bottom status bar

![status bar web studio](images/04_03_web_version_statusbar.png)

</web>

## Notes

* Using the project version capability is optional and as long as not used, project metadata will not contain it. Thus, version command will not show LUs entries. 

* When project's source code is not controlled at GIT, the related project versioning elements will not be shown or act.

  In such case, project version can be added manually to the k2proj.xml (at `<ProjectVersion>` element), so that it will be reflected later at the version command result on the deployed server. Manual update shall not be done when working with GIT and with the project version mechanism.

  As Studio validations cannot be run in such case, there is no guarantee that the version which is shown in the version command's result really represent the version, that was added and saved manually. 



See [here]() examples and illustration diagrams for several use cases of the project code lifecycle.





[![Previous](/articles/images/Previous.png)](/articles/16_deploy_fabric/03_offline_deploy.md)

