package checkers.Universal.GameStates;
import checkers.Universal.IGameLogic;
import checkers.Universal.Piece;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameState {

    public IGameLogic gameLogic;
    private int width;
    private int height;

    private final ObservableList<Piece> pieces = FXCollections.observableArrayList();

    public ObservableList<Piece> getPieces() {
        return pieces;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
