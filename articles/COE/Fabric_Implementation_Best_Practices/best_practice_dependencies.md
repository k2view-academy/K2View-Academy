# Fabric and Project Dependencies

Fabric uses a modular and isolated class loading strategy to keep **project-specific dependencies** separate from the **core application**. This structure enables projects to bring their own versions of dependencies, even if similar versions are already used by the app or other modules.

Fabric adopts the **parent-first delegation**, which is the default class loading behavior in Java.

Accordingly, when a class loader is asked to load a class, it first delegates the request to its parent class loader.
This process repeats recursively up the hierarchy, and if none of the parent class loaders can find the class, the originate class loader will then attempt to load it itself.



## Fabric Class Loader Types

Fabric deals with 3 class loaders: Fabric (App), plugins and LUs.

#### Fabric Class Loader (App)

- Loads Fabric's JARs (typically `com.k2view.*`), as well as those it uses for its core functionality. These JARs are located at `fabric/lib/fabric` and `fabric/lib/provided`.

  > Note: In previous Fabric versions, project's JARs were added via the [**externalJars**](/articles/31_external_resources/01_external_jars.md) folder. This is still supported for backward compatibility, and are also part of the Fabric class loader. 
  >
  > As this is a legacy stuff, avoid relying on `externalJars` for new development.

- Acts as the parent of LU class loaders

- It only exposes packages mentioned at `PACKAGE_NAMES_CLASS_LOADING_FILTER` property at config.ini

#### Plugin Class Loaders

- Load independently from `fabric/lib/plugins/{plugin-name}`
- Each plugin (e.g., `masterkey-aws`, `search-provider-elastic`) has its own dedicated loader.
- Not in the parent-child hierarchy, rather considered as siblings.
- Cannot see App or LU classes and vice versa.

#### LU Class Loaders 

- Each LU is packaged into a JAR during project build, and include the project JARS at `lib/` folder (included during LU build)

  > Note: `lib` folder might contain also JDBC drivers related JARs. Each of them is resides under dedicated folder, named by the interface type name. These JARs are not packed with the LUs.

- Parent: **App class loader**

- Can only access packages from the parent if they match `PACKAGE_NAMES_CLASS_LOADING_FILTER` (e.g., `com.k2view.*`)



This model ensures class reuse and avoids conflicts, but it also introduces constraints when trying to override classes in lower-level loaders, such as LUs, as demonstrated in the following examples.



## Example Scenarios

#### LU Can Use its own JAR Version

- Fabric has Jackson 2.9 JAR at fabric `'fabric/lib` folder.
- LU needs Jackson version 2.14
- Jackson 2.14 is then included in project's `lib` folder.
- Fabric does not load Jackson yet.

In this case, LU loads its **own** (project) version.

#### Conflict if Fabric Already Loaded JAR

- Fabric has Jackson 2.9 JAR at fabric `'lib` folder.
- Jackson 2.14 is included in project's `lib` folder.
- Fabric already loaded classed from that JAR.

In this case, LU must reuse its **parent's** - Fabric - JAR, even though it is **not** mentioned at `PACKAGE_NAMES_CLASS_LOADING_FILTER` property.

#### LU Can Use Own AWS SDK JAR

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

Studio project's complication and build is not under these dependencies rules and constraints. 

* By default it is more strict about Fabric the classpath, encouraging developers to avoid using Fabric core (not `usercode`) code. 

  By doing that, you reduce the risk of case, where Fabric core is changed.

* In any case, you shall align Studio classpath settings with config.ini, to ensure that what you use in Studio is also available by runtime class loaders.

* To set the Studio classpath :

  * Use the bottom-left **Manage Preferences** ![img](https://support.k2view.com/Academy/articles/04_fabric_studio/images/web/settings.png) gear icon > **Settings**.
  * Move to the Workspace tab
  * Click the top right **Open Settings (JSON)** `{}` icon 
  * look for the "java.project.referencedLibraries" and change it, as needed.

</web>

