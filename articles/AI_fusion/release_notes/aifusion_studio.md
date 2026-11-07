# AI Fusion Studio Release Notes

Release notes of the **aifusion-studio** extension (AI Fusion - Studio). The newest version is listed first. See [AI Fusion Studio](/articles/AI_fusion/03_studio/README.md) for the documentation.

For compatibility with Fabric and with the other AI Fusion extensions, see [AI Versions](/articles/Product_Versions/AI_versions.md).

<ul>
<li><a href="#v1.0.2">1.0.2</a> — November 6, 2026</li>
<li><a href="#v1.0.1">1.0.1</a> — September 29, 2026</li>
<li><a href="#v1.0.0">1.0.0</a> — September 28, 2026 (GA)</li>
<li><a href="#preview">0.0.x</a> — June–September 2026 (preview)</li>
</ul>



<a id="v1.0.2"></a>
## Version 1.0.2

**Released:** November 6, 2026 · **Minimum Fabric version:** 8.5.2 · **Requires:** aifusion 3.0.9 for conversation-level evaluation, eval hooks and `additional_context`

- **Conversation-level evaluation.** An eval case can be judged on the whole conversation, not only step by step. In the eval case editor:
  - **Evaluate on** chooses Step level, Conversation level or both. New cases — from **New Eval Suite...**, **New Test...** and **Save as test case** — default to Conversation level and start without criteria; add criteria before running them. (Cases created by the Studio AI test generator are evaluated at step level.)
  - A criteria editor, with **Ask AI**, for the conversation-level criteria. Ask AI proposes criteria, which you approve, modify or deny. A warning appears while the conversation level is on and has no criteria; such a case fails with a note.
  - Properties are collapsible sections with a toggle per level. The last level that is on cannot be switched off.
  - The Evaluation tab has a Step level box and a Conversation level box. Each criterion shows met, partial or missed; click it to see the judge's note and the turns it cites, and click a turn to jump to it in the transcript.
  - Eval CSV files get two new columns, `eval_mode` and `conv_criteria`. Older step-only files open and save unchanged.
- **Eval hooks.** A **Run hooks** section in the Definition tab runs Broadway flows before and after an eval case (`before_hooks`, `after_hooks` columns). Pick a flow from the drop-down, open it, or drag to reorder. The Evaluation tab shows the overall verdict and a chip per hook; click a chip to see its status, message and output.
- **Analyze from the Eval Editor** also sends the unmet conversation criteria to Studio AI.
- **AI Explorer:** an orange bullet now also marks a test case whose conversation criteria were not met, whose after hook failed, or that was not evaluated.
- **`step_start` hook:** the Hooks card and the new-agent template describe the hook's `additional_context` output, which applies to the current turn only, and its `previous_additional_context` input, which holds the previous turn's value so a flow can keep it by returning it again.
- **Performance thresholds:** the **Customize** toggle on a performance dimension stays on after save and reload. Turning it off restores the default thresholds. Long dimension names wrap instead of pushing the toggle off the panel.



<a id="v1.0.1"></a>
## Version 1.0.1

**Released:** September 29, 2026 · **Minimum Fabric version:** 8.4.7 · **Requires:** aifusion 3.0.8 for the new agent keys

- **Response guidelines** field in the Agent Editor, beside System prompt (`response_prompt`) — house style for the user-facing reply, which also travels to a sub-agent the agent hands a whole turn to.
- **Constraints** field (`constraints`) below Response guidelines — non-negotiable domain rules. Both sections have a **?** that opens a matrix showing what happens to each on `task`, on `delegate`, and when the agent answers itself.
- The Subagents section has an **Allow handing a turn to a subagent** toggle (`delegate`, off by default) and a route dropdown per sub-agent — **Both** / **Task only** / **Delegate only** (`invoke`).
- Saved test cases, shared chats and eval transcripts now contain the agent's reply alone, without the narration streamed before it.
- **Observation in new agents.** The new-agent template includes sample `Observation/signals.yaml` and `Observation/widgets.yaml` files, and the Agent Builder skill can create Observation signals and widgets for an agent.
- **Preview:** hover over one of your prompts to copy it, or edit it and send it again as a new turn. **Share**, **Save as test case** and **Analyze** are disabled while the chat is empty. After you fill in the **New chat** dialog, the cursor moves to the message box.
- **Test cases:** **Go to test case** opens the new case and selects it in the AI Explorer. **New Test...** and **New Eval Suite...** also select the new row in the AI Explorer.
- **Agent Editor:** all drop-downs look and behave the same. The ignored `search_reference.dir` field is removed.
- **Evaluation:** a clear *Couldn't start the evaluation* message when the evaluation pipeline fails to start.
- **Setting removed:** the `aifusion.fabricBaseUrl` setting is gone; Studio reaches Fabric on localhost. To use a Fabric server on another host, set the `FABRIC_BASE_URL` environment variable.



<a id="v1.0.0"></a>
## Version 1.0.0

**Released:** September 28, 2026 · **Minimum Fabric version:** 8.4.7

First GA release.

### New Features

- **Test auto-generator in the Agent Builder.** Builds an agent's eval suite end-to-end: each business question is run through the agent to capture a real answer, then saved as a runnable test case. See [AI-Assisted Development](/articles/AI_fusion/03_studio/04_ai_assisted_development.md).
- **Studio AI researcher.** An **Analyze** action on a Preview note diagnoses a bad agent answer, applies the fix, re-runs to verify, and offers a regression test — each step with your approval.
- **Planner plans from written procedures.** The planner reads the agent's `References/Procedures/` pack instead of searching earlier, unreviewed plans, and falls back to planning from the agent's capabilities when nothing matches.
- The Agent Builder is delivered as a Studio AI skill.

### Improvements

- The feedback **Note** pop-up opens upward when there is no room below, so its buttons are always reachable.
- **Save as test case** has a suite drop-down and other refinements.



<a id="preview"></a>
## Preview Versions (0.0.x)

**Released:** June–September 2026

- **0.0.16** (August 24, 2026) — the Thinking drop-down offers `xhigh` and `max`.
- **0.0.8** — React-based Agent Editor: collapsible form view, Monaco-based Source view and a chat tab for testing agents; calls go to the Fabric server hosting Studio using the browser session, with no URL or credential settings.
- **0.0.7** — Agent Editor UI refresh: card-based sections, agent / sub-agent badge, side-by-side Planning and Reference search on wide screens.
- **0.0.6** — the Agent Editor works in K2view Web Studio; AI Explorer and the New Agent / New Sub-Agent commands open agent definitions directly in the editor.
- **0.0.4** — first form-based Agent Editor for `agent.yaml` and sub-agent files: system prompt, tools, MCP interfaces, hooks, planning, reference search, skills and settings, with comment-preserving YAML writes.
