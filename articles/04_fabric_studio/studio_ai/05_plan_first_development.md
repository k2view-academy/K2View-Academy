# Plan-First Development with the Architect Agent

For complex or multi-step development tasks, the most effective approach in Studio AI is to plan before you code. The @Architect agent is designed specifically for this: it takes your goal, reasons about the structure of your project, and produces a detailed implementation plan that @Coder can then execute.

## Why Plan First?

Jumping straight into code generation on a large feature often produces fragmented results — changes that work in isolation but miss dependencies, or an approach that requires significant rework. Using @Architect first gives you a shared blueprint that is consistent, reviewable, and easy to hand off.

The plan-first workflow also separates *design decisions* from *implementation work*. You can iterate on the plan in conversation, refine the approach, and only commit to code once you are confident the strategy is right.

## Step 1: Describe Your Goal to @Architect

Open AI Chat and address @Architect with a description of what you want to build. Be as specific as you can about the goal; you do not need to specify the implementation steps — that is @Architect's job.

**Example prompts:**

```
@Architect Plan a new enrichment that pulls customer risk scores from the Oracle CRM interface
```

```
@Architect I need to add a transaction history table to the customer_bank LU and expose it through a Graphit web service
```

@Architect will analyze your project structure — Logical Units, shared objects, interfaces, existing functions — and generate a **structured implementation plan**.

## Step 2: Review the Plan

The plan is presented directly in the chat as a structured document. It typically includes:

- A summary of the goal and approach
- A list of numbered steps with the specific file changes, functions to write, and configurations to update
- Verification criteria — what should be true once the implementation is complete

Read through the plan and assess whether the approach makes sense. If you want to adjust anything — change the order of steps, add a constraint, choose a different pattern — just tell @Architect in a follow-up message:

```
The enrichment should use batch mode rather than per-instance. Please update the plan.
```

@Architect keeps the whole plan document consistent as you iterate, so you never end up with contradictions between sections.

## Step 3: Execute the Plan with @Coder

Once you are satisfied with the plan, click the **"Execute with Coder"** button that appears below it. This opens a fresh @Coder session pre-loaded with the plan as context.

@Coder reads the plan and works through the steps autonomously in **Agent Mode**, tracking its progress with a visual task list in the chat. You can follow along in real time, and the session notification will alert you when the implementation is complete.

## The Plan File

The plan is automatically saved as a Markdown file in your workspace. This means the plan persists even if you close the chat or the Studio.

If you close the AI Chat and want to resume later, open the plan file in the editor and use the command palette (**F1**) to run **"Execute Plan with Coder"**. This starts a fresh @Coder session from the saved plan, exactly as if you had clicked the button in the original chat.

The plan file is a regular Markdown document, so you can also edit it directly in the Studio editor to make changes outside of the chat, then execute the updated version.

## Tips for Getting the Best Plans

**Be specific about constraints.** If you have preferences about patterns, libraries, or approaches — for example, "use try-with-resources for all DB operations" or "do not modify the existing population logic" — state them upfront. @Architect incorporates these into the plan.

**Include relevant context.** Use `#file:path/to/file` to attach specific files that @Architect should take into account when planning. For example, attaching an existing function that the new one should follow in style:

```
@Architect Plan a currency conversion function similar to the one in #file:implementation/Shared Objects/functions/ParseDate.java
```

**Iterate before executing.** It is much cheaper to revise a plan than to revise implemented code. Spend a few extra messages refining the plan before handing it off to @Coder.

**Check dependencies.** Ask @Architect to analyze dependencies as part of your planning:

```
@Architect Before we plan, analyze the dependencies between the customer_bank and aifusion LUs so we understand the impact
```
