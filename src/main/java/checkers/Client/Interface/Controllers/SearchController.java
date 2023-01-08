package checkers.Client.Interface.Controllers;

import checkers.Server.Client;
import checkers.Server.Server;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class SearchController {
    private Server server;
    private Client client;
    @FXML
    private Button next;

    @FXML
    private TextField numeric;

    @FXML
    private ComboBox<String> typ;

    @FXML
    void initialize() {
   /*     LoadList();
        checkInt();
        next.setOnAction(event -> {
            if (numeric.getText().length() < 4) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Błąd!");
                alert.setHeaderText(null);
                alert.setContentText("Dane niepoprawne! Sprawdż jeszcze raz! Muszą być 4 cyfry.");
                alert.showAndWait();
            }
            if (getWariant()==0){
      //          server = new Server(Integer.parseInt(numeric.getText()));
        //        server.run();
            }
            else {
                client.initConnection(Integer.parseInt(numeric.getText()));
            }

        });
    }

    private void LoadList() {
        ObservableList<String> list = FXCollections.observableArrayList("Stworzyć lobby", "Dołącz do lobby");
        typ.setItems(list);
        typ.setValue("Stworzyć lobby");
    }

    public void checkInt() {
        numeric.setOnKeyTyped(event -> {
            int maxCharacters = 3;
            if (numeric.getText().length() > maxCharacters) event.consume();
        });
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        numeric.setTextFormatter(new TextFormatter<String>(integerFilter));
    }

    public int getWariant(){
        return typ.getSelectionModel().getSelectedIndex();*/
    }

}
