# Fabric and Project Dependencies

Fabric uses a modular and isolated class loading strategy to keep project-specific dependencies separate from the core application.  

At the core of this approach is Java’s standard *parent-first delegation* model: When a class loader is asked to load a class, it first delegates the request to its parent. If none of the parent class loaders can load it, the class loader tries to load it itself.

Fabric adopts this model but introduces an important enhancement: a *filter-based delegation* model, which limits what child class loaders are allowed to delegate to their parent. This ensures better isolation and avoids unintentional conflicts and reduce the risk of version clashes between project's code and core Fabric, for example if Fabric internal functions were changes while were unexpectedly used by project code.



## Filter-Based Delegation: Controlling Inheritance

The enforcement of class loader boundaries, using filtering mechanism,  is governed by the `PACKAGE_NAMES_CLASS_LOADING_FILTER` property in `config.ini`.

- Only packages that match the filter are allowed to be delegated to the App class loader. By default, the filter includes only: `com.k2view.*`
- All other packages must be provided directly in the child class loader's own classpath.

If a child class loader tries to load a class:
- It will delegate to the parent only if the class’s package matches the whitelist.
- If not, it tries to load it from its own classpath.
- If the class isn't found in either, class loading fails — even if the parent already loaded it.

> **Recommendation:** Use `PACKAGE_NAMES_CLASS_LOADING_FILTER` with caution for preserving the isolation.



## Fabric Class Loaders and Packaging Structure

Fabric class loaders separation is between its core system, plugins, project's LUs, and interfaces. As explained, this design supports dependency isolation and flexible packaging, while still enabling controlled sharing of core APIs via filtered delegation.

Here’s an overview of the different components and how their class loading works:

#### Fabric (App) Class Loader

- Loads the core Fabric JARs (`com.k2view.*`) and other internal libraries from: `fabric/lib/fabric` and `fabric/lib/provided`.
- Acts as the *parent* class loader for all other class loaders in the system.

#### Plugins Class Loaders

- Plugins are typically SDK packages used to connect with external services such as AWS S3 / Azure Blob Storage (Managed Storage), KMS and secret management services.
- Each plugin has its own dedicated class loader, separate from LUs, other plugins and Fabric.
- Loaded from:  `fabric/lib/plugins/{plugin-name}`, e.g., `fabric/lib/plugins/masterkey-aws`, `fabric/lib/plugins/search-provider-elastic`.
- Parent: Fabric (App) class loader.

#### LUs Class Loaders

- Each LU is packaged as a JAR during the project build process.

- The LU JAR also includes all project-specific dependencies located in its `lib/` folder, such as custom or third-party libraries, either added manually or  brought by Exchange's extensions, and JDBC drivers.

  Including the full dependency set inside LUs ensures:

  - Clean separation between the project and the Fabric core.

  - Simplified deployment - no need to manually copy JARs to server's classpath location, at each Fabric node.

  - Better support for cloud and Kubernetes deployments - In addition to that the access to Fabric server is limited/disabled (so copying JARs is disabled), Fabric nodes are rebuilt from image registry (accordingly, if something happens to the POD or if it is moved, the K8S infrastructure brings back Fabric from original image registry).


* Each LU gets its own dedicated class loader.

- Parent: Fabric (App) class loader.

#### Interface Class Loaders

- JDBC drivers for specific interface types (not included in Fabric by default) may also be added to the project's `lib/` directory (and accordingly under each LU).
- These interface drivers are packaged under folders named after their interface type. Read [here](/articles/05_DB_interfaces/10_database_types.md#jdbc-drivers-management) for more information.
- Each interface type gets its own dedicated class loader, even though it is part of the LU JAR.



> **Note**: In earlier Fabric versions, project dependencies were typically added via the [`externalJars`](/articles/31_external_resources/01_external_jars.md) folder. Today, bundling dependencies directly with the LU JAR offers better portability and version control.



## Examples

These examples illustrate how the filter-based model behaves in practice:

#### LU Uses Its Own JAR Version

- Fabric has Jackson 2.9 in `fabric/lib`
- LU needs Jackson 2.14, included in its `lib/`
- Jackson is **not** exposed in `PACKAGE_NAMES_CLASS_LOADING_FILTER`.

LU loads **its own** version successfully. The filter ensures that delegation does not occur, so the LU’s loader handles Jackson independently

#### LU and Plugin Use Different Versions

- Fabric uses one of the AWS SDK at `plugins`
- LU uses a different AWS SDK version in the project's `lib/`

LU **can** load and use its own (project) version safely, since plugins are **not** parents, but are sibling loaders, and Fabric uses those SDK JARs and not loads them in its loader.



<web>

### Studio

Studio's build-time compilation environment does not follow the same class loading rules as Fabric runtime.

Studio is more strict by default and discourages use of Fabric core classes (outside `usercode`), to help prevent breakage, if Fabric internals change.

To set the Studio classpath :

* Use the bottom-left **Manage Preferences** ![img](/articles/04_fabric_studio/images/web/settings.png) gear icon > **Settings**.
* Move to the Workspace tab
* Click the top right **Open Settings (JSON)** `{}` icon 
* look for the "java.project.referencedLibraries" and change it, as needed.



> Ensure that your Studio classpath matches the Fabric runtime configuration (as defined in `config.ini`). This alignment ensures that code which compiles and runs in Studio will also behave consistently at runtime, preventing class loading issues and unexpected errors

</web>

