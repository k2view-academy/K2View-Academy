<web>

# MTable Actors

Broadway has several Actors that allow working with [MTables](/articles/09_translations/06_mtables_overview.md):

* **MTableLookup** Actor - allows to fetch data from an MTable by the given key(s). The search indices are created on-the-fly during the first search. If no keys are supplied, the entire MTable dataset is returned. This Actor returns an array of objects.
* **MTableRandom** Actor - allows to fetch a random row from an MTable, creating indices as needed. The random selection can be limited by providing input key(s). This Actor returns one object only.
* **MTableLoad** Actor - allows to either create a new MTable dataset or replace an existing one in the Fabric memory.

### How Do I Use MTable Actors?

Each of the above MTable Actors receives an MTable name as an input parameter. An MTable name can be passed at run time, together with a map of keys & values, in order to perform the lookup. Alternatively, the MTable name, keys and the keys' values can be set during the flow design time, using dedicated editors:

1. Start by defining the MTable name in the Actor's input, either manually or via the editor:

<img src="../images/99_actors_09_0.png" style="zoom:80%;" />

2. The MTable editor opens a popup that displays a list of all deployed MTables and their keys. Select a name from the list and mark the key(s) to be used as input for the lookup:

<img src="../images/99_actors_09_1.png" style="zoom:80%;" />

3. Once selected, the keys are added to the Actor as new input arguments. When a key's link type is set to **Const**, the input becomes a list of this column's distinct values. You can then choose a value, rather than manually type it in. 

<img src="../images/99_actors_09_2.png" style="zoom:80%;" />

4. The same capability of selecting a value from the list is also available when you need to set a **default** value for an MTable key (when it has an **External** or **Link** link type).



</web>
