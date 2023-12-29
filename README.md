# Unused Variables Analyzer

This repository contains my **Java** implementation for the test task required to apply for the **Implement dataflow analysis for LLVM architecture** internship project at **JetBrains**.

The system includes a **parser** capable of interpreting grammar based on the following rules:

- **program** ::= statement_list
- **statement_list** ::= statement | statement_list statement
- **statement** ::= variable '=' expression | 'if' expression statement_list 'end' | 'while' expression statement_list 'end'
- **expression** ::= variable | constant | '(' expression ')' | expression operator expression
- **operator** ::= '+' | '-' | '*' | '/' | '<' | '>'

Additionally, the system features a tool for analyzing **unused variables** within a given program that is written using the above rules. This tool performs **static analysis**, meaning it does not run the provided code but instead tries to determine when an assignment is **FOR SURE** unused.

## Parser Details
* Assumes the given programs are valid.
* Is *newline sensitive*, meaning that each instruction should be written on one line.
* Otherwise, it is *whitespace & indentation insensitive* and can allow empty lines.
* Contains different types of nodes for *variable assignment*, *if statement* and *while statement* instructions. (*example 3 illustrates a scenario containing nested while loops*)

## Unused Variables Analysis Details
* Each path in the control flow graph is considered to be traversed.
* This means that *if* statements are considered to always be true.
* *While* branches are considered to be traversed at least twice, but a finite amount of times. This also means that there will be at least two iterations of the inner *while* loop for each iteration of the outer loop.

## Test cases
* Are present in *src/test/examples*.
* Include the provided example in the problem statement, as well as some other scenarios that help illustrate the capabilities of the tool.
* Are ran using *JUnit* tests. 