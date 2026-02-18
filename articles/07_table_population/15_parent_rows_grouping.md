# Parent Rows Grouping

### Overview

The **SourceDbQuery** Actor is used in the Broadway populations to query the source data. The Actor runs the query defined in the population flow with the addition of the WHERE clause generated in the server. The Actor's WHERE clause is generated automatically prior to query execution and it includes the keys and their values, passed in the `parent_rows` output object of the **PopulationArgs** Actor.

![image](images/07_14_01.PNG)



Starting from Fabric V8.4, the creation of the WHERE clause is impacted by the **Parent Rows Grouping** parameter in the Fabric properties section of JDBC  interface type definition. 

### Parent Rows Grouping Settings

The following table describes each parameter settings and the result query structure, depending on the number of input keys, assuming each key can receive multiple input values:

<table style="width: 900px;">
<tbody>
<tr>
<td style="width: 250px;"><strong>Parameter Value</strong></td>
<td style="width: 130px;"><strong>Keys</strong></td>
<td style="width: 320px;"><strong>Generated Query</strong></td>
<td style="width: 200px;"><strong>Comments</strong></td>
</tr>
<tr>
<td rowspan="2"><strong>OR</strong></td>
<td>1</td>
<td>SELECT * FROM PATIENT WHERE PATIENT_ID = ? OR PATIENT_ID = ?</td>
<td rowspan="2">OR is a default value for all DB types except Cassandra</td>
</tr>
<tr >
<td>&gt; 1</td>
<td>SELECT * FROM PATIENT WHERE PATIENT_ID = ? AND VISIT_ID = ? OR PATIENT_ID = ? AND VISIT_ID = ?</td>
</tr>
<tr>
<td rowspan="2"><strong>IN</strong></td>
<td>1</td>
<td>SELECT * FROM PATIENT WHERE VISIT_ID IN (?,?)</td>
<td rowspan="2">When the query should be based on a composite key, Tupling is used in SQL string within JDBC.</td>
</tr>
<tr>
<td>&gt; 1</td>
<td>SELECT * FROM PATIENT WHERE (PATIENT_ID, VISIT_ID) IN ((?,?), (?,?))</td>
</tr>
<tr>
<td rowspan="2"><strong>IN_WITHOUT_TUPLING</strong><strong><br /></strong><strong>&nbsp;</strong></td>
<td>1</td>
<td>SELECT * FROM PATIENT WHERE VISIT_ID IN (?, ?)</td>
<td rowspan="2">IN_WITHOUT_TUPLING is recommended for DBs that don't support Tupling, such as Aerospike</td>
</tr>
<tr>
<td>&gt; 1</td>
<td>SELECT * FROM PATIENT WHERE PATIENT_ID = ? AND VISIT_ID = ? OR PATIENT_ID = ? AND VISIT_ID = ?</td>
</tr>
<tr>
<td><strong>NONE</strong></td>
<td>Any</td>
<td>SELECT * FROM PATIENT WHERE PATIENT_ID = ? AND VISIT_ID = ?</td>
<td >NONE is a default value for Cassandra, which requires separate calls to server for each value</td>
</tr>
</tbody>
</table>

