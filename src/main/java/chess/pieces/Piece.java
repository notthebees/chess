package chess.pieces;


public interface Piece {

	Colour colour();
	Position position();
	boolean canMoveTo(Position position);

}
