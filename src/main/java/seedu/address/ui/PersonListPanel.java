package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> personListView;

    private final PersonCard.PersonSelectionHandler personSelectionHandler;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList<Person>}
     * and a {@code PersonSelectionHandler} for handling user selections of a person.
     *
     * @param personList The {@code ObservableList<Person>} that contains the list of persons to be displayed.
     * @param personSelectionHandler The {@code PersonSelectionHandler}
     *                               to handle selection events when a person is clicked.
     */
    public PersonListPanel(ObservableList<Person> personList,
                           PersonCard.PersonSelectionHandler personSelectionHandler) {
        super(FXML);
        this.personSelectionHandler = personSelectionHandler;

        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                PersonCard personCard = new PersonCard(person, getIndex() + 1, personSelectionHandler);
                setGraphic(personCard.getRoot());
            }
        }
    }
}
