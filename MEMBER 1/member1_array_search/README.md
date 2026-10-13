# Data Structure and Graph Performance Analyzer (CIT300 Graded Practical Assignment 2)

> This README currently holds **Member 1 only**. Merge the section below into the
> group README; keep every member's name, ID and contribution there.

## Member 1 — Array and Searching

Student Name: [ACTUAL FULL NAME]
Student ID: [ACTUAL STUDENT ID]

Assigned Responsibility:
Array and Searching Implementation

Individual Contribution:
- Implemented integer array operations (fixed capacity, logical size)
- Implemented insertion (beginning / index / end) and deletion (by index / by value)
- Implemented array searching and display
- Implemented Linear Search
- Implemented Binary Search
- Implemented sorted-copy preparation (insertion sort)
- Implemented search comparison metrics (comparisons + nanosecond timing)
- Added input validation and error handling
- Tested array and searching functionality (209 automated checks)
- Prepared reusable interfaces for integration (Member 5)

### Classes (`src/main/java/member1`)
| Class | Responsibility |
|---|---|
| `IntArray` | Array storage + insert/delete/search/display state |
| `SearchAlgorithms` | Manual linear search, binary search, insertion-sort copy |
| `SearchResult` | Immutable result: algorithm, target, found, index, comparisons, nanos, index context |
| `SearchComparison` | Fair Linear-vs-Binary comparison on the same sorted copy |
| `ConsoleInput` | Validated line-based input (shared Scanner, EOF-safe) |
| `ArrayMenu` / `SearchingMenu` | Console submenus sharing one `IntArray` |
| `Member1Demo` | **Temporary** standalone entry point (not the group's main app) |

### Compile and run
```bash
mkdir -p out
javac -d out $(find src -name "*.java")      # Windows PowerShell: javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out member1.RunAllTests             # tests
java -cp out member1.Member1Demo             # standalone demo
```
Requires Java 8 or later (developed and verified on JDK 21). No external libraries.

### Rules
- Indexes are zero-based. Insert allows index `0..size`; delete allows `0..size-1`.
- Array search and Linear Search return the **first** match.
- Binary Search needs sorted data; the menus search a **sorted copy** and label the
  index as "Index in sorted copy". The original array is never modified.
- With duplicates, Binary Search returns *a* matching index, not necessarily the first.
- Comparison = one array element examined (same rule for both searches).

### Complexity
| Operation | Worst-case time |
|---|---|
| Access by index | O(1) |
| Append (capacity free) | O(1) |
| Insert / delete at beginning or middle | O(n) |
| Linear search | O(n) (best O(1)), space O(1) |
| Display | O(n) |
| Binary search (sorted input) | O(log n) (best O(1)), space O(1) |
| Insertion sort preparation | O(n²) time, O(n) extra memory |

### Known limitations
- Integer elements only; capacity is fixed when the array is created.
- Timings on small arrays are noisy; comparison counts are the main metric.
- Group-wide integration (main menu, Performance Comparison screen) belongs to Member 5.
