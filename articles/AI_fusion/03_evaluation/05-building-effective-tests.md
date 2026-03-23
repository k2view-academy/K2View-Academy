# Building Effective Test Conversations

## What Makes a Good Test?

A good test is:
- **Realistic** - Reflects actual customer interactions
- **Clear** - Success criteria are unambiguous
- **Focused** - Tests one scenario at a time
- **Maintainable** - Easy to update as things change
- **Valuable** - Catches real problems

A bad test is:
- Unrealistic scenarios that would never happen
- Vague expected answers that could mean anything
- Tests multiple unrelated things at once
- Brittle and breaks with minor changes
- Tests trivial scenarios that don't matter

---

## Principle 1: Use Real Customer Language

### Bad Examples ❌

```
Query: Aggregate account balance summation across fiscal periods
```
```
Request: Initiate funds transfer transaction between accounts
```

### Good Examples ✅

```
Question: How much money do I have in all my accounts?
```
```
Question: Can I move $500 from my savings to my checking?
```

**Why:** Real customers don't talk like database queries or API documentation. Use natural, conversational language.

### Tips for Natural Language

**Include common variations:**
- "What's my balance?" (casual)
- "Could you tell me my account balance please?" (formal)
- "How much money do I have?" (informal)

**Include real customer quirks:**
- Typos: "What's my ballance?"
- Informal: "Got any money in my account?"
- Vague: "How am I doing?" (when asking about finances)

**Test different phrasings:**
- Direct: "What's my credit card balance?"
- Indirect: "Do I owe anything on my credit card?"
- Contextual: "What do I need to pay this month?"

---

## Principle 2: Test Complete Interactions

### Single-Turn Tests (Okay for Simple Cases)

```
Q: What's my checking account balance?
Expected: Provide exact balance with account number and date
```

**Use when:**
- Testing simple lookup queries
- Validating basic functionality
- Quick smoke tests

### Multi-Turn Tests (Better - Tests Context)

```
Q1: What's my checking account balance?
Expected: Provide exact balance with account number

Q2: And my savings?
Expected: Understand context and provide savings balance

Q3: Which one has more money?
Expected: Compare both balances and identify the higher one
```

**Use when:**
- Testing conversation flow
- Validating context retention
- Simulating realistic customer interactions

**Why multi-turn is better:**
- Real customers ask follow-ups
- Tests if agent remembers previous context
- Catches issues with pronoun resolution ("which one")
- More realistic scenarios

### Progressive Complexity

Build conversations that naturally progress:

```
Q1: Do I have a savings account?
Expected: Yes/No answer with account number if exists

Q2: What's the balance?
Expected: Provide balance (understands "the" refers to savings)

Q3: When did I open it?
Expected: Provide account opening date

Q4: What interest rate am I getting?
Expected: Provide current APY/interest rate
```

---

## Principle 3: Be Specific About Expected Answers

### Vague Expectations (Poor) ❌

```
Expected: "Agent should provide balance information"
```

**Problems:**
- What counts as "balance information"?
- Just the number? With account details?
- How much detail is required?
- Too open to interpretation

### Specific Expectations (Good) ✅

```
Expected: "Agent should state the exact balance of the checking
          account (e.g., $1,234.56), include the last 4 digits
          of the account number, and mention the as-of date"
```

**Benefits:**
- Clear success criteria
- Evaluator knows what to look for
- Consistent evaluation
- Easy to determine if answer is complete

### How to Be Specific

**Include required elements:**
```
Expected answer must include:
• Exact balance amount
• Account identifier (last 4 digits or account name)
• Currency ($ for USD)
• As-of date or timestamp
• All accounts if multiple exist
```

**Specify format preferences:**
```
Expected: Present balance in currency format ($1,234.56, not 1234.56)
Expected: List accounts separately with bullet points or clear separation
Expected: State date in clear format (December 3, 2024, not 12/3/24)
```

**Define completeness:**
```
Expected: List ALL checking and savings accounts. Do not omit any accounts.
```

### Balance Specificity with Flexibility

Don't be SO specific that you test exact wording:

**Too Specific (Brittle):** ❌
```
Expected: Agent must say "Your checking account number 1234 has
          a balance of $1,234.56 as of December 3rd, 2024."
```

**Appropriately Specific:** ✅
```
Expected: Agent should provide the checking account balance ($1,234.56),
          identify the account (by number or name), and include the
          current date. Exact wording may vary.
```

---

## Principle 4: Test Edge Cases

Don't just test happy paths. Include scenarios that might break:

### Empty/Missing Data

```
Q: What's my retirement account balance?
Expected: If customer has no retirement account, agent should
          clearly state this and optionally mention available options
```

### Ambiguous Questions

```
Q: How much do I owe?
Expected: Agent should ask for clarification (credit card? loan?
          mortgage?) or list all amounts owed across all accounts
```

### Multiple Possible Answers

```
Q: What's my account balance?
Expected: If customer has multiple accounts, agent should either
          ask which account or list all accounts with balances
```

### Unusual Values

```
Q: What's my checking balance?
Expected: If balance is negative (overdraft), agent should clearly
          indicate this is negative/overdrawn, not just show the number
```

### Boundary Conditions

```
Q: Can I withdraw $5,000 from my savings?
Expected: If balance is exactly $5,000, agent should mention that
          this would leave $0 balance and potential minimum balance issues
```

### Error Scenarios

```
Q: What's my balance?
Expected: If system is temporarily unavailable, agent should apologize,
          explain the situation, and offer alternatives or retry timing
```

---

## Principle 5: One Main Scenario Per Test

### Bad Test (Tests Too Much) ❌

```
Test: Complete Banking Inquiry

Q1: What are my account balances?
Q2: What's my credit score?
Q3: Do I have any pending loan applications?
Q4: What's the routing number for wire transfers?
Q5: What are your current mortgage rates?
```

**Problems:**
- Tests 5 different features
- When it fails, unclear which feature broke
- Hard to maintain
- Difficult to debug

### Good Approach (Separate Tests) ✅

**Test 1: Account Balance Inquiry**
```
Q1: What are my account balances?
Q2: Which account has the most money?
```

**Test 2: Credit Score Check**
```
Q1: What's my credit score?
Q2: When was it last updated?
```

**Test 3: Loan Application Status**
```
Q1: Do I have any pending loan applications?
Q2: What's the status of my mortgage application?
```

**Benefits:**
- Clear focus per test
- Easy to identify failures
- Simple to maintain
- Run tests independently

### When to Combine Questions

It's okay to have multiple questions in one test if they're **part of the same user journey:**

**Good combined test:**
```
Test: Account Balance Inquiry with Follow-up

Q1: What are my account balances?
Q2: Which account has the highest balance?
Q3: Can I transfer $500 from that account to my checking?
```

**Why this works:**
- Single user journey (checking balances → comparing → taking action)
- Questions build on each other
- Tests context retention
- Represents realistic flow

---

## Test Case Templates by Domain

### Banking Templates

#### Template: Balance Inquiry
```
Q1: What are my current account balances?
Expected: List all accounts with current balances and as-of date

Q2: Which account has the most money?
Expected: Identify specific account and balance amount

Criteria: Accuracy MIN 4.5, Relevance MIN 4.0, Clarity AVG 4.0
```

#### Template: Transaction History
```
Q1: What were my recent transactions on my credit card?
Expected: List recent transactions (date, merchant, amount) for
          last 5-10 transactions

Q2: Did I make a purchase at [specific merchant]?
Expected: Search transaction history and confirm yes/no with details

Criteria: Accuracy MIN 4.0, Relevance MIN 4.5, Clarity AVG 4.0
```

#### Template: Loan Information
```
Q1: What's my current loan balance?
Expected: Provide outstanding principal balance, identify loan type

Q2: When is my next payment due?
Expected: Provide next payment date and amount

Q3: How much interest will I pay over the life of the loan?
Expected: Provide total interest amount (if available in system)

Criteria: Accuracy MIN 4.5, Relevance MIN 4.0, Clarity AVG 4.0
```

### Telecom Templates

#### Template: Billing Inquiry
```
Q1: Why is my bill higher this month?
Expected: Compare current bill to previous month, identify key
          differences (overage charges, new services, rate changes)

Q2: Can you show me the breakdown?
Expected: Detailed line items: service charges, usage charges,
          taxes, fees

Criteria: Accuracy MIN 4.0, Relevance MIN 4.5, Clarity MIN 4.0
```

#### Template: Service Usage
```
Q1: How much data have I used this month?
Expected: Current data usage with total allowed/remaining

Q2: Am I going to go over my limit?
Expected: Project whether customer will exceed limit based on
          current usage patterns

Criteria: Accuracy MIN 4.5, Relevance MIN 4.0, Clarity AVG 4.0
```

### HR Templates

#### Template: Benefits Inquiry
```
Q1: How many vacation days do I have left?
Expected: Specific number of remaining days, mention accrual rate

Q2: When do I get more days?
Expected: Next accrual date and amount

Q3: Do my days expire?
Expected: Explain expiration policy if applicable

Criteria: Accuracy MIN 4.5, Relevance MIN 4.0, Clarity AVG 4.0
```

#### Template: Payroll Question
```
Q1: When is my next paycheck?
Expected: Specific date of next pay period

Q2: How much will it be?
Expected: Gross amount or net amount (with explanation of which)

Criteria: Accuracy MIN 4.5, Relevance MIN 4.0, Clarity AVG 4.0
```

---

## How Many Questions Per Test?

### Guidelines

- **Simple lookup**: 1-2 questions
- **Moderate complexity**: 3-5 questions
- **Complex interaction**: 6-10 questions
- **Maximum recommended**: 15 questions

### Signs You Have Too Many Questions

- Test takes more than 2 minutes to run
- Test covers multiple distinct scenarios
- Hard to summarize what the test is verifying
- Failures are difficult to debug

**Solution:** Split into multiple focused tests

### Signs You Have Too Few Questions

- Test feels incomplete
- Doesn't verify the full scenario
- Only tests surface level, not depth
- Missing obvious follow-up questions

**Solution:** Add natural follow-up questions

---

## Writing Good Test Descriptions

Every test should have a clear description explaining what it validates.

### Poor Descriptions ❌

- "Test 1"
- "Banking test"
- "Checking accounts"

### Good Descriptions ✅

- "Verify agent correctly retrieves and displays all account balances with proper formatting"
- "Test agent's ability to handle ambiguous balance inquiries when customer has multiple accounts"
- "Validate agent provides complete loan information including balance, due date, and interest"

### Description Template

```
Test: [Scenario Name]

Description: Verify that the agent [specific capability being tested]
when the customer [specific situation]. Agent should [expected behavior].

Example:
Test: Multiple Account Balance Comparison

Description: Verify that the agent can retrieve balances for multiple
accounts and correctly identify which account has the highest balance
when customer asks a comparison question. Agent should maintain context
across multiple turns.
```

---

## Testing Persona Variations

Test how your agent handles different customer types:

### Confused Customer
```
Q1: Um, I need to know about money stuff?
Expected: Agent asks clarifying questions to understand intent

Q2: Like how much I have?
Expected: Agent confirms understanding and provides balance information
```

### Expert Customer
```
Q1: What's my current DTI ratio based on reported liabilities?
Expected: Agent either calculates debt-to-income ratio or explains
          that this specific metric isn't available
```

### Frustrated Customer
```
Q1: This is the third time I'm asking - WHY is my bill so high?!
Expected: Agent acknowledges frustration, apologizes, and provides
          clear explanation with specific details
```

### Non-Native Speaker
```
Q1: I want know how many money in account?
Expected: Agent understands despite grammatical issues and provides
          balance information clearly
```

---

## Best Practices Summary

### DO ✅

- Use natural customer language
- Test multi-turn conversations
- Be specific about expected answers
- Include edge cases and error scenarios
- Focus each test on one main scenario
- Write clear test descriptions
- Test different customer personas
- Update tests when requirements change

### DON'T ❌

- Use technical jargon in questions
- Only test happy paths
- Write vague expected answers
- Combine unrelated scenarios in one test
- Test exact wording requirements
- Create brittle tests that break easily
- Ignore edge cases
- Let tests become outdated

---

## Common Mistakes to Avoid

### Mistake 1: Testing What Agent Says, Not What It Knows

**Wrong:**
```
Expected: Agent must say "Hello! I'd be happy to help you with that."
```

**Right:**
```
Expected: Agent should greet customer politely and acknowledge the request
```

### Mistake 2: Unrealistic Expectations

**Wrong:**
```
Expected: Agent should provide balance, transaction history, credit score,
          loan details, and investment portfolio summary all in one response
```

**Right:**
```
Expected: Agent should provide balance and offer to show additional details
```

### Mistake 3: Testing System Capabilities, Not Agent Quality

**Wrong:**
```
Q: What's the weather in New York?
Expected: Provide current weather
```
(Unless your agent is supposed to handle weather queries)

**Right:**
```
Q: What's the weather in New York?
Expected: Agent should politely explain that weather info isn't available
          and redirect to banking services
```

### Mistake 4: Overly Strict Thresholds

**Wrong:**
All criteria set to MIN 5.0

**Right:**
Critical criteria (Accuracy) at MIN 4.0-4.5, others at AVG 4.0

---

## What's Next?

Now that you know how to build effective tests, you're ready to:

**Next Article:** [Organizing Test Suites](./06-organizing-test-suites.md)

**Related Articles:**
- [Creating Your First Test](./02_creating_your_first_test.md) - Apply these principles
- [Understanding Quality Criteria](./03_quality_criteria.md) - Set appropriate thresholds
- [Running Tests and Results](./04-running-tests-and-results.md) - Validate your effective tests
