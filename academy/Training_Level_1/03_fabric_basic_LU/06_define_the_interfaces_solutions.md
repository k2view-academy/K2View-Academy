![](/academy/Training_Level_1/03_fabric_basic_LU/images/Solution.png)

### Solution- Interface Exercise Solution

1. `Answer: BALANCE, CONTRACT_OFFER_MAPPING,INVOICE, OFFER, PAYMENT, SUBSCRIBER`

2. `Answer: Subscriber ID 14825`

Submit the following query in the Query_builder window of the Billing_DB interface:
```
Select sum(INVOICE.BALANCE),
  INVOICE.SUBSCRIBER_ID
From BILLING_DB.INVOICE
Group By INVOICE.SUBSCRIBER_ID
```

Check the highest value for ``` sum(INVOICE.BALANCE)``` in the result table of the query builder: 8021.

Check the associated subscriber_id value: ```14825``` in the juxtaposing column.


3. `Answer: 37351 orders`


 
 
[![Previous](/articles/images/Previous.png)](/academy/Training_Level_1/03_fabric_basic_LU/05_define_the_interfaces_example_and_exercises.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](/academy/Training_Level_1/03_fabric_basic_LU/07_LU_flow.md)
