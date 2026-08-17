# BW_Advanced

# BW Advanced

# Supported Data Types in Broadway

Actors pass Java objects between stages. Broadway supports a focused set of types and will auto-convert between them when an Actor expects a different (compatible) type. Supported types are described by the Schema property, visualized in the Data Inspector, and converted automatically where possible.

## Core supported types

Primitives: String, Long, Real, Boolean, Date, byte[]

Other numerics: int/Integer, short/Short, byte/Byte, float/Float auto-convert to Long/Double

Binary: JDBC Blob → auto-converted to byte[]

Collections (Iterables): supported through Java arrays or maps

## Implicit conversion

Broadway converts inputs to the type an Actor declares. “Reasonable” conversions are automatic; otherwise an exception is thrown.

Handy rules

Numbers ↔ Strings: Long/Double ⇄ String (parse/format)

Booleans ↔ Numbers: true ↔ 1, false ↔ 0

Booleans ↔ Strings: "true"/"false"; empty/"0"/"false" → false

Numbers ↔ Date: numbers are milliseconds since 1970-01-01 00:00:00 UTC

Value → Iterable: becomes a single-item iterable (7 → [7])

Exceptions: null → empty iterable; Map → iterable of values (keys dropped)

Strings ↔ byte[]: via UTF-8; other types format to String first, then to UTF-8 bytes

Practical examples

DB returns Integer → your Actor expects Long ✅ (auto-widened)

Read "0" into a Boolean input ✅ → false

Pass 7 to an Iterable input ✅ → becomes [7]

Convert byte[] to String ✅ → UTF-8 assumed

Try Long into a Map ❌ → not supported (use a Map or wrap it yourself)

Default/null behavior

null is supported and implicitly convertible to every type with safe defaults:

String="", Long=0, Double=0.0, Boolean=false, Date=1970-01-01 00:00:00, byte[]=empty, Iterable=empty, Map=empty

Date formats

String output (format): yyyy-MM-dd HH:mm:ss.SSS (UTC)

String input (parsing):

yyyy-MM-ddTHH:mm[:ss[.SSS[Z]]]

yyyy-MM-dd HH:mm[:ss[.SSS[Z]]]

Delimiter T or space; seconds, millis, and timezone are optional

Broadway supports date/time manipulation Actors for more explicit date/time conversions and calculations.

## Linking between Actors

When you connect an Actor’s output to another Actor’s input, you can choose how the data is passed using the link’s properties:

- Link type

- Value (default)
Pass the value as-is.

- Iterate
Opens a loop over the linked value (Iterable/array). The link appears as a double-dashed line.
Auto-behavior: if you connect an array of T to an input of type T (e.g., string[] → string), Broadway will automatically set the link to Iterate.

- First
Pass only the first item (e.g., the first record of a result set).

- Varargs (variable arguments)
When ON, the target input becomes an array, and each additional source link appends another element to that array. Useful for building arrays on the fly (e.g., feeding the Concat Actor with many strings).

# Error handling

## Overview

Broadway handles exceptions at the Stage level, similar to a Java try–catch. If any Actor in a Stage throws an exception, the Stage’s error handler runs and decides the path forward:

return true → suppress the error and continue the flow

return false → do not suppress; stop the flow

## What can act as an error handler?

Any Actor (including

JavaScript Actor with custom logic in its input parameters

An InnerFlow Actor

Two built-in handlers designed for this purpose:

ErrorHandler

ErrorFields

For reuse across multiple flows or Stages, prefer implementing error handling as an Inner Flow and referencing it where needed.

### ErrorHandler Actor

Catches exceptions raised by any Actor in its Stage and provides Per-exception handling rules

- Exception classification:

- SQL (split into Unique constraint vs. Other).
Unique constraint detection is supported for Oracle, DB2, SQLite, and SQL Server.

HTTP - option to provide a specific status code

General Exception - option to provide a specific error message regex

Suppression control

Suppress checkbox toggles whether to swallow the error.

If an Inner Flow is configured for that error, it can override the checkbox by returning:

true → suppress and continue

false → do not suppress; stop

Built-in Retry

Configure Retries and Interval (ms) in the ErrorHandler editor
(defaults: Retries=0, Interval=500ms).

If an Inner Flow is used as the handler, it may override behavior by returning result = true / false / retry (“result” external name must be in used).

Logging:

Optional “Log” checkbox writes the error to logs

If retries are enabled, the current retry attempt is also logged
(e.g., “Stage <name> – retrying <Actor>: attempt <n>”)

### ErrorFields Actor

Always returns true (i.e., it suppresses the error), so the flow continues. It exposes structured context you can use to make routing decisions:

Error message

Error code

Origin: flow, stage, and actor names

Actor inputs that triggered the exception

Additional info: exception class, SQL statement (when relevant), and stack trace (if requested)

Number of retry attempts

Usage patterns

As the Stage’s error handler (directly)

Inside an Inner Flow that serves as the Stage’s error handler

The Inner Flow receives the error as input, runs ErrorFields to unpack it, and then applies custom logic to decide next steps.

The error input parameter of the ErrorFields must be set as External

## Retry mechanism

When a Stage throws an error, BW can automatically retry the failing Actor.

Ways to enable/drive retries:

ErrorHandler settings
Set Retries and Interval in the ErrorHandler.

If using an Inner Flow as the handler, it can return result = retry to continue attempts
Note: The output parameter must have “result” external name

Any actor defined as an error handler in a flow ('red actor')
Have the handler return retry (instead of true/false) to trigger another attempt.

If implemented as an Inner Flow, you can read the current attempt count via ErrorFields.attempt and branch logic accordingly.

# Reset Actor State

Broadway allows resetting an Actor’s state during flow execution. This is particularly useful when handling nested loops. For example, when using a StringBuild Actor to aggregate values inside an inner loop, the aggregation must be cleared at the start of each iteration of that loop.

How to Reset Actor State

Context Menu Reset (per iteration)

Right-click the Actor → Reset on iteration 0.

This option is available only when the Actor is placed inside an internal iteration (level ≥ 2).

Once enabled, a reset icon appears on the Actor badge.

At runtime, the Actor’s state is reset at the beginning of iteration 0, just before execution.

Applies only to Actors that maintain internal state.

ResetActors Actor

Use the ResetActors Actor to programmatically reset multiple Actors by ID (Actor names).

The specified Actors have their state cleared before continuing execution.

# Build a Custom Actor

Broadway provides two options for creating a custom Actor:

Create a new Actor

Based on Java code, or

Based on a BW Flow

Inherit from an existing Actor

## Creating a New Actor

### New Actor Based on Java code

Create the Java code

In the project, create a new Java file (src → right click → New Java File).

Extend the Actor class.

Implement the action method. This method is executed when the Actor runs.

Use input and output maps to handle parameters:

Retrieve input: input.string("inputName")

Set output: output.put("outputName", value)

Example:

public class MathSubtract implements com.k2view.broadway.model.Actor {

public void action(Data input, Data output) throws Exception {

Integer a = Integer.parseInt(input.string("a"));

Integer b = Integer.parseInt(input.string("b"));

output.put("result", Math.subtractExact(a, b));

}

}

Create the new Actor in Broadway, to execute the Java code

Broadway → Right click → New Actor.

Provide the Actor name

Set Tag (category in the palette).

Set Badge (icon next to Actor name).

Set the java path to your Java Class (Java class path).

Define input/output parameters with types and editors.

### New Actor Based on Broadway Flow

Each BW Flow can be wrapped as a single Actor:

From the flow menu, select Save as Actor….

Provide the Actor’s name and tag, then click Submit.

Inputs and outputs of the flow are automatically added to the Actor.

## Inherit an Actor

You can create a new Actor that reuses logic from an existing Actor.

Create the Java code 
In the project, create a new Java file (src → right click → New Java File).

Extend the inherited Actor class.

Override the action method. This method is executed when the Actor runs.

Use input and output maps to handle parameters:

Retrieve input: input.string("inputName")

Set output: output.put("outputName", value)

Example - Extending Logger

public class MyLogger extends Logger {

@Override

public void action(Data input, Data output) {

String message = input.string("projectName") + ": " + input.string("message");

input.put("message", message);

super.action(input, output);

}

}

- Create the new Actor and select the Actor it extends 
2 options:

- Via New Actor

Broadway → Right click → New Actor.

Provide a name.

In the Actor’s properties, set the Parent Actor from which current Actor will inherit

Via Export

Open the Actor’s menu → Export Actor.

Provide a new Actor name (do not check the Override option)

The Actor will automatically inherit the Actor’s input and output parameters.

- In the Actor’s properties, set the java path to your Java Class (Java class path).

### Override a Custom Actor

To override a custom actor:

Use Export Actor on the source Actor and select Override current

After overriding, adjust parameters (defaults, mandatory, modifiers, editor/schema) or add parameters as needed

## Actor parameters

When defining a new Actor, each parameter should be configured with the following properties:

Default Value – the initial value assigned to the parameter.

Schema – defines the input type (e.g., Boolean, String, Integer).

Editor – specifies the editor presented to the user when setting the parameter value.

Description – provides an explanation of the parameter’s purpose and how it affects the Actor’s logic.

Advanced Options:

Mandatory – whether the parameter must be set.

Modifier:

Final – default value cannot be changed.

Hidden – parameter is not visible to the user.

### Schema Property

The Schema property determines the parameter’s type.

Example basic structure:

{

"type": "string"

}

Supported types:

string

integer

decimal

number

date

boolean

array

object

### Editor Property

The Editor property defines the editor shown to the user when assigning a parameter value.

Default editor:

{

"id": "com.k2view.default"

}

Common Editor Configurations

Drop-down list of values

{

"id": "com.k2view.dropdown",

"options": ["1", "2"]

}

Select from available Broadway flows

{

"id": "com.k2view.innerflow"

}

Select from Logical Units (with optional empty entry)

{

"id": "com.k2view.logicalUnit",

"addEmptyEntry": true

}

Select from Interfaces (filtered by type)

{

"id": "com.k2view.interface",

"interfaceType": ["database"]

}

All available editors are located in:
/opt/apps/fabric/workspace/fabric/staticWeb/editors

Supported Editors:

- com.k2view.code

- com.k2view.distribution

- com.k2view.graphitFiles

- com.k2view.llmPrompt

- com.k2view.multipleSelection

- com.k2view.table

- com.k2view.dataviewer

- com.k2view.dropdown

- com.k2view.innerFlow

- com.k2view.logicalUnit

- com.k2view.regex

- com.k2view.textarea

- com.k2view.dbtable

- com.k2view.errorHandler

- com.k2view.integer

- com.k2view.mTable

- com.k2view.schedule

- com.k2view.timezone

- com.k2view.default

- com.k2view.functions

- com.k2view.interface

- com.k2view.mTableKey

- com.k2view.strings

# Parallel Stage Execution

Overview

A Stage can run multiple Actors (or Inner Flows) in parallel to increase throughput.

Configure parallelism

Open the Stage menu (icon at the stage’s top-right).

Select Parallel and enter the number of threads.

After saving, a Parallel icon with the thread count appears above the Stage name.

Disable parallelism

Open Stage menu → Parallel, set the thread count to 0.

Example

Stage 2 contains two Inner Flows. With parallelism enabled, both inner flows read from the LUI and write to the target DB in parallel, improving total execution time.

# Broadway metrics

## Broadway Profiler

The Broadway Profiler can be enabled during flow execution to provide a detailed breakdown of results by Flow, Stage, Actor, and Iteration. It can be activated from Fabric Studio or via the broadway command.

Enabling the Profiler in Studio

In the Main menu toolbar of the BW flow, select Actions > Profiler and run the flow.

After execution, the Run Results window displays a line: “Profiler Results: Click on the Viewer icon.”

Click the Viewer icon (image) to open and review the Profiler results.

Running the Profiler via Broadway Command

To enable the Profiler when executing a flow with the broadway command, set the profilerTelemetry argument to true.
This adds the Profiler output to the command’s results.

## Trace Command

Get the metrics of a single BW flow by invoking the trace command of your session, before executing the BW flow.
Syntax:
trace [session_scope/global_scope] <TRACE_NAME> '[TRACE_PARAM=[TRACE_VALUES]];...';

Example:

## JMX Stats

JMX Stats provide runtime performance metrics for Broadway flows, broken down by Flow, Stage, Actor, and Iteration. This allows monitoring and analysis of flow behavior across multiple executions, complementing the single-run insights from the Profiler.

# Recovery Point

Recovery Points provide a mechanism to resume execution from a defined stage instead of restarting the entire flow.

- When a Recovery Point is set, Broadway serializes the flow’s data and stores it in the broadway_recovery_point table under the k2system keyspace.

- If a failure occurs (e.g., outage), the flow can restart from the last saved point.

- Once execution completes successfully, the recovery data is automatically removed from the System DB

Recovery Points are best used after completing a sub-process and before starting the next major stage of the flow.
⚠️ Limitations: Recovery Points cannot be set on:

Stages with DB result sets

Transactional Stages

Stages inside iterations

How to Set a Recovery Point

Open the Stage context menu (⋮).

Select Recovery Point → a recovery icon appears on the Stage.

Repeat for additional Stages if multiple Recovery Points are required in the flow.

Running a Flow with Recovery Point

Broadway flows with Recovery Points can be executed in three ways:

Via the BROADWAY Command

Must include a Recovery ID to enable the Recovery mechanism.

If a crash occurs, rerun with the same Recovery ID to resume.

Via the STARTJOB Command (Broadway Job)

Recovery mechanism is enabled automatically.

No need to specify a Recovery ID.

Via Fabric Studio (Simulation)

Set a breakpoint after the Recovery Point.

Select Actions > Run with Recovery Point.

When execution hits the breakpoint, click Stop Run to abort.

Rerun with Run with Recovery Point → flow resumes from Recovery Point.

# Deploy.flow

Automatic Flow Execution on Deploy

Broadway supports automatic execution of flows during a Logical Unit (LU) deploy, using deploy.flow

When a deploy.flow is defined under an LU, it is triggered automatically each time the LU is deployed.

If deploy.flow exists only at the Shared level, it is inherited by all LUs.

If a Soft Deploy is used, the deploy.flow is not executed.

Auto-Generated deploy.flow

When a new LU is created, a deploy.flow is automatically generated with predefined constants:

lu_name – Name of the deployed LU.

nosync – Controls sync behavior:

NOSYNC=TRUE: Only schema changes trigger a sync after deploy.

NOSYNC=FALSE: Any deploy triggers a sync when an instance is first accessed.

is_first_deploy – Boolean indicating if this is the LU’s first deploy.

is_studio – True when the Fabric instance is Dev (includes Studio)

- The job can be created using a BroadwayJob actor in the deploy.flow of the required LU.

In your implementation, add a BroadwayJob Actor to the deploy.flow Broadway flow in the References LU that will run the REF_BACKUP command for all the relevant schemas. It will start the common tables backup job when deploying the References LU.
