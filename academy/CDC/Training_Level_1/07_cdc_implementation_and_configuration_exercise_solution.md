# CDC Implementation and Configuration




### ![](/academy/images/Solution.png) Solution - CDC Implementation and Configuration Exercise 

 <ul>
 <pre><code> 
<strong>Step 4.3</strong>
A. The CDC Publisher job has been added for the CDCTraining consumer.
B. The job type is <strong>CDC_TRANSACTION_PUBLISHER</strong> and the UID is <strong>CDCTraining</strong>. 
Note that when deploying the LU to a local Fabric server, Fabric concatenates the [Fabric version]_[Fabric project name] to the UID.
For example: CDCTraining_6_2_kb_fabric_project. 
C. A new CDC Publisher job is created for each CDC consumer.
</code></pre>
</ul>
<ul>
<pre><code>    
<strong>Step 4.4</strong>
A. The topic name is identical to the CDC consumer name: <strong>CDCTraining</strong>. 
Note that when deploying the LU to a local Fabric server, Fabric concatenates the [Fabric version]_[Fabric project name] to the topic name.
For example: CDCTraining_6_2_kb_fabric_project.
</code></pre>
</ul>
<ul>
<pre><code>    
<strong>Step 4.5</strong>
A. The data type of the Kafka message is <strong>SCHEMA</strong>.
</code></pre>    
</ul>
<ul>
<pre><code>  
<strong>Step 5.2</strong>
A. The <strong>dataType</strong> of the Kafka messages published by the get instance command is <strong>DATA_CHANGE</strong>. 
B and C. The number of Kafka messages is equal to the number of Invoice records.
A separate message is published for each Invoice record.
</code></pre>    
</ul>
<ul>
<pre><code>  
<strong>Step 5.3</strong>
A. This depends on the population mode of the LU table with the CDC fields. If the population mode is Insert, each LUI sync initiates a DATA_CHANGE Kafka message that includes a Delete and Insert of the CDC fields. If the population mode is Upsert, a DATA_CHANGE message is published only if the CDC data is updated. 
</code></pre>    
</ul>
<ul>
<pre><code>  
<strong>Step 5.5</strong>
A. One DATA_CHANGE has been published on all the data updates, included in the transaction.
</code></pre>    
</ul>
<ul>
<pre><code>
<strong>Step 5.7</strong>
A. The redeployment has not published a CDC message, since no CDC fields have been added, updated or removed in the Customer LU. 
</code></pre>    
</ul>
<ul>
<pre><code>
<strong>Step 5.9</strong>
A. The redeployment published CDC transations since a new CDC field has been added to the Invoice LU table of the Customer LU.  
B. Fabric published one <strong>SCHEMA_UPDATE</strong> transaction for the Schema's update. 
In addition, Fabric also published <strong>DELETE_TABLES</strong> and <strong>DATA_REPUBLISH</strong> transations for each Invoice record of each LUI.
This is since Fabric initiates a batch process to republish the CDC data of all Fabric LUIs when a CDC field is added to an existing LU table column.
For more information, read <a href="/articles/18_fabric_cdc/04_cdc_publication_flow.md">CDC Publication Flow</a>.  
</code></pre>    
</ul>


[![Previous](/articles/images/Previous.png)](06_cdc_implementation_and_configuration_exercise.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_search_overview.md)


