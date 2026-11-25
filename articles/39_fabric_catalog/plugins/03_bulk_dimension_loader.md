# Enriching the Catalog with Business Dimensions

The **Bulk Dimension Loader** plugin loads properties (e.g. `description` or `classification`) onto Catalog nodes — schemas, datasets, classes, or fields — in bulk, from an input CSV file. Use this plugin to import business metadata that you keep outside the Catalog, for example in a data dictionary or a spreadsheet.

This plugin is available starting from Fabric V8.5.1.

## Input Parameters

The plugin's input parameter is:

* `inputFile` - the input file name. 
  * The file should be placed in Fabric Studio under: `/SharedObjects/Interfaces/Discovery/Mtable`. 
  * If the `inputFile` is empty, the plugin searches for a file using the default name: `BulkDimensionLoader_<interface_name>.csv`.

## Input File Structure

The expected input file structure is as follows:

* The first 4 columns - ```schema```, ```dataset```, ```class``` and ```field``` - identify the node on which the properties should be created. 
* The remaining columns hold the property values to load, with each column header being the name of the property to create (e.g. ```description```).
* The node level on which the properties are created depends on which of the first 4 columns are populated:
  * When only ```schema``` is populated, the properties are created on the **schema**.
  * When ```schema```, ```dataset``` and ```class``` are populated, the properties are created on the **class**.
  * When all 4 columns - ```schema```, ```dataset```, ```class``` and ```field``` - are populated, the properties are created on the **field**.
