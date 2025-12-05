package comp254.lab6.ex1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public abstract class AbstractHashMap<K,V> {
    protected int n = 0;               // number of entries
    protected int capacity;            // table length
    private int prime;
    private long scale, shift;
    private double maxLoad;            // user-specified load factor

    // constructors
    public AbstractHashMap(int cap, int p, double loadFactor) {
        prime = p;
        capacity = cap;
        maxLoad = loadFactor;
        Random rand = new Random();
        scale = rand.nextInt(prime-1) + 1;
        shift = rand.nextInt(prime);
        createTable();
    }

    public AbstractHashMap(int cap, double loadFactor) { this(cap, 109345121, loadFactor); }
    public AbstractHashMap(double loadFactor) { this(17, 109345121, loadFactor); }
    public AbstractHashMap() { this(17, 109345121, 0.5); }  // default

    // public methods
    public int size() { return n; }
    public abstract Iterable<Entry<K,V>> entrySet();
    public V get(K key) { return bucketGet(hashValue(key), key); }
    public V remove(K key) { return bucketRemove(hashValue(key), key); }

    public V put(K key, V value) {
        V answer = bucketPut(hashValue(key), key, value);
        if ((double)n / capacity > maxLoad)   // check load factor
            resize(2 * capacity - 1);
        return answer;
    }

    // private utilities
    private int hashValue(K key) {
        return (int)((Math.abs(key.hashCode()*scale + shift) % prime) % capacity);
    }

    private void resize(int newCap) {
        ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
        for (Entry<K,V> e : entrySet())
            buffer.add(e);
        capacity = newCap;
        createTable();
        n = 0;
        for (Entry<K,V> e : buffer)
            put(e.getKey(), e.getValue());
    }

    // abstract methods
    protected abstract void createTable();
    protected abstract V bucketGet(int h, K k);
    protected abstract V bucketPut(int h, K k, V v);
    protected abstract V bucketRemove(int h, K k);

    // simple inner class for entries
    protected static class Entry<K,V> {
        private K key; private V value;
        public Entry(K k, V v) { key = k; value = v; }
        public K getKey() { return key; }
        public V getValue() { return value; }
        public void setValue(V v) { value = v; }
    }
}
