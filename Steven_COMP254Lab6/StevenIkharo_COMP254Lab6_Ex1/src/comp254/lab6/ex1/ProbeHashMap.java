package comp254.lab6.ex1;

/**
 * ProbeHashMap: simplified linear probing implementation focusing on constructors.
 * (This class requires full probing logic in your real project.)
 */
public class ProbeHashMap<K,V> extends AbstractHashMap<K,V> {

    // underlying arrays for keys and values (example)
    private Object[] keys;
    private Object[] values;

    public ProbeHashMap() { this(101, 109345121, 0.5); }

    public ProbeHashMap(int cap, long p) { this(cap, p, 0.5); }

    // NEW constructor with load factor
    public ProbeHashMap(int cap, long p, double maxLoadFactor) {
        super(cap, p, maxLoadFactor);
        keys = new Object[capacity];
        values = new Object[capacity];
    }

    @Override
    public V put(K key, V value) {
        // simplified linear probing insertion (no tombstones handling here)
        int idx = Math.floorMod(key.hashCode(), capacity);
        while (keys[idx] != null && !((K)keys[idx]).equals(key)) {
            idx = (idx + 1) % capacity;
        }
        if (keys[idx] == null) {
            keys[idx] = key;
            values[idx] = value;
            n++;
            checkResize();
            return null;
        } else {
            V old = (V)values[idx];
            values[idx] = value;
            return old;
        }
    }

    @Override
    public V get(K key) {
        int idx = Math.floorMod(key.hashCode(), capacity);
        int start = idx;
        while (keys[idx] != null) {
            if (((K)keys[idx]).equals(key)) return (V)values[idx];
            idx = (idx + 1) % capacity;
            if (idx == start) break;
        }
        return null;
    }

    @Override
    protected void resize(int newCapacity) {
        // rehash into new arrays (probe-style)
        Object[] oldKeys = keys;
        Object[] oldValues = values;
        int oldCap = capacity;
        capacity = newCapacity;
        keys = new Object[capacity];
        values = new Object[capacity];
        n = 0;
        for (int i = 0; i < oldCap; i++) {
            if (oldKeys[i] != null) {
                put((K)oldKeys[i], (V)oldValues[i]); // reinsert (this will update n and may resize recursively)
            }
        }
    }
}
