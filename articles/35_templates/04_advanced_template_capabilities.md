# Advanced Templates Capabilities

### Helpers Examples

Fabric uses the [handlebarsjs](https://handlebarsjs.com/) template engine which enables many advanced capabilities, among them the helpers. Helper is a function that can be part of the template and that can be applied by the runtime engine according to the input. For example, an helper can change all input letters to be capital. Helper can be easily added and registered to the engine. 

Handlebars provides several built-in helpers which allow using some logic of iterations and conditions. Following is an example of such usage in Fabric:

One of the common TDM tasks is to load data from LU tables into a target tables. For this, a Broadway flows are being used, which first retrieve the data from LU, prepare it and load it into target. To do so, the DbCommand and the dbLoad actors shall be connected by their output and input fields reparenting table columns. Because each table has its own columns, using a simple template is not enough.  For this the each built-in helper can be used, which iterates on the table column list  and builds those actors dynamically.

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

 

[![Previous](/articles/images/Previous.png)](03_using_templates.md) 