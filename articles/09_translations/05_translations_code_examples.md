<studio>

# Translations - Code Examples

### Example of using a Translation in a Fabric Function 

1.	Create a **Translation**.

![image](/articles/09_translations/images/09_04_01%20Translation.png)

2.	Create the **Function** that uses the **Translation**:

<pre><code>  
if (fabricErrorID != null) {
	Map <String,String> rs = getTranslationValues("trnApplicationErrorList",new Object[]{fabricErrorID});
	result.put("p_errno", rs.get("ErrorID"));
	result.put("p_message", rs.get("ErrorMsg"));
}
</code></pre>

Full example of **fnErrorCheck** function that retrieves the Translation’s output values can be found in the Demo project.
       
       
### Example of using a Translation in a Population

1.	Create a **Translation**.

![image](/articles/09_translations/images/09_04_02%20ranslation%20in%20a%20Population.png)

2.	Create a **Table Population** that uses the **Translation** to transform data from one set of values to another.

![image](images/09_04_03%20Table%20Population.png)

This example displays how to apply the Data Transformation Rule in a Table Population. The Translation **trnOrderType** defines a list of Input/Output combinations. When the target table is populated, Fabric populates the **ORDER_TYPE**  in the target table’s ORDERS column using the **trnOrderType** Translation and a value in the **query_public_orders.order_type** source field.

Full example of how the **trnOrderType** Translation is used in a Table Population can be found in the Demo project.

### Example of using a Translation with Type = SQL

1.	Create a **Translation** where an **Output** column’s **Type = SQL**.

       ![image](/articles/09_translations/images/09_04_04%20Type%20%3D%20SQL..png)

2.	Do the following:
       * In the **SQL** column, add SQL queries and then validate each query in the column by clicking the adjacent **SQL** icon. 
       * Populate other Translation data as needed.
       
       ![image](/articles/09_translations/images/09_04_05%20Translation%20data.png)
       
 3.	Create a function that uses this Translation to execute the relevant SQL query. 


~~~
Map<String, Map<String, String> tranData = getTranslationsData("trnCheckAnalysisQueries");

if(tranData.size() > 0) {
	// do something
}
~~~

Full example of **fnEnrichment_CUSTOMER** function that retrieves the Translation’s output values can be found in the Demo project.


[![Previous](/articles/images/Previous.png)](/articles/09_translations/04_using_translations_in_fabric.md)

</studio>
