package checkers.Client.Interface.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class VictoryController {
    private Parent root;
    private Stage stage;
    @FXML
    private Button menu;

    @FXML
    void initialize() {
        menu.setOnAction(event -> {
            stage = (Stage) menu.getScene().getWindow();
            stage.close();
            root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/resources/System.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Warcaby");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        });

    }
}
