# Data Types in Broadway

When Broadway actors pass data between them, the data is passed as Java Objects. Virtually any data type can be passed between Actors but in practice most actors pass a subset of types that are supported by Broadway. Using a supported Broadway type can be described by the Broadway Schema engine and can be displayed clearly by the Data Inspector.

### Primitives

Broadway supports the following primitives: *String*, *Long*, *Real*, *Boolean*, *Date*, *byte[]*.

Other numeric types such as *Integer*, *Short*, *Float*, *Byte* and their primitive counterparts (long, int, short, byte, double, float), are automatically converted to Long/Real.

Sql Blob is also supported and is automatically converted into a byte[].


### Collections/Iterable

Collections, or sequences of objects, are represented by any implementation of Iterable (Collection, List etc). The advantage of using Iterable as the lowest common denominator is that it does not need to declare its size in advance, easily supporting streaming uses cases such as databases queries and topic subscription where the number of items is not known in advance or is even unbound.
Java Arrays are also supported and treated as sequences.


### Maps/Objects

The Map interface is used to represent Objects that contain a set of named attributes. The key is usually of String type. For instance, Maps are used extensively to represent result set rows.


### Implicit Type Conversion

When Actors read their input arguments from Broadway, and expect a certain type of parameter, Broadway converts the value to the type expected by the Actor.

The conversion is highly robust, where any reasonable conversion is done in the attempt to satisfy the Actor expected input. For instance a Long can be converted into a String and a String can be parsed into an Integer (if possible). An integer can be converted to a boolean and vice versa (0=false, true=1). Automatic conversion is also done between primitives and collections or between maps and collections. The following is an exhaustive table of all available conversions:

| Source/Target |           String          |            Long           |                  Double                 |           Boolean          |      Date      |        byte[]        |         Iterable         |  Map  |
|:-------------:|:-------------------------:|:-------------------------:|:---------------------------------------:|:--------------------------:|:--------------:|:--------------------:|:------------------------:|:-----:|
|      null     |             ""            |             0             |                   0.0                   |            false           | 1970-1-1 00:00 |        byte[0]       |           Empty          | Empty |
|     String    |             =             |           parse           |                  parse                  |  false if empty/"0"/"false |      parse     |      utf-8 bytes     |       single entry       | Error |
|      Long     |        to string       |             =             | Convert to double at possible precision |         false if 0         | ms since epoch | same as string utf-8 |       single entry       | Error |
|     Double    |      to string        |           Floor           |                    =                    |        false if 0.0        | ms since epoch | same as string utf-8 |       single entry       | Error |
|    Boolean    |     "true"/"false" |           0 / 1           |                0.0 / 1.0                |              =             |      Error     | same as string utf-8 |       single entry       | Error |
|      Date     |         UTC format        | milliseconds  since epoch |         milliseconds since epoch        | false if 1970-1-1 00:00:00 |        =       | same as string utf-8 |       single entry       | Error |
|     byte[]    |        utf-8 string       |           Error           |                  Error                  |       false if empty       |      Error     |           =          |       single entry       | Error |
|    Iterable   | join without   delimiters |           Error           |                  Error                  |       false if empty       |      Error     | same as string utf-8 |             =            | Error |
|      Map      |          JSON {:}         |           Error           |                  Error                  |       false if empty       |      Error     | same as string utf-8 | iterable of   map values |   =   |


### null values

Null is supported as a value in Broadway. However, to avoid null pointer exceptions as much as possible, null has a conversion to every requested type.


### Date formats

Dates are converted to Strings using UTC with the following format:
    *yyyy-MM-dd HH:mm:ss.SSS*
When parsed, dates have one of the following formats:
  *yyyy-MM-ddTHH:mm[:ss[.SSS[Z]]]*
  *yyyy-MM-dd HH:mm[:ss[.SSS[Z]]]*
Either T or ' ' as a delimiter between date and time and seconds, milleconds and timezone as optional fields.

When numbers are converted to dates and vice versa, the value of the date is considered the number of milliseconds elapsed since 1970-1-1 00:00.


### byte[]

When binary data is referred to as a string or vice versa, utf-8 binary representations is assumed. When formatting from other types, the conversion is done in the same way as string and then to the utf-8 representation. 
