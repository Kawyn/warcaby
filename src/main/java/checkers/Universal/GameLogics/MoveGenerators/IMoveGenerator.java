package checkers.Universal.GameLogics.MoveGenerators;

import checkers.Universal.GameStates.GameState;
import checkers.Universal.Pieces.Piece;
import checkers.Universal.Structs.Move;

import java.util.ArrayList;

public interface IMoveGenerator {

    ArrayList<Move> getPossibleMoves(GameState gameState, Piece piece);
}
