package checkers.Client.Interface.Controllers;

import checkers.Client.Client;
import checkers.Client.ClientData;
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
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class GameSceneController {

    private final List<StackPane> squares = new ArrayList<>();
    private final ObservableList<Move> possibleMoves = FXCollections.observableArrayList();
    private final ObservableValue<Piece> selectedPiece = new ObservableValue<>(null);
    @FXML
    private FlowPane board;
    private GameState gameState;

    public void initialize() {

        gameState = ClientData.getInstance().getGameState();
        gameState.getPieces().addListener((ListChangeListener<? super Piece>) event -> Platform.runLater(this::displayPieces));

        ClientData.getInstance().getLastRequest().addObserver(((o, arg) -> {
            System.out.println("???");
            String request = ClientData.getInstance().getLastRequest().getValue();

            String[] args = request.split("_");

            Vector2D start = new Vector2D(args[1], args[2]);
            Vector2D destination = new Vector2D(args[3], args[4]);

            Vector2D capture = new Vector2D(args[5], args[6]);

            gameState.gameLogic.move(gameState, new Move(start, destination, capture));
        }));
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

                Color color = (x + y) % 2 == 1 ? Color.web("eeeed2") : Color.web("769656");
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
            Circle circle = new Circle(10);
            circle.setFill(Color.web(piece.getColor().toHex()));

            if(piece.getColor() == ClientData.getInstance().getGameState().getPlayerColor())
            circle.setOnMouseClicked(event -> {
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
