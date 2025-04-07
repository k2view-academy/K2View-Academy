### The Fabric 6.4.5 Official Release

We would like to share that we have just released Fabric 6.4.5 that which includes the following: 

* iidFinder process was enhanced to consume the next kafka batches to avoid getting stuck on a message that takes long time to process.
* The Fabric watchdog.sh script was added in order to keep a specific command alive.
* The Kafka Publish Actor now takes transaction management into account. Kafka is committed only once the transaction iteration is completed or committed.
* Several bug fixes were introduced.

The full list can be found in the [Release Notes](https://support.k2view.com/Academy_6.5/Release_Notes_And_Upgrade/V6.4/Fabric_Release_Notes_V6.4.5.pdf.html).

<img src="images/img3.png" alt="image" style="zoom: 67%;" />