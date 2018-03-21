package util.collections;

/**
 * MyLinkedList:
 * - implements MyList and MyDeque interfaces;
 * - permits all elements (including null);
 * - includes methods of adding, getting and removing (to/at/)from the beginning and
 * (to/at/)from the end of the list;
 * - is not synchronised.
 *
 * @param <E> the type of entry
 */
public class MyLinkedList<E> implements MyList<E>, MyDeque<E> {

    private static int size = 0;
    // header is sth like a pointer, its value is always null when
    // next - is always the first element in list and
    // prev - the last.
    private Entry<E> header = new Entry<E>(null, null, null);

    public MyLinkedList() {
        header.next = header.prev = header;
    }

    private static class Entry<E> {
        E elem;
        Entry<E> next;
        Entry<E> prev;

        Entry(E elem, Entry<E> next, Entry<E> prev) {
            this.elem = elem;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e entry – element we want to add
     */
    @Override
    public void add(E e) {
        addBefore(e, header);
    }

    /**
     * Inserts element at the specified position in this list.
     * Shifts the element currently at that position (if any) to the right.
     *
     * @param i index where to put element
     * @param e inserted element
     */
    @Override
    public void add(int i, E e) {
        Entry<E> elAtI;
        if (i == size) {
            elAtI = header;
        } else {
            elAtI = entry(i);
        }
        addBefore(e, elAtI);
    }

    /**
     * Inserts an element at the beginning of the list.
     *
     * @param e element to be inserted
     */
    @Override
    public void addFirst(E e) {
        addBefore(e, header.next);
    }

    /**
     * Inserts an element to the end of the list.
     *
     * @param e element to be inserted
     */
    @Override
    public void addLast(E e) {
        addBefore(e, header);
    }

    /**
     * Returns an element at the specified position in array.
     *
     * @param i index of the element
     * @return element at i
     */
    @Override
    public E get(int i) {
        return entry(i).elem;
    }

    /**
     * Returns the first element of the list.
     *
     * @return element at i
     */
    @Override
    public E getFirst() {
        return header.next.elem;
    }

    /**
     * Returns the last element of the list.
     *
     * @return element at i
     */
    @Override
    public E getLast() {
        return header.prev.elem;
    }

    /**
     * Replaces the element at the specified position in this list
     * with the new element.
     *
     * @param i index of the element to be replaced
     * @param e new element
     */
    @Override
    public void set(int i, E e) {
        Entry<E> elAtI;
        if (i == size) {
            elAtI = header;
        } else {
            elAtI = entry(i);
        }
        Entry<E> newEntry = new Entry<>(e, elAtI.next, elAtI.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
    }

    /**
     * Removes the specified element.
     *
     * @param e element we want to remove
     */
    @Override
    public void remove(E e) {
        for (Entry<E> entry = header.next; entry != header; entry = entry.next) {
            if (e.equals(entry.elem)) {
                remove(entry);
                break;
            }
        }
    }

    /**
     * Removes an element at the specified position in array.
     *
     * @param i index of the element
     */
    @Override
    public void remove(int i) {
        Entry<E> deletedEl = entry(i);
        remove(deletedEl);
    }

    /**
     * Removes the first element from the list.
     */
    @Override
    public void removeFirst() {
        remove(header.next);
    }

    /**
     * Removes the last element from the list.
     */
    @Override
    public void removeLast() {
        remove(header.prev);
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
     * Returns the indexed entry.
     *
     * @param i index where entry lies
     * @return entry at i
     */
    private Entry<E> entry(int i) {
        if (i < 0 || i >= size)
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size);
        Entry<E> e = header;
        if (i < (size >> 1)) {
            for (int j = 0; j <= i; j++) {
                e = e.next;
            }
        } else {
            for (int j = size; j > i; j--) {
                e = e.prev;
            }
        }
        return e;
    }

    private void addBefore(E e, Entry<E> next) {
        Entry<E> newEntry = new Entry<>(e, next, next.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
    }

    private void remove(Entry<E> e) {
        e.prev.next = e.next;
        e.next.prev = e.prev;
        e.prev = e.next = null;
        e.elem = null;
        size--;
    }
}
