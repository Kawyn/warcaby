package checkers.Universal.Structs;

public class Vector2D {

    public int x;
    public int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(String x, String y) {
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
    }

    @Override
    public String toString() {
        return x + "_" + y;
    }
}
