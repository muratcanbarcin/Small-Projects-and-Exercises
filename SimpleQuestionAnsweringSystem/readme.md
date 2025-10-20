# Simple C# Question Answering System

## Project Overview

This project is a simple, rule-based question-answering system written in C#. [cite_start]It reads questions from a `questions.txt` file, processes them against a knowledge base (`corpus.txt`), and prints the answers directly to the console[cite: 8].

[cite_start]The system operates based on three distinct rules to handle different types of queries: mathematical calculations, pattern matching, and general knowledge retrieval[cite: 14].

## Features

This system can process three types of questions:

1.  **Rule 1: Mathematical Expressions**
    * [cite_start]Identifies questions starting with "What is the result of expression"[cite: 15].
    * [cite_start]Parses and calculates the result of the mathematical expression[cite: 15].
    * [cite_start]It is designed to handle expressions containing a *single type* of operator (e.g., only `+` or only `*`) in one query[cite: 16].
    * The C# code implements this by detecting the operator (`+`, `-`, `*`, or `/`) and splitting the expression accordingly.

2.  **Rule 2: Pattern Matching**
    * [cite_start]Identifies questions starting with "what are the top10 words in the pattern"[cite: 17].
    * [cite_start]Searches the `corpus.txt` for words that match the given pattern[cite: 17].
    * [cite_start]The dash (`-`) character is used as a wildcard for a single letter[cite: 18].
    * It returns up to 10 matching words found in the corpus.

3.  **Rule 3: General Knowledge QA**
    * This is the default rule for any question that doesn't match Rule 1 or 2.
    * [cite_start]It finds the most relevant answer by comparing words in the question with sentences in the `corpus.txt`[cite: 19].
    * [cite_start]Relevance is determined by the **number of common words** between the question and a corpus sentence[cite: 20].
    * A predefined list of "stop words" (like "a", "is", "the", "in") is ignored during comparison.
    * [cite_start]An answer is only returned if it has at least **two** matching non-stop words[cite: 22].
    * [cite_start]If multiple sentences have the same highest match count, all are printed[cite: 21].
    * [cite_start]If no sentence meets the two-word match threshold, it prints "No answer"[cite: 23].

### Additional Features

* [cite_start]**Case Insensitive:** The program works correctly with both uppercase and lowercase letters [cite: 13] by converting all text to uppercase before comparison.
* **Punctuation Removal:** Automatically strips punctuation (e.g., `?`, `.`, `,`, `;`) from questions and corpus text to ensure accurate word matching.

## Code Architecture (How it Works)

The `Main` method in `QASystem.cs` reads the `questions.txt` file line by line. For each line:

1.  It checks if the line starts with the "What is the result of expression" prefix. If yes, it processes it as a **Rule 1** mathematical query.
2.  If not, it checks if the line starts with the "What are the top10 words in the pattern" prefix. If yes, it processes it as a **Rule 2** pattern-matching query.
3.  If neither, it processes the line as a **Rule 3** general QA query.
    * For Rule 3, the question is cleaned (punctuation removed, converted to uppercase).
    * The script then reads `corpus.txt` sentence by sentence, cleaning each one and counting the shared non-stop words.
    * It stores the match count for each sentence, finds the maximum count, and prints all sentences that match this maximum (and are >= 2).
4.  [cite_start]The answer is printed directly to the console[cite: 8].

## How to Use

1.  Ensure you have a `questions.txt` file and a `corpus.txt` file in the same directory as the executable.
2.  Compile the `QASystem.cs` file (or your renamed `.cs` file).
3.  Run the compiled executable.
4.  The answers to the questions in `questions.txt` will be printed to the console.

## Example

### Input (`questions.txt`)

```text
What is the biggest participant sport in the world?
What is the result of expression 2x3+5678x2+4x1234+21x71+5x2 for x=1?
What are the top10 words in the pattern -o-R?
What is your name?
How long is the river Nile?
