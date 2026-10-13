# Member 1 Guide: explanation, integration, Git, video

## 1. How it works (simple English)

**Array.** `IntArray` wraps a real `int[]` plus a counter `size`. Only slots `0..size-1`
are real data; the rest are ignored. Capacity never changes.

**Insert in the middle needs shifting.** Insert 15 at index 1 into `[10, 20, 30]`:
move 30 right, move 20 right, then write 15 -> `[10, 15, 20, 30]`.
The loop runs from the END (`for i = size; i > index; i--`) so nothing is overwritten.

**Delete shifts left.** Delete index 1 from `[10, 20, 30, 40]`: save 20, copy 30 and 40
one step left, `size--` -> `[10, 30, 40]`.

**Failed operations never corrupt data.** Every check (full, empty, bad index) happens
BEFORE any change.

**Linear Search.** Look at index 0, 1, 2 ... until a match or the end. Works on any data.
`[40,10,30,20]`, target 30 -> examines 40, 10, 30 -> index 2, 3 comparisons.

**Binary Search.** Needs sorted data. Look at the middle; if target is bigger, discard the
left half; if smaller, discard the right half. Sorted `[10,20,30,40,50]`, target 40:
middle=30 (<40) -> left=3; middle=40 found -> index 3, 2 comparisons.
`middle = left + (right - left) / 2` avoids integer overflow.

**Why sorted?** The "discard half" step is only valid if everything left of the middle is
smaller. On unsorted data it can throw away the target.

**Sorted copy.** `sortedCopy` copies the array and insertion-sorts the COPY. The original
order is untouched. The index found is a position in the copy, so it is labelled
"Index in sorted copy". Sorting is preparation, not part of binary search, and its time is
reported separately.

**Why binary can look slower on tiny arrays.** Timing is noisy (JVM warm-up). Use the
comparison count: linear ~n, binary ~log2(n). For 1000 elements: up to 1000 vs about 10.

## 2. Integration for Member 5 (no group code was available, so this is NOT yet integrated)

Create ONE array and share it:
```java
Scanner scanner = new Scanner(System.in);              // your one shared Scanner
ConsoleInput in = new ConsoleInput(scanner, System.out);
IntArray array = new IntArray(20);                     // one shared instance
ArrayMenu arrayMenu = new ArrayMenu(array, in, System.out);
SearchingMenu searchingMenu = new SearchingMenu(array, in, System.out);

// main menu case 1: arrayMenu.run();   case 5: searchingMenu.run();
```
Wrap the main loop in `try/catch (ConsoleInput.InputClosedException e)` to exit cleanly.

Performance Comparison screen:
```java
SearchComparison c = SearchComparison.run(array.toArray(), target);
SearchResult lin = c.getLinearResult();   // getComparisons(), getElapsedNanos(), isFound(), getIndex()
SearchResult bin = c.getBinaryResult();
System.out.println(c.toTable());          // or build your own table from the getters
```
Do not close `System.in` / the Scanner inside any module. Package: `member1`.

## 3. Git (run yourself; nothing was committed for you)
```bash
git status
git switch -c feature/member1-array-search
git add src/main/java/member1/IntArray.java
git commit -m "feat(array): implement insertion deletion search and display"
git add src/main/java/member1/SearchResult.java src/main/java/member1/SearchAlgorithms.java
git commit -m "feat(search): implement linear and binary search"
git add src/main/java/member1/SearchComparison.java
git commit -m "feat(search): add comparison metrics"
git add src/main/java/member1/ConsoleInput.java src/main/java/member1/ArrayMenu.java \
        src/main/java/member1/SearchingMenu.java src/main/java/member1/Member1Demo.java
git commit -m "feat(menu): add array and searching submenus"
git add src/test
git commit -m "test(member1): cover array and searching edge cases"
git add README.md MEMBER1_GUIDE.md
git commit -m "docs(member1): document implementation and complexity"
git push -u origin feature/member1-array-search
```
Then open a Pull Request into the group's main branch on GitHub. Do not force-push or
merge into main without your group's agreement. If your group already has a README, add
the Member 1 section to it instead of replacing it.

## 4. Video script (about 2.5 to 3 minutes, face visible the whole time)

**Intro (15s).** "Hello, I'm [NAME], student ID [ID]. I was responsible for the Array and
Searching parts of our Data Structure and Graph Performance Analyzer."

**Array (45s). Show `IntArray.java`.** "An array stores items in order in one block of
memory. My `IntArray` uses a real int array and a `size` counter, so unused slots are never
shown. To insert in the middle, I shift items right from the end to make room (show
`insertAt`). To delete, I shift items left and reduce size (show `deleteAt`). Both are
O(n); appending is O(1). Bad indexes, full arrays and empty arrays are rejected before
anything changes."

**Linear Search (25s). Show `linearSearch`.** "Linear search checks each item from the
start. It works on unsorted data. Best case O(1), worst case O(n)."

**Binary Search (45s). Show `binarySearch` and `sortedCopy`.** "Binary search checks the
middle and throws away half each time, so it is O(log n). It only works on sorted data,
because otherwise discarding half could remove the answer. So I make a sorted copy with
insertion sort and search that. The original array stays unchanged, and the index shown is
labelled as the sorted-copy index."

**Comparison (30s). Show `SearchComparison`.** "Both algorithms search the same sorted
data and target. I count how many elements each examines. Counts are the main result,
because timing on small arrays is noisy. Sorting time is shown separately."

**Integration (15s). Show `Member1Demo` / integration snippet.** "Member 5 creates one
`IntArray`, passes it to `ArrayMenu` and `SearchingMenu`, and calls
`SearchComparison.run` for the performance screen."

### Live demo sequence (capacity 5)
1. `java -cp out member1.Member1Demo`, enter capacity `5`.
2. Option 1 (Array Operations) -> Insert at end: 50, 10, 40, 20, 30.
3. Display Array -> `[50, 10, 40, 20, 30]`, 5 elements.
4. Delete by index 1 -> deleted 10. (Re-insert 10 at index 1 if you want 5 items again.)
5. Search Element 40 -> found at index. Return.
6. Option 2 (Searching Operations): Linear Search for 30; Binary Search for 40 (point out
   Original vs Sorted Copy); Compare for 40 (point out comparisons and the note on sort time).
7. Briefly show the tests: `java -cp out member1.RunAllTests` -> `Passed: 209  Failed: 0`.
8. Show the integration snippet in section 2.

Code to show on screen: `IntArray.insertAt`, `IntArray.deleteAt`, `SearchAlgorithms.linearSearch`,
`SearchAlgorithms.binarySearch`, `SearchAlgorithms.sortedCopy`, `SearchComparison.run`.
