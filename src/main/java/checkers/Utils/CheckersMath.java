package checkers.Utils;

public class CheckersMath {

    public static int squarePositionToIdx(int x, int y) {
        return 8 * y + x;
    }

    public static int squareIdxToPositionX(int idx) {
        return idx % 8;
    }

    public static int squareIdxToPositionY(int idx) {
        return idx / 8;
    }
}
