package comp254.lab6.ex2;

// Method for Exercise 2 — Lab 6
// Adds containKey(K key) to SortedTableMap

public boolean containKey(K key) {
    // Find the index where the key should be
    int j = findIndex(key);

    // Key exists only if:
    //  (1) j is within the table bounds, AND
    //  (2) the key at table[j] matches the given key
    return j < size() && compare(key, table.get(j)) == 0;
}

