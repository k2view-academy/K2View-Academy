# Search Exercise - Solution


### ![](/academy/images/Solution.png) Solution - CDC Implementation and Configuration Exercise 

 <ul>
 <pre><code> 
<strong>Step 3.1</strong>

A. Add a new LU table that icludes all Search fields to enable including all Search fields in one search. A search can include several LU tables only if they have the same structure and the same list of Search fields. The Customer and Address LU tables can have a different structure. 
B. If the search had relied on the First Name, Last Name, and SSN columns only, then we could add the Search fields to the Customer LU table that contains these fields.
</code></pre>
</ul>
<ul>
<pre><code>
<strong>Step 3.4</strong>
A. The CDC Consumer job was added after the deployment of the Customer LU, since Search fields have been added to the Customer LU. 
B. The job type is <strong>CDC_TRANSACTION_CONSUMER</strong> and the UID is <strong>Search</strong>. 
Note that when deploying the LU to a local Fabric server, Fabric concatenates the [Fabric version]_[Fabric project name] to the UID.
For example: Search_6_2_kb_fabric_project. 
</code></pre>
</ul>
<ul>
<pre><code>    
<strong>Step 5.2</strong>
A. The following customers have been returned by the first Search command: 1275, 7800, and 10001. 
B. The following customers have been returned by the second Search command: 1275, 7800, 10001, and 10002.
C. The second search allowed up to two differences using the <strong>fuzziness</strong> paramerer in the Search query. Customer 10002 is named 'Danny Wason'. Therefore the search for 'Danny Warson' returned this customer also.
</code></pre>
</ul>
<ul>
<pre><code>    
<strong>Step 5.3</strong>
A. The following customers have the same combintaion of First Name, Last Name, SSN, Street_Address_1, City, State, and Country as Customer #7800: 
10001 and 10002.
</code></pre>    
</ul>
<ul>
<pre><code>  
<strong>Step 5.4</strong>
A. The search using <strong>Marshall&Ross</strong> returned Customer #10003. The <strong>case-insensitive-match</strong> template set for the First Name field, enables a search using a value that contains special characters like '&'. The search by <strong>Marshall</strong> returned Customers 221, 1856, 7374, 6930 and 9574.
</code></pre>    
</ul>




[![Previous](/articles/images/Previous.png)](10_search_exercise.mds)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](12_cdc_certification_exam.md)


