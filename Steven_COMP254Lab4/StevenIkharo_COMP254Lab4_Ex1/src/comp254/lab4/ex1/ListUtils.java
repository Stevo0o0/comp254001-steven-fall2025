package comp254.lab4.ex1;

import net.datastructures.Position;
import net.datastructures.PositionalList;

/**
 * Utility for PositionalList: find the 0-based index of a given Position p.
 * Walks from first() via next() until we reach p.
 * Runs in O(n).
 */
public class ListUtils {
    public static <E> int indexOf(PositionalList<E> list, Position<E> p) {
        if (p == null) throw new IllegalArgumentException("Position cannot be null.");
        int index = 0;
        Position<E> cur = list.first();
        while (cur != null) {
            if (cur == p) return index;         // identity check: same Position object
            cur = list.after(cur);
            index++;
        }
        // If your instructor prefers -1, change this to: return -1;
        throw new IllegalArgumentException("Position does not belong to this list.");
    }
}
