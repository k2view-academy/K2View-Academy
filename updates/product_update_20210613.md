### The Fabric 6.4.4 Official Release

We would like to share that we have just released Fabric 6.4.4 that which includes the following: 

* In iidFinder, when the first sync of delta partition fails, the LUI is built including the empty schema. The only LUI table which includes data in this case is **_k2_delta_errors**. 
* New parameter was added to the **GET** command: STOP_ON_ERROR (default = true). When this parameter is set to **false**, Fabric supports a GET of several LUIs even if the sync of one LUI fails.
* Kafka interface now supports SASL_SSL and SASL_PLAINTEXT security protocols. 
* Several bug fixes were done.

The full list can be found in the [Release Notes](https://support.k2view.com/Academy_6.5/Release_Notes_And_Upgrade/V6.4/Fabric_Release_Notes_V6.4.4.pdf.html).

<img src="images/img3.png" alt="image" style="zoom: 67%;" />