# Filter Catalog

### Overview

The Catalog application allows filtering the Catalog graph based on the Datasets, Class and Field properties.  Meaning, the property should be either on a dataset, class or field, in order that dataset/class will be displayed.

Set the filter by clicking the![](images/filter.png)icon in the menu bar. 

<img src="images/menu_bar.png" style="zoom:85%;" />

The PII and Classification properties are defined as filterable by default. Their value can be set is to:

*  One of the existing property values.
*  The 'property exists' value, meaning the property exists with any value.

<img src="images/catalog_filter.png" style="zoom:70%;" />

Click **Apply** to apply the filter on the Catalog. The popup closes and the icon changes its color to <img src="images/filter_selected.png" style="zoom:80%;" />. 

When the filter is set after the Data Platform has been expanded, irrelevant Datasets are filtered out. When the filter is set before that, irrelevant Datasets are filtered upon the expand. 

Click **Clear Filter** to clear the filter and return to the full Catalog view.



### How Do I Filter by Additional Properties

To filter by another property (e.g. by sequenceName), add the property definition to the `properties-info.json` as follows:

~~~json
            {
                "name": "sequenceName",
                "editable": true,
                "deletable": true,
                "filterable": true,
                "allow_custom_values": true
            }
~~~

* `"filterable": true` adds the property to the Catalog Filter popup and allows filtering by it.
* `"allow_custom_values": true` allows typing custom values in the value drop-down list.

<img src="images/catalog_filter_with_seq.png" style="zoom:70%;" />



