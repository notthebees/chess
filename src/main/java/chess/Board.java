package chess;

import chess.pieces.Colour;
import chess.pieces.Piece;
import chess.pieces.Position;


public interface Board {

	Board play(Move move);
	boolean isOccupiedAt(Position position);
	Piece pieceAt(Position position);
	boolean isOccupiedBy(Colour colour, Position position);

}
