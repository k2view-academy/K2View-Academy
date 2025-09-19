# K2cloud Roles and Identity Mapping

## Abstract

This technical note explains how K2cloud implements Role-Based Access Control (RBAC) across projects, spaces, and Test Data Management (TDM) environments. It describes how customer-managed identity providers (IDPs) work with the K2cloud CyberArk federation service to enforce consistent authentication and authorization. The document details the layered approach to access control at the cloud and space levels, the process of mapping IDP groups to Fabric roles, and the creation and assignment of roles and permissions.

Practical usage scenarios show how access can be limited to specific areas and how deployment permissions are assigned to authorized groups, ensuring least-privilege access and separation of duties. The note also emphasizes TDM-specific roles and permissions, which expand RBAC controls to data provisioning, masking, and synthetic data generation workflows.

Together, these guidelines offer a framework for customers to align K2cloud access management with enterprise identity policies while maintaining flexibility and security across environments.


## Link

<ul>
		<li><a href="/articles/44_administration_and_operations/Identity_Federation/K2cloud_Roles_and_Identity_Mapping.pdf">K2cloud Roles and Identity Mapping</a>
    </li>
</ul>

## Keywords

Role-Based Access Control (RBAC), Identity Provider (IDP), CyberArk Federation Service, Single Sign-On (SSO), Fabric Roles, Cloud User, Space Admin, Deployment Authorization
