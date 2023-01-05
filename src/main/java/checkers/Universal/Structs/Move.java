package checkers.Universal.Structs;

import checkers.Universal.Pieces.Piece;

public class Move {

    private Vector2D start;
    private Vector2D destination;
    private Piece capture;

    public Move(Vector2D start, Vector2D destination, Piece capture) {

        this.start = start;
        this.destination = destination;

        this.capture = capture;
    }

    public Vector2D getDestination() {
        return destination;
    }

    public void setDestination(Vector2D destination) {
        this.destination = destination;
    }

    public Vector2D getStart() {
        return start;
    }

    public void setStart(Vector2D start) {
        this.start = start;
    }

    public Piece getCapture() {
        return capture;
    }

    public void setCapture(Piece capture) {
        this.capture = capture;
    }

    @Override
    public String toString() {
        return start.x  + ", " + start.y + "|| " + destination.x + ", " + destination.y;
    }
}
