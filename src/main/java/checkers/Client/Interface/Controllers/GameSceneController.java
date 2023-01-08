package checkers.Client.Interface.Controllers;

import checkers.Client.Client;
import checkers.Client.Data;
import checkers.Universal.GameStates.GameState;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.Structs.Move;
import checkers.Universal.Structs.Vector2D;
import checkers.Utils.ObservableValue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class GameSceneController {
    private final List<StackPane> squares = new ArrayList<>();
    private final ObservableList<Move> possibleMoves = FXCollections.observableArrayList();
    private final ObservableValue<Piece> selectedPiece = new ObservableValue<>(null);
    private Parent root;
    private Stage stage;
    @FXML
    private FlowPane board;
    @FXML
    private Button result;
    private GameState gameState;

    public void initialize() {

        gameState = Data.getInstance().getGameState();
        gameState.getPieces().addListener((ListChangeListener<? super Piece>) event -> Platform.runLater(this::displayPieces));

        Data.getInstance().getLastRequest().addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {


                String request = Data.getInstance().getLastRequest().getValue();
                System.out.println(request);
                if (request.startsWith("MOVE")) {
                    String[] args = request.split("_");

                    Vector2D start = new Vector2D(args[1], args[2]);
                    Vector2D destination = new Vector2D(args[3], args[4]);

                    Vector2D capture = new Vector2D(args[5], args[6]);

                    gameState.gameLogic.move(gameState, new Move(start, destination, capture));
                }

                if (request.startsWith("WON")) {

                    Data.getInstance().getLastRequest().deleteObserver(this);
                    Platform.runLater(() -> {
                        stage = (Stage) result.getScene().getWindow();
                        stage.close();
                        root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/resources/Victory.fxml"));
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
                if (request.startsWith("LOST")) {
                    Data.getInstance().getLastRequest().deleteObserver(this);
                    Platform.runLater(() -> {
                        stage = (Stage) result.getScene().getWindow();
                        stage.close();
                        root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/resources/Loss.fxml"));
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
        });

        selectedPiece.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {

                possibleMoves.clear();

                if (selectedPiece.getValue() == null) {

                    Platform.runLater(() -> displayPieces());
                    return;

                }
                possibleMoves.setAll(gameState.gameLogic.getPossibleMoves(gameState, selectedPiece.getValue()));
                Platform.runLater(() -> displayPieces());
            }
        });
        createBoard();
        displayPieces();
    }

    private void createBoard() {

        int size = (int) board.getPrefWidth() / gameState.getWidth();

        for (int y = 0; y < gameState.getHeight(); y++) {
            for (int x = 0; x < gameState.getWidth(); x++) {

                Rectangle pane = new Rectangle(size, size);

                Color color = (x + y) % 2 == 1 ? Color.web("769656") : Color.web("eeeed2");
                pane.setFill(color);

                StackPane square = new StackPane(pane);
                squares.add(square);

                board.getChildren().add(square);
            }
        }
    }

    private void clearPieces() {

        for (StackPane square : squares) {

            ObservableList<Node> nodes = square.getChildren();

            if (square.getChildren().size() > 1) square.getChildren().remove(1, nodes.size());
        }

    }

    private void displayPieces() {

        displayPieces(true);
    }

    private void displayPieces(boolean clear) {

        if (clear) clearPieces();

        for (Piece piece : gameState.getPieces()) {

            if (piece == null) continue;
            int i = gameState.getIdxByPosition(piece.x, piece.y);
            StackPane stackPane = squares.get(i);
            Circle circle = new Circle(13);
            circle.setFill(Color.web(piece.getColor().toHex()));
            circle.setStroke(Color.web("000000"));
            if (piece.getColor() == Data.getInstance().getCurrentColor()) circle.setOnMouseClicked(event -> {
                selectedPiece.setValue(piece);
            });
            stackPane.getChildren().add(1, circle);
        }

        for (Move move : possibleMoves) {
            System.out.println(move.toString());

            int i = gameState.getIdxByPosition(move.getDestination().x, move.getDestination().y);
            StackPane stackPane = squares.get(i);
            Circle circle = new Circle(7);
            circle.setFill(Color.web("00ff00"));
            circle.setOnMouseClicked(event -> {
                Client.request("MOVE_" + move.getStart().x + "_" + move.getStart().y + "_" + move.getDestination().x + "_" + move.getDestination().y + "_" + move.getCapture().x + "_" + move.getCapture().y);
                selectedPiece.setValue(null);
            });
            stackPane.getChildren().add(1, circle);


        }
    }
}
