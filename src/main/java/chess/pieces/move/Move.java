package chess.pieces.move;

import java.util.Set;

import chess.Board;
import chess.pieces.Colour;
import chess.pieces.Piece;

public interface Move {

	Set<Piece> updatePieces(Board board);

	boolean isIllegal(Colour colour, Board board);

}