package util.maps;

import util.MyUtil;

/**
 * Interface, used by MyHashMap class.
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface MyMap<K, V> extends MyUtil {
    void put(K key, V value);

    V get(K key);

    void remove(K key);
}
