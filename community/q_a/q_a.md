### Broadway with Cassandra Interface Consistency Level

**Question:**

Do we need to / Can we change the consistency level on WRITE and READ for **DbLoad** and **DbCommand** actors? 

**Answer:**

The consistency level is set via the Cassandra interface using the server connection string.
Should you need to change the consistency level during your flow, define an additional interface and use it on your actor.

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/303)



### Stopping CDC on Local Debug

**Question:**

How do I disable CDC on the local Fabric / Debug?

**Answer:**

You can update the config.ini parameter of the local Fabric debug and set the CDC_PUBLISH_MODE parameter to OFF in order to avoid publishing CDC messages to the server. Alternatively, you can set this parameter to IF_SETUP (Studio Debug default) in order to publish the CDC messages, in case the CDC publisher has been configured in the config.ini file. [Click to read CDC messages](https://support.k2view.com/Academy_6.5/articles/18_fabric_cdc/03_cdc_messages.html).

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/287)



### Multiple GETs and Release Statement

**Question:**

Why is there a note in the help section of the Release Statement stating the following:

Important: When there is a use case of multiple Get in a loop on the same Fabric session, it is recommended to use the Release command (without LU name) after each iteration.

**Answer:**

It is recommended to use the Release command without the LU name,only in case you need to release the LUIs of all LUs in the session. In case you need to release an LUI of a specific LU, you need to set the LU name in the release command.

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/270)



### Encryption error in environment <env_name>

**Question:**

When trying to save the Environments file from Studio, we sometimes get an error stating "Encryption error in environment <env_name>".

Why do we get this error? What is the logical reason behind this error message?

**Answer:**

We assume that in such cases you use "Fabric URL" option at the environment definitions and you switched from one to another, or you just start using "Fabric URL". In such cases re-key is required. [Click to read about Fabric security interfaces](https://support.k2view.com/Academy_6.5/articles/26_fabric_security/04_fabric_interfaces_security.html).

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/264)



### Fabric versions supporting TDM7

**Question:**

In the past, we had a strong dependency on a specific Fabric version - e.g. Fabric version 5.3 for TDM version 5.1. Is it still the case with TDM7?
TDM7 release notes mention fabric 6.4.1, but now 6.4.2 is available. We are wondering whether  it is OK to go ahead with the latest Fabric versions TDM projects.

**Answer:**

You can run TDM 7.0.1 on either Fabric 6.4.1 or Fabric 6.4.2. TDM 7.1 is based on Fabric 6.5.

[Read more >](https://github.com/k2view-academy/K2View-Academy/issues/248)

