# CIT300 Test Cases

| ID | Test Case | Input / Action | Expected Result | Status |
|---|---|---|---|---|
| TC01 | Main menu | Start application | Main menu displayed with 9 options | PASS |
| TC02 | Array display | Array > Display | Seeded array displayed | PASS |
| TC03 | Array insertion | Insert 99 at valid index | Value inserted | PASS |
| TC04 | Array invalid index | Enter index outside range | Validation message shown | PASS |
| TC05 | Array search | Search for 42 | Correct index returned | PASS |
| TC06 | Empty stack pop | Clear stack, then Pop | Stack underflow message | PASS |
| TC07 | Stack peek | Push 55, then Peek | 55 shown as top | PASS |
| TC08 | Stack pop | Push 55, then Pop | 55 removed and returned | PASS |
| TC09 | Empty queue dequeue | Clear queue, then Dequeue | Queue underflow message | PASS |
| TC10 | Queue front | Enqueue 555, then Peek | Front value displayed correctly | PASS |
| TC11 | Queue FIFO | Enqueue then Dequeue | Oldest value removed first | PASS |
| TC12 | Linked-list insert first | Insert 99 at beginning | 99 becomes head | PASS |
| TC13 | Linked-list insert last | Insert 44 at end | 44 becomes final node | PASS |
| TC14 | Linked-list search | Search for 22 | Correct position returned | PASS |
| TC15 | Linked-list delete | Delete 22 | Node removed | PASS |
| TC16 | Linear search | Search for 42 | Target found and steps displayed | PASS |
| TC17 | Binary search | Search for 42 | Target found in sorted copy | PASS |
| TC18 | Search comparison | Compare target 42 | Linear and binary results/steps displayed | PASS |
| TC19 | Graph display | Graph > Display | Adjacency list displayed | PASS |
| TC20 | BFS | BFS from A | A, B, C, D, E, F traversal displayed | PASS |
| TC21 | DFS | DFS from A | A, B, D, E, F, C traversal displayed | PASS |
| TC22 | Vertex search | Search vertex A | Vertex exists message | PASS |
| TC23 | Performance | Performance Comparison | Search/traversal steps and timings displayed | PASS |
| TC24 | Display all | Display All Results | All structures displayed | PASS |
| TC25 | Invalid menu | Enter non-numeric menu value | Input validation message | PASS |

## Boundary and Error Tests
- Empty stack pop and peek are handled without program termination.
- Empty queue dequeue and peek are handled without program termination.
- Invalid menu input is rejected.
- Invalid array indexes are rejected.
- Missing graph vertices are rejected when used for traversal or edge creation.
- Duplicate graph vertices are rejected.
- Stack and queue capacity limits are checked.
