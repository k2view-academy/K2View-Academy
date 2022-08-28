# Search Index Types Templates

Fabric enables adding templates for Search fields when the default settings do not match a search's needs. For example, to search by fields that contain special characters like an email address. 
-  Each template is in JSON format and creates index settings in the Search provider.
-  All templates must be saved in the Fabric server under the **$K2_HOME/config/cdc-tags** directory.
-  Fabric has  **default templates** that are saved under the **cdc-tags** directory:

<table width="900pxl">
<tbody>
<tr>
<td width="150pxl">
<p><strong>Template Name</strong></p>
</td>
<td width="150pxl">
<p><strong>Elasticsearch Analyzer or Tokenizer</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Use Case</strong></p>
</td>
<td valign="top" width="300pxl">
<p><strong>Example</strong></p>
</td>
</tr>
<tr>
<td width="150pxl">
<p>case-insensitive-match.json</p>
</td>
<td width="150pxl">
<p>Keyword + lowercase</p>
</td>
<td valign="top" width="300pxl">
<p>Case insensitive search for special characters. For example, search by an email address.</p>
</td>
<td valign="top" width="300pxl">
<ul>
<li>JohnD@gmail.com matches johnd@gmail.com</li>
<li>JohnD@gmail does not match JohnD@yahoo.com</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>predictive-search.json</p>
</td>
<td valign="top" width="150pxl">
<p>edge_ngram</p>
</td>
<td valign="top" width="300pxl">
<p>Predictive search.</p>
</td>
<td valign="top" width="300pxl">
<ul>
<li>Typing "s" returns "search", "see", or "sql".</li>
<li>Typing "se" returns "search" or "see".</li>
</ul>
</td>
</tr>
<tr>
<td valign="top" width="150pxl">
<p>precision-match-20.json</p>
</td>
<td valign="top" width="150pxl">
<p>1gram &ndash; 20gram</p>
</td>
<td valign="top" width="300pxl">
<p>Partial match. For example, at least 4 digits must exist and at least 50% of them must be in the correct order in the searched value.</p>
<p>This template supports a maximal length of 20 chars/digits in the searched value.</p>
</td>
<td valign="top" width="300pxl">
<ul>
<li>"1243", "56347712", or "654331234" matches "1234".</li>
<li>"7777712" or "8751883724" does not match "1234".</li>
</ul>
</td>
</tr>
</tbody>
</table>



#### Adding the Templates to the Types List for Search Columns

1. Go to the **project tree**, right click the **project name** >  **Open Folder**. 

2. Open the **[project name].k2proj** file to be edited.

3. Edit the **Search** options under the **DataChangeIndicators** tag and add the template names to the Options tag. The following example displays adding a **precision-match-20 template**:

   ```xml
   <DataChangeIndicators>
       <DataChange name="Search" enabled="true">
         <Options>
           <option>keyword</option>
           <option>data</option>
           <option>date</option>
           <option>precision-match-20</option>
         </Options>
   ```

4. Save and close the **.k2proj** file.

5. Close and reopen the project to reload the updated **.k2proj** file.

6. When creating a new Search index, you can also select the added template as a **Type**.

#### Using N-gram Search Templates

It is recommended to limit the number of Search fields based on N-gram tokenizer like **predictive-search** and **precision-match-20** templates due to the extremely large disk space required to store these fields.
For example: 

- The **predictive-search** template is based on the **Edge-n-gram** tokenizer. The following tokens are created for 'Tali'- 
   - 't', 'ta', 'tal', 'tali' 

- The **precision-match-20** template is based on the **N-gram** tokenizer. The following tokens are created for 'John Doe'-
   - 1gram:  'j', 'o', 'h', 'n', 'd', 'o', 'e'
   - 2gram: 'jo', 'oh', 'hn', 'nd', 'do', 'oe'
   - 3gram: 'joh', 'ohn', 'hnd', 'ndo', 'doe'
   - 4gram: 'john', 'ohnd', 'hndo', 'ndoe'
   - 5gram: 'johnd', 'ohndo', 'hndoe' 
   - 6gram: 'johndo', 'ohndoe' 
   - 7gram : 'johndoe'


#### Update Search Columns Using Templates

Fabric only supports using templates on Search fields when the template is set as a type during the first deployment of the corresponding LU table. This is because Fabric creates an index in the Search provider for each LU Table that has Search fields. Using a template on a Search field requires different index settings in the Search provider. 

Since the index settings cannot be changed once created in the Search provider, to update a Search field from a supported Search provider type to a template, do the following:

- Create an index with the update settings in the Search provider.
- Re-index the data from the old Search provider index to the new index.
- Drop the old Search provider index.
- Add an alias with the same settings as the previous index to the new Search provider index.

  

  [Click for more information about creating Search indexes](03_creating_elasticsearch_indexes_on_search_fields.md).

  
  
  [![Previous](/articles/images/Previous.png)](03_creating_elasticsearch_indexes_on_search_fields.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](05_search_command.md)
