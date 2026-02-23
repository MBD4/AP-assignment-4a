package dk.dtu.compute.course02324.assignment3.lists.implementations;

import dk.dtu.compute.course02324.assignment3.lists.types.List;

import jakarta.validation.constraints.NotNull;
import java.util.Comparator;

/**
 * An implementation of the interface {@link List} based on basic Java
 * arrays, which dynamically are adapted in size when needed.
 *
 * @param <E> the type of the list's elements.
 */
public class ArrayList<E> implements List<E> {

    /**
     * Constant defining the default size of the array when the
     * list is created. The value can be any (strictly) positive
     * number. Here, we have chosen <code>10</code>, which is also
     * Java's default for some array-based collection implementations.
     */
    final private int DEFAULT_SIZE = 10;

    /**
     * Current size of the list.
     */
    private int size = 0;

    /**
     *  The array for storing the elements of the
     */
    private E[] list = createEmptyArray(DEFAULT_SIZE);

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public @NotNull E get(int pos) throws IndexOutOfBoundsException {
        // throw new UnsupportedOperationException("This operation is not yet implemented!");
        // TODO needs implementation (Assignment 3a)
        cheksOutOfBoundsTest(pos);
        return list[pos];
    }

    @Override
    public E set(int pos, @NotNull E e) throws IndexOutOfBoundsException {
        // throw new UnsupportedOperationException("This operation is not yet implemented!");
        // TODO needs implementation (Assignment 3a)
        cheksOutOfBoundsTest(pos);
        cheksArgumentNull(e);
        E rVariable = list[pos];
        list[pos] = e;
        return rVariable;
    }

    @Override
    public boolean add(@NotNull E e) {
        // throw new UnsupportedOperationException("This operation is not yet implemented!");
        // TODO needs implementation (Assignment 3a)
        cheksArgumentNull(e);
        longerArray();
        list[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean add(int pos, @NotNull E e) throws IndexOutOfBoundsException {
        // throw new UnsupportedOperationException("This operation is not yet implemented!");
        // TODO needs implementation (Assignment 3a)
        cheksArgumentNull(e);
        if (pos < 0 || pos > size) {
            throw new IndexOutOfBoundsException();
        }
        longerArray();
        if (pos == size) {
            list[size] = e;
            size++;
            return true;
        }
        for (int i = size; i > pos; i--){
            list[i] = list[i-1];
        }
        list[pos] = e;
        size++;
        return true;
    }

    @Override
    public E remove(int pos) throws IndexOutOfBoundsException {
        // throw new UnsupportedOperationException("This operation is not yet implemented!");
        // TODO needs implementation (Assignment 3a)
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        E rVariable = list[pos];
        for (int i = pos; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;
        return rVariable;
    }

    @Override
    public boolean remove(E e) {
        // throw new UnsupportedOperationException("This operation is not yet implemented!");
        // TODO needs implementation (Assignment 3a)
        for (int i = 0; i < size; i++) {
            if (list[i].equals(e)) {
                remove(i);
                return true;
            }
        }
        return false;

    }

    @Override
    public int indexOf(E e) {
        // throw new UnsupportedOperationException("This operation is not yet implemented!");
        // TODO needs implementation (Assignment 3a)
        for (int i = 0; i < size; i++) {
            if (list[i].equals(e)) {
                return i;
            }
        }
        return - 1;

    }

    @Override
    public void sort(@NotNull Comparator<? super E> c) throws UnsupportedOperationException {
        // throw new UnsupportedOperationException("This operation is not yet implemented!");
        // TODO needs implementation (Assignment 3b)
        if (this instanceof SortedArrayList) {
            throw new UnsupportedOperationException();
        }
        if (c == null) {
            throw new IllegalArgumentException("Comparator, must not be null");
        }
        BubbleSort.sort(c, this);
    }

    /**
     * Creates a new array of type <code>E</code> with the given size.
     *
     * @param length the size of the array
     * @return a new array of type <code>E</code> and the given length
     */


    private E[] createEmptyArray(int length) {
        // there is unfortunately no really easy and elegant way to initialize
        // an array with a type coming in as a generic type parameter, but
        // the following is simple enough. And it is OK, since the array
        // is never passed out of this class.
        return (E[]) new Object[length];
    }

    // TODO probably some private helper methods here (avoiding duplicated code)
    //      (Assignment 3a)

    private void longerArray() {
        if (size == list.length) {
            E[] newArr = createEmptyArray(size + DEFAULT_SIZE);
            for (int i = 0; i < list.length; i++) {
                newArr[i] = list[i];
            }
            list = newArr;
        }
    }

    private void cheksOutOfBoundsTest(int pos) {
        if (pos == 0 && size == 0) {
            return;
        }
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("position " + pos + " out of bounds for size: " + size);
        }
    }

    public void cheksArgumentNull(E e) {
        if (e == null) {
            throw new IllegalArgumentException("argument must not be null");
        }
    }
}
