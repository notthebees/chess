package chess.pieces;

import chess.Board;


public interface Piece {

	Colour colour();
	Position position();
	Piece moveTo(Position position);
	boolean moveIsIllegal(Position position, Board board);
	boolean hasMoved();
	String print();
	boolean requiresReplacement();

}
