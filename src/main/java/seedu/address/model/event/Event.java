package seedu.address.model.event;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;
import seedu.address.model.util.SampleDataUtil;


/**
 * Represents an event that a {@code Person} is or was involved in.
 * Guarantees: details (of the event) are present and not null, field values are validated, immutable.
 */
public class Event {
    private final String eventName;
    private final LocalDate date;
    private final Set<Person> attendees = new HashSet<>();

    /**
     * Constructs an {@code Event}.
     *
     * @param eventName A valid event name.
     * @param date A valid date.
     * @param attendees A set of {@code Person} attending the event.
     */
    public Event(String eventName, LocalDate date, Set<Person> attendees) {
        requireAllNonNull(eventName, date, attendees);
        this.eventName = eventName;
        this.date = date;

        // buffer data to show UI changes, since adding attendees are not yet implemented.
        attendees.addAll(Set.of(SampleDataUtil.getSamplePersons()));
        this.attendees.addAll(attendees);
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Person> getAttendees() {
        return Collections.unmodifiableSet(attendees);
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
                && attendees.equals(otherEvent.attendees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName, date, attendees);
    }

    @Override
    public String toString() {
        return "Event{"
                + "name='" + eventName + '\''
                + ", date=" + date
                + ", attendees=" + attendees
                + '}';
    }
}
