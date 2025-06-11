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



> **NOTE:** While the filtering mechanism helps prevent class conflicts, it also introduces important constraints. A lower-level class loader may fail to load a class, even if that class is included in its own JAR, because its parent has already loaded the same class (even from another JAR version). This can lead to runtime only that are often non-deterministic, depending on class loading order. Such cases will be demonstrated later on in this article.



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
- Fabric hasn’t yet loaded Jackson

LU loads **its own** version successfully.

#### Conflict: Fabric Already Loaded the JAR

- Fabric has Jackson 2.9 in `fabric/lib`
- Project bundles Jackson 2.14 in its `lib/`
- Jackson is **not** exposed in `PACKAGE_NAMES_CLASS_LOADING_FILTER`.
- Fabric already loaded Jackson

LU will fail to load Jackson because it is forced to reuse the parent’s version, but because it is filtered out, it cannot.

#### LU and Plugin Use Different Versions

- Fabric uses one of the AWS SDK at `plugins`
- LU uses a different AWS SDK version in the project's `lib/`

LU **can** load and use its own (project) version safely, since plugins are **not** parents, but are sibling loaders, and Fabric uses those SDK JARs and not loads them in its loader.











# Fabric and Project Dependencies

Fabric uses a modular and isolated class loading strategy to keep project-specific dependencies separate from the core application. This structure enables projects to bring their own versions of dependencies, even if similar versions are already used by the app or other modules.

Fabric adopts the concept of *parent-first delegation* model, which is the default class loading behavior in Java. Accordingly, when a class loader is asked to load a class, it first delegates the request to its parent class loader. This process repeats recursively up the hierarchy, and if none of the parent class loaders can find the class, the original class loader will then attempt to load it itself.

while adopting Java *parent-first* model, Fabric adds a filtering mechanism to control what the children class loaders can inherit from their parent and uses a *filter-based delegation* model:

- When a child class loader is asked to load a class, it only delegates to the App class loader if the class’s package matches a whitelist.
- This whitelist is defined using the `config.ini` property: `PACKAGE_NAMES_CLASS_LOADING_FILTER`.
  - By default, only `com.k2view.*` is exposed.
  - You can add more packages if needed, but doing so weakens class loader isolation.
- For all other packages, the child loader tries to load the class from its own classpath.
  - If it can’t find the class and it wasn’t eligible for delegation, class loading fails, even if Fabric already loaded it at the App level.



> Use `PACKAGE_NAMES_CLASS_LOADING_FILTER` with caution. It is best to minimize exposure to keep LUs self-contained and prevent accidental version clashes. That is, it reduces the project code usage of core Fabric JARs and reduce the cases where Fabric internal changes affects on project implementation code.



## Fabric Java Packages and Class Loader

Fabric deals with several class loaders: Fabric (App), plugins and LUs.

#### Fabric

- Fabric class loader loads Fabric's JARs (typically `com.k2view.*`), as well as those it uses for its core functionality. These JARs are located at `fabric/lib/fabric` and `fabric/lib/provided`.

- Fabric class loader acts as the *parent* of LU and plugins class loaders.

#### Plugins

- Plugins are packages which are usually SDKs which Fabric used to use for connecting to external services, such as managed storage (e.g. AWS S3, Azure Blob Storage), KMS and SM services.  
- Each plugin has its own dedicated loader and is loaded from `fabric/lib/plugins/{plugin-name}` (e.g., `masterkey-aws`, `search-provider-elastic`) 
- Parent: Fabric (App) class loader.

#### LUs 

- Each LU is packaged into a JAR during project build

- Each LU JAR includes also the project JARS located at `lib/` folder. 

  - The projects' JAR might be added manually or while installing Exchange's extensions, per each extension's need. 
  - `lib` folder might contain also JDBC drivers related JARs. 

  Having the project's JARs part of the LU, enables smooth deployment, and avoid the need of copying them manually into Fabric lib classpath on each of its nodes. 

  >  This is also very useful for cloud based deployments: First, the access to Fabric server is limited, and second - in K8S deployment Fabric is considered as immutable because if something happen or it is moved, the K8S infrastructure brings back Fabric from original image.

- Each LU has its own dedicated class loader.

- Parent: Fabric (App) class loader

#### Interfaces

* As mentioned above, JDBC drivers related JARs might be part of the project's lib, for those interface types which their driver are not provided by Fabric. Each of them is resides under dedicated folder, named by the interface type name. 
* Each interface has its own class loader, even though they are packed as part of LUs.



> **Note**: In previous Fabric versions, project's JARs were added via the [**externalJars**](/articles/31_external_resources/01_external_jars.md) folder. This is still supported for backward compatibility, and are also part of the Fabric class loader. 
>
> As this is a legacy stuff, avoid relying on `externalJars` for new development.



## Example Scenarios

#### LU can use its own JAR version

- Fabric has Jackson 2.9 JAR at fabric `'fabric/lib` folder.
- LU needs Jackson version 2.14
- Jackson 2.14 is then included in project's `lib` folder.
- Fabric does not load Jackson yet.

In this case, LU loads its **own** (project) version.

#### Conflict if Fabric already loaded the JAR

- Fabric has Jackson 2.9 JAR at fabric `'lib` folder.
- Jackson 2.14 is included in project's `lib` folder.
- Fabric already loaded classed from that JAR.

In this case, LU must reuse its **parent's** - Fabric - JAR, even though it is **not** mentioned at `PACKAGE_NAMES_CLASS_LOADING_FILTER` property.

#### LU can use its own AWS SDK JAR

- Fabric uses AWS SDK (`masterkey-aws`)
- Project bundles its own AWS SDK version in project's `lib` folder.

In this case, LU **can** load and use its own (project) version safely, as the plugin loader is sibling (not parent) and Fabric uses those SDK JARs and not loads them.



## Controlling Inheritance

### Config.ini

You can explicitly control what packages are exposed from Fabric to LU using this config.ini : `PACKAGE_NAMES_CLASS_LOADING_FILTER` property. For example:

```
PACKAGE_NAMES_CLASS_LOADING_FILTER=com.k2view.*, software.amazon.awssdk.*
```

By default: only `com.k2view.*` is exposed.

> Use it with caution and sparingly, to avoid breaking LU isolation and create conflicts.

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

