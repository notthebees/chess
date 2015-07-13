package chess.pieces;


public interface Piece {

	Colour colour();
	Position position();
	Piece moveTo(Position position);
	boolean moveIsIllegal(Position position);

}
