# CDC (Change Data Capture) Messages

Fabric's Change Data Capture (CDC) solution notifies external systems about data changes published via Kafka and also offers cross-instance Search capabilities through its built-in integration with Elasticsearch.


. since it is integrated with  Elasticsearch, also offers cross-instance Search capabilities.

Fabric Studio enables a definition of selected LU tables and columns whose update must publish a CDC message.

For example, if you need to notify an external consumer system about any address change of a customer, you must create CDC indexes for STATE, CITY, STREET, HOUSE_NO, and ZIP_CODE columns of ADDRESS table of CUSTOMER LU as CDC indexes.

Each type of change on the CDC columns generates a different type of a CDC message. 

The following sections describe the CDC message types.

### CDC Schema

Arrived in CDC when an LU is deployed to Fabric for the first time. This message contains the LU schema name and information for all the defined CDC Tables for this schema.

See an example in [Appendix A](/articles/18_cdc_and_search/02_cdc_messages.md#appendix-a-cdc-schema-message--example) of this document. 

### CDC Schema Update

Arrived in CDC upon: 

- Whenever an existing LU is redeployed to Fabric.
- [Drop of an LU](/articles/02_fabric_architecture/04_fabric_commands.md#drop-lu-command).

This message contains the LU schema name and the information on all the affected CDC LU tables, and contains only the relevant changes, i.e. update of the CDC columns.

See an example in [Appendix B](/articles/18_cdc_and_search/02_cdc_messages.md#appendix-b-cdc-schema-update-message--example) of this document.

###  CDC Delete Tables

Arrived in CDC upon: 

- Delete LUI command
- Run **CDC_REPUBLISH_INSTANCE** Fabric command to republish of the CDC data for a given LUI. The CDC data of the LU tables must be re-created by the CDC consumer.

See an example in [Appendix C](/articles/18_cdc_and_search/02_cdc_messages.md#appendix-c-cdc-delete-tables-message--example) of this document. 

### CDC Table Change Info

Arrived in CDC upon:

- Every change of a MicroDB, triggered by an  insert, update or delete statements.
- Run **CDC_REPUBLISH_INSTANCE** Fabric command to republish of the CDC data for a given LUI. 

The message contains a list of PK columns of the LU tables and the relevant changes on the CDC columns.

See an example in [Appendix D](/articles/18_cdc_and_search/02_cdc_messages.md#appendix-d-cdc-table-change-info-message--example) of this document.  

  

## Serialization

A CDC message is stored in a Kafka topic with the CDC consumer name as defined in the Fabric Studio.

Fabric provides a utility JAVA class to serialize or de-serialize a CDC message: 

```
com.k2view.fabric.cdc.Serialization
```

 The  **Serialization** class has two methods:

- ```
  public static CdcMessage fromJson(String msg)
  ```

  This method creates a **CdcMessage** object from the input message.

- ```
  public static String toJson(CdcMessage msg)
  ```

  This method serialized the CDC message to a JSON format.

  

To view the list of Fabric APIs, click **http://[Fabric IP address]:3213/static/doc/user-api/index.html**

 

## Appendices

 

### Appendix A: CDC Schema Message- Example

```
{
  "info":{
    "trxId":"6e9ace70-6e20-461d-966a-8f1c88fbf91c",
    "ts":1587369930093,
    "msgNo":1,
    "msgCount":1
  },
  "luName":"CTN",
  "dataType":"SCHEMA",
  "schema":{
    "name":"CTN",
    "tables":[
     {
       "name":"CTNMAININFO",
       "columns":[
        {
          "name":"ACCOUNT_STATUS",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"MAIL",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"ACCOUNT_NUMBER",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"CTN",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"ACCOUNT_TYPE",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"IP_ADDRESS",
          "type":"TEXT",
          "tags":[
           ""
          ],
          "isPk":false
        }
       ]
     },
     {
       "name":"SUBSCRIBERADDRESS",
       "columns":[
        {
          "name":"ID_NUMBER",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"CITY",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"STREET_NO",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"APARTMENT_NO",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        },
        {
          "name":"POSTAL_CODE",
          "type":"TEXT",
          "tags":[
           ""
          ],
          "isPk":false
        },
        {
          "name":"MAIL",
          "type":"TEXT",
          "tags":[
           ""
          ],
          "isPk":false
        },
        {
          "name":"STREET",
          "type":"TEXT",
          "tags":[
           "data"
          ],
          "isPk":false
        }
       ]
     }
    ]
  }
 }
```

 

### Appendix B: CDC Schema Update Message- Example

#### A.   Schema Update

```
{
   "info":{
      "trxId":"f0626955-2aee-4e83-93d8-d657edc12283",
      "ts":1587370059952,
      "msgNo":1,
      "msgCount":1
   },
   "luName":"CTN",
   "dataType":"SCHEMA_UPDATE",
   "schemaUpdate":{
      "name":"CTN",
      "tables":[
         {
            "name":"CTNMAININFO",
            "updateType":"UPDATED",
            "columns":[
               {
                  "name":"IP_ADDRESS",
                  "type":"TEXT",
                  "tags":[
                     ""
                  ],
                  "updateType":"REMOVED"
               }
            ]
         },
         {
            "name":"T1",
            "updateType":"UPDATED",
            "columns":[
               {
                  "name":"F2",
                  "type":"TEXT",
                  "tags":[
                     ""
                  ],
                  "updateType":"REMOVED"
               },
               {
                  "name":"F3",
                  "type":"TEXT",
                  "tags":[
                     ""
                  ],
                  "updateType":"REMOVED"
               },
               {
                  "name":"F4",
                  "type":"TEXT",
                  "tags":[
                     ""
                  ],
                  "updateType":"REMOVED"
               }
            ]
         }
      ]
   }
}
```



#### B.   Schema Drop

```
{
  "info":{
    "trxId":"5a96898d-a9df-42c0-be66-81bba9339746",
    "ts":1587369528918,
    "msgNo":1,
    "msgCount":1
  },
  "luName":"CTN",
  "dataType":"SCHEMA_UPDATE",
  "schemaUpdate":{
    "name":"CTN",
    "tables":[
      {
        "name":"CTNACCOUNTBALANCE",
        "updateType":"REMOVED",
        "columns":[

        ]
      },
      {
        "name":"CTNMAININFO",
        "updateType":"REMOVED",
        "columns":[

        ]
      },
      {
        "name":"SUBSCRIBERADDRESS",
        "updateType":"REMOVED",
        "columns":[

        ]
      },
      {
        "name":"T1",
        "updateType":"REMOVED",
        "columns":[

        ]
      }
    ]
  }
}
```

 

### Appendix C: CDC Delete Tables Message- Example

```
{
   "info":{
      "trxId":"db7cea26-458d-4477-a2e7-6c67fd1d8781",
      "ts":1587369340824,
      "msgNo":23,
      "msgCount":24
   },
   "luName":"CTN",
   "iid":"1",
   "dataType":"DELETE_TABLES",
   "deleteTables":{
      "tables":[
         "T1", “T2”, “T3”
      ]
   }
}
```

 

### Appendix D: CDC Table Change Info Message- Example

#### A.   Data Change

```
{
   "info":{
      "trxId":"db198b31-736c-4566-aac7-a0891055bbf1",
      "ts":1587369152081,
      "msgNo":5,
      "msgCount":5
   },
   "luName":"CTN",
   "iid":"4",
   "dataType":"DATA_CHANGE",
   "tblChange":{
      "pk":[

      ],
      "dataChange":{
         "type":"INSERT",
         "table":"SUBSCRIBERADDRESS",
         "names":[
            "STREET",
            "STREET_NO",
            "APARTMENT_NO",
            "FIRST_NAME",
            "MAIL"
         ],
         "oldValues":[

         ],
         "newValues":[
            "Straco",
            "412",
            "69",
            "EINHORN",
            "ovAMthQr@gmail.com"
         ]
      }
   }
}
```

 

#### B.   Data Republish

```
{
  "info":{
    "trxId":"db7cea26-458d-4477-a2e7-6c67fd1d8781",
    "ts":1587369340825,
    "msgNo":24,
    "msgCount":24
  },
  "luName":"CTN",
  "iid":"1",
  "dataType":"DATA_REPUBLISH",
  "tblChange":{
    "pk":[

    ],
    "dataChange":{
      "type":"INSERT",
      "table":"T1",
      "names":[
        "F1",
        "NOW"
      ],
      "oldValues":[

      ],
      "newValues":[
        "1",
        "2020/04/16 16:11:43:399"
      ]
    }
  }
}
```

 
