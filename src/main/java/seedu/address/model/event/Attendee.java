package seedu.address.model.event;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;

/**
 * Represents an Attendee in an event.
 * Extends the Person class to include an attendance status.
 */
public class Attendee extends Person {

    /**
     * Enum representing the status of an attendee.
     */
    public enum Status {
        ATTENDING,
        PENDING,
        NOT_ATTENDING
    }

    private Status status;


    /**
     * Constructs an {@code Attendee}.
     *
     * @param name The name of the attendee.
     * @param phone The phone number of the attendee.
     * @param email The email address of the attendee.
     * @param relationship The relationship of the attendee.
     * @param status The attendance status of the attendee.
     */
    public Attendee(Name name, Phone phone, Email email, Relationship relationship, Status status) {
        super(name, phone, email, relationship);
        this.status = status;
    }

    /**
     * Returns the attendance status of the attendee.
     *
     * @return The attendance status.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the attendance status of the attendee.
     *
     * @param status The new attendance status.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

}
