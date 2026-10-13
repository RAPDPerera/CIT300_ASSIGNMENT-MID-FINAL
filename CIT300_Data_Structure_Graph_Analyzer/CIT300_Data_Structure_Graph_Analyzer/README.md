
# CIT300 Data Structure & Graph Performance Analyzer

## Project Overview
A Java console application implementing the complete CIT300 Graded Practical Assignment 2 requirements. The application integrates an array, stack, queue, linked list, linear/binary searching, graph representation with BFS/DFS, performance measurement, input validation, and a unified menu.

## Requirements Coverage
- Array: insert, delete, search, display, clear
- Stack: push, pop, peek, display, underflow/overflow handling
- Queue: enqueue, dequeue, front/peek, display, underflow/overflow handling
- Linked List: insert first/last, delete, search, display, clear
- Searching: linear and binary search with steps and execution time
- Graph: add vertex, add edge, display adjacency list, vertex search, BFS, DFS
- Performance: operation/step count and nanosecond timing
- Main integration: menu-driven console system
- Input validation: numeric ranges and empty strings
- OOP: separate classes with encapsulated state and methods

## Team Members
Replace the following placeholders with the actual group information before submission.

| Member | Student ID | Responsibility | Individual Contribution |
|---|---|---|---|
| Member 1 | INSERT ID | Array + Searching | Dynamic array, linear/binary search, testing |
| Member 2 | INSERT ID | Stack + Queue | Stack/queue classes, boundary handling, testing |
| Member 3 | INSERT ID | Linked List | Node structure, insert/delete/search/display |
| Member 4 | INSERT ID | Graph | Adjacency list, vertex/edge operations, BFS/DFS |
| Member 5 | INSERT ID | Integration + Performance | Main menu, integration, performance comparison, final testing |

Do not claim a contribution that a member did not actually make. Update this table to reflect genuine GitHub work.

## Technologies
- Java 17 or later
- Console / terminal
- Git and GitHub
- Standard Java Collections where appropriate for graph traversal support

## Project Structure
```
src/
  Main.java
  InputHelper.java
  DynamicArray.java
  StackDS.java
  QueueDS.java
  LinkedListDS.java
  SearchResult.java
  SearchAlgorithms.java
  Graph.java
  PerformanceAnalyzer.java
```

## Running
Compile:
```bash
javac -d out src/*.java
```

Run:
```bash
java -cp out Main
```

## Complexity Summary
| Component | Operation | Complexity |
|---|---|---|
| Array | Insert/delete at arbitrary index | O(n) |
| Array | Search | O(n) |
| Stack | Push/pop/peek | O(1) |
| Queue | Enqueue/dequeue/peek | O(1) |
| Linked List | Insert first | O(1) |
| Linked List | Insert last | O(n) |
| Linked List | Search/delete by value | O(n) |
| Linear Search | Search | O(n) |
| Binary Search | Search sorted data | O(log n) |
| Graph | Add vertex | O(1) average |
| Graph | Add edge | O(degree) |
| BFS | Traversal | O(V+E) |
| DFS | Traversal | O(V+E) |

## GitHub Collaboration
Use a branch per contributor where practical, meaningful commits, pull requests, and issue/task tracking. Do not fabricate commits or contributions. The lecturer may inspect repository history.

## Submission
The official assignment requires a GitHub repository and one merged demonstration video under 15 minutes. If a Google Drive submission is required, upload the complete project, put the folder link in a TXT file, and provide the required editor permissions before LMS submission.

## Test Evidence
The project includes `TestCases.md` containing 25 functional and boundary test cases. The Java application was compiled successfully using Java 21 and exercised through scripted console scenarios covering the main modules.

## System Screenshots
The `screenshots/` folder contains console evidence for:
- Main menu
- Array operations
- Stack operations
- Queue operations
- Linked list operations
- Searching/performance
- Graph, BFS and DFS
- Performance comparison

## Important Group Information
Before submission, replace the member placeholders with the real full names, student IDs, responsibilities, and genuine contributions of the group members. Do not fabricate collaboration history.
