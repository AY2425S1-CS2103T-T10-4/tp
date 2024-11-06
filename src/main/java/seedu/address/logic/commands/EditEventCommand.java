package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDEES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMOVE_ATTENDEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;

/**
 * Updates an Event in the address book.
 */
public class EditEventCommand extends Command {
    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates an event in the address book. "
            + "Multiple changes can be applied in a single command.\n"
            + "Parameters (index is one-based): "
            + PREFIX_INDEX + "<EVENT INDEX> "
            + "[" + PREFIX_NAME + "<NEW EVENT NAME>] "
            + "[" + PREFIX_START_DATE + "<yyyy-mm-dd>] "
            + "[" + PREFIX_END_DATE + "<yyyy-mm-dd>] "
            + "[" + PREFIX_ATTENDEES + "<PERSON INDICES> ] "
            + "[" + PREFIX_LOCATION + "<NEW LOCATION NAME> ] "
            + "[" + PREFIX_REMOVE_ATTENDEE + "<PERSON INDICES> ] \n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_INDEX + "3 "
            + PREFIX_NAME + "New Year's Party "
            + PREFIX_START_DATE + "2025-01-01 "
            + PREFIX_END_DATE + "2025-01-02 "
            + PREFIX_ATTENDEES + "1 2 4 5 "
            + PREFIX_LOCATION + "Marine Parade Road #12-34 "
            + PREFIX_REMOVE_ATTENDEE + "3 6";

    public static final String MESSAGE_SUCCESS = "Event has been updated: %1$s";

    public static final String MESSAGE_INDEX_OUT_OF_BOUNDS = "There is no event at this index";

    private final String newName;
    private final LocalDate newStartDate;
    private final LocalDate newEndDate;
    private final Address newLocation;
    private final Set<Index> addIndices;
    private final Set<Index> removeIndices;
    private final Index indexToUpdate;

    /**
     * Creates an EditEventCommand to update an event to the specified {@code Event}
     */
    public EditEventCommand(String newName,
                            LocalDate newStartDate,
                            LocalDate newEndDate,
                            Address newLocation,
                            Set<Index> addIndices,
                            Set<Index> removeIndices,
                            Index indexToUpdate) {
        this.newName = newName;
        this.newStartDate = newStartDate;
        this.newEndDate = newEndDate;
        this.newLocation = newLocation;
        this.indexToUpdate = indexToUpdate;
        this.addIndices = addIndices;
        this.removeIndices = removeIndices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (indexToUpdate.getZeroBased() >= model.getEventListLength()
                || indexToUpdate.getZeroBased() < 0) {
            throw new CommandException(MESSAGE_INDEX_OUT_OF_BOUNDS);
        }

        ObservableList<Event> eventList = model.getEventList();
        ObservableList<Person> personList = model.getAddressBook().getPersonList();

        Event oldEvent = eventList.get(indexToUpdate.getZeroBased());
        Event newEvent;

        // Add and remove attendees
        Set<Person> changedAttendees = getChangedAttendees(oldEvent, personList);
        newEvent = new Event(
                newName.isEmpty() ? oldEvent.getEventName() : newName,
                newStartDate == null ? oldEvent.getStartDate() : newStartDate,
                newEndDate == null ? oldEvent.getEndDate() : newEndDate,
                newLocation == null ? oldEvent.getLocation() : newLocation,
                changedAttendees);

        assert newEvent != null;

        model.updateEvent(newEvent, indexToUpdate.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                Messages.formatEvent(newEvent)));
    }

    private Set<Person> getChangedAttendees(Event oldEvent, ObservableList<Person> personList)
            throws CommandException {
        Set<Person> oldAttendees = oldEvent.getAttendees();
        Set<Person> changedAttendees = new HashSet<>(oldAttendees);
        for (Index i : addIndices) {
            try {
                Person p = personList.get(i.getZeroBased());
                changedAttendees.add(p);
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException("Attendee index out of bounds.");
            }
        }
        for (Index i : removeIndices) {
            try {
                Person p = personList.get(i.getZeroBased());
                changedAttendees.remove(p);
            } catch (IndexOutOfBoundsException e) {
                throw new CommandException("Attendee index out of bounds.");
            }
        }
        return changedAttendees;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditEventCommand)) {
            return false;
        }

        EditEventCommand otherEditEventCommand = (EditEventCommand) other;
        return newName.equals(otherEditEventCommand.newName)
                && (indexToUpdate == otherEditEventCommand.indexToUpdate)
                && (newStartDate == otherEditEventCommand.newStartDate)
                && (newEndDate == otherEditEventCommand.newEndDate)
                && (newLocation.equals(otherEditEventCommand.newLocation))
                && (addIndices.equals(otherEditEventCommand.addIndices))
                && (removeIndices.equals(otherEditEventCommand.removeIndices));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("newName", newName)
                .add("newStartDate", newStartDate)
                .add("newEndDate", newEndDate)
                .add("newLocation", newLocation)
                .add("indexToUpdate", indexToUpdate)
                .add("addIndices", addIndices)
                .add("removeIndices", removeIndices)
                .toString();
    }
}
