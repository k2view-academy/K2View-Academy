# Installation

#### Requirements & Assumptions
    1. Fabric Version – Fabric version 8.2 or higher is required.
    2. PostgreSQL Integration – The project must have PostgreSQL integrated and available for operational and reporting data.

#### Install from K2 Exchange

    1. Click on the Extensions Icon in Fabric Studio Web
    2. Search for `Verify`
    3. Click the install Button

![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/verifyInstall.png)




#### Validate Verify Library components are added to your Cloud Space
  1. Logical Units:
      - verify.
  2. Shared Objects
      - Broadway:
        * A new folder named `k2verify`: contains all Broadway flows provided by the k2verify library.
      - Java:
        * A new resource file under `recources` named `k2verify`: contains SQL create statements for operational and report tables.
        * A new java folder under `common` named `k2verify`: contains the Java logic used by the k2verify library.
      - Interfaces:
        * A new Interface under `JDBC` named `K2VERIFY_OPERATIONAL_DB`: operational interface used for k2verify operational tables and reporting data.
        * A new Interface under `Other` named `K2VERIFY_SRC_CASS_DETAILS`: holds connection details for the Cassandra source database.
        * A new Interface under `Other` named `K2VERIFY_TAR_CASS_DETAILS`: holds connection details for the Cassandra target database
  3. Libraries:
      - A new jar file under `lib` named `postgresql-42.7.4`.

#### Integrate Verify Web Page

Follow the steps below to merge the Verify application entry into the `apps.json` file

1. In your Cloud Studio, navigate to the existing apps.json file.
2. Open the file and copy the following JSON entry:
   ` { "name": "Verify", "appId": "k2verify" }`
3. Paste the copied JSON entry into your existing apps.json file.
4. Save the file and ensure the JSON structure remains valid.
