package dk.dtu.compute.course02324.assignment3.lists.uses;


import dk.dtu.compute.course02324.assignment3.lists.implementations.ArrayList;
import dk.dtu.compute.course02324.assignment3.lists.implementations.BubbleSort;
import dk.dtu.compute.course02324.assignment3.lists.implementations.GenericComparator;
import dk.dtu.compute.course02324.assignment3.lists.implementations.SortedArrayList;
import dk.dtu.compute.course02324.assignment3.lists.types.List;

import java.util.Comparator;

/**
 * To quickly play with the list implementations, this class implements some
 * uses in the static main method.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class TestLists {

    public static Comparator<Person> comparator = new GenericComparator<>();

    public static void main(String[] args) {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Egon", 50 ));
        persons.add(new Person("Ekkart", 74));
        persons.add(new Person("Anton", 84));
        persons.add(new Person("Carlos", 70));
        persons.add(new Person("Ekkart", 70));

        print(persons);
        System.out.println("--------------------");
        persons.sort(comparator);
        // BubbleSort.sort(comparator,persons);

        print(persons);
        System.out.println("--------------------");

        persons.add(2, new Person("Xavi", 85));

        print(persons);
        System.out.println("--------------------");
        // persons.sort(comparator);
        BubbleSort.sort(comparator, persons);

        print(persons);
        System.out.println("--------------------");

        persons = new SortedArrayList<>();
        persons.add(new Person("Egon", 50 ));
        persons.add(new Person("Ekkart", 74));
        persons.add(new Person("Anton", 84));
        persons.add(new Person("Carlos", 70));
        persons.add(new Person("Ekkart", 70));

        print(persons);
        System.out.println("--------------------");

        // persons.add(0, new Person("Egon", 100 )); // should throw an exception
        persons.add(new Person("Egon", 100 ));
        print(persons);
        System.out.println("--------------------");

        // persons.sort(comparator);  // should throw an exception
    }

    public static void print(List<?> list) {
        for (int i = 0; i < list.size(); i++ ) {
            System.out.println(i + ": " + list.get(i));
        }
    }

}
