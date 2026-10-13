
# CIT300 – Data Structures and Algorithms
## Graded Practical Assignment 2
### Data Structure and Graph Performance Analyzer

**Programming Language:** Java  
**Application Type:** Console-Based System  
**Academic Level:** Advanced / Master-Level Technical Standard

---

## Executive Summary

This project presents an integrated Java console application designed to demonstrate practical knowledge of fundamental data structures, searching algorithms, graph representation and traversal, algorithmic complexity, input validation, and object-oriented programming. The system combines an array, stack, queue, linked list, searching algorithms, and an undirected graph within one menu-driven application.

The implementation is intentionally modular. Each data structure is represented by a dedicated class with encapsulated state and public methods for the operations required by the assignment. The searching component implements both linear and binary search and reports the number of logical search steps and measured execution time. The graph component uses an adjacency-list representation and implements vertex and edge insertion, graph display, vertex search, breadth-first search (BFS), and depth-first search (DFS). A performance module brings the results together and relates observed behaviour to asymptotic complexity.

The design also addresses invalid input and boundary conditions. Empty-stack pop and empty-queue dequeue operations are handled without crashing the program. Full stack and queue conditions are also reported. User input is validated before it is used by the data structures.

The official assignment requires all groups to implement every component, including the graph, regardless of group size. It also requires a README, genuine contribution evidence, GitHub collaboration evidence, and a merged demonstration video of less than 15 minutes. These requirements are incorporated into the project structure and documentation.

---

# 1. Introduction

Data structures and algorithms provide the foundations for efficient software development. A data structure determines how information is organised and accessed, while an algorithm defines the operations used to process that information. Understanding the relationship between the two is essential when designing software that is reliable, maintainable, and computationally efficient.

The purpose of this project is to develop a single Java console application that demonstrates several fundamental data structures and algorithms in an integrated environment. The assignment specifically requires an array, stack, queue, linked list, searching functionality, graph functionality, performance/complexity demonstration, and main-system integration.

The application follows an object-oriented design in which each major structure is implemented in a separate class. This separation improves maintainability and makes it possible to demonstrate the operations and complexity of individual structures independently.

---

# 2. Project Objectives

The main objectives are:

1. Implement an array with insertion, deletion, searching, and display.
2. Implement a stack with push, pop, peek, and display operations.
3. Implement a queue with enqueue, dequeue, front/peek, and display operations.
4. Implement a linked list with insertion, deletion, searching, and display.
5. Implement linear and binary searching.
6. Implement a graph using an adjacency-list representation.
7. Implement BFS and DFS graph traversal.
8. Record meaningful performance information such as operation steps and execution time.
9. Validate user input and handle invalid or empty operations.
10. Integrate every component into one console application.
11. Provide documentation and a structure suitable for GitHub collaboration.

---

# 3. Requirements Analysis

## 3.1 Array

The array component uses a dynamically managed integer array. It supports:

- Insert at a selected index
- Delete by index
- Linear search
- Display
- Clear

Insertion and deletion at arbitrary positions require elements to be shifted. Therefore, their worst-case complexity is O(n). Searching is O(n).

## 3.2 Stack

The stack follows the Last-In, First-Out (LIFO) principle. The implementation supports:

- Push
- Pop
- Peek
- Display
- Clear

The implementation explicitly handles underflow when pop or peek is requested on an empty stack. A fixed capacity is also used to demonstrate overflow handling.

## 3.3 Queue

The queue follows the First-In, First-Out (FIFO) principle. The implementation supports:

- Enqueue
- Dequeue
- Front/Peek
- Display
- Clear

A circular-array approach is used so that positions released by dequeue can be reused. Empty dequeue and peek operations are handled safely.

## 3.4 Linked List

The linked-list component uses a singly linked list. Each node stores a value and a reference to the next node. The implementation provides:

- Insert at beginning
- Insert at end
- Delete by value
- Search
- Display
- Clear

Insertion at the beginning is O(1). Searching and deletion by value are O(n). Insertion at the end is O(n) because the list is traversed to find the last node.

## 3.5 Searching

Two algorithms are implemented:

### Linear Search

Linear search examines elements sequentially until the target is found or the end of the collection is reached.

Worst-case complexity: O(n).

### Binary Search

Binary search repeatedly divides a sorted collection into two halves.

Worst-case complexity: O(log n).

Because binary search requires sorted data, the application sorts a copy of the array before executing the binary-search algorithm. The application displays step counts and execution times so users can compare the two approaches.

---

# 4. Graph Design

The graph is implemented using an adjacency list represented by a LinkedHashMap. Each vertex is associated with a list of neighbouring vertices.

The graph is undirected: when an edge is added between A and B, B is also added to A's adjacency list.

## 4.1 Graph Operations

The system provides:

- Add vertex
- Add edge
- Display graph
- Search vertex
- BFS traversal
- DFS traversal
- Clear graph

## 4.2 Breadth-First Search

BFS explores vertices level by level. A queue is used to hold vertices waiting for processing. A visited set prevents repeated processing.

Complexity: O(V + E), where V is the number of vertices and E is the number of edges.

## 4.3 Depth-First Search

DFS explores one branch as deeply as possible before backtracking. The implementation uses recursion and a visited set.

Complexity: O(V + E).

---

# 5. System Architecture

The application follows a modular object-oriented architecture.

```
                    +-----------------------+
                    |       Main.java       |
                    | Menu & Integration    |
                    +-----------+-----------+
                                |
       +------------------------+-------------------------+
       |          |             |          |              |
       v          v             v          v              v
 DynamicArray  StackDS      QueueDS   LinkedListDS    Graph
       |          |             |          |              |
       +----------+-------------+----------+--------------+
                                |
                                v
                    +-----------------------+
                    | SearchAlgorithms      |
                    | PerformanceAnalyzer   |
                    +-----------------------+
```

This structure separates user interaction from the internal implementation of the data structures.

---

# 6. Object-Oriented Design

The application demonstrates object-oriented principles through:

### Encapsulation
Internal arrays, indexes, node references, and graph adjacency structures are private. Access occurs through public methods.

### Abstraction
The main program does not need to know how a stack or graph is internally represented. It calls methods such as `push()`, `pop()`, `addVertex()`, and `bfs()`.

### Modularity
Each major data structure is implemented in its own class.

### Reusability
The search and performance classes can be reused with different integer datasets and targets.

---

# 7. Input Validation and Error Handling

The application validates:

- Numeric input
- Menu ranges
- Array index ranges
- Empty strings for graph labels
- Operations on empty stack
- Operations on empty queue
- Invalid graph vertices
- Stack overflow
- Queue overflow

The program reports errors to the user rather than terminating unexpectedly.

---

# 8. Performance and Complexity Demonstration

The performance module records:

1. Algorithm name
2. Search result
3. Number of logical steps
4. Execution time in nanoseconds
5. BFS traversal result
6. DFS traversal result

The system demonstrates the following relationships:

| Algorithm | Typical/Worst-Case Complexity |
|---|---|
| Linear Search | O(n) |
| Binary Search | O(log n) |
| BFS | O(V + E) |
| DFS | O(V + E) |
| Stack Push/Pop/Peek | O(1) |
| Queue Enqueue/Dequeue/Peek | O(1) |
| Linked List Search | O(n) |
| Array Insert/Delete | O(n) |

Measured execution time may vary from one run to another because of processor speed, JVM optimisation, background processes, data size, and other environmental factors. Therefore, step counts provide a more stable conceptual comparison than a single timing result.

---

# 9. Testing Strategy

Testing should be performed at three levels.

## 9.1 Normal Operation Testing

| Test | Expected Result |
|---|---|
| Insert array value | Value appears in selected position |
| Delete array value | Selected value is removed |
| Stack push | Value becomes top element |
| Stack pop | Top value is removed |
| Queue enqueue | Value is added at rear |
| Queue dequeue | Front value is removed |
| Linked-list insertion | Node appears in list |
| Graph edge insertion | Neighbour relationship is displayed |
| BFS | Level-oriented traversal is produced |
| DFS | Depth-oriented traversal is produced |

## 9.2 Boundary Testing

| Test | Expected Result |
|---|---|
| Pop empty stack | Underflow message |
| Peek empty stack | Empty message |
| Dequeue empty queue | Underflow message |
| Peek empty queue | Empty message |
| Search missing value | Not-found result |
| BFS unknown vertex | Start vertex not found |
| DFS unknown vertex | Start vertex not found |
| Invalid menu input | Validation message |

## 9.3 Integration Testing

Every menu is tested from the main application. The test confirms that a user can move from the main menu to each submenu and return without losing application state.

---

# 10. Demonstration Dataset

The application initially loads a small demonstration dataset to make the system immediately testable.

Array:
`[42, 17, 8, 31, 56, 23, 14, 65, 39, 72]`

Stack:
`[10, 20, 30, 40]`

Queue:
`[100, 200, 300, 400]`

Linked List:
`11 -> 22 -> 33 -> NULL`

Graph:

```
A -- B -- D
|    |
C    E
|   /
F --
```

The dataset can be cleared and replaced through the menu.

---

# 11. Search Comparison Example

Suppose the target is located near the end of an unsorted array. Linear search may inspect many elements before finding it. Binary search operates on a sorted copy and repeatedly halves the search space.

For n = 1,000 elements:

- Linear search can require up to approximately 1,000 comparisons in the worst case.
- Binary search requires approximately log2(1,000), which is about 10 comparisons.

This illustrates why algorithmic complexity matters when data size increases.

---

# 12. Graph Traversal Comparison

BFS uses a queue and processes neighbours level by level. DFS uses recursion and follows a branch before backtracking.

For the demonstration graph beginning at A, a possible BFS order is:

`A, B, C, D, E, F`

A possible DFS order is:

`A, B, D, E, F, C`

Exact traversal ordering depends on the order in which neighbours are stored. The important requirement is that every reachable vertex is visited according to the traversal algorithm.

---

# 13. Security and Robustness Considerations

Although this is a local educational console application, robustness has been considered through:

- Input validation
- Controlled menu ranges
- Prevention of null/empty operations
- Encapsulation of internal data
- No external network dependency
- No database credentials or sensitive information stored in the source

---

# 14. GitHub Collaboration Plan

The repository should be developed collaboratively rather than uploaded as one final file.

Recommended branch structure:

```
main
├── feature/array-search
├── feature/stack-queue
├── feature/linked-list
├── feature/graph
└── feature/integration-performance
```

Recommended meaningful commits:

- `Implement dynamic array operations`
- `Implement linear and binary search`
- `Implement stack operations and validation`
- `Implement circular queue`
- `Implement singly linked list`
- `Implement graph adjacency list`
- `Implement BFS and DFS`
- `Integrate main menu`
- `Add performance comparison`
- `Add README and testing documentation`

Only record branches, commits, pull requests, and responsibilities that genuinely occurred.

---

# 15. Individual Contribution Record

The assignment requires each member to understand and demonstrate their own contribution. The following section must be completed with the real group data.

### Member 1
Student Name: __________________________  
Student ID: ____________________________  
Responsibility: Array and Searching

Contribution:
- Implemented DynamicArray
- Implemented insertion/deletion/search
- Implemented linear search
- Implemented binary search
- Tested searching behaviour
- Participated in integration

### Member 2
Student Name: __________________________  
Student ID: ____________________________  
Responsibility: Stack and Queue

Contribution:
- Implemented stack operations
- Implemented stack boundary handling
- Implemented circular queue
- Implemented queue boundary handling
- Tested FIFO/LIFO behaviour
- Participated in integration

### Member 3
Student Name: __________________________  
Student ID: ____________________________  
Responsibility: Linked List

Contribution:
- Implemented Node structure
- Implemented insertion
- Implemented deletion
- Implemented search
- Implemented display
- Tested linked-list behaviour

### Member 4
Student Name: __________________________  
Student ID: ____________________________  
Responsibility: Graph

Contribution:
- Implemented adjacency-list graph
- Implemented vertices and edges
- Implemented graph display
- Implemented BFS
- Implemented DFS
- Tested traversal behaviour

### Member 5
Student Name: __________________________  
Student ID: ____________________________  
Responsibility: Integration and Performance

Contribution:
- Integrated all modules
- Implemented main menu
- Implemented performance comparison
- Conducted integration testing
- Prepared documentation

**Important:** Replace these placeholders with actual names, IDs, and genuine contributions before submission.

---

# 16. Demonstration Video Plan

The official requirement is one merged video of less than 15 minutes.

Suggested structure:

### 0:00–1:00 – Introduction
Show the presenter’s face. State:
- Name
- Student ID
- Project title
- Assigned responsibility

### 1:00–3:00 – Array and Searching
Demonstrate:
- Insert
- Delete
- Search
- Linear search
- Binary search
- Performance comparison

### 3:00–5:00 – Stack and Queue
Demonstrate:
- Push
- Pop
- Peek
- Enqueue
- Dequeue
- Empty conditions

### 5:00–7:00 – Linked List
Demonstrate:
- Insert
- Delete
- Search
- Display

### 7:00–10:00 – Graph
Demonstrate:
- Add vertex
- Add edge
- Display
- BFS
- DFS

### 10:00–12:00 – Performance
Explain:
- O(n)
- O(log n)
- O(V+E)
- Step counts
- Timing variation

### 12:00–14:00 – Integration
Run the complete application and demonstrate navigation through all modules.

### 14:00–15:00 – Closing
Summarise the implementation and group contributions.

Every member must appear on camera during their own section and demonstrate the relevant code and functionality.

---

# 17. Limitations

The system is intentionally designed as an educational console application rather than a production database system. It stores data in memory and therefore does not persist changes after the program closes. The integer-based array, stack, queue, and linked list are appropriate for demonstrating core data-structure concepts but could be generalised using Java generics in a production implementation.

Execution-time measurements are also sensitive to JVM warm-up and operating-system conditions. Consequently, timing should be interpreted as an illustrative performance indicator rather than a formal benchmark.

---

# 18. Future Enhancements

Potential improvements include:

1. Generic data structures using Java generics.
2. File-based persistence.
3. Weighted and directed graphs.
4. Dijkstra's shortest-path algorithm.
5. Sorting algorithms such as merge sort and quicksort.
6. Automated unit testing with JUnit.
7. Larger benchmark datasets.
8. CSV import/export.
9. A graphical user interface.
10. More detailed memory-use measurements.

---

# 19. Conclusion

The completed application provides an integrated demonstration of the core requirements for the CIT300 practical assignment. It implements an array, stack, queue, linked list, searching algorithms, graph operations, BFS, DFS, performance measurements, input validation, and a unified console interface.

The project also provides a foundation for explaining algorithmic complexity. Linear search demonstrates sequential O(n) behaviour, while binary search demonstrates logarithmic O(log n) behaviour on sorted data. BFS and DFS demonstrate O(V+E) graph traversal. Stack and queue operations demonstrate constant-time access for their principal operations.

The modular Java design supports maintainability and makes each contribution easy to demonstrate. The accompanying README and GitHub collaboration plan support the documentation and contribution requirements of the assignment. Before final submission, the group must replace all member placeholders with accurate details and ensure that the GitHub history and demonstration video reflect genuine individual contributions.

---

# 20. Final Submission Checklist

- [ ] Complete Java project implemented
- [ ] Array completed
- [ ] Stack completed
- [ ] Queue completed
- [ ] Linked List completed
- [ ] Linear Search completed
- [ ] Binary Search completed
- [ ] Graph completed
- [ ] BFS completed
- [ ] DFS completed
- [ ] Performance comparison completed
- [ ] Main menu integrated
- [ ] Input validation completed
- [ ] Empty-state handling completed
- [ ] README completed
- [ ] Real member names entered
- [ ] Real student IDs entered
- [ ] Genuine responsibilities recorded
- [ ] GitHub branches/commits/PRs created where applicable
- [ ] One merged demonstration video prepared
- [ ] Video under 15 minutes
- [ ] Every member's face visible during their own presentation
- [ ] Google Drive permissions checked if Google Drive is used
- [ ] LMS submission completed before the stated deadline
