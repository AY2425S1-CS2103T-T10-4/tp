package seedu.address.model.event;

import seedu.address.model.person.Person;

/**
 * Represents an association between a Person and an Event with an attendance status.
 */
public class Attendance {
    private final Person person;
    private final Event event;
    private Attendee.Status status;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param person The person attending the event.
     * @param event The event being attended.
     * @param status The attendance status.
     */
    public Attendance(Person person, Event event, Attendee.Status status) {
        this.person = person;
        this.event = event;
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public Event getEvent() {
        return event;
    }

    public Attendee.Status getStatus() {
        return status;
    }

    public void setStatus(Attendee.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Person: %s; Event: %s; Status: %s", person.getName(), event.getEventName(), status);
    }
}
