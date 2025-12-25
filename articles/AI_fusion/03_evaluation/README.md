# AI Fusion Evaluation Framework - Knowledge Base Articles

User-friendly guide for non-technical users who will primarily use the web interface to test AI agents.

## Target Audience

These articles are written for:
- Customer service managers
- Business analysts
- Quality assurance professionals
- Product managers
- Domain experts (banking, telco, HR)
- Anyone testing AI agents **without** needing to use APIs or databases

## What You'll Learn

- How to create and run tests through the web interface
- How to understand quality scores and pass/fail results
- How to organize and maintain your test suite
- Best practices for ongoing testing programs
- How to use AI to auto-generate tests

## Articles

### Getting Started (Essential - Read First)

**[01. Introduction to AI Agent Testing](./01-introduction-to-evaluation.md)**
- What is the evaluation framework?
- Why test your AI agent?
- How it works (simple overview)
- Key concepts explained
- No technical skills required

**[02. Creating Your First Test](./02-creating-your-first-test.md)**
- Step-by-step walkthrough
- Manual test creation
- Auto-generated test creation
- Your first complete example
- Tips for success

**[03. Understanding Quality Criteria and Thresholds](./03-understanding-quality-criteria.md)**
- The four standard quality measures (Accuracy, Relevance, Clarity, Politeness)
- How to interpret scores (0-5 scale)
- Setting minimum thresholds
- Creating custom criteria for your business
- Real-world examples with explanations

**[04. Running Tests and Understanding Results](./04-running-tests-and-results.md)**
- Three ways to run tests
- Reading the results interface
- Understanding pass/fail
- What to do when tests fail
- Tracking trends over time

### Building Quality (Read Next)

**[05. Building Effective Test Conversations](./05-building-effective-tests.md)**
- Using real customer language
- Testing multi-turn conversations
- Writing specific expected answers
- Testing edge cases
- Test templates by domain (Banking, Telco, HR)
- Common mistakes to avoid

**[06. Organizing Test Suites](./06-organizing-test-suites.md)**
- What are test suites?
- Creating and naming suites
- Organization strategies (by domain, journey, test type)
- Enabling/disabling suites
- Managing growing test collections
- Suite maintenance

### Ongoing Success (Read Last)

**[07. Best Practices and Maintenance](./07-best-practices.md)**
- Getting started right
- Running tests regularly
- Acting on results
- Tracking trends
- Maintaining your test suite
- Team collaboration
- Measuring success
- Tips for long-term success

## Suggested Reading Path

### Path 1: Quick Start (1-2 hours)
For getting up and running quickly:

1. Introduction to AI Agent Testing (15 min)
2. Creating Your First Test (30 min)
3. Running Tests and Understanding Results (30 min)
4. Skim Best Practices for tips (15 min)

**Then:** Start creating your first 5 tests!

---

### Path 2: Comprehensive (3-4 hours)
For thorough understanding:

1. Introduction to AI Agent Testing
2. Creating Your First Test
3. Understanding Quality Criteria and Thresholds
4. Running Tests and Understanding Results
5. Building Effective Test Conversations
6. Organizing Test Suites
7. Best Practices and Maintenance

**Then:** Create 10-15 tests and establish your testing rhythm.

---

### Path 3: Role-Specific

#### For Managers
Focus on:
- Introduction (why it matters)
- Understanding Results (interpreting reports)
- Best Practices (team collaboration, tracking trends)

#### For Test Creators
Focus on:
- Creating Your First Test
- Understanding Quality Criteria
- Building Effective Tests
- Best Practices

#### For Quality Assurance
Read all articles in order for complete understanding.

## By Use Case

### "I need to test my banking AI agent"
1. Introduction
2. Creating Your First Test (see banking examples)
3. Building Effective Tests (banking templates section)
4. Running Tests and Understanding Results

### "I want to use auto-generation"
1. Introduction (overview)
2. Creating Your First Test → Method 2: Auto-Generated Tests
3. Building Effective Tests (what makes good tests)
4. Best Practices → reviewing auto-generated tests

### "I have 50+ tests and need to organize them"
1. Organizing Test Suites (entire article)
2. Best Practices → Maintenance section

### "My tests keep failing and I don't know why"
1. Understanding Quality Criteria (what scores mean)
2. Running Tests and Understanding Results → What to Do When Tests Fail
3. Best Practices → Common Issues section

### "I need to convince my team to use this"
1. Introduction → Why Test Your AI Agent
2. Best Practices → Measuring Success
3. Share the "Benefits by Role" section from Introduction

## Article Features

All articles include:
- ✅ **Clear examples** with real-world scenarios
- ✅ **Step-by-step instructions** with UI guidance
- ✅ **Do's and Don'ts** with explanations
- ✅ **Common mistakes** and how to avoid them
- ✅ **Templates** you can copy and adapt
- ✅ **Troubleshooting tips** for common issues
- ❌ **No technical jargon** or API documentation
- ❌ **No database queries** or command-line instructions

## Key Takeaways

After reading these articles, you'll be able to:

- ✓ Create tests through the web interface (manual and auto-generated)
- ✓ Set appropriate quality criteria and thresholds
- ✓ Run tests and interpret results
- ✓ Understand pass/fail and what to do about failures
- ✓ Build effective test conversations that catch real issues
- ✓ Organize tests into logical suites
- ✓ Maintain a testing program over time
- ✓ Track quality trends and improvements
- ✓ Collaborate with your team on testing

## Additional Resources

### Quick Reference Guides

**Quality Criteria Quick Reference:**
- Accuracy: Is information correct? (Threshold: 4.0-4.5)
- Relevance: Does it answer the question? (Threshold: 4.0)
- Clarity: Is it easy to understand? (Threshold: 4.0)
- Politeness: Is tone professional? (Threshold: 3.5)

**Scoring Scale:**
- 5.0 = Excellent
- 4.5 = Very Good
- 4.0 = Good (typical minimum)
- 3.5 = Acceptable
- 3.0 = Fair (needs improvement)
- Below 3.0 = Poor

**Test Structure:**
- Simple test: 1-2 questions
- Typical test: 3-5 questions
- Complex test: 6-10 questions
- Maximum: 15 questions

### Common Questions

**Q: How many tests should I create?**
A: Start with 5-10 core tests covering the most common customer questions. Grow to 50-100 tests over time.

**Q: How often should I run tests?**
A: Weekly for ongoing monitoring. Always before deployments. Daily during active development.

**Q: What pass rate should I aim for?**
A: 85-90% is good. 100% is unrealistic. Focus on trends improving over time.

**Q: Should I use manual or auto-generated tests?**
A: Use both! Manual for critical specific scenarios. Auto-generated for broader coverage and discovering edge cases.

**Q: How long does it take to get started?**
A: 1-2 hours to read basics and create your first test. 1-2 weeks to build initial test suite. 3-6 months for mature testing program.

## Feedback

These articles are living documents. If you have:
- Questions not answered here
- Suggestions for improvements
- Examples from your experience
- Common issues we should address

Please share feedback with your team or system administrator.

## Version History

- **v1.0** (Dec 2024) - Initial release
  - 7 comprehensive articles
  - Focus on non-technical users
  - Web interface guidance
  - Practical examples and templates

---

**Ready to get started? Begin with [Introduction to AI Agent Testing](./01-introduction-to-evaluation.md)**
