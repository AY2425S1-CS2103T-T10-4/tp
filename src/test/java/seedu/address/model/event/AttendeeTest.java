package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;
public class AttendeeTest {

    @Test
    public void equals_sameAttributes_returnsTrue() {
        Attendee attendee1 = new Attendee(new Name("John Doe"), new Phone("12345678"), new Email("john@example.com"),
                new Relationship("friend"), Attendee.Status.ATTENDING);
        Attendee attendee2 = new Attendee(new Name("John Doe"), new Phone("12345678"), new Email("john@example.com"),
                new Relationship("friend"), Attendee.Status.ATTENDING);
        assertEquals(attendee1, attendee2);
    }

    @Test
    public void equals_differentAttributes_returnsFalse() {
        Attendee attendee1 = new Attendee(new Name("John Doe"), new Phone("12345678"), new Email("john@example.com"),
                new Relationship("friend"), Attendee.Status.ATTENDING);
        Attendee attendee2 = new Attendee(new Name("Jane Doe"), new Phone("87654321"), new Email("jane@example.com"),
                new Relationship("colleague"), Attendee.Status.NOT_ATTENDING);
        assertNotEquals(attendee1, attendee2);
    }

    @Test
    public void toString_validAttendee_returnsCorrectString() {
        Attendee attendee = new Attendee(new Name("John Doe"), new Phone("12345678"), new Email("john@example.com"),
                new Relationship("friend"), Attendee.Status.ATTENDING);
        String expectedString = "Name: John Doe; Phone: 12345678; Email: john@example.com;"
                + " Relationship: friend; Status: ATTENDING";
        assertEquals(expectedString, attendee.toString());
    }
}
