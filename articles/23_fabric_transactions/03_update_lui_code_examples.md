# Update LU Instance - Code Examples

* Run transaction using Fabric user code as follows:

~~~
Db ci = db("fabric");

// Get the instance ID 
ci.execute("get Customer.'" + IID + "'");

// Begin Trx
ci.beginTransaction();

//Perform the INSERT command
String SQL = 
"insert into CONTRACT_COPY (CUSTOMER_ID,CONTRACT_ID,CONTRACT_REF_ID ) values (?, ?, ?)";
Object[] params = new Object[]{IID, contrID, contrRefID};
ci.execute(SQL, params);

//Commit Trx
ci.execute("commit");
~~~



* Run transaction using the Fabric console as follows:

~~~
 // get the instance ID  
 get Customer.22;
	
 // Begin Trx
 begin;

 // Run update command
 update CONTRACT_COPY set CONTRACT_REF_ID = 'AXD1234' where CUSTOMER_ID = 22;	
    
 // Commit;
 commit;
~~~





[![Previous](/articles/images/Previous.png)](/articles/23_fabric_transactions/02_fabric_master_of_data.md)