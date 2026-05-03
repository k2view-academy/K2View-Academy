# LU Deployment

Deploy the `verify` Logical Unit:
1. In your Cloud Studio, navigate to: Logical Units->verify.
2. Right-click on the LU name, and choose `Deploy`.
3. Upon deployment, the helper Broadway flow **bwCreateK2verifyTablesNdViews.flow** is automatically executed to create the required operational tables and views in PostgreSQL, based on the configured operational interface and schema.

> ⚠️ **Deployment must be completed before executing any Verify tasks.**