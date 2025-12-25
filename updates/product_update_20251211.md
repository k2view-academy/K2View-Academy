### The Fabric 8.3.1 Official Release

We are glad to announce the Fabric 8.3.1 release which introduces several key enhancements to both Fabric and the Catalog: 

Here are some of the highlights of this release:

* **Improved transaction management**: Catalog's graph creation in Neo4j has been enhanced by splitting the changes into batches. This allows to run each batch in parallel as separate transactions, significantly reducing memory consumption during Discovery job execution.
* **Enhanced data quality**: The quality of generated data during masking and SDG is now improved. This is achieved by the ability to override the Generator's default input parameters with values of the Catalog metrics (calculated from the data snapshot).
* **Version differences list**: The Catalog App now includes a Version Differences window which displays changes between selected versions and allows exporting the data to a CSV file.
* **Expanded JSON support**: Cataloging of JSON files now fully supports Draft-07 and later versions of the JSON Schema specification.
* **Web Studio enhancements**: 
  * You can now hide LUs in the Project tree within Web Studio, excluding them from build and deployment. 
  * We've made various enhancements to both Graphit and Environments UX.
* **Fortanix KMS Integration**: Fabric now supports storing its encrypted master key for data encryption in Fortanix Data Security Manager KMS.

Refer to the [Release Notes](https://support.k2view.com/Academy/Release_Notes/V8.3/Fabric_Release_Notes_V8.3.1.pdf.html) for the full list of features and fixes.

<img src="images/img.png" alt="image" style="zoom: 70%;" />
