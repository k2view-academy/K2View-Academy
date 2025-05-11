# K2exchange - Create and Edit Extensions

<web>

K2exchange extensions are integral components of Fabric projects, designed to enhance their functionality. As such, the development of these extensions is naturally conducted within a Fabric project using Studio. The latter provides a comprehensive suite of tools for efficiently creating, editing, packaging, and publishing extensions.

As this article will outline, selecting some specific project files to be included in your extension allows you to continue working on your project without any disruptions.



## Creating New Extensions



To create a new K2exchange extension in your project, follow these steps:

1. In K2studio application, navigate to the menu bar at the top, select the **View** option then open the **Command Palette** (Ctrl+Shift+P for Windows/Linux or Cmd+Shift+P for macOS). Search for **Fabric: New Fabric Extension...** and select it. 

2. A pop-up window will appear, prompting you to populate the **extension name** and **description**.

3. Once confirmed, a new folder is created under the *extensions* parent folder that resides at the top of the Project Tree. The newly created *extensions* folder contains several subfolders and files that serve as templates, defaults and utilities.

   If this is the first extension being created, then the *extensions* parent folder itself will also be created.



>  You can go ahead and repeat these steps as many times as needed to create multiple separate extensions that stem from the same project.



## Adding Project Files to Extensions

Each created extension is placed in a folder that is located directly under the *extensions* parent folder, and it serves as the foundation for the artifact-building process. All required files should be placed in such folders. Nevertheless, Studio eliminates the need to manually copy project files to extension folders, and thus it prevents code duplication and ensures that the code you have developed and tested is included in the extension.



From the Project Tree:

1. Select the file(s) or folder(s) to be included in the extension package.

2. Right-click and choose **Add to Extension Artifacts List...**

     ![](images/web/29_add_files.png)



3. A pop-up window will appear, prompting you to choose the relevant extension (from a list) to which these files should be added.

4. The above action updates the `artifactsSourcePaths.txt` file, which maintains the list of all files or folders included in the extension package. Thus, when you add project files, the `artifactsSourcePaths.txt` file either opens automatically or gets focus if it is already open. You can edit this file manually - by adding or removing file/folder paths to/from it.



## Preparing for Packaging

Once you have added the relevant project files to the extension, you should update, or at least review, the extension's metadata files. These files are auto-created with default initial content during the extension creation process. 

The key files you should manage include:

- README.md – contains the README information for the extension. You should provide a detailed explanation of the extension’s purpose, settings, usage guidelines, best practices, and licensing terms.

- package.json – stores the package configuration, including:
  - `version` – the extension version.

  - `preview`: true/false – determines whether the package is considered a preview. Starting from Fabric 8.2, *preview* extensions are hidden by default.

  - `minRequiredFabricVersion` – specifies the minimum required Fabric version (validated during installation).

  - `icon` – defines the icon that appears in K2exchange (the default is the **K2** icon).

  - `displayName` – the name displayed in K2exchange.

  - `description` – a brief description of the extension.

- LICENSE.txt – this optional file details your license and terms of use, both of which can be applied to your extension. By default, the README file contains a link to this file.



## Packaging and Publishing Extensions

Studio enables you to execute **several actions** for packaging and publishing extensions:

* Building a **VSIX Package** – an extension installer package file (a zip-like format), which contains all the necessary components of an extension. This package file includes the extension's source code, metadata, any required resources or dependencies, and installation instructions as defined in the extension preparations.

   VSIX can be imported into Studio. This is a good practice for testing an extension before publishing it, or for cases where publishing to the K2exchange store/registry is not possible.

* Publishing an extension to the **K2exchange store/registry** - this action publishes the extension package to the K2exchange store/registry, after which the extension can be discovered in the list of extensions.

* Creating a **k2export** file – this file can be useful for extension consumers who use Desktop Studio. The latter is not integrated with K2exchange and these consumers can therefore use neither a VSIX package nor the K2exchange store/registry.



To run any of these actions, you should first execute the *install* script, as a one-time action per extension:

1. In the Project Tree, go to the *NPM SCRIPTS* section, which appears underneath the *PROJECT* section.

     ![](images/web/29_npm_section.png)

    > If this section is not shown, refresh the Studio page. This might occur only on the first time you create extensions.

2. Expand the entry with the extension name.

3. Click the *install* command; you can either click the arrow aside it or right-click it and then select ‘Run’.

     ![](images/web/29_npm_install.png)





Once it has been run, you can execute the appropriate command to generate the package. This should be done similarly to the *install* command, by either clicking the arrow aside the command or right-clicking it and then selecting ‘Run’.

The commands are:

* `Fabric-lib-publish` – for publishing to the K2exchange store/registry.

  > This command requires a token that is provided for users in the K2exchange portal. Contact the K2view team for more information and options.

* `Fabric-lib-package` – creates a **VSIX** file, which can be imported to the Web Studio.

* `Fabric-lib-k2export` – generates a `.k2export` file, which can be imported to the .NET Desktop Studio.



>  Following execution of the extension packaging process, you would be able to see - in the extension's artifacts folder - the following files:
>
>  * under the root folder – the VSIX file
>  * under the artifacts subfolder – the project's files that are included in the extension, shown as a symbolic link



## Updating Extensions

To update an existing extension, follow the below steps:

1. Add files and/or folders from the project folder to the extension. You can also remove files or folders.

2. Update the **version number** in `package.json` file.

3. Run the relevant script (`publish`, `k2export` or `package`).



> If you are only updating the package content, it is not necessary to run the install again.



## Good Practice

- Maintain dedicated GIT repository and environment/space where extensions are created.

- Once creating an extension, add its folder files to GIT (but not the artifacts or the VSIX package, if created).

- A single repository can house multiple extensions; however, separating them allows for greater flexibility, in terms of GIT branching strategies.

- Before releasing an extension, test it using a VSIX package in a separate environment to ensure proper installation and functionality.





[![Previous](/articles/images/Previous.png)](/articles/04_fabric_studio/28_web_k2exchange.md)

</web>



<studio>Not available in Desktop Studio</studio>
