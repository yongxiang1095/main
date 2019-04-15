package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Landlord;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;
import seedu.address.model.person.Tenant;

/**
 * Panel containing the list of archived persons.
 */
public class ArchiveListPanel extends UiPart<Region> {
    private static final String FXML = "ArchiveListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<Person> archiveListView;

    public ArchiveListPanel(ObservableList<Person> archivedPersonList, ObservableValue<Person> selectedArchivedPerson,
                            Consumer<Person> onSelectedArchivedPersonChange) {
        super(FXML);
        archiveListView.setItems(archivedPersonList);
        archiveListView.setCellFactory(listView -> new PersonListViewCell());
        archiveListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in archive person list panel changed to : '" + newValue + "'");
            onSelectedArchivedPersonChange.accept(newValue);
        });
        selectedArchivedPerson.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected archived person changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected person,
            // otherwise we would have an infinite loop.
            if (Objects.equals(archiveListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                archiveListView.getSelectionModel().clearSelection();
            } else {
                int index = archiveListView.getItems().indexOf(newValue);
                archiveListView.scrollTo(index);
                archiveListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                if (person instanceof Buyer) {
                    setGraphic(new PersonCard((Buyer) person, getIndex() + 1).getRoot());
                } else if (person instanceof Seller) {
                    setGraphic(new PersonCard((Seller) person, getIndex() + 1).getRoot());
                } else if (person instanceof Landlord) {
                    setGraphic(new PersonCard((Landlord) person, getIndex() + 1).getRoot());
                } else if (person instanceof Tenant) {
                    setGraphic(new PersonCard((Tenant) person, getIndex() + 1).getRoot());
                } else {
                    setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
                }
            }
        }
    }

}
