# Best Practices and Maintenance

This guide covers ongoing best practices for maintaining a successful evaluation program.

---

## Getting Started Right

### Start Small and Simple

**Don't:**
- Try to test everything at once
- Create 100 tests in the first week
- Build complex organizational structures immediately

**Do:**
- Start with 5-10 tests covering the most common questions
- Focus on critical user scenarios
- Use simple organization (one suite)
- Build confidence with the tool

**First week goals:**
```
Week 1:
✓ Create 5 core tests
✓ Run them successfully
✓ Understand the results
✓ Fix one issue based on results
```

### Focus on High-Value Scenarios

Ask yourself:
- What questions do customers ask most often?
- What mistakes would be most embarrassing or costly?
- What scenarios are business-critical?
- What features are you least confident about?

**Start here, not with edge cases.**

### Set Realistic Expectations

**Reality check:**
- New agents won't score 5.0 on everything
- Some failures are expected
- Perfect scores take time and iteration
- Focus on progress, not perfection

**Initial targets:**
- Week 1: Get tests running, score ~3.5-4.0
- Month 1: Address major issues, score ~4.0-4.2
- Month 3: Refined quality, score ~4.2-4.5
- Month 6+: Excellence, score ~4.5+

---

## Creating Effective Tests

### Write from Customer Perspective

**Customer voice:**
✅ "How much do I owe on my credit card?"
✅ "Can I transfer money between my accounts?"
✅ "Why is my bill so high this month?"

**Not technical voice:**
❌ "Query outstanding balance on revolving credit facility"
❌ "Initiate inter-account fund transfer"
❌ "Explain variance in billing statement"

### Include Real Customer Quirks

Test how your agent handles:
- Typos: "ballance" instead of "balance"
- Informal language: "Got any money in my account?"
- Vague questions: "How am I doing?" (financially)
- Multiple questions at once: "What's my balance and when is my payment due?"

### Test Happy Paths AND Unhappy Paths

**Happy path (must test):**
```
Q: What's my checking account balance?
A: $1,234.56 (data exists and retrieves correctly)
```

**Unhappy paths (also test):**
```
Q: What's my checking account balance?
Cases to test:
- Customer has no checking account
- Multiple checking accounts (ambiguous)
- Account is closed
- Data temporarily unavailable
- Balance is negative (overdraft)
```

### Set Appropriate Thresholds

**By criterion:**

<table>
<tbody>
<tr>
<td><strong>Criterion</strong></td>
<td><strong>Start With</strong></td>
<td><strong>Raise To</strong></td>
<td><strong>Maintain At</strong></td>
</tr>
<tr>
<td>Accuracy</td>
<td>4.0</td>
<td>4.2-4.5</td>
<td>4.0-4.5</td>
</tr>
<tr>
<td>Relevance</td>
<td>4.0</td>
<td>4.0-4.2</td>
<td>4.0</td>
</tr>
<tr>
<td>Clarity</td>
<td>3.5-4.0</td>
<td>4.0-4.2</td>
<td>4.0</td>
</tr>
<tr>
<td>Politeness</td>
<td>3.5</td>
<td>3.5-4.0</td>
<td>3.5</td>
</tr>
</tbody>
</table>

**Adjustment strategy:**
1. Start conservative (4.0)
2. Run tests, see actual scores
3. If consistently above threshold → raise standards
4. If consistently below → investigate and fix
5. Gradually improve over time

---

## Running Tests Regularly

### Establish a Testing Rhythm

#### Daily (During Active Development)
```
After each change:
- Run affected test suite
- Review failures immediately
- Fix before moving on
```

#### Weekly (Standard Operations)
```
Every Monday morning:
- Run full test suite
- Review any new failures
- Track trends week-over-week
- Share results with team
```

#### Before Deployments (Always)
```
Pre-deployment checklist:
✓ Run critical test suite
✓ All tests passing or documented exceptions
✓ Compare to previous run
✓ Get approval if regressions exist
```

#### Monthly (Maintenance)
```
First week of month:
- Review all test cases for relevance
- Update expected answers if rules changed
- Remove obsolete tests
- Add tests for new features
- Reorganize if needed
```

### When NOT to Run Tests

**Don't run tests when:**
- Agent is actively being edited
- System is known to be down
- Data is being refreshed/migrated
- During peak usage hours (if impacts customers)

**Wait for stable state before testing.**

---

## Understanding and Acting on Results

### When Tests Pass ✅

**Don't just celebrate - analyze:**
- Why did they pass?
- Are scores improving over time?
- Any borderline scores (close to threshold)?
- Is the agent actually good or are thresholds too low?

**Actions:**
- Document the success
- Track the scores
- Consider raising thresholds gradually
- Share positive results with team

### When Tests Fail ❌

**Don't panic - investigate:**

#### Step 1: Understand the Failure

**Questions to ask:**
- Which criterion failed? (Accuracy, Relevance, Clarity, Politeness)
- By how much? (3.8 vs 4.0 is different than 2.0 vs 4.0)
- Is it consistent or random?
- Did multiple tests fail the same way?

#### Step 2: Determine Root Cause

**Common causes:**

<table>
<tbody>
<tr>
<td><strong>Symptom</strong></td>
<td><strong>Likely Cause</strong></td>
<td><strong>Solution</strong></td>
</tr>
<tr>
<td>Accuracy failures</td>
<td>Wrong data or no data access</td>
<td>Fix data configuration</td>
</tr>
<tr>
<td>Relevance failures</td>
<td>Misunderstood question</td>
<td>Improve agent instructions</td>
</tr>
<tr>
<td>Clarity failures</td>
<td>Poor formatting</td>
<td>Fix response templates</td>
</tr>
<tr>
<td>Politeness failures</td>
<td>Tone issues</td>
<td>Adjust personality settings</td>
</tr>
<tr>
<td>Random failures</td>
<td>Threshold too tight</td>
<td>Review threshold setting</td>
</tr>
<tr>
<td>All tests failing</td>
<td>System issue</td>
<td>Check agent is running</td>
</tr>
</tbody>
</table>

#### Step 3: Fix the Right Thing

**Fix the agent when:**
- Information is incorrect
- Behavior doesn't meet user needs
- Real customers would be dissatisfied

**Fix the test when:**
- Expected answer is unrealistic
- Threshold is too strict
- Test doesn't reflect real usage
- Requirements have changed

#### Step 4: Re-run and Verify

```
1. Make the fix
2. Re-run the failing test
3. Verify it now passes
4. Run related tests (check for side effects)
5. Document what was fixed
```

### When Results Are Inconsistent ⚠

**Same test passes sometimes, fails other times:**

**Possible causes:**
- Score is right at threshold boundary (4.0 threshold, scores 3.9-4.1)
- Agent has non-deterministic behavior
- Test depends on timing or external factors
- Data changes between runs

**Solutions:**
- Review threshold (is 4.0 really necessary or is 3.8 acceptable?)
- Investigate agent consistency
- Make test more robust
- Use test data that doesn't change

---

## Tracking Trends Over Time

### What to Track

**Weekly metrics:**
```
Week of Dec 3, 2024

Tests Run: 50
Tests Passed: 42 (84%)
Tests Failed: 8 (16%)

Average Scores:
- Accuracy: 4.2
- Relevance: 4.5
- Clarity: 4.0
- Politeness: 4.1
- Overall: 4.2

Trend: ↗ +2% from last week
```

### Simple Tracking Spreadsheet

Create a simple sheet to track weekly:

<table>
<tbody>
<tr>
<td><strong>Week</strong></td>
<td><strong>Pass Rate</strong></td>
<td><strong>Avg Accuracy</strong></td>
<td><strong>Avg Clarity</strong></td>
<td><strong>Notes</strong></td>
</tr>
<tr>
<td>Dec 3</td>
<td>84%</td>
<td>4.2</td>
<td>4.0</td>
<td>Fixed balance formatting</td>
</tr>
<tr>
<td>Nov 26</td>
<td>82%</td>
<td>4.2</td>
<td>3.9</td>
<td>Added 5 new tests</td>
</tr>
<tr>
<td>Nov 19</td>
<td>86%</td>
<td>4.1</td>
<td>4.0</td>
<td>Improved instructions</td>
</tr>
<tr>
<td>Nov 12</td>
<td>78%</td>
<td>4.0</td>
<td>3.8</td>
<td>Initial baseline</td>
</tr>
</tbody>
</table>

**Look for:**
- ↗ Upward trends (good!)
- ↘ Downward trends (investigate!)
- 📊 Sudden changes (what changed?)
- 📈 Consistent improvement (great!)

### Trend Analysis

**Questions to ask:**
- Are we improving over time?
- Which criteria improve fastest?
- Which criteria remain problematic?
- Do certain test suites consistently fail more?
- What changes correlate with improvements?

---

## Maintaining Your Test Suite

### Regular Maintenance Schedule

#### Weekly (Quick Review)
```
Time: 15 minutes

Tasks:
✓ Review any new failures
✓ Update tests affected by recent changes
✓ Disable broken tests temporarily (with notes)
✓ Re-enable fixed tests
```

#### Monthly (Deep Review)
```
Time: 1-2 hours

Tasks:
✓ Review all test cases for relevance
✓ Update expected answers if business rules changed
✓ Remove obsolete tests
✓ Add tests for new features
✓ Adjust thresholds based on trends
✓ Reorganize suites if needed
✓ Review disabled tests (fix or remove)
```

#### Quarterly (Strategic Review)
```
Time: Half day

Tasks:
✓ Analyze trends over quarter
✓ Identify systematic issues
✓ Evaluate overall test coverage
✓ Plan improvements for next quarter
✓ Update documentation
✓ Team retrospective
```

### Signs Tests Need Updates

**Update tests when:**
- Business rules change (new policies, rates, processes)
- New features are added
- Features are deprecated or removed
- Customer questions change (new common questions)
- Expected answers no longer reflect current standards
- Thresholds are consistently too easy or too hard

**Example:**
```
Business Change: Bank changes overdraft fee from $35 to $25

Impact: Update tests that ask about overdraft fees
Action:
- Find affected tests (search for "overdraft")
- Update expected answers ($25 instead of $35)
- Re-run to verify
- Document the change
```

### Removing Obsolete Tests

**Remove tests when:**
- Feature no longer exists
- Test hasn't run in 6+ months
- Test is duplicate of another test
- Scenario is no longer relevant
- Test is permanently broken and unfixable

**Don't remove:**
- Temporarily failing tests (fix them instead)
- Tests that fail due to agent issues (fix agent)
- Tests covering important edge cases (even if rare)

---

## Dealing with Common Issues

### Issue: Tests Are Too Slow

**Symptoms:**
- Tests take 2+ minutes each
- Full suite takes hours
- Team avoids running tests due to time

**Solutions:**
- Reduce questions per test (split long tests)
- Disable low-priority tests temporarily
- Run smaller suites more frequently
- Use faster test customer data (less complex)

---

### Issue: Tests Fail Randomly

**Symptoms:**
- Same test passes then fails then passes
- No changes between runs
- Inconsistent scores

**Solutions:**
- Check if threshold is too close to typical performance
- Investigate agent non-determinism
- Review if test depends on changing external data
- Consider adjusting threshold slightly
- Add more specific expected answers

---

### Issue: Too Many Tests to Manage

**Symptoms:**
- 200+ tests, unclear which matter
- Don't know which tests to run when
- Overwhelming to review results
- Duplicates everywhere

**Solutions:**
- Organize into clear suites by priority
- Disable low-priority tests
- Remove duplicates
- Focus on core critical path (20 tests)
- Run comprehensive suite weekly, not daily

---

### Issue: Don't Know What Threshold to Set

**Symptoms:**
- Guessing at appropriate thresholds
- Tests all pass or all fail
- Unsure if standards are right

**Solutions:**
1. Start with 4.0 for everything
2. Run tests and review actual scores
3. If agent consistently scores 4.3, threshold is appropriate
4. If agent consistently scores 3.5, either fix agent or lower threshold
5. Adjust based on real data, not guesses

---

### Issue: Expected Answers Keep Changing

**Symptoms:**
- Constantly updating expected answers
- Requirements are unclear
- Different people have different expectations

**Solutions:**
- Document business rules clearly
- Get stakeholder agreement on standards
- Write expected answers referencing policies
- Create templates for common answer types
- Version control your test suite

---

## Team Collaboration

### Sharing Results

**Weekly team email:**
```
Subject: Evaluation Results - Week of Dec 3

Summary:
✓ 84% pass rate (42/50 tests)
↗ +2% improvement from last week
⚠ 2 new failures (see below)

Highlights:
- Balance inquiry tests all passing
- Improved clarity scores (+0.3)

Issues:
- Transfer tests failing accuracy (investigating)
- One test disabled (bug #123)

Action Items:
- Review transfer logic (assigned: John)
- Update loan rate tests for new rates (assigned: Sarah)

Full results: [link]
```

### Dividing Ownership

**Example ownership model:**
```
Banking_Accounts → Customer Service Team
Banking_Loans → Lending Team
Banking_Cards → Card Services Team
Edge_Cases → QA Team
```

**Owner responsibilities:**
- Maintain tests in their suite
- Review failures promptly
- Update tests when features change
- Add new tests as needed
- Report trends and issues

### Documentation

**Document these decisions:**

**Why is this test disabled?**
```
Test: Complex_Transfer
Status: Disabled
Reason: Blocked by bug #123 - transfer calculation error
Ticket: JIRA-123
Expected Fix: Dec 15
Owner: John Smith
```

**Why did we set this threshold?**
```
Test: Balance_Inquiry
Accuracy Threshold: 4.5 (MIN)
Reason: Balance info is critical financial data, must be highly accurate
Set by: Product team
Date: Nov 1, 2024
```

**What does this custom criterion measure?**
```
Criterion: Regulatory_Compliance
Measures: Ensures required disclaimers and disclosures are present
Required by: Legal team
Examples: FDIC notice, interest rate disclosures
Threshold: 5.0 (must be perfect)
```

---

## Tips for Success

### 1. Be Patient

**Reality:**
- Building good test coverage takes months
- Agent quality improves gradually
- Learning the tool takes time

**Approach:**
- Start small, grow steadily
- Celebrate small wins
- Accept that failures are learning opportunities

### 2. Be Consistent

**Make testing a habit:**
- Run tests on a schedule
- Review results promptly
- Don't skip when busy
- Make it part of your workflow

**Consistency > Intensity**
Better to run 20 tests weekly than 100 tests once a quarter.

### 3. Be Pragmatic

**Not everything needs to score 5.0:**
- 4.0-4.5 is good quality
- Some variation is normal
- Focus on preventing serious issues
- Perfection is unrealistic

**Pragmatic priorities:**
1. No critical errors (wrong $ amounts, wrong data)
2. Answers relevant questions
3. Clear enough to understand
4. Professional tone

### 4. Be Curious

**When tests fail, ask why:**
- What does this tell us about the agent?
- Is this an agent problem or a test problem?
- Are there patterns across failures?
- What can we learn?

**Use failures as learning opportunities**, not just things to fix.

### 5. Be Proactive

**Don't wait for problems:**
- Add tests before issues occur
- Think about what could go wrong
- Test scenarios customers will try
- Anticipate edge cases

**Better to find issues in testing than from customer complaints.**

---

## Measuring Success

### What Does Success Look Like?

**Month 1:**
- ✓ 15-20 tests created and running
- ✓ Tests execute successfully
- ✓ Team understands results
- ✓ Fixed 2-3 issues found by tests

**Month 3:**
- ✓ 40-50 tests covering core scenarios
- ✓ Tests run weekly automatically
- ✓ Pass rate improving (75% → 85%)
- ✓ Tests prevent deployment of broken changes

**Month 6:**
- ✓ 80-100 tests with comprehensive coverage
- ✓ Organized suite structure
- ✓ Consistent 85%+ pass rate
- ✓ Tests are trusted by team
- ✓ Quality trends are tracked
- ✓ Testing is part of standard workflow

**Month 12:**
- ✓ 100+ tests covering all major scenarios
- ✓ 90%+ pass rate
- ✓ Agents consistently score 4.2+
- ✓ New features include tests from day 1
- ✓ Testing program is mature and self-sustaining

### Avoid These Traps

**Trap 1: Chasing 100% Pass Rate**
- Some failures are okay
- Focus on the right tests passing
- Learn from failures

**Trap 2: Testing Too Much**
- More tests ≠ better quality
- Focus on valuable tests
- Quality > Quantity

**Trap 3: Testing Too Little**
- Don't skip testing when busy
- Tests save time by catching issues early
- Consistency matters

**Trap 4: Not Updating Tests**
- Stale tests become useless
- Keep tests aligned with current behavior
- Remove obsolete tests

**Trap 5: Ignoring Trends**
- Individual results matter less than trends
- Track improvement over time
- Use data to drive decisions

---

## Getting Help

### When You're Stuck

**Common situations:**

**"I don't know what tests to create"**
→ Start with the 5 most common customer questions
→ Use auto-generation to get ideas
→ Ask customer service team what they get asked

**"My tests keep failing and I don't know why"**
→ Review the evaluation explanations
→ Compare expected vs actual answers
→ Check if agent has data access
→ Ask: Is the agent wrong or is my expectation wrong?

**"I have 200 tests and can't manage them"**
→ Organize into suites
→ Disable low-priority tests
→ Focus on 20 critical tests
→ Remove duplicates

**"Results are inconsistent"**
→ Check if thresholds are too tight
→ Review agent configuration
→ Test with stable data
→ Consider adjusting expectations

### Resources

- **User Guide**: Review earlier articles for detailed instructions
- **Team**: Ask colleagues who have used the system
- **Documentation**: Check system documentation for technical details
- **Support**: Contact your system administrator or support team

---

## Summary: The Evaluation Program Lifecycle

```
1. START
   ↓
   Create 5-10 core tests
   ↓
2. LEARN
   ↓
   Run tests, understand results, fix issues
   ↓
3. GROW
   ↓
   Add more tests, organize into suites, establish rhythm
   ↓
4. OPTIMIZE
   ↓
   Track trends, raise standards, automate
   ↓
5. MAINTAIN
   ↓
   Regular updates, continuous improvement, team collaboration
   ↓
6. MATURE
   ↓
   Self-sustaining program, high quality, trusted by team
```

---

## Final Thoughts

**Remember:**
- The goal is not perfect scores, but consistent quality
- Tests are a tool to improve, not just to judge
- Failures are learning opportunities
- Progress takes time
- Consistency matters more than intensity

**The evaluation framework helps you:**
- Build confidence in your AI agent
- Catch issues before customers do
- Track improvement over time
- Maintain quality standards
- Sleep better at night knowing your agent is tested

**Start small, be consistent, and improve gradually.**

You've got this! 🎯

---

## Related Articles

- [Evaluation Framework Introduction](./00_intro_to_evaluation.md) - Why testing matters
- [Capabilities Overview](./02_capabilities_overview.md) - What the framework can do
- [Creating Your First Test](./02_creating_your_first_test.md) - Get started
- [Quality Criteria and Thresholds](./03_quality_criteria.md) - Set standards
- [Running Tests and Results](./04-running-tests-and-results.md) - Execute and interpret
- [Building Effective Tests](./05-building-effective-tests.md) - Best practices
- [Organizing Test Suites](./06-organizing-test-suites.md) - Structure your tests
