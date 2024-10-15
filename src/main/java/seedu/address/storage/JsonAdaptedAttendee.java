package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Attendee;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Relationship;

/**
 * Jackson-friendly version of {@link Attendee}.
 */
public class JsonAdaptedAttendee {
    private final String name;
    private final String phone;
    private final String email;
    private final String relationship;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedAttendee} with the given attendee details.
     */
    @JsonCreator
    public JsonAdaptedAttendee(@JsonProperty("name") String name,
                               @JsonProperty("phone") String phone,
                               @JsonProperty("email") String email,
                               @JsonProperty("relationship") String relationship,
                               @JsonProperty("status") String status) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.relationship = relationship;
        this.status = status;
    }

    /**
     * Converts a given {@code Attendee} into this class for Jackson use.
     */
    public JsonAdaptedAttendee(Attendee source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        relationship = source.getRelationship().toString();
        status = source.getStatus().name();
    }

    /**
     * Converts this Jackson-friendly adapted attendee object into the model's {@code Attendee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendee.
     */
    public Attendee toModelType() throws IllegalValueException {
        final Name modelName = new Name(name);
        final Phone modelPhone = new Phone(phone);
        final Email modelEmail = new Email(email);
        final Relationship modelRelationship = new Relationship(relationship);
        final Attendee.Status modelStatus = Attendee.Status.valueOf(status);
        return new Attendee(modelName, modelPhone, modelEmail, modelRelationship, modelStatus);
    }
}

