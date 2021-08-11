![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)

### Solution- Interface Exercise Solution

1. `Answer: ACTIVITY, ADDRESS, BALANCE, BROADWAY_TEST, CASE_NOTE, CASES, CONTRACT, CONTRACT_COPY, CUSTOMER, CUSTOMER_COPY, DEVICESTABLE2017, INVOICE, OFFER, PAYMENT, SUBSCRIBER`


Step 1: Using the SQL language (as the type of this interface is SQL_Lite), submit the following query in the Query_builder window of the Billing_DB interface:
```
Select sum(INVOICE.BALANCE),
  INVOICE.SUBSCRIBER_ID
From main.INVOICE
Group By INVOICE.SUBSCRIBER_ID
```

Step 2: Check the highest value for ``` sum(INVOICE.BALANCE)``` in the result table of the query builder.

Step 3: Check the associated subscriber_id value (in the juxtaposing column) matching the value discovered in the query builder.

NB - you could also have executed the following command to extract the max value without using the ```sort column``` capability of the Query Builder.

```
Select max(sum), sub
From (Select sum(INVOICE.BALANCE) As sum,
    INVOICE.SUBSCRIBER_ID As sub
  From main.INVOICE
  Group By INVOICE.SUBSCRIBER_ID)
```



3. `select count(*) from orders`


 
 
[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/03_fabric_basic_LU/05_define_the_interfaces_example_and_exercises.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/07_LU_flow.md)
