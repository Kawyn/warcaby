package checkers.Client.Interface.Controllers;

import checkers.Client.TEMP_GameState;
import checkers.Client.TEMP_Piece;
import checkers.Utils.CheckersMath;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;


public class GameSceneController {

    @FXML
    private FlowPane board;

    private final List<StackPane> squares = new ArrayList<>();

    public void initialize() {

        createBoard();
        displayPieces();
    }

    private void createBoard()  {

        int size = (int)board.getPrefWidth() / TEMP_GameState.Instance.BoardSizeX;

        for (int y = 0; y < TEMP_GameState.Instance.BoardSizeY; y++) {
            for (int x = 0; x < TEMP_GameState.Instance.BoardSizeX; x++) {

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

        for (StackPane square: squares) {

            ObservableList<Node> nodes = square.getChildren();

            if(square.getChildren().size() > 1)
                square.getChildren().remove(1, nodes.size() );
        }

    }

    private void displayPieces() {

        displayPieces(true);
    }

    private void displayPieces(boolean clear) {
    
        if(clear)
            clearPieces();

        for (TEMP_Piece piece: TEMP_GameState.Instance.pieces) {

            int i = CheckersMath.squarePositionToIdx(piece.x, piece.y);
            StackPane stackPane =  squares.get(i);
            Circle circle = new Circle(10);
            circle.setFill(Color.web(piece.getColor()));
            stackPane.getChildren().add(1, circle);
        }
    }
}
