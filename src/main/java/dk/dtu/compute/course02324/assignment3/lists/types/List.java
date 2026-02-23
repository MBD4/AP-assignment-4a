package dk.dtu.compute.course02324.assignment3.lists.types;

import jakarta.validation.constraints.NotNull;
import java.util.Comparator;

/**
 * A sequence of objects (elements) of generic type E, where the
 * position of the object in the sequence matters. Elements can be
 * inserted at the end of the list or at a specific position in
 * the list; elements can also be replaced or removed. But, elements
 * of the list cannot be null.
 *
 * Like in arrays, the position of the first element of the list is
 * <code>0</code> and the last element of the list is at position
 * <code>{@link #size()}-1</code>.
 *
 * Note that there is a built-in interface in Java for that purpose:
 * {@link java.util.List} along with many different implementations.
 *
 * This interface is similar to Java's built-in lists. But for
 * simplicity reasons, this interface is not exactly the same and not
 * compatible to Java's built-in interface of lists.
 *
 * And for the purpose of this assignment, we do not use built-in
 * types and methods of Java for now. For later use in projects,
 * however, it is highly recommended to resort to built-in Java
 * types, whenever you can and when there is not a specific and good
 * reason for not using them.
 *
 * Note that in the declarations of the method below,
 * <code>IllegalArgumentExceptions</code> for parameters with
 * annotations <code>@NotNull</code> are not explicitly stated,
 * even though all methods will throw this exception when called
 * with <code>null</code> for these parameters. This is to ensure
 * that the list never contains <code>null</code> elements.
 *
 * @param <E> the type of the list's elements
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public interface List<E> {

    /**
     * Removes all objects from the list.
     */
    void clear();

    /**
     * Returns the size of (number of elements in) the list.
     *
     * @return size of the list
     */
    int size();

    /**
     * Returns <code>true</code> iff the list is empty (contains no elements).
     *
     * @return <code>true</code> iff the list is empty
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the element at the given position of the list. The list
     * will not be changed. If the given position <code>pos</code>is out
     * of the range of the list (i.e. does not satisfy
     * <code>0 < pos < {@link #size()}</code>), an
     * <code>IndexOutOfBoundsException</code> is thrown.
     *
     * @param pos the position of the element
     * @return the element at the given position of the list
     * @throws IndexOutOfBoundsException if the position is out of the
     *           range of the list
     */
    @NotNull E get(int pos) throws IndexOutOfBoundsException;

    /**
     * Replaces the element at the given position of the list by the given element.
     * There must be an element in the list at the given position. The element that
     * was previously in the list at that position is returned as the result.
     *
     * @param pos the given position
     * @param e the given element, which must not be <code>null</code>
     * @return the element that previously was at the given position
     * @throws IndexOutOfBoundsException if the position is out of the
     *           range of the list
     */
    @NotNull E set(int pos, @NotNull E e) throws IndexOutOfBoundsException;

    /**
     * Adds an element to the list. Typically, the element is added
     * at the end of the list. Returns <code>true</code>, if the element
     * could be added and the list has changed as a result of that;
     * returns false, if the list did not change (the element could
     * not be added).
     *
     * @param e the element to be inserted, which must not be <code>null</code>
     * @return <code>true</code> iff the list has changed
     */
    boolean add(@NotNull E e);

    /**
     * Adds an element to the list at the given position, all existing
     * elements in the list from this position and upwards are shifted
     * up by one position. Returns <code>true</code>, if the element
     * could be added and the list changed as a result of that; returns
     * <code>false</code> if the list did not change (the element could
     * not be added).
     *
     * @param pos the position at which the given element should be inserted
     * @param e the element to be inserted, which must not be <code>null</code>
     * @return <code>true</code> iff the list has changed
     * @throws IndexOutOfBoundsException if the pos is out of range
     *     (but <code>pos == size()</code> is OK
     */
    boolean add(int pos, @NotNull E e) throws IndexOutOfBoundsException;

    /**
     * Removes the element at the given position (and moves all
     * elements after this position down by one position).
     *
     * @param pos the position of the element to be removed
     * @return the element that was removed from the list
     * @throws IndexOutOfBoundsException when position is not in the
     *           range of the list
     */
    @NotNull E remove(int pos) throws IndexOutOfBoundsException;

    /**
     * Removes one occurrence of the given element from the list, if
     * the element occurs in the list. Typically, the fist occurrence
     * of element is removed. Returns <code>true</code>, if the list
     * changed.
     *
     * @param e the element to be removed
     * @return <code>true</code> iff the list has changed
     */
    boolean remove(E e);

    /**
     * Returns the index of an element equal (in the sense of Java's
     * <code>equals()</code>) to the given one. Typically, this is
     * the first equal element in the list. <code>-1</code> is returned
     * if no such element exists.
     *
     * @param e the given element to look for
     * @return the index of the given element or <code>-1</code>
     */
    int indexOf(E e);

    /**
     * Checks whether a given element is contained in the list. Formally,
     * it means that there is an element <code>elt</code> in the list with
     * <code>elt.equals(e)</code>.
     *
     * @param e the given element
     * @return <code>true</code> iff the element is contained in the list
     */
    default boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    /**
     * Sorts the list according to the order defined by the given
     * comparator.
     *
     * Some implementations of <code>List</code> might not support
     * sorting, in which case the operation throws an
     * <code>UnsupportedOperationException</code>.
     *
     * @param c the given comparator according to which the list should
     *          be sorted
     * @throws UnsupportedOperationException if the implementation of
     *           the list does not support sorting.
     */
    void sort(Comparator<? super E> c) throws UnsupportedOperationException;

}
