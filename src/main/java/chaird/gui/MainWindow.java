package chaird.gui;

import chaird.app.Chaird;
import chaird.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Chaird chaird;


    @FXML
    public void initialize() {
        Ui ui = new Ui();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getChairdDialog(ui.greet())
        );
    }

    /** Injects the Chaird instance */
    public void setChaird(Chaird c) {
        chaird = c;

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chaird.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input),
                DialogBox.getChairdDialog(response)
        );
        userInput.clear();
    }
}
