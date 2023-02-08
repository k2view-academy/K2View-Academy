<web>

# MTable Actors

Broadway has a category of Actors that allow working with [MTables](/articles/09_translations/06_mtables_overview.md):

* **MTableLookup** Actor, to fetch data from an MTable by the given key(s). The search indices are created on-the-fly during the first search. If no key(s) are supplied, the entire MTable dataset is returned. This Actor returns an array of objects.
* **MTableRandom** Actor, to fetch a random row from an MTable creating indices as needed. This Actor returns one object only.
* **MTableLoad** Actor, to either create a new MTable dataset it Fabric memory or replace an existing one.

### How Do I Use MTable Actors?

Each of the above MTable Actors can receive an MTable name and a map of keys & values at run time in order to perform the lookup. In addition, there are two dedicated editors that enable selecting the MTable name and keys, and the key's valid values during the flow design time.

Fetching data from MTable requires to first define the MTable name in the Actor's input. The name can be either set manually or by opening the editor:

<img src="../images/99_actors_09_0.png" style="zoom:80%;" />

The MTable editor opens a popup which displays a list of all deployed MTables and their keys. Select a name from the list and mark the key(s) for the lookup:

<img src="../images/99_actors_09_1.png" style="zoom:80%;" />

Once selected, the keys are added to the Actor. Switching the link type of the key from Link (default) to Const allows choosing a valid value from this key, rather than typing it manually. 

<img src="../images/99_actors_09_2.png" style="zoom:80%;" />



</web>
