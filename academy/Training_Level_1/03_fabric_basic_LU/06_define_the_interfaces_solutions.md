![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)

### Solution- Interface Exercise Solution

1. `Answer: BALANCE, CONTRACT_OFFER_MAPPING,INVOICE, OFFER, PAYMENT, SUBSCRIBER`

2. `Answer: Subscriber ID 14825`

Submit the following query in the Query_builder window of the Billing_DB interface:
```
Select sum(INVOICE.BALANCE),
  INVOICE.SUBSCRIBER_ID
From main.INVOICE
Group By INVOICE.SUBSCRIBER_ID
```

Check the highest value for ``` sum(INVOICE.BALANCE)``` in the result table of the query builder.

Check the associated subscriber_id value (in the juxtaposing column) matching the value discovered in the query builder.


3. `select count(*) from orders`


 
 
[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/03_fabric_basic_LU/05_define_the_interfaces_example_and_exercises.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/07_LU_flow.md)
