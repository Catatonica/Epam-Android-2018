package util.collections;

import util.MyUtil;

/**
 * Interface, containing common methods for MyArrayList
 * and MyLinkedList classes.
 *
 * @param <E> the type of entry
 */
public interface MyList<E> extends MyUtil {

    void add(E e);
    void add(int i, E e);

    E get(int i);

    void set(int i, E e);

    void remove(E e);
    void remove(int i);
}
