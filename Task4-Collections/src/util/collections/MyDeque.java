package util.collections;

import util.MyUtil;

/**
 * Interface used by MyLinkedList class.
 *
 * @param <E> type of entry
 */
public interface MyDeque<E> extends MyUtil {

    void addFirst(E e);
    void addLast(E e);

    E getFirst();
    E getLast();

    void removeFirst();
    void removeLast();
}
