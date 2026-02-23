package dk.dtu.compute.course02324.assignment3.lists;

import dk.dtu.compute.course02324.assignment3.lists.types.List;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This is an abstract test class with tests that should work for both
 * kinds of lists ("unsorted" and sorted). The respective types need to
 * be set up for the different types in respective subclasses of
 * this class in the {@see #setUp()} method;
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public abstract class TestForAllLists {

    protected List<String> list;

    final int TEST_SIZE = 10000;

    final NumberFormat format = new DecimalFormat("" + TEST_SIZE);

    @Before
    abstract public void setUp() throws Exception;

    @Test
    public void testListEmptyOnCreation() {
        Assert.assertTrue("List is not empty initially", list.isEmpty());
    }

    @Test
    public void testAddingElements() {
        String value1 = "Gamma";
        Assert.assertTrue(
                "Add should return true",
                list.add(value1));

        Assert.assertEquals(
                "Added element is not at position 0",
                value1,
                list.get(0));

        Assert.assertTrue(
                "Added element is not contained in list",
                list.contains(value1));

        Assert.assertEquals(
                "List size is not 1",
                1,
                list.size());

        String value2 = "Beta";
        Assert.assertTrue(
                "Add should return true",
                list.add(value2));

        Assert.assertTrue(
                "Added element is not contained in list",
                list.contains(value2));

        Assert.assertEquals(
                "List size is not 1",
                2,
                list.size());

        String value3 = "Alpha";
        Assert.assertTrue(
                "Add should return true",
                list.add(value3));

        Assert.assertTrue(
                "Added element is not contained in list",
                list.contains(value3));

        Assert.assertEquals(
                "List size is not 3",
                3,
                list.size());

        Assert.assertTrue(
                "Added value1 no longer contained in list",
                list.contains(value1));
        Assert.assertTrue(
                "Added value2 no longer contained in list",
                list.contains(value2));


        Assert.assertThrows(
                "Adding a null elememt does not raise an IllegalStateException",
                IllegalArgumentException.class,
                () -> { list.add(null); });

        Assert.assertTrue(
                "Added value1 no longer contained in list",
                list.contains(value1));
        Assert.assertTrue(
                "Added value2 no longer contained in list",
                list.contains(value2));
        Assert.assertTrue(
                "Added value3 no longer contained in list",
                list.contains(value3));

        Assert.assertEquals(
                "List size is not 3",
                3,
                list.size());

        String value4 = "Delta";
        Assert.assertFalse(
                "Remove of a not contained element should return false",
                list.remove(value4));
        Assert.assertEquals(
                "List size is not 3",
                3,
                list.size());

        Assert.assertTrue(
                "Added value1 no longer contained in list",
                list.contains(value1));
        Assert.assertTrue(
                "Added value2 no longer contained in list",
                list.contains(value2));
        Assert.assertTrue(
                "Added value3 no longer contained in list",
                list.contains(value3));

        Assert.assertTrue(
                "Remove of a contained element should return tre",
                list.remove(value2));
        Assert.assertEquals(
                "List size is not 2",
                2,
                list.size());

        Assert.assertTrue(
                "Added value1 no longer contained in list",
                list.contains(value1));
        Assert.assertFalse(
                "Added value2 is still contained in list",
                list.contains(value2));
        Assert.assertTrue(
                "Added value3 no longer contained in list",
                list.contains(value3));
    }

    @Test
    public void testAddingAndRemovingManyElements() {
        for (int i=0; i<TEST_SIZE; i++) {
            Assert.assertTrue(
                    "Add should return true",
                    list.add(i+". Test"));
        }

        Assert.assertEquals(
                "List size should be " + TEST_SIZE,
                TEST_SIZE,
                list.size());

        for (int i=0; i<TEST_SIZE; i= i+2) {
            Assert.assertTrue(
                    "Remove of existing element" + i + ". should return true",
                    list.remove(i + ". Test"));
        }

        Assert.assertEquals(
                "List size should be " + TEST_SIZE/2,
                TEST_SIZE/2,
                list.size());

        for (int i=0; i<TEST_SIZE; i= i+2) {
            Assert.assertFalse(
                    "Remove of no existing element" + i + ". should return false",
                    list.remove(i+". Test"));
        }

        Assert.assertEquals(
                "List size should be " + TEST_SIZE/2,
                TEST_SIZE/2,
                list.size());

        for (int i=1; i<TEST_SIZE; i= i+2) {
            Assert.assertTrue(
                    "Remove of existing element" + i + ". should return true",
                    list.remove(i+". Test"));
        }

        Assert.assertEquals(
                "List size should be 0",
                0,
                list.size());
    }

}