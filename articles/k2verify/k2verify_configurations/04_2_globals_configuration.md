# k2verify Globals Configuration

Configure the following Globals to ensure successful execution of the library.

**REQUIRED GLOBALS:**

These Globals must be defined for k2verify to operate correctly.
1. **K2VERIFY_OPERATIONAL_INTERFACE**: Interface used to store k2verify operational tables, operational data, and report data.
    - In the current version, PostgreSQL is supported for the operational interface.
    - Default value: K2VERIFY_OPERATIONAL_DB (provided with the library).
    - To use a different interface, update this Global to an existing PostgreSQL interface. 
2. **K2VERIFY_OPERATIONAL_SCHEMA**: Schema used for k2verify operational tables, operational data, and report data (for the interface defined in K2VERIFY_OPERATIONAL_INTERFACE).

**OPTIONAL GLOBALS:**

Define these Globals only when executing k2verify on Cassandra source or target tables.

1. **K2VERIFY_CQLSH_BIN_DIR**:
    Directory path to the CQLSH binary.
2. **K2VERIFY_KEYS_TABLE_NAME**:
    Name of the table used to store the keys extracted from the Cassandra table.

**ADVANCED GLOBALS:**

These Globals provide advanced tuning and should be modified only when needed.
1. **K2VERIFY_CONF_SEPARATOR**:
    Delimiter used in MTable configuration definitions.
2. **K2VERIFY_KEYS_FETCH_SIZE**:
    Defines the fetch size (number of records per fetch) when retrieving keys from the database.