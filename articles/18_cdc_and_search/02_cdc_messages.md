# CDC Messages

Fabric's Change Data Capture (CDC) solution notifies external systems about data changes published via Kafka and also offers cross-instance Search capabilities through its built-in integration with Elasticsearch.

When defining LU in the Fabric Studio, selected tables and columns can be set to publish CDC messages each time they are updated. 
For example, to notify an external consumer system about a customer's change of address, the following columns are defined as CDC indexes in the ADDRESS table in the CUSTOMER LU: 
-  STATE
-  CITY
-  STREET
-  HOUSE_NO
-  ZIP_CODE 

A specific CDC message is generated each time a specific CDC indexed column is updated. 

Fabric has the following CDC messages:
<table>
<tbody>
<tr>
<td valign="top" width="350pxl">
<p>CDC Message</p>
</td>
<td valign="top" width="550pxl">
<p>Description</p>
</td>
</tr>
<tr>
<td valign="top" width="3000pxl">
<p>CDC Schema</p>
<p>&nbsp;</p>
</td>
<td valign="top" width="6000pxl">
<p>Generated when an LU is deployed to Fabric for the first time. These messages hold the LU Schema's name and information about its CDC tables.&nbsp;</p>
<p>To see an example, refer to&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP3_CDC_Tali/articles/18_cdc_and_search/02_cdc_messages.md#appendix-a-cdc-schema-message--example">Appendix A</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="350pxl">
<p>CDC Schema Update</p>
</td>
<td valign="top" width="550pxl">
<p>Generated when an LU is redeployed to Fabric or following an <a href="https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP3_CDC_Tali/articles/02_fabric_architecture/04_fabric_commands.md#drop-lu-command">LU drop command&nbsp;</a>and only for relevant changes like CDC column updates. These messages hold the&nbsp;LU Schema name, information about the affected CDC LU tables and specific CDC indexed columns.</p>
<p>To see an example, refer to&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP3_CDC_Tali/articles/18_cdc_and_search/02_cdc_messages.md#appendix-b-cdc-schema-update-message--example">Appendix B</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="350pxl">
<p>CDC Delete Tables</p>
</td>
<td valign="top" width="550pxl">
<p>Generated following a Delete LUI command.&nbsp;<br />To republish the CDC data of a deleted LUI, run the&nbsp;CDC_REPUBLISH_INSTANCE&nbsp;command. Note that the CDC data in the LU tables must be re-created.</p>
<p>To see an example, refer to&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP3_CDC_Tali/articles/18_cdc_and_search/02_cdc_messages.md#appendix-c-cdc-delete-tables-message--example">Appendix C</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="350pxl">CDC Table Change Info</td>
<td valign="top" width="550pxl">Generated each time a MicroDB is updated and triggered by INSERT, UPDATE or DELETE statements. These messages hold a list of PK columns in the LU tables and relevant changes in CDC columns.
<p>To republish the CDC data of an LUI, run the CDC_REPUBLISH_INSTANCE command. Note that the CDC data in the LU tables must be re-created.</p>
<p>To see an example, refer to&nbsp;<a href="https://github.com/k2view-academy/K2View-Academy/blob/KB_DROP3_CDC_Tali/articles/18_cdc_and_search/02_cdc_messages.md#appendix-d-cdc-table-change-info-message--example">Appendix D</a>.</p>
</td>
</tr>
<tr>
<td valign="top" width="350pxl">&nbsp;</td>
<td valign="top" width="550pxl">&nbsp;</td>
</tr>
</tbody>
</table>


## Serialization
CDC messages are stored in Kafka topics by their CDC Consumer Name defined in the Fabric Studio and can be serialized or de-serialized using the Fabric Java Class com.k2view.fabric.cdc. Serialization tool.

Fabric offers two Serialization class methods: 
<p>
<table>
<tbody>
<tr>
<td valign="top" width="300pxl">public static CdcMessage fromJson</td>
<td valign="top" width="600pxl">String msg, creates a CdcMessage object from an input message.</td>
</tr>
<tr>
<td valign="top" width="350pxl">&nbsp;public static String toJson</td>
<td valign="top" width="550pxl">&nbsp;CdcMessage msg, serializes the CDC message to JSON format.</td>
</tr>
</tbody>
</table>
To view the list of Fabric APIs, click http://[Fabric IP address]:3213/static/doc/user-api/index.html
 

## Appendices


### Appendix A: CDC Schema Message - Example

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

 

### Appendix B: CDC Schema Update Message - Example

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

 

### Appendix C: CDC Delete Tables Message - Example

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

 

### Appendix D: CDC Table Change Info Messagen- Example

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

 
