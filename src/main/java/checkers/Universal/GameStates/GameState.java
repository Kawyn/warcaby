package checkers.Universal.GameStates;

import checkers.Universal.GameLogics.GameLogic;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.PlayerColor;
import checkers.Universal.Structs.Vector2D;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class GameState {

    private final ObservableList<Piece> pieces = FXCollections.observableArrayList();
    public GameLogic gameLogic;
    private PlayerColor playerColor;
    private int width;
    private int height;

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public ObservableList<Piece> getPieces() {
        return pieces;
    }

    public ArrayList<Piece> getPiecesByColor(String color) {

        ArrayList<Piece> result = new ArrayList<>();

        for (Piece piece : pieces) {

            if (piece.getColor().equals(color)) result.add(piece);
        }

        return result;
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

    public boolean isInBounds(int x, int y) {
        return 0 <= x && x < width && 0 <= y && y < height;
    }

    public boolean isInBounds(Vector2D v) {
        return isInBounds(v.x, v.y);
    }

    public Piece getPieceByVector2D(Vector2D v) {
        return pieces.get(v.y * 8 + v.x);
    }

    public int getIdxByVector(Vector2D v) {
        return v.y * width + v.x;
    }

    public int getIdxByPosition(int x, int y) {
        return y * width + x;
    }
}
