package checkers.Client.Interface.Controllers;

import checkers.Client.Client;
import checkers.Client.ClientData;
import checkers.Universal.GameStates.GameStateFactory;
import checkers.Universal.GameStates.GameType;
import checkers.Universal.PlayerColor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class SystemController {
    private Parent root;
    private Stage stage;
    @FXML
    private ComboBox<String> lista;

    @FXML
    private Button start;

    @FXML
    void initialize() {
        LoadList();
        Choise();
    }

    private void LoadList() {
        ObservableList<String> list = FXCollections.observableArrayList("Angielski", "Rosyjski", "Turecki");
        lista.setItems(list);
        lista.setValue("Angielski");
    }

    private void Choise() {
        start.setOnAction(event -> {

            ClientData.getInstance().getLastRequest().addObserver(new Observer() {
                @Override
                public void update(Observable o, Object arg) {

                    if (!ClientData.getInstance().getLastRequest().getValue().startsWith("START_GAME"))
                        return;
                    if(ClientData.getInstance().getGameState() != null)
                        return;

                    String[] args = ClientData.getInstance().getLastRequest().getValue().split("_");

                    ClientData.getInstance().setGameState(GameStateFactory.createGameState(GameType.values()[getWariant()]));
                    ClientData.getInstance().getGameState().setPlayerColor(PlayerColor.valueOf(args[2]));
                    Platform.runLater(() -> {
                        stage = (Stage) start.getScene().getWindow();
                        stage.close();

                        root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/resources/GameScene.fxml"));
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
            });
            Client.request("JOIN_QUEUE_" + GameType.values()[getWariant()]);
        });
    }

    public int getWariant() {
        return lista.getSelectionModel().getSelectedIndex();
    }

}
