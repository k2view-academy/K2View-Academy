# Product Base Commands Built-In APIs

Fabric provides out-of-the-box web services/APIs.

All API accesses are over HTTPS, and are accessed from the Fabric URL endpoint `https://<Domain Name>:<PORT>`



##  Fabric Commands

**POST** `/api/fabric-commands`


Run either a fabric command or a set of fabric commands. 

**Examples:**

1. Retrieves the logical units list 

​		Request Body

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

​		Response Body

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

2. Activate the ref_sync command to sync reference tables

​		Request Body

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

​		Response Body

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



##  Authenticate

**POST** `/api/authenticate`


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

**GET** `/api/isAlive`



**Example:**

- `https://localhost:3213/api/isAlive`




[![Previous](/articles/images/Previous.png)](/articles/15_web_services_and_graphit/03_built_in_common_ws.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/articles/15_web_services_and_graphit/05_custom_ws.md)

