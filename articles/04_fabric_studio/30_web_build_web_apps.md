<web>

# Building Web Apps in Web Studio 

## Introduction

In today's fast-paced development landscape, having an all-in-one platform to implement and deploy applications is crucial. Web Studio IDE is designed to be a one-stop shop, allowing you to seamlessly develop and deploy your solution **end to end**—from building an LU and populating it to easily creating related APIs and web applications that consume them. Web Studio integrates multiple technologies to accomplish these tasks, offering built-in support for various languages like Java, JavaScript, HTML, and CSS, along with intelligent code completion and error notifications.

This allows you to develop web applications within Web Studio as part of your Fabric solution, catering to various users such as CRM representatives, internal teams, or clients.

One of the key advantages of Web Studio is its **live update** capability, which also applies when developing a React app that typically requires a build step for any change. Web Studio allows developers to see changes instantly as they make modifications, significantly streamlining the development process.

In this article, we’ll walk through the steps of building a web app inside Web Studio, illustrating the process with examples and showcasing its powerful features.



## Setting Up a New Web Application

1. In the project tree, select the LU where you want to create the web application and navigate to the Web folder.

2. Right click and choose "New Web App..."

   ![](images/web/30_new_app.jpg)

3. In the popup, enter a name for your app.

4. In the popup, choose the app type from the following options:

   - **React** – A React framework-based app, where base React source files are pre-generated for you. Additionally, files related to the **Vite** framework are included to support live updates.
   - **Vanilla** – A basic setup with initial files created for you (index.html, main.js, and style.css), along with Vite framework files for live update support.
   - **Empty** – No files are created, allowing you to start from scratch.

5. Once the app is created, a new app folder appears under the **Web** folder. In addition to the generated files, your app is automatically added to the **apps.json** file, making it available in the Fabric main menu (top-left "hamburger" menu).

   > **Note**: It is recommended to manage all apps in the **apps.json** file located in the **Web Services LU**. Since Web Services is deployed last, its apps.json file overrides all others. This is particularly relevant if you have customized apps.json files in your project. 



## Editing and Managing Your Web App

You can now edit the web app code while benefiting from built-in intelligent code completion and error notifications.

With Web Studio, you can also manage - create, edit and debug - the APIs your web app will use, and to first view the data, that expected to be shown in the app, through the **Studio Query Builder** tool. This makes the editing process more efficient and reducing development timelines.

You can see a preview of your HTML files, without going to the app itself, using a built in Preview view.

To do so click on the preview icon ![](images/web/30_open-preview.svg) which is located at the top right of the HTML Editor.

## Live Editing & Instant Updates

One of the most powerful features of Web Studio is its **real-time preview**. As you modify your code, the updates reflect immediately in the preview window, eliminating the need for manual builds and deployments. This feature significantly accelerates the development cycle and enhances productivity.

To activate it:

1. At the Explorer View go to the *NPM SCRIPTS* section, which appears under the *Project* section.
2. Expand the entry with the app name.
3. Click on arrow aside the **Install** command action or right-click and from the open menu click on Run.
4. Similarly, Click on **Start** command action. This will start the live preview watcher.

![](images/web/30_npm.png)



Below are screenshots of the City Mobily C360 Demo project, where its react dashboard web-app is developed within the Studio, along with the LU and APIs (Java and Graphit files), enabling end-to-end debug and preview.



![](images/web/30_full_app_preview.png)



![](images/web/30_full_app_preview1.png)

</web>
