package checkers.Universal;

import checkers.Utils.Position;

import java.util.ArrayList;

public class Piece {

    public int x;
    public int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String color;

    public String getColor() {
        return color;
    }

    private ArrayList<Position> movePattern;

    public void setMovePattern(ArrayList<Position> movePattern) {
        this.movePattern = movePattern;
    }

    public ArrayList<Position> getMovePattern() {
        return movePattern;
    }
}
