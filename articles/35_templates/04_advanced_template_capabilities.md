# Advanced Templates Capabilities

### Complex Input Data

In some cases there is a need to define a complex input structure. For example, if the template contains  `{{person.firstname}} {{person.lastname}}` the expected input shall be like the following element:

```json
{
  "person": {
    "firstname": "",
    "lastname": ""
  }
}
```

Such structure is not managed by the Template Parameters popup window and it shall be added manually to the the JSON tab, which will be in such case the default tab when the Template Parameters is opened. 

The property view tab will show the property as a "Collection". 

### Helpers Examples

Fabric uses the [handlebarsjs](https://handlebarsjs.com/) template engine which enables many advanced capabilities, among them the helpers. Helper is a function that can be part of the template and that can be applied by the runtime engine according to the input. For example, an helper can change all input letters to be capital. Helper can be easily added and registered to the engine. 

Following is an example of Handlebars iterations and conditions built-in helpers as used in Fabric:
One of the common TDM tasks is to load data from LU tables into a target tables. For this, a Broadway flows are being used, which first retrieve the data from LU, prepare it and load it into the target. To do so, the DbCommand and the DbLoad actors shall be connected by their output and input fields reparenting table columns. Because each table has its own columns, using a simple template is not enough.  For this the #each built-in helper can be used, which iterates on the table column list and builds those actors dynamically.

Below is an example of the DbCommand output, inside the template, with expressions and helpers that allow to the engine to build the output dynamically on runtime: 

```handlebars
"properties": {
    {{#each LU_TABLE_COLUMNS~}}
          "{{this}}": {}{{#if @last}}{{^}},{{/if}}
	{{/each}}
} 
```

In this example:

* [each helper](https://handlebarsjs.com/guide/builtin-helpers.html#each) used for the iteration using "#each" & "/each" opening and closing tags.
* LU_TABLE_COLUMNS is one of the inputs or variables
* Tilde sign (~) used by handlebars as a [white space control/removal](https://handlebarsjs.com/guide/expressions.html#whitespace-control). 
* "this" used for the current object in context. 
* ["if" helper](https://handlebarsjs.com/guide/builtin-helpers.html#if) used to examine if a comma is needed to be added after each column name, using also the [@last](https://handlebarsjs.com/api-reference/data-variables.html#last) data variable. 

### Templates in Java

Working behind the scenes the handlebars engine is usually transparent to the users who define or use templates.  However, for advanced usages, it can be explicitly used within Fabric java functions, using the handlebars Java library which already embedded within Fabric.

[More information about using Java with handlebars](https://jknack.github.io/handlebars.java/). 

 

[![Previous](/articles/images/Previous.png)](03_using_templates.md) 