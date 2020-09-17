![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png) 

### Solution - Invoking a Web Service

```java
wsGetCustomer2 ; path :test/getCustomerDetails 

String sql = "select cust.FIRST_NAME||' '||cust.LAST_NAME CUSTOMER_NAME, cont.CONTRACT_ID,cont.CONTRACT_DESCRIPTION," +
        "sub.SUBSCRIBER_ID,sub.MSISDN,sub.IMSI,sub.SIM,sub.SUBSCRIBER_TYPE,sub.VIP_STATUS " +
		"from CUSTOMER cust, CONTRACT cont, SUBSCRIBER sub where cont.CONTRACT_ID=sub.SUBSCRIBER_ID and sub.VIP_STATUS=?";

Db.Rows rows = ludb("Customer", i_id).fetch(sql, i_vipStatus);

return rows;
```



1. `Answer: 3.` 
2. `Answer: 5G tether.` 
3. `Answer: The Web Version that was the last to be deployed.`  




------
