# AI Fusion 1.x Release Notes

Release notes of the **aifusion** extension, versions 1.x. Documentation for this version is in the [legacy section](/articles/AI_fusion/legacy_v1_v2/README.md).

For compatibility with Fabric and with the other AI Fusion extensions, see [AI Fusion Versions](/articles/Product_Versions/AI_Fusion_versions.md).

<ul>
<li><a href="#v1.0.8">1.0.8</a> — January 12, 2026</li>
<li><a href="#v1.0.5">1.0.5</a> — November 18, 2025</li>
<li><a href="#v1.0.3">1.0.3</a> — September 23, 2024</li>
<li><a href="#v1.0.2">1.0.2</a> — September 14, 2024</li>
<li><a href="#v1.0.1">1.0.1</a> — August 13, 2024</li>
<li><a href="#v1.0.0">1.0.0</a> — August 2024</li>
</ul>



<a id="v1.0.8"></a>
## Version 1.0.8

**Released:** January 12, 2026 · **Minimum Fabric version:** 8.3.0

### Orchestrator Improvements

- The **Reflector** has a 4th routing option, **Clarify**, to ask the user for clarification when needed.
- The **Refiner** is activated only on the sub-agent path, where it refines sub-agent goals.
- New **Responder** component that crafts and formalizes the final response on all four paths, so organizations can define rules and instructions for response formatting. The Orchestrator actor accepts a `responderPrompt`.
- **Planner:** plans and procedures are no longer mandatory.

### General

- The GenAI Testing LU was merged into the `aifusion` LU.
- General tuning, cleanup and reorganization for improved usability.
- Added vector-based example flows.
- Fixed evaluation issues in non-development environments.



<a id="v1.0.5"></a>
## Version 1.0.5

**Released:** November 18, 2025 · **Minimum Fabric version:** 8.3.0

- Unified apps.
- New Orchestrator and Planner architecture.



<a id="v1.0.3"></a>
## Version 1.0.3

**Released:** September 23, 2024 · **Minimum Fabric version:** 8.1.0

- General availability (preview tag removed).
- Updated the link to the license file.



<a id="v1.0.2"></a>
## Version 1.0.2

**Released:** September 14, 2024 · **Minimum Fabric version:** 8.1.0

- UI customization using CSS and texts.
- New LLM query Refiner actor.
- Bug fixes and improvements.



<a id="v1.0.1"></a>
## Version 1.0.1

**Released:** August 13, 2024 · **Minimum Fabric version:** 8.0.0

- Fixed duplicate queries during the user story.
- Switched to the product-supplied `LLMConst` and `LLMAppend` actors.



<a id="v1.0.0"></a>
## Version 1.0.0

**Released:** August 2024 · **Minimum Fabric version:** 8.0.0

- Initial release (preview).
