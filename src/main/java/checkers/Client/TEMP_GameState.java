package checkers.Client;

import java.util.Arrays;
import java.util.List;

public class TEMP_GameState {


    public int BoardSizeX = 8;
    public  int BoardSizeY = 8;

    public List<TEMP_Piece> pieces = Arrays.asList(
            new TEMP_Piece(0, 0, "000000"),
            new TEMP_Piece(0, 1, "FFFFFF")
    );

    public static TEMP_GameState Instance = new TEMP_GameState();
}
