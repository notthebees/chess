package chess;

import java.util.Set;

import chess.pieces.Colour;
import chess.pieces.Piece;
import chess.pieces.Position;
import chess.pieces.move.Move;


public interface Board {

	Set<Piece> pieces();
	Board play(Move move);
	boolean isOccupiedAt(Position position);
	Piece pieceAt(Position position);
	boolean isOccupiedBy(Colour colour, Position position);
	Set<Position> spacesAttackedBy(Piece piece);

}
