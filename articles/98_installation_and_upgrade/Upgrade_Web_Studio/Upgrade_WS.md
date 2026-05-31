# **Upgrading Fabric Web Studio**

This document describes the supported upgrade procedure for Fabric Web Studio for Docker Compose environments.

## **Overview**

Starting with Fabric Web Studio for Docker Compose package version 2.1.14, upgrades can be performed using the built-in `k2space.sh` utility.

The supported upgrade approach preserves:

- the existing Space configuration,
- persistent runtime state,
- PostgreSQL/System DB content,
- and the existing encryption context.

This is the recommended approach for upgrading existing Studio environments.

## **Important Considerations**

The recommended upgrade procedure is an in-place upgrade of the existing Studio environment.

Avoid recreating the entire VM/container environment from scratch during the upgrade process unless the deployment is intentionally designed as a disaster recovery (DR) or an immutable infrastructure reprovisioning workflow.

Recreating the environment without preserving the persistent state may result in:

- loss of the original encryption context,
- loss of PostgreSQL/System DB data,
- inability to decrypt previously encrypted interface/environment credentials,
- and redeployment failures for projects containing encrypted values.

If environments are intentionally treated as disposable/recreatable infrastructure, K2view strongly recommends:

- externalizing secrets using a supported secrets manager,
- and/or preserving the original encryption material and persistent database state.

## **Verify Package Version**

Verify that the Studio for Docker Compose package version is 2.1.14 or newer.

The package version can be viewed in the `.VERSION` file located in the root directory of the Studio package.

Example:

```bash
cat .VERSION
```

## **How to Learn about the Default Fabric Version**

You will find along with the .VERSION file, the .env file. This file configures, among other things, the location of where Fabric are pulled from during the space creation or upgrade process. The "FABRIC_VERSION" that is set in this file corresponds to the latest version set by K2view at the time of releasing the package. It can be overridden. Additionally, you can pass the version at installation or upgrade time.

Excerprt from the .env file

```bash
 # Images
 FABRIC_IMAGE=docker.share.cloud.k2view.com/k2view-images/fabric-studio
 FABRIC_VERSION=8.3.3_10
```

## **Upgrade Procedure**

### **Step 1 — Stop Active Development Activities**

Before upgrading:

- stop active deployments,
- ensure no development activity is occurring,
- and ensure backups/snapshots exist in accordance with your operational standards.

## **Step 2 — Execute the Upgrade**

Use the `k2space.sh upgrade` command.

Example:

```bash
./k2space.sh upgrade <space-name> --fabric-version=<new-version>
```

Example:

```bash
./k2space.sh upgrade myspace --fabric-version=8.4.2_28
```

The upgrade process:

- preserves the existing Space configuration,
- preserves environment/profile settings,
- recreates containers using the updated image,
- and maintains the existing persistent runtime state.

## **Supported Upgrade Flags**

----

```bash
**`--fabric-version`**
```

Updates the Fabric/Studio image version.

Example:

```bash
./k2space.sh upgrade myspace --fabric-version=8.4.2_28
```

----

```bash
**`--heap`**
```

Overrides the configured JVM heap size.

Example:

```bash
./k2space.sh upgrade myspace --heap=16G
```

----

```bash
**`--port`**
```

Overrides the exposed UI port.

Example:

```bash
./k2space.sh upgrade myspace --port=8443
```
----

```bash
**`--safety-bypass`**
```

Bypasses the working-directory safety validation.

Use only if you intentionally upgrade from a directory other than the original Space creation directory.

Example:

```bash
./k2space.sh upgrade myspace --fabric-version=8.4.2_28 --safety-bypass
```

## **Alternative Upgrade Method**

Alternatively, the Fabric image version may be updated directly in the deployment `.env` file used by the existing Space deployment.

After updating the version:

```bash
docker compose up -d
```

This approach must still preserve:

- the existing persistent storage,
- PostgreSQL/System DB content,
- and encryption context.

Do not recreate the environment from scratch unless intentionally implementing a DR/reprovisioning workflow.



## **Post-Upgrade Validation**

After upgrade:

1. Verify containers are healthy:

```bash
docker ps
```

2. Verify Studio accessibility.
3. Verify Fabric startup completed successfully.
4. Verify project deployment succeeds.
5. Verify interfaces and environments load correctly and test them.

