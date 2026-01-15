# k2verify MTable Configuration

The **k2verify_config** MTable defines the rules for the data verification, allowing customization of the verification logic per field.
<br>The below customizations are supported:
1. Execute a customized broadway flow to transform the value of a field before it is being compared.
   <br/>**Note:** Custom broadway flow must adhere to specific input and output parameters:
   - Input should be set as 'External' and named 'value' (to receive the original field value).
   - Output should be set as 'External' and named 'value' 
5. Define the comparison when the value is null.
6. Define PII columns where a value difference between the source and target is expected and considered valid, such as fields that are masked in the target system.
7. Define fields to be excluded from the comparison.
8. Specify column name mappings when the source and target tables use different column names for the same data.
Each record in the MTable defines one application table, together with its comparison rules. Fill in the MTable according to your requirements.
<br/>**Note:** The below 'Type' column signifies the number of values allowed in the column; 'Single' indicates the capacity for a single value, while 'Multiple' signifies the ability to accommodate multiple values.


<table width="900pxl">
<tbody>
<tr>
  <td valign="top" width="200pxl"><strong>Column</strong></td>
  <td valign="top" width="400pxl"><strong>Description</strong></td>
  <td valign="top" width="100pxl"><strong>Type</strong></td>
  <td valign="top" width="200pxl"><strong>Example</strong></td>
</tr>

<tr>
  <td valign="top">Source_Table_Name</td>
  <td valign="top">Source table name</td>
  <td valign="top">Single</td>
  <td valign="top">SRC_CUSTOMER</td>
</tr>

<tr>
  <td valign="top">Source_Interface</td>
  <td valign="top">Interface name where the source table is located.</td>
  <td valign="top">Single</td>
  <td valign="top">Oracle_PROD</td>
</tr>

<tr>
  <td valign="top">Source_Schema</td>
  <td valign="top">Schema name where the source table is located.</td>
  <td valign="top">Single</td>
  <td valign="top">CUSTOMER</td>
</tr>

<tr>
  <td valign="top">Source_Transformation_Function_Name</td>
  <td valign="top">
    Custom broadway flows to be executed on a source field level, in order to transform the source value before comparing it to the target value.<br/>
    Leave empty if not required.
  </td>
  <td valign="top">Multiple</td>
  <td valign="top">
    ssn:Mask_SSN<b>&#124;</b>last_name:MaskCustomLastName;<br/>
    Mask_SSN &amp; MaskCustomLastName are custom flows transforming the values fetched from source, before comparing to target values<br/>
    <strong>Note</strong>: Transformation broadway flow should have both input and output as External and named 'value'
  </td>
</tr>

<tr>
  <td valign="top">source_columns_to_Ignore_null</td>
  <td valign="top">
    Columns separated by a pipe delimiter. If the column values are null, a match will be returned even if the target values are not null.
  </td>
  <td valign="top">Multiple</td>
  <td valign="top">secondary_phone_number<b>&#124;</b>secondary_address</td>
</tr>

<tr>
  <td valign="top">Target_Table_Name</td>
  <td valign="top">Target table name to compare to</td>
  <td valign="top">Single</td>
  <td valign="top">TAR_CUSTOMER</td>
</tr>

<tr>
  <td valign="top">Target_Interface</td>
  <td valign="top">Interface name where the target table is located.</td>
  <td valign="top">Single</td>
  <td valign="top">Oracle_UAT</td>
</tr>

<tr>
  <td valign="top">Target_Schema</td>
  <td valign="top">Schema name where the target table is located.</td>
  <td valign="top">Single</td>
  <td valign="top">CUSTOMER</td>
</tr>

<tr>
  <td valign="top">Target_Transformation_Function_Name</td>
  <td valign="top">
    Custom broadway flows to be executed on a target field level, in order to transform the target value before comparing it to the source value.<br/>
    Leave empty if not required.
  </td>
  <td valign="top">Multiple</td>
  <td valign="top">
    ssn:Mask_SSN<b>&#124;</b>last_name:MaskCustomLastName;<br/>
    Mask_SSN &amp; MaskCustomLastName are custom flows transforming the values fetched from source, before comparing to target values.<br/>
    <strong>Note</strong>: Transformation broadway flow should have both input and output as External and must be named 'value'.
  </td>
</tr>

<tr>
  <td valign="top">target_columns_to_Ignore_null</td>
  <td valign="top">
    Columns separated by a pipe delimiter. If the column values are null, a match will be returned even if the source values are not null.
  </td>
  <td valign="top">Multiple</td>
  <td valign="top">secondary_phone_number<b>&#124;</b>secondary_address</td>
</tr>

<tr>
  <td valign="top">Customized_Key_Comparison</td>
  <td valign="top">
    Unique identifier of the record.<br/>
    Used for matching source and target table's records.
  </td>
  <td valign="top">Multiple</td>
  <td valign="top">customer_id<b>&#124;</b>ssn</td>
</tr>

<tr>
  <td valign="top">PII_Columns</td>
  <td valign="top">
    PII Columns names separated by a pipe delimiter.<br/>
    Used for PII verification use case.
  </td>
  <td valign="top">Multiple</td>
  <td valign="top">first_name<b>&#124;</b>ssn</td>
</tr>

<tr>
  <td valign="top">Excluded_Columns_Names</td>
  <td valign="top">List of Columns to exclude from the comparison.</td>
  <td valign="top">Multiple</td>
  <td valign="top">misc_details<b>&#124;</b>ignore_column_text</td>
</tr>

<tr>
  <td valign="top">Active</td>
  <td valign="top">
    If set to false, rule will not be used in the comparison.
  </td>
  <td valign="top">Single</td>
  <td valign="top">
    TRUE (to run the <em>Validation</em> process)<br/>
    FALSE (to skip the <em>Validation</em> process for the table)
  </td>
</tr>

<tr>
  <td valign="top">Column_Mapping</td>
  <td valign="top">
    Mapping between source and target column names when they differ.
  </td>
  <td valign="top">Multiple</td>
  <td valign="top">
    customer_id:cust_id<b>&#124;</b>last_name:last_n
  </td>
</tr>

<tr>
  <td valign="top">Buckets</td>
  <td valign="top">
    Defines how many concurrent threads are used to perform verification on the table.
  </td>
  <td valign="top">Single</td>
  <td valign="top">10</td>
</tr>

<tr>
  <td valign="top">Buckets_Method</td>
  <td valign="top">
    Specifies the Broadway flow responsible for determining which rows belong to a specific thread (Bucket).
  </td>
  <td valign="top">Single</td>
  <td valign="top">
    bwGetBucketRows - is the default Broadway flow template used by the k2verify library to distribute table rows across verification buckets. You may override this flow to customize the row-to-bucket distribution logic based on your requirements.<br/>
    Alternatively, you can create a new Broadway flow that <strong>accepts the same input parameters</strong> and <strong>produces the same output structure</strong>. Once created, update the <code>Buckets_Method</code> value in the configuration MTable to reference the new flow.
  </td>
</tr>
</tbody>
</table>
 </div>
