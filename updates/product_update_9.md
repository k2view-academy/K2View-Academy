### The Fabric 6.4.3 Official Release

We would like to share that we have just released Fabric 6.4.3 that which includes the following: 

* In iidFinder, the **delta_recycle** command was removed. Instead, the delta failed transactions are now reported to the LUI **_k2_delta_errors** new product table instead of to Cassandra. Sync mode = **delta** was enhanced to consider the LUI **_k2_delta_errors** new product table.
* Several bug fixes were done, such as:
  * The DB connection no longer closes after finishing reading the result set.
  * GraphIt now supports returning data with UTF-8 characters of 2 or more bytes.

The full list can be found in the [Release Notes](https://support.k2view.com/Academy_6.4/Release_Notes_And_Upgrade/V6.4/Fabric_Release_Notes_V6.4.3.pdf.html).

<img src="images/img1.png" alt="image" />