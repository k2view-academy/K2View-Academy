# k2verify Interfaces Configuration and Validation

1. Configure and validate k2verify operational interface:
    - In your Cloud Studio, Navigate to Implementation->Shared Objects->Interfaces->JDBC
    - Open `K2VERIFY_OPERATIONAL_DB` interface.
    - Enter the required connection details for your PostgreSQL operational database:
      - Host – PostgreSQL host of your cloud environment
      - Port – PostgreSQL port of your cloud environment
      - Database – Name of the operational database
      - User – Database user credentials
      - Password – Authentication password for the database user
    - Validate interface connectivity by clicking "Test Connection" on the interface to ensure the connection is functioning as expected.

![Extensions](https://raw.githubusercontent.com/k2view/libs-exchange-resources/main/d2d-images/testConnection.png)

2. In case one of the source or target databases is Cassandra, Configure the following custom interfaces provided by the library
    - In your Cloud Studio, Navigate to Implementation->Shared Objects->Interfaces->Other
    - Open `K2VERIFY_SRC_CASS_DETAILS` interface.
    - Enter the required connection details for your Cassandra source database:
        * Host – Cassandra host of your cloud environment
        * Port – Cassandra port of your cloud environement
        * User – Cassandra user credentials
        * Password – Cassandra authentication password
    - Save the Interface configurations.
    - Repeat the same steps for the `K2VERIFY_TAR_CASS_DETAILS` interface to configure the Cassandra target database connection parameters.
