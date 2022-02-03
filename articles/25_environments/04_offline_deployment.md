<studio>

# Offline Environment Deployment

Offline environment deployment is used to deploy an environment to a server from an XML file. 

Do the following:

1. Connect to a server using the Fabric Console.

2. Copy the **Environments.k2fabEnv.XML** file from the following Windows location: **[Fabric Project's Directory]\\[Project Name]\Implementation\SharedObjects\Environments** or edit the existing XML file manually.

3. Deploy it using the following command:

   ~~~
   Deploy environments from file ‘{filename}’
   ~~~

Where {filename} includes the file's path and name on the server.

Fabric encrypts the passwords in the file (if they are not already encrypted) and saves the XML file with the encrypted passwords.

Note that deploying an XML file overrides all existing environments except for *_dev*, which is the default environment. If the environment exists in Fabric, but not in the deployed XML file, it is removed from Fabric.

### XML File Example

~~~
<?xml version="1.0" encoding="utf-8"?>
<FabEnvironmentsManager xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <Name>Environments</Name>
  <EnvironmentsList>
    <Environment enabled="true" name="ENV1">
      <DbInterfacesList>
        <DbInterface k2StudioObjectName="DB_CASSANDRA" dbType="cassandra" active="true">
          <dbHost>10.21.2.69</dbHost>
          <dbPort>9042</dbPort>
          <dbUser>k2admin</dbUser>
          <dbPasswordEncrypted>rf/LY4LdIwL5Q+F3C5TMNWv5gAw814CnqcsgbfyNkhU=:bOzdf3eL8LrbgR81zptHoSyKueeR9F75UdwbwWX1MxA=</dbPasswordEncrypted>
          <connString>jdbc:cassandra://10.21.2.69:9042?consistency=QUORUM</connString>
          <minConnectionsNumber>0</minConnectionsNumber>
          <maxConnectionsNumber>60</maxConnectionsNumber>
        </DbInterface>
        <DbInterface k2StudioObjectName="HIS_DB" dbType="oracle" active="true">
          <dbHost>10.21.2.64</dbHost>
          <dbPort>1521</dbPort>
          <dbScheme>XE</dbScheme>
          <dbUser>TDM_SOURCE</dbUser>
          <dbPasswordEncrypted>EJeepLJexKPIEw5s9Mqy2lLpOzPuHQLLDWQdyWwovSY=:gc2q4ziZnB6Ihrx/ticaTa0Hqlx0a04N8Ujk+0bbjBg=</dbPasswordEncrypted>
          <connString>jdbc:oracle:thin:@10.21.2.64:1521/XE</connString>
          <minConnectionsNumber>0</minConnectionsNumber>
          <maxConnectionsNumber>60</maxConnectionsNumber>
        </DbInterface>
        <DbInterface k2StudioObjectName="LIS_DB" dbType="postgresql" active="true">
          <dbHost>10.21.2.64</dbHost>
          <dbPort>5433</dbPort>
          <dbScheme>postgres</dbScheme>
          <dbUser>postgres</dbUser>
          <dbPasswordEncrypted>QSvWFjtcJ+pWYgF8VecrYxo2UmCTm0420hz8YbcFB5Q=:5bJumCAZyUtvuHC5CqrTTcp19f8ie0999vyQG68A5Wo=</dbPasswordEncrypted>
          <connString>jdbc:postgresql://10.21.2.64:5433/postgres?stringtype=unspecified</connString>
          <minConnectionsNumber>0</minConnectionsNumber>
          <maxConnectionsNumber>60</maxConnectionsNumber>
        </DbInterface>
        <DbInterface k2StudioObjectName="TARGET_DB" dbType="oracle" active="true">
          <dbHost>10.21.2.64</dbHost>
          <dbPort>1521</dbPort>
          <dbScheme>xe</dbScheme>
          <dbUser>TDM_TARGET</dbUser>
          <dbPasswordEncrypted>2FLgpNlqwxI4O+y8AwQpQodKVZiZiU51Obm4iqb2kMU=:GXM0qdrtHJUMQZ+Fa61tHlJHG+4m1jkR1qQL2Ua0Zhw=</dbPasswordEncrypted>
          <connString>jdbc:oracle:thin:@10.21.2.64:1521/xe</connString>
          <minConnectionsNumber>0</minConnectionsNumber>
          <maxConnectionsNumber>60</maxConnectionsNumber>
        </DbInterface>
        <DbInterface k2StudioObjectName="TARGET_PG" dbType="postgresql" active="true">
          <dbHost>10.21.2.64</dbHost>
          <dbPort>5434</dbPort>
          <dbScheme>postgres</dbScheme>
          <dbUser>postgres</dbUser>
          <dbPasswordEncrypted>dqmdIUWuyC+4KaNDEKDlBimtd2utoESMq2Oj4NhUzCY=:X8P+ihKPTG2WuwfX0xztOPSS3lDLrr7Y+UrkzjkHf/c=</dbPasswordEncrypted>
          <connString>jdbc:postgresql://10.21.2.64:5434/postgres?stringtype=unspecified</connString>
          <minConnectionsNumber>0</minConnectionsNumber>
          <maxConnectionsNumber>60</maxConnectionsNumber>
        </DbInterface>
        <DbInterface k2StudioObjectName="TDM" dbType="postgresql" active="true">
          <dbHost>10.21.2.69</dbHost>
          <dbPort>5432</dbPort>
          <dbScheme>TDMDB</dbScheme>
          <dbUser>tdm</dbUser>
          <dbPasswordEncrypted>G40KJfdkxcNAR3O4AvhRG4LI5bbctSaVgsAINS7oMpg=:kleDmQHaba5HuipBJcr7UBFVBE330iVmDRmCFGZ5Flo=</dbPasswordEncrypted>
          <connString>jdbc:postgresql://10.21.2.69:5432/TDMDB?stringtype=unspecified</connString>
          <minConnectionsNumber>0</minConnectionsNumber>
          <maxConnectionsNumber>60</maxConnectionsNumber>
        </DbInterface>
      </DbInterfacesList>
      <GenericInterfacesList>
        <GenericInterface type="REDIS" displayName="Redis" enabled="true" version="1.8">
          <Name>FabricRedis</Name>
          <Properties>
            <Property name="Servers" dataType="string" mandatory="true">
              <Value>10.21.2.69</Value>
            </Property>
            <Property name="Password" dataType="password">
              <Value>3CPlQl20N1nyIA1VsgdiuBNNmH+S9OE41A7sPrllRwI=:O/OpRncg9GNaxLV1lfa95dSgApBJqBMyjcsO+e6Ky4s=</Value>
            </Property>
            <Property name="ConnectionTimeout" dataType="integer">
              <Value>10000</Value>
            </Property>
            <Property name="SoTimeout" dataType="integer">
              <Value>10000</Value>
            </Property>
            <Property name="MaxAttempts" dataType="integer">
              <Value>10</Value>
            </Property>
            <Property name="MaxTotal" dataType="integer">
              <Value>128</Value>
            </Property>
            <Property name="MaxIdle" dataType="integer">
              <Value>128</Value>
            </Property>
            <Property name="MinIdle" dataType="integer">
              <Value>16</Value>
            </Property>
            <Property name="TestOnBorrow" dataType="boolean">
              <Value>True</Value>
            </Property>
            <Property name="TestOnReturn" dataType="boolean">
              <Value>True</Value>
            </Property>
            <Property name="TestWhileIdle" dataType="boolean">
              <Value>True</Value>
            </Property>
            <Property name="MinEvictableIdle" dataType="long">
              <Value>60000</Value>
            </Property>
            <Property name="EvictionRunIntervals" dataType="long">
              <Value>30000</Value>
            </Property>
            <Property name="TestPerEviction" dataType="int">
              <Value>3</Value>
            </Property>
            <Property name="BlockWhenExhausted" dataType="boolean">
              <Value>False</Value>
            </Property>
          </Properties>
          <TestConnection enabled="false" />
        </GenericInterface>
        <GenericInterface type="CUSTOM" displayName="Custom" enabled="true" version="1.2">
          <Name>REDIS</Name>
          <Properties>
            <Property name="Host" dataType="string">
              <Value>10.21.2.69</Value>
            </Property>
            <Property name="Port" dataType="integer">
              <Value>6379</Value>
            </Property>
            <Property name="User" dataType="string">
              <Value>redis</Value>
            </Property>
            <Property name="Password" dataType="password">
              <Value>j+ABoVf02YBOSRZf86+Fl0vFmGBZiZI+TK5wwXWJeJE=:FUuUui2MhDnInQCGJGnKE2MOXJ4a51l9EScYFDIQ/NM=</Value>
            </Property>
            <Property name="Data" dataType="string" multiLine="true">
              <Value />
            </Property>
          </Properties>
          <TestConnection enabled="false" />
        </GenericInterface>
      </GenericInterfacesList>
    </Environment>
  </EnvironmentsList>
</FabEnvironmentsManager>
~~~





[![Previous](/articles/images/Previous.png)](03_deploy_env_from_Fabric_Studio.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_set_and_list_commands.md)



</studio>



