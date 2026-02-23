package dk.dtu.compute.course02324.assignment3.lists;

import dk.dtu.compute.course02324.assignment3.lists.implementations.ArrayList;
import dk.dtu.compute.course02324.assignment3.lists.implementations.GenericComparator;
import dk.dtu.compute.course02324.assignment3.lists.implementations.SortedArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This test class sets up ({@link #setUp()}}) and adds some more
 * tests for {@link SortedArrayList} based on {@link TestForAllLists}.
 */
public class TestArrayLists extends TestForAllLists{

    @Before
    public void setUp() throws Exception {
        this.list = new ArrayList<>();
    }

    @Test
    public void testAddingElementsInFront() {

        for (int i = 0; i < TEST_SIZE; i++) {
            Assert.assertTrue(
                    "Add should return true",
                    list.add(0, format.format(i) + ". Test"));
        }

        for (int i = 0; i + 1 < TEST_SIZE; i++) {
            Assert.assertTrue(
                    "Initially, neighbouring elements should be out of order",
                    list.get(i).compareTo(list.get(i + 1)) > 0);
        }

        Assert.assertThrows(
                "Calling sort(null) should cause an exception",
                IllegalArgumentException.class,
                () -> { list.sort(null); } );

        list.sort(new GenericComparator<>());

        // the following test is redundant with the test after it!
        for (int i = 0; i + 1 < TEST_SIZE; i++) {
            Assert.assertTrue(
                    "After sorting, neighbouring elements should be in order",
                    list.get(i).compareTo(list.get(i + 1)) < 0);
        }

        for (int i = 0; i < TEST_SIZE; i++) {
            Assert.assertEquals(
                    "Element i is at wrong position",
                    i,
                    list.indexOf(format.format(i) + ". Test"));
        }

        for (int i = list.size()-1; i > 0; i= i - 2) {
            String element = list.remove(i);
            String expected = format.format(i) + ". Test";
            Assert.assertEquals(
                    "Removed element has unexpected value",
                    expected,
                    element);
        }

        Assert.assertEquals(
                "List size is wrong",
                TEST_SIZE/2,
                list.size());
    }

    // TODO: there could be some more tests concerning the methods
    //       clear(), isEmpty(), set(i, E) and add(i, E)

}
