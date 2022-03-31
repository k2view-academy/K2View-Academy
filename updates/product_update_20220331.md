### The Fabric 6.5.6 Official Release

We have just released Fabric 6.5.6, which includes the following enhancements:

* **Common Tables** - ref_status command output structure was extended for better common tables sync process tracking.
* **iidFinder** - a new field was added to LU schema properties under IID Finder section called Affinity. Affinity will be taken into consideration when delta partition job will start. It will pass  to the job the Affinity defined on the LU schema and the one defined on config.ini if any.
* **Broadway** - if a flow fails during debug, the failed Actor is highlighted.
* **Studio** - A new property called 'Query Builder: Use Prepared Statement' was added to the  Database Types definition, and it is checked by default.
* **Bug fixes and more**.

See the [Release Notes](https://support.k2view.com/Academy/Release_Notes_And_Upgrade/V6.5/Fabric_Release_Notes_V6.5.6.pdf.html) for the full list.

<img src="images/img2.png" alt="image" style="zoom: 67%;" />