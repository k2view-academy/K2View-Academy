# Protecting Sensitive Values using HashiCorp (Technical Note)


## Abstract

The *Protecting Sensitive Values using HashiCorp* technical note describes how to securely handle sensitive configuration values (such as `auth_token`, `secret_id`, and security tokens) in K2view via integration with HashiCorp Secret Manager. It outlines two main mechanisms: using the **Project Advanced Settings** UI to override `config.ini` values for HashiCorp credentials (which are automatically encrypted), and using HashiCorp secret references in user-facing connection strings and UI fields. The document emphasizes that overridden sensitive values are never stored in plain text, and also specifies operational steps (like pausing/resuming a space) required for the changes to take effect.

## Link

<ul>
  <li><a href="/articles/44_administration_and_operations/Space_and_Configuration_Management/Technical_Note_Protecting_Sensitive_Values_using_HashiCorp.pdf">Technical Note - Protecting Sensitive Values using HashiCorp</a>
  </li>
</ul>

## Keywords

HashiCorp, Sensitive Values, Secret Manager, auth\_token, secret\_id, Encryption, config.ini override, Project Advanced Settings, Connection UI, Secret substitution, Security Token, Connection String, \${secretmanager}, Plain-text avoidance, Pause/Resume Space, Fabric Orchestration, UI Security, Secretkey, Secure Configuration, HashiCorp Integration

