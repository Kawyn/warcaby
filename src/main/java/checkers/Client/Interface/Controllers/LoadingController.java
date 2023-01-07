package checkers.Client.Interface.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadingController {
    private Parent root;
    private Stage stage;
    private double points = 0.0;
    @FXML
    private ProgressBar progress;
    @FXML
    private Button next;
    @FXML
    void initialize() {
        ProgressUpdate.start();
        Load();
    }
    private void Load(){
        next.setVisible(false);
        next.setOnAction(event -> {
            stage = (Stage) next.getScene().getWindow();
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

    Thread ProgressUpdate = new Thread() {
        public void run() {
            int time=10;
            for(int i=0; i<time; i++)
            {
                points = points + 0.1;
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progress.setProgress(points);
            }
            next.setVisible(true);
        }
    };
}


