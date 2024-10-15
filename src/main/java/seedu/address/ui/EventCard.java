package seedu.address.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Event}.
 */
public class EventCard extends UiPart<Region> {
    private static final String FXML = "EventListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Event event;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label date;

    // TODO: Implement attendee group feature
    @FXML
    private FlowPane attendees;

    /**
     * Creates a {@code EventCard} with the given {@code Event} and index to display.
     */
    public EventCard(Event event, int displayedIndex) {
        super(FXML);
        this.event = event;
        id.setText(displayedIndex + ". ");
        name.setText(event.getEventName());
        date.setText(event.getDate().format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        List<Person> sortedAttendees = event.getAttendees().stream()
                .sorted(Comparator.comparing(person -> person.getName().toString()))
                .toList();

        int maxDisplay = 2;
        int numOfAttendees = sortedAttendees.size();

        for (int i = 0; i < maxDisplay; i++) {
            String name = sortedAttendees.get(i).getName().toString();
            String formattedName = (i == maxDisplay - 1)
                                   ? name
                                   : name + ", ";
            attendees.getChildren().add(new Label(formattedName));
        }

        if (numOfAttendees > maxDisplay) {
            int remainingPeople = numOfAttendees - maxDisplay;
            attendees.getChildren().add(new Label(", and " + remainingPeople + " more"));
        }

    }
}
