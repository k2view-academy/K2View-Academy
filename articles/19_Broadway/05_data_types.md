# Data Types in Broadway

Broadway Actors pass data between them as Java objects. Virtually any data type can be passed between Actors but in practice most Actors pass a subset of types that are supported by Broadway.  Supported Broadway types can be described by the Broadway Schema engine, can be displayed clearly by the Data Inspector and can be converted automatically to other supported types.

### Primitives

Broadway supports the following primitives: *String*, *Long*, *Real*, *Boolean*, *Date*, *byte[]*.

Other numeric types such as *Integer*, *Short*, *Float*, *Byte* and their primitive counterparts (long, int, short, byte, double, float), are automatically converted to Long/Real.

SQL JDBC Blobs are also supported and are automatically converted into byte arrays.


### Collections/Iterable

Collections, or sequences of objects, can be represented by any implementation of Iterable (Collection, List etc). The advantage of using Iterable as the lowest common denominator is that it does not need to declare its size in advance, easily supporting streaming use cases such as database queries and topic subscription where the number of items is not known in advance or is even unbound.
Java Arrays are also supported and accessed as Iterable.


### Maps/Objects

The Map interface is used to represent objects that contain a set of named attributes. The key is usually of a String type. For instance, Maps are used extensively to represent database result set rows.


### Implicit Type Conversion

When Actors read their input arguments from Broadway, and expect a certain type of parameter, Broadway converts the value to the type expected by the Actor.

The conversion is highly robust, where any reasonable conversion is done in the attempt to satisfy the Actor's expected input. For instance, a Long can be converted into a string and a string can be parsed into a Long (if possible). An integer can be converted to a boolean and vice versa (0=false, true=1). Automatic conversion is also done between primitives and collections or between maps and collections. The following is an exhaustive table of all available conversions:

<table width="900pxl">
<tbody>
<tr>
<td><strong>Source/ Target</strong></td>
<td><strong>String</strong></td>
<td><strong>Long</strong></td>
<td><strong>Double</strong></td>
<td><strong>Boolean</strong></td>
<td><strong>Date</strong></td>
<td><strong>byte[]</strong></td>
<td><strong>Iterable</strong></td>
<td><strong>Map</strong></td>
</tr>
<tr>
<td><strong>null</strong></td>
<td>""</td>
<td>0</td>
<td>0.0</td>
<td>false</td>
<td>1970-1-1 00:00</td>
<td>byte[0]</td>
<td>Empty</td>
<td>Empty</td>
</tr>
<tr>
<td><strong>String</strong></td>
<td>=</td>
<td>parse</td>
<td>parse</td>
<td>false if empty/ "0"/"false</td>
<td>parse</td>
<td>UTF-8 bytes</td>
<td>Single entry</td>
<td>Error</td>
</tr>
<tr>
<td><strong>Long</strong></td>
<td>to string</td>
<td>=</td>
<td>Convert to Double at possible precision</td>
<td>false if 0</td>
<td>ms since epoch</td>
<td>Same as string UTF-8</td>
<td>Single entry</td>
<td>Error</td>
</tr>
<tr>
<td><strong>Double</strong></td>
<td>to string</td>
<td>Floor</td>
<td>=</td>
<td>false if 0.0</td>
<td>ms since epoch</td>
<td>Same as string UTF-8</td>
<td>Single entry</td>
<td>Error</td>
</tr>
<tr>
<td><strong>Boolean</strong></td>
<td>"true"/ "false"</td>
<td>0 / 1</td>
<td>0.0 / 1.0</td>
<td>=</td>
<td>Error</td>
<td>Same as string UTF-8</td>
<td>Single entry</td>
<td>Error</td>
</tr>
</tbody>
</table>

### *Null* Values Conversion

Null is supported as a value in Broadway. However, to avoid null pointer exceptions as much as possible, null has an implicit conversion to every supported type.

### *Iterable* Conversion
When converting a value into an iterable, the result is an Iterable of a single entry containing that value. For instance, an integer with the value 7, is converted into an iterable where the only value is 7. The exceptions to this rule are **null**, which is converted to an empty iterable, and **Map** which is converted to an iterable of the map values (without the keys).

### *Date* Conversion

Dates are converted to strings using their UTC representation in the following format:
    *yyyy-MM-dd HH:mm:ss.SSS*

When parsed, dates support one of the following formats:
-  *yyyy-MM-ddTHH:mm[:ss[.SSS[Z]]]*
-  *yyyy-MM-dd HH:mm[:ss[.SSS[Z]]]*

Either T or ' ' as a delimiter between date and time. Seconds, milliseconds and timezone are optional.

When numbers are converted to dates and vice versa, the value of the date is considered the number of milliseconds elapsed since 1970-1-1 00:00.

Broadway supports date/time manipulation Actors for more explicit date/time conversions and calculations.


### *byte[]* Conversion

When binary data is referred to as a string or vice versa, utf-8 binary representations is assumed. When formatting other types, the conversion goes via a string and is then converted to the utf-8 representation.


### Errors

Not all conversions are possible. When Broadway cannot convert a data type, an Exception will be thrown. This could be as a result of parsing errors (for instance string to Long) or of unsupported conversions (for instance Long to Map).

In such cases, or when the implicit conversion is unsatisfactory, you can consider using an Actor to explicitly convert between data types and data representations.

[![Previous](/articles/images/Previous.png)](04_built_in_actor_types.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](06_export_actor.md)

