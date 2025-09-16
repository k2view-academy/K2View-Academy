# hanging K2cloud config.ini Settings (Technical Note)


## Abstract

The *Changing K2cloud config.ini Settings* technical note describes how to customize and update configuration parameters (in `config.ini`) for K2cloud Spaces under different scenarios. It covers two main phases:

1. **At Space Creation Time** — For Fabric 8.1 and later, users can override certain `config.ini` parameters via the Project’s Advanced Settings when creating a new Space. Some parameters are restricted by the Space Profile and cannot be overridden.

2. **After Space Is Created** — Two methods are provided for modifying `config.ini` settings on existing Spaces:

   * Using `config_update.sh` script on each node to add, update, comment out, or remove key-value pairs.
   * Using Kubernetes Secrets to centrally update configuration values, followed by scaling or restarting pods to apply changes.

The note also highlights limitations, risks (especially configuration drift), and recommendations for consistency, such as centralizing configurations, documenting manual node changes, using rolling restarts, and validating settings across all nodes.

## Link

<ul>
  <li><a href="/articles/44_administration_and_operations/Space_and_Configuration_Management/Technical_Note_Changing_Config_Ini_Settings.pdf">Technical Note - Changing Config.Ini Settings</a>
  </li>  
</ul>

## Keywords

config.ini, K2cloud, config overrides, Fabric 8.1, Space creation, Project Advanced Settings, Kubernetes Secrets, config\_update.sh, Configuration Drift, Node-level configuration, Centralized configuration, Rolling restart, Key-value parameters, Space Profile, Configuration management, Updating Settings, Restricted parameters, Restart behavior, Pod scaling, Secret management

