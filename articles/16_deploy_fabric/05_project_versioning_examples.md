# Project Versioning Examples

Below are several examples for project versioning use cases, accompany with diagrams that illustrate them, as and end to end work flows.



## Example #1: Studio flow - Single Developer 

This example shows few cases of a developer workflow.

* Create a tag, where all is committed and pushed and aligned with GIT.

* Create a tag, where there are local changes which are not pushed to GIT

  > While this case is little risky, it is valid, when developer already continued working on next features, before it was decided to set a version. In our example, developers push the leftovers. and activate tag version again, this time without getting warnings.

<studio> 

* Deploy to remote server where no changes were done after creating the tag. In this case deployed artifacts are fully aligned with the tag , so that when running `version` command at the remote server, and see as result the project's version,you have confidence that it is aligned with tag artifacts at GIT.

* Deploy to remote server where local changes were done locally after creating the tag. In case of Warn-on-Tag-Diff is chosen at User Preferences for this remote server, then Studio will warn the user. if he choose to proceed anyway, then the built artifacts are signed with star (*). In this way, when running the `version` command at target remote server, user is hinted that the deployed artifacts are not fully aligned with the tagged version.



![diagram](images/04_single_dev_diagram.png)

</studio> 

<web>

* Create a space that is based on tag (e.g., use a project space profile that based on that tag). In this case project content is cloned to space by the tag so that when running `version` command at the remote server, and see as result the project's version, you have confidence that it is aligned with tag artifacts at GIT.

  

![diagram](images/04_web_single_dev_diagram.png)

</web>



## Example #2: Studio Flow - Several Developers

This example illustrate the case of several developers who work on same project. This example starts when version 1.0.4 is created and two developers start working based on that (it is assumed that branch is fully aligned with tag. You can see at example 1 that it is not necessary the case).

* DEV 1 initiate the tag version action. She is warned because DEV 2 made pushes after her, meaning her code is not fully aligned with branch. 

  >  This is a valid case, that another developer continues with implementation that aimed for a later version. Accordingly, DEV 2 might verify, before proceeding, that DEV 2 changes shall not be part of the version content.

* DEV 2 initiate the tag version action. He will be rejected because the candidate version is the same as DEV1 (1.0.5), who already created it. This will happen as long as he did not pull DEV1 changes, also with the tag version, and as long as he did not change the auto proposed version.



![diagram](images/04_few_dev_diagram.png)



## Example #3: Hotfix

This example illustrate a case that hotfix is required, where one of the developer handles it, while the another continue working on main branch, without collisions.

* DEV2 assigned to make hotfix and switch to work based on tag (by doing it he detached from tag and need to make his changes on a some branch).
* When DEV2 initiate the Tag Version, Studio suggests him next version - 1.0.1, comes after current version - 1.0.0.  DEV 2 overrides this value with HF version.
* DEV1 working on the on going branch and thus there are no conflicts when pushing her stuff into GIT as well when initiating tag version, which suggested to be, as expected, 1.0.1.



![diagram](images/04_hotfix_diagram.png)





[![Previous](/articles/images/Previous.png)](/articles/16_deploy_fabric/04_project_versioning.md)



