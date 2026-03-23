# Organizing Test Suites

## What is a Test Suite?

A test suite is a collection of related test cases grouped together. Think of it like folders for organizing files - suites help you organize tests logically so they're easy to find, run, and manage.

**Example:**
```
Banking_Tests/
  ├── Account_Balance_Check
  ├── Recent_Transactions
  ├── Transfer_Funds
  └── Account_Details

Loan_Services/
  ├── Loan_Balance
  ├── Payment_Schedule
  ├── Interest_Rate_Info
  └── Payoff_Amount
```

---

## Why Organize Tests Into Suites?

### Easy to Find
Logical grouping makes it simple to locate specific tests.

**Instead of:**
- "Which of these 150 tests checks balance inquiries?"

**You have:**
- Banking_Tests → Account_Services → Balance_Check

### Selective Testing
Run only relevant tests after changes.

**Example:**
- Changed loan calculation logic → Run "Loan_Services" suite
- Changed UI formatting → Run "Formatting_Tests" suite
- Don't need to run all 150 tests every time

### Progress Tracking
See how different areas are performing.

**Dashboard view:**
- Banking_Tests: 45/50 passing (90%)
- Loan_Services: 18/20 passing (90%)
- Card_Services: 12/15 passing (80%) ← Needs attention

### Team Collaboration
Different teams can own different suites.

**Example:**
- Customer Service team owns: Customer_Service_Tests
- Product team owns: Product_Info_Tests
- Billing team owns: Billing_Tests

---

## Creating and Managing Suites

### Creating a New Suite

When you save a test, you'll be prompted to choose a suite:

1. **Option 1:** Select existing suite from dropdown
2. **Option 2:** Type a new suite name to create one

**Example:**
```
Save test "Balance Inquiry" to:
[Dropdown: Banking_Tests, Loan_Services, Card_Services]
Or create new: [New Suite Name_______]
```

**Note:** Suite is created automatically when you save the first test to it.

### Naming Conventions

Use clear, descriptive, consistent names:

#### Good Names ✅

**By Domain:**
- `Banking_Customer_Service`
- `Telco_Billing_Issues`
- `HR_Benefits_Enrollment`

**By Feature:**
- `Account_Balance_Tests`
- `Transaction_History_Tests`
- `Transfer_Services_Tests`

**By Priority:**
- `Critical_Smoke_Tests`
- `Standard_Regression_Tests`
- `Edge_Case_Tests`

#### Poor Names ❌

- `Tests1`
- `New_Tests`
- `Temp`
- `TODO`
- `Misc`

**Why poor names fail:**
- Unclear what they contain
- Hard to find specific tests
- Creates confusion for team
- Difficult to maintain

### Suite Naming Patterns

#### Pattern: Domain_Feature
```
Banking_Accounts
Banking_Loans
Banking_Cards
Telco_Billing
Telco_Support
HR_Payroll
HR_Benefits
```

#### Pattern: Feature_TestType
```
Accounts_Smoke_Tests
Accounts_Regression_Tests
Accounts_Edge_Cases
Loans_Smoke_Tests
Loans_Regression_Tests
```

#### Pattern: UserJourney
```
New_Customer_Onboarding
Account_Management_Journey
Problem_Resolution_Journey
Account_Closure_Journey
```

**Choose one pattern and stick with it** for consistency.

---

## Suite Organization Strategies

### Strategy 1: By Business Domain

Organize by business area or product line.

```
Banking_Checking_Accounts/
  - Balance_Inquiry
  - Transaction_History
  - Account_Details
  - Transfer_Funds

Banking_Savings_Accounts/
  - Interest_Calculation
  - Withdrawal_Limits
  - Balance_Inquiry

Banking_Credit_Cards/
  - Balance_Check
  - Payment_Due_Date
  - Available_Credit
  - Recent_Charges

Banking_Loans/
  - Loan_Balance
  - Payment_Schedule
  - Payoff_Calculation
```

**Best for:**
- Organizations with clear product divisions
- When different teams own different products
- Large applications with distinct modules

---

### Strategy 2: By User Journey

Organize by complete customer workflows.

```
New_Customer_Onboarding/
  - Account_Opening_Inquiry
  - Required_Documents
  - Initial_Deposit_Questions
  - Welcome_Experience

Active_Account_Management/
  - Balance_Checks
  - Transfers
  - Statement_Requests
  - Profile_Updates

Problem_Resolution/
  - Billing_Disputes
  - Transaction_Errors
  - Account_Access_Issues
  - Service_Complaints

Account_Closure/
  - Closure_Request
  - Final_Balance_Transfer
  - Confirmation_Process
```

**Best for:**
- Customer-centric organizations
- Testing end-to-end experiences
- Validating complete workflows

---

### Strategy 3: By Test Type

Organize by testing purpose and priority.

```
Smoke_Tests/  (Critical functionality - run first)
  - Basic_Balance_Check
  - Simple_Transaction_Query
  - Account_Access

Regression_Tests/  (Comprehensive - run regularly)
  - All_Account_Types_Balance
  - Complex_Transaction_History
  - Multi_Account_Transfers
  - Edge_Cases

Performance_Tests/  (Volume and speed)
  - Large_Transaction_History
  - Multiple_Concurrent_Queries
  - Complex_Calculations

Edge_Case_Tests/  (Unusual scenarios)
  - Zero_Balance_Accounts
  - Negative_Balances
  - Closed_Accounts
  - Missing_Data
```

**Best for:**
- CI/CD integration
- Prioritized test execution
- Different testing phases

---

### Strategy 4: By Deployment Phase

Organize by when tests should run.

```
Pre_Production/  (Must pass before deployment)
  - Critical_Balance_Checks
  - Transaction_Accuracy
  - Security_Validations

Post_Deployment/  (Verify deployment worked)
  - Basic_Smoke_Tests
  - Integration_Checks
  - Data_Access_Verification

Continuous_Monitoring/  (Run regularly in production)
  - Real_World_Scenarios
  - Performance_Baselines
  - Quality_Monitoring
```

**Best for:**
- DevOps workflows
- Staged deployments
- Continuous quality monitoring

---

### Strategy 5: Hybrid Approach

Combine multiple strategies for large systems.

```
Banking/
  ├── Smoke_Tests/
  │   ├── Critical_Balance_Checks
  │   └── Basic_Transfers
  ├── Accounts/
  │   ├── Checking/
  │   ├── Savings/
  │   └── Credit_Cards/
  ├── Loans/
  │   ├── Personal_Loans/
  │   └── Mortgages/
  └── Edge_Cases/
      ├── Missing_Data/
      └── Error_Scenarios/
```

**Best for:**
- Large, complex applications
- Multiple teams
- Diverse testing needs

---

## Enabling and Disabling Suites

### Managing Suite Status

You can enable or disable entire suites:

**In the UI:**
1. Navigate to test suite tree
2. Find the suite
3. Toggle the enable/disable switch
4. Disabled suites won't run during batch execution

**Visual indicator:**
```
✓ Banking_Tests (enabled)
✗ Experimental_Tests (disabled)
```

### When to Disable Suites

**Under Development:**
```
✗ New_Feature_Tests  (being built, not ready for regular runs)
```

**Known Issues:**
```
✗ Loan_Calculator_Tests  (blocked by bug #123, will re-enable after fix)
```

**Seasonal/Conditional:**
```
✗ Year_End_Processing  (only relevant in December/January)
```

**Experimental:**
```
✗ AI_Generated_Exploratory  (reviewing before promoting to regular suite)
```

### Disabling Best Practices

**DO:**
- Document why a suite is disabled
- Set a reminder to re-enable
- Create a ticket to track the issue

**DON'T:**
- Disable suites just because tests are failing
- Leave suites disabled indefinitely without review
- Disable without communicating to team

---

## Managing Individual Tests in Suites

### Enabling/Disabling Individual Tests

You can also enable/disable specific tests within a suite:

```
Banking_Tests/
  ✓ Balance_Inquiry (enabled)
  ✓ Transaction_History (enabled)
  ✗ Complex_Transfer (disabled - flaky test, under review)
  ✓ Account_Details (enabled)
```

**When to disable individual tests:**
- Test is flaky (passes/fails inconsistently)
- Blocked by known issue
- Temporarily broken while fixing agent
- Under review/revision

### Moving Tests Between Suites

If you realize a test is in the wrong suite:

1. Open the test
2. Edit the test configuration
3. Change the suite field
4. Save

**Example:**
```
Move "Credit_Score_Check"
From: Banking_Tests
To: Credit_Services_Tests
```

---

## Suite Maintenance

### Regular Maintenance Tasks

#### Monthly Review

**Review each suite:**
- Are all tests still relevant?
- Any tests that should be moved?
- Suite names still appropriate?
- Tests properly organized?

#### Quarterly Cleanup

**Clean up suites:**
- Remove obsolete tests
- Consolidate duplicate tests
- Reorganize if structure has become messy
- Archive old experimental suites

#### After Major Changes

**Update affected suites:**
- Add tests for new features
- Remove tests for deprecated features
- Update test expectations
- Reorganize if major refactoring occurred

---

### Signs Your Suite Organization Needs Work

**Warning signs:**

❌ **Can't find tests easily**
"Where's that balance test? I know we have one..."

❌ **Tests in wrong places**
"Why is this loan test in the credit card suite?"

❌ **Huge misc/temp suites**
"Misc_Tests" has 87 tests with no clear theme

❌ **Duplicate tests**
Same scenario tested in 3 different suites

❌ **Confusing names**
"What's the difference between Banking_Tests and Bank_Tests?"

**Solutions:**
- Reorganize with clear strategy
- Rename suites for clarity
- Move misplaced tests
- Remove duplicates
- Create clear documentation

---

## Starting Simple and Growing

### Phase 1: Just Starting (1-10 tests)

**Simple approach:**
```
Banking_Tests/
  - All tests go here
```

**Focus:** Just create tests, don't over-organize yet.

---

### Phase 2: Growing (10-50 tests)

**Add basic organization:**
```
Banking_Smoke_Tests/
  - Critical tests (5 tests)

Banking_Regression_Tests/
  - Comprehensive tests (45 tests)
```

**Focus:** Separate critical from comprehensive.

---

### Phase 3: Established (50-100 tests)

**Organize by domain:**
```
Banking_Accounts/
  - Account-related tests (25 tests)

Banking_Loans/
  - Loan-related tests (20 tests)

Banking_Cards/
  - Card-related tests (15 tests)

Banking_Transfers/
  - Transfer-related tests (20 tests)

Banking_Smoke_Tests/
  - Critical cross-domain tests (10 tests)
```

**Focus:** Clear domains, easy to navigate.

---

### Phase 4: Mature (100+ tests)

**Hierarchical organization:**
```
Banking/
  ├── Smoke_Tests/ (10)
  ├── Accounts/
  │   ├── Checking/ (15)
  │   ├── Savings/ (12)
  │   └── Money_Market/ (8)
  ├── Loans/
  │   ├── Personal/ (15)
  │   ├── Mortgage/ (18)
  │   └── Auto/ (10)
  ├── Cards/
  │   ├── Credit/ (20)
  │   └── Debit/ (12)
  └── Edge_Cases/ (25)
```

**Focus:** Detailed hierarchy, multiple levels.

---

## Suite Metrics and Reporting

### Key Metrics Per Suite

Track these metrics for each suite:

**Health Metrics:**
- Pass rate (e.g., 85%)
- Average score (e.g., 4.2/5.0)
- Number of tests
- Execution time

**Trend Metrics:**
- Pass rate over time
- New failures
- Consistent failures
- Improvement trends

### Example Suite Dashboard

```
Suite: Banking_Accounts
Status: ⚠ Warning
Pass Rate: 82% (41/50 tests passing)
Avg Score: 4.1/5.0
Last Run: 2 hours ago
Trend: ↘ -5% from last week

Recent Failures:
- Balance_With_Pending_Transactions (Accuracy: 3.8)
- Multi_Currency_Balance (Clarity: 3.5)
```

---

## Collaboration and Ownership

### Assigning Suite Owners

Assign teams or individuals to own specific suites:

```
Suite: Banking_Accounts
Owner: Customer Service Team
Contact: cs-team@company.com

Suite: Banking_Loans
Owner: Lending Team
Contact: lending@company.com
```

**Owner responsibilities:**
- Maintain test quality
- Review failures
- Update tests when features change
- Add tests for new scenarios

### Sharing Results

Make suite results visible to stakeholders:

**Weekly summary email:**
```
Test Suite Status - Week of Dec 3

Banking_Accounts: ✓ 90% (45/50)
Banking_Loans: ⚠ 85% (17/20)
Banking_Cards: ✓ 93% (14/15)

Overall: ✓ 89% (76/85 passing)
Trend: ↗ +3% from last week
```

---

## Best Practices Summary

### DO ✅

- Use clear, consistent naming conventions
- Organize by clear strategy (domain, journey, type)
- Start simple, grow as needed
- Review and reorganize regularly
- Document suite purposes
- Assign ownership
- Track metrics per suite

### DON'T ❌

- Create "misc" or "temp" catch-all suites
- Over-organize when you only have a few tests
- Use confusing or ambiguous names
- Let suites grow too large (50+ tests)
- Leave disabled suites without documentation
- Duplicate tests across suites

---

## What's Next?

Now that you know how to organize test suites, you're ready to:

**Next Article:** [Best Practices and Maintenance](./07-best-practices.md)

**Related Articles:**
- [Creating Your First Test](./02_creating_your_first_test.md) - Where to save new tests
- [Running Tests and Results](./04-running-tests-and-results.md) - Running entire suites
- [Building Effective Tests](./05-building-effective-tests.md) - What tests to organize
