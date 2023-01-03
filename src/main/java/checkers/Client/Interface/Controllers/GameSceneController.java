package checkers.Client.Interface.Controllers;

import checkers.Client.ClientData;
import checkers.Universal.GameStates.GameState;
import checkers.Universal.Move;
import checkers.Universal.Piece;
import checkers.Utils.CheckersMath;
import checkers.Utils.ObservableValue;
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

    @FXML
    private FlowPane board;

    private final List<StackPane> squares = new ArrayList<>();

    private ObservableValue<Piece> selectedPiece = new ObservableValue<>(null);

    private final ObservableList<Move> possibleMoves = FXCollections.observableArrayList();

    private GameState gameState;

    public void initialize() {

        gameState = ClientData.currentGameState;
        gameState.getPieces().addListener((ListChangeListener<? super Piece>) event -> displayPieces());

        selectedPiece.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {

                possibleMoves.clear();

                possibleMoves.setAll(gameState.gameLogic.GetPossibleMoves(selectedPiece.getValue()));

                displayPieces();
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

            if (square.getChildren().size() > 1)
                square.getChildren().remove(1, nodes.size());
        }

    }

    private void displayPieces() {

        displayPieces(true);
    }

    private void displayPieces(boolean clear) {

        if (clear)
            clearPieces();

        for (Piece piece : gameState.getPieces()) {

            if (piece == null)
                continue;
            ;
            int i = CheckersMath.squarePositionToIdx(piece.x, piece.y);
            StackPane stackPane = squares.get(i);
            Circle circle = new Circle(10);
            circle.setFill(Color.web(piece.getColor()));
            circle.setOnMouseClicked(event ->
            {
                selectedPiece.setValue(piece);
            });
            stackPane.getChildren().add(1, circle);
        }

        for (Move move : possibleMoves) {
            System.out.println(move);
            int i = CheckersMath.squarePositionToIdx(move.x, move.y);
            StackPane stackPane = squares.get(i);
            Circle circle = new Circle(7);
            circle.setFill(Color.web("00ff00"));
            circle.setOnMouseClicked(event ->
            {
                gameState.gameLogic.TryToMove(move);
            });
            stackPane.getChildren().add(1, circle);


        }
    }
}
