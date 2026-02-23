package dk.dtu.compute.course02324.assignment3.lists.types;

import jakarta.validation.constraints.NotNull;
import java.util.Comparator;

/**
 * This is an extended interface of {@link List} which maintains a
 * sorted List of all its elements at all times. The maintained
 * order of the list should come from element type, which implements
 * the {@link Comparable<E>} interface.
 *
 * Note that this breaks the contract of {@link List} in that some
 * are not legal or not supported anymore, since elements cannot
 * be replaced ({@link #set(int, Comparable)}) or added
 * ({@link #add(int, Comparable)}) at a specific position of the list
 * any if the added element would be out of order at this position.
 * These operations will throw an {@link UnsupportedOperationException}.
 * Likewise, the {@link #sort(Comparator)} operation will throw an
 * {@link UnsupportedOperationException} since the list is supposed
 * to maintain the order of its comparable elements at all times;
 * this does not break the contract of {@link List} since {@link List}
 * allows implementations not to support them.
 *
 * @param <E> the type of the list's elements
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public interface SortedList<E extends Comparable<E>> extends List<E> {

    /**
     * This operation is not supported by {@link SortedList<E>}.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    default E set(int pos, @NotNull E e) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Operation set(int pos, E e) not allowed on SortedLists");
    }

    /**
     * This operation is not supported by {@link SortedList<E>}.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    default boolean add(int pos, @NotNull E e) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Operation add(int pos, E e) not allowed on SortedLists");
    }

    /**
     * This operation is not supported by {@link SortedList<E>}.
     *
     * @throws UnsupportedOperationException always
     */
    @Override
    default void sort(@NotNull Comparator<? super E> c) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Operation sort(Comparator<? super E> c) not allowed on SortedLists");
    }

}
