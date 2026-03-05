# K2deployer Change Log

**Version 1.8.27**
* This release incorporates the latest version of the K2cloud Helm charts
* Includes also an operating system patch

**Version 1.8.26**
* Enhancements
  * **Azure App Registration Support**  
    Added support for Azure App Registration integration.
  * **Federated Credentials for Multi-Tenant Applications**  
    Introduced federated credential binding for multi-tenant Azure App Registrations, while preserving backward compatibility with existing logic.

* Fixes
  * **Offline Helm Chart Fix**  
    Resolved an issue in offline Helm deployments related to the `storage.allocated_amount` configuration parameter.

**Version 1.8.25**
* Fixes quoting issues with annotation values containing placeholders that affected the ability to 1) resolve a certificate name used by AGIC and 2) perform correct PostgreSQL URL expansion for a managed Azure PostgreSQL database.
* Addresses Common Vulnerabilities and Exposures (CVE) in K2deployer. 
