package comp254.lab4.ex1;

import net.datastructures.Position;
import net.datastructures.PositionalList;

public class ListUtils {
    public static <E> int indexOf(PositionalList<E> list, Position<E> p) {
        if (p == null) throw new IllegalArgumentException("Position cannot be null.");
        int index = 0;
        Position<E> cur = list.first();
        while (cur != null) {
            if (cur == p) return index;
            cur = list.after(cur);
            index++;
        }
        throw new IllegalArgumentException("Position does not belong to this list.");
    }

}
