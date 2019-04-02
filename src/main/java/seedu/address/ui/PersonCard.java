package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Landlord;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;
import seedu.address.model.person.Tenant;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label customer;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private FlowPane address;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane sellingPrice;
    @FXML
    private FlowPane rentalPrice;

    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        remark.setText(person.getRemark().value);
        email.setText(person.getEmail().value);
    }

    public PersonCard(Buyer buyer, int displayedIndex) {
        super(FXML);
        this.person = buyer;
        customer.setText("Buyer");
        id.setText(displayedIndex + ". ");
        name.setText(buyer.getName().fullName);
        phone.setText(buyer.getPhone().value);
        remark.setText(buyer.getRemark().value);
        email.setText(buyer.getEmail().value);
    }

    public PersonCard(Tenant tenant, int displayedIndex) {
        super(FXML);
        this.person = tenant;
        customer.setText("Tenant");
        id.setText(displayedIndex + ". ");
        name.setText(tenant.getName().fullName);
        phone.setText(tenant.getPhone().value);
        remark.setText(tenant.getRemark().value);
        email.setText(tenant.getEmail().value);
    }

    public PersonCard(Seller seller, int displayedIndex) {
        super(FXML);
        this.person = seller;
        customer.setText("Seller");
        id.setText(displayedIndex + ". ");
        name.setText(seller.getName().fullName);
        phone.setText(seller.getPhone().value);
        remark.setText(seller.getRemark().value);
        email.setText(seller.getEmail().value);
        address.getChildren().add(new Label(seller.getAddress().value));
        sellingPrice.getChildren().add(new Label("Selling Price $:" + seller.getSellingPrice().toString()));
        seller.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    public PersonCard(Landlord landlord, int displayedIndex) {
        super(FXML);
        this.person = landlord;
        customer.setText("Landlord");
        id.setText(displayedIndex + ". ");
        name.setText(landlord.getName().fullName);
        phone.setText(landlord.getPhone().value);
        remark.setText(landlord.getRemark().value);
        email.setText(landlord.getEmail().value);
        address.getChildren().add(new Label(landlord.getAddress().value));
        rentalPrice.getChildren().add(new Label("Rental Price: $" + landlord.getRentalPrice().toString()));
        landlord.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
