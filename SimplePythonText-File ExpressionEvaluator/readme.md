# Simple Python Text-File Expression Evaluator

## Project Overview

This project is a simple Python script that reads mathematical and logical expressions line by line from a text file named `input.txt`, evaluates them, and writes the results to an `output.txt` file.

The most significant constraint of this project is that no built-in evaluation functions like `eval()` or any external Python libraries were used. All parsing, tokenization, and operator precedence logic was coded from scratch.

## Features

* **Basic Arithmetic Operations:** Addition (`+`), Subtraction (`-`), Multiplication (`*`), Division (`/`).
* **Advanced Arithmetic Operations:** Exponentiation (`**`), Integer Division (`//`), and Modulus (`%`).
* **Logical Comparisons:** `<`, `>`, `<=`, `>=`, `==`, and `!=`.
* **Operator Precedence:** Follows standard Python operator precedence rules (e.g., `**` before `*`, `*`/`/` before `+`/`-`).
* **Complex Expressions:** Can solve complex lines containing both arithmetic and logical operators, such as `5 * 3 + 10 > 4 / 2`.
* **Flexible Syntax:** Can process expressions with or without spaces, such as `5+3` and `5 + 3`.
* **Error Handling:** Writes "ERROR" to the output file for invalid syntax, such as `5 + z` (invalid character) or `1+` (missing element).
* **Empty Line Handling:** Empty lines in `input.txt` are mirrored as empty lines in `output.txt` to preserve readability.

## Code Architecture (How it Works)

The script is built on a main loop (`main` function) that reads `input.txt` line by line. For each line:

1.  **Read and Clean:** The line is read (`file_read`) and all unnecessary spaces are removed with `delete_space`.
2.  **Tokenization:** The `find_number` function converts the cleaned string into a list of tokens (numbers, including multi-digit ones, and operators).
3.  **Classification:** `find_logic_or_arith` determines if the expression is purely "arithmetic", "logical", or "mixed (logic+arith)".
4.  **Validation:** `find_error` checks for basic syntax errors, like operators at the beginning or end of the line.
5.  **Evaluation:**
    * **Arithmetic:** The `operator_precedence` function is called, which resolves the expression according to precedence rules.
    * **Logical:** The `logic_compare` function is called.
    * **Mixed:** `logic_arith_op` splits the expression at the logical operator, resolves each side with `operator_precedence`, and finally compares the two results with `logic_compare`.
6.  **Write:** The resulting value (or "ERROR") is written to `output.txt` using `file_append`.

## How to Use

1.  Download the project files and rename the Python script to `evaluator.py`.
2.  Write the expressions you want to calculate in the `input.txt` file, one expression per line.
3.  Run the Python script using the following command:

    ```bash
    python evaluator.py
    ```

4.  The results will be generated in the `output.txt` file in the same directory.

## Example

### `input.txt` File

```text
5 + 3 * 2
5**2 / 5
10 > 8
5 * 5 + 2 >= 30 - 5
99 +
5 / 0
10 % 3
