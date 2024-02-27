package adrian.roszkowski.verkefni4audioplayer.vidmot;

import adrian.roszkowski.verkefni4audioplayer.vinnsla.Askrifandi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Optional;

public class AskrifandiDialog extends Dialog<Askrifandi> {
    @FXML
    private TextField userInput_ID;


    /**
     * Establishes the dialog. Creates the logic for the dialog.
     */
    AskrifandiDialog() {
        super();
        setDialogPane(readAskrifandiDialog());

        setResultConverter(dialogButton -> {
            if (dialogButton.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                return new Askrifandi(userInput_ID.getText());
            } else {
                return null;
            }
        });
    }

    /**
     * Loads the fxml file and returns it.
     * @return DialogPane
     */
    private DialogPane readAskrifandiDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("askrifandi-view.fxml"));
        fxmlLoader.setController(this);

        try {
            return fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Shows the dialog and waits for the result.
     * @return Askrifandi or null
     */
    public Askrifandi getAskrifandi() {
        Optional<Askrifandi> result = showAndWait();
        return result.orElse(null);
    }

}
