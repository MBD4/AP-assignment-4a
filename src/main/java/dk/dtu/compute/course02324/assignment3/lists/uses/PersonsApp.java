package dk.dtu.compute.course02324.assignment3.lists.uses;


import dk.dtu.compute.course02324.assignment3.lists.implementations.ArrayList;
import dk.dtu.compute.course02324.assignment3.lists.implementations.SortedArrayList;
import dk.dtu.compute.course02324.assignment3.lists.types.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A simple JavaFX application with a simple GUI for manually
 * maintaining a list of Persons.
 *
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class PersonsApp extends Application {

    /**
     * The stage of the GUI of this test application.
     */
    private Stage stage;

    /**
     * The pane on which the actual interaction with the
     * list of persons will be added.
     */
    private Pane root;

    /**
     * The GUI for a specific list.
     */
    private PersonsGUI personsGUI = null;

    /**
     * The method starting the application, which sets up the GUI
     * elements of this application.
     *
     * @param stage the stage for this application (provided by JavaFX)
     * @throws Exception if something should go wrong (required by super class)
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        MenuBar menuBar = createMenuBar();
        root = new Pane();
        VBox box = new VBox(menuBar, root);
        Scene scene = new Scene(box);

        this.stage.setScene(scene);

        this.stage.setTitle("Persons List App");
        this.stage.setResizable(false);
        this.stage.sizeToScene();
        this.stage.show();
    }

    /**
     * Methods for creating the menu bar of the application. This menu
     * bar has a single menu, where the user can change between an
     * {@link SortedArrayList} or an {@link ArrayList} implementation
     * to be tested.
     *
     * @return the menubar for choosing the type of implementation
     */
    private MenuBar createMenuBar() {
        // The menu for the choosing an am implication
        Menu selectMenu = new Menu("Choose Implementation");

        // The individual menu items for the different choices, with the
        // respective actions creating the respective stack.
        MenuItem unsortedListItem = new MenuItem("(unsorted) List");
        unsortedListItem.setOnAction(
                e -> {
                    List<Person> list = new ArrayList<>();
                    switchImpl(list);
                }
        );
        selectMenu.getItems().add(unsortedListItem);

        MenuItem sortedListItem = new MenuItem("sorted List");
        sortedListItem.setOnAction(
                e -> {
                    List<Person> list = new SortedArrayList<>();
                    switchImpl(list);
                }
        );
        selectMenu.getItems().add(sortedListItem);

        MenuItem noListItem = new MenuItem("No Implementation");
        noListItem.setOnAction(
                e -> { switchImpl(null); }
        );
        selectMenu.getItems().add(noListItem);

        // creating the menu bar with the single menu on it
        MenuBar menubar = new MenuBar();
        menubar.setMinWidth(400);
        menubar.getMenus().add(selectMenu);

        return menubar;
    }

    /**
     * Methods used for changing to a different list implementations,
     * and updating the GUI accordingly.
     *
     * @param list new person list for which GUI should be initialized; it can
     *              be <code>null</code>
     */
    private void switchImpl(List<Person> list) {
        // if there exists a GUI for some stack already, this GUI
        // is removed.
        if (personsGUI != null) {
            root.getChildren().remove(personsGUI);
            // alternatively, we could just remove all children from root:
            // root.getChildren().clear();
        }

        // if a stack is provided, a corresponding GUI is created,
        // attached to the stack and placed on the GUI's root element.
        if (list != null) {
            personsGUI = new PersonsGUI(list);

            root.getChildren().add(personsGUI);
            this.stage.setTitle("Persons list test: " + list.getClass().getSimpleName());
        } else {
            // otherwise only the title of the application window is changed
            personsGUI = null;
            this.stage.setTitle("Persons list test: no implementation");
        }

        // since this application is not resizable by the user, the application
        // needs to adjust the size of the GUI to the new content, when the
        // GUI arrangement changes.
        stage.sizeToScene();
    }

    /**
     * The main method used to start the JavaFX application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
