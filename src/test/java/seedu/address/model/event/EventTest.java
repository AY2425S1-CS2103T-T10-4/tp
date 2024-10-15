package seedu.address.model.event;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;

public class EventTest {

    @Test
    public void equals_sameAttributes_returnsTrue() {
        Set<Attendee> attendees = new HashSet<>();
        attendees.add(new Attendee(new Name("John Doe"), new Phone("12345678"), new Email("john@example.com"),
                new Relationship("friend"), Attendee.Status.ATTENDING));
        Event event1 = new Event("Birthday Party", LocalDate.of(2024, 10, 1), attendees,
                new Address("123 Party Lane"));
        Event event2 = new Event("Birthday Party", LocalDate.of(2024, 10, 1), attendees,
                new Address("123 Party Lane"));
        assertEquals(event1, event2);
    }

    @Test
    public void equals_differentAttributes_returnsFalse() {
        Set<Attendee> attendees1 = new HashSet<>();
        attendees1.add(new Attendee(new Name("John Doe"), new Phone("12345678"), new Email("john@example.com"),
                new Relationship("friend"), Attendee.Status.ATTENDING));
        Event event1 = new Event("Birthday Party", LocalDate.of(2024, 10, 1), attendees1,
                new Address("123 Party Lane"));

        Set<Attendee> attendees2 = new HashSet<>();
        attendees2.add(new Attendee(new Name("Jane Doe"), new Phone("87654321"), new Email("jane@example.com"),
                new Relationship("colleague"), Attendee.Status.NOT_ATTENDING));
        Event event2 = new Event("Wedding", LocalDate.of(2024, 10, 10), attendees2,
                new Address("456 Wedding Blvd"));

        assertNotEquals(event1, event2);
    }

    @Test
    public void toString_validEvent_returnsCorrectString() {
        Set<Attendee> attendees = new HashSet<>();
        attendees.add(new Attendee(new Name("John Doe"), new Phone("12345678"), new Email("john@example.com"),
                new Relationship("friend"), Attendee.Status.ATTENDING));
        Event event = new Event("Birthday Party", LocalDate.of(2024, 10, 1), attendees,
                new Address("123 Party Lane"));
        String expectedString = "Event: Birthday Party; Date: 2024-10-01; Location: 123 Party Lane; Attendees: "
                + "[Name: John Doe; Phone: 12345678; Email: john@example.com; Relationship: friend; Status: ATTENDING]";
        assertEquals(expectedString, event.toString());
    }
}

