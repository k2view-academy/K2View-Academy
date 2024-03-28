# Product Base Commands Built-In APIs

Fabric provides out-of-the-box web services/APIs.

All API accesses are over HTTPS, and are accessed from the Fabric URL endpoint `https://<Domain Name>:<PORT>`



##  Fabric Commands

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">POST</span>  `/api/fabric-commands`

Run either a fabric command or a set of fabric commands. 

**Examples:**

1. Retrieves the logical units list 

​	Request Body

```
    {
        "commands": [
            {
                "command":"list ?",
                "params":['lut']

            }
        ]
     }
```

​	Response Body

```json
   {
    "results": 
    [
        {
            "resultSet": {
                "columns": [
                    "LU_NAME",
                    "Project Version"
                ],
                "types": [
                    12,
                    12
                ],
                "rows": [
                    [
                        "CRM",
                        ""
                    ],
                    [
                        "Billing",
                        ""
                    ]
                ]
            },
            "duration": 10
        }
     ]
	}
```

2. Activate the ref_sync command to sync reference tables

​	Request Body

```
    {
        "commands": [
            {
                "command":"ref_sync LU_NAME=? TABLES=?;",
                "params":['CRM','ALL']

            }
        ]
     }
```

​	Response Body

```
    {
      "results": [
        {
          "resultSet": {
            "columns": [
              "Table name"
            ],
            "rows": [
              [
                "demo.ref_USA_CoV19_cases_Oct2020"
              ]
            ]
          },
          "duration": 75
        }
      ]
    }
```



> As can be seen, the fabric-commands bring also meta data information and named element. To get a simpler results set, use the *fabric-command* API 



##  Fabric Command

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">POST</span>  `/api/fabric-command`

Run either a fabric command or a set of fabric commands. You shall use ";" followed by a new line to separate between commands.

Example - running 3 commands:

​	Request Body

```json
    {
    	"command": "set sync force;
    	get ?.?;
	    SELECT\ni.InvoiceID,\ni.DueDate,\ni.TotalAmount,\ni.PaymentStatus,\no.OutageID,\no.date_time AS OutageDateTime,\no.estimated_resolution,\no.status AS OutageStatus\nFROM\nInvoice i\nJOIN\nSubscriber s ON i.SubscriberID = s.SubscriberID\nLEFT JOIN\nOutages o ON s.SubscriberID = o.SubscriberID\nWHERE\ns.CustomerID = 1 AND\n(i.PaymentStatus = 'Due' AND i.DueDate < DATE('now') OR\no.status = 'Active');",
    	"params":[
			"Billing",
			"1"
	    ],
	    "allResultSets": true
    }
```

​	Response Body:

```
	[
      [
        {
            "luName": "Billing",
            "iid": "1",
            "version": 1711628351587,
            "action": "UPDATE",
            "notes": ""
        }
      ],
      [
        {
            "INVOICEID": 2738,
            "DUEDATE": "2023-06-29",
            "TOTALAMOUNT": 8.09,
            "PAYMENTSTATUS": "Paid",
            "OUTAGEID": 4701,
            "OutageDateTime": "2024-02-27 22:29:31.184",
            "OutageStatus": "Active"
        },
        {
            "INVOICEID": 2742,
            "DUEDATE": "2023-07-29",
            "TOTALAMOUNT": 8.09,
            "PAYMENTSTATUS": "Paid",
            "OUTAGEID": 4701,
            "OutageDateTime": "2024-02-27 22:29:31.184",
            "OutageStatus": "Active"
        }
      ]
	]
```



<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">GET</span>  `/api/fabric-command?command=<fabric command/s>[&allResultSets=true|false]`

Similar to the POST method but send the information using GET parameters.



##  Authenticate

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">POST</span>  `/api/authenticate`


Generate a JWT digitally signed cookie for Web-Services calls on the same session. Consequently, future Fabric Web-services calls will not require a token as a parameter. 

The web service expects either an API Key to be sent in the request body or username and password, as follows:

- Authenticate by apikey 

  Request Body:

```
{
  "apikey": "string"
}
```

- Authenticate by user/password

  Request Body:

```
{
  "username": "string",
  "password": "string",
}
```



## Fabric isAlive

Indicates whether Fabric is up and running. 

<span style="border-radius: 2em; background-color: #0969da; padding: 0 7px; color:white">GET</span>  `/api/isAlive`



**Example:**

- `https://localhost:3213/api/isAlive`




[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/03_built_in_common_ws.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/05_custom_ws.md)

