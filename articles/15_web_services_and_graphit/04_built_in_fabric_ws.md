# Fabric Built-In APIs

Fabric provides out of the box web services / APIs.

All API access is over HTTPS, and accessed from Fabric URL endpoint `https://<Domain Name>:<PORT>`



##  Fabric Commands

**POST** `/api/fabric-commands`


Run fabric command or a set of fabric commands. 

**Examples:**

1. Request Body

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

Response Body

```
{
  "results": [
    {
      "resultSet": {
        "columns": [
          "LU_NAME"
        ],
        "rows": [
          [
            "Customer"
          ],
          [
            "CRM"
          ]
        ]
      }
    }
  ]
}
```

    2. Request Body

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

Response Body

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
            "lion.ref_USA_CoV19_cases_Oct2020"
          ]
        ]
      },
      "duration": 75
    }
  ]
}
```



## Fabric isAlive

Indicates whether the Fabric is up and running. 

**GET** `/api/isAlive`



**Example:**

- `https://localhost:3213/api/isAlive`

  



[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/03_built_in_common_ws.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/05_custom_ws.md)

