package chaird.gui;

import chaird.app.Chaird;
import chaird.ui.Ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

import java.util.Objects;


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
    private final Image userImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/user.png")));
    private final Image botImage= new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/chaird.jpg")));

    @FXML
    public void initialize() {
        Ui ui = new Ui();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getChairdDialog(ui.greet(), botImage)
        );

        // this code is written using chatgpt to set up the background for the chatbot
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/background.jpg"),
                "Image not found—check src/main/resources/images/background.jpg"));
        BackgroundImage backgroundImage = new BackgroundImage(bgImage,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        setBackground(new Background(backgroundImage));
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
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChairdDialog(response, botImage)
        );
        userInput.clear();
    }
}
