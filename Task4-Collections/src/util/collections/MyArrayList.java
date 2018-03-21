package util.collections;

/**
 * MyArrayList:
 * - resizable-array implementation of the MyList interface;
 * - permits all elements, including null.
 * - the size, isEmpty, get, set operations run in constant time;
 * - adding n elements requires O(n) time;
 * - this implementation is not synchronized.
 *
 * @param <E> the type of entry
 */
public class MyArrayList<E> implements MyList<E> {

    private static final int INITIAL_CAPACITY = 10;

    private E array[]; // buffer into which the elements of the ArrayList are stored
    private int size = 0; // the number of elements array contains

    public MyArrayList() {
        array = (E[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param e entry – element we want to add
     */
    @Override
    public void add(final E e) {
        if (size() == array.length) {
            reinitializeMas(array.length);
        }
        array[size++] = e;
    }

    /**
     * Inserts element at the specified position in this list.
     * Shifts the element currently at that position (if any) to the right.
     *
     * @param i index where to put element
     * @param e inserted element
     */
    @Override
    public void add(final int i, final E e) {
        if (i > size) {
            System.out.println("Index out of bounds");
            return;
        }
        if (size() == array.length - 1) {
            reinitializeMas(array.length);
        }
        System.arraycopy(array, i, array, i + 1, Math.abs(size - i));
        array[i] = e;
        size++;
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
        if (i >= size) {
            System.out.println("Index out of bounds");
            return;
        }
        array[i] = e;
    }

    /**
     * Returns an element at the specified position in array.
     *
     * @param i index of the element
     * @return element at i
     */
    @Override
    public E get(final int i) {
        return (E) array[i];
    }

    /**
     * Removes an element at the specified position in array.
     *
     * @param i index of the element
     */
    @Override
    public void remove(final int i) {
        int numMoved = size - i - 1;
        System.arraycopy(array, i + 1, array, i, numMoved);
        array[--size] = null;
    }

    /**
     * Removes the specified element.
     *
     * @param e element we want to remove
     */
    @Override
    public void remove(final E e) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == e) {
                remove(i);
            }
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
     * Creates an array of new length if it isn't enough place for storing elements.
     *
     * @param length oldLength
     */
    private void reinitializeMas(int length) {
        length = (length * 3) / 2 + 1;
        E masCopy[] = (E[]) new Object[size];
        System.arraycopy(array, 0, masCopy, 0, size);
        array = (E[]) new Object[length];
        System.arraycopy(masCopy, 0, array, 0, size);
    }
}
