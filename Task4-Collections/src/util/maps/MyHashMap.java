package util.maps;

import static java.util.Objects.hash;

/**
 * MyHashMap
 * - permits null values and the null key;
 * - makes no guarantees as to the order of the map (and that the order will remain constant over time);
 * - provides constant-time performance for the basic operations (get and put),
 * assuming the hash function disperses the elements properly among the buckets.;
 * - iteration over collection views requires time proportional to the "capacity"
 * of the HashMap instance (the number of buckets) plus its size (the number of key-value mappings);
 * - this implementation is not synchronized
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    private static final int INITIAL_CAPACITY = 16; // the initial number of buckets in the hash table
    private static final float LOAD_FACTOR = 0.75f; // a measure of how full the hash table is allowed
    // to get before its capacity is automatically increased
    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private Entry[] table; // the table, resized as necessary
    private int size; // the number of key-value mappings contained in this map
    private float loadFactor;
    private int threshold; // the next size value at which to resize (capacity * load factor)

    public MyHashMap() {
        loadFactor = LOAD_FACTOR;
        threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
        table = new Entry[INITIAL_CAPACITY];
    }

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        int hash;

        Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced,
     * otherwise, new entry is added.
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            putForNullKey(value);
            return;
        }
        int hash = hash(key.hashCode()); // Applies a supplemental hash function to a given hashCode.
        // This is critical because HashMap uses power-of-two length hash tables.
        // Note: Null keys always map to hash 0, thus index 0.
        int i = indexFor(hash, table.length);
        for (Entry e = table[i]; e != null; e = e.next) {
            Object k = e.key;
            if (e.hash == hash && k == key || key.equals(k)) {
                e.value = value;
                return;
            }
        }
        addEntry(hash, key, value, i);
    }

    private void putForNullKey(V value) {
        for (Entry e = table[0]; e != null; e = e.next) {
            Object k = e.key;
            if (k == null) {
                e.value = value;
                return;
            }
        }
        addEntry(0, null, value, 0);
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     *
     * @param key key with which the specified value is associated
     * @return value under this key
     */
    @Override
    public V get(K key) {
        if (key == null) {
            return getForNullKey();
        }
        int hash = hash(key.hashCode());
        for (Entry e = table[indexFor(hash, table.length)]; e != null; e = e.next) {
            Object k = e.key;
            if (e.hash == hash && k == key || key.equals(k)) {
                return (V) e.value;
            }
        }
        return null;
    }

    private V getForNullKey() {
        for (Entry e = table[0]; e != null; e = e.next) {
            if (e.key == null) {
                return (V) e.value;
            }
        }
        return null;
    }

    /**
     * Removes the entry associated with the specified key.
     *
     * @param key key, containing values for deleting
     */
    @Override
    public void remove(Object key) {
        int hash = (key == null) ? 0 : hash(key.hashCode());
        int i = indexFor(hash, table.length);
        Entry prev = table[i];
        Entry e = prev;

        while (e != null) {
            Entry next = e.next;
            Object k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k)))) {
                size--;
                if (prev == e) {
                    table[i] = next;
                } else {
                    prev.next = next;
                }
            }
            prev = e;
            e = next;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns index for hash code h.
     *
     * @param h      hash
     * @param length table length
     * @return index in table, where element will be placed
     */
    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    /**
     * Adds a new entry with the specified key, value and hash code to the specified bucket.
     * It is the responsibility of this method to resize the table if appropriate.
     *
     * @param hash        hashcode, by which you'll be able to find the value
     * @param key         key with which the specified value is to be associated
     * @param value       value to be associated with the specified key
     * @param bucketIndex index of bucket, where value is
     */
    private void addEntry(int hash, K key, V value, int bucketIndex) {
        Entry e = table[bucketIndex];
        table[bucketIndex] = new Entry<K, V>(hash, key, value, e);
        if (size++ >= threshold)
            resize(2 * table.length);
    }

    /**
     * Rehashes the contents of this map into a new array with a larger capacity.
     * This method is called automatically when the number of keys in this map reaches its threshold.
     * If current capacity is MAXIMUM_CAPACITY, this method does not resize the map,
     * but sets threshold to Integer.MAX_VALUE.
     *
     * @param newCapacity the new capacity, MUST be a power of two
     */
    private void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
        threshold = (int) (newCapacity * loadFactor);
    }

    /**
     * Transfers all entries from current table to newTable.
     *
     * @param newTable reinitialized old table
     */
    private void transfer(Entry[] newTable) {
        Entry[] src = table;
        int newCapacity = newTable.length;
        for (int j = 0; j < src.length; j++) {
            Entry e = src[j];
            if (e != null) {
                src[j] = null;
                do {
                    Entry next = e.next;
                    int i = indexFor(e.hash, newCapacity);
                    e.next = newTable[i];
                    newTable[i] = e;
                    e = next;
                } while (e != null);
            }
        }
    }
}
