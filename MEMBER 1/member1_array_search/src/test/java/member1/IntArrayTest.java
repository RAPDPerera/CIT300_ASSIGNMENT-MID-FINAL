package member1;

import static member1.TestSupport.*;

final class IntArrayTest {

    private static IntArray of(int capacity, int... values) {
        IntArray a = new IntArray(capacity);
        for (int v : values) a.append(v);
        return a;
    }

    static void run() {
        // 1-2 creation
        IntArray empty = new IntArray(3);
        check("new array is empty", empty.isEmpty() && empty.size() == 0 && empty.capacity() == 3);
        equal("empty toString", "[]", empty.toString());
        throwsEx("capacity 0 rejected", IllegalArgumentException.class, () -> new IntArray(0));
        throwsEx("negative capacity rejected", IllegalArgumentException.class, () -> new IntArray(-5));

        // 3 append
        IntArray a = new IntArray(5);
        a.append(10);
        arrayEquals("append", new int[]{10}, a.toArray());

        // 4-6 insert beginning / middle / end
        a.append(20); a.append(30);
        a.insertFirst(5);
        arrayEquals("insert at 0", new int[]{5, 10, 20, 30}, a.toArray());
        a.insertAt(2, 15);
        arrayEquals("insert middle", new int[]{5, 10, 15, 20, 30}, a.toArray());
        IntArray b = of(5, 10, 20, 30);
        b.insertAt(3, 40);   // index == size
        arrayEquals("insert at size", new int[]{10, 20, 30, 40}, b.toArray());
        IntArray sample = of(5, 10, 20, 30);
        sample.insertAt(1, 15);
        equal("spec example", "[10, 15, 20, 30]", sample.toString());

        // 7-8 bad index, 22 unchanged
        IntArray c = of(5, 1, 2, 3);
        throwsEx("negative insert index", IndexOutOfBoundsException.class, () -> c.insertAt(-1, 9));
        throwsEx("insert index > size", IndexOutOfBoundsException.class, () -> c.insertAt(4, 9));
        arrayEquals("failed insert leaves array unchanged", new int[]{1, 2, 3}, c.toArray());

        // 9-10 full
        IntArray full = of(3, 1, 2, 3);
        check("isFull", full.isFull());
        throwsEx("insert when full", IllegalStateException.class, () -> full.append(4));
        throwsEx("insertAt when full", IllegalStateException.class, () -> full.insertAt(0, 4));
        arrayEquals("full array keeps data", new int[]{1, 2, 3}, full.toArray());

        // 11-13 delete
        IntArray d = of(5, 10, 20, 30, 40);
        equal("delete first returns value", 10, d.deleteAt(0));
        arrayEquals("after delete first", new int[]{20, 30, 40}, d.toArray());
        equal("delete middle returns value", 30, d.deleteAt(1));
        arrayEquals("after delete middle", new int[]{20, 40}, d.toArray());
        equal("delete last returns value", 40, d.deleteAt(1));
        arrayEquals("after delete last", new int[]{20}, d.toArray());

        // 14-15 invalid delete
        IntArray e = new IntArray(3);
        throwsEx("delete from empty", IllegalStateException.class, () -> e.deleteAt(0));
        throwsEx("delete value from empty", IllegalStateException.class, () -> e.deleteFirstOccurrence(1));
        IntArray f = of(5, 1, 2);
        throwsEx("delete negative index", IndexOutOfBoundsException.class, () -> f.deleteAt(-1));
        throwsEx("delete index == size", IndexOutOfBoundsException.class, () -> f.deleteAt(2));
        arrayEquals("failed delete leaves array unchanged", new int[]{1, 2}, f.toArray());

        // 16-17 search
        IntArray s = of(5, 10, 20, 30, 40);
        equal("indexOf existing", 2, s.indexOf(30));
        equal("indexOf missing", -1, s.indexOf(99));
        equal("indexOf on empty", -1, new IntArray(2).indexOf(1));

        // 18 duplicates
        IntArray dup = of(6, 7, 3, 7, 7);
        equal("duplicates: first index", 0, dup.indexOf(7));
        equal("delete by value removes first", 0, dup.deleteFirstOccurrence(7));
        arrayEquals("only first duplicate removed", new int[]{3, 7, 7}, dup.toArray());
        equal("delete missing value returns -1", -1, dup.deleteFirstOccurrence(99));
        arrayEquals("missing value deletes nothing", new int[]{3, 7, 7}, dup.toArray());

        // 19 negatives
        IntArray neg = of(4, -5, 0, -1);
        equal("negative search", 2, neg.indexOf(-1));
        equal("negative delete", -5, neg.deleteAt(0));

        // 20 display shows only active elements
        IntArray disp = of(10, 1, 2, 3);
        disp.deleteAt(2);
        equal("display active only", "[1, 2]", disp.toString());
        equal("stale slot not visible", 2, disp.toArray().length);

        // 21 order preserved
        IntArray ord = of(10, 1, 2, 3, 4, 5);
        ord.insertAt(2, 99);
        ord.deleteAt(0);
        arrayEquals("order preserved", new int[]{2, 99, 3, 4, 5}, ord.toArray());

        // encapsulation: snapshot is defensive
        IntArray enc = of(3, 1, 2);
        int[] snap = enc.toArray();
        snap[0] = 999;
        arrayEquals("snapshot modification does not affect array", new int[]{1, 2}, enc.toArray());
        throwsEx("get invalid index", IndexOutOfBoundsException.class, () -> enc.get(2));
        equal("get valid", 2, enc.get(1));
    }
}
