# Fabric 8.5 IID Finder Upgrade to Fabric Job

## Purpose

This document describes the changes introduced to the IID Finder in Fabric 8.5 and the activities required when upgrading an existing IID Finder deployment.

In Fabric 8.5, the IID Finder is no longer executed as a standalone process. It is executed as a Fabric job.

The implementation is designed to be as transparent as possible for users. Existing Broadway flows are preserved and continue to be supported under the new approach.

## Summary of Changes

The following changes apply to the IID Finder in Fabric 8.5:

* The IID Finder standalone process is replaced by a Fabric job.
* The IID Finder start and stop shell scripts are removed.
* IID Finder logs are written to the k2fabric log files.
* IID Finder JMX metrics are merged with the Fabric JMX metrics.
* IID Finder JVM options are no longer applicable.
* The **iifConfig.ini** configuration file is removed. Its content is relocated to the PubSub, SystemDB, and finder sections of **config.ini**.
* Job affinity must be defined for the IID Finder and Delta jobs.
* The IID Finder hook (filter) is implemented in a Logical Unit instead of in a separate JAR.

## Logging

IID Finder log messages are written to the k2fabric log files.

As a result, the dedicated IID Finder log files and their associated logback configuration are obsolete and can be removed.

## Resource Allocation

Because the IID Finder no longer runs as a separate process, its JVM options are no longer relevant and the resources previously allocated to that process can be released.

Fabric requires additional resources to host the IID Finder job. Review and adjust the Fabric resource requirements accordingly, including the allocated heap size.

## Monitoring

IID Finder JMX metrics are merged with the Fabric JMX metrics and are exposed through the standard Fabric JMX interface.

Existing monitoring definitions that reference the IID Finder process directly must be updated.

## Configuration

The **iifConfig.ini** file is removed. Its settings are relocated to **config.ini** as follows:

* Kafka settings are replaced by the PubSub settings in **config.ini**, according to the section name mapping between the two files. Review the PubSub section of **config.ini** and confirm that each Kafka setting previously defined in **iifConfig.ini** has an equivalent PubSub setting.
* Cassandra settings are replaced by the SystemDB settings.
* Properties defined in the **finder** section of **iifConfig.ini** are moved to the **finder** section of **config.ini**.

Note that using links for the **SourceDbQuery** actor parameters (**sql**, **sourceTable**) causes issues with XML generation. Use constants instead.

## Finder Hook (Filter)

In previous versions, implementing an IID Finder hook required building a separate JAR.

In Fabric 8.5, the hook implementation is moved into a Logical Unit. By default this is the IIDF Logical Unit, but any Logical Unit can be used.

The implementing class must implement the **IidFinderUserFilterInterface** interface and override only one of the following methods:

* **filterSingle()** - to alter or drop a single message.
* **filter()** - to return a list of messages in response to a single message.

The following configuration parameters apply:

* **FILTER_LU_NAME** - the Logical Unit containing the filter implementation. The default value is IIDF. Change this value when the filter is defined in a different Logical Unit.
* **USER_FILTER_CLASS** - the filter implementation class. The value must be the full class name. Set **FILTER_LU_NAME** when using a Logical Unit other than IIDF.

## Job Affinities

Since the IID Finder now runs as a Fabric job, affinities must be defined for the IID Finder and Delta jobs.

Define these affinities through the Fabric Admin as required by the deployment.

Click here for more information about [Affinity Management](/articles/20_jobs_and_batch_services/19_affinity_management.md).

## Upgrade Checklist

1. Review the release notes for the target version.
2. Stop the IID Finder process and remove the IID Finder start and stop scripts.
3. Migrate the **iifConfig.ini** settings into the PubSub, SystemDB, and finder sections of **config.ini**.
4. Remove the obsolete IID Finder logback configuration and log file definitions.
5. Remove the IID Finder JVM options and adjust the Fabric resource allocation.
6. Migrate the finder hook implementation from the external JAR into a Logical Unit.
7. Define the IID Finder and Delta job affinities through the Fabric Admin.
8. Update monitoring definitions to use the merged Fabric JMX metrics.
9. Validate the IID Finder job execution and the existing Broadway flows.
