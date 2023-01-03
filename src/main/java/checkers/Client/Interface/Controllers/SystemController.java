package checkers.Client.Interface.Controllers;

import checkers.Client.ClientData;
import checkers.Universal.GameStates.GameStateFactory;
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

public class SystemController {
        private Parent root;
        private Stage stage;
        @FXML
        private ComboBox <String> lista;

        @FXML
        private Button start;

        @FXML
        void initialize() {
            LoadList();
            Choise();
        }
        private void LoadList(){
            ObservableList<String> list = FXCollections.observableArrayList("Angielski","Rosyjski","Turecki");
            lista.setItems(list);
            lista.setValue("Angielski");
        }
        private void Choise(){
            start.setOnAction(event -> {
                switch (lista.getSelectionModel().getSelectedItem()){
                    case "Angielski":{
                        stage = (Stage) start.getScene().getWindow();
                        stage.close();
                        root = null;
                        ClientData.currentGameState =GameStateFactory.createGameStateByType(GameStateFactory.GameStateType.ENGLISH);

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
                        break;
                    }
                    case "Rosyjski": {

                    }
                    case "Turecki":{

                    }
                }
            });
        }

        public int getWariant(){
            return lista.getSelectionModel().getSelectedIndex();
        }

}
