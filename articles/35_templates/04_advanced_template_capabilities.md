# Advanced Templates Capabilities

### Complex Input Data


There may be a need to define a more complex input structure. For example, if the template contains  `{{person.firstname}} {{person.lastname}}`, the expected input should be similar to the following:

```json
{
  "person": {
    "firstname": "",
    "lastname": ""
  }
}
```

This type of structure is not managed by the Template Parameters tab and should be added manually to the JSON tab, whereby it is also displayed in the Template Parameters tab where its value is Collection.  

### Helpers Examples

Fabric uses the [Handlebarsjs](https://handlebarsjs.com/) template engine to enable many advanced capabilities, including helpers. A helper is a function that can be part of a template and can be applied by the runtime engine according to its input. For example, a helper can change all input letters to capitals. Helpers can be easily added and registered to the engine. 

The following is an example of the handlebar template's iterations and conditions built-in helpers used in Fabric:

A common TDM tasks is to load data from LU tables into a target tables using Broadway flows that first retrieve the data from the LU, prepare it and load it into the target. To do so, the DbCommand and the DbLoad actors are connected by their output and input fields which represnting table columns. Since each table has its own columns, using a simple template is not enough and the #each built-in helper is used to iterate over the table column list and build the actors dynamically.

The following is an example of the DbCommand output in the template, with expressions and helpers that enable the engine to build the output dynamically during runtime: 

```handlebars
"properties": {
    {{#each LU_TABLE_COLUMNS~}}
          "{{this}}": {}{{#if @last}}{{^}},{{/if}}
	{{/each}}
} 
```

In this example:

* ["each" helper](https://handlebarsjs.com/guide/builtin-helpers.html#each) used for the iteration uses "#each" & "/each" opening and closing tags.
* LU_TABLE_COLUMNS is an input or variable.
* Tilde sign (~) is used by the Handlebars template as a [white space control/removal](https://handlebarsjs.com/guide/expressions.html#whitespace-control). 
* "this" is used for the current object in context. 
* ["if" helper](https://handlebarsjs.com/guide/builtin-helpers.html#if) is used to examine whether a comma needs to be added after each column name, using also the [@last](https://handlebarsjs.com/api-reference/data-variables.html#last) data variable. 

### Templates in Java

Working in the background, the Handlebars engine is usually transparent to users as they define or use templates.  However, for advanced usage, it can be explicitly used in Fabric Java functions using the Handlebars Java library which is already embedded into Fabric.

For more information click, [Using Java with Handlebars](https://jknack.github.io/handlebars.java/). 

 

[![Previous](/articles/images/Previous.png)](03_using_templates.md) 
