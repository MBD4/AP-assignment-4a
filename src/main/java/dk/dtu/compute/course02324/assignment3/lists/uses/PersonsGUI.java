package dk.dtu.compute.course02324.assignment3.lists.uses;


import dk.dtu.compute.course02324.assignment3.lists.implementations.GenericComparator;
//import dk.dtu.compute.course02324.assignment3.lists.types.List;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import jakarta.validation.constraints.NotNull;

/**
 * A GUI element that is allows the user to interact and
 * change a list of persons.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 */
public class PersonsGUI extends GridPane {

    /**
     * The list of persons to be maintained in this GUI.
     */
    final private List<Person> persons;

    private GridPane personsPane;

    private TextArea textAreaExceptions;

    private Label averageWeightLabelNumber;

    private Label mostOccuringNameNumber;

    private Label minAgeNumber;

    private Label maxAgeNumber;

    /**
     * Constructor which sets up the GUI attached a list of persons.
     *
     * @param persons the list of persons which is to be maintained in
     *                this GUI component; it must not be <code>null</code>
     */
    public PersonsGUI(@NotNull List<Person> persons) {
        this.persons = persons;

        this.setVgap(5.0);
        this.setHgap(5.0);

        // text filed for user entering a name
        Label labelFieldName = new Label("Name:");

        TextField fieldName = new TextField();
        fieldName.setPrefColumnCount(5);
        fieldName.setText("");


        // button for adding a new person to the list (based on
        // the name in the text field (the weight is just incrementing)

        Label labelFieldWeight = new Label("Weight:");

        TextField fieldWeight = new TextField();
        fieldWeight.setPrefColumnCount(5);
        fieldWeight.setText("");

        Label labelFieldAge = new Label("Age:");

        TextField fieldAge = new TextField();
        fieldAge.setPrefColumnCount(5);
        fieldAge.setText("");

        HBox labelTextFields = new HBox(labelFieldName, labelFieldWeight, labelFieldAge);
        labelTextFields.setSpacing(40);

        HBox textFields = new HBox(fieldName, fieldWeight, fieldAge);
        textFields.setSpacing(2);

        Button addButton = new Button("Add at the end of the list");
        addButton.setOnAction(
                e -> {
                    try {
                        Person person = new Person(fieldName.getText(), Double.parseDouble(fieldWeight.getText()));
                        person.setAge(Integer.parseInt(fieldAge.getText()));
                        persons.add(person);
                    }
                    catch (IllegalArgumentException e1) {
                        textAreaExceptions.appendText(e1.getClass().getSimpleName() + ": " + e1.getMessage() + System.lineSeparator());
                    }
                    // makes sure that the GUI is updated accordingly
                    update();
                });

        TextField fieldIndex = new TextField("");
        fieldIndex.setPrefColumnCount(5);
        Button addAtIndexButton = new Button("Add at index:");
        addAtIndexButton.setOnAction(
                  e -> {
                      try {
                          Person person = new Person(fieldName.getText(), Double.parseDouble(fieldWeight.getText()));

                          person.setAge(Integer.parseInt(fieldAge.getText()));
                          if (person.getAge() < 0) {
                              throw new IllegalArgumentException("Age cannot be negative: " + person.getAge());
                          }
                          persons.add(Integer.parseInt(fieldIndex.getText()), person);
                      }
                      catch (IllegalArgumentException e2) {
                          textAreaExceptions.appendText(e2.getClass().getSimpleName() + ": " + e2.getMessage() + System.lineSeparator());
                      }
                      update();
                  });

        HBox hboxIndex = new HBox(addAtIndexButton, fieldIndex);
        hboxIndex.setSpacing(4);



        Comparator<Person> comparator = new GenericComparator<>();

        // button for sorting the list (according to the order of Persons,
        // which implement the interface Comparable, which is converted
        // to a Comparator by the GenericComparator above)
        Button sortButton = new Button("Sort");
        sortButton.setOnAction(
                e -> {
                    persons.sort(comparator);
                    // makes sure that the GUI is updated accordingly
                    update();
                });

        // button for clearing the list
        Button clearButton = new Button("Clear");
        clearButton.setOnAction(
                e -> {
                    persons.clear();
                    // makes sure that the GUI is updated accordingly
                    update();
                });

        // Button for increasing every person's age by one year
        Button ageOneYearButton = new Button("Age 1 Year");
        ageOneYearButton.setOnAction(
                e -> {
                    persons.stream().forEach(p -> p.setAge(p.getAge() + 1));
                    persons.stream().filter(p -> p.getAge() > 30).forEach(p -> p.weight = p.weight * 1.08);
                    persons.removeIf(p -> p.getAge() >= 99);
                    update();

                }
        );

        Label averageWeightLabel = new Label("Average Weight:");
        averageWeightLabelNumber = new Label("");

        Label mostOccuringName = new Label("Most occuring name:");
        mostOccuringNameNumber = new Label("");

        Label minAge = new Label("min age:");
        minAgeNumber = new Label("");

        Label maxAge = new Label("max age:");
        maxAgeNumber = new Label("");



        // combines the above elements into vertically arranged boxes
        // which are then added to the left column of the grid pane
        VBox actionBox = new VBox(labelTextFields, textFields, addButton, hboxIndex, sortButton, clearButton, ageOneYearButton, averageWeightLabel,
                averageWeightLabelNumber, mostOccuringName, mostOccuringNameNumber, minAge, minAgeNumber, maxAge, maxAgeNumber);
        actionBox.setSpacing(5.0);
        this.add(actionBox, 0, 0);

        // create the elements of the right column of the GUI
        // (scrollable person list) ...
        Label labelPersonsList = new Label("Persons:");

        personsPane = new GridPane();
        personsPane.setPadding(new Insets(5));
        personsPane.setHgap(5);
        personsPane.setVgap(5);


        ScrollPane scrollPane = new ScrollPane(personsPane);
        scrollPane.setMinWidth(200);
        scrollPane.setMaxWidth(220);
        scrollPane.setMinHeight(200);
        scrollPane.setMaxHeight(200);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // labelException and textAreaExeceptions setup is taken from assignment 2:
        Label labelExceptions = new Label("Exceptions:");
        textAreaExceptions = new TextArea();
        textAreaExceptions.setWrapText(true);
        textAreaExceptions.setText("");
        textAreaExceptions.setEditable(false);
        textAreaExceptions.setScrollTop(Double.MAX_VALUE);

        ScrollPane scrollPaneExceptions = new ScrollPane(textAreaExceptions);
        scrollPaneExceptions.setMinWidth(200);
        scrollPaneExceptions.setMaxWidth(220);
        scrollPaneExceptions.setMinHeight(10);
        scrollPaneExceptions.setMaxHeight(200);
        scrollPaneExceptions.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPaneExceptions.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);


        // ... and adds these elements to the right-hand columns of
        // the grid pane
        VBox personsList = new VBox(labelPersonsList, scrollPane, labelExceptions, scrollPaneExceptions);
        personsList.setSpacing(5.0);
        this.add(personsList, 1, 0);

        // updates the values of the different components with the values from
        // the stack
        update();
    }

    /**
     * Updates the values of the GUI elements with the current values
     * from the list.
     */
    private void update() {
        personsPane.getChildren().clear();
        // adds all persons to the list in the personsPane (with
        // a delete button in front of it)
        for (int i=0; i < persons.size(); i++) {
            Person person = persons.get(i);
            Label personLabel = new Label(i + ": " + person.toString());
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(
                    e -> {
                        persons.remove(person);
                        update();
                    }
            );
            HBox entry = new HBox(deleteButton, personLabel);
            entry.setSpacing(5.0);
            entry.setAlignment(Pos.BASELINE_LEFT);
            personsPane.add(entry, 0, i);
        }

        // Calculate Average Weight without loops:
        if (persons.size() > 0) {
            Optional<Double> weightSum = persons.stream().map(p -> p.weight).reduce((a, b) -> a + b);
            double averageWeight = weightSum.orElse(0.0) / persons.size();
            averageWeightLabelNumber.setText(averageWeight + " kg");
        }
        else {
            averageWeightLabelNumber.setText("0.0 kg");
        }

        // Logic for Most Occurring Name with using streams instead of loops

        Map.Entry<String, Long> mostOccurringName = persons.stream()
                .map(p -> p.name).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
        if (mostOccurringName == null) {
            mostOccuringNameNumber.setText("");
        }
        else {
            mostOccuringNameNumber.setText(mostOccurringName.getKey() + " x " + mostOccurringName.getValue());
        }

        // Logic for finding min and max age without using loops
        Optional<Integer> minAge =persons.stream().map(p -> p.getAge()).reduce((a, b) -> Math.min(a,b));
        Optional<Integer> maxAge =persons.stream().map(p -> p.getAge()).reduce((a, b) -> Math.max(a,b));

        minAgeNumber.setText(String.valueOf(minAge.orElse(0)));
        maxAgeNumber.setText(String.valueOf(maxAge.orElse(0)));






    }


}
