# Understanding Quality Criteria and Thresholds

## What Are Quality Criteria?

Quality criteria are the different dimensions used to evaluate your AI agent's responses. Think of them like a report card with different subjects - instead of Math, English, and Science, your agent gets graded on Accuracy, Relevance, Clarity, and Politeness.

Each response your agent gives receives a score (0-5) for each criterion you're measuring.

---

## The Four Standard Quality Criteria

### 1. Accuracy ⭐ (Most Critical)

**What it measures:** Is the information factually correct and complete?

**High Score (4-5) Examples:**
- ✅ Provides exact account balance from database: "$1,234.56"
- ✅ Includes all requested details (account number, balance, date)
- ✅ No factual errors or made-up information
- ✅ Calculations are correct
- ✅ Dates and numbers match customer records exactly

**Low Score (0-2) Examples:**
- ❌ Wrong balance amount
- ❌ Confuses customer's accounts
- ❌ Mentions accounts the customer doesn't have
- ❌ Incorrect dates or calculations
- ❌ Makes up information not in the database

**Medium Score (3) Examples:**
- ⚠️ Correct information but incomplete
- ⚠️ Most details right but one small error
- ⚠️ Doesn't provide full answer requested

**Why it's most important:** Inaccurate information damages customer trust and can cause real problems (wrong payments, missed deadlines, incorrect decisions).

**Recommended threshold:** 4.0 minimum (or 4.5 for critical financial/medical information)

---

### 2. Relevance

**What it measures:** Does the response actually answer the customer's question?

**High Score (4-5) Examples:**
- ✅ Directly addresses what was asked
- ✅ Stays on topic
- ✅ Provides exactly the information needed
- ✅ Doesn't include irrelevant details

**Low Score (0-2) Examples:**
- ❌ Answers a different question
- ❌ Goes off on tangents
- ❌ Provides generic information when specific was requested
- ❌ Talks about unrelated topics

**Medium Score (3) Examples:**
- ⚠️ Partially answers the question
- ⚠️ Includes some irrelevant information
- ⚠️ Addresses the topic but not the specific ask

**Real-world example:**

**Question:** "What's my checking account balance?"

**High Relevance (5):** "Your checking account (ending in 1234) has a balance of $1,234.56 as of today."

**Low Relevance (1):** "We offer many types of accounts including checking, savings, and CDs. Checking accounts are great for everyday transactions..."

**Recommended threshold:** 4.0 minimum

---

### 3. Clarity

**What it measures:** Is the response easy to understand and well-organized?

**High Score (4-5) Examples:**
- ✅ Clear, simple language
- ✅ Well-organized (bullets, sections, logical flow)
- ✅ No jargon or technical terms (unless appropriate for audience)
- ✅ Easy to scan and find key information
- ✅ Proper formatting (numbers, dates, lists)

**Low Score (0-2) Examples:**
- ❌ Confusing or ambiguous language
- ❌ Wall of text with no structure
- ❌ Too much jargon
- ❌ Difficult to understand what's being said
- ❌ Poor grammar or formatting

**Medium Score (3) Examples:**
- ⚠️ Understandable but could be clearer
- ⚠️ Some organization but not optimal
- ⚠️ Mix of clear and confusing parts

**Real-world example:**

**High Clarity (5):**
```
Your account balances:
• Checking (1234): $1,234.56
• Savings (5678): $5,678.90
• Total: $6,913.46
```

**Low Clarity (1):**
```
Your checking which is account number 1234 has $1234.56 in it
and then there's also your savings account that's number 5678
with $5678.90 so altogether that's $6913.46 between both accounts.
```

**Recommended threshold:** 4.0 average (some responses can be complex but overall should be clear)

---

### 4. Politeness

**What it measures:** Is the tone professional, respectful, and appropriate?

**High Score (4-5) Examples:**
- ✅ Courteous and respectful tone
- ✅ Professional language
- ✅ Appropriate greetings and closings
- ✅ Shows empathy when appropriate
- ✅ Patient with confused customers

**Low Score (0-2) Examples:**
- ❌ Rude or dismissive
- ❌ Overly casual or unprofessional
- ❌ Condescending tone
- ❌ Impatient with customer
- ❌ Inappropriate language

**Medium Score (3) Examples:**
- ⚠️ Professional but cold
- ⚠️ Somewhat abrupt
- ⚠️ Missing courtesies
- ⚠️ Too casual for context

**Real-world example:**

**High Politeness (5):** "I'd be happy to help you check your balance. Your checking account currently has $1,234.56. Is there anything else I can assist you with?"

**Low Politeness (1):** "Your balance is $1234.56. Next question."

**Recommended threshold:** 3.5-4.0 average (politeness is important but slight variations are acceptable)

---

## Creating Custom Quality Criteria

Beyond the four standard criteria, you can create custom measures specific to your business needs.

### When to Use Custom Criteria

- Industry-specific requirements
- Company policies
- Special use cases
- Unique quality standards

### Examples of Custom Criteria

#### For Banking:
**"Compliance"**
- Measures: Does response follow regulatory requirements?
- Checks: Privacy disclosures, appropriate disclaimers, FDIC mentions

**"Security Awareness"**
- Measures: Does agent avoid sharing sensitive information inappropriately?
- Checks: Never shares full account numbers, SSN, passwords

**"Upselling Appropriateness"**
- Measures: Does agent mention relevant products without being pushy?
- Checks: Context-appropriate suggestions, not overly sales-focused

#### For Healthcare:
**"HIPAA Compliance"**
- Measures: Does response protect patient privacy?
- Checks: No unauthorized information sharing

**"Medical Accuracy"**
- Measures: Is medical information correct and appropriate?
- Checks: Doesn't diagnose, refers to doctors when appropriate

#### For Customer Service:
**"Problem Resolution"**
- Measures: Does agent actually solve the customer's issue?
- Checks: Provides actionable solution, offers next steps

**"Empathy"**
- Measures: Does agent show understanding of customer frustration?
- Checks: Acknowledges concerns, validates feelings

### How to Define Custom Criteria

When creating a custom criterion, provide:

1. **Name**: Short, descriptive (e.g., "Compliance")
2. **Description**: What you're measuring and why
3. **Examples**: What high vs. low scores look like
4. **Threshold**: Minimum acceptable score

**Example:**
```
Name: Compliance
Description: Verify the response includes required regulatory
             disclosures and follows banking regulations
Threshold: MIN 4.5 (compliance is critical)
```

---

## Understanding Thresholds

A threshold is the minimum acceptable score. Tests only pass if all thresholds are met.

### Threshold Types

#### MIN (Minimum)
Every single response in the conversation must meet this score.

**Use for:**
- Critical criteria (Accuracy in banking)
- Safety/compliance requirements
- Information that must always be correct

**Example:** Accuracy MIN 4.0 = Every response must score at least 4.0 on accuracy

#### AVERAGE
The average score across all responses must meet this value.

**Use for:**
- Stylistic criteria (Clarity, Politeness)
- Qualities where some variation is okay
- Overall conversation quality

**Example:** Clarity AVERAGE 4.0 = Some responses can be 3.5 if others are 4.5, as long as average ≥ 4.0

### Setting Appropriate Thresholds

#### Starting Points (for new agents)

| Criterion  | Recommended | Critical Systems |
|------------|-------------|------------------|
| Accuracy   | 4.0         | 4.5              |
| Relevance  | 4.0         | 4.0              |
| Clarity    | 3.5-4.0     | 4.0              |
| Politeness | 3.5         | 3.5              |

#### Adjusting Thresholds Over Time

**Too many failures?**
- Review a few test runs to see typical scores
- If agent consistently scores 3.8 but threshold is 4.0, either:
  - Fix the agent to perform better, OR
  - Lower threshold to 3.5 temporarily and gradually raise it

**Tests always pass easily?**
- Agent is performing well - consider raising standards
- Increase thresholds by 0.2-0.5 points
- Continue raising until you reach excellence

**Inconsistent results?**
- Same test passes sometimes, fails other times
- Usually means threshold is set right at the edge of performance
- Either improve agent consistency or adjust threshold slightly

### Threshold Strategy by Phase

#### Development Phase
- Start lower (3.5-4.0)
- Accept that agent is learning
- Focus on identifying major issues

#### Testing Phase
- Raise to production targets (4.0-4.5)
- Ensure agent meets standards
- Fix failures before launch

#### Production Phase
- Maintain high standards (4.0-4.5)
- Monitor for regressions
- Gradually improve over time

---

## Scoring Scale Reference

| Score | Meaning     | Description                           |
|-------|-------------|---------------------------------------|
| 5.0   | Excellent   | Exceptional quality, exceeds expectations |
| 4.5   | Very Good   | High quality, minor improvement possible |
| 4.0   | Good        | Solid quality, meets expectations     |
| 3.5   | Acceptable  | Adequate but noticeable gaps         |
| 3.0   | Fair        | Below expectations, needs improvement |
| 2.5   | Poor        | Significant issues                    |
| 2.0   | Very Poor   | Major problems                        |
| 0-1.5 | Failing     | Unacceptable quality                  |

---

## Real-World Examples

### Example 1: Banking Balance Inquiry

**Question:** "What are my account balances?"

**Agent Response:** "You have three accounts: Checking (1234) with $1,234.56, Savings (5678) with $5,678.90, and Credit Card (9012) with a balance of $-456.78 (amount owed). All balances as of today, December 3rd."

**Evaluation:**
- **Accuracy: 5.0** - All balances correct, includes all accounts, current date
- **Relevance: 5.0** - Directly answers question asked
- **Clarity: 4.5** - Well-organized, clear format, easy to understand
- **Politeness: 4.0** - Professional but no greeting/closing

**With thresholds:**
- Accuracy MIN 4.0: ✅ PASS (5.0 ≥ 4.0)
- Relevance MIN 4.0: ✅ PASS (5.0 ≥ 4.0)
- Clarity AVG 4.0: ✅ PASS (4.5 ≥ 4.0)
- Politeness AVG 3.5: ✅ PASS (4.0 ≥ 3.5)

**Overall: PASS**

### Example 2: Failing on Accuracy

**Question:** "What's my credit card balance?"

**Agent Response:** "Your credit card ending in 9012 has a balance of $456.78."

**Actual balance in database:** $-456.78 (customer OWES money, not HAS money)

**Evaluation:**
- **Accuracy: 2.0** - Wrong sign (positive vs negative), major error
- **Relevance: 5.0** - Answered the right question
- **Clarity: 5.0** - Clear response
- **Politeness: 4.0** - Professional

**With thresholds:**
- Accuracy MIN 4.0: ❌ FAIL (2.0 < 4.0)
- Other criteria pass

**Overall: FAIL** (failed on Accuracy threshold)

---

## Best Practices

### 1. Prioritize Accuracy
Always set the highest threshold for Accuracy. Incorrect information is worse than awkward phrasing.

### 2. Be Realistic
Don't expect 5.0 on everything. A score of 4.0-4.5 represents good quality.

### 3. Use MIN for Critical, AVERAGE for Style
- MIN: Accuracy, Compliance, Security
- AVERAGE: Clarity, Politeness

### 4. Test Your Thresholds
Run tests and see what scores you actually get before finalizing thresholds.

### 5. Document Custom Criteria
Write clear descriptions so everyone understands what's being measured.

### 6. Review Edge Cases
When a test scores exactly at the threshold (4.0 vs 4.0), review to ensure it's truly acceptable quality.

---

## What's Next?

Now that you understand quality criteria and thresholds, you're ready to:

**Next Article:** [Running Tests and Understanding Results](./04-running-tests-and-results.md)

**Related Articles:**
- [Creating Your First Test](./02-creating-your-first-test.md) - How to configure criteria when creating tests
- [Building Effective Tests](./05-building-effective-tests.md) - Best practices for setting quality standards
