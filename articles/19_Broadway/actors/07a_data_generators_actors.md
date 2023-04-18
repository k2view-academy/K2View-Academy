# Data Generation Actors

Starting from V7.1, Fabric separates the data generation (manufacturing) of a synthetic data from the hashing and caching capabilities. The data generation Actors can be used to either generate synthetic entities or mask sensitive data. Broadway provides various data manufacturing Actors under the **generators** category to generate a random synthetic value. For example: RandomString, RandomNumber, Sequence...

A data generator Actor can be executed by either the Broadway flow ('as is') to generate new data or the **Masking** Actor to cache the generated data. 

## Main Data Generation Actors

### RandomRegexGenerator

This Actor generates random string matching the input regular expression.

The **regex** input argument can get any regular expression.

**Examples**:

- Populate the **regex** with '[a-z0-9-_\.]{5,6}@[a-z][a-z0-9-_\.]{2,10}[a-z0-9]\.(com|net|org)' to generate an email.
- Populate the **regex** with '\w{10}' to generate a random String with 10 characters.
- Populate the **regex** with '\d' to generate a random number.

### RandomDistribution

This Actor generates random values according to a given distribution. The supported distributions types are **normal**, **uniform**,  **weighted** and **constant** (return one value) .

The distribution parameters are set based on the selected distribution type:

- **Normal** distribution (gaussian) works using **mean** and **stddev** (standard deviation), and can be bound by **minimum** and **maximum** values both inclusive.

- **Uniform** distribution returns a random value between the **minimum** and **maximum** values.

- **Weighted** distribution returns a value from the list based on the value's weight. For example, generate 20% of the customers in Miami, 30% in LA and 50% in NY. Weighted distribution uses a 'weights' map where the keys are the results and the values are positive numbers indicating the weight of the entry out of the total.

  See example:

  ![weighted dist example](/articles/19_Broadway/images/weighted_dist_example.png)

- **costant** distribution returns the populated value. For example: set the number of generated addresses to 1 address per customer.

### RandomFromCollection

This actor returns a random value from the input collection.

### RandomCreditCard

This Actor generates fake but valid credit card number based on the input **value** and **prefixLength** input arguments:

-  **value** - defines the credit card template. For example: 4580111122223333.  The length of this argument defines the generated credit card number length. Note that if this argument is empty, the generated credit card number has 16 digits.
- **prefixLength** - the length of the prefix in the input template.

Example:

- value is 4580111122223333.
- prefixLength is 4.
- The generated credit card number starts with 4580 (the first 4 digits of the template).

### RandomDecimal

This Actor generates a random number in a given range. The precision of the number can be set in the **precision** input argument. Note that a random decimal can be also be generated using the RandomDistribution Actor.

### RandomString

This Actor generates random string with specified length. The String's length is set based on the **minLength** and **maxLength** input arguments. Note that a random String can also be generated using the RandomRegexGenerator and RandomDistribution Actors.

### Sequence

This sequence implements a unique sequential number. 

Click [here](08_sequence_implementation_guide.md) for more information about the sequence implementation.

### RandomUUID

This Actor generates a random UUID.

### RowsGenerator

This Actor has been added to support a generation of synthetic data into the LU table and is a framework for generating random rows given a set of parent rows, a distribution and an inner flow. It relies on the inner flow to generate the actual rows data.

This Actor is invoked by the **SourceDbQuery** Actor in the LU population flow. The **SourceDbQuery** checks the ROWS_GENERATOR key:  

- If the ROWS_GENERATOR is **false**, the SourcebQuery runs the SQL query that is set in this Actor to get the data from the source DB. 
- If the ROWS_GENERATOR is **true**, the SourcebQuery runs the **RowsGenerator** Actor to activate the data generator flow and populate the LU table with a generated synthetic data.  The data generation inner flow is identified by its naming convention: [LU population flow name].generator. For example: the data generation flow of the score LU table is called score.population.generator.flow.

For every parent row, the **RowsGenerator** Actor calls the data generation inner flow a random number of times according to the given distribution. 

The following values passed to the inner flow:

- total - the total number of rows for the current parent row.

- count - the current iteration within the current parent row, starting at 0.
- parent_row - the current parent row. 
- parent_rows - the remaining parent rows, including the current parent_row. Reading rows from this container means they will not be available to the actor.

There are several options to develop the inner flow:

- Row by row - the inner flow can return a single row and let the RowsGenerator actor handle parent rows and number of rows per parent. The flow can either return multiple results that will serve as the row columns or a single result named **result** of type map. 
- Rows per parent - if the inner flow returns a single result named **result** with a **collection of maps**, the actor will collect them and move to the next parent row.
- Handle all parents rows - a flow can traverse the parent_rows and return a **collection of maps**. The actor will return these rows and will not call the inner flow again.

**Example:**

A customer has 2 activities. The data generation inner flow needs to generate 3 case's records for each activity.

**Row by row** mode: the data generation inner flow is called 6 times (2*3) to generate the cases for the customer. It generates one case records on each call.

**Rows per parent** mode:  the data generation inner flow is called 2 times (there are 2 parent activities) - each call is set with a different parent activity ID and it generates 3 cases on each call.

**Handle all parents rows** mode: the data generation inner flow is called once for the customer and generates 6 case records (2*3) for the customer: 3 case records for each parent activity ID.



[![Previous](/articles/images/Previous.png)](07_masking_and_sequence_actors.md)[<img align="right" width="60" height="54" src="/articles/images/Next.png">](08_sequence_implementation_guide.md)



