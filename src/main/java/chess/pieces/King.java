package chess.pieces;

public class King implements Piece {

	private final Colour colour;
	private final Position position;

	public King(final Colour colour, final Position position) {
		this.colour = colour;
		this.position = position;
	}

	@Override
	public String toString() {
		return colour.toString() + " King @ " + position;
	}

}
