# How To Use

## k2verify Configurations

### Web Page Integration

##### Integrate k2verify Web Page
If your Cloud Studio environment already uses an apps.json file, follow the steps below to merge the k2verify application entry.
1. In your Cloud Studio, navigate to: Logical Units->k2verify->Web->apps.json
2. Open the file and copy the following JSON entry:
    ` { "name": "Verify", "appId": "k2verify" }`
3. Paste the copied JSON entry into your existing apps.json file.
4. Save the file and ensure the JSON structure remains valid.

##### Deploy k2verify LU
Deploy the `k2verify` Logical Unit:
1. In your Cloud Studio, navigate to: Logical Units->k2verify.
2. Right-click on the LU name, and choose `Deploy`.
3. Upon deployment, the helper Broadway flow **bwCreateK2verifyTablesNdViews.flow** is automatically executed to create the required operational tables and views in PostgreSQL, based on the configured operational interface and schema.