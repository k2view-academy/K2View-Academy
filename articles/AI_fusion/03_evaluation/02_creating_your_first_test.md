# Creating a Test

## Accessing the Evaluation Interface

1. Open your web browser
2. Navigate to: `/static/aifusion/evaluation`
3. You'll see the Evaluation Dashboard with three main areas:
   - **Left panel**: Test suite tree (your collections of tests)
   - **Center panel**: Test creation and details
   - **Right panel**: Execution and results

## Two Ways to Create Tests

You can create tests in two ways:
1. **Manual Creation** - You write the questions and expected answers
2. **Auto-Generation** - AI creates realistic test conversations for you

Let's start with manual creation to understand the fundamentals, then explore auto-generation.

---

## Method 1: Manual Test Creation

### Step 1: Start a New Test

Click the **"Create New Test"** button in the interface.

### Step 2: Choose Your Application

Select which AI agent you want to test:
- Banking Agent
- Telecom Agent
- HR Agent
- Or other configured applications

This tells the system which agent will answer the questions.

### Step 3: Select a Customer Instance

Enter a **Customer ID** (also called Instance ID or IID).

**Why is this needed?**
Your AI agent uses real customer data to answer questions. The customer ID tells the system which customer's data to use during testing.

**Example:**
Customer ID: `1` might represent "John Smith" with checking and savings accounts.

**Tip:** Use test customers created specifically for testing, not production customer data.

### Step 4: Build the Conversation

Now you'll create the questions and define what good answers should include.

#### Adding Question 1

**Question:** Type what a customer would ask
Example: `"What are my current account balances?"`

**Expected Answer:** Describe what a good response should include
Example: `"The agent should list all accounts (checking, savings, credit cards) with current balances and include the as-of date."`

**Important:** You don't need to write the exact words the agent should say. Instead, describe what information the answer should contain.

#### Adding Question 2 (Optional)

For multi-turn conversations, add follow-up questions:

**Question:** `"Which account has the highest balance?"`

**Expected Answer:** `"The agent should identify the specific account name and state the exact balance amount."`

#### How Many Questions?

- **Simple test**: 1-2 questions
- **Typical test**: 3-5 questions
- **Complex test**: 6-10 questions
- **Maximum**: 15 questions (beyond this, split into multiple tests)

### Step 5: Configure Quality Criteria

Choose what to measure and set minimum acceptable scores.

#### Standard Criteria (Select all that apply):

- **Accuracy** - Is the information correct?
- **Relevance** - Does it answer the question?
- **Clarity** - Is it easy to understand?
- **Politeness** - Is the tone professional?

#### Set Thresholds

For each criterion, set the minimum acceptable score (scale of 0-5):

**Recommended starting values:**
- Accuracy: `4.0` (or `4.5` for critical information)
- Relevance: `4.0`
- Clarity: `4.0`
- Politeness: `3.5`

**Threshold types:**
- **MIN**: Every single response must meet this score
- **AVERAGE**: The overall average must meet this score

**Tip:** Start with thresholds around 4.0. You can adjust them later based on actual performance.

### Step 6: Save Your Test

Give your test a clear, descriptive name:

**Good names:**
- "Account Balance Inquiry"
- "Check Multiple Account Balances"
- "Balance Inquiry with Follow-up"

**Poor names:**
- "Test 1"
- "New Test"
- "Testing"

**Choose a Test Suite:**
Select an existing suite from the dropdown, or type a new name to create one.

Example: Save to suite `"Banking_Tests"`

Click **Save**.

---

## Method 2: Auto-Generated Test Creation

Auto-generation uses AI to create realistic test conversations automatically. This is much faster than manual creation and can discover scenarios you might not think of.

### Step 1: Start Auto-Generation

Click **"Create New Test"**, then select **"Auto-Generate Conversation"**.

### Step 2: Basic Configuration

**Required fields:**

- **Application**: Which AI agent to test (Banking, Telecom, HR)
- **Customer ID**: Which customer data to use (e.g., `1`)
- **Suite Name**: Where to save the test (e.g., `"Banking_Tests"`)

### Step 3: Advanced Options (Optional but Powerful)

#### Focus Area
Narrow the topic the AI should ask about.

**Examples:**
- `"billing issues"`
- `"loan applications"`
- `"account balances"`
- `"credit card inquiries"`

**Leave blank** for general, diverse questions.

#### Persona
Specify what type of customer to simulate.

**Examples:**
- `"confused elderly customer"`
- `"tech-savvy millennial"`
- `"frustrated customer with billing issue"`
- `"new customer unfamiliar with services"`
- `"business customer asking about corporate accounts"`

**Leave blank** for neutral persona.

#### Max Steps
How many question/answer turns to generate (default: 15).

**Recommendations:**
- Short test: 3-5 steps
- Medium test: 6-10 steps
- Long test: 11-15 steps

### Step 4: Generate

Click **"Generate"** and watch in real-time as:
1. AI generates an initial customer question
2. Your agent responds
3. AI asks a follow-up question based on the response
4. Conversation continues naturally
5. Stops at natural conclusion or max steps

**You can stop anytime** and save the current conversation.

### Step 5: Review the Generated Conversation

Look through the generated conversation:

**Check for:**
- ✅ Questions are realistic and natural
- ✅ Conversation flows logically
- ✅ Follow-ups make sense based on previous answers
- ✅ Reaches natural conclusion
- ✅ Tests meaningful scenarios

**Red flags:**
- ❌ Questions are nonsensical or unrealistic
- ❌ Abrupt ending without resolution
- ❌ Repetitive or circular conversation
- ❌ Tests trivial scenarios

### Step 6: Edit if Needed (Optional)

You can edit the generated conversation:
- Modify questions to be clearer
- Adjust expected answers
- Add or remove conversation turns
- Change evaluation criteria

### Step 7: Save the Test

The system auto-generates a name, but you can rename it to something more descriptive.

Click **Save** to add it to your test suite.

---

## Your First Test: Complete Example

Let's walk through a complete example for a banking agent.

### Test Configuration

- **Test Name**: "Account Balance Check with Follow-up"
- **Application**: Banking Agent
- **Customer ID**: 1
- **Suite**: Banking_Tests

### Conversation

**Turn 1:**
- **Question**: "What are my current account balances?"
- **Expected**: "List all accounts with current balances and as-of date"

**Turn 2:**
- **Question**: "Which account has the most money?"
- **Expected**: "Identify specific account name and balance amount"

**Turn 3:**
- **Question**: "Can I transfer $500 from that account?"
- **Expected**: "Confirm sufficient funds and explain transfer process or confirm the action can be done"

### Quality Criteria

- **Accuracy**: MIN 4.0
- **Relevance**: MIN 4.0
- **Clarity**: AVERAGE 4.0
- **Politeness**: AVERAGE 3.5

### Save

Save to suite: `Banking_Tests`

---

## Tips for Success

### Start Simple
Create 1-2 simple tests before building complex multi-turn conversations.

### Use Real Customer Language
Write questions the way real customers would ask them, not formal technical language.

### Be Specific About Expectations
Vague expectations lead to inconsistent evaluation. Be clear about what a complete answer includes.

### Test One Thing at a Time
Each test should focus on one scenario. Don't try to test account balances, loans, and transfers all in one test.

### Use Auto-Generation to Learn
Generate a few tests and review them to see examples of good test structure.

### Iterate
Your first tests won't be perfect. Run them, learn from results, and refine.

---

## What's Next?

Now that you've created your first test, you're ready to:

1. **Run the test** - See how your agent performs (see next article: Running Tests and Understanding Results)
2. **Create more tests** - Build coverage of different scenarios
3. **Organize tests** - Group related tests into suites (see article: Organizing Test Suites)

**Next Article:** [Running Tests and Understanding Results](./04-running-tests-and-results.md)
