package seedu.address.model.event;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;


/**
 * Represents an event that a {@code Person} is or was involved in.
 * Guarantees: details (of the event) are present and not null, field values are validated, immutable.
 */
public class Event {
    private final String eventName;
    private final LocalDate date;
    private final Set<Attendee> attendees = new HashSet<>();
    private Address location;

    /**
     * Constructs an {@code Event}.
     *
     * @param eventName A valid event name.
     * @param date A valid date.
     * @param attendees A set of {@code Person} attending the event.
     */
    public Event(String eventName, LocalDate date, Set<Attendee> attendees, Address location) {
        requireAllNonNull(eventName, date, attendees, location);
        this.eventName = eventName;
        this.date = date;
        this.attendees.addAll(attendees);
        this.location = location;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Attendee> getAttendees() {
        return Collections.unmodifiableSet(attendees);
    }

    public Address getLocation() {
        return location;
    }

    /**
     * Returns true if both events have the same name and date.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getEventName().equals(getEventName())
                && otherEvent.getDate().equals(getDate());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return eventName.equals(otherEvent.eventName)
                && date.equals(otherEvent.date)
                && attendees.equals(otherEvent.attendees)
                && location.equals(otherEvent.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, date, attendees);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("eventName", eventName)
                .add("eventDate", date)
                .add("attendees", attendees)
                .add("location", location)
                .toString();
    }
}
