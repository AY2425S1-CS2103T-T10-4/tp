package seedu.address.testutil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.event.Attendee;
import seedu.address.model.event.Event;
import seedu.address.model.person.Address;

/**
 * A utility class to help with building Event objects.
 */
public class EventBuilder {

    public static final String DEFAULT_NAME = "Event A";
    public static final LocalDate DEFAULT_DATE = LocalDate.of(2023, 10, 1);
    public static final Address DEFAULT_LOCATION = new Address("Default Location");

    private String name;
    private LocalDate date;
    private Set<Attendee> attendees;
    private Address location;

    /**
     * Creates a {@code EventBuilder} with the default details.
     */
    public EventBuilder() {
        name = DEFAULT_NAME;
        date = DEFAULT_DATE;
        attendees = new HashSet<>();
        location = DEFAULT_LOCATION;
    }

    /**
     * Initializes the EventBuilder with the data of {@code eventToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        name = eventToCopy.getEventName();
        date = eventToCopy.getDate();
        attendees = new HashSet<>(eventToCopy.getAttendees());
        location = eventToCopy.getLocation();
    }

    /**
     * Sets the {@code name} of the {@code Event} that we are building.
     */
    public EventBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code date} of the {@code Event} that we are building.
     */
    public EventBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    /**
     * Sets the {@code attendees} of the {@code Event} that we are building.
     */
    public EventBuilder withAttendees(Set<Attendee> attendees) {
        this.attendees = attendees;
        return this;
    }

    /**
     * Sets the {@code location} of the {@code Event} that we are building.
     */
    public EventBuilder withLocation(Address location) {
        this.location = location;
        return this;
    }

    public Event build() {
        return new Event(name, date, attendees, location);
    }
}
