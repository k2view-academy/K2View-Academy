# Running Tests and Understanding Results

## Three Ways to Run Tests

### Method 1: Run a Single Test (Interactive)

**Best for:** Testing after creating a new test, debugging specific scenarios, demonstrations

**Steps:**

1. **Navigate** to the Evaluation interface (`/static/aifusion/evaluation`)

2. **Browse** the test suite tree in the left panel

3. **Click** on a specific test to select it

4. **Click** the **"Execute"** button

5. **Watch** real-time progress as:
   - Test loads
   - Each question is processed
   - Agent generates responses
   - Evaluation occurs

6. **View results** immediately when complete

**Time:** Usually 30-60 seconds per test depending on conversation length

> **Note:** *Screenshot needed:* Open `/static/aifusion/evaluation`, select a test. Capture the test detail view with the Execute/Run button visible. Save as `images/evaluation_single_test_execute.png`

<img src="images/evaluation_single_test_execute.png" alt="Test selected with Execute button" style="zoom:80%;" />

---

### Method 2: Run an Entire Test Suite (Batch)

**Best for:** Comprehensive regression testing, scheduled testing, pre-deployment validation

**Steps:**

1. **Navigate** to the Pipeline menu in your K2view Fabric interface

2. **Select:** `aifusion.evaluatorPipeline`

3. **Execute** the pipeline

4. **Pipeline executes:**
   - All enabled tests in all suites
   - Tests run sequentially
   - Progress tracked in pipeline interface

5. **View summary** when complete showing:
   - Total tests run
   - Pass/fail count
   - Individual results available for review

**Time:** Depends on number of tests (typically 5-10 minutes for 50-100 tests)

**Note:** Disabled tests are automatically skipped.

> **Note:** *Screenshot needed:* Open Fabric Studio pipeline panel, navigate to `aifusion.evaluatorPipeline`. Capture the pipeline execution view. Save as `images/evaluation_batch_pipeline.png`

<img src="images/evaluation_batch_pipeline.png" alt="Batch pipeline execution view" style="zoom:80%;" />

---

### Method 3: Scheduled Automated Testing

**Best for:** Continuous monitoring, nightly regression, CI/CD integration

**How it works:**
- Configure pipeline to run on schedule
- Tests execute automatically
- Results logged to database
- Alerts sent on failures (if configured)

**Typical schedules:**
- **Nightly:** Full test suite
- **After deployments:** Smoke tests
- **Weekly:** Comprehensive regression

---

## Understanding Test Execution

### What Happens During Execution?

1. **Load Test Configuration**
   - Retrieves test case from test suite
   - Reads questions, expected answers, criteria, thresholds

2. **Prepare Environment**
   - Loads customer data (using specified IID)
   - Initializes agent with correct application configuration

3. **Execute Conversation**
   - Sends each question to the agent
   - Agent processes using real customer data
   - Collects actual responses

4. **Evaluate Responses**
   - Evaluator AI reviews each response
   - Compares actual vs. expected
   - Scores against each criterion (Accuracy, Relevance, etc.)
   - Provides explanation for each score

5. **Calculate Results**
   - Aggregates scores across conversation
   - Checks against thresholds (MIN and AVERAGE)
   - Determines PASS or FAIL

6. **Store Results**
   - Saves to evaluation database
   - Generates unique session ID
   - Creates viewable conversation link

---

## Viewing Results - The Results Interface

When a test completes, you'll see the results interface with several sections:

### Top Summary Bar

> **Note:** *Screenshot needed:* Open `/static/aifusion/evaluation` with a completed test execution. Capture the top summary bar showing PASS/FAIL status, overall score, duration, and session ID. Save as `images/evaluation_results_summary_bar.png`

<img src="images/evaluation_results_summary_bar.png" alt="Results summary bar" style="zoom:80%;" />

```
✅ PASSED  |  Overall Score: 4.3/5.0  |  Duration: 47s  |  Session: abc-123-def
```

Or:

```
❌ FAILED  |  Overall Score: 3.8/5.0  |  Duration: 52s  |  Session: xyz-789-uvw
```

**What it shows:**
- **Status:** PASSED (green) or FAILED (red)
- **Overall Score:** Average across all criteria and all responses
- **Duration:** How long the test took
- **Session ID:** Unique identifier for this test run (useful for tracking and comparing)

---

### Conversation View (Main Panel)

> **Note:** *Screenshot needed:* Open a completed test result. Capture the conversation view showing multiple turns with green/yellow/red color indicators and score badges. Save as `images/evaluation_conversation_results.png`

<img src="images/evaluation_conversation_results.png" alt="Color-coded conversation view with score indicators" style="zoom:80%;" />

Shows the full conversation with color-coded quality indicators:

```
🟢 Turn 1 - Score: 4.5

Question: What are my account balances?

Expected: List all accounts with current balances and as-of date

Agent Response:
You have three accounts:
• Checking (1234): $1,234.56
• Savings (5678): $5,678.90
• Credit Card (9012): -$456.78 (balance owed)
As of today, December 3rd.

Scores:
• Accuracy: 5.0/5.0 ✓
• Relevance: 5.0/5.0 ✓
• Clarity: 4.5/5.0 ✓
• Politeness: 4.0/5.0 ✓

---

🟡 Turn 2 - Score: 3.9

Question: Which account has the most money?

Expected: Identify specific account name and balance amount

Agent Response:
Your savings account has the most money.

Scores:
• Accuracy: 3.5/5.0 ⚠ - Did not include the specific balance amount
• Relevance: 5.0/5.0 ✓
• Clarity: 4.0/5.0 ✓
• Politeness: 3.5/5.0 ✓
```

**Color coding:**
- 🟢 **Green** = All scores meet thresholds
- 🟡 **Yellow** = Borderline (within 0.3 of threshold)
- 🔴 **Red** = Failed (below threshold)

---

### Score Breakdown Table

> **Note:** *Screenshot needed:* Capture the score breakdown table showing Criterion / Average / MIN / Threshold / Status columns for a test result. Save as `images/evaluation_score_breakdown_table.png`

<img src="images/evaluation_score_breakdown_table.png" alt="Score breakdown table" style="zoom:80%;" />

Shows aggregated statistics across the entire conversation:

<table>
<tbody>
<tr>
<td><strong>Criterion</strong></td>
<td><strong>Average</strong></td>
<td><strong>MIN</strong></td>
<td><strong>Threshold</strong></td>
<td><strong>Status</strong></td>
</tr>
<tr>
<td>Accuracy</td>
<td>4.3</td>
<td>3.5</td>
<td>MIN 4.0</td>
<td>❌ FAIL</td>
</tr>
<tr>
<td>Relevance</td>
<td>5.0</td>
<td>5.0</td>
<td>MIN 4.0</td>
<td>✅ PASS</td>
</tr>
<tr>
<td>Clarity</td>
<td>4.2</td>
<td>4.0</td>
<td>AVG 4.0</td>
<td>✅ PASS</td>
</tr>
<tr>
<td>Politeness</td>
<td>3.8</td>
<td>3.5</td>
<td>AVG 3.5</td>
<td>✅ PASS</td>
</tr>
</tbody>
</table>

**Reading this table:**
- **Average:** Mean score across all conversation turns
- **MIN:** Lowest score in any turn
- **Threshold:** Your configured pass/fail criteria
- **Status:** Whether this criterion passed or failed

In this example, the test FAILED because Accuracy MIN (3.5) is below the threshold (4.0).

---

### Detailed Explanations

For each score, you'll see an explanation of why that score was given:

**Example:**

> **Accuracy: 3.5/5.0**
>
> The response correctly identified that the savings account has the highest balance, which is accurate. However, the expected answer specified that the agent should include the specific balance amount ($5,678.90), which was missing from the response. While the information provided was correct, it was incomplete based on the requirements.

**What makes good explanations:**
- Specific references to what was right
- Clear identification of what was missing or wrong
- Comparison to expected answer
- Objective reasoning

---

## Interpreting Pass/Fail

### When Does a Test Pass? ✅

A test passes if **ALL** of the following are true:

1. **Every MIN threshold is met** in every response
   - Example: If Accuracy MIN is 4.0, every single response must score ≥ 4.0

2. **Every AVERAGE threshold is met** across the conversation
   - Example: If Clarity AVG is 4.0, the mean clarity score must be ≥ 4.0

3. **No technical errors** occurred during execution
   - Agent successfully processed all questions
   - Evaluation completed without errors

### When Does a Test Fail? ❌

A test fails if **ANY** of the following occur:

1. **Any MIN threshold is violated** in any response
   - Even one response scoring 3.9 when MIN is 4.0 causes failure
   - All other turns can be perfect - one failure fails the test

2. **Any AVERAGE threshold is not met**
   - Overall average falls below the threshold
   - Even if no individual response is terrible

3. **Technical execution errors**
   - Agent crashed or timed out
   - Data not available
   - System errors

### Understanding "Borderline" Results 🟡

Results shown in yellow are technically passing but close to threshold (within 0.3 points):

- Threshold 4.0, Score 4.2 = Borderline (yellow)
- Threshold 4.0, Score 4.5 = Solid pass (green)

**What to do about borderline results:**
- Monitor these tests closely
- Small agent changes could push them to fail
- Consider improving agent or adjusting expectations

---

## What to Do When Tests Fail

### Step 1: Identify the Failure Type

**Accuracy failure:**
- Most serious - incorrect information
- Top priority to fix

**Relevance failure:**
- Agent misunderstood the question
- May need better question understanding

**Clarity failure:**
- Information is correct but poorly presented
- Focus on response formatting

**Politeness failure:**
- Tone issues
- Usually lower priority unless severe

### Step 2: Review the Specific Failures

Look at each failed response:
- Read the question
- Read expected answer
- Read actual response
- Read evaluation explanation
- Identify the gap

### Step 3: Determine Root Cause

**Common causes and solutions:**

<table>
<tbody>
<tr>
<td><strong>Cause</strong></td>
<td><strong>Solution</strong></td>
</tr>
<tr>
<td>Agent lacks data access</td>
<td>Fix data configuration/permissions</td>
</tr>
<tr>
<td>Agent misunderstands question</td>
<td>Improve agent instructions/examples</td>
</tr>
<tr>
<td>Expected answer too strict</td>
<td>Adjust expected answer to be realistic</td>
</tr>
<tr>
<td>Threshold too high</td>
<td>Lower threshold to appropriate level</td>
</tr>
<tr>
<td>Agent needs more context</td>
<td>Provide better system instructions</td>
</tr>
<tr>
<td>Bug in agent logic</td>
<td>Fix the bug in agent configuration</td>
</tr>
</tbody>
</table>

### Step 4: Take Action

**Fix the agent:**
- Update instructions
- Fix data access
- Improve logic

**Fix the test:**
- Adjust unrealistic expected answers
- Lower overly strict thresholds
- Clarify ambiguous questions

**Document:**
- Note what you changed
- Why the test was failing
- What you did to fix it

### Step 5: Re-run and Verify

- Run the test again
- Verify it now passes
- Run related tests to ensure no side effects

---

## Tracking Trends Over Time

### Why Track Trends?

Individual test results are useful, but trends tell the real story:
- Is quality improving?
- Are changes making things better or worse?
- Which areas are most problematic?

### What to Track

**Weekly Metrics:**
- Overall pass rate (e.g., 85% of tests passing)
- Average score per criterion
- Number of failures by criterion type
- Most frequently failing tests

**Month-over-Month:**
- Improvement trends
- New failure patterns
- Impact of major changes

### Simple Tracking Method

Create a simple spreadsheet:

<table>
<tbody>
<tr>
<td><strong>Week</strong></td>
<td><strong>Tests Run</strong></td>
<td><strong>Pass Rate</strong></td>
<td><strong>Avg Accuracy</strong></td>
<td><strong>Avg Clarity</strong></td>
<td><strong>Notes</strong></td>
</tr>
<tr>
<td>W1</td>
<td>50</td>
<td>78%</td>
<td>4.1</td>
<td>4.0</td>
<td>Initial baseline</td>
</tr>
<tr>
<td>W2</td>
<td>50</td>
<td>82%</td>
<td>4.2</td>
<td>4.1</td>
<td>Improved instructions</td>
</tr>
<tr>
<td>W3</td>
<td>50</td>
<td>80%</td>
<td>4.3</td>
<td>3.9</td>
<td>Clarity regression</td>
</tr>
<tr>
<td>W4</td>
<td>50</td>
<td>88%</td>
<td>4.4</td>
<td>4.2</td>
<td>Fixed formatting</td>
</tr>
</tbody>
</table>

**Look for:**
- ↗️ Upward trends (good!)
- ↘️ Downward trends (investigate!)
- 📊 Sudden changes (what changed?)

---

## Comparing Test Runs

### Comparing Before and After Changes

When you make agent changes, compare results:

**Before Change:**
- Session ID: abc-123
- Pass Rate: 75%
- Avg Accuracy: 4.0

**After Change:**
- Session ID: def-456
- Pass Rate: 88%
- Avg Accuracy: 4.3

**Conclusion:** Change improved performance ✅

### Comparing Different Customers

Run same test with different customer IDs:

**Customer 1:**
- More account types
- Complex history
- Score: 4.5

**Customer 2:**
- Simple accounts
- Less data
- Score: 3.8

**Insight:** Agent struggles with simple cases - investigate why.

---

## Result Access Methods

### Method 1: Via Web Interface (Primary)

- Most user-friendly
- Full visualization
- Color-coded results
- Interactive exploration

**URL format:** `/static/aifusion/evaluation/?sessionid=<session-id>`

### Method 2: Via Results History

- Browse past test executions
- Sort by date, status, score
- Filter by test suite
- Compare multiple runs

### Method 3: Export Results

- Export test cases as JSON
- Export test cases with data snapshots as ZIP
- Share with stakeholders or import into other environments

---

## Tips for Effective Result Review

### 1. Review Results Immediately

Don't let results pile up. Review within 24 hours of test execution.

### 2. Focus on Patterns

One failure might be a fluke. Multiple similar failures indicate a real issue.

### 3. Read the Explanations

Don't just look at scores - understand WHY scores were given.

### 4. Test Your Assumptions

If a test fails but you think the agent is right, review your expected answer.

### 5. Celebrate Improvements

When scores improve, acknowledge it! Quality improvement is progress.

### 6. Share Results

Make results visible to your team. Transparency drives quality.

---

## Common Questions

**Q: The same test sometimes passes and sometimes fails. Why?**

A: This usually means:
- Score is right at the threshold boundary
- Agent has some non-deterministic behavior
- Test depends on timing or external factors

**Solution:** Either improve consistency or adjust threshold slightly.

---

**Q: All my tests are failing. What do I do?**

A: Don't panic! This is common when first starting:
1. Review a few failures in detail
2. Check if thresholds are realistic
3. Verify agent has proper data access
4. Consider starting with lower thresholds and improving gradually

---

**Q: Should I fix the agent or adjust the test?**

A: Ask yourself:
- Is the agent's response actually good enough for real customers?
  - Yes → Adjust test (expected answer or threshold)
  - No → Fix agent

---

**Q: How often should I run tests?**

A:
- **After creating new tests:** Immediately
- **During development:** Daily or after changes
- **In production:** At least weekly
- **Before deployments:** Always

---

## What's Next?

Now that you understand how to run tests and interpret results, you're ready to:

**Next Article:** [Building Effective Test Conversations](./05-building-effective-tests.md)

**Related Articles:**
- [Understanding Quality Criteria](./03_quality_criteria.md) - Deep dive into evaluation dimensions
- [Organizing Test Suites](./06-organizing-test-suites.md) - Managing multiple tests
- [Best Practices](./07-best-practices.md) - Advanced tips for testing success
