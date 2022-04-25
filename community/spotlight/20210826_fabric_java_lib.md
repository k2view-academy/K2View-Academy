### New Fabric Java Actor on the Block ! 

Fabric now counts a new actor that allows you to insert data into Big Query tables. 
This actor uses the Google Big Query Java libraries to push data into your project's datasets on your Data Lake.

To use it you will need:

- A project IB, Dataset and a table on Big Query accessible from this [link](https://console.cloud.google.com/iam-admin)
- A Json credentials file needed to authenticate your connection to Big Query
  -Install the libs in the Fabric home libs directory - avaialble from [maven repository](https://mvnrepository.com/artifact/com.google.cloud/gcloud-java-bigquery)
  -The Fabric library - InsertToBigQuery_lib.k2export file available [here]() 

Enjoy your inserts, and stay tuned for more Google BigQuery related actors.
You can also create new ones and submit them to us by emailing us at: devcom@k2view.com
