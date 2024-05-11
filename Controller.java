import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private Label fNameLabel;

    @FXML
    private Label lNameLabel;

    @FXML
    private Label ageLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label fNameLabel1;

    @FXML
    private Label lNameLabel1;

    @FXML
    private Label ageLabel1;

    @FXML
    private Label phoneLabel1;

    @FXML
    public ListView<Friends> friendsList = new ListView<>();
    @FXML
    public ListView<Friends> displayList = new ListView<>();

    @FXML
public void confirmAdd(ActionEvent actionEvent) {
    String firstName = firstNameTextField.getText();
    String lastName = lastNameTextField.getText();
    String phoneNum = phoneNumberTextField.getText();

    // Check if first name is empty or contains non-alphabetical characters
    if (firstName.isBlank()) {
        showAlert(Alert.AlertType.ERROR, "Invalid Name", "First name field cannot be blank.");
        return;
    }
    if (!firstName.matches("[a-zA-Z]+")) {
        showAlert(Alert.AlertType.ERROR, "Invalid Name", "Name must contain only alphabetical characters.");
        return;
    }

    // Check if last name is empty or contains non-alphabetical characters
    if (lastName.isBlank()) {
        showAlert(Alert.AlertType.ERROR, "Invalid Name", "Last name field is blank.");
        return;
    }
    if (lastName.isBlank() || !lastName.matches("[a-zA-Z]+")) {
        showAlert(Alert.AlertType.ERROR, "Invalid Name", "Name must contain only alphabetical characters.");
        return;
    }

    // Check if age is empty or contains non-numeric characters
    if (!ageTextField.getText().matches("\\d+")) {
        showAlert(Alert.AlertType.ERROR, "Invalid Age", "Age must be a number.");
        return;
    }
    if (ageTextField.getText().isBlank()) {
        showAlert(Alert.AlertType.ERROR, "Invalid Age", "Age field cannot be blank.");
        return;
    }

    // Convert age to integer
    int age = Integer.parseInt(ageTextField.getText());

    // Check if age is within a valid range
    if (age < 0 || age > 99) {
        showAlert(Alert.AlertType.ERROR, "Invalid Age", "Age must be between 0 and 99.");
        return;
    }

    // Check if phone number is empty or contains non-numeric characters
    if (phoneNumberTextField.getText().isBlank()) {
        showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone Number field cannot be blank.");
        return;
    }
    if (!phoneNumberTextField.getText().matches("\\d+")) {
        showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number must contain only numeric characters.");
        return;
    }

    // Check if phone number is of valid length
    if (phoneNumberTextField.getText().length() != 10) {
        showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number must be 10 digits long.");
        return;
    }
    // Check if phone number already exists in the list
    for (Friends friend : friendsList.getItems()) {
        if (friend.getPhoneNumber().equals(phoneNum)) {
            showAlert(Alert.AlertType.ERROR, "Duplicate Entry", "Phone number already exists.");
            return;
        }
    }
    // Check if the combination of first name and last name already exists in the list
    for (Friends friend : friendsList.getItems()) {
        if (friend.getfirstName().equalsIgnoreCase(firstName) && friend.getlastName().equalsIgnoreCase(lastName)) {
            showAlert(Alert.AlertType.ERROR, "Duplicate Entry", "Friend already exists.");
            return;
        }
    }

    Friends newFriend = new Friends(firstName, lastName, age, phoneNum);
    friendsList.getItems().add(newFriend);
    displayList.getItems().add(newFriend);
    clearFields();
}

    //Method to display friends...
    @FXML
    private void displayFriends(MouseEvent mouseEvent) {
        
        Friends friend = displayList.getSelectionModel().getSelectedItem();
        if (friend != null) {
            fNameLabel1.setText(friend.getfirstName());
            lNameLabel1.setText(friend.getlastName());
            ageLabel1.setText(Integer.toString(friend.getAge()));
            phoneLabel1.setText(friend.getPhoneNumber());
        } else {
            //Alert to show error if no friend is selected...
            showAlert(Alert.AlertType.ERROR, "No Friend Selected", "Please select a friend from the list view.");
        }
            
    }
    //Method when display tab is first opened...
    @FXML
    private void deleteFriend(MouseEvent mouseEvent) {
        Friends friend = friendsList.getSelectionModel().getSelectedItem();
        if (friend != null) {
            fNameLabel.setText(friend.getfirstName());
            lNameLabel.setText(friend.getlastName());
            ageLabel.setText(Integer.toString(friend.getAge()));
            phoneLabel.setText(friend.getPhoneNumber());
        } else {
            showAlert(Alert.AlertType.ERROR, "No Friend Selected", "Please select a friend from the list view.");
        }
    }
    //Method to confirm deletion..,
    @FXML
    private void confirmDeletion(ActionEvent actionEvent) {
        Friends friend = friendsList.getSelectionModel().getSelectedItem();
        if (friend != null) {
            fNameLabel.setText(friend.getfirstName());
            lNameLabel.setText(friend.getlastName());
            ageLabel.setText(Integer.toString(friend.getAge()));
            phoneLabel.setText(friend.getPhoneNumber());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Deletion");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete " + friend.getfirstName() + " " + friend.getlastName() + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
            Friends selectedFriend = displayList.getSelectionModel().getSelectedItem();
            //Method to clear all fields and remove friend from the list...
            friendsList.getItems().remove(friend);
            displayList.getItems().remove(friend);
            fNameLabel.setText(null);
            lNameLabel.setText(null);
            ageLabel.setText(null);
            phoneLabel.setText(null);
            if (selectedFriend != null && selectedFriend.equals(friend)) {
            fNameLabel1.setText(null);
            lNameLabel1.setText(null);
            ageLabel1.setText(null);
            phoneLabel1.setText(null);
            }
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "No Friend Selected", "Please select a friend from the list view.");
        }
    }

    @FXML
    public void quit(ActionEvent event) {
        // Opens a confirmation alert box...
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Quit Game");
        alert.setContentText("Are you sure you want to quit?");
        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);
        alert.showAndWait().ifPresent(response -> {
            if (response == yesButton) {
                System.out.println("Exited the program successfully!");
                // If user clicks on yes button, the program will close.
                System.exit(0);
            }
            // If user clicks on no button then the program will pick up where it left off.
        });
    }
    
    //Method to clear all fields...
    private void clearFields() {
        firstNameTextField.clear();
        lastNameTextField.clear();
        ageTextField.clear();
        phoneNumberTextField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
