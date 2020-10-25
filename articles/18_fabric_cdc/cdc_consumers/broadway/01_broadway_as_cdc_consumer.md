# Using Broadway as CDC Consumer

Broadway has a queue of [built-in Actors](/articles/19_Broadway/actors/04_queue_actors.md) that manage the handling of Pub or Sub asynchronous messages and can subscribe to Apache Kafka messages. 

Since Fabric publishes [CDC messages](/articles/18_fabric_cdc/02_cdc_messages.md) to Kafka, a dedicated CDC consumer can be defined in Broadway to subscribe CDC messages in a [Broadway flow](/articles/19_Broadway/02a_broadway_flow_overview.md).

## Adding a CDC Consumer for a Broadway Flow

To customize Fabric Studio and add an additional CDC consumer for a Broadway flow, do the following:

1.  Go to the **project tree**, right click the **project name** and select **Open Folder**.

2.  Open the **[project name].k2proj** file to be edited.

3.  Edit the **DataChangeIndicators** tag by adding the **DataChange** tag. By default, the **DataChangeIndicators** contain the **Search** consumer.  Set the consumer name in the **name** and set the **Option** in the **Options** tag to **data**.  See the following example:

```
<DataChangeIndicators>
    <DataChange name="Search" enabled="true">
      <Options>
        <option>keyword</option>
        <option>data</option>
        <option>date</option>
      </Options>
    </DataChange>
	<DataChange name="CDCBroadway" enabled="true">
      <Options>
        <option>data</option>
      </Options>
    </DataChange>
  </DataChangeIndicators>
```

## Creating CDC Indexes for Broadway

Select the LU table columns to be published to the CDC topic of Broadway. 



[Click for more information about CDC Implementation Steps](04_cdc_consumers_implementation.md). 



## Creating a Kafka Interface

Create a new Interface. Set the **Interface Type** to **Kafka**.  Populate the **Bootstrap Servers** using the host and port of the Kafka server. For example: localhost:9092. Note that several servers delimited by a comma can be set.

## Creating a Broadway Flow to Consume the CDC Messages

Define a Broadway flow. Add the built-in **Subscribe** Actor and set its input parameters as follows:

- Set the interface according to the settings in the Kafka interface added to the Fabric project.

- Set the topic according to the CDC consumer name (CDCBroadway in the above example). Note that the topic name of each CDC consumer is identical to the CDC consumer name.

  [Click for examples of Pub/Sub Broadway flows](/articles/19_Broadway/actors/04_queue_actors.md#pub--sub-examples).



