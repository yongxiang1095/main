package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.property.Price;
import seedu.address.model.property.Property;
import seedu.address.model.tag.Tag;

/**
 * Represents a Seller in the address book.
 */
public class Seller extends Person {

    //private final Remark remark;
    private final Property property;

    public Seller(Name name, Phone phone, Email email, Remark remark, Property property) {
        super(name, phone, email, remark);
        this.property = property;
    }

    public Address getAddress() {
        return property.getAddress();
    }

    public Set<Tag> getTags() {
        return property.getTags();
    }

    public Price getSellingPrice() {
        return property.getSellingPrice();
    }

    public Property getProperty() {
        return property;
    }

    /**
     * Returns true if both sellers have the same identity and data fields.
     * This defines a stronger notion of equality between two sellers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Seller)) {
            return false;
        }

        Seller otherSeller = (Seller) other;
        return otherSeller.getName().equals(getName())
                && otherSeller.getPhone().equals(getPhone())
                && otherSeller.getEmail().equals(getEmail())
                && otherSeller.getAddress().equals(getAddress())
                && otherSeller.getRemark().equals(getRemark());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Remark: ")
                .append(getRemark())
                .append(" Address: ")
                .append(getAddress())
                .append(" Selling Price: ")
                .append(getSellingPrice())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
