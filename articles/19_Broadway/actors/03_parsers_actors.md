# Parser Actors

Broadway has a **parsers** category of Actors that parse input stream into different formats like JSON, CSV and XML.

When reading input stream, they parse it into valid objects based on the specific delimiters of each input format while holding only one object in the memory at a time.

### JsonParser Actor

The **JsonParser** Actor analyzes input streams, represented by an iterable collection of blobs or strings, and returns a collection of the JSON objects found in the stream. If the **single** input attribute is set to True, the Actor expects only a single object in the input stream. Otherwise, the parser can handle input with multiple JSON objects.


The **json.flow** example shows how the **JsonParser** handles two types of inputs - a single JSON object and one with multiple objects.

Click **Actions** > **Examples** in the [Main menu](../18_broadway_flow_window.md#main-menu) to open the **json.flow** example. 

![image](../images/99_17_02_tutorial.PNG)



### CsvParser Actor

The **CsvParser** Actor analyzes an input stream and returns an array of objects whereby each array row is a row of the input CSV stream. The parser runs until it detects the end of the stream.

If the **header** input argument is set to True, the Actor uses the first row as a header row. In this case, the labels in the header are used to mark the row object.

Parsers are usually followed by an iteration that allow iterating over each row in the parser's output object consecutively.

**CsvParser Flow Example**

The following example shows how to read and parse a CSV file which includes a list of countries and their area codes, to find an area code of a specific country provided as external input argument. 

![image](../images/99_actors_03_2.PNG)

The parser processes the file's rows consecutively by validating the data in Stage 5 using the [**JavaScript** Actor's code](01_javascript_actor.md#special-keywords-and-conventions) via access to the **flowArgs** arguments and the **contextLoop** object. When the required country is found, the iteration stops.

```java
if (input1.SUBSCRIBER_TYPE == flowArgs["input_subs_type"]) {
    contextLoop.stop();
    input1.SUBSCRIBER_DESC;
}
```

### XmlParser and XmlParserLegacy

**XmlParser** Actor, analyzes an input stream and outputs the objects found in the stream. 

Starting Fabric 6.5.3, there are two Actors:

* The existing **XmlParser** Actor is renamed to **XMLParserLegacy** and it supports parsing an XML with a list of objects only, without attributes.
* The new **XMLParser** supports parsing an XML with a list of objects that can include attributes. Example of XML that can be parsed by **XmlParser** Actor is:

~~~xml
<office name="New York">
	<user id="1" name="John" last_name="Wick">
    	<room>123</room>
        <phone_number>987654321</phone_number>
        <phone_number>123456789</phone_number>
    </user>
    <user id="2" name="Alice" last_name="Liddell">
        <room>wonderland</room>
        <phone_number>3334445555</phone_number>
        <phone_number>7776667777</phone_number>
    </user>
</office>
~~~

The enhanced functionality of the **XMLParser** Actor is that it enables setting an object name to the parsed XML elements and parsed attributes using its new input arguments **valueField** and **attributesField**.

* The input arguments **valueField** and **attributesField** have default values that can be changed or set to **null**.
* When an XML includes an element with attributes and a primitive value (not a nested XML element), setting **valueField** to **null** (empty value) is not supported.

**Example of a parsed object when valueField = '_value' and attributesField = '__attributes'**

~~~javascript
{
    "office": {
        "_attributes": {
            "name": "New York"
        },
        "_value": {
            "user": [{
                    "_attributes": {
                        "name": "John",
                        "last_name": "Wick",
                        "id": "1"
                    },
                    "_value": {
                        "room": {
                            "_value": "123"
                        },
                        "phone_number": [{
                                "_value": "987654321"
                            }, {
                                "_value": "123456789"
                            }
                        ]
                    }
                }, {
                  "_attributes": {
                        "name": "Alice",
                        "last_name": "Liddell",
                        "id": "2"
                    },  
                    "_value": {
                        "room": {
                            "_value": "wonderland"
                        },
                        "phone_number": [{
                                "_value": "3334445555"
                            }, {
                                "_value": "7776667777"
                            }
                        ]
                    }
                }
            ]
        }
    }
} 
~~~

**Example of a parsed object when valueField = '_value' and attributesField is empty**

~~~javascript
{
  "office": {
    "name": "New York",
    "_value": {
      "user": [
        {
          "name": "John",
          "last_name": "Wick",
          "id": "1",
          "_value": {
            "room": {
              "_value": "123"
            },
            "phone_number": [
              {
                "_value": "987654321"
              },
              {
                "_value": "123456789"
              }
            ]
          }
        },
        {
          "name": "Alice",
          "last_name": "Liddell",
          "id": "2",
          "_value": {
            "room": {
              "_value": "wonderland"
            },
            "phone_number": [
              {
                "_value": "3334445555"
              },
              {
                "_value": "7776667777"
              }
            ]
          }
        }
      ]
    }
  }
}
~~~

**Example of a parsed object when both valueField and attributesField are empty**

~~~javascript
{
  "name": "New York",
  "office": {
    "name": "John",
    "last_name": "Wick",
    "id": "1",
    "user": [
      {
        "room": "123",
        "phone_number": [
          "987654321",
          "123456789"
        ]
      },
      {
        "room": "wonderland",
        "phone_number": [
          "3334445555",
          "7776667777"
        ]
      }
    ]
  }
}
~~~



### Other Supported Parsers

Additional parsers supported by Broadway are:

* **Base64Decode** / **Base64Encode** Actor, analyzes an input and outputs it as an encoded string or a decoded buffer.
* **FixedColumnParser** Actor, traverses an incoming stream and for each line parses the columns using their fixed position in the line.
* **LinesParser** Actor, traverses an incoming stream and outputs individual lines.

The Actor's description includes the detailed explanation of the Actor's capabilities. Click ![image](../images/99_19_dots.PNG) in the right corner of the Actor to open the [Actor's context menu](../18_broadway_flow_window.md#actors-context-menu) and select **Description** to view it.

Checkout **xml.flow** for the **XmlParser** example and **lines.flow** for the **LinesParser** example. To do so, go to **Actions** > **Examples** in the [Main menu](../18_broadway_flow_window.md#main-menu).

[![Previous](/articles/images/Previous.png)](02_stream_actors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](04_queue_actors.md)

