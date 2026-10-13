package member1;

import static member1.TestSupport.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

final class MenuTest {

    private static String runArrayMenu(IntArray array, String script) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(buffer);
        new ArrayMenu(array, new ConsoleInput(new Scanner(script), out), out).run();
        return buffer.toString();
    }

    private static String runSearchMenu(IntArray array, String script) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(buffer);
        new SearchingMenu(array, new ConsoleInput(new Scanner(script), out), out).run();
        return buffer.toString();
    }

    static void run() {
        // insert (append) then return
        IntArray shared = new IntArray(5);
        runArrayMenu(shared, "1\n3\n20\n5\n");
        arrayEquals("menu insert at end", new int[]{20}, shared.toArray());

        // state is shared: search menu finds value inserted by array menu
        String found = runSearchMenu(shared, "1\n20\n4\n");
        check("shared state: linear search finds 20", found.contains("Found") && found.contains("Comparisons : 1"));
        String bin = runSearchMenu(shared, "2\n20\n4\n");
        check("binary menu labels sorted copy index", bin.contains("Index in sorted copy"));
        String cmp = runSearchMenu(shared, "3\n20\n4\n");
        check("compare menu prints table", cmp.contains("SEARCH PERFORMANCE"));

        // insert at index + delete by index + delete by value
        runArrayMenu(shared, "1\n1\n10\n5\n");              // insert 10 at beginning
        arrayEquals("menu insert beginning", new int[]{10, 20}, shared.toArray());
        runArrayMenu(shared, "1\n2\n15\n1\n5\n");           // insert 15 at index 1
        arrayEquals("menu insert at index", new int[]{10, 15, 20}, shared.toArray());
        runArrayMenu(shared, "2\n1\n1\n5\n");               // delete index 1
        arrayEquals("menu delete by index", new int[]{10, 20}, shared.toArray());
        runArrayMenu(shared, "2\n2\n10\n5\n");              // delete value 10
        arrayEquals("menu delete by value", new int[]{20}, shared.toArray());

        // invalid inputs do not crash and do not change data
        String bad = runArrayMenu(shared, "abc\n\n99999999999\n9\n5\n");
        check("text input rejected", bad.contains("Invalid input."));
        check("empty input rejected", bad.contains("cannot be empty"));
        check("out-of-range int rejected", bad.contains("Number out of range"));
        check("invalid menu choice rejected", bad.contains("Invalid choice"));
        arrayEquals("array unchanged by bad input", new int[]{20}, shared.toArray());

        // bad index and invalid delete index keep array unchanged
        String badIdx = runArrayMenu(shared, "1\n2\n7\n50\n5\n");
        check("bad insert index reported", badIdx.contains("Error:") && badIdx.contains("unchanged"));
        String badDel = runArrayMenu(shared, "2\n1\n-3\n5\n");
        check("bad delete index reported", badDel.contains("Error:"));
        arrayEquals("array still unchanged", new int[]{20}, shared.toArray());

        // empty array handling
        IntArray empty = new IntArray(2);
        check("delete on empty reported", runArrayMenu(empty, "2\n5\n").contains("Array is empty"));
        check("display empty", runArrayMenu(empty, "4\n5\n").contains("Array is empty."));
        check("search empty", runArrayMenu(empty, "3\n5\n").contains("Array is empty"));
        check("binary on empty reported", runSearchMenu(empty, "2\n4\n").contains("Array is empty"));

        // full array
        IntArray full = new IntArray(1);
        full.append(1);
        check("insert into full reported", runArrayMenu(full, "1\n5\n").contains("Array is full"));
        arrayEquals("full array unchanged", new int[]{1}, full.toArray());

        // not found + display + search messages
        String nf = runArrayMenu(shared, "3\n999\n5\n");
        check("array search not found", nf.contains("not found"));
        check("display shows total", runArrayMenu(shared, "4\n5\n").contains("Total Elements: 1"));

        // end of input handled gracefully
        String eof = runArrayMenu(new IntArray(2), "");
        check("EOF handled in array menu", eof.contains("Input closed"));
        String eof2 = runSearchMenu(new IntArray(2), "1\n");
        check("EOF handled in search menu", eof2.contains("Input closed"));

        // repeated operations
        IntArray rep = new IntArray(5);
        runArrayMenu(rep, "1\n3\n1\n1\n3\n2\n1\n3\n3\n5\n");
        arrayEquals("repeated operations", new int[]{1, 2, 3}, rep.toArray());
    }
}
